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

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">
<script type="text/javascript">
var batchAuditArray;
var refresh;
var rtag;
function batchAudit(){
var array=new Array();
var str="";	
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
                          rtag=$("#rtag").val();//将表征着审核阶段的rtag值传过来
                      }  
                  }) 
      
	batchAuditArray=array;
         refresh=0;
	//获取当前屏幕的绝对位置
	var topPos = window.pageYOffset;
    $('#batchAuditNew').window({left:"0px", top:topPos+"px"}); 
    $("#batchAuditNew").window('open');
    
 		$.ajax({
 		           url:"${pageContext.request.contextPath}/device/findSelectedReservation?array="+batchAuditArray,
 		           type:"POST",
 		          dataType:'json',
		          success:function(json){
		        	  $.each(json.list,function(i,array){
		        		 var trId='reservation'+i;
		        		str+="<tr id='"+trId+"'>"+
						"<td>"+array['deviceName']+"</td>"+
						"<td>"+array['reserveUserName']+"</td>"+
						"<td>"+array['teacherName']+"</td>"+
						"<td>"+array['deviceManagerName']+"</td>"+
						"<td><p>"+array['reservationContent']+"</p></td>"+
						"<td>从"+array['beginTime']+"<br>到"+array['endTime']+"</td>"+
						"<td><input type='button' value='修改时间' onclick='alterTime(this)'/></td>"+
						"<td><select id='result_id'  path='CTrainingResult.id'  >" +
						"<c:forEach items='${results}' var='r' varStatus='i'>"+
						"<option value='${r.id}' >"+"${r.CName}"+"</option>"+
						"</c:forEach>"+
						"</select>"+
						"</td>"+
						"<td><input type='text' id='remark' value='' placeholder='此处可不填'  />" +
						"<input type='hidden' id='resId' value='"+array['reservationId']+"'/>"+
						"<input type='hidden' id='resTag' value='"+array['reservationTag']+"'/>"+
						"<input type='hidden' id='reservation' value='"+i+"'/>"+
								"</td>"+
						"<td><input type='button' value='保存本条审核记录' onclick='saveAudit(this)'/>" +
						"</td>"+
						"</tr>";
		        	  })
		        	 //document.getElementById("res_body").innerHTML=str;
		        	  $(res_body).html(str);
		        	  
		          }  
 		});
  	
      
      } else {   
      	alert("请至少选择一条记录");  
      	} 
    
  	}

//修改时间弹窗
function alterTime(obj){
	var resId= $(obj).parent().parent().find("#resId").val();
	var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/device/alterTime?id='+resId+'" style="width:100%;height:100%;"></iframe>'
    $("#alterTime").html(con);  
    //获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#alterTime').window({left:"20px", top:topPos+"20px"});
    $("#alterTime").window('open');
}

//遍历复选框
function checkAll(){
	var checkBoxAll = document.getElementById("check-all"); 	
$("input[name='CK']:checkbox").each(function() { //遍历所有的name为CK的 checkbox  
      $(this).attr('checked', checkBoxAll.checked);
    })    
}  	


//保存审核结果
function saveAudit(obj){
	refresh++;
	if(refresh==batchAuditArray.length){//审核完成后刷新页面
		if(${isRest==1}){
			var url = "${pageContext.request.contextPath}/device/myReservationListRest/" + ${labRoomId} + "/"+ ${schoolTermId} + "/" + "${deviceName}" + "/"+${page};
			window.location.href=url;
		}
		else{
			location.href="${pageContext.request.contextPath}/device/myReservationList?page=1";
		}
	}
	var ii = $(obj).parent().parent().find("#reservation").val(); 
	var trIdDown="reservation"+ii;
	var resId = $(obj).parent().parent().find("#resId").val();
	var resTag = $(obj).parent().parent().find("#resTag").val();
	var resultId = $(obj).parent().parent().find("#result_id").val();
	if($(obj).parent().parent().find("#result_id").val()==""){
		resultId = 0;
	}
	
	var remark = $(obj).parent().parent().find("#remark").val(); 
	if($(obj).parent().parent().find("#remark").val()==""){
		remark="审核人（批量审核）未填写审核意见。";
	}
		document.getElementById(trIdDown).style.display="None";
		$.ajax({
			url:'${pageContext.request.contextPath}/device/saveAuditResultDiff?&resId='+resId+'&remark='+remark+'&resultId='+resultId+'&resTag='+resTag,
			type:'post',
			async:false,  // 设置同步方式
	        cache:false,
			success:function(data){
				$("#remark").html(remark);
			}
		}); 


	
} 



//获取审核结果的Id
function getResultId(){
	 var s=[];
     $($("#resultId option:selected")).each(function(){
	         s.push($(this).val());
     });
     if(rtag==1){
	 	window.location.href="${pageContext.request.contextPath}/device/saveTeacherAuditBatch?resultId="+s[0]+"&array="+batchAuditArray;
     }
     if(rtag==2){
 	 	window.location.href="${pageContext.request.contextPath}/device/saveManagerAuditBatch?resultId="+s[0]+"&array="+batchAuditArray;
      }
     if(rtag==3){
 	 	window.location.href="${pageContext.request.contextPath}/device/saveLabManagerAuditBatch?resultId="+s[0]+"&array="+batchAuditArray;
      }
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
	window.location.href="${pageContext.request.contextPath}/device/myReservationList?page=1";
}	
</script>
<script type="text/javascript">
//REST专用js
function myReservationListRest(){
    var labRoom_id = $("#labRoom_id").val();
    var schoolTerm_id = $("#schoolTerm_id").val();
    var schoolDevice_deviceName =$("#schoolDevice_deviceName").val();
    //alert(schoolDevice_deviceName);
	if($("#labRoom_id").val()==""){
	  labRoom_id ="-1";
	}
	if($("#schoolTerm_id").val()==""){
	  schoolTerm_id ="-1";
	}
	if($("#schoolDevice_deviceName").val()==""){
	  schoolDevice_deviceName ="-1";
	}
	
	var url = "${pageContext.request.contextPath}/device/myReservationListRest/" + labRoom_id + "/"+ schoolTerm_id + "/" + schoolDevice_deviceName + "/${page}";
	//alert(url);
	window.location.href=url;
}

</script>

</head>

<body>
<div class="navigation">
				<div id="navigation">
					<ul>
						<li><a href="javascript:void(0)">实验设备管理</a></li>
						<li class="end"><a href="javascript:void(0)">设备预约管理</a></li>
					</ul>
				</div>
			</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	<ul class="TabbedPanelsTabGroup" style="margin:0;">
		<sec:authorize ifNotGranted="ROLE_STUDENT,ROLE_GRADUATE">
		<li class="TabbedPanelsTab selected" tabindex="0">
		<a href="${pageContext.request.contextPath}/device/myReservationList?page=1">批量审批</a>
		</li>
		</sec:authorize>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="${pageContext.request.contextPath}/device/auditReservationList?page=1">审核中</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="${pageContext.request.contextPath}/device/passReservationList?page=1">审核通过</a>
		</li>
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="${pageContext.request.contextPath}/device/rejectedReservationList?page=1">审核拒绝</a>
		</li>
		<sec:authorize ifNotGranted="ROLE_STUDENT,ROLE_GRADUATE">
		<li class="TabbedPanelsTab" tabindex="0">
		<a href="${pageContext.request.contextPath}/device/reservationList?page=1">全部</a>
		</li>
		</sec:authorize>
	</ul>
	<div class="TabbedPanelsTabGroup-box">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		     <div class="tool-box">
		     <form:form name="queryForm" action="${pageContext.request.contextPath}/device/myReservationList?page=1" method="post" modelAttribute="reservation">
				<table class="list_form">
					<tr>
						<td>
							学期：
							<form:select path="schoolTerm.id" id="schoolTerm_id">
							<form:option value="">请选择</form:option>
							<form:options items="${terms}" itemLabel="termName" itemValue="id"/>
							</form:select>
							
						</td>
						<td>
						<span style="float: left;margin: 3px 0 0 10px;">设备名称：</span><form:input path="labRoomDevice.schoolDevice.deviceName" id="schoolDevice_deviceName"/>
						</td>
						<td>
						实验室：
						</td>
						<td>
							<form:select path="labRoomDevice.labRoom.id" class="chzn-select" id="labRoom_id">
							<form:option value="">请选择</form:option>
							<form:options items="${rooms}"/>
							</form:select>
						</td>
						<td>
							<input type="button" value="取消" onclick="cancelQuery();">
							<input type="button" value="查询" onclick="myReservationListRest();">
						</td>
					</tr >
			</table>
			</form:form>
		       
		    </div>
    	<div class="content-box">
    		<div class="title">
    			<div id="title">审核中</div>
    			<a  href="javascript:batchAudit();"   class="btn btn-new">批量审核</a>
    			<%--<a class="btn btn-new" href="javascript:void(0)">导出</a>--%>
    		</div>
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                    	<th><input type="checkbox"  onclick="checkAll();"  id="check-all"/></th>
                    	<th style="width:5%">序号</th>
                        <th style="width:15%">预约设备</th>
                        <th style="width:5%">申请人</th>
                        <th style="width:8%">申请人联系电话</th>
                        <%--<th style="width:5%">指导教师</th>--%>
                        <th style="width:10%">课题组名称</th>
                        <th style="width:20%">申请内容</th>
                        <th style="width:7%">日期</th>
                        <th style="width:8%">使用时间</th>
                        <th style="width:5%">实验室</th>
                        <th style="width:5%">审核人</th>
                        <th style="width:5%">状态</th>
                        <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EQUIPMENTADMIN,ROLE_TEACHER">
                        <th style="width:5%">操作</th>
                        </sec:authorize>
                    </tr>
                </thead>
                
                <tbody>
                		<c:forEach items="${reservationList}" var="reservation" varStatus="i">
                		<c:if test="${(reservation.tag==1||reservation.tag==2||reservation.tag==3)}">
                		<tr>
                		<td>
                			<input type="checkbox"  name="CK"  value="${reservation.id }"/>
                			<input type="hidden" id="rtag" value="${reservation.tag }"/>
                		</td>
                        <td>${i.count+(page-1)*pageSize}</td>
                        <td>
	                        <c:if test="${empty reservation.innerDeviceName }">
	                        	${reservation.labRoomDevice.schoolDevice.deviceName}[(${reservation.labRoomDevice.schoolDevice.deviceNumber})]
	                        </c:if>
	                        <c:if test="${not empty reservation.innerDeviceName }">
	                        	${reservation.innerDeviceName}<font color="red">关联设备</font>
	                        </c:if>
                        </td>
                        <td>${reservation.userByReserveUser.cname}</td>
                        <td>${reservation.phone}</td>
                        <%--<td>${reservation.userByTeacher.cname}</td>--%>
                        <td>${reservation.researchProject.name}</td>
                        <td><p>${reservation.content}</p></td>
                        <td><fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy-MM-dd"/> </td>
                        <td>
                        	<c:if test="${reservation.begintime.time.day eq reservation.endtime.time.day}">
		                        <fmt:formatDate value="${reservation.begintime.time}" pattern="HH:mm:ss"/>-<fmt:formatDate value="${reservation.endtime.time}" pattern="HH:mm:ss"/>
	                        </c:if>
	                        
	                        <c:if test="${reservation.begintime.time.day ne reservation.endtime.time.day}">
		                        	起<fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy/MM/dd HH:mm:ss"/><br>止<fmt:formatDate value="${reservation.endtime.time}" pattern="yyyy/MM/dd HH:mm:ss"/>
	                        </c:if>
                        </td>
                        <td>${reservation.labRoomDevice.labRoom.labRoomName}</td>
                        <td>
                         <c:if test="${reservation.CAuditResult.id ne 4 && reservation.CAuditResult.id ne 5 && reservation.CAuditResult.id ne 6}">  <!-- 非审核取消状态才显示 -->
                        <c:forEach items="${reservation.labRoomDeviceReservationResults}" var="s"> 
                              <c:if test="${s.CTrainingResult.CNumber==1}">${s.user.cname}(<font color="blue">通过</font>)<br></c:if>
                         </c:forEach>
                         <c:forEach items="${reservation.labRoomDeviceReservationResults}" var="s"> 
                              <c:if test="${s.CTrainingResult.CNumber==2}">${s.user.cname}(<font color="red">拒绝</font>)<br></c:if>
                         </c:forEach>
                         </c:if>
                         </td>
                        <%-- <td>${reservation.CAuditResult.name}</td> --%>
                        <td>${reservation.CAuditResult.CName}</td>
                        <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EQUIPMENTADMIN,ROLE_TEACHER">
                        <td>
                        <%-- <c:if test="${reservation.CAuditResult.id==1}"> --%>
                        <c:if test="${reservation.CAuditResult.CCategory=='c_audit_result' && reservation.CAuditResult.CNumber =='1'}">
	                        <c:if test="${reservation.tag==1 }">
	                        <a href="${pageContext.request.contextPath}/device/teacherReservationAudit?id=${reservation.id}">导师审核</a>
	                        </c:if>
	                        
	                        <c:if test="${reservation.tag==2 }">
	                        <a href="${pageContext.request.contextPath}/device/labManagerReservationAudit?id=${reservation.id}">实验室管理员审核</a>
	                        </c:if>
	                        
	                        <c:if test="${reservation.tag==3 }">
	                        <a href="${pageContext.request.contextPath}/device/managerReservationAudit?id=${reservation.id}">设备管理员审核</a>
	                        </c:if>
                        </c:if>
                        </td>      
                        </sec:authorize>                     
                        </tr>
                        </c:if>
                		</c:forEach>
                       
                </tbody>
            </table>
           
        
            
<!-- 分页模块 -->
<div class="page" >
        ${totalRecords}条记录,共${pageModel.totalPage}页
    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/device/myReservationList?page=1')" target="_self">首页</a>			    
	<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/device/myReservationList?page=')" target="_self">上一页</a>
	第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	<option value="${pageContext.request.contextPath}/device/myReservationList?page=${page}">${page}</option>
	<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}">
    <option value="${pageContext.request.contextPath}/device/myReservationList?page=${j.index}">${j.index}</option>
    </c:if>
    </c:forEach></select>页
	<a href="javascript:void(0)" onclick="next('${pageContext.request.contextPath}/device/myReservationList?page=')" target="_self">下一页</a>
 	<a href="javascript:void(0)" onclick="last('${pageContext.request.contextPath}/device/myReservationList?page=${pageModel.totalPage}')" target="_self">末页</a>
    </div>
<!-- 分页模块 -->
</div>


	 <div id="batchAudit" class="easyui-window " title="批量审核" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 500px; height: 300px;">
				<form action="" method="post">
					<table>
						<tr><td>批量审核</td></tr>
							
						<tr>
									<td>审核结果</td>
									<td>
							            <select id="resultId" name="resultId" >
										<option value="">请选择</option>
										<c:forEach items="${results}" var="r">
										<option value="${r.id}">${r.CName}</option>
										</c:forEach>
										</select>
									</td>
								</tr>

						<tr>
							<td><input type="button" value="提交" onclick="getResultId();"> </td>
							<td><input type="button" value="取消"> </td>
						</tr>
					</table>
				</form>
	</div>  
	
	
	<div id="batchAuditNew" class="easyui-window " title="批量审核" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 1100px; height: 400px;">
				<form action="" method="post">
				<div class="content-box">
					<table class="" border="0" align="center" cellpadding="0" cellspacing="0" style="">
					<thead>
						<tr>
							<th style="width:10%">预约设备</th>
                        	<th style="width:5%">申请人</th>
                        	<th style="width:5%">指导教师</th>
                        	<th style="width:8%">设备管理员</th>
                        	<th style="width:15%">申请内容</th>
                        	<th style="width:15%">使用时间</th>
                        	<th style="width:5%">修改时间</th>
                        	<th style="width:15%">审核结果</th>
                        	<th style="width:10%">审核意见</th>
                        	<th style="width:10%">操作</th>
						</tr>
					</thead>
					<tbody id="res_body">
						
					</tbody>
					</table>
					</div>
				</form>
	</div>

</div>
</div>
</div>
</div>
</div>

<!--修改时间iframe -->
<div id="alterTime" class="easyui-window" title="修改预约时间" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width: 800px; height:300px;">
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