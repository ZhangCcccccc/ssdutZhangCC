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
	</head>

	<body>
		<div id="bgheight">
			<script type="text/javascript">
				$(function() {
					$("#bgheight").height($(window).height() / 1);
				})
			</script>
			<div class="all_main_content">
				<div class="grey_line">
						<form:form name="form"
								action="${pageContext.request.contextPath}/newoperation/listOperationOutlinePermissions?currpage=1"
								method="post" modelAttribute="operationOutline">
					<table>
						<tr>
							<td>
								<span>学期:</span>
								<select  name="searchterm">
									<option value="">请选择</option>
									<c:forEach items="${schoolTerm}" var="curr">
										<c:if test="${curr.id eq cDictionary.id }">
											<option value="${curr.id }" selected="selected">${curr.CName }</option>
										</c:if>
										<c:if test="${curr.id ne cDictionary.id }">
											<option value="${curr.id }">${curr.CName }</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
							<td>
								<span>课程:</span>
								<form:select  path="labOutlineName">
									<form:option value="">请选择</form:option>
									<c:forEach items="${ outlineNames}" var="curr">
										<form:option value="${curr.labOutlineName }">${curr.labOutlineName }</form:option>
									</c:forEach>
								</form:select>
							</td>
							<td>
								<span>课序号:</span>
								<form:select path="schoolCourseInfoByClassId.courseNumber">
									<form:option value="">请选择</form:option>
									<c:forEach items="${ outlineNames}" var="curr">
										<form:option value="${curr.schoolCourseInfoByClassId.courseNumber}">${curr.schoolCourseInfoByClassId.courseNumber}</form:option>
									</c:forEach>
								</form:select>
							</td>
							<td>
								<button>查询</button>
								<button type="button" onclick="cancel()">取消</button>
							</td>
						</tr>
					</table>
				</form:form>
					<button class="r mt7 mr2p per_ready_btn" type="button">批量授权</button>
					<button class="r mt7 mr2p per_finish_btn hide" type="button" onclick="getChecked()">批量授权完成</button>
				</div>
				<div class="bgwo w100p ptb10">
					<table class="experimental_list_tab" cellspacing="0">
						<tr>
							<th>序号</th>
							<th>课序号</th>
							<th>课程编号</th>
							<th>课程名称</th>
							<th>学时</th>
							<th>学分</th>
							<th>类型</th>
							<th>课程性质</th>
							<th>修订教师
								<input class="permission_box r mt5 mr2" type="checkbox" id="allcheck" onclick="checkAll(this)"/></th>
						</tr>
						<c:forEach items="${Outlinelist}" varStatus="i" var="s">
						<tr>
						<td class="tc">${i.count}</td>
						<td>${s.schoolCourseInfoByClassId.courseNumber}</td>
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
								<select id="permittedTeacher${s.id}">
										<option value="">请选择</option>
										<c:forEach items="${teachers}" var="u">
											<c:if test="${s.userByPermittedTeacher.username eq u.username }">
												<option value="${u.username }" selected="selected">${u.cname }</option>
											</c:if>
											<c:if test="${s.userByPermittedTeacher.username ne u.username }">
												<option value="${u.username }">${u.cname }</option>
											</c:if>
										</c:forEach>
								</select>
								<input class="permission_box r" type="checkbox" value="${s.id}" name="checkTeacher"/>
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
							   <option value="${pageContext.request.contextPath}/newoperation/listOperationOutlinePermissions?currpage=${currpage}">${currpage}</option>
							   <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
					           <c:if test="${j.index!=currpage}">
					           <option value="${pageContext.request.contextPath}/newoperation/listOperationOutlinePermissions?currpage=${j.index}">${j.index}</option>
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
			$(".per_ready_btn").click(
				function() {
					$(".per_ready_btn").removeClass("block").addClass("hide");
					$(".per_finish_btn").removeClass("hide").addClass("block");
					$(".permission_box").removeClass("hide").addClass("block");
				}
			);
			$(".per_finish_btn").click(
				function() {
					$(".per_finish_btn").removeClass("block").addClass("hide");
					$(".per_ready_btn").removeClass("hide").addClass("block");
					$(".permission_box").removeClass("block").addClass("hide");
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
			
			//首页
function first(){
	document.form.action="listOperationOutlinePermissions?currpage=1";
    document.form.submit();
}

function previous(){
	var page=$("#currpage").val();
	if(page==1){
		page=1;
	}else{
		page=page-1;
	}
    document.form.action="listOperationOutlinePermissions?currpage="+page;
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
    document.form.action="listOperationOutlinePermissions?currpage="+page;
    document.form.submit();
}

function last(){
	var page=$("#totalpage").val();
	var tage=parseInt($("#tage").val());
    document.form.action="listOperationOutlinePermissions?currpage="+page;
    document.form.submit();
}
function cancel(){
  				window.location.href="${pageContext.request.contextPath}/newoperation/listOperationOutlinePermissions?currpage=1";
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
		       	check_val.push(obj[k].value);
		       	if(check_teacher == "")
		       	{
		       		check_teacher=$("#permittedTeacher"+obj[k].value).val();
		       	}
		       }
		  }
		  if(flag == 0){
		  	alert("请至少选择一条记录!");
		  }else{
		  	$.ajax({
			         url:"${pageContext.request.contextPath}/newoperation/setOperationOutlinePermission",
			         dataType:"json",
			         type:'GET',
			         data:{outlineids:check_val.join(","),teacher:check_teacher},
			         complete:function(result)
			         {
			         	 window.location.reload();
			          }
			});
		  }
	}	
	function checkAll(obj){
		var checkboxs = document.getElementsByName("checkTeacher");
			for(var i=0; i<checkboxs.length ; i++){  
            var ck =  checkboxs[i];  
            if(obj.checked == true){  
                ck.checked = true;  
            }else{  
                ck.checked = false;  
            }  
        	}  
	}
		</script>
	</body>

</html>