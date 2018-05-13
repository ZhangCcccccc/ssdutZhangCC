 <%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
 <jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
 <jsp:useBean id="now" class="java.util.Date" />  
 <html >  
<head>
<meta name="decorator" content="iframe"/>
<!-- 富文本的css -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css"/> 

<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/easyui.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
function exportSelect(){
 	document.form.action="exportExcelSelectUser";
	document.form.submit();
}

//正则表达式规范得分填写
function changeNumber(obj,score){
	var price=$(obj).val();
	price = price.replace(/[^\d.]/g,"");
    //必须保证第一个为数字而不是.
    price = price.replace(/^\./g,"");
    //保证只有出现一个.而没有多个.
    price = price.replace(/\.{2,}/g,".");
    //保证.只出现一次，而不能出现两次以上
    price = price.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
    if(Number(price)>score){
    	alert("打分不得高于分值，分值为100！");
    	$(obj).val(score);
    	$("#finalScoreFont").html(score);
    	return;
    }
    $(obj).val(price);
}

function grade(obj){
	var assignGradeId = $(obj).parent().parent().find("#assignGradeId").val(); 
	var comments = $(obj).parent().parent().find("#comments").val();
	var finalScore = $(obj).parent().parent().find("#finalScore").val();
	$.ajax({
		url:'${pageContext.request.contextPath}/teaching/assignmentGrading/grade?assignGradeId='+assignGradeId+'&comments='+comments+'&finalScore='+finalScore,
		type:'post',
		async:false,  // 设置同步方式
        cache:false,
		success:function(data){
			if(finalScore!=""){
				$("#isGraded").val("已评分");
			}
			$("span").html('<fmt:formatDate pattern="yyyy-MM-dd" value="${now }" type="both"/>');
			$("#finalScoreFont").html(finalScore);
		}
	}); 
}

function downloadFile(id){
	var input = "<input type='hidden' name='assignId' value='"+ id +"' />";
	var html = "<form action='${pageContext.request.contextPath}/teaching/assignment/downloadAssignment' method='post'>"+input+"</form>"; 
	jQuery(html).appendTo("body").submit().remove();
	/**
	$("#assignId").val(id);
	document.form.submit();
	*/
}

function downloadOneFile(id){
	var input = "<input type='hidden' name='id' value='"+ id +"' />";
	var html = "<form action='${pageContext.request.contextPath}/teaching/assignment/downloadFileForStudent' method='post'>"+input+"</form>"; 
	jQuery(html).appendTo("body").submit().remove();
	/**
	$("#assignId").val(id);
	document.form.submit();
	*/
}
	
</script>
</head>
<body>
	
	<div class="right-content">
		<div id="TabbedPanels1" class="TabbedPanels">
		  	<div class="TabbedPanelsContentGroup">
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">学生提交作业</div>
							<mytag:JspSecurity realm="check" menu="tAssignment">
								<a class="btn btn-new" href="${pageContext.request.contextPath}/teaching/assignment/assignmentGradingList?assignmentId=${tAssignment.id  }&flag=0" onclick="return checkSubmitNumber()">查看历史提交记录</a>
							</mytag:JspSecurity>
						</div> 
						<c:if test="${isGraded!=null }">
							<font color="red">该作业已被批改，无法再提交！</font>
						</c:if>
						<c:if test="${isGraded==null&&now>tAssignment.TAssignmentControl.duedate.time }">
							<font color="red">已超过截止时间，无法再提交！</font>
						</c:if>
						<form:form id="myForm" action="${pageContext.request.contextPath}/teaching/assignment/saveTAssignmentGrade" method="POST" modelAttribute="tAssignmentGrade" onsubmit="return checkForm()" enctype="multipart/form-data">
							<form:hidden path="accessmentgradingId" />
							<div class="new-classroom">
								<table style="width: 60%;margin-top: 10px;">
									<tr>
										<td align="center">作业标题：</td>
										<td align="left">${tAssignment.title }</td>
										<td align="center">最高分值：</td>
										<td align="left">${tAssignment.TAssignmentAnswerAssign.score }</td>
										
									</tr>
									<tr>
										<td align="center">开始时间：</td>
										<td align="left">
											<fmt:formatDate pattern="yyyy-MM-dd" value="${tAssignment.TAssignmentControl.startdate.time}" type="both"/>
										</td>
										<td align="center">截止时间：</td>
										<td align="left">
											<fmt:formatDate pattern="yyyy-MM-dd" value="${tAssignment.TAssignmentControl.duedate.time}" type="both"/>
										</td>
									</tr>
									<tr>
										<td align="center">作业要求：</td>
										<td align="left" colspan="3">
											${tAssignment.content }
										</td>
									</tr>
									<tr>
										<td align="center">状态：</td>
										<td align="left">
											<c:if test="${now>= tAssignment.TAssignmentControl.startdate.time&&now< tAssignment.TAssignmentControl.duedate.time}">
										                      开始
									       	</c:if>
									        <c:if test="${now<tAssignment.TAssignmentControl.startdate.time}">
											 	未到期
											</c:if>
											<c:if test="${now>tAssignment.TAssignmentControl.duedate.time}">
												已过期
											</c:if>
										</td>
										<td align="center">已提交次数/提交限制：</td>
										<td align="left">
											<c:if test="${tAssignment.TAssignmentGradings.size() == 0 }">
												<b><span style="color: red">未提交</span>
												/
												${tAssignment.TAssignmentControl.timelimit==0?'无限制':tAssignment.TAssignmentControl.timelimit }</b>
											</c:if>
											<c:if test="${tAssignment.TAssignmentGradings.size() != 0 }">
												<b><span style="color: green">${tAssignment.TAssignmentGradings.size() }</span>
												/
												${tAssignment.TAssignmentControl.timelimit==0?'无限制':tAssignment.TAssignmentControl.timelimit }</b>
											</c:if>
										</td>
									</tr>
								</table>
	<br><br><br>
	<table  id="my_show"> 
		<thead>
		    <tr>                   
		        <th>作业标题</th>
		        <th>学生姓名</th>
		        <th>成绩</th>
		        <th>评语</th>
		        <th>学生提交时间</th>
		        <th>教师批改时间</th>
		        <th>最终成绩</th>
		        <th>提交状态</th>
		        <th>操作</th>
		    </tr>
		</thead>
		<tbody>
			<c:forEach items="${tAssignmentGradings}" var="current"  varStatus="i">
		   		<tr>
					<td>${current.TAssignment.title}<br>
				       	<p id="isGraded">
					       	<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag!=0}">
					       		<%--<c:if test="${current.userByGradeBy == null }">
					       			<a href="${pageContext.request.contextPath}/teaching/assignment/findTAssignmentGradeToMark?assignGradeId=${current.accessmentgradingId }&flag=${flag }">评分</a>
					       		</c:if>
					       		--%><c:if test="${current.userByGradeBy != null }">
					       			已评分
					       		</c:if>
							</c:if> 
					       	<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag==0}">
					       	</c:if>
				       	</p> 
			       	</td>
	       			<td>${current.userByStudent.cname }</td>
			       	<td>
			       		<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag!=0}">
				       		<c:if test="${current.finalScore != null }">
				       			<font id="finalScoreFont">${current.finalScore }</font>
				       		</c:if>
				       		<c:if test="${current.finalScore == null }">
				       			<font id="finalScoreFont">尚未批改</font>
				       		</c:if>
				       		/${current.TAssignment.TAssignmentAnswerAssign.score }
			       		</c:if>
			       		<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag==0}">
			       			<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'yes' }">
			       				<c:if test="${current.finalScore != null }">
					       			<font id="finalScoreFont">${current.finalScore }</font>
					       		</c:if>
					       		<c:if test="${current.finalScore == null }">
					       			<font id="finalScoreFont">尚未批改</font>
					       		</c:if>
								/${current.TAssignment.TAssignmentAnswerAssign.score }
							</c:if>
							<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'no' }">
								该作业成绩不公布
							</c:if>
			       		</c:if>
			       		
					</td>
					<td>
						<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag!=0}">
							<input type="text" id="comments" value="${current.comments }" onchange="grade(this)"/>
						</c:if>
						<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag!=0}">
							<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'yes' }"><%--
								${current.comments }
							--%></c:if>
							<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'no' }">
								该作业成绩不公布
							</c:if>
						</c:if>
					</td>
					<td>
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${current.submitdate.time }" type="both"/>
					</td>
					<td>
						<span><fmt:formatDate pattern="yyyy-MM-dd" value="${current.gradeTime.time }" type="both"/></span>
					</td>
					<td>
						<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag!=0}">
							<input type="text" id="finalScore" style="width: 45px;" value="${current.finalScore }" onchange="grade(this)" oninput="changeNumber(this,${current.TAssignment.TAssignmentAnswerAssign.score} )"/>
							<input type="hidden" id="assignGradeId" value="${current.accessmentgradingId }"/>
						</c:if>
						<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag==0}">
				        	<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'yes' }">
								${current.finalScore }
							</c:if>
							<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'no' }">
								该作业成绩不公布
							</c:if> 
				       	</c:if>
					</td>
					<td>
						<c:if test="${current.islate==0 }"><font color="green">正常提交</font></c:if>
						<c:if test="${current.islate==1 }"><font color="red">迟交</font></c:if>
					</td>
					<td>
						<mytag:JspSecurity realm="check" menu="tAssignment">
						    <a href="javascript:void(0)" onclick="downloadOneFile(${current.accessmentgradingId})">下载</a>
							<a href="${pageContext.request.contextPath}/teaching/assignment/findTAssignmentGradeToMark?assignGradeId=${current.accessmentgradingId }&flag=${flag }">查看作业详情</a>
						</mytag:JspSecurity>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	
	</table>
								
								
								
								
					 	        <form:hidden path="submitTime" id="submitTime"/>
					 	        <form:hidden path="submitdate"/>
					 	        <form:hidden path="TAssignment.id"/>
					 	        <form:hidden path="userByStudent.username"/>
							</div>
							<c:if test="${isGrade==null && now <= tAssignment.TAssignmentControl.duedate.time}">
								<div class="moudle_footer">
							        <div class="submit_link"
										<input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
							        </div>
							    </div>
							</c:if>
			
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select': {search_contains : true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
   
</script>
<!-- 下拉框的js -->
<script type="text/javascript">
	//生成html编辑栏，设置宽度为888px
	var editor = new UE.ui.Editor({initialFrameWidth:800});
	editor.render("content");	 
	
</script>	
</body>

</html>