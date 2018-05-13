<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipment-resources"/>

<html>
<head>
<title></title>
<meta name="decorator" content="iframe"/>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-sacale=1.0,user-scalable=no"/>
<style type="text/css" media="screen">
			@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/icon.css");
			@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/gray/easyui.css");
			@import url("${pageContext.request.contextPath}/css/style.css");
</style>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">

<!-- 文件上传的样式和js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dhulims/device/editDevice.js"></script>
<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->


<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
function cancel(){
	window.location.href="${pageContext.request.contextPath}/device/listLabRoomDevice?page=1";
}
</script>

<script>
//定义全局变量

var needLoan="${device.CDictionaryByAllowLending.id}";//是否允许出借
var appointment="${device.CDictionaryByAllowAppointment.id}";//是否允许预约
var needAudit="${device.CDictionaryByIsAudit.id}";//预约是否需要审核
//var needtutor="${device.CDictionaryByTeacherAudit.id}";//是否需要导师审核
var needlabadministrator="${device.CDictionaryByLabManagerAudit.id}";//是否需要实验室管理员审核
var needadministrator="${device.CDictionaryByManagerAudit.id}";//是否需设备管理员审核
var needAllowSecurityAccess="${device.CDictionaryByAllowSecurityAccess.id}";//是否需要安全准入trainType
var trainType="${device.CDictionaryByTrainType.id}";//培训形式
//var isAuditTimeLimit = "${device.isAuditTimeLimit}";//是否有预约审核时间限制
//得到各个参数的值
$(document).ready(function(){
	
	$("#needLoan1").change(function(){
		needLoan=$("#needLoan1").val();
	});
	$("#needLoan2").change(function(){
		needLoan=$("#needLoan2").val();
	});
	
	$("#appointment1").change(function(){
		appointment=$("#appointment1").val();
	});
	$("#appointment2").change(function(){
		appointment=$("#appointment2").val();
	});
	
	$("#needAudit1").change(function(){
		needAudit=$("#needAudit1").val();
	});
	$("#needAudit2").change(function(){
		needAudit=$("#needAudit2").val();
	});
	
	$("#needadministrator1").change(function(){
		needlabadministrator=$("#needadministrator1").val();
	});
	$("#needadministrator2").change(function(){
		needlabadministrator=$("#needadministrator2").val();
	});
	
	$("#needadministratora1").change(function(){
		needadministrator=$("#needadministratora1").val();
	});
	$("#needadministratora2").change(function(){
		needadministrator=$("#needadministratora2").val();
	});
	
	$("#needAllowSecurityAccess1").change(function(){
		needAllowSecurityAccess=$("#needAllowSecurityAccess1").val();
	});
	$("#needAllowSecurityAccess2").change(function(){
		needAllowSecurityAccess=$("#needAllowSecurityAccess2").val();
	});
	
	$("#trainType1").change(function(){
		trainType=$("#trainType1").val();
	});
	$("#trainType2").change(function(){
		trainType=$("#trainType2").val();
	});
	
	/*$("#isAuditTimeLimit0").change(function(){
		isAuditTimeLimit=$("#isAuditTimeLimit0").val();
	});
	$("#isAuditTimeLimit1").change(function(){
		isAuditTimeLimit=$("#isAuditTimeLimit1").val();
	});*/

});

//保存参数设置
function saveDeviceSettingRest(id){//将labRoomId deviceNumber deviceName page传递到后台
	
	var needLoan1=needLoan;//是否允许出借
	var appointment1=appointment;//是否允许预约
	var needAudit1=needAudit;//预约是否需要审核
	//var needtutor1=needtutor;//是否需要导师审核
	var needlabadministrator1=needlabadministrator;//是否需要实验室管理员审核
	var needadministrator1=needadministrator;//是否需设备管理员审核
	var needAllowSecurityAccess1=needAllowSecurityAccess;//是否需要安全准入
	var trainType1=trainType;//培训形式
	//var isAuditTimeLimit1=isAuditTimeLimit;//是否有预约审核时间限制
	
    if(needLoan==""){
    	needLoan1=-1;
    }
    if(appointment==""){
    	appointment1=-1;
    }
    if(needAudit==""){
    	needAudit1=-1;
    }
    if(needlabadministrator==""){
    	needlabadministrator1=-1;
    }
    if(needadministrator==""){
    	needadministrator1=-1;
    }
    if(needAllowSecurityAccess==""){
    	needAllowSecurityAccess1=-1;
    }
    if(trainType==""){
    	trainType1=-1;
    }
    
	var url = "${pageContext.request.contextPath}/device/saveDeviceSettingRest/" + ${labRoomId} + "/"+ ${deviceNumber} + "/" + "${deviceName}" +"/"+ ${username}+"/"+${page}+ "/"+ needLoan1
	+ "/"+ appointment1+ "/"+ needAudit1+ "/"+ -1+ "/"+ needlabadministrator1+"/"+ needadministrator1+"/"
	+ needAllowSecurityAccess1+"/" +1+"/"+-1+"/" + id+ "/"+"${schoolDevice_allowAppointment}"; //  trainType1 默认为   1 -- 集中时间段培训
	
	//alert(url);
	window.location.href=url;
}


//是否需要审核的联动
$(document).ready(function(){
	if(${allowAppointment==2} || ${empty isAudit})
    {//是否可以预约联动
		
        document.getElementById("isAudit").style.display="None";
        document.getElementById("manager").style.display="None";
        //document.getElementById("teacher").style.display="None";
        document.getElementById("labManager").style.display="None";
        document.getElementById("allowSecurityAccess").style.display="None";
        document.getElementById("trainingType").style.display="None";
        //document.getElementById("isAuditTimeLimit").style.display="None";
    }else
        {
        document.getElementById("isAudit").style.display="";
        if(${isAudit==2} || ${empty isAudit})
	    {//是否需要审核联动
        	document.getElementById("manager").style.display="None";
	        //document.getElementById("teacher").style.display="None";
	        document.getElementById("labManager").style.display="None";
	    }else
	        {
	        document.getElementById("manager").style.display="";
	        //document.getElementById("teacher").style.display="";
	        document.getElementById("labManager").style.display="";
	        }
         
        document.getElementById("allowSecurityAccess").style.display="";
	    if(${allowSecurityAccess==2} || ${empty allowSecurityAccess})
	    {//是否需要安全准入联动
	    	document.getElementById("trainingType").style.display="None";
	    }else
	        {
	        document.getElementById("trainingType").style.display="None";//此处本来是""，由于不需要进行培训形式选择才引掉
	        }
 		}	
	
$("#needAudit1").change(function(){
	document.getElementById("manager").style.display="";
	//document.getElementById("teacher").style.display="";
	document.getElementById("labManager").style.display="";
	
});
//是否需要安全准入联动
$("#needAllowSecurityAccess1").change(function(){
	document.getElementById("trainingType").style.display="None";//此处本来是""，由于不需要进行培训形式选择才引掉
});


//是否可以预约联动
$("#appointment1").change(function(){
	document.getElementById("allowSecurityAccess").style.display="";
	document.getElementById("isAudit").style.display="";
	if(${isAudit==2} || ${empty isAudit})
    {//是否需要审核联动
    	document.getElementById("manager").style.display="None";
        //document.getElementById("teacher").style.display="None";
        document.getElementById("labManager").style.display="None";
    }else
        {
        document.getElementById("manager").style.display="";
        //document.getElementById("teacher").style.display="";
        document.getElementById("labManager").style.display="";
        }
});
});

$(document).ready(function(){
	$("#needAudit2").change(function(){
		document.getElementById("manager").style.display="None";
		//document.getElementById("teacher").style.display="None";
		document.getElementById("labManager").style.display="None";
		//$("#needtutor2").attr("checked","checked");
		$("#needadministratora2").attr("checked","checked");
		$("#needadministrator2").attr("checked","checked");
	});
	if($("#needAudit2").prop("checked")){
		$("#needAudit2").change();
	}
	$("#needAllowSecurityAccess2").change(function(){
		document.getElementById("trainingType").style.display="None";
	});
	
	$("#appointment2").change(function(){
		document.getElementById("allowSecurityAccess").style.display="None";
		document.getElementById("isAudit").style.display="None";
		document.getElementById("manager").style.display="None";
		//document.getElementById("teacher").style.display="None";
		document.getElementById("labManager").style.display="None";
		document.getElementById("trainingType").style.display="None";
		//document.getElementById("isAuditTimeLimit").style.display="None";
	});
	});
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/xidlims/device/editDevice.js"></script>

<style>
.tool-box2 th{text-align:left;}
table label{
position:relative;
top:-6px;
margin-left:3px;
}
</style>
</head>


<body>

<div class="right-content">	
	<div class="tool-box1">
		<table class="equip_tab">
		    <tr>
				<td>
				    <span>设备编号：</span>
				    <p>${device.schoolDevice.deviceNumber}</p>
				</td>
				<td>
				    <span>设备名称：</span>
				    <p class="equip_name">${device.schoolDevice.deviceName}</p>
				</td>
				<td>
				    <span>仪器型号：</span>
				    <p>${device.schoolDevice.devicePattern}</p>
				</td>
			</tr>
			<tr>
				<td>
				    <span>所在实验室：</span>
				    <p>${device.labRoom.labRoomName}</p>
				</td>
				<td>
				    <span>生产国别：</span>
				    <p>${device.schoolDevice.deviceCountry}</p>
				</td>
				<td>
				    <span>生产厂家：</span>
				    <p>${device.schoolDevice.manufacturer}</p>
				</td>
			</tr>
		</table>
	</div>
	<div id="TabbedPanels1" class="TabbedPanels">
	 <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceInfoRest(${device.id})">设备详情</a>
		</li>
		<%-- <c:if test="${device.CActiveByAllowSecurityAccess.id == 1}"> --%>
		<c:if test="${device.CDictionaryByAllowSecurityAccess.CCategory=='c_active' &&device.CDictionaryByAllowSecurityAccess.CNumber=='1'}">
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceTrainingRest(${device.id})">培训计划</a>
		</li>
		</c:if>
		<li class="TabbedPanelsTab selected" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceSettingRest(${device.id})">参数设置</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceImageRest(${device.id})">相关图片</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceVideoRest(${device.id})">相关视频</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceDocumentRest(${device.id})">相关文档</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="javascript:void(0);" onclick="editDeviceDimensionalCodeRest(${device.id})">二维码</a>
		</li>
	</ul>
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">
				<div id="title">参数设置</div>
				<a class="btn btn-new"  herf="#" onclick="openSetupLink();">返回</a>
				</div>
					<form:form action="${pageContext.request.contextPath}/device/saveDeviceSetting" method="post" modelAttribute="device">
					<div class="new-classroom">
			<table>
			<%--<tr>
				<form:hidden path="id"/>
				<td style="width:40%">是否允许出借:</td>
				<td>
				<c:forEach items="${CActives}" var="active" varStatus="i">
				 <form:radiobutton  path="CActiveByAllowLending.id" value="${active.id}" id="needLoan${i.count}" /><label for="needLoan${i.count}">${active.name}</label> 
				<form:radiobutton  path="CDictionaryByAllowLending.id" value="${active.id}" id="needLoan${i.count}" /><label for="needLoan${i.count}">${active.CName}</label>
				</c:forEach>										
				</td>
			</tr>--%>	
			<tr id="allowAppointment">
				<td>是否允许预约:</td>
				<td>
				<c:forEach items="${CActives}" var="active" varStatus="i">
				<%-- <form:radiobutton path="CActiveByAllowAppointment.id" value="${active.id}" id="appointment${i.count}" /><label for="appointment${i.count}">${active.name}</label> --%>
				<form:radiobutton path="CDictionaryByAllowAppointment.id" value="${active.id}" id="appointment${i.count}" /><label for="appointment${i.count}">${active.CName}</label>
				</c:forEach>										
				</td>
			</tr>	
			<tr id="isAudit">
				<td>预约是否需要审核:</td>
				<td>
				<c:forEach items="${CActives}" var="activ" varStatus="i">
				<%-- <form:radiobutton id="needAudit${i.count}" path="CActiveByIsAudit.id" value="${activ.id}"  /><label for="needAudit${i.count}">${activ.name}</label> --%>
				<form:radiobutton id="needAudit${i.count}" path="CDictionaryByIsAudit.id" value="${activ.id}"  /><label for="needAudit${i.count}">${activ.CName}</label>
				</c:forEach>								
				</td>
			</tr>
				
			<%--<tr id="teacher">
				<td>是否需要导师审核:</td>
				<td>
				<c:forEach items="${CActives}" var="activ" varStatus="i">
				 <form:radiobutton path="CActiveByTeacherAudit.id" value="${activ.id}" id="needtutor${i.count}"/><label for="needtutor${i.count}"> ${activ.name}</label> 
				<form:radiobutton path="CDictionaryByTeacherAudit.id" value="${activ.id}" id="needtutor${i.count}"/><label for="needtutor${i.count}"> ${activ.CName}</label>
				</c:forEach>								
				</td>
			</tr>--%>
			
			<%--<tr id="isAuditTimeLimit">
				<td>否需要导师预约审核时间限制:</td>
				<td>
				<form:radiobutton path="isAuditTimeLimit" value="1" id="isAuditTimeLimit1"/><label for="isAuditTimeLimit0">是</label>
				<form:radiobutton path="isAuditTimeLimit" value="0" id="isAuditTimeLimit0"/><label for="isAuditTimeLimit1">否</label>
				</td>
			</tr>--%>
			
			<tr id="labManager">
				<td>是否需要实验室管理员审核:</td>
				<td>
				<c:forEach items="${CActives}" var="activ" varStatus="i">
				<%-- <form:radiobutton path="CActiveByLabManagerAudit.id" value="${activ.id}" id="needadministrator${i.count}"/><label for="needadministrator${i.count}">${activ.name}</label> --%>
				<form:radiobutton path="CDictionaryByLabManagerAudit.id" value="${activ.id}" id="needadministrator${i.count}"/><label for="needadministrator${i.count}">${activ.CName}</label>
				</c:forEach>								
				</td>
			</tr>
				
			<tr id="manager">
				<td>是否需要设备管理员审核:</td>
				<td>
				<c:forEach items="${CActives}" var="activ" varStatus="i">
				<%-- <form:radiobutton path="CActiveByManagerAudit.id" value="${activ.id}" id="needadministratora${i.count}"/><label for="needadministratora${i.count}">${activ.name}</label> --%>
				<form:radiobutton path="CDictionaryByManagerAudit.id" value="${activ.id}" id="needadministratora${i.count}"/><label for="needadministratora${i.count}">${activ.CName}</label>
				</c:forEach>								
				</td>
			</tr>
				
			<tr id="allowSecurityAccess">
				<td>是否需要安全准入:</td>
				<td>
				<c:forEach items="${CActives}" var="activ" varStatus="i">
				<%-- <form:radiobutton path="CActiveByAllowSecurityAccess.id" value="${activ.id}" id="needAllowSecurityAccess${i.count}"/>${activ.name} --%>
				<form:radiobutton path="CDictionaryByAllowSecurityAccess.id" value="${activ.id}" id="needAllowSecurityAccess${i.count}"/>${activ.CName}
				</c:forEach>								
				</td>
			</tr>
			
			<tr id="trainingType">
				<td>培训形式:</td>
				<td>
				<c:forEach items="${CTrainingTypes}" var="type" varStatus="i">
				<%-- <form:radiobutton path="CTrainingType.id" value="${type.id}" id="trainType${i.count}"/><label for="trainType${i.count}">${type.name}</label> --%>
				<form:radiobutton path="CDictionaryByTrainType.id" value="${type.id}" id="trainType${i.count}"/><label for="trainType${i.count}">${type.CName}</label>
				</c:forEach>								
				</td>
			</tr>
			
			<tr>	
				<td>
				<input type="button" onclick="saveDeviceSettingRest(${device.id});" value="确定">
				<input type="button" onclick="openSetupLink();" value="取消" >
				</td>
			</tr>
			
			</table>
			</div>
			</form:form>
			</div>
		</div>
	  </div>
	  		<input type="hidden" id="labRoomId" value="${labRoomId }">
            <input type="hidden" id="deviceName" value="${deviceName }">
            <input type="hidden" id="deviceNumber" value="${deviceNumber }">
            <input type="hidden" id="username" value="${username }">
            <input type="hidden" id="page" value="${page }">
            <input type="hidden" id="pageContext" value="${pageContext.request.contextPath }">
            <input type="hidden" id="schoolDevice_allowAppointment" value="${schoolDevice_allowAppointment}">
	</div>
</div>
</body>
</html>
