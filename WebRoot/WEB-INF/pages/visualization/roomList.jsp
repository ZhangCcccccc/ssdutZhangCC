<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>


<html>
<head>
<meta name="decorator" content="iframe"/>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">

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
		window.location.href="${pageContext.request.contextPath}/visualization/roomList?page=1";
	}
 </script> 
</head>


<body>

<div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">可视化实验室列表</a></li>
			<li class="end"><a href="javascript:void(0)">实验室管理</a></li>
			<li class="end"><a href="javascript:void(0)">实验分室管理</a></li>
		</ul>
	</div>
</div>

<!-- 查询表单 -->






<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">
					<div id="title">实验室列表</div>
				</div>   
				<div class="tool-box">
					<form:form name="queryForm" action="${pageContext.request.contextPath}/visualization/roomList?page=1" method="post" >
					<ul>
						<li>楼宇：</li> 
						<li> <select id="buildNumber" name="buildNumber" value="${buildNumber}" class="chzn-select" >
						<c:if test="${buildNumber ne ''}">
						<option value="${buildNumber}"> ${buildName}</option>
						</c:if>
						<option value="">请选择</option>
						<c:forEach items="${builds}" var="build"  varStatus="i">
						<option value="${build.buildNumber}"> ${build.buildName}</option>
						</c:forEach>
						</select>	</li>
						
						<li>楼层：</li> 
						<li> <select id="floor" name="floor" value="${floor}" class="chzn-select">
						<c:if test="${floor ne ''}">
						<option value="${floor}">${floor}层</option>
						</c:if>
						<option value="">请选择</option>
						<c:forEach var="floor"  begin="1" end="6"  varStatus="i">
						<option value="${floor}">${floor}层 </option>
						</c:forEach>
						</select>	</li>						
						
						<li><input type="submit" value="查询"/></li>
               			<li> <input type="button" value="取消" onclick="cancel();"/></li>
					</ul>
					
					</form:form>
		    	</div>
    	<div class="content-box">
            <table  class="tb"  id="my_show"> 
                <thead>
                    <tr>
                        <th><center>序号</center></th>
                        <th><center>实验室名称</center></th>
                        <th><center>实验室编号</center></th>
                        <th><center>实验室地点</center></th>
                        <th><center>操作</center></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${labRooms}" var="current"  varStatus="i">	
                        <tr>
                            <td><center>${i.count}</center></td>
                            <td><center>
                            ${current.labRoomName}
                            <c:if test="${current.isUsed!=1}">
                            <font color=red>（实验室已禁用）</font>
                            </c:if>
                            </center></td>
                            <td><center>${current.labRoomNumber}</center></td>
                            <td>
                            <c:if test="${not empty current.systemRoom}">
                            <center>${current.systemRoom.systemBuild.systemCampus.campusName}<!-- 校区 -->
                            ${current.systemRoom.systemBuild.buildName} <!-- 楼栋 -->
                            ${current.systemRoom.roomName}(${current.systemRoom.roomNo}) <!-- 房间 -->
                            </center>
                            </c:if>
                            </td>
                            <td>
                            	<center>
                            	<sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
                            	 <a href="${pageContext.request.contextPath}/labRoom/getLabRoom?id=${current.id}" >添加设备</a>&nbsp;&nbsp;
                            	 <a href="${pageContext.request.contextPath}/visualization/addLabRoomImage?id=${current.id}" >添加图片</a>&nbsp;&nbsp;
                                <a href="${pageContext.request.contextPath}/visualization/roomImage?id=${current.id}" target="_blank">设备定位</a>&nbsp;&nbsp;
                                </sec:authorize>
                                </center>
                            </td>     
                        </tr>
                        </c:forEach>
                </tbody>
                
            </table>
 <div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/visualization/roomList?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/visualization/roomList?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/visualization/roomList?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/visualization/roomList?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/visualization/roomList?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/visualization/roomList?page=${pageModel.totalPage}')" target="_self">末页</a>
    </div>
</div>
</div>
</div>
</div>
</div>

</body>
</html>
