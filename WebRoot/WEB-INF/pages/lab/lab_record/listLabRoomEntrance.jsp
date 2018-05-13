<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<meta name="decorator" content="iframe"/>
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<!-- 下拉的样式结束 -->	

<style type="text/css">
 .titt {
  border-bottom: 1px solid #a7a7a7;
  font-family: microsoft yahei;
  font-size: 0.875em;
  height: 30px;
  line-height: 31px;
  position: relative;
}
.titlet{
border-bottom: 1px solid #02355d;
    display: block;
    height: 30px;
    text-indent: 5px;
    width: 100px;
    font-size: 14px;
   /*  color:#043962 */
}
.content-box{width:80%;
	margin:auto;
	margin-top:10px;}
table th,table td{word-break:break-all;}
table{table-layout:fixed;}
.content-box{width: 100%;}
</style>

<script type="text/javascript">
	//首页
	function first(url){
		//window.location.href=url;
		document.queryForm.action=url;
		document.queryForm.submit();
	}
	//末页
	function last(url){
		//window.location.href=url;
		document.queryForm.action=url;
		document.queryForm.submit();
	}
	//上一页
	function previous(url){
		var page=${page};
		if(page==1){
			page=1;
		}else{
			page=page-1;
		}
		//window.location.href=url+page;
		document.queryForm.action=url+page;
		document.queryForm.submit();
	}
	//下一页
	function next(url){
		var totalPage=${pageModel.totalPage};
		var page=${page};
		if(page>=totalPage){
			page=totalPage;
		}else{
			page=page+1
		}
		//window.location.href=url+page;
		document.queryForm.action=url+page;
		document.queryForm.submit();
	}
	
	
	function cancelQuery(){
		window.location.href="${pageContext.request.contextPath}/lab/entranceList?id="+${id}+"&page=1";
	}
</script>
</head>

<body>

<div class="iStyle_RightInner">
<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">门禁进出记录</a></li>
	<li><a href="javascript:void(0)">实验室门禁管理</a></li>
	<li class="end"><a href="javascript:void(0)">门禁出入学生名单</a></li>
	
</ul>
</div>
</div>


<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="tool-box">

<form:form name="queryForm" action="${pageContext.request.contextPath}/lab/entranceList?id=${id}&page=1" method="post" modelAttribute="commonHdwlog">
	<ul >
		<li>学号：
		<form:input id="username" path="username" />
		</li>
		<li>姓名：
		<form:input id="cardname" path="cardname" />
		</li>

		<li>门禁刷卡时间：<input id="starttime" class="Wdate" type="text" name="starttime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="width:160px;" readonly />
		</li><li>到<input id="endtime" class="Wdate" type="text" name="endtime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="width:160px;"  readonly />
		</li>
	<li >
	<input type="submit" value="提交">
	<input type="button" value="取消" onclick="cancelQuery();">
	</li>
	
	</ul>
</form:form>
</div>
<div class="content-box" >
<div class="title">门禁出入学生名单</div>
<table> 
<thead>
 <tr>
   <th><p>序号</p></th>
   <th><p>姓名</p></th>
   <th><p>学号</p></th>
   <th><p>学院</p></th>
   <th><p>班级</p></th>
   <th><p>专业</p></th>
   <th><p>时间</p></th>
   
</tr>
</thead>

<tbody >
	<c:forEach items="${accessList}" var="access" varStatus="i">
	<tr align="left">
	<td><p>${i.count+(page-1)*pageSize}</p></td>
	<td><p>${access.cname}</p></td>
	<td><p>${access.username}</p></td>
	<td><p>${access.academyName}</p></td>
	<td><p>${access.className}</p></td>
	<td><p>${access.major}</p></td>
	<td><p>${access.attendanceTime}</p></td>
	</tr>
	</c:forEach>
	

</tbody>
</table>
</div>
</div>
</div>
</div>
<!-- 分页导航 -->
<div class="page" >
${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/lab/entranceList?id=${id}&page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/lab/entranceList?id=${id}&page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/lab/entranceList?id=${id}&page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/lab/entranceList?id=${id}&page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/lab/entranceList?id=${id}&page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/lab/entranceList?id=${id}&page=${pageModel.totalPage}')" target="_self">末页</a>
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

