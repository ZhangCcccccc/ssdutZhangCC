<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta name="Keywords" content="西安电子科技大学">
    <meta name="Description" content="西安电子科技大学">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />    
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/index/global_min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery.glide.js"></script>
</head>
 
<body>
<div id="content_suda">
	<c:if test="${empty loginUser }">
           <div id="index_left">
	       <div class="box3_suda">
		     <div class="box1_suda_title" style="border:none; text-indent:1em; width:360px; margin-right:0px;">			   
			 课程列表
			 <div class="more_suda"><a href="">更多>></a></div>
		     </div>
		     <div class="cutline_suda"></div>
		     <table class="table1_suda" cellpadding="0" cellspacing="0">
                         <c:forEach items="${sites }" var="site">
			     <tr>
				<th></th>
	    			<td> ${site.title }（${site.userByCreatedBy.cname }）</td>
	    		     </tr>
                         </c:forEach>					
	    	     </table>			
		</div>
		<div class="box4_suda">
		    <img src="${pageContext.request.contextPath}/images/songProject/course.jpg" />
		</div>
	    </div>
            <div id="index_right">
	        <div class="box2_suda">
		     <div class="box2_suda_title">
			    用户登录
		     </div>			
		     <div class="box2_suda_login">
			    <form id="subform" name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'>
				<fram>
				   <div class="login-box_suda">
					<input type="text" name="j_username" placeholder="用户账号" class="username_suda"/>
				   </div>
				   <div class="login-box_suda">
					<input type="password" name="j_password" placeholder="密码" class="password_suda"/>
				   </div>
					<input type="submit" value="" class="loginbutton_suda"/>
				</fram>
                         <%
				session.setAttribute("LOGINTYPE", "portal");
			 %>
	                    </form>
		     </div>
		 </div>
	     </div>
         </c:if>
            <div class="box1_suda">
	         <img src="${pageContext.request.contextPath}/images/songProject/lab.jpg" />
		 <div class="box1_suda_title">
		      <div class="box1_suda_img"></div>
		      实验室预约动态
		      <div class="more_suda"><a href="">更多>></a></div>				
		 </div>
		 <table class="table_suda"  cellpadding="0" cellspacing="0">
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
			 <td >第<c:forEach items="${reservation.week}"  var="a" ><c:if test="${a!=null}">${a }</c:if></c:forEach>周
     			      星期<c:forEach items="${reservation.day}"  var="d" ><c:if test="${d!=null}">${d }</c:if></c:forEach>
     			      第<c:forEach items="${reservation.time}"  var="f" ><c:if test="${f!=null}">${f }</c:if></c:forEach>节</td>
			 <td >${reservation.name }</td>
                      </tr>
                      </c:forEach>		      
                  </table>
              </div>
              <div class="box1_suda">
		   <img src="${pageContext.request.contextPath}/images/songProject/device.jpg" />
		   <div class="box1_suda_title">
			<div class="box1_suda_img"></div>
			设备预约动态
			<div class="more_suda"><a href="">更多>></a></div>
		   </div>
		   <table class="table_suda"  cellpadding="0" cellspacing="0">
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
			   <td ><fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy/MM/dd HH:mm:ss"/> - <fmt:formatDate value="${reservation.endtime.time}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
			   <td >${reservation.CReservationProperty.CName }</td>
			</tr>
			</c:forEach>	    
                   </table>
              </div>
              <div style="clear:both"></div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index/global.js"></script>
</body>
</html>
