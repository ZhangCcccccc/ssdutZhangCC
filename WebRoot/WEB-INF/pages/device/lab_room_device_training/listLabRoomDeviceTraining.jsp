<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page language="java" isELIgnored="false"	contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
  <head>
  <meta name="decorator" content="iframe" />
  <!-- 下拉框的样式 -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/common/chosen/docsupport/prism.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/common/chosen/chosen.css" />
	<!-- 下拉的样式结束 -->
  <script type="text/javascript">
  function cancel(){
	  location.href="${pageContext.request.contextPath}/device/listLabRoomDeviceTraining?currpage=1";
  }
//跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  
//AJAX验证是否通过安全准入
  function Access(id){
  	$.ajax({
  	           url:"${pageContext.request.contextPath}/device/securityAccess?id="+id,
  	           type:"POST",
  	           success:function(data){//AJAX查询成功
  	           		if(data=="success"){
  	           			alert("您已经通过培训！");
  	           		}else{
  	           			alert("您还未通过培训！");
  	           		}    
  	           }
  	});
  	
  }
  
//我知道了
  function alreadyKnownMessege(id){
  	$.ajax({
          url:"${pageContext.request.contextPath}/device/alreadyKnownMessege?id="+id,
          type:"POST",
          success:function(data){//AJAX查询成功
          	$("#messege").css('display','none'); 
          }
  });
  }
  </script>
  </head>
  
  <body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
		<li class="end"><a href="javascript:void(0)">培训预约列表</a></li>
	  </ul>
	</div>
  </div>
  
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  
  <div class="content-box">
    <div class="title">
	  <div id="title">培训预约列表</div>
	  <a class="btn btn-new" onclick="window.history.go(-1);">返回</a>
	</div>
	<div class="tool-box">
	<!-- 查找框  -->
		<form:form name="queryForm" action="${pageContext.request.contextPath}/device/listLabRoomDeviceTraining?currpage=1" method="post" modelAttribute="labRoomDeviceTraining">
			 <ul>
  				
  				<li><input type="hidden" value="查询"/>
			      <input type="hidden" value="取消" onclick="cancel();"/></li>
  				</ul>
		</form:form>
	</div>
	<table class="tb" id="my_show">
		<thead>
			<tr>
			   <th>序号</th>
			   <th>设备名称</th>
			   <th>设备管理员</th>
			   <th style="width:25%">培训内容</th>
			   <th>培训时间</th>
			   <th>培训地点</th>
			   <th>培训教师</th>
			   <th>培训状态</th>
			   <th>操作</th>  
			</tr>
		</thead>
  		<tbody>
  			<c:forEach items="${labRoomDeviceTrainings}" var="curr" varStatus="i">
				<tr>
			     <td >${i.count}</td>
			     <td >${curr.labRoomDevice.schoolDevice.deviceName}[${curr.labRoomDevice.schoolDevice.deviceNumber}]</td>
			     <td >${curr.labRoomDevice.user.cname}[${curr.labRoomDevice.user.username}]</td>
			     <td ><p>${curr.content}</p></td>
			     <td ><fmt:formatDate value="${curr.time.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			     <td >${curr.address}</td>
			     <td >${curr.user.cname}[${curr.user.username}]</td>
			     <%-- <td >${curr.CTrainingStatus.name}</td> --%>
			     <td >${curr.CTrainingStatus.CName}</td>
			     <td >
			     
			     <c:if test="${isTeacher eq 1 }">
				     <c:if test="${curr.joinNumber ne 0 && curr.CTrainingStatus.id ne 4}">
				     <a href="${pageContext.request.contextPath}/device/findTrainingPeopleByTrainId?id=${curr.id}">结果录入</a>
				     </c:if>
			     </c:if>
			     
			     <c:if test="${isTeacher eq 0 }">
				     <%-- <c:if test="${curr.CTrainingStatus.id eq 1}"><!-- 待培训 --> --%>
				     <c:if test="${curr.CTrainingStatus.CCategory=='c_training_status'&& curr.CTrainingStatus.CNumber=='1'}"><!-- 待培训 -->
							培训已预约
							<a href="${pageContext.request.contextPath}/device/cancleTraining?id=${curr.id }&isTeacher=${isTeacher }&flag=1"  onclick="return confirm('确定取消？')">取消预约</a>
					</c:if>
									
					<%-- <c:if test="${curr.CTrainingStatus.id eq 2}"> --%>
					<c:if test="${curr.CTrainingStatus.CCategory=='c_training_status'&& curr.CTrainingStatus.CNumber=='2'}">
					<c:if test="${fn:contains(curr.labRoomDeviceTrainingPeoples,user.username)}">
					培训结束，请<a href="javascript:void(0);" onclick="Access('${curr.labRoomDevice.id}');"  >点击</a>查看培训结果
					</c:if>
					<c:if test="${fn:indexOf(curr.labRoomDeviceTrainingPeoples,user.username)==-1}">
					培训已结束！
					</c:if>
					</c:if>
					
					<c:set var="exsitMessege" value="0"></c:set>
					 <c:set var="messege_flag" value="0"></c:set>
					 	<c:forEach items="${curr.labRoomDeviceTrainingPeoples }" var="student">
					 		<c:if test="${student.user.username eq user.username && student.messageFlag ne 0}">
					 			<c:set var="exsitMessege" value="1"></c:set>  <!-- 判断是否有消息 -->
					 			<c:set var="messege_flag" value="${student.messageFlag }"></c:set>  <!-- 判断是否有消息 -->
					 		</c:if>
					 	</c:forEach>
					 <c:if test="${exsitMessege eq 1 }">
					 	<font color="red">
					 		<c:choose>
					 			<c:when test="${messege_flag eq 1 }">
					 				<br>培训时间有变化<a id="messege" onclick="alreadyKnownMessege(${curr.id})">我知道了</a>
					 			</c:when>
					 			<c:when test="${messege_flag eq 2 }">
					 				<br>培训已取消<a id="messege" onclick="alreadyKnownMessege(${curr.id})">我知道了</a>
					 			</c:when>
					 		</c:choose>
					 	</font>
					 </c:if>
				</c:if>
			     
			     </td>
				</tr>
			</c:forEach>
  		</tbody>
	</table>
	<div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/device/listLabRoomDeviceTraining?currpage=1&isTeacher=${isTeacher }')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/device/listLabRoomDeviceTraining?currpage=${pageModel.previousPage}&isTeacher=${isTeacher }')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option selected="selected" value="${pageContext.request.contextPath}/device/listLabRoomDeviceTraining?currpage=${pageModel.currPage}&isTeacher=${isTeacher }">${pageModel.currPage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currPage}">
    <option value="${pageContext.request.contextPath}/device/listLabRoomDeviceTraining?currpage=${j.index}&isTeacher=${isTeacher }">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/device/listLabRoomDeviceTraining?currpage=${pageModel.nextPage}&isTeacher=${isTeacher }')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/device/listLabRoomDeviceTraining?currpage=${pageModel.lastPage}&isTeacher=${isTeacher }')" target="_self">末页</a>
    </div>
  </div>
  </div>
  </div>
  </div>
  
  
  
  
<!-- 下拉框的js -->
	<script src="${pageContext.request.contextPath}/js/common/chosen/chosen.jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/common/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
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