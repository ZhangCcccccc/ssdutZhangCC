<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />   
<head>
<meta name="decorator" content="iframe" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
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
	function exportSelect(){
	 	document.form.action="exportExcelSelectUser";
		document.form.submit();
	}
	
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
	    	$(obj).parent().parent().find("#finalScoreFont").html(score);
	    	return;
	    }
	    $(obj).val(price);
	}
	
	function grade(obj){
		var assignGradeId = $(obj).parent().parent().find("#assignGradeId").val(); 
		var comments = $(obj).parent().parent().find("#comments").val().trim();
		var finalScore = $(obj).parent().parent().find("#finalScore").val();
		$.ajax({
			url:'${pageContext.request.contextPath}/teaching/assignmentGrading/grade?assignGradeId='+assignGradeId+'&comments='+comments+'&finalScore='+finalScore,
			type:'post',
			async:false,  // 设置同步方式
	        cache:false,
			success:function(data){
				if(finalScore!="" && comments!=""){
					alert("温馨提示：评语和最终成绩已保存！");
					$("#isGraded").val("已评分");
				}else if(finalScore!="" && comments==""){
					alert("温馨提示：评语为空，最终成绩已保存！");
					$("#isGraded").val("已评分");
				}else if(finalScore=="" && comments!=""){
					alert("温馨提示：最终成绩为空，评语已保存！");
				}else if(finalScore=="" && comments==""){
					alert("温馨提示：最终成绩为空，评语为空！");
				}
				//修改该tr下教师批改时间td中的span的html内容
				$(obj).parent().parent().find("span").html('<fmt:formatDate pattern="yyyy-MM-dd" value="${now }" type="both"/>');
				
				$(obj).parent().parent().find("#finalScoreFont").html(finalScore);
			}
		}); 
	}
	
	function downloadFile(id){
		var input1 = "<input type='hidden' name='tCourseSiteId' value='"+ ${tCourseSite.id } +"' />";
		var input = "<input type='hidden' name='assignId' value='"+ id +"' />";
		var html = "<form action='${pageContext.request.contextPath}/teaching/assignment/downloadAssignment' method='post'>"+input1+input+"</form>"; 
		jQuery(html).appendTo("body").submit().remove();
		/**
		$("#assignId").val(id);
		document.form.submit();
		*/
	}
	
	function downloadOneFile(id,path,tCourseSiteId,moduleType,selectId,url){
		if(url==""||url==null){
			alert("无附件!");
		}else{
			var input = "<input type='hidden' name='id' value='"+ id +"' />";
			var html = "<form action='${pageContext.request.contextPath}/teaching/assignment/"+path+"?tCourseSiteId="+tCourseSiteId+"&moduleType="+moduleType+"&selectId="+selectId+"' method='post'>"+input+"</form>"; 
			jQuery(html).appendTo("body").submit().remove();
		}
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
							<div id="title">作业信息</div>
							<a class="btn btn-new" href="javascript:void(0)" onclick="window.history.go(-1)">返回</a>
							<c:if test="${flag==1}">
								<a class="btn btn-new" href="${pageContext.request.contextPath}/teaching/assignment/updateAssignmentById?tCourseSiteId=${tCourseSite.id }&assignId=${tAssignment.id}&moduleType=${moduleType}&selectId=${selectId}">编辑</a>
							</c:if>
     					</div> 
						
						<div class="new-classroom">
                            <table  class="tb"  id="my_show"> 
				                <thead>
				                    <tr>
				                    <th><label style="margin-left: 16px">站点名称：</label></th>
							    	<td>${tCourseSite.title }</td> 
							    	<th><label style="margin-left: 16px">作业标题：</label></th>
							    	<td>${tAssignment.title }</td>
	                                </tr>
	                                <tr>
				                    <th><label style="margin-left: 16px">作业要求：</label></th>
							    	<td>${tAssignment.content }</td> 
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
							</thead>
							</table>
							</div>
					</div>
				</div>





<!-- 查询、导出、打印 -->
<div class="content-box">
	<div class="title">
		<div id="title">作业列表</div>
		<c:if test="${flag==0}">
			<a style="margin-left: 1000px;color: blue;" onclick="window.history.go(-1)">返回</a>
		</c:if>
		<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag!=0}">
			<mytag:JspSecurity realm="check" menu="tAssignment">
				<a style="margin-left: 800px;" href="javascript:void(0)" onclick="downloadFile(${tAssignment.id })">查看提交的附件</a>
			</mytag:JspSecurity>
			<mytag:JspSecurity realm="check" menu="gradebook">
				<a style="margin-left: 10px;" href="${pageContext.request.contextPath}/tcoursesite/gradeBook?tCourseSiteId=${tAssignment.siteId}&type=assignment">查看本课程所有成绩</a>
			</mytag:JspSecurity>
			<a style="margin-left: 10px;color: red;" onclick="window.history.go(-1)">返回</a>
		</c:if>
	</div> 	
	<table  id="my_show"> 
		<thead>
		    <tr>                   
		        <th>作业标题</th>
		        <th>学生姓名</th>
		        <th>成绩</th>
		        <th>评语</th>
		        <th>提交文字</th>
		        <th>学生提交时间</th>
		        <th>教师批改时间</th>
		        <th>最终成绩</th>
		        <th>提交状态</th>
		        <th>操作</th>
		    </tr>
		</thead>
		<tbody>
			<c:forEach items="${tAssignmentGradings}" var="current"  varStatus="i">
		   		<tr>
					<td>${current.TAssignment.title}<br>
				       	<p id="isGraded">
					       	<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag!=0}">
					       		<%--<c:if test="${current.userByGradeBy == null }">
					       			<a href="${pageContext.request.contextPath}/teaching/assignment/findTAssignmentGradeToMark?assignGradeId=${current.accessmentgradingId }&flag=${flag }">评分</a>
					       		</c:if>
					       		--%><c:if test="${current.userByGradeBy != null }">
					       			已评分
					       		</c:if>
							</c:if> 
					       	<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag==0}">
					       	</c:if>
				       	</p> 
			       	</td>
	       			<td>${current.userByStudent.cname }</td>
			       	<td>
			       		<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag!=0}">
				       		<c:if test="${current.finalScore != null }">
				       			<font id="finalScoreFont">${current.finalScore }</font>
				       		</c:if>
				       		<c:if test="${current.finalScore == null }">
				       			<font id="finalScoreFont">尚未批改</font>
				       		</c:if>
				       		/${current.TAssignment.TAssignmentAnswerAssign.score }
			       		</c:if>
			       		<c:if test="${fn:contains(user.authorities,'STUDENT')&&flag==0}">
			       			<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'yes' }">
			       				<c:if test="${current.finalScore != null }">
					       			<font id="finalScoreFont">${current.finalScore }</font>
					       		</c:if>
					       		<c:if test="${current.finalScore == null }">
					       			<font id="finalScoreFont">尚未批改</font>
					       		</c:if>
								/${current.TAssignment.TAssignmentAnswerAssign.score }
							</c:if>
							<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'no' }">
								该作业成绩不公布
							</c:if>
			       		</c:if>
			       		
					</td>
					<td>
						<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag!=0}">
							<input type="text" id="comments" value="${current.comments }" onchange="grade(this)"/>
						</c:if>
						<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag!=0}">
							<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'yes' }"><%--
								${current.comments }
							--%></c:if>
							<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'no' }">
								该作业成绩不公布
							</c:if>
						</c:if>
					</td>
					<td>
						<a href="javascript:void(0)" onclick="checkFile('${current.TAssignment.title}','${current.userByStudent.cname }','${current.content }')">查看</a>
					</td>
					<td>
						<fmt:formatDate pattern="yyyy-MM-dd" value="${current.submitdate.time }" type="both"/>
					</td>
					<td>
						<span><fmt:formatDate pattern="yyyy-MM-dd" value="${current.gradeTime.time }" type="both"/></span>
					</td>
					<td>
						<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag!=0}">
							<input type="text" id="finalScore" style="width: 45px;" value="${current.finalScore }" onchange="grade(this)" oninput="changeNumber(this,${current.TAssignment.TAssignmentAnswerAssign.score} )"/>
							<input type="hidden" id="assignGradeId" value="${current.accessmentgradingId }"/>
						</c:if>
						<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag==0}">
				        	<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'yes' }">
								${current.finalScore }
							</c:if>
							<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'no' }">
								该作业成绩不公布
							</c:if> 
				       	</c:if>
					</td>
					<td>
						<c:if test="${current.islate==0 }"><font color="green">正常提交</font></c:if>
						<c:if test="${current.islate==1 }"><font color="red">迟交</font></c:if>
					</td>
					<td>
						<mytag:JspSecurity realm="update" menu="tAssignment">
							<c:if test="${fn:contains(user.authorities,'STUDENT')&&flag==0}">
								<a href="javascript:void(0)" onclick="downloadOneFile(${current.accessmentgradingId},'downloadFileForStudent',${tCourseSite.id},${moduleType},${selectId},'${current.gradeUrl}')" >下载</a>
							</c:if>
							<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag==1}">
								<a href="javascript:void(0)" onclick="downloadOneFile(${current.accessmentgradingId},'downloadFile',${tCourseSite.id},${moduleType},${selectId},'${current.gradeUrl}')">下载</a>
							</c:if>
							<%--<a href="${pageContext.request.contextPath}/teaching/assignment/findTAssignmentGradeToMark?assignGradeId=${current.accessmentgradingId }&flag=${flag }">查看作业详情</a>
						--%></mytag:JspSecurity>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	
	</table>
</div>
</div>
</div>

         <!--未提交作业学生列表-->
	<div class="window_box hide fix zx2  " id="lookNotCommitStudent">
        <div class="window_con bgw b1 br3 rel bs10 w20">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
	            <div class="add_tit p20 bb f16">未提交作业学生列表</div>
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
    	<!--未提交作业学生列表-->
    	
    	<!--提交文字-->
	<div class="window_box hide fix zx2  " id="submitContent">
        <div class="window_con bgw b1 br3 rel bs10 w20">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
	            <div class="add_tit p20 bb f16">提交文字</div>
	            <div class="add_con p20 tc">
	            	<table > 
						<thead>
						    <tr>                   
						        <th>作业标题</th>
						        <th>学生姓名</th>
						        <th>提交文字</th>
						    </tr>
						</thead>
						<tbody>
						   		<tr>
					       			<td id="gradeTitle"></td>
					       			<td id="gradeCname" ></td>
					       			<td id="gradeContent"></td>
								</tr>
						</tbody>
					
					</table>
				</div>
			</div>
		</div>
	</div>
    	<!--提交文字-->
    	
    	
<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery-1.7.1.min.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.tablesorter.js"></script>
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
    
    function checkFile(title,cname,content){
    	$("#submitContent").fadeIn(100);
    	document.getElementById("gradeTitle").innerHTML=title;
    	document.getElementById("gradeCname").innerHTML=cname;
    	document.getElementById("gradeContent").innerHTML=content;
    	
    }
    
</script>
<!-- 下拉框的js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
</body>
</html>

