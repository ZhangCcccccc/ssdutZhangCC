<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<%--<%@ include file="/WEB-INF/sitemesh-decorators/course.jsp" %>
--%><html>

<head>
    <title></title>
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

<script type="text/javascript">
	$(function(){
	   	$("#print").click(function(){
			$("#my_show").jqprint();
		});
	});
	
	function newQuestionPool(id,tCourseSiteId){
		if(id!=""){//修改则查询原信息
			$("#questionpoolId").val(id);
			$.ajax({
				url:"${pageContext.request.contextPath}/tcoursesite/question/findQusetionStringById?tCourseSiteId="+tCourseSiteId+"&id="+id,
		       	type:"POST",
		       	async:false,
		       	success:function(data){
					$("#questionTitle").val(data[0]);
					$("#questionType").val(data[1]);
					findQusetionListByTypeAndId(data[1],id,tCourseSiteId);
					$("#description").val(data[2]);
					$("#questionpoolParentId").val(data[3]);
					$("#sort").val(data[4]);
		       	}
			})
		}else{//新增
			$("#questionpoolId").val("");
			findQusetionListByTypeAndId($("#questionType").val(),id,tCourseSiteId);		
			$("#description").val("");
			$("#questionTitle").val("");
			$("#questionPoolParentId").val("");
			$("#sort").val("");
		}
		
		//绑定change事件
		$("#questionType").change(function(){
			findQusetionListByTypeAndId($("#questionType").val(),id,tCourseSiteId);
		})
	    $("#newQuestionPool").fadeIn(100);
	}
	
	function findQusetionListByTypeAndId(type,id,tCourseSiteId){
		$.ajax({
			url:"${pageContext.request.contextPath}/tcoursesite/question/findQusetionListByTypeAndId?type="+type+"&id="+id+"&tCourseSiteId="+tCourseSiteId,
	       	type:"POST",
	       	success:function(data){
	       		$("#questionpoolParentId").html(data);
	       	}
		})
	}
</script>
</head>

<body>
    <div class="course_con ma">
        <div class="course_cont r">
            <ul class="tool_box cf rel zx2 pt5  pb10">
                <li class="rel l pb5">
                	<div class="wire_btn Lele l ml30 mt10 poi " onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=1&selectId=-1'">
                        	课程知识
                    </div>
                </li>
            </ul>
            <div class="course_mod f14 mb2">
                <div class=" lh40 bgg  pl30 f18 ">
                    <span class="bc">章节练习</span>
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14">
                            <table class="w100p">
                                <tr>
                                    <th class="w30p tl">名称</th>
                                    <th class="tl w10p">操作</th>                                    
                                </tr>
                               <c:forEach items="${tAssignmentQuestionpools }" var="questionPool" varStatus="i">
	                                <tr>
	                                    <td>${questionPool.title }</td>
	                                    <td>
	                                    	<a href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/exercise/findOrderItemListBySiteIdAndQuestionId?tCourseSiteId=${tCourseSite.id}&currpage=1&questionId=${questionPool.questionpoolId }&itemType=4'">
	                                    	顺序</a>
	                                    	<a href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/exercise/findStochasticItemListBySiteIdAndQuestionId?tCourseSiteId=${tCourseSite.id }&currpage=1&questionId=${questionPool.questionpoolId }&itemType=4'">
	                                    	随机</a>
	                                    	<a href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/exercise/findMistakeItemListBySiteIdAndQuestionId?tCourseSiteId=${tCourseSite.id }&currpage=1&questionId=${questionPool.questionpoolId }&itemType=4'">
	                                    	错题</a>
	                                    </td>
	                                </tr>
                               </c:forEach>

                            </table>
                        </div>
                    </div>
                </div>
            </div>
            
            <%--<div class="course_mod f14 mb2">
                <div class=" lh40 bgg  pl30 f18 ">
                    <span class="bc">公共题库</span>
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14">
                            <table class="w100p">
                                <tr>
                                    <th class="w30p tl">名称</th>
                                    <th class="tl w10p">操作</th>                                    
                                </tr>
                               <c:forEach items="${tAssignmentQuestionpools }" var="questionPool" varStatus="i">
	                               <c:if test="${questionPool.type == 1 && questionPool.TAssignmentQuestionpool == null}">
	                                <tr>
	                                    <td>${questionPool.title }</td>
	                                    <td>
	                                    	<a href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/exercise/findOrderItemListBySiteIdAndQuestionId?tCourseSiteId=${tCourseSite.id}&currpage=1&questionId=${questionPool.questionpoolId }&itemType=4'">
	                                    	顺序</a>
	                                    	<a href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/exercise/findStochasticItemListBySiteIdAndQuestionId?tCourseSiteId=${tCourseSite.id }&currpage=1&questionId=${questionPool.questionpoolId }&itemType=4'">
	                                    	随机</a>
	                                    	<a href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/exercise/findMistakeItemListBySiteIdAndQuestionId?tCourseSiteId=${tCourseSite.id }&currpage=1&questionId=${questionPool.questionpoolId }&itemType=4'">
	                                    	错题</a>
	                                    </td>
	                                </tr>
	                               </c:if>
                               </c:forEach>

                            </table>
                        </div>
                    </div>
                </div>
            </div>
            
            
        --%></div>
    </div>
  
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>

</body>

</html>