<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>


<html>
<head>
<meta name="decorator" content="iframe"/>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<!-- 下拉框的样式 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 --> 


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
		window.location.href="${pageContext.request.contextPath}/findAllLabRoomDevice?page=1";
	}
 </script>
 
</head>
<body>

<div class="iStyle_RightInner">
<div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">共享数据</a>
			</li>
			<li class="end">
			实验室设备
			</li>
		</ul>
	</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
	   <ul class="TabbedPanelsTabGroup">
  
			<li class="TabbedPanelsTab" tabindex="0">
			<a href="${pageContext.request.contextPath}/findAllSchoolDevice?page=1">学院设备</a>
			</li>
		
			<li class="TabbedPanelsTab  selected"  tabindex="0">
			<a href="${pageContext.request.contextPath}/findAllLabRoomDevice?page=1">实验室设备</a>
			</li>
		
	  </ul>
		<div class="TabbedPanelsContent">
			<div class="content-box">
			<div class="title">
				<div id="title">设备信息列表</div>
				<sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
				<a class="btn btn-new" href='${pageContext.request.contextPath}/newLabRoomDevice'>新建</a>
				</sec:authorize>
			</div>   	
			<div class="tool-box">
				<form:form name="queryForm" action="${pageContext.request.contextPath}/findAllLabRoomDevice?page=1" method="post" modelAttribute="labRoomDevice">
					  <ul>
					  <li>实验室名称： </li>
    				<li><form:select id="labRoomId" path="labRoom.id" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${labRooms}" var="curr">
  							<form:option value="${curr.id}">${curr.labRoomName}</form:option>
  						</c:forEach>
  					</form:select></li>
    				<li>设备名称： </li>
    				<li><form:select id="deviceName" path="schoolDevice.deviceName" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listLabRoomDeviceAll}" var="curr">
  							<form:option value="${curr.schoolDevice.deviceName}">${curr.schoolDevice.deviceName}</form:option>
  						</c:forEach>
  					</form:select></li>
    				<li>设备编号： </li>
    				<li><form:select id="deviceNumber" path="schoolDevice.deviceNumber" class="chzn-select">
  					<form:option value="">请选择</form:option>
  						<c:forEach items="${listLabRoomDeviceAll}" var="curr">
  							<form:option value="${curr.schoolDevice.deviceNumber}">${curr.schoolDevice.deviceNumber}</form:option>
  						</c:forEach>
  					</form:select></li>
    				<li><input type="submit" value="查询"/></li>
    				<li><input type="button" onclick="cancel()" value="取消查询"/></li>
    				</ul>
				</form:form>
		       </div>
    		<!-- 实验室列表 -->
    		<div class="content-box">   		
            <table  class="tb"  id="my_show"> 
                <thead>
                    <tr>
                    	<th>序号</th>
                        <th >设备编号</th>
                        <th >设备名称</th>
                        <th >设备型号</th>
                        <%--<th >设备分类</th>--%>
                        <th >实验室名称</th> 
                        <%--<th >设备生产厂商</th>--%>
                        <%--<th >领用部门编号</th>--%>
                        <sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
                        <th >操作</th>
                        </sec:authorize>
                </thead>
                <tbody>
                	<c:forEach items="${listLabRoomDevice}" var="current"  varStatus="i">
                           <tr>
                           <td>${i.count+pageSize*(page-1) }</td>
                           <td>${current.schoolDevice.deviceNumber}</td>
                           <td>${current.schoolDevice.deviceName}</td>
                           <td>${current.schoolDevice.devicePattern}</td>
                            <td>${current.labRoom.labRoomName }</td>
                           <%--<td>${current.manufacturer}</td>--%>
                           
                           <%--<td>${current.schoolAcademy.academyNumber}</td>--%>
                           <sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
                           <td>
                           <%--<a class="btn btn-common" href='${pageContext.request.contextPath}/showLabRoomDeviceById?deviceNumber=${current.deviceNumber}'>查看</a>--%> 
                           	<a class="btn btn-common" href='${pageContext.request.contextPath}/editLabRoomDevice?id=${current.id}'>编辑</a>
							<a class="btn btn-common" href='${pageContext.request.contextPath}/deleteLabRoomDevice?id=${current.id}' onclick="return confirm('确定删除？');">删除</a>
                           </td>
                           </sec:authorize>
                           </tr>
                    </c:forEach> 
                </tbody>
            </table>
            

        <div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/findAllLabRoomDevice?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/findAllLabRoomDevice?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/findAllLabRoomDevice?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/findAllLabRoomDevice?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/findAllLabRoomDevice?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/findAllLabRoomDevice?page=${pageModel.totalPage}')" target="_self">末页</a>
    </div>
    <!-- 下拉框的js -->
	<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		    var config = {
		      '.chzn-select': {search_contains : true},
		      '.chzn-select-deselect'  : {allow_single_deselect:true},
		      '.chzn-select-no-single' : {disable_search_threshold:10},
		      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
		      '.chzn-select-width'     : {width:"95%"}
		    }
		    for (var selector in config) {
		      $(selector).chosen(config[selector]);
		    }
		</script>
	<!-- 下拉框的js -->
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>