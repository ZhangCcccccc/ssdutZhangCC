 <%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
 <jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
 
 <html >  
<head>
<meta name="decorator" content="iframe"/>
<script src="${pageContext.request.contextPath}/video/Scripts/modernizr.custom.js" type="text/javascript"></script>
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
				<div id="title">实验室详情</div>
				
				</div> 
				<div class="new-classroom">
					<fieldset>
						    <label>实验室名称：</label>${labAnnex.labName}
					</fieldset>		

			  		<fieldset>
						     <label>实验室简称：</label>${labAnnex.labShortName}
					 </fieldset>
					 <fieldset>
					 		<label>实验室英文名称：</label>${labAnnex.labEnName}
					 </fieldset>
					  <fieldset>
					  		<label>所属学科：</label>${labAnnex.labSubject}
					 </fieldset>
					  <fieldset>
						     <label>管理机构:</label>${labAnnex.belongDepartment}
					 </fieldset>     
				     <fieldset>
						     <label>实验室类别:</label>${labAnnex.CDictionaryByLabAnnex.CName}
					 </fieldset>
					 <fieldset>
						     <label>所属中心:</label>${labAnnex.labCenter.centerName}
					 </fieldset>
					   <fieldset>
						     <label>联系方式:</label>${labAnnex.contact}
					 </fieldset>
					  <fieldset class="introduce-box">
						     <label> 实验室简介:</label>
						     <textarea rows="" cols="" >${labAnnex.labDescription}</textarea>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>规章制度:</label>
						     <textarea rows="" cols="" >${labAnnex.labAttention}</textarea>
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label> 获奖信息: </label>
						     <textarea rows="" cols="" >${labAnnex.awardInformation}</textarea>
					 </fieldset>
				
				
				
				</div>
				
			
		</div>
		</div>
		</div>
	</div>
</div>

</body>


</html>