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
	
	function am(s){
	if(isNaN(s)){
		if(s!="-"){
			$("#sequence").val(0);
			s=0;
		}
		
	}
	$("#sequence").val(Number(s));
	
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
						    <c:if test="${ empty tAssignmentAnswer.id }">
							    <div id="title">新建测验题目</div>
							</c:if>
							<c:if test="${!empty tAssignmentAnswer.id}">
							    <div id="title">编辑测验题目</div>
							</c:if>
						</div> 
						
						<form:form action="${pageContext.request.contextPath}/teaching/exam/saveExamAnswer" method="POST" modelAttribute="tAssignmentAnswer">
							<div class="new-classroom">
                            <table  class="tb"  id="my_show"> 
				                <thead>
				                    <tr> 
							    	<th><label style="margin-left: 16px">选项序号：</label></th>
							    	<th><form:input class="easyui-validatebox"  path="sequence" id="sequence" validType="length[0,12]" onkeyup="am(this.value);" required="true" /></th>
	                                </tr>
				                    <tr> 
							    	<th><label style="margin-left: 16px">选项内容：</label></th>
							    	<th><form:input path="text" id="text" required="true" /></th>
	                                </tr>
                                    <tr> 
								    <th><label style="margin-left: 16px">选项标签：</label></th>
									<th>
									    <select name="label" >
									       <option value ="A">A</option>
									       <option value ="B">B</option>
									       <option value ="C">C</option>
									       <option value ="D">D</option>
									       <option value ="E">E</option>
									       <option value ="F">F</option>
									       
									    </select>
									</th>
                                    </tr>
                                    <tr> 
								    <th><label style="margin-left: 16px">是否正确答案：</label></th>
									<th>
									<c:if test="${ empty tAssignmentAnswer.id}">
									   <select name="iscorrect" id="iscorrect" >
									   <option value="1">正确答案</option>
									   <option value="0">错误答案</option>
									   </select>
									</c:if>
									<c:if test="${!empty tAssignmentAnswer.id }">
									   <form:select path="iscorrect" id="iscorrect" class="chzn-select" >
									      <c:if test="${tAssignmentAnswer.iscorrect==1 }">
									       <form:option value="0" label="正确答案"/>
									       <form:option value="1" label="不正确答案"/>
									      </c:if>
									      <c:if test="${tAssignmentAnswer.iscorrect!=1 }">
									       <form:option value="0" label="不正确答案"/>
									       <form:option value="1" label="正确答案"/>
									      </c:if>
									   </form:select>
									</c:if>
									</th>
									</tr>
					 	        <form:hidden path="TAssignmentItem.id" />
							    <form:hidden path="id" />
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