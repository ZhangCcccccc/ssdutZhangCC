 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>
<head>
<meta name="decorator" content="iframe"/>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/flexigrid.js" ></script> --%>
 <script type="text/javascript">
 jQuery.fn.rowspan = function(colIdx) {
 return this.each(function(){
  var that;
  $('tr', this).each(function(row) {
  $('td:eq('+colIdx+')', this).each(function(col) {
      if ($(this).html() == $(that).html()) {
        rowspan = $(that).attr("rowSpan");
        if (rowspan == undefined) {
  
          $(that).attr("rowSpan",1);
          rowspan = $(that).attr("rowSpan");
        }
        rowspan = Number(rowspan)+1;
        $(that).attr("rowSpan",rowspan); // do your action for the colspan cell here
        $(this).hide(); // .remove(); // do your action for the old cell here
      } else {
        that = this;
      }
      that = (that == null) ? this : that; // set the that if not already set
  });
 });

 });
}

jQuery.fn.colspan = function(rowIdx) {
 return this.each(function(){

  var that;
  $('tr', this).filter(":eq("+rowIdx+")").each(function(row) {
  $(this).find('td').each(function(col) {
 /*  alert($(this).text()!=""); */
      if ($(this).html() == $(that).html()) {
        colspan = $(that).attr("colSpan");
        if (colspan == undefined) {
          $(that).attr("colSpan",1);
          colspan = $(that).attr("colSpan");
        }
        colspan = Number(colspan)+1;
        $(that).attr("colSpan",colspan);
        $(this).hide(); // .remove();
      } else {
        that = this;
      }
      that = (that == null) ? this : that; // set the that if not already set
  });
 });

 });
 }
 $(function(){
$('.tb tbody tr').each(function(row) {
   $('.tb').colspan(row);
 });
 $('.tb tbody tr').each(function(col) {
   $('.tb').rowspan(col);
 });
});
//--------------------------取消课程的------------------------
function cancelCourseAppointment(idKey,weekId,weekday){
//获取当前屏幕的绝对位置
var topPos = window.pageYOffset;
//使得弹出框在屏幕顶端可见
$('#test').window({left:"100px", top:topPos+"px"});
$('#test').window('open');
$("#oneWeek").click(function(){
 var r = confirm('确定要删除吗？');
    if(r==true){
     $.ajax({
      type:"POST",
      url: "${pageContext.request.contextPath}/cancelCourseAppointment",
           data:  {id:idKey,weekId:weekId,weekday:weekday},
           success:function(data){
            refresh();
           }
    });
    } 
});
$("#allWeek").click(function(){
 var r = confirm('确定要删除吗？');
    if(r==true){
     $.ajax({
      type:"POST",
      url: "${pageContext.request.contextPath}/cancelAllCourseAppointment",
           data:  {id:idKey},
           success:function(data){
            refresh();
           }
    });
    } 
});}
</script>
</head>
<!-- 四维表显示 -->
<div>
<table id="spdata" class="tb">
  <thead>
                    <tr>
                        <th>星期</th>
                        <th>实验室</th>
                        <c:forEach items="${classMap}" var="current"  varStatus="i">
                        <th id="class_${current.key}">${current.value}</th>
                       </c:forEach>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${labClassWeekdays}" var="current"  varStatus="i">	
                        <tr>
                            <td>${current.weekdayName}</td>
                            <td>${current.labName}</td>
                            <c:forEach items="${classMap}" var="cur"  varStatus="k">
                        <td>  <c:forEach items="${labWeekdays}" var="lab" varStatus="j"><c:if test="${lab.weekdayId==current.weekdayId&&lab.labId==current.labId&&lab.classId==cur.key}">${lab.appointName}</c:if></c:forEach></td>
                           </c:forEach>
                        </tr>
                        </c:forEach>
                </tbody>
</table>
<!-- <script type="text/javascript">
$(function(){
 $('.tb tbody tr').each(function(row) {
   $('.tb').colspan(row);
 });
 });
</script> -->
</div>
