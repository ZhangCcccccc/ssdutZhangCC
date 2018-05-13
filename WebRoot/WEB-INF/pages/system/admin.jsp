<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
    <div class="content-box">
        <div>
            <div class="content">
                <ul class="guide">
                    <li><a href="${pageContext.request.contextPath}/admin/newArticle"><img src="${pageContext.request.contextPath}/images/paper.png" /><span>新建文章</span></a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/article?page=1"><img src="${pageContext.request.contextPath}/images/paper-manage.png" /><span>文章管理</span></a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/channel"><img src="${pageContext.request.contextPath}/images/nav.png" /><span>栏目管理</span></a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/site"><img src="${pageContext.request.contextPath}/images/tool.png" /><span>网站设置</span></a></li>
                    <%-- <li><a href="${pageContext.request.contextPath}/admin/usersList"><img src="${pageContext.request.contextPath}/images/user-manage.png" /><span>用户管理</span></a></li> --%>
                </ul>
            </div>
        </div>
    </div>
  
</body>
</html>