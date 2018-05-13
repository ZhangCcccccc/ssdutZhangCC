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
	
	/*
	*编辑题目
	*/
	function editTAssignmentItem(){
	var sessionId = $("#sessionId").val();
	var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/teaching/exam/examInfo?examId=62&selected_labCenter=-1" style="width:100%;height:100%;"></iframe>'
	$('#editTAssignmentItem').html(con);
	//获取当前屏幕的绝对位置
	var topPos = window.pageYOffset;
	//使得弹出框在屏幕顶端可见
	$('#editTAssignmentItem').window({left:"px", top:topPos+"px"});
	$('#editTAssignmentItem').window('open');
	}

	function checkItemtype(obj){
		
		if($(obj).val()==""){
			$("#typeTip").show();
			$("#scoreAndText").children().val("");
			$("#scoreAndText").hide();
			$("#answer").html("");
		}else{
			var html;
			$("#typeTip").hide();
			$("#scoreAndText").show();
			$("#description").val("");
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
				//html = "<textarea name='description' style='resize:none;width:260px;height:60px;' id='description' required='true' ></textarea>";
				//$("#description").remove();
				//$("#tigan").after(html);
				html = "<b>设置答案说明</b><br>"+
						"填空题答案用大括号“{}”标记。<br>"+
						"例如：Roses are {red} and violets are {blue}. 则正确答案为“red”和“blue”。<br><br>"+
						"使用“|”分隔同义词，<br>"+
						"例如： {They are|They're} very happy. 则正确答案为“They are”或“They are”。<br><br>"+
						"使用星号(*)来表示通配符。<br>"+
						"例如： It's raining {c*} and {d*s}. 则形如“c...”和“d...s”的答案均为正确答案。<br><br>";
				//alert(1)		
			}
			$("#answer").html(html);
		}
	}
	
	function checkRequired(){
		var type = $("select").val();
		<!-- 多选题 -->
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
		<!-- 对错题 -->
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
		<!-- 单选题 -->
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
		<!-- 填空题 -->
		if(type==8){
			var str =$("#description").val().trim();	
			<%--
			function m1(str){  
				if(/[{][^{}]*[}]/.test(str)){  
				    str = str.replace(/[{][^{}]*[}]/g, '');  
				    return m1(str);  
				}else{  
				    return str;  
				}  
			} 
			function run(str){  
				str = m1(str);  
				return !/[{}]/.test(str);  
			} 
			--%>
			if(str.length==0){
				alert("请输入题干！");
				return false;
			}
			if(str.indexOf("{")==-1||str.indexOf("}")==-1){
				alert("请设置至少一个答案");
				return false;
			}
			var strs1 = str.split("{");
			var strs2 = str.split("}");
			if(strs1.length!=strs2.length){
				alert("请注意括号要配对使用");
				return false;
			}
			for(var i=1;i<strs1.length;i++){
				
				if(strs1[i].indexOf("}")==-1){
					alert("请注意大括号不要嵌套使用");
					return false;
					break;
					
				}
				if(strs1[i].substring(0,strs1[i].indexOf("}")).trim()==""){
					alert("答案不能为空");
					return false;
				}
			}
		}
		
	}
</script>
</head>
<body>
	
	<div class="right-content">
		<div id="TabbedPanels1" class="TabbedPanels">
		  	<div class="TabbedPanelsContentGroup">
		  	    <!--题目抬头  -->
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">编辑测验信息</div>
							<%--<a class="btn btn-new" href="${pageContext.request.contextPath}/teaching/exam/findExamItemById?id=${ItemInfo.id }">编辑基本信息</a>
						
						--%></div> 
						<form:form action="${pageContext.request.contextPath}/teaching/exam/saveExamItemForOne?tCourseSiteId=${tCourseSiteId}&moduleType=${moduleType}&selectId=${selectId}" method="POST" modelAttribute="tAssignmentItem" onsubmit="return checkRequired()">
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
										       <option value ="1" <c:if test="${tAssignmentItem.type==1 }">selected</c:if>>多选题</option>
										       <option value ="2" <c:if test="${tAssignmentItem.type==2 }">selected</c:if>>对错题</option>
										       <option value ="4" <c:if test="${tAssignmentItem.type==4 }">selected</c:if>>单选题</option>
										       <option value ="8" <c:if test="${tAssignmentItem.type==8 }">selected</c:if>>填空题</option>
										       <%--<option value ="9" <c:if test="${tAssignmentItem.type==9 }">selected</c:if>>匹配题</option>
										    --%></select>
										    <p id="typeTip" style="color: red;display: none;">*请选择问题类型</p>
										</th>
									</tr>
                                    
								 	<form:hidden path="status" value="0"/>
						 	        <form:hidden path="user.username" />
						 	        <form:hidden path="createdTime" />
						 	        <form:hidden path="sequence" id="sequence" required="true"  class="easyui-numberbox"/>
						 	        <form:hidden path="TAssignmentSection.id" />
								    <form:hidden path="id" />
								</thead>
							</table>
							<div id="scoreAndText">
								<div>
									<label style="margin-left: 16px">分值：</label>
									<form:input  class="easyui-validatebox"  path="score" id="score" validType="length[0,12]" oninput="am(this.value);" required="true" />
								</div><br>
								<div>
									<label id="tigan" style="margin-left: 16px">题干：</label>
									<form:textarea path="description" style="width:450px;height:80px;" id="description" required="true" />
								</div><br>
							</div>
							<div id="answer">
								<!-- 多选题 -->
								<c:if test="${tAssignmentItem.type==1 }">
									<table>
										<c:forEach var="tAnswer" items="${tAssignmentItem.TAssignmentAnswers }">
											<tr>
												<td>
													正确答案<br>
													<input type='hidden' name='answerLabelChoice' value='${tAnswer.label }'>
													<input type="checkbox" name="answerLabel" value="${tAnswer.label }" <c:if test="${tAnswer.iscorrect==1 }">checked</c:if>>${tAnswer.label }
												</td>
												<td>
													<textarea name="answerText" style="resize: none;width: 300px;"><c:out value="${tAnswer.text }"></c:out></textarea> 
												</td>
											</tr>
										</c:forEach>
									</table>
								</c:if>
								<!-- 对错题 -->
								<c:if test="${tAssignmentItem.type==2 }">
									<table>
										<tr>
											<td>
												<c:forEach var="tAnswer" items="${tAssignmentItem.TAssignmentAnswers }">
													<input type="hidden" name="answerLabelChoice" value="${tAnswer.label }">
													<input type="radio" name="answerLabel" value="${tAnswer.label }" <c:if test="${tAnswer.iscorrect==1 }">checked="checked"</c:if>>&nbsp;&nbsp;
													<c:out value="${tAnswer.text }"></c:out><input type="hidden" name="answerText" value="${tAnswer.text }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</c:forEach>
											</td>
										</tr>
									</table>
								</c:if>
								<!-- 单选题 -->
								<c:if test="${tAssignmentItem.type==4 }">
									<table>
										<c:forEach var="tAnswer" items="${tAssignmentItem.TAssignmentAnswers }">
											<tr>
												<td>
													正确答案<br>
													<input type='hidden' name='answerLabelChoice' value='${tAnswer.label }'>
													<input type="radio" name="answerLabel" value="${tAnswer.label }" <c:if test="${tAnswer.iscorrect==1 }">checked</c:if>>${tAnswer.label }
												</td>
												<td>
													<textarea name="answerText" style="resize: none;width: 300px;"><c:out value="${tAnswer.text }"></c:out></textarea> 
												</td>
											</tr>
										</c:forEach>
									</table>
								</c:if>
								<!-- 填空题 -->
								<c:if test="${tAssignmentItem.type==8 }">
									<b>设置答案说明</b><br>
									填空题答案用大括号“{}”标记。<br>
									例如：Roses are {red} and violets are {blue}. 则正确答案为“red”和“blue”。<br><br>
									使用“|”分隔同义词，<br>
									例如： {They are|They're} very happy. 则正确答案为“They are”或“They are”。<br><br>
									使用星号(*)来表示通配符。<br>
									例如： It's raining {c*} and {d*s}. 则形如“c...”和“d...s”的答案均为正确答案。<br><br>
								</c:if>
							</div>
							
							<div class="moudle_footer">
						        <div class="submit_link">
						        	<input class="btn" id="save" type="submit" value="确定">
									<input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
						        </div>
						    </div>
						</div>
						</form:form>
					</div>
				</div>
				<!--题目内容  -->
				<%--<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">测验题目信息</div>
							<a class="btn btn-new" href="${pageContext.request.contextPath}/teaching/exam/newExamAnswer?itemId=${ItemInfo.id }">添加选项</a>
							</div>
						<div class="new-classroom">
                            <table  class="tb"  id="my_show"> 
				                <thead>
				                    <tr>
							    	<th width=10%>序号</th>
						    	    <th width=45%>题目选项</th>
						    	    <th width=15%>是否正确答案</th>
							    	<th width=15%>操作</th>
	                                </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${ItemInfo.TAssignmentAnswers}" var="current"  varStatus="i">
                                           <tr><td width=15%>${i.count}</td>
                                               <td width=45%>${current.label}: ${current.text}
                                               </td>
                                               <td width=10%>
                                               <c:if test="${current.iscorrect==1 }">
                                                       <b>正确答案</b>
                                               </c:if>
                                               <c:if test="${current.iscorrect!=1 }">
                                                       <font color=red>不正确答案</font>
                                               </c:if>
                                               </td>
                                               <td width=15%>
                                               <a href='${pageContext.request.contextPath}/teaching/exam/findExamAnswerById?id=${current.id}' >编辑选项</a>|
                                               <a href='${pageContext.request.contextPath}/teaching/exam/deleteExamAnswerById?id=${current.id}' >删除选项</a>
                                               </td>
                                            </tr>
                                    </c:forEach>
                                    </tbody>
							</table>
							</div>
							
					</div>
				</div>
			--%></div>
		</div>
	</div>

<!-- 编辑题目 -->
<div id="editTAssignmentItem" class="easyui-window" title="编辑题目" closed="true"  modal="true" iconCls="icon-add" style="width:1000px;height:500px">
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