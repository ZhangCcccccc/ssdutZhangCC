<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta name="decorator" content="none">
    <title>我的课程</title>
    <!--[if lt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <![endif]-->
    <!--[if gte IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>  
    <![endif]-->
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/cms/global_min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/cms/header_footer.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/animatecolor-plugin.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/themes/default/easyui.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
</head>
<% 
   //前端登录标记
   session.setAttribute("LOGINTYPE","ADMIN");  
%> 
<body>
    <div class="header">
        <!--<audio src="http://202.121.32.182:3280/portal/templates/1016zzyjoomla/images/music.mp3" autoplay="true" playcount="-1"></audio>-->

        <div class="header_box">
            <div class="logo_box">
                <img src="${pageContext.request.contextPath}/images/index/logo.png" class="logo_pic">
            </div>
            <div class="nav_box">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/cms/index">首页</a>
                    </li>
                   <!--  <li><a href="javascript:void(0);">题库管理</a>
                    </li>
                    <li><a href="javascript:void(0);">实验课程资源管理</a>
                    </li>
                    <li><a href="javascript:void(0);">基础数据管理</a>
                    </li> -->
                    <li class="nav_selected"><a href="${pageContext.request.contextPath}/cms/courseList"><b>课程列表</b></a>
                    </li>
                    <c:if test="${!empty user}">
                    <li class="nav_selected"><a href="${pageContext.request.contextPath}/cms/myCourseList">我的课程</a>
                    </li>
                    </c:if>
                </ul>
            </div>

            <div class="user_box_top">
                <img src="${pageContext.request.contextPath}/images/index/user.png">
            </div>
            <c:if test="${!empty user}">
            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                       <%
					   if(session.getAttribute("selected_role").toString().equals("ROLE_TEACHER")){
					   %>
					   
					   <i class="icon-user-2"></i>   
					   ${user.cname}老师，您好  &nbsp;<a href="${pageContext.request.contextPath}/pages/logout-front-redirect.jsp" target="_parent"><font color=write>退出</font> </a>
					   					   <%}else if(session.getAttribute("selected_role").toString().equals("ROLE_STUDENT")){%>
					   <i class="icon-user-2"></i>   
					    ${user.cname}同学，您好   &nbsp;<a href="${pageContext.request.contextPath}/pages/logout-front-redirect.jsp" target="_parent"><font color=write>退出</font> </a>
					   					   
					   <%}%> 
					   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   </c:if>
		    			                
        </div>
    </div>
    <div class="content">
        <div class="left_nav">
        	
            <div class="left_user_box">
                <img src="${pageContext.request.contextPath}/images/index/user.png">
            </div>
            <div class="left_nav_box">
                <ul class="left_nav_list">
                    <li <c:if test="${sites!=null }">class="selected"</c:if>>
                    	<c:if test="${type=='课程列表' }">
                    		<a href="${pageContext.request.contextPath}/cms/courseList">${type }</a>
                    	</c:if>
                    	<c:if test="${type=='我的课程' }">
                    		<a href="${pageContext.request.contextPath}/cms/myCourseList">${type }</a>
                    	</c:if>
                    	<c:if test="${fn:contains(user.authorities,'TEACHER') }">
	                        <a class="course_manage" href="${pageContext.request.contextPath}">管理</a>
                    	</c:if>
                    </li>
                     <c:if test="${!empty user}">
                    <li <c:if test="${sites==null&&str!=null }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/cms/listMyInfo?type=${type}">个人信息</a>
                    </li>
                    <li <c:if test="${sites==null&&str==null }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/cms/changePassword?type=${type}&password=">修改密码</a>
                    </li>
                    </c:if>
                </ul>
            </div>
            <div class="power">
                Power by <a href="http://www.gvsun.com" target="_blank">Gvsun</a>
            </div>
        </div>
        <div class="right_content">
            <div class="course_tool">
                <a class="course_operate_finish" href="javascript:void(0);">完成</a>
                <a href="javascript:void(0);">复制</a>
                <a href="javascript:void(0);">合并</a>
                <a href="javascript:void(0);">删除</a>
            </div>
            <ul class="course_box">
            	<c:if test="${sites!=null }">
	                <c:forEach items="${sites}" var="site" varStatus="i">
	                <li>
	                    <div class="overflow_box">
	                        <div class="course_int">
	                            <div class="course_img">
	                                <img src="${pageContext.request.contextPath}/${site.siteImage}">
	                            </div>
	                            <div class="course_base">
	                                <div>${site.title}</div>
	                                <div>授课教师：${site.userByCreatedBy.cname}</div>
	                            </div>
	                            <div class="course_link">
	                            <c:if test="${site.title!='人体解剖学实验课'}">
	                                <a href="${pageContext.request.contextPath}/cms/courseSite?id=${site.id}" target="blank">进入网站</a>
	                              </c:if> 
	                               <c:if test="${site.title=='人体解剖学实验课'}">
	                                <a href="${pageContext.request.contextPath}/cms/courseSiteNew" target="blank">进入网站</a>
	                              </c:if> 
	                                <c:if test="${fn:contains(user.authorities,'TEACHER') }">
	                                	<a href="${pageContext.request.contextPath}/courseSitePortal?courseSiteId=${site.id}">教学管理</a>
	                            	</c:if>
	                            </div>
	                        </div>
	                    </div>
	
	                    <div class="course_operate">
	                        <form class="check_imp">
	                            <input type="checkbox" id="sel1" class="check_box">
	                            <label name="sel" class="check_decorate" for="sel1"></label>
	                        </form>
	                        <span class="course_del"> 
	                        </span>
	                    </div>
	                </li>
	                </c:forEach>
            	</c:if>
            	</ul>
            	<c:if test="${sites==null }">
            		<c:if test="${str==null }">
	            		<div align="center">
		            		<form action="${pageContext.request.contextPath}/cms/changePassword?type=${type}" method="POST" onsubmit="return checkNewPassword()">
		            			请输入原密码：<input type="password" name="" id="oldPassword" style="background:#fff;color:#000"/><br><br>
		            			<div id="password" style="display: none;">
			            			请输入新密码：<input type="password" name="password" id="newPassword" style="background:#fff;color:#000"/><br><br>
			            			请确认新密码：<input type="password" name="" id="newPasswordConfirm" style="background:#fff;color:#000"/><br><br>
		            			</div>
		            			<input type="button" id="confirm" value="确认原密码" onclick="checkOldPassword()"/>
		            			<input style="display: none;" type="submit" id="submit" value="确认修改" />
		            			<input style="display: none;" type="button" id="reset" value="重置" onclick="$('#newPassword').val('');$('#newPasswordConfirm').val('');"/>
		            		</form>
	            		</div>
            		</c:if>
            		<c:if test="${str!=null }">
            			<div class="right-content">
						<div id="TabbedPanels1" class="TabbedPanels">
						<div class="TabbedPanelsContentGroup">
						<div class="TabbedPanelsContent">
            			<div class="content-box">
            			<table align="center"> 
							<thead>
								<th>用户工号</th>
								<th>用户姓名</th>
								
								<th>用户身份</th>
								<th>学院/部门</th>
								<!-- <th>专业</th> -->
								<th>专业</th>
								<th>年级</th>
								<th>班级</th>
								<th>电话</th>
								<th>邮箱</th>
								<th>操作</th>
							</thead>
							<tbody>
								<tr>
									<td>${user.username}</td>
									<td>${user.cname}</td>
									<td>${str}</td>
									<td>${user.schoolAcademy.academyName}</td>
									<td><%-- ${user.schoolMajor.majorName} --%></td>
									<td>${user.grade}</td>
									<td><%-- ${user.schoolClasses.className} --%></td>
									<td>${user.telephone}</td>
									<td>${user.email}</td>
									
									<td><a onclick="addRecords(${user.username});" ><!-- <i class="icon-pencil" ></i> -->编辑</a></td>
								</tr>
							</tbody>
						</table>
						</div>
						</div>
						</div>
						</div>
						</div>
						<div id="repairRecords" class="easyui-window" title="修改个人信息" closed="true" iconCls="icon-add" style="width:800px;height:200px">
							<form:form action="${pageContext.request.contextPath}/cms/saveMyInfo?type=${type }" method="POST" onsubmit="return checkMail()" modelAttribute="user" enctype="multipart/form-data">	
								<div class="right-content">
									<div id="TabbedPanels1" class="TabbedPanels">
										<div class="TabbedPanelsContentGroup">
											<div class="TabbedPanelsContent">
												<div class="tool-box">
													<table>
														<tr>
															<form:hidden path="username"/>
														</tr>
														<tr>
															<td class="label" valign="top"> 邮箱: </td>
															<td><form:input id="email"  path="email" validtype="email" invalidMessage="邮箱格式不正确,正确格式如xxx@xxx.xxx" /> </td>
														</tr>
														<tr>
															<td> &nbsp;</td><td></td>
														</tr>
														<tr>
															<td class="label" valign="top"> 电话: </td>
															<td><form:input id="username"  path="telephone" class="easyui-numberbox" placeholder="仅可输入数字" /> </td>
														</tr>
													</table>
												</div>
												<input id="save" type="submit" value="保存">
											</div>
										</div>
									</div>
								</div>
							
							</form:form>
						</div>
            		</c:if>
            	</c:if>
                


            
            <div class="footer_box">
                <div class="footer">
                    <p>Copyright ©2014 西安电子科技大学 All Rights Reserved </p>
                    <p>南校区地址：陕西省西安市西沣路兴隆段266号  北校区地址：陕西省西安市太白南路2号  联系电话：029-88201000</p>
                </div>
            </div>
        </div>

    </div>
    <script type="text/javascript">
        setInterval("TT()", 10);

        function TT() {
                var ss = $(".content").width();
                var H = $(window).height();
                /* $(".right_content ").width(ss - 255);*/
                $(".right_content ").width(ss - 211);
                $(".left_nav").height(H - 130);
                var h = H - 170 + "px"
                $('.right_content').css("min-height", h)

            }
            //        $(".course_link").hide()
        $(".course_box li").hover(
                function () {
                    $(this).stop().animate({
                        top: '-2px'
                    }, 200);
                    $(this).find(".course_int").stop().animate({
                        top: '-30px'
                    }, 200);
                    $(this).find(".course_link").stop().animate({
                        bottom: '-22px'
                    }, 200)
                },
                function () {
                    $(this).stop().animate({
                        top: '+2px'
                    }, 200);
                    $(this).find(".course_int").stop().animate({
                        top: '0px'
                    }, 200);
                    $(this).find(".course_link").stop().animate({
                        bottom: '-30px'
                    }, 200)
                }
            )
            /*背景颜色变化*/
        $(".course_manage").hide()
        $('.left_nav_list li').hover(
            function () {
                $(this).stop().animate({
                    opacity: '0.6'
                });

            },
            function () {
                $(this).stop().animate({
                    opacity: '1'
                })

            }
        )
        $('.left_nav_list li').click(
                function () {

                    $(this).stop().animate({
                        backgroundColor: '#cd0000'
                    })
                    $(this).children("a").stop().animate({
                        color: '#fff'
                    })
                    $(this).siblings().stop().animate({
                        backgroundColor: '#fbfbfc'
                    })
                    $(this).siblings().children("a").stop().animate({
                        color: '#333'
                    });
                    $(this).addClass("selected");
                    $(this).siblings().removeClass("selected")

                }
            )
            /*背景颜色变化结束*/

        $('.course_tool,.course_operate').hide()
        $('.course_manage').click(
            function () {
                $(".course_tool,.course_operate").fadeToggle("200")
            }
        )
        $('.course_operate_finish').click(
            function () {
                $(".course_tool,.course_operate").fadeOut("200")
            }
        )
        $('.nav_box li').hover(
            function () {
                $(this).find('a').stop().animate({
                    color: '#cd0000',
                    top: '-4px'
                })
            },
            function () {
                $(this).find('a').stop().animate({
                    color: '#333',
                    top: '0px'
                })
            }
        )
        $('.nav_box li').click(
            function () {
                $(this).addClass("nav_selected")
                $(this).siblings().removeClass("nav_selected")
            }
        )
        $(function () {

            var elm = $('.left_nav');
            var startPos = $(elm).offset().top;
            $.event.add(window, "scroll", function () {
                var p = $(window).scrollTop();
                $(elm).css('position', ((p) > startPos) ? 'fixed' : 'relative');
                $(elm).css('top', ((p) > startPos) ? '40px' : '');
                if ((p) > startPos) {
                    $('.power').show()
                } else {
                    $('.power').hide()
                }
            });
        });
        /*返回顶部*/

        $(window).scroll(
            function () {
                var hh = $(window).height();
                var hi = $(document).scrollTop();
                if (hi > hh / 3) {
                    $('.top').slideDown("100")
                } else {
                    $('.top').slideUp("100")
                }
            }
        );
        $('.top').click(function () {
            $('html, body').animate({
                scrollTop: 0
            }, 'slow');
        });
        /*返回顶部*/
        
        
        function checkOldPassword(){
        	if($("#oldPassword").val().trim()==""){
        		alert("请输入原密码！");
        		return false;
        	}else{
        		if($("#oldPassword").val().trim()=="${user.password}"){
        			$("#password").show();
        			$("#confirm").hide();
        			$("#submit").show();
        			$("#reset").show();
        		}else{
        			alert("原密码输入有误，请重新输入!");
        			return false;
        		}
        	}
        }
        
        function checkNewPassword(){
        	if($("#oldPassword").val().trim()=="${user.password}"){
        		if($("#newPassword").val().trim()!=$("#newPasswordConfirm").val().trim()){
        			alert("两次密码输入不一致，请重新输入");
        			return false;
        		}
        	}else{
        		alert("原密码输入有误，请重新输入!");
    			return false;
        	}
        }
        
        $(function(){
            //设置邮箱需要验证
            $("#email").validatebox();
        })
        function addRecords(id){
                 $("#repairRecords").window('open');      
            }

        function checkMail(){
        	var email = $("#email").val();
        	if(email.trim()!=""){
        		var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        		if (!filter.test(email)){
        			alert('您的邮箱格式不正确');
        			return false;
        		}
        	}
        	
        }
    </script>

</body>

</html>