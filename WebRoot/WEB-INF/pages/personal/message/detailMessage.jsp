<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta name="decorator" content="iframe"/>
 <link href="${pageContext.request.contextPath}/css/newRoom.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div  class="l_right">
    <div class="list_top">
        <ul class="top_tittle">消息查看</ul>
    </div>
    <div id="userInfo">
              <table class="tb" cellspacing="0">
				<tr>
					<td>消息标题</td>
					<td>${message.messageTitle}</td>
				</tr>
				<tr>
					<td>消息来源</td>
					<td>${message.userByMessageFrom.cname}</td>
				</tr>
				<tr>
					<td>创建时间</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" type="both" value="${message.createdAt.time}"/></td>
				</tr>
				<tr>
					<td>消息内容</td>
					<td>${message.messageContent}</td>
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


