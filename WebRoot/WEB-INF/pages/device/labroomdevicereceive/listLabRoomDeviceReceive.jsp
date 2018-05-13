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
		window.location.href="${pageContext.request.contextPath}/device/newLabRoomDeviceReceive";
	}
 </script> 
</head>
<body>

<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">实验设备管理</a></li>
	<li><a class="end">易耗品赔偿流水账</a></li>
	</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">
<div id="title">易耗品赔偿流水账列表</div>
<c:if test="${admin eq true }">
     <input class="btn btn-new" type="button" value="新建" onclick="newDeviceService();">
</c:if>
</div>

<div class="content-box"> 
<table> 
<thead>
<tr>
  <!--  <th>选择</th> -->
  <th width="15%">时间</th>
   <th  width="10%" >易耗品名称</th>	
	<th  width="10%">实验室</th>
	<th  width="10%">实验项目</th>
	<th  width="10%">申领数量</th>
	<th  width="10%">记录人</th>
	<th  width="10%">申领人</th>
	<th  width="10%">管理人</th>
	<c:if test="${admin eq true }">
	<th width="12%">操作</th>
	</c:if>
</tr>
</thead>
<tbody>


<c:forEach items="${listLabRoomDeviceReceive}" var="current" varStatus="i">
					<c:choose>
						<c:when test="${(i.count) % 2 == 0}">
		    				<c:set var="rowclass" value="rowtwo"/>
						</c:when>
						<c:otherwise>
		    				<c:set var="rowclass" value="rowone"/>
						</c:otherwise>
					</c:choose>	
				<c:if test="${cid == current.labRoom.labAnnex.labCenter.id }">
				<tr class="${rowclass}">
				<td>
                	 <fmt:formatDate value="${current.dateTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
                	 </td>
					<td nowrap="nowrap" class="tabletd">
							${current.CConsumables.name}
					</td>
					<td nowrap="nowrap" class="tabletd">
							${current.labRoom.labRoomName}
					</td>
					<td nowrap="nowrap" class="tabletd">
							${current.operationItem.lpName}
					</td>
					<td nowrap="nowrap" class="tabletd">
							${current.amount}
					</td>
					<td nowrap="nowrap" class="tabletd">
							${current.userByCreateUser.username}
					</td>
					<td nowrap="nowrap" class="tabletd">
							${current.userByReceiveUser.username}
					</td>
					<td nowrap="nowrap" class="tabletd">
							${current.userByManagerUser.username}
					</td>
					<%-- <td nowrap="nowrap" class="tabletd">
							${current.description}
						&nbsp;
					</td> --%>
					<c:if test="${admin eq true }">
					<td>
                    <a class="button" title="修改" href="${pageContext.request.contextPath}/device/editLabRoomDeviceReceive?idKey=${current.id}"  ><span>编辑</span></a>
					<a onclick="return confirm('确定删除此条记录？')"  class="button"   href="${pageContext.request.contextPath}/device/deleteLabRoomDeviceReceive?idKey=${current.id}"  ><span>删除</span></a>
					</td>
                    </c:if>
				</tr>
				</c:if>

</c:forEach> 
</tbody>
</table>
       <div class="page">
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/device/listLabRoomDeviceReceive?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/device/listLabRoomDeviceReceive?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/device/listLabRoomDeviceReceive?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/device/listLabRoomDeviceReceive?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/device/listLabRoomDeviceReceive?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/device/listLabRoomDeviceReceive?page=${pageModel.totalPage}')" target="_self">末页</a>
    </div>
  </div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>