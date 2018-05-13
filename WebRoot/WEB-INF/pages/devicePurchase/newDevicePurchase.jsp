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
  //function testDuplicated(){
	 // var labRoomNumber=$("#labRoomNumber").val();
		//$.post('${pageContext.request.contextPath}/labRoom/testDuplicated?labRoomNumber='+labRoomNumber,function(data){
			//		if(data=="isDuplicated"){
				//		alert("对不起，编号与现存的编号重复，请核实后重新填写！");
					//}else{
						//alert("编号可用");
				//	}
					
				// });
//  }
  
  </script>
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
		    <li><a href="javascript:void(0)">设备申购管理</a></li>
			<li><a href="javascript:void(0)">设备申购</a></li>
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
	<form:form action="${pageContext.request.contextPath}/devicePurchase/saveDevicePurchase?isNew=${isNew}" method="POST" modelAttribute="nDevicePurchase">
	<div class="new-classroom">
	  <fieldset>
	   <form:hidden path="id"/>
	  <c:if test="${isNew eq 0}">
	  <form:hidden path="cDeviceStatus.id"/>
	  <form:hidden path="endDate"/>
	  </c:if>
	    <label>申购名称：</label>
	    <form:input path="useDirection"/>
	  </fieldset>
	  <fieldset>
	    <label>联系方式：</label>
	    <form:input path="contactInformation"/>
	  </fieldset>
	   <fieldset>
	    <label>经费名称：</label>
	    <form:input path="costName"/>
	  </fieldset>
	  <fieldset>
	    <label>申购金额：</label>
	    <form:input path="purchaseCost" placeholder="请填写正数"/>
	  </fieldset>
	  <fieldset>
	    <label>经费编号：</label>
	    <form:input path="costCode"/>
	  </fieldset>
	  <fieldset>
	    <label>是否紧急：</label>
	    <form:radiobutton path="ifEmergency" value="1" id="costCode1" /><label>是</label>
	    <form:radiobutton path="ifEmergency" value="0" id="costCode2" /><label>否</label>
	  </fieldset>
	  <fieldset>
	     <label>经费类型：</label>
		<form:select path="CDictionaryByCostType.id" class="chzn-select">
		<form:option value="">请选择</form:option>
		<form:options items="${costType}" itemLabel="CName" itemValue="id"/>
		</form:select>
       </fieldset>
       <fieldset>
	     <label>申购原因：</label>
		<form:select path="CDictionaryByCostReason.id" class="chzn-select">
		<form:option value="">请选择</form:option>
		<form:options items="${CostReason}" itemLabel="CName" itemValue="id"/>
		</form:select>
       </fieldset>
	  <fieldset>
	    <label>申购说明：</label>
	    <form:textarea path="devicePurchaseReason" style="width:1000px;height:100px"/>
	  </fieldset>
	  <fieldset>
	    <label>规格需求：</label>
	    <form:textarea path="purchaseFormat" style="width:1000px;height:100px"/>
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
