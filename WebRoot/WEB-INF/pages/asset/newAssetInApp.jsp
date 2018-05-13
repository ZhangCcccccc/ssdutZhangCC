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
  function saveAssetInApp(){
      $.ajax({
             url:'${pageContext.request.contextPath}/asset/saveAssetInApp',
             type:'POST',
            data:$('#edit_form').serialize(),
            error:function (request){
              alert('请求错误!');
            },
            success:function(){
              parent.location.href="${pageContext.request.contextPath}/asset/viewAssetApp?id="+${id};
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
		    <li><a href="javascript:void(0)">新建药品字典</a></li> 
		</ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
      <div id="title"> 新建药品信息 
      </div>
	</div>
	<form:form id="edit_form" action="${pageContext.request.contextPath}/asset/saveAsset" method="POST" modelAttribute="asset">
	<div class="new-classroom"> 
		<fieldset>  
	    	<label>药品名称<font color="red">*</font></label>
	    	<form:hidden path="assetLimit" value="0"/>
	   	 <form:input path="chName" required="true"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>规格/型号<font color="red">*</font><font color="red" style="text-transform:lowercase;">（规范填写：500g/瓶）</font></label>
	   	 <form:input path="specifications" required="true"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>计量单位<font color="red">*</font></label>
	   	 <form:input path="unit" required="true"/>
	  	</fieldset>
	  	<fieldset> 
	    	<label>CAS号</label>
	   	 <form:input path="cas"/>
	  	</fieldset>
	    <fieldset> 
	    	<label>药品类型<font color="red">*</font></label>
	   	 <form:select path="category" class="chzn-select" id="category" required="true"> 
		<form:option value="0">试剂</form:option>
		<form:option value="1">耗材</form:option>
		</form:select>
	  	</fieldset>  
	</div>
	<div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="button" onclick="saveAssetInApp()" value="确定">
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
