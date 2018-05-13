<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>预习测试</title>
    <!--[if lt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <![endif]-->
    <!--[if gte IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>  
    <![endif]-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/reset.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/lib.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/global.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/skill/experiment.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/skill/picChange.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
    

<script type="text/javascript">
	function checkUser(obj){
		if($(obj).val().trim()!=""){
			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/teaching/coursesitetag/checkUser",
				data: {'username':$(obj).val().trim()},
				dataType:'json',
				success:function(data){
					if(!data){
						alert("该工号不存在，请查询后输入！");
						$(obj).val("");
					}
				}
			});
			
		}
	}
	
/*
*编辑题目
*/
function editTAssignmentItem(examItemId){
var sessionId = $("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/teaching/exam/examItemInfo?examItemId='+ examItemId +'" style="width:100%;height:100%;"></iframe>'
$('#editTAssignmentItem').html(con);
//获取当前屏幕的绝对位置
var topPos = window.pageYOffset;
//使得弹出框在屏幕顶端可见
$('#editTAssignmentItem').window({left:"px", top:topPos+"px"});
$('#editTAssignmentItem').window('open');
}

	function openwindow(sectionId,countScore){
	    var con = '<iframe id="con" scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/teaching/question/checkQuestionListForSection?sectionId='+sectionId+'" style="width:100%;height:100%;"></iframe>'
	    $("#openwindow").html(con); 
	    //获取当前屏幕的绝对位置
	    var topPos = window.pageYOffset+10;
	    //使得弹出框在屏幕顶端可见
	    //$('#openwindow').window({
	    //	top:topPos+"px",
	    //	onBeforeClose:function(){
	    //		$("#con").remove();
		//		$("#openwindow").window('close',true);
	    //	}
	    //}); 
	    //$("#openwindow").window('open');
	    $("#questions").fadeIn(100);
	}
	function checkScore(countScore,score){
		if(countScore != score){
			alert("总分不符！");
			return false;
		}
		return confirm('确定要发布吗？');
	}

</script>
</head>
<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="tCourseSiteId" value="${tCourseSite.id}"/>
	<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
	<input type="hidden"  id="now" value="${now}"/>
	<input type="hidden"  id="skillId" value="${tExperimentSkill.id}"/>
	
	
	<div class="course_con ma back_gray" >
		<div class="course_cont r back_gray">
			<div class="course_content">
                <div class="tab_content">
                	<!--题目内容  -->
                	<div class="tab_content hide" >
                      <c:set var="countScore" value="0"></c:set>
                      <c:forEach items="${examInfo.TAssignmentSections}" var="current"  varStatus="i">
                          <c:forEach items="${current.TAssignmentItems}" var="current1"  varStatus="k">
                                   <c:set var="countScore" value="${countScore+current1.score }"></c:set>
                          </c:forEach>
                      </c:forEach>
                      ${countScore}/${examInfo.TAssignmentAnswerAssign.score}
                    </div>
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">测验题目信息</div>
							<!-- 如果已发布，则不能再更改测压信息 -->
							<c:if test="${examInfo.status == 0 }">
								<mytag:JspSecurity realm="add" menu="exam">
									<a class="btn btn-new" href="${pageContext.request.contextPath}/tcoursesite/skill/prepareExamNewSection?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id }">添加大题</a>
								</mytag:JspSecurity>
							</c:if>
							<c:if test="${examInfo.status == 0 }">
								<a class="btn btn-new" href="${pageContext.request.contextPath}/tcoursesite/skill/prepareExamRelease?tCourseSiteId=${tCourseSite.id}&assignmentId=${examInfo.id }&skillId=${tExperimentSkill.id }" onclick="return checkScore(${countScore},${examInfo.TAssignmentAnswerAssign.score})" >发布</a>
							</c:if>
							<a class="btn btn-new" href="${pageContext.request.contextPath}/tcoursesite/skill/prepareExam?tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}">返回</a>
						</div>
						<div class="new-classroom" >
                            <table  class="tb"  id="my_show"> 
				                <thead>
				                    <tr> 
							    	<!-- <th>序号</th>
							    	<th>题目名称</th>
							    	<th>题目类型</th>
							    	<th>分值</th>
							    	<th>创建时间</th>
							    	<th>删除</th> -->
							    	<th width=10%>序号</th>
							    	<th width=90%>
							    	 <table  class="tb"  id="my_show"> 
							    	 <tr>
							    	  <td width=65%>题目信息</td>
							    	  <td width=10%>题目类型</td>
							    	  <td width=10%>分值</td>
							    	  <c:if test="${examInfo.status == 0 }">
							    	  <td width=10%>操作</td>
							    	  </c:if>
							    	  </tr>
							    	  </table>
							    	  </th>
							    	<%--<th>
							    	<table  class="tb"  id="my_show"> 
							    	</table>
							    	
							    	</th>
	                                --%></tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${examInfo.TAssignmentSections}" var="current"  varStatus="i">
                                    <tr>
                                        <td>
                                        	${i.count }.<c:out value="${current.description}"></c:out><br>
                                        	<c:if test="${examInfo.status == 0 }">
                                        	<a href="javascript:void(0);" onclick="openwindow(${current.id},${countScore})">从题库选题</a><br>
                                        	<a href="${pageContext.request.contextPath}/tcoursesite/skill/prepareExamNewItem?sectionId=${current.id}&tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}">添加题目</a>
                                        	</c:if>
                                        </td>
                                        <td>
                                        <table  class="tb"  id="my_show"> 
                                        <c:forEach items="${current.TAssignmentItems}" var="current1"  varStatus="j">
                                        	<tr>
                                            	<td width=65% style="padding: 10px;">${j.count}、题干：<c:out value="${current1.description}"></c:out><br>
                                                	<c:forEach items="${current1.TAssignmentAnswers}" var="current2"  varStatus="k">
		                                                <!-- 若是选择题则把选项显示出来 -->
		                                                <c:if test="${current1.type==1||current1.type==4 }">
		                                                	<p><c:out value="${current2.label}: ${current2.text}"></c:out></p>
		                                                	 
		                                                </c:if>
	                                                
	                                                </c:forEach>
                                               
                                               </td>
                                               <td width=10%>
		                                           	<c:if test="${current1.type==1}">多选题</c:if>
		                                           	<c:if test="${current1.type==2}">对错题</c:if>
		                                           	<c:if test="${current1.type==4}">单选题</c:if>
		                                           	<c:if test="${current1.type==5}">简答题</c:if>
		                                           	<c:if test="${current1.type==8}">填空题</c:if>
		                                           	<c:if test="${current1.type==9}">匹配题</c:if>
                                               </td>
                                               <td width=10%>${current1.score}</td>
                                               <c:if test="${examInfo.status == 0 }">
                                               <td width=10%>
                                               		<mytag:JspSecurity realm="update" menu="exam">
	                                                   	<a href="${pageContext.request.contextPath}/tcoursesite/skill/prepareExamEditItem?examItemId=${current1.id}&tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}">编辑题目</a><br><br>
                                               		</mytag:JspSecurity>
                                               		<mytag:JspSecurity realm="delete" menu="exam">
	                                                   	<a href='${pageContext.request.contextPath}/tcoursesite/skill/prepareExamDeleteItem?examItemId=${current1.id}&tCourseSiteId=${tCourseSite.id}&skillId=${tExperimentSkill.id}' onclick="return confirm('是否确认删除该题目？')">删除题目</a>
                                               		</mytag:JspSecurity>
                                               </td>
                                               </c:if>
                                            </tr>
                                            
                                        </c:forEach>
                                        </table>
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
    </div>
    
<!-- 编辑题目 -->
<div id="editTAssignmentItem" class="easyui-window" title="编辑题目" closed="true"  modal="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>

<div class="window_box hide fix zx2  " id="questions">content
	<div class="window_con bgw b1 br3 rel bs10 ">
	<span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
<div id="openwindow" class="easyui-window" title="选择题库" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width: 900px; height:450px;">
</div>
   </div>
   </div> 	
    

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/skill/experimentSkill.js"></script>
</body>
</html>