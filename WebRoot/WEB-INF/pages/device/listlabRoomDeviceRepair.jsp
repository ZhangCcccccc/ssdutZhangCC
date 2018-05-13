<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<meta name="decorator" content="iframe"/>
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
	
	function cancel(){
		window.location.href="${pageContext.request.contextPath}/appointment/listLabAnnex?page=1";
	}
</script>
<html >  
<head> 
<script type="text/javascript">

function addRecords(id){
         $("#repairRecords").window('open');  
         $("#labRoomDeviceRepairId").val(id);     
    }
 
 function addStatus(id){
         $("#addstatus").window('open');  
         $("#labRoomDeviceRepairId2").val(id);     
    }
 
function detailList() {
    //alert("asd");
    window.location.href =  "${pageContext.request.contextPath}/device/labRoomDeviceRepair/detailList";

}
function newDeviceService() {
    //alert("asd");
    window.location.href =  "${pageContext.request.contextPath}/device/newDeviceService?td=${td}";

}
function serviceRecord() {
    //alert("asd");
    window.location.href =  "${pageContext.request.contextPath}/device/labRoomDeviceRepair/serviceRecord";

} 
</script>
</head>
<body>
<div class="iStyle_RightInner">

<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">实验室设备管理</a></li>
	<li class="end"><a href="${pageContext.request.contextPath}/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=-1">设备报修列表</a></li>
</ul>
</div>
</div>





<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">
<div id="title">设备编号:${labRoomDevice.schoolDevice.deviceNumber}&nbsp;设备名称：${labRoomDevice.schoolDevice.deviceName}&nbsp;所在实验室：${labRoomDevice.labRoom.labRoomName}&nbsp;设备管理员：${labRoomDevice.user.cname}</div>
<input class="btn btn-new" type="button" onclick="newDeviceService();" value="新建">
</div>
<table> 
<thead>
<tr>
   <th>序号</th>
   <th>报修时间</th>
   <th>报修人</th>
   <th>硬件故障</th>
   <th>软件故障</th>
   <th>分区</th>
   <th>报故时间</th>
   <th>修复时间</th>
   <th>修理记录</th>
   <th>状态</th>
 </tr>
</thead>
<tbody>
<c:forEach items="${listLabRoomDeviceRepair}" var="current" varStatus="i" >	
<tr>
<td >
        	${i.index+1 }
        	</td>
        	<td >
 <fmt:formatDate dateStyle="short" type="both" value="${current.repairTime.time}"/>
        	</td>
        	<td >
        	${current.user.cname}
        	</td>
        	<td >
        	${current.hardwareFailure}
        	</td>
        	<td >
        	${current.softwareFailure}
        	</td>
        	<td >
        	<%-- ${current.CLabRoomDevicePartition.name} --%>
        	${current.CDictionaryByPartitionId.CNname}
        	</td>
        	<td>
 				<fmt:formatDate dateStyle="short" type="both" value="${current.createTime.time}"/>
        	</td>
        	<td>
 				<fmt:formatDate dateStyle="short" type="both" value="${current.restoreTime.time}"/>
        	</td>
        	<td>
        	<c:if test="${current.repairRecords!=''}">
        	${current.repairRecords}
        	</c:if>
        	<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_EQUIPMENTADMIN">
        	<c:if test="${current.repairRecords==null}">
        	<button onclick="addRecords(${current.id});">操作</button>
        	</c:if>
        	</sec:authorize>
        	</td>
        	<td>
        	<%-- <c:if test="${current.CLabRoomDeviceRepairStatus!=''}">
        	${current.CLabRoomDeviceRepairStatus.name}
        	</c:if> --%>
        	<c:if test="${current.CDictionaryByStatusId!=''}">
        	${current.CDictionaryByStatusId.CNname}
        	</c:if>
        	<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_EQUIPMENTADMIN">
        	<%-- <c:if test="${current.CLabRoomDeviceRepairStatus==null}"> --%>
        	<c:if test="${current.CDictionaryByStatusId==null}">
        	<button onclick="addStatus(${current.id});">操作</button>
        	</c:if>  
        	</sec:authorize>     	
        	</td>

</tr>
</c:forEach> 
</tbody>
</table>
</div>
</div>
</div>
</div>
</div>
</div>
</div>


<div id="repairRecords" class="easyui-window " title="添加修理记录" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 300px; height: 150px;">
<form:form   action="updateLabRoomDeviceRepairRepairRecords"   modelAttribute="labRoomDeviceRepair" >
<form:input path="repairRecords"/>
<form:hidden path="id" id="labRoomDeviceRepairId"/>
<input type="submit">
</form:form>
</div>

<div id="addstatus" class="easyui-window " title="添加修理记录" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 300px; height: 150px;">
<form:form action="statusLabRoomDeviceRepairRepairstatus" modelAttribute="labRoomDeviceRepair">
<td>软件故障选择</td>
<td>
<%-- <form:select path="CLabRoomDeviceRepairStatus.id"  items="${map2}">
</form:select> --%>
<form:select path="CDictionaryByStatusId.id" >
	<c:forEach items="${cDictionaries}" var="cDictionary">
		<form:option value="${cDictionary.id }">${cDictionary.CName }</form:option>
	</c:forEach>
</form:select>
</td>
<form:hidden path="id" id="labRoomDeviceRepairId2"/>
<input type="submit">
</form:form>
</div>
       <div class="pagination" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=${pageModel.totalPage}')" target="_self">末页</a>
    </div>



</body></html>