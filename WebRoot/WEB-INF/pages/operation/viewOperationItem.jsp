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
  //审核通过
  function checkPass(){
    if(confirm('通过后不能恢复，确定审核通过？'))
    {
      window.location.href="${pageContext.request.contextPath}/operation/checkOperationItem?operationItemId=${operationItem.id}&result=1";
    }
  }
  //审核拒绝
  function checkFail(){
    if(confirm('拒绝后不能恢复，确定审核拒绝？'))
    {
      window.location.href="${pageContext.request.contextPath}/operation/checkOperationItem?operationItemId=${operationItem.id}&result=0"
    }
  }
  </script>
  
    <script type="text/javascript">
    //材料使用
  function addMaterialRecord()
  {
    $("#lpmrId").val(null);
    $("#lpmr_name").val("");
    $("#lpmr_category").val("");
    $("#lpmr_model").val("");
    $("#lpmr_unit").val("");
    $("#lpmr_quantity").val("");
    $("#lpmr_amount").val("");
    $("#lpmr_remark").val("");
  	
    $("#editMaterialRecord").show();
  	//获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    $('#editMaterialRecord').window({left:"100px", top:topPos+"px"});
    $("#editMaterialRecord").window('open');
  }
  
  function getSpec(osel){
	     var content = osel.options[osel.selectedIndex].text;
	      
	      var x,y;
	      for(var i = 0; i<content.length; i++){
	     	 if(content[i] == '['){
	     		 x=i;
	     	 }
	     	 if(content[i] == ']'){
	     		 y=i;
	     		 break;
	     	 }
	      }
	      var unit  = content.substring(x,y+1);
	      unit = unit.replace(/[^a-z^A-Z]/g,'');
	      $("#unit").html(unit);
	  }
  
  function saveMaterialRecord()
  {
    document.form_material.action="${pageContext.request.contextPath}/operation/saveItemMaterialRecord?flag="+${flag}+"&status="+${status}+"&id="+${operationItem.id};
    if(isNaN($("#lpmr_quantity").val())){
			alert("【数量】填写数字");
			return false;
		}
    document.form_material.submit();
    
  }
  
  function editMaterialRecord(mrId)
  {
    $.ajax({
      type:"GET",
      url:"${pageContext.request.contextPath}/operation/ajaxGetMaterialRecord",
      data:{mrId:mrId},
      dataType:"json",
      success:function(data){
        $("#lpmrId").val(mrId);
        $("#lpmr_name").val(data.lpmrName);
        $("#lpmr_category").val(data.lpmrCategory);
        $("#lpmr_model").val(data.lpmrModel);
        $("#lpmr_unit").val(data.lpmrUnit);
        $("#lpmr_quantity").val(data.lpmrQuantity);
        $("#lpmr_amount").val(data.lpmrAmount);
        $("#lpmr_remark").val(data.lpmrRemark);
        
        $("#editMaterialRecord").show();
        $("#editMaterialRecord").window('open');
      }
    });
  }
  
  
  //设备关联
  function addDevices()
  {
    $("#addDevice").show();
    //获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#addDevice').window({left:"100px", top:topPos+"px"});
    $("#addDevice").window('open');
  }
  
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
  //保存实验项目设备
  function submitForm()
  {
    var flag = false;  //是否有checkbox被选中
    var category = $("#device_category").val();
    var ids = "";
    $("input[name='itemDevice']:checked").each(function(){
        ids += $(this).val()+",";
		flag = true;
	});
	
	if(flag)
	{
	  if(ids.length > 0)
	  {
	  	ids = ids.substring(0, ids.length-1);  //去掉最后的逗号
	  }
	  if(category=="")
	  {
	    alert("请选择类型！");
	    return false;
	  }
	  
	  document.device_form.action="${pageContext.request.contextPath}/operation/saveItemDeviceNew?itemId=${operationItem.id}&category="+category+"&ids="+ids+"&flag="+${flag}+"&status="+${status};
      document.device_form.submit();
	}
	else
	{
	  alert("至少选择一个设备！");
	}
  }
  </script>
  
  <script type="text/javascript">
	/*所有rest方法*/
	//返回列表页面
	function listOperationItemRest(){
		var url = "${pageContext.request.contextPath}/operationRest/listOperationItemRest/" + "${lp_name}" + "/"+ ${term_id} + "/" + ${course_number} + "/" + "${lp_create_user}" + "/${page}"+ "/${1}"+ "/${status}"+ "/${orderBy}";
		//page 后面的1--flag
		window.location.href=url;
	}
	//返回我的列表页面
	function listMyOperationItemRest(){
		var url = "${pageContext.request.contextPath}/operationRest/listMyOperationItemRest/" + "${lp_name}" + "/"+ ${term_id} + "/" + ${course_number} + "/" + "${lp_create_user}" + "/${page}"+ "/${0}"+ "/${status}"+ "/${orderBy}";
		//page 后面的0--flag
		window.location.href=url;
	}
	
	
</script>
  
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

</head>
  
<body>



  <div class="navigation">
		<div id="navigation">
			<ul>
				<li><a href="javascript:void(0);">实验项目</a></li>
				<li class="end"><a href="javascript:void(0);">实验项目详细信息</a></li>
			</ul>
		</div>
  </div>
 
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="edit-content-box">
    <div class="title">
	  <div id="title">实验项目详情</div>
	  <c:if test="${flag==1}">
	  <a class="btn btn-new" href="javascript:void(0);" onclick="listOperationItemRest();">返回</a>
	  </c:if>
	  <c:if test="${flag==2}">
	  <a class="btn btn-new" href="javascript:void(0);" onclick="listMyOperationItemRest();">返回</a>
	  </c:if>
	  <c:if test="${flag==3}">
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/operation/listOperationItem?currpage=1&status=${status}&orderBy=${orderBy }">返回</a>
	  </c:if>
	  <sec:authorize ifAnyGranted="ROLE_DEPARTMENTHEAD,ROLE_EXCENTERDIRECTOR,ROLE_EXPERIMENTALTEACHING,ROLE_SUPERADMIN">
	  <a class="btn btn-edit">编辑</a>
	 </sec:authorize>
    </div>
    <div class="edit-content">
    <table>
      <tr>
        <th>实验名称：</th><td>${operationItem.lpName}</td>
        <th>实验学时：</th><td>${operationItem.lpDepartmentHours}</td>
        <th>实验总学时：</th><td>${operationItem.lpDepartmentHoursTotal}</td>
        <th>课程总学时：</th><td>${operationItem.lpCourseHoursTotal}</td>
      </tr>
      <tr>
        <th>计划学年总人数：</th><td>${operationItem.lpYearsPeopleNumberPlan}</td>
        <th>实验者人数：</th><td>${operationItem.lpStudentNumber}</td>
        <th>实验套数：</th><td>${operationItem.lpSetNumber}</td>
        <th>每组人数：</th><td>${operationItem.lpStudentNumberGroup}</td>
      </tr>
      <tr>
        <th>循环次数：</th><td>${operationItem.lpCycleNumber}</td>
        <th>指导书名称：</th><td>${operationItem.lpGuideBookTitle}</td>
        <th>编者：</th><td>${operationItem.lpGuideBookAuthor}</td>
        <th>考核方法：</th><td>${operationItem.lpAssessmentMethods}</td>
      </tr>
      <tr>
        <th>项目简介：</th><td>${operationItem.lpIntroduction}</td>
        <th>课前准备：</th><td>${operationItem.lpPreparation}</td>
        <th>实验类别：</th><td>${operationItem.CDictionaryByLpCategoryMain.CName}</td>
        <th>实验类型：</th><td>${operationItem.CDictionaryByLpCategoryApp.CName}</td>
      </tr>
      <tr>
        <th>实验性质：</th><td>${operationItem.CDictionaryByLpCategoryNature.CName}</td>
        <th>实验者类型：</th><td>${operationItem.CDictionaryByLpCategoryStudent.CName}</td>
        <th>变动状态：</th><td>${operationItem.CDictionaryByLpStatusChange.CName}</td>
        <th>开放实验：</th><td>${operationItem.CDictionaryByLpCategoryPublic.CName}</td>
      </tr>
      <tr>
        <th>获奖等级：</th><td>${operationItem.CDictionaryByLpCategoryRewardLevel.CName}</td>
        <th>实验要求：</th><td>${operationItem.CDictionaryByLpCategoryRequire.CName}</td>
        <th>实验指导书：</th><td>${operationItem.CDictionaryByLpCategoryGuideBook.CName}</td>
        <th>学期：</th><td>${operationItem.schoolTerm.termName}</td>
      </tr>
      <tr>
        <th>所属实验室：</th><td>${operationItem.labRoom.labRoomName}</td>
        <th>所属学科：</th><td>${operationItem.systemSubject12.SName}[${operationItem.systemSubject12.SNumber}]</td>
        <th>所属专业：</th><td>${operationItem.systemMajor12.MName}[${operationItem.systemMajor12.MNumber}]</td>
        <th>所属课程：</th><td>${operationItem.schoolCourseInfo.courseName}[${operationItem.schoolCourseInfo.courseNumber}]</td>
      </tr>
      <tr>
        <th>主讲教师：</th><td>${operationItem.userByLpTeacherSpeakerId.cname}[${operationItem.userByLpTeacherSpeakerId.username}]</td>
        <th>辅导教师：</th><td>${operationItem.userByLpTeacherAssistantId.cname}[${operationItem.userByLpTeacherAssistantId.username}]</td>
        <th>面向专业：</th><td>${majorStr}</td>
        <th>指定审核人：</th><td>${operationItem.userByLpCheckUser.cname}[${operationItem.userByLpCheckUser.username}]</td>
      </tr>
      
      
      <tr>
        <th>审核状态：</th><td>${operationItem.CDictionaryByLpStatusCheck.CName}</td>
      </tr>
      <c:if test="${operationItem.CDictionaryByLpStatusCheck.id==toCheck.id && operationItem.userByLpCheckUser.username==currUser.username}">
      <tr>
        <td><input type="button" onclick="checkPass();" value="审核通过" /></td>
        <td><input type="button" onclick="checkFail();" value="审核拒绝" /></td>
      </tr>
      </c:if>
    </table>
    </div>
    
    <form:form action="${pageContext.request.contextPath}/operation/saveOperationItemWhileAudit?flag=${flag }&status=${status }" method="POST" modelAttribute="operationItem">
		<div class="edit-edit">
			<table>
					<form:hidden path="id"/>
					<tr>
			        <th>实验名称：</th><td><form:input path="lpName"/></td>
			        <th>实验学时：</th><td><form:input path="lpDepartmentHours" /></td>
			        <th>实验总学时：</th><td><form:input path="lpDepartmentHoursTotal" /></td>
			        <th>课程总学时：</th><td><form:input path="lpCourseHoursTotal" /></td>
			      </tr>
			      <tr>
			        <th>计划学年总人数：</th><td><form:input path="lpYearsPeopleNumberPlan" /></td>
			        <th>实验者人数：</th><td><form:input path="lpStudentNumber" /></td>
			        <th>实验套数：</th><td><form:input path="lpSetNumber" /></td>
			        <th>每组人数：</th><td><form:input path="lpStudentNumberGroup" /></td>
			      </tr>
			      <tr>
			        <th>循环次数：</th><td><form:input path="lpCycleNumber" /></td>
			        <th>指导书名称：</th><td><form:input path="lpGuideBookTitle" /></td>
			        <th>编者：</th><td><form:input path="lpGuideBookAuthor" /></td>
			        <th>考核方法：</th><td><form:input path="lpAssessmentMethods" /></td>
			      </tr>
			      <tr>
			        <th>项目简介：</th><td><form:input path="lpIntroduction" /></td>
			        <th>课前准备：</th><td><form:input path="lpPreparation" /></td>
			        <th>实验类别：</th>
			        <td>
			        	<form:select path="CDictionaryByLpCategoryMain.id" required="true">
				          	<form:option value="">请选择</form:option>
				          	<form:options items="${labProjectMain}" itemLabel="CName" itemValue="id"/>
			        	</form:select>
			        </td>
			        <th>实验类型：</th>
			        <td>
			        	<form:select path="CDictionaryByLpCategoryApp.id" required="true">
				          <form:option value="">请选择</form:option>
				          <form:options items="${labProjectApp}" itemLabel="CName" itemValue="id"/>
				        </form:select>
			        </td>
			      </tr>
			      <tr>
			        <th>实验性质：</th>
			        <td>
			        	<form:select path="CDictionaryByLpCategoryNature.id" required="true">
				          <form:option value="">请选择</form:option>
				          <form:options items="${labProjectNature}" itemLabel="CName" itemValue="id"/>
				        </form:select>
			        </td>
			        <th>实验者类型：</th>
			        <td>
			        	<form:select path="CDictionaryByLpCategoryStudent.id" required="true">
				          <form:option value="">请选择</form:option>
				          <form:options items="${labProjectStudent}" itemLabel="CName" itemValue="id"/>
				        </form:select>
			        </td>
			        <th>变动状态：</th><td>
				        <form:select path="CDictionaryByLpStatusChange.id" required="true">
				          <form:option value="">请选择</form:option>
				          <form:options items="${labProjectChange}" itemLabel="CName" itemValue="id"/>
				        </form:select>
			        </td>
			        <th>开放实验：</th>
			        <td>
			        	<form:select path="CDictionaryByLpCategoryPublic.id" required="true">
				          <form:option value="">请选择</form:option>
				          <form:options items="${labProjectPublic}" itemLabel="CName" itemValue="id"/>
				        </form:select>
			        </td>
			      </tr>
			      <tr>
			        <th>获奖等级：</th>
			        <td>
			        	<form:select path="CDictionaryByLpCategoryRewardLevel.id" required="true">
				          <form:option value="">请选择</form:option>
				          <form:options items="${labProjectRewardLevel}" itemLabel="CName" itemValue="id"/>
				        </form:select>
			        </td>
			        <th>实验要求：</th>
			        <td>
			        	<form:select path="CDictionaryByLpCategoryRequire.id" required="true">
				          <form:option value="">请选择</form:option>
				          <form:options items="${labProjectRequire}" itemLabel="CName" itemValue="id"/>
				        </form:select>
			        </td>
			        <th>实验指导书：</th>
			        <td>
			        	<form:select path="CDictionaryByLpCategoryGuideBook.id" required="true">
				          <form:option value="">请选择</form:option>
				          <form:options items="${labProjectGuideBook}" itemLabel="CName" itemValue="id"/>
				        </form:select>
			        </td>
			        <th>学期：</th>
			        <td>
			        	<form:select path="schoolTerm.id" required="true">
				          <form:option value="">请选择</form:option>
				          <form:options items="${schoolTerms}" itemLabel="termName" itemValue="id"/>
				        </form:select>
			        </td>
			      </tr>
			      <tr>
			        <th>所属实验室：</th>
			        <td>
			        	<form:select path="labRoom.id" class="chzn-select" required="true">
				          <form:option value="">请选择</form:option>
				          <c:forEach items="${labRooms}" var="l">
				            <form:option value="${l.id}">${l.labRoomName}</form:option>
				          </c:forEach>
				        </form:select>
			        </td>
			        <th>所属学科：</th>
			        <td>
			        	<form:select path="systemSubject12.SNumber" class="chzn-select" required="true">
				          <form:option value="">请选择</form:option>
				          <c:forEach items="${subjects}" var="s">
				            <form:option value="${s.SNumber}">[${s.SNumber}]${s.SName}</form:option>
				          </c:forEach>
				        </form:select>
			        </td>
			        <th>所属专业：</th>
			        <td>
			        	<form:select path="systemMajor12.MNumber" class="chzn-select" required="true">
				          <form:option value="">请选择</form:option>
				          <c:forEach items="${majors}" var="m">
				            <form:option value="${m.MNumber}">[${m.MNumber}]${m.MName}</form:option>
				          </c:forEach>
				        </form:select>
			        </td>
			        <th>所属课程：</th>
			        <td>
			        	<form:select path="schoolCourseInfo.courseNumber" class="chzn-select" required="true">
				          <form:option value="">请选择</form:option>
				          <c:forEach items="${schoolCourseInfos}" var="c">
				            <form:option value="${c.courseNumber}">[${c.courseNumber}]${c.courseName}</form:option>
				          </c:forEach>
				        </form:select>
			        </td>
			      </tr>
			      <tr>
			        <th>主讲教师：</th>
			        <td>
			        	<form:select path="userByLpTeacherSpeakerId.username" id="teacher_speaker" class="chzn-select" required="true">
				          <form:option value="">请选择</form:option>
							<c:forEach items="${users}" var="user">
				            <form:option value="${user.username}">[${user.username}]${user.cname}</form:option>
							</c:forEach>
				        </form:select>
			        </td>
			        <th>辅导教师：</th>
			        <td>
			        	<form:select path="userByLpTeacherAssistantId.username" id="teacher_speaker" class="chzn-select">
				          <form:option value="">请选择</form:option>
							<c:forEach items="${users}" var="user">
				            <form:option value="${user.username}">[${user.username}]${user.cname}</form:option>
							</c:forEach>
				        </form:select>
			        </td>
			        <th>面向专业：</th>
			        <td>
			        	<form:select path="lpMajorFit" class="chzn-select" required="true" multiple="true" >
					      <c:forEach items="${majorList}" var="m">
					        	<form:option value="${m.MNumber}" selected="selected">[${m.MNumber}]${m.MName}</form:option>
					      </c:forEach>
				          <c:forEach items="${majors}" var="m">
				            <form:option value="${m.MNumber}">[${m.MNumber}]${m.MName}</form:option>
				          </c:forEach>
				        </form:select>
			        </td>
			        <th>指定审核人：</th><td>${operationItem.userByLpCheckUser.cname}[${operationItem.userByLpCheckUser.username}]</td>
			      </tr>
			</table>
		</div>
		<div class="edit-action">
		<input class="btn" id="save" type="submit" value="保存" />
		<input class="btn btn-return" id="save" type="button" value="返回" /> 
		</div>
	</form:form>
    <script type="text/javascript">
		$(".btn-edit").click(function(){
			$(this).parent().next().slideUp(); //原信息隐藏
			$(this).parent().next().next().children(".edit-edit").slideDown();//修改信息显示
			$(this).hide();//修改按钮隐藏
			$(this).parent().next().next().children(".edit-action").show();	//返回保存按钮显示					
		});
		$(".btn-return").click(function(){
			$(this).parent().hide();//返回保存按钮隐藏
			$(this).parent().parent().prev().prev().children(".btn-edit").show();//修改按钮显示
			$(this).parent().prev().slideUp();//修改信息隐藏
			$(this).parent().parent().prev().slideDown();//原始信息显示

		})
	</script>
    
  </div>
  </div>
  </div>
  </div>
  
  <!-- 材料使用 -->
  
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">材料使用</div>
	  <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR,ROLE_EXPERIMENTALTEACHING,ROLE_SUPERADMIN">
	  	<a class="btn btn-new" onclick="addMaterialRecord();">添加</a>
	  </sec:authorize>	
  </div>
   <table>
				      <thead>
				      <tr>
				      	
				        <th>序号</th>
				        <th>材料名称</th>
				        <th>材料类型</th>
				        <th>型号/规格</th>
				        <th>单位</th>
				        <th>数量</th>
				        <%--<th>金额（元）</th>--%>
				        <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR,ROLE_EXPERIMENTALTEACHING,ROLE_SUPERADMIN">
				        <th>操作</th>
				        </sec:authorize>
				        
				      </tr>
				      </thead>
				      <tbody>
				      <c:forEach items="${listItemMaterialRecord}" var="curr" varStatus="i">
				      <tr>
				      	
				        <td>${i.count}</td>
				        <td>${curr.assetCabinetWarehouseAccessRecord.asset.chName}</td>
				         <td>
				        	<c:if test="${curr.assetCabinetWarehouseAccessRecord.asset.category eq 0}">
								       试剂
							</c:if>
							<c:if test="${curr.assetCabinetWarehouseAccessRecord.asset.category eq 1}">
								       耗材
							</c:if>
						</td>
				        <td>${curr.assetCabinetWarehouseAccessRecord.asset.specifications}</td>
				        <td>${curr.assetCabinetWarehouseAccessRecord.asset.unit}</td>
				        <td>${curr.lpmrQuantity}</td>
				        <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR,ROLE_EXPERIMENTALTEACHING,ROLE_SUPERADMIN">
				       	<td>
				          <a href="javascript:void(0)" onclick="editMaterialRecord(${curr.lpmrId});">编辑</a>
				          <a href="${pageContext.request.contextPath}/operation/deleteItemMaterialRecordNew?mrId=${curr.lpmrId}&itemId=${operationItem.id}&flag=${flag}&status=${status}" onclick="return confirm('确认要删除吗？')">删除</a>
				        </td>
				        </sec:authorize>
				      </tr>
				      </c:forEach>
				      </tbody>
				    </table>
  </div>
  </div>
  </div>
  </div>
  
 <!-- 仪器设备 -->
    <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">仪器设备</div>
	  <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR,ROLE_EXPERIMENTALTEACHING,ROLE_SUPERADMIN">
	  <a class="btn btn-new" onclick="addDevices();">添加</a>
	  </sec:authorize>
  </div>
  <table>
      <thead>
      <tr>
        <th>序号</th>
        <th>类型</th>
        <th>设备编号</th>
        <th>设备名称</th>
        <th>规格</th>
        <th>型号</th>
        <th>单价</th>
        <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR,ROLE_EXPERIMENTALTEACHING,ROLE_SUPERADMIN">
        <th>操作</th>
        </sec:authorize>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${listItemDevice}" var="curr" varStatus="i">
      <tr>
        <td>${i.count}</td>
        <td>${curr.CDictionary.CName}</td>
        <td>${curr.labRoomDevice.schoolDevice.deviceNumber}</td>
        <td>${curr.labRoomDevice.schoolDevice.deviceName}</td>
        <td>${curr.labRoomDevice.schoolDevice.deviceFormat}</td>
        <td>${curr.labRoomDevice.schoolDevice.devicePattern}</td>
        <td>${curr.labRoomDevice.schoolDevice.devicePrice}</td>
        <sec:authorize ifAnyGranted="ROLE_EXCENTERDIRECTOR,ROLE_EXPERIMENTALTEACHING,ROLE_SUPERADMIN">
        <td>
          <a href="${pageContext.request.contextPath}/operation/deleteItemDeviceNew?itemDeviceId=${curr.id}&itemId=${operationItem.id}&flag=${flag}&status=${status}" onclick="return confirm('确认要删除吗？')">删除</a>
        </td>
        </sec:authorize>
       </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</div>
</div>
</div>

 <div id="editMaterialRecord" class="easyui-window" closed="true" modal="true" minimizable="true" title="材料使用记录" style="width: 600px;height: 500px;padding: 20px">
  <div class="content-box">
  <form:form name="form_material" method="post" modelAttribute="operationItemMaterialRecord">
  <table class="color_tb">
  <%--<tr>
    <td>材料名称</td><td><form:input path="lpmrName" id="lpmr_name" /><form:hidden path="lpmrId" /><form:hidden path="operationItem.id" id="item_id" value="${operationItem.id}"/></td>
  </tr>--%>
    <tr>
		<td>材料名称</td>
		<td>
			<%--<form:input path="lpmrName" id="lpmr_name" />--%><form:hidden path="lpmrId" />
			<form:hidden path="operationItem.id" id="item_id" value="${operationItem.id}"/>
			<form:select id="id" path="assetCabinetWarehouseAccessRecord.id" class="chzn-select" onChange="getSpec(this)">
  			<form:option value="">请选择</form:option>
  			<c:forEach items="${assetRecords}" var="curr">
  					<form:option value="${curr.id}">${curr.asset.chName}[${curr.asset.specifications}]</form:option>
  			</c:forEach>
  			</form:select>
		</td>
	</tr>
  <%--<tr>
    <td>材料类型</td>
    <td>
      <form:select path="CDictionary.id" id="lpmr_category">
        <form:options items="${categoryMaterialRecordMain}" itemLabel="CName" itemValue="id"/>
      </form:select>
    </td>
  </tr>--%>
  <%--<tr>
    <td>型号/规格</td><td><form:input path="lpmrModel" id="lpmr_model" /></td>
  </tr>--%>
  <%--<tr>
    <td>单位</td><td><form:input path="lpmrUnit" id="lpmr_unit" /></td>
  </tr>--%>
  <tr>
    <td>数量</td><td><form:input path="lpmrQuantity" id="lpmr_quantity" /><font id="unit"></font></td>
  </tr>
  <%--<tr>
    <td>金额</td><td><form:input path="lpmrAmount" id="lpmr_amount" /></td>
  </tr>--%>
  <tr>
    <td>备注</td><td><form:textarea path="lpmrRemark" id="lpmr_remark" /></td>
  </tr>
  </table>
  <div class="moudle_footer">
    <div class="submit_link">
        <input class="btn" id="save" type="button" onclick="saveMaterialRecord();" value="确定">
    </div>
  </div>
  </form:form>
</div>
</div>	

<div id="addDevice" class="easyui-window" closed="true" modal="true" minimizable="true" title="实验室设备" style="width: 600px;height: 500px;padding: 20px">
  <div class="content-box">
  <form name="device_form" method="post">
    <table>
      <tr>
        <td>类型</td>
        <td>
           <select name="device_category" id="device_category">
             <option value="1">公用</option>
             <option value="2">专用</option>
           </select>
        </td>
      </tr>
    </table>
    <table>
      <thead>
      <tr>
        <th><input id="check_all" type="checkbox" onclick="checkAll();"/></th>
        <th>设备编号</th>
        <th>设备名称</th>
        <th>领用单位号</th>
        <th>型号</th>
        <th>规格</th>
        <th>价格</th>
        <th>地点</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${listLabRoomDevice}" var="curr">
        <tr>
          <td><input id="device_${curr.id}" name="itemDevice" type="checkbox" value="${curr.id}"/></td>
          <td>${curr.schoolDevice.deviceNumber}</td>
          <td>${curr.schoolDevice.deviceName}</td>
          <td>${curr.schoolDevice.schoolAcademy.academyNumber}</td>
          <td>${curr.schoolDevice.devicePattern}</td>
          <td>${curr.schoolDevice.deviceFormat}</td>
          <td>${curr.schoolDevice.devicePrice}</td>
          <td>${curr.schoolDevice.deviceAddress}</td>
        </tr>
      </c:forEach>
      <tr>
          <td colspan="100" style="text-align:right;"><input type="button" value="提交" onclick="submitForm();"/></td>
      </tr>
      </tbody>
    </table>
  </form>
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
