<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/iconFont.css"	rel="stylesheet">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
function setCookie(name,value)
{
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

//读取cookies
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
function closeBatche(){
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
           parent.layer.close(index);//关闭弹窗
}

// groupId -- 当前分组   currpage -- 分页
function queryUser(groupId, currpage){
	if(groupId!=0){
		setCookie("groupId",groupId);
	}
	var classNumber=$("#classNumber").val();
	if(currpage == 0){/// 取消
		currpage = 1;
		document.getElementById("classNumber").value="";
		classNumber="";
	}
	$.ajax({
	           url:"${pageContext.request.contextPath}/newtimetable/findUserByDetailNoAndClassNumber?courseDetailNo=${courseDetailNo}&classNumber="
	        		   +classNumber+"&batchId=${timetableBatchId}"+"&currpage="+currpage+"&groupId="+groupId,
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("user_body").innerHTML=data;
	            
	           }
	});
	$("#addAdmin").show();
    $("#addAdmin").window('open'); 
}
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
function addStudents(){
	var groupId=getCookie("groupId");
	
    var array=new Array();
    var flag; //判断是否一个未选   
    $("input[name='CK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                if ($(this).attr("checked")) { //判断是否选中    
                    array.push($(this).val()); //将选中的值 添加到 array中 
                    flag = true; //只要有一个被选择 设置为 true  
                }  
            })
    if(flag){ 
    	console.log(groupId);
    	 $.ajax({
             url:"${pageContext.request.contextPath}/newtimetable/saveInBatches?groupId="+groupId+"&array="+array,
             type:"POST",
             success:function(data){//AJAX查询成功
                 location.reload();
             }
          });
    } else {   
    	alert("请至少选择一条记录");  
    	}  
	}

	</script>

</script>
</head>
<body>

<div class="iStyle_RightInner">
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="tool-box">
</div>
<div class="content-box">
<div class="title">手动分批</div>
<table class="tb" cellspacing="0" id="my_show"> 
<thead>
<tr>
    <th>批次</th>
    <th>人数</th>
    <th>操作</th>
</tr>
</thead>
<tbody>
<c:forEach items="${groups}" var="current"  varStatus="i">	
<tr>
   <td>${current.groupName}</td>
   <td>${current.timetableGroupStudentses.size()}</td>
   <td> 
    <a class="btn btn-common" href='javascript:void(0)'	onclick="queryUser(${current.id}, 1)">添加学生名单</a>
   	</td>
</tr>
</c:forEach>
</tbody>
</table>
<button onclick="closeBatche()">确定</button>
</div>
</div>
</div>
</div>
</div>
</div>            
</div>
<div id="addAdmin" class="easyui-window " title="添加学生" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 600px; height: 500px;">
		<div class="content-box">
		<form:form id="userForm" >
			<table class="tb" id="my_show">
				<tr>
					<td>班级：</td>
					<td><input id="classNumber" name="classNumber"/></td>
					<td>
						<input type="button" value="搜索" onclick="queryUser(0,1);">

						<input type="button" value="取消" onclick="queryUser(0,0);">

						<input type="button" value="添加" onclick="addStudents();">
					</td>
				</tr>
			</table>
		</form:form>
		<table id="my_show">
					<thead>
						<tr>
							<th style="width:30% !important">姓名</th>
							<th style="width:30% !important">工号</th>
							<th style="width:30% !important">班级</th>
							<th><input id="check_all" type="checkbox" onclick="checkAll();"/></th>	
						</tr>
					</thead>
											
					<tbody id="user_body">
											
					</tbody>					
		</table>
	    </div>
	</div>

</body>
</html>

