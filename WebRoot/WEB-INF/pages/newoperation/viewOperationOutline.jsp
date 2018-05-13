<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="bundles.projecttermination-resources"/>
<html>

	<head>
		<title></title>
		<meta name="Generator" content="gvsun">
		<meta name="Author" content="chenyawen">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="Keywords" content="">
		<meta name="decorator" content="new"/>
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
								<span>查看实验大纲</span>
							</td>
						</tr>
					</table>
					<button class="r mt7" type="button" onclick="returnToList()">返回</button>
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
								<td>${operationOutline.schoolCourseInfoByClassId.courseNumber}</td>
								<td id="outlineName">${operationOutline.labOutlineName}</td>
								<td>${operationOutline.enName}</td>
								<td class="tc">${operationOutline.COperationOutlineCredit.credit}</td>
								<td class="tc">${operationOutline.period}</td>
								<td class="tc">${operationOutline.experimentPeriod}</td>
								<td class="tc">${operationOutline.courseDescription}</td>
								<td class="tc">
									<c:forEach items="${operationOutline.CDictionarys}" var="curr">
										${curr.CName }
									</c:forEach>
								</td>
								<td class="tc">
									<c:forEach items="${operationOutline.systemMajors}" var="curr">
										${curr.MName }
									</c:forEach>
								</td>
								<td class="tc">${operationOutline.labOutlineName}
									<c:forEach items="${operationOutline.schoolCourseInfoes}" var="curr">
										${curr.courseName}
									</c:forEach>
								</td>
								<td class="tc">
									<c:forEach items="${operationOutline.studyStages}" var="curr">
										${curr.CName}
									</c:forEach>
								</td>
								<td class="tc">
									<c:forEach items="${operationOutline.schoolAcademies}" var="curr">
										${curr.academyName}
									</c:forEach> 
								</td>
							</tr>
						</table>
						<div class="w100p ovh mt30">
							<div class="edit_block l">
								<div>课程的教学目标与任务</div>
								&nbsp; ${operationOutline.outlineCourseTeachingTarget}
							</div>
							<div class="edit_block r">
								<div>考核方式</div>
								&nbsp; ${operationOutline.assResultsPerEvaluation}
							</div>
							<div class="edit_block l">
								<div>本课程与其它课程的联系和分工</div>
								&nbsp;${operationOutline.basicContentCourse}
							</div>
							<div class="edit_block r">
								<div>推荐教材与参考资料</div>
								&nbsp;${operationOutline.useMaterials}
							</div>
						</div>
						<div class="edit_bottom">
							<fieldset class="introduce-box">
						<input type="hidden" value="" id="xsd">
						<table>
							<tr >
								 <td>文档名称</td>
								 <input type="hidden" name="docment" id="docment" />
							 </tr>
							 <c:forEach items="${operationOutline.commonDocuments}" var="curr">
							 	<tr>
							 		<td>${curr.documentName }</td>
							 		<td><a href="${pageContext.request.contextPath}/operation/downloadfile?idkey=${curr.id}">下载</a></td>
							 	</tr>
							 </c:forEach>
						</table>
						 <table>
							  <tbody id="doc"></tbody>
						 </table>	
					</fieldset>
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
								</tr>
							</c:forEach>
						</table>
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
                url:"${pageContext.request.contextPath}/newoperation/findWeekdaysByTermId?courseNumber="+$("#coursenumber").val(),
                type:"POST",
                success:function(data){//AJAX查询成功
                      $("#outlineName").html(data);
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
	function returnToList(){
		if(${from} == 1){
			window.location.href="${pageContext.request.contextPath}/newoperation/experimentalmanagement?currpage=${page}";
		}
		else if(${from} == 2){
			window.location.href="${pageContext.request.contextPath}/newoperation/experimentalMyAudit?currpage=${page}";
		}else if(${from} == 3){
			window.location.href="${pageContext.request.contextPath}/newoperation/experimentalAudit?currpage=${page}";
		}else if(${from} == 4){
			window.location.href="${pageContext.request.contextPath}/newoperation/listStudentsOperationOutlines?currpage=${page}";
		}else if(${from} == 5){
			window.location.href="${pageContext.request.contextPath}/newoperation/listTeachersOperationOutlines?currpage=${page}";
		}
		
	}
</script>
	</body>

</html>