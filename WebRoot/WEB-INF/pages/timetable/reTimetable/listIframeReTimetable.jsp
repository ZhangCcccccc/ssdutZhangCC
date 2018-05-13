<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<title>我要排课</title>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	

</head>
<body>

<div class="iStyle_RightInner">
<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">排课管理</a></li>
	<li class="end"><a href="javascript:void(0)">二次排课（含分组）</a></li>
</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">日历选择详细信息</div>
<table  > 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th>当前用户</th>
   <td>${user.cname }</td>
   <th>所属学院</th>
   <td>${user.schoolAcademy.academyName }</td>
   <th>所属学期</th>
   <td>${schoolTerm.termName} </td>
</tr>
<tr>
   <th>所选节次</th>
   <td>
      <c:if test="${classids==1}">
           <span id="className12-13">第一节~第二节</span></td>  
      </c:if>
      <c:if test="${classids==2}">
           <span id="className12-13">第三节~第四节</span></td>  
      </c:if>
      <c:if test="${classids==3}">
           <span id="className12-13">第五节~第六节</span></td>  
      </c:if>
      <c:if test="${classids==4}">
           <span id="className12-13">第七节~第八节</span></td>  
      </c:if>
      <c:if test="${classids==5}">
           <span id="className12-13">第九节</span></td>  
      </c:if>
      <c:if test="${classids==6}">
           <span id="className12-13">第十节</span></td>  
      </c:if>
      <c:if test="${classids==7}">
           <span id="className12-13">第十一节</span></td>  
      </c:if>
      <c:if test="${classids==8}">
           <span id="className12-13">第十二节</span></td>  
      </c:if>
      <c:if test="${classids==9}">
           <span id="className12-13">第十三节</span></td>  
      </c:if>
      
   <th>所选星期</th>
   <td>周${weekday }</td>
   <th>其他</th>
   <td></td>
</tr>
</thead>

</table>
<br>

</div>
</div>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">选择选课组</div>
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/doReTimetableIframe">

</form>
</div>
</div>
</div>
</div>
</div>
<br>
<hr>


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