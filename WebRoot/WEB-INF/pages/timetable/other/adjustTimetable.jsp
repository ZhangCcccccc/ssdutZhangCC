<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<head>
<meta name="decorator" content="iframe"/>
<!-- 下拉框的样式 -->
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/style.css" /> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<!-- 利用ajax保存页面 -->
<!-- <script type="text/javascript">
var arrangeSize=${arrangeSize};
var appointSize=${appointSize};
var courseId=${id};
var weekday=${weekday1};
var page=${page};
$(function(){
 if(arrangeSize==appointSize){
 window.location.href="${pageContext.request.contextPath}/courseList?currpage=${page}";
 }
});
</script> -->
<script  type="text/javascript">
function saveAppointment(){
 var checkboxs=$("input[type='checkbox']:checked");
 var week=$("#appointment_week").val();
 var classStr=$("#appointment_class").val();
 $("#reflect_explain").attr("value","");
 var index=0;
  //如果有选择的则看是否选择冲突的实验室了
  if(checkboxs.length>0){
   var reflectCheckboxs=$("table#reflect_table").find("input[type='checkbox']:checked");
   //如果reflectCheckboxs的大小大于0
   if(reflectCheckboxs.length>0){
    alert("实验室有冲突");
    $("#reflect_explain").val("实验室有冲突");
   }
  }else{
   alert("请选择实验室！");
   index=1;
  }
  //如果week或classStr为空时index等于1
  if(week==null||classStr==null){
  index=1;
  alert("请选择周次和节次！");
  }
  if(index==0){
   $.ajax({
           type: "POST",    
           url: "${pageContext.request.contextPath}/saveAdjustTimetable",
           data:  $("#form_appointment").serialize(),
           success:function(data){
             if(data=="false"){
               window.location.href="${pageContext.request.contextPath}/adjustTimetable?id=${id}&weekday=${weekday}";
             }
             if(data=="exist")
             {
               alert("该时间段已存在");
             }
             if(data=="true"){
             alert("该课程已完成排课");
          window.location.href="${pageContext.request.contextPath}/timetable/listTimetableTerm";
 }
           }
        });
  }
}
</script>
<!-- 利用ajax保存页面完结 -->
</head>
  <div>
   <!--教师需求开始-->
<div style="float:left;width: 95%">
    <div class="sit_module width_3_quarter"style="float:left;width: 100%">
        <div class="sit_title">
            <h3 style="width: 40%"><fmt:message key="arranged.course.title"/></h3>
            <ul class="tabs1">
                <li><a onclick="window.history.go(-1)"><fmt:message key="navigation.back"/></a></li>
            </ul>
        </div>    
    <!-- 课程安排 -->   
   <table border="1" style="width:100%;"> 
               <thead>
                    <tr>
                        <th><fmt:message key="course.coursename.title"/></th>
                        <th><fmt:message key="course.arrangeweek.title"/></th>
                        <th><fmt:message key="course.weekday.title"/></th>
                        <th><fmt:message key="course.allclass.title"/></th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${courseArranges}" var="current"  varStatus="i">	
                        <tr>
                            <td>${teacherNeed.course.courseName}</td>
                            <td>${current.arrangedWeek}</td>
                            <td><c:if test="${current.weekday==1}">星期一</c:if>
                           <c:if test="${current.weekday==2}">星期二</c:if>
                           <c:if test="${current.weekday==3}">星期三</c:if>
                           <c:if test="${current.weekday==4}">星期四</c:if>
                           <c:if test="${current.weekday==5}">星期五</c:if>
                           <c:if test="${current.weekday==6}">星期六</c:if>
                           <c:if test="${current.weekday==7}">星期天</c:if></td>
                            <td>${current.arrangeClass}</td>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>    
 <!--课程安排-->
    </div>
</div>
 <!--教师需求结束-->
 <form:form id="form_appointment" modelAttribute="courseAppointment">   
 <!-- 添加课程的实验室 -->
 <div style="float:left;width:95%">
    <div class="sit_module width_3_quarter"style="float:left;width: 100%">
        <div class="sit_title">
            <h3 style="width: 40%"><fmt:message key="course.arrangelab.title"/></h3>
        </div>    
   
 <div>
 <!-- 课程安排 -->   
   <table border="1" style="width:100%;"> 
               <thead>
                    <tr>
                        <th><fmt:message key="course.coursename.title"/></th>
                        <th><fmt:message key="course.arrangeweek.title"/></th>
                        <th><fmt:message key="course.weekday.title"/></th>
                        <th><fmt:message key="course.allclass.title"/></th>
                    </tr>
                </thead>
                <tbody>
                        <tr>
                            <td>${teacherNeed.course.courseName}<form:hidden path="courseArrange.courseDetail.courseNumber" value="${teacherNeed.course.courseNumber}"/></td>
                            <td><form:select class="chzn-select" id="appointment_week" path="memo" items="${weekMap}" multiple="true"  style="width:350px;" required="true"/></td>
                            <td><c:if test="${weekday==1}">星期一</c:if>
                           <c:if test="${weekday==2}">星期二</c:if>
                           <c:if test="${v==3}">星期三</c:if>
                           <c:if test="${weekday==4}">星期四</c:if>
                           <c:if test="${weekday==5}">星期五</c:if>
                           <c:if test="${weekday==6}">星期六</c:if>
                           <c:if test="${weekday==7}">星期天</c:if><form:hidden path="courseArrange.weekday" value="${weekday}"/></td>
                            <td><form:select class="chzn-select" id="appointment_class" path="appointmentNo" items="${classMap}" multiple="true"  style="width:350px;" required="true"/></td>
                        </tr>
                </tbody>
            </table>    
 <!--课程安排-->
 <form:hidden path="courseArrange.memo" value="${arrangeNumber}"/>
 <form:hidden path="courseDetail.id" value="${detail.id}"/>
 <form:hidden path="courseArrange.courseType" id="reflect_explain"/>
 </div>
</div> 

<div class="sit_module width_3_quarter"style="float:left;width: 100%">
        <div class="sit_title">
            <h3 style="width: 40%">请选择实验室</h3>
             <ul class="tabs1">
                <li><input type="submit" value="保存" class="alt_btn" onclick="saveAppointment()"> </li>
        </ul>
        </div> 
 <!-- 课程安排 -->   
 <table border="1" style="width:100%;"> 
 <thead>
 <tr>
 </tr>
 </thead>
 <tbody>
 <tr>
<c:forEach items="${noLabs}" var="current"  varStatus="i">	
                        <c:if test="${(i.count) % 5 == 0}">
                        <td><form:checkbox path="arrangeDetail" value="${current.id}"/></td>
                            <td>${current.labName}</td>
                            <td></td>
                        </td>
                        </tr>
                        <tr>
                        </c:if>
                        <c:if test="${(i.count) % 5 == 1}">
                        <td><form:checkbox path="arrangeDetail" value="${current.id}"/></td>
                            <td>${current.labName}</td>
                            <td>${current.labCapacity}</td>
                        </td>
                        </c:if>
                        <c:if test="${(i.count) % 5 == 2}">
                        <td><form:checkbox path="arrangeDetail" value="${current.id}"/></td>
                            <td>${current.labName}</td>
                            <td>${current.labCapacity}</td>
                        </td>
                        </c:if>
                        <c:if test="${(i.count) % 5 == 3}">
                        <td><form:checkbox path="arrangeDetail" value="${current.id}"/></td>
                            <td>${current.labName}</td>
                            <td>${current.labCapacity}</td>
                        </td>
                        </c:if>
                        <c:if test="${(i.count) % 5 == 4}">
                        <td><form:checkbox path="arrangeDetail" value="${current.id}"/></td>
                            <td>${current.labName}</td>
                            <td>${current.labCapacity}</td>
                        </td>
                        </c:if>
</c:forEach>
</tr>
 </tbody>
</table>    
 <!--课程安排-->

 <form:hidden path="courseDetail.id" value="${detail.id}"/>
 <form:hidden path="memo" id="reflect_explain"/>
 </div>
 </div>
 </div>

<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    var config = {
      '.chzn-select'           : {},
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
 <!-- 添加课程的实验室 --> 
 </div>