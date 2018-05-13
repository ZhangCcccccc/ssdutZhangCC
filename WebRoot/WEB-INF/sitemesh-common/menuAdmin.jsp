<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" import="java.util.*" import="java.sql.*" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iStyle_Feelings_Base.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
</head>
<div class="iStyle_Feelings_Tree_Buttons">
<div class="iStyle_Feelings_Tree_Trunks" actived="true">
	<c:if test="${empty sessionScope.turnType || not empty  sessionScope.turnType && (sessionScope.turnType eq 'message' || sessionScope.turnType eq 'myInfo')}">
	<div class="iStyle_Feelings_Tree_Trunk">
        <a class="iStyle_Feelings_Tree_TrunkTitle iStyle_Feelings_Tree_Opened">
        <div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>我的工作平台</a>
        <div class="iStyle_Feelings_Tree_Leaves">
        	 <c:if test="${sessionScope.turnType eq 'message' }">
	        	<c:if test="${messageNum eq 0 }">
					<a class="iStyle_Feelings_Tree_Leaf iStyle_Feelings_Tree_ActiveLeaf" href="${pageContext.request.contextPath}/personal/messageList?currpage=1">我的消息</a>
	            </c:if>
	            <c:if test="${messageNum ne 0 }">
					<a class="iStyle_Feelings_Tree_Leaf iStyle_Feelings_Tree_ActiveLeaf" href="${pageContext.request.contextPath}/personal/messageList?currpage=1">我的消息<font class="message_number">${messageNum }</font></a>
	            </c:if>
	        </c:if>
            <c:if test="${sessionScope.turnType eq 'myInfo' }">
            <a class="iStyle_Feelings_Tree_Leaf iStyle_Feelings_Tree_ActiveLeaf" href="${pageContext.request.contextPath}/personal/listMyInfo">我的信息</a>
            </c:if>
        </div>
	</div>
	</c:if>
	<c:if test="${not empty sessionScope.turnType && sessionScope.turnType eq 'operation'}">
	<sec:authorize ifNotGranted="ROLE_STUDENT">
		<div class="iStyle_Feelings_Tree_Trunk">
	        <a class="iStyle_Feelings_Tree_TrunkTitle  iStyle_Feelings_Tree_Opened"><div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>实验课程</a>
	        <div class="iStyle_Feelings_Tree_Leaves">
	        <sec:authorize ifAnyGranted="ROLE_EQUIPMENTADMIN,ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING,ROLE_TEACHER">
	            <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/operation/experimentalmanagement?currpage=1">实验大纲管理</a>
	        </sec:authorize>    
	            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=0&orderBy=9">我的实验项目</a>
	            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&orderBy=9">实验项目导入</a>
	        	<a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/operation/project/projectsummary?currpage=1&orderBy=9">实验项目汇总</a>
	        </div>  
		</div>
	</sec:authorize>
	</c:if>
	
	<c:if test="${empty sessionScope.turnType}">
	<sec:authorize ifNotGranted="ROLE_STUDENT">
		<div class="iStyle_Feelings_Tree_Trunk">
	        <a class="iStyle_Feelings_Tree_TrunkTitle"><div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>实验课程</a>
	        <div class="iStyle_Feelings_Tree_Leaves">
	        <sec:authorize ifAnyGranted="ROLE_EQUIPMENTADMIN,ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING,ROLE_TEACHER">
	            <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/operation/experimentalmanagement?currpage=1">实验大纲管理</a>
	            <%--<a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=0&orderBy=9">实验项目管理</a>
	        --%></sec:authorize>    
	            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=0&orderBy=9">我的实验项目</a>
	            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&orderBy=9">实验项目导入</a>
	        	<a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/operation/project/projectsummary?currpage=1&orderBy=9">实验项目汇总</a>
	        </div>  
		</div>
	</sec:authorize>
	</c:if>
<c:if test="${not empty sessionScope.turnType && sessionScope.turnType eq 'labRoom'}">
<sec:authorize ifAnyGranted="ROLE_SUPERADMIN, ROLE_LABCENTERMANAGER, ROLE_TEACHER, ROLE_LABMANAGER">
<div class="iStyle_Feelings_Tree_Trunk">
        <a class="iStyle_Feelings_Tree_TrunkTitle iStyle_Feelings_Tree_Opened"><div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>实验室管理</a>
        <div class="iStyle_Feelings_Tree_Leaves">
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/labRoom/selectCenter">实验室管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/labRoom/listLabWorker?currpage=1">实验室人员管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/labRoom/listLabRoomCourseCapacity?currpage=1">实验室容量与课程的关系管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/labRoom/entranceManageForLab?page=1">门禁记录</a>
        </div>  
</div>
</sec:authorize>
</c:if>

<c:if test="${empty sessionScope.turnType}">
<sec:authorize ifAnyGranted="ROLE_SUPERADMIN, ROLE_LABCENTERMANAGER, ROLE_TEACHER, ROLE_LABMANAGER">
<div class="iStyle_Feelings_Tree_Trunk">
        <a class="iStyle_Feelings_Tree_TrunkTitle"><div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>实验室管理</a>
        <div class="iStyle_Feelings_Tree_Leaves">
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/labRoom/selectCenter">实验室管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/labRoom/listLabWorker?currpage=1">实验室人员管理</a>
        </div>  
</div>
</sec:authorize>
</c:if>

<c:if test="${not empty sessionScope.turnType && sessionScope.turnType eq 'labReservation'}">
<div class="iStyle_Feelings_Tree_Trunk">
        <a class="iStyle_Feelings_Tree_TrunkTitle  iStyle_Feelings_Tree_Opened"><div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>实验室预约管理</a>
        <div class="iStyle_Feelings_Tree_Leaves"> 
            <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/lab/labAnnexList?currpage=1">实验室预约列表</a>
	        <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/labreservation/Labreservationmanage?tage=0&currpage=1">实验室预约统计</a>
        </div>  
</div>
</c:if>

<c:if test="${empty sessionScope.turnType}">
<div class="iStyle_Feelings_Tree_Trunk">
        <a class="iStyle_Feelings_Tree_TrunkTitle"><div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>实验室预约管理</a>
        <div class="iStyle_Feelings_Tree_Leaves"> 
            <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/lab/labAnnexList?currpage=1">实验室预约列表</a>
            <sec:authorize ifNotGranted="ROLE_STUDENT">
	        <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/labreservation/Labreservationmanage?tage=0&currpage=1">实验室预约统计</a>
	        </sec:authorize>
        </div>  
</div>
</c:if>
<c:if test="${not empty sessionScope.turnType && sessionScope.turnType eq 'timetable'}">
<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING, ROLE_TEACHER, ROLE_COURSETEACHER, ROLE_STUDENT">
<div class="iStyle_Feelings_Tree_Trunk">
   <a class="iStyle_Feelings_Tree_TrunkTitle iStyle_Feelings_Tree_Opened">
   <div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>实验室排课管理</a>
        <div class="iStyle_Feelings_Tree_Leaves">
	    <sec:authorize ifNotGranted="ROLE_STUDENT">
	    	<a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1">排课管理</a>
	    	<a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/timetable/attendanceManageIframe?currpage=1&id=${center.id}&status=-1">考勤管理</a>
	 	</sec:authorize>
	</div>
</div>
</sec:authorize>
</c:if>


<c:if test="${empty sessionScope.turnType}">
<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING, ROLE_TEACHER, ROLE_STUDENT">
<div class="iStyle_Feelings_Tree_Trunk">
   <a class="iStyle_Feelings_Tree_TrunkTitle">
   <div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>实验室排课管理</a>
        <div class="iStyle_Feelings_Tree_Leaves">
	    <sec:authorize ifNotGranted="ROLE_STUDENT">
		    <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING, ROLE_TEACHER">
			    <%--<a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/timetable/listTimetableTerm?currpage=1&id=-1">教务排课管理</a>
			    --%>
			    <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/timetable/listTimetableIframe">排课</a>
			    <%--<a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/timetable/listReTimetable">二次排课(含分批)</a>
			    <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/timetable/selfTimetable/listSelfTimetable">自主排课</a>
		    --%>
		    	<%--<a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/timetable/listTCurriculumSchedule">排课课表</a>
		    	--%><a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/timetable/listReTimetable">排课课表</a>
		    </sec:authorize>
	    	<a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1">排课管理</a>
	 	</sec:authorize>
	    <sec:authorize ifAnyGranted="ROLE_STUDENT">
	    <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/timetable/reTimetable/listGroupTimetableStudentSelect">学生选课</a>
	    </sec:authorize>
	    
	    <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING,ROLE_TEACHER">
	    <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/timetable/attendanceManageIframe?currpage=1&id=-1&status=-1">考勤管理</a>
	    </sec:authorize>
	    
	    <sec:authorize ifAnyGranted="ROLE_STUDENT">
	    <div class="is_Leaves" src="${pageContext.request.contextPath}/timetable/attendanceManage">我的考勤</div>
	    </sec:authorize>
	</div>
</div>
</sec:authorize>
</c:if>
<c:if test="${not empty sessionScope.turnType && sessionScope.turnType eq 'content'}">
<sec:authorize ifAnyGranted="ROLE_SUPERADMIN, ROLE_TEACHER">
<div class="iStyle_Feelings_Tree_Trunk">
        <a class="iStyle_Feelings_Tree_TrunkTitle iStyle_Feelings_Tree_Opened"><div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>实验项目内容</a>
        <div class="iStyle_Feelings_Tree_Leaves">
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/tcoursesite/listTCourseSite?currpage=1">课程列表</a>
        </div>  
</div>
</sec:authorize>
</c:if>

<c:if test="${empty sessionScope.turnType}">
<sec:authorize ifAnyGranted="ROLE_SUPERADMIN, ROLE_TEACHER">
<div class="iStyle_Feelings_Tree_Trunk">
        <a class="iStyle_Feelings_Tree_TrunkTitle"><div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>实验项目内容</a>
        <div class="iStyle_Feelings_Tree_Leaves">
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/tcoursesite/listTCourseSite?currpage=1">课程列表</a>
        </div>  
</div>
</sec:authorize>
</c:if>

<c:if test="${not empty  sessionScope.turnType && sessionScope.turnType eq 'system'}">
<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_COLLEGELEADER,ROLE_DEPARTMENTHEAD,ROLE_ASSETMANAGEMENT, ROLE_LABCENTERMANAGER">
<div class="iStyle_Feelings_Tree_Trunk">
        <a class="iStyle_Feelings_Tree_TrunkTitle iStyle_Feelings_Tree_Opened"><div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>系统管理</a>
        <div class="iStyle_Feelings_Tree_Leaves">
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/system/listUser?currpage=1">用户管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/userAuthorityMange/listUserAuthority">权限管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/system/listTerm?currpage=1">学期管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/system/timeManage">节次管理</a>
        </div>  
</div>
</sec:authorize>
</c:if>

<c:if test="${empty sessionScope.turnType}">
<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_COLLEGELEADER,ROLE_DEPARTMENTHEAD,ROLE_ASSETMANAGEMENT, ROLE_LABCENTERMANAGER">
<div class="iStyle_Feelings_Tree_Trunk">
        <a class="iStyle_Feelings_Tree_TrunkTitle"><div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>系统管理</a>
        <div class="iStyle_Feelings_Tree_Leaves">
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/system/listUser?currpage=1">用户管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/userAuthorityMange/listUserAuthority">权限管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/system/listTerm?currpage=1">学期管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/system/timeManage">节次管理</a>
        </div>  
</div>
</sec:authorize>
</c:if>

<c:if test="${not empty  sessionScope.turnType && sessionScope.turnType eq 'shareData'}">
<sec:authorize  ifAnyGranted="ROLE_LABMANAGER,ROLE_SUPERADMIN,ROLE_EQUIPMENTADMIN, ROLE_LABCENTERMANAGER">
<div class="iStyle_Feelings_Tree_Trunk">
        <a class="iStyle_Feelings_Tree_TrunkTitle iStyle_Feelings_Tree_Opened"><div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>共享数据</a>
        <div class="iStyle_Feelings_Tree_Leaves">
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/findAllSchoolAcademy?page=1">学院管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/findAllSystemCampus">校区管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/findAllSchoolBuild?page=1">建筑管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/findAllSchoolDevice?page=1">学院设备</a>
        </div>  
</div>
</sec:authorize>
</c:if>

<c:if test="${empty  sessionScope.turnType}">
<sec:authorize  ifAnyGranted="ROLE_LABMANAGER,ROLE_SUPERADMIN,ROLE_EQUIPMENTADMIN, ROLE_LABCENTERMANAGER">
<div class="iStyle_Feelings_Tree_Trunk">
        <a class="iStyle_Feelings_Tree_TrunkTitle"><div class="iStyle_Feelings_Tree_Icon icon-envelope-alt"></div>共享数据</a>
        <div class="iStyle_Feelings_Tree_Leaves">
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/findAllSchoolAcademy?page=1">学院管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/findAllSystemCampus">校区管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/findAllSchoolBuild?page=1">建筑管理</a>
            <a class="iStyle_Feelings_Tree_Leaf" href="${pageContext.request.contextPath}/findAllSchoolDevice?page=1">学院设备</a>
        </div>  
</div>
</sec:authorize>
</c:if>
<!--系统报表  -->
<c:if test="${not empty  sessionScope.turnType && sessionScope.turnType eq 'report'}">
<sec:authorize  ifNotGranted="ROLE_STUDENT">
<div class="iStyle_Feelings_Tree_Trunk">
    <a class="iStyle_Feelings_Tree_TrunkTitle iStyle_Feelings_Tree_Opened">
    <div class="iStyle_Feelings_Tree_Icon icon-building"></div>系统报表</a>
    <div class="iStyle_Feelings_Tree_Leaves">
       
            <sec:authorize  ifAnyGranted="ROLE_SUPERADMIN, ROLE_LABCENTERMANAGER">
            <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/report/comprehensiveReport?termBack=-1">综合报表展示</a>
            <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/report/sciPerstatic">实验室人员现状</a>
            <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/report/listLabRoomUsage?page=1">实验室日志</a>
            <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/dataReport/reportLabRate?termBack=-1">实验室利用率</a>
            </sec:authorize>
            <sec:authorize  ifNotGranted="ROLE_SUPERADMIN, ROLE_STUDENT, ROLE_LABCENTERMANAGER">
            <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/report/listLabRoomUsage?page=1">我的实验室日志</a>
            </sec:authorize>
            <sec:authorize  ifAnyGranted="ROLE_SUPERADMIN, ROLE_LABCENTERMANAGER">
            <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/report/teachingReport/termRegister">学期登记</a>
            <%-- <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/report/teachingReport/weekRegister">周次登记</a> --%>
        	<a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/report/listLabWorker?currpage=1">专任实验室人员表</a>
        	<a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/report/listLabRoomBasic?currpage=1">实验室基本情况表</a>
        	<a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/report/listLabItemHourNumber?page=1">实验学时统计表</a>
        	
        	</sec:authorize>
	</div>
</div>
</sec:authorize>
</c:if>


<c:if test="${empty  sessionScope.turnType}">
<sec:authorize  ifAnyGranted="ROLE_EXCENTERDIRECTOR,ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING">
<div class="iStyle_Feelings_Tree_Trunk">
    <a class="iStyle_Feelings_Tree_TrunkTitle">
    <div class="iStyle_Feelings_Tree_Icon icon-building"></div>系统报表</a>
    <div class="iStyle_Feelings_Tree_Leaves">
       
            <sec:authorize  ifAnyGranted="ROLE_LABMANAGER,ROLE_EQUIPMENTADMIN,ROLE_SUPERADMIN,ROLE_EXPERIMENTALTEACHING">
            <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/report/reportMain">综合报表</a>
            </sec:authorize>
            <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/report/teachingReport/termRegister">学期登记</a>
            <a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/report/teachingReport/weekRegister">周次登记</a>
        	<a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/report/listLabWorker?currpage=1">专任实验室人员表</a>
        	<a class="iStyle_Feelings_Tree_Leaf " href="${pageContext.request.contextPath}/report/listLabRoomBasic?currpage=1">实验室基本情况表</a>
	</div>
</div>
</sec:authorize>
</c:if>
<div class="is_Shadow"></div>
<div class="is_SlideButton"><i class="icon-pause-2"></i></div>
</div>
</div> 