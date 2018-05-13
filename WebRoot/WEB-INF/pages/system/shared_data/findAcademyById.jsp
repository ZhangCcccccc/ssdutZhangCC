<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta name="decorator" content="iframe"/>
 <link href="${pageContext.request.contextPath}/css/newRoom.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div  class="l_right">
    <div class="list_top">
        <ul class="top_tittle">返回</ul>
    </div>
    <div id="userInfo">
              <table class="tb" cellspacing="0">
				<tr>
					<td>学院编号：</td>
					<td>${schoolAcademy.academyNumber}</td>
				</tr>
				<tr>
					<td>学院名称：</td>
					<td>${schoolAcademy.academyName}</td>
				</tr>
				<tr>
					<td>是否有效：</td>
					<td>${schoolAcademy.isVaild}</td>
				</tr>
				<tr>
					<td>学院类型：</td>
					<td>${schoolAcademy.academyType}</td>
				</tr>
				<tr>
					<td>创建时间：</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${schoolAcademy.createdAt.time}"/></td>
				</tr>
				<tr>
					<td>更新时间：</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${schoolAcademy.updatedAt.time}"/></td>
				</tr>
            </table>
          </div>
            	<div class="moudle_footer">
        			<ul class="top_tittle" style="float:right;margin-right:40px;">
        			<a onclick="window.history.go(-1)" href="javascript:void(0)">返回</a></ul>
    			</div>
    				
    				
</div>
</body>
</html>


