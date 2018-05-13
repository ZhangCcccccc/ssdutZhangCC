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
  	$("#cid").val("");
  	$("#labRoomId").val("");
  	$("#schoolDeviceNumber").val("");
  	document.queryForm.submit();
  }
  
  function findLabRoomByCenter(cid){
  	if(cid==""){//未选择实验室中心，则清除实验室名称列表及设备列表
  		$("#labRoomId").children().eq(0).siblings().remove();
  		$("#schoolDeviceNumber").children().eq(0).siblings().remove();
  		
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
  
  function findSchoolDeviceByLabRoom(labroomId){
  	if(labroomId==""){//未选择实验室
  		$("#schoolDeviceNumber").children().eq(0).siblings().remove();
  	}else {
		$.ajax({
			'url':'${pageContext.request.contextPath}/cms/device/findSchoolDeviceByLabRoom?labroomId='+labroomId,
			'type':'POST',
			'success':function(data){
				alert(1)
				var html = "";
				$.each(data,function(key,value){
					html += "<option value='"+key+"'>"+value+"</option>";
				})
				$("#schoolDeviceNumber").children().eq(0).siblings().remove();
				$("#schoolDeviceNumber").append(html);
			}
		})
	}
  }
</script>
</head>
  
<body>
<div id="content_suda">
        <div class="box1_suda">
		    <div class="box1_suda_title" style="width:98%; margin-right:auto;">
			    <div class="box1_suda_img" style="left:10px;"></div>
			    设备预约动态
				<div class="more_suda"><a href="">更多>></a></div>
			</div>
			<table class="table_suda"  cellpadding="0" cellspacing="0" style="width:98%;margin-right:auto;">
			       <thead>
				      <th width="150px" >设备名称</th>
				      <th width="120px" >设备编号</th>
				      <th width="80px">预约人</th>
				      <th width="160px">预约时间</th>
				      <th >预约主题</th>
			       </thead>
                               <c:forEach items="${labRoomDeviceReservations }" var="reservation">  
			       <tr>
				      <td >${reservation.labRoomDevice.schoolDevice.deviceName }</td>
				      <td>${reservation.labRoomDevice.schoolDevice.deviceNumber}</td>
			              <td >${reservation.userByReserveUser.cname }</td>
				      <td ><fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy/MM/dd HH:mm:ss"/>-<fmt:formatDate value="${reservation.endtime.time}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
				      <td >${reservation.CDictionaryByCReservationProperty.CName }</td>
				</tr>
	                        </c:forEach>
		         </table>
        </div>
	<div id="title" class="box1_suda_title" style="width:98%; margin-right:auto; border:none;">设备列表</div>
	<div style=" clear:both"></div>	
	<div class="">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/cms/device/reservationList?currpage=1" method="post" modelAttribute="labRoomDevice">
			 <ul>
			 	<li>
			 		实验室中心：
			 		<select id="cid" name="cid"  class="chzn-select" onchange="findLabRoomByCenter(this.value)">
						<option value="">请选择实验中心</option>
						<c:forEach items="${centers}" var="center">
							<option value="${center.id}" <c:if test="${center.id==cid }">selected="selected"</c:if>>${center.centerName}</option>
						</c:forEach>
					</select>
			 	</li>
  				<li>
                                         实验室名称：
  					<form:select id="labRoomId" path="labRoom.id"  class="chzn-select" onchange="findSchoolDeviceByLabRoom(this.value)">
						<form:option value="">请选择实验中心</form:option>
						<c:forEach items="${rooms}" var="room">
							<form:option value="${room.id}">${room.labRoomName}</form:option>
						</c:forEach>
					</form:select>
				</li>
		      	        <li>
		      		设备名称：
		      		<form:select id="schoolDeviceNumber" path="schoolDevice.deviceNumber" class="chzn-select" >
						<form:option value="">请选择设备名称</form:option>
						<c:forEach items="${labRoomDeviceNumbers}" var="labRoomDevice">
							<form:option value="${labRoomDevice.schoolDevice.deviceNumber}" >${labRoomDevice.schoolDevice.deviceName}</form:option>
						</c:forEach>
					</form:select>
		              	</li>
  				<li><input type="submit" value="查询"/>
			      <input type="button" value="取消" onclick="cancel();"/>
			    </li>
			</ul>			 
		</form:form>
	</div>
	<div style=" clear:both"></div>
	<div>
	<table class="" id="" style="width:100%; text-align:center;">
	  <thead>
	  <tr>
	    <th width="10%"><a href="javascript:void(0);" onclick="orderByNumber()">编号</a></th>
	    <th width="15%"><a href="javascript:void(0);" onclick="orderByName()">名称</a></th>
	    <th width="15%"><a href="javascript:void(0);" onclick="orderByLabCenter()">所属实验中心</a></th>
	    <th width="15%"><a href="javascript:void(0);" onclick="orderByRoomAddress()">所在实验室</a></th>
	    <th width="15%"><a href="javascript:void(0);" onclick="orderByRoomAddress()">设备管理员</a></th>
	    <th width="15%"><a href="javascript:void(0);" onclick="orderByRoomAddress()">状态</a></th>
	    <th width="15%">操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listLabRoomDevice}" var="curr">
	  <tr>
	    <td>${curr.schoolDevice.deviceNumber}</td>
	    <td>${curr.schoolDevice.deviceName}</td>
	    <td>${curr.labRoom.labCenter.centerName}</td>
	    <td>${curr.labRoom.labRoomName}</td>
	    <td>${curr.user.cname}</td>
	    <td>${curr.CDictionaryByDeviceStatus.CName}</td>
	    <td>
	      	预约
	      	<a href="${pageContext.request.contextPath}/cms/device/findLabRoomDeviceDetail?labRoomDeviceId=${curr.id}">详情</a>
	    </td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	</div>
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/cms/device/reservationList?currpage=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/cms/device/reservationList?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/cms/device/reservationList?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/cms/device/reservationList?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/cms/device/reservationList?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/cms/device/reservationList?currpage=${pageModel.lastPage}')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
</div>
<div style="clear:both"></div>
</body>
</html>
