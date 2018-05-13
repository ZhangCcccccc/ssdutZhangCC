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
  <style>
  .chzn-container{width:94%;margin: 0 3%;}
  </style>
  <script src="${pageContext.request.contextPath}/js/Calendar.js" type="text/javascript"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
  <!-- 下拉的样式结束 -->
  <script type="text/javascript">
  $(document).ready(function(){
	  var type = ${type};
	  if(type == 0)
	  {
		  document.getElementById("item").style.display="None";
	  }
  });
  function listOperationItems(){
	    var operationOutlineId = document.getElementById("operationOutline").value; 
	    if(operationOutlineId == '0'){
	    	document.getElementById("item").style.display="None";
	    }
	    else{
	        $.ajax({
		        url:"${pageContext.request.contextPath}/asset/findoperationItemsByoperationOutlineId?operationOutlineId="+operationOutlineId,
		        type: 'POST',    
		        async: false,  
		        cache: false,  
		        contentType: false,  
		        processData: false, 
		        success:function(data){
		            $("#operationItem").html(data.operationItems);
		            $("#operationItem").trigger("liszt:updated");
		        }
		    });
	        document.getElementById("item").style.display="";
	    }
	}
  </script>
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
		    <li><a href="javascript:void(0)">药品溶液管理</a></li>
			<li><a href="javascript:void(0)">新建</a></li>
		</ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
      <div id="title">申领</div>
	</div>
	<form:form name="form" action="${pageContext.request.contextPath}/asset/saveAssetReceive" method="POST" modelAttribute="assetReceive">
	<div class="new-classroom">
	<fieldset>
	<form:hidden path="id"/>
	    <label>申领编号：</label>${assetReceive.receiveNo} 
	  </fieldset>
	   <%--<fieldset>
	    <label>实验课题：</label>
	    <form:input path="projectName"/>
	  </fieldset>--%>
	  <%--<fieldset>
	    <label>实验内容：</label>
	    <form:input path="projectContent"/>
	  </fieldset>--%>
	  <fieldset> 
	    <label>实验类型：</label>
	     <select class="chzn-select" id="operationOutline" required="true" name="operationOutline" onChange="listOperationItems()">
	     <option value="">请选择</option>
	     <c:if test="${type eq 0 }" >
	     <option value="0" selected="selected">公用</option>
	     </c:if>
	      <c:if test="${type ne 0 }" >
	     <option value="0">公用</option>
	     </c:if>
  				<c:forEach items="${operationOutlines}" var="curr">
  				<c:if test="${type eq curr.id }">
  					<option value="${curr.id}" selected="selected">${curr.labOutlineName}</option>
  				</c:if>
  				<c:if test="${type ne curr.id }">
  					<option value="${curr.id}">${curr.labOutlineName}[${curr.schoolTerm.termName }]</option>
  				</c:if>
  				</c:forEach>
  		</select>
	  </fieldset>
	  <fieldset id="item"> 
	    <label>实验项目：</label>
	     <form:select class="chzn-select" path="operationItem.id" id="operationItem" name="operationItem">
	     <form:option value="">请选择</form:option>
	     <c:forEach items="${items}" var="curr">
	     <c:if test="${assetReceive.operationItem.id eq curr.id}">
  				<option value="${curr.id}" selected="selected">${curr.lpName}</option>
  		</c:if>
  		<c:if test="${assetReceive.operationItem.id ne curr.id}">
  				<option value="${curr.id}">${curr.lpName}</option>
  		</c:if>
  			</c:forEach>
  		</form:select>
	  </fieldset>
	  <fieldset>
	   <label>申请日期和时间： </label> 
	   <div style="width:100%;overflow:hidden;">
	       <input style="width:42%;height:25px;float:left;margin:0 3%;" id="date"  class="Wdate datepicker" value="${date}"   onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'yyyy-MM-dd'})" type="text" name="date"   readonly />
		   <input style="width:42%;height:25px; float:left" id="startData"  class="Wdate datepicker" value="${startDate}"   onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'HH:mm:ss'})" type="text" name="startData"   readonly />
		</div>
		<div style="width:100%;margin:10px 0;text-align:center;letter-spacing:0;">————————至————————</div>
        <input style="width:90%;height:25px;margin:0 3%;" id="endDate" class="Wdate datepicker" value="${endDate}" onfocus="WdatePicker({skin:'whyGreen',dateFmt: 'HH:mm:ss'})"  type="text" name="endDate" readonly /> 
	</fieldset>
	</div>
	<div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="button" value="确定" onclick="checkDate()">
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
		    
		   /* function checkDate(){
		    	 var startDate = document.getElementById("startData").value; 
		    	 var endDate = document.getElementById("endDate").value; 
		    	 $.ajax({
				        url:"${pageContext.request.contextPath}/asset/checkDate?startDate="+startDate+"&endDate="+endDate,
				        type: 'POST',    
				        async: false,  
				        cache: false,  
				        contentType: false,  
				        processData: false, 
				        success:function(data){
				        	if(data=='legal'){
				        		document.form.action="${pageContext.request.contextPath}/asset/saveAssetReceive";
				        	    document.form.submit();
				        	}
				        	else{
				        		alert("时间跨度超过两周，请重新选择时间！")
				        	}
				        }
				    });
		    }*/
		    function checkDate(){
		    	var date = document.getElementById("date").value; 
		    	var startDate = document.getElementById("startData").value; 
		    	 var endDate = document.getElementById("endDate").value; 
		    	 $.ajax({
				        url:"${pageContext.request.contextPath}/asset/checkDate?date="+date+"&startDate="+startDate+"&endDate="+endDate,
				        type: 'POST',    
				        async: false,  
				        cache: false,  
				        contentType: false,  
				        processData: false, 
				        success:function(data){
				        	if(data == "dateilegal"){
				        		alert("选择的日期与当前的日期间隔超过两周，请重新选择！");
				        	}
				        	if(data == "timeilegal"){
				        		alert("选择的时间间隔超过两小时，请重新选择！");
				        	}
				        	if(data == "ilegal"){
				        		alert("开始时间大于结束时间，请重新选择！");
				        	}
				        	if(data=="legal"){
				        		document.form.action="${pageContext.request.contextPath}/asset/saveAssetReceive";
				        	    document.form.submit();
				        	}
				        }
				    });
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
