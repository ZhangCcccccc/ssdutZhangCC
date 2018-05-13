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
  
  //批量删除
function batchDelete(){
var array=new Array();
var flag=false; //判断是否一个未选   
$("input[name='items']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
            if ($(this).is(':checked')) { //判断是否选中    
                flag = true; //只要有一个被选择 设置为 true  
            } 
        })
        
      if (flag) {  
         $("input[name='items']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                      if ($(this).is(':checked')) { //判断是否选中
                          array.push($(this).val()); //将选中的值 添加到 array中 
                      }  
                  })
         window.location.href="${pageContext.request.contextPath}/operation/batchDeleteOperationItem?&array="+array+"&status=${status}";
      } else {   
      	alert("请至少选择一条记录");  
      	} 
}
  
  
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
    window.location.href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=${status}&orderBy=${orderBy }";
  }
  //跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  //弹出填写审核人的表单
  function submitItem(id)
  {
    $("#lp_id").val(id);
    $("#check_user").show();
    $("#check_user").window('open');
    
    ajaxGetUser();
  }
  //提交审核人数据
  function saveCheckUser()
  {
    document.check_user_form.action="${pageContext.request.contextPath}/operation/submitOperationItem?isMine=0";
    document.check_user_form.submit();
  }
  //弹出设置项目编号的表单
  function editCode(id)
  {
    $("#oi_id").val(id);
    $("#edit_code").show();
    $("#edit_code").window('open');
  }
  //设置项目编号
  function saveCodeCustom()
  {
    document.edit_code_form.action="${pageContext.request.contextPath}/operation/saveCodeCustom?status=${status}";
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
  //获取教师数据
  function ajaxGetUser(){
    $.ajax({
      type:"GET",
      data:{academyNumber:${schoolAcademy.academyNumber},role:"1"},  //role=1是教师
      url:"${pageContext.request.contextPath}/operation/ajaxGetUser",
      dataType:"json",
      success:function(data){
    	  if(data==null){
  		  	alert("您好，目前您所在的中心还未在系统中设置系主任，暂时不能提交审核，请联系相关人员进行设置。")
  		  	$("#check_user").window('close');
  		  }
  	  else{
  		  
  		 $("#check_users").empty();
         $.each(data, function(i, obj){
           $("#check_users").append("<option value='"+obj.username+"'>"+obj.cname+"["+obj.username+"]</option>");
         })
         
         $("#check_users").trigger("liszt:updated");
  	  }
      }
    });
   
  }
  
//排序
  
  var asc=${asc};//声明全局变量asc
  function orderByNumber(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=${status}&orderBy=10";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=${status}&orderBy=0";
  }
  function orderByName(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=${status}&orderBy=11";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=${status}&orderBy=1";
  }
  function orderByLab(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=${status}&orderBy=12";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=${status}&orderBy=2";
  }
  function orderByCourse(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=${status}&orderBy=13";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=${status}&orderBy=3";
  }
  function orderByStatus(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=${status}&orderBy=14";
	  }else window.location.href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=${status}&orderBy=4";
  }
  </script>
  
<script type="text/javascript">
	/*所有rest方法*/
	//审核or编辑
	function viewOperationItemRest(id){
    var lp_name = $("#lp_name").val();
    var term_id = $("#term_id").val();
    var course_number = $("#course_number").val();
    var lp_create_user = $("#lp_create_user").val();
	if($("#lp_name").val()==""){
		lp_name ="-1";
	}
	if($("#term_id").val()==""){
		term_id ="-1";
	}
	if($("#course_number").val()==""){
		course_number ="-1";
	}
	if($("#lp_create_user").val()==""){
		lp_create_user ="-1";
	}
	var url = "${pageContext.request.contextPath}/operationRest/viewOperationItemRest/" + lp_name + "/"+ term_id + "/" + course_number + "/" + lp_create_user + "/${page}"+ "/${1}"+ "/${status}/" + id;
	//page 后面的1  flag
	window.location.href=url;
	}
	//查看详情--材料
	function listItemMaterialRecordRest(id){
	    var lp_name = $("#lp_name").val();
	    var term_id = $("#term_id").val();
	    var course_number = $("#course_number").val();
	    var lp_create_user = $("#lp_create_user").val();
		if($("#lp_name").val()==""){
			lp_name ="-1";
		}
		if($("#term_id").val()==""){
			term_id ="-1";
		}
		if($("#course_number").val()==""){
			course_number ="-1";
		}
		if($("#lp_create_user").val()==""){
			lp_create_user ="-1";
		}
		var url = "${pageContext.request.contextPath}/operationRest/listItemMaterialRecordRest/" + lp_name + "/"+ term_id + "/" + course_number + "/" + lp_create_user + "/${page}"+ "/${0}"+ "/${status}"+"/${orderBy }/" + id;
		//page后面的0--isMine
		window.location.href=url;
		}
	
</script>
  
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
    <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab" id="s0"><a href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=0&orderBy=${orderBy }">全部</a></li>
		<%--<li class="TabbedPanelsTab" id="s1"><a href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=1&orderBy=${orderBy }">草稿</a></li>
		--%><li class="TabbedPanelsTab" id="s2"><a href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=2&orderBy=${orderBy }">审核中</a></li>
		<li class="TabbedPanelsTab" id="s3"><a href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=3&orderBy=${orderBy }">审核通过</a></li>
		<li class="TabbedPanelsTab" id="s4"><a href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=4&orderBy=${orderBy }">审核拒绝</a></li>
		<li class="TabbedPanelsTab" id="s5"><a href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=5&orderBy=${orderBy }">我的审核</a></li>
	</ul>
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  
  <div class="content-box">
    <div class="title">
	  <div id="title">实验项目列表</div>
	  <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
		  <a class="btn btn-new" href="javascript:void(0);" onclick="batchDelete()">批量删除</a>
	  </sec:authorize>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=${status}&orderBy=${orderBy }" method="post" modelAttribute="operationItem">
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
  				<li>所属课程：</li>
  				<li style="width:180px;">
	  			    <form:select path="schoolCourseInfo.courseNumber"  id="course_number" class="chzn-select">
	  				    <form:option value="">请选择</form:option>
	  				    <c:forEach items="${schoolCourseInfos}" var="sc">
	  				    	<form:option value="${sc.key}">[${sc.key}]${sc.value }</form:option>
	  				    </c:forEach>
	  				</form:select>
  				</li>
  				</ul>
  				
  				<ul>
  				
  				<li>创建者：</li>
  				<li>
	  				<form:select path="userByLpCreateUser.username"  id="lp_create_user" class="chzn-select">
	  				    <form:option value="">请选择</form:option>
	  				    <form:options items="${users}"/>
	  				</form:select>
  				</li>
  				
  				
  				<li>
			        <input type="button" value="取消" onclick="cancel();"/>
  				    <input type="submit" value="查询"/>
  				</li>
  				</ul>
			 
		</form:form>
	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	  	<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
	  	<th><input id="check_all" type="checkbox" onclick="checkAll();"/></th>
	  	</sec:authorize>
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
	  <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER">
	  <td><input id="check_${curr.id}" name="items" type="checkbox" value="${curr.id}"/></td>
	  </sec:authorize>
	    <td>
	      <c:if test="${curr.CDictionaryByLpStatusCheck.id==checkYes.id}">
	        <c:choose>
		        <c:when test="${empty curr.lpCodeCustom}">
	          <!-- 超级管理员才可以设置编号 -->
		        <sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
		        	<input onclick="editCode(${curr.id});" value="设置编号"/>
		        </sec:authorize>	
		        </c:when>
	        	<c:otherwise>
	        		${curr.lpCodeCustom}
	        	</c:otherwise>
	        	
	        </c:choose>
	        
	      </c:if>
	    </td>
	    <td>${curr.lpName}</td>
	    <td>${curr.schoolTerm.termName}</td>
	    <td>${curr.labRoom.labCenter.centerName}</td>
	    <td>${curr.labRoom.labCenter.userByCenterManager.cname}</td>
	    <td>${curr.labRoom.labRoomName}</td>
	    <td>${curr.schoolCourseInfo.courseName}[${curr.schoolCourseInfo.courseNumber}]</td>
	    <td>${curr.userByLpCreateUser.cname}</td>
	    <td>${curr.userByLpCheckUser.cname}</td>
	    <td>${curr.CDictionaryByLpStatusCheck.CName}</td>
	    <td>
	      <c:if test="${draft.id==curr.CDictionaryByLpStatusCheck.id || checkNo.id==curr.CDictionaryByLpStatusCheck.id}">
	      <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER"> <!-- 实验室中心主任可以提交、编辑、删除项目卡 -->
	      <a href="javascript:void(0)" onclick="submitItem(${curr.id});">提交</a>
	      <a href="${pageContext.request.contextPath}/operation/editOperationItem?operationItemId=${curr.id}&&isMine=0&flagId=1">编辑</a>
	      <a href="${pageContext.request.contextPath}/operation/deleteOperationItem?operationItemId=${curr.id}&&isMine=0&status=${status}" onclick="return confirm('确定删除？');">删除</a>
	      </sec:authorize>
	      <sec:authorize ifNotGranted="ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER"> <!-- 不是实验室中心主任，是实验室管理员、或者项目卡创建者可以提交、编辑、删除项目卡 -->
		    <sec:authorize ifAnyGranted="ROLE_TEACHER">
		    <c:if test="${curr.userByLpCreateUser.username==currUser.username||fn:contains(curr.labRoom.labRoomAdmins,currUser.username)}">
		      <a href="javascript:void(0)" onclick="submitItem(${curr.id});">提交</a>
		      <a href="${pageContext.request.contextPath}/operation/editOperationItem?operationItemId=${curr.id}&&isMine=0&flagId=1">编辑</a>
		      <a href="${pageContext.request.contextPath}/operation/deleteOperationItem?operationItemId=${curr.id}&&isMine=0&status=${status}" onclick="return confirm('确定删除？');">删除</a>
			</c:if>
			</sec:authorize>
		 </sec:authorize>
	      </c:if>
	      <c:if test="${curr.CDictionaryByLpStatusCheck.id==toCheck.id && curr.userByLpCheckUser.username==currUser.username}">
	      	<a href="javascript:void(0);" onclick="viewOperationItemRest(${curr.id})">审核</a>
	      </c:if>
	      <c:if test="${curr.CDictionaryByLpStatusCheck.id==checkYes.id}">
		      <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR,ROLE_EXPERIMENTALTEACHING,ROLE_SUPERADMIN,ROLE_ASSETMANAGEMENT">
		      <a href="javascript:void(0);" onclick="viewOperationItemRest(${curr.id})">编辑</a>
		      </sec:authorize>
	      </c:if>
	      <c:if test="${not empty curr.lpCodeCustom}">
	      <sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
	      	<a onclick="editCodeCustom(${curr.id}, '${curr.lpCodeCustom}');">修改编号</a>
	      </sec:authorize>
	      </c:if>
	      <a href="javascript:void(0);" onclick="listItemMaterialRecordRest(${curr.id});">详情</a>
	    </td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=${status}&orderBy=${orderBy }')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/operation/listOperationItem?currpage=${pageModel.previousPage}&status=${status}&orderBy=${orderBy }')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/operation/listOperationItem?currpage=${pageModel.currpage}&status=${status}&orderBy=${orderBy }">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/operation/listOperationItem?currpage=${j.index}&status=${status}&orderBy=${orderBy }">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/operation/listOperationItem?currpage=${pageModel.nextPage}&status=${status}&orderBy=${orderBy }')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/operation/listOperationItem?currpage=${pageModel.lastPage}&status=${status}&orderBy=${orderBy }')" target="_self">末页</a>
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
          <form:select path="userByLpCheckUser.username" id="check_users" class="chzn-select">
            <form:option value="">请选择</form:option>
          </form:select>
        </td>
      </tr>
    </table>
    <div class="moudle_footer">
    <div class="submit_link">
        <input class="btn" id="save" type="button" onclick="saveCheckUser();" value="提交">
    </div>
</div>
    </form:form>
  </div>
  </div>
  
  <div id="edit_code" class="easyui-window" closed="true" modal="true" minimizable="true" title="设置编号" style="width: 580px;height: 350px;padding: 20px">
  <div class="content-box">
    <form:form name="edit_code_form" method="post" modelAttribute="operationItem1">
    <table>
      <tr>
        <td>项目编号</td>
        <td>
          <form:hidden path="id" id="oi_id"/>
          <form:input path="lpCodeCustom"/>
        </td>
      </tr>
    </table>
    <div class="moudle_footer">
    <div class="submit_link">
        <input class="btn" id="save" type="button" onclick="saveCodeCustom();" value="确定">
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
