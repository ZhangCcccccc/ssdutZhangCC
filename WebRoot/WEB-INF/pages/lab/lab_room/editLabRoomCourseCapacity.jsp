<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  
  <!-- 下拉框的样式 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 -->
  
  <script type="text/javascript">
  $(function() {
    $("[id^='lwCategoryExpert']").click(function(){
    	clickCheckbox();
    });
});

   function clickCheckbox()
  {
	var i=0;
	
	$("[id^='lwCategoryExpert']").each(function(){
		if($(this).attr('checked'))
		{
			i++;
		}
	});

	if(i > 2)
	{
	    $("[id^='lwCategoryExpert']").removeAttr("checked");
		alert('专家类别不能多于2个！');
	}
  }
  </script>
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">实验室容量与课程的关系</a></li>
			<li class="end"><a href="javascript:void(0)">新建</a></li>
			
		</ul>
	</div>
  </div>
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
  <div class="title">
      <div id="title">新建实验室容量与课程的关系</div>
  </div>
  <form:form action="${pageContext.request.contextPath}/labRoom/saveLabRoomCourseCapacity" method="POST" modelAttribute="labRoomCourseCapacity">
  <div class="new-classroom">
<form:hidden path="id"/>
  <fieldset>
    <label>实验室：</label>
    <form:select path="labRoom.id" class="chzn-select">
		<form:option value="">请选择</form:option>
		<c:forEach  items="${listLabRooms}" var="curr">
			<form:option value="${curr.id}">${curr.labRoomName}[${curr.labRoomAddress}]</form:option>
		</c:forEach>
      <%-- <form:options items="${listLabRooms}" itemLabel="labRoomName" itemValue="id"/> --%>
    </form:select>
  </fieldset>
  <fieldset>
    <label>课程：</label>
    <form:select path="schoolCourseDetail.courseDetailNo" class="chzn-select">
		<form:option value="">请选择</form:option>
		<c:forEach items="${listSchoolCourseDetails}" var="curr">
			<form:option value="${curr.courseDetailNo}">${curr.courseName}[${curr.courseDetailNo}]</form:option>
		</c:forEach>
      <%-- <form:options items="${listSchoolCourseDetails}" itemLabel="courseName" itemValue="courseDetailNo"/> --%>
    </form:select>
  </fieldset>
  <fieldset>
    <label>容量：</label>
    <form:input path="capacity" required="true"/>
  </fieldset>
  </div>
  <div class="moudle_footer">
      <div class="submit_link">
      <input class="btn" id="save" type="submit" value="确定">
      <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
      </div>
  </div>
  </form:form>
  <!-- 下拉框的js -->
	<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		    var config = {
		      '.chzn-select': {search_contains : true},
		      '.chzn-select-deselect'  : {allow_single_deselect:true},
		      '.chzn-select-no-single' : {disable_search_threshold:10},
		      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
		      '.chzn-select-width'     : {width:"95%"}
		    }
		    for (var selector in config) {
		      $(selector).chosen(config[selector]);
		    }
		</script>
	<!-- 下拉框的js -->
  </div>
  </div>
  </div>
  </div>
  </div>
  
</body>
</html>
