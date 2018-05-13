 <%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
 <jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
 
 <html >  
<head>
<meta name="decorator" content="iframe"/>
<!-- 富文本的css -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css"/> 

<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/thezzmes/icon.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
	function checkUser(obj){
		if($(obj).val().trim()!=""){
			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/teaching/coursesitetag/checkUser",
				data: {'username':$(obj).val().trim()},
				dataType:'json',
				success:function(data){
					if(!data){
						alert("该工号不存在，请查询后输入！");
						$(obj).val("");
					}
				}
			});
			
		}
	}
	
/*
*编辑题目
*/
function editTAssignmentItem(examItemId){
var sessionId = $("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/teaching/exam/examItemInfo?examItemId='+ examItemId +'" style="width:100%;height:100%;"></iframe>'
$('#editTAssignmentItem').html(con);
//获取当前屏幕的绝对位置
var topPos = window.pageYOffset;
//使得弹出框在屏幕顶端可见
$('#editTAssignmentItem').window({left:"px", top:topPos+"px"});
$('#editTAssignmentItem').window('open');
}

	function openwindow(sectionId,countScore){
	    var con = '<iframe id="con" scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/teaching/question/checkQuestionListForSection?sectionId='+sectionId+'" style="width:100%;height:100%;"></iframe>'
	    $("#openwindow").html(con); 
	    //获取当前屏幕的绝对位置
	    var topPos = window.pageYOffset+10;
	    //使得弹出框在屏幕顶端可见
	    $('#openwindow').window({
	    	top:topPos+"px",
	    	onBeforeClose:function(){
	    		$("#con").remove();
				$("#openwindow").window('close',true);
	    		/**
	    		if(confirm("是否确认关闭视频?")){
	    			$("#con").remove();
	    			$("#mediaDisplay").window('close',true);
	    		}else{
	    			return false;
	    		}
	    		*/
	    	}
	    }); 
	    $("#openwindow").window('open');
	}
	function checkScore(countScore,score){
		if(countScore != score){
			alert("总分不符！");
			return false;
		}
		return confirm('确定要发布吗？');
	}

</script>
</head>

<body>
	
	<div class="right-content">
		<div id="TabbedPanels1" class="TabbedPanels">
		  	<div class="TabbedPanelsContentGroup">
		  	    <!--题目抬头  -->
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">测验信息</div>
							<%--<a class="btn btn-new" href="javascript:void(0)" onclick="window.history.go(-1)">返回</a>--%>
							<a class="btn btn-new" href="${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}&selected_courseSite=${tCourseSite.id}" >返回</a>
							<!-- 如果已发布，则不能再更改测压信息 -->
							<c:if test="${examInfo.status == 0 }">
								<a class="btn btn-new" href="${pageContext.request.contextPath}/teaching/exam/findExamById?examId=${examInfo.id }&tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}">编辑基本信息</a>
							</c:if>
						</div> 
						
						<div class="new-classroom">
                            <table  class="tb"  id="my_show"> 
				                <thead>
				                    <tr> 
							    	<th><label style="margin-left: 16px">所属站点：</label></th>
							    	<td><%--${examInfo.TCourseSite.title }--%></td>
							    	<th><label style="margin-left: 16px">测验标题：</label></th>
							    	<td>${examInfo.title }</td>
	                                </tr>
                                    <tr> 
							    	<th><label style="margin-left: 16px">开始时间：</label></th>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${examInfo.TAssignmentControl.startdate.time}" type="both"/></td>
                                    <th><label style="margin-left: 16px">截止时间：</label></th>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${examInfo.TAssignmentControl.duedate.time}" type="both"/></td>
                                    </tr>
                                    <tr>
                                        <th><label style="margin-left: 16px">测试说明：</label></th>
                                        <td>${examInfo.content }
                                        <th><label style="margin-left: 16px">教师：</label></th>
                                        <td>${examInfo.user.cname }
							 	     </td>
							 	     </tr>
							 	     <tr>
                                        <th><label style="margin-left: 16px">总分：</label></th>
                                        <td>
                                          <c:set var="countScore" value="0"></c:set>
                                          <c:forEach items="${examInfo.TAssignmentSections}" var="current"  varStatus="i">
                                              <c:forEach items="${current.TAssignmentItems}" var="current1"  varStatus="k">
                                                       <c:set var="countScore" value="${countScore+current1.score }"></c:set>
                                              </c:forEach>
                                          </c:forEach>
                                          ${countScore}/${examInfo.TAssignmentAnswerAssign.score}
							 	     </tr>
							</thead>
							</table>
							</div>
					</div>
				</div>
				<!--题目内容  -->
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">测验题目信息</div>
							<!-- 如果已发布，则不能再更改测压信息 -->
							<c:if test="${examInfo.status == 0 }">
								<mytag:JspSecurity realm="add" menu="exam">
									<a class="btn btn-new" href="${pageContext.request.contextPath}/teaching/exam/newExamSection?tCourseSiteId=${tCourseSite.id}&assignmentId=${examInfo.id }&moduleType=${moduleType}&selectId=${selectId}">添加大项</a>
								</mytag:JspSecurity>
							</c:if>
							<c:if test="${examInfo.status == 0 }">
								<a class="btn btn-new" href="${pageContext.request.contextPath}/teaching/exam/releaseSection?tCourseSiteId=${tCourseSite.id}&assignmentId=${examInfo.id }&moduleType=${moduleType}&selectId=${selectId}" onclick="return checkScore(${countScore},${examInfo.TAssignmentAnswerAssign.score})" >发布</a>
							</c:if>
						</div>
						<div class="new-classroom" >
                            <table  class="tb"  id="my_show"> 
				                <thead>
				                    <tr> 
							    	<!-- <th>序号</th>
							    	<th>题目名称</th>
							    	<th>题目类型</th>
							    	<th>分值</th>
							    	<th>创建时间</th>
							    	<th>删除</th> -->
							    	<th width=10%>序号</th>
							    	<th width=90%>
							    	 <table  class="tb"  id="my_show"> 
							    	 <tr>
							    	  <td width=65%>题目信息</td>
							    	  <td width=10%>题目类型</td>
							    	  <td width=10%>分值</td>
							    	  <td width=10%>操作</td>
							    	  </tr>
							    	  </table>
							    	  </th>
							    	<%--<th>
							    	<table  class="tb"  id="my_show"> 
							    	</table>
							    	
							    	</th>
	                                --%></tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${examInfo.TAssignmentSections}" var="current"  varStatus="i">
                                    <tr>
                                        <td>
                                        	${i.count }.<c:out value="${current.description}"></c:out><br>
                                        	<a href="javascript:void(0);" onclick="openwindow(${current.id},${countScore})">从题库选题</a>
                                        </td>
                                        <td>
							    	    <c:if test="${current.TAssignmentItems.size()==0}">
							    	    	<mytag:JspSecurity realm="add" menu="exam">
							    	        	<a href="${pageContext.request.contextPath}/teaching/exam/newExamItemForOne?sectionId=${current.id}&tCourseSiteId=${tCourseSite.id}">添加题目</a>
							    	    	</mytag:JspSecurity>
							    	    </c:if>
                                        <table  class="tb"  id="my_show"> 
                                        <c:forEach items="${current.TAssignmentItems}" var="current1"  varStatus="j">
                                        	<tr>
                                            	<td width=65% style="padding: 10px;">${j.count}、题干：<c:out value="${current1.description}"></c:out><br>
                                                	<c:forEach items="${current1.TAssignmentAnswers}" var="current2"  varStatus="k">
		                                                <!-- 若是选择题则把选项显示出来 -->
		                                                <c:if test="${current1.type==1||current1.type==4 }">
		                                                	<p><c:out value="${current2.label}: ${current2.text}"></c:out></p>
		                                                	 
		                                                </c:if>
	                                                
	                                                </c:forEach>
                                               
                                               </td>
                                               <td width=10%>
		                                           	<c:if test="${current1.type==1}">多选题</c:if>
		                                           	<c:if test="${current1.type==2}">对错题</c:if>
		                                           	<c:if test="${current1.type==4}">单选题</c:if>
		                                           	<c:if test="${current1.type==5}">简答题</c:if>
		                                           	<c:if test="${current1.type==8}">填空题</c:if>
		                                           	<c:if test="${current1.type==9}">匹配题</c:if>
                                               </td>
                                               <td width=10%>${current1.score}</td>
                                               <td width=10%>
                                               		<mytag:JspSecurity realm="add" menu="exam">
	                                                   	<a href="${pageContext.request.contextPath}/teaching/exam/newExamItem?sectionId=${current.id}&tCourseSiteId=${tCourseSiteId}&moduleType=${moduleType}&selectId=${selectId}&examId=${examId}">添加题目</a><br><br>
													</mytag:JspSecurity>
                                               		<mytag:JspSecurity realm="update" menu="exam">
	                                                   	<a href="${pageContext.request.contextPath}/teaching/exam/examItemInfoSelect?examItemId=${current1.id}&tCourseSiteId=${tCourseSiteId}&examId=${examId}&moduleType=${moduleType}&selectId=${selectId}">编辑题目</a><br><br>
                                               		</mytag:JspSecurity>
                                               		<mytag:JspSecurity realm="delete" menu="exam">
	                                                   	<a href='${pageContext.request.contextPath}/teaching/exam/deleteTAssignmentItemById?examItemId=${current1.id}&tCourseSiteId=${tCourseSiteId}&examId=${examId}&moduleType=${moduleType}&selectId=${selectId}' onclick="return confirm('是否确认删除该题目？')">删除题目</a>
                                               		</mytag:JspSecurity>
                                               </td>
                                            </tr>
                                            
                                        </c:forEach>
                                        </table>
                                        </td>
                                     </tr> 
                                    </c:forEach>
                                    </tbody>
							</table>
							</div>
							
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- 编辑题目 -->
<div id="editTAssignmentItem" class="easyui-window" title="编辑题目" closed="true"  modal="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>

<div id="openwindow" class="easyui-window" title="选择题库" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width: 900px; height:450px;">
</div>

<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select': {search_contains : true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
</script>
</body>


</html>