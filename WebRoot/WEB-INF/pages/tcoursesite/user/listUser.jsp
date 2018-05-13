<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />

<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/operation/operationoutline.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	$("#print").click(function(){
	$("#my_show").jqprint();
	});
});	
</script>
</script> 
<script type="text/javascript">
function exportSelect(){
	 document.form.action="exportExcelSelectUser";
	 document.form.submit();
	}
</script>
<script type="text/javascript">
function exportAll(url){
	window.location.href="${pageContext.request.contextPath}/tcoursesite/exportAll?currpage=${page}";
	}
</script>
</head>
<body>
<!-- 导航栏 -->
	<div class="lest_content " >
		<div class="left_nav">
        	<div class="left_user_box">
                <img src="${pageContext.request.contextPath}/images/index/user.png"/>
            </div>
            <div class="left_nav_box">
                <ul class="left_nav_list">
	                <sec:authorize ifAnyGranted="ROLE_TEACHER">
	                    <li <c:if test="${select eq 'tcourse' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=1">选课组管理</a>
	                    </li>
	                </sec:authorize>
	                <sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
	                    <li <c:if test="${select eq 'authority' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listUserAuthority">权限管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'user' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listUser?currpage=1">用户管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'term' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listTerm?currpage=1">学期管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'visualization' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/visualization/roomList?page=1">可视化管理</a>
	                    </li>
		            </sec:authorize>
                </ul>
            </div>  
            <div class="power2" style="display: block;">
                Power by <a href="http://www.gvsun.com" target="_blank">Gvsun</a>
            </div>
        </div>
<!-- 导航栏 -->
<div id="TabbedPanels1" class="TabbedPanels" style="float:right;width:80%">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent0">
		<!-- 查询、导出、打印 -->
		<div class="tool-box">
		    <form:form name="form" method="Post" action="${pageContext.request.contextPath}/tcoursesite/listUser?currpage=1" modelAttribute="formUser">
			<ul>
			    <li> 工号：
			    <form:input name="username" path="username"/></li>
			    <li> 姓名：
			    <form:input name="cname" path="cname"/></li>
			   	<li> 学院：
				<form:select path="schoolAcademy.academyNumber" class="chzn-select">
					<form:option value="-1">请选择</form:option>
						 <c:forEach items="${academys}" var="a">
							<form:option value="${a.academyNumber}">${a.academyName}</form:option>
						</c:forEach>
				</form:select>
			    </li>
				<li><input type="submit" value="查询"></li>
				<li><a href="${pageContext.request.contextPath}/tcoursesite/listUser?currpage=1"><input type="button" value="取消查询"></a></li>
				<li><input type="button" value="导出" onclick="exportAll()"></li>
				<li><input type="button" value="打印" id="print"></li>
				<li><a href="${pageContext.request.contextPath}/tcoursesite/newUser"><input type="button" value="新建"></a></li>
			</ul>
			</form:form>
		</div>
<!-- 查询、导出、打印 -->

		<div class="content-box">
				<div class="title">用户管理</div>
            <table  id="my_show"> 
                <thead>
                    <tr>                   
                        <th>序号</th>
                        <th>用户工号</th>
                        <th>用户姓名</th>
                        <th>学院</th>
                        <th>用户身份</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${userMap}" var="current"  varStatus="i">	
                        <tr>
                           <td>${i.count}</td>
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
                           <td><a class="btn btn-normal" href="${pageContext.request.contextPath}/tcoursesite/userDetailInfo?num=${current.username}&">信息展示</a></td>
                        </tr>
                        </c:forEach>
                </tbody>
               </table>
<!-- 分页导航 -->
<tr> 
    <td colspan="6" align="center" >
                 总记录:<strong>${totalRecords}</strong>条&nbsp;
                 总页数:<strong>${pageModel.totalPage}</strong>页 <input type="hidden" value="${pageModel.lastPage}" id="totalpage" />&nbsp;
                 当前页:第<strong>${pageModel.currpage}</strong>页 <input type="hidden" value="${pageModel.currpage}" id="currpage" />&nbsp;
		   <a href="${pageContext.request.contextPath}/tcoursesite/listUser?currpage=${pageModel.firstPage}" target="_self"> 首页</a> 	   
		   <a href="${pageContext.request.contextPath}/tcoursesite/listUser?currpage=${pageModel.previousPage}"  target="_self">上一页 </a> 
		   <a href="${pageContext.request.contextPath}/tcoursesite/listUser?currpage=${pageModel.nextPage}"  target="_self">下一页 </a> 
		   <a href="${pageContext.request.contextPath}/tcoursesite/listUser?currpage=${pageModel.lastPage}" target="_self">末页 </a>&nbsp;
		   <!-- 跳转到选择/输入的页面 -->
		   第 <select  onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
		   <option value="${pageContext.request.contextPath}/tcoursesite/listUser?currpage=${page}">${page}</option>
		   <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
           <c:if test="${j.index!=page}">
           <option value="${pageContext.request.contextPath}/tcoursesite/listUser?currpage=${j.index}">${j.index}</option>
           </c:if>
           </c:forEach>
           </select> 页
    </td>
</tr>
<!-- 分页导航 -->

</div>
</div>
</div>
</div>
</div>
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
</body>
</html>

