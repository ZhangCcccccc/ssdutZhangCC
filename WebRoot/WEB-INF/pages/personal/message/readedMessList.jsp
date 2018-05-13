<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipmentlend-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(function(){
    $("#myMess").click(function(){
     window.location.href="${pageContext.request.contextPath}/messageList?currpage=1";
    });
     $("#readedMess").click(function(){
     window.location.href="${pageContext.request.contextPath}/readedMessList?currpage=1";
    });
     $("#unreadMess").click(function(){
         window.location.href="${pageContext.request.contextPath}/unreadMessList?currpage=1";
        });
	});
</script>
</head>
<body>
<div>
    <ul id="c">
        <li id="cc" class="sortbtn" style="border-left:1px solid #ccc"><a href="javascript:void(0)" id="myMess">所有消息</a></li>
        <li id="lc" class="sortbtn"><a href="javascript:void(0)" id="readedMess">已读消息</a></li>
        <li id="sc" class="sortbtn"><a href="javascript:void(0)" id="unreadMess">未读消息</a></li>
        <li id="sc" class="sortbtn"><a href="${pageContext.request.contextPath}/test" id="return">返回</a></li>
    </ul>
</div>
<div class="l_right">
    <div class="list_top">
        <ul class="top_tittle">已读消息</ul>     
    </div>
            <table class="tb"> 
                <thead>
                    <tr>
                    	<%--<th></th>
                        --%><th>标题</th>
                        <th>来自</th>
                        <th>时间</th>
                        <%--<th>操作</th>
                    --%></tr>
                </thead>
                <tbody>
                	<c:forEach items="${messages}" var="current"  varStatus="i">	
                        <tr>
                        	<%--<td><input type="radio"></td>
                            --%><td>${current.messageTitle}</td>
                            <td>${current.userByMessageFrom.username}</td>
                            <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" type="both" value="${current.createdAt.time}"/></td>
                            <%--<td><a href="${pageContext.request.contextPath}/detailMessage?idkey=${current.id}&"><img src="${pageContext.request.contextPath}/images/icn_search.png" /></a></td>
                        --%></tr>
                        </c:forEach>
                </tbody>
            </table>
<c:choose><c:when test='${newFlag}'>
        <div class="pagination">
   <a href="${pageContext.request.contextPath}/readedMessList?currpage=${pageModel.firstPage}" target="_self"><fmt:message key="firstpage.title"/></a>				    
	<a href="${pageContext.request.contextPath}/readedMessList?currpage=${pageModel.previousPage}" target="_self"><fmt:message key="previouspage.title"/></a>
		<%--<c:forEach var="currentpage" begin="1" end="${pageModel.totalPage}"><a href="${pageContext.request.contextPath}/messageList?currpage=<c:out value="${currentpage}"/>" target="_self"><c:out value="${currentpage}"/></a></c:forEach>--%>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;"><option value="${pageContext.request.contextPath}/readedMessList?currpage=${page}">${page}</option><c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}"><option value="${pageContext.request.contextPath}/readedMessList?currpage=${j.index}">${j.index}</option></c:if></c:forEach></select>页
	<a href="${pageContext.request.contextPath}/readedMessList?currpage=${pageModel.nextPage}" target="_self"><fmt:message key="nextpage.title"/></a>
	<a href="${pageContext.request.contextPath}/readedMessList?currpage=${pageModel.lastPage}" target="_self"><fmt:message key="lastpage.title"/></a>
    </div>
    <div class="pagination_desc" style="float: left">
       <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
    <strong><fmt:message key="totalpage.title"/>:${pageModel.totalPage}&nbsp;</strong>
</div>
</c:when><c:otherwise>
<div class="pagination_desc" style="float: left">
       <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
</div>
</c:otherwise></c:choose> 
</div>
</body>
</html>

