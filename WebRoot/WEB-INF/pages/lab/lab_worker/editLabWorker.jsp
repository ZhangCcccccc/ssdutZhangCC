<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
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
  $(function() {
    $("[id^='lwCategoryExpert']").click(function(){
    	clickCheckbox();
    });
});

   function clickCheckbox()
  {
	var i=0;
	
	$("[id^='lwCategoryExpert']").each(function(){
		if($(this).attr('checked'))
		{
			i++;
		}
	});

	if(i > 2)
	{
	    $("[id^='lwCategoryExpert']").removeAttr("checked");
		alert('专家类别不能多于2个！');
	}
  }
  </script>
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">实验室工作人员</a></li>
			<li class="end"><a href="javascript:void(0)">新建</a></li>
			
		</ul>
	</div>
  </div>
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
  <div class="title">
      <div id="title">新建实验室工作人员</div>
  </div>
  <form:form action="${pageContext.request.contextPath}/labRoom/saveLabWorker" method="POST" modelAttribute="labWorker">
  <div class="new-classroom">
  <fieldset>
    <form:hidden path="id"/>
    <label>编号：</label>
    <form:input path="lwCodeCustom" required="true"/>
  </fieldset>
  <%--<fieldset>
    <label>姓名：</label>
    <form:input path="user.username" class="chzn-select" required="true"/>
  </fieldset>--%>
  
  <fieldset>
    <label>姓名：</label>
    <form:select path="user.username" class="chzn-select">
	    <form:option value="">请选择</form:option>
	    <c:forEach items="${listUser}" var="curr">
	    	<form:option value="${curr.key }">${curr.value }</form:option>
	    </c:forEach>
    </form:select>
  </fieldset>
  <fieldset>
    <label>毕业学校：</label>
    <form:input path="lwGraduationSchool" required="true"/>
  </fieldset>
  <fieldset>
    <label>所学专业：</label>
    <form:input path="lwGraduationMajor" required="true"/>
  </fieldset>
  <fieldset>
    <label>实验室工龄：</label>
    <form:input path="lwWorkAge" required="true"/>
  </fieldset>
  <fieldset>
    <label>职务：</label>
    <form:input path="lwDuty" required="true"/>
  </fieldset>
  <fieldset>
    <label>技术等级：</label>
    <form:input path="lwSkillLevel" required="true"/>
  </fieldset>
  <fieldset>
    <label>业务专长：</label>
    <form:input path="lwProfessionSpecialty" required="true"/>
  </fieldset>
  <fieldset>
    <label>论文数量：</label>
    <form:input path="lwPaperQuantity" required="true"/>
  </fieldset>
  <fieldset>
    <label>著作数量：</label>
    <form:input path="lwBookQuantity" required="true"/>
  </fieldset>
  <fieldset>
    <label>项目名称：</label>
    <form:input path="lwLabProject" required="true"/>
  </fieldset>
  <fieldset>
    <label>国内培训时间（学历教育）：</label>
    <form:input path="lwTrainFormalInlandTime" required="true"/>
  </fieldset>
  <fieldset>
    <label>国内培训时间（非学历教育）：</label>
    <form:input path="lwTrainInformalInlandTime" required="true"/>
  </fieldset>
  <fieldset>
    <label>国内培训内容：</label>
    <form:input path="lwTrainInformalInlandContent" required="true"/>
  </fieldset>
  <fieldset>
    <label>国外培训时间（学历教育）：</label>
    <form:input path="lwTrainFormalAbroadTime" required="true"/>
  </fieldset>
  <fieldset>
    <label>国外培训时间（非学历教育）：</label>
    <form:input path="lwTrainInformalAbroadTime" required="true"/>
  </fieldset>
  <fieldset>
    <label>国外培训内容：</label>
    <form:input path="lwTrainInformalAbroadContent" required="true"/>
  </fieldset>
  <fieldset>
    <label>部门：</label>
    <form:select path="labCenter.id" class="chzn-select">
      <form:options items="${listLabCenter}" itemLabel="centerName" itemValue="id"/>
    </form:select>
  </fieldset>
  <fieldset>
    <label>性别：</label>
    <form:select path="lwSex">
      <form:option value="1">男</form:option>
      <form:option value="0">女</form:option>
    </form:select>
  </fieldset>
  <fieldset>
    <label>文化程度：</label>
    <form:select path="CDictionaryByLwAcademicDegree.id" class="chzn-select">
      <form:options items="${listAcademicDegree}" itemLabel="CName" itemValue="id"/>
    </form:select>
  </fieldset>
  <fieldset>
    <label>所属学科：</label>
    <form:select path="CDictionaryByLwSubject.id" class="chzn-select">
      <form:options items="${listSubject}" itemLabel="CName" itemValue="id"/>
    </form:select>
  </fieldset>
  <fieldset>
    <label>专业职称：</label>
    <form:select path="CDictionaryByLwSpecialtyDuty.id" class="chzn-select">
      <form:options items="${listSpecialtyDuty}" itemLabel="CName" itemValue="id"/>
    </form:select>
  </fieldset>
  <fieldset>
    <label>人员类别：</label>
    <form:select path="CDictionaryByLwCategoryStaff.id" class="chzn-select">
      <form:options items="${listCategoryStaff}" itemLabel="CName" itemValue="id"/>
    </form:select>
  </fieldset>
  <fieldset>
    <label>聘任情况：</label>
    <form:select path="CDictionaryByLwEmployment.id" class="chzn-select">
      <form:options items="${listEmployment}" itemLabel="CName" itemValue="id"/>
    </form:select>
  </fieldset>
  <fieldset>
    <label>成果奖励：</label>
    <form:select path="CDictionaryByLwReward.id" class="chzn-select">
      <form:options items="${listReward}" itemLabel="CName" itemValue="id"/>
    </form:select>
  </fieldset>
  <fieldset>
    <label>外语语种：</label>
    <form:select path="CDictionaryByLwForeignLanguage.id" class="chzn-select">
      <form:options items="${listForeignLanguage}" itemLabel="CName" itemValue="id"/>
    </form:select>
  </fieldset>
  <fieldset>
    <label>外语水平：</label>
    <form:select path="CDictionaryByLwForeignLanguageLevel.id" class="chzn-select">
      <form:options items="${listForeignLanguageLevel}" itemLabel="CName" itemValue="id"/>
    </form:select>
  </fieldset>
  <fieldset>
    <label>学位：</label>
    <form:select path="CDictionaryByLwDegree.id" class="chzn-select">
      <form:options items="${listDegree}" itemLabel="CName" itemValue="id"/>
    </form:select>
  </fieldset>
  <fieldset>
    <label>主要工作：</label>
    <form:select path="CDictionaryByLwMainWork.id" class="chzn-select">
      <form:options items="${listMainWork}" itemLabel="CName" itemValue="id"/>
    </form:select>
  </fieldset>
  <fieldset>
    <label>论文级别：</label>
    <form:select path="CDictionaryByLwPaperLevel.id" class="chzn-select">
      <form:options items="${listPaperLevel}" itemLabel="CName" itemValue="id"/>
    </form:select>
  </fieldset>
  <fieldset>
    <label>著作级别：</label>
    <form:select path="CDictionaryByLwBookLevel.id" class="chzn-select">
      <form:options items="${listBookLevel}" itemLabel="CName" itemValue="id"/>
    </form:select>
  </fieldset>
  <fieldset>
    <label>出生年月：</label>
    <input name="lwBirthday" class="easyui-datebox" value="<fmt:formatDate value='${labWorker.lwBirthday.time}' pattern='yyyy-MM-dd'/>" />
  </fieldset>
  <fieldset>
    <label>毕业时间：</label>
    <input name="lwGraduationTime" class="easyui-datebox" value="<fmt:formatDate value='${labWorker.lwGraduationTime.time}' pattern='yyyy-MM-dd'/>" />
  </fieldset>
  <fieldset>
    <label>工作时间：</label>
    <input name="lwWorkTime" class="easyui-datebox" value="<fmt:formatDate value='${labWorker.lwWorkTime.time}' pattern='yyyy-MM-dd'/>" />
  </fieldset>
  <fieldset>
    <label>评职时间：</label>
    <input name="lwProfessionTime" class="easyui-datebox" value="<fmt:formatDate value='${labWorker.lwProfessionTime.time}' pattern='yyyy-MM-dd'/>" />
  </fieldset>
  <fieldset>
    <label>获奖时间：</label>
    <input name="lwRewardTime" class="easyui-datebox" value="<fmt:formatDate value='${labWorker.lwRewardTime.time}' pattern='yyyy-MM-dd'/>" />
  </fieldset>
  <fieldset>
    <label>专家类别：</label>
    <c:forEach items="${listCategoryExpert}" var="curr">
      <form:checkbox path="lwCategoryExpert" value="${curr.id}"/>
      ${curr.CName}
    </c:forEach>
  </fieldset>
  <c:if test="${not empty labWorker.lwCategoryExpert}">
  <script>
  var str = "${labWorker.lwCategoryExpert}";
  var arr = str.split(",");
  
  $("[id^='lwCategoryExpert']").each(function(){
		if($.inArray($(this).val(), arr)>-1)
		{
			$(this).attr("checked", "checked");
		}
	});
  </script>
  </c:if>
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
		</script>
	<!-- 下拉框的js -->
  </div>
  </div>
  </div>
  </div>
  </div>
  
</body>
</html>
