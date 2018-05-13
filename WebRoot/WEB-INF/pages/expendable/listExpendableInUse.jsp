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
//取消查询
  function cancel()
  {
    window.location.href="${pageContext.request.contextPath}/expendable/listExpendableInUse?currpage=1";
  }
  //跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  function importSample(){
	  	$('#importSample').window({left:"50px",top:"50px"});
	  	$("#importSample").window('open');
	  	
	  }
//AJAX验证是否有不规范的字段
  function checkRegex(){
  	var formData = new FormData(document.forms.namedItem("importForm"));
  	$.ajax({
          url:"${pageContext.request.contextPath}/importSample/checkRegex",
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
          	}else if(data == 'dateError'){
          		alert("请检查上传文档中的日期格式，然后再次上传");
          		var obj = document.getElementById('file');
                  obj.outerHTML = obj.outerHTML; //兼容IE8
          	}else if(data == 'dateNotInTerm'){
          		alert("当前日期不属于任何一个学期，请您到系统管理--学期管理新建学期");
          		var obj = document.getElementById('file');
                  obj.outerHTML = obj.outerHTML; //兼容IE8
          	}else if(data == 'numError'){
          		alert("请检查上传文档中的数字格式，然后再次上传");
          		var obj = document.getElementById('file');
                  obj.outerHTML = obj.outerHTML; //兼容IE8
          	}else if(data == 'teacherError'){
          		alert("当前授课教师不在教师列表中，请您到系统管理--用户管理中进行添加");
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
	    <li><a href="javascript:void(0)">耗材管理</a></li>
		<li class="end"><a href="javascript:void(0)">耗材记录</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">在用耗材记录</div>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/expendable/listExpendableInUse?currpage=1" method="post" modelAttribute="expendable">
			 <ul>
			 <li>耗材名称： </li>
  				<li>
  					<form:select id="expendableName" path="expendableName" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listExpendableNames}" var="curr">
  							<form:option value="${curr.expendableName}">${curr.expendableName}</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li>耗材类别： </li>
  				<li>
  					<form:select id="expendableSource" path="expendableSource" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listExpendableSources}" var="curr">
  							<form:option value="${curr.expendableSource}">${curr.expendableSource}</form:option>
  						</c:forEach>
  					</form:select>
  				</li>
  				<li>危化品标志： </li>
  				<li>
  					<form:select id="ifDangerous" path="ifDangerous" class="chzn-select">
  						<form:option value="">请选择</form:option>
  						<form:option value="1">是</form:option>
  						<form:option value="0">否</form:option>
  					</form:select>
  				</li>
  				<li><input type="submit" value="查询"/>
			      <input type="button" value="取消" onclick="cancel()"/></li>
  				</ul>
  				</form:form>
  				<a href="${pageContext.request.contextPath}/expendable/newExpendableRecord"class="btn btn-new">新建耗材记录</a>
				<a class="btn btn-new" onclick="importSample()">导入耗材记录</a>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th>序号</th>
	    <th>类型</th>
	    <th>危化品标记</th>
	    <th>危化品种类</th>
	    <th>商品名称</th>
	    <th>类别</th>
	    <th>规格</th>
	    <th>包装单位</th>
	    <th>品牌</th>
	    <th>单价</th>
	    <th>数量</th>
	    <th>申购人</th>
	    <th>存放位置</th>
	    <th>操作</th>
	    <th>查看记录</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listExpendableInUse}" var="curr" varStatus="i">
	  <tr>
	    <td>${i.count+pageSize*(currpage-1)}</td>
	    <td>${curr.expendableType}</td>
	    <c:if test="${curr.ifDangerous eq 0}">
	    <td>否</td></c:if>
	    <c:if test="${curr.ifDangerous eq 1}">
	    <td>是</td></c:if>
	    <c:if test="${curr.ifDangerous eq null}">
	    <td></td>
	    </c:if>
	    <td>${curr.dangerousType}</td>
	    <td>${curr.expendableName}</td>
	    <td>${curr.expendableSource}</td>
	    <td>${curr.expendableSpecification}</td>
	    <td>${curr.expendableUnit}</td>
	    <td>${curr.brand}</td>
	    <td>${curr.unitPrice}</td>
	    <td>${curr.quantity}</td>
	    <td>${curr.user.cname}</td>
	    <td>
	    	${curr.place}
	    	<a href="${pageContext.request.contextPath}/expendable/modifyPlace?id=${curr.id}">修改</a>
	    </td>
	    <td>
	    <c:if test="${curr.user.username ne userName}">
	      <a href="${pageContext.request.contextPath}/expendable/newExpendableApply?id=${curr.id}">申领</a>
	      </c:if>
	      <c:if test="${curr.user.username eq userName}">
	      <a href="${pageContext.request.contextPath}/expendable/newExpendablestock?id=${curr.id}">盘库</a>
	   </c:if>
	    </td>
	    <td>
	      <a href="${pageContext.request.contextPath}/expendable/listExpendableApplyRecords?id=${curr.id}&currpage=1">申领记录</a>
	      <a href="${pageContext.request.contextPath}/expendable/listExpendablestockRecords?id=${curr.id}&currpage=1">盘库记录</a>
	    </td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
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
	
	</div>
		<div id= "importSample"  class ="easyui-window panel-body panel-body-noborder window-body" title= "导入模版" modal="true" dosize="true" maximizable= "true" collapsible ="true" minimizable= "false" closed="true" iconcls="icon-add" style=" width: 600px; height :400px;">
        <form:form name="importForm" action="${pageContext.request.contextPath}/expendable/importExpendable?" enctype ="multipart/form-data">
	         <br>
	         <input type="file" id="file" name ="file" onchange="checkRegex()" required="required" />
	         <input type="submit"  value ="开始上传" />
	         <br><br><hr><br>
		<a href="${pageContext.request.contextPath}/pages/importSample/expendable.xlsx">点击此处</a>，下载范例<br><br><hr><br>
		示例图片：<br>
		<img src="${pageContext.request.contextPath}/images/importSample/expendable.png" heigth ="65%" width="65%" />
        </form:form>
        </div>
	<!-- 下拉框的js -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-easyui/jquery-1.7.1.min.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<!-- 分页[s] -->
	
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableInUse?currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableInUse?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/expendable/listExpendableInUse?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/expendable/listExpendableRecords?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableInUse?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/expendable/listExpendableInUse?currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
