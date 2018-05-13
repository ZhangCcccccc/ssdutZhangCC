 <%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
 <jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
 
 <html >  
<head>
<meta name="decorator" content="iframe"/>
<!-- 富文本的css -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css"/> 

<style type="text/css" media="screen">
	@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/icon.css");
	@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/gray/easyui.css");
</style>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	/* function showTimeLimitDiv(obj){
		if($(obj).attr("checked")=="checked"){
			$("#timeLimit").show();
		}else{
			$("#timeLimit").hide();
			$("#timelimit").val(1);
		}
	} */
	
	var array = new Array(0,0,0,0);
	var count = 0;
	function addTr() {
	    //获取当前屏幕的绝对位置
	    var topPos = window.pageYOffset;
	    //使得弹出框在屏幕顶端可见
	    $('#addTr').window({left:"0px", top:topPos+"px"}); 
	    $("#addTr").window('open');
	}
	
	function deleteThisTr(obj){
		$(obj).parent().parent().remove();
	}
	
	function addTrRecord(){
	
		var itemType = $("#addTrItemType").val();
		var itemTypeHtml = $("#addTrItemType option:selected").html();
		if(itemType==""){
			alert("请选择试题类型");
			return false;
		}
		var itemQuantity = $("#addTrItemQuantity").val().trim();
		if(itemQuantity==""){
			alert("请填写试题数量");
			return false;
		}
		var itemScore = $("#addTrItemScore").val().trim();
		if(itemScore==""){
			alert("请填写试题分值");
			return false;
		}
		if (array.indexOf(itemType)!=-1) {
			alert("该试题类型已添加，可直接修改数量");
			return false;
		}
		array[count] = itemType;
		count++;
		var trString = "<tr>"+
						"<td><input type='hidden' name='itemType' value='"+itemType+"'/>"+itemTypeHtml+"</td>"+
						"<td><input type='text' style='width:100px;' name='itemQuantity' value='"+itemQuantity+"' class='easyui-numberbox' /></td>"+
						"<td><input type='text' style='width:100px;' name='itemScore' value='"+itemScore+"' class='easyui-numberbox' /></td>"+
						"<td><button type='button' class='btn' onclick='deleteThisTr(this)'>删除</button></td>"+
						"</tr>";
		$("#itemBody").children().last().before(trString);
		$("#addTrItemType").val("");
		$("#addTrItemQuantity").val("");
		$("#addTrItemScore").val("");
		$("#addTr").window('close');
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
						    <c:if test="${tAssignment.id==null }">
							    <div id="title">新建考试</div>
							</c:if>
							<c:if test="${tAssignment.id!=null }">
							    <div id="title">编辑考试</div>
							</c:if>
						</div> 
						
						<form:form action="${pageContext.request.contextPath}/teaching/test/saveTest" method="POST" modelAttribute="tAssignment" onsubmit="return checkForm()">
							<div class="new-classroom">
                            <table  class="tb" > 
				                <thead>
				                    <tr> 
							    	<th style="width: 15%"><label style="margin-left: 16px">考试标题<font color="red">*</font>：</label></th>
							    	<th><form:input path="title" id="title" required="true" /></th>
	                                </tr>
                                    <tr> 
							    	<th><label style="margin-left: 16px">开始时间<font color="red">*</font>：</label></th>
									<th><input type="text" id="startdate" class="Wdate" name="startdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="width:145px;height: 23px;" value="${startdate }" readonly="readonly" required="required"></th>
                                    </tr>
                                    <tr>
								    <th><label style="margin-left: 16px">截止时间<font color="red">*</font>：</label></th>
									<th><input type="text" id="duedate" class="Wdate" name="duedate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="width:145px;height: 23px;" value="${duedate }" readonly="readonly" required="required"></th>
                                    	
                                    </tr>
                                    <tr style="display: none;">
                                    	<th><label>作业布置人：</label></th>
						     			<th>
						     				<form:input path="TAssignmentAnswerAssign.user.username" placeholder="请输入工号/学号"/>
                                    	</th>
                                    </tr>
						     		<th><label style="margin-left: 16px">评分设置：</label><br></th>
						     		<th><form:radiobutton path="TAssignmentControl.toGradebook" value="yes" checked="checked" />将考试添加到成绩簿 
						     			<form:radiobutton path="TAssignmentControl.toGradebook" value="no"/>不要将考试加入到成绩簿中<br>
						     			<form:radiobutton path="TAssignmentControl.gradeToStudent" value="yes" checked="checked"/>将成绩公布给学生 
						     			<form:radiobutton path="TAssignmentControl.gradeToStudent" value="no" />不将成绩公布给学生<br>
						     			<form:radiobutton path="TAssignmentControl.gradeToTotalGrade" value="yes" checked="checked"/>将成绩计入总成绩
						     			<form:radiobutton path="TAssignmentControl.gradeToTotalGrade" value="no" />不将成绩计入总成绩
                                    </th>
                                    </tr>
                                    <tr>
						     		<th><label style="margin-left: 16px">考试分值<font color="red">*</font>：</label><br></th>
						     		<th><form:input path="TAssignmentAnswerAssign.score" id="testScore" style="width:200px;" placeholder="请输入分值" class="easyui-numberbox" required="true"/>
                                    </th>
                                    </tr>
                                    
                                    <tr>
                                        <th><label style="margin-left: 16px">抽取题库：</label><br></th>
                                        <th>
                                        	<a href="javascript:void(0);" onclick="ssss();aaaa();">请选择题库</a><br>
                                       		<c:forEach items="${tAssignmentQuestionpools }" var="tAssignmentQuestionpool" varStatus="i">
                              						<input type="checkbox" <c:if test="${map[tAssignmentQuestionpool.questionpoolId]!=null }">checked="true"</c:if>  name="questionId" value="${tAssignmentQuestionpool.questionpoolId }"/>${tAssignmentQuestionpool.title }&nbsp;
                              						<c:if test="${i.count%3==0 }"></br></c:if>
                                        	</c:forEach>
                                        	
								 	    </th>	
							 	     </tr>
                                    <tr>
                                        <th><label style="margin-left: 16px">试题组成：</label><br></th>
                                        <th>
                                        	<table id="item" style="width: 60%">
                                        		<thead>
	                                        		<tr>
	                                        			<td>试题类型</td>
	                                        			<td>试题数量</td>
	                                        			<td>试题分值(仅用于该次考试)</td>
	                                        			<td>操作</td>
	                                        		</tr>
                                        		</thead>
                                        		<tbody id="itemBody">
	                                        		<c:forEach items="${itemComponents }" var="itemComponent">
	                                        			<tr>
	                                        				<td>
	                                        					<c:if test="${itemComponent.itemType==1 }">
	                                        						多选题
	                                        					</c:if>
	                                        					<c:if test="${itemComponent.itemType==2 }">
	                                        						对错题
	                                        					</c:if>
	                                        					<c:if test="${itemComponent.itemType==4 }">
	                                        						单选题
	                                        					</c:if>
	                                        					<c:if test="${itemComponent.itemType==8 }">
	                                        						填空题
	                                        					</c:if>
	                                        					<input type="hidden" name="itemType" value="${itemComponent.itemType }"/> 
	                                        				</td>
	                                        				<td>
	                                        					<input type="text" name="itemQuantity" style="width:100px;"  value="${itemComponent.itemQuantity }" class="easyui-numberbox" />
	                                        				</td>
	                                        				<td>
	                                        					<input type="text" name="itemScore" style="width:100px;"  value="${itemComponent.itemScore }" />
	                                        				</td>
	                                        				<td>
	                                        					<button type="button" class="btn" onclick="deleteThisTr(this)">删除</button>
	                                        				</td>
	                                        			</tr>
	                                        		</c:forEach>
	                                       			<tr>
	                                       				<td><button class="btn" type="button" onclick="addTr()">添加</button></td>
	                                       				<td></td>
	                                       				<td></td>
	                                       				<td></td>
	                                       			</tr>
                                        		</tbody>
                                        	</table>
								 	    </th>	
							 	     </tr>
						 	    </thead>
							</table>
							 	  
						 	<form:hidden path="type" value="test"/>
						 	<form:hidden path="status" value="0"/>
						 	<form:hidden path="createdTime" />
				 	        <form:hidden path="user.username" />
						    <form:hidden path="id" />
						    <form:hidden path="TAssignmentControl.id" />
						    <form:hidden path="TAssignmentAnswerAssign.id" />
						    <form:hidden path="TAssignmentControl.timelimit" value="1" />
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
	<div id="addTr" class="easyui-window" title="试题详情" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add"  style="width:500px; height:400px;">
		<form action="">
			<table id="my_show">
				<tr>
					<td align="right">试题类型</td>
					<td>
						<select id="addTrItemType">
							<option value="">请选择</option>
							<option value="1">多选题</option>
							<option value="2">对错题</option>
							<option value="4">单选题</option>
							<option value="8">填空题</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">试题数量</td>
					<td>
						<input type="text" id="addTrItemQuantity" placeholder="请输入数字" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td align="right">试题分值(仅用于该次考试)</td>
					<td>
						<input type="text" id="addTrItemScore" placeholder="请输入数字" class="easyui-numberbox"/>
					</td>
				</tr>
				<tr>
					<td align="right"></td>
					<td>
						<input type="button" value="确定" onclick="addTrRecord()"/>
					</td>
				</tr>
			</table>
		</form>
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
    
    
    function checkForm(){
    	var result = 0;
    	var questionIdArray = new Array();
    	var typeArray = new Array();
    	var quantityArray = new Array();
    	var scoreArray = new Array();
    	$("input[name='questionId']:checked").each(function(i,value){
			questionIdArray[i] = $(this).val().trim();
    	})
    	if (questionIdArray.length==0) {
			alert("请选择题库！");
			return false;
		}
    	$("input[name='itemType']").each(function(i,value){
			typeArray[i] = $(this).val().trim();
    	})
    	if (typeArray.length==0) {
			alert("请添加组成试卷的试题！");
			return false;
		}
    	$("input[name='itemQuantity']").each(function(i,value){
    		if ($(this).val().trim()==""||isNaN($(this).val().trim())) {
				alert("试题数量不能为空且必须为数字");
				$(this)[0].focus();
				result = 1;
				return false;
			}
			quantityArray[i] = $(this).val().trim();
    	})
    	if (result == 1) {
			return false;
		}
		$("input[name='itemScore']").each(function(i,value){
    		if ($(this).val().trim()==""||isNaN($(this).val().trim())) {
				alert("试题分值不能为空且必须为数字");
				$(this)[0].focus();
				result = 1;
				return false;
			}
			scoreArray[i] = $(this).val().trim();
    	})
    	if (result == 1) {
			return false;
		}
		$.ajax({
			url:'${pageContext.request.contextPath}/teaching/test/checkItemQuantity?questionIdArray='+questionIdArray+'&typeArray='+typeArray+'&quantityArray='+quantityArray,
			type:'post',
			async:false,  // 设置同步方式
	        cache:false,
	        contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(data){
				if(data['result']!=""){
					alert(data['result']);
					result = 1;
				}
			}
		}); 
		if (result == 1) {
			return false;
		}
		var totalScore = 0;
		 $.each(quantityArray,function(i,value){
		 	totalScore += quantityArray[i]*scoreArray[i];
		 })
		 if(totalScore!=Number($("#testScore").val().trim())){
		 	alert("已添加试题分值为"+totalScore+"分，与考试分值不符，请检查！");
		 	return false;
		 }
    }
</script>
</body>


</html>