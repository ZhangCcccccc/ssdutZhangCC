<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.ccoursetype-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	$("#print").click(function(){
	$("#my_show").jqprint();
	})
	});
	
	//导出查询之后的数据
	  function exportSelect(){
		 document.form.action="${pageContext.request.contextPath}/exportSelectCourseAttendance";
		 document.form.submit();
	}
	//查询
	function submitSelect(){
	 document.form.action="${pageContext.request.contextPath}/selectCourseAttendance";
		 document.form.submit();
	}
</script>  
</head>

<body>
<div class="list_tittle">
     <form:form name="form" method="post" modelAttribute="course">
 <table class="list_form">
    <tr>
        <td>搜索课程:
               <input name="courseName" value="${name}" onkeyup="value=value.replace(/[^\u4E00-\u9FA5\w]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5\w]/g,''))">
                <input type="button" value="查询" onclick="submitSelect();">&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="window.history.go(-1)">返回</a>
        </td>    
    </tr>
</table>
</form:form>
</div>
<div class="l_right">
    <div class="list_top">
        <ul class="top_tittle">考勤——课程列表</ul> 
      <ul  class="new_bulid"> <li class="new_bulid_1"><c:choose><c:when test="${newFlag}"><a href="${pageContext.request.contextPath}/exportCourseAttendance">导出</a></c:when><c:otherwise><a href="javascript:void(0)" onclick="exportSelect();">导出</a></c:otherwise></c:choose></li></ul>  
      <ul class="new_bulid">
                <li class="new_bulid_1"><a id="print" href="javascript:void(0)">打印</a></li>
            </ul>  <ul  class="new_bulid"><li class="new_bulid_1"><a href="javascript:void(0)" onclick="window.history.go(-1)">返回</a></li></ul>   
          
    </div>
            <table  id="my_show" class="tb"> 
                <thead>
                    <tr>
                    	<%--<th>实验室中心</th>--%>
                    	<th><fmt:message key="course.number"/></th>
                    	<th>课程名称</th>
                        <th><fmt:message key="academy"/></th>
                        <%--<th><fmt:message key="lab.name"/></th>
                        --%><th><fmt:message key="teacher"/></th>
                        <th><fmt:message key="team"/></th>
                        <%--<th>上课地点</th>--%>
                        <sec:authorize ifAnyGranted="ROLE_ADMINISTRATOR,ROLE_ADMIN,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER,ROLE_EXTEACHDIRECTOR,ROLE_EXPERIMENTALTEACHING"><th><fmt:message key="week.attendance"/></th>
                        <th><fmt:message key="team.attendance"/></th></sec:authorize>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${courseDetails}" var="current"  varStatus="i">	
                        <tr>
                        	<%--<td>
                        	<c:forEach items="${current.courseAppointments}" var="curr">
                            	${curr.labAnnex.labCenter.centerName}
                            </c:forEach>
                            </td>
                        	--%><td>${current.courseNumber}</td>
                        	<td>${current.courseName}</td>
                            <td>
                            <%--<c:forEach items="${current.courseAppointments}" var="curr">
                            	${curr.labAnnex.labCenter.schoolAcademy.academyName}
                            </c:forEach>--%>
                            ${current.schoolAcademy.academyName}
                            </td>
                            <%--<td>
                            <c:forEach items="${current.courseAppointments}" var="curr">
                            	${curr.labAnnex.labName}
                            </c:forEach>
                            </td>--%>
                            <td>${current.user.cname}</td>
                            <td>${current.schoolTerm.termName}</td>
                            <%--<td>${current.courseAddressType}</td>
                            --%><sec:authorize ifAnyGranted="ROLE_ADMINISTRATOR,ROLE_ADMIN,ROLE_PREEXTEACHING,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER,ROLE_EXTEACHDIRECTOR,ROLE_EXPERIMENTALTEACHING"><td><a href="${pageContext.request.contextPath}/courseSelect?idkey=${current.id}&num=${current.courseNumber}&currpage=1">考勤</a></td>
                            <td><a href="${pageContext.request.contextPath}/courseTimeAttendanceStu?idkey=${current.id}&">查看</a></td></sec:authorize>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
<c:choose><c:when test='${newFlag}'>
        <div class="pagination">
   <a href="${pageContext.request.contextPath}/attendenceList?currpage=${pageModel.firstPage}" target="_self"><fmt:message key="firstpage.title"/></a>				    
	<a href="${pageContext.request.contextPath}/attendenceList?currpage=${pageModel.previousPage}" target="_self"><fmt:message key="previouspage.title"/></a>
	 <%--<c:forEach var="currentpage" begin="1" end="${pageModel.totalPage}"><a href="${pageContext.request.contextPath}/attendenceList?currpage=<c:out value="${currentpage}"/>" target="_self"><c:out value="${currentpage}"/></a></c:forEach>
	 第--%><select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;"><option value="${pageContext.request.contextPath}/attendenceList?currpage=${page}">${page}</option><c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}"><option value="${pageContext.request.contextPath}/attendenceList?currpage=${j.index}">${j.index}</option></c:if></c:forEach></select>页
	<a href="${pageContext.request.contextPath}/attendenceList?currpage=${pageModel.nextPage}" target="_self"><fmt:message key="nextpage.title"/></a>
	<a href="${pageContext.request.contextPath}/attendenceList?currpage=${pageModel.lastPage}" target="_self"><fmt:message key="lastpage.title"/></a>
    </div>
    <div class="pagination_desc" style="float: left">
       <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
    <strong><fmt:message key="totalpage.title"/>:${pageModel.totalPage}&nbsp;</strong>
</div>
</c:when><c:otherwise>
<div class="pagination_desc" style="float: left">
       <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
</div>
</c:otherwise></c:choose> 
</div>
</body>
</html>

