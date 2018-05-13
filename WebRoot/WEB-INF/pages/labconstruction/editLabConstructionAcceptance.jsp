<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
  
  <!-- 下拉框的样式 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 -->
  
  <!-- 文件上传的样式和js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>
  
  
  <script type="text/javascript">
  
  
  function uploadDocument(flag){
		 $('#searchFile').window({top: 300});
		 $('#searchFile').window('open');
		 $('#file_upload').uploadify({
			'formData':{id:'${labConstructionAcceptance.id}',flag:flag},    //传值
      'swf': '${pageContext.request.contextPath}/swfupload/swfupload.swf',  
      'uploader':'${pageContext.request.contextPath}/labconstruction/acceptanceDocumentUpload;jsessionid=<%=session.getId()%>',   
      //提交的controller和要在火狐下使用必须要加的id
      buttonText:'选择附件',
       onQueueComplete : function(data) {//当队列中的所有文件全部完成上传时触发	
			    //当上传玩所有文件的时候关闭对话框并且转到显示界面
      	 $('#searchFile').window('close');
      	window.location.href="${pageContext.request.contextPath}/labconstruction/editLabConstructionAcceptance?labConstructionAcceptanceId="+${labConstructionAcceptance.id}; 	           	
			}
  });
		
}
  
  
  </script>
</head>
  
<body>

  <div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">项目验收</a></li>
			<li class="end"><c:if test="${isEdit}"><a href="javascript:void(0)">编辑</a></c:if><c:if test="${!isEdit}"><a href="javascript:void(0)">新建</a></c:if></li>
		</ul>
	</div>
  </div>
  
  <!-- 内容栏开始 -->
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
  <!-- 标题 -->
    <div class="title">
      <div id="title"><c:if test="${isEdit}">编辑</c:if><c:if test="${!isEdit}">新建</c:if>项目验收</div>
    </div>
    
  <!-- 表单 -->
    <form:form action="${pageContext.request.contextPath}/labconstruction/saveLabConstructionAcceptance?labConstructionProjectId=${labConstructionProject.id }" method="POST" modelAttribute="labConstructionAcceptance">
    <div class="new-classroom">
      <fieldset>
        <form:hidden path="id"/>
         <label>项目名称：</label>
         <td>${labConstructionProject.projectName }</td>
		      
      </fieldset>
      <fieldset>
        <label>项目编号：</label>
        <td>${labConstructionProject.projectNumber }</td>
		      
      </fieldset>
            
      <fieldset>
      	<label>创建时间：</label>
      	<input name="createdAt" class="easyui-datebox" value="<fmt:formatDate value='${labConstructionAcceptance.createdAt.time}' pattern='yyyy-MM-dd'/>" />
      </fieldset>
      
      
        <div class="submit_link">
          <input class="btn" id="save" type="submit" value="保存">
          <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
        </div>
      
    </div>
    
    </form:form>
    
    
    
    <!-- 实验项目基本信息提交后出现的东西 -->
    
    <fieldset class="introduce-box">
		<legend>项目建设阶段资料</legend>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
				<c:if test="${!isEdit}">
					<td>
						<font color=red>请您提交保存后，上传项目建设阶段资料。</font>
					</td>
				</c:if>
				
				<c:if test="${isEdit }">
				<div id="TabbedPanels1" class="TabbedPanels">
					<div class="TabbedPanelsContentGroup">
						<div class="TabbedPanelsContent">
						
							<input type="button" onclick="uploadDocument(1);" value="上传附件">
							<div class="content-box">
							<table>
							
								<thread>
									<tr>
										<th>序号</th>
										<th>名称</th>
										<th>上传时间</th>
										<th>上传人</th>
										<th>操作</th>
									</tr>	
								</thread>
								
								<tbody>
									<c:forEach items="${commonDocuments1}" var="d" varStatus="i">
									<tr>
										<td></td>
										<td>${i.count}</td>
										<td>${d.documentName}</td>
										<td><fmt:formatDate value="${d.createdAt.time}" pattern="yyyy-MM-dd"/></td>
										<td>${d.user.cname}</td>
										<td>
											<a class="btn btn-common" href="${pageContext.request.contextPath}/labconstruction/downloadAcceptanceDocument?id=${d.id}">下载</a>
										    <a class="btn btn-common"  href="${pageContext.request.contextPath}/labconstruction/deleteAcceptanceDocument?id=${d.id}"  onclick="return confirm('确定要删除吗？')">删除</a>
										</td>
									</tr>	
									</c:forEach>
								</tbody>
							
							</table>
							
							
							</div>
							
						</div>
					</div>
				</div>
				</c:if>
			</tr>
		</table>	
	</fieldset>
	
	
	<fieldset class="introduce-box">
		<legend>项目教学阶段资料</legend>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
				<c:if test="${!isEdit}">
					<td>
						<font color=red>请您提交保存后，上传项目教学阶段资料。</font>
					</td>
				</c:if>
				
				<c:if test="${isEdit }">
				<div id="TabbedPanels1" class="TabbedPanels">
					<div class="TabbedPanelsContentGroup">
						<div class="TabbedPanelsContent">
						
							<input type="button" onclick="uploadDocument(2);" value="上传附件">
							<div class="content-box">
							<table>
							
								<thread>
									<tr>
										<th>序号</th>
										<th>名称</th>
										<th>上传时间</th>
										<th>上传人</th>
										<th>操作</th>
									</tr>	
								</thread>
								
								<tbody>
									<c:forEach items="${commonDocuments2}" var="d" varStatus="i">
									<tr>
										<td></td>
										<td>${i.count}</td>
										<td>${d.documentName}</td>
										<td><fmt:formatDate value="${d.createdAt.time}" pattern="yyyy-MM-dd"/></td>
										<td>${d.user.cname}</td>
										<td>
											<a class="btn btn-common" href="${pageContext.request.contextPath}/labconstruction/downloadAcceptanceDocument?id=${d.id}">下载</a>
										    <a class="btn btn-common"  href="${pageContext.request.contextPath}/labconstruction/deleteAcceptanceDocument?id=${d.id}"  onclick="return confirm('确定要删除吗？')">删除</a>
										</td>
									</tr>	
									</c:forEach>
								</tbody>
							
							</table>
							
							
							</div>
							
						</div>
					</div>
				</div>
				</c:if>
			</tr>
		</table>	
	</fieldset>
	
	
	
	
	<fieldset class="introduce-box">
		<legend>仪器设备资料</legend>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
				<c:if test="${!isEdit}">
					<td>
						<font color=red>请您提交保存后，上传仪器设备资料。</font>
					</td>
				</c:if>
				
				<c:if test="${isEdit }">
				<div id="TabbedPanels1" class="TabbedPanels">
					<div class="TabbedPanelsContentGroup">
						<div class="TabbedPanelsContent">
						
							<input type="button" onclick="uploadDocument(3);" value="上传附件">
							<div class="content-box">
							<table>
							
								<thread>
									<tr>
										<th>序号</th>
										<th>名称</th>
										<th>上传时间</th>
										<th>上传人</th>
										<th>操作</th>
									</tr>	
								</thread>
								
								<tbody>
									<c:forEach items="${commonDocuments3}" var="d" varStatus="i">
									<tr>
										<td></td>
										<td>${i.count}</td>
										<td>${d.documentName}</td>
										<td><fmt:formatDate value="${d.createdAt.time}" pattern="yyyy-MM-dd"/></td>
										<td>${d.user.cname}</td>
										<td>
											<a class="btn btn-common" href="${pageContext.request.contextPath}/labconstruction/downloadAcceptanceDocument?id=${d.id}">下载</a>
										    <a class="btn btn-common"  href="${pageContext.request.contextPath}/labconstruction/deleteAcceptanceDocument?id=${d.id}"  onclick="return confirm('确定要删除吗？')">删除</a>
										</td>
									</tr>	
									</c:forEach>
								</tbody>
							
							</table>
							
							
							</div>
							
						</div>
					</div>
				</div>
				</c:if>
			</tr>
		</table>	
	</fieldset>
	
	<fieldset class="introduce-box">
		<legend>综合效益资料</legend>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
				<c:if test="${!isEdit}">
					<td>
						<font color=red>请您提交保存后，上传综合效益资料。</font>
					</td>
				</c:if>
				
				<c:if test="${isEdit }">
				<div id="TabbedPanels1" class="TabbedPanels">
					<div class="TabbedPanelsContentGroup">
						<div class="TabbedPanelsContent">
						
							<input type="button" onclick="uploadDocument(4);" value="上传附件">
							<div class="content-box">
							<table>
							
								<thread>
									<tr>
										<th>序号</th>
										<th>名称</th>
										<th>上传时间</th>
										<th>上传人</th>
										<th>操作</th>
									</tr>	
								</thread>
								
								<tbody>
									<c:forEach items="${commonDocuments4}" var="d" varStatus="i">
									<tr>
										<td></td>
										<td>${i.count}</td>
										<td>${d.documentName}</td>
										<td><fmt:formatDate value="${d.createdAt.time}" pattern="yyyy-MM-dd"/></td>
										<td>${d.user.cname}</td>
										<td>
											<a class="btn btn-common" href="${pageContext.request.contextPath}/labconstruction/downloadAcceptanceDocument?id=${d.id}">下载</a>
										    <a class="btn btn-common"  href="${pageContext.request.contextPath}/labconstruction/deleteAcceptanceDocument?id=${d.id}"  onclick="return confirm('确定要删除吗？')">删除</a>
										</td>
									</tr>	
									</c:forEach>
								</tbody>
							
							</table>
							
							
							</div>
							
						</div>
					</div>
				</div>
				</c:if>
			</tr>
		</table>	
	</fieldset>
	
    
    
    
    <!-- 上传图片 --> 				
	<div id="searchFile" class="easyui-window" title="上传附件" closed="true" iconCls="icon-add" style="width:400px;height:200px;">
	    <form id="form_file" method="post" enctype="multipart/form-data">
           <table  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
            <td>
          	<div id="queue"></div>
		    <input id="file_upload" name="file_upload" type="file" multiple="true">
            </tr> 
            
            
              
            </table>
         </form>
     </div>	
      <!-- 上传图片结束 --> 	
    
    
    <!-- 保存整个实验室项目卡 -->
		<form:form action="${pageContext.request.contextPath}/labconstruction/saveLabConstructionAcceptanceAll" method="POST" ><!-- modelAttribute="operationItem"没有加这个 -->
	        <div class="submit_link">
	          <input class="btn" id="save" type="submit" value="保存">
	          <%--<input class="btn btn-return" type="button" value="保存" onclick="window.history.go(-1)">  --%>
	        </div>
		</form:form>
    
  
		
		
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
</body>
</html>
