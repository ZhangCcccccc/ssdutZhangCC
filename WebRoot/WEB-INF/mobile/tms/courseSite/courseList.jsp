<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>GVSUN实验实训综合教学平台</title>
    <meta name="decorator" content="none">
    <meta name="Keywords" content="庚商微课">
    <meta name="Description" content="庚商微课">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <link style="type/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/index/global_min.css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>
    <script type="text/javascript">
		function targetUrl(url)
		{
    		document.queryForm.action=url;
    		document.queryForm.submit();
  		}
  	</script>
</head>
<style>
#confirm,#submit,#reset{
   background-color: #5894e9;
    color: #fff;
    line-height: 40px;
    padding: 0 10px;
    cursor: pointer;
    border: none;
}
.form{
    width: 100%;
    line-height: 40px;
    color: #000;
    padding-left: 10px; 
}
.form .form_title{
    border-bottom: 1px solid #ccc
}
.form_title span{
   margin-left:10px;
}
.flxePassword span{
  color:#333;
}
.tool-box ul li{
  padding:0 5px;
}
</style>

<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
    <div class="header_bg">
        <div class="header">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/index/logo.png" />
            </div>
            <ul class="menu_container">
                <li>
                    <a href="${pageContext.request.contextPath}/tms/index">首页</a>
                </li>
                <c:if test="${type eq 'courseList' }">                   	
		        	<li class="select">
	                    <a href="${pageContext.request.contextPath}/tms/courseList?currpage=1">课程列表</a>
	                </li>
	                <c:if test="${!empty user}">
		                <li>
		                    <a href="${pageContext.request.contextPath}/tms/myCourseList?currpage=1">我的选课</a>
		                </li>
	                </c:if>
	            </c:if>      
	            <c:if test="${type eq 'myCourseList' }">
	                <li>
	                    <a href="${pageContext.request.contextPath}/tms/courseList?currpage=1">课程列表</a>
	                </li>
	                <c:if test="${!empty user}">
		                <li class="select">
		                    <a href="${pageContext.request.contextPath}/tms/myCourseList?currpage=1">我的选课</a>
		                </li>
	                </c:if>
	            </c:if>  
                <sec:authorize ifAnyGranted="ROLE_TEACHER">
                <li>
                   <a href="${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=1" >系统管理</a>
                </li>
                <li >
                   <a href="${pageContext.request.contextPath}/visualization/show/index" >可视化</a>
                </li>
                </sec:authorize>
            </ul>
            <c:if test="${empty user}">
	            <div class="right_container">
	                <form>
	                    <input id="search" name="keyword" value="搜索你喜欢的" onfocus="this.value='';" onblur="if(this.value==''){this.value='搜索你喜欢的'}" />
	                    <input class="login_btn" type="button" value="登录" />
	                </form>
	            </div>
            </c:if>
            <c:if test="${!empty user}">
            <div class="right_container">
                <div style="text-align:right;font-size:12px;">
                    <%
					   if(session.getAttribute("selected_role").toString().equals("ROLE_TEACHER")){
					%>
     				    <font style="color:#fff;">${user.cname} 老师您好  </font>
     				<%}else if(session.getAttribute("selected_role").toString().equals("ROLE_STUDENT")){%>
     				    <font style="color:#fff;">${user.cname} 同学您好  </font>
     				<%}%>
     			   <a href="${pageContext.request.contextPath}/pages/logout-redirect.jsp" target="_parent"><font color=write><font style="color:#fff;">退出</font> </a>  
                </div> 
            </div>
            </c:if>
            <form id="subform" name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'> 
	            <div class="log-in">
	                <div class="log_in_box">
	                    <img class="close" src="${pageContext.request.contextPath}/images/index/msg_close.png" />
	                    <img src="${pageContext.request.contextPath}/images/index/logo_a.png" />
	                    <div class="sat_name"><span>会员登录</span>
	                    </div>
	                    <fram>
	                        <div class="username-box">
	                            <input type="text" name="j_username" placeholder="会员账号" />
	                        </div>
	                        <div class="password-box">
	                            <input type="password" name="j_password" placeholder="密码" />
	                        </div>
	                        <div class="log-in-box">
	                            <input type="submit" value="登录"  />
	                        </div>
	                    </fram>
	                </div>
	            </div>
            </form>
        </div>
    </div>
    <div class="lest_content ">
        <div class="left_nav">
        	<div class="left_user_box">
                <img src="${pageContext.request.contextPath}/images/index/user.png"/>
            </div>
            <div class="left_nav_box">
                <ul class="left_nav_list">
                	<c:if test="${type eq 'courseList' }">
                		<li style="opacity: 1;"> 
	                		<a href="${pageContext.request.contextPath}/test?labCenterId=-1">进入系统</a> 
	                	</li>
	                </c:if>
	                    <li <c:if test="${sites!=null }">class="selected"</c:if> style="opacity: 1;"> 
	                    	<c:if test="${type eq 'courseList' }">                   	
	                    		<a href="${pageContext.request.contextPath}/tms/courseList?currpage=1">课程列表</a> 
	                    	</c:if>      
	                    	<c:if test="${type eq 'myCourseList' }">
	                    		<a href="${pageContext.request.contextPath}/tms/myCourseList?currpage=1">我的课程</a>
	                    	</c:if>    	
	                    </li>
	                
                    <c:if test="${!empty user}">
	                    <li <c:if test="${sites==null&&str!=null }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tms/listMyInfo?type=${type}">个人信息</a>
	                    </li>
	                    <li <c:if test="${sites==null&&str==null }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tms/changePassword?type=${type}&password=">修改密码</a>
	                    </li>
	                    <c:if test="${type eq 'myCourseList' }">
		                    <li ><a href="${pageContext.request.contextPath}/tms/coursesite/newCourseSite">新建课程</a>
		                    </li>
	                    </c:if>
                    </c:if>
                </ul>
            </div>  
            <div class="power2" style="display: block;">
                Power by <a href="http://www.gvsun.com" target="_blank">Gvsun</a>
            </div>
        </div>
        <div class="courses_list2">
        
        	<c:if test="${sites!=null }">
        		<div class="tool-box">
					<form:form name="queryForm" action="${pageContext.request.contextPath}${url}?currpage=1" method="post" modelAttribute="tCourseSite">
					   	<ul>
							<li>选择学期：</li>
							<li>
								<form:select id="termId" path="schoolTerm.id" class="select_search">
									<form:option value="-1" >请选择</form:option>
										<c:forEach items="${schoolTerms}" var="schoolTerm">
											<form:option value="${schoolTerm.id}" >${schoolTerm.termName }</form:option>
										</c:forEach>
								</form:select>
						 	</li>
				          	<li>课程标题：</li>
				           	<li>
				           		<form:input id="title" path="title" />
							</li>
							<li>授课教师：</li>
				           	<li>
				           		<form:input id="userByCreatedByCname" path="userByCreatedBy.cname" />
							</li>
					        <li>
					        	<input type="submit" value="查询"/>
					    		<input type="button" value="取消" onclick="cancel();"/>
					    	</li>
						</ul>
					</form:form>
			   </div>
            	<ul>
	                <c:forEach items="${sites}" var="site" varStatus="i" >
		                <li>
		                    <div class="courses_box">
		                        <div class="courses_pic">
		                            <img src="${pageContext.request.contextPath}/${site.siteImage}">
		                        </div>
		                        <div class="courses_title_container">
		                            <h4>${site.title}</h4>
		                            <h5>${site.userByCreatedBy.cname}</h5>
		                            <h5>授课教师:</h5>
		                        </div>
		                        <div class="courses_intro">
		                            <div class="teacher">
		                                <div class="teacher_pic">
		                                    <img src="${pageContext.request.contextPath}/${site.teacherImage}">
		                                </div>
		                                <div class="teacher_intro">
		                                    <div class="teacher_name">
		                                       	 ${site.userByCreatedBy.cname}
		                                    </div>
		                                    <div class="job_description"></div>
		                                </div>
		                            </div>
		                            <div class="intro_courses">
		                                         ${site.description}
		                            </div>                            
		                        </div>
		                        <div class="courses_intro_tit">
		                            <a href="${pageContext.request.contextPath}/tcoursesite?tCourseSiteId=${site.id}" target="self">
		                                <span class="course_tit">进入站点</span>
		                                <div class="arrow">
		                                	<span>＞</span>
		                                </div>
		                            </a>
		                        </div>
		                    </div>
		                </li>
		            </c:forEach>
            	</ul>
            	<div class="page-message">
		        	<a class="page page-pos" href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}${url}?currpage=${pageModel.lastPage}')">末页</a>
		        	<a class="page" href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}${url}?currpage=${pageModel.nextPage}')">下一页</a>
		        	<div class="page-select">
		        		<div class="page-word">页</div>
		        		<form>
		    		        <%-- select class="page-number" name="page" onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">--%>
		    		        <select class="page-number" name="page" onchange="targetUrl(this.options[this.selectedIndex].value)">
		    		        	<option value="${pageContext.request.contextPath}${url}?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
                	            <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
                                <c:if test="${j.index != pageModel.currpage}">
                                <option value="${pageContext.request.contextPath}${url}?currpage=${j.index}&termId=${termId}">${j.index}</option>
                                </c:if>
                                </c:forEach>
                            </select>
		    	        </form>
		    	        <div class="page-word">第</div>
		            </div>
		            <a class="page" href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}${url}?currpage=${pageModel.previousPage}')">上一页</a>
		            <a class="page" href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}${url}?currpage=1')">首页</a>
		            <div class="p-pos">
		            	<span>${pageModel.totalRecords}</span>
		            	<span>条记录，共</span>
		            	<sapn>${pageModel.totalPage}</span>
		                <span>页</span>
		            </div>
		        </div>
		    </c:if>
		    
		    <c:if test="${sites==null }">
            		<c:if test="${str==null }">
	            		<div align="center" class="flxePassword">
		            		<form action="${pageContext.request.contextPath}/tms/changePassword?type=${type}" method="POST" onsubmit="return checkNewPassword()">
		            			<span>请输入原密码:</span><input type="password" name="" id="oldPassword" style="background:#fff;color:#000;margin-left: 10px;line-height: 24px;outline: none;"/><br><br>
		            			<div id="password" style="display: none;">
			            			<span>请输入新密码:</span><input type="password" name="password" id="newPassword" style="background:#fff;color:#000;margin-left: 10px;line-height: 24px;outline: none;"/><br><br>
			            			<span>请确认新密码:</span><input type="password" name="" id="newPasswordConfirm" style="background:#fff;color:#000;margin-left: 10px;line-height: 24px;outline: none;"/><br><br>
		            			</div>
		            			<input type="button" id="confirm" value="确认原密码" onclick="checkOldPassword()"/>
		            			<input style="display: none;" type="submit" id="submit" value="确认修改" />
		            			<input style="display: none;" type="button" id="reset" value="重置" onclick="$('#newPassword').val('');$('#newPasswordConfirm').val('');"/>
		            		</form>
	            		</div>
            		</c:if>
            	</c:if>
		    
        </div>
    
        <script type="text/javascript">
        
        
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
        		if($("#newPassword").val().trim()==""||$("#newPasswordConfirm").val().trim()==""){
        			alert("新密码不能为空！");
        			return false;
        		}
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
    </div>




    <div class="footer_container">
        <div class="footer">
            <div class="copyright">Copyright ©2014 庚商教育智能科技 All Rights Reserved 沪ICP备14016833号</div>
            <div class="power">庚商微课 power by <a href="www.gvsun.com">Gvsun</a>
            </div>
        </div>
    </div>
    <div class="top">
        <img src="${pageContext.request.contextPath}/images/index/top.png">
    </div>
    
    <script type="text/javascript">
        setInterval("TT()", 10);
        function TT() {
            var ss = $(".lest_content").width()
            $(".list_course ").width(ss - 230);
        }
        var wh = $(window).height();
        var fh = $(".list_course").height();
        $(".left_nav").css("minHeight", wh-50)
        $(".left_nav").css("height", fh - 50)
        window.onload = function () {
            var h = $(window).height()
            $(".log-in").height(h) + "px"
        }
        $(".menu_container li").click(
            function () {
                $(this).addClass("select");
                $(this).siblings().removeClass("select")
            })
        $(".left_nav li").click(
            function () {
                $(this).addClass("list_select");
                $(this).siblings().removeClass("list_select")
            })
        $(".left_nav li").hover(
            function () {
                $(this).addClass("list_hover");
            },
            function () {
                $(this).removeClass("list_hover");
            }
        )
        $(".login_btn").click(
            function () {
                $(".log-in").show();
            });
        $(".close").click(
            function () {
                $(".log-in").hide();
            }
        )
        $(window).scroll(
            function () {
                var hh = $(window).height();
                var hi = $(document).scrollTop();
                if (hi > hh) {
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
        $(".courses_box").hover(
            function () {
                $(this).children(".courses_intro").stop().slideDown({
                    duration: 500,
                    easing: 'easeOutBounce'
                });
                $(this).children(".courses_intro_tit").stop().slideDown({
                    duration: 500,
                    easing: 'easeOutBounce'
                });
            },
            function () {
                $(this).children(".courses_intro").stop().slideUp({
                    duration: 500,
                    easing: 'easeOutCirc'
                });
                $(this).children(".courses_intro_tit").stop().slideUp();
            }
        )
    </script>
    
    <script type="text/javascript" >
    $(function () {
        $('.select_search').searchableSelect();
        		//.attr("src", $("#contextPath").val()+values);
        
    });
    
</script>
</body>

</html>