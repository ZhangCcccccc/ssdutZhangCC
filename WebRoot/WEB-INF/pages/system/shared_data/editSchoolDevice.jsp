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
   
  </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>  
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
		    <li><a href="javascript:void(0)">学院设备</a></li>
		    <li> <a href="javascript:void(0)">新建编辑学院设备</a> </li> 
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
       	新建编辑学院设备
      </div>
	</div>
	<form:form action="${pageContext.request.contextPath}/saveSchoolDevice" method="POST" modelAttribute="schoolDevice">
	<div class="new-classroom"> 
		<fieldset> 
	    	<label>设备编号</label>
	   	 <form:input path="deviceNumber" id="deviceNumber" required ="true" onblur="checkUnique()"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>设备名称</label>
	   	 <form:input path="deviceName" required ="true"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>设备型号</label>
	   	 <form:input path="devicePattern" required ="true"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>设备规格</label>
	   	 <form:input path="deviceFormat"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>使用方向</label> 
	    	<form:input path="deviceUseDirection"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>购买日期</label>  
	    	<input class="easyui-datebox" id="date1" name="date1" value="${ date1}" onclick="new Calendar().show(this);"  readonly="readonly"/> 
 		 
	  	</fieldset>
	  	<fieldset> 
	    	<label>存放地点</label> 
	    	<form:input path="deviceAddress"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>国别</label> 
	    	<form:input path="deviceCountry"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>价格</label> 
	    	<form:input path="devicePrice"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>设备入账日期</label>  <input class="easyui-datebox" id="date2" value="${ date2}" name="date2" onclick="new Calendar().show(this);"  readonly="readonly"/>  
	  	</fieldset>
	  	<fieldset> 
	    	<label>设备供应商</label> 
	    	<form:input path="deviceSupplier"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>领用人</label>  
	    	<form:select id="userNumber" path="userByUserNumber.username" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${users}" var="curr">
  							<form:option value="${curr.key}">${curr.value}[${curr.key}]</form:option>
  						</c:forEach>
  					</form:select>
	  	</fieldset>
	  	<fieldset> 
	    	<label>保管人</label>  
	    	<form:select id="keepUser" path="userByKeepUser.username" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${users}" var="curr">
  							<form:option value="${curr.key}">${curr.value}[${curr.key}]</form:option>
  						</c:forEach>
  					</form:select>
	  	</fieldset>
	  	<fieldset> 
	    	<label>项目来源</label> 
	    	<form:input path="projectSource"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>生产厂家</label> 
	    	<form:input path="manufacturer"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>序列号</label> 
	    	<form:input path="sn"/>
	  	</fieldset> 
	  	<fieldset> 
	    	<label>资产类别</label> 
	    	<form:input path="categoryMain"/>
	  	</fieldset> 
	  	<fieldset> 
	    	<label>资产类型</label> 
	    	<form:input path="categoryType"/>
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
		    
		    function checkUnique(){
		    	var deviceNumber = document.getElementById("deviceNumber").value;
		    	$.ajax({
		            url:'${pageContext.request.contextPath}/judgeSchoolDeviceNumberUnique?deviceNumber='+deviceNumber,
		            type:'POST',
		           error:function (request){
		             alert('请求错误!');
		           },
		           success:function(data){
		        	   if(data !="unique"){
		        		   alert("该设备编号已存在!请重新输入！");
		        		   document.getElementById("deviceNumber").value="";
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
