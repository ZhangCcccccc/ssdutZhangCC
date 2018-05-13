<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>


<html>
<head>
<meta name="decorator" content="iframe"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式 -->
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_LeftList.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_Searcher.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/is_SystemUI_Allpages.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<script type="text/javascript">
function submitUser()
{
    var id = ${Id};
    var username = $("#username").val();
    
	$.ajax({
		type: "GET",
		url: "${pageContext.request.contextPath}/userAuthorityMange/saveUserAuthority",
		data: {id:id,username:username},
		success: function(data){
			if(data == 'ok')
			{
				parent.$('#doSearchStudents').window('close');
				parent.window.location.reload();
			}
		}
	});



	doucment.form.submit();
	
}
</script>
</head>
<div class="navigation">
				<div id="navigation">
					<ul>
						<li><a href="javascript:void(0)">人员管理</a></li>
						<li class="end"><a href="javascript:void(0)">新建</a>
						&nbsp;&nbsp;用户组:[${authority.authorityName}]${authority.cname}
						</li>
					</ul>
				</div>
			</div>

<!-- 查询表单 -->


<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
			<div class="title">
				<div id="title">新建用户组:[${authority.authorityName}]${authority.cname}</div>
			</div>   	
    		<!-- 实验室列表 -->
    		<!-- <div class="content-box">   --> 		
            <form:form id="form" modelAttribute="listUser">
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
				<tr>
					<td width="350px">工号：</td>
					<td>
			 <form:select class="chzn-select"  path="username" id="username" required="true" cssStyle="width:200px" >
	  <c:forEach items="${userMap}" var="current1"  varStatus="i">	
	    <form:option value="${current1.username}" >学号：${current1.username} 姓名：${current1.cname}</form:option>
	  </c:forEach>
   </form:select>
				    				    				            
			</td> 
			
			    </tr>

					
				</tbody>
			</table>    
      <input class="btn" type="button" onclick="submitUser();" value="确定" />
            <button class="btn" onclick="window.history.go(-1)" href="javascript:void(0)">取消</button>
          
          <!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select'           : {search_contains:true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
</script>
<!-- 下拉框的js -->
          
		</form:form>
            
           
</div>
</div>
</div>
</div>
</body>
</html>