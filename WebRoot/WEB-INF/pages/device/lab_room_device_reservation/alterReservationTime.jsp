<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<title>无标题文档</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function info(id){
	var begintime=$("#begintime").val();
	var endtime=$("#endtime").val();
	$.ajax({
		url:"${pageContext.request.contextPath}/device/judgeConfliction?id="+id,
		type:"POST",
		data: {'begintime':begintime,'endtime':endtime},
        success:function(data){//AJAX查询成功
        		if(data == 'success'){
        			alert("修改成功，请关闭当前页面");
        		}
        		if(data == 'error'){
        			alert("你预约的时间和其他预约存在时间冲突或者您的预约时间小于当前时间!");
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
</div>
</div>
<div class="right-content">	
	<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
					<form:form name="auditform" action="" method="post">
					<table>
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
   	 							<a href="${pageContext.request.contextPath}/device/modifyReserva/${reservation.labRoomDevice.id}/${reservation.id}/4">修改时间</a>
             					</td>
             					<td>
             					</td>
						</tr> 
						<tr>
							<td>
							<input type="button" onclick="info(${reservation.id})" value="提交">
							</td>
						</tr>
					</table>
					</form:form>
			</div>
		</div>
		
	  </div>
	</div>
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
