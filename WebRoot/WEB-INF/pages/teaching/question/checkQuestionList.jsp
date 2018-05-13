<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>


<html>
<head>
<meta name="decorator" content="iframe"/>

	<link rel="stylesheet" href="/gvsun/css/iconFont.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/index/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index/animatecolor-plugin.js"></script>
    <script type="text/javascript"	src="${pageContext.request.contextPath}/js/browse.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/themes/default/easyui.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<style type="text/css">
	 .children1 td{
		text-indent: 10px;
	}
	 .children2 td{
		text-indent: 20px;
	}
</style>
<script type="text/javascript">
	$(function(){
	   	$("#print").click(function(){
			$("#my_show").jqprint();
		});
	});
	
	function openwindow(id){
		if(id!=""){//修改则查询原信息
			$("#questionpoolId").val(id);
			$.ajax({
				url:"${pageContext.request.contextPath}/teaching/question/findQusetionStringById?id="+id,
		       	type:"POST",
		       	async:false,
		       	success:function(data){
					$("#questionTitle").val(data[0]);
					$("#type").val(data[1]);
					findQusetionListByTypeAndId(data[1],id);
					$("#description").val(data[2]);
					$("#questionpoolParentId").val(data[3]);
		       	}
			})
		}else{//新增
			$("#questionpoolId").val("");
			$("#type").val(1);
			findQusetionListByTypeAndId(1,id);		
			$("#description").val("");
			$("#questionTitle").val("");
			$("#questionPoolParentId").val("");
		}
		
		//绑定change事件
		$("#questionType").change(function(){
			findQusetionListByTypeAndId($("#questionType").val(),id)
		})
		var topPos = window.pageYOffset+100;
		//使得弹出框在屏幕顶端可见
	    $('#openWindow').window({top:topPos+"px"}); 
	    $("#openWindow").window('open');
	}
	
	function findQusetionListByTypeAndId(type,id){
		$.ajax({
			url:"${pageContext.request.contextPath}/teaching/question/findQusetionListByTypeAndId?type="+type+"&id="+id,
	       	type:"POST",
	       	success:function(data){
	       		$("#questionpoolParentId").html(data);
	       	}
		})
	}
</script> 
</head>
<body>
	
	<div class="right-content">
		<div id="TabbedPanels1" class="TabbedPanels">
			<div class="TabbedPanelsContentGroup">
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">题库类别</div>
							<mytag:JspSecurity realm="add" menu="questionPool">
								<a class="btn btn-new" href="javascript:void(0);" onclick="openwindow('')">添加分类</a>
							</mytag:JspSecurity>
						</div>   
						
					    
			    		<div class="content-box">
			    			<b>课程题库</b>
				            <table class="tb"  id="my_show"> 
				                <thead>
				                    <tr>
				                        <th>序号</th>
				                        <th>分类名称</th>
				                        <th>创建人</th>
				                        <th>创建时间</th>
				                        <th>分类信息</th>
				                        <th>操作</th>
				                    </tr>
				                </thead>
				                <tbody>
				                	<c:forEach items="${tAssignmentQuestionpools }" var="questionPool" varStatus="i">
				                		<c:if test="${questionPool.type != 1&& questionPool.TAssignmentQuestionpool == null }">
				                			<tr>
					                			<td>${i.count }</td>
					                			<td>
													${questionPool.title }
												</td>
					                			<td>${questionPool.user.cname }</td>
					                			<td>
													<fmt:formatDate value="${questionPool.createdTime.time }" pattern="yyyy-MM-dd"/>				                			
					                			</td>
					                			<td>${questionPool.description }</td>
					                			<td>
					                				<a href="${pageContext.request.contextPath }/teaching/question/checkQuestion?examId=${examId }&questionId=${questionPool.questionpoolId}">选择</a>
												</td>
					                		</tr>
					                		<c:forEach items="${questionPool.TAssignmentQuestionpools }" var="questionPool1" varStatus="j">
						                		<tr class="children1">
						                			<td>${j.count }</td>
						                			<td>
														${questionPool1.title }
													</td>
						                			<td>${questionPool1.user.cname }</td>
						                			<td>
														<fmt:formatDate value="${questionPool1.createdTime.time }" pattern="yyyy-MM-dd"/>				                			
						                			</td>
						                			<td>${questionPool1.description }</td>
						                			<td>
						                				<a href="${pageContext.request.contextPath }/teaching/question/checkQuestion?examId=${examId }&questionId=${questionPool1.questionpoolId}">选择</a>
													</td>
						                		</tr>
						                		<c:forEach items="${questionPool1.TAssignmentQuestionpools }" var="questionPool2" varStatus="k">
							                		<tr class="children2">
							                			<td>${k.count }</td>
							                			<td>
							                				${questionPool2.title }
							                			</td>
							                			<td>${questionPool2.user.cname }</td>
							                			<td>
															<fmt:formatDate value="${questionPool2.createdTime.time }" pattern="yyyy-MM-dd"/>				                			
							                			</td>
							                			<td>${questionPool2.description }</td>
							                			<td>
							                				<a href="${pageContext.request.contextPath }/teaching/question/checkQuestion?examId=${examId }&questionId=${questionPool2.questionpoolId}">选择</a>
							                			</td>
							                		</tr>
							                	</c:forEach>
						                	</c:forEach>
				                		</c:if>
				                	</c:forEach>
				                </tbody>
				            </table>
				            <br>  	
			    			<b>公共题库</b>	
				            <table class="tb"  id="my_show"> 
				                <thead>
				                    <tr>
				                        <th>序号</th>
				                        <th>分类名称</th>
				                        <th>创建人</th>
				                        <th>创建时间</th>
				                        <th>分类信息</th>
				                        <th>操作</th>
				                    </tr>
				                </thead>
				                <tbody>
				                	<c:forEach items="${tAssignmentQuestionpools }" var="questionPool" varStatus="i">
				                		<c:if test="${questionPool.type == 1 && questionPool.TAssignmentQuestionpool == null}">
					                		<tr>
					                			<td>${i.count }</td>
					                			<td>
					                				${questionPool.title }
					                			</td>
					                			<td>${questionPool.user.cname }</td>
					                			<td>
													<fmt:formatDate value="${questionPool.createdTime.time }" pattern="yyyy-MM-dd"/>				                			
					                			</td>
					                			<td>${questionPool.description }</td>
					                			<td>
					                				<a href="${pageContext.request.contextPath }/teaching/question/checkQuestion?examId=${examId }&questionId=${questionPool.questionpoolId}">选择</a>
					                			</td>
					                		</tr>
					                		<c:forEach items="${questionPool.TAssignmentQuestionpools }" var="questionPool1" varStatus="j">
						                		<tr class="children1">
						                			<td>${j.count }</td>
						                			<td>
						                				${questionPool1.title }
						                			</td>
						                			<td>${questionPool1.user.cname }</td>
						                			<td>
														<fmt:formatDate value="${questionPool1.createdTime.time }" pattern="yyyy-MM-dd"/>				                			
						                			</td>
						                			<td>${questionPool1.description }</td>
						                			<td>
						                				<a href="${pageContext.request.contextPath }/teaching/question/checkQuestion?examId=${examId }&questionId=${questionPool1.questionpoolId}">选择</a>
													</td>
						                		</tr>
						                		<c:forEach items="${questionPool1.TAssignmentQuestionpools }" var="questionPool2" varStatus="k">
							                		<tr class="children2">
							                			<td>${k.count }</td>
							                			<td>
							                				${questionPool2.title }
							                			</td>
							                			<td>${questionPool2.user.cname }</td>
							                			<td>
															<fmt:formatDate value="${questionPool2.createdTime.time }" pattern="yyyy-MM-dd"/>				                			
							                			</td>
							                			<td>${questionPool2.description }</td>
							                			<td>
							                				<a href="${pageContext.request.contextPath }/teaching/question/checkQuestion?examId=${examId }&questionId=${questionPool2.questionpoolId}">选择</a>
							                			</td>
							                		</tr>
							                	</c:forEach>
						                	</c:forEach>
				                		</c:if>
				                	</c:forEach>
				                </tbody>
				            </table>
			    			
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="openWindow" class="easyui-window" title="题库分类" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width: 1000px; height:300px;">
		<form:form action="${pageContext.request.contextPath }/teaching/question/saveCheckQuestionPool" method="POST" modelAttribute="tAssignmentQuestionpool">
			<div class="right-content">
				<div id="TabbedPanels1" class="TabbedPanels">
					<div class="TabbedPanelsContentGroup">
						<div class="TabbedPanelsContent">
							<div class="tool-box">
								<table>
									<form:hidden path="questionpoolId" id="questionpoolId"/>
									<tr>
										<td>题库类型</td>
										<td>
											<form:select path="type" id="questionType">
												<form:option value="1">公共题库</form:option>
												<form:option value="2">课程题库</form:option>
											</form:select>
										</td>
									</tr>
									<tr>
										<td>父类别</td>
										<td>
											<form:select path="TAssignmentQuestionpool.questionpoolId" id="questionpoolParentId">
												<form:option value="">请选择</form:option>
												<%--<form:options items="${map }"/>
											--%></form:select>
										</td>
									</tr>
									<tr>
										<td>名称</td>
										<td>
											<form:input path="title" id="questionTitle" required="true"/>
										</td>
									</tr>
									<tr>
										<td>类别信息</td>
										<td>
											<form:input path="description" id="description"/>
										</td>
									</tr>
								</table>
							</div>
							<input type="hidden" name="examId"  value="${examId }"/>
							<input type="submit" value="保存" />
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>