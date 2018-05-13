<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<!--直接排课  -->

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
</head>

<div class="iStyle_RightInner">

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">

<div class="content-box">
<div class="title">用户权限组列表</div>
<table> 
<thead>
<tr>
   <th>序号</th>
   <th>用户组标识</th>
   <th>用户组名称</th>
   <th>用户组人数</th>
   <th>操作</th>
</tr>
</thead>
<tbody>
<c:if test="${isSUPERADMIN && !isEXCENTERDIRECTOR}">

<c:forEach items="${AllUserAuthority}" var="a"  varStatus="i">	

<tr>
    <th>${i.count}</th>
    <td>${a.authorityName}</td>
    <td>${a.authorityCname}</td>
    <td>${a.authorityNumber}</td>
    
    <td>
    <a class="btn btn-common" href='${pageContext.request.contextPath}/userAuthorityMange/listUserAuthorityDetail?page=1&Id=${a.authorityId}'>查看</a>&nbsp;&nbsp;
    </td>
    
</tr>

</c:forEach> 
</c:if>
<c:if test="${!isSUPERADMIN && isEXCENTERDIRECTOR}">
<c:forEach items="${userAuthorityForEXCENTERDIRECTOR}" var="a"  varStatus="i">	

<tr>
    <th>${i.count}</th>
    <td>${a.authorityName}</td>
    <td>${a.authorityCname}</td>
    <td>${a.authorityNumber}</td>
    
    <td>
    <a class="btn btn-common" href='${pageContext.request.contextPath}/userAuthorityMange/listUserAuthorityDetail?page=1&Id=${a.authorityId}'>查看</a>&nbsp;&nbsp;
    </td>
    
</tr>

</c:forEach>
</c:if>
<c:if test="${isSUPERADMIN && isEXCENTERDIRECTOR}">

<c:forEach items="${AllUserAuthority}" var="a"  varStatus="i">	

<tr>
    <th>${i.count}</th>
    <td>${a.authorityName}</td>
    <td>${a.authorityCname}</td>
    <td>${a.authorityNumber}</td>
    
    <td>
    <a class="btn btn-common" href='${pageContext.request.contextPath}/userAuthorityMange/listUserAuthorityDetail?page=1&Id=${a.authorityId}'>查看</a>&nbsp;&nbsp;
    </td>
    
</tr>

</c:forEach> 
</c:if>
</tbody>

</table>
</div>
</div>
</div>
</div>
</div>
</div>

<div id="searchFile" class="easyui-window" title="直接排课" closed="true" iconCls="icon-add" style="width:850px;height:450px">

</div>


<!-- 调整排课 -->
<div id="doAdmin" class="easyui-window" title="调整排课" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>

<!-- 查看学生名单 -->
<div id="doSearchStudents" class="easyui-window" title="查看学生名单" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
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

