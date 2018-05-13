<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<%--<%@ include file="/WEB-INF/sitemesh-decorators/course.jsp" %>
--%><html>

<head>
    <title></title>
    <!--[if lt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <![endif]-->
    <!--[if gte IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>  
    <![endif]-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/reset.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/lib.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/global.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/jquery.searchableSelect.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>


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
    <div class="course_con ma">
        <div class="course_cont r">
            <ul class="tool_box cf rel zx2 pt5  pb10">
                <li class="rel l pb5">
                	<c:if test="${flag==1}">
                    <div class="wire_btn Lele l ml30 mt10 poi" onclick="importItems()">
                        <i class="fa fa-plus mr5"></i>导入试题
                    </div>
                    </c:if>
                    <div class="wire_btn Lele l ml30 mt10 poi" onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=1&selectId=-1'">
                        <i class="fa mr5"></i>课程知识
                    </div>
                    <div class="wire_btn Lele l ml30 mt10 poi">
	                    <a href="${pageContext.request.contextPath}/tcoursesite/question/findQuestionList?tCourseSiteId=${tCourseSiteId}">返回</a>
                    </div>
                </li>
            </ul>
            <div class="course_mod f14 mb2">
                <div class=" lh40 bgg  pl30 f18 ">
                    <span class="bc">课程题库</span>
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14">
                            <table class="w100p">
                                <tr>
                                    <th class="w5p tl">序号</th>
                                    <th class="w50p tl ">试题</th>
                                    <th class="w15p tl">创建人</th>
                                    <th class="w20p tl">创建时间</th>
                                    <th class="tl w10p">操作</th>                                    
                                </tr>
                               <c:forEach items="${tAssignmentItems }" var="tAssignmentItem" varStatus="i">
	                                <tr>
	                                    <td><a href="javascript:void(0);" class="g3 hbc">${i.count}</a></td>
	                                    <td>
	                                    	<c:if test="${tAssignmentItem.type==8 }">
	                                			<c:out value="${tAssignmentItem.descriptionTemp}"></c:out>
                                			</c:if>
                                			<c:if test="${tAssignmentItem.type!=8 }">
                                				<c:out value="${tAssignmentItem.description}"></c:out>
                                			</c:if>
                               			</td>
	                                    <td>${tAssignmentItem.user.cname }</td>
	                                    <td><fmt:formatDate value="${tAssignmentItem.createdTime.time }" pattern="yyyy-MM-dd"/>
	                                    </td>
	                                    <td>
	                                    	<c:if test="${flag==1}">
	                                        <a href="${pageContext.request.contextPath}/tcoursesite/question/deleteTAssignmentItemById?tCourseSiteId=${tCourseSite.id }&questionId=${tAssignmentQuestionpool.questionpoolId }&itemId=${tAssignmentItem.id }" onclick="return confirm('删除该题库会同时删除下面的试题,确认删除该题库？')" class="g9 hbc">
	                                        	<i class="fa fa-trash-o f18 mr10  lh30 poi" ></i></a>
	                                        </c:if>
	                                    </td>
	                                </tr>
                               </c:forEach>

                            </table>
                        </div>
                    </div>
                </div>
            </div>
            
            
            
        </div>
        
        <div class="page course_cont r" >
	        ${totalRecords}条记录,共${pageModel.totalPage}页
	    	<a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/tcoursesite/question/findTAssignmentItemsByQuestionId?tCourseSiteId=${tCourseSite.id }&id=${tAssignmentQuestionpool.questionpoolId }&currpage=1')" target="_self">首页</a>			    
			<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/tcoursesite/question/findTAssignmentItemsByQuestionId?tCourseSiteId=${tCourseSite.id }&id=${tAssignmentQuestionpool.questionpoolId }&currpage=')" target="_self">上一页</a>
			第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
				<option value="${pageContext.request.contextPath}/tcoursesite/question/findTAssignmentItemsByQuestionId?tCourseSiteId=${tCourseSite.id }&id=${tAssignmentQuestionpool.questionpoolId }&currpage=${page}">${page}</option>
					<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
				    	<c:if test="${j.index!=page}">
				    		<option value="${pageContext.request.contextPath}/tcoursesite/question/findTAssignmentItemsByQuestionId?tCourseSiteId=${tCourseSite.id }&id=${tAssignmentQuestionpool.questionpoolId }&currpage=${j.index}">${j.index}</option>
				    	</c:if>
		    		</c:forEach>
	    	</select>页
			<a href="javascript:void(0)"  onclick="next('${pageContext.request.contextPath}/tcoursesite/question/findTAssignmentItemsByQuestionId?tCourseSiteId=${tCourseSite.id }&id=${tAssignmentQuestionpool.questionpoolId }&currpage=')" target="_self">下一页</a>
	 		<a href="javascript:void(0)"  onclick="last('${pageContext.request.contextPath}/tcoursesite/question/findTAssignmentItemsByQuestionId?tCourseSiteId=${tCourseSite.id }&id=${tAssignmentQuestionpool.questionpoolId }&currpage=${pageModel.totalPage}')" target="_self">末页</a>
	    </div>
        
        
    </div>
    <div class="window_box hide fix zx2  " id="importItems" title="导入试题">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p20 bb f16">导入试题</div>
            <div class="add_con p20">
            	<form:form action="${pageContext.request.contextPath}/tcoursesite/question/importItemsByQuestionId?tCourseSiteId=${tCourseSite.id }&questionId=${tAssignmentQuestionpool.questionpoolId }" enctype="multipart/form-data" onsubmit="return checkFileType()">
				<br>
				<p><b>导入试题</b></p>
				<hr>
				<br>
				<input type="file" name="file" id="file" required="required"/>
				<input type="submit" value="提交">
				<br>下载：<a href="${pageContext.request.contextPath}/pages/tCourseSite/question/example.xls">Excel模板</a>&nbsp;
						<a href="${pageContext.request.contextPath}/pages/tCourseSite/question/example.txt">Txt模板（utf-8编码格式）</a><br><br>
					<b>Excel模板	说明：</b>
					第一列是题目类型，1：多选题；2：对错题；4：单选题；8：填空题；第二列是题干，第三列是分值。
					<img src="${pageContext.request.contextPath}/images/model/teaching/question/ItemsExample.png" heigth="90%" width="90%" /><br>
					如果是多选题，则从第三列开始，每两列一组，分别是选项内容和对错，其中1表示正确，0表示错误；（注意正确选项不唯一）<br>
					如果是对错题，则第三列填写0或1，1表示正确，0表示错误；<br>
					如果是单选题，则从第三列开始，每两列一组，分别是选项内容和对错，其中1表示正确，0表示错误；（注意正确选项唯一）<br>
					如果是填空题，则第二列填写题干，第三列填写答案<br>
					填空题注意第二列题干列的填写方式：<br>
					填空题答案用小括号“（）”代替。<br>					
					例如： They（ ） very happy。<br><br>
					<b>txt模板（utf-8编码格式）         说明：</b>
					所有问题的题干写在一行，答案写在另一行内，题干以双冒号(英文格式)“::”开始；<br>
					如果是多选题，答案写在大括号内，答案之间以空格隔开，答案前加标记位，“=”表示正确，“~”表示错误；（注意正确选项不唯一）<br>
					如果是判断题，答案写在大括号内，正确写“TRUE”，错误写“FALSE”。<br>
					如果是单选题，答案写在大括号内，答案之间以空格隔开，答案前加标记位，“=”表示正确，“~”表示错误；（注意正确选项唯一）<br>
					如果是填空题，答案写在大括号内，只有一个答案。<br>
				</form:form>
            </div>
        </div>
    </div>
  
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>

<script type="text/javascript">
	function importItems(){
    	$("#importItems").fadeIn(100);
    }
	
	function checkFileType(){
		var string = $("#file").val();
		if(string==""){
			alert("请参照样本上传excel文件或txt文件！");
			return false;
		}else{
			var ss = string.split(".");
			var s = (ss[ss.length-1]).toLowerCase();			
			if(s=="xls"||s=="txt"){
				$("#fileName").val(string);
				return true;
			}else{
				alert("请参照样本上传excel文件或txt文件！");
				return false;
			}
		}
		
	}
</script>

</body>

</html>