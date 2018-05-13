 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>
<head>
<meta name="decorator" content="iframe"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.freezeheader.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/span.js" ></script>
<script type="text/javascript">
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
});
 /* var r = confirm('确定要删除吗？');
    if(r==true){
     $.ajax({
      type:"POST",
      url: "${pageContext.request.contextPath}/cancelCourseAppointment",
           data:  {id:idKey},
           success:function(data){
            refresh();
           }
    });
    } */
}
//全选
function test() {  
   if($("#clickAll").prop("checked"))
   {
     $('input[id^="weeksss_"]').each(function() {
         $(this).prop("checked", true);
     });
   }
   else
   {
     $('input[id^="weeksss_"]').each(function() {
         $(this).prop("checked", false);
     });           
   }
   }
//点击确定事件
function sureOnclick(){
  //获取页面所选的学期
  /* var termId2=${termId}; */
  //获取被选中的周次
   var arr = new Array();
  var weekList="";
     var i=0;
  $("input[name='week']:checked").each(function(){ 
    arr[i]=$(this).val();
    weekList+=$(this).val()+"\r\n"; 
    i=i+1;
  });
  if(arr.length==0){
     alert("请选择至少一个周次");
  }else{
   var weekList=arr.join(",");
/*    var queryParams = $('#spdata').datagrid('options').queryParams;  
			   queryParams.termId =termId2;
	           queryParams.week =weekList;
	          /*  queryParams.weekday1=weekday; */
		/*  $('#spdata').datagrid('options').queryParams=queryParams;     
    $("#spdata").datagrid('reload');  */
   document.form.action="${pageContext.request.contextPath}/labCoursesByQuery?week="+weekList;
	 document.form.submit();
 }
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div>
<form:form name="form">
<c:forEach items="${weeks}" var="curr" varStatus="h"><input type="checkbox" name="week" id="weeksss_${curr.id}" value="${curr.week}">${curr.week}周</c:forEach><input type="checkbox" name="clickAll" id="clickAll" onclick="test();"/>全选 <input type="submit" value="确定" onclick="sureOnclick();"/>
</form:form>
</div>
</br>
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
                            <td><div title="${current.memo}" class="wenben">${current.labName}</div></td>
                            <c:forEach items="${classMap}" var="cur"  varStatus="k">
                            <c:set var="labHours" scope="session" value="false"/><c:set var="courses" scope="session" value=""/><c:set var="courseDetails" scope="session" value=""/>
                        <c:forEach items="${labWeekdays}" var="lab" varStatus="j"><c:if test="${lab.weekdayId==current.weekdayId&&lab.labId==current.labId&&lab.classId==cur.key}"><c:set var="labHours" value="true"/><c:set var="courses" value="${lab.appointName}"/><c:set var="courseDetails" value="${lab.memo}"/></c:if></c:forEach>
                            <c:choose><c:when test="${labHours}"><td style="background-color: red"><div title="${courseDetails}" class="wenben">${courses}</div> <c:set var="labHours" value="false"/><c:set var="courses" value=""/><c:set var="courseDetails" scope="session" value=""/></td></c:when><c:otherwise><td> <div style="display:none">appoint_${current.weekdayId}_${current.labId}_${cur.key}</div></td></c:otherwise></c:choose>
                           </c:forEach>
                         
                        </tr>
                        </c:forEach>
                </tbody>
</table>
</div>
	<div id="test" class="easyui-window" closed="true" title="取消排课" style="width:400px;height:400px;">	<div class="toolbar"><a id="oneWeek" href="javascript:void(0)">取消本周的排课</a> |
		<a href="javascript:void(0)" id="allWeek">取消本门课的所有排课</a></div></div>
	</body>