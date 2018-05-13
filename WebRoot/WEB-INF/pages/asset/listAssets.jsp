<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

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
      var s=${category};
      if(s==0){
    	  $("#s0").addClass("TabbedPanelsTab selected");
    	  $("#s1").addClass("TabbedPanelsTab");
      }
      if(s==1){
    	  $("#s1").addClass("TabbedPanelsTab selected");
    	  $("#s0").addClass("TabbedPanelsTab");
      }
     
	});
//取消查询
  function cancel()
  {
    window.location.href="${pageContext.request.contextPath}/asset/listAssets?currpage=1&category=${category}";
  }
  //跳转
  function targetUrl(url)
{
	location.href = url;
}
  function importAsset(){
	  	$('#importAsset').window({left:"50px",top:"50px"});
	  	$("#importAsset").window('open');
	  } 
//AJAX验证是否有不规范的字段
  function checkRegex(){
  	var formData = new FormData(document.forms.namedItem("importForm"));
  	$.ajax({
          url:"${pageContext.request.contextPath}/asset/checkRegex",
          type: 'POST',  
          data: formData,  
          async: false,  
          cache: false,  
          contentType: false,  
          processData: false, 
          
          error: function(request) {
              alert("暂时不能判断该文档是否规范");
          },
          success: function(data) {
          	if(data == 'success'){
          		// do nothing
          	}else if(data == 'nullError'){
          		alert("文档不规范，请完善必填项！");
          		var obj = document.getElementById('file');
                  obj.outerHTML = obj.outerHTML; //兼容IE8
          	}else if(data == 'flagError'){
          		alert("请检查上传文档中的是否需要上限提醒格式，只能填写'Y'或'N'");
          		var obj = document.getElementById('file');
                  obj.outerHTML = obj.outerHTML; //兼容IE8
          	}else if(data == '"categoryError"'){
          		alert("请检查上传文档中的药品类型格式，只能填写'试剂'或'耗材'");
          		var obj = document.getElementById('file');
                  obj.outerHTML = obj.outerHTML; //兼容IE8
          	}else{
          		alert("文件格式错误，请您上传excel格式的文档");
          		var obj = document.getElementById('file');
                  obj.outerHTML = obj.outerHTML; //兼容IE8
          	}
          }
      });
  }
  
  </script>
</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">药品溶液管理</a></li>
		<li class="end"><a href="javascript:void(0)">药品字典</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
  
			<li class="TabbedPanelsTab" tabindex="0" id="s0">
			<a href="${pageContext.request.contextPath}/asset/listAssets?currpage=1&category=0">试剂</a>
			</li>
		
			<li class="TabbedPanelsTab"  tabindex="0" id="s1">
			<a href="${pageContext.request.contextPath}/asset/listAssets?currpage=1&category=1">耗材</a>
			</li>
		
	  </ul>
  <div class="TabbedPanelsContentGroup">
  
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">药品信息字典列表</div>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/newAsset?category=${category}">新建字典</a>
	  <a class="btn btn-new" onclick="importAsset()">导入</a>
	</div>
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/asset/listAssets?currpage=1&category=${category}" method="post" modelAttribute="asset">
			 <ul>
  				<li>药品名称： </li>
  				<li style="width:300px;">
  					<form:select id="id" path="id" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listAssetChNames}" var="curr">
  							<form:option value="${curr.id}">${curr.chName}[${curr.specifications}]</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li>
			      <input type="button" value="取消" onclick="cancel()"/><input type="submit" value="查询"/></li>
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>序号</th> 
	    <th>药品名称</th>
	    <th>规格</th>
	    <th>单位</th>
	    <th>CAS号</th> 
	    <th>库存提醒</th> 
	    <th>提醒下限</th> 
	    <th>操作</th> 
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listAssets}" var="curr" varStatus="i">
	  <tr>
	    <td>${i.count+pageSize*(currpage-1)}</td> 
	    <td>${curr.chName}</td>  
	    <td>${curr.specifications}</td> 
	    <td>${curr.unit}</td> 
	    <td>${curr.cas}</td>  
	    <td>
	    	<c:if test = "${curr.flag eq 1}">是</c:if>
	    	<c:if test = "${curr.flag eq 0}">否</c:if>
	    </td> 
	    <td>
	    	<c:if test = "${curr.flag eq 1}">${curr.assetLimit}</c:if>
	    	<c:if test = "${curr.flag eq 0}">--</c:if>
	    </td> 
	     <td>
	      <a href="${pageContext.request.contextPath}/asset/editAsset?id=${curr.id}&category=${category }">编辑</a> 
	      <a href="${pageContext.request.contextPath}/asset/deleteAsset?id=${curr.id}&category=${category }" onclick="return confirm('确定删除？');">删除</a>
	    </td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	</div>
		<div id= "importAsset"  class ="easyui-window panel-body panel-body-noborder window-body" title= "导入模版" modal="true" dosize="true" maximizable= "true" collapsible ="true" minimizable= "false" closed="true" iconcls="icon-add" style=" width: 600px; height :400px;">
        <form:form name="importForm" action="${pageContext.request.contextPath}/asset/importAsset?category=${category }" enctype ="multipart/form-data">
	         <br>
	         <input type="file" id="file" name ="file" onchange="checkRegex()" required="required" />
	         <input type="submit"  value ="开始上传" />
	         <br><br><hr><br>
		<a href="${pageContext.request.contextPath}/pages/importSample/assets.xlsx">点击此处</a>，下载范例<br><br><hr><br>
		示例图片：<br>
		<img src="${pageContext.request.contextPath}/images/importSample/assets.png" heigth ="65%" width="65%" />
        </form:form>
        </div>
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
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/asset/listAssets?currpage=1&category=${category }')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/asset/listAssets?currpage=${pageModel.previousPage}&category=${category }')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}//asset/listAssets?currpage=${pageModel.currpage}&category=${category }">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/asset/listAssets?currpage=${j.index}&category=${category }">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/asset/listAssets?currpage=${pageModel.nextPage}&category=${category }')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/asset/listAssets?currpage=${pageModel.lastPage}&category=${category }')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
  
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
