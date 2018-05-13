<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
   <!-- 下拉框的样式 -->
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 --> 
  
  <!-- 文件上传的样式和js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>  
  
  <style>
  	select{
  		width:162px;
  		margin-left:10px;
  	}
  	.chzn-container{
  	width:162px !important;
  	margin-left:10px
  	}
  	.edit-content-box {
    border: 1px solid #9BA0AF;
    border-radius: 5px;
    overflow: visible;
    margin-top: 15px;
}
  </style>
  <script>
  	
  </script>
</head>

<body>
<div class="main_container cf rel w95p ma">
 
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div>
	<fieldset class="introduce-box">
	<legend>耗材申领审核基本信息</legend>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
				<th>商品名称：</th><td>${expendableApply.expendable.expendableName}</td>
				<th>规格：</th><td>${expendableApply.expendable.expendableSpecification}</td>
				<th>包装单位：</th><td>${expendableApply.expendable.expendableUnit}</td>
				<th>危险品标识：</th>
				<c:if test="${expendableApply.expendable.ifDangerous eq 0}">
			    <td>否</td></c:if>
			    <c:if test="${expendableApply.expendable.ifDangerous eq 1}">
			    <td>是</td></c:if>
			    <c:if test="${empty expendableApply.expendable.ifDangerous}">
			    <td></td>
			    </c:if>
			</tr>
			<tr>
				
				<th>单价：</th><td>${expendableApply.expendable.unitPrice}</td>
				<th>存放位置：</th><td>${expendableApply.expendable.place}</td>
				<th>申领数量：</th><td>${expendableApply.expendableNumber}</td>
				<th>实际剩余数量：</th><td>${expendableApply.expendable.quantity}</td>
				<th>品牌：</th><td>${expendableApply.expendable.brand}</td>
				<th>危险品种类：</th><td>${expendableApply.expendable.dangerousType}</td>
			</tr>
			<tr>
			</tr>
			<tr>
			</tr>
			<tr>
				<th>申领说明：</th><td>${expendableApply.remarks}</td>
			</tr>
		</table>
	</fieldset>
  </div>
  </div>
  </div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
      <div id="title">审核</div>
	</div>
	<form:form action="${pageContext.request.contextPath}/expendable/saveExpendableApplyByOwner?id=${id}" method="POST" modelAttribute="expendableApply">
	<div class="new-classroom"> 
	  <fieldset>
	    <label>审核结果：</label>
	    <form:radiobutton path="flag" value="2" id="flag" name="flag"/><label>审核通过</label>
	    <form:radiobutton path="flag" value="3" id="flag" name="flag" /><label>审核拒绝</label>
	  </fieldset>
	<fieldset>
	
	  <form:hidden path="id"/>
	    <label>审核意见：</label>
	    <form:input path="remarks" name="remarks" id="remarks"/>
	  </fieldset>
	     
	</div>
	<div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="submit" value="确定">
		  <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
        </div>
	</div>
	</form:form>
		
		
		
		
		
		
		
		
			
		<input type="hidden" id="pageContext" value="${pageContext.request.contextPath }"/>
		<input type="hidden" id="itemId" value="${operationItem.id }"/>
   <script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
   <script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
   <script type="text/javascript">
	// 弹窗
	function addRecords(){
		$("#addRecords").show();
	    $("#addRecords").window('open');  
	}
	
	
	
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
	 <script type="text/javascript">
	 var expendableId;
	 function moveId(id){
		 expendableId = id;
	 }
		$(".changeAmount").click(function(){
			$(this).hide();//修改按钮隐藏
			$(this).parent().find(".edit-edit").slideDown();//修改信息显示
		});
		$(".edit-edit").blur(function(){
			$(this).hide();//修改按钮隐藏
			$(this).parent().find(".changeAmount").slideDown();//修改信息显示
			var amount = $(this).val();
			if(amount==''){
				amount=0;
			}
			$.ajax({
		        url:"${pageContext.request.contextPath}/operation/saveItemExpendableAmount?expendableId="+expendableId+"&amount="+amount,
		        type:"POST",
		        success:function(data){//AJAX查询成功
		        		if(data=="success"){
		        			window.history.go(0);
		        		}   
		        }
			});
		});
	</script>
</body>
</html>
