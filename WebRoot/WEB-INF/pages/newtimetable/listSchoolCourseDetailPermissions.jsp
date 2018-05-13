<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<title></title>
		<meta name="Generator" content="gvsun">
		<meta name="Author" content="chenyawen">
		 <meta name="decorator" content="new"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="Keywords" content="">
		<meta name="Description" content="">
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/lib.css" />
		<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
		
		<!-- 下拉框的样式 -->
 		 <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  		<link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/chosen/chosen.css" />
  		<!-- 下拉的样式结束 --> 
	</head>

	<body>
		<div id="bgheight">
			<script type="text/javascript">
				$(function() {
					$("#bgheight").height($(window).height() / 1);
				})
			</script>
			<div class="all_main_content">
				<div class="gray_line">
				<form:form name="form"
								action="${pageContext.request.contextPath}/newtimetable/listSchoolCourseDetailPermissions?currpage=1"
								method="post" modelAttribute="schoolCourseDetail">
					<table style="width:400px;">
						<tr>
							<td>
								<span>学期:&nbsp;</span>
								<form:select  path="schoolTerm.id"  class="chzn-select" style="width:300px;">
									<form:option value="">请选择</form:option>
									<c:forEach items="${schoolTerms}" var="curr">
										<form:option value="${curr.id}">${curr.termName }</form:option>
									</c:forEach>
								</form:select>
							</td>
						</tr>
					</table>
					<table style="width:400px;">
						<tr>
						<td>
							<span>课程:&nbsp;</span>
							<form:select  path="courseName"  class="chzn-select" style="width:300px;">
									<form:option value="">请选择</form:option>
									<c:forEach items="${detailListAll}" var="curr">
										<form:option value="${curr.schoolCourse.schoolCourseInfo.courseName}">${curr.schoolCourse.schoolCourseInfo.courseName}</form:option>
									</c:forEach>
							</form:select>
						</td>
						</tr>
					</table>
					<table style="width:400px;">
						<tr>
						<td>
							<span>课序号:&nbsp;</span>
							<form:select  path="schoolCourse.courseNo"  class="chzn-select" style="width:300px;">
									<form:option value="">请选择</form:option>
									<c:forEach items="${detailListAll}" var="curr">
										<form:option value="${curr.schoolCourse.courseNo}">${fn:substring(curr.schoolCourse.courseNo, 0, fn:indexOf(curr.schoolCourse.courseNo, "-(") ) }</form:option>
									</c:forEach>
							</form:select>
						</td>
						</tr>
					</table>
					<table>
						<td>
							<button>查询</button>
							<button type="button" onclick="cancel()">取消</button>
						</td>
						</tr>
					</table>
					</form:form>
					<%--<button class="r mt7 mr2p per_ready_btn" type="button">批量授权</button>--%>
					<%--<button class="r mt7 mr2p per_finish_btn hide" type="button" onclick="getChecked()">批量授权完成</button>--%>
				</div>
				<div class="bgwo w100p ptb10">
					<table class="experimental_list_tab" cellspacing="0">
						<tr>
							<th>序号</th>
							<th>课程编号</th>
							<th>课程名称</th>
							<th>课序号</th>
							<th>课程组编号</th>
							<th>课程负责人</th>
							<th>任课教师</th>
							<%--<th>排课教师</th>--%>
						</tr>
						<c:forEach items="${courseDetailList }" var="curr" varStatus="i">
							<tr>
								<td class="tc">${i.count+(currpage-1)*pageSize}</td>
								<td>${curr.schoolCourse.schoolCourseInfo.courseNumber  }</td>
								<td>${curr.courseName}</td>
								<td class="tc">${fn:substring(curr.schoolCourse.courseNo, 0, fn:indexOf(curr.schoolCourse.courseNo, "-(") ) }</td>
								<td class="tc">${curr.schoolCourse.courseNo }<input type="hidden" value="${curr.courseDetailNo }" id="detailNo${i.count}"/></td>
								<td class="tc">${curr.user.cname }</td>
								<td class="tc ovh">
									<c:forEach items="${curr.users }" var="u">
										<input class="permission_btn" type="button" value="${u.cname }" title="选择教师">
									</c:forEach>
								</td>
								<%--<td class="tc">
									<select id="scheduleTeacher${i.count}" onchange="changeScheduleTeacher(${i.count})">
										<option value="">请选择</option>
										<c:forEach items="${curr.users }" var="u">
											<c:if test="${curr.userByScheduleTeacher.username eq u.username }">
												<option value="${u.username }" selected="selected">${u.cname }</option>
											</c:if>
											<c:if test="${curr.userByScheduleTeacher.username ne u.username }">
												<option value="${u.username }">${u.cname }</option>
											</c:if>
										</c:forEach>
									</select>
									<input class="permission_box r" type="checkbox" value="${i.count}" name="checkTeacher"/>
								</td>--%>
							</tr>
						</c:forEach>
					</table>
					<div class="page-message">
						<a class="btn" onclick="last();">末页</a> <input type="hidden"
									value="${pageModel.lastPage}" id="totalpage" />&nbsp;
									<input
									type="hidden" value="${currpage}" id="currpage" />
						<a class="btn" onclick="next();">下一页</a>
						<div class="page-select">
							<div class="page-word">页</div>
							<form>
								<select  class="page-number"onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
							   <option value="${pageContext.request.contextPath}/newtimetable/listSchoolCourseDetailPermissions?currpage=${currpage}">${currpage}</option>
							   <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
					           <c:if test="${j.index!=currpage}">
					           <option value="${pageContext.request.contextPath}/newtimetable/listSchoolCourseDetailPermissions?currpage=${j.index}">${j.index}</option>
					           </c:if>
					           </c:forEach>
					           </select>
							</form>
							<div class="page-word">第</div>
						</div>
						<a class="btn" onclick="previous();">上一页</a>
						<a class="btn" onclick="first();">首页</a>
						<div class="p-pos">
							${totalRecords }条记录 • 共${pageModel.totalPage}页
						</div>
				</div>
			</div>
		</div>

		</div>
		<script type="text/javascript">
			$(".per_ready_btn").click(
				function() {
					$(".per_ready_btn").removeClass("block").addClass("hide");
					$(".per_finish_btn").removeClass("hide").addClass("block");
					$(".permission_box").removeClass("hide").addClass("block");
				}
			);
			/*$(".per_finish_btn").click(
				function() {
					$(".per_finish_btn").removeClass("block").addClass("hide");
					$(".per_ready_btn").removeClass("hide").addClass("block");
					$(".permission_box").removeClass("block").addClass("hide");
				}
			);*/
			$(".quickbtn").click(
				function() {
					$(".quick_above").addClass("block");
				}
			);
			$(document).bind("click", function() {
				$('.quick_above').removeClass("block");
			});
			$(".quickbtn").click(function(event) {
				event.stopPropagation();

			});
			$(".quick_above").click(function(event) {
				event.stopPropagation();

			});
			$(window).scroll(function() {
				$(".quick_above").removeClass("block");
			});
			
			
//首页
function first(){
	document.form.action="listSchoolCourseDetailPermissions?currpage=1";
    document.form.submit();
}

function previous(){
	var page=$("#currpage").val();
	if(page==1){
		page=1;
	}else{
		page=page-1;
	}
    document.form.action="listSchoolCourseDetailPermissions?currpage="+page;
    document.form.submit();
}

function next(){
	var totalpage=$("#totalpage").val();
	var page=parseInt($("#currpage").val());
	if(page==totalpage){
		page=totalpage;
	}else{
		page=page+1;
	}
    document.form.action="listSchoolCourseDetailPermissions?currpage="+page;
    document.form.submit();
}

function last(){
	var page=$("#totalpage").val();
	var tage=parseInt($("#tage").val());
    document.form.action="listSchoolCourseDetailPermissions?currpage="+page;
    document.form.submit();
}

	function cancel(){
  				window.location.href="${pageContext.request.contextPath}/newtimetable/listSchoolCourseDetailPermissions?currpage=1";
			}
	function getChecked(){
		  var obj = document.getElementsByName("checkTeacher");
		  var check_val = [];
		  var check_teacher ="";
		  var flag = 0;
		  for(k in obj){
		      if(obj[k].checked)
		      {
		      	flag = 1;
		       	check_val.push($("#detailNo"+obj[k].value).val());
		       	if(check_teacher == "")
		       	check_teacher = $("#scheduleTeacher"+obj[k].value).val();
		       }
		  }
		  if(flag == 0){
		  	alert("请至少选择一条记录!");
		  }else{
		  	$.ajax({
			         url:"${pageContext.request.contextPath}/newtimetable/setSchedulePermission",
			         dataType:"json",
			         type:'GET',
			         data:{detailNos:check_val.join(","),teacher:check_teacher},
			         complete:function(result)
			         {
			         	 window.location.reload();
			          }
			});
		  }
	}	
	
	
	function changeScheduleTeacher(number){
		var teacher = $("#scheduleTeacher"+number).val();
		var detailNo = $("#detailNo"+number).val();
		$.ajax({
			         url:"${pageContext.request.contextPath}/newtimetable/changeScheduleTeacher",
			         dataType:"json",
			         type:'GET',
			         data:{detailNo:detailNo,teacher:teacher},
			         complete:function(result)
			         {
			         	 
			          }
			});
	}	
		</script>
		
		<script
						src="${pageContext.request.contextPath}/chosen/chosen_width.jquery.js"
						type="text/javascript"></script>

					<script
						src="${pageContext.request.contextPath}/chosen/docsupport/prism.js"
						type="text/javascript" charset="utf-8"></script>
							
					<script type="text/javascript">

				    var config = {
				
				      '.chzn-select'           : {width:"70%", search_contains : true},
				
				      '.chzn-select-deselect'  : {allow_single_deselect:true},
				
				      '.chzn-select-no-single' : {disable_search_threshold:10},
				
				      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
				
				      '.chzn-select-width'     : {width:"70%"}
				
				    }
				
				    for (var selector in config) {
				
				      $(selector).chosen(config[selector]);
				
				    }
				
				</script>
	</body>

</html>