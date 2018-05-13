<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.major-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
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
<div class="list_tittle">
     <form:form action="selectSchoolMajor" method="post" modelAttribute="schoolMajor">
 <table class="list_form">
    <tr>
        <td>搜索专业:
               <input name="majorNumber" onkeyup="value=value.replace(/[^\u4E00-\u9FA5\w]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5\w]/g,''))">
                <input type="submit" value="查询">&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="window.history.go(-1)">返回</a>
        </td>    
    </tr>
</table>
</form:form>
</div>
<div class="l_right">
    <div class="list_top">
        <ul class="top_tittle"><fmt:message key="major.list"/></ul> 
         <ul  class="new_bulid"><li class="new_bulid_1"><a href="javascript:void(0)" onclick="window.history.go(-1)">返回</a></li></ul>   
      <ul  class="new_bulid"> <li class="new_bulid_1"><a href="${pageContext.request.contextPath}/exportSchoolMajor">导出</a></li></ul>     
    	<ul class="new_bulid">
                <li class="new_bulid_1"><a id="print" href="javascript:void(0)">打印</a></li>
            </ul>  
    </div>
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                        <th><fmt:message key="major.number.title"/></th>
                        <th><fmt:message key="major.name.title"/></th>
                        <th><fmt:message key="academy.name"/></th>
                        <th><fmt:message key="operation"/></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${schoolMajors}" var="current"  varStatus="i">	
                        <tr>
                            <td>${current.majorNumber}</td>
                            <td>${current.majorName}</td>
                            <td>${current.schoolAcademy.academyName}</td>
                            <td><a href="${pageContext.request.contextPath}/findSchoolMajorById?idkey=${current.id}">详细</a></td>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
<c:choose><c:when test='${newFlag}'>
        <div class="pagination">
   <a href="${pageContext.request.contextPath}/schoolMajorList?currpage=${pageModel.firstPage}" target="_self"><fmt:message key="firstpage.title"/></a>				    
	<a href="${pageContext.request.contextPath}/schoolMajorList?currpage=${pageModel.previousPage}" target="_self"><fmt:message key="previouspage.title"/></a>
	 <%--<c:forEach var="currentpage" begin="1" end="${pageModel.totalPage}"><a href="${pageContext.request.contextPath}/schoolMajorList?currpage=<c:out value="${currentpage}"/>" target="_self"><c:out value="${currentpage}"/></a></c:forEach>
	--%>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;"><option value="${pageContext.request.contextPath}/schoolMajorList?currpage=${page}">${page}</option><c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}"><option value="${pageContext.request.contextPath}/schoolMajorList?currpage=${j.index}">${j.index}</option></c:if></c:forEach></select>页
	<a href="${pageContext.request.contextPath}/schoolMajorList?currpage=${pageModel.nextPage}" target="_self"><fmt:message key="nextpage.title"/></a>
	<a href="${pageContext.request.contextPath}/schoolMajorList?currpage=${pageModel.lastPage}" target="_self"><fmt:message key="lastpage.title"/></a>
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

