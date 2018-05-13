<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipment-resources"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta name="decorator" content="iframe"/>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-sacale=1.0,user-scalable=no" />
<style type="text/css" media="screen">
@import
	url("${pageContext.request.contextPath}/js/jquery-easyui/themes/icon.css")
	;

@import
	url("${pageContext.request.contextPath}/js/jquery-easyui/themes/gray/easyui.css")
	;

			@import url("${pageContext.request.contextPath}/css/style.css");
</style>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/iconFont.css">
<script src="${pageContext.request.contextPath}/js/SpryTabbedPanels.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/SpryTabbedPanels.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/js/jquery-1.8.3.min.js"></script>
<!-- 文件上传的样式和js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
function xs(){
  
     var questionid="";
      var answer="";
	  var c=document.getElementById("dd").getElementsByTagName("input"); 
       	    for(var i=0;i<c.length;i++){   
                if(c[i].type=="radio" && c[i].checked){
                	questionid+=c[i].id+",";
                	answer+=c[i].value+",";
           		}
          	}
           $("#questionid").attr("value",questionid);
            $("#answer").attr("value",answer);
            alert(questionid);
              alert(answer);
		$("#form")
				.attr(
						"action",
						"${pageContext.request.contextPath}/device/savequestionandanswer?idkey=${device.id}");
}
</script>
</head>
<body>
<div class="right-content">	
	<div class="tool-box1">
		<table>
			<tr>
					<th>设备编号${device.id}</th>
					<td>${device.schoolDevice.deviceNumber}</td>
					<th>设备名称</th>
					<td>${device.schoolDevice.deviceName}</td>
			</tr>
			<tr>
					<th>所在实验室</th>
					<td>${device.labRoom.labRoomName}</td>
					<th>生产国别</th>
					<td>${device.schoolDevice.deviceCountry}</td>
			</tr>
			<tr>
					<th>仪器型号</th>
					<td>${device.schoolDevice.devicePattern}</td>
					<th>生产厂家</th>
					<td>${device.schoolDevice.manufacturer}</td>
			</tr>
		</table>
	</div>
	<div id="TabbedPanels1" class="TabbedPanels">
	 <form:form id="form" name="form"   method="post" >
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
							<div class="title">
								设备管理 <input type="hidden" id="questionid" name="questionid" /><input
									type="hidden" id="answer" name="answer" /> <span>
									<input class="btn-new"
									type="button" onclick="openexam();" value="导入题库">
								</span>
							</div>
					<table id="dd">
					     <c:forEach items="${questionlist }" var="s" varStatus="k">
							  <c:if test="${s.CQuestionType.id==1 }">
											<tr>
												<td colspan="4">${k.index+1}.${s.topic}</td>
											</tr>
							         <c:forEach  items="${s.labRoomDeviceAnswerses}" var="i">
												<tr>
													<td><input type="radio" id="${s.id }" name="${s.id }"
														value="${i.id }">${i.optionA}${i.optionAContent}</td>
												</tr>
							           </c:forEach>
							   </c:if>
							    <c:if test="${s.CQuestionType.id==2 }">
											<tr>
												<td colspan="2">${k.index+1}.${s.topic}<br>
												<input type="radio" id="${s.id }" name="${s.id }" value="1">正确<input
													type="radio" id="${s.id }" name="${s.id }" value="2">错误</td>
											</tr>
							    </c:if>
					      </c:forEach>
							<tr>
										<th><input type="submit" onclick="xs();" value="提交" />
										</th>
										<td><input type="button" value="返回"
											onclick="javascript:history.go(-1);" />
										</td>
							</tr>
						</table>
					</div>
	</div>
</div>
			</form:form>
		</div>
	</div>
</body>
</html>
