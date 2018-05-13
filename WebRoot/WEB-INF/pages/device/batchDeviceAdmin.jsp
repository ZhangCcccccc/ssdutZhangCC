<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipment-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式 -->	
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<style type="text/css">

</style>

<script type="text/javascript">
function addAdmin(typeId){
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
          url:"${pageContext.request.contextPath}/device/findUserForDeviceAdmin?cname="+cname+"&username="+username+"&cid="+${cid}+"&page=1",
          type:"POST",
          success:function(data){//AJAX查询成功
          		$(user_body).html(data);
          }
	});
	document.getElementById("adminType").value=typeId;
    $("#addAdmin").show();
    $("#addAdmin").window('open');   
    
 }

function batchAdd(){
var array=new Array();
var flag=false; //判断是否一个未选   
$("input[name='CK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
            if ($(this).is(':checked')) { //判断是否选中    
                flag = true; //只要有一个被选择 设置为 true  
            } 
        })  
      if (flag) {  
         $("input[name='CK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                      if ($(this).is(':checked')) { //判断是否选中
                          array.push($(this).val()); //将选中的值 添加到 array中 
                      }  
                  })  
   
         addAdmin(2);
//         $("#addUser").attr("onclick","addUser("+array+")");
         $("#addUser").click(function(){
        	 addUser(array);
        	});
      } else {   
      	alert("请至少选择一条记录");  
      	}  
  	}
  	
  	//遍历复选框
 	function checkAll(){
  		var checkBoxAll = document.getElementById("check-all"); 	
		$("input[name='CK']:checkbox").each(function() { //遍历所有的name为CK的 checkbox  
           $(this).attr('checked', checkBoxAll.checked);
         })    
 	}
 	
  	//添加用户
 	function addUser(deviceIdArray){	
        var array=new Array();
        var flag; //判断是否一个未选   
        $("input[name='CK_name']:radio").each(function() { //遍历所有的name为CK_name的 checkbox  
                    if ($(this).attr("checked")) { //判断是否选中    
                        flag = true; //只要有一个被选择 设置为 true  
                    }  
                })  

        if (flag) {  
           $("input[name='CK_name']:radio").each(function() { //遍历所有的name为CK_name的 checkbox  
                        if ($(this).attr("checked")) { //判断是否选中
                            array.push($(this).val()); //将选中的值 添加到 array中 
                        }  
                    })  
           //alert(array);         
           var typeId=document.getElementById("adminType").value;
           //将要所有要添加的数据传给后台处理   
		   window.location.href="${pageContext.request.contextPath}//device/saveBatchDeviceAdmin?deviceArray="+deviceIdArray+"&array="+array+"&typeId="+typeId; 
        } else {   
        	alert("请选择一条记录");  
        	}  
    	}
 	
 	function queryUser(){
 		var cname=document.getElementById("cname").value;
 		var username=document.getElementById("username").value;
 		$.ajax({
 		           url:"${pageContext.request.contextPath}/device/findUserForDeviceAdmin?cname="+cname+"&username="+username+"&cid="+${cid}+"&page=1",
 		           type:"POST",
 		           success:function(data){//AJAX查询成功
 		                  $(user_body).html(data);
 		            
 		           }
 		});
 		  
 	}
 	
 	//取消查询
 	function undo(){
 		document.getElementById("cname").value="";
 		document.getElementById("username").value="";
 		var cname="";
 		var username="";
 		$.ajax({
 		           url:"${pageContext.request.contextPath}/appointment/findUserForBacth?cname="+cname+"&username="+username+"&cid="+${cid}+"&page=1",
 		           type:"POST",
 		           success:function(data){//AJAX查询成功
 		                  $(user_body).html(data);
 		            
 		           }
 		         });
 	} 

 	//首页
 	function firstPage(page){
 		var cname=document.getElementById("cname").value;
 		var username=document.getElementById("username").value;
 		$.ajax({
 		           url:"${pageContext.request.contextPath}/appointment/findUserForBacth?cname="+cname+"&username="+username+"&cid="+${cid}+"&page="+page,
 		           type:"POST",
 		           success:function(data){//AJAX查询成功
 		                  $(user_body).html(data);
 		            
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
 		var cname=document.getElementById("cname").value;
 		var username=document.getElementById("username").value;
 		$.ajax({
 		          url:"${pageContext.request.contextPath}/appointment/findUserForBacth?cname="+cname+"&username="+username+"&cid="+${cid}+"&page="+page,
 		           type:"POST",
 		           success:function(data){//AJAX查询成功
 		                  $(user_body).html(data);
 		            
 		           }
 		});
 	}
 	//下一页
 	function nextPage(page,totalPage){
 		if(page>=totalPage){
 				page=totalPage;
 			}else{
 				page=page+1
 			}	
 		var cname=document.getElementById("cname").value;
 		var username=document.getElementById("username").value;
 		$.ajax({
 		           url:"${pageContext.request.contextPath}/appointment/findUserForBacth?cname="+cname+"&username="+username+"&cid="+${cid}+"&page="+page,
 		           type:"POST",
 		           success:function(data){//AJAX查询成功
 		                  $(user_body).html(data);
 		           }
 		});
 	}
 	//末页
 	function lastPage(page){
 		var cname=document.getElementById("cname").value;
 		var username=document.getElementById("username").value;
 		$.ajax({
 		           url:"${pageContext.request.contextPath}/appointment/findUserForBacth?cname="+cname+"&username="+username+"&cid="+${cid}+"&page="+page,
 		           type:"POST",
 		           success:function(data){//AJAX查询成功
 		                  $(user_body).html(data);
 		            
 		           }
 		});
 	}
 </script>

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
function cancelQuery(){
	window.location.href="${pageContext.request.contextPath}/device/batchDeviceAdmin?page=1";
}	

//AJAX验证是否通过安全准入
function Access(id){
	$.ajax({
	           url:"${pageContext.request.contextPath}/device/securityAccess?id="+id,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	           		if(data=="success"){
	           			//alert("您已经通过安全准入验证"+data);
	                  	window.location.href="${pageContext.request.contextPath}/device/reservationDevice?id="+id;
	           		}else{
	           			alert("您还未通过安全准入验证！"+data);
	           			window.location.href="${pageContext.request.contextPath}/device/findAllTrainingByDeviceId?deviceId="+id;
	           		}    
	           }
	});
	
}
</script>


</head>

<body>
<div class="iStyle_RightInner">
<div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">实验设备管理</a></li>
			<li class="end"><a href="javascript:void(0)">设备管理员批量设置</a></li>
		</ul>
	</div>
</div>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		     <div class="tool-box">
		      	<form:form name="queryForm" action="${pageContext.request.contextPath}/device/batchDeviceAdmin?page=1" method="post" modelAttribute="labRoomDevice">
					<table class="list_form" style="position: relative;left: 5px;top: 0;">
						<tr>
							<td>实验室：</td>
							<td>
								<form:select class="chzn-select"  path="labRoom.id">
								<form:option value="">请选择</form:option>
								<form:options cssStyle="width:300px;" items="${rooms}" itemLabel="labRoomName" itemValue="id"/>
								</form:select>		    				    				            
							</td> 
							 
							<td>设备编号：</td>
							<td>
								<form:select class="chzn-select"  path="schoolDevice.deviceNumber">
								<form:option value="">请选择</form:option>
								<form:options cssStyle="width:200px;" items="${listLabRoomDeviceAll}" itemLabel="schoolDevice.deviceNumber" itemValue="schoolDevice.deviceNumber"/>
								</form:select>
							</td> 
				     
							<td>设备名称：</td>
							<td>
								<form:select class="chzn-select"  path="schoolDevice.deviceName">
								<form:option value="">请选择</form:option>
								<form:options cssStyle="width:200px;" items="${listLabRoomDeviceAll}" itemLabel="schoolDevice.deviceName" itemValue="schoolDevice.deviceName"/>
								</form:select>
							</td> 
							
							<td>设备管理员：</td>
							<td>
								<form:select class="chzn-select"  path="schoolDevice.userByKeepUser.username">
								<form:option value="">请选择</form:option>
								<form:options cssStyle="width:200px;" items="${users}" itemLabel="cname" itemValue="username"/>
								</form:select>
							</td> 
							<td>
								<input type="button" value="取消" onclick="cancelQuery();">
								<input type="submit" value="查询">
							</td>
						</tr >
				</table>
				</form:form>		        
		    </div>
    	<div class="content-box">
    	<div class="title">
			<div id="title">设备管理</div>
			<a  href="javascript:batchAdd();"   class="btn btn-new">批量添加设备管理员</a>
			</div>
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                    	<th><input type="checkbox"  onclick="checkAll();"  id="check-all"/></th>
                    	<th>设备编号</th>
                        <th>设备名称</th>
                        <th>实验室</th>
                        <%--<th>设备管理员</th>--%>
                        <th>操作</th>
                    </tr>
                </thead>
                
                <tbody>
                		<c:forEach items="${listLabRoomDevice}" var="labRoomDevice" varStatus="i">
                		<tr>
                		<td><input type="checkbox"  name="CK"  value="${labRoomDevice.id }"/></td>
                        <td>${labRoomDevice.schoolDevice.deviceNumber}</td>
                        <td>${labRoomDevice.schoolDevice.deviceName}</td>
                        <td>${labRoomDevice.labRoom.labRoomName}</td>
                        <%--<td>${labRoomDevice.user.cname}</td>--%>
                        <td><a href="${pageContext.request.contextPath}/device/showDeviceTraining?id=${labRoomDevice.id}"   >查看</a></td>
                        </tr>
                		</c:forEach>
                       
                </tbody>
            </table>
            
<!-- 分页模块 -->
<div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/device/batchDeviceAdmin?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/device/batchDeviceAdmin?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/device/batchDeviceAdmin?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/device/batchDeviceAdmin?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/device/batchDeviceAdmin?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/device/batchDeviceAdmin?page=${pageModel.totalPage}')" target="_self">末页</a>
    </div>
<!-- 分页模块 -->
</div>

</div>
</div>
</div>
</div>
</div>

<!-- 添加设备管理员 -->
	<div id="addAdmin" class="easyui-window " title="添加设备管理员" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 600px; height: 500px;">
		<div class="content-box">
		<form:form id="userForm" method="post"   modelAttribute="admin">
			<table class="tb" id="my_show">
				<tr>
					<td>姓名：<form:input id="cname" path="user.cname"/></td>
					<td>工号：<form:input id="username" path="user.username"/>
						<a onclick="queryUser();" >搜索</a>	
						<a onclick="undo();" >取消搜索</a>							
					</td>
					<td>
						<input type="hidden" id="adminType">
						<input id="addUser" type="button" value="添加" >
					</td>
				</tr>
			</table>
		</form:form>
		
		<table id="my_show">
		<thead>
			<tr>
				<th style="width:10% !important">选择</th>
				<th style="width:30% !important">姓名</th>
				<th style="width:30% !important">工号</th>
				<th style="width:30% !important">所属学院</th>
				
			</tr>
		</thead>
			
		<tbody id="user_body">
			
		</tbody>
					
	</table>
	</div>
</div>	

<script type="text/javascript" src="${pageContext.request.contextPath}/js/is_Icons.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/is_LeftList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Downlist.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Allpages.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_SystemUI_Interface.js"></script>
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

</body>
</html>


