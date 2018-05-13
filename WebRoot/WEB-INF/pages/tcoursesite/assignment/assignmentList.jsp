<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />   
<head>
<meta name="decorator" content="iframe" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
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
	
	function checkSubmitNumber(number){
		if(number==0){
			alert("尚未有提交内容，无法查看！");
			return false;
		}
	}
	
	/**
	function downloadFile(number,id){
		if(number==0){
			alert("尚无人提交！");
			return false;
		}
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/teaching/assignment/downloadAssignment",
			data: {'assignId':id},
			dataType:'json',
			success:function(data){
				alert(data)
			}
		});
	}
	*/
	function downloadFile(number,id){
		var input = "<input type='hidden' name='assignId' value='"+ id +"' />";
		var html = "<form action='${pageContext.request.contextPath}/teaching/assignment/downloadAssignment' method='post'>"+input+"</form>"; 
		jQuery(html).appendTo("body").submit().remove();
		/**
		$("#assignId").val(id);
		document.form.submit();
		*/
	}
</script>
</head>
<body>

<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent0">
<!-- 查询、导出、打印 -->
<div class="content-box">
	<%--<form name="form" action="${pageContext.request.contextPath}/teaching/assignment/downloadAssignment" method='post'>
		<input type="hidden" name="assignId" id="assignId" value=""/>
	</form>	
	--%><table  id="my_show"> 
		<thead>
		    <tr>                   
		        <th>作业标题</th>
		        <th>状态</th>
		        <th>开始时间</th>
		        <th>截止时间</th>
		        <th>提交总数/未批改数</th>
		        <th>评分方式</th>
		        <th>排序</th>
		        <th>操作</th>
		    </tr>
		</thead>
		<tbody>
			<c:forEach items="${viewTAssignments }" var="current"  varStatus="i">
		   		<tr>
		       		<td>${current.title}<br>
		       		<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag!=0}">	
		       			<mytag:JspSecurity realm="update" menu="tAssignment">
					        <a href="${pageContext.request.contextPath}/teaching/assignment/updateAssignmentById?assignId=${current.id}">编辑</a>|
					        <a href="${pageContext.request.contextPath}/teaching/assignment/assignmentGradingList?assignmentId=${current.id}&flag=${flag }" onclick="return checkSubmitNumber(${current.tAssignGradeSubmitCount})">浏览提交内容</a>&nbsp;&nbsp;&nbsp;
				        </mytag:JspSecurity>
				        </c:if> 	
					</td>
					<td>
		       			<c:if test="${current.status==0 }">
			       			草稿
			       		</c:if>
			       		<c:if test="${current.status==1 }">
			       			<c:if test="${now>= current.TAssignmentControl.startdate.time&&now< current.TAssignmentControl.duedate.time}">
								进行中
				       		</c:if>
					        <c:if test="${now<current.TAssignmentControl.startdate.time}">
								未到期
					       	</c:if>
					       	<c:if test="${now>current.TAssignmentControl.duedate.time}">
								 已过期
					       	</c:if>
			       		</c:if>
					</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.TAssignmentControl.startdate.time}" type="both"/></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.TAssignmentControl.duedate.time}" type="both"/></td>
					<td>
						<b>${current.tAssignGradeSubmitCount }/<font color="red">${current.tAssignGradeNotCorrectCount }</font></b>
						<%--<a href="javascript:void(0)" onclick="downloadFile(${current.tAssignGradeSubmitCount },${current.id })">查看提交的附件</a>
					--%></td>
					<td>0--${current.TAssignmentAnswerAssign.score }</td>
					<td>
						<mytag:JspSecurity realm="update" menu="tAssignment">
							<a  href="javascript:void(0);" onclick="move('moveup','${current.id}',this)">上移</a>
							<a  href="javascript:void(0);" onclick="move('movedown','${current.id}',this)">下移</a>
							<input type="text" style="width: 20px;" class="easyui-numberbox" value="1"/>位
						</mytag:JspSecurity>
					</td>
					<td>
						<c:if test="${fn:contains(user.authorities,'TEACHER')&&flag!=0}">
							<c:if test="${current.status==0 }">
								<mytag:JspSecurity realm="delete" menu="tAssignment">
									<a href="${pageContext.request.contextPath}/teaching/assignment/deleteAssignmentById?assignId=${current.id}" onclick="return confirm('是否确认删除？')">删除</a>
								</mytag:JspSecurity>
								<mytag:JspSecurity realm="update" menu="tAssignment">
									<a href="${pageContext.request.contextPath}/teaching/assignment/changeAsignmentStatusById?assignId=${current.id}" onclick="return confirm('是否确认发布,发布后不可删除？')">发布</a>
								</mytag:JspSecurity>
							</c:if>
						</c:if>
							
				       	<c:if test="${fn:contains(user.authorities,'STUDENT')&&flag==0}">
				       		<mytag:JspSecurity realm="update" menu="tAssignment">
				        		<a href="${pageContext.request.contextPath}/teaching/assignment/newAssignmentGrade?&assignId=${current.id}">提交作业</a> 
				       		</mytag:JspSecurity>
				       	</c:if>
				        
						
					</td>
			   </tr>
			</c:forEach>
		</tbody>
	<!-- 分页导航 -->
	
	</table>
</div>
</div>
</div>
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
    
    function move(type,id,obj){
    	var quantity;
    	if (type=='moveup') {
			quantity = $(obj).next().next().val().trim();
		}
    	if (type=='movedown') {
			quantity = $(obj).next().val().trim();
		}
		
		if (quantity=='') {
			alert("请输入需要移动的位数");
			return;
		}
		window.location.href = '${pageContext.request.contextPath}/teaching/assignment/'+type+'?id='+id+'&quantity='+quantity;
    }
</script>
<!-- 下拉框的js -->
</body>
</html>

