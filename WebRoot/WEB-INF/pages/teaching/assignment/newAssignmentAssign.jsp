 <%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
 <jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
 <jsp:useBean id="now" class="java.util.Date" />  
 <html >  
<head>
<meta name="decorator" content="iframe"/>
<!-- 富文本的css -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.all.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ueditor/themes/default/css/ueditor.css"/> 

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
						</div> 
						
						<form:form action="${pageContext.request.contextPath}/teaching/assignment/saveAssignment?flagId=${flagId}" method="POST" modelAttribute="tAssignment">
							<div class="new-classroom">
								<fieldset style="width: 60%">
							    	<label style="margin-left: 16px">作业标题：</label>
							    	<label style="margin-left: 16px">${tAssignment.title }</label>
							    	<label style="margin-left: 16px">开始时间：</label>
							    	<label style="margin-left: 16px">
							    	<fmt:formatDate pattern="yyyy-MM-dd" value="${tAssignment.TAssignmentControl.startdate.time}" type="both"/></label>
      								<label style="margin-left: 16px">截止时间：</label>
							    	<label style="margin-left: 16px"><fmt:formatDate pattern="yyyy-MM-dd" value="${tAssignment.TAssignmentControl.startdate.time}" type="both"/></label>
									<label style="margin-left: 16px">作业要求：</label>
							    	<label style="margin-left: 16px">${tAssignment.content }</label>
									<label style="margin-left: 16px">评分方式：</label>
							    	<label style="margin-left: 16px">分数最高：${tAssignment.TAssignmentAnswerAssign.score }</label>
									<label style="margin-left: 16px">状态：</label>
							    	<label style="margin-left: 16px">
							    	<c:if test="${now>= tAssignment.TAssignmentControl.startdate.time&&now< tAssignment.TAssignmentControl.duedate.time}">
								                      开始
								       </c:if>
								        <c:if test="${now<tAssignment.TAssignmentControl.startdate.time}">
								                      未到期
								       </c:if>
								       <c:if test="${now>tAssignment.TAssignmentControl.duedate.time}">
								                     已过期
								       </c:if>
                                    </label>
																    	
								</fieldset>		

							  
							
							   	<fieldset >
							   	           &nbsp;<b>作业提交：</b>
								   	<div>
						     			<label>作业提交：</label>
						     			<textarea id="TAssignmentAnswerAssign.content" name="TAssignmentAnswerAssign.content" cols="120"></textarea>
								 	</div>
							 	</fieldset>
							 	<form:hidden path="type" value="assignment"/>
							 	<form:hidden path="status" value="0"/>
							 	<form:hidden path="createdTime" />
					 	        <form:hidden path="user.username" />
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
	var editor = new UE.ui.Editor({initialFrameWidth:800});
	editor.render("TAssignmentAnswerAssign.content");	 
</script>	
</body>


</html>