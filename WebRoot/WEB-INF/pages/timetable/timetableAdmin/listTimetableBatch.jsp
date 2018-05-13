<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<head>
<meta name="decorator" content="iframe"/>
<link type="text/css" rel="stylesheet"	href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet"	href="${pageContext.request.contextPath}/css/iconFont.css">
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<!--直接排课  -->

</head>

<div class="iStyle_RightInner">

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="tool-box">
<form:form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/timetableAdminIframe?currpage=1&id=-1&status=${status }" modelAttribute="timetableBatch">
<ul>
   <li>
   <select class="chzn-select" id="term" name="term" style="width:180px">
       <c:forEach items="${schoolTerms}" var="current">
    	    <c:if test="${current.id == term}">
    	       <option value ="${current.id}" selected>${current.termName} </option>
    	    </c:if>
    	    <c:if test="${current.id != term}">
    	       <option value ="${current.id}" >${current.termName} </option>
    	    </c:if>		
        </c:forEach>
    </select>
   </li>
   <li>
  
   </li> 
   <li><input type="submit" value="查询" id="search" /> </li> 
   <li> <a href="${pageContext.request.contextPath}/timetable/timetableAdminIframe?currpage=1&status=${status }&id=-1"><input type="button" value="取消查询"></a></li>  
</ul>

</form:form>
</div>
<div class="content-box">
<div class="title">教务排课管理列表</div>
<table> 
<thead>
<tr>
   <th width="30px;">序号</th>
   <th width="70px;">批次名称</th>
   <th width="50px;">选课组编号</th> 
   <th width="120px;">所属学院</th> 
   <th width="130px;">排课情况</th>
   <th width="130px;">分组情况</th>
   <th width="160px;">选课开始时间</th>
   <th width="160px;">选课结束时间</th>
   
   <th width="10%;">操作</th>
   <th></th>
   
</tr>
</thead>
<tbody>
<c:forEach items="${timetableBatchs}" var="current"  varStatus="i">

<tr>
  <td>${i.count } </td>
  <td>${current.batchName } </td>
  <td>${current.courseCode } </td>
  <td>
   <c:set var="timetableGroup" value=""></c:set>
   <c:set var="timetableAppointment" value=""></c:set>
   <c:set var="termId" value="-1"></c:set>
   <c:if test="${current.timetableGroups.size()>0 }">
            <c:set var="timetableGroup" value="${current.timetableGroups.iterator().next()}"></c:set>
            <c:if test="${timetableGroup.timetableAppointments.size()>0 }">
                 <c:set var="timetableAppointment" value="${timetableGroup.timetableAppointments.iterator().next()}"></c:set>
                 <c:if test="${timetableAppointment.timetableStyle==1||timetableAppointment.timetableStyle==2||timetableAppointment.timetableStyle==3||timetableAppointment.timetableStyle==4 }">
                     ${timetableAppointment.schoolCourse.schoolAcademy.academyName }
                     <c:set var="termId" value="${timetableAppointment.schoolCourse.schoolTerm.id}"></c:set>
                 </c:if>
                 <c:if test="${timetableAppointment.timetableStyle==5||timetableAppointment.timetableStyle==6}">
                     ${timetableAppointment.timetableSelfCourse.schoolAcademy.academyName }
                     <c:set var="termId" value="${timetableAppointment.timetableSelfCourse.schoolTerm.id}"></c:set>
                 </c:if>
            </c:if>
   </c:if>
  </td>
  <td>${current.flag } </td>
  <td>
   <c:if test="${current.timetableGroups.size()==0 }">
        <b>尚未开始分组</b>               
   </c:if>
   <c:if test="${current.timetableGroups.size()>0 }">
            已有${current.timetableGroups.size()}个分组             
   </c:if>
  </td>
  <td><fmt:formatDate value="${current.startDate.time }" pattern="yyyy-MM-dd"/>  </td>
  <td><fmt:formatDate value="${current.endDate.time }" pattern="yyyy-MM-dd"/> </td>
  <td><a class="btn btn-common" href="${pageContext.request.contextPath}/timetable/deleteBatchAdmin?id=${current.id }&term=${termId }&courseCode=${current.courseCode }">删除批次</a></td>
</tr>
</c:forEach> 
</tbody>
</table>
<a href="${pageContext.request.contextPath}/timetable/timetableAdminIframe?currpage=${pageModel.firstPage}&term=${term }&id=<%=request.getParameter("id") %>&status=<%=request.getParameter("status") %>" target="_self"><fmt:message key="firstpage.title"/></a>				    
<a href="${pageContext.request.contextPath}/timetable/timetableAdminIframe?currpage=${pageModel.previousPage}&term=${term }&id=<%=request.getParameter("id") %>&status=<%=request.getParameter("status") %>" target="_self"><fmt:message key="previouspage.title"/></a>
 第
<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
    <option value="${pageContext.request.contextPath}/timetable/timetableAdminIframe?currpage=${page}&term=${term }&id=<%=request.getParameter("id") %>&status=<%=request.getParameter("status") %>">${page}</option>
    <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
        <c:if test="${j.index!=page}">
        <option value="${pageContext.request.contextPath}/timetable/timetableAdminIframe?currpage=${j.index}&term=${term }&id=<%=request.getParameter("id") %>&status=<%=request.getParameter("status") %>">${j.index}</option>
        </c:if>
    </c:forEach>
</select>页
<a href="${pageContext.request.contextPath}/timetable/timetableAdminIframe?currpage=${pageModel.nextPage}&term=${term }&id=<%=request.getParameter("id") %>&status=<%=request.getParameter("status") %>" target="_self"><fmt:message key="nextpage.title"/></a>
<a href="${pageContext.request.contextPath}/timetable/timetableAdminIframe?currpage=${pageModel.lastPage}&term=${term }&id=<%=request.getParameter("id") %>&status=<%=request.getParameter("status") %>" target="_self"><fmt:message key="lastpage.title"/></a>
</div>
<div class="pagination_desc" style="float: left">
   <fmt:message key="total"/><strong>${totalRecords}</strong> 
   <fmt:message key="record"/><strong>
   <fmt:message key="totalpage.title"/>:${pageModel.totalPage}&nbsp;</strong>

</div>
</div>
</div>
</div>
</div>
</div>

<div id="searchFile" class="easyui-window" title="直接排课" closed="true" iconCls="icon-add" style="width:850px;height:450px">
<br>
<form:form id="form_lab" action="" method="post"  modelAttribute="timetableBatch">
当前学期：${schoolTerm.termName} 当前周次：${week }
<!-- schoolCourseDetail的no -->
<table>
<tr>
   <td></td>
   <td></td>
</tr>

</table>
<br>
<br>
<br>
<hr>
<input type="submit" value="确定">
</form:form>
</div>

<!-- 调整排课 -->
<div id="doAdmin" class="easyui-window" title="调整排课" closed="true" iconCls="icon-add" style="width:1050px;height:500px">
</div>

<!-- 查看学生名单 -->
<div id="doSearchStudents" class="easyui-window" title="查看学生名单" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>

<!-- 查看学生名单 -->
<div id="doSearchGroup" class="easyui-window" title="查看学生分组" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>

<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var config = {
	'.chzn-select' : {
		search_contains : true
	},
	'.chzn-select-deselect' : {
		allow_single_deselect : true
	},
	'.chzn-select-no-single' : {
		disable_search_threshold : 10
	},
	'.chzn-select-no-results' : {
		no_results_text : 'Oops, nothing found!'
	},
	'.chzn-select-width' : {
		width : "95%"
	}
}
for ( var selector in config) {
	$(selector).chosen(config[selector]);
}</script>
<!-- 下拉框的js -->
