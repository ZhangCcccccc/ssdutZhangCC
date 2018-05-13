<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>


<html>
<head>
<meta name="decorator" content="iframe"/>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<link rel="stylesheet" href="/gvsun/css/iconFont.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery-1.7.1.min.js" ></script>

<script type="text/javascript">
	$(function(){
	   	$("#print").click(function(){
			$("#my_show").jqprint();
		});
	});


	//首页
	function first(url){
		document.queryForm.action=url;
		document.queryForm.submit();
		//window.location = url;
	}
	//末页
	function last(url){
		document.queryForm.action=url;
		document.queryForm.submit();
		//window.location = url;
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
		//window.location = url+page;
	}
	//下一页
	function next(url){
		var totalPage=${pageModel.totalPage};
		var page=${page};
		if(page>=totalPage){
			page=totalPage;
		}else{
			page=page+1;
		}
		//alert("下一页的路径："+page);
		document.queryForm.action=url+page;
		document.queryForm.submit();
		//window.location = url+page;
	}
	
	function cancel(){
		$("#queryType").val("1");		
		$("#titleQuery").val("");
		document.queryForm.submit();
		//window.location = url+page;
	}
	
	function checkAll(obj){
		if($(obj).prop("checked")){
			$("input[name='tMessageId']").prop("checked",true);
		}else{
			$("input[name='tMessageId']").prop("checked",false);
		}
	}
	
	function checkChecked(obj){
		if(!$(obj).prop("checked")){
			$("#allChoice").prop("checked",false);
		}else{
			var total = $("#tbody").find("input[type='checkbox']").size();
			var checked = $("#tbody").find("input:checked").size();
			if(total==checked){
				$("#allChoice").prop("checked",true);
			}
		}
	}
	
	function searchUser(){
		var classQuery = $("#classQuery").val().trim();
		//var cnameQuery = $("#cnameQuery").val().trim();
		//if(classQuery=="" && cnameQuery==""){
		//	alert("请输入至少一个搜索条件！");
		//	return;
		//}
		$.ajax({
	        url:"${pageContext.request.contextPath}/teaching/message/findClassnameBykeyword?classname="+classQuery+"&page=1",
	        type:"POST",
	        success:function(data){//AJAX查询成功
	     		document.getElementById("user_body").innerHTML=data;
	        }
		});
		
	}
	
	//班级列表的分页
	//首页
	function firstPage(page){
		var classQuery = $("#classQuery").val().trim();
		//var cnameQuery = $("#cnameQuery").val().trim();
		$.ajax({
	        url:"${pageContext.request.contextPath}/teaching/message/findClassnameBykeyword?classname="+classQuery+"&page="+page,
	        type:"POST",
	        success:function(data){//AJAX查询成功
	     		document.getElementById("user_body").innerHTML=data;
	        }
		});
	}
	//上一页
	function previousPage(page){
		if(page==1){
			page=1;
		}else{
			page=page-1;
		}	
		var classQuery = $("#classQuery").val().trim();
//		var cnameQuery = $("#cnameQuery").val().trim();
		$.ajax({
	        url:"${pageContext.request.contextPath}/teaching/message/findClassnameBykeyword?classname="+classQuery+"&page="+page,
	        type:"POST",
	        success:function(data){//AJAX查询成功
	     		document.getElementById("user_body").innerHTML=data;
	        }
		});
	}
	//下一页
	function nextPage(page,totalPage){
		if(page>=totalPage){
			page=totalPage;
		}else{
			page=page+1;
		}	
		var classQuery = $("#classQuery").val().trim();
//		var cnameQuery = $("#cnameQuery").val().trim();
		$.ajax({
	        url:"${pageContext.request.contextPath}/teaching/message/findClassnameBykeyword?classname="+classQuery+"&page="+page,
	        type:"POST",
	        success:function(data){//AJAX查询成功
	     		document.getElementById("user_body").innerHTML=data;
	        }
		});
	}
	//末页
	function lastPage(page){
		var classQuery = $("#classQuery").val().trim();
//		var cnameQuery = $("#cnameQuery").val().trim();
		$.ajax({
	        url:"${pageContext.request.contextPath}/teaching/message/findClassnameBykeyword?classname="+classQuery+"&page="+page,
	        type:"POST",
	        success:function(data){//AJAX查询成功
	     		document.getElementById("user_body").innerHTML=data;
	        }
		});
	}
	
	function checkUserAll(obj){
		if($(obj).prop("checked")){
			$("input[name='CK_name']").prop("checked",true);
		}else{
			$("input[name='CK_name']").prop("checked",false);
		}
	}
	
	function checkUserChecked(obj){
		if(!$(obj).prop("checked")){
			$("#allUserChoice").prop("checked",false);
		}else{
			var total = $("#user_body").find("input[type='checkbox']").size();
			var checked = $("#user_body").find("input:checked").size();
			if(total==checked){
				$("#allUserChoice").prop("checked",true);
			}
		}
	}
	function publish(){
		var arrayOne = new Array();
		var arrayTwo = new Array();
		$("#tbody").find("input:checked").each(function(){
			arrayOne.push($(this).val());
		})
		$("#user_body").find("input:checked").each(function(){
			arrayTwo.push($(this).val());
		})
		if(arrayOne==""){
			alert("请选择要发布的通知！");
		}else if(arrayTwo==""){
			alert("请选择要发布的对象！");
		}else{
			$.ajax({
		        url:"${pageContext.request.contextPath}/teaching/message/publishClassMessage?messageIds="+arrayOne+"&classnumbers="+arrayTwo,
		        type:"POST",
		        success:function(data){//AJAX查询成功
		        	alert("通知发布成功！");
		        }
			});
		}
	}
</script> 
</head>
<body>
	<div class="navigation">
		<div id="navigation">
			<ul>
				<li><a href="javascript:void(0)">教学管理</a></li>
				<li class="end"><a href="javascript:void(0)">通知</a></li>
			</ul>
		</div>
	</div>
	
	
	<div class="right-content">
		<div id="TabbedPanels1" class="TabbedPanels">
			<div class="TabbedPanelsContentGroup">
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">通知管理</div>
							<mytag:JspSecurity realm="add" menu="message">
								<a class="btn btn-new" href="${pageContext.request.contextPath}/teaching/message/newTMessage">新建通知</a>
							</mytag:JspSecurity>	
						</div>   
						
						<mytag:JspSecurity realm="check" menu="message">
							<div class="tool-box">
								<form name="queryForm" action="${pageContext.request.contextPath}/teaching/message/listMessages?currpage=1" method="post">
									<ul>
				    					<li>
									 		<select id="queryType" name="queryType">
									 			<option value="1" <c:if test="${queryType ==1 }">selected="selected"</c:if>>模糊搜索</option>
									 			<option value="2" <c:if test="${queryType ==2 }">selected="selected"</c:if>>精确搜索</option>
									 		</select>
									 	</li>
				    					<li><input type="text" id="titleQuery" name = "titleQuery" value="${titleQuery }" placeholder="请输入标题关键字"/></li>
				    					<li>
				    						<input type="submit" value="查询"/>
									    	<input type="button" value="取消" onclick="cancel();"/>
									    </li>
				    				</ul>
								</form>
						    </div>
						</mytag:JspSecurity>
					    
			    		<div style="width: 60%;float: left;"> 
			    			<div class="content-box">
					            <table  class="tb"  id="my_show"> 
					                <thead>
					                    <tr>
					                        <th width="8%"><input id="allChoice" type="checkbox" onchange="checkAll(this)" />全选</th>
					                        <th width="30%">标题</th>
					                        <th width="10%">发布人</th>
					                        <th width="12%">发布时间</th>
					                        <th width="20%">操作</th>
					                    </tr>
					                </thead>
					                <tbody id="tbody">
					                	<c:forEach items="${tMessages}" var="tMessage"  varStatus="i">
					                    	<tr>
					                            <td><input type="checkbox" onchange="checkChecked(this)" name="tMessageId" value="${tMessage.id }" ></td>
					                            <td>${tMessage.title }</td>
					                            <td>${tMessage.user.cname }</td>
					                            <td><fmt:formatDate value="${tMessage.releaseTime.time }" pattern="yyyy-MM-dd"></fmt:formatDate> </td>
					                            <td>
					                            	<mytag:JspSecurity realm="update" menu="message">
					                            		<a href="${pageContext.request.contextPath}/teaching/message/updateTMessageById?id=${tMessage.id }">修改</a>
					                            	</mytag:JspSecurity>
					                            	<mytag:JspSecurity realm="delete" menu="message">
					                            		<a href="${pageContext.request.contextPath}/teaching/message/deleteTMessageById?id=${tMessage.id }" onclick="return confirm('确认要删除该通知吗？')">删除</a>
					                            	</mytag:JspSecurity>
					                            </td>
					                        </tr>
				                        </c:forEach>
					                </tbody>
					            </table>
						        <div class="page" >
							        ${totalRecords}条记录,共${pageModel.totalPage}页
							    	<a href="javascript:void(0)" onclick="first('${pageContext.request.contextPath}/teaching/message/listMessages?currpage=1')" target="_self">首页</a>			    
									<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/teaching/message/listMessages?currpage=')" target="_self">上一页</a>
									第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
										<option value="${pageContext.request.contextPath}/teaching/message/listMessages?currpage=${page}">${page}</option>
											<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
										    	<c:if test="${j.index!=page}">
										    		<option value="${pageContext.request.contextPath}/teaching/message/listMessages?currpage=${j.index}">${j.index}</option>
										    	</c:if>
								    		</c:forEach>
							    	</select>页
									<a href="javascript:void(0)"    onclick="next('${pageContext.request.contextPath}/teaching/message/listMessages?currpage=')" target="_self">下一页</a>
							 		<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/teaching/message/listMessages?currpage=${pageModel.totalPage}')" target="_self">末页</a>
							    </div>
						    </div>
						</div>
						<div style="width: 39%;float: left;">   
							<div class="content-box" style="min-height: 160px;">	
								<table>
									<tr>
										<td>班级</td>
										<td><input type="text" id= "classQuery" placeholder="请输入班级名称" style="width: 90px;"/></td>
										<%--<td>姓名</td>
										<td><input type="text" id= "cnameQuery" placeholder="请输入姓名" style="width: 60px;"/></td>
										--%><td>
											<mytag:JspSecurity realm="check" menu="message">
												<a onclick="searchUser();">搜索</a>
											</mytag:JspSecurity>
											</td>
										<td>
											<mytag:JspSecurity realm="update" menu="message">
												<input type="button" value="发布" onclick="publish();"/>
											</mytag:JspSecurity>
										</td>
									</tr>
								</table>
								<table>
									<thead>
										<tr>
											<th style="width:10% !important"><input type='checkbox' id="allUserChoice" onchange="checkUserAll(this)"/></th>
											<th style="width:30% !important">班级</th>
											
										</tr>
									</thead>
									<tbody id="user_body">
									
									</tbody>
								</table>
								
					    	</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>