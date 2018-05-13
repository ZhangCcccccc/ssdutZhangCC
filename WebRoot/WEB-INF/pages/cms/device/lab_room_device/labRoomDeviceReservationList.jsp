<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript">
		function targetUrl(url){
			window.location=url;
		}
	</script>
</head>
  
<body>
<div id="content_suda">
	<form name="queryForm" action=""></form>
	<table width="100%" style="margin-top:10px; text-align:center" >
		<thead>
			<th width="5%" >序号</th>
			<th width="5%" >申请人</th>
			<th width="10%" >指导老师</th>
			<th width="25%">申请内容</th>
			<th width="10%" >预约日期</th>
			<th width="30%" >使用时间</th>
			<th width="5%" >审核人</th>
			<th width="10%" >状态</th>
		</thead>
		<tbody>
			<c:forEach items="${reservationList}" var="reservation" varStatus="i">
				<tr>
					<td>${i.count }</td>
					<td>${reservation.userByReserveUser.cname }</td>
					<td>${reservation.userByTeacher.cname }</td>
					<td>${reservation.content }</td>
					<td><fmt:formatDate value="${reservation.time.time }" pattern="yyyy-MM-dd"/></td>
					<td>
						<fmt:formatDate value="${reservation.begintime.time }" pattern="yyyy-MM-dd HH:mm:ss"/>
						至<fmt:formatDate value="${reservation.endtime.time }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<c:forEach items="${reservation.labRoomDeviceReservationResults}" var="s"> 
                              ${s.user.cname}<${s.CDictionaryByCTrainingResult.CName }>
                         </c:forEach>
					</td>
					<td>
						${reservation.CAuditResult.CName}
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
	    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/cms/device/reservationList?labRoomDeviceId=${labRoomDeviceId}&currpage=1')" target="_self">首页</a>			    
		<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/cms/device/reservationList?labRoomDeviceId=${labRoomDeviceId}&currpage=${pageModel.previousPage}')" target="_self">上一页</a>
		第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
		<option value="${pageContext.request.contextPath}/cms/device/reservationList?labRoomDeviceId=${labRoomDeviceId}&currpage=${pageModel.currpage}">${pageModel.currpage}</option>
		<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
	    <c:if test="${j.index!=pageModel.currpage}">
	    <option value="${pageContext.request.contextPath}/cms/device/reservationList?labRoomDeviceId=${labRoomDeviceId}&currpage=${j.index}">${j.index}</option>
	    </c:if>
	    </c:forEach></select>页
		<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/cms/device/reservationList?labRoomDeviceId=${labRoomDeviceId}&currpage=${pageModel.nextPage}')" target="_self">下一页</a>
	 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/cms/device/reservationList?labRoomDeviceId=${labRoomDeviceId}&currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
<div style="clear:both"></div>
</body>
</html>
