<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.academy-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
</head>

<body>
<%--<div class="list_tittle">
     <form:form action="selectSchoolAcademy" method="post" modelAttribute="schoolAcademy">
 <table class="list_form">
    <tr>
        <td>搜索学院:
               <input name="academyNumber">
                <input type="submit" value="查询">&nbsp;&nbsp;&nbsp;&nbsp;
                <a onclick="window.history.go(-1)">返回</a>
        </td>    
    </tr>
</table>
</form:form>
</div>
--%><div class="l_right">
    <div class="list_top">
        <ul class="top_tittle">数据同步</ul> 
         <ul  class="new_bulid"><li class="new_bulid_1"><a href="javascript:void(0)" onclick="window.history.go(-1)">返回</a></li></ul>   
      <%--<ul  class="new_bulid"> <li class="new_bulid_1"><a href="${pageContext.request.contextPath}/exportSchoolAcademy">导出</a></li></ul>     
    --%></div>
            <table class="tb"> 
                <tr>
                	<th>需要同步的数据源</th>
                	<th>操作</th>
                </tr>
                 <tr>
                	<td>学院</td>
                	<td><input type="button" value="同步数据" onclick=""/></td>
                </tr>
                 <tr>
                	<td>部门</td>
                	<td><input type="button" value="同步数据" onclick=""/></td>
                </tr>
                 <tr>
                	<td>学生和教职员工</td>
                	<td><input type="button" value="同步数据" onclick=""/></td>
                </tr>
                 <tr>
                	<td>设备</td>
                	<td><input type="button" value="同步数据" onclick=""/></td>
                </tr>
                 <tr>
                	<td>学期</td>
                	<td><input type="button" value="同步数据" onclick=""/></td>
                </tr>
                 <tr>
                	<td>班级</td>
                	<td><input type="button" value="同步数据" onclick=""/></td>
                </tr>
                 <tr>
                	<td>课表</td>
                	<td><input type="button" value="同步数据" onclick=""/></td>
                </tr>
                <tr>
                	<td>专业</td>
                	<td><input type="button" value="同步数据" onclick=""/></td>
                </tr>
                 <tr>
                	<td>课程安排</td>
                	<td><input type="button" value="同步数据" onclick=""/></td>
                </tr>
                 
            </table>
<%--<div class="pagination_desc" style="float: left">
       <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
</div>
--%></div>
</body>
</html>

