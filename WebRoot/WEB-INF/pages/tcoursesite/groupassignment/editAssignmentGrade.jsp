<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />   
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/reset.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/lib.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/check_work.css" rel="stylesheet" type="text/css">
    <!--下拉框样式-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/chosen/chosen.css" />
    <!--下拉框的样式结束-->
    <!-- ueditor编辑器 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script> 
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css"/> 
    
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autosize.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.searchableSelect.js"></script>
<script type="text/javascript">
	//检查提交次数是否超过限制
	function checkForm(){
		var now = ${tAssignment.TAssignmentGradings.size()};//已提交次数
		var total = ${tAssignment.TAssignmentControl.timelimit};//提交次数限制
		if(now >= total&&total!=0){
			alert("提交次数已达限制，无法再进行提交操作！");
			return false;
		}else{
			//$("#submitTime").val()==1&& used for save check
			if(now > 0){
				return confirm("此次提交会覆盖以前的提交记录，是否确认继续？");
			}
		}
	}
</script>
 <script type="text/javascript">
     var editor = UE.getEditor("distribution");
     
</script>
</head>
<body>
<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>

<div class="course_con ma">
	<div class="course_cont r">
		<div class="course_mod f14 mb2">
			<div class=" lh40 bgg  pl30 f18 ">
				<span class="bc">小组作业</span>
				<a class="btn btn-new f14 r mr10 mt5 g3 hbc" href="javascript:void(0)" onclick="window.history.go(-1)">返回</a>
			</div>
			
			<div class="module_con  mtb20">
				<div class="plr30 lh30 f14">
					<div class="w100p f14">
						<form:form id="myForm" action="${pageContext.request.contextPath}/tcoursesite/assignment/saveGroupTAssignmentGrade?moduleType=${moduleType }&selectId=${selectId}&groupId=${group.id}" method="POST" modelAttribute="tAssignmentGrade" onsubmit="return checkForm()" enctype="multipart/form-data">
						<form:hidden path="accessmentgradingId" />
						<div class="mb15">
                            <span class="mr10">小组名称：</span>
                            <span >${group.groupTitle}</span>
                        </div>
                        <div class="mb15" id="groupMemberInfo">
                            <span class="mr10">该组成员：</span>
                            <span >${groupMemberInfo }</span>
                        </div>
						<div class="cf mb15">
                            <label>作业描述：</label>
                             <script type="text/javascript">
								     var editor = UE.getEditor("content");
								</script>
                            <textarea id="content" name="content" class="r mr23p" style="width:605px;">${tAssignmentGrade.content }</textarea>
                        </div>
						<div class="cf mb15">
                            <span>小组分工：</span>
                            <textarea id="distribution" name="distribution" class="r mr23p" style="width:605px;">${tAssignmentGrade.distribution }</textarea>
                        </div>
                        
						<div class="cf mb15">
			     			<span class="l">作业附件：</span>	
			     			<c:if test="${groupRole == 2}"> 
				     			<button class="wire_btn l ml5  poi" type="button" onclick="upAttachment(${tAssignment.id },${tCourseSite.id})">上传</button>
				     			<button class="wire_btn l ml5  poi" type="button" onclick="deleteAttachment(${tAssignment.id },${tCourseSite.id})">批量删除</button>
			     			</c:if>	
			     			<button class="wire_btn l ml5  poi" type="button" onclick="downloadAttachments(${tCourseSite.id},${tAssignment.id });" >批量下载</button><br/>
		                    <input name="groupAssignmentsList" id="groupAssignmentsList" type="text" class="hide" value="${groupAssignmentsList }"/><br>
	                    	<ul id="groupAssignmentsNameList" class="">
								${groupAssignmentsNameList}
	                    	</ul>
					 	</div>
					 	<c:if test="${groupRole != 2}"> 
					 		<span style="color:red">不是组长无法提交作业！！！</span>
					 	</c:if>
					 	<form:hidden path="submitTime" id="submitTime"/>
					 	<form:hidden path="submitdate"/>
					 	<form:hidden path="userByStudent.username"/>
					 	<form:hidden path="TAssignment.id"/>
					 	<form:hidden path="groupId"/>
					 	<c:if test="${isGraded==null && now <= tAssignment.TAssignmentControl.duedate.time && groupRole == 2}">
						 	<div class="tc mb15">
			                    <input type="submit" class="bbtn bgb f14 tc wh nb br3 w80 poi" value="提交" />
			                </div>
						</c:if>
					 	</form:form>
					 </div>
				</div>
			</div>
		</div>
	</div>
</div>
    	
<!--添加文件开始-->
<div class="window_box hide fix zx2  " id="upAttachment">
    <div class="window_con bgw b1 br3 rel bs10 ">
        <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
         <div class="add_tit p10 bb f16">资源上传</div>
        <div class="add_con p10">
            <div class="add_module cf">
                <div class="tab_box">
                <div class="tab_list f14 mb2">
                <input type="file" name="file" id="upWkUploadUploadifyPic" />
                </div>
                </div>
            </div>
            <div class="cf tc">
                <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">取消</div>
            </div>
        </div>
    </div>
</div>
<!--添加文件结束-->   
    

 
<script src="${pageContext.request.contextPath}/js/tCourseSite/global.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select': {search_contains : true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
      '.chzn-select-width'     : {width:"100px"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }

    </script>
<!-- 上传插件的css和js -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/tGroupAssignment.js"></script>
</body>
</html>

