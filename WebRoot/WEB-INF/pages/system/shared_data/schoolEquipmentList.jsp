<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipment-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
  <script type="text/javascript">
	$(document).ready(function(){
	$("#print").click(function(){
	$("#my_show").jqprint();
	});
	
	$("#turn").click(function(){
	 	   var page=${pageModel.totalPage};
	       var id=$("#page").val();
	        if(id.length==0){
	         alert("请输入数字！");
	         }else{
	         reg=/^[-+]?\d*$/;
	          if(!reg.test(id)){    
	             alert("对不起，您输入的整数类型格式不正确!");//请将“整数类型”要换成你要验证的那个属性名称！    
	           }else{
	               if(id<=page){
	             	  window.location.href="${pageContext.request.contextPath}/propertyList?currpage="+id;
	                   }else{
	                 	  alert("对不起，您输入的整数不正确!");
	                       }
	           }    
	         }
	 	   });
	});
</script>
</head>

<body>
<div class="list_tittle">
     <form:form action="selectSchoolEquipment" method="post" modelAttribute="schoolEquipment">
 <table class="list_form">
    <tr>
        <td>搜索设备:
               <input name="equipmentNumber" onkeyup="value=value.replace(/[^\u4E00-\u9FA5\w]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5\w]/g,''))">
                <input type="submit" value="查询">&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="window.history.go(-1)">返回</a>
        </td>    
    </tr>
</table>
</form:form>
</div>
<div class="l_right">
    <div class="list_top">
        <ul class="top_tittle"><fmt:message key="property.manage"/></ul> 
         <ul  class="new_bulid"><li class="new_bulid_1"><a href="javascript:void(0)" onclick="window.history.go(-1)">返回</a></li></ul>   
      <ul  class="new_bulid"> <li class="new_bulid_1"><a href="${pageContext.request.contextPath}/exportSchoolEquipment">导出</a></li></ul>     
    	<ul class="new_bulid">
                <li class="new_bulid_1"><a id="print" href="javascript:void(0)">打印</a></li>
            </ul>  
    </div>
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                        <th><fmt:message key="equipment.number.title"/></th>
                        <th><fmt:message key="equipment.name.title"/></th>
                        <th><fmt:message key="equipment.pattern.title"/></th>
                        <th><fmt:message key="equipment.format.title"/></th>
                        <th><fmt:message key="equipment.price.title"/></th>
                        <th><fmt:message key="equipment.supplier.title"/></th>
                        <th><fmt:message key="equipment.resourse"/></th>
                        <th><fmt:message key="department.number"/></th>
                        <th><fmt:message key="operation"/>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${schoolEquipments}" var="current"  varStatus="i">	
                        <tr>
                            <td>${current.equipmentNumber}</td>
                            <td>${current.equipmentName}</td>
                            <td>${current.equipmentPattern}</td>
                            <td>${current.equipmentFormat}</td>
                            <td>${current.equipmentPrice}</td>
                            <td>${current.equipmentSupplier}</td>
                            <td>${current.projectSource}</td>
                            <td>${current.schoolAcademy.academyNumber}</td>
                            <td><a href="${pageContext.request.contextPath}/findSchoolEquipmentById?idkey=${current.id}&">详细</a></td>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
<c:choose><c:when test='${newFlag}'>
        <div class="pagination"><%--
        <fmt:message key="currentpage.title"/>当前页为:${page}&nbsp;
	 	<fmt:message key="turnto"/>：<input name="currpage" id="page" size="4"/><fmt:message key="page"/><a href="javascript:void(0)" id="turn" target="_self"><img src="${pageContext.request.contextPath}/images/newCss/go.gif" /></a>&nbsp;
   --%><a href="${pageContext.request.contextPath}/propertyList?currpage=${pageModel.firstPage}" target="_self"><fmt:message key="firstpage.title"/></a>				    
	<a href="${pageContext.request.contextPath}/propertyList?currpage=${pageModel.previousPage}" target="_self"><fmt:message key="previouspage.title"/></a>
	 第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;"><option value="${pageContext.request.contextPath}/propertyList?currpage=${page}">${page}</option><c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}"><option value="${pageContext.request.contextPath}/propertyList?currpage=${j.index}">${j.index}</option></c:if></c:forEach></select>页
	 <%--<c:forEach var="currentpage" begin="1" end="${pageModel.totalPage}"><a href="${pageContext.request.contextPath}/propertyList?currpage=<c:out value="${currentpage}"/>" target="_self"><c:out value="${currentpage}"/></a></c:forEach>
	--%><a href="${pageContext.request.contextPath}/propertyList?currpage=${pageModel.nextPage}" target="_self"><fmt:message key="nextpage.title"/></a>
	<a href="${pageContext.request.contextPath}/propertyList?currpage=${pageModel.lastPage}" target="_self"><fmt:message key="lastpage.title"/></a>
    </div>
    <div class="pagination_desc" style="float: left">
       <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
    <strong><fmt:message key="totalpage.title"/>:${pageModel.totalPage}&nbsp;</strong>
</div>
</c:when><c:otherwise>
<div class="pagination_desc" style="float: left">
       <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
</div>
</c:otherwise></c:choose> 
</div>
</body>
</html>

