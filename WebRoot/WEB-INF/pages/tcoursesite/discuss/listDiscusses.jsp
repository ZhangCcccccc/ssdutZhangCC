<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
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
    <link href="${pageContext.request.contextPath}/css/tCourseSite/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
	 <link href="${pageContext.request.contextPath}/css/tCourseSite/message/notice.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>
</head>

<body>
        <div class="course_con ma back_gray">
        <div class="course_cont r back_gray">
        	<div class="notice_cont ">
            <div class="w100p cf">
                
                <ul class="tool_box tab cf rel zx2 pt5 ">
                
                <c:if test="${flag == 1 || currflag == 1}">
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml30 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/message/listMessages?tCourseSiteId=${tCourseSite.id}&currpage=1&queryType=1&titleQuery=" class="g3">
                        <i class="fa fa-edit mr5"></i>通知列表
                        </a>
                    </div>
                </li></c:if>
                
                <c:if test="${flag == 1 || currflag == 1}">
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/message/newMessageUser?tCourseSiteId=${tCourseSite.id}" class="g3">
                        <i class="fa fa-edit mr5"></i>信息发送
                        </a>
                    </div>
                </li></c:if>
                
                <%--<li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath }/tcoursesite/message/findAllMessageUsers?tCourseSiteId=${tCourseSite.id }" class="g3">
                        <i class="fa fa-file-text-o mr5"></i>历史记录查询
                        </a>
                    </div>
                </li>
                
                --%><li class="rel l pb5">
                    <div class="wire_btn1 bgb l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/discuss/listDiscusses?tCourseSiteId=${tCourseSite.id}&currpage=1" class="g3">
                        <i class="fa fa-comments-o mr5"></i>讨论
                        </a>
                    </div>
                </li>
                </ul>
            </div>
            </div>
            <ul class="tool_box cf rel zx2 pt5 bb pb10">
                <li class="rel l pb5">
                    <div class="wire_btn Lele l ml30 mt10 poi" onclick="editDiscuss(-1)">
                        <i class="fa fa-plus mr5"></i>发表帖子
                    </div>
                </li>
            </ul>
            <div class="f14 mb2 ">
                <c:forEach items="${tDiscusses}" var="tDiscuss"  varStatus="i">
	                <div class="pl30 hf1 cf pt20 discussList ">
	                    <div class="l w5p lh30">
	                        <i class="fa bgb fa-user wh f16 h30 w30 lh30 tc br30 mt2"></i>
	                    </div>
	                    <div class="l w95p bb pb20 ">
	                        <div class=""><a class="bc hbc f18" href="#">${tDiscuss.title}</a></div>
	                        <div class="f10 gc cf lh16">
	                            <div class="l mr20">发布人：${tDiscuss.user.cname}</div>
	                            <div class="l mr20"><fmt:formatDate value="${tDiscuss.discussTime.time}" pattern="yyyy-MM-dd hh mm"></fmt:formatDate></div>
	                        </div>
	                        <div class="g3 lh30 mtb5">${tDiscuss.content}</div>
	                        <div class="cf f12">
	                            <a class="l poi mr20 hbc g3" onclick="replyDiscuss(${tDiscuss.id},${tCourseSite.id})">
	                            <i class="fa fa-comment-o f18"></i><span class="ml5">${fn:length(tDiscuss.TDiscusses)}</span></a>
	                            <%--<a class="l poi mr20 hbc g3"><i class="fa fa-eye-slash f18"></i><span class="ml5 ">未读1</span></a>
	                            --%>
	                            <c:if test="${flag==1}">
	                            <a class="l poi mr20 hbc g3" onclick="editDiscuss(${tDiscuss.id})"><i class="fa fa-edit f18 mt1"></i></a>
	                            <a class="l poi mr20 hbc g3" onclick="deleteDiscuss(${tDiscuss.id},${tCourseSite.id})"><i class="fa fa-trash-o f18"></i></a>
	                        	</c:if>
	                        </div>
	                    </div>
	                </div>
                </c:forEach>
                
            </div>
        </div>
    </div>
    <div class="window_box hide fix zx2 " id="editDiscuss">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p20 bb f16">发表帖子</div>
            <form:form action="${pageContext.request.contextPath}/tcoursesite/discuss/saveTDiscuss?tCourseSiteId=${tCourseSite.id}" method="POST" modelAttribute="tDiscuss">
	            <div class="add_con p20">
	                <div class="add_module cf f14">
	                	<form:hidden path="id" id="id"/>
	                	<form:hidden path="type" id="type" value="0"/>
	                    <div class="cf w100p  mt10 mb20">
	                        <div class="lh25">标题</div>
	                        <div class="w100p">
	                            <form:input class="w98p lh25 br3 b1 plr5" type="text" path="title" id="title"/>
	                        </div>
	
	                    </div>
	                    <div class="cf w100p  mt10 mb20">
	                        <div class="">内容</div>
	                        <div class=" w100p">
	                            <form:textarea type="text" class="w98p b1 br3 h30 lh30 mt5 plr5" path="content" id="content"></form:textarea>
	                        </div>
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
    <script type="text/javascript">
        $(function () {
            $('select').searchableSelect();
        });
        
        function editDiscuss(discussId) {
            //var con = '<iframe id="con" scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/tcoursesite/videoLook?folderId=' + id + '" style="width:100%;height:560px;"></iframe>'
            //$("#mediaDisplay").html(con); 
            if(discussId!=-1){
            	$("#id").val(discussId);
            	$.ajax({
            		type: "POST",
            		url: "${pageContext.request.contextPath}/tcoursesite/discuss/editDiscuss",
            		data: {'discussId':discussId},
            		dataType:'json',
            		success:function(data){
            			$.each(data,function(key,values){  
            				//alert(key+"="+values);
            				//document.getElementById(key).innerHTML=values;
            				document.getElementById(key).innerHTML=values;
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
            $("#editDiscuss").fadeIn(100);
        }
        
      //删除讨论
        function deleteDiscuss(discussId,tCourseSiteId){
        	if(confirm("是否确认删除？"))
        	   {
        		window.location = '${pageContext.request.contextPath}/tcoursesite/discuss/deleteDiscuss?discussId='+discussId+'&tCourseSiteId='+tCourseSiteId;
        	   }
        }
      
        //进入回复
        function replyDiscuss(discussId,tCourseSiteId){
       		window.location = '${pageContext.request.contextPath}/tcoursesite/discuss/tDiscuss?discussId='+discussId+'&tCourseSiteId='+tCourseSiteId+'&currpage=1';
        }
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>

</body>

</html>