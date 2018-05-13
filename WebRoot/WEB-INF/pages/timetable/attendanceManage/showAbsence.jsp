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
						<li class="end"><a href="javascript:void(0)">缺勤人数</a></li>
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
			<div class="tool-box">
			<tr><td>课程名称：</td><td>${schoolCourse.courseName}</td><td>周次：</td>
			<td>
			<c:if test="${week==0}">全部</c:if>
			<c:if test="${week!=0}">第${week}周</c:if>
			 </td>
			</tr>
			</div>
    		<div class="content-box">   		
            <table  class="tb"  id="my_show"> 
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>缺勤次数</th>
                        
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${people}" var="p" varStatus="i">
                	<tr>
                	<td>${i.count}</td>
                	<td>${p.username}</td>
                	<td>${p.cname}</td>
                	<td>${p.time}</td>
                	
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