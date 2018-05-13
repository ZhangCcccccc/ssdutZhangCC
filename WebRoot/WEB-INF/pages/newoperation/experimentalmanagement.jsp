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
		 <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/operation/newoperationoutline.js"></script>
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
								action="${pageContext.request.contextPath}/newoperation/experimentalmanagement?currpage=1"
								method="post" modelAttribute="operationOutline">
					<table style="width:400px;">
						<tr>
							<td>
								<span>实验大纲名称:</span>
								<form:select  path="labOutlineName" class="chzn-select"  style="width:300px;">
									<form:option value="">请选择</form:option>
									<c:forEach items="${ outlineNames}" var="curr">
										<form:option value="${curr.labOutlineName }">${curr.labOutlineName }</form:option>
									</c:forEach>
								</form:select>
							</td>
						</tr>
					</table>
					<%-- <table style="width:400px;">
						<tr>
							<td>
								<span>审核状态:</span>
								<form:select path="status" class="chzn-select"  style="width:300px;">
									<form:option value="">请选择</form:option>
									<form:option value="1">审核中</form:option>
									<form:option value="2">审核通过</form:option>
									<form:option value="3">审核拒绝</form:option>
								</form:select>
							</td>
						</tr>
					</table> --%>
					<table>
						<tr>
							<td>
								<button>查询</button>
								<button type="button" onclick="cancel()">取消</button>
							</td>
						</tr>
					</table>
					<sec:authorize ifNotGranted="ROLE_SUPERADMIN, ROLE_LABCENTERMANAGER">
						<button class="r mt7 mr2p" onclick="viewAllOperationOutlines()" type="button">查看全部实验大纲</button>
					</sec:authorize>
					<button class="r mt7 mr2p" onclick="newOperationOutline()" type="button">新建</button>
					<button class="r mt7">导入</button>
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
						<!-- <th>审核状态</th> -->
					</tr>
					<c:forEach items="${Outlinelist}" varStatus="i" var="s">
						<tr>
						<td class="tc">${i.count}</td>
						<td>${s.labOutlineName}</td>
						<td>${s.schoolCourseInfoByClassId.courseNumber}</td>
						<td>${s.schoolCourseInfoByClassId.courseName}</td>
						<td class="tc">${s.period }</td>
						<td class="tc">${s.COperationOutlineCredit.credit}</td>
						<td class="tc">${s.courseDescription }</td>
						<td>
							<c:forEach items="${s.CDictionarys}" var="curr">
								${curr.CName }&nbsp;
							</c:forEach>
						</td>
						<%-- <td class="tc">
						<!--<i class="fa fa-file-text-o" title="查看详情"></i>-->
						<a class="fa fa-search blue" title="查看详情" href="${pageContext.request.contextPath}/newoperation/viewOperationOutline?idkey=${s.id}&from=1&page=${currpage}"></a>
						<c:if test="${s.status eq 1 }">
							<a class="fa fa-edit" title="编辑" href="#"></a>
							<a class="fa fa-times" title="删除"  href="#"></a>
						</c:if>
						<c:if test="${s.status eq 2 }">
							<a class="fa fa-edit" title="编辑" href="${pageContext.request.contextPath}/newoperation/editoutline?idkey=${s.id}"></a>
							<a class="fa fa-times" title="删除"  href="#"></a>
						</c:if>
						<c:if test="${s.status eq 3 }">
							<a class="fa fa-edit" title="编辑" href="${pageContext.request.contextPath}/newoperation/editoutline?idkey=${s.id}"></a>
							<a class="fa fa-times" title="删除"  href="${pageContext.request.contextPath}/newoperation/delectuotline?idkey=${s.id}" onclick="return confirm('您确认删除吗？')"></a>
						</c:if>
						</td> --%>
						<td class="tc">
						<!--<i class="fa fa-file-text-o" title="查看详情"></i>-->
							<a class="fa fa-search blue" title="查看详情" href="${pageContext.request.contextPath}/newoperation/viewOperationOutline?idkey=${s.id}&from=1&page=${currpage}"></a>
							<a class="fa fa-edit" title="编辑" href="${pageContext.request.contextPath}/newoperation/editoutline?idkey=${s.id}"></a>
							<a class="fa fa-times" title="删除"  href="${pageContext.request.contextPath}/newoperation/delectuotline?idkey=${s.id}" onclick="return confirm('您确认删除吗？')"></a>
						</td>
						<%-- <c:if test="${s.status eq 1 }">
							<td class="tc yellow">审核中</td>
						</c:if>
						<c:if test="${s.status eq 2 }">
							<td class="tc green">审核通过</td>
						</c:if>
						<c:if test="${s.status eq 3 }">
							<td class="tc red">审核拒绝</td>
						</c:if>
						<c:if test="${s.status eq 0 || s.status eq null }">
							<td class="tc red">未提交</td>
						</c:if> --%>
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
							   <option value="${pageContext.request.contextPath}/newoperation/experimentalmanagement?currpage=${currpage}">${currpage}</option>
							   <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
					           <c:if test="${j.index!=currpage}">
					           <option value="${pageContext.request.contextPath}/newoperation/experimentalmanagement?currpage=${j.index}">${j.index}</option>
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
  				window.location.href="${pageContext.request.contextPath}/newoperation/experimentalmanagement?currpage=1";
			}
			function viewAllOperationOutlines(){
	window.location.href = "${pageContext.request.contextPath}/newoperation/listViewAllOperationOutlines?currpage=1";
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