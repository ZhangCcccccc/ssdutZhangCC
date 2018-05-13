<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<head>
<!-- 下拉框的样式 -->
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/style.css" /> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<script type="text/javascript">
  //打印
   $(function(){
      $("#print").click(function(){
	$("#my_show").jqprint();
	});
   });
  //导出查询之后的数据
  function exportSelect(){
	 document.form.action="${pageContext.request.contextPath}/exportSelectCenterNowWeekCourse";
	 document.form.submit();
}
//查询
function submitSelect(){
 document.form.action="${pageContext.request.contextPath}/selectCenterNowWeekCourseList";
	 document.form.submit();
}
 </script>
</head>
<!-- 查询框 -->
     <form:form name="form"  method="Post" modelAttribute="course">
 <table class="list_form">
    <tr>
        <td>
              <form:select id="term" path="schoolTerm.id" items="${terms}"/> 
             <form:select class="chzn-select" id="week" path="userByTeacher.id" style="width:100px">
                    <form:option value="0" label="全部周次"/>
                    <form:options items="${weeks}"/>
                </form:select>
               <form:select class="chzn-select" id="lab" path="CCourseStatus.id">
                 <form:option value="0" label="实验室可选"/>
                 <form:options items="${labs}"/>
               </form:select>
               <form:select class="chzn-select" id="username" path="memo">
                 <form:option value="" label="教师可选"/>
                 <form:options items="${users}"/>
               </form:select>
                <input type="button" value="查询" onclick="submitSelect();">&nbsp;&nbsp;&nbsp;&nbsp;
                <a onclick="window.history.go(-1)">返回</a>
                <ul class="new_bulid"><li class="new_bulid_1"><a href="javascript:void(0)" id="print"><fmt:message key="navigation.print"/></a></li>
            </ul>   
              <ul  class="new_bulid"> <li class="new_bulid_1"><c:choose><c:when test="${newFlag}"><a href="${pageContext.request.contextPath}/exportCenterNowWeekCourse"><fmt:message key="navigation.export"/></a></c:when><c:otherwise><a href="javascript:void(0)" onclick="exportSelect();" ><fmt:message key="navigation.export"/></a></c:otherwise></c:choose></li></ul> 
        </td> 
    </tr>
</table>
 <!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chzn-select'           : {search_contains:true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  </script>
  <!-- 下拉框的js -->
</form:form>
<!-- 查询框 -->
 <table id="my_show"  class="tb"  align="center" valign="center">
        <tr>
            <th><fmt:message key="week.class.title"/></th>
            <th><fmt:message key="monday.title"/></th>
            <th><fmt:message key="tuesday.title"/></th>
            <th><fmt:message key="wednesday.title"/></th>
            <th><fmt:message key="thursday.title"/></th>
            <th><fmt:message key="friday.title"/></th>
            <th><fmt:message key="saturday.title"/></th>
            <th><fmt:message key="sunday.title"/></th>
        </tr>
        <c:forEach items="${schoolClasses}" var="current"  varStatus="i">
            <tr>
            <td>${current.className}</td>
                    <c:forEach items="${weekdays}" var="cur"  varStatus="k">
                    <td id="weekday_${current.id}_${cur.termCode}">
                       <c:forEach items="${courseArranges}" var="arrange" varStatus="j">
                       <c:if test="${arrange.weekday==cur.termCode&&arrange.arrangeClass==current.id}">
                       ${arrange.course.courseName}${arrange.course.userByTeacher.cname}第${arrange.memo}周
                      (<c:forEach items="${arrange.courseAppointments}" var="appoint" varStatus="k">${appoint.labName}</c:forEach>)
                      </c:if>
                       </c:forEach>
                       </td>
                       </c:forEach>
               
            </tr>
            </c:forEach>
    </table>