 <%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
 <jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
 
 <html >  
<head>
<meta name="decorator" content="iframe"/>
<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<link rel="stylesheet" href="/dhulims/css/iconFont.css">
<script type="text/javascript">

	//首页
	function first(url){
		document.queryForm.action=url;
		document.queryForm.submit();
	}
	//末页
	function last(url){
		document.queryForm.action=url;
		document.queryForm.submit();
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
		document.queryForm.action=url+page;
		document.queryForm.submit();
	}
	//下一页
	function next(url){
		var totalPage=${pageModel.totalPage};
		var page=${page};
		if(page>=totalPage){
			page=totalPage;
		}else{
			page=page+1
		}
		//alert("下一页的路径："+page);
		document.queryForm.action=url+page;
		document.queryForm.submit();
	}
	
	function cancel(){
		window.location.href="${pageContext.request.contextPath}/timetable/myAttendance?page=1";
	}
 </script> 
 <script type="text/javascript">
 //查看实到人数
 function showPeople(courseNo){
		var week=document.getElementById("memo").value;
		window.location.href="${pageContext.request.contextPath}/timetable/showPeople?courseNo="+courseNo+"&week="+week;
	}
	//查看缺勤人数
	function showAbsence(courseNo){
		var week=document.getElementById("memo").value;
		window.location.href="${pageContext.request.contextPath}/timetable/showAbsence?courseNo="+courseNo+"&week="+week;
	}
 </script>
</head>

<body>

<div class="navigation">
				<div id="navigation">
					<ul>
						<li><a href="javascript:void(0)">排课管理</a></li>
						<li class="end"><a href="javascript:void(0)">我的考勤</a></li>
					</ul>
				</div>
			</div>


<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
			<div class="title">
				<div id="title">我的考勤列表</div>	
				<a class="btn btn-new" href="javascript:void(0)">打印</a>
				<a class="btn btn-new" href="javascript:void(0)">Excel导出</a>

				
			</div>   	
			<div class="tool-box">
			  <form:form name="queryForm" action="${pageContext.request.contextPath}/timetable/attendanceManage" method="post" modelAttribute="schoolCourse">
			     <ul>
    				<li>学期： </li>
    				<li><form:select id="term" path="schoolTerm.id"  class="chzn-select">	
    				<form:option value="">请选择</form:option>
    				<c:forEach items="${schoolTerms}" var="t">
    				<%-- <c:if test="${term.id==t.id}">
    				<form:option value="${t.id}" selected="true">${t.termName}</form:option>
    				</c:if>
    				<c:if test="${t.id!=term.id}">
    				
    				</c:if> --%>
    				<form:option value="${t.id}">${t.termName}</form:option>
    				</c:forEach>			
    	          	</form:select>
    	          	</li>
                    <li>课程： </li>
    				<li><form:select id="courseNo" path="courseNo" class="chzn-select">		
    				<form:option value="">请选择</form:option>		
                   	<form:options   items="${schoolCourses}" itemLabel="courseName" itemValue="courseNo"/>
    	          	</form:select>
    	          	</li>
    	          	<li>教师： </li>
    				<li><form:select id="teacher" path="userByTeacher.username" class="chzn-select">	
    					<form:option value="">请选择</form:option>
                   	<form:options   items="${users}" itemLabel="cname" itemValue="username"/>
    	          	</form:select>
    	          	</li>
    	          	<li>周次： </li>
    				<li><form:select id="memo" path="memo" class="chzn-select">	
    					
    					<c:forEach items="${weeks}" var="w">
    					<%-- <c:if test="${w.key==week}">
    					<form:option value="${w.key}" selected="true">${w.value}</form:option>
    					</c:if>
    					<c:if test="${w.key!=week}">
    					<form:option value="${w.key}">${w.value}</form:option>
    					</c:if> --%>
    					<form:option value="${w.key}">${w.value}</form:option>
    					</c:forEach>
    	          	</form:select>
    	          	</li>
    	          	
                    <li><input type="submit" value="查询"/>
					<input type="button" value="取消" onclick="cancel();"/>
					</li>
    				</ul>
				</form:form>
		       </div>
    		<div class="content-box">   		
            <table  class="tb"  id="my_show"> 
                <thead>
                    <tr>
                        <th>选课组</th>
                        <th>学院</th>
                        <th>课程</th>
                        <th>教师</th>
                        <th>学期</th>
                        <th>班级人数</th>
                        <th>考勤次数</th>
                        <th>实到人数</th>
                        <th>缺勤人数</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${attList}" var="t">
                	<tr>
                	<td>${t.courseNo}</td>
                	<td>${t.college}</td>
                	<td>${t.courseName}</td>
                	<td>${t.teacher}</td>
                	<td>${t.term}</td>
                	<td><a title="查看学生名单" href="${pageContext.request.contextPath}/timetable/listOfStudent?courseNo=${t.courseNo}">${t.classesNumber}</a></td>
                	<td>${t.attendanceTime}</td>
                	<td><a title="查看实到人数" href="javascript:void(0)" onclick="showPeople('${t.courseNo}');">${t.people}</a></td>
                	<td><a title="查看缺勤人数" href="javascript:void(0)" onclick="showAbsence('${t.courseNo}');">${t.absence}</a></td>
                	</tr>
                	</c:forEach>
                	
                </tbody>
            </table>
        <%-- <div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0);"    onclick="first('${pageContext.request.contextPath}/timetable/attendanceManage?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0);" onclick="previous('${pageContext.request.contextPath}/timetable/attendanceManage?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/timetable/attendanceManage?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/timetable/attendanceManage?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0);"     onclick="next('${pageContext.request.contextPath}/timetable/attendanceManage?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0);"    onclick="last('${pageContext.request.contextPath}/timetable/attendanceManage?page=${pageModel.totalPage}')" target="_self">末页</a>
    </div> --%>
    
</div>
</div>
</div>
</div>
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
					<!-- 下拉框的js -->	
</body>
</html>