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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autosize.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.searchableSelect.js"></script>
    
 
<script type="text/javascript">
 function changeGroup(groupId){
	//切换小组
 	$.ajax({
 		type: 'POST',
 		url: '${pageContext.request.contextPath}/tcoursesite/assignment/changeGroup',
 		data: {'assignmentId':${tAssignment.id},'groupId':groupId},
 		dataType:'json',
 		success:function(data){
 			$.each(data,function(key,values){
 				if(key=="groupAssignmentsList"){
 					$("#"+key).attr("value",values);
 				}else{
 					document.getElementById(key).innerHTML=values;
 				}
 			});
 		}
 	});
 }
 
 function saveScore(assignmentId,username,groupId,type){
	 //获取分数
	 var score = document.getElementById(username).value;
	 if(type == 2){
		 //如果组员成绩为空则改为和组长相同分数
		 $("#scoreInfo").find("input").each(function () {//遍历页面所有checkbox
			    if(this.value == "" || this.value == null){//如果多选框被选中
			    	this.value = score;
			    	saveScore(assignmentId,this.id,groupId,0);
			    }
		    });
	 }
	//评分时自动保存
	 $.ajax({
	 		type: 'POST',
	 		url: '${pageContext.request.contextPath}/tcoursesite/assignment/saveScore',
	 		data: {'assignmentId':assignmentId,'username':username,'score':score,'groupId':groupId},
	 	});
 }
 
//正则表达式规范得分填写
function changeNumber(obj,score){
	var price=$(obj).val();
	price = price.replace(/[^\d.]/g,"");
    //必须保证第一个为数字而不是.
    price = price.replace(/^\./g,"");
    //保证只有出现一个.而没有多个.
    price = price.replace(/\.{2,}/g,".");
    //保证.只出现一次，而不能出现两次以上
    price = price.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
    if(Number(price)>score){
    	alert("打分不得高于分值，分值为"+score+"！");
    	$(obj).val(score);
    	$(obj).parent().parent().find("#finalScoreFont").html(score);
    	return;
    }
    $(obj).val(price);
}
 </script><%--
 <script type="text/javascript">
         var editor = UE.getEditor('content',{  
                autoClearinitialContent:true,  
                initialFrameWidth:605  
            });
         var editor = UE.getEditor('distribution',{
             autoClearinitialContent:true,  
                initialFrameWidth:605  
         });
         
    </script>
--%></head>
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
                        <div class="mb15 group_list">
                       		
                            <span class="mr10">小组列表：</span>
                            <select onchange="changeGroup(this.options[this.selectedIndex].value);" class="chzn-select" style="width:150px;">
								<c:forEach items="${groups}" var="group" varStatus="i">
									<option value="${group.id}"
										<c:if test='${groupId == group.id}'> selected='selected'
		     															</c:if>>${group.groupTitle}</option>
								</c:forEach>
							</select>
                        </div>
                        <div class="mb15">
                            <span class="mr10">小组名称：</span>
                            <span >${groupTitle}</span>
                        </div>
                        <div class="mb15" id="groupMemberInfo">
                            <span class="mr10">该组成员：</span>
                            <span >${groupMemberInfo }</span>
                        </div>
						<div class="cf mb15">
                            <span>作业描述：</span>
                            <div id="content" name="content" class="r mr23p" style="width:605px;">${tAssignmentGrading.content }</div>
                        </div>
						<div class="cf mb15">
                            <span>小组分工：</span>
                            <div id="distribution" name="distribution" class="r mr23p" style="width:605px;">${tAssignmentGrading.distribution }</div>
                        </div>
						<div class="cf mb15">
			     			<span class="l">作业附件：</span>	
			     			<button class="wire_btn l ml5  poi" onclick="downloadAttachments(${tCourseSite.id},${tAssignment.id });" >批量下载</button><br/'>
		                    <input name="groupAssignmentsList" id="groupAssignmentsList" type="text" class="hide" value="${groupAssignmentsList }"/><br>
	                    	<ul id="groupAssignmentsNameList" class="file_download w90p cf cl mrla">
								${groupAssignmentsNameList}
	                    	</ul>
					 	</div>
					 	<div id="scoreInfo">
					 		${scoreInfo}
					 	</div><br/><br/>
					 </div>
				</div>
			</div>
		</div>
	</div>
</div>
    	
	
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
   $(function(){
	   $('.group_list').find('.chzn-container').css("width","120px");
   })
	
   </script>
        
<!-- js for groupAssignment -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/tGroupAssignment.js"></script>
</body>
</html>

