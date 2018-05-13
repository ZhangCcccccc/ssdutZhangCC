<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<meta name="decorator" content="iframe"/>
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	

<style type="text/css">
 .titt {
  border-bottom: 1px solid #a7a7a7;
  font-family: microsoft yahei;
  font-size: 0.875em;
  height: 30px;
  line-height: 31px;
  position: relative;
}
.titlet{
border-bottom: 1px solid #02355d;
    display: block;
    height: 30px;
    text-indent: 5px;
    width: 130px;
    
    font-size: 14px;
   /*  color:#043962 */
}
.content-box{width:99%;
	margin:auto;
	margin-top:10px;
	min-height:700px;}
table th,table td{word-break:break-all;}
table{table-layout:fixed;}
.panel {top:50px !important;}
</style>

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
	
	
//打开视频
function openVideo(roomId,id) {
	$.ajax({
	           url:"${pageContext.request.contextPath}/appointment/openVideo?roomId="+roomId+"&id="+id,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	           		var con ="<iframe target='_parent' scrolling='yes' id='message' frameborder='0' src='"+data+"' style='width:100%;height:100%;''></iframe>";
	           		//window.open(data);
	           		$("#openVIdeo").html(con);
	           		 $("#openVIdeo").window('open');  
	           }
	});
}	

function cancelQuery(){
	window.location.href="${pageContext.request.contextPath}/timetable/labRoomResource?page=1";
}
</script>
<script>
		function change(){
			var s=document.getElementById("academyNumber").value;
			//alert("s的值为："+s);
			//Ajax方法 根据网站id查询出所有的栏目
			$.post('${pageContext.request.contextPath}/timetable/finSystemBuildByAcademy?academyNumber='+s+'',function(data){  //serialize()序列化
				alert(data);
				$(buildNumber).html(data);
			 });
		}
		</script>	
</head>

<body>
<div class="iStyle_RightInner">
<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">考勤管理</a></li>
	<li class="end"><a href="javascript:void(0)">实验室考勤与成绩管理</a></li>
	
</ul>
</div>
</div>


<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">

<ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="${pageContext.request.contextPath}/timetable/attendanceManageIframe?currpage=1&id=-1&status=-1">课程考勤与成绩管理</a>
		</li>
		<li class="TabbedPanelsTab selected" tabindex="0">
		<a href="${pageContext.request.contextPath}/timetable/labRoomResource?page=1">实验室考勤</a>
		</li>
</ul>


<div class="TabbedPanelsContent">
<div class="tool-box">

<form:form name="queryForm" action="${pageContext.request.contextPath}/timetable/labRoomResource?page=1" method="post" modelAttribute="labRoom">
	<table style="position:absolute;left:5px;">
	<tr><%--
	<td>学院：
	<form:select id="academyNumber" path="labAnnex.labCenter.schoolAcademy.academyNumber" >
	<form:option value="${user.schoolAcademy.academyNumber }">${user.schoolAcademy.academyName }</form:option>
	<c:forEach items="${academys}" var="a">
	<form:option value="${a.academyNumber}">${a.academyName}</form:option>
	</c:forEach>
	</form:select>
	</td>
	--%><td>实验室：
	<form:select id="id" path="id"  class="chzn-select">
	<form:option value="0">请选择实验室</form:option>
	<c:forEach items="${listLabRooms}" var="l">
	<form:option value="${l.id}">${l.labRoomName}</form:option>
	</c:forEach>
	</form:select>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="取消查询" onclick="cancelQuery();">
	<input type="submit" value="查询">
	</td>
	
	</tr>
	</table>
</form:form>
</div>
<div class="content-box">
<div class="title">实验室考勤管理列表</div>
<table> 
<thead>
 <tr>
   <th><p>序号</p></th>
   <th><p>学院</p></th>
   <th><p>实验室名称</p></th>
   <th><p>所在位置</p></th>
   <th><p>面积</p></th>
   <th><p>容量</p></th>
   <th><p>是否开放</p></th>
   <%--<th><p>门禁</p></th>
   --%><th><p>考勤</p></th><%--
   <th><p>视频</p></th>
   <th><p>预约记录</p></th>
 
   
--%></tr>
</thead>
<tbody >
	<c:forEach items="${labRoomList}" var="room" varStatus="i">
	<tr align="left">
	
	<td><p>${i.count}</p></td>
	<td><p>${room.labAnnex.labCenter.schoolAcademy.academyName}</p></td>
	<td><p> <a class="classroom-name" href="${pageContext.request.contextPath}/appointment/showLabRoom?id=${room.id}">${room.labRoomName}</a></p></td>
	<td><p>${room.systemRoom.systemBuild.systemCampus.campusName}<!-- 校区 -->
                            ${room.systemRoom.systemBuild.buildName} <!-- 楼栋 -->
                            ${room.systemRoom.roomName} <!-- 房间 --></p></td>
	<td><p>${room.labRoomArea}</p></td>
	<td><p>${room.labRoomCapacity}</p></td>
	<td><p>${room.CActiveByLabRoomActive.name}</p></td><%--
	<td><p>
	<c:forEach items="${room.labRoomAgents}" var="agent">
		<c:if test="${agent.CAgentType.id==2}"><!-- 门禁 -->
		<a href="${pageContext.request.contextPath}/timetable/Access?id=${agent.id}&page=1">${agent.CAgentType.name}</a>
		</c:if>
	</c:forEach> 
	</p></td>
	--%><td><p>
	<c:forEach items="${room.labRoomAgents}" var="agent">
		<c:if test="${agent.CAgentType.id==1}"><!-- 考勤机 -->
		<a href="${pageContext.request.contextPath}/timetable/Attendance?id=${agent.id}&page=1">${agent.CAgentType.name}</a>
		</c:if>
	</c:forEach> 
	</p></td>
	<%--<td><p>
	<c:forEach items="${room.labRoomAgents}" var="agent">
		<c:if test="${agent.CAgentType.id==3}"><!-- 视频 -->
		<a href="javascript:void(0)" onclick="openVideo('${agent.labRoom.id}','${agent.id}');">${agent.CAgentType.name}</a>
		</c:if>
	</c:forEach> 
	</p></td>
	<td><a href="${pageContext.request.contextPath}/timetable/labRoomUseRecord?roomId=${room.id}&page=1">查看</a> </td>
	--%></tr>
	</c:forEach>
	

</tbody>
</table>
</div>
</div>
</div>
</div>
</div>

<!-- 分页导航 -->
<div class="page" >
${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/timetable/labRoomResource?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/timetable/labRoomResource?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/timetable/labRoomResource?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/timetable/labRoomResource?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/timetable/labRoomResource?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/timetable/labRoomResource?page=${pageModel.totalPage}')" target="_self">末页</a>
</div>
<div id="openVIdeo" class="easyui-window " title="查看视频" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 1200px; height: 600px;">
		
</div>

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

</body>
</html>

