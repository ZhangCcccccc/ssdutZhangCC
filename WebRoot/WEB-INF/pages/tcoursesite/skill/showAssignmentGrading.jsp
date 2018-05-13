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
	
	function grade(obj,num){
		var k=num;
		var assignGradeId = $(obj).parent().parent().find("#assignGradeId").val(); 
		var comments = $(obj).parent().parent().find("#comments_"+num+"").val().trim();
		var finalScore = $(obj).parent().parent().find("#finalScore_"+num+"").val();
		$.ajax({
			url:'${pageContext.request.contextPath}/teaching/assignmentGrading/grade?assignGradeId='+assignGradeId+'&comments='+comments+'&finalScore='+finalScore,
			type:'post',
			async:false,  // 设置同步方式
	        cache:false,
			success:function(data){
				if(finalScore !=""){
					$(obj).parent().parent().find("#isGraded").html("已评分");
					
					//修改该tr下教师批改时间td中的span的html内容
					$(obj).parent().parent().find("span").html('<fmt:formatDate pattern="yyyy-MM-dd" value="${now }" type="both"/>');
					
					$(obj).parent().parent().find("#finalScoreFont").html(finalScore);
					
				}else{
					$(obj).parent().parent().find("#isGraded").html("");
					
					$(obj).parent().parent().find("#finalScoreFont").html("尚未批改");
				}
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
	
	function downloadOneFile(id,path,tCourseSiteId,url){
		if(url==""||url==null){
			alert("无附件!");
		}else{
			var input = "<input type='hidden' name='id' value='"+ id +"' />";
			var html = "<form action='${pageContext.request.contextPath}/tcoursesite/assignment/"+path+"?tCourseSiteId="+tCourseSiteId+"' method='post'>"+input+"</form>"; 
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
	
	function saveScoreAndComment(num){
		var k=num;
		var inputComment=document.getElementById("comments_"+k+"");
		var inputFinalScore=document.getElementById("finalScore_"+k+"");
		//设置保存按钮不可见、编辑按钮可见
		//$("#saveOrEdit_"+k+"").fadeOut(100);
		document.getElementById("saveOrEdit_"+k+"").style.display = "none";
		document.getElementById("editOrSave_"+k+"").style.display = "block";
		//增加文本框不可见和不可编辑的属性
		inputComment.setAttribute("style","border-style:none");
		inputComment.setAttribute("readonly","readonly");
		inputFinalScore.setAttribute("style","border-style:none");
		inputFinalScore.setAttribute("readonly","readonly");
	}
	
	function editScoreAndComment(num){
		var k=num;
		var inputComment=document.getElementById("comments_"+k+"");
		var inputFinalScore=document.getElementById("finalScore_"+k+"");
		//设置编辑按钮不可见、保存按钮可见
		//$("#editOrSave_"+k+"").fadeOut(100);
		document.getElementById("editOrSave_"+k+"").style.display = "none";
		document.getElementById("saveOrEdit_"+k+"").style.display = "block";
		//删除文本框不可见和不可编辑的属性
		inputComment.removeAttribute("style");
		inputComment.removeAttribute("readonly");
		inputFinalScore.removeAttribute("style");
		inputFinalScore.removeAttribute("readonly");
	}
	
	function reject(obj,num,islate){
		var finalScore = $(obj).parent().parent().parent().find("#finalScore_"+num+"").val();
		var islate = islate;
		if(islate ==2){
			alert("您已驳回该生作业，不能重复驳回！");
			return false;
		}
		if(finalScore !="" && typeof finalScore !="undefined"){
			alert("您已给出成绩，不能驳回！若想驳回，请清除成绩并保存后重试！");
			return false;
		}
		var assignGradeId = $(obj).parent().parent().parent().find("#assignGradeId").val(); 
		$.ajax({
			url:'${pageContext.request.contextPath}/teaching/assignmentGrading/rejectGrade?assignGradeId='+assignGradeId,
			type:'post',
			async:false,  // 设置同步方式
	        cache:false,
			success:function(data){
				//修改该tr下作业提交状态td中的font的html内容
				$(obj).parent().parent().parent().find("#isOperated").html('<font color="red">被驳回</font>');
				document.getElementById("finalScore_"+num+"").style.display = "none";
			}
		}); 
	}
	
</script>
<style>
.clearfix{
	clear:both;
}
</style>
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
								<a class="btn btn-new" href="${pageContext.request.contextPath}/tcoursesite/assignment/editAssignmentById?tCourseSiteId=${tCourseSite.id }&assignId=${tAssignment.id}&skillId=${skillId }">编辑</a>
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
		<c:if test="${flag!=0}">
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
		        <th>学生学号</th>
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
					       	<c:if test="${flag!=0}">
					       		<%--<c:if test="${current.userByGradeBy == null }">
					       			<a href="${pageContext.request.contextPath}/teaching/assignment/findTAssignmentGradeToMark?assignGradeId=${current.accessmentgradingId }&flag=${flag }">评分</a>
					       		</c:if>
					       		--%><c:if test="${current.finalScore != null }">
					       			已评分
					       		</c:if>
							</c:if> 
					       	<c:if test="${flag==0}">
					       	</c:if>
				       	</p> 
			       	</td>
	       			<td>${current.userByStudent.cname }</td>
	       			<td>${current.userByStudent.username }</td>
			       	<td>
			       		<c:if test="${flag!=0}">
				       		<c:if test="${current.finalScore != null }">
				       			<font id="finalScoreFont">${current.finalScore }</font>
				       		</c:if>
				       		<c:if test="${current.finalScore == null }">
				       			<font id="finalScoreFont">尚未批改</font>
				       		</c:if>
				       		/${current.TAssignment.TAssignmentAnswerAssign.score }
			       		</c:if>
			       		<c:if test="${flag==0}">
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
						<c:if test="${flag!=0}">
							<input type="text" id="comments_${i.count }" value="${current.comments }" onchange="grade(this,${i.count })"/>
						</c:if>
						<c:if test="${flag==0}">
							<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'yes' }">
								${current.comments }
							</c:if>
							<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'no' }">
								该作业成绩不公布
							</c:if>
						</c:if>
					</td>
					<td>
						<a href="javascript:void(0)" onclick="checkFile('${current.TAssignment.title}','${current.userByStudent.cname }',this)">查看</a>
						<%-- <input type="hidden" id="fileContent" value="${current.content }" /> --%>
						<div id="fileContent" style="display:none">
							${current.content }
						</div>
					</td>
					<td>
						<fmt:formatDate pattern="yyyy-MM-dd" value="${current.submitdate.time }" type="both"/>
					</td>
					<td>
						<span><fmt:formatDate pattern="yyyy-MM-dd" value="${current.gradeTime.time }" type="both"/></span>
					</td>
					<td>
						<c:if test="${flag!=0}">
						    <c:if test="${current.islate !=2 }">
						    	<input type="text" id="finalScore_${i.count }" style="width: 45px;" value="${current.finalScore }" onchange="grade(this,${i.count })" oninput="changeNumber(this,${current.TAssignment.TAssignmentAnswerAssign.score} )"/>
						    </c:if>
						    <c:if test="${current.islate ==2 }">
						    	${current.finalScore }
						    </c:if>
							<input type="hidden" id="assignGradeId" value="${current.accessmentgradingId }"/>
						</c:if>
						<c:if test="${flag==0}">
				        	<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'yes' }">
								${current.finalScore }
							</c:if>
							<c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent == 'no' }">
								该作业成绩不公布
							</c:if> 
				       	</c:if>
					</td>
					<td>
						<c:if test="${current.islate==0 }"><p id="isOperated" ><font color="green">正常提交</font></p></c:if>
						<c:if test="${current.islate==1 }"><p id="isOperated" ><font color="red">迟交</font></p></c:if>
						<c:if test="${current.islate==2 }"><p id="isOperated" ><font color="red">被驳回</font></p></c:if>
					</td>
					<td>
						<mytag:JspSecurity realm="update" menu="tAssignment">
							<c:if test="${flag==0}">
								<a href="javascript:void(0)" onclick="downloadOneFile(${current.accessmentgradingId},'downloadFileForStudent',${tCourseSite.id},'${current.gradeUrl}')" >下载</a>
							</c:if>
							<c:if test="${flag==1}">
								<%-- <a href="javascript:void(0)" onclick="readHistory(${current.userByStudent.username })">查看历史提交记录</a> --%>
								<a id="saveOrEdit_${i.count}" name="saveOrEdit_${i.count}" href="javascript:void(0)" onclick="saveScoreAndComment(${i.count})" style="display:block;float:left;">保存</a>
								<a id="editOrSave_${i.count}" name="editOrSave_${i.count}" href="javascript:void(0)" onclick="editScoreAndComment(${i.count})" style="display:none;float:left;">编辑</a>
								<a style="float:left;" href="javascript:void(0)" onclick="downloadOneFile(${current.accessmentgradingId},'downloadFileForTeacher',${tCourseSite.id},'${current.gradeUrl}')">下载</a>
								<a style="float:left;" href="javascript:void(0)" onclick="reject(this,${i.count },${current.islate })">驳回</a>
								<div class="clearfix"></div>
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
    	
    	<!--历史提交记录-->
	<div class="window_box hide fix zx2  " id="readHistory">
        <div class="window_con bgw b1 br3 rel bs10 w20">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
	            <div class="add_tit p20 bb f16">历史提交记录</div>
	            <div class="add_con p20 tc">
	               <table  id="stuHistory"> 
			         <thead>
		       			<tr>                   
		                   <th>作业标题</th>
		                   <th>学生姓名</th>
		                   <th>提交内容</th>
		                   <th>评语</th>
		                   <th>学生提交时间</th>
		                   <th>教师批改时间</th>
		                   <th>提交状态</th>
		                   <th>操作</th>
		               </tr>
		            </thead>
		            <tbody>
		              <c:forEach items="${map }" var="map1"  varStatus="i">
			           <c:forEach items="${map1.value }" var="current"  varStatus="j">
		   		            <tr class="ass_record" id="stu_${map1.key }">
					           <td>${current.TAssignment.title}</td>
	       			           <td>${current.userByStudent.cname }</td>
	       			           <td>
									<a href="javascript:void(0)" onclick="checkFileByDetail('${current.TAssignment.title}','${current.userByStudent.cname }',this)">查看</a>
									<div id="fileContentByDetail" style="display:none">
										${current.content }
									</div>
							   </td>
					           <td>${current.comments }</td>
					           <td>
						             <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${current.submitdate.time }" type="both"/>
					           </td>
					           <td>
						             <span><fmt:formatDate pattern="yyyy-MM-dd" value="${current.gradeTime.time }" type="both"/></span>
					           </td>
					           <td>
						             <c:if test="${current.islate==0 }"><font color="green">正常提交</font></c:if>
						             <c:if test="${current.islate==1 }"><font color="red">迟交</font></c:if>
						             <c:if test="${current.islate==2 }"><font color="red">被驳回</font></c:if>
					           </td>
					           <td>
						             <mytag:JspSecurity realm="check" menu="tAssignment">
						                  <a href="javascript:void(0)" onclick="downloadOneFile(${current.accessmentgradingId},'downloadFileForStudent',${tCourseSite.id},'${current.gradeUrl}')" >下载</a>
						             </mytag:JspSecurity>
					           </td>
				             </tr>
			            </c:forEach>
			           </c:forEach>
		             </tbody>
         	       </table>
				</div>
			</div>
		</div>
    	<!--历史提交记录-->
    	
    	<!--提交详情中的提交文字-->
	<div class="window_box hide fix zx2  " id="submitContentByDetail">
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
					       			<td id="gradeTitleByDetail"></td>
					       			<td id="gradeCnameByDetail" ></td>
					       			<td id="gradeContentByDetail"></td>
								</tr>
						</tbody>
					
					</table>
				</div>
			</div>
		</div>
	</div>
    	<!--提交详情中的提交文字-->
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
    
    function checkFile(title,cname,obj){
    
    	$("#submitContent").fadeIn(100);
    	var fileContent = $(obj).parent().find("#fileContent").html();
    	//alert(fileContent);
    	document.getElementById("gradeTitle").innerHTML=title;
    	document.getElementById("gradeCname").innerHTML=cname;
    	document.getElementById("gradeContent").innerHTML=fileContent;
    	
    }
    
	function readHistory(username){
		var stuNum = username;
		var stuAssRecord =document.getElementsByClassName("ass_record");
		for(var i=0;i<stuAssRecord.length;i++){ 
			if(stuAssRecord[i].id =="stu_"+stuNum){
				stuAssRecord[i].setAttribute("style","display:block");
			}else{
				stuAssRecord[i].setAttribute("style","display:none");
			}
		}
		$("#readHistory").fadeIn(100);
	}

	function checkFileByDetail(title,cname,obj){
	    
    	$("#submitContentByDetail").fadeIn(100);
    	var fileContentByDetail = $(obj).parent().find("#fileContentByDetail").html();
    	document.getElementById("gradeTitleByDetail").innerHTML=title;
    	document.getElementById("gradeCnameByDetail").innerHTML=cname;
    	document.getElementById("gradeContentByDetail").innerHTML=fileContentByDetail;
    	
    }
</script>
<!-- 下拉框的js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
</body>
</html>

