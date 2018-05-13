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
  function testDuplicated(){
	  var labRoomNumber=$("#labRoomNumber").val();
		$.post('${pageContext.request.contextPath}/labRoom/testDuplicated?labRoomNumber='+labRoomNumber,function(data){
					if(data=="isDuplicated"){
						alert("对不起，编号与现存的编号重复，请核实后重新填写！");
					}else{
						alert("编号可用");
					}
					
				 });
  }
  
  </script>
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">实验室</a></li>
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
      <div id="title">新建实验室</div>
	</div>
	<form:form action="${pageContext.request.contextPath}/labRoom/saveLabRoom" method="POST" modelAttribute="labRoom">
	<div class="new-classroom">
	  <fieldset>
	    <form:hidden path="id"/>
	    <label>编号：</label>
	    <form:input path="labRoomNumber" id="labRoomNumber"  required="true" onchange="testDuplicated();"/>
	  </fieldset>
	  <fieldset>
	    <label>名称：</label>
	    <form:input path="labRoomName" required="true"/>
	  </fieldset>
	  <fieldset>
	    <label>英文名称：</label>
	    <form:input path="labRoomEnName"/>
	  </fieldset>
	  <fieldset>
	    <label>简称：</label>
	    <form:input path="labRoonAbbreviation"/>
	  </fieldset>
	  <fieldset>
	    <label>容量：</label>
	    <form:input path="labRoomCapacity"
	    onkeyup="value=value.replace(/[^\d]/g,'') "   
		placeholder="请输入大于零的整数"
	    />
	  </fieldset>
	  <fieldset>
	    <label>使用面积：</label>
	    <form:input path="labRoomArea"/>
	  </fieldset>
	  <fieldset>
	    <label>房间号：</label>
	    <form:input path="labRoomAddress"/>
	  </fieldset>
	  <fieldset>
	    <label>所属实验室：</label>
	    <form:select path="labAnnex.id" class="chzn-select">
	      <form:options items="${listLabAnnex}" itemLabel="labName" itemValue="id"/>
	    </form:select>
	  </fieldset>
	  <fieldset>
	    <label>所属实验中心：</label>
	    <form:select path="labCenter.id" class="chzn-select">
	      <form:option value="${labCenterId }">${labCenterName }</form:option>  	
	      <form:options items="${listLabCenter}" itemLabel="centerName" itemValue="id"/>
	    </form:select>
	  </fieldset>
	  <fieldset>
	    <label>所属学科：</label>
	    <form:select path="systemSubject12.SNumber" class="chzn-select">
	      <c:forEach items="${subject12s}" var="subject">
	        <form:option value="${subject.SNumber}">${subject.SName}</form:option>
	      </c:forEach>
	    </form:select>
	  </fieldset>
	  <fieldset>
	    <label>是否可用：</label>
	    <form:select path="labRoomActive">
	      <form:option value="1">可用</form:option>
	      <form:option value="0">不可用</form:option>
	    </form:select>
	  </fieldset>
	  <fieldset>
	    <label>是否可预约：</label>
	    <form:select path="labRoomReservation">
	      <form:option value="1">可预约</form:option>
	      <form:option value="0">不可预约</form:option>
	    </form:select>
	  </fieldset>
	  <fieldset>
	    <label>建立时间：</label>
	    <input name="labRoomTimeCreate" class="easyui-datebox" value="<fmt:formatDate value='${labRoom.labRoomTimeCreate.time}' pattern='yyyy-MM-dd'/>" />
	  </fieldset>
	  <fieldset>
	    <label>实验室描述：</label>
	    <form:textarea path="labRoomIntroduction" style="width:1000px;height:100px"/>
	  </fieldset>
	  <fieldset>
	    <label>实验室注意事项：</label>
	    <form:textarea path="labRoomRegulations" style="width:1000px;height:100px"/>
	    <label>获奖信息：</label>
	    <form:textarea path="labRoomPrizeInformation" style="width:1000px;height:100px"/>
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
