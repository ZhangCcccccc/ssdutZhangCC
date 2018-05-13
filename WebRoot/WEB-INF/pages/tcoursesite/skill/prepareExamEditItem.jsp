<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>预习测试</title>
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
    
    <link href="${pageContext.request.contextPath}/css/tCourseSite/lib.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/global.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/skill/experiment.css" rel="stylesheet" type="text/css">
    

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/skill/picChange.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>


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
									"<input type='checkbox' name='answerLabel' id='A' value='A'>A"+
									"<label class='l mt10' for='A'></label>"+
								"</td>"+
								"<td>"+
									"<textarea name='answerText' style='resize: none;width: 300px;'></textarea>"+
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td>"+
									"正确答案<br>"+
									"<input type='hidden' name='answerLabelChoice' value='B'>"+
									"<input type='checkbox' name='answerLabel' id='B' value='B'>B"+
									"<label class='l mt10' for='B'></label>"+
								"</td>"+
								"<td>"+
									"<textarea name='answerText' style='resize: none;width: 300px;'></textarea>"+ 
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td>"+
									"正确答案<br>"+
									"<input type='hidden' name='answerLabelChoice' value='C'>"+
									"<input type='checkbox' name='answerLabel' id='C' value='C'>C"+
									"<label class='l mt10' for='C'></label>"+
								"</td>"+
								"<td>"+
									"<textarea name='answerText' style='resize: none;width: 300px;'></textarea>"+ 
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td>"+
									"正确答案<br>"+
									"<input type='hidden' name='answerLabelChoice' value='D'>"+
									"<input type='checkbox' name='answerLabel' id='D' value='D'>D"+
									"<label class='l mt10' for='D'></label>"+
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
									"<input type='radio' id='yes' name='answerLabel' value='0'>"+
									"<label class='l mt10' for='yes'></label>"+
									"&nbsp;&nbsp;"+
									"对<input type='hidden' name='answerText' value='对'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
									"<br><br>"+
									"<input type='hidden' name='answerLabelChoice' value='1'>"+
									"<input type='radio' id='no' name='answerLabel' value='1'>"+
									"<label class='l mt10' for='no'></label>"+
									"&nbsp;&nbsp;"+
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
									"<input type='radio' name='answerLabel' id='A' value='A'>A"+
									"<label class='l mt10' for='A'></label>"+
								"</td>"+
								"<td>"+
									"<textarea name='answerText' style='resize: none;width: 300px;'></textarea>"+
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td>"+
									"正确答案<br>"+
									"<input type='hidden' name='answerLabelChoice' value='B'>"+
									"<input type='radio' name='answerLabel' id='B' value='B'>B"+
									"<label class='l mt10' for='B'></label>"+
								"</td>"+
								"<td>"+
									"<textarea name='answerText' style='resize: none;width: 300px;'></textarea>"+ 
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td>"+
									"正确答案<br>"+
									"<input type='hidden' name='answerLabelChoice' value='C'>"+
									"<input type='radio' name='answerLabel' id='C' value='C'>C"+
									"<label class='l mt10' for='C'></label>"+
								"</td>"+
								"<td>"+
									"<textarea name='answerText' style='resize: none;width: 300px;'></textarea>"+ 
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td>"+
									"正确答案<br>"+
									"<input type='hidden' name='answerLabelChoice' value='D'>"+
									"<input type='radio' name='answerLabel' id='D' value='D'>D"+
									"<label class='l mt10' for='D'></label>"+
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

<style>

input {
    background: #77bace none repeat scroll 0 0;
    border: 1px solid #77bace;
    border-radius: 3px;
    box-shadow: 0 1px 0 #fff;
    color: #fff;
    cursor: pointer;
    height: 22px;
    line-height: 20px;
    padding: 0 10px;
}
</style>


</head>
<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="tCourseSiteId" value="${tCourseSite.id}"/>
	<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
	<input type="hidden"  id="now" value="${now}"/>
	<input type="hidden"  id="skillId" value="${tExperimentSkill.id}"/>
	
	
	<div class="course_con ma back_gray" >
		<div class="course_cont r back_gray">
			<div class="course_content">
                <div class="tab_content">
                	<!--题目内容  -->
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">编辑测验信息</div>
							<%--<a class="btn btn-new" href="${pageContext.request.contextPath}/teaching/exam/findExamItemById?id=${ItemInfo.id }">编辑基本信息</a>
						
						--%></div> 
						<form:form action="${pageContext.request.contextPath}/tcoursesite/skill/SavePrepareExamItem?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}" method="POST" modelAttribute="tAssignmentItem" onsubmit="return checkRequired()">
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
													<input type="checkbox" name="answerLabel" id="${tAnswer.label }" value="${tAnswer.label }" <c:if test="${tAnswer.iscorrect==1 }">checked</c:if>>
													<label class='l mt10' for='${tAnswer.label }'></label>${tAnswer.label }
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
													<input type="radio" name="answerLabel" id='${tAnswer.label }' value="${tAnswer.label }" <c:if test="${tAnswer.iscorrect==1 }">checked="checked"</c:if>>
													<label class="l mt10" for='${tAnswer.label }'></label>&nbsp;&nbsp;
													<c:out value="${tAnswer.text }"></c:out><input type="hidden" name="answerText" value="${tAnswer.text }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<br><br>
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
													<input type="radio" name="answerLabel" id='${tAnswer.label }' value="${tAnswer.label }" <c:if test="${tAnswer.iscorrect==1 }">checked</c:if>>
													<label class='l mt10' for='${tAnswer.label }'></label>${tAnswer.label }
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
                </div>
                
            </div>
                 
            
        </div>
    </div>
    
    	
    
    <script language="javascript" type="text/javascript">

  //实验问答教师给学生评分
    function grade(obj){
    	var now = $("#now").val(); 
    	var tCourseSiteId = $("#tCourseSiteId").val(); 
    	var assignGradeId = $(obj).parent().parent().find("#assignGradeId").val(); 
    	var comments = $(obj).parent().parent().find("#comments").val();
    	var finalScore = $(obj).parent().parent().find("#finalScore").val();
    	$.ajax({
    		url: $("#contextPath").val()+'/tcoursesite/skill/gradeReport?tCourseSiteId='+tCourseSiteId+'&assignGradeId='+assignGradeId+'&comments='+comments+'&finalScore='+finalScore,
    		type:'post',
    		async:false,  // 设置同步方式
            cache:false,
    		success:function(data){
    			if(finalScore!=""){
    				$("#isGraded").val("已评分");
    			}
    			$("span").html('<fmt:formatDate pattern="yyyy-MM-dd HH-mm" value="${now}" type="both"/>');
    			$("#finalScoreFont").html(finalScore);
    		}
    	}); 
    }
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/skill/experimentSkill.js"></script>
</body>
</html>