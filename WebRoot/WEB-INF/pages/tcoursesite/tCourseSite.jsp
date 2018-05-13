<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<%--<%@ include file="/WEB-INF/sitemesh-decorators/course.jsp" %>
--%><html>

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
    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tCourseSite/date.css" />
   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
    
    
    <!-- 上传插件的css和js -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>
   	
</head>

<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
    <div class="course_con ma">
        <div class="course_cont r">
            <ul class="tool_box cf rel zx2 pt5 pb10">
                <li class="rel l">
                    <div class="wire_btn Lele l ml30 mt10 poi" onclick="newWkLesson(-1)">
                        <i class="fa fa-plus mr5"></i>学习单元
                    </div>
                </li>
                <li class="rel l dropdown pb5">
                    <div class="wire_btn l ml20 mt10 poi">
                        <i class="fa fa-plus mr5"></i>学习活动
                    </div>
                    <ul class="dropdown_list le20 mt5 bs5 b1 abs br3 bgw ptb5 hide ">
                        <li class="video lh24 plr10 poi hg9 Lele">视频</li>
                        <li class="material lh24 plr10 poi hg9 Lele">参考文件</li>
                        <li class="pic lh24 plr10 poi hg9 Lele">图片</li>
                        <li class="homework lh24 plr10 poi hg9 Lele">作业</li>
                        <li class="exam lh24 plr10 poi hg9 Lele">考试</li>
                        <li class="exam lh24 plr10 poi hg9 Lele">测试</li>
                    </ul>
                </li>
                <li class="rel l dropdown  pb5">
                    <div class="wire_btn drop_box l ml20 mt10 cf ovh poi">
                        <span class="l">课程内容:</span>
                        <span class="l ml5 pl5 bl drop_btn cc1">全部</span><i class="fa fa-angle-double-down ml5 gc"></i>
                    </div>
                    <ul class="dropdown_list drop_list mt5 bs5 b1 abs br3 bgw ptb5 hide le85 ">
                        <li class="video lh24 plr10 poi hg9">全部</li>
                        <li class="video lh24 plr10 poi hg9">视频</li>
                        <li class="material lh24 plr10 poi hg9">参考文件</li>
                        <li class="pic lh24 plr10 poi hg9">图片</li>
                        <li class="homework lh24 plr10 poi hg9">作业</li>
                        <li class="exam lh24 plr10 poi hg9">考试</li>
                    </ul>
                </li>
            </ul>
        <c:forEach items="${tCourseSite.wkChapters}" var="chapter" varStatus="i">
        	<c:if test="${chapter.type == moduleType}">
	            <div class="course_mod mb1 f14">
	                <div class="module_tit lh40 bgg  pl30 ">${chapter.name}
	   					<i class="fa fa-edit g9 f14 mr5 poi" onclick="newWkChapter(${chapter.id})"></i>
	   					<i class="fa fa-angle-double-down g9 f14 mr5 poi"></i>
	   					<i class="fa fa-angle-double-up g9 f14 mr5 poi"></i> 
	   					<i class="fa fa-plus g9 f14 mr5 poi"></i> 
	   					<i class="fa fa-trash-o g9 f14 mr5 poi" onclick="deleteWkChapter(${chapter.id},${tCourseSite.id},${moduleType})"></i>
	                </div>
	                <div class="module_con pb20 bb">
	                    <ul class="gdrag_list">
	                    	<c:forEach items="${chapter.wkFolders}" var="folder" varStatus="i">
	                    		<c:if test="${folder.type == 2}">
			                        <li class="pic_list hg9 lh35 ptb10 rel ovh">
			                            <div class="cf pl30 rel zx1 z c_category">
			                                <i class="fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
			                                <div class="l mlr15 cc1 c_tool poi">图片——${folder.name}</div>
			                                <span class="c_tool w50 tc file_op btn_l mt10 bgb f10 l wh poi"> 查看图片</span>
			
			                                <i class="fa fa-trash-o g9 f18 r mr30  lh35 poi c_tool hide"  onclick="deleteWkFolder(${folder.id},${tCourseSite.id},${moduleType})"></i>
			                                <i class="fa fa-edit g9 f18 r mr10  lh35 poi c_tool hide" ></i>
			                            </div>
			                            <!--附件-->
			                            <div class="accessory pb10 hide">
			                                <table class="accessorytable">
			                                    <tr>
			                                        <th>附件名</th>
			                                        <th>大小</th>
			                                        <th class="w50">操作</th>
			                                    </tr>
		                                    <c:forEach items="${folder.uploads }" var="image" varStatus="i">
			                                    <tr>
			                                        <td>
			                                            <i class="fa fa-file-picture-o f14 bc c_tool poi mr10"></i><span class="c_tool poi">${image.name}</span>
			                                        </td>
			                                        <td>${image.size}</td>
			                                        <td><i class="fa fa-download c_tool poi" onclick="downloadFile(${image.id})"></i>
			                                            <i class="fa fa-eye c_tool poi ml10" onclick="imageLook('${image.url}','${image.name}')"></i>
			                                        </td>
			                                    </tr>
		                                    </c:forEach>
			                                </table>
			                            </div>
			                        </li>
		                        </c:if>
		                        <c:if test="${folder.type == 3}">
			                        <li class="file_list hg9 lh35 ptb10 rel ovh">
			                            <div class="cf pl30 rel zx1 z c_category">
			                                <i class="fa fa-paperclip mt5 w24 h24 brh lh24 tc l wh bgb"></i>
			                                <div class="l mlr15 cc1 c_tool poi">参考文件——${folder.name}</div>
			                                <span class="c_tool w50 tc file_op btn_l mt10 bgb f10 l wh poi"> 查看文件</span>
			                                <i class="fa fa-trash-o g9 f18 r mr30 lh35 c_tool hide poi"  onclick="deleteWkFolder(${folder.id},${tCourseSite.id},${moduleType})"></i>
			                                <i class="fa fa-edit g9 f18 r mr10 lh35 c_tool hide poi" onclick="newDocumentFolder('chapter',${chapter.id},${folder.id})"></i>
			                            </div>
			                            <!--附件-->
			                            <div class="accessory ptb10 hide">
			                                <table class="accessorytable">
			                                    <tr>
			                                        <th>附件名</th>
			                                        <th>大小</th>
			                                        <th class="w50">操作</th>
			                                    </tr>
		                                    <c:forEach items="${folder.uploads }" var="document" varStatus="i">
			                                    <tr>
			                                        <td>
			                                            <i class="fa fa-file-picture-o f14 bc c_tool poi"></i><span class="c_tool poi">${document.name}</span>
			                                        </td>
			                                        <td>${document.size}</td>
			                                        <td><i class="fa fa-download c_tool poi" onclick="downloadFile(${document.id})"></i>
			                                        </td>
			                                    </tr>
		                                    </c:forEach>
			                                </table>
			                            </div>
			                        </li>
		                        </c:if>
		                        <c:if test="${folder.type == 1}">
			                        <li class="video_list hg9 lh35 rel ptb10 ovh">
			                            <div class="cf pl30  rel zx1 z c_category ">
			                                <i class="fa fa-file-video-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
			                                <div class="l mlr15 cc1 c_tool poi">视频——${folder.name}</div>
			                                <span class="c_tool w50 tc  btn_l mt10 bgb f10 l wh poi" onclick="videoLook(${folder.id},${moduleType})"> 查看视频</span>
			                                <div class="accessory_info mlr15 l f12 g9"> 视频大小为
				                                 <c:forEach items="${folder.uploads }" var="video" varStatus="i">
				                                 	${video.size }
				                                 </c:forEach>
			                                </div>
			                                <i class="fa fa-trash-o g9 f18 r mr30  lh35 c_tool hide poi"  onclick="deleteWkFolder(${folder.id},${tCourseSite.id},${moduleType})"></i>
			                                <i class="fa fa-edit g9 f18 r mr10  lh35 c_tool hide poi" onclick="newVideoFolder('chapter',${chapter.id},${folder.id})"></i>
			                            </div>
			
			                        </li>
		                        </c:if>
		                        <c:if test="${folder.type == 6}">
			                        <li class="exam_list hg9 lh35 rel ptb10 ovh">
			                            <div class=" cf pl30  rel zx1 z c_category">
			                                <i class="fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
			                                <div class="l mlr15 cc1 c_tool poi">考试——${folder.name}</div>
			                                <div class="accessory_info l f12 g9"> 
				                                 <c:forEach items="${folder.TAssignments}" var="test" varStatus="i">
				                                 	<c:if test="${now < test.TAssignmentControl.startdate.time}">
				                                 		开放时间为
				                                 		<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${test.TAssignmentControl.startdate.time }"/>
				                                 	</c:if>
				                                 	<c:if test="${now > test.TAssignmentControl.startdate.time && now < test.TAssignmentControl.duedate.time}">
				                                 		结束时间为
				                                 		<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${test.TAssignmentControl.duedate.time }"/>
				                                 	</c:if>
				                                 	<c:if test="${now > test.TAssignmentControl.duedate.time}">
				                                 		<span class="c_tool w50 tc expired  mt10 bgc f10 l poi"> 已过期</span>
				                                 	</c:if>
				                                 </c:forEach>
			                                </div>
			                                <i class="fa fa-trash-o g9 f18 r mr30  lh35 c_tool hide poi"  onclick="deleteWkFolder(${folder.id},${tCourseSite.id},${moduleType})"></i>
			                                <i class="fa fa-edit g9 f18 r mr10  lh35 c_tool hide poi"></i>
			                            </div>
			
			                        </li>
		                        </c:if>
		                        <c:if test="${folder.type == 5}">
			                        <li class="exam_list hg9 lh35 rel ptb10 ovh">
			                            <div class=" cf pl30  rel zx1 z c_category">
			                                <i class="fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
			                                <div class="l mlr15 cc1 c_tool poi">测试——${folder.name}</div>
			                                <div class="accessory_info l f12 g9"> 
				                                 <c:forEach items="${folder.TAssignments}" var="test" varStatus="i">
				                                 	<c:if test="${now < test.TAssignmentControl.startdate.time}">
				                                 		开放时间为
				                                 		<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${test.TAssignmentControl.startdate.time }"/>
				                                 	</c:if>
				                                 	<c:if test="${now > test.TAssignmentControl.startdate.time && now < test.TAssignmentControl.duedate.time}">
				                                 		结束时间为
				                                 		<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${test.TAssignmentControl.duedate.time }"/>
				                                 	</c:if>
				                                 	<c:if test="${now > test.TAssignmentControl.duedate.time}">
				                                 		<span class="c_tool w50 tc expired  mt10 bgc f10 l poi"> 已过期</span>
				                                 	</c:if>
				                                 </c:forEach>
			                                </div>
			                                <i class="fa fa-trash-o g9 f18 r mr30  lh35 c_tool hide poi"  onclick="deleteWkFolder(${folder.id},${tCourseSite.id},${moduleType})"></i>
			                                <i class="fa fa-edit g9 f18 r mr10  lh35 c_tool hide poi"></i>
			                            </div>
			
			                        </li>
								</c:if>
								<c:if test="${folder.type == 4}">
			                        <li class="homework_list hg9 lh35 rel ptb10 ovh">
			                            <div class="cf pl30  rel zx1 z c_category">
			                                <i class="fa fa-file-text-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
			                                <div class="l mlr15 cc1 c_tool poi">作业——${folder.name}</div>
			                                <div class="accessory_info l f12 g9"> 
				                                 <c:forEach items="${folder.TAssignments}" var="test" varStatus="i">
				                                 	<c:if test="${now < test.TAssignmentControl.startdate.time}">
				                                 		开放时间为
				                                 		<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${test.TAssignmentControl.startdate.time }"/>
				                                 	</c:if>
				                                 	<c:if test="${now > test.TAssignmentControl.startdate.time && now < test.TAssignmentControl.duedate.time}">
				                                 		结束时间为
				                                 		<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${test.TAssignmentControl.duedate.time }"/>
				                                 	</c:if>
				                                 	<c:if test="${now > test.TAssignmentControl.duedate.time}">
				                                 		<span class="c_tool w50 tc expired  mt10 bgc f10 l poi"> 已过期</span>
				                                 	</c:if>
				                                 </c:forEach>
			                                </div>
			                                <i class="fa fa-trash-o g9 f18 r mr30  lh35 c_tool hide poi"  onclick="deleteWkFolder(${folder.id},${tCourseSite.id},${moduleType})"></i>
			                                <i class="fa fa-edit g9 f18 r mr10  lh35 c_tool hide poi"></i>
			                            </div>
			                        </li>
		                        </c:if>
							</c:forEach>
	                    </ul>
	                    <div class="add_mod pl30 pt20 pr20 ">
	                        <div class="add_box bd1 bgg hg9 cf lh30">
	                            <div class="l p20 bgc"> <i class="fa fa-plus mr5"></i>新增学习活动</div>
	
	                            <div class="wire_btn Lele l ml30 mt20 poi bgw" onclick="newVideoFolder('chapter',${chapter.id},-1)">
	                                视频
	                            </div>
	                            <div class="wire_btn Lele l ml30 mt20 poi bgw" onclick="newDocumentFolder('chapter',${chapter.id},-1)">
	                                参考文件
	                            </div>
	                            <div class="wire_btn Lele l ml30 mt20 poi bgw" onclick="newImageFolder('chapter',${chapter.id},-1)">
	                                图片
	                            </div>
	                            <div class="wire_btn Lele l ml30 mt20 poi bgw" onclick="newAssignmentFolder('chapter',${chapter.id},-1)">
	                                作业
	                            </div>
	                            <div class="wire_btn Lele l ml30 mt20 poi bgw" onclick="newTestFolder('chapter',${chapter.id},-1)">
	                                考试
	                            </div>
	                            <div class="wire_btn Lele l ml30 mt20 poi bgw" onclick="newExamFolder('chapter',${chapter.id},-1)">
	                                测试
	                            </div>
	                        </div>
	                        <!--<div class="mod_classify bgg p20 b1 cf">
	                            
	                        </div>-->
	                    </div>
	                </div>
	            	<c:forEach items="${chapter.wkLessons}" var="lesson" varStatus="i">
		                <div class="module_con   pb20 bb">
		                    <div class="lh60 pl30 f18 cf"> 
		                         ${lesson.title} 
		                         <i class="fa fa-edit g9 f14 mr5 poi" onclick="newWkLesson(${lesson.id} )"></i>
		                         <i class="fa fa-angle-double-down g9 f14 mr5 poi"></i>
		                         <i class="fa fa-angle-double-up g9 f14 mr5 poi"></i> 
		                         <i class="fa fa-plus g9 f14 mr5 poi"></i> 
		                         <i class="fa fa-trash-o g9 f14 mr5 poi" onclick="deleteWkLesson(${lesson.id},${tCourseSite.id},${moduleType})"></i>
		                    	<%--<p class="f14">教学目标：${lesson.learningTarget}</p>
		                    	<p class="f14">备注：${lesson.remarks}</p>
		                    --%></div>
		                    <ul class="gdrag_list">
		                    	<c:forEach items="${lesson.wkFolders}" var="folder" varStatus="i">
		                    		<c:if test="${folder.type == 2}">
				                        <li class="pic_list hg9 lh35 ptb10 rel ovh">
				                            <div class="cf pl30 rel zx1 z c_category">
				                                <i class="fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
				                                <div class="l mlr15 cc1 c_tool poi">图片——${folder.name}</div>
				                                <span class="c_tool w50 tc file_op btn_l mt10 bgb f10 l wh poi"> 查看图片</span>
				
				                                <i class="fa fa-trash-o g9 f18 r mr30  lh35 poi c_tool hide"  onclick="deleteWkFolder(${folder.id},${tCourseSite.id},${moduleType})"></i>
				                                <i class="fa fa-edit g9 f18 r mr10  lh35 poi c_tool hide"></i>
				                            </div>
				                            <!--附件-->
				                            <div class="accessory pb10 hide">
				                                <table class="accessorytable">
				                                    <tr>
				                                        <th>图片名</th>
				                                        <th>大小</th>
				                                        <th class="w50">操作</th>
				                                    </tr>
					                            <c:forEach items="${folder.uploads }" var="image" varStatus="i">
				                                    <tr>
				                                        <td>
				                                            <i class="fa fa-file-picture-o f14 bc c_tool poi"></i><span class="c_tool poi">${image.name}</span>
				                                        </td>
				                                        <td>${image.size}</td>
				                                        <td><i class="fa fa-download c_tool poi" onclick="downloadFile(${image.id})"></i>
				                                            <i class="fa fa-eye c_tool poi ml10" onclick="imageLook('${image.url}','${image.name}')"></i>
				                                        </td>
				                                    </tr>
			                                    </c:forEach>
				                                </table>
				                            </div>
				                        </li>
			                        </c:if>
			                        <c:if test="${folder.type == 3}">
				                        <li class="file_list hg9 lh35 ptb10 rel ovh">
				                            <div class="cf pl30 rel zx1 z c_category">
				                                <i class="fa fa-paperclip mt5 w24 h24 brh lh24 tc l wh bgb"></i>
				                                <div class="l mlr15 cc1 c_tool poi">参考文件——${folder.name}</div>
				                                <span class="c_tool w50 tc file_op btn_l mt10 bgb f10 l wh poi"> 查看文件</span>
				                                <i class="fa fa-trash-o g9 f18 r mr30 lh35 c_tool hide poi"  onclick="deleteWkFolder(${folder.id},${tCourseSite.id},${moduleType})"></i>
				                                <i class="fa fa-edit g9 f18 r mr10 lh35 c_tool hide poi" ></i>
				                            </div>
				                            <!--附件-->
				                            <div class="accessory ptb10 hide">
				                                <table class="accessorytable">
				                                    <tr>
				                                        <th>附件名</th>
				                                        <th>大小</th>
				                                        <th class="w50">操作</th>
				                                    </tr>
			                                    <c:forEach items="${folder.uploads }" var="document" varStatus="i">
				                                    <tr>
				                                        <td>
				                                            <i class="fa fa-file-picture-o f14 bc c_tool poi"></i><span class="c_tool poi">${document.name}</span>
				                                        </td>
				                                        <td>${document.size}</td>
				                                        <td><i class="fa fa-download c_tool poi" onclick="downloadFile(${document.id})"></i>
				                                        </td>
				                                    </tr>
			                                    </c:forEach>
				                                </table>
				                            </div>
				                        </li>
			                        </c:if>
			                        <c:if test="${folder.type == 1}">
				                        <li class="video_list hg9 lh35 rel ptb10 ovh">
				                            <div class="cf pl30  rel zx1 z c_category ">
				                                <i class="fa fa-file-video-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
				                                <div class="l mlr15 cc1 c_tool poi">视频——${folder.name}</div>
				                                <span class="c_tool w50 tc  btn_l mt10 bgb f10 l wh poi" onclick="videoLook(${folder.id},${moduleType})"> 查看视频</span>
				                                <div class="accessory_info l f12 g9"> 视频大小为
					                                 <c:forEach items="${folder.uploads }" var="video" varStatus="i">
					                                 	${video.size }
					                                 </c:forEach>
				                                </div>
				
				                                <i class="fa fa-trash-o g9 f18 r mr30  lh35 c_tool hide poi"  onclick="deleteWkFolder(${folder.id},${tCourseSite.id},${moduleType})"></i>
				                                <i class="fa fa-edit g9 f18 r mr10  lh35 c_tool hide poi"></i>
				                            </div>
				
				                        </li>
			                        </c:if>
			                        <c:if test="${folder.type == 6}">
				                        <li class="exam_list hg9 lh35 rel ptb10 ovh">
				                            <div class=" cf pl30  rel zx1 z c_category">
				                                <i class="fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
				                                <div class="l mlr15 cc1 c_tool poi">考试——${folder.name}</div>
				                                <div class="accessory_info l f12 g9"> 
					                                <c:forEach items="${folder.TAssignments}" var="test" varStatus="i">
					                                 	<c:if test="${now < test.TAssignmentControl.startdate.time}">
					                                 		开放时间为
					                                 		<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${test.TAssignmentControl.startdate.time }"/>
					                                 	</c:if>
					                                 	<c:if test="${now > test.TAssignmentControl.startdate.time && now < test.TAssignmentControl.duedate.time}">
					                                 		结束时间为
					                                 		<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${test.TAssignmentControl.duedate.time }"/>
					                                 	</c:if>
					                                 	<c:if test="${now > test.TAssignmentControl.duedate.time}">
					                                 		<span class="c_tool w50 tc expired  mt10 bgc f10 l poi"> 已过期</span>
					                                 	</c:if>
					                                 </c:forEach>
				                                </div>
				                                
				                                <i class="fa fa-trash-o g9 f18 r mr30  lh35 c_tool hide poi"  onclick="deleteWkFolder(${folder.id},${tCourseSite.id},${moduleType})"></i>
				                                <i class="fa fa-edit g9 f18 r mr10  lh35 c_tool hide poi"></i>
				                            </div>
				
				                        </li>
			                        </c:if>
			                        <c:if test="${folder.type == 5}">
				                        <li class="exam_list hg9 lh35 rel ptb10 ovh">
				                            <div class=" cf pl30  rel zx1 z c_category">
				                                <i class="fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
				                                <div class="l mlr15 cc1 c_tool poi">测试——${folder.name}</div>
				                                <div class="accessory_info l f12 g9"> 
					                                 <c:forEach items="${folder.TAssignments}" var="test" varStatus="i">
					                                 	<c:if test="${now < test.TAssignmentControl.startdate.time}">
					                                 		开放时间为
					                                 		<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${test.TAssignmentControl.startdate.time }"/>
					                                 	</c:if>
					                                 	<c:if test="${now > test.TAssignmentControl.startdate.time && now < test.TAssignmentControl.duedate.time}">
					                                 		结束时间为
					                                 		<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${test.TAssignmentControl.duedate.time }"/>
					                                 	</c:if>
					                                 	<c:if test="${now > test.TAssignmentControl.duedate.time}">
					                                 		<span class="c_tool w50 tc expired  mt10 bgc f10 l poi"> 已过期</span>
					                                 	</c:if>
					                                 </c:forEach>
				                                </div>
				                                <i class="fa fa-trash-o g9 f18 r mr30  lh35 c_tool hide poi"  onclick="deleteWkFolder(${folder.id},${tCourseSite.id},${moduleType})"></i>
				                                <i class="fa fa-edit g9 f18 r mr10  lh35 c_tool hide poi"></i>
				                            </div>
				
				                        </li>
									</c:if>
									<c:if test="${folder.type == 4}">
				                        <li class="homework_list hg9 lh35 rel ptb10 ovh">
				                            <div class="cf pl30  rel zx1 z c_category">
				                                <i class="fa fa-file-text-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
				                                <div class="l mlr15 cc1 c_tool poi">作业——${folder.name}</div>
				                                <div class="accessory_info l f12 g9">
													<c:forEach items="${folder.TAssignments}" var="test" varStatus="i">
					                                 	<c:if test="${now < test.TAssignmentControl.startdate.time}">
					                                 		开放时间为
					                                 		<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${test.TAssignmentControl.startdate.time }"/>
					                                 	</c:if>
					                                 	<c:if test="${now > test.TAssignmentControl.startdate.time && now < test.TAssignmentControl.duedate.time}">
					                                 		结束时间为
					                                 		<fmt:formatDate type="date"  pattern="yy-MM-dd HH:mm" value="${test.TAssignmentControl.duedate.time }"/>
					                                 	</c:if>
					                                 	<c:if test="${now > test.TAssignmentControl.duedate.time}">
					                                 		<span class="c_tool w50 tc expired  mt10 bgc f10 l poi"> 已过期</span>
					                                 	</c:if>
					                                 </c:forEach>
					                             </div>
				                                <i class="fa fa-trash-o g9 f18 r mr30  lh35 c_tool hide poi"  onclick="deleteWkFolder(${folder.id},${tCourseSite.id},${moduleType})"></i>
				                                <i class="fa fa-edit g9 f18 r mr10  lh35 c_tool hide poi"></i>
				                            </div>
				                        </li>
			                        </c:if>
								</c:forEach>
		                    </ul>
		                    <div class="add_mod pl30 pt20 pr20 ">
		                        <div class="add_box bd1 bgg hg9 cf lh30">
		                            <div class="l p20 bgc"> <i class="fa fa-plus mr5"></i>新增学习活动</div>
		
		                            <div class="wire_btn Lele l ml30 mt20 poi bgw" onclick="newVideoFolder('lesson',${lesson.id},-1)">
		                               	 视频
		                            </div>
		                            <div class="wire_btn Lele l ml30 mt20 poi bgw" onclick="newDocumentFolder('lesson',${lesson.id},-1)">
		                                	参考文件
		                            </div>
		                            <div class="wire_btn Lele l ml30 mt20 poi bgw" onclick="newImageFolder('lesson',${lesson.id},-1)">
		                               	 图片
		                            </div>
		                            <div class="wire_btn Lele l ml30 mt20 poi bgw" onclick="newAssignmentFolder('lesson',${lesson.id},-1)">
		                               	 作业
		                            </div>
		                            <div class="wire_btn Lele l ml30 mt20 poi bgw" onclick="newTestFolder('lesson',${lesson.id},-1)">
		                               	 考试
		                            </div>
		                            <div class="wire_btn Lele l ml30 mt20 poi bgw" onclick="newExamFolder('lesson',${lesson.id},-1)">
	                                	测试
	                            	</div>
		                        </div>
		                        <!--<div class="mod_classify bgg p20 b1 cf">
		                            
		                        </div>-->
		                    </div>
		                </div>
	                </c:forEach>
	            </div>
            </c:if>
        </c:forEach>
            
            
            
            <div class="add_section p20 bgg mt20">
                <div class="add_box bd1c bgc1 p10 cf lh30 f14 c_c1 poi Lele" onclick="newWkChapter(-1)">
                    <i class="fa fa-plus mr5 c_c"></i>新增章节
                </div>
            </div>
        </div>

    </div>
    <div class="window_box hide fix zx2  " id="newWkLesson">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p20 bb f16">新增学习单元</div>
            <div class="add_con p20">
            <form:form action="${pageContext.request.contextPath}/tcoursesite/saveLesson?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}" method="POST" modelAttribute="wkLesson">
                <div class="add_module cf">
                	<form:input path="id" id="lessonId" style="display:none;"/>
                	<form:input path="seq" id="lessonSeq" style="display:none;"/>
                	<div class="cf">
	                    <div class="l w45p f14 mt20">
	                        	名称
	                        <form:input type="text" path="title" id="lessonIdTitle" class=" w100p b1 br3 h30 lh30 mt5 plr5" />
	                    </div>
	                    <div class="r w45p f14 mt20" >
	                        	章节
	                        <form:select path="wkChapter.id" id="lessonChapterId" class="w100p lh25 br3 b1" >
					       		<c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
					       			<c:if test="${chap.type == moduleType}">
					       				<form:option value="${chap.id}">${chap.name}</form:option>
					       			</c:if>
					       		</c:forEach>
					       	</form:select>
	                    </div>
	                    </div>
                    <div class="cf">
	                    <div class="l w45p f14 mt20">
	                      	  学习目标
	                        <form:textarea type="text" path="learningTarget" id="lessonLearningTarget" class=" w100p b1 br3 h30 lh30 mt5 plr5"></form:textarea>
	                    </div>
	                    <div class="r w45p f14 mt20">
	                       	 备注
	                        <form:textarea type="text" path="remarks" id="lessonRemarks" class=" w100p b1 br3 h30 lh30 mt5 plr5"></form:textarea>
	                    </div>
                    </div>

                </div>
                <div class="cf tc">
                	<input type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" value="保存"/>
                    <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
                        取消
                    </div>
                </div>
            </form:form>
            </div>
        </div>

    </div>
    
    <div class="window_box hide fix zx2  " id="newWkChapter">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p20 bb f16">新增章节</div>
            <div class="add_con p20">
            <form:form action="${pageContext.request.contextPath}/tcoursesite/saveChapter?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}" method="POST" modelAttribute="wkChapter">
                <div class="add_module cf">
                	<form:input path="id" id="chapterId" style="display:none;"/>
                	<form:input path="seq" id="chapterSeq" style="display:none;"/>
                	<form:input path="TCourseSite.id" id="chapterId" style="display:none;" value="${tCourseSite.id}"/>
                    <div class="l w45p f14 mt20">
                        名称
                        <form:input type="text" path="name" id="chapterName" class=" w100p b1 br3 h30 lh30 mt5 plr5" />
                    </div>
                    

                </div>
                <div class="cf tc">
                	<input type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" value="保存"/>
                    <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
                        取消
                    </div>
                </div>
            </form:form>
            </div>
        </div>

    </div>

 
 <!--添加视频开始-->
	<div id="editVideoFolder" class="window_box hide fix zx2  ">
	<div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p10 bb f16">新增学习活动</div>
            <div class="add_con p10">
    <form:form action="${pageContext.request.contextPath}/tcoursesite/saveVideoFolder?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}" method="POST" modelAttribute="videoFolder">
    	<div class="tab_box">
    	<ul class="tab cf nav-pills nav-pills-mini mbs file-chooser-tabs">
        	<li class="rel l pb5" onclick="uploadVideo()" id="uploadVideo"><a class="wire_btn bgc l mt10 poi">上传视频</a></li>
        	<li class="rel l pb5" onclick="importVideo()" id="importVideo"><a class="wire_btn  l ml10 mt10 poi">导入网络视频</a></li>
		</ul>
      	<!--上传视频的div  -->
      	<div class="tab_list file-chooser-uploader" >
 			<input type="file" name="file" id="uploadify" />
 			<form:input path="id" id="videoFolderId" style="display:none;"/>
 			<input type="text" class="hide" name="videoId" id="videoId" />
 			<div class="w55p ma  red" >注意：请上传小于50M的MP4格式视频（转码时转成<b>H.264/AVC</b>格式）(请使用谷歌浏览器)</div>
 			<div class="w55p ma"><lable>当前视频：</lable><lable id="videoName">无</lable></div>
        </div>
        <!-- 导入视频的div -->	     		
        <div class=" tab_list input-group cf hide" >
        	<form:input path="videoUrl" class="form-control l w45p lh25 br3 b1" id="videoUrl" placeholder="只支持优酷" />
        	
            <button type="button" onclick="convert_url()" class="lh25 l br3 b1 ml10">导入</button>
        	
       	</div>
       	</div>
       	<div class="cf">
       	<div class="l w45p">
       	名字：<form:input path="name" id="videoFolderName" required="required" class=" w100p b1 br3 h30 lh30 mt5 plr5"/><br>
       	</div>
       	<div id="belongToChapterVideo" class="r w45p">
       	章节：<form:select path="wkChapter.id" id="wkChapterVideoId" class="w100p lh25 br3 b1" >
       		<c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
       			<c:if test="${chap.type == moduleType}">
       				<form:option value="${chap.id}">${chap.name}</form:option>
       			</c:if>
       		</c:forEach>
       	</form:select>
       	</div>
       	<div id="belongToLessonVideo" class="r w45p">
       	课时：<form:select path="wkLesson.id" id="wkLessonVideoId" class="w100p lh25 br3 b1">
       				<form:option value="-1">请选择</form:option>
       		<c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
       			<c:if test="${chap.type == moduleType}">
	       			<c:forEach items="${chap.wkLessons}" var="less" varStatus="j">
	       				<form:option value="${less.id}">${chap.name}______${less.title}</form:option>
	       			</c:forEach>
       			</c:if>
       		</c:forEach>
       	</form:select>
       	</div>
       	</div>
       	<div class="cf tc">
	       	<input type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" value="保存视频"/>
	       	<div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
	                        取消
            </div>
       	</div>
    </form:form>
    </div>
    </div>
   
	</div>
	<!--添加视频结束-->
	
	
	<!--添加图片开始-->
	<div class="window_box hide fix zx2  " id="editImageFolder">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p10 bb f16">新增学习活动</div>
            <div class="add_con p10">
            <form:form action="${pageContext.request.contextPath}/tcoursesite/saveImageFolder?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}" method="POST" modelAttribute="imageFolder">
                <div class="add_module cf">
                    <div class="cf bb pb20">
                        <div class=" w45p f14 l mt10">
                            名称
                            <form:input type="text" path="name" id="name" class=" w100p b1 br3 h30 lh30 mt5 plr5" />
                        </div>
                        <div class=" w45p f14 r mt10" id="belongToChapterImage">
                            章节
                            <form:select path="wkChapter.id" id="wkChapterImageId" class="w100p lh30 br3 b1 mt5">
                                <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
                                	<c:if test="${chap.type == moduleType}">
                                		<form:option value="${chap.id}">${chap.name}</form:option>
                                	</c:if>
                                </c:forEach>
                            </form:select>

                        </div>
                        <div class=" w45p f14 r mt10 hide" id="belongToLessonImage">
                            课时
                            <form:select path="wkLesson.id" id="wkLessonImageId" class="w100p lh30 br3 b1 mt5">
                                		<form:option value="-1">请选择</form:option>
                                <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
                                	<c:if test="${chap.type == moduleType}">
						       			<c:forEach items="${chap.wkLessons}" var="less" varStatus="j">
						       				<form:option value="${less.id}">${chap.name}----${less.title}</form:option>
						       			</c:forEach>	
					       			</c:if>
					       		</c:forEach>
                            </form:select>

                        </div>
                    </div>
                    <div class="tab_box">
                    <ul class=" tab cf rel zx2 pt5 mt20 pb10">
                        <li class="rel l pb5">
                            <div class="wire_btn bgc l mt10 poi">
                                <i class="fa fa-upload mr5"></i>本地上传
                            </div>
                        </li>
                        <li class="rel l pb5">
                            <div class="wire_btn l ml10 mt10 poi">
                                <i class="fa  fa-folder-o mr5"></i>资源库
                            </div>
                        </li>
                    </ul>
                    <div class="tab_list f14 mb2">
                    <input type="file" name="file" id="imageUploadifyPic" />
                    <input name="imagesList" id="imagesList" type="text" class="hide"/><br>
                    
                    <div class="module_con  mtb10">
	                    <ul id="imagesNameList" class="">
	                       
	
	                    </ul>
	
	                </div>
                    
                    </div>
                    <div class="tab_list f14 mb2 hide">
                        <div class="course_mod f14  mb2">
                <div class=" lh40 bgg ">
                    <input  class="l"  id="checkallImage" type="checkbox" name="AllImage">
                    <label class="checkallImage mr10" for="checkallImage"></label>资源名 
                </div>
                <div class="module_con  mtb10">
                    <ul class="">
                        <li class="pic_list hg9 lh35 ptb5 rel ovh">
                            <div class="cf rel zx1 z c_category">
                                <input class="l check_box" type='checkbox' id='image0' name='checkname' value='0' />
                                <label class="l mt10" for="image0"></label>
                                <i class="fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
                                <div class="l mlr15 cc1 c_tool poi">图片1.jpg</div>
                                <div class="accessory_info l f12 g9">2016.05.20 11:20 大小： 451 Bytes</div>
                            </div>
                        </li>
                        <li class="pic_list hg9 lh35 ptb5 rel ovh">
                            <div class="cf rel zx1 z c_category">
                                <input class="l check_box" type='checkbox' id='image1' name='checkname' value='0' />
                                <label class="l mt10" for="image1"></label>
                                <i class="fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
                                <div class="l mlr15 cc1 c_tool poi">图片2.png</div>
                                <div class="accessory_info l f12 g9">2016.05.20 11:20 大小： 451 Bytes</div>
                            </div>
                        </li>
                       

                    </ul>

                </div>
            </div>
                    </div>
                    </div>

                </div>
                <div class="cf tc">
					<input type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" value="保存"/>
                    <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
                        取消
                    </div>
                </div>
            </form:form>
            </div>
        </div>

    </div>
    <!--添加图片结束-->
    
    	<!--添加文件开始-->
	<div class="window_box hide fix zx2  " id="editDocumentFolder">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
             <div class="add_tit p10 bb f16">新增学习活动</div>
            <div class="add_con p10">
            <form:form action="${pageContext.request.contextPath}/tcoursesite/saveDocumentFolder?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}" method="POST" modelAttribute="imageFolder">
                <div class="add_module cf">
                    <div class="cf bb pb20">
                        <div class=" w45p f14 l mt10">
                            名称
                            <form:input type="text" path="name" id="videoDocumentName" class=" w100p b1 br3 h30 lh30 mt5 plr5" />
                        </div>
                        <div class=" w45p f14 r mt10" id="belongToChapterDocument">
                            章节
                            <form:select path="wkChapter.id" id="wkChapterDocumentId" class="w100p lh30 br3 b1 mt5">
                                <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
                                	<c:if test="${chap.type == moduleType}">
                                		<form:option value="${chap.id}">${chap.name}</form:option>
                                	</c:if>
                                </c:forEach>
                            </form:select>

                        </div>
                        <div class=" w45p f14 r mt10 hide" id="belongToLessonDocument">
                            课时
                            <form:select path="wkLesson.id" id="wkLessonDocumentId" class="w100p lh30 br3 b1 mt5">
                                		<form:option value="-1">请选择</form:option>
                                <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
                                	<c:if test="${chap.type == moduleType}">
						       			<c:forEach items="${chap.wkLessons}" var="less" varStatus="j">
						       				<form:option value="${less.id}">${chap.name}----${less.title}</form:option>
						       			</c:forEach>
					       			</c:if>
					       		</c:forEach>
                            </form:select>

                        </div>
                    </div>
                    <div class="tab_box">
                    <ul class=" tab cf rel zx2 pt5 mt20 pb10">
                        <li class="rel l pb5">
                            <div class="wire_btn bgc l mt10 poi">
                                <i class="fa fa-upload mr5"></i>本地上传
                            </div>
                        </li>
                        <li class="rel l pb5">
                            <div class="wire_btn l ml10 mt10 poi">
                                <i class="fa  fa-folder-o mr5"></i>资源库
                            </div>
                        </li>
                    </ul>
                    <div class="tab_list f14 mb2">
                    <input type="file" name="file" id="DocumentUploadifyPic" />
                    <input name="documentsList" id="documentsList" type="text" class="hide"/><br>
                     
                     <div class="module_con  mtb10">
                    <ul id="documentsNameList" class="">
                       

                    </ul>

                </div>
                    
                    </div>
                    <div class="tab_list f14 mb2 hide">
                        <div class="course_mod f14  mb2">
                <div class=" lh40 bgg ">
                    <input  class="l"  id="checkallDocument" type="checkbox" name="AllDocument">
                    <label class="checkallDocument mr10" for="checkallDocument"></label>资源名 
                </div>
                <div class="module_con  mtb10">
                
                    <ul class="">
                        <li class="pic_list hg9 lh35 ptb5 rel ovh">
                            <div class="cf rel zx1 z c_category">
                                <input class="l check_box" type='checkbox' id='document0' name='checkname' value='0' />
                                <label class="l mt10" for="document0"></label>
                                <i class="fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
                                <div class="l mlr15 cc1 c_tool poi">图片1.jpg</div>
                                <div class="accessory_info l f12 g9">2016.05.20 11:20 大小： 451 Bytes</div>
                            </div>
                        </li>
                        <li class="pic_list hg9 lh35 ptb5 rel ovh">
                            <div class="cf rel zx1 z c_category">
                                <input class="l check_box" type='checkbox' id='document1' name='checkname' value='0' />
                                <label class="l mt10" for="document1"></label>
                                <i class="fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
                                <div class="l mlr15 cc1 c_tool poi">图片2.png</div>
                                <div class="accessory_info l f12 g9">2016.05.20 11:20 大小： 451 Bytes</div>
                            </div>
                        </li>
                       

                    </ul>

                </div>
            </div>
                    </div>
                    </div>

                </div>
                <div class="cf tc">
		<input type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" value="保存"/>
                    <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
                        取消
                    </div>
                </div>
            </form:form>
            </div>
        </div>

    </div>
    <!--添加文件结束-->
    
    		<!--添加作业开始-->
	<div id="editAssignmentFolder" class="window_box hide fix zx2  "  >
		<div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
             <div class="add_tit p10 bb f16">新增学习活动</div>
            <div class="add_con p10">
		<form:form action="${pageContext.request.contextPath}/tcoursesite/saveAssignment?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}" method="POST" modelAttribute="tAssignment" enctype="multipart/form-data" >
    		<div class="w98p cf">
    		<div class="abs f10 gc" style='left:145PX;top:17px'>
				<c:if test="${tAssignment.id ==null }">
					请填写如下信息，然后点击页面最下面的“发布”按钮。带<font color="red">*</font>的项目必须填写。
				</c:if>
				<c:if test="${tAssignment.id !=null }">
					<c:if test="${tAssignment.status==1 }">
						<font color="red">您正在修订的作业已经发布给学生。</font>
					</c:if>
					<c:if test="${now>tAssignment.TAssignmentControl.duedate.time }">
						<font color="red">你正在修改一份已经过了截止时间的作业。</font>
					</c:if>
				</c:if>
				</div>
				<div class=" w45p f14 l mt10">
			    	<label >作业标题<font color="red">*</font>：</label>
			    	<form:input path="title" id="title" required="true" type="text" class=" w100p b1 br3 h30 lh30 mt5 plr5" />
			    	<form:hidden path="id" id="id" />
			    	<form:hidden path="TAssignmentAnswerAssign.id" id="TAssignmentAnswerAssignId" />
			    	<form:hidden path="TAssignmentControl.id" id="TAssignmentControlId" />
				</div>	
				<div class="cf">
				<div class=" w45p f14 r mt10" id="belongToChapterTAssignment">
                            章节
                   <form:select path="wkFolder.wkChapter.id" id="wkChapterTAssignmentId" class="w100p lh30 br3 b1 mt5">
                   			<form:option value="-1">请选择</form:option>
                       <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
                       		<c:if test="${chap.type == moduleType}">
                       			<form:option value="${chap.id}">${chap.name}</form:option>
                       		</c:if>
                       </c:forEach>
                   </form:select>

               </div>
               <div class=" w45p f14 r mt10 hide" id="belongToLessonTAssignment">
                   课时
                   <form:select path="wkFolder.wkLesson.id" id="wkLessonTAssignmentId" class="w100p lh30 br3 b1 mt5">
                   				<form:option value="-1">请选择</form:option>
                       <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
                       		<c:if test="${chap.type == moduleType}">
					   			<c:forEach items="${chap.wkLessons}" var="less" varStatus="j">
					   				<form:option value="${less.id}">${chap.name}----${less.title}</form:option>
					   			</c:forEach>
				   			</c:if>
				   		</c:forEach>
                   </form:select>

               </div>
               </div>	
               <div class="cf">
		  		<div class="l w45p f14 mt10 rel">
				     <label >开始时间<font color="red">*</font>：</label>
					 <input type="text" name="TAssignmentControl.startdate" id="TAssignmentControl_startdate" value="${startdate }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly" required="required" class="Wdate w100p b1 br3 h30 lh30 mt5 plr5 " />
			 	</div>
			 	<div class="r w45p f14 mt10 rel">
				     <label >截止时间<font color="red">*</font>：</label>
					 <input type="text" name="TAssignmentControl.duedate" id="TAssignmentControl_duedate" value="${duedate }"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly" required="required"class="Wdate w100p b1 br3 h30 lh30 mt5 plr5 rel"  />
			 	</div>
			 	</div>
			  	<div class="l w100p f14 l mt10 hide">
				   	<div>
		     			<label>作业布置人：</label>
		     			<form:input path="TAssignmentAnswerAssign.user.username" placeholder="请输入工号/学号" class=" w100p b1 br3 h30 lh30 mt5 plr5"/>
				 	</div>
			 	</div>
			 	
		 		<div class="l w100p f14 l mt10 hid">
     				<div class="add_box bd1 bgg hg9 cf lh30 ">
                            <div class="l p20 bgc"> <i class="fa fa-plus mr5"></i>上传附件：</div>
                            <input type="file" name="file" class="l ml30 mt20 poi" />
                   </div>     
     			</div>
			 	
			   <div class="w100p f14 l mt10 hid">
			    	<div>
		     			<label >评分设置：</label><br>
		     			<form:radiobutton path="TAssignmentControl.toGradebook" value="yes"  id="1" checked="checked"/><label for="1">将作业添加到成绩簿</label> 
		     			<form:radiobutton path="TAssignmentControl.toGradebook" value="no"  id="2"/><label for="2">不要将作业加入到成绩簿中 </label><br>
		     			<form:radiobutton path="TAssignmentControl.gradeToStudent" value="yes" id="3" checked="checked"/><label for="3">将成绩公布给学生 </label>
		     			<form:radiobutton path="TAssignmentControl.gradeToStudent" value="no" id="4" /><label for="4">不将成绩公布给学生</label><br>
		     			<form:radiobutton path="TAssignmentControl.gradeToTotalGrade" value="yes" id="5" checked="checked"/><label for="5">将成绩计入总成绩</label>
		     			<form:radiobutton path="TAssignmentControl.gradeToTotalGrade" value="no" id="6" /><label for="6">不将成绩计入总成绩</label>
			 		</div>
		 		</div>
			    <div class="l w45p f14 l mt10">
			    	<div>
			    		<label>是否允许重复提交</label>
			    		<div claSS="cf">
			    		<div class="l lh40">
		     			<input type="checkbox" onchange="showTimeLimitDiv(this)" <c:if test="${tAssignment.TAssignmentControl.timelimit != 1 }">checked</c:if> id="showTime"/>
		     			<label for="showTime">是</label>
		     			</div>
		     			<div id="timeLimit" <c:if test="${tAssignment.TAssignmentControl.timelimit == 1 }">style="display: none;"</c:if> class="l ml10 w80p lh40">
		     				<div class="rel w100p  l" >
							<form:select class="select_search" path="TAssignmentControl.timelimit" id="timelimitselect"  required="true" >
								<option value ="-1" >请选择重复提交次数</option>
								<option value ="1" <c:if test="${tAssignment.TAssignmentControl.timelimit==1 }">selected</c:if>>1</option>
								<c:forEach begin="2" end="10"  varStatus="i" >
					               	<option value ="${i.current }" <c:if test="${tAssignment.TAssignmentControl.timelimit==i.current }">selected</c:if> >${i.current }</option>
						       	</c:forEach>
						       	<option value="0"  <c:if test="${tAssignment.TAssignmentControl.timelimit==0 }">selected</c:if>>无限制</option>
							</form:select>
							</div>
			 			</div>
			 			</div>
			 		</div>
		 		</div>
		 		<div class="r w45p f14 l mt10">
				   	<div>
		     			<label>学生提交作业的形式<font color="red">*</font>：</label>
		     			<form:select class="select_search" path="TAssignmentControl.submitType" style="width:250px;" required="required">
		     				<option value="1" <c:if test="${tAssignment.TAssignmentControl.submitType==1 }">selected</c:if>>输入文字或添加附件</option>
		     				<option value="2" <c:if test="${tAssignment.TAssignmentControl.submitType==2 }">selected</c:if>>直接输入文字</option>
		     				<option value="3" <c:if test="${tAssignment.TAssignmentControl.submitType==3 }">selected</c:if>>仅提交附件</option>
		     			</form:select>
				 	</div>
			 	</div>
		 		<div class="w100p f14 l mt10">
				   	<div>
				   		<!--默认作业配置中的最高分为100分  -->
		     			<label>作业分值<font color="red">*</font>：</label>
		     			<form:input path="TAssignmentAnswerAssign.score"  type="text" placeholder="请输入分值" class=" w100p b1 br3 h30 lh30 mt5 plr5" required="true"/>
				 	</div>
			 	</div>
			 	<div class="w100p f14 l mt10">
				   	<div>
		     			<label>作业要求：</label>
		     			<textarea id="content" path="content" name="content" class=" w100p b1 br3 h30 lh30 mt5 plr5">${tAssignment.content }</textarea>
				 	</div>
			 	</div>
			 	<form:hidden path="type" value="assignment"/>
			 	
			 	<form:hidden path="status" id="status" value="0"/>
			 	<form:hidden path="createdTime" />
			 	<form:hidden path="wkFolder.id" />
	 	        <form:hidden path="user.username" />
			</div>
			<div class="moudle_footer cf tc">
		        <div class="submit_link">
		        	<input class="btn bgb l mt10 poi  plr20 br3 wh" id="save" type="submit" value="发布" onclick="$('#status').val(1);return confirm('是否确认发布,发布后不可删除？')">
		        	<c:if test="${tAssignment.status != '1' }">
		        		<input class="btn close_icon bgc l ml30 mt10 poi plr20 br3" id="save" type="submit" value="保存草稿" onclick="$('#status').val(0)">
		        	</c:if>
					<input class="btn close_icon bgc l ml30 mt10 poi plr20 br3" type="button" value="返回" onclick="window.history.go(-1)">
		        </div>
		    </div>
    	</form:form>
    	</div>
    	</div>
	</div>
	<!--添加作业结束-->
	
	<!--添加考试开始-->
	<div class="window_box hide fix zx2  " id="editTestFolder">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p10 bb f16">新增学习单元</div>
            <div class="add_con ">
                
			<form:form action="${pageContext.request.contextPath}/tcoursesite/saveTest?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}" method="POST" modelAttribute="tAssignment" enctype="multipart/form-data" onsubmit="return checkForm()">
                <!--考试-->
			    	
                <div class="add_module cf ">
                    <div class="cf plr10  bb2 ptb20 ">
                    <div class="cf">
                        <div class=" w45p f14 l ">
                            名称
                            <form:input path="title" id="testTitle" required="true" type="text" class=" w97p b1 br3 h30 lh30 mt5 plr5" />
                        </div>
		                <div class=" w45p f14 r mt10" id="belongToChapterTest">
		                            章节
		                   <form:select path="wkFolder.wkChapter.id" id="wkChapterTestId" class="w100p lh30 br3 b1 mt5">
		                   			<form:option value="-1">请选择</form:option>
		                       <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
		                       		<c:if test="${chap.type == moduleType}">
		                       			<form:option value="${chap.id}">${chap.name}</form:option>
		                       		</c:if>
		                       </c:forEach>
		                   </form:select>
		               </div>
		               <div class=" w45p f14 r mt10 hide" id="belongToLessonTest">
		                   课时
		                   <form:select path="wkFolder.wkLesson.id" id="wkLessonTestId" class="w100p lh30 br3 b1 mt5">
		                   				<form:option value="-1">请选择</form:option>
		                       <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
		                       		<c:if test="${chap.type == moduleType}">
							   			<c:forEach items="${chap.wkLessons}" var="less" varStatus="j">
							   				<form:option value="${less.id}">${chap.name}----${less.title}</form:option>
							   			</c:forEach>
						   			</c:if>
						   		</c:forEach>
		                   </form:select>
		               </div>
               		</div>
               			<div class="cf">
	                        <div class=" w45p f14 l mt10 ">
	                            开始时间
	                            <input type="text" id="startdate" name="startdateTest" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"  value="${startdate }" readonly="readonly" required="required" class="Wdate w97p b1 br3 h30 lh30 mt5 plr5" />
	                        </div>
	                        <div class=" w45p f14 r  mt10">
	                            结束时间
	                            <input type="text" id="duedate" name="duedateTest" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" value="${duedate }" readonly="readonly" required="required"class="Wdate w97p b1 br3 h30 lh30 mt5 plr5" />
	                        </div>
                        </div>
                        <div class="l w100p f14 l mt10 hide">
						   	<div>
				     			<label>考试布置人：</label>
				     			<form:input path="TAssignmentAnswerAssign.user.username" placeholder="请输入工号/学号" class=" w100p b1 br3 h30 lh30 mt5 plr5"/>
						 	</div>
					 	</div>
                    </div>
                    <div class="cf plr10 ptb20 bb2">
                        <div class=" w100p f14 l">
                            <ul class="cf ">
                                <li class="l lh30 w180">将考试添加到成绩簿:</li>
                                <li class="l lh30 w80">
                                    <form:radiobutton class=" check_box"  id='id1Test' name='checknameTest' path="TAssignmentControl.toGradebook" value="yes" checked="checked"/>
                                    <label class="l " for="id1Test">是</label>
                                </li>
                                <li class="l lh30">
                                    <form:radiobutton class=" check_box"  id='id2Test' name='checknameTest' path="TAssignmentControl.toGradebook" value="no" />
                                    <label class="l " for="id2Test">否</label>
                                </li>
                            </ul>
                            <ul class="cf">
                                <li class="l lh30 w180">将成绩公布给学生:</li>
                                <li class="l lh30 w80">
                                    <form:radiobutton class=" check_box"  id='id3Test' name='checkname1Test' path="TAssignmentControl.gradeToStudent" value="yes" checked="checked" />
                                    <label class="l" for="id3Test">是</label>
                                </li>
                                <li class="l lh30">
                                    <form:radiobutton class=" check_box"  id='id4Test' name='checkname1Test' path="TAssignmentControl.gradeToStudent" value="no" />
                                    <label class="l" for="id4Test">否 </label>
                                </li>
                            </ul>
                            <ul class="cf ">
                                <li class="l lh30 w180">将成绩计入总成绩:</li>
                                <li class="l lh30 w80">
                                    <form:radiobutton class=" check_box"  id='id5Test' name='checkname2Test' path="TAssignmentControl.gradeToTotalGrade" value="yes" checked="checked"/>
                                    <label class="l" for="id5Test">是</label>
                                </li>
                                <li class="l lh30">
                                    <form:radiobutton class=" check_box"  id='id6Test' name='checkname2Test' path="TAssignmentControl.gradeToTotalGrade" value="no" />
                                    <label class="l" for="id6Test">否 </label>
                                </li>
                            </ul>
                        </div>
                        <div class="l w100p f14 l mt10 ">
						   	<div>
				     			<label>考试分值：</label>
				     			<form:input path="TAssignmentAnswerAssign.score" id="testScoreTest" placeholder="请输入分值" required="true" class=" w95p b1 br3 h30 lh30 mt5 plr5"/>
						 	</div>
					 	</div>
                    </div>
                    <div class="cf plr10 ptb20 pb20">
                        <div class="f14 ">
                            抽取题库
                            <ul class="cf w100p f14 l mt50">
                            	<c:forEach items="${tAssignmentQuestionpools }" var="tAssignmentQuestionpool" varStatus="i">
                     				<li class="l lh30 mr5 mb5">
	                                    <input class=" check_box" type='checkbox' id="poolTest${tAssignmentQuestionpool.questionpoolId }" <c:if test="${map[tAssignmentQuestionpool.questionpoolId]!=null }">checked="true"</c:if> name="questionIdTest" value="${tAssignmentQuestionpool.questionpoolId }"/>
	                                    <label class="l" for="poolTest${tAssignmentQuestionpool.questionpoolId }">${tAssignmentQuestionpool.title }&nbsp;</label>
	                                    <c:if test="${i.count%3==0 }"></br></c:if>
	                                </li>		
                               	</c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="cf plr10 pb20 bb2">
                        <div class="f14 mt10">
                            <!--试题组成-->
                            <table class="w100p question_tab">
                                <tr>
                                    <th>试题类型</th>
                                    <th>数量</th>
                                    <th>分值（仅用于此次）</th>
                                    
                                </tr>
                                <tr>
                                    <th>
                                        <input class=" check_box" type='checkbox' id='singleTest' name='singleTest' value='0' />
                                        <label class="asd l" for="singleTest">单选题</label>
                                    </th>
                                    <td>
                                    	<input type="hidden" name="itemTypeTest" value="4"/> 
                                        <input type="text" name="itemQuantityTest" disabled="true" value="0" class=" w50 b1 br3" />
                                    </td>
                                    <td>
                                        <input type="text" name="itemScoreTest"  disabled="true" value="0" class=" w50 b1 br3" />
                                    </td>
                                    
                                </tr>
                                <tr>
                                    <th>
                                        <input class=" check_box" type='checkbox' id='multipleTest' name='multipleTest' value='0' />
                                        <label class="asd l" for="multipleTest">多选题</label>
                                    </th>
                                    <td>
                                    	<input type="hidden" name="itemTypeTest" value="1"/> 
                                        <input type="text" name="itemQuantityTest" disabled="true" value="0" class=" w50 b1 br3" />
                                    </td>
                                    <td>
                                        <input type="text" name="itemScoreTest"  disabled="true" value="0" class=" w50 b1 br3" />
                                    </td>
                                    
                                </tr>
                                <tr>
                                    <th>
                                        <input class=" check_box" type='checkbox' id='judgeTest' name='judgeTest' value='0' />
                                        <label class="asd l" for="judgeTest">对错题</label>
                                    </th>
                                    <td>
                                    	<input type="hidden" name="itemTypeTest" value="2"/> 
                                        <input type="text" name="itemQuantityTest" disabled="true" value="0" class=" w50 b1 br3" />
                                    </td>
                                    <td>
                                        <input type="text" name="itemScoreTest"  disabled="true" value="0" class=" w50 b1 br3" />
                                    </td>
                                    
                                </tr>
                                <tr>
                                    <th>
                                        <input class=" check_box" type='checkbox' id='blanksTest' name='blanksTest' value='0' />
                                        <label class="asd l" for="blanksTest">填空题</label>
                                    </th>
                                    <td>
                                    	<input type="hidden" name="itemTypeTest" value="8"/> 
                                        <input type="text" name="itemQuantityTest" disabled="true" value="0" class=" w50 b1 br3" />
                                    </td>
                                    <td>
                                        <input type="text" name="itemScoreTest"  disabled="true" value="0" class=" w50 b1 br3" />
                                    </td>
                                    
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="cf plr10 ptb20 bb2 pb20">
                        <div class="f14">
                            考试描述
                            <form:textarea id="contentTest" path="content" name="contentTest" class=" w100p b1 br3 h30 lh30 mt5 plr5"/>
                        </div>
                    </div>

							<form:hidden path="type" value="test"/>
						 	<form:hidden path="status" value="0"/>
						 	<form:hidden path="createdTime" />
				 	        <form:hidden path="user.username" />
						    <form:hidden path="id" />
						    <form:hidden path="TAssignmentControl.id" />
						    <form:hidden path="TAssignmentAnswerAssign.id" />
						    <form:hidden path="TAssignmentControl.timelimit" value="1" />
                </div>
                <div class="cf tc plr10 ptb20">

                    <input type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" />


                    <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
                        取消
                    </div>
                </div>
            </form:form>
            </div>
        </div>
        </div>
        <!--添加考试结束-->
        
        <!--添加测试开始-->
	<div class="window_box hide fix zx2  " id="editExamFolder">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p10 bb f16">新增学习单元</div>
            <div class="add_con ">
                
			<form:form action="${pageContext.request.contextPath}/tcoursesite/saveExam?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}" method="POST" modelAttribute="tAssignment" enctype="multipart/form-data" >
                <!--测试-->
			    	
                <div class="add_module cf ">
                    <div class="cf plr10  bb2 ptb20 ">
                    <div class="cf">
                        <div class=" w45p f14 l ">
                            	名称
                            <form:input path="title" id="examTitle" required="true" type="text" class=" w97p b1 br3 h30 lh30 mt5 plr5" />
                        </div>
		                <div class=" w45p f14 r mt10" id="belongToChapterExam">
		                            	章节
		                   <form:select path="wkFolder.wkChapter.id" id="wkChapterExamId" class="w100p lh30 br3 b1 mt5">
		                   			<form:option value="-1">请选择</form:option>
		                       <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
		                       		<c:if test="${chap.type == moduleType}">
		                       			<form:option value="${chap.id}">${chap.name}</form:option>
		                       		</c:if>
		                       </c:forEach>
		                   </form:select>
		               </div>
		               <div class=" w45p f14 r mt10 hide" id="belongToLessonExam">
		                   课时
		                   <form:select path="wkFolder.wkLesson.id" id="wkLessonExamId" class="w100p lh30 br3 b1 mt5">
		                   				<form:option value="-1">请选择</form:option>
		                       <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
		                       		<c:if test="${chap.type == moduleType}">
							   			<c:forEach items="${chap.wkLessons}" var="less" varStatus="j">
							   				<form:option value="${less.id}">${chap.name}----${less.title}</form:option>
							   			</c:forEach>
						   			</c:if>
						   		</c:forEach>
		                   </form:select>
		               </div>
               		</div>
               			<div class="cf">
	                        <div class=" w45p f14 l mt10 ">
	                            开始时间
	                            <input type="text" id="startdate" name="TAssignmentControl.startdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"  value="${startdate }" readonly="readonly" required="required" class="Wdate w97p b1 br3 h30 lh30 mt5 plr5" />
	                        </div>
	                        <div class=" w45p f14 r  mt10">
	                            结束时间
	                            <input type="text" id="duedate" name="TAssignmentControl.duedate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" value="${duedate }" readonly="readonly" required="required"class="Wdate w97p b1 br3 h30 lh30 mt5 plr5" />
	                        </div>
                        </div>
                        <div class="l w100p f14 l mt10 hide">
						   	<div>
				     			<label>测试布置人：</label>
				     			<form:input path="TAssignmentAnswerAssign.user.username" placeholder="请输入工号/学号" class=" w100p b1 br3 h30 lh30 mt5 plr5"/>
						 	</div>
					 	</div>
                    </div>
                    <div class="cf plr10 ptb20 bb2">
                        <div class=" w100p f14 l">
                             <ul class="cf ">
                                <li class="l lh30 w180">提交次数:</li>
                                <li class="l lh30 w80">
                                    <input class="unrefer"  id="timelimit" type='radio' name='refer' value='0' checked="checked" onclick="$('#timelimitExam').val(1);"/>
                                    <label class="l " for="timelimit">一次</label>
                                </li>
                                <li class="l lh30 ">
                                    <input class="refer" type='radio' id='refer' name='refer' value='0' />
                                    <label class="l " for="refer">自定义</label>
                                </li>
                                 <li class="l lh30 submittimes hide">
                                    <form:input type="text" path="TAssignmentControl.timelimit" id="timelimitExam" class="w100 b1 br3 h20 lh20 plr5" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" value="1"/>
                                </li>
                            </ul>
                            <ul class="cf ">
                                <li class="l lh30 w180">将测试添加到成绩簿:</li>
                                <li class="l lh30 w80">
                                    <form:radiobutton class=" check_box"  id='id1Exam' name='checknameExam' path="TAssignmentControl.toGradebook" value="yes" checked="checked"/>
                                    <label class="l " for="id1Exam">是</label>
                                </li>
                                <li class="l lh30">
                                    <form:radiobutton class=" check_box"  id='id2Exam' name='checknameExam' path="TAssignmentControl.toGradebook" value="no" />
                                    <label class="l " for="id2Exam">否</label>
                                </li>
                            </ul>
                            <ul class="cf">
                                <li class="l lh30 w180">将成绩公布给学生:</li>
                                <li class="l lh30 w80">
                                    <form:radiobutton class=" check_box"  id='id3Exam' name='checkname1Exam' path="TAssignmentControl.gradeToStudent" value="yes" checked="checked" />
                                    <label class="l" for="id3Exam">是</label>
                                </li>
                                <li class="l lh30">
                                    <form:radiobutton class=" check_box"  id='id4Exam' name='checkname1Exam' path="TAssignmentControl.gradeToStudent" value="no" />
                                    <label class="l" for="id4Exam">否 </label>
                                </li>
                            </ul>
                            <ul class="cf ">
                                <li class="l lh30 w180">将成绩计入总成绩:</li>
                                <li class="l lh30 w80">
                                    <form:radiobutton class=" check_box"  id='id5Exam' name='checkname2Exam' path="TAssignmentControl.gradeToTotalGrade" value="yes" checked="checked"/>
                                    <label class="l" for="id5Exam">是</label>
                                </li>
                                <li class="l lh30">
                                    <form:radiobutton class=" check_box"  id='id6Exam' name='checkname2Exam' path="TAssignmentControl.gradeToTotalGrade" value="no" />
                                    <label class="l" for="id6Exam">否 </label>
                                </li>
                            </ul>
                        </div>
                        <div class="l w100p f14 l mt10 ">
						   	<div>
				     			<label>测试分值：</label>
				     			<form:input path="TAssignmentAnswerAssign.score" id="examScoreExam" placeholder="请输入分值" required="true" class=" w95p b1 br3 h30 lh30 mt5 plr5"/>
						 	</div>
					 	</div>
                    </div>
                    <div class="cf plr10 ptb20 bb2 pb20">
                        <div class="f14">
                            测试描述
                            <form:textarea id="contentExam" path="content" name="contentExam" class=" w100p b1 br3 h30 lh30 mt5 plr5"/>
                        </div>
                    </div>

							<form:hidden path="type" value="exam"/>
						 	<form:hidden path="status" value="0"/>
						 	<form:hidden path="createdTime" />
				 	        <form:hidden path="user.username" />
						    <form:hidden path="id" />
						    <form:hidden path="TAssignmentControl.id" />
						    <form:hidden path="TAssignmentAnswerAssign.id" />
                </div>
                <div class="cf tc plr10 ptb20">

                    <input type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" />


                    <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
                        取消
                    </div>
                </div>
            </form:form>
            </div>
        </div>
        </div>
        <!--添加测试结束-->
        
         <!--图片预览开始-->
        <div class="window_box hide fix zx2  " id="imageLook">
	        <div class="window_con bgw b1 br3 rel bs10 ">
	            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
	            <div class="add_tit p20 bb f16">图片预览</div>
	            <div class="add_con p20 tc">
	            	<div><img src="" id="imageUrlLook" class="p10  mb20 br5 bgg mw97p"></div>
	            	<label id="imageNameLook">图片名字</label>
	            </div>
	        </div>
	    </div>
    	<!--图片预览结束-->
    	
    	<!--视频预览开始-->
        <div class="window_box hide fix zx2  " id="videoLook">
	        <div class="window_con bgw b1 br3 rel bs10 ">
	            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20" onclick="closeVideoLook()">x</span>
	            <div class="add_tit p20 bb f16">视频预览</div>
	            <div class="add_con p20 tc" id="mediaDisplay" >
	            	<label id="videoNameLook">图片名字</label>
	            </div>
	        </div>
	    </div>
    	<!--视频预览结束-->

    
	
	
	    <script type="text/javascript">
        $(".gdrag_list").dragsort({
            //dragSelector: "div.modelTitle", //容器拖动手柄
            dragBetween: true, //
            //dragEnd: saveOrder, //执行之后的回调函数
            dragSelectorExclude: ".c_tool", //指定不会执行动作的元素
            //placeHolderTemplate: "<li class='placeHolder'><div></div></li>", //拖动列表的HTML部分
            scrollSpeed: 15 //拖动速度
        });  
        function showTimeLimitDiv(obj){
        	
    		if($(obj).attr("checked")=="checked"){
    			$("#timeLimit").show();
    		}else{
    			$("#timeLimit").hide();
    			$("#timelimitselect").val(1);
    		}
    	}
        var moduleType = ${moduleType};
        if(moduleType == 1){
        	
        	$(function(){
            	$('.date_picker').date_input();
            	$(".course_menu").find(".cm_list").eq(0).addClass("select").siblings().removeClass("select");
    			$.cookie("mysel",0,{"path":"/", expires:30});
    			
    			var cookie_sel = $.cookie("mysel");
    			console.log(document.cookie);
            	})
        	
        }
        
        	
        	//考试js
     	$(".question_tab ").find("input[type='checkbox']").click(
            function () {
                if (this.checked) {
                    $(this).parents("tr").find("input[type='text']").attr("disabled", false)
                } else {
                    $(this).parents("tr").find("input[type='text']").attr("disabled", "true")
                }
            })
        $(".refer").click(
            function(){
                   $(this).parents("ul").find(".submittimes").show()
            }
        )
        $(".unrefer").click(
            function(){
                   $(this).parents("ul").find(".submittimes").hide()
            }
        )
        
        function imageLook(url,name){
        	$("#imageUrlLook").attr("src", "${pageContext.request.contextPath}"+url);
        	$("#imageNameLook").html(name);
		 	$("#imageLook").fadeIn(100);
		}
        
      //弹出的方法
        function videoLook(id,moduleType) {
            var con = '<iframe id="con" scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/tcoursesite/videoLook?folderId=' + id + '&moduleType= ' + moduleType + '" style="width:100%;height:560px;"></iframe>'
            $("#mediaDisplay").html(con); 
            $("#videoLook").fadeIn(100);
            $("body").css("overflow-y","hidden")
        }
        //关闭视频
        function closeVideoLook(){
     		 $("#con").remove();
        	}

      
    </script>

 <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.date_input.pack.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/tcoursesite.js"></script>
</body>


</html>
