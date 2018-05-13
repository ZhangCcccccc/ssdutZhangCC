<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<meta name="decorator" content="iframe"/>
<!-- 加载lodop打印组件 -->
<script language="javascript" src="${pageContext.request.contextPath}/js/LodopFuncs.js"></script>
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
	    strHTML=strHTML + "<th colspan=26><b><font face='黑体' size='6'>专任实验室人员表</font></b></th></tr>";
	    strHTML=strHTML + "<tr><th colspan=26>&nbsp;</th>";
	    strHTML=strHTML + "</th></tr>";
	    strHTML=strHTML + "<tr><th colspan=26><div align='right'>制表单位：西安电子科技大学   制表时间：${systemTime} </div> </th>";
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
		LODOP.SAVE_TO_FILE("专任实验室人员表.xls"); //文件名

	};
</script>
<script>
function start(v){
try{printWB.ExecWB(v,1);}catch(e){}
window.close();
}
</script>
<object id="printWB" style="display:none" width=0 height=0 classid="clsid:8856F961-340A-11D0-A96B-00C04FD705A2" VIEWASTEXT></object><body topmargin=0 leftmargin=0 onload="start(7)"></body>
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="WebBrowser3" width="0" VIEWASTEXT>
</OBJECT>
<script language="#">
function test()
{
document.all.WebBrowser3.ExecWB(7,1);
}
</script>
<INPUT type="button" value="Button" onclick="test()">
</head>
  
<body bgcolor="#ffffff" text="#000000" onbeforeprint="btnprint.style.display='none';btnback.style.display='none';" onafterprint="btnprint.style.display='';btnback.style.display=''>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">系统报表</a></li>
		<li class="end"><a href="javascript:void(0)">专任实验室人员表</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
  	<div class="title">
	  <div id="title">实验室人员列表</div>
	  <a class="btn btn-new" href="javascript:void(0);"><input type="button" onclick="SaveAsFile();" value="导出"></a>
	</div>
	
	<table class="tb" id="my_show">
	  <thead style="center-content">
		  <tr>
		    <th width=6%>学校代码</th>
		    <th width=6%>人员编号</th>
		    <th width=6%>部门编号</th>
		    <th width=8%>部门名称</th>
		    <th width=5%>姓名</th>
		    <th width=5%>性别</th>
		    <th width=8%>出生年月</th>
		    <th width=7%>所属学科</th>
		    <th width=8%>专业技术职务</th>
		    <th width=7%>文化程度</th>
		    <th width=6%>专家类别</th>
		    <th width=7%>国内培训时间 <br>学历教育</th>
		    <th width=7%>国内培训时间 <br>非学历教育</th>
		    <th width=7%>国外培训时间 <br>学历教育</th>
		    <th width=7%>国外培训时间 <br>非学历教育</th>
		  </tr>
	  </thead>
	  
	  <tbody>
		  <c:forEach items="${labWorkers}" var="curr">
		    <tr>
			  <td>12799</td>
			  <td>${curr.lwCodeCustom}</td>
			  <td>${curr.labCenter.centerNumber}</td>
			  <td>${curr.labCenter.centerName}</td>
			  <td>${curr.lwName}</td>
			  <td>${curr.lwSex}</td>
			  <td><fmt:formatDate value="${curr.lwBirthday.time}" pattern="yyyy-MM-dd"/></td>
			  <td>${curr.CDictionaryByLwSubject.CName}</td>
			  <td>${curr.CDictionaryByLwSpecialtyDuty.CName}</td>
			  <td>${curr.CDictionaryByLwAcademicDegree.CName}</td>
			  <td>${curr.lwCategoryExpert}</td>
			  <td>${curr.lwTrainFormalInlandTime}</td>
			  <td>${curr.lwTrainInformalInlandTime}</td>
			  <td>${curr.lwTrainFormalAbroadTime}</td>
			  <td>${curr.lwTrainInformalAbroadTime}</td>
		    </tr>
		  </c:forEach>
	  </tbody>
	</table>
	
	<!-- 分页开始 -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="${pageContext.request.contextPath}/report/listLabWorker?currpage=1" target="_self">首页</a>			    
	<a href="${pageContext.request.contextPath}/report/listLabWorker?currpage=${pageModel.previousPage}" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/report/listLabWorker?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/report/listLabWorker?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="${pageContext.request.contextPath}/report/listLabWorker?currpage=${pageModel.nextPage}" target="_self">下一页</a>
 	<a href="${pageContext.request.contextPath}/report/listLabWorker?currpage=${pageModel.lastPage}" target="_self">末页</a>
    </div>
    <!-- 分页结束 -->
    
  </div>
  </div>
  </div>
  </div>
  </div>

</body>
</html>
