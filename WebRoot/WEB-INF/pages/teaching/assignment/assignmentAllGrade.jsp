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
	
	//正则表达式规范得分填写
	function changeNumber(obj){
		var price=$(obj).val();
		price = price.replace(/[^\d.]/g,"");
	    //必须保证第一个为数字而不是.
	    price = price.replace(/^\./g,"");
	    //保证只有出现一个.而没有多个.
	    price = price.replace(/\.{2,}/g,".");
	    //保证.只出现一次，而不能出现两次以上
	    price = price.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
	    
	    $(obj).val(price);
	}
	
	function grade(obj){
		var assignGradeId = $(obj).parent().parent().find("#assignGradeId").val(); 
		var comments = $(obj).parent().parent().find("#comments").val();
		var finalScore = $(obj).parent().parent().find("#finalScore").val();
		$.ajax({
			url:'${pageContext.request.contextPath}/teaching/assignmentGrading/grade?assignGradeId='+assignGradeId+'&comments='+comments+'&finalScore='+finalScore,
			type:'post',
			async:false,  // 设置同步方式
	        cache:false,
			success:function(data){
				if(finalScore!=""){
					$("#isGraded").val("已评分");
				}
				$("span").html('<fmt:formatDate pattern="yyyy-MM-dd" value="${now }" type="both"/>');
				$("#finalScoreFont").html(finalScore);
			}
		}); 
	}
	
	function downloadFile(id){
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
	<div class="title">
		<div id="title">
			<a style="margin-left: 1000px;" href="javascript:void(0);" onclick="history.go(-1);">返回</a>	
		</div>
		
	</div> 	
	<table  id="my_show"> 
		<tbody>
			<c:forEach items="${viewTAssignmentAllGrades}" var="current"  varStatus="i">
		   		<tr>
					<td>${current.cname}</td>
			       	<c:forEach items="${current.scores}" var="score"  varStatus="i">
	       				<td>${score }</td>
	       			</c:forEach>
	       			<td>${current.totalGradeForOneUser }</td>
				</tr>
			</c:forEach>
		</tbody>
	
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
</script>
<!-- 下拉框的js -->
</body>
</html>

