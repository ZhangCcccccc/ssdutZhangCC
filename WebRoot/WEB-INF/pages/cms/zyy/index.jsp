<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上海中医药大学解剖实验课</title>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/songProject/style.css" />
<script type="text/javascript">
function setTab(m,n){
var menu=document.getElementById("tab"+m).getElementsByTagName("li");  
var showdiv=document.getElementById("tablist"+m).getElementsByTagName("div");  
for(i=0;i<menu.length;i++)
{
   menu[i].className=i==n?"now":""; 
   showdiv[i].style.display=i==n?"block":"none"; 
}
}
</script>
<style>
	.log_out{
	height:25px;
		line-height:25px;
		color:#fff;
		background:#4BA6EC;
		padding:4px 10px;
		text-decoration:none;
		border-radius:8px;
	}
	.manage{
		height:25px;
		line-height:25px;
		color:#fff;
		background:#4BA6EC;
		padding:4px 10px;
		text-decoration:none;
		border-radius:8px;
	}
</style>
</head>
<% 
//前端登录标记
session.setAttribute("LOGINTYPE","ZYY");  
%>
<div id="content">

<!-- 左侧栏开始  -->
    <div id="index_left">
      <div id="box1">
		     
		    <div class="title1">${labIntroduction.channelName}</div>
	        <div class="box1_img">
		        <c:if test="${labIntroduction.TCourseSiteArticals.size()>0 }">
		        	<c:forEach items="${labIntroduction.TCourseSiteArticals}" var="artical" end="0">
		        		<img src="${pageContext.request.contextPath}${artical.imageUrl}" />
		        	</c:forEach>
		        </c:if>
		        <c:if test="${labIntroduction.TCourseSiteArticals.size()==0 }">
        			暂无图片
		        </c:if>
	        </div>
	        
			<div class="box1_content">
			<li class="text1">
			<c:if test="${labIntroduction.TCourseSiteArticals.size()>0 }">
	        	<c:forEach items="${labIntroduction.TCourseSiteArticals}" var="artical" end="0">
	        		${artical.content}
	        	</c:forEach>
	        </c:if>&nbsp;&nbsp;</li>
			<li class="more1">
				<a href="${pageContext.request.contextPath}/cms/channel/findArticalsByChannelId?channelId=${labIntroduction.id }&currpage=1" style="color:#3797E1; text-decoration:none;font-size:8px; line-height:30px;">更多>></a>
	       	</li>
			</div>
	  </div>
	    
	    
		<div id="box2">
		    <div class="title1">题库与测试</div>
			<div class="box2">
				<ul>
					<c:forEach items="${tCourseSites }" var="tCourseSite" end="3">
						<li>
							<a href="${pageContext.request.contextPath}/cmsteaching/exam/examList?cid=${tCourseSite.id }&currpage=1&status=0" <c:if test="${empty loginUser }">onclick="alert('请先登录！');return false;"</c:if>>${tCourseSite.title}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
<!-- 左侧栏结束 -->	

<!-- 中间栏开始 -->	
	
	<div id="index_middle">
	    <div id="box3">
		    <div id="tab1">
                <ul>
                	<c:forEach items="${map }" var="var" end="3" varStatus="i">
                		<c:if test="${i.index==0 }">
	                		<li onmouseover="setTab(1,${i.index})" class="now">${var.key.name }</li>
                		</c:if>
                		<c:if test="${i.index!=0 }">
	                		<li onmouseover="setTab(1,${i.index})">${var.key.name }</li>
                		</c:if>
                	</c:forEach>
				   	    
                </ul>
            </div>
            
			<div id="tablist1">
				<c:forEach items="${map }" var="var" end="3" varStatus="i">
					<c:if test="${i.count == 1 }">
						<div class="tablist block">
					</c:if>
					<c:if test="${i.count != 1 }">
						<div class="tablist">
					</c:if>
						<p class="button_more">
							<a href="${pageContext.request.contextPath}/cms/channel/findArticalsByChannelId?channelId=${var.key.TCourseSiteChannel.id}&currpage=1">MORE...</a>
						</p>
						<p class="box3_content">
							<li class="text2">   ${var.key.content }</li> 
							<li class="more2">   <a href="${pageContext.request.contextPath}/cms/channel/findArticalsByChannelId?channelId=${var.key.TCourseSiteChannel.id}&currpage=1" style="color:#3797E1; text-decoration:none;font-size:12px;">更多>></a></li>
						</p>
						<ul>
							<!-- 遍历前6篇文章 -->
							<c:forEach items="${var.value.TCourseSiteArticals}"  var = "tCourseSiteArtical" end="5" varStatus="j"> 
								<li>
									<a href="${pageContext.request.contextPath}/cms/channel/findArticalsByChannelId?channelId=${var.value.id}&currpage=1"> ${tCourseSiteArtical.name }</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
				 
			</div>
		</div>
		
		
	 	<div id="box4">
			<div class="title1">实验报告管理</div>
			<div class="button_more"><a href="${pageContext.request.contextPath}/cms/teaching/assignment/index">MORE...</a></div>
			<div class="box4">
			     <ul>
			      <c:forEach items="${reportManagements}"  var = "reportManagement"  varStatus="i" end="3">
			       <li><a href="${pageContext.request.contextPath}/cms/teaching/assignment/findDetail?channelId=${reportManagement.id }&currpage=1">
			       	   <img src="${pageContext.request.contextPath}/images/songProject/book.png" /><br/>${reportManagement.channelName }</a>
			       </li>
			      </c:forEach> 
			  </ul>					 
			</div>
			 
	 	</div>
	 
	 
	</div>
<!-- 中间栏结束 -->		

<!-- 右侧栏开始 -->		
	<div id="index_right">
		<div id="box5">
		     <div class="title2">LOGIN</div>
		     <c:if test="${empty user}">
				 <div class="box5">
				      <form id="form1" name="form1" method="post" action="${pageContext.request.contextPath}/j_spring_security_check" style="height:160px">
					  用户名：<input id="ID" type="text" name="j_username" style="width:148px; height:24px; border:1px solid #c0c0c0; line-height:24px;" name="" value="">
					  密&nbsp;码：<input id="password" name="j_password" type="password" style="width:148px; height:24px;border:1px solid #c0c0c0;line-height:24px;" name="" value="" />
					 <c:if test="${result == 'error' }">
					 	<p style="color: red;">用户名或密码错误！</p>
					 </c:if>
					 <input name="" type="submit" value="" style=" border:none;background-image:url(${pageContext.request.contextPath}/images/songProject/login.png); background-repeat:no-repeat; width:78px; height:24px; position:absolute; right:0px;bottom:8px;" />
					  </form><!-- 把button改成submit -->
				 </div>
			 </c:if>
			 <c:if test="${!empty user}">
			 	<div class="box5">
			 		<c:choose>
						<c:when test="${fn:contains(user.authorities,'SUPERADMIN') }">
							<font style="color:#000;">${user.cname} 超级管理员，您好  </font>
						</c:when>			 		
						<c:when test="${fn:contains(user.authorities,'TEACHER') }">
							<font style="color:#000;">${user.cname} 老师，您好  </font>
						</c:when>			 		
						<c:when test="${fn:contains(user.authorities,'STUDENT') }">
							<font style="color:#000;">${user.cname} 同学，您好  </font>
						</c:when>			 		
						<c:when test="${fn:contains(user.authorities,'FAMILYMEMBER') }">
							<font style="color:#000;">${user.cname} &nbsp;&nbsp;您好  </font>
						</c:when>			 		
			 		</c:choose>
					<div>
						<a href="${pageContext.request.contextPath}/pages/logout-front-redirect.jsp" target="_parent" class="log_out">退出</a>  
						<c:choose>
							<c:when test="${fn:contains(user.authorities,'SUPERADMIN') }">
								<% 
								//前端登录标记修改
								session.setAttribute("LOGINTYPE","ADMIN");  
								%>
				 				<a href="${pageContext.request.contextPath}/login" class="manage">后台管理</a>
							</c:when>
							<c:when test="${fn:contains(user.authorities,'TEACHER') }">
								<% 
								//前端登录标记修改
								session.setAttribute("LOGINTYPE","TEACHER");  
								%>
				 				<a href="${pageContext.request.contextPath}/login" class="manage">后台管理</a>
							</c:when>
						</c:choose>
					</div>
			 			
			 			
			 	</div>
			 	
			 </c:if>
		</div>
		<div id="box6">
		   <div class="box6">
		      <div class="box6_title1"><a href="javascript:void(0);">实验教学平台</a></div>
		      <div class="box6_title2"><a href="javascript:void(0);">实验互动平台</a></div>
			  <div class="box6_title3"><a href="javascript:void(0);">数字解剖平台</a></div>
			  <div class="box6_title4"><a href="javascript:void(0);">遗体捐献纪念馆</a></div>			   
		   </div>
		</div>
	</div>
<!-- 右侧栏结束 -->		
	
</div>

<!-- 友情链接开始  -->
<div id="friendlink">
    <div class="friendlink"><div class="friendlink_title">友情链接</div></div>
	<div class="box7">
	    <ul>
		   <li><a href="http://www.shutcm.edu.cn/web/guest/index">上海中医药大学</a></li>
		   <li><a href="http://lib.shutcm.edu.cn/">上海中医药大学图书馆</a></li>
		   <li><a href="http://www.jpkcw.com/">国家精品课程资源网</a></li>
		   <li><a href="http://www.satcm.gov.cn">国家中医药管理局</a></li>
		   <li><a href="http://www.nsfc.gov.cn">国家自然科学基金委员会</a></li>
	    </ul>		   
	</div>
</div>

<!-- 友情链接结束  -->
<script type="text/javascript">

	//清除富文本编辑框中内容的样式
	$(".text1").find("img").remove();
	$(".text2").find("p > *").removeAttr("style");
</script>
</body>
</html>
