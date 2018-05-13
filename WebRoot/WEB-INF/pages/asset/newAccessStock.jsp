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
	  document.getElementById("item").style.display="None";
  });
//字段显示切换
	function userDisplay(){
		var noneeduser = $("#ifPublic").val();
		if(noneeduser == 1){
			document.getElementById("manager").style.display="None";
		}else{
			document.getElementById("manager").style.display="";
		}
	}
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>  
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
		    <li><a href="javascript:void(0)">药品溶液管理</a></li>
		    <li><a href="javascript:void(0)">药品入库</a></li> 
		</ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
      <div id="title">
		新建入库 
      </div>
	</div>
	<form:form action="${pageContext.request.contextPath}/asset/saveAccessStock" method="POST" modelAttribute="assetCabinetWarehouseAccess">
	<div class="new-classroom"> 
		<fieldset> 
	    	<label>药品名称:<font color="red">*</font></label>
	   	 <form:select id="id" path="asset.id" class="chzn-select" required="true">
  			<form:option value="">请选择</form:option>
  				<c:forEach items="${listAssets}" var="curr">
  					<form:option value="${curr.id}">${curr.chName}[${curr.specifications}]</form:option>
  				</c:forEach>
  		</form:select>
	  	</fieldset> 
	  	<fieldset> 
	    	<label>单价:</label>
	   	  <form:input path="unitPrice"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>入库数量<font color="red">*</font>:</label>
	   	 <form:input path="cabinetQuantity" required="true"/>
	  	</fieldset>
	  	<%--<fieldset> 
	    	<label>药品属性:</label>
	   	 <form:select path="ifPublic" class="chzn-select" id="ifPublic" onchange="userDisplay()"> 
		<form:option value="1">公用</form:option>
		<form:option value="0">私用</form:option>
		</form:select>
	  	</fieldset>--%> 
	  		<%--<fieldset id="manager"> 
	    	<label>使用人:</label>
	   	 <form:select class="chzn-select" name="manager" path="user.username" >
  			<form:option value="${currUser.username}">${currUser.cname}</form:option>
  				<c:forEach items="${users}" var="curr">
  					<form:option value="${curr.key}">${curr.value}</form:option>
  				</c:forEach>
  		</form:select>
	  	</fieldset>--%>  
	  	<fieldset> 
	    <label>实验类型<font color="red">*</font>：</label>
	     <form:select class="chzn-select" id="operationOutline" path="type" required="true" name="operationOutline" onChange="listOperationItems()">
	     <form:option value="">请选择</form:option>
	     <form:option value="0">公用</form:option>
  				<c:forEach items="${operationOutlines}" var="curr">
  					<form:option value="${curr.id}">${curr.labOutlineName}</form:option>
  				</c:forEach>
  		</form:select>
	  </fieldset>
	  <fieldset id="item"> 
	    <label>实验项目：</label>
	     <form:select class="chzn-select" path="operationItem.id" id="operationItem" name="operationItem">
	     <form:option value="">请选择</form:option>
  		</form:select>
	  </fieldset>
	  	<fieldset> 
	    	<label>入账时间:</label>
	   	 <input class="easyui-datebox" id="createDate" name="createDate" onclick="new Calendar().show(this);"  readonly="readonly"/> 
	  	</fieldset>   
	  	<fieldset> 
	    	<label>入库时间:</label>
	   	 <input class="easyui-datebox" id="stockDate" name="stockDate" onclick="new Calendar().show(this);"  readonly="readonly"/> 
	  	</fieldset> 
	</div>
	<div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="submit" value="保存">
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
