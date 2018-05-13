<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="bundles.projecttermination-resources"/>

<html >  
<head>
<meta name="decorator" content="iframe"/>  
<title><fmt:message key="html.title"/></title>
<!-- <meta name="decorator" content="iframe"/> -->

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
  
  <!-- 下拉框的样式 -->
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 --> 

<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.gif" />
</head>
<body>
<div class="navigation">
				<div id="navigation">
					<ul>
						 <li><a href="javascript:void(0)">考勤管理</a></li>
	                     <li class="end"><a href="javascript:void(0)">修改-学生考勤</a></li>
					</ul>
				</div>
			</div>
<!-- <div class="right-content"> -->
<!-- <div id="TabbedPanels1" class="TabbedPanels"> -->
	<!--   <div class="TabbedPanelsContentGroup"> -->
		<div class="TabbedPanelsContent">
		     <div class="tool-box">
		 <ul class="top_tittle">
			<li></li>
		</ul>
		<ul id="list-nav" >
			
		</ul>
		<ul class="new_bulid">
			<li class="new_bulid_1"><a onclick="window.history.go(-1)">返回</a></li>
		</ul>
 </div>



<div class="content-box">
	
   
    <form:form action="${pageContext.request.contextPath}/timetable/saveTimetableAttendance" method="POST" modelAttribute="timetableAttendance" target="_parent" >
    
    <table id="listTable" width="50%" cellpadding="0" cellspacing="0" class="tablesorter" >
      	
      	

      	
       <tr align="center" >
		</tr>	
    		 <tr>
    		 <td class="label" valign="top" width="40px">学号：</td>
    		 <td class="label" valign="top" width="40px" align="left">
            ${timetableAttendance.userByUserNumber.username}</td>
    		<form:hidden path="id"/>
			</tr>				
			<tr>
			<td class="label" valign="top" width="40px">姓名：</td>
    		 <td class="label" valign="top" width="40px" align="left">
            ${timetableAttendance.userByUserNumber.cname}
    		</td>
			<form:hidden path="userByUserNumber.cname"/>
			</tr>
			
			
			<tr>
			<td class="label" valign="top" width="80px">考勤：</td>
            <td class="label" valign="top" width="40px"  >
			<form:radiobutton required="true" path="actualAttendance" value="1" />√
			<form:radiobutton required="true" path="actualAttendance" value="0" />×
			</td> 
			
			</tr>
			
      
      <tr rowspan=4>
      <td class="label" valign="top" width="80px" >备注：</td>
     <td>
     <form:select id="memo" path="memo" class="chzn-select" style="width: 300px;height: 50px;padding: 20px">
     <form:option value="">--请选择--</form:option>
     <form:option value="迟到">迟到</form:option>
     <form:option value="旷课">旷课</form:option>
     <form:option value="请假">请假</form:option>
     </form:select>
     </td>
      </tr>
      
			</tbody></table>
			
			
<br>


<center>
<input type="submit" value="提交"/>
<input type="button" onclick="window.history.go(-1)" href="javascript:void(0)" value="取消" />
</center>

</form:form>
</div><!-- </div> -->
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

<!-------------列表结束----------->
</html>