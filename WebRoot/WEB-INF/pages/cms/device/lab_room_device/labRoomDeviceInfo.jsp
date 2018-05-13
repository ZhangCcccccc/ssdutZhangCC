<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript">
		function findLabRoomDevicePermitUserList(url){
			$.ajax({
				'url':url,
				'type':'POST',
				'success':function(data){
					$("#permitUsers").find("span[title='totalRecords']").text(data['pageModel']['totalRecords']);
					$("#permitUsers").find("span[title='totalPage']").text(data['pageModel']['totalPage']);
					var tbodyhtml = "";
					$.each(data['userList'],function(i,value){
						tbodyhtml += "<tr>" +
									"<td>"+value[0]+"</td>" +
									"<td>"+value[1]+"</td>" +
									"<td>"+value[2]+"</td>" +
									"<td>"+value[3]+"</td>" +
									"</tr>";
					})
					$("#permitUsers tbody").html(tbodyhtml);
					var url = "${pageContext.request.contextPath}/cms/device/labRoomDevicePermitUserList?labRoomDeviceId="+data['labRoomDeviceId']+"&currpage=";
					$("#permitUsers a:eq(0)").attr("onclick","findLabRoomDevicePermitUserList('"+url+"1')");
					$("#permitUsers a:eq(1)").attr("onclick","findLabRoomDevicePermitUserList('"+url+data['pageModel']['previousPage']+"')");
					$("#permitUsers a:eq(2)").attr("onclick","findLabRoomDevicePermitUserList('"+url+data['pageModel']['nextPage']+"')");
					$("#permitUsers a:eq(3)").attr("onclick","findLabRoomDevicePermitUserList('"+url+data['pageModel']['totalPage']+"')");
					var optionhtml = "";
					for(var i=1;i<=data['pageModel']['totalPage'];i++){
						optionhtml += "<option value='"+i+"' ";
						if (data['pageModel']['currpage']==i) {
							optionhtml += "selected='selected'";
						}
						optionhtml += ">"+i+"</option>";
					}
					$("#permitUsers select").html(optionhtml);
					$("#permitUsers select").attr("onchange","getPermitUserIndex(this,'"+url+"')");
				}
			})
		}
		function findLabRoomDeviceTrainingList(url){
			$.ajax({
				'url':url,
				'type':'POST',
				'success':function(data){
					$("#trainingRecord").find("span[title='totalRecords']").text(data['pageModel']['totalRecords']);
					$("#trainingRecord").find("span[title='totalPage']").text(data['pageModel']['totalPage']);
					var tbodyhtml = "";
					$.each(data['trainingList'],function(i,value){
						tbodyhtml += "<tr>" +
									"<td>"+value[0]+"</td>" +
									"<td>"+value[1]+"</td>" +
									"<td>"+value[2]+"</td>" +
									"<td>"+value[3]+"</td>" +
									"<td>"+value[4]+"</td>" +
									"<td>"+value[5]+"</td>" +
									"<td>"+value[6]+"</td>" +
									"<td>"+value[7]+"</td>" +
									"</tr>";
					})
					$("#trainingRecord tbody").html(tbodyhtml);
					var url = "${pageContext.request.contextPath}/cms/device/labRoomDeviceTrainingList?labRoomDeviceId="+data['labRoomDeviceId']+"&currpage=";
					$("#trainingRecord a:eq(0)").attr("onclick","findLabRoomDeviceTrainingList('"+url+"1')");
					$("#trainingRecord a:eq(1)").attr("onclick","findLabRoomDeviceTrainingList('"+url+data['pageModel']['previousPage']+"')");
					$("#trainingRecord a:eq(2)").attr("onclick","findLabRoomDeviceTrainingList('"+url+data['pageModel']['nextPage']+"')");
					$("#trainingRecord a:eq(3)").attr("onclick","findLabRoomDeviceTrainingList('"+url+data['pageModel']['totalPage']+"')");
					var optionhtml = "";
					for(var i=1;i<=data['pageModel']['totalPage'];i++){
						optionhtml += "<option value='"+i+"' ";
						if (data['pageModel']['currpage']==i) {
							optionhtml += "selected='selected'";
						}
						optionhtml += ">"+i+"</option>";
					}
					$("#trainingRecord select").html(optionhtml);
					$("#trainingRecord select").attr("onchange","getTrainingIndex(this,'"+url+"')");
				}
			})
		}
		function getPermitUserIndex(obj,url){
			findLabRoomDevicePermitUserList(url+$(obj).val());
		}
		function getTrainingIndex(obj,url){
			findLabRoomDeviceTrainingList(url+$(obj).val());
		}
		
		function findLabRoomDeviceTrainingInfo(labRoomDeviceId){
			findLabRoomDevicePermitUserList("${pageContext.request.contextPath}/cms/device/labRoomDevicePermitUserList?labRoomDeviceId="+labRoomDeviceId+"&currpage=1");
			findLabRoomDeviceTrainingList("${pageContext.request.contextPath}/cms/device/labRoomDeviceTrainingList?labRoomDeviceId="+labRoomDeviceId+"&currpage=1");
			$("#deviceTraining").show();
		}
	</script>
</head>
  
<body>
	<div class="tool-box1">
		<div class="title">设备详情</div>
		<table>
			<tr>
				<th>设备编号</th><td>${device.schoolDevice.deviceNumber}</td>
				<th>设备名称</th><td>${device.schoolDevice.deviceName}</td>
			</tr>
			<tr>
				<th>设备管理员</th><td>${device.user.cname}</td>
				<th>设备状态</th><td>${device.CDictionaryByDeviceStatus.CName}</td>
			</tr>
			<tr>
				<th>所在实验室</th><td>${device.labRoom.labRoomName}</td>
				<th>生产国别</th><td>${device.schoolDevice.deviceCountry}</td>
			</tr>
			<tr>
				<th>所属类型</th><td>${device.CDictionaryByDeviceType.CName}</td>
				<th>收费标准</th><td>${device.CDictionaryByDeviceCharge.CName}</td>
			</tr>
			<tr>
				<th>仪器型号</th><td>${device.schoolDevice.devicePattern}</td>
				<th>生产厂家</th><td>${device.schoolDevice.manufacturer}</td>
			</tr>
			<tr>
				<th>费用</th><td>${device.price}</td>
			</tr>
			<tr>
				<th>主要技术指标</th><td>${device.indicators}</td>
			</tr>
			<tr>
				<th>功能应用范围</th><td>${device.function}</td>
			</tr>
			<tr>
				<th>技术特色</th><td>${device.features}</td>
			</tr>
			<tr>
				<th>主要应用领域</th><td>${device.applications}</td>
			</tr>
		</table>
	</div>
	
	<a href="javascript:void(0);" onclick="findLabRoomDeviceTrainingInfo('${device.id}')">设备培训</a>
	<a href="javascript:void(0);" onclick="findLabRoomDeviceTrainingList('${pageContext.request.contextPath}/cms/device/labRoomDeviceTrainingList?labRoomDeviceId=109&currpage=1')">设对阵双方备培训</a>
	<a href="javascript:void(0);" onclick="findLabRoomDevicePermitUserList('${pageContext.request.contextPath}/cms/device/labRoomDevicePermitUserList?labRoomDeviceId=109&currpage=1')">设备dsfdslh培训</a>
	<a href="${pageContext.request.contextPath}/cms/device/reservationList?labRoomDeviceId=${device.id}&currpage=1">预约记录</a>
	<div id="deviceTraining" style="display: none;">
		<div id="permitUsers">
			<table>
				<thead>
					<tr>
						<th>学号</th>
						<th>姓名</th>
						<th>身份</th>
						<th>所属学院</th>
					</tr>
				</thead>
				<tbody>
				
				</tbody>
			</table>
			<div class="page">
		        <span title="totalRecords">${pageModel.totalRecords}</span>条记录,共<span title="totalPage">${pageModel.totalPage}</span>页
			    <a href="javascript:void(0)" onclick="" target="_self">首页</a>			    
				<a href="javascript:void(0)" onclick="" target="_self">上一页</a>
				第<select onchange="">
			    </select>页
				<a href="javascript:void(0)"  onclick="" target="_self">下一页</a>
			 	<a href="javascript:void(0)"  onclick="" target="_self">末页</a>
		    </div>
						
		</div>
		<div id="trainingRecord">
			<table>
				<thead>
					<tr>
						<th width="20%">培训内容</th>
						<th>培训地点</th>
						<th>培训时间</th>
						<th>主讲教师</th>
						<th>培训人数</th>
						<th>学期</th>
						<th>状态</th>
						<th>通过率</th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
			<div class="page" >
		        <span title="totalRecords"></span>条记录,共<span title="totalPage"></span>页
			    <a href="javascript:void(0)" onclick="" target="_self">首页</a>			    
				<a href="javascript:void(0)" onclick="" target="_self">上一页</a>
				第<select onchange="">
			    </select>页
				<a href="javascript:void(0)"  onclick="" target="_self">下一页</a>
			 	<a href="javascript:void(0)"  onclick="" target="_self">末页</a>
		    </div>
		</div>
	</div>
<div style="clear:both"></div>
</body>
</html>
