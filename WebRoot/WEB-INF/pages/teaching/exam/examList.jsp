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
	/* 
	* 在新标签下显示内容
	*/	
	$(document).ready(function(){
	
	});
	function displayNewTag(title,url){
		//window.parent.document.getElementById("other").append("<div class='iStyle_Mark' hidden='true'  src='${pageContext.request.contextPath}/teaching/exam/examList?flag=1'><span>已发布测验</span></div>");;
		parent.$("#abc").empty();
		parent.$("#abc").html("<div class='iStyle_Mark ' src='${pageContext.request.contextPath}/teaching/exam/examList?flag=0'><span>可发布测验</span></div><div class='iStyle_Mark ' src='${pageContext.request.contextPath}/teaching/exam/examList?flag=1'><span>已发布测验</span></div><div class='iStyle_Mark iStyle_ActiveMark'   src='${pageContext.request.contextPath}/teaching/exam/"+ title + "'><span>" + title + "</span></div>");;
		//parent.$("#tt3").show();
		parent.$("#ssd").empty();
		parent.$("#ssd").append("<iframe style='display:none;' scrolling='yes' src='${pageContext.request.contextPath}/teaching/exam/examList?flag=0' id='mainframe'></iframe>");;
		parent.$("#ssd").load("${pageContext.request.contextPath}/teaching/exam/"+url,fnt);
	}
	function fnt(){
	        parent.$("#ssd").show();
	}
	
	function checkSubmitNumber(number){
		if(number==0){
			alert("尚未有提交内容，无法查看！");
			return false;
		}
	}
	
	function openwindow(examId){
	    var con = '<iframe id="con" scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/teaching/question/checkQuestionList?examId='+examId+'" style="width:100%;height:100%;"></iframe>'
	    $("#openwindow").html(con); 
	    //获取当前屏幕的绝对位置
	    var topPos = window.pageYOffset+10;
	    //使得弹出框在屏幕顶端可见
	    $('#openwindow').window({
	    	top:topPos+"px",
	    	onBeforeClose:function(){
	    		$("#con").remove();
				$("#openwindow").window('close',true);
	    		/**
	    		if(confirm("是否确认关闭视频?")){
	    			$("#con").remove();
	    			$("#mediaDisplay").window('close',true);
	    		}else{
	    			return false;
	    		}
	    		*/
	    	}
	    }); 
	    $("#openwindow").window('open');
	}
</script>
</head>
<body>

<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent0" style="height: 500px;">
<!-- 查询、导出、打印 -->
<div class="content-box">
<%--<c:if test="${status!=0 }">
 查看: 所有 | 进行中: 对学生公开，可以进行 | 关闭: 学生不能进行测验 
</c:if>
--%><table  id="my_show">
<thead>
    <c:if test="${status==0 }">
    <tr>                   
        <th>序号</th>
        <th>标题</th>
        <th>修改时间</th>
    </tr>
    </c:if>
    <c:if test="${status==1 }">
    <tr>                   
        <th>序号</th>
        <th>标题</th>
        <th>状态</th>
        <th>已提交</th>
        <th>发布日期</th>
        <th>过期日期</th>
        <th>创建日期</th>
        <th>评分方式</th>
        <th>操作</th>
    </tr>
    </c:if>
</thead>
<tbody>
<!--遍历未发布列表  -->
<c:if test="${status==0 }">
<c:forEach items="${tAssignments}" var="current"  varStatus="i">
<tr>    
        <td>${i.count }
        
        </td>
        <td>${current.title}</a><br>
	        <a href="${pageContext.request.contextPath}/teaching/exam/beginExam?simulation=1&examId=${current.id}" target="_blank">模拟测验</a>|
	        <a href="${pageContext.request.contextPath}/teaching/exam/examInfo?examId=${current.id}" >编辑测验</a>|
	        <a onclick="history.go(0)" href="${pageContext.request.contextPath}/teaching/exam/deployExam?examId=${current.id}">发布测验</a>|
	        <a onclick="return confirm('是否确认删除测试？')" href="${pageContext.request.contextPath}/teaching/exam/deleteExamById?examId=${current.id}">删除测验</a>|
	        <a onclick="openwindow(${current.id})" href="javascript:void(0);">同步测验中试题到题库</a>
            
       	</td>
        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.createdTime.time}" type="both"/></td>
</tr>
</c:forEach>
</c:if>
<!--遍历已发布列表  -->
<c:if test="${status==1 }">
<c:forEach items="${tAssignments}" var="current"  varStatus="i">
<tr>
	<td>
        ${i.count }
    </td>
    <td>${current.title}<br>
        <%--<a href="${pageContext.request.contextPath}/teaching/exam/beginExam?simulation=1&examId=${current.id}" target="_blank">模拟测验</a>|
        <a href="${pageContext.request.contextPath}/teaching/exam/examInfo?examId=${current.id}">查看测验</a>|
        <a href="${pageContext.request.contextPath}/teaching/exam/examInfo?examId=${current.id}">导出测验</a>|
        <a href="/gvsun/teaching/exam/examGradingList?examId=${current.id}" onclick="return checkSubmitNumber(${current.tAssignGradeSubmitCount})">浏览提交内容</a>

    --%></td>
    <td>
	    <c:if test="${now>= current.TAssignmentControl.startdate.time&&now< current.TAssignmentControl.duedate.time}">
	                      开始
	   	</c:if>
	    <c:if test="${now<current.TAssignmentControl.startdate.time}">
	                      未到期
	    </c:if>
	    <c:if test="${now>current.TAssignmentControl.duedate.time}">
	                     已过期
	    </c:if>
    </td>
       
    <td>
    	<mytag:JspSecurity realm="check" menu="exam">
	       	<a href="/gvsun/teaching/exam/examGradingList?examId=${current.id}" onclick="return checkSubmitNumber(${current.tAssignGradeSubmitCount})">
	       		${current.tAssignGradeSubmitCount }
	       	</a>
		</mytag:JspSecurity>       	
    </td>
    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.TAssignmentControl.startdate.time}" type="both"/></td>
    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.TAssignmentControl.duedate.time}" type="both"/></td>
    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.createdTime.time}" type="both"/></td>
    <td><font >0--${current.TAssignmentAnswerAssign.score }</font></td>
    <td>
    	<a onclick="openwindow(${current.id})" href="javascript:void(0);">同步测验中试题到题库</a>
    	<a href="${pageContext.request.contextPath }/teaching/exam/editExamAttributes?examId=${current.id}">编辑属性</a>
    </td>
</tr>
</c:forEach>
</c:if>
</tbody>
<!-- 分页导航 -->

</table>
</div>
</div>
</div>

<div id="openwindow" class="easyui-window" title="选择题库" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width: 900px; height:450px;">
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

