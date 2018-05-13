<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<html>
<head>
  <!-- 文件上传的样式和js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>  
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<meta name="decorator" content="iframe"/>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.gif" />
<script type="text/javascript">
$(function(){
    //设置邮箱需要验证
    $("#email").validatebox();
})
function addRecords(){
         $("#repairRecords").window('open');      
    }

function checkMail(){
	var email = $("#email").val();
	if(email.trim()!=""){
		var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (!filter.test(email)){
			alert('您的邮箱格式不正确');
			return false;
		}
	}
	
}
</script>

<script type="text/javascript">
function uploaddocment(){
		
	 //获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#searchFile').window({left:"0px", top:topPos+"px"});
	 $('#searchFile').window('open');
	 
	 $('#file_upload').uploadify({
		'formData':{id:1},    //传值
       'swf': '${pageContext.request.contextPath}/swfupload/swfupload.swf',  
       'uploader':'${pageContext.request.contextPath}/newoperation/uploadphoto;jsessionid=<%=session.getId()%>',		//提交的controller和要在火狐下使用必须要加的id
       buttonText:'选择文件',
      'onUploadSuccess' : function(file, data, response) {
		  
			   $("#doc").append(data); 
	 },
	 onQueueComplete : function(data) {
	   var ss="";
	   
	    $("tr[id*='s_']").each(function(){
        var eval1=$(this).attr("id");
         var str1= eval1.substr(eval1.indexOf("_")+1 ,eval1.lenght);  
        var vals1=str1+"_"+$(this).attr("value");
       
        ss+=str1+",";
        });
	   
       $("#docment").attr("value",ss); 
        $('#searchFile').window('close');	
	 }
   });
	
}

function delectuploaddocment(s){
if(confirm( '你真的要删除吗？ ')==false){return   false;}else{ 
 $.post('${pageContext.request.contextPath}/operation/delectdnolinedocment?idkey='+s+'',function(data){  //serialize()序列化
		   if(data=="ok"){
		   $("#s_"+s+"").empty();
		   
		   }
		
	 });
	 }
}
</script>
</script>
</head>
<body>
<div class="iStyle_RightInner">

<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">个人中心</a></li>
	<li class="end"><a href="${pageContext.request.contextPath}/personal/listMyInfo">我的资料</a></li>
</ul>
</div>
</div>


<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">我的信息</div>
<table> 
<thead>
<tr>
<th>用户工号</th>
<th>用户姓名</th>

<th>用户身份</th>
<th>学院/部门</th>
<!-- <th>专业</th> -->
<th>专业</th>
<th>年级</th>
<th>班级</th>
<th>电话</th>
<th>邮箱</th>
<th>操作</th>
<th><input type="button" value="上传头像"onclick="uploaddocment();" ></th>
</tr>
</thead>
<tbody>

<tr>
<td>${user.username}</td>
<td>${user.cname}</td>
<td>${str}</td>
<td>${user.schoolAcademy.academyName}</td>

<td><%-- ${user.schoolMajor.majorName} --%></td>
<td>${user.grade}</td>
<td><%-- ${user.schoolClasses.className} --%></td>
<td>${user.telephone}</td>
<td>${user.email}</td>
<td><a onclick="addRecords();">编辑</a></td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
</div>
</div>
</div>

<div id="repairRecords" class="easyui-window" title="修改个人信息" modal="true" closed="true" iconCls="icon-add" style="width:1000px;height:200px">
<form:form action="${pageContext.request.contextPath}/personal/saveMyInfo" method="POST" onsubmit="return checkMail()" modelAttribute="user">	
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="tool-box">
<table>
<tbody>	
			<tr>
			<form:hidden path="username" value=""/>
			</tr>
			<tr>
			<td class="label" valign="top"> 邮箱: </td>
			<td><form:input id="email"  path="email" validtype="email" invalidMessage="邮箱格式不正确,正确格式如xxx@xxx.xxx" /> </td>
			</tr>
			<tr>
			<td> &nbsp;</td><td></td>
			</tr>
			<tr>
			<td class="label" valign="top"> 电话: </td>
			<td><form:input id="username" path="telephone" class="easyui-numberbox" placeholder="仅可输入数字" /> </td>
			</tr>
</table>

</div>
<input id="save" type="submit" value="保存">
</div>
</div>
</div>
</div>
</form:form>

</div>
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
</body>
</html>
