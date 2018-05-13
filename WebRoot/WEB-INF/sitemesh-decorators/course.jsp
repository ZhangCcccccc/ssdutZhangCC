<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="decorator" content="none">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课程平台</title>
<decorator:head></decorator:head>
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式 -->
    <link href="${pageContext.request.contextPath}/css/tCourseSite/reset.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/lib.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/global.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.dragsort-0.5.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.cookie.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/tcoursesite.js"></script>
<style type="text/css">
	.tool_box li div{
		padding:3px 18px;
		border:1px solid #aaa;
		border-radius:4px;
		font-size:15px;
	}
	.tool_box li div:hover{
		background:#eee;
	}
</style>
</head>


<!-- 菜单栏开始  -->
<div>
        <div class="top ">
            <div class="header ma wh f24 cf">
                <!--<img src="images/logo1.png">-->
                <div class="sat_name l">课程平台</div>
                <div class="user f12 r">
                    <ul class=""><%--
                    <c:if test="${user.userRole>1}">
                    	<li class="my_course l ml15"><a href="${pageContext.request.contextPath}/login" class="wh">实验管理</a></li>
                    	</c:if>
                        <li class="my_course l ml15"><a href="${pageContext.request.contextPath}/tms/myCourseList?currpage=1" class="wh">我的课程</a></li>
                      	--%>
                      	<li class="my_course l ml15"><a href="${pageContext.request.contextPath}/self/myCenter" class="wh">返回首页</a></li>
                      	<li class="my_course l ml15"><a href="${pageContext.request.contextPath}/tms/courseList?currpage=1" class="wh">课程列表</a></li>
                      	<li class="user_name l ml15"><a href="javascript:void(0);" class="wh"><i class="fa fa-user fa-cir mr5"></i>${user.cname}</a></li>
                        <li class="log_out l ml15"><a href="${pageContext.request.contextPath}/pages/logout-redirect.jsp" class="wh">退出</a></li>
                    </ul>
                </div>
            </div>

        </div>
        <div class="course_info ma ">
            <div class="cf mt15 mb15 ">
                <div class="course_name f16 l">${tCourseSite.title}</div>
                <c:if test="${currflag==1}"><%--
                <div class="wire_btn l ml30 mt10 poi">
                    课程预览
                </div>
                --%><div class="wire_btn l ml20 mt10 poi" onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/copy/foldersList?tCourseSiteId=${tCourseSite.id}'">
                    课程复制
                </div>
                </c:if>              
                <c:forEach items="${tMessageShowViewList}" var="tMessageShowView"  varStatus="i">
                 <c:set var="createDate">  
    			<fmt:formatDate value="${tMessageShowView[8]}" pattern="yyyy-MM-dd " type="date"/>  
				</c:set> 
            	<c:if test="${createDate <= now}">
            		<c:if test="${tMessageShowView[5] == 0}">
                   		<div id="${tMessageShowView[2]}"  class="wire_btn l ml20 mt10 poi" style="background: #FF0000" onclick="showMessage(${tMessageShowView[2]})">                         
                        	${tMessageShowView[9] }                          
                    	</div>
                    </c:if>
                    <c:if test="${tMessageShowView[5] == 1}">
                   		<div id="${tMessageShowView[2]}" class="wire_btn l ml20 mt10 poi" style="background: #00FF00" onclick="showMessage(${tMessageShowView[2]})">                         
                        	${tMessageShowView[9] }                          
                    	</div>
                    </c:if>
                </c:if>
                </c:forEach>
                 
            </div>
            <div class="left_nav l mt10">
                <ul class="course_menu">
                	
                	  <li class="cm_list br3 ">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/skill/experimentSkillsList?tCourseSiteId=${tCourseSite.id}&moduleType=2&selectId=-1" class="f14 g3 ">
                    		<i class="fa fa-server bc mr5 ml5 f18"></i> 实验项目</a></li>
                    
                    <li class="cm_list br3">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/question/findQuestionList?tCourseSiteId=${tCourseSite.id}&moduleType=1&selectId=-1" class="f14 g3 ">
                    		<i class="fa fa-server bc mr5 ml5 f18"></i> 知识</a></li>
                    		
                  <%--
                    		
                    < <li class="cm_list br3 ">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=3&selectId=-1" class="f14 g3 ">
                    		<i class="fa fa-server bc mr5 ml5 f18"></i> 创造体验</a></li>
                    
                    <li class="cm_list br3">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/message/listMessages?tCourseSiteId=${tCourseSite.id}&currpage=1&queryType=1&titleQuery=" class="f14 g3 ">
                    		<i class="fa fa-bell-o bc mr5 ml5 f18"></i> 课程留言</a></li>
                    
                    --%>  
                    <li class="cm_list br3">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/message/listMessages?tCourseSiteId=${tCourseSite.id}&currpage=1&queryType=1&titleQuery=" class="f14 g3 ">
                    		<i class="fa fa-bell-o bc mr5 ml5 f18"></i> 公告栏</a></li>
                    	<c:if test="${flag == 1 || currflag == 1}">
                    		<li class="cm_list br3">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/student/courseStudentsList?tCourseSiteId=${tCourseSite.id}&currpage=1" class="f14 g3 ">
                    		<i class="fa fa-users bc mr5 ml5 f18"></i> 学习管理</a></li></c:if><%--
                    		
                    <li class="cm_list br3 ">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/disk/listFiles?tCourseSiteId=${tCourseSite.id}" class="f14 g3 ">
                    		<i class="fa fa-server bc mr5 ml5 f18"></i> 资源容器</a></li> 
                    
                    
                    --%><%--<li class="cm_list br3">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/log?tCourseSiteId=${tCourseSite.id}" class="f14 g3 ">
                    <i class="fa fa-pie-chart bc mr5 ml5 f18"></i> 学习行为</a></li>--%>
                  
                    
                </ul>
            </div>
        </div>
    </div>
<!-- 菜单栏结束  -->
  
           <div class="window_box hide fix zx2  " id="showMessage" style="z-index:300;">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
             <div class="add_con p20">
                <div class="add_module cf">
            <div class="l w100p f14 mt20">
                        公告标题
                        <input id="showTitle" type="text" class=" w100p b1 br3 h30 lh30 mt5 plr5" />
            </div>
            <div class="l w100p f14 mt20">
                        公告内容
                        <input id="showContent" type="text" class=" w100p b1 br3 h30 lh30 mt5 plr5">
            </div>
        </div>
  			</div>
        </div>
    </div>
   
  
        <c:if test="${module eq '知识'||module eq '技能'||module eq '体验'||moduleType==1}">
  	   <div class="course_con ma">
        <div class="course_cont r">
        	<ul class="tool_box cf rel zx2 pt5 pb10">
        			<c:choose>
        			<c:when test="${moduleType ==1 }">
        			</c:when>
        			<c:otherwise>
	                	<li class="rel l">
		                    <div class="wire_btn2 Lele l ml10 mt10 poi <c:if test="${selectId==-1}">bgb</c:if>" onclick="select(${tCourseSite.id},${moduleType},-1);">
		                        <i class="fa "></i>
		                        	<%--<c:if test="${moduleType==1||fn:contains(sessionUrl, 'exercise')||fn:contains(sessionUrl, 'question')}">知识</c:if>
		                        	--%>
		                        	
		                        	<c:if test="${moduleType==2}">技能</c:if>
		                        	<c:if test="${moduleType==3}">体验</c:if>
		                        	
		                    </div>
		                </li>
	                </c:otherwise>
	                </c:choose>
	                <%--
	                <li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi <c:if test="${selectId==1}">bgb</c:if>" onclick="select(${tCourseSite.id},${moduleType},1);">
	                        <i class="video fa "></i>视频
	                    </div>
	                </li>
	                <li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi <c:if test="${selectId==3}">bgb</c:if>" onclick="select(${tCourseSite.id},${moduleType},3);">
	                        <i class="material fa "></i>参考文件
	                    </div>
	                </li>
	                <li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi <c:if test="${selectId==2}">bgb</c:if>" onclick="select(${tCourseSite.id},${moduleType},2);">
	                        <i class="pic fa "></i>图片
	                    </div>
	                </li>
	                <li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi <c:if test="${selectId==4}">bgb</c:if>" onclick="select(${tCourseSite.id},${moduleType},4);">
	                        <i class="homework fa "></i>作业
	                    </div>
	                </li>
	                <li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi <c:if test="${selectId==6}">bgb</c:if>" onclick="select(${tCourseSite.id},${moduleType},6);">
	                        <i class="exam fa "></i>考试
	                    </div>
	                </li>
	                <li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi <c:if test="${selectId==5}">bgb</c:if>" onclick="select(${tCourseSite.id},${moduleType},5);">
	                        <i class="exam fa "></i>测试
	                    </div>
	                </li>
	                <li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi <c:if test="${selectId==9}">bgb</c:if>" onclick="select(${tCourseSite.id},${moduleType},9);">
	                        <i class="homework fa "></i>考勤
	                    </div>
	                </li>
	                --%> 
                <c:if test="${moduleType==1}">
                
	                <%--<li class="rel l">
	                	<c:if test="${flag>0}">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi <c:if test="${fn:contains(sessionUrl, 'question')}">bgb</c:if>" onclick="goToUrl('${pageContext.request.contextPath}/tcoursesite/question/findQuestionList?tCourseSiteId=${tCourseSite.id}');">
	                        <i class="fa "></i>题库
	                    </div>
	                    	</c:if>
	                </li>
	                --%><ul class="tool_box cf rel zx2 pt5  pb10">
                <li class="rel l pb5">
                	<c:if test="${flag>0}">
                    <div class="wire_btn Lele l ml30 mt10 poi" <c:if test="${fn:contains(sessionUrl, 'question')}">bgb</c:if>" onclick="goToUrl('${pageContext.request.contextPath}/tcoursesite/question/findQuestionList?tCourseSiteId=${tCourseSite.id}');">
                        <i class="fa"></i>题库
                    </div>
                    </c:if>
                    <div class="wire_btn Lele l ml30 mt10 poi" onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=1&selectId=-1'">
                        <i class="fa mr5"></i>课程知识
                    </div>
                </li>
                </ul>
                </c:if>
            	<c:if test="${flag>0&&fn:contains(sessionUrl, 'findQuestionList')}">
	                <li class="rel l pb5">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi" onclick="newQuestionPool('',${tCourseSite.id})">
	                        <i class="fa fa-plus mr5"></i>题库
	                    </div>
	                    <%--<div class="wire_btn2 Lele l ml10 mt10 poi" onclick="copyQuestionPool('${user.username}','${tCourseSite.id}',${isTest})">
	                        <i class="fa fa-plus mr5"></i>题库复制
	                    </div>
	                --%></li>	
                </c:if>
                <c:if test="${flag>0&&fn:contains(sessionUrl, 'testpoolsList')}">
	                <li class="rel l pb5">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi" onclick="newTestPool('',${tCourseSite.id})">
	                        <i class="fa fa-plus mr5"></i>试卷库
	                    </div>
	                    <div class="wire_btn2 Lele l ml10 mt10 poi" onclick="copyTestPool('${user.username}','${tCourseSite.id}')">
	                        <i class="fa fa-plus mr5"></i>试卷库复制
	                    </div>
	                </li>	
                </c:if>
                <c:if test="${flag>0&&fn:contains(sessionUrl, 'question/findTAssignmentItemsByQuestionId')}">
	                <div class="wire_btn2 Lele l ml10 mt10 poi" onclick="importItems()">
                        <i class="fa fa-plus mr5"></i>导入试题
                    </div>
                    <div class="wire_btn2 Lele l ml10 mt10 poi">
	                    <a href="${pageContext.request.contextPath}/tcoursesite/question/findQuestionList?tCourseSiteId=${tCourseSiteId}">返回</a>
                    </div>
                </c:if>
                <c:if test="${flag>0&&fn:contains(sessionUrl, 'testpool/findTAssignmentItemsByQuestionId')}">
	                <div class="wire_btn2 Lele l ml10 mt10 poi" onclick="importItems()">
                        <i class="fa fa-plus mr5"></i>导入试卷
                    </div>
                    <div class="wire_btn2 Lele l ml10 mt10 poi">
	                    <a href="${pageContext.request.contextPath}/tcoursesite/testpool/testpoolsList?tCourseSiteId=${tCourseSiteId}">返回</a>
                    </div>
                </c:if>
	            <c:if test="${fn:contains(sessionUrl, 'exercise')}">
	                <li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi <c:if test="${fn:contains(sessionUrl, 'OrderItem')}">bgb</c:if>" onclick="goToUrl('${pageContext.request.contextPath}/tcoursesite/exercise/findOrderItemListBySiteIdAndQuestionId?tCourseSiteId=${tCourseSite.id}&currpage=1&questionId=-1&itemType=4');">
	                        <i class="fa "></i>顺序学习
	                    </div>
	                </li>
	                <li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi <c:if test="${fn:contains(sessionUrl, 'findQuestionListBySiteId')}">bgb</c:if>" onclick="goToUrl('${pageContext.request.contextPath}/tcoursesite/exercise/findQuestionListBySiteId?tCourseSiteId=${tCourseSite.id}&currpage=1');">
	                        <i class="fa "></i>章节学习
	                    </div>
	                </li>
	                <li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi <c:if test="${fn:contains(sessionUrl, 'StochasticItem')}">bgb</c:if>" onclick="goToUrl('${pageContext.request.contextPath}/tcoursesite/exercise/findStochasticItemListBySiteIdAndQuestionId?tCourseSiteId=${tCourseSite.id}&currpage=1&questionId=-1&itemType=4');">
	                        <i class="fa "></i>随机学习
	                    </div>
	                </li>
	                <li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi <c:if test="${fn:contains(sessionUrl, 'MistakeItem')}">bgb</c:if>" onclick="goToUrl('${pageContext.request.contextPath}/tcoursesite/exercise/findMistakeItemListBySiteIdAndQuestionId?tCourseSiteId=${tCourseSite.id}&currpage=1&questionId=-1&itemType=4');">
	                        <i class="fa "></i>错题学习
	                    </div>
	                </li>
	            </c:if>
	            <c:if test="${moduleType==2}">
	            	<%--<li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi g9" onclick="goToUrl('');">
	                        <i class="fa "></i>知识地图
	                    </div>
	                </li>--%>
	                <li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi" onclick="goToUrl('${pageContext.request.contextPath}/tcoursesite/skill/experimentSkillsList?tCourseSiteId=${tCourseSite.id}');">
	                        <i class="fa "></i>实验项目
	                    </div>
	                </li>
	                <%--<li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi g9" onclick="goToUrl('');">
	                        <i class="fa "></i>云实验室
	                    </div>
	                </li>--%>
	                <%--<li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi g9" onclick="goToUrl('');">
	                        <i class="fa "></i>机房管理
	                    </div>
	                </li>--%>
	                <%--<li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi g9" onclick="goToUrl('');">
	                        <i class="fa "></i>实验准入
	                    </div>
	                </li>--%>
	            </c:if>
	            <c:if test="${moduleType==3}">
	            	<%--<li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi g9" onclick="goToUrl('');">
	                        <i class="fa "></i>毕业论文管理
	                    </div>
	                </li>--%>
	                <%--<li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi g9" onclick="goToUrl('');">
	                        <i class="fa "></i>学生创新管理
	                    </div>
	                </li>--%>
	                <%--<li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi g9" onclick="goToUrl('');">
	                        <i class="fa "></i>工程训练管理
	                    </div> 
	                </li>--%>
	                <li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi" onclick="goToUrl('${pageContext.request.contextPath}/tcoursesite/student/courseStudentsGroupList?tCourseSiteId=${tCourseSite.id}&currpage=1');">
	                        <i class="fa "></i>学生分组
	                    </div>
	                </li>
	                <%--<li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi g9" onclick="goToUrl('');">
	                        <i class="fa "></i>作品展示
	                    </div>
	                </li>--%>
	            </c:if>
	            <c:if test="${fn:contains(sessionUrl, 'chaptersList')&&viewFlag==0}">
	            	<li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi" onclick="goToUrl('${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}&viewFlag=1');">
	                        <i class="fa "></i>列表模式
	                    </div>
	                </li>
	            </c:if>
	            <c:if test="${fn:contains(sessionUrl, 'chaptersList')&&viewFlag==1}">
	                <li class="rel l">
                        <div class="wire_btn2 Lele l ml10 mt10 poi" onclick="goToUrl('${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}&viewFlag=0');">
                            <i class="fa "></i>顺序模式
                        </div>
                    </li>
	            </c:if>
           		<c:if test="${flag>0&&fn:contains(sessionUrl, 'chaptersList')}">
	                <li class="rel l">
	                    <div class="wire_btn2 Lele l ml10 mt10 poi" onclick="newWkChapter(-1)">
	                        <i class="fa fa-plus "></i>章节
	                    </div>
	                </li>
                </c:if>
               
            </ul>
        </div>
        </div>
    </c:if>

<decorator:body></decorator:body>

<!-- 页脚开始  -->


<!-- 页脚结束  -->
<script type="text/javascript">
$(".cm_list").click(
		function(){
			var sel =$(this).index()
			//console.log(sel);
			var s="kongsd";			
			$(".course_menu").find(".cm_list").eq(sel).addClass("select").siblings().removeClass("select");
			$.cookie("mysel",sel,{"path":"/", expires:30});
			
			var cookie_sel = $.cookie("mysel");
			console.log(document.cookie);
		}
	)
	$(function(){
	var dt = new Date(); 
	dt.setSeconds(dt.getSeconds() + 60); 
	document.cookie = "cookietest=1; expires=" + dt.toGMTString(); 
	var cookiesEnabled = document.cookie.indexOf("cookietest=") != -1; 		
		var cookie_sel = $.cookie("mysel"); 
		
		if(cookie_sel==null){ 
			$(".course_menu").find(".cm_list").eq(0).addClass("select").siblings().removeClass("select");
			
		}else{ 
		console.log(cookie_sel);
			$(".course_menu").find(".cm_list").eq(cookie_sel).addClass("select").siblings().removeClass("select");}
			
	})
	function showMessage(messageId) {  
		
    	$("#id").val(messageId);
    	$.ajax({
    		type: "POST",
    		url: "${pageContext.request.contextPath}/tcoursesite/message/showMessage",
    		data: {'messageId':messageId},
    		dataType:'json',
    		success:function(data){
    			$.each(data,function(key,values){  
    				//document.getElementById(key).innerHTML=values;
    				$("#"+key).val(""+values);
    			 }); 
    		},
    		error:function(){
    			alert("信息错误！");
    			}
    	});    
    $("#showMessage").fadeIn(100);
    $("#"+messageId).css("background","#00FF00");
}

</script>

<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select'           : {search_contains:true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
</script>
<!-- 下拉框的js -->

</body>
</html>
