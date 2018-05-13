<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  
  <!-- 下拉框的样式 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 -->
  <script type="text/javascript"> 
  $(document).ready(function(){
	  var oldFlag='<%=request.getAttribute("oldFlag")%>';
	  if(oldFlag == 0||oldFlag==null){
		  document.getElementById("date").style.display="None";
	  }
  });
  
  // 字段显示切换
  	function dateDisplay(){
  		var needDate = $("#type").val();
  		if(needDate == 0){
  			document.getElementById("date").style.display="None";
  		}else{
  			document.getElementById("date").style.display="";
  		}
  	}
  </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>  
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
		    <li><a href="javascript:void(0)">设备申购管理</a></li>
			<li><a href="javascript:void(0)">设备状态字典</a></li>
		</ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
      <div id="title">设备状态</div>
	</div>
	<form:form action="${pageContext.request.contextPath}/devicePurchase/saveDeviceStatus?isNew=${isNew}&oldFlag=${oldFlag}" method="POST" modelAttribute="cDeviceStatus">
	<div class="new-classroom"> 
	<fieldset>
	  <form:hidden path="id"/>
	  <form:hidden path="statusOrder"/>
	    <label>申购状态：</label>
	    <form:input path="statusName"/>
	  </fieldset>
	   <fieldset>
	    <label>类型：</label>
	    <form:select path="flag" class="chzn-select" id="type"  onchange="dateDisplay()" required="true">
	    <form:option value="">请选择</form:option>
		<form:option value="1">正常状态</form:option>
		<form:option value="0">特殊状态</form:option>
		</form:select>
	  </fieldset>  
	  <fieldset id="date">
	    <label>间隔天数：</label>
	    <form:input path="intervalDate" placeholder="请填写正整数"
        onkeyup="value=value.replace(/[^\d]/g,'') "
        onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
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
