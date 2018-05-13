<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html>

<head>
    <title>XDU实验实训教学平台</title>
    <!--[if lt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <![endif]-->
    <!--[if gte IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>  
    <![endif]-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/reset.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/lib.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/global.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tCourseSite/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
   	<link href="${pageContext.request.contextPath}/css/tCourseSite/check_work.css" rel="stylesheet" type="text/css">
   
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.dragsort-0.5.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>
    
    
    <!-- 文件上传的样式和js开始 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>  
	<!-- 文件上传的样式和js结束 -->
<script type="text/javascript">
	// 文件上传
	function uploadImageForLabRoom(type){
		$("#searchFile").show();
		$("#searchFile").fadeIn(100);
			$("#file_upload").uploadify({
				'fileTypeExts': "*.bmp;*.gif;*.png;*.tif;*.jpe;*.jpeg;*.jpg",
				'formData':{siteId:'${tCourseSite.id}',type:type},    //传值
				'swf': '${pageContext.request.contextPath}/swfupload/swfupload.swf',  
				'uploader':'${pageContext.request.contextPath}/tcoursesite/uploadImageForSite;jsessionid=<%=session.getId()%>',		//提交的controller和要在火狐下使用必须要加的id
				buttonText:'选择图片',
				
				onQueueComplete : function(data) {//当队列中的所有文件全部完成上传时触发	
				//当上传完所有文件的时候关闭对话框并且转到显示界面
					window.location.href="${pageContext.request.contextPath}/tcoursesite/courseInfo/courseInfo?tCourseSiteId="+${tCourseSite.id}+"&curWeek="+${curWeek};          	
				}
			});			 
	}
	// 添加课表
	function addSchedule(day,session){
		var id = day+session;
		$("#tCourseSiteTagByDayId").val(""+day);
		$("#tCourseSiteTagBySessionId").val(""+session);
		var content = "";
		content = $("#"+id).text();
		$("#scheduleContent").val(""+content);
		$("#addSchedule").fadeIn(100);
	}
	</script>

</head>

<body>
    <div class="course_con ma">
        <div class="course_cont r">
            <ul class="tool_box cf rel zx2 pt5  pb10">
                <li class="rel l pb5">
                    <div class="wire_btn l ml30 mt10 poi">
                        <i class="fa fa-download mr5"></i>下载课表
                    </div>
                </li>
                <li class="rel l pb5">
                    <div class="wire_btn l ml30 mt10 poi">
                        <i class="fa fa-download mr5"></i>下载课程介绍
                    </div>
                </li>
                <c:if test="${flag==1}">
                <li class="rel l pb5">
                    <div class="wire_btn l ml30 mt10 poi" onclick="uploadImageForLabRoom(1)">
                        <i class="fa fa-upload mr5"></i>上传课程图片
                    </div>
                </li>
                
                <li class="rel l pb5">
                    <div class="wire_btn l ml30 mt10 poi" onclick="uploadImageForLabRoom(2)">
                        <i class="fa fa-upload mr5"></i>上传教师图片
                    </div>
                </li>
                </c:if>


            </ul>
            
            <ul class="tool_box cf rel zx2 pt5  pb10">
            	<c:if test="${tCourseSite.siteImage != null && tCourseSite.siteImage ne ''}">
				<img  src="${pageContext.request.contextPath}${tCourseSite.siteImage}" border="0"  width="400px" ></img> 
				</c:if>
				课程图片
				<c:if test="${tCourseSite.teacherImage != null && tCourseSite.teacherImage ne ''}">
				<img  src="${pageContext.request.contextPath}${tCourseSite.teacherImage}" border="0"  width="400px" ></img> 
            	</c:if>
            	教师图片
            </ul>
             <div class=" lh40 bgg  pl30 f18 ">
                            <span class="bc">课表</span>
                            <c:if test="${flag==1}">
                            <div class="wire_btn  r ml10 mt6 mr10 poi f14" onclick="newSchedule()">                         
                                <i class="fa fa-plus mr5"></i>手动添加
                            </div>
                           	</c:if>
                        </div>
                        <table class="tc w97p courseShedule ml15 mr15 mt20 mb20 f14">
                            <tr>
                                <td>周次</td>
                               
                               
	                                    <td colspan="1"><input type="button"  value="上一周" class="button" onclick="lastWeek()"/></td>
	                                    <%--<td colspan="5">第${curWeek}周</td>
	                                    --%>
	                                    <td colspan="5">
	                                    <select onchange="findSchedule(this.value)"  class="chzn-select" style="width:150px;z-index:0;">		                     
					                                <option value="0">第${curWeek}周</option>                                   		
					                       			<option value="1">第一周</option>                                          		
					                       			<option value="2">第二周</option>
					                       			<option value="3">第三周</option>                                          		
					                       			<option value="4">第四周</option>
					                       			<option value="5">第五周</option>                                          		
					                       			<option value="6">第六周</option>
					                       			<option value="7">第七周</option>                                          		
					                       			<option value="8">第八周</option>
					                       			<option value="9">第九周</option>                                          		
					                       			<option value="10">第十周</option>
					                       			<option value="11">第十一周</option>                                          		
					                       			<option value="12">第十二周</option>
					                       			<option value="13">第十三周</option>                                          		
					                       			<option value="14">第十四周</option>
					                       			<option value="15">第十五周</option>                                          		
					                       			<option value="16">第十六周</option>
					                       			<option value="17">第十七周</option>                                          		
					                       			<option value="18">第十八周</option>
					                       			<option value="19">第十九周</option>
					                       			<option value="20">第二十周</option>
					                   	</select>
					                   </td>
	                                    <td colspan="1""><input type="button"  value="下一周" class="button" onclick="nextWeek()"/></td>
	                                   
                            </tr>
                            <tr>
                                <td>
                                    节次
                                </td>
                                <c:forEach items="${days}" var="day"  varStatus="i">
	                                    <td>${day.siteTagText }</td>
                                </c:forEach>
                            </tr>
                            <c:forEach items="${curSessions}" var="curSession"  varStatus="i">
                                <tr>
                                    <td >${curSession.siteTagText}</td>
                                    <c:forEach items="${days}" var="day"  varStatus="i">
                                    <td >
                                    <c:forEach items="${tCourseSiteSchedule}" var="entry"  varStatus="i">
                                    	<c:if test="${entry.day eq day.siteTag&&entry.session eq curSession.siteTag}">
                                    	<p id="${entry.id}">地点：${entry.place}</p>
                                    	<c:forEach items="${curFolders}" var="curFolder"  varStatus="i">                                   	
                                    	<p id="${curFolder[0]}">${curFolder[2]}</p>
                                    	<i class="fa fa-download c_tool poi" onclick="downloadFile(${curFolder[0]})"></i>                                    	                                   	
                                    	</c:forEach>                                 	                               	
                                    	</c:if>                                      	                                 	                                
                                    </c:forEach><%--
                                    <c:if test="${day.siteTag eq '5'}">
                                    	<c:forEach items="${curExams}" var="curExam"  varStatus="i">                                   	
                                    	<p id="${curExam[0]}">${curExam[1]}</p>                                    	                                    	                                   	
                                    	</c:forEach>
                                    </c:if> 
                                    <c:if test="${day.siteTag eq '7'}">
                                    	<c:forEach items="${curTests}" var="curTest"  varStatus="i">                                   	
                                    	<p id="${curTest[0]}">${curTest[1]}</p>                                    	                                    	                                   	
                                    	</c:forEach>
                                    </c:if> 
                                    --%></td>
                                    </c:forEach>
                                </tr>
                                </c:forEach>
                           <tr>
                                    	<td>考试</td>
                                    	<td></td>
	                                    <td></td>
	                                    <td></td>
	                                    <td></td>
	                                    <td>                                    
                                    	<c:forEach items="${curExams}" var="curExam"  varStatus="i">                                   	
                                    	<p id="${curExam[0]}">${curExam[1]}</p>
                                    	<c:if test="${flag==0}">
	                                 		<c:if test="${curExam[9] == 0}">
										       		<a href="${pageContext.request.contextPath}/teaching/exam/beginExam?simulation=0&examId=${curExam[0]}&moduleType=1&selectId=-1" onclick="return checkSubmitTimeForExam(${now>curExam[7]},${curTest[9]},${curExam[5] })">开始测试</a>
	                                 		</c:if>
	                                 		<c:if test="${curExam[9] > 0 && curExam[9] != curExam[5]}">
										       		<a href="${pageContext.request.contextPath}/teaching/exam/beginExam?simulation=0&examId=${curExam[0]}&moduleType=1&selectId=-1" onclick="return checkSubmitTimeForExam(${now>curExam[7]},${curTest[9]},${curExam[5] })">再次测试</a>
										       		<a href="${pageContext.request.contextPath}/teaching/exam/examGradingList?tCourseSiteId=${tCourseSite.id }&examId=${curExam[0]}&moduleType=1&selectId=-1" >答题详情</a>
	                                 		</c:if>
	                                 		<c:if test="${curExam[9] >= curExam[5]}">
										       		测试次数已用完
										       		<a href="${pageContext.request.contextPath}/teaching/exam/examGradingList?tCourseSiteId=${tCourseSite.id }&examId=${curExam[0]}&moduleType=1&selectId=-1" >答题详情</a>
	                                 		</c:if>
				                        </c:if>   
				                        <c:if test="${flag==1&&curExam[8] != 1}">
				                                 		<span class="c_tool w50 tc expired  mt10 bgc f10 l poi"> 草稿</span>
				                                 			<a href="${pageContext.request.contextPath}/teaching/exam/examInfo?tCourseSiteId=${tCourseSite.id }&examId=${curExam[0]}&moduleType=1&selectId=-1" >添加题目</a>
				                        </c:if>
				                       	<c:if test="${flag==1&&curExam[8] == 1}">
				                                 			<a href="${pageContext.request.contextPath}/teaching/exam/examGradingList?tCourseSiteId=${tCourseSite.id }&examId=${curExam[0]}&moduleType=1&selectId=-1" >答题详情</a>
				                                 			
				                                 			
				                        </c:if>                                  	                                    	                                   	
                                    	</c:forEach>                                    	
	                                    </td>
	                                    <td></td>
	                                    <td>
	                                    <c:forEach items="${curTests}" var="curTest"  varStatus="i">                                                            	
                                    	<p id="${curTest[0]}">${curTest[1]}</p>
                                    	<c:if test="${flag==0}">
	                                 		<c:if test="${curTest[9] == 0}">
										       		<a href="${pageContext.request.contextPath}/teaching/exam/beginExam?simulation=0&examId=${curTest[0]}&moduleType=1&selectId=-1" onclick="return checkSubmitTimeForExam(${now>curTest[7]},${curTest[9]},${curTest[5] })">开始测试</a>
	                                 		</c:if>
	                                 		<c:if test="${curTest[9] > 0 && curTest[9] != curTest[5]}">
										       		<a href="${pageContext.request.contextPath}/teaching/exam/beginExam?simulation=0&examId=${curTest[0]}&moduleType=1&selectId=-1" onclick="return checkSubmitTimeForExam(${now>curTest[7]},${curTest[9]},${curTest[5] })">再次测试</a>
										       		<a href="${pageContext.request.contextPath}/teaching/exam/examGradingList?tCourseSiteId=${tCourseSite.id }&examId=${curTest[0]}&moduleType=1&selectId=-1" >答题详情</a>
	                                 		</c:if>
	                                 		<c:if test="${curTest[9] >= curTest[5]}">
										       		测试次数已用完
										       		<a href="${pageContext.request.contextPath}/teaching/exam/examGradingList?tCourseSiteId=${tCourseSite.id }&examId=${curTest[0]}&moduleType=1&selectId=-1" >答题详情</a>
	                                 		</c:if>
				                        </c:if>
				                        <c:if test="${flag==1&&curTest[8] != 1}">
				                                 		<span class="c_tool w50 tc expired  mt10 bgc f10 l poi"> 草稿</span>
				                                 			<a href="${pageContext.request.contextPath}/teaching/exam/examInfo?tCourseSiteId=${tCourseSite.id }&examId=${curTest[0]}&moduleType=1&selectId=-1" >添加题目</a>
				                        </c:if>
				                       	<c:if test="${flag==1&&curTest[8] == 1}">
				                                 			<a href="${pageContext.request.contextPath}/teaching/exam/examGradingList?tCourseSiteId=${tCourseSite.id }&examId=${curTest[0]}&moduleType=1&selectId=-1" >答题详情</a>				                                 						                                			
				                        </c:if>                                    	                                    	                                   	
                                    	</c:forEach>
	                                    </td>
                       
                                </tr>
                        </table>

			<c:forEach items="${tCourseSiteChannels}" var="channel" varStatus="i">
				<c:forEach items="${channel.TCourseSiteArticals}" var="artical" varStatus="i">
		            <div class="course_mod f14 mb2">
		                <div class=" lh40 bgg  pl30 f18 ">
		                    <span class="bc">${artical.name}</span> 
		                    <c:if test="${flag==1}">
		                    <i class="fa fa-edit Lele g9 poi " onclick="editArtical(${artical.id})"></i>
		                	<i class="fa fa-trash-o g9 f18 mr5 poi" onclick="deleteArtical(${artical.id},${tCourseSite.id})"></i>
		                	</c:if>
		                </div>
		                <div class="module_con  mtb20">
		                    <div class="plr30 lh30 f14">
		                        <div class="w100p f14">
		                        	<%--<c:if test="${flag==1}">
		                            <textarea type="text" class=" w100p b1 br3 h30 lh30 mt5 plr5" onclick="editArtical(${artical.id})">${artical.content}</textarea>
		                        	</c:if>
		                        	<c:if test="${flag==0}">
		                            <textarea type="text" class=" w100p b1 br3 h30 lh30 mt5 plr5" readonly="readonly">${artical.content}</textarea>
		                        	</c:if>
		                        --%> <p>${artical.content}</p>
		                        </div>
		                    </div>
		                </div>
		            </div>
	            </c:forEach>
            </c:forEach>
            <c:if test="${flag==1}">
            <div class="cf mt20 mb20">
                <div class="w50p l">
                    <div class="bbtn bgb f10 r mt10 poi tc br3 wh w80" onclick="editArtical(-1,'','')">
                       	 新增课程信息
                    </div>
                </div>
            </div>
			</c:if>
			
            <%--<div class="cf mt20 mb20">
                <div class="w50p l">
                    <div class="bbtn bgb f10 r mt10 poi tc br3 wh w80" onclick="newSchedule()">
                       	 新建课程
                    </div>
                </div>
            </div>
			
        --%></div>

    </div>
    <div class="window_box hide fix zx2  " id="editCourseInfo"style="z-index: 1011;">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <form:form action="${pageContext.request.contextPath}/tcoursesite/courseInfo/saveArtical?tCourseSiteId=${tCourseSite.id}&curWeek=${curWeek}" method="POST" modelAttribute="tCourseSiteArtical" enctype="multipart/form-data" >
	            <form:hidden path="id" id="id"/>
	            <div class="add_tit p20 bb f16">
	            	名称:
	            	<form:input path="name" id="name" required="required" type="text" class=" w90p b1 br3 h30 lh30 mt5 plr5" />
	            </div>
	            <div class="add_con p20">
	                <div class="add_module cf">
	                    <div class="l w100p f14 mt10 mb20">
	                        <form:textarea path="content" type="text" class=" w100p b1 br3 h30 lh30 mt5 plr5"></form:textarea>
	                    </div>
	                </div>
	                <div class="cf tc">
	                    <input type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" value="保存"/>
	                    <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
	                        	取消
	                    </div>
	                </div>
	            </div>
	       	</form:form>
        </div>       
    </div>
    
     <div class="window_box hide fix zx2  " id="newSchedule" style="z-index: 1011;">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <form:form action="${pageContext.request.contextPath}/tcoursesite/courseInfo/saveSchedule?tCourseSiteId=${tCourseSite.id}&place=place&curWeek=${curWeek}" method="POST"  enctype="multipart/form-data" >
	            
	            <div class="add_tit p20 bb f16" style="width:820px;">
	            	星期:
	            	</select>
					<select  multiple="multiple" class=" w100p lh30 br3 b1 mt5 select_chosen" name="sDay" id="sDay" values="sDay" >
                         <c:forEach items="${days}" var="day"  varStatus="i">
	                      <option value="${day.siteTag }">${day.siteTagText }</option>	                     
                    	</c:forEach>                                 
                    </select> 
				</div>
	            <div class="add_tit p20 bb f16" style="width:820px;">
	            	节次:
	            	<select  multiple="multiple" class=" w100p lh30 br3 b1 mt5 select_chosen" name="sSession" id="sSession" values="sSession">
	            	<c:forEach items="${sessions}" var="session"  varStatus="i">
	                      <option value="${session.siteTag }">${session.siteTagText }</option>	                     
                    </c:forEach>
					</select>
			 	</div>
			 	<div class="add_tit p20 bb f16" style="width:820px;">
	            	周次:
	            	<select  multiple="multiple" class=" w100p lh30 br3 b1 mt5 select_chosen" name="sWeek" id="sWeek" values="sWeek">
	            	<c:forEach items="${weeks}" var="week"  varStatus="i">
	                      <option value="${week.siteTag }">${week.siteTagText }</option>	                     
                    </c:forEach>
					</select>
			 	</div>
			 	<div class="add_tit p20 bb f16" style="width:850px;">
	            	地点:     	 
	            	 <input name="place" id="place" type="text" class=" w90p b1 br3 h30 lh30 mt5 plr5" />                                   
	            </div>
	            <div class="add_con p20">
	                <div class="cf tc">
	                    <input type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" value="保存"/>
	                    <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
	                        	取消
	                    </div>
	                </div>
	            </div>
	       	</form:form>
        </div>
    </div>
    <div id="searchFile" class="window_box hide fix zx2  " title="请选择" closed="true" iconCls="icon-add" style="width:400px;height:200px;">
				    <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
				    <form id="form_file" method="post" enctype="multipart/form-data">
			           <table  border="0" align="center" cellpadding="0" cellspacing="0">
			            <tr>
				            <td>
				          	<div id="queue"></div>
						    <input id="file_upload" name="file_upload" type="file" multiple="true">
						    </td>
			            </tr>   
			            </table>
			         </form>
			     </div>
<script type="text/javascript">


//进入页面时清空菜单栏相中效果的cookie
$(function(){
  	$('.date_picker').date_input();
   	$(".course_menu").find(".cm_list").eq(0).addClass("select").siblings().removeClass("select");
	$.cookie("mysel",0,{"path":"/", expires:30});
	
	var cookie_sel = $.cookie("mysel");
	console.log(document.cookie);
})


$(function (){	
	$('.select_chosen').chosen();
	$(".chzn-container").css("z-index","0");
});
//编辑课程信息
function editArtical(articalId) {
    //var con = '<iframe id="con" scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/tcoursesite/videoLook?folderId=' + id + '" style="width:100%;height:560px;"></iframe>'
    //$("#mediaDisplay").html(con); 
    if(articalId!=-1){
    	$.ajax({
    		type: "POST",
    		url: "${pageContext.request.contextPath}/tcoursesite/courseInfo/editArtical",
    		data: {'articalId':articalId},
    		dataType:'json',
    		success:function(data){
    			$.each(data,function(key,values){  
    				//alert(key+"="+values);
    				//document.getElementById(key).innerHTML=values;
    				document.getElementById(key).innerHTML=values;
    				$("#"+key).val(""+values);
    			 }); 
    		},
    		error:function(){
    			alert("信息错误！");
    			}
    	});
    }
    $("#id").val(articalId);
    $("#editCourseInfo").fadeIn(100);
}
//新建课表
function newSchedule() {
    $("#newSchedule").fadeIn(100);
}
//删除文件夹
function deleteArtical(articalId,tCourseSiteId){
	if(confirm("是否确认删除？"))
	   {	         
		window.location = '${pageContext.request.contextPath}/tcoursesite/courseInfo/deleteArtical?articalId='+articalId+'&tCourseSiteId='+tCourseSiteId+'&curWeek='+${curWeek};
	   }
}
//上一周
function lastWeek(){ 
	var tCourseSiteId = ${tCourseSite.id};
	var lastWeek = ${curWeek};
	if(lastWeek>1){
		lastWeek = lastWeek -1;
	}
	else{
		lastWeek = 1;
		}
	window.location = '${pageContext.request.contextPath}/tcoursesite/courseInfo/courseInfo?tCourseSiteId='+tCourseSiteId+'&curWeek='+lastWeek;
  }
  //下一周
function nextWeek(){
	var tCourseSiteId = ${tCourseSite.id};
	var nextWeek = ${curWeek};
	if(nextWeek<20){
		nextWeek = nextWeek +1;
	}
	else{
		nextWeek = 20;
		}
	window.location = '${pageContext.request.contextPath}/tcoursesite/courseInfo/courseInfo?tCourseSiteId='+tCourseSiteId+'&curWeek='+nextWeek;
  }
//下载文件
function downloadFile(id){
	window.location.href="${pageContext.request.contextPath}/tcoursesite/downloadFile?id="+id;
}
//开始测试的检验
function checkSubmitTimeForExam(isOverdue,submitTime,timeLimit){
	if(isOverdue){
		alert("测验已过期，无法参加测验！");
		return false;
	}
	if(timeLimit!=0&&submitTime>=timeLimit){
		alert("已达到参加次数限制，无法继续参加！");
		return false;
	}
}
//周跳转
function findSchedule(weekNumber){
	var tCourseSiteId = ${tCourseSite.id};
	window.location.href="${pageContext.request.contextPath}/tcoursesite/courseInfo/courseInfo?tCourseSiteId=" + tCourseSiteId+"&curWeek="+weekNumber;
}
</script>

 <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.date_input.pack.js"></script>
 

</body>

</html>