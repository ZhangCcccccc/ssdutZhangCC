<%@page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<fmt:setBundle basename="bundles.projecttermination-resources" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<title></title>
		<meta name="Generator" content="gvsun">
		<meta name="decorator" content="new"/>
		<meta name="Author" content="chenyawen">
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
								action="${pageContext.request.contextPath}/newoperation/listViewAllOperationOutlines?currpage=1"
								method="post" modelAttribute="operationOutline">
					<table style="width:700px;">
						<tr>
							<td>
								<span>实验大纲名称:</span>
								<form:select  path="labOutlineName" class="chzn-select"  style="width:300px;">
									<form:option value="">请选择</form:option>
									<c:forEach items="${ outlineNames}" var="curr">
										<form:option value="${curr.labOutlineName }">${curr.labOutlineName }</form:option>
									</c:forEach>
								</form:select>
							<td>
								<button>查询</button>
								<button type="button" onclick="cancel()">取消</button>
							</td>
						</tr>
					</table>
				</form:form>
				</div>
				<div class="bgwo w100p ptb10">
				<table class="experimental_list_tab" cellspacing="0">
					<tr>
						<th>序号</th>
						<th>大纲名称</th>
						<th>课程编号</th>
						<th>课程名称</th>
						<th>学时</th>
						<th>学分</th>
						<th>课程类型</th>
						<th>课程性质</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${Outlinelist}" varStatus="i" var="s">
						<tr>
						<td class="tc">${i.count}</td>
						<td>${s.labOutlineName}</td>
						<td>${s.schoolCourseInfoByClassId.courseNumber}</td>
						<td>${s.schoolCourseInfoByClassId.courseName}</td>
						<td class="tc">${s.period }</td>
						<td class="tc">${s.COperationOutlineCredit.id}</td>
						<td class="tc">${s.courseDescription }</td>
						<td>
							<c:forEach items="${s.CDictionarys}" var="curr">
								${curr.CName }&nbsp;
							</c:forEach>
						</td>
						<td class="tc">
							<a class="fa fa-search blue" title="查看详情" href="${pageContext.request.contextPath}/newoperation/viewOperationOutline?idkey=${s.id}&from=5&page=${currpage}"></a>
						</td>
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
							   <option value="${pageContext.request.contextPath}/newoperation/listViewAllOperationOutlines?currpage=${currpage}">${currpage}</option>
							   <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
					           <c:if test="${j.index!=currpage}">
					           <option value="${pageContext.request.contextPath}/newoperation/listViewAllOperationOutlines?currpage=${j.index}">${j.index}</option>
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
			
			function newOperationOutline(){
				window.location.href="${pageContext.request.contextPath}/newoperation/newoperationproject";
			}
			
			function cancel(){
  				window.location.href="${pageContext.request.contextPath}/newoperation/listViewAllOperationOutlines?currpage=1";
			}
		//首页
function first(){
	document.form.action="listViewAllOperationOutlines?currpage=1";
    document.form.submit();
}

function previous(){
	var page=$("#currpage").val();
	if(page==1){
		page=1;
	}else{
		page=page-1;
	}
    document.form.action="listViewAllOperationOutlines?currpage="+page;
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
    document.form.action="listViewAllOperationOutlines?currpage="+page;
    document.form.submit();
}

function last(){
	var page=$("#totalpage").val();
	var tage=parseInt($("#tage").val());
    document.form.action="listViewAllOperationOutlines?currpage="+page;
    document.form.submit();
}

function viewAllOperationOutlines(){
	
}
		</script>
<!-- 下拉框的js -->
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