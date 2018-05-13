 <%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
 <jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
 
 <html >  
<head>
<meta name="decorator" content="iframe"/>
<script src="${pageContext.request.contextPath}/video/Scripts/modernizr.custom.js" type="text/javascript"></script>
<script type="text/javascript">
function showImage(image){
	//alert(image.src);
	//$("#img").src=image.src;
	document.getElementById("img").src=image.src;
	$('#showImage').window({
		        top: 1000   
		     });
	$('#showImage').window('open');
}
</script>
</head>
<body>
<div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">实验室及预约管理</a></li>
			<li><a href="javascript:void(0)">实验室管理</a></li>
			<li class="end"><a href="javascript:void(0)">详情</a></li>
			
		</ul>
	</div>
</div>
<div class="right-content">
	<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">
				<div id="title">实验分室详情</div>
				
				</div> 
				<div class="new-classroom">
					<fieldset>
						    <label>实验室名称：</label>${labRoom.labRoomName}
					</fieldset>		

			  		<fieldset>
						     <label>实验室编号：</label>${labRoom.labRoomNumber}
					 </fieldset>
					 <fieldset>
					 		<label>实验室英文名称：</label>${labRoom.labRoomEnName}
					 </fieldset>
					  <fieldset>
					  		<label>实验室简称：</label>${labRoom.labRoonAbbreviation}
					 </fieldset>
					  <fieldset>
					  		<label>实验室类别：</label>${labRoom.CDictionaryByLabRoom.CName}
					 </fieldset>
					  <fieldset>
						     <label> 实验室地点:</label>${labRoom.labRoomAddress}
					 </fieldset>
					   <fieldset>
						     <label>使用面积:</label>${labRoom.labRoomArea}
					 </fieldset>
					 <fieldset>
						     <label>实验室容量:</label>${labRoom.labRoomCapacity}
					 </fieldset>
					  <fieldset>
						     <label>管理机构:</label>${labRoom.labRoomManagerAgencies}
					 </fieldset>
					 <fieldset>
					 		<%-- <c:if test="${not empty labRoom.systemSubject12}">
						     <label>所属学科:</label>${labRoom.systemSubject12.SName}
						     </c:if> --%>
					 </fieldset>
					 <%-- <fieldset>
						     <label>联系方式:</label>${labRoom.labRoomPhone}
					 </fieldset> --%>
					 <fieldset>
						     <label>所属实验室:</label>
						     <c:if test="${empty labRoom.labAnnex}">
						     	无
						     </c:if>
						     <c:if test="${not empty labRoom.labAnnex}">
						     	labRoom.labAnnex.labName
						     </c:if>
					 </fieldset>
					  <fieldset>
						     <label>是否可用:</label><c:if test="${labRoom.labRoomActive==1}">是</c:if><c:if test="${labRoom.labRoomActive==0}">否</c:if>
					 </fieldset>
					 <fieldset>
						     <label>是否可预约:</label><c:if test="${labRoom.labRoomReservation==1}">是</c:if><c:if test="${labRoom.labRoomReservation==0}">否</c:if>
					 </fieldset>
					 <fieldset>
						     <label>预约是否审核:</label><c:if test="${labRoom.labRoomAudit==1}">是</c:if><c:if test="${labRoom.labRoomAudit==0}">否</c:if>
					 </fieldset>
					 
					  <fieldset class="introduce-box">
						     <label> 实验室简介:</label>
						     <textarea rows="" cols="" >${labRoom.labRoomIntroduction}</textarea>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>规章制度:</label>
						     <textarea rows="" cols="" >${labRoom.labRoomRegulations}</textarea>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label> 获奖信息: </label>
						     <textarea rows="" cols="" >${labRoom.labRoomPrizeInformation}</textarea>
					 </fieldset>
					<fieldset class="introduce-box">
						    <label> 附件 ：</label>
							    <ul class="img-box">
						     	<c:forEach items="${labRoom.commonDocuments}" var="d">
						     	<c:if test="${d.type==2}">
						    	<li>
						    	${d.documentName}
						    	<a class="btn btn-common" href="${pageContext.request.contextPath}/appointment/downloadLabRoomDocument?id=${d.id}">下载</a>
						    	
						    	</li>
						    	</c:if>
						     </c:forEach>
						     </ul>
							 
					</fieldset>
					<fieldset class="introduce-box">
						     <label>图片: </label>
						     <ul class="img-box">
						     <c:forEach items="${labRoom.commonDocuments}" var="d">
						     	<c:if test="${d.type==1}">
						    	<li>
						    	<img alt="${d.documentName}" src="${pageContext.request.contextPath}/${d.documentUrl}" border="0"  width="200px" height="150px" onclick="showImage(this);"> 
						    	
						    	</li>
						    	</c:if>
						     </c:forEach>
						     </ul>
							 
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>视频: </label>
						     <ul class="img-box">
						     <c:forEach items="${labRoom.commonVideos}" var="v">
						    	<li>
						    	<embed src="${pageContext.request.contextPath}/${v.videoUrl}"  autostart="true" loop="true" width="450" height="400">
						    	</embed>
						    	</li>
						     </c:forEach>   
						     </ul>
					 </fieldset>
				
				
				</div>
				
			
		</div>
		</div>
		</div>
	</div>
</div>
<div id="showImage" class="easyui-window" title="查看图片" closed="true" iconCls="icon-add" style="width:620px;height:340px">
		<center>
	   <img id="img" alt="" src="" width="600" height="300" border="0">
	   </center>
     </div>	

</body>


</html>