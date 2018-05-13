<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>实验报告--老师页面</title>
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
    <link href="${pageContext.request.contextPath}/css/tCourseSite/skill/experiment.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/skill/picChange.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>

</head>
<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="tCourseSiteId" value="${tCourseSite.id}"/>
	<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
     <div class="course_con ma back_gray" >
        <div class="course_cont r back_gray">
            <div class="course_content">
                <div class="tab_content">
                <c:if test="${flag==1||(flag==0&&assignment.status == 1)}">
                	<ul class="tool_box cf rel zx2 pt5 bb pb10">
		                <li class="rel l pb5">
		                    <div class="wire_btn Lele l ml30 mt10 poi" onclick="newReportWork(${wkChapter.id},-1,0)">
		                        <i class="fa fa-plus mr5"></i>新建作业
		                    </div>
		                </li>
		            </ul>
		        </c:if>
                   <div class="course_mod f14">
                <div class="module_tit lh40 bgg  pl30 ">
                </div>
                <div class="module_con  mtb20">
                    <ul class="gdrag_list">
                    
                    <c:forEach items="${wkChapter.wkFolders}" var="folder" varStatus="i">
                    	<c:if test="${folder.type == 4}">
								<c:forEach items="${folder.TAssignments}" var="assignment" varStatus="i">
								<c:if test="${flag==1||(flag==0&&assignment.status == 1)}">
		                        <li id="folderli${folder.id}" class="homework_list hg9 lh35 rel ptb10 ovh">
		                            <div class="cf pl30  rel zx1 z c_category">
		                                <i class="fa fa-file-text-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
		                                <div class="l mlr15 cc1 c_tool poi">
		                                <c:if test="${flag==0}">
		                                <c:forEach items="${chapterViewTAssignments}" var="view" varStatus="i">
			                                 <c:if test="${view.id == assignment.id}"> 
			                                 	<c:if test="${now > assignment.TAssignmentControl.startdate.time && now < assignment.TAssignmentControl.duedate.time}">
											       <c:if test="${view.submitTimeForStudent==0 }"> 
												  	  <a href="${pageContext.request.contextPath}/tcoursesite/assignment/saveAssignmentGrade?tCourseSiteId=${tCourseSite.id}&assignId=${assignment.id}&flag=0&skillId=${skillId}" >作业——${folder.name}</a> 
												   </c:if>
												   <c:if test="${view.submitTimeForStudent!=0&&view.submitTimeForStudent!=view.TAssignmentControl.timelimit&&(empty view.scoreForStudent) }"> 
												  	  <a href="${pageContext.request.contextPath}/tcoursesite/assignment/saveAssignmentGrade?tCourseSiteId=${tCourseSite.id}&assignId=${assignment.id}&flag=0&skillId=${skillId}">作业——${folder.name}</a> 
												   </c:if>
												   <c:if test="${view.submitTimeForStudent!=0&&view.submitTimeForStudent!=view.TAssignmentControl.timelimit&&(not empty view.scoreForStudent) }">
												     	<a href="${pageContext.request.contextPath}/teaching/assignment/lookAssignmentGrade?assignId=${assignment.id}&flag=0&moduleType=${moduleType}&selectId=${selectId}" >作业——${folder.name}</a> 
												   </c:if>
												   <c:if test="${assignment.teacherFilePath!='' && assignment.teacherFilePath!=null }">
												   	   <a href="${pageContext.request.contextPath}/tcoursesite/assignment/downloadFile?tCourseSiteId=${tCourseSite.id}&id=${assignment.id}">作业——${folder.name}</a>
												   </c:if> 
												  </c:if>
												   <c:if test="${view.submitTimeForStudent==view.TAssignmentControl.timelimit }"> 
												       <a href="${pageContext.request.contextPath}/teaching/assignment/lookAssignmentGrade?assignId=${assignment.id}&flag=0&moduleType=${moduleType}&selectId=${selectId}" >作业——${folder.name}</a> 
												   </c:if>
												  <c:if test="${now > assignment.TAssignmentControl.duedate.time}">
												  		<a href="javascript:void(0)" >作业——${folder.name}</a> 
												  </c:if> 
											 </c:if>
										 </c:forEach>
		                                </c:if>
		                                <c:if test="${flag==1}">
		                                <a href="${pageContext.request.contextPath}/tcoursesite/assignment/showAssignmentGrading?tCourseSiteId=${tCourseSite.id}&assignmentId=${assignment.id}&skillId=${skillId }&flag=${flag}" onclick="return checkSubmitNumber(${current.tAssignGradeSubmitCount})">
		                                作业——${folder.name}</a></c:if>
		                                </div>
		                                	<div class="accessory_info l f12 g9"> 
			                                 
			                                 <c:if test="${assignment.status == 1}">
			                                 	<c:if test="${now < assignment.TAssignmentControl.startdate.time}">
			                                 		开放时间为
			                                 		<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${assignment.TAssignmentControl.startdate.time }"/>
			                                 	</c:if>
			                                 	<c:if test="${now > assignment.TAssignmentControl.startdate.time && now < assignment.TAssignmentControl.duedate.time}">
			                                 		结束时间为
			                                 		<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${assignment.TAssignmentControl.duedate.time }"/>
			                                 	</c:if>
			                                 	<c:if test="${now > assignment.TAssignmentControl.duedate.time}">
			                                 		<span class="c_tool w50 tc expired  mt10 bgc f10 l poi"> 已过期</span>
			                                 	</c:if>
			                                 	<c:if test="${flag==0 && stuTAssignment.TAssignmentGradings.size() != 0}">
												    <span style="color: green">已提交${assignment.TAssignmentGradings.size() }次</span>
											    </c:if>
			                                 </c:if>
											    
			                                 	<c:if test="${flag==0}">
			                                 		<c:forEach items="${chapterViewTAssignments}" var="view" varStatus="i">
			                                 			<c:if test="${view.id == assignment.id}"> 
			                                 				<c:if test="${now > assignment.TAssignmentControl.startdate.time && now < assignment.TAssignmentControl.duedate.time}">
													        <c:if test="${view.submitTimeForStudent==0 }"> 
												        		<a href="${pageContext.request.contextPath}/tcoursesite/assignment/saveAssignmentGrade?tCourseSiteId=${tCourseSite.id}&assignId=${assignment.id}&flag=0&skillId=${skillId}" >提交作业</a> 
												     		</c:if>
												      		<c:if test="${view.submitTimeForStudent!=0&&view.submitTimeForStudent!=view.TAssignmentControl.timelimit&&(empty view.scoreForStudent) }"> 
												        		<a href="${pageContext.request.contextPath}/tcoursesite/assignment/saveAssignmentGrade?tCourseSiteId=${tCourseSite.id}&assignId=${assignment.id}&flag=0&skillId=${skillId}">再次提交</a> 
												      		</c:if>
												      		<c:if test="${view.submitTimeForStudent!=0&&view.submitTimeForStudent!=view.TAssignmentControl.timelimit&&(not empty view.scoreForStudent) }">
												      			<a href="${pageContext.request.contextPath}/teaching/assignment/lookAssignmentGrade?assignId=${assignment.id}&flag=0&moduleType=${moduleType}&selectId=${selectId}" >查看作业</a> 
												      		</c:if>
												      		<c:if test="${assignment.teacherFilePath!='' && assignment.teacherFilePath!=null }">
												      			<a href="${pageContext.request.contextPath}/tcoursesite/assignment/downloadFile?tCourseSiteId=${tCourseSite.id}&id=${assignment.id}">下载教师附件</a>
												      		</c:if> 
												      		</c:if>
												      		<c:if test="${view.submitTimeForStudent==view.TAssignmentControl.timelimit }"> 
												        		<a href="${pageContext.request.contextPath}/teaching/assignment/lookAssignmentGrade?assignId=${assignment.id}&flag=0&moduleType=${moduleType}&selectId=${selectId}" >查看作业</a> 
												      		</c:if>
												            <c:if test="${flag==0}">
												              <c:set  var="tAssignmentGrading_id" value="0" />
												              <c:set  var="tAssignmentGrading_islate" value="0" />
												              <c:forEach items="${assignment.TAssignmentGradings }" var="tAssignmentGrading" varStatus="j">
												              	 <c:if test="${tAssignmentGrading.accessmentgradingId > tAssignmentGrading_id}">
												              	 	 <c:set var="tAssignmentGrading_id" value="${tAssignmentGrading.accessmentgradingId}" />
										 		              	 	 <c:set var="tAssignmentGrading_islate" value="${tAssignmentGrading.islate}" />
												              	 </c:if>
												              </c:forEach>
												              <c:if test="${tAssignmentGrading_islate ==2 }">
												              	  <font color="red">已被驳回</font>
												              </c:if>
			                                 	            </c:if>
												      	</c:if>
										       		</c:forEach>
										       	</c:if>
											       	
										       	<c:if test="${flag==1&&assignment.status == 1}">
										       		<a href="${pageContext.request.contextPath}/tcoursesite/assignment/showAssignmentGrading?tCourseSiteId=${tCourseSite.id}&assignmentId=${assignment.id}&skillId=${skillId }&flag=${flag}" onclick="return checkSubmitNumber(${current.tAssignGradeSubmitCount})">浏览提交内容</a>
			                                 	</c:if>
			                                 	<c:if test="${flag==1&&assignment.status != 1}">
			                                 		<span class="c_tool w50 tc expired  mt10 bgc f10 l poi"> 草稿</span>
			                                 	</c:if>
			                                 
		                                	</div>
		                                	<c:if test="${flag==1}">
			                                	<i class="newScroll fa fa-trash-o g9 f18 r mr30  lh35 c_tool hide poi"  onclick="deleteWkFolder(${folder.id},${tCourseSite.id},0)"></i>
			                                	<i class="newScroll fa fa-edit g9 f18 r mr10  lh35 c_tool hide poi" onclick="newReportWork(${wkChapter.id},${folder.id},0)"></i>
			                            	</c:if>
			                            </div>
		                        	</li>
		                        	</c:if>
		                        </c:forEach>
	                        </c:if>
                        </c:forEach>
                    </ul>
                </div>
            	</div>
                </div>
            </div>
        </div>
    </div>
    
    
   		<!--添加作业开始-->
<div id="editAssignmentFolder" class="window_box hide fix zx2  ">
	<div class="window_con bgw b1 br3 rel bs10 ">
           <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p10 bb f16">新增学习活动</div>
           <div class="add_con p10">
	<form:form action="${pageContext.request.contextPath}/tcoursesite/assignment/saveAssignmentGrading?tCourseSiteId=${tCourseSite.id}&skillId=${skillId}" 
	method="POST" modelAttribute="tAssignment" enctype="multipart/form-data" onsubmit="return submitAssignment()">
   		<div class="w98p cf">
   		<div class="abs f10 gc" style='left:145PX;top:17px'>
			<c:if test="${tAssignment.id ==null }">
				请填写如下信息，然后点击页面最下面的“发布”按钮。带<font color="red">*</font>的项目必须填写。
			</c:if>
			<c:if test="${tAssignment.id !=null }">
				<c:if test="${tAssignment.status==1 }">
					<font color="red">您正在修订的作业已经发布给学生。</font>
				</c:if>
				<c:if test="${now>tAssignment.TAssignmentControl.duedate.time }">
					<font color="red">你正在修改一份已经过了截止时间的作业。</font>
				</c:if>
			</c:if>
			</div>
			<div class="cf  pb20">
			<div class=" w45p f14 l mt10">
		    	<label >作业标题<font color="red">*</font>：</label>
		    	<form:input path="title" id="tAssignmentTitle" required="true" type="text" class=" w100p b1 br3 h30 lh30 mt5 plr5" />
		    	<form:hidden path="id" id="tAssignmentId" />
		    	<form:hidden path="TAssignmentAnswerAssign.id" id="tAssignmentAnswerAssignId" />
		    	<form:hidden path="TAssignmentControl.id" id="tAssignmentControlId" />
			</div>	
			<div class=" w45p f14 r mt10 cf">
                           章节
                  <form:select path="wkFolder.wkChapter.id" id="tAssignmentWkChapterId" 
                  onchange="changeAssignmentLessonsByChapterId(this.value)" class="w100p lh30 br3 b1 mt5 select_chosen">
                  			<form:option value="-1">请选择</form:option>
                      <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
                      		<c:if test="${chap.type == 200}">
                      			<form:option value="${chap.id}">${chap.name}</form:option>
                      		</c:if>
                      </c:forEach>
                  </form:select>

              </div>
              </div>	
              <div class="cf">
	  		<div class="l w45p f14 mt10 rel">
			     <label >开始时间<font color="red">*</font>：</label>
				 <input type="text" name="startdateAssignment" id="TAssignmentControl_startdate" value="${startdate }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly" required="required" class="Wdate w100p b1 br3 h30 lh30 mt5 plr5 " />
		 	</div>
		 	<div class="r w45p f14 mt10 rel">
			     <label >截止时间<font color="red">*</font>：</label>
				 <input type="text" name="duedateAssignment" id="TAssignmentControl_duedate" value="${duedate }"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly" required="required"class="Wdate w100p b1 br3 h30 lh30 mt5 plr5 rel"  />
		 	</div>
		 	</div>
		  	<div class="l w100p f14 l mt10 hide">
			   	<div>
	     			<label>作业布置人：</label>
	     			<form:input path="TAssignmentAnswerAssign.user.username" id="tAssignmentUsername" placeholder="请输入工号/学号" class=" w100p b1 br3 h30 lh30 mt5 plr5"/>
			 	</div>
		 	</div>
		 	
	 		<div class="l w100p f14 l mt10 hid">
    				<div class="add_box bd1 bgg hg9 cf lh30 ">
                           <div class="l p20 bgc"> <i class="fa fa-plus mr5"></i>上传附件：</div>
                           <input type="file" name="file" class="l ml30 mt20 poi" />
                  </div>     
    			</div>
		 	
		   <div class="w100p f14 l mt10 hid">
		    	<div>
	     			<label >评分设置：</label><br>
	     			<form:radiobutton path="TAssignmentControl.toGradebook" name="TAssignmentControlToGradebook" value="yes"  id="TAssignmentControlToGradebookYes" checked="checked"/><label for="TAssignmentControlToGradebookYes">将作业添加到成绩簿</label> 
	     			<form:radiobutton path="TAssignmentControl.toGradebook" name="TAssignmentControlToGradebook" value="no"  id="TAssignmentControlToGradebookNo"/><label for="TAssignmentControlToGradebookNo">不要将作业加入到成绩簿中 </label><br>
	     			<form:radiobutton path="TAssignmentControl.gradeToStudent" name="TAssignmentControlGradeToStudent" value="yes" id="TAssignmentControlGradeToStudentYes" checked="checked"/><label for="TAssignmentControlGradeToStudentYes">将成绩公布给学生 </label>
	     			<form:radiobutton path="TAssignmentControl.gradeToStudent" name="TAssignmentControlGradeToStudent" value="no" id="TAssignmentControlGradeToStudentNo" /><label for="TAssignmentControlGradeToStudentNo">不将成绩公布给学生</label><br>
	     			<form:radiobutton path="TAssignmentControl.gradeToTotalGrade" name="TAssignmentControlGradeToTotalGrade" value="yes" id="TAssignmentControlGradeToTotalGradeYes" checked="checked"/><label for="TAssignmentControlGradeToTotalGradeYes">将成绩计入总成绩</label>
	     			<form:radiobutton path="TAssignmentControl.gradeToTotalGrade" name="TAssignmentControlGradeToTotalGrade" value="no" id="TAssignmentControlGradeToTotalGradeNo" /><label for="TAssignmentControlGradeToTotalGradeNo">不将成绩计入总成绩</label>
		 		</div>
	 		</div>
		    <div class="l w45p f14 l mt10">
		    	<div>
		    		<label>是否允许重复提交</label>
		    		<div claSS="cf">
		    		<div class="l lh40">
	     			<input type="checkbox" onchange="showTimeLimitDiv(this)" <c:if test="${tAssignment.TAssignmentControl.timelimit != 1 }">checked</c:if> id="showTime"/>
	     			<label for="showTime">是</label>
	     			</div>
	     			<div id="timeLimit" <c:if test="${tAssignment.TAssignmentControl.timelimit == 1 }">style="display: none;"</c:if> class="l ml10 w80p lh40">
	     				<div class="rel w100p  l" >
						<form:select class="select_chosen" path="TAssignmentControl.timelimit" id="timelimitselect"  required="true" >
							<option value ="-1" >请选择重复提交次数(默认为无限制)</option>
							<option value ="1" <c:if test="${tAssignment.TAssignmentControl.timelimit==1 }">selected</c:if>>1</option>
							<c:forEach begin="2" end="10"  varStatus="i" >
				               	<option value ="${i.current }" <c:if test="${tAssignment.TAssignmentControl.timelimit==i.current }">selected</c:if> >${i.current }</option>
					       	</c:forEach>
					       	<option value="0"  <c:if test="${tAssignment.TAssignmentControl.timelimit==0 }">selected</c:if>>无限制</option>
						</form:select>
						</div>
		 			</div>
		 			</div>
		 		</div>
	 		</div>
	 		<div class="r w45p f14 l mt10"> 
			   	<div>
	     			<label>学生提交作业的形式<font color="red">*</font>：</label>
	     			<form:select class="select_chosen" path="TAssignmentControl.submitType" id="TAssignmentControlSubmitType" style="width:250px;" required="required">
	     				<option value="1" <c:if test="${tAssignment.TAssignmentControl.submitType==1 }">selected</c:if>>输入文字或添加附件</option>
	     				<option value="2" <c:if test="${tAssignment.TAssignmentControl.submitType==2 }">selected</c:if>>直接输入文字</option>
	     				<option value="3" <c:if test="${tAssignment.TAssignmentControl.submitType==3 }">selected</c:if>>仅提交附件</option>
	     			</form:select>
			 	</div>
		 	</div>
	 		<div class="w100p f14 l mt10">
			   	<div>
			   		<!--默认作业配置中的最高分为100分  -->
	     			<label>作业分值<font color="red">*</font>：</label>
	     			<form:input path="TAssignmentAnswerAssign.score"  type="text" placeholder="请输入分值" class=" w100p b1 br3 h30 lh30 mt5 plr5" required="true"/>
			 	</div>
		 	</div>
		 	<div class="w100p f14 l mt10">
			   	<div>
	     			<label>作业要求：</label>
	     			<textarea id="assignmentContent" path="content" name="content" class=" w100p b1 br3 h30 lh30 mt5 plr5">${tAssignment.content }</textarea>
			 	</div>
		 	</div>
		 	<form:hidden path="type"  value="assignment"/>
		 	<form:hidden path="siteId" id="assignmentSiteId" value="${tCourseSite.id}"/>
		 	<form:hidden path="status" id="assignmentStatus" value="0"/>
		 	<form:hidden path="createdTime" id="assignmentCreatedTime" />
		 	<form:hidden path="wkFolder.id" id="assignmentWkFolderId" />
 	        <form:hidden path="user.username" id="assignmentuserusername" />
		</div>
		<div class="moudle_footer cf tc">
	        <div class="submit_link">
	        	<input class="btn bgb l mt10 poi  plr20 br3 wh" id="save" type="submit" value="发布" onclick="$('#assignmentStatus').val(1)">
	        	<input class="btn  bgc l ml30 mt10 poi plr20 br3" id="save" type="submit" value="保存草稿" onclick="$('#assignmentStatus').val(0)">
				<div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
                      	 取消
                   </div>
                   <input type="reset" name="reset"  />
	        </div>
	    </div>
   	</form:form>
   	</div>
   	</div>
</div>
<!--添加作业结束-->
    <script language="javascript" type="text/javascript">
    
    
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/skill/experimentSkill.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
    <!-- deleteWkFolder()方法 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/tcoursesite.js"></script>
</body>
</html>