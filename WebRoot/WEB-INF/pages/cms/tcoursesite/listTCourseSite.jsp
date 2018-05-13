<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  
  <script type="text/javascript">
  //跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  
</script>
</head>
  
<body>
<div id="content_suda">
    <div class="listcourse">
        <ul>
            <c:forEach items="${sites }" var="site">
            <li>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td rowspan="3" width="100px">
                                      <img src="" width="100px" height="120px"/>
                                </td>
				<td height="40px">${site.title}</td>
			</tr>
			<tr>
                                <td height="40px">${site.userByCreatedBy.cname}</td>
                        </tr>
			<tr>
                                <td height="40px"><a href="" target="_blank">教学资源</a></td>
                        </tr>
		</table>
	     </li>
             </c:forEach>
       </ul>
    </div>
    <div style="clear:both"></div>		
		<form:form name="queryForm" action="${pageContext.request.contextPath}/cms/tcoursesite/listTCourseSite?currpage=1" method="post">
			 
		</form:form>
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/cms/tcoursesite/listTCourseSite?currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/cms/tcoursesite/listTCourseSite?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/cms/tcoursesite/listTCourseSite?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/cms/tcoursesite/listTCourseSite?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/cms/tcoursesite/listTCourseSite?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/cms/tcoursesite/listTCourseSite?currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
    <div style="clear:both"></div>
</body>
</html>
