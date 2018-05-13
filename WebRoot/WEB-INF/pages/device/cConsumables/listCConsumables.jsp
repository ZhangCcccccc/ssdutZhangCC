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
		window.location.href="${pageContext.request.contextPath}/cConsumables/listCConsumables?page=1";
	}
 </script>
 
</head>
<body>

<div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">实验队伍管理</a></li>
			<li class="end"><a href="javascript:void(0)">人员管理</a></li>
		</ul>
	</div>
</div>







<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
			<div class="title">
				<div id="title">易耗品信息列表</div>
				<c:if test="${admin eq true }">
				<a class="btn btn-new" href="${pageContext.request.contextPath}/cConsumables/newCConsumables">新建</a>
				</c:if>
				<a class="btn btn-new" onclick="window.history.go(-1)">返回</a>
			</div>   	
			<div class="tool-box">
				<form:form name="queryForm" action="${pageContext.request.contextPath}/cConsumables/listCConsumables?page=1" method="post" modelAttribute="cConsumables">
					 <ul>
    				<li>易耗品名称： </li>
    				<li><form:input id="name" path="name"/></li>
    				
    				<li><input type="submit" value="查询"/>
					                <input type="button" value="取消" onclick="cancel();"/></li>
    				</ul>
				</form:form>
		       </div>
    		<!-- 实验室列表 -->
    		<div class="content-box">   		
            <table  class="tb"  id="my_show"> 
                <thead>
                    <tr>
                    <th >序号</th>
                        <th >易耗品名称</th>
                        <th >易耗品计量</th>
                        <th >易耗品规格</th>
                        <c:if test="${admin eq true }">
                        <th >操作</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${listCConsumables}" var="current"  varStatus="i">
                           <tr>
                           <td>${i.count}</td>
                           <td>${current.name}</td>
                            <td>${current.measurementUnit}</td>
                            <td>${current.consumablesStandard}</td>
                            <c:if test="${admin eq true }">
                           <td>
                           <a href="${pageContext.request.contextPath}/cConsumables/editCConsumables?idKey=${current.id}" ">编辑</a>
                           <a href="${pageContext.request.contextPath}/cConsumables/deleteCConsumables?idKey=${current.id}" onclick="return confirm('确认要删除吗？')">删除</a>
                           </td>
                           </c:if>
                           </tr>
                    </c:forEach> 
                </tbody>
            </table>
    
</div>
</div>
</div>
</div>
</div>
</body>
</html>