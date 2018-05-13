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
<script type="text/javascript">
function exportSelect(){
	 document.form.action="exportExcelSelectUser";
	 document.form.submit();
	}
	
function SaveAsFile(){ 
	LODOP=getLodop();   
	LODOP.PRINT_INIT(""); 
	LODOP.ADD_PRINT_TABLE(100,20,1000,80,document.documentElement.innerHTML); 
	LODOP.SET_SAVE_MODE("Orientation",2); //Excel文件的页面设置：横向打印   1-纵向,2-横向;
	LODOP.SET_SAVE_MODE("PaperSize",9);  //Excel文件的页面设置：纸张大小   9-对应A4
	LODOP.SET_SAVE_MODE("Zoom",90);       //Excel文件的页面设置：缩放比例
	LODOP.SET_SAVE_MODE("CenterHorizontally",true);//Excel文件的页面设置：页面水平居中
	LODOP.SET_SAVE_MODE("CenterVertically",true); //Excel文件的页面设置：页面垂直居中
//	LODOP.SET_SAVE_MODE("QUICK_SAVE",true);//快速生成（无表格样式,数据量较大时或许用到） 
	LODOP.SAVE_TO_FILE("成绩项一览.xls"); 
};	 

</script>
</script>
</head>
<body>
${user.username }:${user.cname }同学当前试题情况
<hr>
<input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">

<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent0">
<!-- 查询、导出、打印 -->
<div class="content-box">
<c:if test="${flag==1 }">
 查看: 所有 | 进行中: 对学生公开，可以进行 | 关闭: 学生不能进行测验 
</c:if>
<a class="btn btn-new" onclick="SaveAsFile();"><span>导出Excel</span></a>
<table  id="my_show"> 
<thead>
    <tr>                   
        <th>试卷标题</th>
        <th>截止时间</th>
        <th>分数</th>
        <th>成绩项分值</th>
        <th>来源</th>
    </tr>
</thead>
<tbody>
<!--遍历已发布列表  -->
<c:forEach items="${tGradeRecords}" var="current"  varStatus="i">
<tr>    
       <td>${current.TGradeObject.title}</td>
       <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.TGradeObject.dueTime.time}" type="both"/></td>
       <td>${current.points}</td>
       <td>${current.TGradeObject.pointsPossible}</td>
       <td>
       <c:if test="${current.TGradeObject.TAssignment.type=='exam' }">
                          从练习与测验
       </c:if>
       <c:if test="${current.TGradeObject.TAssignment.type=='assignment' }">
                          从作业
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
</script>
<!-- 下拉框的js -->
</body>
</html>

