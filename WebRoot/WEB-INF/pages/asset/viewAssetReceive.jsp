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
  <script type="text/javascript">
  function getSpec(osel){
     var content = osel.options[osel.selectedIndex].text;
      
      var x,y;
      for(var i = 0; i<content.length; i++){
     	 if(content[i] == '['){
     		 x=i;
     	 }
     	 if(content[i] == ']'){
     		 y=i;
     		 break;
     	 }
      }
      var unit  = content.substring(x,y+1);
      unit = unit.replace(/[^a-z^A-Z]/g,'');
      $("#unit").html(unit);
  }
  
  function trimNumber(str){ 
		return str.replace(/\d+/g,''); 
	} 
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
	<legend>药品申领列表</legend>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
				<th>申请编号：</th><td>${assetReceive.receiveNo}</td>
				<th>申请时间：</th><td><fmt:formatDate value="${assetReceive.receiveDate.time}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>申领人：</th><td>${assetReceive.user.cname}</td>
				 <th>实验大纲：</th><td>
	    	<c:if test = "${assetReceive.type == 0}"><span class="title_focus">公用</span></c:if>
	    	<c:if test = "${assetReceive.type != 0}"><span class="title_focus">${assetReceive.operationItem.operationOutline.labOutlineName}</span></c:if>
	    </td>
				<th>实验项目：</th><td>
	    	<c:if test = "${assetReceive.type == 0}"><span class="title_focus">--</span></c:if>
	    	<c:if test = "${assetReceive.type != 0}"><span class="title_focus">${assetReceive.operationItem.lpName}</span></c:if>
	    </td>
			</tr>
			<tr>
			<th>开始时间：</th><td><fmt:formatDate value="${assetReceive.startData.time}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
			<th>结束时间：</th><td><fmt:formatDate value="${assetReceive.endDate.time}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
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
				  	<div class="title">新增
				         <a class="btn btn-new" onclick="addRecords();">添加</a>
				         <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/submitAssetReceive?id=${id}">提交</a>
				         <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/listAssetReceives?currpage=1&status=9">返回列表</a>
				  	</div>
					  	<table> 
							<thead>
							<tr>
								<th>药品名称</th>
								<th>药品属性</th>
								<th>申领数量</th>
								<th>申领单位</th>
							</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${assetReceiveRecords }" var="curr" varStatus="i">
									<tr>
										<td>${curr.asset.chName}[${curr.asset.specifications}]</td>
										<td>
											<c:if test="${curr.ifPublic eq 1}">公用</c:if>
											<c:if test="${curr.ifPublic eq 0}">私用</c:if>
										</td>
											<td>${curr.quantity}</td>
										<td>${unit[i.count-1]}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				  </div>
			  </div>
		  </div>
	  </div>
  </div>
	<div id="addRecords" class="easyui-window " title="添加" align="left" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 500px; height: 250px;">
					<form:form id="edit_form" action="${pageContext.request.contextPath}/asset/saveAssetReceiveRecord?id=${id}" modelAttribute="assetReceiveRecord">
						<table>
							<tr>
						    <label>药品名称：</label>
  				<li>
  					<form:select id="chName" name="chName" path="asset.id" class="chzn-select" required="true"  onChange="getSpec(this)">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${assets}" var="curr">
  							<form:option value="${curr.id}">${curr.chName}[${curr.specifications}]</form:option>
  						</c:forEach>
  					</form:select>
  					<%--<form:select id="ifPublic" name="ifPublic" path="ifPublic" class="chzn-select" required="true">
  					<form:option value="1">公用</form:option>
  					<form:option value="0">私用</form:option>	
  					</form:select>--%>
  				</li>
						    </tr>
							<tr>
								<td>申领数量：</td>
								<td><form:input path="quantity" required="true"/><font id="unit"></font></td>
							</tr>
							<tr>
								<td><input type="button" value="保存" onclick="saveAssetReceiveRecord()">
								</td>
							</tr>
						</table>
					</form:form>
				</div>	
			
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
	 function saveAssetReceiveRecord(){
         $.ajax({
                url:'${pageContext.request.contextPath}/asset/saveAssetReceiveRecord?id='+${id},
                type:'POST',
               data:$('#edit_form').serialize(),
               error:function (request){
                 alert('请求错误!');
               },
               success:function(data){
            	   if(data == "match"){
            		   window.location.reload();
            	   }
            	   else{
            		   alert("数量超出范围!");
            	   }
               }         
         });
      }
	</script>
</body>
</html>
