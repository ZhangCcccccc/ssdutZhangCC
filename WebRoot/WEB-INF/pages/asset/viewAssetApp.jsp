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
<script src="${pageContext.request.contextPath}/js/cmsSystem/plugins/iCheck/icheck.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/cmsSystem/plugins/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/cmsSystem/plugins/sweetalert/sweetalert.min.js"></script>
  
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
  	function newAsset(){
  		var id=${id};
  		layer.open({
            type: 2,
            title: '新建物资',
            maxmin: true,
            shadeClose: true, 
            area : ['700px' , '350px'],
            content: '${pageContext.request.contextPath }/asset/newAssetInApp?id='+id
            });  
  	}
  	function edit(id){
  		layer.open({
            type: 2,
            title: '编辑',
            maxmin: true,
            shadeClose: true, 
            area : ['700px' , '350px'],
            content: '${pageContext.request.contextPath }/asset/editAssetAppRecord?id='+id+'&appId='+${id}
            });  
  	}
  	function showOther(){
  		var flag  = $("input:radio[name='appSpec']:checked").val();
  		if(flag =="是"){
  			$("#other").show();
  		}
  		else{
  			$("#other").hide();
  		}
  	}
  	
  	$(function () {
  		$("#other").hide();
  		$("#number1").hide();
  		$("#number2").hide();
  		$("#number3").hide();
        /*var allBox = $(":checkbox");
        allBox.click(function () {
            allBox.removeAttr("checked");
            $(this).attr("checked", "checked");
        });*/
    });
  	
  	function check(id){
  		$("#number"+id).show();
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
	<legend>药品申购列表</legend>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
				<th>申请编号：</th><td>${assetApp.appNo}</td>
				<th>申请时间：</th><td><fmt:formatDate value="${assetApp.appDate.time}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>申购人：</th><td>${assetApp.user.cname}</td>
				<th>实验大纲：</th><td>
	    	<c:if test = "${assetApp.type == 0}"><span class="title_focus">公用</span></c:if>
	    	<c:if test = "${assetApp.type != 0}"><span class="title_focus">${assetApp.operationItem.operationOutline.labOutlineName}</span></c:if>
	    </td>
				<th>实验项目：</th><td>
	    	<c:if test = "${assetApp.type == 0}"><span class="title_focus">--</span></c:if>
	    	<c:if test = "${assetApp.type != 0}"><span class="title_focus">${assetApp.operationItem.lpName}</span></c:if>
	    </td>
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
				         <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/submitAssetApps?id=${id}">提交</a>
				         <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/listAssetApps?currpage=1&assetAuditStatus=9">返回列表</a>
				  	</div>
					  	<table> 
							<thead>
							<tr>
								<th>药品名称</th>
								<th>药品规格</th>
								<th>cas号</th>
								<th>货号</th>
								<th>经销商</th>
								<th>数量</th>
								<th>单价</th>
								<th>总价</th>
								<th>是否可在实验材料系统中直接购买</th>
								<th>是否需要领取移液枪</th> 
								<th>请写明数量及规格</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${assetAppRecords }" var="curr" varStatus="i">
									<tr>
										<td>${curr.asset.chName}</td>
										<td>${curr.asset.specifications}</td>
										<td>${curr.asset.cas}</td>
										<td>${curr.styleNumber}</td>
										<td>${curr.appSupplier}</td>
										<td>${curr.appQuantity}</td>
										<td><fmt:formatNumber  value="${curr.appPrice*1.00}"  pattern="0.00"></fmt:formatNumber></td>
										<td><fmt:formatNumber  value="${curr.appPrice*curr.appQuantity*1.00}"  pattern="0.00"></fmt:formatNumber></td>
										<td>${curr.appUsage}</td>
										<td>${curr.appSpec}</td>
										<td>
											<c:if test="${ curr.appSpec eq '是'}">${curr.appfinalSpec } ${curr.selectNumber }</c:if>
											<c:if test="${ curr.appSpec eq '否'}">--</c:if>
										</td>
										<td>${curr.mem}</td>
										<td>
										  <a href="${pageContext.request.contextPath}/asset/deleteAssetAppRecord?id=${id }&recordId=${curr.id}" onclick="return confirm('确定删除？');">删除</a>
										   <a onclick="edit(${curr.id})">编辑</a>
										</td>
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
					<form:form action="${pageContext.request.contextPath}/asset/saveAssetAppRecord?id=${id}" modelAttribute="assetAppRecord">
						<table>
							<tr>
						    <label>药品名称<font color="red">*</font>：</label>
  				<li>
  					<form:select id="chName" name="chName" path="asset.id" class="chzn-select" required="true">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${assets}" var="curr">
  							<form:option value="${curr.id}">${curr.chName}[${curr.specifications}]</form:option>
  						</c:forEach>
  					</form:select>
  					<a class="btn btn-new" onclick="newAsset();" >新建</a>
  				</li>
						    </tr>
						    <tr>
								<td>货号<font color="red">*</font>：</td>
								<td><form:input path="styleNumber" required="true"/>
								</td>
							</tr>
							<tr>
								<td>经销商<font color="red">*</font>：</td>
								<td><form:input path="appSupplier" required="true"/>
								</td>
							</tr>
							<tr>
								<td>数量<font color="red">*</font>：</td>
								<td><form:input path="appQuantity" required="true"/>
								</td>
							</tr>
							<tr>
								<td>单价<font color="red">*</font>：</td>
								<td><form:input path="appPrice" required="true"/>
								</td>
							</tr>
							<tr>
								<td>是否可在实验材料系统中直接购买<font color="red">*</font>：</td>
								<td>
								<input type="radio" name="appUsage" value="是"  required="true" />是
									<input type="radio" name="appUsage" value="否"  required="true" checked="checked"/>否
								</td>
							</tr>
							<tr>
								<td>是否需要领取移液枪<font color="red">*</font>：</td>
								<td><input type="radio" name="appSpec" value="是"  required="true"  onchange="showOther()"/>是
									<input type="radio" name="appSpec" value="否" required="true" checked="checked" onchange="showOther()"/>否
								</td>
							</tr> 
							<tr>
							<table id="other">
								<tr>
									<td>请写明数量及规格:</td>
								</tr>
								<tr>
									<td><input id="spec1"  type="checkbox" name="appfinalSpec" value="20ul" onclick="check(1)"/>20ul</td>
									<td id="number1"><form:input name="selectNumber" path="selectNumber"/>个</td>
								</tr>
								<tr>
									<td><input id="spec2" type="checkbox" value="200ul" name="appfinalSpec"  onclick="check(2)"/>200ul</td>
									<td id="number2"><form:input name="selectNumber" path="selectNumber"/>个</td>
								</tr>
								<tr>
									<td><input id="spec3" type="checkbox" value="1000ul" name="appfinalSpec"  onclick="check(3)"/>1000ul</td>
									<td id="number3"><form:input name="selectNumber" path="selectNumber"/>个</td>
								</tr> 
							</table>
							</tr>	
							<tr>
								<td>备注：</td>
								<td><form:input path="mem"/>
								</td>
							</tr>
							<tr>
								<td><input type="submit" value="保存">
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
