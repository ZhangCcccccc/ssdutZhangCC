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
	function checkUser(obj){
		if($(obj).val().trim()!=""){
			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/teaching/coursesitetag/checkUser",
				data: {'username':$(obj).val().trim()},
				dataType:'json',
				success:function(data){
					if(!data){
						alert("该工号不存在，请查询后输入！");
						$(obj).val("");
					}
				}
			});
			
		}
	}
	function uploadDocument(){
		var sessionId=$("#sessionId").val();
		$('#searchFile').window({left:"100px", top:"600px"});
		$('#searchFile').window('open');
		$('#file_upload').uploadify({
			'formData':{code:${equipment.servBegin},id:${equipment.servId}},    //传值
			'swf': '${pageContext.request.contextPath}/swfupload/swfupload.swf',
			'cancelImg':'${pageContext.request.contextPath}/js/uploadify/cancel.png',
			//提交的controller和要在火狐下使用必须要加的id  
			'uploader':'${pageContext.request.contextPath}/equipment/fileUpload;jsessionid=<%=session.getId()%>' ,		
			buttonText:'选择文件',
			//当上传完成所有文件的时候关闭对话框并且转到显示界面
			onQueueComplete : function(stats) {//当队列中的所有文件全部完成上传时触发						    
				$('#searchFile').window('close');
				window.location.href='${pageContext.request.contextPath}/equipment/acceptance/editEquipment?flag=${flag}&servIdKey=${equipment.servId}';　　 
			}
		});
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
							<c:if test="${tAssignment.id ==null }">
								<div id="title">新建作业</div>
							</c:if>
							<c:if test="${tAssignment.id != null }">
								<div id="title">编辑作业</div>
							</c:if>
							
						</div> 
						
						<form:form action="${pageContext.request.contextPath}/teaching/assignment/saveAssignment?tCourseSiteId=${tCourseSite.id }&moduleType=${moduleType}&selectId=${selectId}" method="POST" modelAttribute="tAssignment" enctype="multipart/form-data">
							<div class="new-classroom">
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
								<fieldset style="width: 60%">
							    	<label style="margin-left: 16px">作业标题<font color="red">*</font>：</label>
							    	<form:input path="title" id="title" required="true" />
							    	<form:hidden path="id" id="id" />
							    	<form:hidden path="TAssignmentAnswerAssign.id" id="TAssignmentAnswerAssignId" />
							    	<form:hidden path="TAssignmentControl.id" id="TAssignmentControlId" />
								</fieldset>		
			
						  		<fieldset style="width: 60%">
								     <label style="margin-left: 16px">开始时间<font color="red">*</font>：</label>
									 <input type="text" name="startdateAssignment" id="TAssignmentControl_startdate" value="${startdate }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly"  required="true" class="Wdate w100p b1 br3 h30 lh30 mt5 plr5 "/>
							 	</fieldset>
							 	<fieldset style="width: 60%">
								     <label style="margin-left: 16px">截止时间<font color="red">*</font>：</label>
									 <input type="text" name="duedateAssignment" id="TAssignmentControl_duedate" value="${duedate }"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly"  required="true" class="Wdate w100p b1 br3 h30 lh30 mt5 plr5 rel"/>
							 	</fieldset>
							  	<fieldset style="display: none;">
								   	<div>
						     			<label>作业布置人：</label>
						     			<form:input path="TAssignmentAnswerAssign.user.username" placeholder="请输入工号/学号"/>
								 	</div>
							 	</fieldset>
						 		<fieldset style="width: 60%">
							    	<div>
						     			<label style="margin-left: 16px">上传附件：</label>
				     					<input type="file" name="file">
				     				</div>
				     			</fieldset>
							 	
							    <fieldset style="width: 60%">
							    	<div>
						     			<label style="margin-left: 16px">评分设置：</label><br>
						     			<form:radiobutton path="TAssignmentControl.toGradebook" value="yes" checked="checked"/>将作业添加到成绩簿 
						     			<form:radiobutton path="TAssignmentControl.toGradebook" value="no" />不要将作业加入到成绩簿中 <br>
						     			<form:radiobutton style="margin-left:225px;" path="TAssignmentControl.gradeToStudent" value="yes" checked="checked"/>将成绩公布给学生 
						     			<form:radiobutton path="TAssignmentControl.gradeToStudent" value="no" />不将成绩公布给学生<br>
						     			<form:radiobutton style="margin-left:225px;" path="TAssignmentControl.gradeToTotalGrade" value="yes" checked="checked"/>将成绩计入总成绩
						     			<form:radiobutton path="TAssignmentControl.gradeToTotalGrade" value="no" />不将成绩计入总成绩
							 		</div>
						 		</fieldset>
							    <fieldset style="width: 60%">
							    	<div>
						     			<label style="margin-left: 16px"><input type="checkbox" onchange="showTimeLimitDiv(this)" <c:if test="${tAssignment.TAssignmentControl.timelimit != 1 }">checked</c:if> />允许学生重复提交</label><br>
						     			<div id="timeLimit" <c:if test="${tAssignment.TAssignmentControl.timelimit == 1 }">style="display: none;"</c:if>>
						     				允许重复提交的次数
											<form:select path="TAssignmentControl.timelimit" id="timelimit" style="width:100px;" required="true" >
												<option value ="1" <c:if test="${tAssignment.TAssignmentControl.timelimit==1 }">selected</c:if>>1</option>
												<c:forEach begin="2" end="10"  varStatus="i" >
									               	<option value ="${i.current }" <c:if test="${tAssignment.TAssignmentControl.timelimit==i.current }">selected</c:if> >${i.current }</option>
										       	</c:forEach>
										       	<option value="0"  <c:if test="${tAssignment.TAssignmentControl.timelimit==0 }">selected</c:if>>无限制</option>
											</form:select>
							 			</div>
							 		</div>
						 		</fieldset>
						 		<fieldset style="width: 60%">
								   	<div>
						     			<label>学生提交作业的形式<font color="red">*</font>：</label>
						     			<form:select path="TAssignmentControl.submitType" style="width:250px;" required="required">
						     				<option value="1" <c:if test="${tAssignment.TAssignmentControl.submitType==1 }">selected</c:if>>输入文字或添加附件</option>
						     				<option value="2" <c:if test="${tAssignment.TAssignmentControl.submitType==2 }">selected</c:if>>直接输入文字</option>
						     				<option value="3" <c:if test="${tAssignment.TAssignmentControl.submitType==3 }">selected</c:if>>仅提交附件</option>
						     			</form:select>
								 	</div>
							 	</fieldset>
						 		<fieldset style="width: 60%">
								   	<div>
								   		<!--默认作业配置中的最高分为100分  -->
						     			<label>作业分值<font color="red">*</font>：</label>
						     			<form:input path="TAssignmentAnswerAssign.score" style="width:200px;" placeholder="请输入分值" class="easyui-numberbox" required="true"/>
								 	</div>
							 	</fieldset>
							 	<fieldset >
								   	<div>
						     			<label>作业要求：</label>
						     			<textarea id="content" name="content" cols="120">${tAssignment.content }</textarea>
								 	</div>
							 	</fieldset>
							 	<form:hidden path="type" value="assignment"/>
							 	
							 	<form:hidden path="status" id="status" value="0"/>
							 	<form:hidden path="createdTime" />
					 	        <form:hidden path="user.username" />
					 	        <form:hidden path="wkFolder.id" />
					 	        <form:hidden path="wkFolder.wkLesson.id" />
					 	        <form:hidden path="wkFolder.wkChapter.id" />
							</div>
							<div class="moudle_footer">
						        <div class="submit_link">
						        	<input class="btn" id="save" type="submit" value="发布" onclick="$('#status').val(1);return confirm('是否确认发布,发布后不可删除？')">
						        	<c:if test="${tAssignment.status != '1' }">
						        		<input class="btn" id="save" type="submit" value="保存草稿" onclick="$('#status').val(0)">
						        	</c:if>
									<input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
						        </div>
						    </div>
			
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
	
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
	
	function showTimeLimitDiv(obj){
		if($(obj).attr("checked")=="checked"){
			$("#timeLimit").show();
		}else{
			$("#timeLimit").hide();
			$("#timelimit").val(1);
		}
	}
</script>	
</body>


</html>