<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipment-resources"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta name="decorator" content="iframe"/>
 <link href="${pageContext.request.contextPath}/css/newRoom.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div  class="l_right">
    <div class="list_top">
        <ul class="top_tittle"><fmt:message key="look.up"/></ul>
    </div>
    <div id="userInfo">
              <table class="tb" cellspacing="0">
				<tr>
					<td><fmt:message key="equipment.number.title"/></td>
					<td>${schoolEquipment.equipmentNumber}</td>
				</tr>
				<tr>
					<td><fmt:message key="equipment.name.title"/></td>
					<td>${schoolEquipment.equipmentName}</td>
				</tr>
				<tr>
					<td><fmt:message key="equipment.en.name"/></td>
					<td>${schoolEquipment.equipmentEnName}</td>
				</tr>
				<tr>
					<td><fmt:message key="equipment.use.direction"/></td>
					<td>${schoolEquipment.equipmentUseDirection}</td>
				</tr>
				<tr>
					<td><fmt:message key="equipment.buy.date"/></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${schoolEquipment.equipmentBuyDate.time}"/></td>
				</tr>
				<tr>
					<td><fmt:message key="equipment.pattern.title"/></td>
					<td>${schoolEquipment.equipmentPattern}</td>
				</tr>
				<tr>
					<td><fmt:message key="equipment.format.title"/></td>
					<td>${schoolEquipment.equipmentFormat}</td>
				</tr>
				<tr>
					<td><fmt:message key="equipment.price.title"/></td>
					<td>${schoolEquipment.equipmentPrice}</td>
				</tr>
				<tr>
					<td><fmt:message key="equipment.accounted.date"/></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${schoolEquipment.equipmentAccountedDate.time}"/></td>
				</tr>
				<tr>
					<td><fmt:message key="equipment.supplier.title"/></td>
					<td>${schoolEquipment.equipmentSupplier}</td>
				</tr>
				<tr>
					<td><fmt:message key="department.name"/></td>
					<td>${current.schoolAcademy.academyNumber}</td>
				</tr>
				<tr>
					<td><fmt:message key="equipment.user"/></td>
					<td>${schoolEquipment.userByUserNumber}</td>
				</tr>
				<tr>
					<td><fmt:message key="equipment.resourse"/></td>
					<td>${schoolEquipment.projectSource}</td>
				</tr>
            </table>
          </div>
            	<div class="moudle_footer">
        			<ul class="top_tittle" style="float:right;margin-right:40px;"><a onclick="window.history.go(-1)" href="javascript:void(0)">返回</a></ul>
    			</div>
</div>
</body>
</html>


