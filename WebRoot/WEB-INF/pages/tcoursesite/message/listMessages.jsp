<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<%@ page import="java.util.Date"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  

<html>

<head>
    <title>XDU实验实训教学平台</title>
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
    <link href="${pageContext.request.contextPath}/css/tCourseSite/message/notice.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.dragsort-0.5.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.date_input.pack.js"></script>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
</head>

<body>
    <div class="course_con ma back_gray">
        <div class="course_cont r back_gray">
        	<div class="notice_cont ">
            <div class="w100p cf">
                
                <ul class="tool_box tab cf rel zx2 pt5 ">
                
                
                <li class="rel l pb5">
                    <div class="wire_btn1 bgb l ml30 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/message/listMessages?tCourseSiteId=${tCourseSite.id}&currpage=1&queryType=1&titleQuery=" class="g3">
                        <i class="fa fa-edit mr5"></i>通知列表
                        </a>
                    </div>
                </li>
                
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/message/newMessageUser?tCourseSiteId=${tCourseSite.id}" class="g3">
                        <i class="fa fa-edit mr5"></i>信息发送
                        </a>
                    </div>
                </li><%--
                
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath }/tcoursesite/message/findAllMessageUsers?tCourseSiteId=${tCourseSite.id }" class="g3">
                        <i class="fa fa-file-text-o mr5"></i>历史记录查询
                        </a>
                    </div>
                </li>
                
                --%><li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/discuss/listDiscusses?tCourseSiteId=${tCourseSite.id}&currpage=1" class="g3">
                        <i class="fa fa-comments-o mr5"></i>讨论
                        </a>
                    </div>
                </li>
                </ul>
            </div>
            </div>
            <ul class="tool_box cf rel zx2 pt5  pb10">
                <li class="rel l pb5">
                	<c:if test="${flag==1}">
                    <div class="wire_btn Lele l ml30 mt10 poi" onclick="editMessage(-1)">
                        <i class="fa fa-plus mr5"></i>通知
                    </div>
                    </c:if>
                </li>
                
            </ul>
            <c:forEach items="${tMessages}" var="tMessage"  varStatus="i">           
			<c:set var="createDate">  
    		<fmt:formatDate value="${tMessage.releaseTime.time}" pattern="yyyy-MM-dd " type="date"/>  
			</c:set> 
            <c:if test="${flag==1||(flag==0 && createDate <= nowDate)}">
            <div class="course_mod f14 mb2 poi">    
                <%--<div class="module_tit lh40 bgg  pl30 f18 ">
                --%><div class="lh40 bgg  pl30 f18 ">
                	
                    <span class="bc">${tMessage.title }</span> 
                    <c:if test="${flag==1}">
                    <i class="fa fa-edit g9 " onclick="editMessage(${tMessage.id})"></i>
                    <i class="fa fa-trash-o g9 f18 mr5 poi" onclick="deleteMessage(${tMessage.id},${tCourseSite.id})"></i>
                    </c:if>
                    <i class="fa fa-angle-double-down"></i><i class="fa fa-angle-double-up"></i>
                	<span class="f12"><fmt:formatDate value="${tMessage.releaseTime.time }" pattern="yyyy-MM-dd"></fmt:formatDate></span>
                	<span class="f12"> 发布人：${tMessage.user.username }</span>               	
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        ${tMessage.content }
                    </div> 
                </div>
            </div>
            </c:if>
            </c:forEach>
            
            
        </div>

    </div>
    <div class="window_box hide fix zx2  " id="editMessage">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p20 bb f16">新增通知</div>
            <form:form action="${pageContext.request.contextPath}/tcoursesite/message/saveTMessage?tCourseSiteId=${tCourseSite.id}" method="POST" 
            modelAttribute="tMessage" onsubmit="return submitAssignment()">
            <div class="add_con p20">
                <div class="add_module cf">
                	<form:hidden path="id" id="id"/>
                    <div class="l w100p f14 mt20">
                        公告标题
                        <form:input path="title" id="title" type="text" class=" w100p b1 br3 h30 lh30 mt5 plr5" />
                    </div>
                    <div class="cf">
                    <div class="l w100p f14 mt20 rel">
				     <label >  发布时间：</label>
                     <input type="datetime" name="releaseTime" id="releaseTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly" required="required" class="Wdate w100p b1 br3 h30 lh30 mt5 plr5 " /> 
                    </div>
                    </div>
                    <div class="l w100p f14 mt20">
                        公告内容
                        <form:textarea path="content" id="content" type="text" class=" w100p b1 br3 h30 lh30 mt5 plr5"></form:textarea>
                    </div>
                   

                </div>
                <div class="cf tc">
                    <input type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" value="保存"/>
                    <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
                        取消
                    </div>
                </div>
            </div>
            </form:form>
        </div>

    </div>
       

 <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>

<script type="text/javascript">
function editMessage(messageId) {
    //var con = '<iframe id="con" scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/tcoursesite/videoLook?folderId=' + id + '" style="width:100%;height:560px;"></iframe>'
    //$("#mediaDisplay").html(con); 
    if(messageId!=-1){
    	$("#id").val(messageId);
    	$.ajax({
    		type: "POST",
    		url: "${pageContext.request.contextPath}/tcoursesite/message/editMessage",
    		data: {'messageId':messageId},
    		dataType:'json',
    		success:function(data){
    			$.each(data,function(key,values){  
    				//alert(key+"="+values);
    				//document.getElementById(key).innerHTML=values;
    				//document.getElementById(key).innerHTML=values;
    				$("#"+key).val(""+values);
    			 }); 
    		},
    		error:function(){
    			alert("信息错误！");
    			}
    	});
    }else{
    	$("#id").val('');
    	$("#title").val('');
    	$("#content").val('');
    }
    $("#editMessage").fadeIn(100);
}

//删除通知
function deleteMessage(messageId,tCourseSiteId){
	if(confirm("是否确认删除？"))
	   {
		window.location = '${pageContext.request.contextPath}/tcoursesite/message/deleteMessage?messageId='+messageId+'&tCourseSiteId='+tCourseSiteId;
	   }
}
//保存通知
function submitAssignment(){
	var t = $("#title").val();//获取标题
	var c = $("#content").val();//获取内容
	if(t==""||c==""){
		alert("请填写标题和内容！");//入标题或内容为空弹出提示框
		return false;		
	}
	window.location = '${pageContext.request.contextPath}/tcoursesite/message/saveTMessage?tCourseSiteId=${tCourseSite.id}';
}



</script>

</body>

</html>