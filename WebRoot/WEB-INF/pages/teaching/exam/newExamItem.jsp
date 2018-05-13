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
	
	function am(s,countScore){
		if(isNaN(s)){
			if(s!="-"){
				$("#score").val(0);
				s=0;
			}
			
		}
		var totalScore = "${tAssignmentItem.TAssignmentSection.TAssignment.TAssignmentAnswerAssign.score}";
		totalScore = totalScore - countScore;
		if(Number(s)>totalScore){
			alert("总分值溢出!");
			s = totalScore;
		}
		$("#score").val(Number(s));
		
	}
	
	function checkItemtype(obj){
		
		if($(obj).val()==""){
			$("#typeTip").show();
			$("#scoreAndText").hide();
			$("#answer").html("");
		}else{
			$("#typeTip").hide();
			$("#scoreAndText").show();
			$("#description").val("");
			var html;
			<!--多选题 -->
			if($(obj).val()=="1"){
				html = "<table>"+
							"<tr>"+
								"<td>"+
									"正确答案<br>"+
									"<input type='hidden' name='answerLabelChoice' value='A'>"+
									"<input type='checkbox' name='answerLabel' value='A'>A"+
								"</td>"+
								"<td>"+
									"<textarea name='answerText' style='resize: none;width: 300px;'></textarea>"+
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td>"+
									"正确答案<br>"+
									"<input type='hidden' name='answerLabelChoice' value='B'>"+
									"<input type='checkbox' name='answerLabel' value='B'>B"+
								"</td>"+
								"<td>"+
									"<textarea name='answerText' style='resize: none;width: 300px;'></textarea>"+ 
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td>"+
									"正确答案<br>"+
									"<input type='hidden' name='answerLabelChoice' value='C'>"+
									"<input type='checkbox' name='answerLabel' value='C'>C"+
								"</td>"+
								"<td>"+
									"<textarea name='answerText' style='resize: none;width: 300px;'></textarea>"+ 
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td>"+
									"正确答案<br>"+
									"<input type='hidden' name='answerLabelChoice' value='D'>"+
									"<input type='checkbox' name='answerLabel' value='D'>D"+
								"</td>"+
								"<td>"+
									"<textarea name='answerText' style='resize: none;width: 300px;'></textarea>"+ 
								"</td>"+
							"</tr>"+
						"</table>";
			}
			<!--对错题 -->
			if($(obj).val()=="2"){
				html = "<table>"+
							"<tr>"+
								"<td>"+
									"<input type='hidden' name='answerLabelChoice' value='0'>"+
									"<input type='radio' name='answerLabel' value='0'>&nbsp;&nbsp;"+
									"对<input type='hidden' name='answerText' value='对'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
									"<input type='hidden' name='answerLabelChoice' value='1'>"+
									"<input type='radio' name='answerLabel' value='1'>&nbsp;&nbsp;"+
									"错<input type='hidden' name='answerText' value='错'>"+
								"</td>"+
							"</tr>"+
						"</table>";
			}
			<!--单选题 -->
			if($(obj).val()=="4"){
				html = "<table>"+
							"<tr>"+
								"<td>"+
									"正确答案<br>"+
									"<input type='hidden' name='answerLabelChoice' value='A'>"+
									"<input type='radio' name='answerLabel' value='A'>A"+
								"</td>"+
								"<td>"+
									"<textarea name='answerText' style='resize: none;width: 300px;'></textarea>"+
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td>"+
									"正确答案<br>"+
									"<input type='hidden' name='answerLabelChoice' value='B'>"+
									"<input type='radio' name='answerLabel' value='B'>B"+
								"</td>"+
								"<td>"+
									"<textarea name='answerText' style='resize: none;width: 300px;'></textarea>"+ 
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td>"+
									"正确答案<br>"+
									"<input type='hidden' name='answerLabelChoice' value='C'>"+
									"<input type='radio' name='answerLabel' value='C'>C"+
								"</td>"+
								"<td>"+
									"<textarea name='answerText' style='resize: none;width: 300px;'></textarea>"+ 
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td>"+
									"正确答案<br>"+
									"<input type='hidden' name='answerLabelChoice' value='D'>"+
									"<input type='radio' name='answerLabel' value='D'>D"+
								"</td>"+
								"<td>"+
									"<textarea name='answerText' style='resize: none;width: 300px;'></textarea>"+ 
								"</td>"+
							"</tr>"+
						"</table>";
			}
			<!--填空题 -->
			if($(obj).val()=="8"){
				
				html = "<b>设置答案</b><br>"+
						"填空题答案用大括号“{}”标记。<br>"+
						"例如：Roses are {red} and violets are {blue}.<br><br>"+
						"使用“|”分隔同义词，<br>"+
						"例如： {They are|They're} very happy.<br><br>"+
						"使用星号(*)来表示通配符。<br>"+
						"例如： It's raining {c*} and {d*s}.<br><br>";
				//alert(1)		
			}
			$("#answer").html(html);
		}
	}
	
	function checkRequired(){
		var type = $("select").val();
		<!--多选题 -->
		if(type==1){
			var count = 0;
			$("input:checked").each(function(){
				count += 1;
				
			})
			if(count<2){
				alert("多选题正确选项不能少于两个！");
				return false;
			}
			count = false;
			$("textarea").each(function(){
				if($(this).val().trim()==""){
					count = true;
				}
			})
			if(count){
				alert("答案内容不能为空！");
				return false;
			}
		}
		<!--对错题 -->
		if(type==2){
			var count = 0;
			$("input:checked").each(function(){
				count += 1;
				
			})
			if(count==0){
				alert("请选择正确答案！");
				return false;
			}
		}
		<!--单选题 -->
		if(type==4){
			var count = 0;
			$("input:checked").each(function(){
				count += 1;
				
			})
			if(count==0){
				alert("请选择正确答案！");
				return false;
			}
			count = false;
			$("textarea").each(function(){
				if($(this).val().trim()==""){
					count = true;
				}
			})
			if(count){
				alert("答案内容不能为空！");
				return false;
			}
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
						    <c:if test="${tAssignmentItem.getId()==null }">
							    <div id="title">新建测验题目</div>
							</c:if>
							<c:if test="${tAssignmentItem.getId()!=null }">
							    <div id="title">编辑测验题目</div>
							</c:if>
						</div> 
						<c:set var="countScore" value="0"></c:set>
	                    <c:forEach items="${examInfo.TAssignmentSections}" var="current"  varStatus="i">
	                        <c:forEach items="${current.TAssignmentItems}" var="current1"  varStatus="k">
	                                 <c:set var="countScore" value="${countScore+current1.score }"></c:set>
	                        </c:forEach>
	                    </c:forEach>&nbsp;${countScore}/${examInfo.TAssignmentAnswerAssign.score}
						<form:form action="${pageContext.request.contextPath}/teaching/exam/saveExamItemForOne?tCourseSiteId=${tCourseSiteId}&moduleType=${moduleType}&selectId=${selectId}"  method="POST" modelAttribute="tAssignmentItem" onsubmit="return checkRequired()">
							<div class="new-classroom">
	                            <table  class="tb"  id="my_show"> 
					                <thead>
					                	<tr> 
									    	<th><label style="margin-left: 16px">所属大题：</label></th>
									    	<th><form:input path="TAssignmentSection.description" id="TAssignmentSection" readonly="true"/></th>
		                                </tr>
					                	<tr> 
						                	<th><label style="margin-left: 16px">题目类型：</label></th>
											<th>
											    <select name="type"  cssStyle="width:300px;" required="true" onchange="checkItemtype(this)">
											       <option value="">选择问题类型</option>
											       <option value ="1">多选题</option>
											       <option value ="2">对错题</option>
											       <option value ="4">单选题</option>
											       <option value ="8">填空题</option>
											       <%--<option value ="9">匹配题</option>
											    --%></select>
											    <p id="typeTip" style="color: red;display: none;">*请选择问题类型</p>
											</th>
										</tr>
	                                    
									 	<form:hidden path="status" value="0"/>
							 	        <form:hidden path="user.username" />
							 	        <form:hidden path="createdTime" />
							 	        <form:hidden path="sequence" id="sequence" required="true" value="${tAssignmentItem.TAssignmentSection.TAssignmentItems.size()+1 }" class="easyui-numberbox"/>
							 	        <form:hidden path="TAssignmentSection.id" />
									    <form:hidden path="id" />
								    </thead>
								</table>
								<div id="scoreAndText" style="display: none;">
									<label style="margin-left: 16px">分值：</label>
									<form:input  class="easyui-validatebox"  path="score" id="score" validType="length[0,12]" oninput="am(this.value,${countScore});" required="true" />
								</div><br>
								<div>
									<label style="margin-left: 16px">题干：</label>
									<form:textarea path="description" style="width:450px;height:80px;" id="description" required="true" />
								</div><br>
								<div id="answer">
									
								</div>
								<!-- 多选题 type=1-->
								<%--<div id="answer" name="answer">
									<table>
										<tr>
											<td>
												正确答案<br>
												<input type="checkbox" name="answerLabel" value="A">A
											</td>
											<td>
												<textarea name="answerText" style="resize: none;width: 300px;"></textarea> 
											</td>
										</tr>
										<tr>
											<td>
												正确答案<br>
												<input type="checkbox" name="answerLabel" value="B">B
											</td>
											<td>
												<textarea name="answerText" style="resize: none;width: 300px;"></textarea> 
											</td>
										</tr>
										<tr>
											<td>
												正确答案<br>
												<input type="checkbox" name="answerLabel" value="C">C
											</td>
											<td>
												<textarea name="answerText" style="resize: none;width: 300px;"></textarea> 
											</td>
										</tr>
										<tr>
											<td>
												正确答案<br>
												<input type="checkbox" name="answerLabel" value="D">D
											</td>
											<td>
												<textarea name="answerText" style="resize: none;width: 300px;"></textarea> 
											</td>
										</tr>
									</table>
								</div>
								<!-- 对错题 type=2-->
								<div id="answer" name="answer">
									<table>
										<tr>
											<td>
												正确答案<br>
												<input type="hidden" name="answerLabel" value="1">
											</td>
											<td>
												<textarea name="answerText" style="resize: none;width: 300px;"></textarea> 
											</td>
										</tr>
										<tr>
											<td>
												错误答案<br>
												<input type="hidden" name="answerLabel" value="0">
											</td>
											<td>
												<textarea name="answerText" style="resize: none;width: 300px;"></textarea> 
											</td>
										</tr>
									</table>
								</div>
								<!-- 单选题 type=4-->
								<div id="answer" name="answer">
									<table>
										<tr>
											<td>
												正确答案<br>
												<input type="radio" name="answerLabel" value="A">A
											</td>
											<td>
												<textarea name="answerText" style="resize: none;width: 300px;"></textarea> 
											</td>
										</tr>
										<tr>
											<td>
												正确答案<br>
												<input type="radio" name="answerLabel" value="B">B
											</td>
											<td>
												<textarea name="answerText" style="resize: none;width: 300px;"></textarea> 
											</td>
										</tr>
										<tr>
											<td>
												正确答案<br>
												<input type="radio" name="answerLabel" value="C">C
											</td>
											<td>
												<textarea name="answerText" style="resize: none;width: 300px;"></textarea> 
											</td>
										</tr>
										<tr>
											<td>
												正确答案<br>
												<input type="radio" name="answerLabel" value="D">D
											</td>
											<td>
												<textarea name="answerText" style="resize: none;width: 300px;"></textarea> 
											</td>
										</tr>
									</table>
								</div>
								
								--%><div class="moudle_footer">
							        <div class="submit_link">
							        	<input class="btn" id="save" type="submit"  value="确定">
										<input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
							        </div>
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