<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/iconFont.css"	rel="stylesheet">

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

<div class="iStyle_RightInner">
<div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">教学管理</a>
			</li>
			<li class="end">
				<mytag:JspSecurity realm="update" menu="courseSite">
					<a href="${pageContext.request.contextPath}/teaching/coursesite/listCourseSites?currpage=1">站点管理</a>
				</mytag:JspSecurity>
			</li>
		</ul>
	</div>
</div>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="tool-box">
<form:form name="form" action="${pageContext.request.contextPath}/teaching/coursesite/listCourseSites?currpage=1" method="post" modelAttribute="tCourseSite">
<table class="list_form">
<tr>
   	<td>搜索课程:
   		<mytag:JspSecurity realm="check" menu="courseSite">
   			<%-- <form:input id="schoolCourseInfo.courseNumber" path="schoolCourseInfo.courseNumber" onkeyup="value=value.replace(/[^\u4E00-\u9FA5\w]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5\w]/g,''))"/> --%>
   			<form:input id="title" path="title" placeholder="输入课程名称" />
   			<input type="submit" value="查询">&nbsp;&nbsp;&nbsp;&nbsp;
   		</mytag:JspSecurity>
   		<mytag:JspSecurity realm="add" menu="courseSite">
   			<a  class="btn btn-common"  href="${pageContext.request.contextPath}/teaching/coursesite/newCourseSite?id=-1" >新建选课组</a>
		</mytag:JspSecurity>
   </td>    
</tr>
</table>
</form:form>
</div>
<div class="content-box">
<div class="title">教务排课管理列表</div>
<table class="tb" cellspacing="0" id="my_show"> 
<thead>
<tr>
    <th>序号</th>
    <th>课程编号</th>
    <th>课程名称</th>
    <th>课程站点编号</th>
    <th>课程站点名称</th>
    <th>所属学期</th>
    <th>授课教师</th>
    <th>学生名单</th>
    <th>操作</th>
</tr>
</thead>
<tbody>
<c:forEach items="${tCourseSites}" var="current"  varStatus="i">	
<tr>
   	<td>${i.count}</td>
	<td>${current.schoolCourseInfo.courseNumber}</td>
	<td>${current.schoolCourseInfo.courseName}</td>
	<td>${current.siteCode}</td>
	<td>${current.title}</td>
	<td>${current.schoolTerm.termName}</td>
	<td>${current.userByCreatedBy.cname}</td>
	<td>
   		<mytag:JspSecurity realm="update" menu="courseSite">
  			 名单：<a href='${pageContext.request.contextPath}/teaching/coursesite/ListCourseStudents?id=${current.id }'>${current.TCourseSiteUsers.size()}</a>
		</mytag:JspSecurity>
	</td>
	<td>
		<mytag:JspSecurity realm="delete" menu="courseSite">
			<a href='${pageContext.request.contextPath}/teaching/coursesite/deleteTCourseSite?id=${current.id }'>删除</a>&nbsp;
		</mytag:JspSecurity>
		<mytag:JspSecurity realm="update" menu="courseSite">
			<a href='${pageContext.request.contextPath}/teaching/coursesite/newCourseSite?id=${current.id }'>编辑</a>
		</mytag:JspSecurity>
	</td>
</tr>
</c:forEach>
</tbody>
</table>
<c:choose><c:when test='${newFlag}'>
<mytag:JspSecurity realm="check" menu="courseSite">
<a href="${pageContext.request.contextPath}/teaching/coursesite/listCourseSites?currpage=${pageModel.firstPage}" target="_self"><fmt:message key="firstpage.title"/></a>				    
<a href="${pageContext.request.contextPath}/teaching/coursesite/listCourseSites?currpage=${pageModel.previousPage}" target="_self"><fmt:message key="previouspage.title"/></a>
 第
<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;"><option value="${pageContext.request.contextPath}/teaching/coursesite/listCourseSites?currpage=${page}">${page}</option>
    <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
        <c:if test="${j.index!=page}">
        <option value="${pageContext.request.contextPath}/teaching/coursesite/listCourseSites?currpage=${j.index}">${j.index}</option>
        </c:if>
    </c:forEach>
</select>页
<a href="${pageContext.request.contextPath}/teaching/coursesite/listCourseSites?currpage=${pageModel.nextPage}" target="_self"><fmt:message key="nextpage.title"/></a>
<a href="${pageContext.request.contextPath}/teaching/coursesite/listCourseSites?currpage=${pageModel.lastPage}" target="_self"><fmt:message key="lastpage.title"/></a>
</mytag:JspSecurity>

</div>

<div class="pagination_desc" style="float: left">
   <fmt:message key="total"/><strong>${totalRecords}</strong> 
   <fmt:message key="record"/><strong>
   <fmt:message key="totalpage.title"/>:${pageModel.totalPage}&nbsp;</strong>

</div>
<
</c:when>
<c:otherwise>

</c:otherwise>
</c:choose>    
</div>
</div>
</div>
</div>
</div>
</div>            
</div>
<div id="mediaDisplay" class="easyui-window" title="学生列表" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width: 900px; height:650px;">
</div>

</body>
</html>
