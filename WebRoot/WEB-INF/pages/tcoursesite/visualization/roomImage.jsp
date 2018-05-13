<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>设备添加</title>
    <meta name="decorator" content="none"/>
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
  <link href="${pageContext.request.contextPath}/css/visualization/floor/table.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/visualization/floor/style.css" rel="stylesheet" type="text/css">
  

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/floor/jquery-1.11.0.min.js"></script>
    <!--<script type="text/javascript" src="js/jquery.mousewheel.js"></script>
    <script type="text/javascript" src="js/perfect-scrollbar.js"></script>
<link href="css/perfect-scrollbar.css" rel='stylesheet' type='text/css'>-->
    <script type="text/javascript">
    function mouseMove(ev) {
        Ev = ev || window.event;
        var mousePos = mouseCoords(ev);  
        var img_width=$("#viewer_img").width();
        var img_height=$("#viewer_img").height();
        var percentage_w=(mousePos.x/img_width).toFixed(2)
        var percentage_h=(mousePos.y/img_height).toFixed(2)
        //console.log(img_width,mousePos.x,percentage_w)
        document.getElementById("xCoordinate").value = percentage_w;
        document.getElementById("yCoordinate").value = percentage_h;
        //新建设备窗口打开
        if($(".equipment_inforamtion").is(':hidden')){
            $("#labRoomDeviceId").val("");
            $("#deviceNumber").val("");
            $("#deviceName").val("");
        }

        $(".equipment_inforamtion").show()
    }

    function mouseCoords(ev) {
            if (ev.pageX || ev.pageY) {
                return {
                    x: ev.pageX,
                    y: ev.pageY
                };
            }
            return {
                x: ev.clientX + document.getElementById("viewer_img").scrollLeft - document.body.clientLeft,
                y: ev.clientY + document.getElementById("viewer_img").scrollTop - document.body.clientTop
            };
        }
        //document.getElementById("viewer_img").ondblclick = mouseMove;
    document.ondblclick = mouseMove;
    setInterval("TT()", 10);
    function TT() {
        var Height = $(window).height();
        var Width = $(window).width()
        $(".panorama").height(Height);
        var img_w=$("#viewer_img").width()
        $(".ksajdfh").width(img_w)
    }

    //关闭设备窗口
    function cancel(){
    	$(".equipment_inforamtion").hide()
    }

    //编辑设备
    function editDevice(id){
    	$.ajax({
    		type: "POST",
    		url: "${pageContext.request.contextPath}/tcoursesite/visualization/editDevice",
    		data: {'id':id},
    		dataType:'json',
    		success:function(data){
    			$.each(data,function(key,values){  
    				//alert(key+"="+values);
    				//document.getElementById(key).innerHTML=values;
    				$("#"+key).val(""+values);
    			 }); 
    		},
    		error:function(){
    			//alert("加载课表失败!");
    			}
    	});
    	$(".equipment_inforamtion").show()
    }
    
    //删除设备
    function deletDevice(){
    	var labRoomDeviceid = $("#labRoomDeviceId").val();
    	if(labRoomDeviceid == ''){
    		$(".equipment_inforamtion").hide();
    		return false;
    	}else{
    		$.ajax({
        		type: "POST",
        		url: "${pageContext.request.contextPath}/tcoursesite/visualization/deletDevice",
        		data: {'labRoomDeviceId':labRoomDeviceid},
        		success:function(data){
        			//alert("yes!");
        			if(data){
        				$(".equipment_inforamtion").hide();
        				$("#device"+labRoomDeviceid).remove();
					}
        		},
        		error:function(){
        			//alert("失败!");
        			}
        	});
    		//window.location = "${pageContext.request.contextPath}/tcoursesite/visualization/deletDevice?labRoomDeviceId="+labRoomDeviceid;
    	}
    }
    
	//首页
	function saveDevice(){
		$("#myForm").submit();
	}
    
    </script> 
    
    
        
    
    <style>
        * {
            margin: 0px;
        }
        .panorama {
            height: 100%;
            position: relative;
        }
        
        .panorama img {
            height: 100%;
        }
        
        .equipment_icon{
        	position:absolute;
        	
        	z-index:1;
        }
    </style>
    
</head>

<body style="" class="selected_floor">
    <div class="layout_panorama_container">
    <div style="position:relative; width:100%;" class="ksajdfh">
    <c:forEach items="${labRoomDevices}" var="labRoomDevice"  varStatus="i">
    <div id="device${labRoomDevice.id}" class="equipment_icon" style="left:${100*labRoomDevice.xCoordinate}%;top:${100*labRoomDevice.yCoordinate}%;">
    <a href='javascript:void(0)' onclick="editDevice(${labRoomDevice.id})">
    <img src="${pageContext.request.contextPath}/images/visualization/floor/icon.png" ></a></div>
    </c:forEach>
   
        <div class="panorama ">
            <c:forEach items="${labRoom.commonDocuments}" var="d">
				<c:if test="${d.type==3}">
				    <img src="${pageContext.request.contextPath}/${d.documentUrl}" id="viewer_img">
				</c:if>
			</c:forEach>
        </div>
       </div>
    </div>

                <div class="equipment_inforamtion">
                <img class="close" src="${pageContext.request.contextPath}/images/visualization/floor/msg_close.png"  onclick="cancel()">
                <form:form id="myForm" action="${pageContext.request.contextPath}/tcoursesite/visualization/saveDevice" method="POST" modelAttribute="labRoomDevice">     
                    	<div class="">
                     	<h2 class="equipment_name" >编辑设备信息</h2>
                     	
                     </div>
                     <table class="equipment_info">
                       <tr> 
                       <th>鼠标X轴:</th> 
                       <td><form:input id="xCoordinate" type="text" path="xCoordinate" /></td>
                       <th>鼠标Y轴:</th>
                       <td><form:input id="yCoordinate" type="text" path="yCoordinate" /></td>
                       </tr>
                       <tr>
                       <th>实验设备:</th>
	                   <td colspan="3" ><form:select id="labRoomDeviceId" path="id" class="chzn-select">
					       <c:forEach items="${labRoom.labRoomDevices}" var="labRoomDevice"  varStatus="i">
						       <form:option value="${labRoomDevice.id}">${labRoomDevice.schoolDevice.deviceNumber} &nbsp;&nbsp;${labRoomDevice.schoolDevice.deviceName}</form:option>
						   </c:forEach>
					   </form:select></td>
                       </tr>
                       
                       <tr>
                       <th>实验视频:</th>
	                   <td colspan="3" >
	                   <form:select id="qRCodeUrl" path="qRCodeUrl" class="chzn-select">
					       <c:forEach items="${wkFolders}" var="wkFolder"  varStatus="i">
						       <form:option value="${wkFolder.qRCodeUrl}">${wkFolder.name}</form:option>
						   </c:forEach>
					   </form:select>
					   </td>
                       </tr>
                       
                       <tr style="display:none;">
                       	<th>实验室ID</th>
                       <td><form:input id="labRoomId" type="text" path="labRoom.id" value="${labRoom.id}"/></td>
                       </tr>
                     </table>
                     <input type="submit" value="提交" class="alt_btn" onclick="saveDevice()">
                     <input type="button" value="删除" class="alt_btn" onclick="deletDevice()">
                     
                     
                </form:form>
                </div>
    
</body>

</html>