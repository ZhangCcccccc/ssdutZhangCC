<!DOCTYPE html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />

<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Document</title>
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

     <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>
    
 
 
<!--[if lte IE 8]>
<link href="css/ie8.css" rel="stylesheet" type="text/css"/>
<![endif]-->
<!--[if IE]>
<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<![endif]-->
          <!-- ueditor编辑器 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script> 
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
    function selectAll(){  
    if ($("#check_all").attr("checked")) {  
        $(":checkbox").attr("checked", true);  
    } else {  
        $(":checkbox").attr("checked", false);  
    }  
	}
    function findMessageUsers(tMessageId){
    	$.ajax({
    		async:false,
    		url:"${pageContext.request.contextPath}/tcoursesite/message/findAllMessageUsersBytMessageId?tMessageId="+tMessageId,
           	type:"POST",
           	success:function(data){ 
           		var _tr = "";
           		$.each(data,function(key,values){
           			_tr +=  "<tr>"+"<td>";
           			_tr +="<input type='text' value= '"+values+"'/>";
           			_tr +="</td>"+"</tr>";
           		})
           		
           	 	$("#MessageUsersDetail").html(_tr);
           	}
    	})
    	$("#MessageUsers").fadeIn(100);
    } 	
    	
</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css"/> 
   
</head>
<body>
<!-- 装饰页面-->
 <div class="course_con ma back_gray" >
        <div class="course_cont r back_gray">
        <div class="notice_cont mb10">
            <div class=" w100p ">
                <ul class="tool_box tab cf rel zx2 pt5 ">
                
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml30 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/message/listMessages?tCourseSiteId=${tCourseSite.id}&currpage=1&queryType=1&titleQuery=" class="g3">
                        <i class="fa fa-edit mr5"></i>通知列表
                        </a>
                    </div>
                </li>
                
                <li class="rel l pb5">
                    <div class="wire_btn l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/message/newMessageUser?tCourseSiteId=${tCourseSite.id}" class="g3">
                        <i class="fa fa-edit mr5"></i>信息发送
                        </a>
                    </div>
                </li>
                <%--<li class="rel l pb5">
                    <div class="wire_btn bgb l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath }/tcoursesite/message/findAllMessageUsers?tCourseSiteId=${tCourseSite.id }" class="g3">
                        <i class="fa fa-comments-o mr5"></i>历史记录查询
                        </a>
                    </div>
                </li>
                
                --%><li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/discuss/listDiscusses?tCourseSiteId=${tCourseSite.id}&currpage=1" class="g3">
                        <i class="fa fa-file-text-o mr5"></i>讨论
                        </a>
                    </div>
                </li>
                </ul>
            </div>
        </div>
        <div class="tool_bar cf z2">
            <%--<div class="lh40 bgg mb15 pl30 f18 ">
                    <span class="bc">历史记录查询</span>  
                </div>
            <div  class="l mt10"><input type="checkbox" id="check_all" onclick="selectAll()" /><label for="check_all"></label>
            </div>
            --%><div class="wire_btn Lele l ml30 mt10 poi bgw">
                                删除
            </div>
            <div class="rel dropdown pb5">
                <div class="wire_btn drop_box l ml20 mt10 cf ovh poi">
                        <span class="l">标记为:</span>
                        <span class="l ml5 pl5 bl drop_btn cc1">已读</span><i class="fa fa-angle-double-down ml5 gc"></i>
                    </div>
                    <ul class="dropdown_list drop_list z300 mt5 bs5 b1 abs br3 bgw ptb5 hide le180 ">
                        <li class="video lh24 plr10 poi hg9">已读</li>
                        <li class="video lh24 plr10 poi hg9">未读</li>
                        <li class="material lh24 plr10 poi hg9">全部设为已读</li>
                        <li class="pic lh24 plr10 poi hg9">待办</li>
                    </ul>
            </div>
        </div>
        <div class="gu0">  
        </div>
        <div class="bcg mb20">
            <div class="f14">
             <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14">                      
                            <table class="w100p" id="messageList">                           	                          
                                <tr>
                                	<th class="w10p tl">标题</th>
                                    <th class="w10p tl ">发送时间</th>
                                    <th class="tl w10p">操作</th>  
                                </tr>
                                <c:forEach items="${tMessages}" var="tMessage" varStatus="i">
                                <tr>
                                	<td>${tMessage.title}
		                    		</td>		                    		
                                   <td> <fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${tMessage.releaseTime.time }"/>
                                   </td>
                                    <td>                                  
		                                <input type="button" value="查看发送对象" onclick="findMessageUsers(${tMessage.id})">
									</td>								
                                </tr>
                                </c:forEach>
                            </table>
                            
                        </div>
                    </div>
                </div>              
            </div>
        </div>
     
        </div>
    </div>
    </div>
  <div class="window_box hide fix zx2  " id="MessageUsers" style="z-index:300;">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>                        
                            <table class="w100p" >                            	                             
                                <tr>
                                <th class="w10p tl">
		     		 				学生姓名</th>
                                </tr>                               
                                <tr id="MessageUsersDetail">
                                </tr>                              
                            </table>
                            
                            <div class="cf tc">                   	
                </div>
             </div>
         </div>   
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
</body>
</html>