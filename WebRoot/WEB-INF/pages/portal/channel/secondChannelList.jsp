<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8">
		<title>西安电子科技大学首页</title>
		<script src="${pageContext.request.contextPath}/js/xidlims/second/jquery-1.8.3.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/xidlims/second/index.js"></script>
		<link href="${pageContext.request.contextPath}/css/xidlims/second/index.css" type="text/css" rel="stylesheet">
		<!--<link  href="LESS/font-awesome.min.css" type="text/css" rel="stylesheet">	-->
	</head>
	<body>
		<!--header-->
		<div class="con">
			
		<!--<img src="${pageContext.request.contextPath}/images/xidlims/second/蓝色二级blue_02.png">-->
			<div class="con1">
				<img src="${pageContext.request.contextPath}/images/xidlims/second/标题_03.png">
			</div>
			
				
				
			<!--遮罩层问题-->
			<div class="con2">
			<div class="con22">
				<img src="${pageContext.request.contextPath}/images/xidlims/second/蓝色二级blue_03_看图王.png" style="display: none;">
					<div class="con2221">
					<p></p>
					</div>
			</div>
			<div class="con22">
				<img src="${pageContext.request.contextPath}/images/xidlims/second/蓝色二级blue_03_看图王.png">
					<div class="con222">
					<p>药学学科综合训练中心</p>	
					</div>
			</div>
			<div class="con22">
				<img src="${pageContext.request.contextPath}/images/xidlims/second/蓝色二级blue_03_看图王.png" style="display: none;">
					<div class="con2222">
					<p>医学虚拟教学实验平台</p>
					</div>
			</div>
			<div class="clear"></div>
			<div class="con22">
				<img src="${pageContext.request.contextPath}/images/xidlims/second/蓝色二级blue_03_03.png"  style="display: none;">
					<div class="con2223">
					<p>基础医学教学实验中心</p>
					</div>
			</div>
			<div class="con22">
				<img src="${pageContext.request.contextPath}/images/xidlims/second/蓝色二级blue_03_03.png">
					<div class="con222">
					<p>临床医学医术实训中心</p>
					</div>
			</div>
			<div class="con22">
				<img src="${pageContext.request.contextPath}/images/xidlims/second/蓝色二级blue_03_03.png"  style="display: none;">
					<div class="con2224">
					<p>生物基础课教学实验中心</p>	
					</div>
			</div>
		 </div>
		</div>
		<!--center-->
		<div class="com">
			<img src="${pageContext.request.contextPath}/images/xidlims/second/线条_03.png">
		</div>
			
		<div class="btn">
			<!--选项卡-->
			<div class="btn1">
				<ul>
					<c:forEach items="${cmsChannels }" var="channel" end="3" varStatus="i">
						<li <c:if test="${i.first }">class="on"</c:if>><p>${channel.title }</p></li>
					</c:forEach>
				</ul>
			</div>
			
			<div class="btn2">
				<c:forEach items="${cmsChannels }" var="channel" varStatus="i">
			 		<div class="btn2${i.index }">
						<div class="btn201">
							<h4>${channel.title }</h4>
							<p><img src="${pageContext.request.contextPath}/images/xidlims/second/横线_03.png"></p>
							<a href="javacsript:;"><img src="${pageContext.request.contextPath}/images/xidlims/second/圆圈_03.png"></a>
						</div>
						
						<div class='btn202'>
							<c:forEach items="${channel.cmsArticles }" var="article" end="1" varStatus="j">
								<div class="btn2021">
									<div class="btn20210">
										<div class="btn00"></div>
										<p style="color:gray; font-size: 12px;"><fmt:formatDate pattern="yyyy-MM-dd" value="${article.createTime.time }"></fmt:formatDate></p>
										<a href="javascript:;">${article.title }</a>
									</div>
									<div class="btn20211">
										<img src="${pageContext.request.contextPath}/images/xidlims/second/握手图片_03.png">
									</div>
									<div class="btn20212">
										<span>${article.news }
										</span>
									</div>
								</div>
								<hr>
								<div></div>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
			</div>
			
			
		</div>
		<!--footer-->
		<div class="zxn">
			<div class="zxn0"></div>
			<div class="zxn1">
				<div class="zxn10"></div>
				<p><a href="javascript:;">网站导航</a></p>
				<div class="zxn11"></div>
				<a href="javascript:;">通知公告</a>
				<a href="javascript:;">培训系统</a><br>
				<a href="javascript:;">预约考试系统</a>
				<a href="javascript:;">联系我们</a>
				<div class="zxn13"></div>
				<hr>
			</div>
			<div class="zxn2">
				<div class="zxn10"></div>
				<p class="zxn12">联系方式</p>
				<div class="zxn11"></div>
				<p>地址：苏州市工业园区仁爱路199号</p>
				<p>电话：888888888</p>
				<p>邮编：20023239</p>
				<p>E-mail:aadmin@.com</p>
				<div class="zxn13"></div>
				<hr>
					<div class="zxn13"></div>
			        <p class="zxn14"><button><font>&nbsp;Top&nbsp;</font></button></p>
			        <div class="zxn13"></div>
				<span>版权所有：西安电子科技大学</span>
				
				<span>Power by Gvsun</span>
				
			</div>
			<div class="zxn3">
				<div class="zxn10"></div>
				<p><a href="javascript:;">站外链接</a></p>
				<div class="zxn11"></div>
				<a href="javascript:;">南京医科大学</a>
				<a href="javascript:;">中国药科大学</a><br>
				<a href="javascript:;">暨南大学药学院</a>
				<a href="javascript:;">中山大学药学院</a>
				<div class="zxn13"></div>
				<hr>
			</div>
			<div class="zxn4"></div>
		</div>
	</body>
</html>
