<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.authority-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
 <%--   	<link href="${pageContext.request.contextPath}/css/room/lmsInfor.css" rel="stylesheet" type="text/css" />  --%>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
  <script type="text/javascript">
	$(document).ready(function(){
	$("#print").click(function(){
	$("#my_show").jqprint();
	})
	});
</script> 
</head>
<body>
<div  class="l_right">
    <div class="list_top">
        <ul class="top_tittle"><fmt:message key="user"/></ul>
        <ul class="new_bulid">
                <li class="new_bulid_1"><a id="print" href="javascript:void(0)">打印</a></li>
            </ul>
         <ul  class="new_bulid"><li class="new_bulid_1"><a href="javascript:void(0)" onclick="window.history.go(-1)">返回</a></li></ul>    
    </div>
            <table class="tb" cellspacing="0" id="my_show"> 
                <thead>
                    <tr>
                        <th>ID</th>
                        <%--<th><fmt:message key="user.group.identification"/></th>
                        --%><th><fmt:message key="user.group.name"/></th>
                        <%--<th><fmt:message key="user.group.number"/></th>
                        --%><th><fmt:message key="operation"/></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${authorities}" var="current"  varStatus="i">	
                        <tr>
                            <td>${current.id}</td>
                            <%--<td>${current.authorityName}</td>
                            --%><td>${current.cname}</td>
                            <%--<td></td>
                            --%><td><a href="${pageContext.request.contextPath}/updateAuthority?iskey=${current.id}&currpage=1"><img src="${pageContext.request.contextPath}/images/icn_search.png" /></a>&nbsp;&nbsp;</td>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
</div>
</body>
</html>

