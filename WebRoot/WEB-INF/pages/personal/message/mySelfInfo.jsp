<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipmentlend-resources"/>
<!doctype html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/SpryTabbedPanels.js"> 
 </script>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<script type="text/javascript">
function viewRecords(id){
     $("#repairRecords"+id).window('open');       
    }
    
function viewOperation(id){
     $("#operationRecords"+id).window('open');       
    }
function viewcOperation(id){
     $("#coperationRecords"+id).window('open');       
    }    
    function viewReservation(id){
     $("#viewReservations"+id).window('open');       
    }
function viewLend(id){
 $("#viewLends"+id).window('open');       
}
function viewRepairs(id){
 $("#viewrepairs"+id).window('open');       
}
function zln(s){
 if(confirm( '你真的要删除吗？ ')==false){
       return   false;
    }else{
     window.location.href="${pageContext.request.contextPath}/operation/delectitem?idkey="+s;
    }
}
</script>
</head>
<body>
<div class="right-content">
	<div class="content-box">
			<div >
				<div class="content-double">
					<div class="title">我的预约</div>
					<div id="TabbedPanels1" class="TabbedPanels">
					  <ul class="TabbedPanelsTabGroup">
						<li class="TabbedPanelsTab" tabindex="0">实验室预约</li>
						<li class="TabbedPanelsTab" tabindex="0">设备预约</li>
						<li class="TabbedPanelsTab" tabindex="0">设备借用</li>
						<li class="TabbedPanelsTab" tabindex="0">设备维修</li>
					  </ul>
					  <div class="TabbedPanelsContentGroup">
						<div class="TabbedPanelsContent">
<table >
<thead><tr>
   <th>预约性质</th>
   <th >周次</th>
   <th >星期</th>
   <th >节次</th>
   <th >预约实验室</th>
   <th >审批状态</th>
   <center><th>操作</th></center>  
</tr>
</thead>

<tbody >
<c:forEach items="${labReservations}" var="s">
<tr align="center">
     <td >${s.nametype}</td>
	<td>  第[<c:forEach  items="${s.week }" var="bs" > <c:if test="${bs!=null}">${bs }&nbsp;</c:if>  </c:forEach>]周</td> 
     <td> 星期[<c:forEach items="${s.day}"  var="d" ><c:if test="${d!=null}">${d }&nbsp;</c:if></c:forEach>]</td> 
     <td> 第[<c:forEach items="${s.time}"  var="f" ><c:if test="${f!=null}">${f }&nbsp;</c:if></c:forEach>]节</td>
     <td>${s.lab}</td>
     <td><c:if test="${s.cont==1}">审核通过</c:if>
        <c:if test="${s.cont==2}">审核中</c:if>
        <c:if test="${s.cont==4}">审核拒绝</c:if>
        <c:if test="${s.cont==3}">未审核</c:if> </td>
    <td><a onclick="viewRecords(${s.id});"  >查看</a></td>
	</tr></c:forEach>
         	
         </tbody>
					</table>
					<div  class=" more"><a href="${pageContext.request.contextPath}/timetable/MyAppointment?page=1">查看更多</a></div>
						</div>
						<!-- 设备预约 -->
						<div class="TabbedPanelsContent">
						<table>
				<thead>
                    <tr>
                    	<th>序号</th>
                        <th>预约设备</th>
                        <th>申请人</th>
                        <th>指导教师</th>
                        <th>日期</th>
                      <!--   <th>实验室</th> -->
                        <th>审核状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                
                <tbody>
                		<c:forEach items="${labRoomDeviceReservation}" var="reservation" varStatus="i">
                		<tr>
                        <td>${i.count}</td>
                        <td>${reservation.labRoomDevice.schoolDevice.deviceName}</td>
                        <td>${reservation.userByReserveUser.cname}</td>
                        <td>${reservation.userByTeacher.cname}</td>
                        <td><fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy-MM-dd"/> </td>
<%--                         <td>${reservation.labRoomDevice.labRoom.labRoomName}</td>
 --%>               <td>
                        ${reservation.CAuditResult.CName}
                         </td>
                            <td><a onclick="viewReservation(${reservation.id});"  >查看</a>
                            </td>                          
                        </tr>
                		</c:forEach>
                       
                </tbody>
							</table>
							<div  class=" more"><a href="${pageContext.request.contextPath}/timetable/MyLabRoomAppointment?page=1">查看更多</a></div>
						</div>
					<!-- 设备借用 -->
                   <div class="TabbedPanelsContent">
						<table>
				<thead>
                    <tr>
                    	<th>序号</th>
                        <th>借用设备</th>
                        <th>申请人</th>
                        <th>设备管理员</th>
                        <th>借用日期</th>
                      <!--   <th>实验室</th> -->
                     <!--    <th>状态</th> -->
                        <th>操作</th>
                    </tr>
                </thead>
                
                <tbody>
                		<c:forEach items="${schoolDevices}" var="reservation" varStatus="i">
                		<tr>
                        <td>${i.count}</td>
                        <td>${reservation.deviceName}</td>
                        <td>${reservation.deviceEnName}</td>
                        <td>${reservation.deviceNumber}</td>
                        <td><fmt:formatDate value="${reservation.createdAt.time}" pattern="yyyy-MM-dd"/> </td>
                        <td><a onclick="viewLend(${reservation.id});"  >查看</a>
                        </td>                          
                        </tr>
                		</c:forEach>
                       
                </tbody>
			</table>
			<div  class=" more"><a href="${pageContext.request.contextPath}/labshow/MyLabRoomLend?page=1">查看更多</a></div>
		    </div>
			<!-- 设备维修 -->	
			<div class="TabbedPanelsContent">
				<table>
				<thead>
                    <tr>
                    	<th>序号</th>
                        <th>报修设备</th>
                        <th>报修人</th>
                        <th>报修时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                
                <tbody>
                		<c:forEach items="${repairs}" var="reservation" varStatus="i">
                		<tr>
                        <td>${i.count}</td>
                        <td>${reservation.labRoomDevice.schoolDevice.deviceName}</td>
                        <td>${reservation.user.cname}</td>
                        <td><fmt:formatDate value="${reservation.repairTime.time}" pattern="yyyy-MM-dd"/> </td>
                        <td><a onclick="viewRepairs(${reservation.id});"  >查看</a>
                        </td>                          
                        </tr>
                		</c:forEach>
                       
                </tbody>
							</table>
							<div  class=" more"><a href="${pageContext.request.contextPath}/labshow/MyLabRoomRepair?page=1">查看更多</a></div>
						</div>
					  </div>
					</div>
					<script type="text/javascript">
					var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
					</script>
					
				</div>
				
				
				



				
<div class="content-double">
	<div class="title">实验项目</div>
	<div id="TabbedPanels3" class="TabbedPanels">
	<ul class="TabbedPanelsTabGroup">
	  <li class="TabbedPanelsTab" tabindex="0">实验项目</li>
	  <li class="TabbedPanelsTab" tabindex="0">综合实验项目</li>
	 </ul>
  <div class="TabbedPanelsContentGroup">
	<div class="TabbedPanelsContent">
      <table><thead>
		     <tr>
				<th class="thead" width="8%" >序号</th>	
				<th class="thead" width="10%">项目编号</th>
				<th class="thead" width="10%">实验名称</th>
				<th class="thead" width="10%">所属课程</th>
				<th class="thead" width="14%">创建时间</th>
				<th class="thead" width="8%">查看</th>
			  </tr>
		   </thead>
		  <tbody>

				<c:forEach items="${operationItem}" var="s" varStatus="i">
				<tr  align="left">
				    <td>${i.index+1 }</td>
				    <td>${s.experimentItemCardNumber}</td>
				    <td>${s.lpName }</td>
				    <td>${s.schoolCourseByClassNo.courseName}</td>
				    <td><fmt:formatDate value="${s.createTime.time}" pattern="yyyy-MM-dd"/></td>
                    <td> <a   onclick="viewOperation(${s.id});">查看</a> </td>
                 </tr>
				</c:forEach>
			</tbody>						
		</table>
		<div  class=" more"><a href="${pageContext.request.contextPath}/operation/indexLmsExperiment?currpage=1">查看更多</a></div>
	</div>
	
	<div class="TabbedPanelsContent">
			<table>
				<thead>
		        <tr>
				  <th class="thead" width="8%" >序号</th>	
				  <th class="thead" width="10%">项目编号</th>
				  <th class="thead" width="10%">实验名称</th>
				  <th class="thead" width="14%">所属课程</th>
				  <th class="thead" width="10%">申请人</th>
				  <th class="thead" width="14%">创建时间</th>
				  <th class="thead" width="14%">状态</th>
				  <th class="thead" width="8%">查看</th>
			    </tr>
		        </thead>
		        <tbody>
                 <c:forEach items="${coperationItem}" var="q" varStatus="i">
				  <tr  align="left">
				    <td>${i.index+1 }</td>
				    <td>${q.experimentItemCardNumber}</td>
				    <td>${q.lpName }</td>
				    <td>${q.schoolCourseByClassNo.courseName}</td>
				    <td>${q.user.cname}</td>
				    <td><fmt:formatDate value="${q.createTime.time}" pattern="yyyy-MM-dd"/></td>
				    <td>
                      <c:if test="${q.conservess==2}">
						<c:if test="${q.auditStatus!= null}">
							<c:if test="${q.auditStatus==1 }">审核通过</c:if>
							<c:if test="${q.auditStatus==2 }">审核拒绝</c:if>
							<c:if test="${q.auditStatus==3 }">审核中</c:if>
						</c:if>
						<c:if test="${q.auditStatus == null}">未审核</c:if>
					</c:if> 
					<c:if test="${q.conservess==1}">未提交  </c:if>
                   </td>
                    <td><a  onclick="viewcOperation(${q.id});">查看</a>  </td>
                  </tr>
				 </c:forEach>
			   </tbody>						
			</table>
			<div  class=" more"><a href="${pageContext.request.contextPath}/operation/complexLmsExperiment?currpage=1&tage=0">查看更多</a></div>
		</div>
		
		
		
		<script type="text/javascript">
			var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels3");
		</script>
</div>
		</div>

</div>	
			
			</div>	








<c:forEach items="${labReservatio}" var="s">
<div id="repairRecords${s.id}" class="easyui-window"  modal="true" title="实验室预约" closed="true" iconCls="icon-add" style="width:1000px;height:px">

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<!-- <div class="title">实验室预约</div> -->
<table>
<thead>
<tr>
   <th style="width:10%;">预约性质</th>
   <th style="width:6%;">申请人</th>
   <th style="width:15%;">管理员</th>
   <th style="width:10%;">申请内容</th>
   <th style="width:28%;">使用时间</th>
   <th style="width:17%;">实验室</th>
   <th style="width:6%;">审核人</th>
   <th style="width:8%;">状态</th>
</tr>
</thead>
<tbody>
<tr>
<td>${s.CLabReservationType.name}</td>
<td>${s.user.cname}</td>
<td><c:forEach items="${s.labRoom.labRoomAdmins}" var="t">${t.user.cname},</c:forEach>
  </td>
<td>${s.reservations}</td>
<td>
<c:forEach items="${labno}" var="bss">
<c:if test="${s.id==bss.id}">
第[<c:forEach  items="${bss.week}" var="bsa" > <c:if test="${bsa!=null}">${bsa}&nbsp;</c:if>  </c:forEach>]周
&nbsp;星期[<c:forEach items="${bss.day}"  var="d" ><c:if test="${d!=null}">${d}&nbsp;</c:if></c:forEach>]
&nbsp; 第[<c:forEach items="${bss.time}"  var="f" ><c:if test="${f!=null}">${f}&nbsp;</c:if></c:forEach>]节
</c:if>
</c:forEach>
  </td>
	<td>${s.labRoom.labRoomName} </td>
<td><c:forEach items="${s.labReservationAudits}" var="y">${y.user.cname}</c:forEach>
</td>
     <td><c:if test="${s.auditResults==1}">审核通过</c:if>
         <c:if test="${s.auditResults==2}">审核中</c:if>
         <c:if test="${s.auditResults==4}">审核拒绝</c:if>
         <c:if test="${s.auditResults==3}"><a href="${pageContext.request.contextPath}/lab/checkButton?idkey=${s.id}&tage=0">审核</a></c:if> </td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
</div>
</div>
</div>
</c:forEach>



<c:forEach items="${labRoomDeviceReservation}" var="s">
<div id="viewReservations${s.id}" class="easyui-window" modal="true" title="实验室设备预约" closed="true" iconCls="icon-add" style="width:750px;height:300px">
<%-- <div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">我的工作平台</a></li>
	<li class="end"><a href="${pageContext.request.contextPath}/personal/message/mySelfInf">个人中心</a></li>
</ul>
</div>
</div> --%>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<!-- <div class="title">设备预约</div> -->
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                        <th>预约设备</th>
                        <th>申请人</th>
                        <th>指导教师</th>
                        <th>申请内容</th>
                        <th>日期</th>
                        <th>使用时间</th>
                        <th>实验室</th>
          <!--               <th>审核人</th> -->
                        <th>状态</th>
                    </tr>
                </thead>
                
                <tbody>
                		<tr>
                        <td>${s.labRoomDevice.schoolDevice.deviceName}</td>
                        <td>${s.userByReserveUser.cname}</td>
                        <td>${s.userByTeacher.cname}</td>
                        
                        <td>${s.content}</td>
                        <td><fmt:formatDate value="${s.begintime.time}" pattern="yyyy-MM-dd"/> </td>
                        <td>
                        <fmt:formatDate value="${s.begintime.time}" pattern="HH:mm:ss"/>-<fmt:formatDate value="${s.endtime.time}" pattern="HH:mm:ss"/>
                        </td>
                        <td>${s.labRoomDevice.labRoom.labRoomName}</td>
                        <td>
                        <c:if test="${s.CAuditResult.id==1}">
                        <a href="${pageContext.request.contextPath}/personal/message/labRoomDeviceReservationResult?id=${s.id}">${s.CAuditResult.name}</a></td>                          
                        </c:if>
                        <c:if test="${s.CAuditResult.id!=1}">
                        ${s.CAuditResult.name}
                        </c:if>
                        </tr>
                </tbody>
            </table>
</div>
</div>
</div>
</div>
</div>
</div>
</c:forEach>

<!-- 设备借用 -->
<c:forEach items="${schoolDevices}" var="s">
<div id="viewLends${s.id}" class="easyui-window" modal="true" title="实验室设备借用" closed="true" iconCls="icon-add" style="width:750px;height:300px">
<%-- <div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">我的工作平台</a></li>
	<li class="end"><a href="${pageContext.request.contextPath}/personal/message/mySelfInf">个人中心</a></li>
</ul>
</div>
</div> --%>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<!-- <div class="title">设备预约</div> -->
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                        <th>借用设备</th>
                        <th>申请人</th>
                        <th>指导教师</th>
                        <th>申请内容</th>
                        <th>借用日期</th>
                        <th>审核人</th>
                        <th>备注</th>
                        <th>状态</th>
                        <c:if test="${s.deviceAddress == '未审核'}">
                        <th>操作</th>
                        </c:if>
                    </tr>
                </thead>
                
                <tbody>
                		<tr>
                        <td>${s.deviceName}</td>
                        <td>${s.deviceEnName}</td>
                        <td>${s.devicePattern}</td>
                        <td>${s.deviceFormat }</td>
                        <td><fmt:formatDate value="${s.createdAt.time}" pattern="yyyy-MM-dd"/> </td>
                        <td>${s.deviceUseDirection}</td>
                        <td>${s.deviceSupplier}</td>
                        <td>${s.deviceAddress }</td>    
                        <td>
                        	<c:if test="${s.deviceAddress == '未审核'}">
                        	<c:if test="${user.username == projectSource }">
                        	<a href="${pageContext.request.contextPath}/device/auditDeviceLending?idKey=${s.id}">审核</a>
                        	</c:if></c:if>
                        </td>                      
                        </tr>
                </tbody>
            </table>
</div>
</div>
</div>
</div>
</div>
</div>
</c:forEach>

<!-- 设备维修 -->
<c:forEach items="${repairs}" var="reservation">
<div id="viewrepairs${reservation.id}" class="easyui-window" modal="true" title="实验室设备维修" closed="true" iconCls="icon-add" style="width:750px;height:300px">
<%-- <div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">我的工作平台</a></li>
	<li class="end"><a href="${pageContext.request.contextPath}/personal/message/mySelfInf">个人中心</a></li>
</ul>
</div>
</div> --%>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<!-- <div class="title">设备预约</div> -->
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                        <th>报修设备</th>
                        <th>报修人</th>
                        <th>硬件故障</th>
                        <th>软件故障</th>
                        <th>分区</th>
                        <th>报修时间</th>
                        <th>修复时间</th>
                        <th>修理记录</th>
                        <th>状态</th>
                    </tr>
                </thead>
                
                <tbody>
                		<tr>
                         <td>${reservation.labRoomDevice.schoolDevice.deviceName}</td>
                        <td>${reservation.user.cname}</td>
                        <td>${reservation.hardwareFailure}</td>
                        <td>${reservation.softwareFailure}</td>
                        <td>${reservation.CLabRoomDevicePartition.name}</td>
                        <td><fmt:formatDate value="${reservation.createTime.time}" pattern="yyyy-MM-dd"/> </td>
                        <td><fmt:formatDate value="${reservation.restoreTime.time}" pattern="yyyy-MM-dd"/> </td>
                        <td><c:if test="${reservation.repairRecords!=''}">
        	                 ${reservation.repairRecords}
        	             </c:if>
        	            <c:if test="${reservation.labRoomDevice.user.username == user.username  }">
			        	<c:if test="${reservation.CLabRoomDeviceRepairStatus.id!=3}">
			        	<a href="${pageContext.request.contextPath}/device/repairDevice?idKey=${reservation.id}">维修</a>
			        	</c:if>
			        	</c:if>
        	            </td>
                        <td><c:if test="${reservation.CLabRoomDeviceRepairStatus!=''}">
			        	${reservation.CLabRoomDeviceRepairStatus.name}
			        	</c:if>
			        	</td>
			        	</tr>
                </tbody>
            </table>
</div>
</div>
</div>
</div>
</div>
</div>
</c:forEach>


<c:forEach items="${operationItem}" var="s">
<div id="operationRecords${s.id}" class="easyui-window" modal="true" title="实验项目卡展示" closed="true" iconCls="icon-add" style="width:750px;">
<%-- <div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">我的工作平台</a></li>
	<li class="end"><a href="${pageContext.request.contextPath}/personal/message/mySelfInfo">个人中心</a></li>
</ul>
</div>
</div> --%>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<!-- <div class="title">实验室项目平台</div> -->
							<table>
						<thead>
							<tr>
							<th class="thead" width="10%">实验室编号</th>
							<th class="thead" width="10%">实验名称</th>
							<th class="thead" width="10%">所属课程</th>
							<th class="thead" width="14%">创建时间</th>
							</tr>
						</thead>
					<tbody>

				    <td>${s.experimentItemCardNumber}</td>
				    <td>${s.lpName }</td>
				    <td>${s.schoolCourseByClassNo.courseName}</td>
				            	<td >
 <fmt:formatDate dateStyle="short" type="both" value="${s.createTime.time}"/>
        	</td>
</tr>
					</tbody>						
					</table>
</div>
</div>
</div>
</div>
</div>
</div>
</c:forEach>


<c:forEach items="${coperationItem}" var="s">
<div id="coperationRecords${s.id}" class="easyui-window" modal="true" title="实验项目卡展示" closed="true" iconCls="icon-add" style="width:750px;">
<%-- <div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">我的工作平台</a></li>
	<li class="end"><a href="${pageContext.request.contextPath}/personal/message/mySelfInfo">个人中心</a></li>
</ul>
</div>
</div> --%>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<!-- <div class="title">实验室项目平台</div> -->
							<table>
						<thead>
							<tr>
							<th class="thead" width="10%">实验室编号</th>
							<th class="thead" width="10%">实验名称</th>
							<th class="thead" width="10%">所属课程</th>
							<th class="thead" width="14%">创建时间</th>
							<th class="thead" width="14%">状态</th>
							<th class="thead" width="14%">操作</th>
							</tr>
						</thead>
					<tbody>
                    <tr>
				       <td>${s.experimentItemCardNumber}</td>
				       <td>${s.lpName }</td>
				       <td>${s.schoolCourseByClassNo.courseName}</td>
				       <td><fmt:formatDate dateStyle="short" type="both" value="${s.createTime.time}"/></td>
				       <td><c:if test="${s.conservess==2}">
												<c:if test="${s.auditStatus!= null}">
													<c:if test="${s.auditStatus==1 }">
														<a
															href="${pageContext.request.contextPath}/operation/projectCentrolManagerView?idkey=${s.id}&tage=0">审核通过</a>
													</c:if>
													<c:if test="${s.auditStatus==2 }">
														<a
															href="${pageContext.request.contextPath}/operation/projectCentrolManagerView?idkey=${s.id}&tage=0">
															审核拒绝</a>
													</c:if>
													<c:if test="${s.auditStatus==3 }">
														<a
															href="${pageContext.request.contextPath}/operation/projectCentrolManagerView?idkey=${s.id}&tage=0">审核中
														</a>
													</c:if>
												</c:if>
												<c:if test="${s.auditStatus == null}">
													<a class="btn btn-common"
														href="${pageContext.request.contextPath}/operation/projectCentrolManagerView?idkey=${s.id}&tage=0">未审核
													</a>
												</c:if>
											</c:if> <c:if test="${s.conservess==1}">未提交  </c:if></td>
				                           <td> <c:if test="${s.conservess==1}">
												   <a href="javascript:void(0)" onclick="zln(${s.id});">删除</i></a>
												</c:if> 
										        <c:if test="${s.conservess==1}">
											        <a href="${pageContext.request.contextPath}/operation/updatprojectCentrolManagerView?idkey=${s.id}&tage=0"  >编辑</a>
											    </c:if>
											    <!-- 未提交 -->
											    <c:if test="${s.conservess==1}">
												    <a class="btn btn-common" href="${pageContext.request.contextPath}/operation/operationitemtijao?idkey=${s.id}&tage=0">提交</a>
											    </c:if></td>
                    </tr>
					</tbody>						
					</table>
</div>
</div>
</div>
</div>
</div>
</div>
</c:forEach>

</body>
</html>

