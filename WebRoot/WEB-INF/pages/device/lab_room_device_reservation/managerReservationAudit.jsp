<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<title>无标题文档</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<%--<script src="${pageContext.request.contextPath}/js/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
--%><script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function info(id){
	var begintime=$("#begintime").val();
	var endtime=$("#endtime").val();
	var resultId=1;//默认同意
	if(document.getElementById('result2').checked){
		resultId=2;
	}
	
	var remark=$("#remark").val();
	if($("#remark").val()==""){
		remark="审核人（设备管理员）未填写审核意见。";
	}
	$.ajax({
		url:"${pageContext.request.contextPath}/device/judgeConfliction?id="+id,
		type:"POST",
		data: {'begintime':begintime,'endtime':endtime},
        success:function(data){//AJAX查询成功
        		if(data == 'success'){
        			if(${isRest==0}){
        				document.auditform.submit();
        			}else{
        				var url = "${pageContext.request.contextPath}/device/saveManagerAuditRest/" + ${labRoomId} + "/"+ ${schoolTermId} + "/" + "${deviceName}" + "/"+${page}+"/"+resultId+"/"+remark+"/"+id;
        				window.location.href=url;
        			}
        		}
        		if(data == 'error'){
        			alert("你预约的时间和其他预约存在时间冲突或者您的预约时间小于当前时间!");
        			if(${isRest==1}){
	        			var url2 = "${pageContext.request.contextPath}/device/managerReservationAuditRest/" + ${labRoomId} + "/"+ ${schoolTermId} + "/" + "${deviceName}" + "/"+${page}+"/"+id;
	    				window.location.href=url2;
        			}else{
	        			window.location.href="${pageContext.request.contextPath}/device/managerReservationAudit?id="+id;
        			}
        		}
        }
	});
}

</script>
</head>
<body>

<div class="iStyle_Conteiner">
<div class="iStyle_RightInner">

<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">实验设备管理</a></li>
	<li class="end">设备预约管理</li>
</ul>
</div>
</div>
<div class="right-content">	
	<div class="tool-box1">
		<table>
			<tr>
				<th>设备编号</th><td>${reservation.labRoomDevice.schoolDevice.deviceNumber}</td>
				<th>设备名称</th>
				<td>
                 <c:if test="${empty reservation.innerDeviceName }">
                 	${reservation.labRoomDevice.schoolDevice.deviceName}[(${reservation.labRoomDevice.schoolDevice.deviceNumber})]
                 </c:if>
                 <c:if test="${not empty reservation.innerDeviceName }">
                 	${reservation.innerDeviceName}<font color="red">关联设备</font>
                 </c:if>
                </td>
			</tr>
			<tr>
				<th>申请人</th><td>${reservation.userByReserveUser.cname}[${reservation.userByReserveUser.username}]（${reservation.phone }）</td>
				<th>指导老师</th><td>${reservation.userByTeacher.cname}</td>
			</tr>
			<tr>
				<th>申请内容</th><td>${reservation.content}</td>
				<th>使用时间</th>
				<td>
				<%-- <c:if test="${reservation.CAuditResult.id!=3 }"> --%>
				<c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' && reservation.CAuditResult.CNumber!='3' }">
				<fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>--<fmt:formatDate value="${reservation.endtime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</c:if>
				<%-- <c:if test="${reservation.CAuditResult.id==3 }"> --%>
				<c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' && reservation.CAuditResult.CNumber=='3' }">
				<fmt:formatDate value="${reservation.originalBegin.time}" pattern="yyyy/MM/dd HH:mm:ss"/>--<fmt:formatDate value="${reservation.originalEnd.time}" pattern="yyyy/MM/dd HH:mm:ss"/>
				</c:if>
				</td>
			</tr>
		</table>
	</div>
	<div id="TabbedPanels1" class="TabbedPanels">
	<!-- 标题栏  -->
	  <ul class="TabbedPanelsTabGroup">
	  	
			<%--<li class="TabbedPanelsTab" tabindex="0">
			<a href="${pageContext.request.contextPath}/device/teacherReservationAudit?id=${reservation.id}">导师审核</a>
			</li>--%>
			
			<li class="TabbedPanelsTab" tabindex="0">
			<a href="${pageContext.request.contextPath}/device/labManagerReservationAudit?id=${reservation.id}">实验室管理员审核</a>
			</li>
		
			<li class="TabbedPanelsTab selected"  tabindex="0">
			<a href="${pageContext.request.contextPath}/device/managerReservationAudit?id=${reservation.id}">设备管理员审核</a>
			</li>
		
	  </ul>
	  
	 <!--内容栏  --> 
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
			<%-- <c:if test="${reservation.labRoomDevice.CActiveByManagerAudit.id==1}"> --%>  <!-- 需要设备管理员审核-->
			<c:if test="${reservation.labRoomDevice.CDictionaryByManagerAudit.CCategory=='c_active' && reservation.labRoomDevice.CDictionaryByManagerAudit.CNumber=='1' }">
			<%-- <c:if test="${isUnderManagerAudit&& reservation.CAuditResult.id==1}"> --%> <!-- stage为1，或stage为0且不需要导师审核 -->
			<c:if test="${isUnderManagerAudit && reservation.CAuditResult.CCategory=='c_audit_result' && reservation.CAuditResult.CNumber=='1'}">
				<c:if test="${user.username==reservation.labRoomDevice.user.username}">  <!-- 当前登陆人是设备管理员  -->
					<div class="title">设备管理员意见</div>
							<form:form name="auditform" action="${pageContext.request.contextPath}/device/saveManagerAudit?id=${reservation.id}" method="post" modelAttribute="audit">
							<table>
								<tr>
									<td>审核意见</td>
									<td><form:textarea path="remark" id="remark"/></td>
								</tr>
								<tr>
									<td>结果</td>
									<td>
									<%-- <form:radiobutton path="CTrainingResult.id" value="1" checked="checked"></form:radiobutton> <label>通过</label>
						      		<c:forEach items="${results}" var="r" varStatus="i">
						      		<c:if test="${i.index>0 }">
					              		<form:radiobutton path="CTrainingResult.id" value="${r.id}"></form:radiobutton> <label>${r.name}</label>
					              	</c:if>
					             	</c:forEach> --%>
					             	<c:forEach items="${results}" var="r" varStatus="i">
						      		<c:if test="${i.index==0 }">
					              		<form:radiobutton path="CTrainingResult.id" value="${r.id}" id="result${i.count}" checked="checked"></form:radiobutton> <label>${r.CName}</label>
					              	</c:if>
						      		<c:if test="${i.index>0 }">
					              		<form:radiobutton path="CTrainingResult.id" value="${r.id}" id="result${i.count}" ></form:radiobutton> <label>${r.CName}</label>
					              	</c:if>
					             	</c:forEach>
									</td>
								</tr>
								<tr style="display: none;">
	    	  						<td>修改预约时间</td>
	              					<td> <input id="begintime" class="Wdate" type="text" name="begintime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="width:200px;" value="<fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy-MM-dd HH:mm:ss" />" readonly /> </td>
									<td>到</td>
	   	 							<td> <input id="endtime" class="Wdate" type="text" name="endtime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="width:200px;" value="<fmt:formatDate value="${reservation.endtime.time}" pattern="yyyy-MM-dd HH:mm:ss" />" readonly /> </td>
								</tr> 
								
								<tr>
	    	  						<td>修改预约时间</td>
	              					<td>
	              						<fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy-MM-dd HH:mm:ss" />
										到
		   	 							<fmt:formatDate value="${reservation.endtime.time}" pattern="yyyy-MM-dd HH:mm:ss" />
		   	 							&nbsp;
		   	 							<a href="${pageContext.request.contextPath}/device/modifyReserva/${reservation.labRoomDevice.id}/${reservation.id}/3">修改时间</a>
	              					</td>
	              					<td>
	              					</td>
								</tr> 
								
								
								<tr>
									<td>
									<input type="button" onclick="info(${reservation.id})" value="提交">
									<input type="button" onclick="window.history.go(-1)" value="取消">
									</td>
								</tr>
							</table>
							</form:form>
				</c:if>
				
				
				
			</c:if>
				
				<c:if test="${user.username!=reservation.labRoomDevice.user.username && empty manageAudit}"> <!-- 申请人不是设备管理员，且未有设备管理员审核意见 -->
					<table id="listTable" width="50%" cellpadding="0" cellspacing="0" class="tablesorter" >
					    <tr><td colspan="6">设备管理员信息 </td></tr>
					 	<tr align="center" >
						     <td>姓名：   ${reservation.labRoomDevice.user.cname}</td> 
						     <td>工号：   ${reservation.labRoomDevice.user.username}</td> 
						     <td>部门：   ${reservation.labRoomDevice.user.schoolAcademy.academyName}</td> 
						     <td>联系方式：   ${reservation.labRoomDevice.user.telephone}</td> 
						</tr>  
					</table>
				</c:if>

			<%-- <c:if test="${reservation.stage>2 || 
	                        (reservation.stage==2 && (reservation.labRoomDevice.CActiveByTeacherAudit.id==2||reservation.labRoomDevice.CActiveByLabManagerAudit.id==2))||
	                        (reservation.stage==1 && reservation.labRoomDevice.CActiveByTeacherAudit.id==2 && reservation.labRoomDevice.CActiveByLabManagerAudit.id==2)
	                        ||reservation.stage==-1}"> <!-- 审核阶段高于当前阶段，或者审核拒绝 --> --%>		
			<c:if test="${reservation.stage>2 || 
	                        (reservation.stage==2 && ((reservation.labRoomDevice.CDictionaryByTeacherAudit.CCategory=='c_active' &&reservation.labRoomDevice.CDictionaryByTeacherAudit.CNumber=='2')||(reservation.labRoomDevice.CDictionaryByLabManagerAudit.CCategory=='c_active' &&reservation.labRoomDevice.CDictionaryByLabManagerAudit.CNumber=='2')))||
	                        (reservation.stage==1 && (reservation.labRoomDevice.CDictionaryByTeacherAudit.CCategory=='c_active' &&reservation.labRoomDevice.CDictionaryByTeacherAudit.CNumber=='2')&&(reservation.labRoomDevice.CDictionaryByLabManagerAudit.CCategory=='c_active' &&reservation.labRoomDevice.CDictionaryByLabManagerAudit.CNumber=='2'))
	                        ||reservation.stage==-1}"> <!-- 审核阶段高于当前阶段，或者审核拒绝 -->		
				<table id="listTable" width="50%" cellpadding="0" cellspacing="0" class="tablesorter">
						  <tr><td colspan="6" ><font color="blue">设备管理员审核</font> </td></tr>
						  <c:if test="${not empty manageAudit }">
								<tr>
									<td>审核意见：${manageAudit.remark }  </td>
									<%-- <td>审核结果：<font>${manageAudit.CTrainingResult.name}</font></td> --%>
									<td>审核结果：${manageAudit.CTrainingResult.CName}</td>
								</tr>
						  </c:if>
						  <c:if test="${empty manageAudit }">
						  <tr>
							  <td>
							  	<font color="red">没有设备管理员审核意见。</font>
							  </td>
						  </tr>
						  </c:if>
				</table>
			</c:if>
			
		</c:if>		
		
		<%-- <c:if test="${reservation.labRoomDevice.CActiveByManagerAudit.id!=1}"> --%>  <!-- 不需要设备管理员审核-->
		<c:if test="${reservation.labRoomDevice.CDictionaryByManagerAudit.CCategory=='c_active' &&reservation.labRoomDevice.CDictionaryByManagerAudit.CNumber!='1'}">
			<table id="listTable" width="50%" cellpadding="0" cellspacing="0" class="tablesorter">
						  <tr><td colspan="6"><font color="blue">设备管理员审核情况</font> </td></tr>
								<tr>
									<td>您好，该设备不需要设备管理员审核。</td>
								</tr>
			</table>
		</c:if>
		
			</div>
		</div>
		
	  </div>
	</div>
	<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
</div>


</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/is_Icons.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/is_LeftList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Downlist.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Allpages.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Interface.js"></script>
</body>
</html>
