<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.lab-resources" />
<head>
<meta name="decorator" content="iframe" />
<link href="${pageContext.request.contextPath}/css/iconFont.css"
	rel="stylesheet">
<!-- 下拉框的样式 -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<!-- 打印插件的引用 -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
  <script type="text/javascript">
  function cancel(){
	  window.location.href="${pageContext.request.contextPath}/lab/labAnnexList?currpage=1"
  }
//跳转
  function targetUrl(url)
  {
    document.form.action=url;
    document.form.submit();
  }
function ff(div) {
if(div==1){
 $("#f2").hide();
 $("#f1").show();
}
if(div==2){
  $("#f1").hide();
   $("#f2").show();
}
    
    }
  </script>
<script type="text/javascript">
$(document).ready(function() { 
  $("#f2").show();
   $("#f2").hide();
});
  $(function(){
     $("#print").click(function(){
	$("#my_show").jqprint();
	});
  });
     
function openwin(s){
		$.ajax({
	           url:"${pageContext.request.contextPath}/findLabRoomLimitTimesByLabId?labId="+s,
	           
	           type:"POST",
	           success:function(data){//AJAX查询成功
	                  document.getElementById("body").innerHTML=data;
	            
	           }
		});
         //打开弹出框
         $("#deviceType").attr("value",s);
         $("#deviceType1").attr("value",s);
         $("#openwindow").show();
         //获取当前屏幕的绝对位置
         var topPos = window.pageYOffset;
         //使得弹出框在屏幕顶端可见
         $('#openwindow').window({left:"100px", top:topPos+"px"});
         $("#openwindow").window('open');   
     }

    //根据课程编号 获取电话
function classesdd(s){
$("#classno").val(s);
 
}   

function exportAll(s){
	document.form.action=s;
	document.form.submit();
}

//弹出选择学生窗口
function newStudents() {
    var sessionId=$("#sessionId").val();
    //获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#newStudents').window({left:"0px", top:topPos+"px"}); 
    $("#newStudents").window('open');
}
 </script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/labreservation/labreservation.js"></script>
<script type="text/javascript">
$(document).ready(function(){
$("#labRooms").chosen().change(function(){
$("#weeks").html("");
var values=[];
$($("#classes option:selected")).each(function(){
	values.push(this.value);
});
 var roomId=  $("#deviceType1").val();
     if(roomId==null){
       roomId =  $("#deviceType").val();
     }
 var termId= ${currTerm.id };
 if(termId==null){
       termId =  8;
     }
$.ajax({
         url:"${pageContext.request.contextPath}/lab/getValidWeeksMap",
         dataType:"json",
         type:'GET',
         data:{term:termId,weekday:$("#weekday").val(),labrooms:labRooms.join(","),classes:values.join(",")},
         complete:function(result)
         {
            var obj = eval(result.responseText); 
            var result2;
            
            for (var i = 0; i < obj.length; i++) { 
                 //var val = obj.responseText[i]; 
                 //result2 = result2 + "<option value='" +obj[i].id + "' selected>" + obj[i].value + "</option>";
                 result2 = result2 + "<option value='" +obj[i].id + "'>" + obj[i].value + "</option>";
        
            }; 
         
            $("#weeks").append(result2);
            $("#weeks").trigger("liszt:updated");
            result2="";
          }
});
$("#weeks").trigger("liszt:updated");
});

$("#classes").chosen().change(function(){
var roomId=  $("#deviceType1").val();
     if(roomId==null){
       roomId =  $("#deviceType").val();
     }
 var termId=  $("#schoolTermid").val()
 if(termId==null){
       termId =  8;
     }
$("#classes").trigger("liszt:updated");
$("#weeks").html("");
var values=[];
$($("#classes option:selected")).each(function(){
	values.push(this.value);
});


$.ajax({
         url:"${pageContext.request.contextPath}/lab/getValidWeeksMap",
         dataType:"json",
         type:'GET',
         data:{term:termId,weekday:$("#weekday").val(),labrooms:roomId,classes:values.join(",")},
         complete:function(result)
         {
            var obj = eval(result.responseText); 
            var result2;
            
            for (var i = 0; i < obj.length; i++) { 
                 //var val = obj.responseText[i]; 
                 //result2 = result2 + "<option value='" +obj[i].id + "' selected>" + obj[i].value + "</option>";
                 result2 = result2 + "<option value='" +obj[i].id + "'>" + obj[i].value + "</option>";
            }; 
         
            $("#weeks").append(result2);
            $("#weeks").trigger("liszt:updated");
            result2="";
          }
});
$("#weeks").trigger("liszt:updated");
});
}); 

$(document).ready(function(){
$("#weekday").chosen().change(function(){
$("#weeks").html("");
var roomId=  $("#deviceType1").val();
     if(roomId==null){
       roomId =  $("#deviceType").val();
     }
 var termId=  $("#schoolTermid").val()
 if(termId==null){
       termId =  8;
 }
var values=[];
$($("#classes option:selected")).each(function(){
	values.push(this.value);
});

$.ajax({
    
    url:"${pageContext.request.contextPath}/lab/getValidWeeksMap",
    dataType:"json",
    type:'GET',
    data:{term:termId,weekday:$("#weekday").val(),labrooms:roomId,classes:values.join(",")},
    complete:function(result)
    {
       var obj = eval(result.responseText); 
       var result2;
       for (var i = 0; i < obj.length; i++) { 
                  result2 = result2 + "<option value='" +obj[i].id + "'>" + obj[i].value + "</option>";
       }; 
       $("#weeks").append(result2);
       $("#weeks").trigger("liszt:updated");
       result2="";
    }
});

$("#weeks").trigger("liszt:updated");

});
}); 

//ajax查询用户的班级表  
function getSchoolClasses(grade){ 		
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/timetable/selfTimetable/getSchoolClasses",
		data: {'grade':grade},
		dataType:'json',
		success:function(data){
			var jslength=1;
			var currLine=1;
			for(var js2 in data){jslength++;}
			if(jslength==0){alert("本周无课程数据");}else{}
			var tableStr="<table id='listTable' width='80%' cellpadding='0'><tr><td colspan=3><b>选择班级</b></td></tr>";//新建html字符
			$.each(data,function(key,values){  
			   if(currLine%3==0){
		           tableStr = tableStr + "<td><a class='btn btn-common' href='javascript:void(0)' onclick='getSchoolClassesUser("+ key +")'>" + values + "</a></td><tr>";
			   }else  if(currLine%3==1){
			       tableStr = tableStr + "<tr><td><a class='btn btn-common' href='javascript:void(0)' onclick='getSchoolClassesUser("+ key +")'>" + values + "</a></td>";
			   }
			   else  if(currLine%3==2){
			       tableStr = tableStr + "<td><a class='btn btn-common' href='javascript:void(0)' onclick='getSchoolClassesUser("+ key +")'>" + values + "</a></td>";
			   }
			   currLine=currLine+1
			   jslength=jslength+1;
			 }); 
			 tableStr = tableStr + "</tr></table>";
			 document.getElementById('schoolClasses').innerHTML=tableStr; 	
		},
		error:function(){
			//alert("加载课表失败!");
			}
	});
}

//ajax查询班级用户列表  
function putSchoolClassesUser(){ 		
	 var obj = document.getElementsByName("username");
	 var s='';//如果这样定义var s;变量s中会默认被赋个null值
     for(var i=0;i<obj.length;i++){
         if(obj[i].checked) //取到对象数组后，我们来循环检测它是不是被选中
         s+=obj[i].value+'\n';   //如果选中，将value添加到变量s中    
     }
     var str = $('#student1').val() +'\n' +s;
     $('#student1').val(str);
     $("#newStudents").window('close');
}

//ajax查询班级用户列表  
function getSchoolClassesUser(classNumber){ 		
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/timetable/selfTimetable/getSchoolClassesUser",
		data: {'classNumber':classNumber},
		dataType:'json',
		success:function(data){
			var jslength=1;
			var currLine=1;
			var allLine=1;
			for(var js2 in data){jslength++;}
			if(jslength==0){alert("本周无课程数据");}else{}

			var tableStr="<table id='listTable' width='80%' cellpadding='0'><tr><td><b>选择学生</b></td><td colspan=3><a class='btn btn-common' href='javascript:void(0)' onclick='putSchoolClassesUser()'>提交</a></td></tr>";//新建html字符
			$.each(data,function(key,values){  
			   if(currLine%4==0){
		           tableStr = tableStr + "<td><input name='username' id='username" + allLine + "' type='checkbox' value='" + key + "' checked='checked' />" + key + "：" + values + "</a></td><tr>";
			   }else  if(currLine%4==1){
			       tableStr = tableStr + "<tr><td><input name='username' id='username" + allLine + "' type='checkbox' value='" + key + "' checked='checked' />" + key + "：" + values + "</a></td>";
			   }else  if(currLine%4==2){
			       tableStr = tableStr + "<td><input name='username' id='username" + allLine + "' type='checkbox' value='" + key + "' checked='checked' />" + key + "：" + values + "</a></td>";
			   }else if(currLine%4==3){
			       tableStr = tableStr + "<td><input name='username' id='username" + allLine + "' type='checkbox' value='" + key + "' checked='checked' />" + key + "：" + values + "</a></td>";
			   }
			   //currLine=currLine%4;
			   jslength=jslength+1;
			   currLine = currLine +1;
			   allLine =allLine+1;
			 }); 
			 if(currLine%4==0){
			   tableStr = tableStr + "</table>";
			 }else if(currLine%4==1){
			   tableStr = tableStr + "<td>&nbsp;</td><td>&nbsp;</td><td&nbsp;></td></tr></table>";
			 }else if(currLine%4==2){
			   tableStr = tableStr + "<td>&nbsp;</td><td>&nbsp;</td></tr></table>";
			 }else if(currLine%4==3){
			   tableStr = tableStr + "<td>&nbsp;</td></tr></table>";
			 }
			
			 document.getElementById('schoolClassesUser').innerHTML=tableStr; 	
		},
		error:function(){
			//alert("加载课表失败!");
			}
	});
}
function judgeAndSave(){
	var roomId=  $("#deviceType1").val();
	if(roomId==null){
	    roomId =  $("#deviceType").val();
	  }
	 var termId=  $("#schoolTermid").val()
	 if(termId==null){
	       termId =  8;
	 }
	var values=[];
	$($("#classes option:selected")).each(function(){
		values.push(this.value);
	});
	
	var weeks=[];
	$($("#weeks option:selected")).each(function(){
		weeks.push(this.value);
	});
	$.ajax({
	    url:"${pageContext.request.contextPath}/lab/judgeTimeIsInLimitTime",
	    dataType:"json",
	    type:'GET',
	    data:{term:termId,weekday:$("#weekday").val(),labroom:roomId,classes:values.join(","), weeks:weeks.join(",")},
	    success:function(result)
	    {
	       if(result.isIn == 0){
	       	document.reservationForm.submit();
	       }
	       else{
	       	alert("无法预约,"+result.hint+"是禁用时间！");
	       }
    	}
	});
}

function openCalendar(roomId){
 window.location.href="${pageContext.request.contextPath}/lab/labReservationCalendar?roomId="+roomId;
}
</script>
</head>
<body> 
<!--导航  -->
<div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0)">实验室预约管理 </a></li>
			<li class="end"><a href="javascript:void(0)">实验室预约列表</a></li>
		</ul>
	</div>
</div>
<!--导航结束  -->

<div class="right-content">
	<div id="TabbedPanels1" class="TabbedPanels">
		<div class="TabbedPanelsContentGroup1">
			<div class="TabbedPanelsContent">
				<div class="tool-box">
					<ul>
						<form:form name="form" action="${pageContext.request.contextPath}/lab/labAnnexList?currpage=1" method="post" modelAttribute="labRoom">
							<li>实验室名称： </li>
							<li><form:input path="labRoomName" /></li>
							<li><input type="button" onclick="exportAll('${pageContext.request.contextPath}/lab/labAnnexList?currpage=1');" value="搜索"></li>
							<%--<li><input type="button" value="打印" id="print"></li>--%>
							<%--<li>
							  <input type="button" value="导出" onclick="exportAll('${pageContext.request.contextPath}/lab/labAnnexListexportall?page=${currpage}');">
							</li>--%>
							<li><input type="button" onclick="cancel()" value="取消"></li>
						</form:form>
					</ul>
				</div>
			</div>
			<div class="content-box">
				<table>
					<thead>
						<tr>
							<th>序号</th>
							<th>实验室名称
							</th>
							<th>实验室编号
							</th>
							<th>实验室地址</th>
							<th>最大预约并发</th>
							<th><%-- <fmt:message key="navigation.operate" /> --%>操作
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listLabRoom}" var="s" varStatus="i">
							<tr >
								<td>${i.count+(currpage-1)*pageSize }</td>
								<td>${s.labRoomName}</td>
								<td>${s.labRoomNumber}</td>
								<td>${s.labRoomAddress}</td>
								<td>${s.reservationNumber}</td>
								<td><sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR,ROLE_TEACHER,ROLE_LABMANAGER,ROLE_EQUIPMENTADMIN,ROLE_ASSISTANT,ROLE_STUDENT">
								<a onclick="openwin(${s.id})" href="javascript:void(0)"  > 预约</a>
								<a onclick="openCalendar(${s.id})" href="javascript:void(0)"  > 总课表</a>
								</sec:authorize>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="page">
					${totalRecords}条记录 &nbsp;   共${pageModel.totalPage}页 &nbsp; 
					<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/lab/labAnnexList?currpage=1')" target="_self">首页</a> 
					<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/lab/labAnnexList?currpage=${pageModel.previousPage}')" target="_self">上一页</a>
					<input type="hidden" value="${pageModel.lastPage}" id="totalpage" />&nbsp; <input type="hidden" value="${currpage}" id="currpage" /> 
					&nbsp;第 
					<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
					   <option value="${pageContext.request.contextPath}/system/listUser?currpage=${currpage}">${currpage}</option>
					   <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
			           <c:if test="${j.index!=currpage}">
			           <option value="${pageContext.request.contextPath}/lab/labAnnexList?currpage=${j.index}">${j.index}</option>
			           </c:if>
			           </c:forEach>
           			</select>页&nbsp;
					 
					<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/lab/labAnnexList?currpage=${pageModel.nextPage}')" target="_self">下一页</a>
					<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/lab/labAnnexList?currpage=${pageModel.lastPage}')" target="_self">末页</a>
				</div>

<div id="openwindow" class="easyui-window" closed="true"	modal="true" minimizable="true" title="开始实验室预约"	style="width: 600px;height: 520px;padding: 20px">
<form:form	name="reservationForm" action="${pageContext.request.contextPath}/labreservation/savelabreservation"
						method="post" modelAttribute="labReservation">

						<div class="content-box">
						实验室禁用时间
							<table class="eq" id="my_show">
								<thead>
									<tr>
										<th width="35%">学期</th>
										<th width="15%">周次</th>
										<th width="15%">节次</th>
										<th width="15%">星期</th>
										<th width="10%">类型</th>
									</tr>
								</thead>
								<tbody id="body">
								</tbody>
						</table>
					</div>
					<div class="content-box">
							<table>
							    <tr>
									<input type="hidden" id="deviceType" name="deviceType">
									<form:input type="hidden" path="CDictionaryByLabReservetYpe.id" value="553" />
									<input type="hidden" value="${currTerm.id }" id="schoolTermid" name="schoolTermid">
									<input type="hidden" value="365" name="courseCode"> <!-- 给实验室预约特定建立的选课组，东华项目中id为365 -->
									<th>当前学期</th>
									<td>
										${currTerm.termName }
	                            	</td>
									<th>选择星期</th>
									<td> 
									<select class="chzn-select"   name="weekday" id="weekday" style="width:80px">
									   <c:forEach begin="1" end="7"  varStatus="i">
									       <c:if test="${i.count == appointment.weekday }">
								               <option value ="${i.count}" selected> 星期${i.count}</option>
								           </c:if>
								           <c:if test="${i.count != appointment.weekday  }">
								               <option value ="${i.count}"> 星期${i.count}</option>
								           </c:if>
								       </c:forEach>
								     </select>
									</td>
                                </tr>
								
								<tr>
									<th>选择节次</th>
									<td>
									      <select class="chzn-select" multiple="multiple" data-placeholder='请选择节次：' name="classes" id="classes" style="width:165px" required="true">
									        <c:forEach begin="1" end="13"  varStatus="i">
									               <option value ="${i.count}"> ${i.count}</option>
									           </c:forEach>
									       </select>
									</td>
									
									<th>周次</th>
									<td> 
										<select class="chzn-select"  multiple="multiple"  name="weeks" data-placeholder='请选择周次：' id="weeks" style="width:165px" required="true">
								       <c:forEach items="${week}" var="current"  varStatus="i">
									   <c:if test="${current.key<=16}">
								       <option value ="${current.key}"> ${current.value}</option>
								       </c:if>
								        <c:if test="${current.key>16}">
								       <option value ="${current.key}"> ${current.value}</option>
								       </c:if>
								       </c:forEach>
								     </select>
							     	</td>
								</tr>
								
								<tr>
									<th>活动类别</th>
									<td><form:select
										path="CDictionaryByActivityCategory.id"
										items="${activitycategory }" style="width:165px"
										required="true"></form:select>
									</td>
									<th>活动名称 </th>
									<td> <form:input
										path="eventName" name="eventName" style="width:145px"
										required="true" />
									</td>
								</tr>
								
								<tr>
									<th>预约内容</th>
									<td colspan="3"><form:input path="reservations"
									name="reservations"
									style="width: 450px;height: 50px;padding: 5px" /></td>
								</tr>
								
								<tr>
									<th>备注</th>
									<td colspan="3"><form:input
										path="remarks" name="remarks"
										style="width: 450px;height: 50px;padding: 5px" /></td>
								</tr>
								
								<tr>
									<th>添加学生(输入学生学号，以逗号分开)
									<td colspan="3">
									<textarea rows="" cols=""
										name="student" id="student"
										style="width: 450px;height: 50px;padding: 5px"></textarea></td>
								</tr>
							</table>
							<div>
									<input type="button" value="提交" onclick="judgeAndSave()">
									<input type="button" value="取消" onclick="window.history.go(0)"><!-- 改正取消的跳转页面问题  贺子龙  2015-09-22 09:28:37 -->
							</div>
						</div>
					</form:form>
				</div>
			</div>
</div>

<!-- 弹出选择学生窗口 -->
<div id="newStudents" class="easyui-window" title="选择添加学生" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width:950px; height:800px;">
	<div class="TabbedPanelsContentGroup">
	<div class="TabbedPanelsContent">
	
	<div class="content-box">
	<form:form action="" method="post">
	<fieldset class="introduce-box">
         <legend>年级信息</legend>
         <div>
         <table id="listTable" width="85%" cellpadding="0">
          <tr><td><b>选择年级</b></td><tr>
          <tr>
         	<td>
         	<c:forEach items="${grade }" var="s" varStatus="i">
         	<c:if test="${s.grade>'2010' }">
			 <a class='btn btn-common' href='javascript:void(0)' onclick='getSchoolClasses(${s.grade})' >${s.grade}</a>	
			</c:if>
			</c:forEach></td>
         </tr>
         </table>
         </div>
         <div id="schoolClasses"></div>
         <div id="schoolClassesUser"></div>
	</fieldset>
	</form:form>
	</div>
	</div>
	</div>
	
</div>
		<!-- 下拉框的js -->

		<script
			src="${pageContext.request.contextPath}/chosen/chosen.jquery.js"  type="text/javascript"></script>

		<script
			src="${pageContext.request.contextPath}/chosen/docsupport/prism.js"
			type="text/javascript" charset="utf-8"></script>

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
	</div></div>