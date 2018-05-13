 <%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
 <jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
 
 <html >  
<head>
<meta name="decorator" content="iframe"/>
<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">

<style type="text/css">
.select-box{overlow:hidden;}
.left-box,.right-box{float:left;
margin-right:15px;}
.right-box a{color:#333;
	font-size:12px;
	}
.right-box{width:250px;
	border:1px solid #333;}
.right-box select{width:250px;
	overflow:scroll;}
.select-action a{color:#333;
	text-decoration:none;}
.chzn-container{width: 200px;}
.chzn-container ,#build_chzn{width:100%;}
.chzn-container{width:100% !important;}

</style>
</head>
<body>
<div class="right-content">
	<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">
				<div id="title">修改物联硬件</div>
				
				</div> 
<form:form action="${pageContext.request.contextPath}/appointment/saveLabRoomAgent?roomId=${agent.labRoom.id}" method="POST" modelAttribute="agent">
		<div class="new-classroom">
					<fieldset>
						    <label>>硬件名称：</label>
						       	<%-- <form:hidden path="id"/>
							   	<form:select path="CAgentType.id" class="chzn-select">
								<form:options items="${types}" itemLabel="name" itemValue="id"/>
								</form:select> --%>
							   	<form:select path="CDictionary.id" class="chzn-select">
								<form:options items="${types}" itemLabel="CName" itemValue="id"/>
								</form:select>
							</td>
					</fieldset>
					<fieldset>
						    <label>Ip：</label>
							   	<form:input path="hardwareIp" />
					</fieldset>
					<fieldset>
						    <label>端口：</label>
							   	<form:input path="hardwarePort" />
					</fieldset>
					<fieldset>
						    <label>PageCam：</label>
							   	<form:input path="hardwareRemark" />
					</fieldset>
					<fieldset>
						    <label>物联服务器：</label>
							   	<form:select path="commonServer.id" class="chzn-select">
								<form:options items="${serverList}" itemLabel="serverName" itemValue="id"/>
								</form:select>
					</fieldset>
					
		</div>	 
			<div class="moudle_footer">
			        <div class="submit_link">
			          <input class="btn" id="save" type="submit" value="确定">
					<input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
			        </div>
			    </div>
		</table>
		
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
		
	</form:form>

  
     
     </div>
		</div>
		</div>
	</div>
</div>  

</body>
</html>