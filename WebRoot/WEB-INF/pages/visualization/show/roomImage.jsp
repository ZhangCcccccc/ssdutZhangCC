<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>西安电子科技大学可视化</title>
    <meta name="decorator" content="none"/>
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">

    <link href="${pageContext.request.contextPath}/css/visualization/floor/style.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/visualization/floor/table.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/visualization/floor/layout_header.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/floor/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/visualization/floor/drag.js"></script>
    <!--<script type="text/javascript" src="js/jquery.mousewheel.js"></script>
    <script type="text/javascript" src="js/perfect-scrollbar.js"></script>
	<link href="css/perfect-scrollbar.css" rel='stylesheet' type='text/css'>-->
    <script type="text/javascript">
        setInterval("TT()", 100);

        function TT() {
            var Height = $(window).height();
            var Width = $(window).width()
            $(".pv-inner,.drag_container,.panorama").height(Height);
            $(".drag_container").width(Width);
            var img_w=$("#viewer_img").width();
            var img_H=$("#viewer_img").height();
        	$(".panorama").width(img_w);
        	//console.log(img_H,img_w)
        } 
        
            //编辑设备
    function lookDevice(id){
    	$.ajax({
    		type: "POST",
    		url: "${pageContext.request.contextPath}/visualization/show/lookDevice",
    		data: {'id':id},
    		dataType:'json',
    		success:function(data){
    			$.each(data,function(key,values){  
    				//alert(key+"="+values);
    				//document.getElementById(key).innerHTML=values;
    				if(key=="qRCodeUrl"){
    					$("#qRCodeUrl").attr("src", $("#contextPath").val()+values);
    				}else{
    					document.getElementById(key).innerHTML=values;
        				$("#"+key).val(""+values);
    				}
    				if(key=="dimensionalCode"){
    					$("#dimensionalCode").attr("src", $("#contextPath").val()+"/"+values);
    				}else{
    					document.getElementById(key).innerHTML=values;
        				$("#"+key).val(""+values);
    				}
    				
    			 }); 
    		},
    		error:function(){
    			//alert("加载课表失败!");
    			alert(id);
    			}
    	});
    	$(".equipment_inforamtion").show()
    }
        //关闭设备窗口
    function cancel(){
    	$(".equipment_inforamtion").hide()
    }
    </script>
       <style>
        * {
            margin: 0px;
        }
        .panorama {
            position: relative;
        }
        
        .panorama img {
            height: 100%;
            position:relative;
        }
        .drag_container{
        	position:relative;
        	    z-index: 0;
        	overflow:hidden;
        }
        
    </style> 
</head>
<body style="" class="selected_floor" >
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
    <div class="layout_panorama_container">
 		<div class="drag_container">
        <div class="panorama" id="dragDiv" style="position:relative; background-color:#FFFFFF;left:0px;top:0px;filter:alpha(opacity=100);opacity:1;">
            <c:forEach items="${labRoom.commonDocuments}" var="d">
				<c:if test="${d.type==3}">
				    <img src="${pageContext.request.contextPath}/${d.documentUrl}" id="viewer_img">
				</c:if>
			</c:forEach>
            
		    <c:forEach items="${labRoomDevices}" var="labRoomDevice"  varStatus="i">
		    <div id="device${labRoomDevice.id}" class="equipment_icon" style="left:${100*labRoomDevice.xCoordinate}%;top:${100*labRoomDevice.yCoordinate}%;">
		    <a href='javascript:void(0)' id="lookdevice" onmouseup="lookDevice(${labRoomDevice.id})">
		    <img src="${pageContext.request.contextPath}/images/visualization/floor/icon.png" ></a></div>
		    </c:forEach>
        </div>
        </div>
        <div class="layout_logo_container" style="position:fixed"><img src="${pageContext.request.contextPath}/images/visualization/floor/sub_logo.png">
        </div>     
    </div>
    
                    <div class="equipment_inforamtion">
                  
                    <img class="close" src="${pageContext.request.contextPath}/images/visualization/floor/msg_close.png"  onclick="cancel()">
                <form:form id="myForm" action="${pageContext.request.contextPath}/visualization/saveDevice" method="POST" modelAttribute="labRoomDevice">     
                     <div class="">
                     	<h2 class="equipment_name" id="deviceName"></h2>
                     	
                     </div>
                     	
                     <table class="equipment_info">
                       <tr style="display:none"> 
                       <th>鼠标X轴:</th> 
                       <td><form:input id="xCoordinate" type="text" path="xCoordinate"/></td>
                       <th>鼠标Y轴:</th>
                       <td><form:input id="yCoordinate" type="text" path="yCoordinate"/></td>
                       </tr>
                       <tr>
                       <th>设备编号:</th>
                       <td><form:input id="deviceNumber" type="text" path="schoolDevice.deviceNumber" readonly="true"/></td>  
                        <th>生产厂家:</th>
                       <td><form:input id="deviceSupplier" type="text" path="schoolDevice.manufacturer" readonly="true" /></td>           
                     <!--    <th>仪器规格:</th>
                       <td><form:input id="deviceFormat" type="text" path="schoolDevice.deviceNumber"/></td>     -->                          
                       </tr>
                       <tr>
            		  
                       <th>仪器型号:</th>
                       <td><form:input id="devicePattern" type="text" path="schoolDevice.deviceNumber" readonly="true"/></td>                    
                       
            		   <th>所属学院:</th>
                       <td><form:input id="academyName" type="text" path="schoolDevice.schoolAcademy.academyName" readonly="true"/></td>   
                       <%--<th>仪器型号:</th>
                       <td><form:input id="devicePattern" type="text" path="schoolDevice.deviceNumber"/></td>                    
                       --%></tr>
                       <tr>
                        <th>设备信息:</th>
                        <td colspan="4">
                       <img  id="dimensionalCode" src="${pageContext.request.contextPath}/images/visualization/floor/msg_close.png" style="wdith:100px;height:100px;margin-left:100px;" >
                       </td>
                        <th>知识视频:</th>
                       <td colspan="4">
                       <img  id="qRCodeUrl" src="${pageContext.request.contextPath}/images/visualization/floor/msg_close.png" style="wdith:100px;height:100px;margin-left:100px;" >
                       </td>
                       </tr>
                       
                       <tr style="display:none;">
                       <td>实验设备id</td>
                       <td><form:input id="labRoomDeviceId" type="text" path="id" /></td>
                       <th>实验室编号</th>
                       <td><form:input id="labRoomId" type="text" path="labRoom.id" /></td>   
                       </tr>
                     </table>
                     <!-- <input type="button" value="返回" class="alt_btn""> -->
                     
                </form:form>
                </div>
    
</body>

</html>