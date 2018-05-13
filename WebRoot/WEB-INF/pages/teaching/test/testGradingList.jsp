<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />   
<head>
<meta name="decorator" content="iframe" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/reset.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/lib.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/global.css" rel="stylesheet" type="text/css">
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
 <script type="text/javascript">
	$(document).ready(function(){
		$("#print").click(function(){
		$("#my_show").jqprint();
		});
	});	
</script> 
<script type="text/javascript">
	
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
	    	alert("打分不得高于分值，分值为"+score+"！");
	    	$(obj).val(score);
	    	$("#finalScoreFont").html(score);
			grade(obj);
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
				//$("span").html('<fmt:formatDate pattern="yyyy-MM-dd" value="${now }" type="both"/>');
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
		var html = "<form action='${pageContext.request.contextPath}/teaching/assignment/downloadFile' method='post'>"+input+"</form>"; 
		jQuery(html).appendTo("body").submit().remove();
		/**
		$("#assignId").val(id);
		document.form.submit();
		*/
	}
	
	function lookNotCommitStudent(){
		$("#lookNotCommitStudent").fadeIn(100);

	}
</script>
</head>
<body>

<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent0">

		  	    <!--题目抬头  -->
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">考试信息</div>
							<a class="btn btn-new" href="javascript:void(0)" onclick="window.history.go(-1)">返回</a>
     					</div> 
						
						<div class="new-classroom">
                            <table  class="tb"  id="my_show"> 
				                <thead>
				                    <tr>
				                    <th><label style="margin-left: 16px">考试标题：</label></th>
							    	<td>${tAssignment.title }</td> 
							    	<th><label style="margin-left: 16px">未提交/已提交：</label></th>
							    	<td>
							    	<a href="javascript:void(0)" onclick="lookNotCommitStudent()">
							    	${fn:length(notCommitStudents)}</a>
							    	/${fn:length(tAssignmentGradings)}</td>
	                                </tr>
                                    <tr> 
							    	<th><label style="margin-left: 16px">开始时间：</label></th>
									<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${tAssignment.TAssignmentControl.startdate.time}" type="both"/></td>
                                    <th><label style="margin-left: 16px">截止时间：</label></th>
									<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${tAssignment.TAssignmentControl.duedate.time}" type="both"/></td>
                                    </tr>
                                    <tr>
                                        <th><label style="margin-left: 16px">总分：</label></th>
                                        <td>
                                          ${tAssignment.TAssignmentAnswerAssign.score }
                                        </td>
                                        <th><label style="margin-left: 16px">教师：</label></th>
                                        <td>
                                        ${tAssignment.user.cname }
							 	     	</td>
							 	    </tr>
							 	    <tr> 
							    	<th><label style="margin-left: 16px">考试描述：</label></th>
									<td>${tAssignment.content }</td>
                                    </tr>
							</thead>
							</table>
							</div>
					</div>
				</div>





<!-- 查询、导出、打印 -->
<div class="content-box">
	<div class="title">
		<div id="title">考试列表</div>
		<a class="btn btn-new" href="javascript:void(0)" onclick="window.history.go(-1)">返回</a>
	</div> 	
	<table  id="my_show"> 
		<thead>
		    <tr>                   
		        <th>考试标题</th>
		        <th>姓名</th>
		        <th>学号/工号</th>
		        <th>角色</th>
		        <th>提交日期</th>
		        <th>调整</th>
		        <th>总分</th>
		        <th>评语</th>
		        <th>详情</th>
		    </tr>
		</thead>
		<tbody>
			<c:forEach items="${tAssignmentGradings}" var="current"  varStatus="i">
		   		<tr>
					<td>${current.TAssignment.title}</td>
	       			<td>${current.userByStudent.cname }</td>
	       			<td>${current.userByStudent.username }</td>
	       			<td>
	       				<c:forEach begin="0" end="0" items="${current.userByStudent.authorities }" var="authority">
	       					${authority.cname }
	       				</c:forEach>
	       			</td>
			       	<td>
			       		<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${current.submitdate.time }" type="both"/>
			       	</td>
			       	<td> 
			       		<input type="text" id="finalScore" style="width: 40px;" value="${current.finalScore }" onchange="grade(this)" oninput="changeNumber(this,${current.TAssignment.TAssignmentAnswerAssign.score} )"/>
			       		<input type="hidden" id="assignGradeId" value="${current.accessmentgradingId }"/>
			       	</td>
					<td>
						<p id="finalScoreFont">${current.finalScore }</p>
					</td>
					<td>
			       		<input type="text" id="comments" value="${current.comments }" onchange="grade(this)"/>
			       	</td>
			       	<td>
			       		<a href="${pageContext.request.contextPath}/teaching/test/findTestDetailByGradingId?assignGradingId=${current.accessmentgradingId}">答题详情</a>
			       	</td>
				</tr>
			</c:forEach>
		</tbody>
	
	</table>
</div>
</div>
</div>

         <!--未提交考试学生列表-->
	<div class="window_box hide fix zx2  " id="lookNotCommitStudent">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
	            <div class="add_tit p20 bb f16">未提交考试学生列表</div>
	            <div class="add_con p20 tc">
	            	<table > 
						<thead>
						    <tr>                   
						        <th>姓名</th>
						        <th>学号/工号</th>
						    </tr>
						</thead>
						<tbody>
							<c:forEach items="${notCommitStudents}" var="student"  varStatus="i">
						   		<tr>
					       			<td>${student.user.cname }</td>
					       			<td>${student.user.username }</td>
								</tr>
							</c:forEach>
						</tbody>
					
					</table>
	            </div>
	        </div>
	    </div>
    	<!--未提交考试学生列表-->
<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select'           : {search_contains:true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {  	
      $(selector).chosen(config[selector]);
    }
</script>
<!-- 下拉框的js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
</body>
</html>

