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
<link rel="stylesheet" href="/xidlims/css/iconFont.css">
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
				<a class="btn btn-new" href="javascript:void(0)">返回上一页</a>

				
			</div>   	
			<div class="tool-box">
			  <form:form name="queryForm" action="${pageContext.request.contextPath}/timetable/myAttendance?page=1" method="post" modelAttribute="timetableAppointment">
			     <ul>
    				<li>选择学期： </li>
    				<li><form:select id="term" path="schoolCourse.schoolTerm.id" class="chzn-select">				
                   <form:options   items="${schoolTerm}" itemLabel="termName" itemValue="id"/>
    	          </form:select></li>
                      <li>姓名：</li>
                     <li> <form:input path="schoolCourse.userByCreatedBy.username" value="${user.cname}"/></li>
                      <%--
                      <li><form:select id="user" path="schoolCourse.userByCreatedBy.id" class="chzn-select">				
                   <form:options   items="${user}" itemLabel="termName" itemValue="id"/>
    	          </form:select></li>
    				--%><li><input type="submit" value="查询"/>
					    <input type="button" value="取消" onclick="cancel();"/></li>
    				</ul>
				</form:form>
		       </div>
    		<div class="content-box">   		
            <table  class="tb"  id="my_show"> 
                <thead>
                    <tr>
                        <th></th>
                        <th>周次</th>
                        <th>星期</th>
                        <th>节次</th>
                        <th>课程名称</th>
                        <th>实验室</th>
                        <th>实验项目名称</th>
                        <th>是否到勤</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${listTimetableAppointment}" var="s"  varStatus="i">	
                	<c:if test="${s.timetableAppointmentSameNumbers.size()==0 }">
                        <tr>
                            <td>${i.count}</td>
                            <td>第${s.totalWeeks}周</td>
                            <td>${s.weekday}</td>
                            <td>${s.totalClasses}</td>
                            <td>
                            <c:if test="${s.timetableStyle==5||s.timetableStyle==6}">${s.timetableSelfCourse.schoolCourseInfo.courseName } 
                            </c:if>
                            <c:if test="${s.timetableStyle!=5&&s.timetableStyle!=5}">${s.schoolCourseInfo.courseName}
                            </c:if>
                            </td>
                            <td>
                            
                            <c:forEach items="${s.timetableLabRelateds}" var="m">
                            ${m.labRoom.labRoomName}
                            </c:forEach>
                            </td>
                            <td>
                           
                            <c:forEach items="${s.timetableItemRelateds}" var="p">  
	                            ${p.operationItem.lpName}
	                            </c:forEach>
                            </td>
                            <td>
                            <c:if test="${s.timetableAttendances.size()!=0}">
                              <image src="${pageContext.request.contextPath}/images/icn_alert_success.png"/>                                                                               
                            </c:if>
                            <c:if test="${s.timetableAttendances.size()==0}">
                              <image src="${pageContext.request.contextPath}/images/icn_alert_error.png"/>                                                  
                            </c:if>
                            </td>
                        </tr>
                        </c:if>
                        <c:if test="${s.timetableAppointmentSameNumbers.size()!=0 }">
                        <c:forEach items="${s.timetableAppointmentSameNumbers}" var="lm">
                        <tr>
                            <td>${i.count}</td>
                            <td>第${lm.totalWeeks}周</td>
                            <td>${lm.weekday}</td>
                            <td>${lm.totalClasses}</td>
                            <td>
                            <c:if test="${s.timetableStyle==5||s.timetableStyle==6}">${s.timetableSelfCourse.schoolCourseInfo.courseName } 
                            </c:if>
                            <c:if test="${s.timetableStyle!=5&&s.timetableStyle!=5}">${s.schoolCourseInfo.courseName}
                            </c:if>
                            </td>
                            <td>
                            
                            <c:forEach items="${s.timetableLabRelateds}" var="m">
                            ${m.labRoom.labRoomName}
                            </c:forEach>
                            </td>
                            <td>
                           
                            <c:forEach items="${s.timetableItemRelateds}" var="p">  
	                            ${p.operationItem.lpName}
	                            </c:forEach>
                            </td>
                            <td>
                            <c:if test="${s.timetableAttendances.size()!=0}">
                              <image src="${pageContext.request.contextPath}/images/icn_alert_success.png"/>                                                                               
                            </c:if>
                            <c:if test="${s.timetableAttendances.size()==0}">
                              <image src="${pageContext.request.contextPath}/images/icn_alert_error.png"/>                                                  
                            </c:if>
                            </td>
                        </tr>
                        </c:forEach>
                        </c:if>
                        </c:forEach>
                </tbody>
            </table>
        <div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/timetable/myAttendance?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/timetable/myAttendance?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/timetable/myAttendance?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/timetable/myAttendance?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/timetable/myAttendance?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/timetable/myAttendance?page=${pageModel.totalPage}')" target="_self">末页</a>
    </div>
    
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