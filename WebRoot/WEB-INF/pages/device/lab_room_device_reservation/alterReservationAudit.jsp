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
//定义全局变量
var result=1;//审核结果,默认是1--通过
$(document).ready(function(){
	
	if(${refresh==1}){
		parent.location.href="${pageContext.request.contextPath}/device/passReservationList?page=1";
	}
	
	if(${refresh==2}){
		parent.location.href="${pageContext.request.contextPath}/device/rejectedReservationList?page=1";
	}
	
	$("#result1").change(function(){
		result=$("#result1").val();
	});
	$("#result2").change(function(){
		result=$("#result2").val();
	});
});

function info(id){
	var begintime=$("#begintime").val();
	var endtime=$("#endtime").val();
	$.ajax({
		url:"${pageContext.request.contextPath}/device/judgeConfliction?id="+id,
		type:"POST",
		data: {'begintime':begintime,'endtime':endtime},
        success:function(data){//AJAX查询成功
        		if(data == 'success'){
        			document.auditform.submit();
        		}
        		if(data == 'error'){
        			alert("你预约的时间和其他预约存在时间冲突或者您的预约时间小于当前时间!");
        			if(${tag==1}){
	        			parent.location.href="${pageContext.request.contextPath}/device/passReservationList?page=1";
        			}
        			if(${tag==2}){
	        			parent.location.href="${pageContext.request.contextPath}/device/rejectedReservationList?page=1";
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
			<li class="TabbedPanelsTab selected" tabindex="0">
			<a href="javascript:void(0);">更改审核</a>
			</li>
	  </ul>
	  
	<!-- 内容栏 -->  
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
					<div class="title">更改审核意见</div>
							<form:form name="auditform" action="${pageContext.request.contextPath}/device/saveAlterAudit?id=${reservation.id}" method="post" modelAttribute="audit">
							<table>
								<tr>
									<td>审核意见</td>
									<td><form:textarea path="remark"/> </td>
								</tr>
								<tr>
									<td>结果</td>
									<td>
									<%-- <form:radiobutton path="CTrainingResult.id" value="1" checked="checked"></form:radiobutton> <label>通过</label>
						      		<c:forEach items="${results}" var="r" varStatus="i">
						      		<c:if test="${i.index>0 }">
					              		<form:radiobutton path="CTrainingResult.id" value="${r.id}" id="result${i.count}"></form:radiobutton> <label>${r.name}</label>
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
		   	 							<a href="${pageContext.request.contextPath}/device/modifyReserva/${reservation.labRoomDevice.id}/${reservation.id}/5">修改时间</a>
	              					</td>
	              					<td>
	              					</td>
								</tr> 
								<tr>
									<td>
									<input type="button" onclick="info(${reservation.id})" value="提交">
									<input type="button" onclick="window.history.go(0)" value="取消">
									</td>
								</tr>
								
							</table>
							</form:form>
				
				
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
