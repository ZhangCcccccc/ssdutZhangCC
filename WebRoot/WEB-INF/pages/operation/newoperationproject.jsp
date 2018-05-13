<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="bundles.projecttermination-resources"/>

<html >  
<head>
<meta name="decorator" content="iframe"/>  
<title><fmt:message key="html.title"/></title>
<!-- <meta name="decorator" content="iframe"/> -->

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
  
  <!-- 下拉框的样式 -->
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 --> 
   
  <!-- 文件上传的样式和js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>  


<script type="text/javascript">

$(function(){
    $("#Pop_content").window({
        top: ($(window).width() - 800) * 0.5 ,
        left: ($(window).width() - 1000) * 0.5   
    })
     $(".listTable").css('height',600); 
})
function newwindor(){
  
     $("#Pop_content").show();
     //获取当前屏幕的绝对位置
     var topPos = window.pageYOffset;
     //使得弹出框在屏幕顶端可见
     $('#Pop_content').window({left:"0px", top:topPos+"px"});
     $("#Pop_content").window('open');
     
       var nameop ="";
	         $.ajax({
	           url:"${pageContext.request.contextPath}/operation/getitem",
	           data:{nameop:nameop},
	           type:"POST",
	           success:function(data){
	                  $("#npo").html("");
					  $("#npo").append(data);
										//  $("#npo").replace(data);
	            
	           }
	         });
	         
     
    
  }
  
  function addproject(){
  $("#projectitrms").attr("value","");
  
   var  projectitems="";
   var c=document.getElementById("Pop_content").getElementsByTagName("input");
       	    for(var i=0;i<c.length;i++){   
                if(c[i].type=="checkbox" && c[i].checked){
                	projectitems+=c[i].value+",";
           		}
          	}
         
     $.post('${pageContext.request.contextPath}/operation/getuserprojectitems',{projectitems:projectitems},function(data){  //serialize()序列化
				$("#ds").after(data);
			 });
          	
    $("#projectitrms").attr("value",projectitems);
     alert("添加成功！");     
    $('#Pop_content').window('close');	  	
  }
  
  
    $(function(){
	         $("#userSubmit").click(function(){
	         var nameop = $("#nameop").val();
	         $.ajax({
	           url:"${pageContext.request.contextPath}/operation/getitem",
	           data:{nameop:nameop},
	           type:"POST",
	           success:function(data){
	                  /*  $("#npo").html("");
						document.getElementById("npo").style.display="none";
	                       $("#s").html("");
											  $("#s").append(data); */
											    $("#npo").html("");
										// document.getElementById("npo").style.display="none";
                                         
											  $("#npo").append(data);
	            
	           }
	         });
	         
	      }); 
  });
  function sunb(){
  
   var jie=[];
    $("#commencementnaturemap option:selected").each(function(){
	         jie.push(this.value);
     }); 
      var sss=[];
    $("#schoolMajorsa option:selected").each(function(){
	         sss.push(this.value);
     }); 
   if(jie.length ==0){
	   alert("请选择面向专业！");
	   return false;
   };
   if(sss.length == 0){
	   alert("请选择课程性质！");
	   return false;  
   }
  
  }
  
  function del(s){
  $("#"+s+"").remove();
   var d=   $("#projectitrms").val();
     var a= d.replace(s+",","");
       $("#projectitrms").attr("value","");
        $("#projectitrms").attr("value",a);
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
	            'uploader':'${pageContext.request.contextPath}/operation/uploaddnolinedocment;jsessionid=<%=session.getId()%>',		//提交的controller和要在火狐下使用必须要加的id
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
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.gif" />

<style type=text/css>
.chzn-container,.chzn-container-single {
	width:200px !important;
}
</style>

</head>
<body>
<div class="navigation">
				<div id="navigation">
					<ul>
						<li><a href="javascript:void(0)">实验室运行管理</a></li>
						<c:if test="${isNew eq 1 }">
						<li class="end"><a href="javascript:void(0)">新建大纲</a></li>
						</c:if>
						<c:if test="${isNew eq 0 }">
						<li class="end"><a href="javascript:void(0)">编辑大纲</a></li>
						</c:if>
			            
						
					</ul>
				</div>
			</div>
	<div class="right-content">
		<div id="TabbedPanels1" class="TabbedPanels">
			<div class="TabbedPanelsContentGroup">
				<div class="TabbedPanelsContent">
					<div class="title">
						<div id="title">实验大纲</div>
						<a class="btn btn-return" onclick="window.history.go(-1)">返回</a>
					</div>
					 
			 <div class="content-box">
					<form:form action="${pageContext.request.contextPath}/operation/saveoperationoutline"
							 method="post" modelAttribute="operationOutline"  onsubmit="return sunb();">
					<fieldset class="introduce-box">
					 <form:hidden path="id"/>
						<legend>实验大纲基本信息<input type="hidden" value="" id="xsd"></legend>
						<table id="listTable" width="50%" cellpadding="0" cellspacing="0" class="tablesorter">
						   <tr align="center">
						   
						   
						  <td class="label" valign="top" >课程代码</td>
								<td class="label" valign="top"  align="left">
								<select
										path="schoolCourseInfoByClassId.courseNumber" name="coursenumber" class="chzn-select">
										<c:forEach items="${schoolCourseInfoMap}" var="map">
											<option value="${map.key }">${map.value }</option>
										</c:forEach>
									</select>
								</td>
						   
								 
								 <td class="label" valign="top">英文名称</td>
								 <td class="label" valign="top" ><form:input path="enName" /> </td>
							 </tr>
                             <tr align="center">
								 <td class="label" valign="top" width="40px">学时<font
										color=red>*</font>
									</td>
									<td class="label" valign="top" width="40px"><form:input
											path="period" required="true"/>
									</td>
								<td class="label" valign="top" width="40px">学分</td>
								<td class="label" valign="top" width="40px" align="left"><select
										path="cOperationOutlineCredit.id" name="credit">
										<c:forEach items="${operationscareMap}" var="sca">
											<c:if test="${sca.key==outlineCredit.id }">
												<option value="${sca.key }" selected="selected">${sca.value }</option>
											</c:if>
												<option value="${sca.key }">${sca.value }</option>
										</c:forEach>
										</select>
								</td>
							 </tr>
							 
							 <tr align="center">
								<%--<td class="label" valign="top" width="40px">后续课程</td>
								<td class="label" valign="top" width="40px" align="left"><form:select
										path="schoolCourseInfoByFollowUpCourses.courseNumber"
										class="chzn-select">
										<option value="" selected="selected">请选择</option>
										<form:options items="${schoolCourseInfoMap}" />
									</form:select>
								</td>
								--%>
								<td class="label" valign="top" width="40px">课程类型</td>
								<td class="label" valign="top" width="40px" align="left"><select
										path="courseDescription" id="coursetype" name="coursetype" 
										class="chzn-select">
										<%--<option value="" selected="selected">请选择</option>
										--%>
										<c:forEach items="${coursetypemap }" var="curr">
											<c:if test="${courseType==curr.CName }">
												<option value="${curr.id }" selected="selected">${curr.CName }</option>
											</c:if>
												<option value="${curr.id }">${curr.CName }</option>
										</c:forEach>
									</select>
								</td>
								
								<td class="label" valign="top" width="40px">课程性质<font color=red>*</font></td>
								<td class="label" valign="top" width="40px" align="left"><select
									id="commencementnaturemap" name="commencementnaturemap"
									class="chzn-select"  multiple="multiple">
										<c:forEach items="${commencementnaturemap}" var="s">
										<c:forEach items="${property}" var="curr">
										<c:if test="${curr.id eq s.id }">
										<option value="${s.id}" selected="selected">${s.CName}</option>
										</c:if> 
										</c:forEach>
										<option value="${s.id}">${s.CName}</option>
										</c:forEach>
								</select></td>
							</tr>
							 
							 <tr align="center">
								<td class="label" valign="top" width="40px">开课学院</td>
								<td class="label" valign="top" width="40px" align="left"><select
										name="academynumbers" id="academynumbers" 
										class="chzn-select" multiple="multiple">
										<c:forEach items="${operationstartschooleMap}" var="s">
										<c:forEach items="${academies}" var="curr">
										<c:if test="${curr.academyNumber eq s.key }">
										<option value="${s.key}" selected="selected">${s.value}</option>
										</c:if> 
										</c:forEach>
										<option value="${s.key}">${s.value}</option>
										</c:forEach>
									</select>
								</td>
								
								<td class="label" valign="top" width="40px">面向专业<font color=red>*</font></td>
								<td class="label" valign="top" width="40px" align="left">
								   <select id="schoolMajorsa" name="schoolMajorsa" class="chzn-select" multiple="multiple" >
										<c:forEach items="${schoolmajer}" var="d">
											<c:forEach items="${majorsEdit}" var="curr">
												<c:if test="${d.MNumber eq curr.MNumber}">
													<option value="${d.MNumber}" selected="selected">${d.MName}</option>
												</c:if>
											</c:forEach>
											<option value="${d.MNumber}">${d.MName}</option>
										</c:forEach>
								   </select>
								   </td>
							 </tr>
                             
							<tr align="center">
								<td class="label" valign="top" width="40px">先修课程</td>
								<td class="label" valign="top" width="40px" align="left"><select
										name="firstcourses" id="firstcourses" 
										class="chzn-select" multiple="multiple" >
										<c:forEach items="${schoolCourseInfoMap }" var="s">
										<c:forEach items="${firstCourses }" var="curr">
											<c:if test="${s.key eq curr.courseNumber }">
												<option value="${s.key }" selected="selected">${s.value }</option>
											</c:if>
											</c:forEach>
												<option value="${s.key }">${s.value }</option>
										</c:forEach>
									</select>
								</td>
								<td class="label" valign="top" width="40px">所属学期</td>
								<td class="label" valign="top" width="40px" align="left"><select
										name="termIdes" id="termIdes" class="chzn-select" 
										 multiple="multiple" required="true">
										<c:forEach items="${cDictionaryTerms }" var="s">
										<c:forEach items="${studyStages }" var="curr">
											<c:if test="${s.id eq curr.id }">
												<option value="${s.id }" selected="selected">${s.CName }</option>
											</c:if>
										</c:forEach>
												<option value="${s.id}">${s.CName }</option>
										
										</c:forEach>
									</select>
								</td>
							 </tr>
							 
							<tr>
									<td class="label" valign="top" width="40px">使用教材</td>
									<td colspan="3"><form:textarea path="useMaterials" /></td>
							 </tr>
                         </table>
					</fieldset>
					
					<fieldset class="introduce-box">
						<legend>课程任务和教学目标<input type="hidden" value="" id="xsd"></legend>
						<table id="listTable" width="50%" cellpadding="0">
								<tbody>
								<tr>
									<td colspan="3">任务和教学目标<font color="red">*</font></td></tr>
									<tr><td><form:textarea
												path="outlineCourseTeachingTarget" required="true" cols="104" />
									</td>
								</tr>
								</tbody>
							</table>
					</fieldset>
					<%--<fieldset class="introduce-box">
						<legend>课程简介及建议<input type="hidden" value="" id="xsd"></legend>
						<table id="listTable" width="50%" cellpadding="0">
								<tbody>
								<tr> <td colspan="3">课程简介<font color="red">*</font></td></tr>
								<tr><td><form:textarea path="courseDescription"
											required="true"	cols="104" /></textarea>
									</td>
								</tr>
								<tr>
									<td colspan="3">选课建议<font color="red">*</font></td></tr>
									<tr><td><form:textarea path="coursesAdvice"
												required="true" cols="104" /></textarea>
									</td>
								</tr>
								</tbody>
						</table>
					</fieldset>
						<fieldset class="introduce-box">
						<legend>课程基本内容及要求<input type="hidden" value="" id="xsd"></legend>
						<table id="listTable" width="100%" cellpadding="0"
								cellspacing="0" class="tablesorter">
								<tr>
									<td colspan="3">课程基本内容及要求</td>
								</tr>
								<td colspan="3">（一）课程基本内容</td>
								</tr>
								<td colspan="10"><form:textarea path="basicContentCourse"
										cols="104" /></td>
								</tr>
								<td colspan="3">（二）课程基本要求</td>
								</tr>
								<td colspan="10"><form:textarea
										path="basicRequirementsCourse" cols="104" />
								</td>
								</tr>
							</table>
					</fieldset>--%>
					
					<fieldset class="introduce-box">
						<legend>本课程与其它课程的联系和分工<input type="hidden" value="" id="xsd"></legend>
						<table id="listTable" width="100%" cellpadding="0"
								cellspacing="0" class="tablesorter">
								<tr>
									<td colspan="3">联系和分工</td>
								</tr>
								<tr>
								<td colspan="10"><form:textarea path="basicContentCourse"
										cols="104" /></td>
								</tr>
							</table>
					</fieldset>
					
					<fieldset class="introduce-box">
						<legend>作业及成绩评定<input type="hidden" value="" id="xsd"></legend>
					<table>
						<tr>
									<td colspan="3">作业、考核成绩及成绩评定</td>
								</tr>
								<td colspan="10"><form:textarea
										path="assResultsPerEvaluation" cols="104" /></td>
								</tr>
								<%--<tr>
											<td >课程任务和教学目标<font color="red">*</font></td>
										</tr>
										<td ><form:textarea
												path="outlineCourseTeachingTargetOver" cols="104" required="true" /></td>
										</tr>
					--%></table>
					</fieldset>
					<fieldset class="introduce-box">
						<legend>文档名称<input type="hidden" value="" id="xsd"></legend>
					<table>
						<tr >
							 <td>文档名称</td>
							 <td>操作&nbsp;<input type="button" onclick="uploaddocment()" value="上传附件"/>
							 	<input type="hidden" name="docment" id="docment" />
							 </td>
						 </tr>
					</table>
					 <table>
						  <tbody id="doc"></tbody>
					 </table>	
					</fieldset>
					
					
					<fieldset class="introduce-box">
						<legend>项目名称<input type="hidden" value="" id="xsd"></legend>
						    <table>
								 <tr>
									 <td>实验项目添加</td>
									 <td><input id="u1436_input" type="button"
											onclick="newwindor();" value="添加">
								 </tr>
							 <table>
										<tr id="ds">
											<td>实验项目编号</td>
											<td>实验项目名称</td>
											<td>实验类型</td>
											<td>实验时数</td>
											<td>每组人数</td>
											<td>实验室</td>
											<td>操作</td>
										</tr>
										<c:forEach items="${operationOutline.operationItems}" var="s">
											<tr>
												<td>${s.lpCodeCustom}</td>
												<td>${s.lpName}</td>
												<td>${s.CDictionaryByLpCategoryMain.CName}</td>
												<td>${s.lpDepartmentHours}</td>
												<td>${s.lpStudentNumberGroup}</td>
												<td><c:forEach items="${s.labRooms}" var="a">${a.labRoomName}/</c:forEach></td>
												<td></td>
											</tr>
	
										</c:forEach>
									</table>
								</table>
					</fieldset>
					<%-- </form:form>
					 --%>
					
					
					
					
						<%-- <form:form
							action="${pageContext.request.contextPath}/operation/"
							method="post" modelAttribute="operationOutline"  onsubmit="return sunb();">
						 --%>	


							
	                               
<tr><td colspan="5"></td></tr>
							<tr>
								<input type="hidden" id="projectitrms" name="projectitrms" />
								<td></td>
								<td><input type="submit" value="提交">
								</td>
								<td></td>
								<td colspan="3"></td>
							</tr>

						</form:form>
					</div>
					<!-- </div> -->
					<!-- 下拉框的js -->

					<script
						src="${pageContext.request.contextPath}/chosen/chosen.jquery.js"
						type="text/javascript"></script>

					<script
						src="${pageContext.request.contextPath}/chosen/docsupport/prism.js"
						type="text/javascript" charset="utf-8"></script>
							
					<script type="text/javascript">

    var config = {

      '.chzn-select'           : {},

      '.chzn-select-deselect'  : {allow_single_deselect:true},

      '.chzn-select-no-single' : {disable_search_threshold:10},

      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},

      '.chzn-select-width'     : {width:"95%"}

    }

    for (var selector in config) {

      $(selector).chosen(config[selector]);

    }

</script>
		
					<div id="Pop_content" class="easyui-window" closed="true"
						modal="true" minimizable="true" title="添加实验项目"
						style="width:800px;padding:10px;top: 50px">
						
							<div class="tool-box">
								
								<table >
									<tr>
										<td>实验项目名称:<input type="text" id="nameop" /><input type="button" id="userSubmit" name="userSubmit" value="查找" /></td>
										<td></td>
										<td><input class="savebutton" id="saveContent"
											type="button" onclick="addproject();" value="添加" /></td>
									</tr>
									
								</table>
                              </div>
                              
                              <div class="content-box">
								<form:form id="clauseForm">
									<table >
										<tr>
										<thead>
											<tr id="s">
												<th width="10%">操作</th>
												<th width="15%">实验项目编号</th>
												<th width="65%">实验项目名称</th>
												
											</tr>
											<!-- <tr id="s">
												<td></td>
												<td align="center"></td>
												<td align="center"></td>
												<td align="center"></td>
											</tr> -->
										</thead>
										<div>
											<tbody id="npo">
												<c:forEach items="${operationItem }" var="s">
													<tr>
														<td><input type="checkbox" value="${s.id }">
														</td>
														<td align="left">${s.lpCodeCustom}</td>
														<td align="left">${s.lpName}</td>
														
													</tr>
												</c:forEach>

											</tbody>
										</div>


									</table>
								</form:form>
								</div>
							
</div></table>
				
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
<!-------------列表结束----------->
</html>