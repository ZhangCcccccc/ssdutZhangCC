<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />   
<head>
<meta name="decorator" content="iframe" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">

<!-- 加载lodop打印组件 -->
<script language="javascript" src="${pageContext.request.contextPath}/js/LodopFuncs.js"></script>

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
</head>
<body>

<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent0">
<!-- 查询、导出、打印 -->
	<form action="${pageContext.request.contextPath }/teaching/gradebook/singleWeightSetting" onsubmit="return checkForm(this)">
		<div class="content-box" style="width: 30%;float: left;">
				作业权重
				<table  id="my_show">
					<thead>
					    <tr>                   
					        <th>标题</th>
					        <th>权重</th>
					    </tr>
					</thead>
					<tbody>
						<c:forEach items="${assignmentList }" var="current">
							<tr>
								<c:forEach items="${current }" var="current1" varStatus="i">
									<c:if test="${i.first }">
										<td style="display: none;"><input type="hidden" name="objectId" value="${current1 }"> </td>
									</c:if>
									<c:if test="${!i.first&&!i.last }">
										<td>${current1 }</td>
									</c:if>
									<c:if test="${i.last }">
										<td><input name="weight" type="text" style="width: 20px;" value="${100*current1 }" class="easyui-numberbox" required="required"/>% </td>
									</c:if>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			<br>
			<button class="btn" type="submit">确定</button>
		</div>
	</form>
	<form action="${pageContext.request.contextPath }/teaching/gradebook/singleWeightSetting" onsubmit="return checkForm(this)">
		<div class="content-box" style="width: 29%;float: left;margin-left: 5%;">
				测验权重
				<table  id="my_show">
					<thead>
					    <tr>                   
					        <th>标题</th>
					        <th>权重</th>
					    </tr>
					</thead>
					<tbody>
						<c:forEach items="${examList }" var="current">
							<tr>
								<c:forEach items="${current }" var="current1" varStatus="i">
									<c:if test="${i.first }">
										<td style="display: none;"><input type="hidden" name="objectId" value="${current1 }"> </td>
									</c:if>
									<c:if test="${!i.first&&!i.last }">
										<td>${current1 }</td>
									</c:if>
									<c:if test="${i.last }">
										<td><input name="weight" type="text" style="width: 20px;" value="${100*current1 }" class="easyui-numberbox" required="required"/>% </td>
									</c:if>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			<br>
			<button class="btn" type="submit">确定</button>
		</div>
	</form>
	<form action="${pageContext.request.contextPath }/teaching/gradebook/singleWeightSetting" onsubmit="return checkForm(this)">
		<div class="content-box" style="width: 29%;float: left;margin-left: 5%;">
				总评权重
				<table  id="my_show">
					<thead>
					    <tr>                   
					        <th>标题</th>
					        <th>权重</th>
					    </tr>
					</thead>
					<tbody>
						<c:forEach items="${weightSettings }" var="current">
							<tr>
								<td style="display: none;"><input type="hidden" name="weightSettingId" value="${current.id }"/></td>
								<td>
									<c:if test="${current.type eq 'assignment' }">作业</c:if>
									<c:if test="${current.type eq 'exam' }">测验</c:if>
									<c:if test="${current.type eq 'test' }">考试</c:if>
								</td>
								<td><input name="weight" type="text" style="width: 20px;" value="${100*current.weight }" class="easyui-numberbox" required="required"/>%</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<button class="btn" type="submit">确定</button>
			<br>
		</div>
	</form>
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
    
    function checkForm(obj){
    	var total = 0;
    	$(obj).find("input[type='text']").each(function(){
			total += Number($(this).val());    	
    	});
    	if (total!=100) {
			if (confirm("当前权重之和不为1，是否确认？")) {
				return true;
			}else {
				return false;
			}
		}
		return true;
    }
</script>
<!-- 下拉框的js -->
</body>
</html>

