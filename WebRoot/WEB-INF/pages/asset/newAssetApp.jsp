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
	  var outLine = ${outLine};
	  if(outLine==0)
	  {
		  document.getElementById("item").style.display="None";
	  }
	  var flag='<%=request.getAttribute("flag")%>';
	  if(flag == 1){
		  alert("超出申购时间范围!");
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
      <div id="title">申购</div>
	</div>
	<form:form action="${pageContext.request.contextPath}/asset/saveAssetApp?" method="POST" modelAttribute="assetApp">
	<div class="new-classroom">
	<fieldset>
	<form:hidden path="id"/>
	    <label>申领编号：</label>${assetApp.appNo} 
	  </fieldset>
	   <%--<fieldset> 
	    <label>实验课题：</label>
	    <form:input path="projectName"/>
	  </fieldset>--%>
	  <fieldset> 
	    <label>实验类型：</label>
	     <form:select class="chzn-select" id="operationOutline" path="type" required="true" name="operationOutline" onChange="listOperationItems()">
	     <form:option value="">请选择</form:option>
	     <form:option value="0">公用</form:option>
  				<c:forEach items="${operationOutlines}" var="curr">
  					<form:option value="${curr.id}">${curr.labOutlineName}[${curr.schoolTerm.termName }]</form:option>
  				</c:forEach>
  		</form:select>
	  </fieldset>
	  <fieldset id="item"> 
	    <label>实验项目：</label>
	     <form:select class="chzn-select" path="operationItem.id" id="operationItem" name="operationItem">
	     <form:option value="">请选择</form:option>
	     <c:forEach items="${items}" var="curr">
  					<form:option value="${curr.id}">${curr.lpName}</form:option>
  				</c:forEach>
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
