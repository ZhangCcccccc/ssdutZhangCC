<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<script type="text/javascript"> 
   //打开添加实验室软件的弹出框；
   function newUser(){
    $('#searchUser').window('open');
   }
   //提交查询的软件；
   function searchSoftwareSubmit()
   {
       var name = $('#searchUser #user_name').val();
       $('#tt-user').datagrid({
           url: encodeURI('${pageContext.request.contextPath}/searchUser?name='+name+'&iskey='+'${iskey}'),
           title: '添加用户',
           width: 670,
           height: 'auto',
           fitColumns: true,
           rownumbers: true,
           singleSelect: true,
           columns: [[
                   {field: 'usernumber', title: '编号', width: 50},
                   {field: 'cname', title: '姓名', width: 150},
                   {field: 'do', title: '操作', width: 30, align: 'left'}
               ]]
       });
   }
   
      //保存添加的user;
   function addUserSubmit(id,iskey){
   $.ajax({
       type: "POST",    
           url: "${pageContext.request.contextPath}/addNewUser",
           data:{idUser:id,iskey:iskey},
           success:function(data){
             if(data=="ok"){
             alert("用户已添加成功！");
              window.location.href="${pageContext.request.contextPath}/updateAuthority?iskey="+iskey+"&currpage="+1;
             }
           }
      });
   }
</script>
</head>

<body>
<div class="list_tittle">
     <form:form name="form" action="selectUser" method="post" modelAttribute="user">
 <table class="list_form">
    <tr>
        <td>搜索用户:
        		<form:hidden path="userStatus" value="${iskey}"/>
               <form:input id="user_name" path="cname" onkeyup="value=value.replace(/[^\u4E00-\u9FA5\w]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5\w]/g,''))"/>
                <input type="submit" value="查询">&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="window.history.go(-1)">返回</a>
        </td>    
    </tr>
</table>
</form:form>
</div>

<div  class="l_right">
    <div class="list_top">
        <ul class="top_tittle"><fmt:message key="user.list"/></ul>
         <ul class="top_tittle" style="float:right;margin-right:100px;">
                <a href="javascript:void(0)" onclick="newUser();"><fmt:message key="add.user"/></a>
            </ul> 
            <ul class="top_tittle" style="float:right;margin-right:-160px;">
                <a href="javascript:void(0)" onclick="window.history.go(-1)" href="javascript:void(0)"><fmt:message key="return.back"/></a>
            </ul>
    </div>
    
            <table class="tb" cellspacing="0"> 
                <thead>
                    <tr>
                        <th><fmt:message key="user.number"/></th>
                        <th><fmt:message key="user.name"/></th>
                        <th><fmt:message key="user.academy"/></th>
                        <th><fmt:message key="operation"/></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${users}" var="current"  varStatus="i">	
                        <tr>
                            <td>${current.username}</td>
                            <td>${current.cname}</td>
                            <td>${current.schoolAcademy.academyName}</td>
                            <td><a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/deleteUser?userId=${current.id}&authorityId=${iskey}&" onclick='return confirm("确定要删除吗？")'><img src="${pageContext.request.contextPath}/images/icn_trash.png " /></a>&nbsp;&nbsp;</td>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
            <c:choose><c:when test='${newFlag}'>
     <div class="pagination"><%--
     <fmt:message key="currentpage.title"/>当前页为:${page}&nbsp;
	 <fmt:message key="turnto"/>：<input name="currpage" id="page" size="4"/><fmt:message key="page"/><a href="javascript:void(0)" id="turn" target="_self"><img src="${pageContext.request.contextPath}/images/newCss/go.gif" /></a>&nbsp;
   --%><a href="${pageContext.request.contextPath}/updateAuthority?currpage=${pageModel.firstPage}&iskey=${iskey}" target="_self"><fmt:message key="firstpage.title"/></a>				    
	<a href="${pageContext.request.contextPath}/updateAuthority?currpage=${pageModel.previousPage}&iskey=${iskey}" target="_self"><fmt:message key="previouspage.title"/></a>
	 第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;"><option value="${pageContext.request.contextPath}/updateAuthority?currpage=${page}&iskey=${iskey}">${page}</option><c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}"><option value="${pageContext.request.contextPath}/updateAuthority?currpage=${j.index}&iskey=${iskey}">${j.index}</option></c:if></c:forEach></select>页
	 <%--<c:forEach var="currentpage" begin="1" end="${pageModel.totalPage}"><a href="${pageContext.request.contextPath}/userList?currpage=<c:out value="${currentpage}"/>" target="_self"><c:out value="${currentpage}"/></a></c:forEach>
	--%><a href="${pageContext.request.contextPath}/updateAuthority?currpage=${pageModel.nextPage}&iskey=${iskey}" target="_self"><fmt:message key="nextpage.title"/></a>
	<a href="${pageContext.request.contextPath}/updateAuthority?currpage=${pageModel.lastPage}&iskey=${iskey}" target="_self"><fmt:message key="lastpage.title"/></a>
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
       <!--搜索用户-->
<div id="searchUser" class="easyui-window" title="添加用户" closed="true" iconCls="icon-add" style="width:710px;height:400px">
    <table width="100%">
        <tr>
            <td width="14%">请输入查询条件:</td>
            <td width="16%"><input id="user_name" type="text" style="width: 100px"></td>
            <td width="29%"><input type="submit" onclick="searchSoftwareSubmit();"  value="确定" border="0" style="margin-top:1px"></td>
        </tr>
    </table>
    <table id="tt-user" align="center"></table>
</div>
</div>
</body>
</html>


