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
    <!-- 文件上传的样式和js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>  
  <style type="text/css">
  	fieldset th{
  		text-align:left;
  	}
  </style>
  <script type="text/javascript">
  function uploaddocment(){
		
		 //获取当前屏幕的绝对位置
      var topPos = window.pageYOffset;
      //使得弹出框在屏幕顶端可见
      $('#searchFile').window({left:"0px", top:topPos+"px"});
		 $('#searchFile').window('open');
		 
		 $('#file_upload').uploadify({
			'formData':{id:1},    //传值
         'swf': '${pageContext.request.contextPath}/swfupload/swfupload.swf',  
         'uploader':'${pageContext.request.contextPath}/operation/uploadItemdocument;jsessionid=<%=session.getId()%>',		//提交的controller和要在火狐下使用必须要加的id
         buttonText:'选择文件',
	       'onUploadSuccess' : function(file, data, response) {
 		  
				   $("#doc").append(data); 
		 },
		 onQueueComplete : function(data) {
		   var ss="";
		   
		    $("tr[id*='s_']").each(function(){
	         var eval1=$(this).attr("id");
	          var str1= eval1.substr(eval1.indexOf("_")+1 ,eval1.lenght);  
	         var vals1=str1+"_"+$(this).attr("value");
	        
	         ss+=str1+",";
	         });
		   
         $("#docment").attr("value",ss); 
          $('#searchFile').window('close');	
		 }
     });
		
	}
	
	function delectuploaddocment(s){
	if(confirm( '你真的要删除吗？ ')==false){return   false;}else{ 
	  $.post('${pageContext.request.contextPath}/operation/delectdnolinedocment?idkey='+s+'',function(data){  //serialize()序列化
			   if(data=="ok"){
			   $("#s_"+s+"").empty();
			   
			   }
			
		 });
		 }
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
  //获取教师数据
  function ajaxGetUser(id){
    $.ajax({
      type:"GET",
      data:{academyNumber:${schoolAcademy.academyNumber},role:"1"},  //role=1是教师
      url:"${pageContext.request.contextPath}/operation/ajaxGetUser",
      dataType:"json",
      success:function(data){
        $.each(data, function(i, obj){
          $("#"+id).append("<option value='"+obj.username+"'>"+obj.cname+"["+obj.username+"]</option>");
        })
        
        $("#"+id).trigger("liszt:updated");
        alert("获取成功！");
      }
    });
  }
  
  //2015-09-10新增，为了让新建页面中有添加材料和仪器设备
  
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
    //使得弹出框在屏幕顶端可见
    $('#editMaterialRecord').window({left:"100px", top:topPos+"px"});

    $("#editMaterialRecord").window('open');
  }
  
  function saveMaterialRecord()
  {
   document.form_material.action="${pageContext.request.contextPath}/operation/saveItemMaterialRecordNew";
    if(isNaN($("#lpmr_quantity").val())){
			alert("【数量】填写数字");
			return false;
		}
    document.form_material.submit();
    
  }
  
  function checkRequired(){
	  
	  if($("#lpName").val() == ""){
		  alert("请填写实验名称!");
	  }
	  else if($("#lpDepartmentHours").val() == ""){
		  alert("请填写实验学时!");
	  }
	  else if($("#lpDepartmentHoursTotal").val() == ""){
		  alert("请填写实验总学时!");
	  }
	  /*else if($("#lpStudentNumber").val() == ""){
		  alert("请填写实验者人数!");
	  }
	  else if($("#labRoomId").val() == ""){
		  alert("请选择所属实验室!");
	  }
	  else if( $("#schoolCourseInfo").val() == ""){
		  alert("请选择所属课程!");
	  }
	  else if($("#teacher_speaker").val() == ""){
		  alert("请选择主讲教师!");
	  }*/
	  else if($("#outline").val() == ""){
		  alert("请选择所属大纲!");
	  } 
	  else{
		  document.edit_form.action="${pageContext.request.contextPath}/operation/saveOperationItemforxidlims?toMyList="+${isMine} ;
		  document.edit_form.submit();
	  }
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
	  
	  document.device_form.action="${pageContext.request.contextPath}/operation/saveItemDevice?itemId=${operationItem.id}&category="+category+"&ids="+ids;
      document.device_form.submit();
	}
	else
	{
	  alert("至少选择一个设备！");
	}
  }
  
  /*$(document).ready(function(){
     if(${flagId==10}){
    	 alert("项目卡片保存成功！")
     }
	});*/
  
  
  //保存新建/编辑 的实验室项目卡
  function saveEditForm(){
	  if($("#lpGuideBookTitle").val()==""){
		  alert("您还未填写“指导书名称”一栏，暂不能保存！")
	  }else{
	  	document.edit_form.submit();
	  }
  }
  </script>
 <style>
  
fieldset label {
    display: block;
    float: left;
    font-weight: bold;
    height: 25px;
    line-height: 25px;
    margin: -5px 0 5px;
    padding-left: 10px;
    text-shadow: 0 1px 0 #fff;
    text-transform: uppercase;
    width: 90%;
}  
</style>
</head>
  
<body>

  <div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">实验项目</a></li>
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
      <div id="title"><c:if test="${isEdit}">编辑</c:if><c:if test="${!isEdit}">新建</c:if>实验项目</div>
    </div>
    
  <!-- 表单 -->
    <form:form name="edit_form" action="${pageContext.request.contextPath}/operation/saveOperationItemforxidlims?toMyList=${isMine}" method="POST" modelAttribute="operationItem">
    <div class="new-classroom">
      <fieldset>
        <form:hidden path="id"/>
        <label>实验名称<font color="red">*</font>：</label>
        <form:input path="lpName" id="lpName" class="easyui-validatebox" required="true"/>
      </fieldset>
      <fieldset>
        <label>实验学时<font color="red">*</font>：（该项目所需学时）</label>
        <form:input path="lpDepartmentHours" id="lpDepartmentHours" class="easyui-validatebox" required="true"
        onkeyup="value=value.replace(/[^\d]/g,'') "   
		placeholder="请输入大于零的整数"
        />
      </fieldset>
      <fieldset>
        <label>实验总学时<font color="red">*</font>：（实验项目对应课程的实验总学时，包括课内、课外）</label>
        <form:input path="lpDepartmentHoursTotal" id="lpDepartmentHoursTotal" class="easyui-validatebox" required="true"
        onkeyup="value=value.replace(/[^\d]/g,'') "  
		placeholder="请输入大于零的整数"
        />
      </fieldset>
      <fieldset>
        <label>课程总学时：（实验项目对应课程总学时）</label>
        <form:input path="lpCourseHoursTotal" id="lpCourseHoursTotal" class="easyui-validatebox" 
        onkeyup="value=value.replace(/[^\d]/g,'') "  
		placeholder="请输入大于零的整数"
        />
      </fieldset><%--
      <fieldset>
        <label>计划学年总人数：</label>
        <form:input path="lpYearsPeopleNumberPlan" class="easyui-validatebox" required="true"
        onkeyup="value=value.replace(/[^\d]/g,'') "   
		placeholder="请输入大于零的整数"
        />
      </fieldset>
      <fieldset>
        <label>实验者人数<font color="red">*</font>：（该实验项目一学年内上课人数：一人做两次实验按一人计算）</label>
        <form:input path="lpStudentNumber" id="lpStudentNumber" class="easyui-validatebox" required="true"
        onkeyup="value=value.replace(/[^\d]/g,'') "  
		placeholder="请输入大于零的整数"
        />
      </fieldset>
      <fieldset>
        <label>实验套数：（该实验室可开出本实验项目的设备人数）</label>
        <form:input path="lpSetNumber" id="lpSetNumber" class="easyui-validatebox" 
        onkeyup="value=value.replace(/[^\d]/g,'') "
		placeholder="请输入大于零的整数"
        />
      </fieldset>
      <fieldset>
        <label>每组人数：（教学实验项目中在每套仪器设备上完成本实验项目的人数）</label>
        <form:input path="lpStudentNumberGroup" id="lpStudentNumberGroup" class="easyui-validatebox" 
        onkeyup="value=value.replace(/[^\d]/g,'') "   
		placeholder="请输入大于零的整数"
        />
      </fieldset>
      <fieldset>
        <label>循环次数：</label>
        <form:input path="lpCycleNumber" class="easyui-validatebox" required="true"
        onkeyup="value=value.replace(/[^\d]/g,'') "   
		placeholder="请输入大于零的整数"
        />
      </fieldset>
      --%>
      <%--<fieldset>
        <label>指导书名称：</label>
        <form:input path="lpGuideBookTitle" id="lpGuideBookTitle" class="easyui-validatebox" />
      </fieldset>
      <fieldset>
        <label>编者：</label>
        <form:input path="lpGuideBookAuthor" id="lpGuideBookAuthor" class="easyui-validatebox" />
      </fieldset>
      <fieldset>
        <label>考核方法：</label>
        <form:input path="lpAssessmentMethods" id="lpAssessmentMethods" class="easyui-validatebox" />
      </fieldset>
      <fieldset>
      	<label>计划学年总人数：</label>
        <form:input path="lpYearsPeopleNumberPlan" id="lpYearsPeopleNumberPlan" class="easyui-validatebox"/>
      </fieldset>
      <fieldset>
        <label>循环次数：</label>
        <form:input path="lpCycleNumber" id="lpCycleNumber" class="easyui-validatebox" />
      </fieldset>
      <fieldset>
        <label>项目简介：</label>
        <fieldset class="introduce-box">
			<legend>文档名称<input type="hidden" value="" id="xsd"></legend>
				<table>
					<tr >
						<td>文档名称</td>
						<td>操作&nbsp;<input type="button" onclick="uploaddocment()" value="上传附件"/>
							<input type="hidden" name="docment" id="docment" />
						</td>
						<table>
						 <c:forEach items="${operationItem.commonDocuments}" var="k">
							<tr><td>${k.documentName}</td><td><a href="${pageContext.request.contextPath}/operation/downloadfile?idkey=${k.id}">下载</a></td></tr>
						</c:forEach>	
                    </table>
					</tr>
				</table>
				<table>
					<tbody id="doc"></tbody>
					
				</table>	
		</fieldset>
        <form:textarea path="lpIntroduction" id="lpIntroduction" cssStyle=" height:150px;"/>
      </fieldset>
      <fieldset>
        <label>课前准备：</label>
        <form:textarea path="lpPreparation" id="lpPreparation" cssStyle=" height:150px;"/>
      </fieldset>
      <fieldset style="width:18.8%">
        <label>实验类别：</label>
        <form:select path="CDictionaryByLpCategoryMain.id" id="CDictionaryByLpCategoryMain">
          <form:option value="">- - - -请选择- - - -</form:option>
          <form:options items="${labProjectMain}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset style="width:18.8%">
        <label>实验类型：</label>
        <form:select path="CDictionaryByLpCategoryApp.id" id="CDictionaryByLpCategoryApp" >
          <form:option value="">- - - -请选择- - - -</form:option>
          <form:options items="${labProjectApp}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset style="width:18.8%">
        <label>实验性质：</label>
        <form:select path="CDictionaryByLpCategoryNature.id" id="CDictionaryByLpCategoryNature">
          <form:option value="">- - - -请选择- - - -</form:option>
          <form:options items="${labProjectNature}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset style="width:18.8%">
        <label>实验者类型：</label>
        <form:select path="CDictionaryByLpCategoryStudent.id" id="CDictionaryByLpCategoryStudent" >
          <form:option value="">- - - -请选择- - - -</form:option>
          <form:options items="${labProjectStudent}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset style="width:18.8%">
        <label>变动状态：</label>
        <form:select path="CDictionaryByLpStatusChange.id" id="CDictionaryByLpStatusChange" >
          <form:option value="538">未变动</form:option>
          <form:option value="">----请选择----</form:option>
          <form:options items="${labProjectChange}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset style="width:18.8%"> 
        <label >开放实验：</label>
        <form:select path="CDictionaryByLpCategoryPublic.id" id="CDictionaryByLpCategoryPublic">
          <form:option value="478">校内开放</form:option>
          <form:option value="">----请选择----</form:option>
          <form:options items="${labProjectPublic}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset style="width:18.8%">
        <label>获奖等级：</label>
        <form:select path="CDictionaryByLpCategoryRewardLevel.id" id="CDictionaryByLpCategoryRewardLevel">
          <form:option value="480">未获奖</form:option>
          <form:option value="">----请选择----</form:option>
          <form:options items="${labProjectRewardLevel}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset style="width:18.8%">
        <label>实验要求：</label>
        <form:select path="CDictionaryByLpCategoryRequire.id" id="CDictionaryByLpCategoryRequire">
          <form:option value="">- - - -请选择- - - -</form:option>
          <form:options items="${labProjectRequire}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset>
        <label>实验指导书：</label>
        <form:select path="CDictionaryByLpCategoryGuideBook.id" id="CDictionaryByLpCategoryGuideBook">
          <form:option value="">- - - -请选择- - - -</form:option>
          <form:options items="${labProjectGuideBook}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      --%><fieldset>
        <label>学期<font color="red">*</font>：</label>
        <form:select path="schoolTerm.id" id="schoolTermId" required="true">
          <form:option value="${currSchoolTerm.id }">${currSchoolTerm.termName }</form:option>
          <form:options items="${schoolTerms}" itemLabel="termName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset>
        <label>所属实验室：</label>
        <form:select path="labRoom.id" id="labRoomId" name="labRoomId" class="chzn-select">
          <form:option value="">- - - -请选择- - - -</form:option>
          <c:forEach items="${labRooms}" var="l">
            <form:option value="${l.id}">${l.labRoomName}-${l.labRoomAddress }</form:option>
          </c:forEach>
        </form:select>
      </fieldset><%--
      <fieldset>
        <label>所属学科：</label>
        <form:select path="systemSubject12.SNumber" id="systemSubject12" class="chzn-select">
          <form:option value="">- - - -请选择- - - -</form:option>
          <c:forEach items="${subjects}" var="s">
            <form:option value="${s.SNumber}">[${s.SNumber}]${s.SName}</form:option>
          </c:forEach>
        </form:select>
      </fieldset>
      <fieldset>
        <label>所属专业：</label>
        <form:select path="systemMajor12.MNumber" id="systemMajor12" class="chzn-select">
          <form:option value="">- - - -请选择- - - -</form:option>
          <c:forEach items="${majors}" var="m">
            <form:option value="${m.MNumber}">[${m.MNumber}]${m.MName}</form:option>
          </c:forEach>
        </form:select>
      </fieldset>
      <fieldset>
        <label>所属课程<font color="red">*</font>：</label>
        <form:select path="schoolCourseInfo.courseNumber" id="schoolCourseInfo" class="chzn-select" required="true">
          <form:option value="">- - - -请选择- - - -</form:option>
          <c:forEach items="${schoolCourseInfos}" var="c">
            <form:option value="${c.courseNumber}">[${c.courseNumber}]${c.courseName}--${c.totalHours }学时</form:option>
          </c:forEach>
        </form:select>
      </fieldset>
      <fieldset>
        <label>面向专业：</label>
        <form:select path="lpMajorFit" id="lpMajorFit" class="chzn-select"  multiple="true" >
	        <c:forEach items="${majorList}" var="m">
	        	<form:option value="${m.MNumber}" selected="selected">[${m.MNumber}]${m.MName}</form:option>
	        </c:forEach>
	        
          <c:forEach items="${majors}" var="m">
            <form:option value="${m.MNumber}">[${m.MNumber}]${m.MName}</form:option>
          </c:forEach>
        
        </form:select>
      </fieldset>
      <fieldset>
      <c:if test="${flagId==0 }">
        <label>主讲教师<font color="red">*</font>：</label> <!-- (<a onclick="ajaxGetUser('teacher_speaker');" style="color: #0000ff">点击</a>获取教师数据) -->
        <form:select path="userByLpTeacherSpeakerId.username" id="teacher_speaker" class="chzn-select" required="true">
          <form:option value="">- - - -请选择- - - -</form:option>
          <c:forEach items="${users}" var="user">
            <form:option value="${user.username}">[${user.username}]${user.cname}</form:option>
        </c:forEach>
        </form:select>
      </c:if>
      <c:if test="${flagId==1 || flagId==10 }">
      	<label>主讲教师<font color="red">*</font>：</label>
      
      <form:select path="userByLpTeacherSpeakerId.username" id="teacher_speaker" class="chzn-select" required="true">
      	<form:option value="${operationItem.userByLpTeacherSpeakerId.username }">${operationItem.userByLpTeacherSpeakerId.cname }</form:option>  
      	<c:forEach items="${users}" var="user">
            <form:option value="${user.username}">[${user.username}]${user.cname}</form:option>
        </c:forEach>
      </form:select>
      </c:if>
      
      </fieldset>
      <fieldset>
      <c:if test="${flagId==0 }">
        <label>辅导教师：</label><!-- (<a onclick="ajaxGetUser('teacher_assistant');" style="color: #0000ff">点击</a>获取教师数据) -->
        <form:select path="userByLpTeacherAssistantId.username" id="teacher_assistant" class="chzn-select" >
          <form:option value="">- - - -请选择- - - -</form:option>
          <c:forEach items="${users}" var="user">
            <form:option value="${user.username}">[${user.username}]${user.cname}</form:option>
        </c:forEach>
        </form:select>
      </c:if>
      <c:if test="${flagId==1 || flagId==10 }">
        <label>辅导教师：</label>
        <form:select path="userByLpTeacherAssistantId.username" id="teacher_assistant" class="chzn-select" >
          <form:option value="${operationItem.userByLpTeacherAssistantId.username }">${operationItem.userByLpTeacherAssistantId.cname }</form:option>
          <c:forEach items="${users}" var="user">
            <form:option value="${user.username}">[${user.username}]${user.cname}</form:option>
        </c:forEach>
        </form:select>
      </c:if>
      </fieldset>
      
      --%>
      <fieldset>
        <label>所属大纲<font color="red">*</font>：</label>
        <form:select path="operationOutline.id" id="outline" class="chzn-select" required="true" >
          <form:option value="">- - - -请选择- - - -</form:option>
          <c:forEach items="${outlines}" var="curr">
            <form:option value="${curr.id}">${curr.labOutlineName}</form:option>
        </c:forEach>
        </form:select>
      </fieldset>
      <fieldset>
        <label>基本要求：</label>
        <form:textarea path="basicRequired" id="basicRequired" cssStyle=" height:150px;"/>
      </fieldset>
    </div>
    <div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="button" onclick="checkRequired()" value="保存">
          <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
        </div>
    </div>
    </form:form>
    
    
    
      <!-- 实验项目基本信息提交后出现的东西 -->
        <%--<fieldset class="introduce-box" style="float:none;">
		<label><a href="${pageContext.request.contextPath}/operation/editOperationItem?operationItemId=${operationItem.id}&&isMine=1&flagId=1">材料使用</a></label>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
			
				<c:if test="${flagId==0}">
					<td>
						<font color=red>请您提交保存后，再对实验材料进行添加。</font>
					</td>
				</c:if>
				<c:if test="${flagId==1 || flagId==10 }">
				
				  <div id="TabbedPanels1" class="TabbedPanels">
				  <div class="TabbedPanelsContentGroup">
				  <div class="TabbedPanelsContent">
				        <li><input type="button" onclick="addMaterialRecord();" value="添加"></li>
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
					    <form:select id="id" path="assetCabinetWarehouseAccessRecord.id" class="chzn-select" onChange="getSpec(this)">
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
				    <td>数量</td><td><form:input path="lpmrQuantity" id="lpmr_quantity" /><font id="unit"></font></td>
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

				  
				</c:if>
			</tr>
		</table>
		</fieldset>
		
		
		
		--%><!-- 设备仪器开始  -->
		
		<%--<fieldset class="introduce-box" style="float:none;">
		<label><a href="${pageContext.request.contextPath}/operation/editOperationItem?operationItemId=${operationItem.id}&&isMine=1&flagId=1">仪器设备</a></label>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
			
				<c:if test="${flagId==0}">
					<td>
						<font color=red>请您提交保存后，再对仪器设备进行添加。</font>
					</td>
				</c:if>
				<c:if test="${flagId==1 || flagId==10 }">
				
				<div id="TabbedPanels1" class="TabbedPanels">
 
					  <div class="TabbedPanelsContentGroup">
					  <div class="TabbedPanelsContent">
					    <div class="tool-box">
					      <ul>
					        <li><input type="button" onclick="addDevices();" value="批量添加"></li>
					      </ul>
					    </div>
					    
					    <div class="content-box">
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
					        <th>操作</th>
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
					        <td>
					          <a href="${pageContext.request.contextPath}/operation/deleteItemDevice?itemDeviceId=${curr.id}&itemId=${operationItem.id}" onclick="return confirm('确认要删除吗？')">删除</a>
					        </td>
					      </tr>
					      </c:forEach>
					      </tbody>
					    </table>
					    </div>
					  </div>
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
					          <td colspan="100" style="text-align:right;"><input type="button" value="保存" onclick="submitForm();"/></td>
					      </tr>
					      </tbody>
					    </table>
					  </form>
					  </div>
					  </div>

				  
				</c:if>
			</tr>
		</table>
		</fieldset>
		<div id="searchFile" class="easyui-window" title="上传附件" closed="true" iconCls="icon-add" style="width:400px;height:200px">
	    <form id="form_file" method="post" enctype="multipart/form-data">
           <table  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
            <td>
          	<div id="queue"></div>
		    <input id="file_upload" name="file_upload" type="file" multiple="true">
            </tr>   
            </table>
         </form>
     </div>	
		<!-- 设备仪器结束  -->
		
		<!-- 保存整个实验室项目卡 -->
		<form:form action="${pageContext.request.contextPath}/operation/saveOperationItemAll?isMine=${isMine }" method="POST" ><!-- modelAttribute="operationItem"没有加这个 -->
			<div class="moudle_footer">
	        <div class="submit_link">
	          <input class="btn" id="save" type="submit" value="保存">
	          <input class="btn btn-return" type="button" value="保存" onclick="window.history.go(-1)"> 
	        </div>
	       </div>
		</form:form>--%>
		
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
