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
  		<script type="text/javascript"	src="${pageContext.request.contextPath}/js/browse.js"></script>
  		<!-- 下拉的样式结束 --> 
  		<!-- 打印开始 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
		<!-- 打印结束 -->

		<!-- 打印、导出组件 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/LodopFuncs.js"></script>
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
				<form name="form"
								action="${pageContext.request.contextPath}/newtimetable/viewTimetableGroupStudents?groupId=${groupId}&courseId=${courseId}&termId=${termId}&labId=${labId}&currpage=1"
								method="post">
					<table style="width:400px;">
						<tr>
							<td>
								<span>学号:&nbsp;</span>
								<select name="username" class="chzn-select" style="width:300px;">
									<option value="">请选择</option>
									<c:forEach items="${users}" var="curr">
										<c:if test="${username == curr.username }">
											<option value="${curr.username }" selected>${curr.cname}</option>
										</c:if>
										<c:if test="${username != curr.username }">
											<option value="${curr.username }">${curr.cname}</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<table style="width:400px;">
						<tr>
							<td>
								<span>班级:&nbsp;</span>
								<select name="selectClass" class="chzn-select" style="width:300px;">
									<option value="">请选择</option>
									<c:forEach items="${classes}" var="curr">
										<c:if test="${selectClass == curr.classNumber}">
											<option value="${curr.classNumber }" selected>${curr.className}</option>
										</c:if>
										<c:if test="${selectClass != curr.classNumber}">
											<option value="${curr.classNumber }">${curr.className}</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<table style="width:400px;">
						<tr>
							<td>
								<span>学院:&nbsp;</span>
								<select name="academy" class="chzn-select" style="width:300px;">
									<option value="">请选择</option>
									<c:forEach items="${academys}" var="curr">
									<c:if test="${academy == curr.academyNumber}">
										<option value="${curr.academyNumber}" selected>${curr.academyName}</option>
									</c:if>
									<c:if test="${academy != curr.academyNumber }">
										<option value="${curr.academyNumber}">${curr.academyName}</option>
									</c:if>	
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<table>
						<tr>
							<td>
								<button>查询</button>
								<button type="button" onclick="cancel()">取消</button>
							</td>
						</tr>
					</table>
					</form>
					<a class="r mt10 f18 poi" title="返回" onclick="returnToTimetable()"><button>返回</button></a>
					<a class="fa fa-print r mt10 mr2p f18 poi" title="打印" onclick="printPreview()"></a>
					<a class="fa fa-file-excel-o r mt10 mr1p f18 poi" title="导出" onclick="exportGroupStudents()"></a>
				</div>
				<div class="bgwo w100p ptb10">
					<table class="experimental_list_tab" cellspacing="0" style="margin:25px auto 0;" id="myShow">
						<tr>
							<th>序号</th>
							<th>学号</th>
							<th>姓名</th>
							<th>班级</th>
							<th>学院</th>
						</tr>
						<c:forEach items="${students}" var="curr" varStatus="i">
							<tr>
								<td class="timetable_tab_tit">${i.count}</td>
								<td>${curr.user.username }</td>
								<td>${curr.user.cname }</td>
								<td>${curr.user.schoolClasses.className }</td>
								<td>${curr.user.schoolAcademy.academyName }</td>
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
							   <option value="${pageContext.request.contextPath}/newtimetable/viewTimetableGroupStudents?currpage=${currpage}">${currpage}</option>
							   <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
					           <c:if test="${j.index!=currpage}">
					           <option value="${pageContext.request.contextPath}/newtimetable/viewTimetableGroupStudents?currpage=${j.index}">${j.index}</option>
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
		</div>
		<style>
			.experimental_list_tab td {
				text-align: center;
			}
		</style>
		<script type="text/javascript">
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
	document.form.action="viewTimetableGroupStudents?groupId=${groupId}&courseId=${courseId}&termId=${termId}&labId=${labId}&currpage=1";
    document.form.submit();
}

function previous(){
	var page=$("#currpage").val();
	if(page==1){
		page=1;
	}else{
		page=page-1;
	}
    document.form.action="viewTimetableGroupStudents?groupId=${groupId}&courseId=${courseId}&termId=${termId}&labId=${labId}&currpage="+page;
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
    document.form.action="viewTimetableGroupStudents?groupId=${groupId}&courseId=${courseId}&termId=${termId}&labId=${labId}&currpage="+page;
    document.form.submit();
}

function last(){
	var page=$("#totalpage").val();
	var tage=parseInt($("#tage").val());
    document.form.action="viewTimetableGroupStudents?groupId=${groupId}&courseId=${courseId}&termId=${termId}&labId=${labId}&currpage="+page;
    document.form.submit();
}

	function cancel(){
  				window.location.href="${pageContext.request.contextPath}/newtimetable/viewTimetableGroupStudents?groupId=${groupId}&courseId=${courseId}&termId=${termId}&labId=${labId}&currpage=1";
			}
			
			function returnToTimetable(){
				window.location.href="${pageContext.request.contextPath}/newtimetable/returnToTimetable?groupId=${groupId}&courseId=${courseId}&termId=${termId}&labId=${labId}";
			}
			
	function exportGroupStudents(){
		document.form.action="${pageContext.request.contextPath}/newtimetable/exportGroupStudents?groupId=${groupId}&&currpage=${currpage}";
   		document.form.submit();
	}
	
	function printPreview(){
		$('#myShow').jqprint();
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