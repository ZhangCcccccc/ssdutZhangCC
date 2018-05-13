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
	  if(${isSpec} == 0){
		  $("#other").hide();
		  $("#number1").hide();
	  		$("#number2").hide();
	  		$("#number3").hide();
	  }
	  else{
		  $("#appSpec1").prop("checked","checked");
		  if(${flag1} == 0){
			  $("#number1").hide();
			  $("#selectNumber1").val("");
		  }else{
			  $("#selectNumber1").val(${number1}); 
			  $("#spec1").prop("checked","checked");
		  }
		  if(${flag2} == 0){
			  $("#number2").hide();
			  $("#selectNumber2").val("");
			  
		  }
		  else{
			  $("#selectNumber2").val(${number2}); 
			  $("#spec2").prop("checked","checked");
		  }
		  if(${flag3} == 0){
			  $("#number3").hide();
			  $("#selectNumber3").val("");
		  }
		  else{
			  $("#selectNumber3").val(${number3}); 
			  $("#spec3").prop("checked","checked");
		  }
	  }
	if(${isUse} == 1){
		 $("#appUsage1").prop("checked","checked");
	}
  });
  function showOther(){
		var flag  = $("input:radio[name='appSpec']:checked").val();
		if(flag =="是"){
			$("#other").show();
		}
		else{
			$("#other").hide();
		}
	}
  function check(id){
		$("#number"+id).show();
	}
  function save(){
      $.ajax({
             url:'${pageContext.request.contextPath}/asset/saveEditAssetAppRecord?appId='+${appId},
             type:'POST',
            data:$('#edit_form').serialize(),
            error:function (request){
              alert('请求错误!');
            },
            success:function(){
              parent.location.href="${pageContext.request.contextPath}/asset/viewAssetApp?id="+${appId};
              var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
              parent.layer.closeAll('iframe');
            }         
      });
   }
  </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>  
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
		    <li><a href="javascript:void(0)">药品溶液管理</a></li>
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
      </div>
	</div>
	<form:form id="edit_form" action="${pageContext.request.contextPath}/asset/saveEditAssetAppRecord" method="POST" modelAttribute="assetAppRecord">
	<div class="new-classroom"> 
		<table>
							<tr>
						    <label>药品名称：</label>
  				<li>
  				<form:hidden path="id"/>
  					<form:select id="chName" name="chName" path="asset.id" class="chzn-select" required="true">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${assets}" var="curr">
  							<form:option value="${curr.id}">${curr.chName}[${curr.specifications}]</form:option>
  						</c:forEach>
  					</form:select>
  					<%--<a class="btn btn-new" onclick="newAsset();" >新建</a>--%>
  				</li>
						    </tr>
						    <tr>
								<td>货号：</td>
								<td><form:input path="styleNumber"/>
								</td>
							</tr>
							<tr>
								<td>经销商：</td>
								<td><form:input path="appSupplier" required="true"/>
								</td>
							</tr>
							<tr>
								<td>数量：</td>
								<td><form:input path="appQuantity" required="true"/>
								</td>
							</tr>
							<tr>
								<td>单价：</td>
								<td><form:input path="appPrice" required="true"/>
								</td>
							</tr>
							<tr>
								<td>是否可在实验材料系统中直接购买<font color="red">*</font>：</td>
								<td>
								<input type="radio" id="appUsage1" name="appUsage" value="是"  required="true" />是
									<input type="radio" id="appUsage2" name="appUsage" value="否"  required="true" checked="checked"/>否
								</td>
							</tr>
							<tr>
								<td>是否需要领取移液枪<font color="red">*</font>：</td>
								<td><input type="radio" name="appSpec" id="appSpec1" value="是"  required="true"  onchange="showOther()"/>是
									<input type="radio" name="appSpec" id="appSpec2" value="否" required="true" checked="checked" onchange="showOther()"/>否
								</td>
							</tr> 
							<tr>
							<table id="other">
								<tr>
									<td>请写明数量及规格:</td>
								</tr>
								<tr>
									<td><input id="spec1"  type="checkbox" name="appfinalSpec" value="20ul" onclick="check(1)"/>20ul</td>
									<td id="number1"><form:input id="selectNumber1" path="selectNumber"/>个</td>
								</tr>
								<tr>
									<td><input id="spec2" type="checkbox" value="200ul" name="appfinalSpec"  onclick="check(2)"/>200ul</td>
									<td id="number2"><form:input id="selectNumber2" path="selectNumber"/>个</td>
								</tr>
								<tr>
									<td><input id="spec3" type="checkbox" value="1000ul" name="appfinalSpec"  onclick="check(3)"/>1000ul</td>
									<td id="number3"><form:input id="selectNumber3" path="selectNumber"/>个</td>
								</tr> 
							</table>
							<tr>
								<td>备注：</td>
								<td><form:input path="mem"/>
								</td>
							</tr>
							<tr>
								<td><input type="button" onclick="save()" value="保存">
								</td>
							</tr>
						</table>
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
