<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<meta name="decorator" content="iframe"/>
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
		window.location.href="${pageContext.request.contextPath}/appointment/listLabAnnex?page=1";
	}
function newDeviceService() {
    //alert("asd");
    window.location.href =  "${pageContext.request.contextPath}/Lab/labAnnexList?currpage=1";

}
function addStatus(s){
		window.location.href="${pageContext.request.contextPath}/Elective/courseselection?idkey="+s;
	}
function Withdrawalcandidature(s){
	window.location.href="${pageContext.request.contextPath}/Elective/Withdrawalcandidature?idkey="+s;
}	
function newnmyclass(){
window.location.href="${pageContext.request.contextPath}/Elective/labRoomTopicsStudentElective?currpage=1&tage=1";
}
</script>
<html >  
<head> 

</head>
<body>
<div class="iStyle_RightInner">

<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">实验室预约课程</a></li>
	<li class="end"><a href="javascript:void(0)">课程选择</a></li>
</ul>
</div>
</div>





<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title" >
<span align="right">
<button  class="btn" type="button" title="新建" >新建</button></span><span align="right">
<button  class="btn" type="button" title="我的选课" onclick="newnmyclass();" >我的选课</button></span></div>
<table> 
<thead>
<tr>
   <th>序号</th>
   <th>活动名称</th>
   <th>实验室</th>
   <th>活动类别</th>
   <th>上课时间</th>
   <th>教师</th>
   <th>已选人数/最多人数</th>
   <th>操作</th>
 </tr>
</thead>
<tbody>
<c:forEach items="${list}" var="current" varStatus="i">	
<tr>
            <td >
        	${i.index+1}
        	</td>
        	<td >
            ${current.eventName}
        	</td>
        	<td >
        	${current.labRoom.labRoomName}
        	</td>
        	<td >
        	${current.CLabReservationActivityCategory.name}
        	</td>
        	<td >
        	${current.CLabReservationWeek.name}-${current.schoolWeekday.weekdayName}-${current.systemTime.timeName}
        	</td>
        	<td >
        	${current.user.cname}
        	</td>
        	<td >
              ${current.selecteNumber}/${current.number}
        	</td>
        	<td >
        	<c:if test="${current.selecteNumber <= current.number}">
        	   <c:if test="${fn:length(current.users)>0}">
        	 <c:forEach items="${current.users}" var="s">
        	     <c:if test="${s.username==users.username}">
        	           <button onclick="Withdrawalcandidature(${current.id});">推选</button>
        	     </c:if>
        	     <c:if test="${s.username!=users.username}">
        	        	<button onclick="addStatus(${current.id});">选择</button>
        	     </c:if>
        	</c:forEach>
            </c:if>
              <c:if test="${fn:length(current.users)==0}"><button onclick="addStatus(${current.id});">选择</button></c:if>
        	</c:if>       	
        	</td>

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
</div>

       <div class="pagination" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/device/labRoomDeviceRepair/listlabRoomDeviceRepair?page=${pageModel.totalPage}')" target="_self">末页</a>
    </div>



</body></html>




