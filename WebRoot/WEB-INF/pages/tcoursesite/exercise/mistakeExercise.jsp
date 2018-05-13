<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html>

<head>
    <title>gvsun</title>
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
    <link href="${pageContext.request.contextPath}/css/tCourseSite/jquery.searchableSelect.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/question.js"></script>
</head>

<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
    <div class="course_con ma">
        <div class="course_cont r">
            <ul class="tool_box cf rel zx2 pt5  pb10">
                <li class="rel l pb5">
                    <div class="wire_btn Lele l ml30 mt10 poi " onclick="window.location.href='${pageContext.request.contextPath }/tcoursesite/exercise/findOrderItemListBySiteIdAndQuestionId?tCourseSiteId=${tCourseSite.id}&currpage=1&questionId=-1&itemType=4'">
                        	顺序学习
                    </div>
                    <div class="wire_btn Lele l ml30 mt10 poi " onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/exercise/findQuestionListBySiteId?tCourseSiteId=${tCourseSite.id }&currpage=1'">
                        	章节学习
                    </div>
                    <div class="wire_btn Lele l ml30 mt10 poi " onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/exercise/findStochasticItemListBySiteIdAndQuestionId?tCourseSiteId=${tCourseSite.id }&currpage=1&questionId=-1&itemType=4'">
                        	随机学习
                    </div>
                    <div class="wire_btn Lele l ml30 mt10 poi " onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/exercise/findMistakeItemListBySiteIdAndQuestionId?tCourseSiteId=${tCourseSite.id }&currpage=1&questionId=-1&itemType=4'">
                        	错题学习
                    </div>
                    <div class="wire_btn Lele l ml30 mt10 poi " onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=1&selectId=-1'">
                        	课程知识
                    </div>
                </li>
            </ul>
            <div class="course_mod f14 mb2">
            	<div class=" lh40 bgg  pl30 f18 ">
            		<c:if test="${questionId<0 }">
                    <span class="bgg">错题练习</span>
                    </c:if>
                    <c:if test="${questionId>0 }">
                    <span class="bgg">${tAssignmentQuestionpool.title }</span>
                    <a class="r f14 " href="${pageContext.request.contextPath}/tcoursesite/exercise/findQuestionListBySiteId?tCourseSiteId=${tCourseSite.id }&currpage=1">章节列表</a>
                    </c:if>
                </div>
                <div class=" lh40   pl30 f14 cf ">
                    <div class="bc l w20p poi" onclick="changeMistakeExerciseType(${tCourseSite.id},${questionId },4)" <c:if test="${itemType==4 }">style="color:#000; font-weight:bold"</c:if>>单选题</div>
                    <div class="bc l w20p poi" onclick="changeMistakeExerciseType(${tCourseSite.id},${questionId },1)" <c:if test="${itemType==1 }">style="color:#000; font-weight:bold"</c:if>>多选题</div>
                    <div class="bc l w20p poi" onclick="changeMistakeExerciseType(${tCourseSite.id},${questionId },8)" <c:if test="${itemType==8 }">style="color:#000; font-weight:bold"</c:if>>填空题</div>
                </div>
                <div class="  mt2">
                    <div class="pl30  ptb10 ">
                    	<c:if test="${tMistakeItem != null}">
                        <div class="cf ">
								<div >
									<input type="hidden" value="${tMistakeItem.TAssignmentItem.id }"/>
									<h1>${(pageModel.currpage-1)*(pageModel.pageSize)+1}.
										<!-- 多选，对错，单选 -->
										<c:if test="${tMistakeItem.TAssignmentItem.type==1||tMistakeItem.TAssignmentItem.type==2||tMistakeItem.TAssignmentItem.type==4 }">
											${tMistakeItem.TAssignmentItem.description}
											<span style="margin-left: 20px;color: blue;">
											错题次数:${tMistakeItem.errorCount }次</span>
										</c:if>
										<!-- 填空 -->
										<c:if test="${tMistakeItem.TAssignmentItem.type==8 }">
											${tMistakeItem.TAssignmentItem.descriptionTemp }
											<span style="margin-left: 20px;color: blue;">
											错题次数:${tMistakeItem.errorCount }次</span>
										</c:if>
									</h1><br>
									<!-- 多选 -->
									<c:if test="${tMistakeItem.TAssignmentItem.type==1 }">
										<c:forEach items="${tMistakeItem.TAssignmentItem.TAssignmentAnswers }" var="answer" varStatus="j">
											<input type="checkbox" value="${answer.iscorrect }" class=" check_box" id='answer${answer.id }' class=" check_box"/>
											<label class="l " for="answer${answer.id }">
											<span style="display: none;">${answer.label }</span>
											${answer.label }.<c:out value="${answer.text }"></c:out>
											</label>
											<input type="hidden" value="${answer.id }" />
											<br><br>
										</c:forEach>
										<button class="btn" id="myCheckButton" onclick="myCheck(this)">提交答案 </button>
									</c:if>
									<!-- 对错 -->
									<c:if test="${tMistakeItem.TAssignmentItem.type==2 }">
										<c:forEach items="${tMistakeItem.TAssignmentItem.TAssignmentAnswers }" var="answer" varStatus="j">
											<input type="radio" name="answer" value="${answer.iscorrect }" id='answer${answer.id }' class=" check_box"/>
											<label class="l " for="answer${answer.id }">
											<span style="display: none;">${answer.text }</span>
											<c:out value="${answer.text }"></c:out>
											</label>
											<input type="hidden" value="${answer.id }" />
											<br><br>
										</c:forEach>
									</c:if>
									<!-- 单选 -->
									<c:if test="${tMistakeItem.TAssignmentItem.type==4 }">
										<c:forEach items="${tMistakeItem.TAssignmentItem.TAssignmentAnswers }" var="answer" varStatus="j">
											<input type="radio" name="answer" value="${answer.iscorrect }" id='answer${answer.id }' class=" check_box"/>
											<label class="l " for="answer${answer.id }">
											<span style="display: none;">${answer.label }</span>
											${answer.label }.<c:out value="${answer.text }"></c:out>
											</label>
											<input type="hidden" value="${answer.id }" />
											<br><br>
										</c:forEach>
									</c:if>
									<!-- 填空 -->
									<c:if test="${tMistakeItem.TAssignmentItem.type==8 }">
										<c:forEach items="${tMistakeItem.TAssignmentItem.TAssignmentAnswers }" var="answer" varStatus="j">
											<input type="text" value="" class=" w20p b1 br3 h30 lh30 mt5 plr5"/>
											<input type="hidden" value="${answer.text }" />
											<input type="hidden" value="${answer.id }" />
											<br>
										</c:forEach>
									</c:if>
									<br>
									<%--<button class="btn" id="checkAnswerButton" onclick="checkAnswer(this,${tMistakeItem.TAssignmentItem.type})">查看答案</button>
									--%><p style="background-color: red;margin-left: 0;margin-top: 10px; width: 50%;display: none;">
										<font id="info" class="wh f14" ></font></p>
									<br>
								</div>
                        </div>
                        <div style="padding: 50px 150px">
							<button class="btn" onclick="window.location.href='${pageContext.request.contextPath }/tcoursesite/exercise/findMistakeItemListBySiteIdAndQuestionId?currpage=${pageModel.previousPage}&tCourseSiteId=${tCourseSite.id}&questionId=${questionId }&itemType=${itemType }'">上一题</button>
							<button class="btn" onclick="window.location.href='${pageContext.request.contextPath }/tcoursesite/exercise/findMistakeItemListBySiteIdAndQuestionId?currpage=${pageModel.nextPage}&tCourseSiteId=${tCourseSite.id}&questionId=${questionId }&itemType=${itemType }'">下一题</button>
						</div>
						<div style="padding-left: 80px">
							<form name="form" action="" >
							答对：<b><font id="rightCount" color="green">${rightCount }</font></b>题&nbsp;&nbsp;&nbsp;
							答错：<b><font id="errorCount" color="red">${errorCount }</font></b>题&nbsp;&nbsp;
							正确率：<font id="accuracy">${accuracy }</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<!-- <input type="checkbox" />自动下一题&nbsp;&nbsp; -->
							共${pageModel.totalRecords }题&nbsp;
							转到<input type="text" style="width: 28px;" value="${(pageModel.currpage-1)*(pageModel.pageSize)+1 }" class="easyui-numberbox keydown"/>题
							
							</form>
						</div>
						</c:if>
						<c:if test="${tMistakeItem == null}">
						<div class="cf ">
								<div >
									<h1>暂无错题</h1>
								</div>
						</c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    $("input[type='radio']").change(function(){
		if($(this).attr("checked")){
			var answerId = $(this).next().next().val();
			var itemId = $(this).parent().find("input:first-child").val();
			$(this).parent().find("input[type='radio']").attr("disabled","disabled");
			$.ajax({
				'url':'${pageContext.request.contextPath}/tcoursesite/exercise/saveItemRecord',
				'type':'POST',
				'data':{'answerId':answerId,'questionId':"${questionId}",'itemId':itemId,'tCourseSiteId':"${tCourseSite.id}",'type':"mistake"},
				'success':function(data){
					$("#info").html(data['result']);
					$("#info").parent().show();
					$("#rightCount").html(data['rightCount']);
					$("#errorCount").html(data['errorCount']);
					$("#accuracy").html(data['accuracy']);
					$("#checkAnswerButton").attr("disabled","disabled");
				}
			})
			
		}
	})
	function myCheck(obj)//多选题判断答案对错并保存
    {
    	var answer = '';
    	$(obj).parent().find("input[type='checkbox']:checked").each(function(){
    		answer += $(this).next().next().val()+",";
		})
		if(answer == ""){
			alert("请选择答案！");
			return false;
		}
		var itemId =$(obj).parent().find("input:first-child").val();
			$.ajax({
				'url':'${pageContext.request.contextPath}/tcoursesite/exercise/saveMultiItemRecord',
				'type':'POST',
				'data':{'answer':answer,'questionId':"${questionId}",'itemId':itemId,'tCourseSiteId':"${tCourseSite.id}",'type':"order"},
				'success':function(data){
					$("#info").html(data['result']);
					$("#info").parent().show();
					$("#rightCount").html(data['rightCount']);
					$("#errorCount").html(data['errorCount']);
					$("#accuracy").html(data['accuracy']);
					$("#checkAnswerButton").attr("disabled","disabled");
				}
			})
	}
	$(".keydown").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
            $(this).blur();
            if($(this).val()==""){
            	alert("请输入整数");
            	
            }
            if($(this).val()!=""){
            	var currRecords = $(this).val();
            	var pageSize = ${pageModel.pageSize};
            	var currpage;
            	if(currRecords%pageSize==0){
            		currpage = currRecords/pageSize;
            	}else{
            		currpage = Number((currRecords/pageSize).toString().split(".")[0])+1;
            	}
            	if(currpage<1){
            		currpage = 1;
            	}
            	if (currpage>${pageModel.lastPage}) {
					currpage = ${pageModel.lastPage};
				}
            	window.location.href="${pageContext.request.contextPath }/tcoursesite/exercise/findMistakeItemListBySiteIdAndQuestionId?currpage="+currpage+"&tCourseSiteId="+"${tCourseSite.id}"+"&questionId="+"${questionId }"+"&&itemType="+"${itemType }";
            	//document.form.submit();
            }
        }
    });

	function checkAnswer(obj,type){
		var answer = "正确答案是：";
		//多选
		if(type==1){
			$(obj).parent().find("input[type='checkbox'][value='1']").each(function(){
				answer += $(this).next().html()+"、";
			})
			answer = answer.substr(0,answer.length-1)+"。";
		}
		//对错或单选
		if(type==2||type==4){
			$(obj).parent().find("input[value='1']").each(function(){
				answer += $(this).next().html()+"。";
			})
		}
		//填空
		if(type==8){
			$(obj).parent().find("input[type='text']").each(function(){
				answer += $(this).next().val()+"、";
			})
			answer = answer.substr(0,answer.length-1)+"。";
		}
		$(obj).next().find("font").html(answer);
		$(obj).next().toggle();
	}
	
	
	function createTest(tCourseSiteId){
		window.location.href = '${pageContext.request.contextPath}/tcoursesite/exercise/beginTest?tCourseSiteId=${tCourseSite.id}';
	}
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>

</body>

</html>