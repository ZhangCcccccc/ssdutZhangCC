<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/iconFont.css"	rel="stylesheet">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
$(document).ready(function(){
$("#print").click(function(){
$("#my_show").jqprint();
});
 $("#turn").click(function(){
 	var page=${pageModel.totalPage};
    var id=$("#page").val();
     if(id.length==0){
      alert("请输入数字！");
      }else{
      reg=/^[-+]?\d*$/;
       if(!reg.test(id)){    
          alert("对不起，您输入的整数类型格式不正确!");//请将“整数类型”要换成你要验证的那个属性名称！    
        }else{
            if(id<=page){
          	  window.location.href="${pageContext.request.contextPath}/userList?currpage="+id;
                }else{
              	  alert("对不起，您输入的整数不正确!");
                    }
        }    
      }
 	});
});
</script> 
<script type="text/javascript">
function exportSelect(){
	 document.form.action="exportExcalSelectUser";
	 document.form.submit();
	}
function check(){
	if($("input[name='VoteOption1']:checked").val()==null){
		alert("请选择需要删除的课程！");
		return false;
	}
	return confirm('确定删除选中的课程？');
}
</script>
</head>
<body>

<div class="iStyle_RightInner">
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="tool-box">
<form:form name="form" action="${pageContext.request.contextPath}/timetable/selfTimetable/listSchoolCourseInfo?currpage=1" method="post" modelAttribute="schoolCourseInfo">
<table class="list_form">
<tr>
   <td>课程编号:
   	<form:input id="courseNumber" path="courseNumber" />
   	</td>
   	<td>
   	课程名称:
   	<form:input id="courseName" path="courseName" />
   	</td>
   	<td>
    <a  class="btn btn-common" style="float:right;margin-left:15px;" href="${pageContext.request.contextPath}/timetable/selfTimetable/listSchoolCourseInfo?currpage=1" >取消查询</a>
    <input style="margin-top:5px;" type="submit" value="查询">
    </td>
    <td>
    <a  class="btn btn-common"  href="${pageContext.request.contextPath}/timetable/selfTimetable/newSelfSchoolCourseInfo?courseNumber=-1" >新建课程</a>
   </td>    
</tr>
</table>
</form:form>
</div>
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/selfTimetable/batchDeleteSelfSchoolCourseInfo">
<div class="content-box">
<div class="title">教务排课管理列表 <a onclick="return check()" href='javascript:void(0)'><input type="submit" value="批量删除"></a></div>
<table class="tb" cellspacing="0" id="my_show"> 
<thead>
<tr>
    <th>选择</th>
    <th>序号</th>
    <th>课程编号</th>
    <th>课程名称</th>
    <th>所属学院</th>
    <th>创建日期</th>
    <th>操作</th>
</tr>
</thead>
<tbody>
<c:forEach items="${schoolCourseInfoList}" var="current"  varStatus="i">	
<tr>
   <td><input type='checkbox' name='VoteOption1' value="${current.courseNumber}"></td>
   <td>${i.count}</td>
   <td>${current.courseNumber}</td>
   <td>${current.courseName}</td>
   <td>${current.academyNumber}</td>
   <td><fmt:formatDate value="${current.createdAt.time}" type="both" dateStyle="long" pattern="yyyy-MM-dd" /></td>
   <td>
<%--    <a href='${pageContext.request.contextPath}/timetable/selfTimetable/deleteTimetableSelfCourse?id=${current.courseNumber }'>删除</a>&nbsp;&nbsp; --%>
   <a href='${pageContext.request.contextPath}/timetable/selfTimetable/newSelfSchoolCourseInfo?courseNumber=${current.courseNumber }'>编辑</a>
   </td>
</tr>
</c:forEach>
</tbody>
</table>
<c:choose><c:when test='${newFlag}'>

<a href="${pageContext.request.contextPath}/timetable/selfTimetable/listCourseCodes?currpage=${pageModel.firstPage}" target="_self"><fmt:message key="firstpage.title"/></a>				    
<a href="${pageContext.request.contextPath}/timetable/selfTimetable/listCourseCodes?currpage=${pageModel.previousPage}" target="_self"><fmt:message key="previouspage.title"/></a>
 第
<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;"><option value="${pageContext.request.contextPath}/timetable/selfTimetable/listCourseCodes?currpage=${page}">${page}</option>
    <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
        <c:if test="${j.index!=page}">
        <option value="${pageContext.request.contextPath}/timetable/selfTimetable/listCourseCodes?currpage=${j.index}">${j.index}</option>
        </c:if>
    </c:forEach>
</select>页
<a href="${pageContext.request.contextPath}/timetable/selfTimetable/listCourseCodes?currpage=${pageModel.nextPage}" target="_self"><fmt:message key="nextpage.title"/></a>
<a href="${pageContext.request.contextPath}/timetable/selfTimetable/listCourseCodes?currpage=${pageModel.lastPage}" target="_self"><fmt:message key="lastpage.title"/></a>
</div>

<div class="pagination_desc" style="float: left">
   <fmt:message key="total"/><strong>${totalRecords}</strong> 
   <fmt:message key="record"/><strong>
   <fmt:message key="totalpage.title"/>:${pageModel.totalPage}&nbsp;</strong>
</div>
</c:when><c:otherwise>
</c:otherwise></c:choose> 
</form>   
</div>
</div>
</div>
</div>
</div>
</div>
</div>


</body>
</html>

