<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>


<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<meta name="decorator" content="iframe"/>
 <script type="text/javascript">
  $(function(){
     $("#print").click(function(){
	$("#my_show").jqprint();
	});
	$("#lab_name").focus();
	$("#lab_name").bind('keydown', function (e) {

            var key = e.which;

            if (key == 13) {

                e.preventDefault();
              document.form.action="${pageContext.request.contextPath}/selectLabList";
	           document.form.submit();

            }

        });
  });


	//首页
	function first(url){
		document.queryForm.action=url;
		document.queryForm.submit();
	}
	//末页
	function last(url){
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
		//alert("上一页的路径："+url+page);
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
		//alert("下一页的路径："+page);
		document.queryForm.action=url+page;
		document.queryForm.submit();
	}
	
	function cancel(){
		window.location.href="${pageContext.request.contextPath}/device/consumableDeviceCompensation?page=1";
	}
	
	function newDeviceService(){
		window.location.href="${pageContext.request.contextPath}/device/newConsumableDeviceCompensation";
	}
 </script> 
</head>
<body>
              
    <div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">易耗品赔偿流水账</a></li>
	<%-- <li class="end"><a href="${pageContext.request.contextPath}/device/consumableDeviceCompensation?page=1">教务排课管理</a></li> --%>
</ul>
</div>
</div>


<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="tool-box">
<form:form name="form" method="Post" action="${pageContext.request.contextPath}/device/consumableDeviceCompensation?page=1" modelAttribute="labRoomDeviceReparation">
<ul>
	<li>
    <form:input id="stage" path="stage" onkeyup="value=value.replace(/[^\u4E00-\u9FA5\w]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5\w]/g,''))" required="true"/>
    </li>
    <input type="submit" value="查询" id="save" value="查询" />
</ul>
</form:form>
</div>
<div class="content-box">
<div class="title">易耗品赔偿流水账列表

<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_EQUIPMENTADMIN">
 <button class="btn btn-new" type="button" title="设备维修记录" onclick="newDeviceService()">新建<button>
 </sec:authorize>


</div>
<table> 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th width="5%"><center>ID</center></th>
                     <th width="10%"><center>时间</center></th>
                     <th width="10%"><center>易耗品名称</center></th>
                     <th width="15%"><center>实验室</center></th>
					<th width="5%"><center>赔偿数量</center></th>
					<th width="5%"><center>赔偿单价</center></th>
					<th width="5%"><center>赔偿总价</center></th>
					<th width="5%"><center>记录人</center></th>
					<th width="5%"><center>赔偿人</center></th>
					<th width="10%"><center>导师/辅导员</center></th>
					<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_EQUIPMENTADMIN">
					<th width="10%"><center>操作</center></th>
					</sec:authorize>
</tr>
</thead>
<tbody>
<c:forEach items="${listLabRoomDeviceReparation}" var="current"  varStatus="i">
           <tr>
               <td><center>${current.id}</center></td>
			   <td><fmt:formatDate value="${current.dateTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        	   <td><center>${current.CConsumables.name}</center></td>
			   <td><center>${current.labRoom.labRoomName}</center></td>
			   <td><center>${current.amount}</center></td>
			   <td><center>${current.price}</center></td>
			   <td><center>${current.amount*current.price}</center></td>
			   <td><center>${current.userByCreateUser.cname}</center></td>
			   <td><center>${current.userByReparationUser.cname}</center></td>
			   <td><center>${current.userByTeacher.cname}</center></td>
			   <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_EQUIPMENTADMIN">
               <td>
				   <center>
					<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/device/editLabRoomDeviceReparation?idKey=${current.id}&"  >编辑<!-- <img src="images/icons/edit.gif" /> --></a>
					<a onclick="return confirm('确定删除此条记录？')"   href="${pageContext.request.contextPath}/deleteLabRoomDeviceReparation?idKey=${current.id}"  >删除</a>
				   </center>
			   </td>
			   </sec:authorize>
         </tr>
</c:forEach> 
</tbody>
</table>
</div>
</div>
</div>
</div>
</div>
</div>        
    
    <div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/device/consumableDeviceCompensation?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/device/consumableDeviceCompensation?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/device/consumableDeviceCompensation?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/device/consumableDeviceCompensation?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/device/consumableDeviceCompensation?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/device/consumableDeviceCompensation?page=${pageModel.totalPage}')" target="_self">末页</a>
    </div>
    
    
</div>
</body>
</html>