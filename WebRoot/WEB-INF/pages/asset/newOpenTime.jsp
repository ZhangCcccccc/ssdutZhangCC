<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/timer.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
  <!-- 下拉框的样式 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 -->
  <script type="text/javascript">
  $(document).ready(function(){
	  document.getElementById("item").style.display="None";
	  var flag='<%=request.getAttribute("flag")%>';
	  if(flag == 1){
		  alert("超出申购时间范围!");
	  }
  });
  function listWeekdays(){
	    var termId = document.getElementById("term").value; 
	    
	        $.ajax({
		        url:"${pageContext.request.contextPath}/asset/findWeekdaysByTermId?termId="+termId,
		        type: 'POST',    
		        async: false,  
		        cache: false,  
		        contentType: false,  
		        processData: false, 
		        success:function(data){
		            $("#schoolweekday").html(data.operationItems);
		            $("#schoolweekday").trigger("liszt:updated");
		        }
		    });
	}
  </script>
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
		    <li><a href="javascript:void(0)">药品溶液管理</a></li>
			<li><a href="javascript:void(0)">新建开箱时间记录</a></li>
		</ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
      <div id="title">新建开箱时间记录</div>
	</div>
	<form:form action="${pageContext.request.contextPath}/asset/saveOpenTime?" method="POST" modelAttribute="iotSharePowerOpentime">
	<div class="new-classroom">
	<fieldset>
		<form:hidden path="id"/>
	    <label>学期：</label> 
	    <form:select path="schoolTerm.id" id="term" onchange="listWeekdays()" required="true">
			<form:option value="">请选择</form:option>
			<form:options items="${schoolTerms}" itemLabel="termName" itemValue="id"/>
		</form:select>
	  </fieldset>
	   <%--<fieldset> 
	    <label>实验课题：</label>
	    <form:input path="projectName"/>
	  </fieldset>--%>
	  <fieldset> 
	    <label>周几：</label> 
  		<form:select path="schoolWeekday.id" id="schoolweekday" required="true">
			<form:option value="">请选择</form:option>
			<c:forEach items="${schoolWeekdays}" var="curr">
  					<form:option value="${curr.key}">${curr.value}</form:option>
  				</c:forEach>
		</form:select>
	  </fieldset> 
	  <fieldset> 
	    <label>开始时间：</label> 
	  <input id="startTime" class="Wdate datepicker" value="${endTime}" required="true" onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'HH:mm:ss'})"  type="text" name="startTime" readonly /> 
	  </fieldset> 
	  <fieldset> 
	    <label>结束时间：</label> 
	  <input id="endTime" class="Wdate datepicker" value="${endTime}" required="true" onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'HH:mm:ss'})"  type="text" name="endTime" readonly /> 
	  </fieldset> 
	  <fieldset> 
	  <label>是否启动：</label> 
	  <form:select path="enable" id="enable" required="true">
			<form:option value="1">是</form:option>
			<form:option value="0">否</form:option>
		</form:select>
	  </fieldset> 
	</div>
	<div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="submit" value="确定" onclick="checkDate();">
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
