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
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
  <!-- 文件上传的样式和js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>
  
  
  
  <script type="text/javascript">
  
  //全部选择或不选
  function checkAll()
  {
    if($("#check_all").attr("checked"))
    {
      $(":checkbox").attr("checked", true);
    }
    else
    {
      $(":checkbox").attr("checked", false);
    }
  }
  /**
  添加项目成员
  */
  function addMembers(){
  	var cname=document.getElementById("cname").value;
  	var username=document.getElementById("username").value;
  	$.ajax({
  	           url:"${pageContext.request.contextPath}/labconstruction/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&labConstructionProjectId="+${labConstructionProjectId}+"&page=1",
  	           type:"POST",
  	           success:function(data){//AJAX查询成功
  	           		document.getElementById("user_body").innerHTML=data;
  	           }
  	});
  	//获取当前屏幕的绝对位置
   	var topPos = window.pageYOffset+10;
    $("#addMembers").show();
    $('#addMembers').window({top:topPos+"px"});
    $("#addMembers").window('open');   
      
   }
  
  function uploadDocument(){
	  
		 $('#searchFile').window({top: 1200});
		 $('#searchFile').window('open');
		 $('#file_upload').uploadify({
			'formData':{id:'${labConstructionProject.id}'},    //传值
         'swf': '${pageContext.request.contextPath}/swfupload/swfupload.swf',  
         'uploader':'${pageContext.request.contextPath}/labconstruction/projectDocumentUpload;jsessionid=<%=session.getId()%>',   
         //提交的controller和要在火狐下使用必须要加的id
         buttonText:'选择立项相关附件',
          onQueueComplete : function(data) {//当队列中的所有文件全部完成上传时触发	
   			    //当上传玩所有文件的时候关闭对话框并且转到显示界面
         	 $('#searchFile').window('close');
         	window.location.href="${pageContext.request.contextPath}/labconstruction/editLabConstructionProject?labConstructionProjectId="+${labConstructionProject.id}+"&status="+${status}; 	           	
			}
     });
		
  }
  </script>
  
</head>
  
<body>

  <div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">项目立项</a></li>
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
      <div id="title"><c:if test="${isEdit}">编辑</c:if><c:if test="${!isEdit}">新建</c:if>项目立项</div>
    </div>
    
  <!-- 表单 -->
    <form:form action="${pageContext.request.contextPath}/labconstruction/saveLabConstructionProject?status=${status}" method="POST" modelAttribute="labConstructionProject">
    <div class="new-classroom">
      <fieldset>
        <form:hidden path="id"/>
        <label>实验室项目编号：</label>
        <form:input path="projectNumber" class="easyui-validatebox" required="true"
        />
      </fieldset>
      <fieldset>
        <label>实验室项目名称：</label>
        <form:input path="projectName" class="easyui-validatebox" required="true"/>
      </fieldset>
      <fieldset>
        <label>所属院（系、部）：</label>
        <form:select path="schoolAcademy.academyNumber" class="chzn-select" required="true">
          <form:option value="${user.schoolAcademy.academyNumber}">${user.schoolAcademy.academyName}</form:option>
          <c:forEach items="${schoolAcademies}" var="sa">
            <form:option value="${sa.academyNumber}">${sa.academyName}</form:option>
          </c:forEach>
        </form:select>
      </fieldset>
      <fieldset>
	      <c:if test="${!isEdit }">
	        <label>项目负责人：</label>
	        <form:select path="user.username" id="project_manager" class="chzn-select" required="true">
	          <form:option value="${user.username }">[${user.username}]${user.cname}</form:option>
	          <c:forEach items="${teachers}" var="t">
              <form:option value="${t.username}">[${t.username}]${t.cname}</form:option>
              </c:forEach>
	        </form:select>
	      </c:if>
	      <c:if test="${isEdit }">
	      	  <label>项目负责人：</label>
		      <form:select path="user.username" id="project_manager" class="chzn-select" required="true">
		      	<form:option value="${labConstructionProject.user.username }">${labConstructionProject.user.cname }</form:option>  
		      </form:select>
	      </c:if>
      </fieldset>
      <fieldset>
        <label>联系电话：</label>
        <form:input path="telephone" class="easyui-validatebox" required="true"
        onkeyup="value=value.replace(/[^\d]/g,'') "   
		onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"  
        />
      </fieldset>
      
      <fieldset>
        <label>Email地址：</label>
        <form:input path="email" class="easyui-validatebox" required="true"/>
      </fieldset>
      <fieldset>
        <label>预算总额：</label>
        <form:input path="projectBudget" class="easyui-validatebox" required="true"
        onkeyup="value=value.replace(/[^\d]/g,'') "   
		onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"  
        />
      </fieldset>
      <fieldset>
      	<label>填报时间：</label>
      	<input id="createdAt" type="text" name="createdAt" style="width:200px;" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
				value="<fmt:formatDate value="${labConstructionProject.createdAt.time}" pattern="yyyy-MM-dd" />" readonly />
      </fieldset>
      <fieldset>
        <label>项目分析：</label>
        <form:textarea path="projectAnalysis" style="width:1000px;height:100px" required="true"/>
      </fieldset>
      <fieldset>
        <label>建设方案：</label>
        <form:textarea path="projectScheme" style="width:1000px;height:100px" required="true"/>
      </fieldset>
      
      <div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="submit" value="保存">
          <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
        </div>
      </div>
      
    </div>
    </form:form>
    
    
    
      <!-- 实验项目基本信息提交后出现的东西 -->
      
      <fieldset class="introduce-box">
		<legend>相关附件</legend>
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
				<c:if test="${!isEdit}">
					<td>
						<font color=red>请您提交保存后，添加项目附件。</font>
					</td>
				</c:if>
				
				<c:if test="${isEdit }">
				<div id="TabbedPanels1" class="TabbedPanels">
					<div class="TabbedPanelsContentGroup">
						<div class="TabbedPanelsContent">
						
							<input type="button" onclick="uploadDocument();" value="上传附件">
							<div class="content-box">
							<c:forEach items="${labConstructionProject.commonDocuments}" var="d">
								<li>
									${d.documentName}
									<a class="btn btn-common" href="${pageContext.request.contextPath}/labconstruction/downloadProjectDocument?id=${d.id}">下载</a>
									  
								    <a class="btn btn-common"  href="${pageContext.request.contextPath}/labconstruction/deleteProjectDocument?id=${d.id}&status=${status}"  onclick="return confirm('确定要删除吗？')">删除</a>
								</li>
							</c:forEach>
							</div>
							
						</div>
					</div>
				</div>
				</c:if>
			</tr>
		</table>	
				
				
		
		
		</fieldset>
      
        <fieldset class="introduce-box">
		<legend>项目成员</legend>
		
		<table id="listTable" width="50%" cellpadding="0">
			<tr>
			
				<c:if test="${!isEdit}">
					<td>
						<font color=red>请您提交保存后，添加项目成员。</font>
					</td>
				</c:if>
				<c:if test="${isEdit }">
				
				  <div id="TabbedPanels1" class="TabbedPanels">
				  <div class="TabbedPanelsContentGroup">
				  <div class="TabbedPanelsContent">
				        <li><input type="button" onclick="addMembers();" value="添加"></li>
				    <div class="content-box">
				    <table>
				      <thead>
				      <tr>
				        <th>姓名</th>
				        <th>工号</th>
				        <th>操作</th>
				      </tr>
				      </thead>
				      <tbody>
				      <c:forEach items="${projectMembers}" var="pm" varStatus="i">
				      <tr>
				      	<td></td>
				        <td>${i.count}</td>
				        <td>${pm.user.cname}</td>
				        <td>${pm.user.username}</td>
				        <td>
				          <a href="${pageContext.request.contextPath}/labconstruction/deleteProjectMember?id=${pm.id}&status=${status}" onclick="return confirm('确认要删除吗？')">删除</a>
				        </td>
				      </tr>
				      </c:forEach>
				      </tbody>
				    </table>
				    </div>
				    <!-- 选择教师的弹窗  -->
		<div id="addMembers" class="easyui-window " title="添加项目成员" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 600px; height: 500px;">
		<div class="content-box">
		<form:form id="userForm" method="post"   modelAttribute="projectMember">
			<table class="tb" id="my_show">
				<tr>
					<td>姓名：<form:input id="cname" path="user.cname"/></td>
					<td>工号：<form:input id="username" path="user.username"/>
						<a onclick="queryUser();" >搜索</a>	
					</td>
					<td>
						<input type="button" value="添加" onclick="addUser();">
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
<script type="text/javascript">
function addUser(){
        var array=new Array();
        var flag; //判断是否一个未选   
        $("input[name='CK_name']:checkbox").each(function() { //遍历所有的name为CK_name的 checkbox  
                    if ($(this).attr("checked")) { //判断是否选中    
                        flag = true; //只要有一个被选择 设置为 true  
                    }  
                })  

        if (flag) {  
           $("input[name='CK_name']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                        if ($(this).attr("checked")) { //判断是否选中
                            array.push($(this).val()); //将选中的值 添加到 array中 
                        }  
                    })  
           //将要所有要添加的数据传给后台处理   
		   window.location.href="${pageContext.request.contextPath}/labconstruction/saveProjectMember?labConstructionProjectId="+${labConstructionProjectId}+"&array="+array+"&status="+${status}; 
        } else {   
        	alert("请至少选择一条记录");  
        	}  
    	}

function queryUser(){
	
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
			   url:"${pageContext.request.contextPath}/labconstruction/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&labConstructionProjectId="+${labConstructionProjectId}+"&page=1",
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	            
	           }
	});
	  
}


//首页
function firstPage(page){
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
		       url:"${pageContext.request.contextPath}/labconstruction/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&labConstructionProjectId="+${labConstructionProjectId}+"&page=1",
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
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
			   url:"${pageContext.request.contextPath}/labconstruction/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&labConstructionProjectId="+${labConstructionProjectId}+"&page=1",
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
			page=page+1
		}	
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
			   url:"${pageContext.request.contextPath}/labconstruction/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&labConstructionProjectId="+${labConstructionProjectId}+"&page=1",
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	           }
	});
}
//末页
function lastPage(page){
	var cname=document.getElementById("cname").value;
	var username=document.getElementById("username").value;
	$.ajax({
			   url:"${pageContext.request.contextPath}/labconstruction/findUserByCnameAndUsername?cname="+cname+"&username="+username+"&labConstructionProjectId="+${labConstructionProjectId}+"&page=1",
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	            
	           }
	});
}
</script>
	</div>
				    
				  </div>
				  </div>
				  </div>
				  
				  
				  
				</c:if>
			</tr>
		</table>
		
		</fieldset>
		
		
		
		<!-- 上传图片 --> 				
	<div id="searchFile" class="easyui-window" title="上传附件" closed="true" iconCls="icon-add" style="width:400px;height:200px">
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
		
		
		
		<!-- 保存整个项目立项 -->
		<form:form action="${pageContext.request.contextPath}/labconstruction/saveLabConstructionProjectAll?status=${status}" method="POST" ><!-- modelAttribute="operationItem"没有加这个 -->
			<div class="moudle_footer">
	        <div class="submit_link">
	          <input class="btn" id="save" type="submit" value="保存">
	          <%--<input class="btn btn-return" type="button" value="保存" onclick="window.history.go(-1)">  --%>
	        </div>
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


