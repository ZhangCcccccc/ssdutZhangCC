<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  
  <!-- 下拉框的样式 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 -->
  <script type="text/javascript">
  //function testDuplicated(){
	 // var labRoomNumber=$("#labRoomNumber").val();
		//$.post('${pageContext.request.contextPath}/labRoom/testDuplicated?labRoomNumber='+labRoomNumber,function(data){
			//		if(data=="isDuplicated"){
				//		alert("对不起，编号与现存的编号重复，请核实后重新填写！");
					//}else{
						//alert("编号可用");
				//	}
					
				// });
//  }
  
  </script>
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
		    <li><a href="javascript:void(0)">实验室及预约管理</a></li>
			<li><a href="javascript:void(0)">实验室管理</a></li>
			<c:if test="${isNew eq 1 }">
			<li class="end"><a href="javascript:void(0)">新建</a></li>
			</c:if>
			
			<c:if test="${isNew eq 0 }">
			<li class="end"><a href="javascript:void(0)">编辑</a></li>
			</c:if>
		</ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
      <c:if test="${isNew eq 1 }">
      <div id="title">新建实验室</div>
      </c:if> 
      
      <c:if test="${isNew eq 0 }">
      <div id="title">编辑实验室</div>
      </c:if>
      
	</div>
	<form:form action="${pageContext.request.contextPath}/labAnnex/saveLabAnnex" method="POST" modelAttribute="labAnnex">
	<div class="new-classroom">
	  <fieldset>
	  <form:hidden path="id"/>
	    <label>实验室名称：</label>
	    <form:input path="labName"/>
	  </fieldset>
	  <fieldset>
	    <label>实验室简称：</label>
	    <form:input path="labShortName"/>
	  </fieldset>
	  <fieldset>
	    <label>实验室英文名称：</label>
	    <form:input path="labEnName"/>
	  </fieldset>
	  <fieldset>
	    <label>所属学科：</label>
	    <form:input path="labSubject"/>
	  </fieldset>
	  <fieldset>
	     <label> 实验室类别：</label>
		<form:select path="CDictionaryByLabAnnex.id" class="chzn-select">
		<form:option value="">请选择</form:option>
		<form:options items="${CDictionary}" itemLabel="CName" itemValue="id"/>
		</form:select>
       </fieldset>
	   <fieldset>
	    <label>管理机构：</label>
	    <form:input path="belongDepartment"/>
	  </fieldset>
	  <%--<fieldset>
	    <label>所属中心：</label>
	    <form:select path="labCenter.id" class="chzn-select">
	    <form:option value="${labCenterId }">${labCenterName }</form:option>  	
	    <form:options items="${listLabCenter}" itemLabel="centerName" itemValue="id"/>
	    </form:select>
	  </fieldset>--%>
	   <fieldset>
	   <label>联系方式：</label>
	    <form:input path="contact"/>
	  </fieldset>
	  <fieldset>
	    <label>实验室简介：</label>
	    <form:textarea path="labDescription" style="width:1000px;height:100px"/>
	  </fieldset>
	  <fieldset>
	    <label>规章制度：</label>
	    <form:textarea path="labAttention" style="width:1000px;height:100px"/>
	    <label>获奖信息：</label>
	    <form:textarea path="awardInformation" style="width:1000px;height:100px"/>
	  </fieldset>
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
