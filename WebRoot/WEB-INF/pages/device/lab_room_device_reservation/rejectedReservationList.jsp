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
	window.location.href="${pageContext.request.contextPath}/device/rejectedReservationList?page=1";
}	

//审核通过后的审核意见修改
function alterAudit(id) {
    var sessionId=$("#sessionId").val();
    var con = '<iframe scrolling="yes" id="alter" frameborder="0"  src="${pageContext.request.contextPath}/device/alterAudit?id='+id+'&tag=2"  style="width:100%;height:100%;"></iframe>'
    $("#alterAudit").html(con);  
    //获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#alterAudit').window({left:"0px", top:topPos+"px"});
    $("#alterAudit").window('open');
}
</script>

</head>

<body>
<div class="navigation">
				<div id="navigation">
					<ul>
						<li><a href="javascript:void(0)">实验设备管理</a></li>
						<li class="end"><a href="javascript:void(0)">设备预约管理</a></li>
					</ul>
				</div>
			</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	<ul class="TabbedPanelsTabGroup">
		
		<sec:authorize ifNotGranted="ROLE_STUDENT,ROLE_GRADUATE">
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="${pageContext.request.contextPath}/device/myReservationList?page=1">批量审批</a>
		</li>
		</sec:authorize>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="${pageContext.request.contextPath}/device/auditReservationList?page=1">审核中</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="${pageContext.request.contextPath}/device/passReservationList?page=1">审核通过</a>
		</li>
		<li class="TabbedPanelsTab selected" tabindex="0">
		<a href="${pageContext.request.contextPath}/device/rejectedReservationList?page=1">审核拒绝</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="${pageContext.request.contextPath}/device/reservationList?page=1">全部</a>
		</li>
	</ul>
	<div class="TabbedPanelsTabGroup-box">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		     <div class="tool-box">
		     <form:form name="queryForm" action="${pageContext.request.contextPath}/device/rejectedReservationList?page=1" method="post" modelAttribute="reservation">
				<table class="list_form">
					<tr>
						<td>
							学期：
							<form:select path="schoolTerm.id">
							<form:option value="">请选择</form:option>
							<form:options items="${terms}" itemLabel="termName" itemValue="id"/>
							</form:select>
							
						</td>
						<td>
						<span style="float: left;margin: 3px 0 0 10px;">设备名称：</span><form:input path="labRoomDevice.schoolDevice.deviceName"/>
						</td>
						<td>
						实验室：</td>
						<td>
							<form:select path="labRoomDevice.labRoom.id" class="chzn-select">
							<form:option value="">请选择</form:option>
							<form:options items="${rooms}"/>
							</form:select>
						</td>
						<td>
							<input type="button" value="取消" onclick="cancelQuery();">
							<input type="submit" value="查询">
						</td>
					</tr >
			</table>
			</form:form>
		       
		    </div>
    	<div class="content-box">
    		<div class="title">
    			<div id="title">审核拒绝</div>
    			<a class="btn btn-new" href="javascript:void(0)">导出</a>
    		</div>
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                    	<th style="width:3%">序号</th>
                        <th style="width:15%">预约设备</th>
                        <th style="width:5%">申请人</th>
                        <th style="width:8%">申请人联系电话</th>
                        <%--<th style="width:5%">指导教师</th>--%>
                        <th style="width:10%">课题组名称</th>
                        <th style="width:20%">申请内容</th>
                        <th style="width:7%">日期</th>
                        <th style="width:8%">使用时间</th>
                        <th style="width:5%">实验室</th>
                        <th style="width:7%">审核人</th>
                        <th style="width:5%">状态</th>
                        <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EQUIPMENTADMIN,ROLE_TEACHER">
                        <th style="width:5%">操作</th>
                        </sec:authorize>
                    </tr>
                </thead>
                
                <tbody>
                		<c:forEach items="${reservationList}" var="reservation" varStatus="i">
                		<tr>
                        <td>${i.count+(page-1)*pageSize}</td>
                        <td>
	                        <c:if test="${empty reservation.innerDeviceName }">
	                        	${reservation.labRoomDevice.schoolDevice.deviceName}[(${reservation.labRoomDevice.schoolDevice.deviceNumber})]
	                        </c:if>
	                        <c:if test="${not empty reservation.innerDeviceName }">
	                        	${reservation.innerDeviceName}<font color="red">关联设备</font>
	                        </c:if>
                        </td>
                        <td>${reservation.userByReserveUser.cname}</td>
                        <td>${reservation.phone}</td>
                        <%--<td>${reservation.userByTeacher.cname}</td>--%>
                        <td>${reservation.researchProject.name}</td>
                        <td><p>${reservation.content}</p></td>
                        <td><fmt:formatDate value="${reservation.originalBegin.time}" pattern="yyyy-MM-dd"/></td>
						<td>
                        	<c:if test="${reservation.begintime.time.day eq reservation.endtime.time.day}">
		                        <fmt:formatDate value="${reservation.originalBegin.time}" pattern="HH:mm:ss"/>-<fmt:formatDate value="${reservation.originalEnd.time}" pattern="HH:mm:ss"/>
	                        </c:if>
	                        
	                        <c:if test="${reservation.begintime.time.day ne reservation.endtime.time.day}">
		                        	起<fmt:formatDate value="${reservation.originalBegin.time}" pattern="yyyy/MM/dd HH:mm:ss"/><br>止<fmt:formatDate value="${reservation.originalEnd.time}" pattern="yyyy/MM/dd HH:mm:ss"/>
	                        </c:if>
                        </td>
                        <td>${reservation.labRoomDevice.labRoom.labRoomName}</td>
                        <td>
                        <c:if test="${reservation.CAuditResult.id ne 4 && reservation.CAuditResult.id ne 5 && reservation.CAuditResult.id ne 6}">  <!-- 非审核取消状态才显示 -->
                        <c:forEach items="${reservation.labRoomDeviceReservationResults}" var="s"> 
                              <c:if test="${s.CTrainingResult.CNumber==1}">${s.user.cname}(<font color="blue">通过</font>)<br></c:if>
                         </c:forEach>
                         <c:forEach items="${reservation.labRoomDeviceReservationResults}" var="s"> 
                              <c:if test="${s.CTrainingResult.CNumber==2}">${s.user.cname}(<font color="red">拒绝</font>)<br></c:if>
                         </c:forEach>
                         </c:if>
                         </td>
                        <td><a href="${pageContext.request.contextPath}/device/labManagerReservationAudit?id=${reservation.id}"><%-- ${reservation.CAuditResult.name} --%>${reservation.CAuditResult.CName}</a></td>
                        <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EQUIPMENTADMIN,ROLE_TEACHER">
                        <td>
                        <%-- <c:if test="${reservation.CAuditResult.id==1}"> --%>
                        <c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' && reservation.CAuditResult.CNumber =='1'}">
                        <%--<a href="${pageContext.request.contextPath}/device/deviceAudit?id=${reservation.id}">审核</a>--%>
                        <a href="${pageContext.request.contextPath}/device/teacherReservationAudit?id=${reservation.id}">审核</a>
                        </c:if>
                        <%-- <c:if test="${(reservation.CAuditResult.id==3)}"> --%>
                         <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EQUIPMENTADMIN">
                        <c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' && reservation.CAuditResult.CNumber =='3'}">
	                    	<a href="javascript:alterAudit(${reservation.id});" >更改审核结果</a>
	                    </c:if>
	                    </sec:authorize>
                        </td>      
                        </sec:authorize>                     
                        </tr>
                		</c:forEach>
                       
                </tbody>
            </table>
            
<!-- 分页模块 -->
<div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/device/rejectedReservationList?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/device/rejectedReservationList?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/device/rejectedReservationList?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/device/rejectedReservationList?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)" onclick="next('${pageContext.request.contextPath}/device/rejectedReservationList?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)" onclick="last('${pageContext.request.contextPath}/device/rejectedReservationList?page=${pageModel.totalPage}')" target="_self">末页</a>
    </div>
<!-- 分页模块 -->
</div>

</div>
</div>
</div>
</div>
</div>
<!--审核通过后的审核意见修改 -->
<div id="alterAudit" class="easyui-window" title="更改审核结果" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width: 1000px; height:800px;">
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


