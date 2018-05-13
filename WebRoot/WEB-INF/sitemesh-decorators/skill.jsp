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
</head>


<!-- 菜单栏开始  -->
<div>
        <div class="top ">
            <div class="header ma wh f24 cf">
                <!--<img src="images/logo1.png">-->
                <div class="sat_name l">课程平台</div>
                <div class="user f12 r">
                    <ul class="">
                        <li class="my_course l ml15"><a href="${pageContext.request.contextPath}/self/myCenter" class="wh">返回首页</a></li>
                        <li class="my_course l ml15"><a href="${pageContext.request.contextPath}/tms/index" class="wh">我的课程</a></li>
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
                   <li class="cm_list br3">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/message/listMessages?tCourseSiteId=${tCourseSite.id}&currpage=1&queryType=1&titleQuery=" class="f14 g3 ">
                    		<i class="fa fa-bell-o bc mr5 ml5 f18"></i> 公告栏</a></li>
                   <c:if test="${flag == 1 || currflag == 1}">
                      <li class="cm_list br3">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/student/courseStudentsList?tCourseSiteId=${tCourseSite.id}&currpage=1" class="f14 g3 ">
                    		<i class="fa fa-users bc mr5 ml5 f18"></i> 学习管理</a></li>
                   </c:if>
                	<%--<li class="cm_list br3">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/courseInfo/courseInfo?tCourseSiteId=${tCourseSite.id}&curWeek=1" class="f14 g3 ">
                    	<i class="fa fa-list-alt bc mr5 ml5 f18"></i> 课程信息</a></li>
                    	
                    <li class="cm_list br3">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=1&selectId=-1" class="f14 g3 ">
                    		<i class="fa fa-server bc mr5 ml5 f18"></i> 课程知识</a></li>
                    		
                    <li class="cm_list br3 ">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=2&selectId=-1" class="f14 g3 ">
                    		<i class="fa fa-server bc mr5 ml5 f18"></i> 实验技能</a></li>
                    		
                    <li class="cm_list br3 ">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=3&selectId=-1" class="f14 g3 ">
                    		<i class="fa fa-server bc mr5 ml5 f18"></i> 创造体验</a></li>
                    
                    <li class="cm_list br3">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/message/listMessages?tCourseSiteId=${tCourseSite.id}&currpage=1&queryType=1&titleQuery=" class="f14 g3 ">
                    		<i class="fa fa-bell-o bc mr5 ml5 f18"></i> 公告栏</a></li>
                    
                    <li class="cm_list br3">
                    	<a href="${pageContext.request.contextPath}/tcoursesite/student/courseStudentsList?tCourseSiteId=${tCourseSite.id}&currpage=1" class="f14 g3 ">
                    		<i class="fa fa-users bc mr5 ml5 f18"></i> 学习管理</a></li>
                    		
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

<!--实验技能模块装饰页面-->
    <div class="course_con ma back_gray">
        <div class="course_infobox r">
                <h4 class="course_infobox_title">
                    <span>${tCourseSite.title}</span>
                </h4>
                <div class="course_infobox_profile">
                	<p>${tCourseSite.experimentSkillProfile}</p>
                </div>
                <div class="course_schedule">
                    <c:forEach begin="1" end="${skillDoList[0]}" step="1" varStatus="j" var="current">	
               			<div class="bggreen"></div>
                   	</c:forEach>
                   	<c:forEach begin="1" end="${skillDoList[1]}" step="1" varStatus="j" var="current">	
               			<div class="bgb"></div>
                   	</c:forEach>
                   	<c:forEach begin="1" end="${skillDoList[2]}" step="1" varStatus="j" var="current">	
               			<div class="bgg"></div>
                   	</c:forEach>
                    <p class="f16">( ${skillDoList[0]}/${fn:length(tExperimentSkills)})</p>
                </div>
        </div>
    </div>
    <div class="course_con ma back_gray" >
        <div class="course_cont r ">
            <div class="course_nav">
                <div class="r lab_item_ctrl">
                    <a class="btn_back" href="${pageContext.request.contextPath}/tcoursesite/skill/experimentSkillsList?tCourseSiteId=${tCourseSite.id}">返回</a>
                </div>
                <ul class="nav_tab">
                    <li <c:if test="${url eq 'viewExperimentSkill'}">class="chosen"</c:if>>
                    <a href="${pageContext.request.contextPath}/tcoursesite/skill/viewExperimentSkill?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}">实验内容</a></li>
                    <li <c:if test="${url eq 'coachExperimentSkill'}">class="chosen"</c:if>>
                    <a href="${pageContext.request.contextPath}/tcoursesite/skill/coachExperimentSkill?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}">实验指导</a></li>
                    <li <c:if test="${url eq 'weikeExperimentSkill'}">class="chosen"</c:if>>
                    <a href="${pageContext.request.contextPath}/tcoursesite/skill/weikeExperimentSkill?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}">实验微课</a></li>
                    <li <c:if test="${url eq 'prepareExam'}">class="chosen"</c:if>>
                    <a href="${pageContext.request.contextPath}/tcoursesite/skill/prepareExam?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}">预习测试</a></li>
                    <li <c:if test="${url eq 'reportExperimentSkill'}">class="chosen"</c:if>>
                    <a href="${pageContext.request.contextPath}/tcoursesite/skill/reportExperimentSkill?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}">实验报告</a></li>
                    <li <c:if test="${url eq 'workExperimentSkill'}">class="chosen"</c:if>>
                    <a href="${pageContext.request.contextPath}/tcoursesite/skill/workExperimentSkill?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}">实验作业</a></li>
                    <li <c:if test="${url eq 'discussExperimentSkill'}">class="chosen"</c:if>>
                    <a href="${pageContext.request.contextPath}/tcoursesite/skill/discussExperimentSkill?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}&currpage=1">实验问答</a></li>
                    <li <c:if test="${url eq 'gradeExperimentSkill'}">class="chosen"</c:if>>
                    <a href="${pageContext.request.contextPath}/tcoursesite/skill/gradeExperimentSkill?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}">成绩管理</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!--实验技能模块装饰页面结束-->



  
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
