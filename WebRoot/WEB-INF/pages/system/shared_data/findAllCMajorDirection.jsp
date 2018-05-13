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
		window.location.href="${pageContext.request.contextPath}/appointment/listLabAnnex?page=1";
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
			专业方向管理
			</li>
		</ul>
	</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
			<div class="title">
				<div id="title">专业方向信息列表</div>
				
				<a class="btn btn-new" onclick="window.history.go(-1)">返回</a>
			</div>   	
			<div class="tool-box">
				<form:form name="queryForm" action="${pageContext.request.contextPath}/findAllCMajorDirection?page=1" method="post" modelAttribute="cMajorDirection">
					 <ul>
    				<li>专业名称： </li>
    				<li><form:input id="buildName" path="majorId"/></li>
    				
    				<li><input type="submit" value="查询"/>
					                </li>
    				</ul>
				</form:form>
		       </div>
    		<!-- 实验室列表 -->
    		<div class="content-box">   		
            <table  class="tb"  id="my_show"> 
                <thead>
                    <tr>
                        <th ><center>专业方向编号</center></th>
                        <th ><center>专业名称</center></th>
                        <th ><center>专业方向</center></th>
                </thead>
                <tbody>
                	<c:forEach items="${listCMajorDirection}" var="current"  varStatus="i">
                           <tr>
                           <td>${current.directionCode}</td>
                           <td>${current.majorId}</td>
                           <td>${current.directionName}</td>
                           
                           </tr>
                    </c:forEach> 
                </tbody>
            </table>
            
            <!-- 弹出框 -->
     <div id="doSearchStudents" class="easyui-window" title="修改学生考勤" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
     </div>

        <div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/findAllCMajorDirection?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/findAllCMajorDirection?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/findAllCMajorDirection?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/findAllCMajorDirection?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/findAllCMajorDirection?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/findAllCMajorDirection?page=${pageModel.totalPage}')" target="_self">末页</a>
    </div>
    
</div>
</div>
</div>
</div>
</div>
</div>
</div>

</body>
</html>