 <%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
 <jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
 
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
	
	function showTimeLimitDiv(obj){
		if($(obj).attr("checked")=="checked"){
			$("#timeLimit").show();
		}else{
			$("#timeLimit").hide();
			$("#timelimit").val(1);
		}
	}
</script>
</head>
<body>
	<div class="navigation">
		<div id="navigation">
			<ul>
				<li><a href="javascript:void(0)">教学管理</a></li>
				<li class="end"><a href="javascript:void(0)">练习与测验</a></li>
			</ul>
		</div>
	</div>
	<div class="right-content">
		<div id="TabbedPanels1" class="TabbedPanels">
		  	<div class="TabbedPanelsContentGroup">
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
						    <c:if test="${tAssignment.id==null }">
							    <div id="title">新建测验</div>
							</c:if>
							<c:if test="${tAssignment.id!=null }">
							    <div id="title">编辑测验</div>
							</c:if>
						</div> 
						
						<form:form action="${pageContext.request.contextPath}/teaching/exam/saveExam?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}" method="POST" modelAttribute="tAssignment">
							<div class="new-classroom">
                            <table  class="tb"  id="my_show"> 
				                <thead>
				                    <tr> 
							    	<th><label style="margin-left: 16px">测验标题<font color="red">*</font>：</label></th>
							    	<th><form:input path="title" id="title" required="true" /></th>
	                                </tr>
                                    <tr> 
							    	<th><label style="margin-left: 16px">开始时间<font color="red">*</font>：</label></th>
									<th><input type="text" name="TAssignmentControl.startdate" id="TAssignmentControl_startdate" onclick="new Calendar().show(this);" value="${startdate }" required="true" /></th>
                                    </tr>
                                    <tr> 
								    <th><label style="margin-left: 16px">截止时间<font color="red">*</font>：</label></th>
									<th><input type="text" name="TAssignmentControl.duedate" id="TAssignmentControl_duedate" onclick="new Calendar().show(this);" value="${duedate }" required="true" /></th>
                                    </tr>
                                    <tr style="display: none;">
                                    	<th><label>作业布置人：</label></th>
						     			<th>
						     				<form:input path="TAssignmentAnswerAssign.user.username" placeholder="请输入工号/学号"/>
                                    	</th>
                                    </tr>
                                    <%--<tr>
						     		<th><label style="margin-left: 16px">上传附件：</label></th>
						     		<th><a href="#" onclick="uploadDocument(&quot;11&quot;,&quot;1937&quot;)"><font color=red><input type="button" value="上传（假功能）"></font></a></th>							 	</fieldset>
                                    </tr>
                                    --%><tr>
						     		<th><label style="margin-left: 16px">评分设置：</label><br></th>
						     		<th><form:radiobutton path="TAssignmentControl.toGradebook" value="yes" checked="checked" />将测验添加到成绩簿 
						     			<form:radiobutton path="TAssignmentControl.toGradebook" value="no"/>不要将测验加入到成绩簿中<br>
						     			<form:radiobutton path="TAssignmentControl.gradeToStudent" value="yes" checked="checked"/>将成绩公布给学生 
						     			<form:radiobutton path="TAssignmentControl.gradeToStudent" value="no" />不将成绩公布给学生<br>
						     			<form:radiobutton path="TAssignmentControl.gradeToTotalGrade" value="yes" checked="checked"/>将成绩计入总成绩
						     			<form:radiobutton path="TAssignmentControl.gradeToTotalGrade" value="no" />不将成绩计入总成绩
                                    </th>
                                    </tr>
                                    <tr>
						     		<th><label style="margin-left: 16px"><input type="checkbox" onchange="showTimeLimitDiv(this)" <c:if test="${tAssignment.TAssignmentControl.timelimit!=null && tAssignment.TAssignmentControl.timelimit != 1 }">checked</c:if> />允许学生重复提交</label><br></th>
					     			<th><div id="timeLimit" <c:if test="${tAssignment.TAssignmentControl.timelimit==null || tAssignment.TAssignmentControl.timelimit == 1 }">style="display: none;"</c:if>>
						     				允许重复提交的次数
											<form:select path="TAssignmentControl.timelimit" id="timelimit" style="width:100px;" required="true" >
												<option value ="1" selected>1</option>
												<c:forEach begin="2" end="10"  varStatus="i" >
									               	<option value ="${i.current }" <c:if test="${tAssignment.TAssignmentControl.timelimit == i.current}">selected</c:if>>${i.current }</option>
										       	</c:forEach>
										       	<option value="0" <c:if test="${tAssignment.TAssignmentControl.timelimit == 0}">selected</c:if>>无限制</option>
											</form:select>
							 			</div>
                                    </th>
                                    </tr>
                                    <tr>
						     		<th><label style="margin-left: 16px">测试分值<font color="red">*</font>：</label><br></th>
						     		<th><form:input path="TAssignmentAnswerAssign.score" style="width:200px;" placeholder="请输入分值" class="easyui-numberbox" required="true"/>
                                    </th>
                                    </tr>
                                    <tr>
                                        <th><label style="margin-left: 16px">测试说明：</label><br></th>
                                        <th>
                                        <textarea name="content" style="width: 222px;height: 80px" >${tAssignment.content}</textarea>
								 	    </th>
								 	</tr>
								 	<%-- <tr>
								 	    <th>
								 	    	<form:input path="content" style="width: 222px;height: 80px" placeholder="请输入说明" class="easyui-textbox"/>
								 	    </th>	
							 	    </tr> --%>
						 	    </thead>
							</table>
							 	  
						 	<form:hidden path="type" value="exam"/>
						 	<form:hidden path="status" value="0"/>
						 	<form:hidden path="createdTime" />
				 	        <form:hidden path="user.username" />
						    <form:hidden path="id" />
						    <form:hidden path="TAssignmentControl.id" />
						    <form:hidden path="TAssignmentAnswerAssign.id" />
							</div>
							<div class="moudle_footer">
						        <div class="submit_link">
						        	<input class="btn" id="save" type="submit" value="确定">
									<input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
						        </div>
						    </div>
			
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
	var editor = new UE.ui.Editor({initialFrameWidth:700});
	editor.render("content");
</script>	
</body>


</html>