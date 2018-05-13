<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
 <%--   	<link href="${pageContext.request.contextPath}/css/room/lmsInfor.css" rel="stylesheet" type="text/css" />  --%>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
 <script type="text/javascript">
	$(document).ready(function(){
	$("#print").click(function(){
	$("#my_show").jqprint();
	});
	
	
	 $("#turn").click(function(){
	 	   var page=${pageModel.totalPage};
	       var id=$("#page").val();
	        if(id.length==0){
	         alert("请输入数字！");
	         }else{
	         reg=/^[-+]?\d*$/;
	          if(!reg.test(id)){    
	             alert("对不起，您输入的整数类型格式不正确!");//请将“整数类型”要换成你要验证的那个属性名称！    
	           }else{
	               if(id<=page){
	             	  window.location.href="${pageContext.request.contextPath}/userList?currpage="+id;
	                   }else{
	                 	  alert("对不起，您输入的整数不正确!");
	                       }
	           }    
	         }
	 	   });
	});
	
	
</script> 
<script type="text/javascript">
function exportSelect(){
	 document.form.action="exportExcalSelectUser";
	 document.form.submit();
	}
</script>
</head>
<body>
<div class="list_tittle">
     <form:form name="form" action="selectUserList" method="post" modelAttribute="user">
 <table class="list_form">
    <tr>
        <td>搜索用户:
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
        <ul class="top_tittle"><fmt:message key="user.manage"/></ul>
        <ul class="new_bulid">
                <li class="new_bulid_1"><a id="print" href="javascript:void(0)">打印</a></li>
            </ul> 
             <%--<ul  class="new_bulid"><li class="new_bulid_1"><a href="javascript:void(0)" onclick="window.history.go(-1)">返回</a></li></ul>   
            --%><ul class="new_bulid">
                <li class="new_bulid_1">
            <c:choose>
			<c:when test="${newFlag}">
			<td>
				<!-- 导出所有软件 -->
				<a href="${pageContext.request.contextPath}/exportUserInfo">导出</a>  
			</td>
			</c:when>
			<c:otherwise>
			<td>
				<!-- 其他情况下，导出查询出的软件 -->
				<%-- <input type="button"  onclick="exportSelect();" value="导出"/>--%>
				<a href="javascript:void(0)" onclick="exportSelect();">导出</a> 
			</td>
			</c:otherwise>
			</c:choose></li></ul>
    </div>
            <table class="tb" cellspacing="0" id="my_show"> 
                <thead>
                    <tr>
                        <th><fmt:message key="user.number"/></th>
                        <th><fmt:message key="user.name"/></th>
                        <th><fmt:message key="user.academy"/></th>
                        <th><fmt:message key="user.cposition.name"/></th>
                        <th><fmt:message key="operation"/></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${user1}" var="current"  varStatus="i">	
                        <tr>
                           <td>${current.username}</td>
                           <td>${current.cname}</td>
                           <td>${current.schoolAcademy.academyName}</td>
                           <td>
                           <c:choose>
                           		<c:when test="${current.userRole==0}">
                           			学生
                           		</c:when>
                           		<c:otherwise>
                           			教师
                           		</c:otherwise>
                           </c:choose>
                          </td>
                           <td><a href="${pageContext.request.contextPath}/userDetailInfo?id=${current.id}&">信息展示</a></td>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
        <c:choose><c:when test='${newFlag}'>
     <div class="pagination"><%--
     <fmt:message key="currentpage.title"/>当前页为:${page}&nbsp;
	 <fmt:message key="turnto"/>：<input name="currpage" id="page" size="4"/><fmt:message key="page"/><a href="javascript:void(0)" id="turn" target="_self"><img src="${pageContext.request.contextPath}/images/newCss/go.gif" /></a>&nbsp;
   --%><a href="${pageContext.request.contextPath}/userList?currpage=${pageModel.firstPage}" target="_self"><fmt:message key="firstpage.title"/></a>				    
	<a href="${pageContext.request.contextPath}/userList?currpage=${pageModel.previousPage}" target="_self"><fmt:message key="previouspage.title"/></a>
	 第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;"><option value="${pageContext.request.contextPath}/userList?currpage=${page}">${page}</option><c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}"><option value="${pageContext.request.contextPath}/userList?currpage=${j.index}">${j.index}</option></c:if></c:forEach></select>页
	 <%--<c:forEach var="currentpage" begin="1" end="${pageModel.totalPage}"><a href="${pageContext.request.contextPath}/userList?currpage=<c:out value="${currentpage}"/>" target="_self"><c:out value="${currentpage}"/></a></c:forEach>
	--%><a href="${pageContext.request.contextPath}/userList?currpage=${pageModel.nextPage}" target="_self"><fmt:message key="nextpage.title"/></a>
	<a href="${pageContext.request.contextPath}/userList?currpage=${pageModel.lastPage}" target="_self"><fmt:message key="lastpage.title"/></a>
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

