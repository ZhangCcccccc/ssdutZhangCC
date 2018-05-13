<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  
  <!-- 下拉框的样式 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 -->
  <script type="text/javascript">
  
  //全部选择或不选
  function checkAll()
  {
    if($("#check_all").attr("checked"))
    {
      $(":checkbox").attr("checked", true);
    }
    else
    {
      $(":checkbox").attr("checked", false);
    }
  }
  function addDeviceTag2()
  {
    $("#device_id").val(null);
    $("#device_name").val("");
    $("#device_model").val("");
    $("#device_quantity").val("");
    $("#device_price_sig").val("");
  
    $("#editDeviceTag2").show();
    $("#editDeviceTag2").window('open');
  }
  
  function saveDeviceTag2()
  {
   document.form_device.action="${pageContext.request.contextPath}/labconstruction/saveDeviceTag2?status="+${status};
    if(isNaN($("#device_quantity").val())){
			alert("【数量】填写数字");
			return false;
		}
    document.form_device.submit();
    
  }
  
  function editDeviceTag2(id)
  {
   
    $.ajax({
      type:"GET",
      url:"${pageContext.request.contextPath}/labconstruction/ajaxGetDeviceTag2",
      data:{id:id},
      dataType:"json",
      success:function(data){
    	
    	$("#device_id").val(id);
        $("#device_name").val(data.deviceName);
        $("#device_model").val(data.deviceModel);
        $("#device_quantity").val(data.deviceQuantity);
        $("#device_price_sig").val(data.devicePriceSig);
        
        $("#editDeviceTag2").show();
        $("#editDeviceTag2").window('open');
      },
      error:function(){
			alert("请删除后重新填写");
			
		}
    });
  }
  
  
  
  </script>
</head>
  
<body>

  <div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">项目经费</a></li>
			<li class="end"><c:if test="${isEdit}"><a href="javascript:void(0)">编辑</a></c:if><c:if test="${!isEdit}"><a href="javascript:void(0)">新建</a></c:if></li>
		</ul>
	</div>
  </div>
  
  <!-- 内容栏开始 -->
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
  <!-- 标题 -->
    <div class="title">
      <div id="title"><c:if test="${isEdit}">编辑</c:if><c:if test="${!isEdit}">新建</c:if>项目经费</div>
    </div>
    
  <!-- 表单 -->
    <form:form action="${pageContext.request.contextPath}/labconstruction/saveLabConstructionFunding?labConstructionProjectId=${labConstructionProject.id }&status=${status}" method="POST" modelAttribute="labConstructionFunding">
    <div class="new-classroom">
      <fieldset>
        <form:hidden path="id"/>
         <label>项目名称：</label>
         <td>${labConstructionProject.projectName }</td>
		      
      </fieldset>
      <fieldset>
        <label>项目编号：</label>
        <td>${labConstructionProject.projectNumber }</td>
		      
      </fieldset>
            
      <fieldset>
        <label>经费编号：</label>
        <form:input path="fundingNumber" id="funding_number" class="easyui-validatebox" required="true"
        onkeyup="value=value.replace(/[^\d]/g,'') "   
		onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
		placeholder="请输入数字"
        />
      </fieldset>
      
      
      <div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="submit" value="保存">
          <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
        </div>
      </div>
      
    </div>
    
    </form:form>
    
    
    
    <fieldset class="introduce-box">
		<legend>设备详单</legend>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
			
			<c:if test="${!isEdit}">
					<td>
						<font color=red>请您提交保存后，再对设备详单进行编辑。</font>
					</td>
			</c:if>
			<c:if test="${isEdit}">
				  <div id="TabbedPanels1" class="TabbedPanels">
				  <div class="TabbedPanelsContentGroup">
				  <div class="TabbedPanelsContent">
				        <li><input type="button" onclick="addDeviceTag2();" value="添加"></li>
				    <div class="content-box">
				    <table>
				      <thead>
				      <tr>
				      	
				        <th>序号</th>
				        <th>设备名称</th>
				        <th>型号/规格</th>
				        <th>数量</th>
				        <th>设备单价（元）</th>
				        <th>操作</th>
				      </tr>
				      </thead>
				      <tbody>
				      <c:forEach items="${labConstructionDevices}" var="curr" varStatus="i">
				      <tr>
				        <td>${i.count}</td>
				        <td>${curr.deviceName}</td>
				        <td>${curr.deviceModel}</td>
				        <td>${curr.deviceQuantity}</td>
				        <td>${curr.devicePriceSig}</td>
				        <td>
				          <a href="javascript:void(0)" onclick="editDeviceTag2(${curr.id});">编辑</a>
				          <a href="${pageContext.request.contextPath}/labconstruction/deleteDeviceTag2?id=${curr.id}&labConstructionFundingId=${labConstructionFunding.id}&status=${status}" onclick="return confirm('确认要删除吗？')">删除</a>
				        </td>
				      </tr>
				      </c:forEach>
				      </tbody>
				    </table>
				    </div>
				  </div>
				  </div>
				  </div>
				  
				
				  <div id="editDeviceTag2" class="easyui-window" closed="true" modal="true" minimizable="true" title="需要申购物品详情" style="width: 600px;height: 500px;padding: 20px">
				  <div class="content-box">
				  <form:form name="form_device" method="post" modelAttribute="labConstructionDevice" >
				  <table class="color_tb">
				  <tr>
				    <td>设备名称</td>
				    <td>
					    <form:input path="deviceName" id="device_name" />
					    <form:hidden path="id" id="device_id"/>
					    <form:hidden path="labConstructionProject.id" id="lab_construction_project" value="${labConstructionProject.id}"/>
				    </td>
				  </tr>
				  <tr>
				    <td>型号/规格</td>
				    <td><form:input path="deviceModel" id="device_model" /></td>
				  </tr>
				  <tr>
				    <td>数量</td><td><form:input path="deviceQuantity" id="device_quantity" /></td>
				  </tr>
				  <tr>
				    <td>设备单价（元）</td><td><form:input path="devicePriceSig" id="device_price_sig" /></td>
				  </tr>
				  </table>
				  <div class="moudle_footer">
				    <div class="submit_link">
				        <input class="btn" id="save" type="button" onclick="saveDeviceTag2();" value="确定">
				    </div>
				  </div>
				  </form:form>
				</div>
				</div>
		</c:if>
	</tr>
		</table>
		</fieldset>
    <!-- 保存整个实验室项目卡 -->
		<form:form action="${pageContext.request.contextPath}/labconstruction/saveLabConstructionFundingAll?status=${status}" method="POST" ><!-- modelAttribute="operationItem"没有加这个 -->
			<div class="moudle_footer">
	        <div class="submit_link">
	          <input class="btn" id="save" type="submit" value="保存">
	          <%--<input class="btn btn-return" type="button" value="保存" onclick="window.history.go(-1)">  --%>
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
