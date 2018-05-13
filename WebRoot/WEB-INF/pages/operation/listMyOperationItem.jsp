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
  $(document).ready(function(){
        var s=${status};
        for(var i=0;i<=5;i++)
        {
        	if(i==s)
        	{
        		$("#s"+i).addClass("TabbedPanelsTab selected");
        	}
        	else
        	{
        		$("#s"+i).addClass("TabbedPanelsTab");
        	}
        }
	});
  //取消查询
  function cancel()
  {
    window.location.href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=${status}&orderBy=${orderBy }";
  }
  //跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  
  //提交审核人数据
  function saveCheckUser()
  {
    if($("#username_check").val()==""){
    	alert("老师，您需要先选择指定审核人再提交。")
    }else{
	  document.check_user_form.action="${pageContext.request.contextPath}/operation/submitOperationItem?isMine=1";
	  document.check_user_form.submit();
    }
    	
  }
  //弹出填写审核人的表单
  function submitItem(id)
  {
	  if("${thereIsAHeader}"){
		  
    $("#lp_id").val(id);
    $("#check_user").show();
    $("#check_user").window('open');
	  }else{
		  alert("您好，目前您所在的中心还未在系统中设置系主任，暂时不能提交审核，请联系相关人员进行设置。")
	  }
	  
  }
  //设置项目编号
  function saveCodeCustom()
  {
    document.edit_code_form.action="${pageContext.request.contextPath}/operation/saveCodeCustom";
    document.edit_code_form.submit();
  }
  //修改项目编号
  function editCodeCustom(id, code)
  {
    $("#oi_id").val(id);
    $("#edit_code form table #lpCodeCustom").val(code);
    $("#edit_code").show();
    $("#edit_code").window('open');
  }
  //排序
  
  var asc=${asc};//声明全局变量asc
  function orderByNumber(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=${status}&orderBy=10";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=${status}&orderBy=0";
  }
  function orderByName(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=${status}&orderBy=11";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=${status}&orderBy=1";
  }
  function orderByLab(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=${status}&orderBy=12";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=${status}&orderBy=2";
  }
  function orderByCourse(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=${status}&orderBy=13";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=${status}&orderBy=3";
  }
  function orderByStatus(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=${status}&orderBy=14";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=${status}&orderBy=4";
  }
  </script>
<script type="text/javascript">
//查看详情--材料
function listItemMaterialRecordRest(id){
    var lp_name = $("#lp_name").val();
    var term_id = $("#term_id").val();
    //var course_number = $("#course_number").val();
    var lp_create_user = "-1";
	if($("#lp_name").val()==""){
		lp_name ="-1";
	}
	if($("#term_id").val()==""){
		term_id ="-1";
	}
	//if($("#course_number").val()==""){
		//course_number ="-1";
	//}
	var url = "${pageContext.request.contextPath}/operationRest/listItemMaterialRecordRest/" + lp_name + "/"+ term_id + "/" + lp_create_user + "/${page}"+ "/${1}"+ "/${status}"+"/${orderBy }/" + id;
	//page后面的1--isMine
	window.location.href=url;
	}
//删除	
function deleteOperationItemRest(id){
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
	var url = "${pageContext.request.contextPath}/operationRest/deleteOperationItemRest/" + lp_name + "/"+ term_id + "/" + course_number + "/" + lp_create_user + "/${page}"+ "/${1}"+ "/${status}"+"/${orderBy }/" + id;
	//page后面的1--isMine
	window.location.href=url;
	}	
//提交--保存审核人
function saveCheckUserRest(){
	var id = $("#lp_id").val();//由于之前的传递，lp_id的值就是对应项目卡的id
	var lp_name = $("#lp_name").val();
    var term_id = $("#term_id").val();
    var course_number = $("#course_number").val();
    var lp_create_user = "-1";
    var username_check = $("#username_check").val();
	if($("#lp_name").val()==""){
		lp_name ="-1";
	}
	if($("#term_id").val()==""){
		term_id ="-1";
	}
	if($("#course_number").val()==""){
		course_number ="-1";
	}
	if($("#username_check").val()==""){
    	alert("老师，您需要先选择指定审核人再提交。")
    }else{
		var url = "${pageContext.request.contextPath}/operationRest/submitOperationItemRest/" + lp_name + "/"+ term_id + "/" + course_number + "/" + lp_create_user + "/" + username_check + "/${page}"+ "/${1}"+ "/${status}"+"/${orderBy }/" + id;
		//page后面的1--isMine
		window.location.href=url;
    }
}
</script>	

</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">实验项目</a></li>
		<li class="end"><a href="javascript:void(0)">我的实验项目列表</a></li>
	  </ul>
	</div>
  </div>
  
  <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" id="s0"><a href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=0&orderBy=${orderBy }">全部</a></li>
		<%--<li class="TabbedPanelsTab" id="s1"><a href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=1&orderBy=${orderBy }">草稿</a></li>
		<li class="TabbedPanelsTab" id="s2"><a href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=2&orderBy=${orderBy }">审核中</a></li>
		<li class="TabbedPanelsTab" id="s3"><a href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=3&orderBy=${orderBy }">审核通过</a></li>
		<li class="TabbedPanelsTab" id="s4"><a href="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=4&orderBy=${orderBy }">审核拒绝</a></li>
	--%></ul>
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  
  <div class="content-box">
    <div class="title">
	  <div id="title">实验项目列表</div>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/operation/newOperationItem?isMine=1&flagId=0">新建</a>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&&status=0&orderBy=${orderBy }" method="post" modelAttribute="operationItem">
			 <ul>
  				<li>实验项目名称： </li>
  				<li><form:input id="lp_name" path="lpName"/></li>
  				<li>学期：</li>
  				<li>
  				  <form:select path="schoolTerm.id" id="term_id">
  				    <form:option value="${schoolTerm.id }">${schoolTerm.termName }</form:option>
  				    <form:options items="${schoolTerms}" itemLabel="termName" itemValue="id"/>
  				  </form:select>
  				</li>
  				<%--<li>所属课程：</li>
  				<li>
	  			    <form:select path="schoolCourseInfo.courseNumber"  id="course_number" class="chzn-select">
	  				    <form:option value="">请选择</form:option>
	  				    <c:forEach items="${schoolCourseInfos}" var="sc">
	  				    	<form:option value="${sc.key}">[${sc.key}]${sc.value }</form:option>
	  				    </c:forEach>
	  				</form:select>
  				</li>
  				--%><li>
			      <input type="button" value="取消" onclick="cancel();"/><input type="submit" value="查询"/></li>
  				</ul>
			 
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <th><a href="javascript:void(0);" onclick="orderByNumber()";>序号</a></th>
	    <th><a href="javascript:void(0);" onclick="orderByName()";>实验名称</a></th>
	    <th>学期</th>
	    <th>所属大纲</th>
	    <%--<th>中心主任</th>
	    --%><th><a href="javascript:void(0);" onclick="orderByLab()";>所属实验室</a></th>
	    <%--<th><a href="javascript:void(0);" onclick="orderByCourse()";>所属课程</a></th>
	    --%><th>创建者</th><%--
	    <th>指定审核人</th>
	    <th><a href="javascript:void(0);" onclick="orderByStatus()";>审核状态</a></th>
	    --%><th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listOperationItem}" var="curr" varStatus="i">
	  <tr>
	  <td>
	  ${i.index+1 }
	  </td>  
	    <td>${curr.lpName}</td>
	    <td>${curr.schoolTerm.termName}</td>
	    <td>${curr.operationOutline.labOutlineName}</td>
	    <%--<td>${curr.labRoom.labCenter.userByCenterManager.cname}</td>
	    --%><td>${curr.labRoom.labRoomName}</td>
	    <%--<td>${curr.schoolCourseInfo.courseName}[${curr.schoolCourseInfo.courseNumber}]</td>
	    --%><td>${curr.userByLpCreateUser.cname}</td>
	    <%--<td>${curr.userByLpCheckUser.cname}</td>
	    <td>${curr.CDictionaryByLpStatusCheck.CName}</td>
	    --%><td><%--
	      <c:if test="${draft.id==curr.CDictionaryByLpStatusCheck.id || checkNo.id==curr.CDictionaryByLpStatusCheck.id}">
	      <a href="javascript:void(0)" onclick="submitItem(${curr.id});">提交</a>
	      <a href="${pageContext.request.contextPath}/operation/editOperationItem?operationItemId=${curr.id}&&isMine=1&flagId=1">编辑</a>
	      <a href="javascript:deleteOperationItemRest(${curr.id});" onclick="return confirm('确定删除？');">删除</a>
	      </c:if>
	      --%><a href="javascript:void(0);" onclick="listItemMaterialRecordRest(${curr.id});">详情</a>
	      
	    </td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=1&status=${status}&orderBy=${orderBy }')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=${pageModel.previousPage}&status=${status}&orderBy=${orderBy }')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=${pageModel.currpage}&status=${status}&orderBy=${orderBy }">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=${j.index}&status=${status}&orderBy=${orderBy }">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=${pageModel.nextPage}&status=${status}&orderBy=${orderBy }')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/operation/listMyOperationItem?currpage=${pageModel.lastPage}&status=${status}&orderBy=${orderBy }')" target="_self">末页</a>
    </div>
    
  </div>
  </div>
  </div>
  </div>
  
  <div id="check_user" class="easyui-window" closed="true" modal="true" minimizable="true" title="指定审核人" style="width: 580px;height: 350px;padding: 20px">
  <div class="content-box">
    <form:form name="check_user_form" method="post" modelAttribute="operationItem">
    <table>
      <tr>
        <td>指定审核人</td>
        <td>
          <form:hidden path="id" id="lp_id"/>
          <form:select path="userByLpCheckUser.username" id="username_check" class="chzn-select">
          <form:option value="">请选择</form:option>
            <c:forEach items="${departmentHeaders}" var="u">
              <form:option value="${u.username}">[${u.username}]${u.cname}</form:option>
            </c:forEach>
          </form:select>
        </td>
      </tr>
    </table>
    <div class="moudle_footer">
    <div class="submit_link">
        <input class="btn" id="save" type="button" onclick="saveCheckUserRest();" value="提交">
    </div>
</div>
    </form:form>
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
