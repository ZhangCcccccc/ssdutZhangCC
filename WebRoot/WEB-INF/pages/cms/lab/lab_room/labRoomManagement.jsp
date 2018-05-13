<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  
  <script type="text/javascript">
  //跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  
  function cancel(){
  	$("#lab_name").val("");
  	$("#center_id").val("");
  	$("#username").val("");
  	document.queryForm.submit();
  }
  
  $(document).ready(function(){
  })
  
  function findLabRoomByCenter(cid){
  	if(cid==""){//未选择实验室中心，则清除实验室名称列表及实验室管理员列表
  		$("#labRoomId").children().eq(0).siblings().remove();
  		$("#username").children().eq(0).siblings().remove();
  	}else {
		$.ajax({
			'url':'${pageContext.request.contextPath}/cms/labRoom/findLabRoomByCenter?cid='+cid,
			'type':'POST',
			'success':function(data){
				var html = "";
				$.each(data,function(i,value){
					html += "<option value='"+value['id']+"'>"+value['labRoomName']+"</option>"
				})
				$("#labRoomId").children().eq(0).siblings().remove();
				$("#labRoomId").append(html);
			}
		})
	}
  }
  function findLabRoomByCheckCenter(cid){
  	if(cid==""){//未选择实验室中心，则清除实验室名称列表
  		$("#labroom_id").children().eq(0).siblings().remove();
  	}else {
		$.ajax({
			'url':'${pageContext.request.contextPath}/cms/labRoom/findLabRoomByCenter?cid='+cid,
			'type':'POST',
			'success':function(data){
				var html = "";
				$.each(data,function(i,value){
					html += "<option value='"+value['id']+"'>"+value['labRoomName']+"</option>"
				})
				$("#labroom_id").children().eq(0).siblings().remove();
				$("#labroom_id").append(html);
			}
		})
	}
  }
  
  function findLabRoomAdminsByLabRoom(labroomId){
  	if(labroomId==""){//未选择实验室，则清除实验室管理员列表
  		$("#username").children().eq(0).siblings().remove();
  	}else {
		$.ajax({
			'url':'${pageContext.request.contextPath}/cms/labRoom/findLabRoomAdminsByLabRoom?labroomId='+labroomId,
			'type':'POST',
			'success':function(data){
				var html = "";
				$.each(data,function(key,value){
					html += "<option value='"+key+"'>"+value+"</option>";
				})
				$("#username").children().eq(0).siblings().remove();
				$("#username").append(html);
			}
		})
	}
  }
  
</script>
</head>
  
<body>
<div id="content_suda">
        <div class="box1_suda" >
		    <div class="box1_suda_title" style="width:98%; margin-right:auto;">
			    <div class="box1_suda_img" style="left:10px;"></div>
			    实验室预约动态
			    <div class="more_suda"><a href="">更多>></a></div>				
		    </div>
		    <table class="table_suda"  cellpadding="0" cellspacing="0" style="width:98%;margin-right:auto;">
			       <thead>
				      <th width="150px" >实验室名称</th>
				      <th width="80px">预约人</th>
				      <th width="160px">预约时间</th>
				      <th >预约主题</th>
			       </thead>
                               <c:forEach items="${labReservations }" var="reservation">  
			       <tr>
				      <td >${reservation.labRoom.labRoomName }</td>
				      <td >${reservation.user.cname}</td>
				      <td >第<c:forEach items="${reservation.week }"  var="a" ><c:if test="${a!=null}">${a }</c:if></c:forEach>周星期<c:forEach items="${reservation.day}"  var="d" ><c:if test="${d!=null}">${d }</c:if></c:forEach>第<c:forEach items="${reservation.time}"  var="f" ><c:if test="${f!=null}">${f }</c:if></c:forEach>节</td>
			              <td >${reservation.name }</td>
			       </tr>
                               </c:forEach>
		    </table>
	</div>
	<div style="clear:both"></div>
        <div id="title" class="box1_suda_title" style="width:98%; margin-right:auto; border:none;">实验室列表</div>
        <div style=" clear:both"></div>	
	<div class="">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/cms/labRoom/labRoomManagement?currpage=1" method="post" modelAttribute="labRoom">
			 <ul>
			 	<li>
			 		实验室中心：
			 		<form:select id="center_id" path="labCenter.id"  class="chzn-select" onchange="findLabRoomByCenter(this.value)">
						<form:option value="">请选择实验中心</form:option>
						<c:forEach items="${centers}" var="center">
							<form:option value="${center.id}">${center.centerName}</form:option>
						</c:forEach>
					</form:select>
			 	</li>
  				<li>
                                       实验室名称：
					<form:select id="labRoomId" path="id"  class="chzn-select" onchange="findLabRoomAdminsByLabRoom(this.value)">
						<form:option value="">请选择实验室</form:option>
						<c:forEach items="${searchLabRooms}" var="labroom">
							<form:option value="${labroom.id}">${labroom.labRoomName}</form:option>
						</c:forEach>
					</form:select>
				</li>
		      	        <li>
		      		       实验室管理员：
		      		         <select id="username" name="username" class="chzn-select">
						<option value="">请选择管理员</option>
						<c:forEach items="${users}" var="user">
							<option value="${user.username}" <c:if test="${user.username == username }">selected="selected"</c:if>>${user.cname}</form>
						</c:forEach>
					</select>
		      	        </li>
  				<li><input type="submit" value="查询"/>
			            <input type="button" value="取消" onclick="cancel();"/>
			        </li>
			</ul>			 
		</form:form>
	</div>
	<div>
		<table class="" id="" style="width:100%; text-align:center;">
			<thead>
				<tr>
					<th width="15%"><a href="javascript:void(0);" onclick="orderByNumber()">编号</a></th>
					<th width="25%"><a href="javascript:void(0);" onclick="orderByName()">名称</a></th>
					<th width="25%"><a href="javascript:void(0);" onclick="orderByLabCenter()">所属实验中心</a></th>
					<th width="20%"><a href="javascript:void(0);" onclick="orderByRoomAddress()">房间号</a></th>
					<th width="15%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${labRooms}" var="curr">
					<tr>
						<td>${curr.labRoomNumber}</td>
						<td>${curr.labRoomName}</td>
						<td>${curr.labCenter.centerName}</td>
						<td>${curr.labRoomAddress}</td>
						<td>
							预约
							<a href="${pageContext.request.contextPath}/cms/labRoom/findLabRoomDetail?labroomId=${curr.id}">详情</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 分页[s] -->
	<div>
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/cms/labRoom/labRoomManagement?currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/cms/labRoom/labRoomManagement?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/cms/labRoom/labRoomManagement?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/cms/labRoom/labRoomManagement?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/cms/labRoom/labRoomManagement?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/cms/labRoom/labRoomManagement?currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
    
    
    <c:if test="${!empty loginUser }">
	    <div>
		    <form:form action="${pageContext.request.contextPath}/cms/labRoom/savelabreservation" method="post" modelAttribute="labReservation">
				<div id="f1" class="content-box">
					<table>
						<tr>
							<th>实验室中心</th>
							<td>
								<form:select id="labcenter_id" path="labRoom.labCenter.id"  class="chzn-select" onchange="findLabRoomByCheckCenter(this.value)" required="required">
									<form:option value="">请选择实验中心</form:option>
									<c:forEach items="${centers}" var="center">
										<form:option value="${center.id}">${center.centerName}</form:option>
									</c:forEach>
								</form:select>
							</td>
							<th>选择实验室</th>
							<td>
								<form:select id="labroom_id" path="labRoom.id"  class="chzn-select" required="required">
									<form:option value="">请选择实验室</form:option>
									<c:forEach items="${searchLabRooms}" var="labroom">
										<form:option value="${labroom.id}">${labroom.labRoomName}</form:option>
									</c:forEach>
								</form:select>
							</td>
						</tr>
						<tr>
							<th><input type="hidden" id="deviceType" name="deviceType">
								实验室预约</th>
							<td><form:select path="CDictionaryByLabReservetYpe.id"
									style="width:165px">
									<option value="553" onclick="ff(1)">实验室预约</option>
									<!-- <option value="2" onclick="ff(2)">课程预约</option> -->
								</form:select></td>
							<th>选择选课组</th>
							<td><select name="courseCode" id="courseCode"
								style="width:165px">
									<c:forEach items="${selfCourseList}" var="s">
										<option value="${s.id}">${s.name}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th>学期</th>
							<td><select name="schoolTermid" id="schoolTermid"
								istyle="width: 100px" class="chzn-select" required="true">
									<option value="${currTerm.id }">${currTerm.termName }</option>
							</select></td>
							<th>星期</th>
							<td><select class="chzn-select" name="weekday" id="weekday"
								style="width:80px">
									<c:forEach begin="1" end="7" varStatus="i">
										<c:if test="${i.count == appointment.weekday }">
											<option value="${i.count}" selected>星期${i.count}</option>
										</c:if>
										<c:if test="${i.count != appointment.weekday  }">
											<option value="${i.count}">星期${i.count}</option>
										</c:if>
									</c:forEach>
							</select></td>
	
						</tr>
						<tr>
							<th>节次</th>
							<td><select class="chzn-select" multiple="multiple"
								data-placeholder='请选择节次：' name="classes" id="classes"
								style="width:165px" required="true">
									<c:forEach begin="1" end="13" varStatus="i">
										<option value="${i.count}">${i.count}</option>
									</c:forEach>
							</select></td>
							<th>周次</th>
							<td><select class="chzn-select" multiple="multiple"
								name="weeks" data-placeholder='请选择周次：' id="weeks"
								style="width:165px" required="true">
									<c:forEach items="${week}" var="current" varStatus="i">
										<c:if test="${current.key<=16}">
											<option value="${current.key}">${current.value}</option>
										</c:if>
										<c:if test="${current.key>16}">
											<option value="${current.key}">${current.value}</option>
										</c:if>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th>活动类别</th>
							<td><form:select path="CDictionaryByActivityCategory.id"
									items="${activitycategory }" style="width:165px" required="true"></form:select>
							</td>
							<!-- 原来这个地方写的是CDictionaryByLabReservetYpe.id  报500错误 -->
							<th>活动名称</th>
							<td><form:input path="eventName" name="eventName"
									style="width:145px" required="true" /></td>
						</tr>
						<tr>
							<th>预约内容</th>
							<td colspan="3"><input path="reservations"
								name="reservations"
								style="width: 450px;height: 50px;padding: 5px" />
							</td>
	
						</tr>
						<tr>
							<th>备注</th>
							<td colspan="3"><form:input path="remarks" name="remarks"
									style="width: 450px;height: 50px;padding: 5px" />
							</td>
						</tr>
						<tr>
							<th>添加学生(输入学生学号，以逗号分开)</th>
							<td colspan="3"><textarea rows="" cols="" name="student"
									id="student" style="width: 450px;height: 50px;padding: 5px"></textarea>
							</td>
						</tr>
	
					</table>
					<div>
							<input type="submit" value="提交">
							<input type="button" value="取消" onclick="window.history.go(0)">
						</div>
					
		
				</div>
			</form:form>
		</div>
    </c:if>
</div>
<div style="clear:both"></div>
</body>
</html>
