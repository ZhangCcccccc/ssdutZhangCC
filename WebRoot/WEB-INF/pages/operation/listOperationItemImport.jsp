<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
    window.location.href="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&orderBy=${orderBy}";
  }
  //跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
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
  
  function submitForm()
  {
    var flag = false;  //是否有checkbox被选中
    var ids = "";
    $("input[name='items']:checked").each(function(){
        ids += $(this).val()+",";
		flag = true;
	});
	
	if(flag)
	{
	  if(ids.length > 0)
	  {
	  	ids = ids.substring(0, ids.length-1);  //去掉最后的逗号
	  }
	  if($("#term").val()=="")
	  {
	    alert("请选择导入学期！");
	    return false;
	  }
	  
	  document.itemsForm.action="${pageContext.request.contextPath}/operation/importOperationItem?termId="+$("#term").val()+"&itemIds="+ids;
	  document.itemsForm.submit();
	}
	else
	{
	  alert("至少选择一个实验项目！");
	}
  }
  //导入整个学期的实验项目
  function submitTermForm()
  {
    var sourceId = $("#term_id").val();
    var targetId = $("#term").val();
    if(sourceId==targetId)
    {
      alert("来源学期与要导入的学期相同！");
      return false;
    }
    if(sourceId=="")
    {
      alert("请选择查询条件的学期！");
      return false;
    }
    if(targetId=="")
    {
      alert("请选择要导入的学期！");
      return false;
    }
    
    if(confirm("确定导入整个学期？"))
    {
      window.location.href="${pageContext.request.contextPath}/operation/importTermOperationItem?sourceId="+sourceId+"&targetId="+targetId+"&cid=${cid}";
    }
  }
  
  //排序
  
  var asc=${asc};//声明全局变量asc
  function orderByNumber(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&status=${status}&orderBy=10";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&status=${status}&orderBy=0";
  }
  function orderByName(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&status=${status}&orderBy=11";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&status=${status}&orderBy=1";
  }
  function orderByLab(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&status=${status}&orderBy=12";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&status=${status}&orderBy=2";
  }
  function orderByCourse(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&status=${status}&orderBy=13";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&status=${status}&orderBy=3";
  }
  function orderByStatus(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&status=${status}&orderBy=14";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&status=${status}&orderBy=4";
  }
  </script>
 <script type="text/javascript"> 
 /*所有rest方法*/
//查看详情--材料
function listItemMaterialRecordRest(id){
    var lp_name = $("#lp_name").val();
    var term_id = $("#term_id").val();
    var course_number = $("#course_number").val();
    var lp_create_user = "-1";
	if($("#lp_name").val()==""){
		lp_name ="-1";
	}
	if($("#term_id").val()==""){
		term_id ="-1";
	}
	if($("#course_number").val()==""){
		course_number ="-1";
	}
	var url = "${pageContext.request.contextPath}/operationRest/listItemMaterialRecordRest/" + lp_name + "/"+ term_id + "/" + course_number + "/" + lp_create_user + "/${page}"+ "/${3}"+ "/${0}"+"/${orderBy }/" + id;
	//page后面的3--isMine  3后面的0--status
	window.location.href=url;
	}
</script>	
  
  
  <style>
    .t_style ul li{
      float:left;
    }
  </style>
</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">实验项目</a></li>
		<li class="end"><a href="javascript:void(0)">实验项目列表</a></li>
	  </ul>
	</div>
  </div>
  
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">实验项目列表</div>
	</div>
	
	<div class="tool-box" style="min-height:40px!important;">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&orderBy=${orderBy}" method="post" modelAttribute="operationItem">
			 <ul>
  				<li>学期</li>
  				<li>
  				  <form:select path="schoolTerm.id" id="term_id">
  				    <form:option value="">请选择</form:option>
  				    <form:options items="${schoolTerms}" itemLabel="termName" itemValue="id"/>
  				  </form:select>
  				</li>
  				<li>实验项目名称： </li>
  				<li><form:input id="lp_name" path="lpName" style="width:100px;"/></li>
  				<li>所属课程： </li>
  				<li>
	  			    <form:select path="schoolCourseInfo.courseNumber"  id="course_number" class="chzn-select">
	  				    <form:option value="">请选择</form:option>
	  				    <c:forEach items="${schoolCourseInfos}" var="sc">
	  				    	<form:option value="${sc.key}">[${sc.key}]${sc.value }</form:option>
	  				    </c:forEach>
	  				</form:select>
  				</li>
  				<li><input type="button" value="取消" onclick="cancel();"/><input type="submit" value="查询"/> &nbsp;</li>
  			</ul>
  			<ul>
  				<li>导入学期</li>
  				<li>
	              <select id="term" name="term">
	                <option value="">请选择</option>
	                <c:forEach items="${schoolTerms}" var="t">
	                  <option value="${t.id}">${t.termName}</option>
	                </c:forEach>
	              </select>
	            </li>
	            <li><input type="button" value="导入选中" onclick="submitForm();"/></li>
  			</ul>
		</form:form>
	</div>
	
	<form name="itemsForm" method="post">
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th><input id="check_all" type="checkbox" onclick="checkAll();"/></th>
	    <th><a href="javascript:void(0);" onclick="orderByNumber()";>实验编号</a></th>
	    <th><a href="javascript:void(0);" onclick="orderByName()";>实验名称</a></th>
	    <th>学期</th>
	    <th>所属实验中心</th>
	    <th>中心主任</th>
	    <th><a href="javascript:void(0);" onclick="orderByLab()";>所属实验室</a></th>
	    <th><a href="javascript:void(0);" onclick="orderByCourse()";>所属课程</a></th>
	    <th>创建者</th>
	    <th>指定审核人</th>
	    <th><a href="javascript:void(0);" onclick="orderByStatus()";>审核状态</a></th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listOperationItem}" var="curr">
	  <tr>
	    <td><input id="check_${curr.id}" name="items" type="checkbox" value="${curr.id}"/></td>
	    <td>${curr.lpCodeCustom}</td>
	    <td>${curr.lpName}</td>
	    <td>${curr.schoolTerm.termName}</td>
	    <td>${curr.labRoom.labCenter.centerName}</td>
	    <td>${curr.labRoom.labCenter.userByCenterManager.cname}[${curr.labRoom.labCenter.userByCenterManager.username}]</td>
	    <td>${curr.labRoom.labRoomName}</td>
	    <td>${curr.schoolCourseInfo.courseName}[${curr.schoolCourseInfo.courseNumber}]</td>
	    <td>${curr.userByLpCreateUser.cname}[${curr.userByLpCreateUser.username}]</td>
	    <td>${curr.userByLpCheckUser.cname}[${curr.userByLpCheckUser.username}]</td>
	    <td>${curr.CDictionaryByLpStatusCheck.CName}</td>
	    <td><a href="javascript:void(0);" onclick="listItemMaterialRecordRest(${curr.id});">查看</a></td>
	    <!-- isMine=3   表示从导入页面进入的查看 -->
	  </tr>
	  </c:forEach><%--
	  <tr>
	    <td colspan="100" style="text-align:right;"><input type="button" value="导入整个学期" onclick="submitTermForm();"/></td>
	  </tr>
	  --%></tbody>
	</table>
	</form>
	
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&status=${status}&orderBy=${orderBy}')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=${pageModel.previousPage}&status=${status}&orderBy=${orderBy}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=${pageModel.currpage}&status=${status}&orderBy=${orderBy}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=${j.index}&status=${status}&orderBy=${orderBy}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=${pageModel.nextPage}&status=${status}&orderBy=${orderBy}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=${pageModel.lastPage}&status=${status}&orderBy=${orderBy}')" target="_self">末页</a>
    </div>
    
  </div>
  </div>
  </div>
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
</body>
</html>
