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

//弹出查看该项作业学生成绩
function gradebookListInfo(objectId) {
    var sessionId=$("#sessionId").val();
    var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/teaching/gradebook/gradebookListDetal?objectId='+ objectId +'" style="width:100%;height:100%;"></iframe>'
    $('#gradebookListInfo').html(con);
    //获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#gradebookListInfo').window({left:"0px", top:topPos+"px"}); 
    $("#gradebookListInfo").window('open');
}

var ePrintStr="";
var totalPage = 1;
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
</head>
<body>

<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent0">
<!-- 查询、导出、打印 -->
<div class="content-box">
<c:if test="${flag==1 }">
 查看: 所有 | 进行中: 对学生公开，可以进行 | 关闭: 学生不能进行测验  
</c:if>
<mytag:JspSecurity realm="check" menu="gradebook">
<a class="btn btn-new" onclick="SaveAsFile();"><span>导出Excel</span></a>
</mytag:JspSecurity>
<table  id="my_show"> 
<thead>
    <tr>                   
        <th>标题</th>
        <th>平均分</th>
        <th>成绩权重</th>
        <th>是否计入课程成绩</th>
        <th>截止日期</th>
        <th>公布给学生</th>
        <th>成绩来源</th>
    </tr>
</thead>
<tbody>
<!--遍历已发布列表  -->
<c:forEach items="${tGradeObjects}" var="current"  varStatus="i">
<tr>    
       <td> <a href='javascript:void(0)' onclick='gradebookListInfo(${current.id})'>${current.title}</a></td>
       <td>
       <!--合计分  -->
       <c:set var="meanScore" value="0"></c:set>
       <c:forEach items="${current.TGradeRecords}" var="current1"  varStatus="j">
           <c:set var="meanScore" value="${meanScore+current1.points }"></c:set>
       </c:forEach>
       <c:set var="meanScore" value="${meanScore/current.TGradeRecords.size()}"></c:set>
       ${meanScore}
       </td>
       <td>
         ${current.weight }
       </td>
       <td>
         <c:if test="${current.marked==0 }">
                                                      不计入
         </c:if>
         <c:if test="${current.marked==1 }">
                                                       计入
         </c:if>
       </td>
       <td><fmt:formatDate pattern="yyyy-MM-dd" value="${current.dueTime.time}" type="both"/></td>
       <td>
           <%--<c:if test="${current.released==0 }">否</c:if>
           <c:if test="${current.released==1 }">是</c:if>--%>
           <c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent== 'no' }">否</c:if>
           <c:if test="${current.TAssignment.TAssignmentControl.gradeToStudent== 'yes' }">是</c:if>
       </td>
       <td>
       <c:if test="${current.TAssignment.type=='exam' }">
                          从练习与测验
       </c:if>
       <c:if test="${current.TAssignment.type=='assignment' }">
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

<div id="gradebookListInfo" class="easyui-window" title="查看详细"  closed="true" modal="true"  iconcls="icon-add" style="width: 900px; height:600px;">
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

