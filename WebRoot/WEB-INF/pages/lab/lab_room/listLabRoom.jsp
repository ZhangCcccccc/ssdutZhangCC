<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  
  <script type="text/javascript">
  //跳转
  function targetUrl(url)
  {
    document.queryForm.action=url;
    document.queryForm.submit();
  }
  
  
  //弹窗--规章制度
  function showRegulations(id){
	  $("#showRegulations").show();
      //使得弹出框在屏幕顶端可见
      $('#showRegulations').window({left:"100px", top:"100px"});
      $("#showRegulations").window('open');
      
      $.ajax({
          url:"${pageContext.request.contextPath}/labRoom/getLabRoomDetail?roomId="+id,// 排除已存在于该实验室的管理员
          type:"POST",
          success:function(data){//AJAX查询成功
                 document.getElementById("labRoom_body").innerHTML=data;
          }
		});
  }
//排序
  
  var asc=${asc};//声明全局变量asc
  function orderByNumber(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=10&cid=${cid }";
	  }else window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=0&cid=${cid }";
  }
  function orderByName(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=11&cid=${cid }";
	  }else window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=1&cid=${cid }";
  }
  function orderByLabCenter(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=12&cid=${cid }";
	  }else window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=2&cid=${cid }";
  }
  function orderByCapacity(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=13&cid=${cid }";
	  }else window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=3&cid=${cid }";
  }
  function orderByArea(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=14&cid=${cid }";
	  }else window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=4&cid=${cid }";
  }
  function orderByActive(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=15&cid=${cid }";
	  }else window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=5&cid=${cid }";
  }
  function orderByReservation(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=16&cid=${cid }";
	  }else window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=6&cid=${cid }";
  }
  function orderByRoomAddress(){
	  asc=!asc;
	  if(asc){
		  window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=17&cid=${cid }";
	  }else window.location.href="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=7&cid=${cid }";
  }
  
  function api(){	
  	console.log(12);
		$.ajax({
			url: "${pageContext.request.contextPath}/api/getApi",
			type: "POST",
			success:function(data){
			  alert(data);
			}
		});	
	}
  </script>
</head>
  
<body>
  <div class="navigation">
    <div id="navigation">
	  <ul>
	    <li><a href="javascript:void(0)">实验室管理</a></li>
		<li class="end"><a href="javascript:void(0)">实验室管理</a></li>
	  </ul>
	</div>
  </div>
  
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
    <div class="title">
	  <div id="title">实验室列表</div>
	  <a class="btn btn-new" href="${pageContext.request.contextPath}/labRoom/newLabRoom?labCenterId=${cid}">新建</a>
	</div>
	
	<div class="tool-box">
		<form:form name="queryForm" action="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=${orderBy }&cid=${cid }" method="post" modelAttribute="labRoom">
			 <ul>
  				<li>实验室名称： </li>
  				<li><form:input id="lab_name" path="labRoomName"/></li>
  				<li>
			      <input type="button" value="取消" onclick="window.history.go(0)"/><input type="submit" value="查询"/></li>
                        <input type="button" value="1111" onclick="api()"/></li>		
  				</ul>
			 
		</form:form>
	</div>
	
	<div id="showRegulations" class="easyui-window" closed="true" modal="true" minimizable="true" title="规章制度详情" style="width: 580px;height: 250px;padding: 20px">
	  <div class="content-box">
	    <table id="my_show">
				<tbody id="labRoom_body">
					
				</tbody>
		</table>
	  </div>
  	</div>
	
	<table class="tb" id="my_show">
	  <thead>
	  <tr>
	    <%--<th><a href="javascript:void(0);" onclick="orderByLabCenter()">所属实验中心</a></th>--%>
	    <th><a href="javascript:void(0);" onclick="orderByNumber()">实验室编号</a></th>
	    <th><a href="javascript:void(0);" onclick="orderByName()">实验室名称</a></th>
	    <th><a href="javascript:void(0);" onclick="orderByRoomAddress()">房间号</a></th>
	    <th><a href="javascript:void(0);" onclick="orderByCapacity()">容量</a></th>
	    <th><a href="javascript:void(0);" onclick="orderByArea()">使用面积</a></th>
	    <%--<th>房间号</th>
	    --%><th><a href="javascript:void(0);" onclick="orderByActive()">使用状态</a></th>
	    <th><a href="javascript:void(0);" onclick="orderByReservation()">预约状态</a></th>
	     <th><a href="javascript:void(0);" onclick="orderByLabAnnex()">所属实验中心</a></th>
	    <th>操作</th>
	  </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${listLabRoom}" var="curr">
	  <tr>
	  	<%--<td>${curr.labCenter.centerName}</td>--%>
	    <td>${curr.labRoomNumber}</td>
	    <td>${curr.labRoomName}</td>
	    <td>${curr.labRoomAddress}</td>
	    <td>${curr.labRoomCapacity}</td>
	    <td>${curr.labRoomArea}</td>
	    <%--<td>${curr.systemRoom}</td>
	    --%><td>
	      <c:choose>
	      <c:when test="${curr.labRoomActive==1}">可用</c:when>
	      <c:otherwise>不可用</c:otherwise>
	      </c:choose>
	    </td>
	    <td>
	      <c:choose>
	      <c:when test="${curr.labRoomReservation==1}">可预约</c:when>
	      <c:otherwise>不可预约</c:otherwise>
	      </c:choose>
	    </td>
	    <td>${curr.labCenter.centerName}</td>
	    <td>
	      <a href="${pageContext.request.contextPath}/labRoom/getLabRoom?id=${curr.id}&cid=${cid}">查看</a>
	      <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER" >
	      <a href="${pageContext.request.contextPath}/labRoom/editLabRoom?labRoomId=${curr.id}">编辑</a>
	      </sec:authorize>
	      <sec:authorize ifAnyGranted="ROLE_LABCENTERMANAGER" >
	      	<c:if test="${user.labCenter.id == cid }">
	      		<a href="${pageContext.request.contextPath}/labRoom/editLabRoom?labRoomId=${curr.id}">编辑</a>
	      	</c:if>
	      </sec:authorize>
	      <sec:authorize ifAnyGranted="ROLE_TEACHER, ROLE_LABMANAGER" >
	      	<c:if test="${fn:contains(labString, curr.id)}">
	      		<a href="${pageContext.request.contextPath}/labRoom/editLabRoom?labRoomId=${curr.id}">编辑</a>
	      	</c:if>
	      </sec:authorize>
	      <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_ASSETMANAGEMENT,ROLE_DEPARTMENTHEAD,ROLE_COLLEGELEADER" >
	      <%-- <a href="${pageContext.request.contextPath}/labRoom/deleteLabRoom?labRoomId=${curr.id}" onclick="return confirm('确定删除？');">删除</a> --%>
	      <a href="${pageContext.request.contextPath}/labRoom/deleteLabRoom?labRoomId=${curr.id}&cid=${cid}" onclick="return confirm('确定删除？');">删除</a>
	      <%--<a href="javascript:void(0);" onclick="showRegulations(${curr.id})">规章制度</a>--%>
	      </sec:authorize>
	      <sec:authorize ifAnyGranted="ROLE_LABCENTERMANAGER" >
	      	<c:if test="${user.labCenter.id == cid }">
	      		<a href="${pageContext.request.contextPath}/labRoom/deleteLabRoom?labRoomId=${curr.id}&cid=${cid}" onclick="return confirm('确定删除？');">删除</a>
	      	</c:if>
	      </sec:authorize>
	     <sec:authorize ifAnyGranted="ROLE_TEACHER, ROLE_LABMANAGER" >
	      	<c:if test="${fn:contains(labString, curr.id)}">
	      		<a href="${pageContext.request.contextPath}/labRoom/deleteLabRoom?labRoomId=${curr.id}&cid=${cid}" onclick="return confirm('确定删除？');">删除</a>
	      	</c:if>
	      </sec:authorize>
	    </td>
	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	<!-- 分页[s] -->
	<div class="page" >
        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=1&orderBy=${orderBy }&cid=${cid }')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=${pageModel.previousPage}&orderBy=${orderBy }&cid=${cid }')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=${pageModel.currpage}&orderBy=${orderBy }&cid=${cid }">${pageModel.currpage}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=pageModel.currpage}">
    <option value="${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=${j.index}&orderBy=${orderBy }&cid=${cid }">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=${pageModel.nextPage}&orderBy=${orderBy }&cid=${cid }')" target="_self">下一页</a>
 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/labRoom/listLabRoom?currpage=${pageModel.lastPage}&orderBy=${orderBy }&cid=${cid }')" target="_self">末页</a>
    </div>
    <!-- 分页[e] -->
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
