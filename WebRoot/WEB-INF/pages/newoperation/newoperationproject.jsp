<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="bundles.projecttermination-resources"/>
<html>

	<head>
		<title></title>
		<%response.setHeader("Charset","GB2312"); %>
		<meta name="Generator" content="gvsun">
		<meta name="Author" content="chenyawen">
		<meta name="decorator" content="new"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="Keywords" content="">
		<meta name="Description" content="">
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/lib.css" />
		<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
		  <!-- 下拉框的样式 -->
 		 <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  		<link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/chosen/chosen.css" />
  		<!-- 下拉的样式结束 --> 
  		<link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/js/jquery-easyui/themes/gray/easyui.css" />
  		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/spring/Spring.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery-1.7.1.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery-1.7.1.min.js"></script>
		  <!-- 文件上传的样式和js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>  
		
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
	</head>

	<body>
		<div id="bgheight">
			<script type="text/javascript">
				$(function() {
					$("#bgheight").height($(window).height() / 1);
				})
			</script>

			<div class="all_main_content">
				<div class="grey_line">
					<table>
						<tr>
							<td>
								<i class="fa fa-plus-square-o l lh33 mr10 f24"></i>
								<c:if test="${isNew eq 1 }">
								<span>新建实验大纲</span>
								</c:if>
								<c:if test="${isNew eq 0 }">
								<span>编辑实验大纲</span>
								</c:if>
							</td>
						</tr>
					</table>
					<%-- <c:if test="${isNew eq 0 }">
					<button class="r mt7 mr2p"  type="button" onclick="submitOutline()"><i class="fa fa-external-link mr5"></i>提交</button>
					</c:if> --%>
					<button class="r mt7" type="button" onclick="saveOutline()"><i class="fa fa-save mr5"></i>保存</button>
				</div>
				
				<div class="bgw w100p ptb10">
					<form:form action="${pageContext.request.contextPath}/newoperation/saveoperationoutline"
							 method="post" modelAttribute="operationOutline"  name="outlineForm" onsubmit="return sunb();" >
							 <form:hidden path="id"/>
					<div class="new_exp_block">
						<span class="new_tit">实验大纲基本信息</span>
						<table class="experimental_list_tab" cellspacing="0">
							<tr>
								<th>课程编号</th>
								<th>课程名称</th>
								<th>英文名称</th>
								<th>学分</th>
								<th>总学时</th>
								<th>实验学时</th>
								<th>课程类型</th>
								<th>课程性质</th>
								<th>适用专业</th>
								<th>先修课程</th>
								<th>开课学期</th>
								<th>开课院系</th>
							</tr>
							<tr>
								<td>
									<form:select
										path="schoolCourseInfoByClassId.courseNumber" name="coursenumber" id="coursenumber" class="chzn-select"  onchange="setOutlineName()">
										<form:option value="">请选择</form:option>
										<c:forEach items="${schoolCourseInfoMap}" var="map">
											<form:option value="${map.key }">${map.value }</form:option>
										</c:forEach>
									</form:select>
								</td>
								<td id="outlineName">${operationOutline.labOutlineName}</td>
								<td><form:input path="enName" /></td>
								<td class="tc"><select
										path="cOperationOutlineCredit.id" name="credit">
										<c:forEach items="${operationscareMap}" var="sca">
											<c:if test="${sca.key==outlineCredit.id }">
												<option value="${sca.key }" selected="selected">${sca.value }</option>
											</c:if>
												<option value="${sca.key }">${sca.value }</option>
										</c:forEach>
										</select></td>
								<td class="tc"><form:input
											path="period" required="true"/></td>
								<td class="tc"><form:input
											path="experimentPeriod" required="true"/></td>
								<td class="tc">
									<select
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
								<td class="tc">
									<select
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
								</select>
								</td>
								<td class="tc">
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
								<td class="tc">
									<select
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
								<td class="tc">
									<select
										name="termIdes" id="termIdes" class="chzn-select" 
										 multiple="multiple">
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
								<td class="tc">
									<select
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
							</tr>
						</table>
						<div class="w100p ovh mt30">
							<div class="edit_block l">
								<div>课程的教学目标与任务</div>
								<form:textarea placeholder="请输入内容" path="outlineCourseTeachingTarget" required="true"></form:textarea>
							</div>
							<div class="edit_block r">
								<div>考核方式</div>
								<form:textarea placeholder="请输入内容" path="assResultsPerEvaluation" ></form:textarea>
							</div>
							<div class="edit_block l">
								<div>本课程与其它课程的联系和分工</div>
								<form:textarea placeholder="请输入内容" path="basicContentCourse"></form:textarea>
							</div>
							<div class="edit_block r">
								<div>推荐教材与参考资料</div>
								<form:textarea placeholder="请输入内容" path="useMaterials"></form:textarea>
							</div>
						</div>
						<div class="edit_bottom">
							<span><button onclick="uploaddocment()" type="button">上传附件</button></span>
							<fieldset class="introduce-box">
						<input type="hidden" value="" id="xsd">
						<table>
							<tr >
								 <td>文档名称</td>
								 <input type="hidden" name="docment" id="docment" />
							 </tr>
							 <c:forEach items="${operationOutline.commonDocuments}" var="curr">
							 	<tr id="s_${curr.id}">
							 		<td>${curr.documentName }</td>
							 		<td><input type="button" onclick="delectuploaddocment(${curr.id})" value="删除"></td>
							 	</tr>
							 </c:forEach>
						</table>
						 <table>
							  <tbody id="doc"></tbody>
						 </table>	
					</fieldset>
							<button class="r mtb5"><i class="fa fa-save mr5"></i>保存</button>
						</div>
					</div>
					</form:form>
					<div class="new_exp_block">
						<span class="new_tit">课程内容及基本要求</span>
						<table class="experimental_list_tab" cellspacing="0">
							<tr>
								<th>实验编号</th>
								<th>实验名称</th>
								<th>实验学时</th>
								<th>实验属性</th>
								<th>所属实验室</th>
								<th>基本要求</th>
								<th>操作</th>
							</tr>
							<c:forEach items="${items }" var="curr">
								<tr id="showItem${curr.id }">
									<td>${curr.lpCodeCustom}</td>
									<td>${curr.lpName}</td>
									<td class="tc">${curr.lpDepartmentHours}</td>
									<td class="tc">
										<c:if test="${curr.specification eq 1}">必选</c:if>
										<c:if test="${curr.specification eq 2}">非必选</c:if>
									</td>
									<td class="tc">
										${curr.labRoom.labRoomName}
									</td>
									<td class="tc">
										${curr.basicRequired}
									</td>
									<td class="tc">
										<a class="fa fa-edit mlr2p blue" onclick="showEditItem(${curr.id})"></a>
										<a class="fa fa-times mlr2p blue" href="${pageContext.request.contextPath}/newoperation/deleteOperationItem?operationItemId=${curr.id }"onclick="return confirm('您确认删除吗？')"></a>
										<a class="fa fa-arrow-up mlr2p blue" href="${pageContext.request.contextPath}/newoperation/upOrder?id=${curr.id }"></a>
										<a class="fa fa-arrow-down mlr2p blue" href="${pageContext.request.contextPath}/newoperation/downOrder?id=${curr.id }"></a>
									</td>
								</tr>
								<form action="${pageContext.request.contextPath}/newoperation/saveEditOperationItem?itemId=${curr.id }"
								 method="post" modelAttribute="operationItem" name="editForm${curr.id }">
								<tr id="editItem${curr.id }" style="display:none">
									<td id="addCode${curr.id }">${curr.lpCodeCustom}</td>
									<td><input id="lpName${curr.id }" class="easyui-validatebox" required="true" value="${curr.lpName}" name="lpName${curr.id }"/></td>
									<td class="tc"><input name="lpDepartmentHours${curr.id }" id="lpDepartmentHours${curr.id }" class="easyui-validatebox" required="true"
							        onkeyup="value=value.replace(/[^\d]/g,'') "   
									placeholder="请输入大于零的整数"
							         value="${curr.lpDepartmentHours}"/></td>
							         <td>
							         	<select name="specification${curr.id }" id="specification${curr.id }" class="chzn-select">
								          <option value="">--请选择--</option>
								          	<c:if test="${curr.specification eq 1 }">
								          	<option value="1" selected="selected">必选</option>
								          	<option value="2">非必选</option>
								          </c:if>
								            <c:if test="${curr.specification eq 2 }">
								          	<option value="1">必选</option>
								          	<option value="2"  selected="selected">非必选</option>
								          </c:if>
								           <c:if test="${curr.specification ne 1 && curr.specification ne 2  }">
								          	<option value="1">必选</option>
								          	<option value="2">非必选</option>
								          </c:if>
								        </select>
							         </td>
									<td class="tc">
										<select name="labRoomId${curr.id }" id="labRoomId${curr.id }" class="chzn-select">
								          <option value="">- - - -请选择- - - -</option>
								          <c:forEach items="${labRooms}" var="l">
								          	<c:if test="${l.id eq curr.labRoom.id }"><option value="${l.id}" selected="selected">${l.labRoomName}-${l.labRoomAddress }</option></c:if>
								            <c:if test="${l.id ne curr.labRoom.id }"><option value="${l.id}">${l.labRoomName}-${l.labRoomAddress }</option></c:if>
								          </c:forEach>
								        </select>
									</td>
									<td class="tc">
										<textarea  class="requirement_edit" id="basicRequired${curr.id }" name="basicRequired${curr.id }" placeholder="请输入内容">${curr.basicRequired}</textarea>
									</td>
									<td class="tc">
										<button class="fa fa-save mlr2p blue" ></button>
									</td>
								</tr>
								</form>
							</c:forEach>
							<c:if test="${isNew eq 0 }">
								<form:form action="${pageContext.request.contextPath}/newoperation/saveOperationItemforxidlims?outlineId=${operationOutline.id }"
								 method="post" modelAttribute="operationItem" name="addForm">
								<tr id="addItem" style="display:none">
									<td id="addCode">${itemCode } <form:input type="hidden" path="lpCodeCustom" value="${itemCode }"/></td>
									<td><form:input path="lpName" id="lpName" class="easyui-validatebox" required="true"/></td>
									<td class="tc"><form:input path="lpDepartmentHours" id="lpDepartmentHours" class="easyui-validatebox" required="true"
							        onkeyup="value=value.replace(/[^\d]/g,'') "   
									placeholder="请输入大于零的整数"
							        /></td>
							         <td>
							         	<form:select path="specification" class="chzn-select">
								          <form:option value="">--请选择--</form:option>
								          	<form:option value="1" selected="selected">必选</form:option>
								          	<form:option value="2">非必选</form:option>
								        </form:select>
							         </td>
									<td class="tc">
										<form:select path="labRoom.id" id="labRoomId" name="labRoomId" class="chzn-select">
								          <form:option value="">- - - -请选择- - - -</form:option>
								          <c:forEach items="${labRooms}" var="l">
								            <form:option value="${l.id}">${l.labRoomName}-${l.labRoomAddress }</form:option>
								          </c:forEach>
								        </form:select>
									</td>
									<td class="tc">
										<form:textarea path="basicRequired" class="requirement_edit" placeholder="请输入内容"/>
									</td>
									<td class="tc">
										<button class="fa fa-save mlr2p blue" ></button>
									</td>
								</tr>
								</form:form>
							</c:if>
						</table>
						<div class="edit_bottom">
							<a class="fa fa-plus f20 r hbb poi" onclick="showAddItem()" title="新增内容"></a>
						</div>
					</div>

				</div>
				
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
		</div>
		<script type="text/javascript">
			$(".quickbtn").click(
				function() {
					$(".quick_above").addClass("block");
				}
			);
			$(document).bind("click", function() {
				$('.quick_above').removeClass("block");
			});
			$(".quickbtn").click(function(event) {
				event.stopPropagation();

			});
			$(".quick_above").click(function(event) {
				event.stopPropagation();

			});
			$(window).scroll(function() {
				$(".quick_above").removeClass("block");
			});
			
				
			function setOutlineName(){
				$.ajax({
                url:"${pageContext.request.contextPath}/newoperation/getCourseNameByCourseNumber?courseNumber="+$("#coursenumber").val(),
                type:"POST",
                contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                success:function(data){//AJAX查询成功
                      $("#outlineName").html(data.courseName);
                }
            });
			}
			
			function showAddItem(){
				$("#addItem").show();
			}
			
			function saveOperationItem(){
				document.addForm.submit();
			}
			function showEditItem(id){
				$("#showItem"+id).hide();
				$("#editItem"+id).show();
			}
			function submitOutline(){
				window.location.href="${pageContext.request.contextPath}/newoperation/submitOutline?outlineId=${operationOutline.id}";
			}
			
			function saveOutline(){
				document.outlineForm.submit();
			}
		</script>
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
	</body>

</html>