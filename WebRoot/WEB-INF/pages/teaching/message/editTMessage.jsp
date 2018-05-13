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
</script>
</head>
<body>
	<div class="navigation">
		<div id="navigation">
			<ul>
				<li><a href="javascript:void(0)">教学管理</a></li>
				<li><a href="javascript:void(0)">通知</a></li>
				<li class="end">
					<mytag:JspSecurity realm="add" menu="message">
						<a href="javascript:void(0)">新建通知</a>
					</mytag:JspSecurity>
				</li>
				
			</ul>
		</div>
	</div>
	<div class="right-content">
		<div id="TabbedPanels1" class="TabbedPanels">
		  	<div class="TabbedPanelsContentGroup">
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">新建通知</div>
						</div> 
						<form:form action="${pageContext.request.contextPath}/teaching/message/saveTMessage" method="POST" modelAttribute="tMessage">
							<div class="new-classroom">
								<table>
									<tr>
										<td>通知标题:</td>
										<td>
											<input type="hidden" name="id" value="${tMessage.id }"/>
											<input type="text" name="title" value="${tMessage.title }"/>
										</td>
										<td>通知概要:</td>
										<td colspan="3">
											<input type="text" name="summary" value="${tMessage.summary }"/>
										</td>
									</tr>
									<tr>
										<td>发布时间:</td>
										<td><input type="text" name="releaseTime"  onclick="new Calendar().show(this);" value="<fmt:formatDate value="${tMessage.releaseTime.time }" pattern="yyyy-MM-dd"></fmt:formatDate>"/></td>
										<td>发布人:</td>
										<td><form:input type="hidden"  value="${user.username }" path="user.username" required="true"/>
										${user.cname }</td>
										</tr>
										<tr>
										<td>所属网站:</td>
										<td>
											<input type="hidden" name="TCourseSite.id" value="${tMessage.TCourseSite.id }"/>
											<input type="text" value="${tMessage.TCourseSite.title }" readonly="readonly"/></td>
									</tr>
								</table>
							   	<div>
						     		<div style="margin-top: 5px;margin-bottom: 5px;">通知内容：</div>
						     		<textarea id="content" name="content" >${tMessage.content }</textarea>
							 	</div>
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
	var editor = new UE.ui.Editor({initialFrameWidth:1100});
	editor.render("content");	 
</script>	
</body>


</html>