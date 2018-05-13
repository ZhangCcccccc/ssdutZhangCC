<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.labappointment-resources"/>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<!-- 打印插件的引用 -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
  <script type="text/javascript">
	$(document).ready(function(){
	$("#print").click(function(){
	$("#list_top").jqprint();
	})
	});
	//导出选中excel
function expers(s){

	document.form.action=s;
	document.form.submit();
}
</script>
 <!-- 打印插件的引用 -->
</head>
<body>
<div id="TabbedPanels1" class="TabbedPanels">
	<div class="TabbedPanelsContentGroup">
	<div class="TabbedPanelsContent">

 <div class="tool-box">
               <form:form name="form"   action="${pageContext.request.contextPath}/lab/findstudentforlabreation?idkey=${idkey}&currpage=1" method="post"  modelAttribute="labReservationTimeTableStudent">
				<ul>
					<li>实验室：<form:input path="user.cname" />  </li>
					<li><input type="button" onclick="expers('${pageContext.request.contextPath}/lab/findstudentforlabreation?idkey=${idkey}&currpage=1')"  value="查询"></li>
					<li><input type="button" onclick="expers('${pageContext.request.contextPath}/labreservation/Labreservationmanage?currpage=1&tage=${tage}')"  value="导出"></li>
					<li><input type="button" value="打印" id="print"></li>
					
				</ul>
				</form:form>
			</div>
<div class="content-box">
<table >
<thead>
   <th>序号</th>
   <th >预约类型</th>
   <th >活动名称</th>
    <th >实验室</th>
   <th >学生编号</th>
    <th >学生姓名</th>
  
</tr>
</thead>

<tbody >
<c:forEach items="${TableStudent}" var="s" varStatus="i">
<tr align="center">
   <td >${i.index+1}</td>
     <%-- <td >${s.labReservation.CLabReservationType.name}</td> --%>
     <td >${s.labReservation.CDictionaryByLabReservetYpe.CName}</td>
       <td >${s.labReservation.eventName}</td>
     <td>${s.labReservation.labRoom.labRoomName}</td>
     <td>${s.user.username}</td> 
      <td>${s.user.cname}</td> 
     
</tr>
</c:forEach>
	

</table>
<div class="page">  总页数:${pageModel.totalPage}页 <input type="hidden" value="${pageModel.lastPage}" id="totalpage" />&nbsp;
                  <input type="hidden" value="${currpage}" id="currpage" /><input type="hidden" value="${tage}" id="tage" />
                      <a href="javascript:void(0)" onclick="first();" target="_self">首页</a>				    
                      <a href="javascript:void(0)" onclick="previous();" target="_self">上一页</a>
                      <a href="javascript:void(0)" onclick="next();" target="_self">下一页</a>
                      <a href="javascript:void(0)" onclick="last();" target="_self">末页</a>		</div>
</div></div></div>
</body>
</html 