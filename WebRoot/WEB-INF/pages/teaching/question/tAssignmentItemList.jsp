<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>


<html>
<head>
<meta name="decorator" content="iframe"/>

<!-- 下拉框的样式 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->

<%--  <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery-1.8.3.min.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/thezzmes/icon.css" type="text/css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.gif" />
<script type="text/javascript">
	$(function(){
	   	$("#print").click(function(){
			$("#my_show").jqprint();
		});
	});

	//首页
	function first(url){
		//document.queryForm.action=url;
		//document.queryForm.submit();
		window.location = url;
	}
	//末页
	function last(url){
		//document.queryForm.action=url;
		//document.queryForm.submit();
		window.location = url;
	}
	//上一页
	function previous(url){
		var page=${page};
		if(page==1){
			page=1;
		}else{
			page=page-1;
		}
		//alert("上一页的路径："+url+page);
		//document.queryForm.action=url+page;
		//document.queryForm.submit();
		window.location = url+page;
	}
	//下一页
	function next(url){
		var totalPage=${pageModel.totalPage};
		var page=${page};
		if(page>=totalPage){
			page=totalPage;
		}else{
			page=page+1;
		}
		//alert("下一页的路径："+page);
		//document.queryForm.action=url+page;
		//document.queryForm.submit();
		window.location = url+page;
	}
</script>
</head>
<body>
	<div class="navigation">
		<div id="navigation">
			<ul>
				<li><a href="javascript:void(0)">教学管理</a></li>
				<li class="end"><a href="javascript:void(0)">题库</a></li>
			</ul>
		</div>
	</div>
	
	
	<div class="right-content">
		<div id="TabbedPanels1" class="TabbedPanels">
			<div class="TabbedPanelsContentGroup">
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">题目信息</div>
							<a class="btn btn-new" href="${pageContext.request.contextPath}/teaching/question/findQuestionList" >返回</a>
							<a class="btn btn-new" href="javascript:void(0);" onclick="importItems()">导入试题</a>
						</div>
						
					    
			    		<div class="content-box">   		
				            <table  class="tb"  id="my_show"> 
				                <thead>
				                	<tr>
				                		<th>序号</th>
				                		<th width="65%">试题</th>
					                	<th width="8%">创建人</th>
					                	<th width="12%">创建时间</th>
					                	<th width="10%">操作</th>
				                	</tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${tAssignmentItems }" var="tAssignmentItem" varStatus="i">
                                		<tr>
                                			<td>${i.count }</td>
	                                		<td style="padding-right: 10px;">
	                                			<c:if test="${tAssignmentItem.type==8 }">
		                                			<c:out value="${tAssignmentItem.descriptionTemp}"></c:out>
	                                			</c:if>
	                                			<c:if test="${tAssignmentItem.type!=8 }">
		                                			<c:out value="${tAssignmentItem.description}"></c:out>
	                                			</c:if>
	                                		</td>
	                                		<td>
	                                			${tAssignmentItem.user.cname }
	                                		</td>
	                                		<td>
	                                			<fmt:formatDate value="${tAssignmentItem.createdTime.time }" pattern="yyyy-MM-dd"/>
	                                		</td>
	                                		<td>
	                                			<a href="${pageContext.request.contextPath}/teaching/question/deleteTAssignmentItemById?questionId=${tAssignmentQuestionpool.questionpoolId }&itemId=${tAssignmentItem.id }">删除</a>
	                                		</td>
                                		</tr>
                                	</c:forEach>
                                </tbody>
				            </table>
					        <div class="page" >
						        ${totalRecords}条记录,共${pageModel.totalPage}页
						    	<a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/teaching/question/findTAssignmentItemsByQuestionId?id=${tAssignmentQuestionpool.questionpoolId }&currpage=1')" target="_self">首页</a>			    
								<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/teaching/question/findTAssignmentItemsByQuestionId?id=${tAssignmentQuestionpool.questionpoolId }&currpage=')" target="_self">上一页</a>
								第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
									<option value="${pageContext.request.contextPath}/teaching/question/findTAssignmentItemsByQuestionId?id=${tAssignmentQuestionpool.questionpoolId }&currpage=${page}">${page}</option>
										<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
									    	<c:if test="${j.index!=page}">
									    		<option value="${pageContext.request.contextPath}/teaching/question/findTAssignmentItemsByQuestionId?id=${tAssignmentQuestionpool.questionpoolId }&currpage=${j.index}">${j.index}</option>
									    	</c:if>
							    		</c:forEach>
						    	</select>页
								<a href="javascript:void(0)"  onclick="next('${pageContext.request.contextPath}/teaching/question/findTAssignmentItemsByQuestionId?id=${tAssignmentQuestionpool.questionpoolId }&currpage=')" target="_self">下一页</a>
						 		<a href="javascript:void(0)"  onclick="last('${pageContext.request.contextPath}/teaching/question/findTAssignmentItemsByQuestionId?id=${tAssignmentQuestionpool.questionpoolId }&currpage=${pageModel.totalPage}')" target="_self">末页</a>
						    </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 弹出导入试题窗口 -->
	<div id="importItems"  class="easyui-window panel-body panel-body-noborder window-body" title="导入试题" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width:700px; height:560px;">
		<form:form action="${pageContext.request.contextPath}/teaching/question/importItemsByQuestionId?questionId=${tAssignmentQuestionpool.questionpoolId }" enctype="multipart/form-data" onsubmit="return checkFileType()">
		<br>
		<p><b>导入试题（xls）</b></p>
		<hr>
		<br>
		<input type="file" name="file" id="file" required="required"/>
		<input type="submit" value="提交">
		<br>下载：<a href="${pageContext.request.contextPath}/pages/model/teaching/question/题库导入模板.xls">下载模板</a><br><br>
			范例：<br>
			<img src="${pageContext.request.contextPath}/images/model/teaching/question/ItemsExample.png" heigth="90%" width="90%" /><br>
			说明：
			第一列是题目类型，1：多选题；2：对错题；4：单选题；8：填空题；<br>
			第二列是题干，第三列是分值。<br><br>
			如果是多选题，则从第三列开始，每两列一组，分别是选项内容和对错，其中1表示正确，0表示错误；（注意正确选项不唯一）<br><br>
			如果是对错题，则第三列填写0或1，1表示正确，0表示错误；<br><br>
			如果是单选题，则从第三列开始，每两列一组，分别是选项内容和对错，其中1表示正确，0表示错误；（注意正确选项唯一，选项数目暂定为4项）<br><br>
			如果是填空题，注意第二列题干列的填写方式：<br>
			"填空题答案用大括号“{}”标记。<br>
			"例如：Roses are {red} and violets are {blue}. 则正确答案为“red”和“blue”。<br><br>
			"使用“|”分隔同义词，<br>
			"例如： {They are|They're} very happy. 则正确答案为“They are”或“They are”。<br><br>
			"使用星号(*)来表示通配符。<br>
			"例如： It's raining {c*} and {d*s}. 则形如“c...”和“d...s”的答案均为正确答案。<br><br>
			
		</form:form>
	</div>
<script type="text/javascript">
	function importItems(){
    	$("#importItems").show();
    	//获取当前屏幕的绝对位置
    	var topPos = window.pageYOffset+10;
    	//使得弹出框在屏幕顶端可见
    	$('#importItems').window({top:topPos+"px"});
    	$('#importItems').window('open');
    }
	
	function checkFileType(){
		var string = $("#file").val();
		if(string==""){
			alert("请参照样本上传excel文件！");
			return false;
		}else{
			var ss = string.split(".");
			var s = (ss[ss.length-1]).toLowerCase();			
			if(s=="xls"){
				$("#fileName").val(string);
				return true;
			}else{
				alert("请参照样本上传excel文件！");
				return false;
			}
		}
		
	}
</script>
</body>
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
</html>