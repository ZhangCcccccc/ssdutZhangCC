<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<head>
<meta name="decorator" content="iframe"/>
<%--<link href="${pageContext.request.contextPath}/css/newRoom.css" rel="stylesheet" type="text/css" />--%>
<script type="text/javascript">
$(function(){
	$(".datebox :text").attr("readonly","readonly"); 
	$("input[type=text]").validatebox();
});

$.extend($.fn.validatebox.defaults.rules,{
	  mobile: {//value值为文本框中的值
	        validator: function (value) {
	            var reg = /^1[3|4|5|8|9]\d{9}$/;
	            return reg.test(value);
	        },
	        message: '输入手机号码格式不准确.'
	    },
});
</script>
</head>
<div class="l_right">
    <div class="list_top">
         <ul class="top_tittle"><fmt:message key="user.manage"/></ul>
    </div>  
 <form:form action="${pageContext.request.contextPath}/saveUpdateUser?currpage=${page}&" method="POST" modelAttribute="user">
   <table class="new">
    <!--  <tr><td><font color=red>系统编号*:</font><input id="className" value="${aa}"/></td></tr>-->
	<tr><td><font color=red><fmt:message key="user.number"/>*:</font><form:hidden id="id" path="username" required="true" readonly="true"/>
	<input value="${num}" disabled/>
			<form:hidden path="password"/>
			<form:hidden path="id"/>
	</td></tr>
    <tr>
     <td><fmt:message key="user.name"/><font color=red>*:</font>
			<form:hidden id="name" path="cname" readonly="true" required="true"/>
			<input value="${name}" disabled/>
     </td>
    </tr>
    <tr>
    <td><fmt:message key="user.cposition.name"/>:<form:hidden path="userRole" readonly="true"/><input value="${role}" disabled/>
    </td>
    </tr>
    <tr>
    <td><fmt:message key="user.academy"/>:<form:select path="schoolAcademy.id" items="${academys}"/>
    </td>
    </tr>
    <tr>
    <td><fmt:message key="last.login.time"/>:<input class="easyui-datebox" name="lastLogin" value="<fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${user.lastLogin.time}"/>"/>
    </td>
    </tr>
      <tr>
    <td><fmt:message key="user.phone"/>:<input name="telephone" class="easyui-validatebox" value="${user.telephone}" required="true" validType="mobile"/><!--  invalidMessage="不能超过11个字符！" -->
    </td>
    </tr>
      <tr>
    <td><fmt:message key="user.email"/>:<input class="easyui-validatebox" name="email" value="${user.email}" required="true" validType="email"/>
    </td>
    </tr>
    <tr>
    <td><fmt:message key="user.major"/>:<form:select path="schoolMajor.id" items="${majors}"/>
    </td>
    </tr>
    <tr>
    <td><fmt:message key="user.class"/>:<form:select path="schoolClasses.id" items="${classes}"/>
    </td>
    </tr>
    <tr><td>职称:<form:select path="majorDirection.id" items="${majorDirects}"/></td></tr>
    <tr><td>学籍状态:<form:select path="CEnrollmentStatus.id" items="${cEnrollmentStatus}"/></td></tr>
    <tr><td>学生类别:<form:select path="CStudentType.id" items="${cStudnetTypes}"/></td></tr>
   </table>
    <div class="moudle_footer">
        <div class="submit_link">
            <input type="submit" value="提交" class="alt_btn">
            <script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId:'save', event:'onclick'}));</script>
            <input type="submit" value="返回" onclick="window.history.go(-1)">
        </div>
    </div>
   </form:form>
</div>
