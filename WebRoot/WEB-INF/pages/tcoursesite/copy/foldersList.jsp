<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html>

<head>
    <title>XDU实验实训教学平台</title>
    <meta name="decorator" content="none"/>
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

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.dragsort-0.5.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
</head>

<body>
    <div>
        <div class="top ">
            <div class="header ma wh f24 cf">
                <!--<img src="images/logo1.png">-->
                <div class="sat_name l">XDU实验实训教学平台 </div>
                <div class="user f12 r">
                    <ul class="">
                        <li class="my_course l ml15"><a href="javascript:void(0);" class="wh">我的课程</a></li>
                      	<li class="user_name l ml15"><a href="javascript:void(0);" class="wh"><i class="fa fa-user fa-cir mr5"></i>${user.cname}</a></li>
                        <li class="log_out l ml15"><a href="${pageContext.request.contextPath}/pages/logout-redirect.jsp" class="wh">退出</a></li>
                    </ul>
                </div>
            </div>

        </div>
        <div class="course_info ma mb30 ">
            <div class="copy_nav cf p30">
                <div class="l tc w33p rel ">
                    <div class="f24">选择学习单元</div>
                    <div class="ma f24 h60 w60 bgb tc lh60 br30 rel zx2 wh">1</div>
                    <span class="di_l h10 w45p block bgb abs r0 zx1 t75"></span>
                </div>
                <div class="l tc w33p rel ">
                    <div class="f24">选择目标课程</div>
                    <div class="ma f24 h60 w60 bgc tc lh60 br30 rel zx2 wh">2</div>
                    <span class="di_l h10 w45p block bgc abs r0 zx1 t75"></span>
                    <span class="di_l h10 w45p block bgc abs l0 zx1 t75"></span>
                </div>
                <div class="l tc w33p rel ">
                    <div class="f24">完成复制</div>
                    <div class="ma f24 h60 w60 bgc tc lh60 br30 rel zx2 wh">1</div>
                    <span class="di_l h10 w45p block bgc abs l0 zx1 t75"></span>
                </div>
            </div>
            <div class="cf mt20">
                <div class="w50p l">
                    <div class="bbtn bgb f14 r mt10 poi tc br3 wh w80" onclick="setAllIdsString(${tCourseSite.id})">
                        下一步
                    </div>
                </div>
                <div class="w50p l">
                   <div class="bbtn bgc f14 l ml30 mt10 poi tc w80 br3" onclick="window.history.go(-1)">
                        取消
                    </div>
                </div>
            </div>
            

        </div>

    </div>

    <div class="course_con ma">
        <div class="bgw">
        <div class="module_tit lh40 bgg ">
            <input class="checkall " id="All" name='All' type="checkbox"  value="">
            <label class="mt10 " for="All"></label> 全部 <i class="fa fa-angle-double-down "></i><i class="fa fa-angle-double-up"></i>
        </div>
        <c:forEach items="${tCourseSite.wkChapters}" var="chapter" varStatus="i">
            <div class="course_mod f14  mb2">
                <div class="module_tit lh40 bgg ">
                    <input class="checkall " id="chapter${chapter.id}" name='chapter' type="checkbox"  value="${chapter.id}">
                    <label class="mt10 " for="chapter${chapter.id}"></label> ${chapter.name} <i class="fa fa-angle-double-down "></i><i class="fa fa-angle-double-up"></i>
                </div>
                <div class="module_con  mtb20">
                    <ul class="">
                    <c:forEach items="${chapter.wkFolders}" var="folder" varStatus="i">
                    	<c:if test="${folder.type == 1}">
	                        <li class="pic_list hg9 lh35 ptb10 rel ovh">
	                            <div class="cf rel zx1 z c_category">
	                                <input class="l check_box" type='checkbox' id='folder${folder.id}' name='checkname' value='${folder.id}' />
	                                <label class="l mt10" for="folder${folder.id}"></label>
	                                <i class="fa  fa-file-video-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
	                                <div class="l mlr15 cc1 c_tool poi">视频——${folder.name}</div>
	                            </div>
	                        </li>
                        </c:if>
                        <c:if test="${folder.type == 2}">
	                        <li class="pic_list hg9 lh35 ptb10 rel ovh">
	                            <div class="cf rel zx1 z c_category">
	                                <input class="l check_box" type='checkbox' id='folder${folder.id}' name='checkname' value='${folder.id}' />
	                                <label class="l mt10" for="folder${folder.id}"></label>
	                                <i class="fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
	                                <div class="l mlr15 cc1 c_tool poi">图片——${folder.name}</div>
	                            </div>
	                        </li>
                        </c:if>
                        <c:if test="${folder.type == 3}">
	                        <li class="pic_list hg9 lh35 ptb10 rel ovh">
	                            <div class="cf rel zx1 z c_category">
	                                <input class="l check_box" type='checkbox' id='folder${folder.id}' name='checkname' value='${folder.id}' />
	                                <label class="l mt10" for="folder${folder.id}"></label>
	                                <i class="fa  fa-paperclip mt5 w24 h24 brh lh24 tc l wh bgb"></i>
	                                <div class="l mlr15 cc1 c_tool poi">文件——${folder.name}</div>
	                            </div>
	                        </li>
                        </c:if>
                        <c:if test="${folder.type == 4}">
	                        <li class="pic_list hg9 lh35 ptb10 rel ovh">
	                            <div class="cf rel zx1 z c_category">
	                                <input class="l check_box" type='checkbox' id='folder${folder.id}' name='checkname' value='${folder.id}' />
	                                <label class="l mt10" for="folder${folder.id}"></label>
	                                <i class="fa  fa-file-text-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
	                                <div class="l mlr15 cc1 c_tool poi">作业——${folder.name}</div>
	                            </div>
	                        </li>
                        </c:if>
                        <c:if test="${folder.type == 5}">
	                        <li class="pic_list hg9 lh35 ptb10 rel ovh">
	                            <div class="cf rel zx1 z c_category">
	                                <input class="l check_box" type='checkbox' id='folder${folder.id}' name='checkname' value='${folder.id}' />
	                                <label class="l mt10" for="folder${folder.id}"></label>
	                                <i class="fa  fa-file-text-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
	                                <div class="l mlr15 cc1 c_tool poi">测试——${folder.name}</div>
	                            </div>
	                        </li>
                        </c:if>
                        <c:if test="${folder.type == 6}">
	                        <li class="pic_list hg9 lh35 ptb10 rel ovh">
	                            <div class="cf rel zx1 z c_category">
	                                <input class="l check_box" type='checkbox' id='folder${folder.id}' name='checkname' value='${folder.id}' />
	                                <label class="l mt10" for="folder${folder.id}"></label>
	                                <i class="fa  fa-file-text-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
	                                <div class="l mlr15 cc1 c_tool poi">考试${folder.name}</div>
	                            </div>
	                        </li>
                        </c:if>
					</c:forEach>
                    </ul>

                </div>
            	<c:forEach items="${chapter.wkLessons}" var="lesson" varStatus="i">
	                <div class="module_con   pb20 bb">
	                    <div class="lh40 pl30 f18 cf "> 
	                         <input class="checkall " id="lesson${lesson.id}" name='lesson' type="checkbox"  value="${lesson.id}">
                    		 <label class="mt10" for="lesson${lesson.id}"></label> ${lesson.title}  
	                    </div>
	                 <ul class="">
		                 <c:forEach items="${lesson.wkFolders}" var="folder" varStatus="i">
		                 	<c:if test="${folder.type == 1}">
		                        <li class="pic_list hg9 lh35 pl30 ptb10 rel ovh">
		                            <div class="cf rel zx1 z c_category">
		                                <input class="l check_box" type='checkbox' id='folder${folder.id}' name='checkname' value='${folder.id}' />
		                                <label class="l mt10" for="folder${folder.id}"></label>
		                                <i class="fa  fa-file-video-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
		                                <div class="l mlr15 cc1 c_tool poi">视频——${folder.name}</div>
		                            </div>
		                        </li>
		                    </c:if>
		                    <c:if test="${folder.type == 2}">
		                        <li class="pic_list hg9 lh35 pl30 ptb10 rel ovh">
		                            <div class="cf rel zx1 z c_category">
		                                <input class="l check_box" type='checkbox' id='folder${folder.id}' name='checkname' value='${folder.id}' />
		                                <label class="l mt10" for="folder${folder.id}"></label>
		                                <i class="fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
		                                <div class="l mlr15 cc1 c_tool poi">图片——${folder.name}</div>
		                            </div>
		                        </li>
		                    </c:if>
		                    <c:if test="${folder.type == 3}">
		                        <li class="pic_list hg9 lh35 pl30 ptb10 rel ovh">
		                            <div class="cf rel zx1 z c_category">
		                                <input class="l check_box" type='checkbox' id='folder${folder.id}' name='checkname' value='${folder.id}' />
		                                <label class="l mt10" for="folder${folder.id}"></label>
		                                <i class="fa  fa-paperclip mt5 w24 h24 brh lh24 tc l wh bgb"></i>
		                                <div class="l mlr15 cc1 c_tool poi">文件——${folder.name}</div>
		                            </div>
		                        </li>
		                    </c:if>
		                    <c:if test="${folder.type == 4}">
		                        <li class="pic_list hg9 lh35 pl30 ptb10 rel ovh">
		                            <div class="cf rel zx1 z c_category">
		                                <input class="l check_box" type='checkbox' id='folder${folder.id}' name='checkname' value='${folder.id}' />
		                                <label class="l mt10" for="folder${folder.id}"></label>
		                                <i class="fa  fa-file-text-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
		                                <div class="l mlr15 cc1 c_tool poi">测试——${folder.name}</div>
		                            </div>
		                        </li>
		                    </c:if>
		                    <c:if test="${folder.type == 5}">
		                        <li class="pic_list hg9 lh35 pl30 ptb10 rel ovh">
		                            <div class="cf rel zx1 z c_category">
		                                <input class="l check_box" type='checkbox' id='folder${folder.id}' name='checkname' value='${folder.id}' />
		                                <label class="l mt10" for="folder${folder.id}"></label>
		                                <i class="fa  fa-file-text-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
		                                <div class="l mlr15 cc1 c_tool poi">作业——${folder.name}</div>
		                            </div>
		                        </li>
		                    </c:if>
		                    <c:if test="${folder.type == 6}">
		                        <li class="pic_list hg9 lh35 pl30 ptb10 rel ovh">
		                            <div class="cf rel zx1 z c_category">
		                                <input class="l check_box" type='checkbox' id='folder${folder.id}' name='checkname' value='${folder.id}' />
		                                <label class="l mt10" for="folder${folder.id}"></label>
		                                <i class="fa  fa-file-text-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
		                                <div class="l mlr15 cc1 c_tool poi">考试+${folder.name}</div>
		                            </div>
		                        </li>
		                    </c:if>
		                    
						 </c:forEach>
	                 </ul>
	        		</div>
	        	</c:forEach>
            </div>
        </c:forEach>

			

        </div>
		<div class="cf mt20 mb20">
                <div class="w50p l">
                    <div class="bbtn bgb f14 r mt10 poi tc br3 wh w80" onclick="setAllIdsString(${tCourseSite.id})">
                        下一步
                    </div>
                </div>
                <div class="w50p l">
                   <div class="bbtn bgc f14 l ml30 mt10 poi tc w80 br3" onclick="window.history.go(-1)">
                        取消
                    </div>
                </div>
            </div>
    </div>

    <script type="text/javascript">
       
        $(".checkall").click(
            function (event) {
                event.stopPropagation();
                if (this.checked) {
                    $(this).parent().parent().find("input[name='checkname']").each(function () {
                        this.checked = true;
                    });
                    $(this).parent().parent().find("input[name='chapter']").each(function () {
                        this.checked = true;
                    });
                    $(this).parent().parent().find("input[name='lesson']").each(function () {
                        this.checked = true;
                    });
                } else {
                    $(this).parent().parent().find("input[name='checkname']").each(function () {
                        this.checked = false;
                    });
                    $(this).parent().parent().find("input[name='chapter']").each(function () {
                        this.checked = false;
                    });
                    $(this).parent().parent().find("input[name='lesson']").each(function () {
                        this.checked = false;
                    });
                }
            }
        );
        
        $(".check_box").click(
        		
        	    function (event) {
        	        if (this.checked) {
        	        	
        	        	$(this).parent().parent().parent().parent().parent().find("input[name='chapter']").each(function () {
        	                this.checked = true;
        	            });
        	        	$(this).parent().parent().parent().parent().find("input[name='lesson']").each(function () {
        	                this.checked = true;
        	            });
        	        } else {
        	        	$(this).parent().parent().parent().parent().parent().parent().find("input[name='All']").each(function () {
        	                this.checked = false;
        	            });
        	        }
        	    }
        	);
        
        $(".mt10").click(
       		function (event) {
                   event.stopPropagation();
       		}
        );
                    
        
        function setAllIdsString(tCourseSiteId){
        	var allIdsString = '';
        	$("input:checkbox[name!='all']:checked").each(function(){//遍历
        		//alert($(this).val());
        		//alert(this.id);
        		var inputId = this.id;
        		if(inputId.indexOf("chapter")>-1){
        			allIdsString = allIdsString + "!" + this.id ;
        		} 
        		if(inputId.indexOf("lesson")>-1){
        			allIdsString = allIdsString + ";" + this.id ;
        		} 
        		if(inputId.indexOf("folder")>-1){
        			allIdsString = allIdsString + "," + this.id ;
        		} 
            })
        	//alert(allIdsString);
        	window.location.href="${pageContext.request.contextPath}/tcoursesite/copy/tCourseSitesList?tCourseSiteId="+tCourseSiteId+"&allIdsString="+allIdsString+"";
        }
        
    </script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>

</body>

</html>
