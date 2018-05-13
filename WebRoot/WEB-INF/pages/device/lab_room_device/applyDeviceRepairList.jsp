<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipment-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式 -->	
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">

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
	
function cancelQuery(){
	window.location.href="${pageContext.request.contextPath}/device/deviceRepairList?page=1";
}	
</script>

</head>

<body>
<div class="navigation">
				<div id="navigation">
					<ul>
						<li><a href="javascript:void(0)">实验设备管理</a></li>
						<li class="end"><a href="javascript:void(0)">设备维修管理</a></li>
					</ul>
				</div>
			</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	<ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab " tabindex="0">
		<a href="${pageContext.request.contextPath}/device/deviceRepairList?page=1">全部</a>
		</li>
		<li class="TabbedPanelsTab selected" tabindex="0">
		<a href="${pageContext.request.contextPath}/device/applyDeviceRepairList?page=1">报修</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="${pageContext.request.contextPath}/device/passDeviceRepairList?page=1">已修复</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="${pageContext.request.contextPath}/device/rejectedDeviceRepairList?page=1">未修复</a>
		</li>
	</ul>
	<div class="TabbedPanelsTabGroup-box">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		   <%--   <div class="tool-box">
		     <form:form name="queryForm" action="${pageContext.request.contextPath}/device/deviceRepairList?page=1" method="post" modelAttribute="reservation">
				<table class="list_form">
					<tr>
						<td>
							学期：
							<form:select path="">
							<form:options items="${terms}" itemLabel="termName" itemValue="id"/>
							</form:select>
							
						</td>
						<td>
						设备名称：<form:input path=""/>
						</td>
						<td>
							<input type="submit" value="查询">
							<input type="button" value="取消" onclick="cancelQuery();">
						</td>
					</tr >
			</table>
			</form:form>
		       
		    </div> --%>
    	<div class="content-box">
    		<div class="title">
    			<div id="title">报修状态的设备</div>
    			<a class="btn btn-new" href="javascript:void(0)">导出</a>
    		</div>
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                    	<th>序号</th>
                        <th>报修设备</th>
                        <th>报修人</th>
                        <th>硬件故障</th>
                        <th>软件故障</th>
                        <th>分区</th>
                        <th>报修时间</th>
                        <th>修复时间</th>
                        <th>修理记录</th>
                        <th>状态</th>
                    </tr>  
                </thead>
                
                <tbody>
                		<c:forEach items="${deviceRepairList}" var="reservation" varStatus="i">
                		<c:if test="${cid == reservation.labRoomDevice.labRoom.labAnnex.labCenter.id }">
                		<tr>
                        <td>${i.count}</td>
                        <td>${reservation.labRoomDevice.schoolDevice.deviceName}(${reservation.labRoomDevice.schoolDevice.deviceNumber})</td>
                        <td>${reservation.user.cname}</td>
                        <td>${reservation.hardwareFailure}</td>
                        <td>${reservation.softwareFailure}</td>
                        <%-- <td>${reservation.CLabRoomDevicePartition.name}</td> --%>
                        <td>${reservation.CDictionaryByPartitionId.CName}</td>
                        <td><fmt:formatDate value="${reservation.createTime.time}" pattern="yyyy-MM-dd"/> </td>
                        <td><fmt:formatDate value="${reservation.repairTime.time}" pattern="yyyy-MM-dd"/> </td>
                        <td><c:if test="${reservation.repairRecords!=''}">
        	                 ${reservation.repairRecords}
        	            </c:if>
        	            <c:if test="${admin eq true || reservation.labRoomDevice.user.username == user.username  }">
			        	<%-- <c:if test="${reservation.CLabRoomDeviceRepairStatus.id!=3}"> --%>
			        	<c:if test="${reservation.CDictionaryByStatusId.CCategory=='c_lab_room_device_repair_status' && reservation.CDictionaryByStatusId.CNumber!='3'}">
			        	<a href="${pageContext.request.contextPath}/device/repairDevice?idKey=${reservation.id}">维修</a>
			        	</c:if>
			        	</c:if>
        	            </td>
                        <%-- <td><c:if test="${reservation.CLabRoomDeviceRepairStatus!=''}"> --%>
                        <td><c:if test="${! empty reservation.CDictionaryByStatusId}">
			        	<%-- ${reservation.CLabRoomDeviceRepairStatus.name} --%>
			        	${reservation.CDictionaryByStatusId.CName}
			        	</c:if>
			        	<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_EQUIPMENTADMIN">
			        	<%-- <c:if test="${reservation.CLabRoomDeviceRepairStatus==null}"> --%>
			        	<c:if test="${reservation.CDictionaryByStatusId==null}">
			        	<button onclick="addStatus(${reservation.id});">操作</button>
			        	</c:if>  
        				</sec:authorize></td>
                        </tr>
                        </c:if>
                		</c:forEach>
                       
                </tbody>
            </table>
            
<!-- 分页模块 -->
<div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/device/applyDeviceRepairList?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/device/applyDeviceRepairList?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/device/applyDeviceRepairList?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/device/applyDeviceRepairList?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)" onclick="next('${pageContext.request.contextPath}/device/applyDeviceRepairList?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)" onclick="last('${pageContext.request.contextPath}/device/applyDeviceRepairList?page=${pageModel.totalPage}')" target="_self">末页</a>
    </div>
<!-- 分页模块 -->
</div>

</div>
</div>
</div>
</div>
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


