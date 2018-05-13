<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>月报报表</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/timetable/lmsReg.css">

<!-- 打印开始 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<!-- 打印结束 -->

<!-- 打印、导出组件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/LodopFuncs.js"></script>

<style type="text/css">
	table{width:100%;}
	.array{width:100%;
		word-break:break-all;}
</style>
<script type="text/javascript">
$(document).ready(function(){
      $('#fullview').click(function(){
           $('.sit_sider_bar').animate( { width:'0'}, 500 );
           $('.sit_maincontent').animate( { width:'100%'}, 500 );
           $('.toggle,.toggleLink,#fullview,.sit_footer,.sit_sider_bar > h3').css("display","none");
           $('#fullview1').css("display","inline");
      });
  
      $('#fullview1').click(function(){
           $('.sit_sider_bar').animate( { width:'23%'}, 500 );
           $('.sit_maincontent').animate( { width:'75%'}, 500 );
           $('#fullview1').css("display","none");
           $('.toggle,#fullview,.toggleLink,.sit_footer,.sit_sider_bar > h3 ').css("display","inline");
      });
      
      $('#myPrint').click(function(){
           printPreview();
      });
});
                              
$(function(){
      var height = $(document).height();
      $(".sit_sider_bar").css('height',height);
      $(".sit_maincontent").css('height',height);
}) ;

</script>
<script type="text/javascript">
$(function(){
    $("#showTimetable").window({
        top: ($(window).width() - 800) * 0.5 ,
        left: ($(window).width() - 1000) * 0.5   
    })
    $(".sit_maincontent").css('height',600);
})
</script>
<script type="text/javascript">
				//如果为查询则提交查询页面，如果为电子表格导出，则导出excel
					function subform(gourl){ 
					 var gourl;
					 form.action=gourl;
					 form.submit();
					}
					
	//导出excel
	function exportExcel()
	{
		document.form.action = "${pageContext.request.contextPath}/report/teachingReport/exportMonthRegister";
		document.form.submit();
	}
</script>
</head>

<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
		<li class="end"><a href="javascript:void(0)">月报报表</a></li>
	  </ul>
	</div>
  </div>

<div class="sit_maincontent" style="width: 99%; height: 800px;">

<script type="text/javascript">
function WordWrap(textlength, id){
var obj=document.getElementById(id);
var strText=obj.innerHTML;
var tem="";
while(strText.length>textlength){
tem+=strText.substr(0,textlength)+"<br/>";
strText=strText.substr(textlength,strText.length);
}
tem+= strText;
obj.innerHTML=tem;
}
</script>
<div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
<div class="title">
	  <div id="title">月报报表</div>
	  <a class="btn btn-new" href="javascript:void(0);"><input type="button" onclick="SaveAsFile();" value="导出"></a>
	  <a class="btn btn-new" href="javascript:void(0);"><input id="myPrint" value="打印" type="button" /></a>
</div>

<table border="0" cellpadding="5" cellspacing="0" bgcolor="#F3EFEE" height="30" width="100%">  <!-- border="0" cellpadding="5" cellspacing="0" bgcolor="#F3EFEE" height="30" width="100%" -->
<tbody>
<tr>
    <td>
    <form name="form" action="" method="post" modelAttribute="timetableAppointment">
  		<select class="chzn-select" id="term" name="term" style="width:180px">
	  		<c:forEach items="${schoolTerms}" var="current">
	    	    <c:if test="${current.id == term}">
	    	       <option value ="${current.id}" selected>${current.termName} </option>
	    	    </c:if>
	    	    <c:if test="${current.id != term}">
	    	       <option value ="${current.id}" >${current.termName} </option>
	    	    </c:if>		
	        </c:forEach>
        </select>
        
        <select id="year" name="year" style="width:120px">
			<option value="${year }" selected="selected">${year }年</option>
			<option value="0">年份</option>
			<option value="2013">2013年</option>
			<option value="2014">2014年</option>
			<option value="2015">2015年</option>
			<option value="2016">2016年</option>
			<option value="2017">2017年</option>
			<option value="2018">2018年</option>
			<option value="2019">2019年</option>
			<option value="2020">2020年</option>
			<option value="2021">2021年</option>
			<option value="2022">2022年</option>
			<option value="2023">2023年</option>
			<option value="2024">2024年</option>
			<option value="2025">2025年</option>
		</select>
		<select id="month" name="month" style="width:120px">
			<option value="${month }" selected="selected">${month }月</option>
			<option value="0">月份</option>
			<option value="1">1月</option>
			<option value="2">2月</option>
			<option value="3">3月</option>
			<option value="4">4月</option>
			<option value="5">5月</option>
			<option value="6">6月</option>
			<option value="7">7月</option>
			<option value="8">8月</option>
			<option value="9">9月</option>
			<option value="10">10月</option>
			<option value="11">11月</option>
			<option value="12">12月</option>
		</select>
    
    
	    <input value="查询" onclick="subform('${pageContext.request.contextPath}/report/teachingReport/monthRegister?currpage=1');" type="button">&nbsp;&nbsp;&nbsp;&nbsp;
	    <a href="${pageContext.request.contextPath}/report/teachingReport/monthRegister?currpage=1"><input type="button" value="取消查询"></a>
    </form>
    </td>    
</tr>
</tbody>
</table>
<div id="myShow">
<form name="printform" id="printform">
<table>  <!--  valign="center" cellpadding="5" cellspacing="0" align="center" width="100%" style="word-wrap:break-all" -->
<tbody>
<tr>
    <th class="tbh" width="2%">序号</th>
    <th class="tbh" width="4%">系别</th>
    <th class="tbh" width="4%">实验<br>部门</th>
    <th class="tbh" width="4%">负责人</th>
    <th class="tbh" width="4%">实验室<br>名称</th>
    <th class="tbh" width="4%">地点</th>
    <th class="tbh" width="4%">日期</th>
    <th class="tbh" width="4%">课程<br>名称</th>
    <th class="tbh" width="4%">实验<br>名称</th>
    <th class="tbh" width="3%">专科<br>人数</th>
    <th class="tbh" width="3%">专科<br>课时</th>
    <th class="tbh" width="3%">专科<br>人时数</th>
    <th class="tbh" width="3%">本科<br>人数</th>
    <th class="tbh" width="3%">本科<br>课时</th>
    <th class="tbh" width="3%">本科<br>人时数</th>
    <th class="tbh" width="3%">其他<br>人数</th>
    <th class="tbh" width="3%">其他<br>课时</th>
    <th class="tbh" width="3%">其他<br>人时数</th>
    <th class="tbh" width="3%">机房<br>人数</th>
    <th class="tbh" width="3%">机房<br>课时</th>
    <th class="tbh" width="3%">机房<br>人时数</th>
    <th class="tbh" width="4%">实验<br>类别</th>
    <th class="tbh" width="4%">指导<br>教师</th>
    <th class="tbh" width="4%">班级</th>
    <th class="tbh" width="4%">备注</th>
    <th class="tbh" width="6%">考核<br>方法</th>
</tr>
	<c:forEach var="monthReport" items="${monthReports}" varStatus="i">
		<tr>
			<td>${i.count }</td>
			<td>${monthReport.department }</td>
			<td>${monthReport.experimentDepartment }</td>
			<td>${monthReport.responsiblePerson }</td>
			<td>${monthReport.laboratoryName }</td>
			<td>${monthReport.place }</td>
			<td><fmt:formatDate value="${monthReport.date.time}" pattern="yyyy-MM-dd "/></td>
			<td>${monthReport.courseName }</td>
			<td>${monthReport.experimentName }</td>
			<td>${monthReport.juniorNumber }</td>
			<td>${monthReport.juniorClass }</td>
			<td>${monthReport.juniorTime }</td>
			<td>${monthReport.undergraduateNumber }</td>
			<td>${monthReport.undergraduateClass }</td>
			<td>${monthReport.undergraduateTime }</td>
			<td>${monthReport.otherNumber }</td>
			<td>${monthReport.otherClass }</td>
			<td>${monthReport.otherTime }</td>
			<td>${monthReport.roomNumber }</td>
			<td>${monthReport.roomClass }</td>
			<td>${monthReport.roomTime }</td>
			<td>${monthReport.experimentType }</td>
			<td>${monthReport.guidingTeacher }</td>
			<td>${monthReport.className }</td>
			<td>${monthReport.note }</td>
			<td>${monthReport.assessment }</td>
		</tr>
	</c:forEach>
    
</tbody>
</table>
</form>
</div>
<!--//right maincontent -->
</div>
</div>
</div>
</div>
<script type="text/javascript">
	var LODOP; //声明为全局变量 
	//导出excel文件  
	function SaveAsFileOld(){ 
		LODOP=getLodop();   
		LODOP.PRINT_INIT(""); 
		//alert($("#myShow").html());
		LODOP.ADD_PRINT_TABLE(0,0,"100%","100%",$("#myShow").html()); 
		LODOP.SET_SAVE_MODE("Orientation",2); //Excel文件的页面设置：横向打印   1-纵向,2-横向;
		LODOP.SET_SAVE_MODE("PaperSize",9);  //Excel文件的页面设置：纸张大小   9-对应A4
		LODOP.SET_SAVE_MODE("Zoom",90);       //Excel文件的页面设置：缩放比例
		LODOP.SET_SAVE_MODE("CenterHorizontally",true);//Excel文件的页面设置：页面水平居中
		LODOP.SET_SAVE_MODE("CenterVertically",true); //Excel文件的页面设置：页面垂直居中
//		LODOP.SET_SAVE_MODE("QUICK_SAVE",true);//快速生成（无表格样式,数据量较大时或许用到） 
		LODOP.SET_SHOW_MODE("NP_NO_RESULT",true);  //解决chrome弹出框问题
		LODOP.SAVE_TO_FILE("月报报表.xls"); 
	};
	//导出excel文件  
	function SaveAsFile(){ 	
		LODOP=getLodop();   
		LODOP.PRINT_INIT(""); 

	    var strBodyStyle="<style>table,th{border:none;height:18px} td{border: 1px solid #000;height:18px}</style>";
	    strHTML=strBodyStyle + "<table border=0 cellSpacing=0 cellPadding=0  width='100%' height='200' bordercolor='#000000' style='border-collapse:collapse'>";
	    strHTML=strHTML + "<thead><tr>";
	    //定义标题内容
	    strHTML=strHTML + "<th colspan=26><b><font face='黑体' size='6'>月报报表</font></b></th></tr>";
	    strHTML=strHTML + "<tr><th colspan=26>&nbsp;</th>";
	    strHTML=strHTML + "</th></tr>";
	    strHTML=strHTML + "<tr><th colspan=26><div align='right'>制表人：${user.cname}   制表时间：${systemTime} </div> </th>";
	    strHTML=strHTML + "</th></tr>";
	    
		var abc =  document.getElementById('printform').innerHTML;//printform是所要打印的表的名字
		abc = abc.replace("<table>","");
		abc = abc.replace("<thead>","");
		abc = strHTML + abc  + "</table>"; 
		LODOP.ADD_PRINT_TABLE(100,20,1100,80,abc); 
		LODOP.SET_SAVE_MODE("Orientation",2); //Excel文件的页面设置：横向打印   1-纵向,2-横向;
		LODOP.SET_SAVE_MODE("PaperSize",9);  //Excel文件的页面设置：纸张大小   9-对应A4
		LODOP.SET_SAVE_MODE("Zoom",90);       //Excel文件的页面设置：缩放比例
		LODOP.SET_SAVE_MODE("CenterHorizontally",true);//Excel文件的页面设置：页面水平居中
		LODOP.SET_SAVE_MODE("CenterVertically",true); //Excel文件的页面设置：页面垂直居中
//		LODOP.SET_SAVE_MODE("QUICK_SAVE",true);//快速生成（无表格样式,数据量较大时或许用到） 
		LODOP.SAVE_TO_FILE("月报报表.xls"); //文件名

	};
	
	
	//打印预览
	function printPreview(){
		LODOP=getLodop();  
		var strStyleCSS = "<link type='text/css' rel='stylesheet' href='${pageContext.request.contextPath}/css/style.css'>";
		    strStyleCSS +="<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/css/timetable/lmsReg.css'>";
		var strHtml = strStyleCSS+"<body>"+$("#myShow").html()+"</body>";
		LODOP.PRINT_INIT("");
		LODOP.SET_PRINT_STYLE("FontSize",18);  //字体大小
		LODOP.SET_PRINT_STYLE("Bold",1);  //是否粗体，1是，0否
		LODOP.ADD_PRINT_HTM(30,30,"RightMargin:30","BottomMargin:50",strHtml);
		LODOP.PREVIEW();
	}
</script>
</body>
</html>