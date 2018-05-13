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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
		<!-- 下拉框的样式 -->
 		 <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  		<link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/chosen/chosen.css" />
  		<!-- 下拉的样式结束 --> 
  		<style type="text/css">
  			.course_btn{
  				    height: 30px;
				    display: inline-block;
				    line-height: 30px;
				    padding: 0 8px;
				    border-radius: 4px;
  			}
  		</style>
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
				<form:form name="queryForm" action="${pageContext.request.contextPath}${url}?currpage=1" method="post" modelAttribute="tCourseSite">
					<table >
						<tr>
							<td>
								<span>课程编号:&nbsp;</span>
								<form:input path="schoolCourseInfo.courseNumber" />
							</td>
					</table>
					<table >
						<td>
							<span>课程名称:&nbsp;</span>
							<input name="title" type="text"/>
						</td>
					</table>
					<table >
						<tr>
							<td>
								<span>课程组编号:&nbsp;</span>
								<form:input path="schoolCourse.courseNo"/>
							</td>
					</table>
					<table>
						<td>
							<button>查询</button>
							<button type="button" onclick="cancel()">取消</button>
						</td>
						</tr>
					</table>
					</form:form>
					<div class="list_chose_div my_btn " style="float:right;width:auto;">
					    <a  class="course_btn <c:if test='${empty allCourseFlag }'>selected_btn</c:if>" href="${pageContext.request.contextPath}/tms/courseList?currpage=1" >我的课程</a>
					    <a class="course_btn <c:if test='${allCourseFlag==1 }'>selected_btn</c:if>" href="${pageContext.request.contextPath}/tms/courseList?currpage=1&allCourseFlag=1" >所有课程</a>
						<%--<a href="${pageContext.request.contextPath}/tms/coursesite/newCourseSite" >新建课程</a>
					--%></div>
				</div>
				<div class="bgwo w100p ptb10">
					<table class="experimental_list_tab" cellspacing="0">
						<tr>
							<th>课程编号</th>
							<th>课程名称</th>
							<th>课序号</th>
							<th>教师名称</th>
							<th class="rel"><input class="permission_box" style="position:absolute;left:2px;top:10px;" type="checkbox" />操作</th>
						</tr>
						<c:forEach items="${sites }" var="curr" varStatus="i">
						<c:forEach items="${teacherMap }" var="item">
						<%--<c:forEach items="${courseDetailList }" var="curr1">
						--%><c:if test="${item.key==curr.id }">
							<tr>
								<td>${curr.schoolCourse.schoolCourseInfo.courseNumber }</td>
								<td>${curr.title}</td>
								
								<%--<td>
								<c:if test="${curr1[6] eq 0}">
									${fn:substring(curr1[1], 0, fn:indexOf(curr1[1], "-(") ) }
								</c:if>
								<c:if test="${curr1[6] eq 1}">
									${curr1[4] }
								</c:if>
								</td>
								
								--%><td>${curr.schoolCourse.courseCode}</td>
								<td>
								<c:forEach items="${item.value }" var="va" varStatus="va1">
									<c:if test="${!va1.last}">
										${va.cname },
									</c:if>
									<c:if test="${va1.last}">
										${va.cname }
									</c:if>
								</c:forEach>
								</td>
								<td><a href="${pageContext.request.contextPath}/tcoursesite?tCourseSiteId=${curr.id }">详情</a></td>
							</tr>
							</c:if>
							</c:forEach>
						</c:forEach>
						</table>
					<div class="page-message">
						<a class="btn" href="${pageContext.request.contextPath}/tms/courseList?currpage=${pageModel.lastPage}&allCourseFlag=${allCourseFlag}">末页</a>
						<a class="btn" href="${pageContext.request.contextPath}/tms/courseList?currpage=${pageModel.nextPage}&allCourseFlag=${allCourseFlag}">下一页</a>
						<div class="page-select">
							<div class="page-word">页</div>
							<form>
								<select  class="page-number"onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
							   <option value="${pageContext.request.contextPath}/tms/courseList?currpage=${currpage}&allCourseFlag=${allCourseFlag}">${currpage}</option>
							   <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
					           <c:if test="${j.index!=currpage}">
					           <option value="${pageContext.request.contextPath}/tms/courseList?currpage=${j.index}&allCourseFlag=${allCourseFlag}">${j.index}</option>
					           </c:if>
					           </c:forEach>
					           </select>
							</form>
							<div class="page-word">第</div>
						</div>
						<a class="btn" href="${pageContext.request.contextPath}/tms/courseList?currpage=${pageModel.previousPage}&allCourseFlag=${allCourseFlag}">上一页</a>
						<a class="btn" href="${pageContext.request.contextPath}/tms/courseList?currpage=${pageModel.firstPage}&allCourseFlag=${allCourseFlag}">首页</a>
						<div class="p-pos">
							${totalRecords }条记录 • 共${pageModel.totalPage}页
						</div>
				</div>
			</div>
		</div>

		</div>
		<script type="text/javascript">
		$(function (){
			$.cookies.del("mysel"); 
		})
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
			
	function viewAppointments(type,id){
	 $.ajax({
			url:"${pageContext.request.contextPath}/self/setTurnTypeSession",
			data:{sessionType:"timetable"},
			type:"POST",
			success:function(data){
				if(type ==1)
				{
				window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#${pageContext.request.contextPath}/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectCourseDetailNo="+id;
				}
				if(type ==2)
				{
				window.location.href="${pageContext.request.contextPath}/test?labCenterId=-1&selected_labCenter=-1#${pageContext.request.contextPath}/timetable/timetableAdminIframeNew?currpage=1&id=-1&status=-1&selectMergeId="+id;
				}
			}
		});
	}
function cancel(){
  				window.location.href="${pageContext.request.contextPath}/tms/courseList?currpage=1";
			}
			
			function getChecked(){
		  var obj = document.getElementsByName("checkCourse");
		  var check_val = [];
		  var flag = 0;
		  for(k in obj){
		      if(obj[k].checked)
		      {
		      	flag = 1;
		       	check_val.push($("#detailNo"+obj[k].value).val());
		       }
		  }
		  if(flag == 0){
		  	alert("请至少选择一条记录!");
		  }else{
		  	$.ajax({
			         url:"${pageContext.request.contextPath}/newtimetable/mergeCourses",
			         dataType:"json",
			         type:'GET',
			         data:{detailNos:check_val.join(",")},
			         complete:function(result)
			         {
			         	 window.location.reload();
			          }
			});
		  }
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
				
				      '.chzn-select'           : {width:"70%"},
				
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