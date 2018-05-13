<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  
  <script type="text/javascript">
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
    $("#editMaterialRecord").window('open');
  }
  
  function saveMaterialRecord()
  {
    document.form_material.action="${pageContext.request.contextPath}/operation/saveItemMaterialRecordNewAfterAudit?lp_name="+${lp_name}+"&term_id="+${term_id}+"&course_number="+${course_number}+"&lp_create_user="+${lp_create_user}+"&id="+${id}+ "&page="+${page}+ "&status="+${status}+"&orderBy="+${orderBy}+"&isMine=1";
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
//跳转到仪器设备页面

function listItemDeviceRest(id){
	var url = "${pageContext.request.contextPath}/operationRest/listItemDeviceRest/" + "${lp_name}" + "/"+ ${term_id} + "/" + ${course_number} + "/" + "${lp_create_user}" + "/${page}"+ "/${isMine}"+ "/${status}"+ "/${orderBy}/"+id;
	
	window.location.href=url;
}
//查看详情--材料
function listItemMaterialRecordRest(id){
	var url = "${pageContext.request.contextPath}/operationRest/listItemMaterialRecordRest/" + "${lp_name}" + "/"+ ${term_id} + "/" + ${course_number} + "/" + "${lp_create_user}" + "/${page}"+ "/${isMine}"+ "/${status}"+"/${orderBy }/" + id;
	window.location.href=url;
	}
</script>
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0);">实验项目管理</a></li>
			<li class="end"><a href="javascript:void(0);">实验项目详细信息</a></li>
			<c:if test="${isMine==0 }">
			<a class="btn btn-return" href="javascript:void(0);" onclick="listOperationItemRest();">返回</a>
			</c:if>
			<c:if test="${isMine==1 }">
			<a class="btn btn-return" href="javascript:void(0);" onclick="listMyOperationItemRest();">返回</a>
			</c:if>
			<c:if test="${isMine==3 }">
			<a class="btn btn-return" href="${pageContext.request.contextPath}/operation/listOperationItemImport?currpage=1&orderBy=${orderBy }">返回</a>
			</c:if>
		</ul>
	</div>
  </div>
  
  <div class="content-box">
    <table>
      <tr>
        <th>实验名称：</th><td>${operationItem.lpName}</td>
        <th>实验学时：</th><td>${operationItem.lpDepartmentHours}</td>
       
      </tr>
      <tr>
       <th>实验总学时：</th><td>${operationItem.lpDepartmentHoursTotal}</td>
        <th>课程总学时：</th><td>${operationItem.lpCourseHoursTotal}</td>
      </tr>
      <%--<tr>
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
      --%><tr>
        <%--<th>获奖等级：</th><td>${operationItem.CDictionaryByLpCategoryRewardLevel.CName}</td>
        <th>实验要求：</th><td>${operationItem.CDictionaryByLpCategoryRequire.CName}</td>
        <th>实验指导书：</th><td>${operationItem.CDictionaryByLpCategoryGuideBook.CName}</td>
        --%><th>学期：</th><td>${operationItem.schoolTerm.termName}</td>
      </tr>
      <tr>
        <th>所属实验室：</th><td>${operationItem.labRoom.labRoomName}</td>
        <%--<th>所属学科：</th><td>${operationItem.systemSubject12.SName}[${operationItem.systemSubject12.SNumber}]</td>
        <th>所属专业：</th><td>${operationItem.systemMajor12.MName}[${operationItem.systemMajor12.MNumber}]</td>
        <th>所属课程：</th><td>${operationItem.schoolCourseInfo.courseName}[${operationItem.schoolCourseInfo.courseNumber}]</td>
      --%></tr>
      <tr>
        <th>所属实验大纲：</th><td>${operationItem.operationOutline.labOutlineName}</td>
        </tr>
        <tr>
        <th>基本要求：</th><td>${operationItem.basicRequired}</td>
        </tr>
      <%--
      <tr>
        <th>主讲教师：</th><td>${operationItem.userByLpTeacherSpeakerId.cname}[${operationItem.userByLpTeacherSpeakerId.username}]</td>
        <th>辅导教师：</th><td>${operationItem.userByLpTeacherAssistantId.cname}[${operationItem.userByLpTeacherAssistantId.username}]</td>
        <th>面向专业：</th><td>${majorStr}</td>
        <th>指定审核人：</th><td>${operationItem.userByLpCheckUser.cname}[${operationItem.userByLpCheckUser.username}]</td>
      </tr>
      <tr>
        <th>审核状态：</th><td>${operationItem.CDictionaryByLpStatusCheck.CName}</td>
      </tr>
    --%></table>
  </div>
  
  <%--<div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
		<li class="TabbedPanelsTab selected" id="s1"><a href="javascript:void(0);" onclick="listItemMaterialRecordRest(${operationItem.id});">材料使用</a></li>
		<li class="TabbedPanelsTab" id="s2"><a href="javascript:void(0);" onclick="listItemDeviceRest(${operationItem.id});">仪器设备</a></li>
	</ul>
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <li><input type="button" onclick="addMaterialRecord();" value="添加"></li>
    <div class="tool-box">
    </div>
    
    <div class="content-box">
    <table>
      <thead>
      <tr>
        <th>序号</th>
        <th>材料名称</th>
        <th>材料类型</th>
        <th>型号/规格</th>
        <th>单位</th>
        <th>数量</th>
        <th>金额（元）</th>
        <th>操作</th>
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
        <td>${curr.lpmrAmount}</td>
        <td>
          <a href="javascript:void(0)" onclick="editMaterialRecord(${curr.lpmrId});">编辑</a>
          <a href="${pageContext.request.contextPath}/operation/deleteItemMaterialRecord?mrId=${curr.lpmrId}&itemId=${operationItem.id}" onclick="return confirm('确认要删除吗？')">删除</a>
        </td>
      </tr>
      </c:forEach>
      </tbody>
    </table>
    </div>
  </div>
  </div>
  </div>
  
    <div id="editMaterialRecord" class="easyui-window" closed="true" modal="true" minimizable="true" title="材料使用记录" style="width: 600px;height: 500px;padding: 20px">
				  <div class="content-box">
				  <form:form name="form_material" method="post" modelAttribute="operationItemMaterialRecord" >
				  <table class="color_tb">
				  <tr>
				    <td>材料名称</td>
				    <td>
					    <form:input path="lpmrName" id="lpmr_name" /><form:hidden path="lpmrId" />
					    <form:hidden path="operationItem.id" id="item_id" value="${operationItem.id}"/>
					    <form:select id="id" path="assetCabinetWarehouseAccessRecord.id" class="chzn-select">
  						<form:option value="">请选择</form:option>
  							<c:forEach items="${assetRecords}" var="curr">
  								<form:option value="${curr.id}">${curr.asset.chName}[${curr.asset.specifications}]</form:option>
  							</c:forEach>
  						</form:select>
				    </td>
				  </tr>
				  <tr>
				    <td>材料类型</td>
				    <td>
				      <form:select path="CDictionary.id" id="lpmr_category">
				        <form:options items="${categoryMaterialRecordMain}" itemLabel="CName" itemValue="id"/>
				      </form:select>
				    </td>
				  </tr>
				  <tr>
				    <td>型号/规格</td><td><form:input path="lpmrModel" id="lpmr_model" /></td>
				  </tr>
				  <tr>
				    <td>单位</td><td><form:input path="lpmrUnit" id="lpmr_unit" /></td>
				  </tr>
				  <tr>
				    <td>数量</td><td><form:input path="lpmrQuantity" id="lpmr_quantity" /></td>
				  </tr>
				  <tr>
				    <td>金额</td><td><form:input path="lpmrAmount" id="lpmr_amount" /></td>
				  </tr>
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
--%></body>
</html>
