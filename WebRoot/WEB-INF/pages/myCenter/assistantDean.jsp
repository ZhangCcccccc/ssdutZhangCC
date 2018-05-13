<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	
	<head>
		<title></title>
		<meta name="Generator" content="gvsun">
		<meta name="decorator" content="new"/>
		<meta name="Author" content="chenyawen">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="Keywords" content="">
		<meta name="Description" content="">
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/lib.css" />
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
	</head>

	<body>
		<div id="bgheight">
			<script type="text/javascript">
				$(function() {
					$("#bgheight").height($(window).height() / 1);
					$("#maincontent").height($(window).height() / 1.3);
				})
			</script>
			<div class="main_content" id="maincontent">
				<div class="personal_message">
					<div class="per_font">个人信息</div>
					<div class="per_img">
						<img src="${pageContext.request.contextPath}/images/img/user_img.gif" />
					</div>
					<div class="per_font">用户名</div>
					<div>通信工程学院</div>
					<div><c:forEach items="${authorities }" var="curr">
							<c:if test="${sessionScope.authorityName eq  curr.authorityName}">
								${curr.cname }
							</c:if>
						</c:forEach> | 个人中心</div>
				</div>
				<div class="right_content">
					<div class="table_message">
						<div class="notice_tab">
							<div class="table_content">
								<table class="message_tab">
									<tr>
										<th>序号</th>
										<th>栏目</th>
										<th>内容</th>
										<th>操作</th>
									</tr>
									<!-- <tr>
										<td>1</td>
										<td>学生选课</td>
										<td>B1001电子线路实验开始选课</td>
										<td class="tc">
											<i class="fa fa-search blue" title="查看详情"></i>
											<i class="fa fa-times blue" title="删除"></i>
										</td>
									</tr>
									<tr>
										<td>2</td>
										<td>学生选课</td>
										<td>B1001电子线路实验开始选课</td>
										<td class="tc">
											<i class="fa fa-search blue" title="查看详情"></i>
											<i class="fa fa-times blue" title="删除"></i>
										</td>
									</tr>
									<tr>
										<td>3</td>
										<td>学生选课</td>
										<td>B1001电子线路实验开始选课</td>
										<td class="tc">
											<i class="fa fa-search blue" title="查看详情"></i>
											<i class="fa fa-times blue" title="删除"></i>
										</td>
									</tr>
									<tr>
										<td>4</td>
										<td>学生选课</td>
										<td>B1001电子线路实验开始选课</td>
										<td class="tc">
											<i class="fa fa-search blue" title="查看详情"></i>
											<i class="fa fa-times blue" title="删除"></i>
										</td>
									</tr>
									<tr>
										<td>5</td>
										<td>学生选课</td>
										<td>B1001电子线路实验开始选课</td>
										<td class="tc">
											<i class="fa fa-search blue" title="查看详情"></i>
											<i class="fa fa-times blue" title="删除"></i>
										</td>
									</tr>
									<tr>
										<td>6</td>
										<td>学生选课</td>
										<td>B1001电子线路实验开始选课</td>
										<td class="tc">
											<i class="fa fa-search blue" title="查看详情"></i>
											<i class="fa fa-times blue" title="删除"></i>
										</td>
									</tr>
									<tr>
										<td>7</td>
										<td>学生选课</td>
										<td>B1001电子线路实验开始选课</td>
										<td class="tc">
											<i class="fa fa-search blue" title="查看详情"></i>
											<i class="fa fa-times blue" title="删除"></i>
										</td>
									</tr>
									<tr>
										<td>8</td>
										<td>学生选课</td>
										<td>B1001电子线路实验开始选课</td>
										<td class="tc">
											<i class="fa fa-search blue" title="查看详情"></i>
											<i class="fa fa-times blue" title="删除"></i>
										</td>
									</tr>
									<tr>
										<td>9</td>
										<td>学生选课</td>
										<td>B1001电子线路实验开始选课</td>
										<td class="tc">
											<i class="fa fa-search blue" title="查看详情"></i>
											<i class="fa fa-times blue" title="删除"></i>
										</td>
									</tr>
									<tr>
										<td>10</td>
										<td>学生选课</td>
										<td>B1001电子线路实验开始选课</td>
										<td class="tc">
											<i class="fa fa-search blue" title="查看详情"></i>
											<i class="fa fa-times blue" title="删除"></i>
										</td>
									</tr> -->
								</table>
							</div>
							<div class="notice_show" title="隐藏左边">
								<i class="fa fa-hand-o-left"></i>
							</div>
						</div>
						<div class="link_tab">
							<div class="link_line">
								<div class="line_tit">教师排课</div>
								<div class="line_content">
									<a href="#" class="link_modular">
										<i class="fa fa-user-plus"></i>
										<div class="link_modular_div">我的排课</div>
									</a>
									<i class="fa fa-long-arrow-right line-space"></i>
									<a href="#" class="link_modular">
										<i class="fa fa-calendar-check-o"></i>
										<div class="link_modular_div">排课权限</div>
									</a>
									<i class="fa fa-long-arrow-right line-space"></i>
									<a href="#" class="link_modular">
										<i class="fa fa-calendar"></i>
										<div class="link_modular_div">我的课表</div>
									</a>
								</div>
							</div>
							<div class="link_line">
								<div class="line_tit">学生选课</div>
								<div class="line_content">
									<a href="#" class="link_modular">
										<i class="fa fa-th"></i>
										<div class="link_modular_div">我的选课</div>
									</a>
									<i class="fa fa-long-arrow-right line-space"></i>
									<a href="#" class="link_modular">
										<i class="fa fa-calendar"></i>
										<div class="link_modular_div">我的课表</div>
									</a>
								</div>
							</div>
							<div class="link_line">
								<div class="line_tit">大纲管理</div>
								<div class="line_content">
									<a href="#" class="link_modular">
										<i class="fa fa-file-text-o"></i>
										<div class="link_modular_div">实验大纲</div>
									</a>
								</div>
							</div>
							<div class="link_line">
								<div class="line_tit">课程学习</div>
								<div class="line_content">
									<a href="#" class="link_modular">
										<i class="fa fa-server"></i>
										<div class="link_modular_div">课程平台</div>
									</a>
								</div>
							</div>
							<div class="link_show" title="隐藏右边">
								<i class="fa fa-hand-o-right"></i>
							</div>
						</div>
						<div class="notice_link_reset" title="恢复初始状态"></div>
					</div>
					<div class="lesson_message">
						<p>我的应用</p>
						<div class="modular_content">
							<a href="${pageContext.request.contextPath}/newoperation/experimentalmanagement?currpage=1" class="modular">
								<i class="fa fa-file-text-o"></i>
								<div class="modular_div">实验大纲</div>
							</a>
							<%-- <a href="${pageContext.request.contextPath}/newoperation/listOperationOutlinePermissions?currpage=1" class="modular">
								<!--<i class="fa fa-anchor"></i>-->
								<i class="fa fa-shield"></i>
								<div class="modular_div">大纲修订权限</div>
							</a> --%>
						</div>
					</div>
				</div>
			</div>

		</div>
		<script type="text/javascript">
			$(".notice_show").click(
				function() {
					$(".notice_tab").addClass("hide")
					$(".link_tab").removeClass("hide").addClass("w100p")
					$(".notice_link_reset").removeClass("hide").addClass("block")
					$(".link_line").removeClass("link_line").addClass("link_line2")
				}
			);
			$(".link_show").click(
				function() {
					$(".link_tab").addClass("hide")
					$(".notice_tab").removeClass("hide").addClass("w100p")
					$(".notice_link_reset").removeClass("hide").addClass("block")
					$(".message_tab").removeClass("message_tab").addClass("message_tab2")
				}
			);
			$(".notice_link_reset").click(
				function() {
					$(".link_tab").removeClass("w100p hide").addClass("block w49_9p")
					$(".notice_tab").removeClass("w100p hide").addClass("block w49_9p")
					$(".notice_link_reset").addClass("hide")
					$(".link_line2").removeClass("link_line2").addClass("link_line")
					$(".message_tab2").removeClass("message_tab2").addClass("message_tab")
				}
			);
			$(".quickbtn").click(
				function() {
					$(".quick_above").addClass("block");
				}
			);
			$(document).bind("click", function() {
				$('.quick_above').removeClass("block");
			});
			$(".quickbtn").click(function(event) {
				event.stopPropagation();

			});
			$(".quick_above").click(function(event) {
				event.stopPropagation();

			});
			$(window).scroll(function() {
				$(".quick_above").removeClass("block");
			});
		</script>
	</body>

</html>