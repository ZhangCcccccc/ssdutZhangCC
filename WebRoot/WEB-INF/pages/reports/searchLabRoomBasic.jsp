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
<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var config = {
	'.chzn-select' : {
		search_contains : true
	},
	'.chzn-select-deselect' : {
		allow_single_deselect : true
	},
	'.chzn-select-no-single' : {
		disable_search_threshold : 10
	},
	'.chzn-select-no-results' : {
		no_results_text : 'Oops, nothing found!'
	},
	'.chzn-select-width' : {
		width : "95%"
	}
}
for ( var selector in config) {
	$(selector).chosen(config[selector]);
}
</script>
<script type="text/javascript">
  //取消查询
  function cancel()
  {
    window.location.href="${pageContext.request.contextPath}/labCenter/listLabCenter?currpage=1";
  }
  //跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  </script>
<!-- 下拉框的js -->
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
		<li class="end"><a href="javascript:void(0)">实验室基本情况表</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
  	<div class="title">
	  <div id="title">实验室基本情况列表</div>
	  <a class="btn btn-new" href="javascript:void(0);"><input type="button" onclick="SaveAsFile();" value="导出"></a>
	  <a class="btn btn-new" href="javascript:void(0);"><INPUT TYPE="button" value="打 印" id="btnprint" onclick="self.print();"></a>
	</div>
	<form:form name="form" method="Post" action="${pageContext.request.contextPath}/report/searchLabRoomBasic?currpage=1" modelAttribute="labRoom">
		<ul>
			<li>实验中心名称：<form:input id="lab_name" path="labCenter.centerName"/>
			<input type="submit" value="查询"/>
			<input type="button" value="取消" onclick="cancel();"/></li><br>
		</ul>
	</form:form>
	
	
	<%-- <form:form name="form" method="Post" action="${pageContext.request.contextPath}/report/listLabRoomBasic?currpage=1" modelAttribute="labRoom">
	<ul>
		<select class="chzn-select" id="lab_name" name="centerName" style="width:180px">
	       <c:forEach items="${centers}" var="current">
	    	    <c:if test="${current.id == cid}">
	    	       <option value ="${current.id}" selected>${current.centerName} </option>
	    	    </c:if>
	    	    <c:if test="${current.id != cid}">
	    	       <option value ="${current.id}" >${current.centerName} </option>
	    	    </c:if>		
	        </c:forEach>
	    </select>
	    <input type="submit" value="查询" id="search" /> 
   		<a href="${pageContext.request.contextPath}/report/listLabRoomBasic?currpage=1"><input type="button" value="取消查询"></a> 
	</ul><br>
	</form:form> --%>
	<table border= "1 " width= "200 " >
     <tr>
        <th colspan="34"  >
        </th>
     </tr>
     <tr>
        <th   width= "3% " rowspan="3">学校代码</th>  
        <th   width= "3% " rowspan="3">实验室编号</th>  
        <th   width= "3% " rowspan="3">实验室名称</th>  
        <th   width= "3% " rowspan="3">实验室类别</th> 
        <th   width= "3% " rowspan="3">建立年份</th> 
        <th   width= "3% " rowspan="3">房屋使用面积</th> 
        <th   width= "3% " rowspan="3">实验室类型</th> 
        <th   width= "3% " rowspan="3">所属学科</th> 
        <th   width= "3% " colspan="3">教师获奖与成果</th> 
        <th   width= "3% " rowspan="3">学生获奖情况</th>
        <th   width= "3% " colspan="5">论文和教材情况</th> 
        <th   width= "3% " colspan="5">科研及社会服务情况</th>  
        <th   width= "3% " colspan="3">毕业设计和论文人数</th> 
        <th   width= "3% " colspan="6">开放实验</th> 
        <th   width= "2% " rowspan="3">兼任人员数</th>
        <th   width= "2% " colspan="2">实验教学运行经费</th> 
     </tr>
     <tr>
     	<th   width= "3% " rowspan="2">国家级</th> 
     	<th   width= "3% " rowspan="2">省部级</th> 
     	<th   width= "3% " rowspan="2">发明专利</th> 
        <th   width= "3% " colspan="2">三大检索与收录</th> 
        <th   width= "3% " colspan="2">核心刊物</th>
        <th   width= "3% " rowspan="2">实验教材</th>  
        <th   width= "3% " colspan="2">科研项目数</th>
        <th   width= "3% " rowspan="2">社会服务项目数</th>
        <th   width= "3% " colspan="2">教研项目数</th> 
        <th   width= "3% " rowspan="2">专科生人数</th>
        <th   width= "3% " rowspan="2">本科生人数</th>
        <th   width= "3% " rowspan="2">研究生人数</th>
        <th   width= "3% " colspan="2">实验个数</th>
        <th   width= "3% " colspan="2">实验个数</th>
        <th   width= "3% " colspan="2">实验个数</th>
        <th   width= "2% " rowspan="2">小计</th>
        <th   width= "3% " rowspan="2">其中教学实验年材料消耗费</th>
     </tr>
     <tr>
        <th   width= "3% ">教学</th>
        <th   width= "3% ">科研</th>
        <th   width= "3% ">教学</th>
        <th   width= "3% ">科研</th>
        <th   width= "3% ">省部级以上</th>
        <th   width= "3% ">其它</th>  
        <th   width= "3% ">省部级以上</th>
        <th   width= "3% ">其它</th>
        <th   width= "3% ">校内</th>
        <th   width= "3% ">校外</th> 
        <th   width= "3% ">校内</th>
        <th   width= "3% ">校外</th>
        <th   width= "3% ">校内</th>
        <th   width= "3% ">校外</th> 
     </tr>
     
     <tbody>
		  <c:forEach items="${labRoomBasics}" var="curr">
		    <tr>
			  <td>12799</td>
			  <td>${curr.labRoomNumber}</td>
			  <td>${curr.labRoomName}</td>
			  <td>${curr.CDictionaryByLabRoom.CName}</td>
			  <td><fmt:formatDate value="${curr.labRoomTimeCreate.time}" pattern="yyyy.MM"/></td>
			  <td>${curr.labRoomArea}</td>
			  <td>${curr.labRoomAddress}</td>
			  <td>${curr.systemSubject12.SNumber}</td>
			  <td>${curr.labRoomPrizeInformation}</td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
		    </tr>
		  </c:forEach>
	  </tbody>
     
</table>
	
	<!-- 分页开始 -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="${pageContext.request.contextPath}/report/listLabRoomBasic?currpage=1" target="_self">首页</a>			    
	<a href="${pageContext.request.contextPath}/report/listLabRoomBasic?currpage=${pageModel.previousPage}" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/report/listLabRoomBasic?currpage=${pageModel.currpage}">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/report/listLabRoomBasic?currpage=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="${pageContext.request.contextPath}/report/listLabRoomBasic?currpage=${pageModel.nextPage}" target="_self">下一页</a>
 	<a href="${pageContext.request.contextPath}/report/listLabRoomBasic?currpage=${pageModel.lastPage}" target="_self">末页</a>
    </div>
    <!-- 分页结束 -->
    
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
