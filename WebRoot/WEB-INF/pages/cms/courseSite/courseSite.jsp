<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<jsp:useBean id="now" class="java.util.Date" />

<html>

<head>
    <title>课程站点</title>
    
    <meta name="decorator" content="none"/>
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
     <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
 
 
    
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/index/global_min.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery.easing.1.3.js"></script>
   <%--  <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery.glide.js"></script> --%>
    <script type="text/javascript"	src="${pageContext.request.contextPath}/js/browse.js"></script>
    
    
    <%-- <link href="${pageContext.request.contextPath}/css/courseSite/header_footer.css" rel="stylesheet" type="text/css"> --%>
    <link href="${pageContext.request.contextPath}/css/courseSite/course.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/courseSite/animatecolor-plugin.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/courseSite/jquery.nav.js"></script>
    <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/courseSite/jquery.rotate.min.js"></script> --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/courseSite/jquery.animate-shadow.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/courseSite/jquery.autosize.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/locale/easyui-lang-zh_CN.js"></script>
   <style type="text/css" media="screen">
		@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/icon.css");
		@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/gray/easyui.css");
			
	</style> 
<!-- 树形表格的js和css-->  	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/treeTable/demo.css"  />
<style type="text/css">
table,td,th {    border: 1px solid #F5F5F5; padding:5px; border-collapse: collapse; font-size:12px; }
#my_show{
width:100%;}
thead{    background-color: #eee;
    line-height: 25px;}
</style>   	
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/treeTable/jquery.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/treeTable/treeTable/jquery.treeTable.js" ></script>	
    <script type="text/javascript" >  
    //弹出的方法
	function mediaDisplay(id1) {
	    var sessionId=$("#sessionId").val();
	    var con = '<iframe id="con" scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/cmsshow/' + id1 + '/video" style="width:100%;height:100%;"></iframe>'
	    $("#mediaDisplay").html(con); 
	    //获取当前屏幕的绝对位置
  	    var topPos = window.pageYOffset;
	    //使得弹出框在屏幕顶端可见
	    $('#mediaDisplay').window({
	    	top:topPos+"px",
	    	onBeforeClose:function(){
    			$("#con").remove();
    			$("#mediaDisplay").window('close',true);
	    	}
    	}); 
	    $("#mediaDisplay").window('open');
	}
	</script>
<script type="text/javascript">
        $(function(){
            var option = {
                theme:'vsStyle',//主题，有两个选项：default、vsStyle
                expandLevel : 2,//树表的展开层次.
                //展开子节点前触发的事件
                beforeExpand : function($treeTable, id) {
                    //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
                    if ($('.' + id, $treeTable).length) { return; }
                    //ajax请求获取子节点的数据
                    $.ajax({
		           		url:"${pageContext.request.contextPath}/cms/getContentsList?id="+id,
			           	type:"POST",
			           	success:function(data){//AJAX查询成功
			           		//alert(data);
			            	$treeTable.addChilds(data);
			           	}
					});
                    
                },
                onSelect : function($treeTable, id) {
                    window.console && console.log('onSelect:' + id);
                    
                }

            };
            $('#treeTable1').treeTable(option);

            option.theme = 'default';
            $('#treeTable2').treeTable(option);
        });
        
function downloadFile(id){
	if('${!empty user}'){
		if(confirm("是否确认下载？")){
			window.location.href="${pageContext.request.contextPath}/wk/admin/downloadFile?id="+id;
		}
	}else{
		alert(22)
	}
}        

function checkSubmitNumber(number){
	if(number==0){
		alert("尚未有提交内容，无法查看！");
		return false;
	}
}

//弹出作业成绩窗口
function grading(id) {
    var sessionId=$("#sessionId").val();
    //获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    $.ajax({url:"${pageContext.request.contextPath}/teaching/assignment/grading?id="+id,
       	type:"POST",
       	success:function(Grading){
       		
       		$("#score").html(Grading[0]);
       		$("#comments").html(Grading[1]);
       		$("#content").html(Grading[2]);
       		
       	}
    	
    })
    //使得弹出框在屏幕顶端可见
    $('#Grading').window({left:"0px", top:topPos+"px"}); 
    $("#Grading").window('open');
}
//未提交学生名单窗口
function NotSubmitStudents(id) {
    var sessionId=$("#sessionId").val();
    //获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    $.ajax({url:"${pageContext.request.contextPath}/teaching/assignment/notSubmitStudents?id="+id,
       	type:"POST",
       	success:function(NotSubmitStudents){
       		$("#students").html(NotSubmitStudents);
       	
       	}
    	
    })
    //使得弹出框在屏幕顶端可见
    $('#NotSubmitStudents').window({left:"0px", top:topPos+"px"}); 
    $("#NotSubmitStudents").window('open');
}
</script>

</head>
<% 
   //前端登录标记
   session.setAttribute("LOGINTYPE","CMS");  
%>
<body>
    <%-- <div class="header">
        <div class="header_box">
            <div class="logo_box">
                <img src="${pageContext.request.contextPath}/images/courseSite/logo.png" class="logo_pic">
            </div>
            <div class="nav_box">
                <ul>
                    <li><a href="javascript:void(0);">题库管理</a>
                    </li>
                    <li><a href="javascript:void(0);">实验课程资源管理</a>
                    </li>
                    <li><a href="javascript:void(0);">基础数据管理</a>
                    </li>
                    <li class="nav_selected"><a href="javascript:void(0);">我的课程</a>
                    </li>
                </ul>
            </div>
            <div class="user_box_top">
                <img src="${pageContext.request.contextPath}/images/courseSite/user.png">
            </div>
        </div>
    </div> --%>
    
    <div class="header_bg">
        <div class="header">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/index/logo.png" />
            </div>
            <ul class="menu_container">
                <li class="select">
                    <a href="${pageContext.request.contextPath}/cms/index"  target=_self>首页 </a>
                </li>
                
                <li>
                    <a href="${pageContext.request.contextPath}/cms/courseList" target=_self>课程列表</a>
                </li>
                
                <li>
                    <a href="javascript:void(0);">关于我们</a>
                </li>
                <c:if test="${!empty user}">
                <li>
                    <a href="${pageContext.request.contextPath}/cms/myCourseList" target=_self>我的选课</a>
                </li>
                </c:if>
                
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
                <div style="color:#fff;">
                    <%
					   if(session.getAttribute("selected_role").toString().equals("ROLE_TEACHER")){
					%>
     				    <font>${user.cname} 老师您好  </font>
     				<%}else if(session.getAttribute("selected_role").toString().equals("ROLE_STUDENT")){%>
     				    <font >${user.cname} 同学您好  </font>
     				<%}%>
     			   <a href="${pageContext.request.contextPath}/pages/logout-front-redirect.jsp" target="_parent"><font color=write><font style="color:#fff;">退出</font> </a>  
                </div> 
            </div>
            </c:if>
            <form id="subform" name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'> 
	            <div class="log-in">
	                <div class="log_in_box">
	                    <img class="close" src="${pageContext.request.contextPath}/images/index/msg_close.png" />
	                    <%-- <img src="${pageContext.request.contextPath}/images/index/logo_a.png" /> --%>
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
	                            <input type="submit" value="登录" />
	                        </div>
	
	                    </fram>
	                </div>
	            </div>
            </form>
        </div>
    </div>
    <div class="header_img">
        <div class="course_img"><img src="${pageContext.request.contextPath}/${course.siteImage}"> </div>
        <div class="course_explain">
            <div>
                <p class="courde_name">${course.title}</p>
                <p class="courde_teacher">授课教师：${course.userByCreatedBy.cname}</p>
                <p class="courde_state">${course.description}</p>
            </div>
            <%-- <div class="tool">
                <div class="apply"><a href="javascript:void(0);">课程报名</a>
                </div>
                <div class="evaluate"><a href="javascript:void(0);">课程评价</a>
                </div>
                <div class="evaluate_star">
                    <div class="gray">
                    <img src="${pageContext.request.contextPath}/images/courseSite/star_gray.png">
                    </div>
                    <div class="color" style="width:89px">
                    <img src="${pageContext.request.contextPath}/images/courseSite/star_color.png">
                    </div>

                </div>
                <div class="score"> 4.2 <span>（20人评价）</span>
                </div> --%>
<div class="share_code">
                <div class="share">
                <img src="${pageContext.request.contextPath}/images/courseSite/share.png">
                </div>
                <div class="code">
                <img src="${pageContext.request.contextPath}/images/courseSite/2_code.png">
                </div>
                <div>
                    <!-- JiaThis Button BEGIN -->
                    <div class="jiathis_style">
                        <a class="jiathis_button_qzone"></a>
                        <a class="jiathis_button_tsina"></a>
                        <a class="jiathis_button_tqq"></a>
                        <a class="jiathis_button_weixin"></a>
                        <a class="jiathis_button_renren"></a>
                    </div>
                    <!-- JiaThis Button END -->
                    <div class="big_code_pic">
                        <img src="${pageContext.request.contextPath}/images/courseSite/img8.png">
                    </div>
                </div>
            </div>
            </div>
            
        </div>


    </div>
    <div class="content ">
        <div class="left_nav course_left_nav">
            <div class="course_list_tit">${course.title}</div>
            <ul class="nav_list">
            	<c:forEach items="${channels}" var="channel" varStatus="i">
            	<li>
                    <a href="#div${channel.id}">${channel.channelName}</a>
                </li>
            	</c:forEach>
            	
            	
                <li>
                    <a href="#divSection">微课资源</a>
                </li>
                <li>
                    <a href="#divResources">教学资源</a>
                </li>
                <c:if test="${!empty user}">
                <li>
                    <a href="#divTassignments">作业列表</a>
                </li>
                <li>
                    <a href="#divExams">练习与测验</a>
                </li>
                </c:if><c:if test="${!empty user}">
                <li>
                    <a href="#divExperiments">实验管理</a>
                </li>
                </c:if>
                <!-- <li>
                    <a href="#divDiscuss">话题讨论</a>
                </li> -->
            </ul>
            
        </div>
        <div class="right_content">
        	<c:forEach items="${channels}" var="channel" varStatus="k">
        	<!-- 课程简介 -->
        	<c:if test="${channel.TCourseSiteTag.id==1}">
            <div class="right_content_box" id="div${channel.id}">
            	<c:forEach items="${channel.TCourseSiteArticals}" var="artical" varStatus="i">
            	<c:if test="${i.count==1}">
            	<div class="right_content_tit">${artical.name}</div>
                <div class="right_content_article">${artical.content}</div>
                </c:if>
            	</c:forEach>
            </div>
        	</c:if>
        	<!-- 课程简介结束 -->
        	<!-- 教师团队 -->
            <c:if test="${channel.TCourseSiteTag.id==3}">
            <div class="right_content_box" id="div${channel.id}">
	            <div class="right_content_tit">${channel.channelName}</div>
                <div class="teacher_box">
                	<c:forEach items="${channel.TCourseSiteArticals}" var="ta">
                	<div class="teacher_list">
                        <div class="teacher_list_box">
                            <div class="teacher_pic">
                                <a href="${pageContext.request.contextPath}/cms/teacher?id=${ta.id}">
                                <img src="${pageContext.request.contextPath}/${ta.imageUrl}">
                                </a>
                            </div>
                            <div class="teacher_intro">
                                <p class="teacher_name">${ta.name}<span class="teacher_post">授课教师</span>
                                </p>
                                <p class="teacher_honour">${ta.text}</p>
                            </div>
                        </div>
                    </div>
                	</c:forEach>
                </div>
            </div>
            </c:if>
            <!-- 教师团队结束 -->
        	<!-- 参考教材 -->
            <c:if test="${channel.TCourseSiteTag.id==4}">
            <div class="right_content_box" id="div${channel.id}">
                <div class="right_content_tit">${channel.channelName}</div>
                <div class="teaching_material">
                	<c:forEach items="${channel.TCourseSiteArticals}" var="m">
                	<div class="teaching_material_list">
                        <div class="book_pic">
                        	<a href="${pageContext.request.contextPath}/cms/book?id=${m.id}">
                            	<img src="${pageContext.request.contextPath}/${m.imageUrl}">
                            </a>
                        </div>
                        <div class="book_intro">
                            <p class="book_name">${m.name}</p>
                     
                            <p class="book_publishing">
                            <c:if test="${m.text==null||m.text==''}">暂无简介</c:if>
                            <c:if test="${m.text!=null&&m.text!=''}">${m.text}</c:if>
                            
                            </p>
                        </div>
                    </div>
                	</c:forEach>
                </div>
            </div>
            </c:if>
            <!-- 参考教材结束 -->
        	<!-- 教学介绍 -->
            <c:if test="${channel.TCourseSiteTag.id==5}">
            <div class="right_content_box" id="div${channel.id}">
                <div class="right_content_tit"> ${channel.channelName}</div>
                <div class="right_content_article">
                	<c:forEach items="${channel.TCourseSiteArticals}" var="artical" varStatus="i">
                	<c:if test="${i.count==1}">
                	${artical.content}
                	</c:if>
                	</c:forEach>
                </div>
                <a href="${pageContext.request.contextPath}/cms/teachingIntroduction?id=${channel.id}" class="more">了解更多</a>

            </div>
            </c:if>
            <!-- 教学介绍 结束-->
        	<!-- 新闻列表开始 -->
            <c:if test="${channel.TCourseSiteTag.id==6}">
            <div class="right_content_box" id="div${channel.id}">
                <div class="right_content_tit">${channel.channelName}</div>
                <div class="right_content_article">
                    <ul class="right_article_list">
                    	<c:forEach items="${channel.TCourseSiteArticals}" var="artical">
                    	<li>
                            <div class="list_time"><fmt:formatDate value="${artical.createDate.time}" pattern="yyyy-MM-dd"/>  </div>
                            <a href="${pageContext.request.contextPath}/cms/artical?id=${artical.id}">${artical.name}</a>
                        </li>
                    	</c:forEach>
                    </ul>
                </div>
                <a href="${pageContext.request.contextPath}/cms/articalList?id=${channel.id}" class="more">查看更多</a>

            </div>
            </c:if>
            <!-- 新闻列表结束 -->
        	
        	
        	</c:forEach>
        	
            
            
            
            
            
            <!-- ------------------------------------------------------------下面开始为微课的课程资源内容 -------------------------------->
            <div class="right_content_box" id="divSection">
                <div class="right_content_tit">
                    微课资源
                </div>
                <div class="section_wrapper">
                <%--<c:forEach items="${sectionList}" var="section" varStatus="i">
                <div class="section">
                        <div class="section_tit">
                            <span class="section_icon"></span>
                            <span class="section_line"></span>
                            <span class="section_nub">章节${i.count}</span>
                            <span class="section_name">${section[1]}</span>
                        </div>
                        <div class="section_con open">
                            <ul>
                            	<c:forEach items="${lessonList}" var="lesson" varStatus="k">
                            	<c:if test="${lesson[1]==section[0]}">
                            	<li>
                                    <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
                                    <!-- 由于这里是对比课时所属章节才能确定章节和课时的关系所以下面的k.count可能不会准确，整合好doamin之后再处理 -->
                                    <span class="section_nub">课时${k.count}</span>
                                    <a href="javascript:void(0);" class="section_name">${lesson[2]}</a>
                                    <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson[0]}')">再次学习</a></span>
                                    <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
                                            <img src="${pageContext.request.contextPath}/images/courseSite/img8.png" class="big_code" /></span>
                                </li>
                            	</c:if>
                            	
                            	</c:forEach>
                                
                            </ul>
                        </div>
                    </div>
                </c:forEach>
                --%><!-- 遍历每个课程的章节 -->
				<c:forEach items="${wkCourse.wkChapters}" var="chapter" varStatus="i">
					<!-- 章节 -->
					<div class="section">
	                    <div class="section_tit">
	                        <span class="section_icon"></span>
	                        <span class="section_line"></span>
	                        <span class="section_nub">章节${i.count}</span>
	                        <span class="section_name">${chapter.name}</span>
	                    </div>
	                    <div class="section_con open" style="display: none;">
	                        <ul>
	                        	<c:forEach items="${chapter.wkLessons}" var="lesson" varStatus="k">
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub">课时${k.count}</span>
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            </li>
	                        	
	                        	</c:forEach>
	                            
	                        </ul>
	                    </div>
	                </div>
				</c:forEach>    

                   
                </div>
            </div>
            
            <!-- 教学资源 -->
            <div class="right_content_box" id="divResources">
                <div class="right_content_tit">
                    教学资源
                </div>
                    
    <!-- 文件列表 -->
	 <div class="section_wrapper">
	 <div class="section_con open">
	<%-- <ul>

	<c:forEach items="${uploads}" var="current" varStatus="i"> 
	<li>
  		<span>${current.id}&nbsp;</span>
 		<span>${current.name}</strong>&nbsp;</span>	
  		<span class="text-muted text-sm"><fmt:formatDate value="${current.upTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;</span>
 		
	</li>
	</c:forEach>                 
	</ul> --%>
	<table id="treeTable1" style="width:100%">
			<thead>
                <tr>
                    <td style="width:200px;">标题</td>
                    <td>创建者</td>
                    <td>最后修改时间</td>
                    <td>大小</td>
                    <td>操作</td>
                </tr>
                </thead>
                
                <c:forEach items="${folders}" var="f" varStatus="i">
                	<!-- 如果当前文件夹有子级目录，显示该文件夹的子级目录和该文件下下的文件-->
                	<c:if test="${f.folders.size()>0}">
                	<tr id="${f.id}"><!-- 当前文件夹 -->
                	<td><span controller="true">${f.name}</span></td>
                	<td>${f.user.cname}</td>
                    <td><fmt:formatDate value="${f.updateTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                    <td>${f.folders.size()}项</td>
                    <td>
                    </td>
                    </tr>
	                    <c:forEach items="${f.folders}" var="c" varStatus="k"><!-- 当前文件夹的子级目录 -->
	                    <c:if test="${c.folders.size()>0}"><!-- 子级目录还有子级目录 -->
	                    <tr id="${c.id}" pId="${f.id}" hasChild="true">
	                    <td>${c.name}</td>
	                    <td>${c.user.cname}</td>
	                    <td><fmt:formatDate value="${c.updateTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                    	<td>${c.folders.size()}项</td>
                    	<td>
                    	</td>
	                	</tr>
	                	
	                    </c:if>
	                    <c:if test="${c.folders.size()==0}"><!-- 子级目录如果没有下一级目录 显示文件夹和文件 -->
	                    <tr id="${c.id}" pId="${f.id}">
	                    <td>${c.name}</td>
	                    <td>${c.user.cname}</td>
	                    <td><fmt:formatDate value="${c.updateTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    <td>${c.uploads.size()}个文件</td>
	                    <td>
                    	</td>
	                	</tr>
	                	<!-- 子级目录下面的文件 -->
	                	<c:forEach items="${c.uploads}" var="upload" varStatus="m">
	                	<tr id="${upload.id}" pId="${c.id}">
	                    <td>${upload.name}</td>
	                    <td>${upload.user.cname}</td>
	                    <td><fmt:formatDate value="${upload.upTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    <td>${upload.size}</td>
                    	<td>
		                    <c:if test="${!empty user}">
	                    		<a href="javascript:void(0);" onclick="downloadFile('${upload.id}')" >下载</a>
	                   		</c:if>
                   		</td>
	                	</tr>
	                	</c:forEach>
	                    </c:if>
	                    
	                    </c:forEach>
	                    <!-- 当前文件夹下的文件 -->
	                    <c:forEach items="${f.uploads}" var="upload" >
	                    <tr id="${upload.id}" pId="${f.id}">
	                    <td>${upload.name}</td>
	                    <td>${upload.user.cname}</td>
	                    <td><fmt:formatDate value="${upload.upTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    <td>${upload.size}</td>
	                    <td>
	                    	<c:if test="${!empty user}">
                    			<a href="javascript:void(0);" onclick="downloadFile('${upload.id}')" >下载</a>
                    		</c:if>
                    	</td>
	                	</tr>
	                    </c:forEach>
	                    
                	</c:if>
                	<!-- 如果没有子级目录，则显示文件夹和该文件夹下的文件-->
                    <c:if test="${f.folders.size()==0}">
                    <tr id="${f.id}">
                	<td>${f.name}</td>
                	<td>${f.user.cname}</td>
                    <td><fmt:formatDate value="${f.updateTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                <td>${f.uploads.size()}个文件</td>
	                <td>
                    </td>
                    </tr>
                    <!-- 当前文件夹下的文件 -->
	                	<c:forEach items="${f.uploads}" var="upload" varStatus="m">
	                	<tr id="${upload.id}" pId="${f.id}">
	                    <td>${upload.name}</td>
	                    <td>${upload.user.cname}</td>
	                    <td><fmt:formatDate value="${upload.upTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
	                    <td>${upload.size}</td>
	                    
	                    <td>
	                    	<c:if test="${!empty user}">	
                    			<a href="javascript:void(0);" onclick="downloadFile('${upload.id}')" >下载</a>
	                    	</c:if>
                    	</td>
	                	</tr>
	                	</c:forEach>
                	</c:if>
                	
                
                
                </c:forEach>
                
            </table>
	</div>
	</div>
	</div>
			<!-- 作业列表 -->
			<c:if test="${!empty user}">
			<div class="right_content_box" id="divTassignments">
	            <div class="right_content_tit">
               		作业列表
	            </div>
	            <c:if test="${empty user}">
	            	请登录后查看作业
	            </c:if>
	            <c:if test="${!empty user}">
		            <table  id="my_show"> 
						<thead>
						    <tr>                   
						        <th>作业标题</th>
						        <th>状态</th>
						        <th>开始时间</th>
						        <th>截止时间</th>
						        	<c:if test="${fn:contains(user.authorities,'TEACHER')}">	
						        <th>提交总数/未批改数</th>
						        <th>未交学生</th>
						        </c:if>
						        <th>评分方式</th>
						        <th>操作</th>
						    </tr>
						</thead>
						<tbody>
							<c:forEach items="${viewTAssignments }" var="current"  varStatus="i">
						   		<tr>
						       		<td><a class="btn btn-common" href='javascript:void(0)'	onclick='grading(${current.id})'>${current.title}</a></td>
									<td>
						       			<c:if test="${current.status==0 }">
							       			草稿
							       		</c:if>
							       		<c:if test="${current.status==1 }">
							       			<c:if test="${now>= current.TAssignmentControl.startdate.time&&now< current.TAssignmentControl.duedate.time}">
												进行中
								       		</c:if>
									        <c:if test="${now<current.TAssignmentControl.startdate.time}">
												未到期
									       	</c:if>
									       	<c:if test="${now>current.TAssignmentControl.duedate.time}">
												 已过期
									       	</c:if>
							       		</c:if>
									</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.TAssignmentControl.startdate.time}" type="both"/></td>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.TAssignmentControl.duedate.time}" type="both"/></td>
								<c:if test="${fn:contains(user.authorities,'TEACHER')}">	
									<td>
										<b>${current.tAssignGradeSubmitCount }/<font color="red">${current.tAssignGradeNotCorrectCount }</font></b>
										<%--<a href="javascript:void(0)" onclick="downloadFile(${current.tAssignGradeSubmitCount },${current.id })">查看提交的附件</a>
									--%></td>
									<td>
									<a class="btn btn-common" href='javascript:void(0)'	onclick='NotSubmitStudents(${current.id})'>${current.noSubmitStudents }</a>
									</td>
								</c:if>
									<td>0--${current.TAssignmentAnswerAssign.score }</td>
									<td>
									<c:if test="${fn:contains(user.authorities,'STUDENT')}">
									        <c:if test="${current.submitTimeForStudent==0 }"> 
								        	<a href="${pageContext.request.contextPath}/teaching/assignment/newAssignmentGrade?&assignId=${current.id}">提交作业</a> 
								     </c:if>
								      <c:if test="${current.submitTimeForStudent!=0&&current.submitTimeForStudent!=current.TAssignmentControl.timelimit }"> 
								        	<a href="${pageContext.request.contextPath}/teaching/assignment/newAssignmentGrade?&assignId=${current.id}">再次提交</a> 
								      </c:if>
								      <c:if test="${current.submitTimeForStudent==current.TAssignmentControl.timelimit }"> 
								        	<a href="${pageContext.request.contextPath}/teaching/assignment/lookAssignmentGrade?&assignId=${current.id}&flag=0">查看作业</a> 
								      </c:if>
								       	</c:if>
								       	<c:if test="${fn:contains(user.authorities,'TEACHER')}">	
									        <a href="${pageContext.request.contextPath}/teaching/assignment/updateAssignmentById?assignId=${current.id}">编辑</a>|
									        <a href="${pageContext.request.contextPath}/teaching/assignment/assignmentGradingList?assignmentId=${current.id}&flag=1" onclick="return checkSubmitNumber(${current.tAssignGradeSubmitCount})">浏览提交内容</a>&nbsp;&nbsp;&nbsp;
								        </c:if>
										<c:if test="${fn:contains(user.authorities,'TEACHER')}">
											<c:if test="${current.status==0 }">
												<a href="${pageContext.request.contextPath}/teaching/assignment/deleteAssignmentById?assignId=${current.id}" onclick="return confirm('是否确认删除？')">删除</a>
												<a href="${pageContext.request.contextPath}/teaching/assignment/changeAsignmentStatusById?assignId=${current.id}" onclick="return confirm('是否确认发布,发布后不可删除？')">发布</a>
											</c:if>
								        </c:if>
										
									</td>
							   </tr>
							</c:forEach>
						</tbody>
					
					</table>
	            </c:if>
    		</div>
    		</c:if>
			<!-- 练习与测验 -->
			<c:if test="${!empty user}">
			<div class="right_content_box" id="divExams">
	            <div class="right_content_tit">
               		练习与测验
	            </div>
	            <c:if test="${empty user}">
	            	请登录后查看
	            </c:if>
	            <c:if test="${!empty user}">
	            	<c:if test="${fn:contains(user.authorities,'STUDENT')}">
	            	<b>参加测验</b>
		            <table  id="my_show"> 
						<thead>
					    	<tr>                   
						        <th>序号</th>
						        <th>标题</th>
						        <th>发布日期</th>
						        <th>过期日期</th>
						        <th>评分方式</th>
						    </tr>
						</thead>
						<tbody>
							<c:forEach items="${viewExams }" var="current"  varStatus="i">
						   		<tr>
						       		<td>
								        ${i.count }
								    </td>
								    <td>
								    	<a href="${pageContext.request.contextPath}/teaching/exam/beginExam?simulation=0&examId=${current.id}">${current.title}</a>
								    </td>
								    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.TAssignmentControl.startdate.time}" type="both"/></td>
								    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.TAssignmentControl.duedate.time}" type="both"/></td>
								    <td><font >0--${current.TAssignmentAnswerAssign.score }</font></td>
							   </tr>
							</c:forEach>
						</tbody><br>
					</table>
					<b>已提交测试</b><br>
					<table  id="my_show"> 
						<thead>
						    <tr>                   
						        <th>序号</th>
						        <th>试卷标题</th>
						        <th>成绩</th>
						        <th>提交日期</th>
						        <th>满分分值</th>
						    </tr>
						</thead>
						<tbody>
							<c:forEach items="${TAssignmentGradings}" var="current"  varStatus="i">
								<tr>    
								        <td>
								        ${i.count }
								        </td>
								        <td>${current.TAssignment.title}</td>
								        <td>
								         ${current.finalScore}
								        </td>
								        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.submitdate.time}" type="both"/></td>
								        <td><font color=red>${current.TAssignment.TAssignmentAnswerAssign.score }</font></td>
								</tr>
							</c:forEach>
						</tbody>
						
					</table>
					</c:if>
					<c:if test="${fn:contains(user.authorities,'TEACHER')}">
					<b>已发布测试</b>
		            <table  id="my_show"> 
						<thead>
						    <tr>                   
						        <th>序号</th>
						        <th>标题</th>
						        <th>状态</th>
						        <th>已提交</th>
						        <th>发布日期</th>
						        <th>过期日期</th>
						        <th>创建日期</th>
						        <th>评分方式</th>
						    </tr>
						</thead>
						<tbody>
							<c:forEach items="${viewExams }" var="current"  varStatus="i">
					       		<c:if test="${current.status==1 }">
							   		<tr>
						       			<td>
									        ${i.count }
									    </td>
									    <td>${current.title}<br></td>
									    <td>
										    <c:if test="${now>= current.TAssignmentControl.startdate.time&&now< current.TAssignmentControl.duedate.time}">
										                      开始
										   	</c:if>
										    <c:if test="${now<current.TAssignmentControl.startdate.time}">
										                      未到期
										    </c:if>
										    <c:if test="${now>current.TAssignmentControl.duedate.time}">
										                     已过期
										    </c:if>
									    </td>
									       
									    <td>
									       	<a href="/gvsun/teaching/exam/examGradingList?examId=${current.id}" onclick="return checkSubmitNumber(${current.tAssignGradeSubmitCount})">
									       		<%--<font color=blue>
									       		--%>${current.tAssignGradeSubmitCount }
									       		<%--</font>
									       	--%></a>
									    </td>
									    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.TAssignmentControl.startdate.time}" type="both"/></td>
									    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.TAssignmentControl.duedate.time}" type="both"/></td>
									    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.createdTime.time}" type="both"/></td>
									    <td><font >0--${current.TAssignmentAnswerAssign.score }</font></td>
								    </tr>
					       		</c:if>
							</c:forEach>
						</tbody>
					
					</table>
					</c:if>
	            </c:if>
    		</div>
    		</c:if>
    		
    		<!-- 实验管理 -->
    		<div class="right_content_box" id="divExperiments">
                <div class="right_content_tit">
                    实验管理
                </div>
                <c:if test="${empty user}">
	            	请登录后操作实验管理！
	            </c:if>
	            <c:if test="${!empty user}">
                <div class="section_wrapper">
                <!-- 遍历功能 -->
				<%--<c:forEach items="${wkCourse.wkChapters}" var="chapter" varStatus="i">
					<!-- 功能 -->
					<div class="section">
	                    <div class="section_tit">
	                        <span class="section_icon"></span>
	                        <span class="section_line"></span>
	                        <span class="section_nub">排课管理</span>
	                        <span class="section_name">${chapter.name}</span>
	                    </div>
	                    <div class="section_con open" style="display: none;">
	                        <ul>
	                        	<c:forEach items="${chapter.wkLessons}" var="lesson" varStatus="k">
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub">课时${k.count}</span>
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            </li>
	                        	
	                        	</c:forEach>
	                            
	                        </ul>
	                    </div>
	                </div>
				</c:forEach>--%>                       
				<%--<c:forEach items="${wkCourse.wkChapters}" var="chapter" varStatus="i">--%>     
					<!-- 排课管理 -->
					<div class="section">
	                    <div class="section_tit">
	                        <span class="section_icon"></span>
	                        <span class="section_line"></span>	                        
	                        <span class="section_nub">排课管理</span>
	                        <%--<span class="section_name">${chapter.name}</span>--%>
	                    </div>
	                    <div class="section_con open" style="display: none;">
	                        <ul>
	                        	<%--<c:forEach items="${chapter.wkLessons}" var="lesson" varStatus="k">--%>
	                        	<%--<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/timetable/listTimetableTerm?currpage=1&id=-1" target="_blank ">教务排课管理</a></span>
	                            </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/timetable/listReTimetable" target="_blank">二次排课（含分批)</a></span> 
	                           </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/timetable/listOnlyNoGroupReTimetable" target="_blank" >二次排课（不分批)</a></span>
	                            </li>
	                        	--%>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/timetable/selfTimetable/listSelfTimetable" target="_blank" >自主排课（含分组)</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/timetable/timetableAdmin?currpage=1" target="_blank" >排课管理</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/timetable/attendanceManageIframe?currpage=1&id=-1&status=-1" target="_blank" >考勤管理</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                            
	                            <%--	                        	
	                        	</c:forEach>	                            
	                        --%>
	                        </ul>
	                    </div>
	                </div>
	                
	                <!-- 实验运行管理 -->
					<div class="section">
	                    <div class="section_tit">
	                        <span class="section_icon"></span>
	                        <span class="section_line"></span>	                        
	                        <span class="section_nub">实验运行管理</span>
	                        <%--<span class="section_name">${chapter.name}</span>--%>
	                    </div>
	                    <div class="section_con open" style="display: none;">
	                        <ul>
	                        	<%--<c:forEach items="${chapter.wkLessons}" var="lesson" varStatus="k">--%>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/operation/experimentalmanagement?currpage=1" target="_blank" >实验大纲管理</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/operation/indexLmsExperiment?currpage=1" target="_blank" >实验项目管理</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/operation/complexLmsExperiment?currpage=1&tage=0" target="_blank" >综合实验项目管理</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/operation/operationtiemcategoriesdictionary?currpage=1" target="_blank" >实验大类字典</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                            
	                            <%--	                        	
	                        	</c:forEach>	                            
	                        --%>
	                        </ul>
	                    </div>
	                </div>
	                
	                <!-- 实验室及预约管理 -->
					<div class="section">
	                    <div class="section_tit">
	                        <span class="section_icon"></span>
	                        <span class="section_line"></span>	                        
	                        <span class="section_nub">实验室及预约管理</span>
	                        <%--<span class="section_name">${chapter.name}</span>--%>
	                    </div>
	                    <div class="section_con open" style="display: none;">
	                        <ul style="position: absolute;">
	                        	<%--<c:forEach items="${chapter.wkLessons}" var="lesson" varStatus="k">--%>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/appointment/listLabCenter?page=1" target="_blank" >实验中心</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/appointment/listLabAnnex?page=1" target="_blank" >实验室管理</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/Lab/labAnnexList?currpage=1" target="_blank" >实验室预约</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/Labreservation/Labreservationmanage?tage=0&currpage=1" target="_blank" >实验室预约管理</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                            
	                            <%--	                        	
	                        	</c:forEach>	                            
	                        --%>
	                        </ul>
	                    </div>
	                </div>
	                
	                <!-- 实验设备管理 -->
					<div class="section">
	                    <div class="section_tit">
	                        <span class="section_icon"></span>
	                        <span class="section_line"></span>	                        
	                        <span class="section_nub">实验设备管理</span>
	                        <%--<span class="section_name">${chapter.name}</span>--%>
	                    </div>
	                    <div class="section_con open" style="display: none;">
	                        <ul>
	                        	<%--<c:forEach items="${chapter.wkLessons}" var="lesson" varStatus="k">--%>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/device/listLabRoomDevice?page=1" target="_blank" >设备管理</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/device/reservationList?page=1" target="_blank" >设备预约管理</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/device/allDeviceLendList?page=1" target="_blank" >设备借用管理</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                            <li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/device/deviceRepairList?page=1" target="_blank" >设备维修管理</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/device/listLabRoomDeviceReceive?page=1" target="_blank" >低值易耗品申领</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/listCConsumables?page=1" target="_blank" >易耗品管理</a></span><%--	                                
	                                <a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')" class="section_name">${lesson.title}</a>
	                                <span class="learn_box"><a href="javascript:void(0)" onclick="mediaDisplay('${lesson.id}')">学习</a></span>
	                                <span class="code_box"><img src="${pageContext.request.contextPath}/images/courseSite/code.png" class="code_1" />
	                                        <img src="${lesson.codeImgPath}" class="big_code" /></span>
	                            --%></li>
	                            
	                            <%--	                        	
	                        	</c:forEach>	                            
	                        --%>
	                        </ul>
	                    </div>
	                </div>
	                
	                <!-- 实验队伍管理 -->
					<div class="section">
	                    <div class="section_tit">
	                        <span class="section_icon"></span>
	                        <span class="section_line"></span>	                        
	                        <span class="section_nub">实验队伍管理</span>
	                        <%--<span class="section_name">${chapter.name}</span>--%>
	                    </div>
	                    <div class="section_con open" style="display: none;">
	                        <ul>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/listLabRoomTeamMange?page=1" target="_blank" >助教管理</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/lab/listLabTeamJobClass?page=1" target="_blank" >助教职责</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/labTeamManage/indexCLabTeamSkill?currpage=1" target="_blank" >技术职务</a></span>
	                                </li>
	                            
	                        </ul>
	                    </div>
	                </div>
	                
	                <%--	                
	                <!-- 系统管理 -->
					<div class="section">
	                    <div class="section_tit">
	                        <span class="section_icon"></span>
	                        <span class="section_line"></span>	                        
	                        <span class="section_nub">系统管理</span>
	                    </div>
	                    <div class="section_con open" style="display: none;">
	                        <ul>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/system/listUser?currpage=1" target="_blank" >用户管理</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/userAuthorityMange/listUserAuthority" target="_blank" >权限管理</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/system/listTerm?currpage=1" target="_blank" >学期管理</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/system/timeManage" target="_blank" >节次管理</a></span>
	                                </li>
	                            
	                        </ul>
	                    </div>
	                </div>
	                
	                <!-- 绩效报表 -->
					<div class="section">
	                    <div class="section_tit">
	                        <span class="section_icon"></span>
	                        <span class="section_line"></span>	                        
	                        <span class="section_nub">绩效报表</span>
	                    </div>
	                    <div class="section_con open" style="display: none;">
	                        <ul>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/report/reportMain" target="_blank" >综合报表</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/report/businessReport/reportList" target="_blank" >业务报表</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/report/teachingReport/termRegister" target="_blank" >学期登记</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/report/teachingReport/weekRegister" target="_blank" >周次登记</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/report/teachingReport/experimentRegister" target="_blank" >上机登记</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/report/teachingReport/experimentPrepare" target="_blank" >实验准备</a></span>
	                                </li>
	                            
	                            
	                        </ul>
	                    </div>
	                </div>
	                
	                <!-- 共享数据 -->
					<div class="section">
	                    <div class="section_tit">
	                        <span class="section_icon"></span>
	                        <span class="section_line"></span>	                        
	                        <span class="section_nub">共享数据</span>
	                    </div>
	                    <div class="section_con open" style="display: none;">
	                        <ul>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/sharedData/findAllCourseSchedule?currpage=1&id=-1" target="_blank" >选课组管理</a></span> 
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/findAllSystemCampus" target="_blank" >校区管理</a></span> 
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/findAllDepartment?page=1" target="_blank" >单位管理</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/findAllSchoolAcademy?page=1" target="_blank" >学院管理</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/findAllSchoolBuild?page=1" target="_blank" >建筑管理</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/findAllSystemRoom?page=1" target="_blank" >房间管理</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/findAllUserCard?currpage=1" target="_blank" >一卡通管理</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/findAllSchoolDevice?page=1" target="_blank" >资产管理</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/findAllSchoolMajor?page=1" target="_blank" >专业管理</a></span>
	                                </li>
	                        	<li>
	                                <span class="section_icon"><img src="${pageContext.request.contextPath}/images/courseSite/learned.png"></span>
	                                <span class="section_nub"><a href="${pageContext.request.contextPath}/findAllCMajorDirection?page=1" target="_blank" >专业方向管理</a></span>
	                                </li> 
	                        </ul>
	                    </div>
	                </div>
	                --%>
	                </div>
                </c:if>
            </div>
            
            <div class="top">
        	<img src="${pageContext.request.contextPath}/images/index/top.png">
    		</div>
            <div class="footer_container">
	        <div class="footer">
                <div class="copyright">Copyright ©2016 西安电子科技大学 All Rights Reserved</div>
                <div class="power">XDU微课 power by<a href="www.gvsun.com">Gvsun</a>
                </div>
            </div>
	        
    </div>
        </div>
 
    </div>
    <!-- 弹出分数窗口 -->
<div id="Grading" class="easyui-window" title="作业详情" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add"  style="width:220px; height:200px;">
	
	<form:form action="" method="post">
	<table  id="my_show"> 
         <thead>
    
    <tr>    
        <th>作业内容</th>               
        <th>分数</th>
        <th>评语</th>
 		
    </tr>
    
    </thead>
    <tbody><tr>
        <td id="content">
 
    
        <td id="score">
 
        
        </td>
       <td id="comments">
 
        
        </td>
       </tr>
</tbody>
    
	</table>
	</form:form>
	
	
    </div>
    <!-- 弹出未交作业学生窗口 -->
<div id="NotSubmitStudents" class="easyui-window" title="未提交作业的学生" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add"  style="width:340px; height:400px;">
	
	<form:form action="" method="post">
	<table  id="students"> 
    
	</table>
	</form:form>
	
	
    </div>
    
    
    <script type="text/javascript">
        /* 分享和二维码*/
        $(".big_code_pic,.jiathis_style").hide()
        $(".code").click(
            function () {
                $(".big_code_pic").fadeToggle()
            }
        )
        $(".share").click(
                function () {
                    $(".jiathis_style").fadeToggle()
                }
            )
            /* 分享和二维码结束*/

        /**/
        $(".discuss_list li").hover(
            function () {
                $(this).css("background", "#fafafa")
            },
            function () {
                $(this).css("background", "#fff")
            }
        );
        $(".discuss_list li").click(
            function () {
                window.open("discusscontent.html",  "_blank")
            }
        )
        /*登录效果*/
 window.onload = function () {
     var h = $(window).height()
     /* var h = window. */
     $(".log-in").height(h) + "px"
 }
 $(".login_btn").click(
     function () {
         $(".log-in").show();
     });
 $(".close").click(
         function () {
             $(".log-in").hide();
         }
     )
     /*登录效果*/
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/courseSite/style.js"></script>
  	<script type="text/javascript" src="http://v3.jiathis.com/code_mini/jia.js" charset="utf-8"></script>
    <!--分享功能-->
    <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/courseSite/jia.js" charset="utf-8"></script> --%>
   	<div id="mediaDisplay" class="easyui-window" title="查看资源" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width: 1000px; height:700px;">
	</div>
</body>

</html>