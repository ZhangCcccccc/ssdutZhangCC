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
var ePrintStr="";
var totalPage = 1;
function SaveAsFile(){ 
	strHTML=strBodyStyle + "<table border='0' width='100%' >";
	strHTML=strHTML+"<tr><td>";
	strHTML=strHTML+ePrintStr;
	strHTML=strHTML+"</td></tr>";
   		    
    var strBodyStyle="<style>table,th{border:none;height:18px} td{border: 1px solid #000;height:18px}</style>";
    strHTML=strBodyStyle + "<table border=0 cellSpacing=0 cellPadding=0  width='100%' height='200' bordercolor='#000000' style='border-collapse:collapse'>";
    strHTML=strHTML + "<thead><tr>";

    //定义标题内容
    strHTML=strHTML + "<th colspan=11><b><font face='黑体' size='6'>上海外国语大学查询统计表</font></b></th></tr>";
    strHTML=strHTML + "<tr><th colspan=11>&nbsp;</th>";
    strHTML=strHTML + "</th></tr>";
    strHTML=strHTML + "<tr><th colspan=11><p align='right'>制表人：${user.cname}   制表时间：${systemTime} </p> </th>";
    strHTML=strHTML + "</th></tr><tr>";

     //定义表头内容
    strHTML=strHTML + "<td><b>单位编号</b></td><td><b>单位名称</b></td><td><b>分类号</b></td><td><b>数量</b></td><td><b>金额</b></td></tr></thead><tbody>";	

	
    //定义数据内容<tr>部分
    strHTML=strHTML + ePrintStr;
    strHTML=strHTML + "</tbody>";
    
    //定义页脚和表尾部分
    strHTML=strHTML + "<tfoot><tr><th colspan=2 tdata='SubCount' format='0' align='right' >当前小计： ##台件</th><th colspan=2 tdata='SubSum' format='#,##0.00' tindex='6' align='left' > &nbsp;###元</th><th tdata='AllCount' format='0' tindex='6' align='right' colspan=3>  总计：###台件</th><th tdata='AllSum' format='#,##0.00' tindex='7' align='left' >&nbsp; ###元</th>";
    strHTML=strHTML + "<th colspan=3 align='right'>当前第<font tdata='PageNO' format='Num' color='blue'>##</font>页</span>(共<font tdata='PageCount' format='Num' color='blue'>##</font>页)</span></th></tr></tfoot></table>";
    strHTML=strHTML + document.documentElement.innerHTML;
	LODOP=getLodop();   
	LODOP.PRINT_INIT(""); 
	LODOP.ADD_PRINT_TABLE(100,20,1000,80,document.documentElement.innerHTML); 
	LODOP.SET_SAVE_MODE("Orientation",2); //Excel文件的页面设置：横向打印   1-纵向,2-横向;
	LODOP.SET_SAVE_MODE("PaperSize",9);  //Excel文件的页面设置：纸张大小   9-对应A4
	LODOP.SET_SAVE_MODE("Zoom",90);       //Excel文件的页面设置：缩放比例
	LODOP.SET_SAVE_MODE("CenterHorizontally",true);//Excel文件的页面设置：页面水平居中
	LODOP.SET_SAVE_MODE("CenterVertically",true); //Excel文件的页面设置：页面垂直居中
//	LODOP.SET_SAVE_MODE("QUICK_SAVE",true);//快速生成（无表格样式,数据量较大时或许用到） 
	LODOP.SAVE_TO_FILE("学生成绩明细.xls"); 
};	 
</script>
</head>
<body>

<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent0">
<!-- 查询、导出、打印 -->
<div class="content-box">
学生成绩明细 
<mytag:JspSecurity realm="check" menu="gradebook">
<a class="btn btn-new" onclick="SaveAsFile();"><span>导出Excel</span></a>
</mytag:JspSecurity>
<!--计算权重之和  -->
<c:set var="countWeight" value="0" />
<c:forEach items="${tGradeObjects}" var="tGradeObject"  varStatus="k">
    <c:if test="${tGradeObject.marked==1 }">
        <c:set var="countWeight" value="${tGradeObject.weight+countWeight}" />
    </c:if>
</c:forEach>

<table  id="my_show"> 
<thead>
    <tr>                   
        <th>学生姓名</th>
        <th>学生学号</th>
        <th>课程成绩</th>
        <c:forEach items="${tGradeObjects}" var="current"  varStatus="i">
        <th>${current.title }</th>
        </c:forEach>
    </tr>
</thead>
<tbody>
<!--遍历课程站点成员列表  -->
<c:forEach items="${tCourseSiteUsers}" var="current"  varStatus="i">
<tr>    
       <td>${current.user.cname}</td>
       <td>${current.user.username}</td>
       <td>
           
           <c:set var="myPoints" value="0" />
           <!--遍历课程站点成员的成绩项列表  -->
	       <c:forEach items="${current.user.TGradeRecords}" var="current3"  varStatus="k">
	          <c:if test="${current3.TGradeObject.marked==1&&countWeight!=0 }">
		          <c:set var="myPoints" value="${current3.points* current3.TGradeObject.weight/countWeight+myPoints}" />
	          </c:if>
	       </c:forEach>
	       ${myPoints}
       </td>
       <!--遍历成绩册的试题列表  -->
       <c:forEach items="${tGradeObjects}" var="current1"  varStatus="k">
           <td>
           <!--遍历课程站点成员的成绩项列表  -->
	       <c:forEach items="${current.user.TGradeRecords}" var="current2"  varStatus="j">
		       <c:if test="${current1.id==current2.TGradeObject.id }">
		       ${current2.points }
		       </c:if>
	       </c:forEach>
	       </td>
	   </c:forEach>
 </tr>
</c:forEach>
</tbody>
</table>
<br>
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

