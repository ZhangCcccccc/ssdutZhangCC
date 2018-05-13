<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta name="decorator" content="iframe"/>
    <title>报表参数</title>
    <!-- 下拉框的样式 -->
	<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/style.css" /> --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
	<!-- 下拉的样式结束 -->
    
  </head>
  
  <body>
    <div class="right-content">
	<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">
				<div id="title">报表参数</div>
				
				</div> 
			<form:form action="${pageContext.request.contextPath}/report/saveReportParameter" method="POST" modelAttribute="reportParameter">
				<div class="new-classroom">
					  <fieldset>
						     <label> 学院：</label>
							<form:select class="chzn-select" path="schoolAcademy.academyNumber">
							<form:options items="${academies}" itemLabel="academyName" itemValue="academyNumber"/>
							</form:select>
							<form:hidden path="id"/>
					  </fieldset>
					  <fieldset>
						     <label> 学期：</label>
							<form:select class="chzn-select" path="schoolTerm.id">
							<form:options items="${schoolTerms}" itemLabel="termName" itemValue="id"/>
							</form:select>
					  </fieldset>
					  <fieldset>
						    <label>实验室生均面积：</label>
					    <form:input path="labAvgArea" class="easyui-numberbox" min="0" max="99999" validType="maxNumberVal[5]" invalidMessage="无效字符！" /> 
					  </fieldset>
					  <fieldset>
						    <label>额定课时数：</label>
					    <form:input path="ratedCourseTime" class="easyui-numberbox" min="0" max="99999" validType="maxNumberVal[5]" invalidMessage="无效字符！" /> 
					  </fieldset>
					  <fieldset>
						    <label>设备平均机时：</label>
					    <form:input path="deviceAvgTime" class="easyui-numberbox" min="0" max="99999" validType="maxNumberVal[5]" invalidMessage="无效字符！" /> 
					  </fieldset>
					  <fieldset>
						    <label>学院学科系数：</label>
					    <form:input path="subjectFactor" class="easyui-numberbox" precision="2" min="0" max="1" validType="maxNumberVal[5]" invalidMessage="无效字符！"/> 
					  </fieldset>
				</div>
				<div class="moudle_footer">
			        <div class="submit_link">
					<input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
			        <input class="btn btn-return" type="submit" value="确定">
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
			    
		</form:form>
		</div>
		</div>
		</div>
	</div>
</div>
  </body>
</html>
