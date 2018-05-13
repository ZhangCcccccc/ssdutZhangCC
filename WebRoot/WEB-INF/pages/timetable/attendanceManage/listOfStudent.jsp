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
<script type="text/javascript"><%--

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
	
	
 --%>
 function cancel(){
		window.location.href="${pageContext.request.contextPath}/timetable/attendanceManage";
	}
 </script> 
</head>

<body>

<div class="navigation">
				<div id="navigation">
					<ul>
						<li><a href="javascript:void(0)">排课管理</a></li>
						<li class="end"><a href="javascript:void(0)">我的考勤</a></li>
						<li class="end"><a href="javascript:void(0)">学生名单</a></li>
					</ul>
				</div>
			</div>


<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
			<div class="title">
				<div id="title">学生名单</div>	
				<a class="btn btn-new" href="javascript:void(0)" onclick="cancel();">返回</a>

				
			</div>   	
			
    		<div class="content-box">   		
            <table  class="tb"  id="my_show"> 
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>选课组编号</th>
                        <th>课程名称</th>
                        <th>学生编号</th>
                        <th>学生姓名</th>
                        <th>授课教师</th>
                        <th>学院名称</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${studentList}" var="t" varStatus="i">
                	<tr>
                	<td>${i.count}</td>
                	<td>${t.schoolCourseDetail.schoolCourse.courseNo}</td>
                	<td>${t.schoolCourseDetail.schoolCourse.courseName}</td>
                	<td>${t.userByStudentNumber.username}</td>
                	<td>${t.userByStudentNumber.cname}</td>
                	<td>${t.userByTeacherNumber.cname} </td>
                	<td>${t.schoolAcademy.academyName}</td>
                	
                	</tr>
                	</c:forEach>
                	
                </tbody>
            </table>
        <%-- <div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/timetable/attendanceManage?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/timetable/attendanceManage?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/timetable/attendanceManage?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/timetable/attendanceManage?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/timetable/attendanceManage?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/timetable/attendanceManage?page=${pageModel.totalPage}')" target="_self">末页</a>
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