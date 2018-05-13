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
								action="${pageContext.request.contextPath}/newtimetable/listMySchedule?currpage=1"
								method="post" modelAttribute="schoolCourseDetail">
					<table style="width:400px;">
						<tr>
							<td>
								<span>学期:&nbsp;</span>
								<form:select  path="schoolTerm.id" class="chzn-select" style="width:300px;">
									<form:option value="">请选择</form:option>
									<c:forEach items="${schoolTerms}" var="curr">
									<c:if test="${curr.id eq termId}">
										<form:option value="${curr.id}" selected="selected">${curr.termName }</form:option>
									</c:if>
									<c:if test="${curr.id ne termId}">
										<form:option value="${curr.id}">${curr.termName }</form:option>
									</c:if>
									</c:forEach>
								</form:select>
							</td>
					</table>
					<table style="width:400px;">
						<td>
							<span>课程:&nbsp;</span>
							<select path="schoolCourse.courseNo" name="schoolCourse.courseNo" class="chzn-select" style="width:300px;">
									<option value="">请选择</option>
									<c:forEach items="${detailListAll}" var="curr">
									<c:if test="${courseId == curr[1]}">
										<option value="${curr[1]}" selected>${curr[2]}${curr[1]}</option>
									</c:if>
									<c:if test="${courseId != curr[1] }"> 
										<option value="${curr[1]}">${curr[2]}${curr[1]}</option>
									</c:if>	
									</c:forEach>
								</select>
						</td>
					</table>
					<table style="width:400px;">
						<td>
							<span>课序号:&nbsp;</span>
							<form:select  path="courseNumber" class="chzn-select" style="width:300px;">
									<form:option value="">请选择</form:option>
									<c:forEach items="${detailListAll}" var="curr">
										<c:if test="${curr[6] eq 0}">
											<form:option value="${curr[1]}">${fn:substring(curr[1], 0, fn:indexOf(curr[1], "-(") ) }</form:option>
										</c:if>
										<c:if test="${curr[6] eq 1}">
											<form:option value="${curr[1]}">${curr[4]}</form:option>
										</c:if>
									</c:forEach>
							</form:select>
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
					
					<form:form name="pageForm" >
					<input type="hidden" name="currpage" value="${currpage}" id="currpage" />
					</form:form>
					
					<button class="r mt7 mr2p per_ready_btn">合班</button>
					<button class="r mt7 mr2p per_finish_btn hide" type="button" onclick="getChecked()">合班确定</button>
				</div>
				<div class="bgwo w100p ptb10">
					<table class="experimental_list_tab" cellspacing="0">
						<tr>
							<th>序号</th>
							<th>课程编号</th>
							<th>课程名称</th>
							<th>课序号</th>
							<th>课程组编号</th>
							<th>学生</th>
							<th class="rel"><input class="permission_box" style="position:absolute;left:2px;top:10px;" type="checkbox" />操作</th>
						</tr>
						<c:forEach items="${courseDetailList }" var="curr" varStatus="i">
							<tr>
								<td class="tc">${i.count }</td>
								<td>
									<c:if test="${curr[6] eq 0}">
										${curr[4] }
									</c:if>
									<c:if test="${curr[6] eq 1}">
										<c:set var="ss" value="${curr[11]}"></c:set>
										${fn:substring(ss, 0,fn:length(ss)-2 ) }
									</c:if>
								</td>
								<td>${curr[2]}</td>
								<td class="tc">
								<c:if test="${curr[6] eq 0}">
									${fn:substring(curr[1], 0, fn:indexOf(curr[1], "-(") ) }
								</c:if>
								<c:if test="${curr[6] eq 1}">
									${curr[4] }
								</c:if>
								</td>
								<td class="tc">${curr[1]}<input type="hidden" value="${curr[3] }" id="detailNo${i.count}"/> </td>
								<td class="tc"><a href='javascript:void(0)'
		                                       onclick='listTimetableStudents("${curr[3]}",${curr[6]})'>名单：</a>${curr[5]}</td>
								<td class="tc rel">
									<c:if test="${curr[6] eq 0}"><!-- 非合班 -->
										<input class="permission_box" style="position:absolute;left:3px;top:7px;" type="checkbox" value="${i.count}" name="checkCourse"/>
										<c:if test="${curr[14] eq 0}"><!-- 无排课记录 -->
											<a href="${pageContext.request.contextPath}/newtimetable/doSpecializedBasicCourseTimetable?courseDetailNo=${curr[3]}">
											<button class="ma schedule_btn">排课</button></a>
										</c:if>
										<c:if test="${curr[14] ne 0}"><!-- 有排课记录 -->
											<a href="${pageContext.request.contextPath}/newtimetable/doSpecializedBasicCourseTimetable?courseDetailNo=${curr[3]}">
											<button class="ma schedule_btn">正在排课</button></a>
											<a href="javascript:void(0);" onclick="viewAppointments(1,'${curr[3]}')">
											<button class="ma schedule_btn">查看排课</button></a>
										</c:if>
									</c:if>
									<c:if test="${curr[6] eq 1}"><!-- 合班 -->
										<c:if test="${curr[14] eq 0}"><!-- 无排课记录 -->
											<a href="${pageContext.request.contextPath}/newtimetable/doMergeCourseTimetable?mergeId=${curr[0]}">
											<button class="ma schedule_btn">排课</button></a>
										</c:if>
										<c:if test="${curr[14] ne 0}"><!-- 有排课记录 -->
											<a href="${pageContext.request.contextPath}/newtimetable/doMergeCourseTimetable?mergeId=${curr[0]}">
											<button class="ma schedule_btn">正在排课</button></a>
											<a href="javascript:void(0);" onclick="viewAppointments(2,${curr[0]})">
											<button class="ma schedule_btn">查看排课</button></a>
										</c:if>
									</c:if>
									<a href="javascript:void(0);" onclick="checkIsExit('${curr[12]}','${curr[1]}','${curr[3]}')"><button class="ma schedule_btn">生成教学对应课程</button></a>
								</td>
							</tr>
						</c:forEach>
						</table>
					<div class="page-message">
						<a class="btn" onclick="last();">末页</a> <input type="hidden"
									value="${pageModel.lastPage}" id="totalpage" />&nbsp;
									<a class="btn" onclick="next();">下一页</a>
						<div class="page-select">
							<div class="page-word">页</div>
							<form>
								<select  class="page-number"onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
							   <option value="${pageContext.request.contextPath}/newtimetable/listMySchedule?currpage=${currpage}">${currpage}</option>
							   <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
					           <c:if test="${j.index!=currpage}">
					           <option value="${pageContext.request.contextPath}/newtimetable/listMySchedule?currpage=${j.index}">${j.index}</option>
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
						<!-- 查看学生名单 -->
						<div id="doSearchStudents" class="easyui-window" title="查看学生名单" modal="true"	closed="true" iconCls="icon-add" style="width:1000px;height:500px">
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
			
			
			function first(){
				document.form.action="listMySchedule?currpage=1";
			    document.form.submit();
			}

			function previous(){
				var page=$("#currpage").val();
				if(page==1){
					page=1;
				}else{
					page=page-1;
				}
			    document.form.action="listMySchedule?currpage="+page;
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
			    document.form.action="listMySchedule?currpage="+page;
			    document.form.submit();
			}
			
			function last(){
				var page=$("#totalpage").val();
				var tage=parseInt($("#tage").val());
			    document.form.action="listMySchedule?currpage="+page;
			    document.form.submit();
			}

			function getPage1(url){
				document.pageForm.action=url;
				document.pageForm.submit();
			}
	
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
  				window.location.href="${pageContext.request.contextPath}/newtimetable/listMySchedule?currpage=1";
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
			   
			 /*
 			 *查看学生名单
 			 */
			function listTimetableStudents(courseDetailNo,merge) {
				var sessionId = $("#sessionId").val();
				var con = '<iframe scrolling="yes" id="message1" frameborder="0"  src="${pageContext.request.contextPath}/timetable/openSearchStudent?courseDetailNo='
			    + courseDetailNo + '&merge='+merge+'" style="width:100%;height:100%;"></iframe>'
				$('#doSearchStudents').html(con);
				$("#doSearchStudents").show();
				//获取当前屏幕的绝对位置
   			 var topPos = window.pageYOffset;
    			//使得弹出框在屏幕顶端可见
   			 $('#doSearchStudents').window({left:"px", top:topPos+"px"});
				$('#doSearchStudents').window('open');
			}

		</script>
			<script src="${pageContext.request.contextPath}/chosen/chosen_width.jquery.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript"></script>
							
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
				function checkIsExit(courseNo,groupNumber,courseDetailNo){
					//判断是否已经生成对应教学课程
					 $.ajax({
							url:"${pageContext.request.contextPath}/tcoursesite/skill/isTcourseSiteExit",
							data:{"courseNo":courseNo,"groupNumber":groupNumber,"courseDetailNo":courseDetailNo},
							type:"POST",
							dataType:"html",
							success:function(data){
							if(data=="true"){
								//没有生成过相关的课程记录
								location.href="${pageContext.request.contextPath}/tcoursesite/createTCourseSiteNew?type=1&courseNo="+courseNo;
								alert("课程生成完毕");
							}else{
								alert("已生成，请勿重复生成！");
							}
							}
						});
				}
				</script>

	</body>

</html>