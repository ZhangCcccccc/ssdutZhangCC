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
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
      <div id="title"><c:if test="${isEdit}">编辑</c:if><c:if test="${!isEdit}">新建</c:if>实验项目</div>
    </div>
    <form:form action="${pageContext.request.contextPath}/labProject/saveLabProject" method="POST" modelAttribute="labProject">
    <div class="new-classroom">
      <fieldset>
        <form:hidden path="id"/>
        <label>实验名称：</label>
        <form:input path="lpName" required="true"/>
      </fieldset>
      <fieldset>
        <label>实验学时：</label>
        <form:input path="lpDepartmentHours" required="true"/>
      </fieldset>
      <fieldset>
        <label>实验总学时：</label>
        <form:input path="lpDepartmentHoursTotal" required="true"/>
      </fieldset>
      <fieldset>
        <label>课程总学时：</label>
        <form:input path="lpCourseHoursTotal" required="true"/>
      </fieldset>
      <fieldset>
        <label>计划学年总人数：</label>
        <form:input path="lpYearsPeopleNumberPlan" required="true"/>
      </fieldset>
      <fieldset>
        <label>实验者人数：</label>
        <form:input path="lpStudentNumber" required="true"/>
      </fieldset>
      <fieldset>
        <label>实验套数：</label>
        <form:input path="lpSetNumber" required="true"/>
      </fieldset>
      <fieldset>
        <label>每组人数：</label>
        <form:input path="lpStudentNumberGroup" required="true"/>
      </fieldset>
      <fieldset>
        <label>循环次数：</label>
        <form:input path="lpCycleNumber" required="true"/>
      </fieldset>
      <fieldset>
        <label>指导书名称：</label>
        <form:input path="lpGuideBookTitle" required="true"/>
      </fieldset>
      <fieldset>
        <label>编者：</label>
        <form:input path="lpGuideBookAuthor" required="true"/>
      </fieldset>
      <fieldset>
        <label>考核方法：</label>
        <form:input path="lpAssessmentMethods" required="true"/>
      </fieldset>
      <fieldset>
        <label>项目简介：</label>
        <form:textarea path="lpIntroduction"/>
      </fieldset>
      <fieldset>
        <label>课前准备：</label>
        <form:textarea path="lpPreparation"/>
      </fieldset>
      <fieldset>
        <label>实验类别：</label>
        <form:select path="CDictionaryByLpCategoryMain.id">
          <form:options items="${labProjectMain}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset>
        <label>实验类型：</label>
        <form:select path="CDictionaryByLpCategoryApp.id">
          <form:options items="${labProjectApp}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset>
        <label>实验性质：</label>
        <form:select path="CDictionaryByLpCategoryNature.id">
          <form:options items="${labProjectNature}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset>
        <label>实验者类型：</label>
        <form:select path="CDictionaryByLpCategoryStudent.id">
          <form:options items="${labProjectStudent}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset>
        <label>变动状态：</label>
        <form:select path="CDictionaryByLpStatusChange.id">
          <form:options items="${labProjectChange}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset>
        <label>开放实验：</label>
        <form:select path="CDictionaryByLpCategoryPublic.id">
          <form:options items="${labProjectPublic}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset>
        <label>获奖等级：</label>
        <form:select path="CDictionaryByLpCategoryRewardLevel.id">
          <form:options items="${labProjectRewardLevel}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset>
        <label>实验要求：</label>
        <form:select path="CDictionaryByLpCategoryRequire.id">
          <form:options items="${labProjectRequire}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset>
        <label>实验指导书：</label>
        <form:select path="CDictionaryByLpCategoryGuideBook.id">
          <form:options items="${labProjectGuideBook}" itemLabel="CName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset>
        <label>学期：</label>
        <form:select path="schoolTerm.id">
          <form:options items="${schoolTerms}" itemLabel="termName" itemValue="id"/>
        </form:select>
      </fieldset>
      <fieldset>
        <label>所属实验室：</label>
        <form:select path="labRoom.id" class="chzn-select">
          <c:forEach items="${labRooms}" var="l">
            <form:option value="${l.id}">${l.labRoomName}</form:option>
          </c:forEach>
        </form:select>
      </fieldset>
      <fieldset>
        <label>所属学科：</label>
        <form:select path="systemSubject12.SNumber" class="chzn-select">
          <c:forEach items="${subjects}" var="s">
            <form:option value="${s.SNumber}">[${s.SNumber}]${s.SName}</form:option>
          </c:forEach>
        </form:select>
      </fieldset>
      <fieldset>
        <label>所属专业：</label>
        <form:select path="systemMajor12.MNumber" class="chzn-select">
          <c:forEach items="${majors}" var="m">
            <form:option value="${m.MNumber}">[${m.MNumber}]${m.MName}</form:option>
          </c:forEach>
        </form:select>
      </fieldset>
      <fieldset>
        <label>所属课程：</label>
        <form:select path="schoolCourse.CNumber" class="chzn-select">
          <form:options items="${schoolCourses}" itemLabel="CName" itemValue="CNumber"/>
        </form:select>
      </fieldset>
      <fieldset>
        <label>面向专业：</label>
        <form:select path="lpMajorFit" class="chzn-select" multiple="true">
          <c:forEach items="${majors}" var="m">
            <form:option value="${m.MNumber}">[${m.MNumber}]${m.MName}</form:option>
          </c:forEach>
        </form:select>
      </fieldset>
      <fieldset>
        <label>主讲教师：</label>
        <form:select path="userByLpTeacherSpeakerId.username" class="chzn-select">
          <c:forEach items="${users}" var="u">
            <form:option value="${u.username}">${u.cname}[${u.username}]</form:option>
          </c:forEach>
        </form:select>
      </fieldset>
      <fieldset>
        <label>辅导教师：</label>
        <form:select path="userByLpTeacherAssistantId.username" class="chzn-select">
          <c:forEach items="${users}" var="u">
            <form:option value="${u.username}">${u.cname}[${u.username}]</form:option>
          </c:forEach>
        </form:select>
      </fieldset>
      
      <div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="submit" value="确定">
          <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
        </div>
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
