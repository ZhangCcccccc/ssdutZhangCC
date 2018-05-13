<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
<!-- 添加关联设备对应js -->
<script type="text/javascript">
//添加设备
function addDevices(page)
{
   var page=page;
   var deviceName = $("#deviceName").val();
   var deviceNumber = $("#deviceNumber").val();
   var labRoomId = $("#labRoomId").val();
   if($("#labRoomId").val()==""||$("#labRoomId").val()==null){
	   labRoomId=0;
   }
   $.ajax({
        url:"${pageContext.request.contextPath}/device/findSchoolDeviceByNameAndNumber?labRoomId="+labRoomId+"&deviceName="+deviceName+"&deviceNumber="+deviceNumber+"&page="+page,
        type:"POST",
        success:function(data){//AJAX查询成功
              $(schoolDevice_body).html(data);
        }
 });  
  $("#addDevice").show();
  $('#addDevice').window({left:"200px", top:"200px"});
  $("#addDevice").window('open');
}

//保存设备
function saveInnerSameDevice(){
	 var array=new Array();
    var flag; //判断是否一个未选   
    $("input[name='CK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                if ($(this).attr("checked")) { //判断是否选中    
                    flag = true; //只要有一个被选择 设置为 true  
                }  
            })  

    if (flag) {  
       $("input[name='CK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                    if ($(this).attr("checked")) { //判断是否选中
                        array.push($(this).val()); //将选中的值 添加到 array中 
                    }  
                })  
       //将要所有要添加的数据传给后台处理   
		   window.location.href="${pageContext.request.contextPath}/device/saveInnerSameDevice?deviceId="+${device.id}+"&array="+array; 
    } else {   
    	alert("请至少选择一条记录");  
    	} 
}

</script>

</head>
<body>
<div class="iStyle_RightInner">

<div class="navigation">
<div id="navigation">
<ul>
	<li class="end"><a href="javascript:void(0)">关联设备管理</a></li>
</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
      <div class="title">
      <div id="title">关联设备列表</div>
      <a class="btn btn-new" onclick="addDevices(1)">添加关联设备</a>
    </div>
    
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                      <th>序号</th>
                      <th>设备编号</th>
                        <th>设备名称</th>
                        <th>操作</th>
                    </tr>
                </thead>
                
                <tbody>
                  <c:forEach items="${schoolDevices}" var="curr" varStatus="i">
                  <tr>
                        <td>${i.count}</td>
                        <td>${curr.deviceNumber}</td>
                        <td>${curr.deviceName}</td>
                        <td>
                          <a onclick="return confirm('确定解除关联？')" href="${pageContext.request.contextPath}/device/deleteInnerSameDevice?deviceId=${device.id}&deviceNumber=${curr.deviceNumber}">解除关联</a>
                        </td>
                       </tr>
                  </c:forEach>
                </tbody>
            </table>
</div>
</div>
</div>
</div>
</div>
</div>

   <div id="addDevice" class="easyui-window" closed="true" modal="true" minimizable="true" title="实验室设备" style="width: 1000px;height: 500px;padding: 20px">
  <div class="content-box">
  <form:form id="queryForm" method="post" modelAttribute="schoolDevice">
  <table>
  <tr>
  
  	<td>
  		所属实验室
  		<select id="labRoomId" class="chzn-select" required="true">
          <option value="">请选择</option>
          <c:forEach items="${labRooms}" var="l">
            <option value="${l.id}">${l.labRoomName}(${l.labRoomAddress })</option>
          </c:forEach>
        </select>
  	</td>
  
    <td>
      	设备名称:
      <form:input id="deviceName" path="deviceName"/>
    </td>
    
    <td>
      	设备编号:
      <form:input id="deviceNumber" path="deviceNumber" placeholder="请输入数字" validType="length[0,9]"/>
    </td>
    
    <td>
      <input type="button" value="搜索" onclick="addDevices(1);">
    </td>
    
    <td>
      <input type="button" value="添加" onclick="saveInnerSameDevice();"> 
    </td>
  </tr>
  
  </table>
  </form:form>
  
  <form name="device_form" method="post">
    <table>
      <thead>
      <tr>
        <th>设备编号</th>
        <th>设备名称</th>
        <th>型号</th>
        <th>价格</th>
        <th><input id="check_all" type="checkbox" onclick="checkAll();"/></th>
      </tr>
      </thead>
      
      <tbody id="schoolDevice_body">
  
    </tbody>
    </table>
  </form>
  </div>
  </div>
   


<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select'           : {search_contains:true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
</script>
<!-- 下拉框的js -->
</body>
</html>