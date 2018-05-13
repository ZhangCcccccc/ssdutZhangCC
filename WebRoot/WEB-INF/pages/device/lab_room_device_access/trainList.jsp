<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.lab-resources" />
<html>
<head>
<meta name="decorator" content="iframe" />
<title></title>
<style type="text/css" media="screen">
			@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/icon.css");
			@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/gray/easyui.css");
			@import url("${pageContext.request.contextPath}/css/style.css");
</style>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery-1.8.3.min.js"></script>
<!-- 文件上传的样式和js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>

<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<script type="text/javascript">

  /**
 培训结果表单提交 
 */
 function trainFrom(){
 	var c=document.getElementById("radioTable").getElementsByTagName("input"); 
 			var idArray=new Array();
 			var valueArray=new Array();
       	    for(var i=0;i<c.length;i++){   
                if(c[i].type=="radio" && c[i].checked){
                		idArray.push(c[i].id);
                		valueArray.push(c[i].value);
           		}
          	}
          	/* alert(idArray);
          	alert(valueArray); */
          	$("#trainFrom").attr("action","${pageContext.request.contextPath}/device/saveTrainResult?idArray="+idArray+"&valueArray="+valueArray);
 }
 
 
  
 

</script>

</head>
<body>
<div class="right-content">
	

	<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<!-- 左侧开始 -->
			<div class="content-box-double-left">
				<div class="tool-box">
						<form:form action="${pageContext.request.contextPath}/device/findAllTrainingByDeviceId?deviceId=${deviceId}" method="post" modelAttribute="train">
						<table>
						<tr>
							<td>学期:</td>
							<td>
							<form:select path="schoolTerm.id">
							<form:options items="${terms}" itemLabel="termName" itemValue="id"/>
							</form:select>
							</td>
							<td><input type="submit" value="查询"></td>
								
						</tr>
					
						</table>
						</form:form>
					
				</div>
				<div class="content-double">
					<div class="title">
					<div id="title">设备培训</div>
					</div>
						
					<table >
						<thead>
							<tr>
								<th>时间</th>
								<th>地点</th>
								<th>内容</th>
								<th>教师</th>
								<th>人数</th>
								<th>学期</th>
								<th>状态</th>
								<th>通过率</th>
								<th>操作</th>
							</tr>
						</thead>
						<c:forEach items="${trainList}" var="t">
						<tr>
							<td><fmt:formatDate value="${t.time.time}" pattern="yyyy-MM-dd"/> </td>
							<td>${t.address}</td>
							<td>${t.content}</td>
							<td>${t.user.cname}</td>
							<td>${t.joinNumber}/${t.number}</td>
							<td>${t.schoolTerm.termName}</td>
							<%-- <td>${t.CTrainingStatus.name}</td> --%>
							<td>${t.CTrainingStatus.CName}</td>
							<td>${t.passRate}</td>
							<td><a href="${pageContext.request.contextPath}/device/findTrainingPeopleByDeviceIdAndTrainId?deviceId=${t.labRoomDevice.id}&id=${t.id}">查看</a>
							<c:if test="${t.number>t.joinNumber}"><!-- 没有超出人数限制 -->
							<sec:authorize ifAnyGranted="ROLE_STUDENT"><!-- 只有学生才能参加培训 -->
							<%-- <c:if test="${t.CTrainingStatus.id==1}"> --%><!-- 待培训 -->
							<c:if test="${t.CTrainingStatus.CCategory=='c_training_status'&&t.CTrainingStatus.CNumber=='1'}">
							<c:forEach items="${map}" var="m">
							<c:if test="${m.key==t.id}"><!-- 匹配培训id -->
							<c:if test="${m.value==1}">
							<a href="${pageContext.request.contextPath}/device/joinTraining?id=${t.id}">预约培训</a>
							</c:if>
							<c:if test="${m.value==0}">
							已参加
							</c:if>
							
							</c:if>
							</c:forEach>
							
							</c:if>
							</sec:authorize>
							</c:if>
							<c:if test="${t.number<=t.joinNumber}">
							人数已满
							</c:if>
							</td>
						</tr>
						</c:forEach>
						
						
						
					</table>
				</div>
			</div>
			<!-- 左侧列表结束 -->

			
			<!-- 右侧开始 -->
			<div class="content-box-double-right">
				<div class="tool-box">
				</div>
				<div class="content-double">
					<div class="title">培训名单及培训情况</div>
					
					<table id="radioTable">
						<thead>
							<tr>
								<th>序号</th>
								<th>学号</th>
								<th>姓名</th>
								<th>培训结果</th>
							</tr>
						</thead>
						<c:forEach items="${peoples}" var="p" varStatus="i">
						<tr>
							<td>${i.count}</td>
							<td>${p.user.username}</td>
							<td>${p.user.cname}</td>
							<td>
							<c:forEach items="${results}" var="result"  varStatus="s">
							<%-- <c:if test="${p.CTrainingResult.id==result.id}"> --%>
							<c:if test="${p.CDictionary.id==result.id}">
							<input type="radio" id="${p.id}" name="p${i.count}" value="${result.id}" checked="checked">${result.CName}
							</c:if>
							
							<%-- <c:if test="${p.CTrainingResult.id!=result.id}"> --%>
							<c:if test="${p.CDictionary.id!=result.id}">
							<input type="radio" id="${p.id}" name="p${i.count}" value="${result.id}">${result.CName}
							</c:if>
							
							</c:forEach>
							
							</td>
						</tr>
						</c:forEach>
						<c:if test="${peoples.size()>0}">
						<form id="trainFrom" method="post">
						<table>
						<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_EQUIPMENTADMIN">
						<tr>
						<td>
						<input type="submit" value="提交" onclick="trainFrom();">
						</td>
						</tr>
						</sec:authorize>
						</table>
						</form>
						</c:if>
					</table>
				</div>
			</div>
			<!-- 右侧结束 -->
		</div>
	  </div>
	</div>
</div>


</body>
</html>