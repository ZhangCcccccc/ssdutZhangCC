<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html>

<head>
    <title>XDU实验实训微课教学平台</title>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/check_work.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tCourseSite/date.css" />
    <link href="${pageContext.request.contextPath}/css/tCourseSite/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/tcoursesiteBefore.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
   	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/easyui.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js" ></script>
   
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dragsort-0.5.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autosize.min.js"></script>
    <!-- 上传插件的css和js -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>
   	
	
</head>

<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
	<input type="hidden"  id="moduleType" value="${moduleType}"/>
	<input type="hidden"  id="tCourseSiteId" value="${tCourseSite.id}"/>
    <div class="course_con ma">
        <div class="course_cont r">
            <ul class="tool_box cf rel zx2 pt5 pb10">
           		<c:if test="${flag==1}">
	                <li class="rel l">
	                    <div class="wire_btn Lele l ml30 mt10 poi" onclick="newWkLesson(-1,-1,'${tCourseSite.wkChapters}');">
	                        <i class="fa fa-plus mr5"></i>学习单元
	                    </div>
	                </li>
	                <li class="rel l dropdown pb5">
	                    <div class="wire_btn l ml20 mt10 poi">
	                        <i class="fa fa-plus mr5"></i>学习活动
	                    </div>
	                    <ul class="dropdown_list le20 mt5 bs5 b1 abs br3 bgw ptb5 hide ">
	                        <li class="video lh24 plr10 poi hg9 Lele" onclick="newVideoFolder(${moduleType},-1,-1,-1)">视频</li>
	                        <li class="material lh24 plr10 poi hg9 Lele" onclick="newDocumentFolder(${moduleType},-1,-1,-1,-1)">参考文件</li>
							<li class="pic lh24 plr10 poi hg9 Lele" onclick="newImageFolder(${moduleType},-1,-1,-1)">图片</li>
	                        <li class="homework lh24 plr10 poi hg9 Lele" onclick="newTestFolder(${moduleType},-1,-1,-1,0)">作业</li>
	                        <li class="exam lh24 plr10 poi hg9 Lele" onclick="newTestFolder(${moduleType},-1,-1,-1,0)">考试</li>
	                        <li class="exam lh24 plr10 poi hg9 Lele" onclick="newExamFolder(${moduleType},-1,-1,-1,0)">测试</li>
	                    </ul>
	                </li>
                </c:if>
                <li class="rel l dropdown  pb5">
                    <div class="wire_btn drop_box l ml20 mt10 cf ovh poi">
                        <span class="l">课程内容:</span>
                        <span class="l ml5 pl5 bl drop_btn cc1">全部</span><i class="fa fa-angle-double-down ml5 gc"></i>
                    </div>
                    <ul class="dropdown_list drop_list mt5 bs5 b1 abs br3 bgw ptb5 hide le85 ">
                        <li class="video lh24 plr10 poi hg9" onclick="select(${tCourseSite.id},${moduleType},-1)">全部</li>
                        <li class="video lh24 plr10 poi hg9" onclick="select(${tCourseSite.id},${moduleType},1)">视频</li>
                        <li class="material lh24 plr10 poi hg9" onclick="select(${tCourseSite.id},${moduleType},3)">参考文件</li>
                        <li class="pic lh24 plr10 poi hg9" onclick="select(${tCourseSite.id},${moduleType},2)">图片</li>
                        <li class="homework lh24 plr10 poi hg9" onclick="select(${tCourseSite.id},${moduleType},4)">作业</li>
                        <li class="exam lh24 plr10 poi hg9" onclick="select(${tCourseSite.id},${moduleType},6)">考试</li>
                        <li class="exam lh24 plr10 poi hg9" onclick="select(${tCourseSite.id},${moduleType},5)">测试</li>
                        <li class="exam lh24 plr10 poi hg9" onclick="select(${tCourseSite.id},${moduleType},9)">考勤</li>
                    </ul>
                </li>
                <a href="${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}&viewFlag=0">切换视图
                </a>
            </ul>
             <!-- 循环每一个章节-->
        <c:forEach items="${tCourseSite.wkChapters}" var="chapter" varStatus="i">
        	<c:if test="${chapter.type == moduleType}">
        	<form:form id="form" action="${pageContext.request.contextPath}/tcoursesite/deleteUploads?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}" method="POST" enctype="multipart/form-data" >
	            <div id="chapterdiv${chapter.id}" class="course_mod mb1 f14">
	                <div class="module_tit lh40 bgg  pl30 ">
	               		<c:if test="${flag==1}">
		                	<input class="checkall " id="checkall${chapter.id}"  type="checkbox" name="All" >
	                       	<label class="mt10" for="checkall${chapter.id}"></label>
                       	</c:if>
	                	<span style="font-size:24px;">${chapter.name}</spans>
	                	<c:if test="${flag==1}">
	   					<i class="newScroll fa fa-edit g9 f14 mr5 poi" onclick="newWkChapter(${chapter.id})"></i>
	   					</c:if>
	   					<%--<i class="fa fa-angle-double-down g9 f14 mr5 poi"></i>
	   					<i class="fa fa-angle-double-up g9 f14 mr5 poi"></i> 
	   					--%><c:if test="${flag==1}">
	   						<i class="newScroll fa fa-plus g9 f14 mr5 poi" onclick="newWkLesson(-1,${chapter.id},'${tCourseSite.wkChapters}')"></i> 
	   					</c:if>
	   					<c:if test="${flag==1}">
	   					<i class="fa fa-trash-o g9 f14 mr5 poi" onclick="deleteWkChapter(${chapter.id},${tCourseSite.id},${moduleType})"></i>
	                	</c:if>
	                	<c:if test="${flag==1}">
	                		<button type="submit" >批量删除</button>
	                	</c:if>
	                	<br/>
	                		<p class="f14">内容概述：${chapter.content}</p>
	                </div>
	                
	                <div class="module_con cl  mtb20">
                    	<ul class="">
	                    <!-- 分类显示六类学习活动 -->
	                    	<!-- 图片 -->
	                    	<li class="pic_list hg9  rel ovh">
	                    		<div class="pl40" id="uploadImage">
	                    			<span class="l b">图片：</span>
	                    			<c:set var="count" value="0"></c:set>
	                    			<ul class="l w80p">
		                    			<c:forEach items="${chapter.wkFolders}" var="folder" varStatus="i">
			                    			<c:if test="${selectId==2||selectId==-1}">
				                    		<c:if test="${folder.type == 2&&folder.wkLesson==null}">
			                                    <c:forEach items="${folder.uploads }" var="image" varStatus="i">
			                                    	<c:if test="${count < 6}">
				                                    	<div>
						                    				<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="upload${image.id}" name='checknames' value='upload${image.id}'/>
						                                        	<label for="upload${image.id}"></label>
						                                        </c:if>
						                                        <a id="${image.id}" href="javascript:void(0);" onclick="imageLookByChapter('${chapter.id}','-1','${count+1}');">
						                                        <c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(image.name,fn:indexOf(image.name,'.')+1,-1))}">
						                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(image.name,fn:indexOf(image.name,'.')+1,-1)}.gif"  alt="" />
						                                        </c:if>
						                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(image.name,fn:indexOf(image.name,'.')+1,-1))}">
						                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
						                                        </c:if>
						                                        <span>${image.name}</span>
						                                        </a>
						                                    </li>
											            </div>
					                                </c:if>
					                                <c:if test="${count > 5}">	
						                                <div class="hide">
						                                	<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="upload${image.id}" name='checknames' value='upload${image.id}'/>
						                                        	<label for="upload${image.id}"></label>
						                                        </c:if>
						                                        <a id="${image.id}" href="javascript:void(0);" onclick="imageLookByChapter('${chapter.id}','-1','${count+1}');">
						                                        <c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(image.name,fn:indexOf(image.name,'.')+1,-1))}">
						                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(image.name,fn:indexOf(image.name,'.')+1,-1)}.gif"  alt="" />
						                                        </c:if>
						                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(image.name,fn:indexOf(image.name,'.')+1,-1))}">
						                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
						                                        </c:if>
						                                        <span>${image.name}</span>
						                                        </a>
						                                    </li>
						                                </div>
					                                </c:if>
					                                <c:set var="count" value="${count+1}"></c:set>
				                                </c:forEach>
				                            </c:if>
				                            </c:if>
		                                </c:forEach>
		                                <c:if test="${count > 6}">
			                            	<input id="uploadImageCheck" type="button" onclick="checkMore('uploadImage');" value="显示更多" />
			                            </c:if>
	                    			</ul>
	                    		</div>
	                    	</li>
	                    	
							<!-- 文件 -->
							<li class="pic_list hg9  rel ovh">
	                    		<div class="pl40" id="uploadDocument">
	                    			<span class="l b">文件：</span>
	                    			<c:set var="count" value="0"></c:set>
	                    			<ul class="l w80p">
		                    			<c:forEach items="${chapter.wkFolders}" var="folder" varStatus="i">
			                    			<c:if test="${selectId==3||selectId==-1}">
				                    		<c:if test="${folder.type == 3&&folder.wkLesson==null}">
			                                    <c:forEach items="${folder.uploads }" var="document" varStatus="i">
			                                    	<c:if test="${count < 6}">
				                                    	<div>
						                    				<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="upload${document.id}" name='checknames' value='upload${document.id}'/>
						                                        	<label for="upload${document.id}"></label>
						                                        </c:if>
						                                        <c:if test="${fn:contains(document.url,'.pdf')}">
							                                        <a href="${pageContext.request.contextPath}/tcoursesite/showFile?id=${document.id}" target="_blank">
								                                        <img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_pdf.gif"  alt="" />
								                                        ${document.name}</a>
							                                    </c:if>
							                                    <c:if test="${!fn:contains(document.url,'.pdf')}">
								                                    <c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(document.name,fn:indexOf(document.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(document.name,fn:indexOf(document.name,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(document.name,fn:indexOf(document.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
							                                    	${document.name}
							                                    </c:if>
						                                    </li>
											            </div>
					                                </c:if>
					                                <c:if test="${count > 5}">	
						                                <div class="hide">
						                                	<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="upload${document.id}" name='checknames' value='upload${document.id}'/>
						                                        	<label for="upload${document.id}"></label>
						                                        </c:if>
						                                        <c:if test="${fn:contains(document.url,'.pdf')}">
							                                        <a href="${pageContext.request.contextPath}/tcoursesite/showFile?id=${document.id}" target="_blank">
								                                        <img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_pdf.gif"  alt="" />
								                                        ${document.name}</a>
							                                    </c:if>
							                                    <c:if test="${!fn:contains(document.url,'.pdf')}">
							                                    	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(document.name,fn:indexOf(document.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(document.name,fn:indexOf(document.name,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(document.name,fn:indexOf(document.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
							                                    	${document.name}
							                                    </c:if>
						                                    </li>
						                                </div>
					                                </c:if>
					                                <c:set var="count" value="${count+1}"></c:set>
				                                </c:forEach>
				                            </c:if>
				                            </c:if>
		                                </c:forEach>
		                                <c:if test="${count > 6}">
			                            	<input id="uploadDocumentCheck" type="button" onclick="checkMore('uploadDocument');" value="显示更多" />
			                            </c:if>
	                    			</ul>
	                    		</div>
	                    	</li>
		                    	
							<!-- 视频 -->
							<li class="pic_list hg9  rel ovh">
	                    		<div class="pl40" id="uploadVideo">
	                    			<span class="l b">视频：</span>
	                    			<c:set var="count" value="0"></c:set>
	                    			<ul class="l w80p">
		                    			<c:forEach items="${chapter.wkFolders}" var="folder" varStatus="i">
			                    			<c:if test="${selectId==1||selectId==-1}">
				                    		<c:if test="${folder.type == 1&&folder.wkLesson==null}">
			                                    <c:forEach items="${folder.uploads }" var="video" varStatus="i">
			                                    	<c:if test="${count < 6}">
				                                    	<div>
						                    				<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="upload${video.id}" name='checknames' value='upload${video.id}'/>
						                                        	<label for="upload${video.id}"></label>
						                                        </c:if>
						                                        <a id="${video.id}" href="javascript:void(0);" onclick="videoLook(${folder.id},${moduleType});">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(video.name,fn:indexOf(video.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(video.name,fn:indexOf(video.name,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(video.name,fn:indexOf(video.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        	<span>${video.name}</span>
						                                        </a>
						                                    </li>
											            </div>
					                                </c:if>
					                                <c:if test="${count > 5}">	
						                                <div class="hide">
						                                	<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="upload${video.id}" name='checknames' value='upload${video.id}'/>
						                                        	<label for="upload${video.id}"></label>
						                                        </c:if>
						                                        <a id="${video.id}" href="javascript:void(0);" onclick="videoLook(${folder.id},${moduleType});">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(video.name,fn:indexOf(video.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(video.name,fn:indexOf(video.name,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(video.name,fn:indexOf(video.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        	<span>${video.name}</span>
						                                        </a>
						                                    </li>
						                                </div>
					                                </c:if>
					                                <c:set var="count" value="${count+1}"></c:set>
				                                </c:forEach>
				                            </c:if>
				                            </c:if>
		                                </c:forEach>
		                                <c:if test="${count > 6}">
			                            	<input id="uploadVideoCheck" type="button" onclick="checkMore('uploadVideo');" value="显示更多" />
			                            </c:if>
	                    			</ul>
	                    		</div>
	                    	</li>
							
							<!-- 考试 -->
							<li class="pic_list hg9  rel ovh">
	                    		<div class="pl40" id="uploadTest">
	                    			<span class="l b">考试：</span>
	                    			<c:set var="count" value="0"></c:set>
	                    			<ul class="l w80p">
		                    			<c:forEach items="${chapter.wkFolders}" var="folder" varStatus="i">
			                    			<c:if test="${selectId==6||selectId==-1}">
				                    		<c:if test="${folder.type == 6&&folder.wkLesson==null}">
			                                    <c:forEach items="${folder.TAssignments }" var="test" varStatus="i">
			                                    	<c:if test="${count < 6}">
				                                    	<div>
						                    				<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${test.id}" name='checknames' value='assignment${test.id}'/>
						                                        	<label for="assignment${test.id}"></label>
						                                        </c:if>
						                                        <a id="${test.id}">
						                                       		<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(test.title,fn:indexOf(test.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(test.title,fn:indexOf(test.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(test.title,fn:indexOf(test.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        	<span>${test.title}</span>
						                                        </a>
						                                    </li>
											            </div>
					                                </c:if>
					                                <c:if test="${count > 5}">	
						                                <div class="hide">
						                                	<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${test.id}" name='checknames' value='assignment${test.id}'/>
						                                        	<label for="assignment${test.id}"></label>
						                                        </c:if>
						                                        <a id="${test.id}">
						                                        <c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(test.title,fn:indexOf(test.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(test.title,fn:indexOf(test.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(test.title,fn:indexOf(test.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${test.title}</span>
						                                        </a>
						                                    </li>
						                                </div>
					                                </c:if>
					                                <c:set var="count" value="${count+1}"></c:set>
				                                </c:forEach>
				                            </c:if>
				                            </c:if>
		                                </c:forEach>
		                                <c:if test="${count > 6}">
			                            	<input id="uploadTestCheck" type="button" onclick="checkMore('uploadTest');" value="显示更多" />
			                            </c:if>
	                    			</ul>
	                    		</div>
	                    	</li>
							
							<!-- 测试 -->
							<li class="pic_list hg9  rel ovh">
	                    		<div class="pl40" id="uploadExam">
	                    			<span class="l b">测试：</span>
	                    			<c:set var="count" value="0"></c:set>
	                    			<ul class="l w80p">
		                    			<c:forEach items="${chapter.wkFolders}" var="folder" varStatus="i">
			                    			<c:if test="${selectId==5||selectId==-1}">
				                    		<c:if test="${folder.type == 5&&folder.wkLesson==null}">
			                                    <c:forEach items="${folder.TAssignments }" var="exam" varStatus="i">
			                                    	<c:if test="${count < 6}">
				                                    	<div>
						                    				<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${exam.id}" name='checknames' value='assignment${exam.id}'/>
						                                        	<label for="assignment${exam.id}"></label>
						                                        </c:if>
						                                        <a id="${exam.id}">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(exam.title,fn:indexOf(exam.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(exam.title,fn:indexOf(exam.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(exam.title,fn:indexOf(exam.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${exam.title}</span>
						                                        </a>
						                                    </li>
											            </div>
					                                </c:if>
					                                <c:if test="${count > 5}">	
						                                <div class="hide">
						                                	<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${exam.id}" name='checknames' value='assignment${exam.id}'/>
						                                        	<label for="assignment${exam.id}"></label>
						                                        </c:if>
						                                        <a id="${exam.id}">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(exam.title,fn:indexOf(exam.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(exam.title,fn:indexOf(exam.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(exam.title,fn:indexOf(exam.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${exam.title}</span>
						                                        </a>
						                                    </li>
						                                </div>
					                                </c:if>
					                                <c:set var="count" value="${count+1}"></c:set>
				                                </c:forEach>
				                            </c:if>
				                            </c:if>
		                                </c:forEach>
		                                <c:if test="${count > 6}">
			                            	<input id="uploadExamCheck" type="button" onclick="checkMore('uploadExam');" value="显示更多" />
			                            </c:if>
	                    			</ul>
	                    		</div>
	                    	</li>
							
							<!-- 作业 -->
							<li class="pic_list hg9  rel ovh">
	                    		<div class="pl40" id="uploadAssignment">
	                    			<span class="l b">作业：</span>
	                    			<c:set var="count" value="0"></c:set>
	                    			<ul class="l w80p">
		                    			<c:forEach items="${chapter.wkFolders}" var="folder" varStatus="i">
			                    			<c:if test="${selectId==4||selectId==-1}">
				                    		<c:if test="${folder.type == 4&&folder.wkLesson==null}">
			                                    <c:forEach items="${folder.TAssignments }" var="assignment" varStatus="i">
			                                    	<c:if test="${count < 6}">
				                                    	<div>
						                    				<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${assignment.id}" name='checknames' value='assignment${assignment.id}'/>
						                                        	<label for="assignment${assignment.id}"></label>
						                                        </c:if>
						                                        <a id="${assignment.id}">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${assignment.title}</span>
						                                        </a>
						                                    </li>
											            </div>
					                                </c:if>
					                                <c:if test="${count > 5}">	
						                                <div class="hide">
						                                	<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${assignment.id}" name='checknames' value='assignment${assignment.id}'/>
						                                        	<label for="assignment${assignment.id}"></label>
						                                        </c:if>
						                                        <a id="${assignment.id}">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${assignment.title}</span>
						                                        </a>
						                                    </li>
						                                </div>
					                                </c:if>
					                                <c:set var="count" value="${count+1}"></c:set>
				                                </c:forEach>
				                            </c:if>
				                            </c:if>
		                                </c:forEach>
		                                <c:if test="${count > 6}">
			                            	<input id="uploadAssignmentCheck" type="button" onclick="checkMore('uploadAssignment');" value="显示更多" />
			                            </c:if>
	                    			</ul>
	                    		</div>
	                    	</li>
							
							<!-- 考勤 -->
							<li class="pic_list hg9  rel ovh">
	                    		<div class="pl40" id="uploadAttendence">
	                    			<span class="l b">考勤：</span>
	                    			<c:set var="count" value="0"></c:set>
	                    			<ul class="l w80p">
		                    			<c:forEach items="${chapter.wkFolders}" var="folder" varStatus="i">
			                    			<c:if test="${selectId==9||selectId==-1}">
				                    		<c:if test="${folder.type == 9&&folder.wkLesson==null}">
			                                    <c:forEach items="${folder.TAssignments }" var="assignment" varStatus="i">
			                                    	<c:if test="${count < 6}">
				                                    	<div>
						                    				<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${assignment.id}" name='checknames' value='assignment${assignment.id}'/>
						                                        	<label for="assignment${assignment.id}"></label>
						                                        </c:if>
						                                        <a id="${assignment.id}">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${assignment.title}</span>
						                                        </a>
						                                    </li>
											            </div>
					                                </c:if>
					                                <c:if test="${count > 5}">	
						                                <div class="hide">
						                                	<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${assignment.id}" name='checknames' value='assignment${assignment.id}'/>
						                                        	<label for="assignment${assignment.id}"></label>
						                                        </c:if>
						                                        <a id="${assignment.id}">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${assignment.title}</span>
						                                        </a>
						                                    </li>
						                                </div>
					                                </c:if>
					                                <c:set var="count" value="${count+1}"></c:set>
				                                </c:forEach>
				                            </c:if>
				                            </c:if>
		                                </c:forEach>
		                                <c:if test="${count > 6}">
			                            	<input id="uploadAttendenceCheck" type="button" onclick="checkMore('uploadAttendence');" value="显示更多" />
			                            </c:if>
	                    			</ul>
	                    		</div>
	                    	</li>
	                    </ul>
		                <c:if test="${flag==1}">
		                    <div class="add_mod pl30 pt20 pr20 ">
		                        <div class="add_box bd1 bgg hg9 cf lh30">
		                            <div class="l p20 bgc"> <i class="fa fa-plus mr5"></i>新增学习活动</div>
		
		                            <div class="newScroll wire_btn Lele l ml30 mt20 poi bgw" onclick="newVideoFolder(${chapter.type},${chapter.id},-1,-1)">
		                                视频
		                            </div>
		                            <div class="newScroll wire_btn Lele l ml30 mt20 poi bgw" onclick="newDocumentFolder(${chapter.type},${chapter.id},-1,-1,-1)">
		                                参考文件
		                            </div>
		                            <div class="newScroll wire_btn Lele l ml30 mt20 poi bgw" onclick="newImageFolder(${chapter.type},${chapter.id},-1,-1)">
		                                图片
		                            </div>
		                            <div class="newScroll wire_btn Lele l ml30 mt20 poi bgw" onclick="newAssignmentFolder(${chapter.type},${chapter.id},-1,-1,0)">
		                                作业
		                            </div>
		                            <div class="newScroll wire_btn Lele l ml30 mt20 poi bgw" onclick="newTestFolder(${chapter.type},${chapter.id},-1,-1,0)">
		                                考试
		                            </div>
		                            <div class="newScroll wire_btn Lele l ml30 mt20 poi bgw" onclick="newExamFolder(${chapter.type},${chapter.id},-1,-1,0)">
		                                测试
		                            </div>
		                            <div class="newScroll wire_btn Lele l ml30 mt20 poi bgw" onclick="newAttendenceFolder(${chapter.type},${chapter.id},-1,-1,0)">
		                                考勤
		                            </div>
		                        </div>
		                    </div>
		                </c:if>
	                </div>
           	<c:forEach items="${chapter.wkLessons}" var="lesson" varStatus="i">
                <div id="lessondiv${lesson.id}" class="module_con   pb20 bb">
                    <div class="lh60 pl30 f18 cf module_tit"> 
                         ${lesson.title} 
                         <c:if test="${flag==1}">
                         <i class="newScroll fa fa-edit g9 f14 mr5 poi" onclick="newWkLesson(${lesson.id},${chapter.id},'${tCourseSite.wkChapters}' )"></i>
                         </c:if>
                         <c:if test="${flag==1}">
                         <i class="fa fa-trash-o g9 f14 mr5 poi" onclick="deleteWkLesson(${lesson.id},${tCourseSite.id},${moduleType})"></i>
                    	 </c:if>
                    	 <p class="f14">内容概述：${lesson.content}</p>
                    </div>
		            <div class="module_con cl  mtb20">
		            	<ul class="">
	                    <!-- 分类显示六类学习活动 -->
	                    	<!-- 图片 -->
	                    	<li class="pic_list hg9  rel ovh">
	                    		<div class="pl40" id="lessonUploadImage">
	                    			<span class="l b">图片：</span>
	                    			<c:set var="count" value="0"></c:set>
	                    			<ul class="l w80p">
		                    			<c:forEach items="${lesson.wkFolders}" var="folder" varStatus="i">
			                    			<c:if test="${selectId==2||selectId==-1}">
				                    		<c:if test="${folder.type == 2}">
			                                    <c:forEach items="${folder.uploads }" var="image" varStatus="i">
			                                    	<c:if test="${count < 6}">
				                                    	<div>
						                    				<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="upload${image.id}" name='checknames' value='upload${image.id}'/>
						                                        	<label for="upload${image.id}"></label>
						                                        </c:if>
						                                        <a id="${image.id}" href="javascript:void(0);" onclick="imageLookByChapter('${chapter.id}','${lesson.id}','${count+1}');">
						                                        <c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(image.name,fn:indexOf(image.name,'.')+1,-1))}">
						                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(image.name,fn:indexOf(image.name,'.')+1,-1)}.gif"  alt="" />
						                                        </c:if>
						                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(image.name,fn:indexOf(image.name,'.')+1,-1))}">
						                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
						                                        </c:if>
						                                        <span>${image.name}</span>
						                                        </a>
						                                    </li>
											            </div>
					                                </c:if>
					                                <c:if test="${count > 5}">	
						                                <div class="hide">
						                                	<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="upload${image.id}" name='checknames' value='upload${image.id}'/>
						                                        	<label for="upload${image.id}"></label>
						                                        </c:if>
						                                        <a id="${image.id}" href="javascript:void(0);" onclick="imageLookByChapter('${chapter.id}','${lesson.id}','${count+1}');">
						                                        <c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(image.name,fn:indexOf(image.name,'.')+1,-1))}">
						                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(image.name,fn:indexOf(image.name,'.')+1,-1)}.gif"  alt="" />
						                                        </c:if>
						                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(image.name,fn:indexOf(image.name,'.')+1,-1))}">
						                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
						                                        </c:if>
						                                        <span>${image.name}</span>
						                                        </a>
						                                    </li>
						                                </div>
					                                </c:if>
					                                <c:set var="count" value="${count+1}"></c:set>
				                                </c:forEach>
				                            </c:if>
				                            </c:if>
		                                </c:forEach>
		                                <c:if test="${count > 6}">
			                            	<input id="lessonUploadImageCheck" type="button" onclick="checkMore('lessonUploadImage');" value="显示更多" />
			                            </c:if>
	                    			</ul>
	                    		</div>
	                    	</li>
	                    	
							<!-- 文件 -->
							<li class="pic_list hg9  rel ovh">
	                    		<div class="pl40" id="lessonUploadDocument">
	                    			<span class="l b">文件：</span>
	                    			<c:set var="count" value="0"></c:set>
	                    			<ul class="l w80p">
		                    			<c:forEach items="${lesson.wkFolders}" var="folder" varStatus="i">
			                    			<c:if test="${selectId==3||selectId==-1}">
				                    		<c:if test="${folder.type == 3 }">
			                                    <c:forEach items="${folder.uploads }" var="document" varStatus="i">
			                                    	<c:if test="${count < 6}">
				                                    	<div>
						                    				<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="upload${document.id}" name='checknames' value='upload${document.id}'/>
						                                        	<label for="upload${document.id}"></label>
						                                        </c:if>
						                                        <c:if test="${fn:contains(document.url,'.pdf')}">
							                                        <a href="${pageContext.request.contextPath}/tcoursesite/showFile?id=${document.id}" target="_blank">
								                                        <img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_pdf.gif"  alt="" />
								                                        ${document.name}</a>
							                                    </c:if>
							                                    <c:if test="${!fn:contains(document.url,'.pdf')}">
								                                    <c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(document.name,fn:indexOf(document.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(document.name,fn:indexOf(document.name,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(document.name,fn:indexOf(document.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
							                                    	${document.name}
							                                    </c:if>
						                                    </li>
											            </div>
					                                </c:if>
					                                <c:if test="${count > 5}">	
						                                <div class="hide">
						                                	<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="upload${document.id}" name='checknames' value='upload${document.id}'/>
						                                        	<label for="upload${document.id}"></label>
						                                        </c:if>
						                                        <c:if test="${fn:contains(document.url,'.pdf')}">
							                                        <a href="${pageContext.request.contextPath}/tcoursesite/showFile?id=${document.id}" target="_blank">
								                                        <img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_pdf.gif"  alt="" />
								                                        ${document.name}</a>
							                                    </c:if>
							                                    <c:if test="${!fn:contains(document.url,'.pdf')}">
								                                    <c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(document.name,fn:indexOf(document.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(document.name,fn:indexOf(document.name,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(document.name,fn:indexOf(document.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
							                                    	${document.name}
							                                    </c:if>
						                                    </li>
						                                </div>
					                                </c:if>
					                                <c:set var="count" value="${count+1}"></c:set>
				                                </c:forEach>
				                            </c:if>
				                            </c:if>
		                                </c:forEach>
		                                <c:if test="${count > 6}">
			                            	<input id="lessonUploadDocumentCheck" type="button" onclick="checkMore('lessonUploadDocument');" value="显示更多" />
			                            </c:if>
	                    			</ul>
	                    		</div>
	                    	</li>
		                    	
							<!-- 视频 -->
							<li class="pic_list hg9  rel ovh">
	                    		<div class="pl40" id="lessonUploadVideo">
	                    			<span class="l b">视频：</span>
	                    			<c:set var="count" value="0"></c:set>
	                    			<ul class="l w80p">
		                    			<c:forEach items="${lesson.wkFolders}" var="folder" varStatus="i">
			                    			<c:if test="${selectId==1||selectId==-1}">
				                    		<c:if test="${folder.type == 1 }">
			                                    <c:forEach items="${folder.uploads }" var="video" varStatus="i">
			                                    	<c:if test="${count < 6}">
				                                    	<div>
						                    				<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="upload${video.id}" name='checknames' value='upload${video.id}'/>
						                                        	<label for="upload${video.id}"></label>
						                                        </c:if>
						                                        <a id="${video.id}" href="javascript:void(0);" onclick="videoLook(${folder.id},${moduleType});">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(video.name,fn:indexOf(video.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(video.name,fn:indexOf(video.name,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(video.name,fn:indexOf(video.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${video.name}</span>
						                                        </a>
						                                    </li>
											            </div>
					                                </c:if>
					                                <c:if test="${count > 5}">	
						                                <div class="hide">
						                                	<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="upload${video.id}" name='checknames' value='upload${video.id}'/>
						                                        	<label for="upload${video.id}"></label>
						                                        </c:if>
						                                        <a id="${video.id}" href="javascript:void(0);" onclick="videoLook(${folder.id},${moduleType});">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(video.name,fn:indexOf(video.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(video.name,fn:indexOf(video.name,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(video.name,fn:indexOf(video.name,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${video.name}</span>
						                                        </a>
						                                    </li>
						                                </div>
					                                </c:if>
					                                <c:set var="count" value="${count+1}"></c:set>
				                                </c:forEach>
				                            </c:if>
				                            </c:if>
		                                </c:forEach>
		                                <c:if test="${count > 6}">
			                            	<input id="lessonUploadVideoCheck" type="button" onclick="checkMore('lessonUploadVideo');" value="显示更多" />
			                            </c:if>
	                    			</ul>
	                    		</div>
	                    	</li>
							
							<!-- 考试 -->
							<li class="pic_list hg9  rel ovh">
	                    		<div class="pl40" id="lessonUploadTest">
	                    			<span class="l b">考试：</span>
	                    			<c:set var="count" value="0"></c:set>
	                    			<ul class="l w80p">
		                    			<c:forEach items="${lesson.wkFolders}" var="folder" varStatus="i">
			                    			<c:if test="${selectId==6||selectId==-1}">
				                    		<c:if test="${folder.type == 6 }">
			                                    <c:forEach items="${folder.TAssignments }" var="test" varStatus="i">
			                                    	<c:if test="${count < 6}">
				                                    	<div>
						                    				<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${test.id}" name='checknames' value='assignment${test.id}'/>
						                                        	<label for="assignment${test.id}"></label>
						                                        </c:if>
						                                        <a id="${test.id}">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(test.title,fn:indexOf(test.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(test.title,fn:indexOf(test.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(test.title,fn:indexOf(test.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${test.title}</span>
						                                        </a>
						                                    </li>
											            </div>
					                                </c:if>
					                                <c:if test="${count > 5}">	
						                                <div class="hide">
						                                	<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${test.id}" name='checknames' value='assignment${test.id}'/>
						                                        	<label for="assignment${test.id}"></label>
						                                        </c:if>
						                                        <a id="${test.id}">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(test.title,fn:indexOf(test.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(test.title,fn:indexOf(test.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(test.title,fn:indexOf(test.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${test.title}</span>
						                                        </a>
						                                    </li>
						                                </div>
					                                </c:if>
					                                <c:set var="count" value="${count+1}"></c:set>
				                                </c:forEach>
				                            </c:if>
				                            </c:if>
		                                </c:forEach>
		                                <c:if test="${count > 6}">
			                            	<input id="lessonUploadTestCheck" type="button" onclick="checkMore('lessonUploadTest');" value="显示更多" />
			                            </c:if>
	                    			</ul>
	                    		</div>
	                    	</li>
							
							<!-- 测试 -->
							<li class="pic_list hg9  rel ovh">
	                    		<div class="pl40" id="lessonUploadExam">
	                    			<span class="l b">测试：</span>
	                    			<c:set var="count" value="0"></c:set>
	                    			<ul class="l w80p">
		                    			<c:forEach items="${lesson.wkFolders}" var="folder" varStatus="i">
			                    			<c:if test="${selectId==5||selectId==-1}">
				                    		<c:if test="${folder.type == 5 }">
			                                    <c:forEach items="${folder.TAssignments }" var="exam" varStatus="i">
			                                    	<c:if test="${count < 6}">
				                                    	<div>
						                    				<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${exam.id}" name='checknames' value='assignment${exam.id}'/>
						                                        	<label for="assignment${exam.id}"></label>
						                                        </c:if>
						                                        <a id="${exam.id}">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(exam.title,fn:indexOf(exam.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(exam.title,fn:indexOf(exam.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(exam.title,fn:indexOf(exam.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${exam.title}</span>
						                                        </a>
						                                    </li>
											            </div>
					                                </c:if>
					                                <c:if test="${count > 5}">	
						                                <div class="hide">
						                                	<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${exam.id}" name='checknames' value='assignment${exam.id}'/>
						                                        	<label for="assignment${exam.id}"></label>
						                                        </c:if>
						                                        <a id="${exam.id}">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(exam.title,fn:indexOf(exam.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(exam.title,fn:indexOf(exam.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(exam.title,fn:indexOf(exam.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${exam.title}</span>
						                                        </a>
						                                    </li>
						                                </div>
					                                </c:if>
					                                <c:set var="count" value="${count+1}"></c:set>
				                                </c:forEach>
				                            </c:if>
				                            </c:if>
		                                </c:forEach>
		                                <c:if test="${count > 6}">
			                            	<input id="lessonUploadExamCheck" type="button" onclick="checkMore('lessonUploadExam');" value="显示更多" />
			                            </c:if>
	                    			</ul>
	                    		</div>
	                    	</li>
							
							<!-- 作业 -->
							<li class="pic_list hg9  rel ovh">
	                    		<div class="pl40" id="lessonUploadAssignment">
	                    			<span class="l b">作业：</span>
	                    			<c:set var="count" value="0"></c:set>
	                    			<ul class="l w80p">
		                    			<c:forEach items="${lesson.wkFolders}" var="folder" varStatus="i">
			                    			<c:if test="${selectId==4||selectId==-1}">
				                    		<c:if test="${folder.type == 4 }">
			                                    <c:forEach items="${folder.TAssignments }" var="assignment" varStatus="i">
			                                    	<c:if test="${count < 6}">
				                                    	<div>
						                    				<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${assignment.id}" name='checknames' value='assignment${assignment.id}'/>
						                                        	<label for="assignment${assignment.id}"></label>
						                                        </c:if>
						                                        <a id="${assignment.id}">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${assignment.title}</span>
						                                        </a>
						                                    </li>
											            </div>
					                                </c:if>
					                                <c:if test="${count > 5}">	
						                                <div class="hide">
						                                	<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${assignment.id}" name='checknames' value='assignment${assignment.id}'/>
						                                        	<label for="assignment${assignment.id}"></label>
						                                        </c:if>
						                                        <a id="${assignment.id}">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${assignment.title}</span>
						                                        </a>
						                                    </li>
						                                </div>
					                                </c:if>
					                                <c:set var="count" value="${count+1}"></c:set>
				                                </c:forEach>
				                            </c:if>
				                            </c:if>
		                                </c:forEach>
		                                <c:if test="${count > 6}">
			                            	<input id="lessonUploadAssignmentCheck" type="button" onclick="checkMore('lessonUploadAssignment');" value="显示更多" />
			                            </c:if>
	                    			</ul>
	                    		</div>
	                    	</li>
							
							<!-- 考勤 -->
							<%--<li class="pic_list hg9  rel ovh">
	                    		<div class="pl40" id="lessonUploadAttendence">
	                    			<span class="l b">考勤：</span>
	                    			<c:set var="count" value="0"></c:set>
	                    			<ul class="l w80p">
		                    			<c:forEach items="${lesson.wkFolders}" var="folder" varStatus="i">
			                    			<c:if test="${selectId==9||selectId==-1}">
				                    		<c:if test="${folder.type == 9 }">
			                                    <c:forEach items="${folder.TAssignments }" var="assignment" varStatus="i">
			                                    	<c:if test="${count < 6}">
				                                    	<div>
						                    				<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${assignment.id}" name='checknames' value='assignment${assignment.id}'/>
						                                        	<label for="assignment${assignment.id}"></label>
						                                        </c:if>
						                                        <a id="${assignment.id}">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${assignment.title}</span>
						                                        </a>
						                                    </li>
											            </div>
					                                </c:if>
					                                <c:if test="${count > 5}">	
						                                <div class="hide">
						                                	<li class="l w33p">
						                    					<c:if test="${flag==1}">
						                                        	<input type="checkbox" id="assignment${assignment.id}" name='checknames' value='assignment${assignment.id}'/>
						                                        	<label for="assignment${assignment.id}"></label>
						                                        </c:if>
						                                        <a id="${assignment.id}">
						                                        	<c:if test="${fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_${fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1)}.gif"  alt="" />
							                                        </c:if>
							                                        <c:if test="${!fn:contains('.chm.doc.exe.jpg.mp3.mv.pdf.ppt.psd.rar.txt.xls',fn:substring(assignment.title,fn:indexOf(assignment.title,'.')+1,-1))}">
							                                        	<img src="${pageContext.request.contextPath}/images/fileTypeImages/icon_default.png"  alt="" />
							                                        </c:if>
						                                        <span>${assignment.title}</span>
						                                        </a>
						                                    </li>
						                                </div>
					                                </c:if>
					                                <c:set var="count" value="${count+1}"></c:set>
				                                </c:forEach>
				                            </c:if>
				                            </c:if>
		                                </c:forEach>
		                                <c:if test="${count > 6}">
			                            	<input id="lessonUploadAttendenceCheck" type="button" onclick="checkMore('lessonUploadAttendence');" value="显示更多" />
			                            </c:if>
	                    			</ul>
	                    		</div>
	                    	</li>
	                    --%></ul>
	                    
		                <c:if test="${flag==1}">
		                    <div class="add_mod pl30 pt20 pr20 ">
		                        <div class="add_box bd1 bgg hg9 cf lh30">
		                            <div class="l p20 bgc"> <i class="fa fa-plus mr5"></i>新增学习活动</div>
		
		                            <div class="newScroll wire_btn Lele l ml30 mt20 poi bgw" onclick="newVideoFolder(${chapter.type},${chapter.id},${lesson.id},-1)">
		                               	 视频
		                            </div>
		                            <div class="newScroll wire_btn Lele l ml30 mt20 poi bgw" onclick="newDocumentFolder(${chapter.type},${chapter.id},${lesson.id},-1,-1)">
		                                	参考文件
		                            </div>
		                            <div class="newScroll wire_btn Lele l ml30 mt20 poi bgw" onclick="newImageFolder(${chapter.type},${chapter.id},${lesson.id},-1)">
		                               	 图片
		                            </div>
		                            <div class="newScroll wire_btn Lele l ml30 mt20 poi bgw" onclick="newAssignmentFolder(${chapter.type},${chapter.id},${lesson.id},-1,0)">
		                               	 作业
		                            </div>
		                            <div class="newScroll wire_btn Lele l ml30 mt20 poi bgw" onclick="newTestFolder(${chapter.type},${chapter.id},${lesson.id},-1,0)">
		                               	 考试
		                            </div>
		                            <div class="newScroll wire_btn Lele l ml30 mt20 poi bgw" onclick="newExamFolder(${chapter.type},${chapter.id},${lesson.id},-1,0)">
	                                	测试
	                            	</div>
		                        </div>
		                    </div>
		                </c:if>
		                
		                </div>
		                </div>
	                </c:forEach>
	            </div>
	            </form:form>
            </c:if>
        </c:forEach>
            
            
            <c:if test="${flag==1}">
            <div class="add_section p20 bgg mt20">
                <div class="add_box newScroll bd1c bgc1 p10 cf lh30 f14 c_c1 poi Lele" onclick="newWkChapter(-1)">
                    <i class="fa fa-plus mr5 c_c"></i>新增章节
                </div>
            </div>
            </c:if>
        </div>

    </div>
    <div class="window_box hide fix zx2  " id="newWkLesson">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p20 bb f16">新增学习单元</div>
            <div class="add_con p20">
            <form:form action="${pageContext.request.contextPath}/tcoursesite/saveLesson?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}" method="POST" modelAttribute="wkLesson">
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
	                    <div class="l w45p f14 mt20">
	                       	 内容概述
	                        <form:textarea type="text" path="content" id="lessoncContent" class=" w100p b1 br3 h30 lh30 mt5 plr5"></form:textarea>
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
            <form:form action="${pageContext.request.contextPath}/tcoursesite/saveChapter?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}" method="POST" modelAttribute="wkChapter">
                <div class="add_module cf">
                	<form:input path="id" id="chapterId" style="display:none;"/>
                	<form:input path="seq" id="chapterSeq" style="display:none;"/>
                	<form:input path="TCourseSite.id" id="chapterId" style="display:none;" value="${tCourseSite.id}"/>
                    <div class="l w45p f14 mt20">
                        名称
                        <form:input type="text" path="name" id="chapterName" class=" w100p b1 br3 h30 lh30 mt5 plr5" />
                    </div>                   
                    <div class="r w45p f14 mt20">
                        内容概述
                        <form:input type="text" path="content" id="chapterContent" class=" w100p b1 br3 h30 lh30 mt5 plr5" />
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
    <form:form action="${pageContext.request.contextPath}/tcoursesite/saveVideoFolder?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}" 
    method="POST"  onsubmit="return submitVideoFolder()" modelAttribute="videoFolder">
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
 			<div class="w55p ma  red" >注意：请上传小于50M的MP4格式视频（上传格式为 MP4 h.264）</div>
 			<div class="w55p ma"><lable>当前视频：</lable><lable id="videoName">无</lable></div>
        </div>
        <!-- 导入视频的div -->	     		
        <div class=" tab_list input-group cf hide" >
        	<form:input path="videoUrl" class="form-control l w45p lh25 br3 b1" id="videoUrl" placeholder="只支持优酷" />
        	
            <button type="button" onclick="convert_url()" class="lh25 l br3 b1 ml10">导入</button>
        	
       	</div>
       	</div>
       	<div class="cf pb20">
	       	<div class="l w45p">
	       	名字：<form:input path="name" id="videoFolderName" required="required" class=" w100p b1 br3 h30 lh30 mt5 plr5"/><br>
	       	</div>
	 	    <div class=" w45p f14 r mt10 cf" id="">
	                分类
               <form:select path="wkChapter.type" id="videoChapterType" onchange="changeVideoChaptersByModuleType(${tCourseSite.id},this.value)" class=" w100p lh30 br3 b1 mt5 select_chosen" >
                   <form:option value="1">知识</form:option>
                   <form:option value="2">技能</form:option>
                   <form:option value="3">体验</form:option>
               </form:select>
	
	        </div>
        </div>
	    <div class="cf  pb20">
       	<div id="belongToChapterVideo" class="l w45p">
       	章节：<form:select path="wkChapter.id" id="wkChapterVideoId" 
       	onchange="changeVideoLessonsByChapterId(this.value)" class="w100p lh25 br3 b1 select_chosen" >
       				<form:option value="-1">请选择</form:option>
       		<c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
       			<c:if test="${chap.type == moduleType}">
       				<form:option value="${chap.id}">${chap.name}</form:option>
       			</c:if>
       		</c:forEach>
       	</form:select>
       	</div>
       	<div id="belongToLessonVideo" class="r w45p">
       	课时：<form:select path="wkLesson.id" id="wkLessonVideoId" class="w100p lh25 br3 b1 select_chosen">
       				<form:option value="-1">请选择</form:option>
       		<c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
       			<c:if test="${chap.type == moduleType}">
	       			<c:forEach items="${chap.wkLessons}" var="less" varStatus="j">
	       				<form:option value="${less.id}">${less.title}</form:option>
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
            <form:form action="${pageContext.request.contextPath}/tcoursesite/saveImageFolder?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}" 
            method="POST" onsubmit="return submitImageFolder()" modelAttribute="imageFolder">
                <div class="add_module cf">
                    <div class="cf bb pb20">
                    	<form:input path="id" id="imageFolderId" style="display:none;"/>
                        <div class=" w45p f14 l mt10">
                            名称
                            <form:input type="text" path="name" id="imageFolderName" class=" w100p b1 br3 h30 lh30 mt5 plr5" />
                        </div>
                        <div class=" w45p f14 r mt10 cf" id="">
                             分类
                            <form:select path="wkChapter.type" id="imageChapterType" onchange="changeImageChaptersByModuleType(${tCourseSite.id},this.value)" class=" w100p lh30 br3 b1 mt5 select_chosen" >
                                <form:option value="1">知识</form:option>
                                <form:option value="2">技能</form:option>
                                <form:option value="3">体验</form:option>
                            </form:select>

                        </div>
                    </div>
                    <div class="cf  pb20">
                        <div class=" w45p f14 l mt10" id="belongToChapterImage">
                            章节
                            <form:select path="wkChapter.id" id="wkChapterImageId" 
                            onchange="changeImageLessonsByChapterId(this.value)" class="w100p lh30 br3 b1 mt5 select_chosen">
                                		<form:option value="-1">请选择</form:option>
                                <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
                                	<c:if test="${chap.type == moduleType}">
                                		<form:option value="${chap.id}">${chap.name}</form:option>
                                	</c:if>
                                </c:forEach>
                            </form:select>

                        </div>
                        <div class=" w45p f14 r mt10 " id="belongToLessonImage">
                            课时
                            <form:select path="wkLesson.id" id="wkLessonImageId" class="w100p lh30 br3 b1 mt5 select_chosen">
                                		<form:option value="-1">请选择</form:option>
                                <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
                                	<c:if test="${chap.type == moduleType}">
						       			<c:forEach items="${chap.wkLessons}" var="less" varStatus="j">
						       				<form:option value="${less.id}">${less.title}</form:option>
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
                    <ul class="" id="picResourceList">
                        <c:forEach items="${wkImagesUploadList }" var="pic" varStatus="i">
 	                        <li class="pic_list hg9 lh35 ptb5 rel ovh">
 	                            <div class="cf rel zx1 z c_category">
 	                                <input class="l check_box" type='checkbox' id='upload${pic.id}' name='checkname' value='${pic.id }' />
 	                                <label class="l mt10" for="upload${pic.id}"></label>
 	                                <i class="fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
 	                                <div class="l mlr15 cc1 c_tool poi">${pic.name }</div>
 	                                <div class="accessory_info l f12 g9"><fmt:formatDate value="${pic.upTime.time }" pattern="yyyy-MM-dd"></fmt:formatDate> 大小： ${pic.size }</div>
 	                            </div>
 	                         </li>
 		                        
	                    </c:forEach>
                       	<a href="javascript:void(0)" onclick="firstPage(1,1);">首页</a>
                       	<a href="javascript:void(0)" onclick="previousPage(1,1);">上一页</a>
                       	<a href="javascript:void(0)" onclick="nextPage(1,1,${pageModel1.totalPage});">下一页</a>
                       	<a href="javascript:void(0)" onclick="lastPage(1,${pageModel1.totalPage});">末页</a>
                       	当前第 1 页 共 ${pageModel1.totalPage } 页 ${pageModel1.totalRecords } 条记录

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
            <form:form action="${pageContext.request.contextPath}/tcoursesite/saveDocumentFolder?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}" 
            method="POST" onsubmit="return submitDocumentFolder()" modelAttribute="documentFolder">
                <div class="add_module cf">
                    <div class="cf  pb20">
                    	<form:input path="id" id="documentFolderId" style="display:none;"/>
                        <div class=" w45p f14 l mt10 cf">
                            名称
                            <form:input type="text" path="name" id="documentFolderName" class=" w100p b1 br3 h30 lh30 mt5 plr5" />
                        </div>
                        <div class=" w45p f14 r mt10 cf" id="">
                            分类
                            <form:select path="wkChapter.type" id="documentChapterType" onchange="changeDocumentChaptersByModuleType(${tCourseSite.id},this.value)" class=" w100p lh30 br3 b1 mt5 select_chosen" style="display: none;">
                                <form:option value="1">知识</form:option>
                                <form:option value="2">技能</form:option>
                                <form:option value="3">体验</form:option>
                            </form:select>

                        </div>
                    </div>
                    <div class="cf  pb20">
                        <div class=" w45p f14 l mt10 cf" id="belongToChapterDocument">
                            章节
                            <form:select path="wkChapter.id" id="wkChapterDocumentId" onchange="changeDocumentLessonsByChapterId(this.value)" class="w100p lh30 br3 b1 mt5 select_chosen">
                                		<form:option value="-1">请选择</form:option>
                                <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
                                	<c:if test="${chap.type == moduleType}">
                                		<form:option value="${chap.id}">${chap.name}</form:option>
                                	</c:if>
                                </c:forEach>
                            </form:select>

                        </div>
                        <div class=" w45p f14 r mt10 cf" id="belongToLessonDocument">
                            课时
                            <form:select path="wkLesson.id" id="wkLessonDocumentId" class="w100p lh30 br3 b1 mt5 select_chosen">
                                		<form:option value="-1">请选择</form:option>
                                <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
                                	<c:if test="${chap.type == moduleType}">
						       			<c:forEach items="${chap.wkLessons}" var="less" varStatus="j">
						       				<form:option value="${less.id}">${less.title}</form:option>
						       			</c:forEach>
					       			</c:if>
					       		</c:forEach>
                            </form:select>

                        </div>
                    </div>
                    <%--<div class="cf bb pb20">
                        <div class=" w45p f14 l mt10 cf" id="belongToFolderDocument">
                            所属文件夹
                            <form:select path="wkChapter.id" id="wkFolderDocumentId" class="w100p lh30 br3 b1 mt5 select_chosen">
                                <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
                                		<form:option value="-1">请选择</form:option>
                                	<c:if test="${chap.type == moduleType}">
                                		<form:option value="${chap.id}">${chap.name}</form:option>
                                	</c:if>
                                </c:forEach>
                            </form:select>

                        </div>
                    </div>
                    --%><div class="tab_box">
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
                    <ul class="" id="fileResourceList">
                        <c:forEach items="${wkFilesUploadList }" var="file" varStatus="i">
	                        <li class="pic_list hg9 lh35 ptb5 rel ovh">
	                            <div class="cf rel zx1 z c_category">
	                                <input class="l check_box" type='checkbox' id='upload${file.id}' name='checkname' value='${file.id }' />
	                                <label class="l mt10" for="upload${file.id}"></label>
	                                <i class="fa  fa-file-picture-o mt5 w24 h24 brh lh24 tc l wh bgb"></i>
	                                <div class="l mlr15 cc1 c_tool poi">${file.name }</div>
	                                <div class="accessory_info l f12 g9"><fmt:formatDate value="${file.upTime.time }" pattern="yyyy-MM-dd"></fmt:formatDate> 大小： ${file.size }</div>
	                            </div>
	                         </li>
		                        
	                    </c:forEach>
                       	<a href="javascript:void(0)" onclick="firstPage(2,1);">首页</a>
                       	<a href="javascript:void(0)" onclick="previousPage(2,1);">上一页</a>
                       	<a href="javascript:void(0)" onclick="nextPage(2,1,${pageModel2.totalPage});">下一页</a>
                       	<a href="javascript:void(0)" onclick="lastPage(2,${pageModel2.totalPage});">末页</a>
                       	当前第 1 页 共 ${pageModel2.totalPage } 页 ${pageModel2.totalRecords } 条记录
                        
                       

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
		<form:form action="${pageContext.request.contextPath}/tcoursesite/saveAssignment?
		tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}" method="POST" 
		modelAttribute="tAssignment" enctype="multipart/form-data" onsubmit="return submitAssignment()">
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
				<div class="cf  pb20">
				<div class=" w45p f14 l mt10">
			    	<label >作业标题<font color="red">*</font>：</label>
			    	<form:input path="title" id="tAssignmentTitle" required="true" type="text" class=" w100p b1 br3 h30 lh30 mt5 plr5" />
			    	<form:hidden path="id" id="tAssignmentId" />
			    	<form:hidden path="TAssignmentAnswerAssign.id" id="tAssignmentAnswerAssignId" />
			    	<form:hidden path="TAssignmentControl.id" id="tAssignmentControlId" />
				</div>	
				<div class=" w45p f14 r mt10 cf" id="">
                            分类
                    <form:select path="wkFolder.wkChapter.type" id="assignmentChapterType" onchange="changeAssignmentChaptersByModuleType(${tCourseSite.id},this.value)" class=" w100p lh30 br3 b1 mt5 select_chosen" >
                        <form:option value="1">知识</form:option>
                        <form:option value="2">技能</form:option>
                        <form:option value="3">体验</form:option>
                    </form:select>

                </div>
            	</div>
                    
				<div class="cf">
				<div class=" w45p f14 l mt10" id="belongToChapterTAssignment">
                            章节
                   <form:select path="wkFolder.wkChapter.id" id="tAssignmentWkChapterId" 
                   onchange="changeAssignmentLessonsByChapterId(this.value)" class="w100p lh30 br3 b1 mt5 select_chosen">
                   			<form:option value="-1">请选择</form:option>
                       <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
                       		<c:if test="${chap.type == moduleType}">
                       			<form:option value="${chap.id}">${chap.name}</form:option>
                       		</c:if>
                       </c:forEach>
                   </form:select>

               </div>
               <div class=" w45p f14 r mt10 " id="belongToLessonTAssignment">
                   课时
                   <form:select path="wkFolder.wkLesson.id" id="tAssignmentWkLessonId" class="w100p lh30 br3 b1 mt5 select_chosen">
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
					 <input type="text" name="startdateAssignment" id="TAssignmentControl_startdate" value="${startdate }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly" required="required" class="Wdate w100p b1 br3 h30 lh30 mt5 plr5 " />
			 	</div>
			 	<div class="r w45p f14 mt10 rel">
				     <label >截止时间<font color="red">*</font>：</label>
					 <input type="text" name="duedateAssignment" id="TAssignmentControl_duedate" value="${duedate }"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly" required="required"class="Wdate w100p b1 br3 h30 lh30 mt5 plr5 rel"  />
			 	</div>
			 	</div>
			  	<div class="l w100p f14 l mt10 hide">
				   	<div>
		     			<label>作业布置人：</label>
		     			<form:input path="TAssignmentAnswerAssign.user.username" id="tAssignmentUsername" placeholder="请输入工号/学号" class=" w100p b1 br3 h30 lh30 mt5 plr5"/>
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
		     			<form:radiobutton path="TAssignmentControl.toGradebook" name="TAssignmentControlToGradebook" value="yes"  id="TAssignmentControlToGradebookYes" checked="checked"/><label for="TAssignmentControlToGradebookYes">将作业添加到成绩簿</label> 
		     			<form:radiobutton path="TAssignmentControl.toGradebook" name="TAssignmentControlToGradebook" value="no"  id="TAssignmentControlToGradebookNo"/><label for="TAssignmentControlToGradebookNo">不要将作业加入到成绩簿中 </label><br>
		     			<form:radiobutton path="TAssignmentControl.gradeToStudent" name="TAssignmentControlGradeToStudent" value="yes" id="TAssignmentControlGradeToStudentYes" checked="checked"/><label for="TAssignmentControlGradeToStudentYes">将成绩公布给学生 </label>
		     			<form:radiobutton path="TAssignmentControl.gradeToStudent" name="TAssignmentControlGradeToStudent" value="no" id="TAssignmentControlGradeToStudentNo" /><label for="TAssignmentControlGradeToStudentNo">不将成绩公布给学生</label><br>
		     			<form:radiobutton path="TAssignmentControl.gradeToTotalGrade" name="TAssignmentControlGradeToTotalGrade" value="yes" id="TAssignmentControlGradeToTotalGradeYes" checked="checked"/><label for="TAssignmentControlGradeToTotalGradeYes">将成绩计入总成绩</label>
		     			<form:radiobutton path="TAssignmentControl.gradeToTotalGrade" name="TAssignmentControlGradeToTotalGrade" value="no" id="TAssignmentControlGradeToTotalGradeNo" /><label for="TAssignmentControlGradeToTotalGradeNo">不将成绩计入总成绩</label>
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
		     			<form:select class="select_search" path="TAssignmentControl.submitType" id="TAssignmentControlSubmitType" style="width:250px;" required="required">
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
		     			<textarea id="assignmentContent" path="content" name="content" class=" w100p b1 br3 h30 lh30 mt5 plr5">${tAssignment.content }</textarea>
				 	</div>
			 	</div>
			 	<form:hidden path="type"  value="assignment"/>
			 	<form:hidden path="siteId" id="assignmentSiteId" value="${tCourseSite.id}"/>
			 	<form:hidden path="status" id="assignmentStatus" value="0"/>
			 	<form:hidden path="createdTime" id="assignmentCreatedTime" />
			 	<form:hidden path="wkFolder.id" id="assignmentWkFolderId" />
	 	        <form:hidden path="user.username" id="assignmentuserusername" />
			</div>
			<div class="moudle_footer cf tc">
		        <div class="submit_link">
		        	<input class="btn bgb l mt10 poi  plr20 br3 wh" id="save" type="submit" value="发布" onclick="$('#assignmentStatus').val(1)">
		        	<input class="btn  bgc l ml30 mt10 poi plr20 br3" id="save" type="submit" value="保存草稿" onclick="$('#assignmentStatus').val(0)">
					<div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
                       	 取消
                    </div>
                    <input type="reset" name="reset"  />
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
                
			<form:form action="${pageContext.request.contextPath}/tcoursesite/saveTest?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}" 
			method="POST" modelAttribute="tAssignment" enctype="multipart/form-data" onsubmit="return checkForm()">
                <!--考试-->
			    	
                <div class="add_module cf ">
                    <div class="cf plr10  bb2 ptb20 ">
                    <div class="cf">
                        <div class=" w45p f14 l ">
                            名称
                            <form:input path="title" id="testTitle" required="true" type="text" class=" w97p b1 br3 h30 lh30 mt5 plr5" />
                        </div>
                        <div class=" w45p f14 r mt10 cf" id="">
                            分类
                            <form:select path="wkFolder.wkChapter.type" id="testChapterType" onchange="changeTestChaptersByModuleType(${tCourseSite.id},this.value)" class=" w100p lh30 br3 b1 mt5 select_chosen">
                                <form:option value="1">知识</form:option>
                                <form:option value="2">技能</form:option>
                                <form:option value="3">体验</form:option>
                            </form:select>

                        </div>
                    </div>
                    <div class="cf  pb20">
		                <div class=" w45p f14 l mt10" id="belongToChapterTest">
		                            章节
		                   <form:select path="wkFolder.wkChapter.id" id="testWkChapterId" 
		                   onchange="changeTestLessonsByChapterId(this.value)" class="w100p lh30 br3 b1 mt5 select_chosen">
		                   			<form:option value="-1">请选择</form:option>
		                       <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
		                       		<c:if test="${chap.type == moduleType}">
		                       			<form:option value="${chap.id}">${chap.name}</form:option>
		                       		</c:if>
		                       </c:forEach>
		                   </form:select>
		               </div>
		               <div class=" w45p f14 r mt10 " id="belongToLessonTest">
		                   课时
		                   <form:select path="wkFolder.wkLesson.id" id="testWkLessonId" class="w100p lh30 br3 b1 mt5 select_chosen">
		                   				<form:option value="-1">请选择</form:option>
		                       <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
		                       		<c:if test="${chap.type == moduleType}">
							   			<c:forEach items="${chap.wkLessons}" var="less" varStatus="j">
							   				<form:option value="${less.id}">${less.title}</form:option>
							   			</c:forEach>
						   			</c:if>
						   		</c:forEach>
		                   </form:select>
		               </div>
               		</div>
               			<div class="cf">
	                        <div class=" w45p f14 l mt10 ">
	                            开始时间
	                            <input type="text" id="testTAssignmentControl_startdate" name="startdateTest" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"  value="${startdate }" readonly="readonly" required="required" class="Wdate w97p b1 br3 h30 lh30 mt5 plr5" />
	                        </div>
	                        <div class=" w45p f14 r  mt10">
	                            结束时间
	                            <input type="text" id="testTAssignmentControl_duedate" name="duedateTest" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" value="${duedate }" readonly="readonly" required="required"class="Wdate w97p b1 br3 h30 lh30 mt5 plr5" />
	                        </div>
                        </div>
                        <div class="l w100p f14 l mt10 hide">
						   	<div>
				     			<label>考试布置人：</label>
				     			<form:input path="TAssignmentAnswerAssign.user.username" id="testUsername" placeholder="请输入工号/学号" class=" w100p b1 br3 h30 lh30 mt5 plr5"/>
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

                    <div class="cf plr10 pb20 bb2">
                        <div class="f14 mt10">
                            <!--试题组成-->
                            <table class="w100p question_tab">
                                <tr>
                                   
                                        <div>
                                        	<table id="item" style="width: 95%">
                                        		<thead>
	                                        		<tr>
	                                        			<td>试题大项</td>
	                                        			<td>试题类型</td>
	                                        			<td>所属题库</td>
	                                        			<td>试题数量</td>
	                                        			<td>试题分值(仅用于该次考试)</td>
	                                        			<td>操作</td>
	                                        		</tr>
                                        		</thead>
                                        		<tbody id="itemBody">
	                                        		<c:forEach items="${tAssignment.TAssignmentItemComponents }" var="itemComponent">
	                                        			<tr>
<!-- 	                                        				 <th> -->
<!--                                                                 <input class=" check_box" type='checkbox' id='singleTest' name='singleTest' value='0' /> -->
<!--                                                                 <label class="asd l" for="singleTest">单选题</label> -->
<!--                                                              </th> -->
	                                        				<td>
	                                        					<input type="text" name="sectionName" style="width:100px;" value="${itemComponent.sectionName }"/>
	                                        				</td>
	                                        				<td>
	                                        				    <input type="hidden" name="itemTypeTest" value="4"/> 
	                                        				    <input type="hidden" name="multipleTest" value="1"/> 
	                                        				    <input type="hidden" name="judgeTest" value="2"/> 
	                                        				    <input type="hidden" name="blanksTest" value="8"/> 
	                                        					<input type="hidden" name="questionIdTest" style="width:50px;"  value="${itemComponent.TAssignmentQuestionpool.questionpoolId }" class="easyui-numberbox" />
	                                        					${itemComponent.TAssignmentQuestionpool.title }
	                                        				</td>
	                                        				<td>
	                                        					<input type="text" name="itemQuantityTest" disabled="true" value="0" class=" w50 b1 br3" /> 
	                                        				</td>
	                                        				<td>
	                                        					<input type="text" name="itemScoreTest"  disabled="true" value="0" class=" w50 b1 br3" /> -->
	                                        				</td>
	                                        				<td>
	                                        					<button type="button" class="btn" onclick="deleteThisTr(this)">删除</button>
	                                        				</td>
	                                        				
	                                        			</tr>
	                                        		</c:forEach>
	                                       			<tr>
	                                       				<td><button class="btn" type="button" onclick="addTr()">添加</button></td>
	                                       				<td></td>
	                                       				<td></td>
	                                       				<td></td>
	                                       			</tr>
                                        		</tbody>
                                        	</table>
								 	    </div>	
							 	     </fieldset>
                                </tr>
                                
<!--                                 <tr> -->
<!--                                     <th> -->
<!--                                         <input class=" check_box" type='checkbox' id='singleTest' name='singleTest' value='0' /> -->
<!--                                         <label class="asd l" for="singleTest">单选题</label> -->
<!--                                     </th> -->
<!--                                     <td> -->
<!--                                     	<input type="hidden" name="itemTypeTest" value="4"/>  -->
<!--                                         <input type="text" name="itemQuantityTest" disabled="true" value="0" class=" w50 b1 br3" /> -->
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         <input type="text" name="itemScoreTest"  disabled="true" value="0" class=" w50 b1 br3" /> -->
<!--                                     </td> -->
                                    
<!--                                 </tr> -->
<!--                                 <tr> -->
<!--                                     <th> -->
<!--                                         <input class=" check_box" type='checkbox' id='multipleTest' name='multipleTest' value='0' /> -->
<!--                                         <label class="asd l" for="multipleTest">多选题</label> -->
<!--                                     </th> -->
<!--                                     <td> -->
<!--                                     	<input type="hidden" name="itemTypeTest" value="1"/>  -->
<!--                                         <input type="text" name="itemQuantityTest" disabled="true" value="0" class=" w50 b1 br3" /> -->
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         <input type="text" name="itemScoreTest"  disabled="true" value="0" class=" w50 b1 br3" /> -->
<!--                                     </td> -->
                                    
<!--                                 </tr> -->
<!--                                 <tr> -->
<!--                                     <th> -->
<!--                                         <input class=" check_box" type='checkbox' id='judgeTest' name='judgeTest' value='0' /> -->
<!--                                         <label class="asd l" for="judgeTest">对错题</label> -->
<!--                                     </th> -->
<!--                                     <td> -->
<!--                                     	<input type="hidden" name="itemTypeTest" value="2"/>  -->
<!--                                         <input type="text" name="itemQuantityTest" disabled="true" value="0" class=" w50 b1 br3" /> -->
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         <input type="text" name="itemScoreTest"  disabled="true" value="0" class=" w50 b1 br3" /> -->
<!--                                     </td> -->
                                    
<!--                                 </tr> -->
<!--                                 <tr> -->
<!--                                     <th> -->
<!--                                         <input class=" check_box" type='checkbox' id='blanksTest' name='blanksTest' value='0' /> -->
<!--                                         <label class="asd l" for="blanksTest">填空题</label> -->
<!--                                     </th> -->
<!--                                     <td> -->
<!--                                     	<input type="hidden" name="itemTypeTest" value="8"/>  -->
<!--                                         <input type="text" name="itemQuantityTest" disabled="true" value="0" class=" w50 b1 br3" /> -->
<!--                                     </td> -->
<!--                                     <td> -->
<!--                                         <input type="text" name="itemScoreTest"  disabled="true" value="0" class=" w50 b1 br3" /> -->
<!--                                     </td> -->
                                    
<!--                                 </tr> -->
                            </table>
                        </div>
                    </div>
                    <div class="cf plr10 ptb20 bb2 pb20">
                        <div class="f14">
                            考试描述
                            <form:textarea id="testContent" path="content" name="contentTest" class=" w100p b1 br3 h30 lh30 mt5 plr5"/>
                        </div>
                    </div>

							<form:hidden path="type" value="test"/>
							<form:hidden path="siteId" id="testSiteId" value="${tCourseSite.id}"/>
						 	<form:hidden path="status" id="testStatus" value="0"/>
						 	<form:hidden path="createdTime" id="testCreatedTime"/>
				 	        <form:hidden path="user.username" id="testUserUsername"/>
						    <form:hidden path="id" id="testId"/>
						    <form:hidden path="wkFolder.id" id="testWkFolderId"/>
						    <form:hidden path="TAssignmentControl.id" id="testControlId"/>
						    <form:hidden path="TAssignmentAnswerAssign.id" id="testAnswerAssignId"/>
						    <form:hidden path="TAssignmentControl.timelimit" value="1" id="testtimelimitselect"/>
                </div>
                <div class="cf tc plr10 ptb20">

					<input class="btn bgb l mt10 poi  plr20 br3 wh" id="save" type="submit" value="发布" onclick="$('#testStatus').val(1);return confirm('是否确认发布？')">
		        	<input class="btn bgc l ml30 mt10 poi plr20 br3" id="save" type="submit" value="保存草稿" onclick="$('#testStatus').val(0)">

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
                
			<form:form action="${pageContext.request.contextPath}/tcoursesite/saveExam?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}" 
			method="POST" modelAttribute="tAssignment" onsubmit="return submitExam()" enctype="multipart/form-data" >
                <!--测试-->
			    	
                <div class="add_module cf ">
                    <div class="cf plr10  bb2 ptb20 ">
                    <div class="cf">
                        <div class=" w45p f14 l ">
                            	名称
                            <form:input path="title" id="examTitle" required="true" type="text" class=" w97p b1 br3 h30 lh30 mt5 plr5" />
                        </div>
                        
                        <div class=" w45p f14 r mt10 cf" id="">
                            分类
	                    <form:select path="wkFolder.wkChapter.type" id="examChapterType" onchange="changeExamChaptersByModuleType(${tCourseSite.id},this.value)" class=" w100p lh30 br3 b1 mt5 select_chosen" >
	                        <form:option value="1">知识</form:option>
	                        <form:option value="2">技能</form:option>
	                        <form:option value="3">体验</form:option>
	                    </form:select>
	
	                	</div>
	            	</div>
                    
					<div class="cf">
		                <div class=" w45p f14 l mt10" id="belongToChapterExam">
		                            	章节
		                   <form:select path="wkFolder.wkChapter.id" id="examWkChapterId" 
		                   onchange="changeExamLessonsByChapterId(this.value)" class="w100p lh30 br3 b1 mt5 select_chosen">
		                   			<form:option value="-1">请选择</form:option>
		                       <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
		                       		<c:if test="${chap.type == moduleType}">
		                       			<form:option value="${chap.id}">${chap.name}</form:option>
		                       		</c:if>
		                       </c:forEach>
		                   </form:select>
		               </div>
		               <div class=" w45p f14 r mt10 " id="belongToLessonExam">
		                   课时
		                   <form:select path="wkFolder.wkLesson.id" id="examWkLessonId" class="w100p lh30 br3 b1 mt5 select_chosen">
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
	                            <input type="text" id="examTAssignmentControl_startdate" name="startdateExam" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"  value="${startdate }" readonly="readonly" required="required" class="Wdate w97p b1 br3 h30 lh30 mt5 plr5" />
	                        </div>
	                        <div class=" w45p f14 r  mt10">
	                            结束时间
	                            <input type="text" id="examTAssignmentControl_duedate" name="duedateExam" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" value="${duedate }" readonly="readonly" required="required"class="Wdate w97p b1 br3 h30 lh30 mt5 plr5" />
	                        </div>
                        </div>
                        <div class="l w100p f14 l mt10 hide">
						   	<div>
				     			<label>测试布置人：</label>
				     			<form:input path="TAssignmentAnswerAssign.user.username" id="examUsername" placeholder="请输入工号/学号" class=" w100p b1 br3 h30 lh30 mt5 plr5"/>
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
                                    <form:radiobutton class=" check_box"  id='examTAssignmentControlToGradebookYes' name='checknameExam' path="TAssignmentControl.toGradebook" value="yes" checked="checked"/>
                                    <label class="l " for="examTAssignmentControlToGradebookYes">是</label>
                                </li>
                                <li class="l lh30">
                                    <form:radiobutton class=" check_box"  id='examTAssignmentControlToGradebookNo' name='checknameExam' path="TAssignmentControl.toGradebook" value="no" />
                                    <label class="l " for="examTAssignmentControlToGradebookNo">否</label>
                                </li>
                            </ul>
                            <ul class="cf">
                                <li class="l lh30 w180">将成绩公布给学生:</li>
                                <li class="l lh30 w80">
                                    <form:radiobutton class=" check_box"  id='examTAssignmentControlGradeToStudentYes' name='checkname1Exam' path="TAssignmentControl.gradeToStudent" value="yes" checked="checked" />
                                    <label class="l" for="examTAssignmentControlGradeToStudentYes">是</label>
                                </li>
                                <li class="l lh30">
                                    <form:radiobutton class=" check_box"  id='examTAssignmentControlGradeToStudentNo' name='checkname1Exam' path="TAssignmentControl.gradeToStudent" value="no" />
                                    <label class="l" for="examTAssignmentControlGradeToStudentNo">否 </label>
                                </li>
                            </ul>
                            <ul class="cf ">
                                <li class="l lh30 w180">将成绩计入总成绩:</li>
                                <li class="l lh30 w80">
                                    <form:radiobutton class=" check_box"  id='examTAssignmentControlGradeToTotalGradeYes' name='checkname2Exam' path="TAssignmentControl.gradeToTotalGrade" value="yes" checked="checked"/>
                                    <label class="l" for="examTAssignmentControlGradeToTotalGradeYes">是</label>
                                </li>
                                <li class="l lh30">
                                    <form:radiobutton class=" check_box"  id='examTAssignmentControlGradeToTotalGradeNo' name='checkname2Exam' path="TAssignmentControl.gradeToTotalGrade" value="no" />
                                    <label class="l" for="examTAssignmentControlGradeToTotalGradeNo">否 </label>
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
                            <form:textarea id="examContent" path="content" name="contentExam" class=" w100p b1 br3 h30 lh30 mt5 plr5"/>
                        </div>
                    </div>

							<form:hidden path="type" value="exam"/>
							<form:hidden path="siteId" id="examSiteId" value="${tCourseSite.id}"/>
						 	<form:hidden path="status" id="examStatus" value="0"/>
						 	<form:hidden path="createdTime" id="examCreatedTime"/>
				 	        <form:hidden path="user.username" id="examUserUsername"/>
						    <form:hidden path="id" id="examId"/>
						    <form:hidden path="wkFolder.id" id="examWkFolderId"/>
						    <form:hidden path="TAssignmentControl.id" id="examControlId"/>
						    <form:hidden path="TAssignmentAnswerAssign.id" id="examAnswerAssignId"/>
                </div>
                <div class="cf tc plr10 ptb20">

		        	<input class="btn bgc l ml30 mt10 poi plr20 br3" id="save" type="submit" value="保存" onclick="$('#examStatus').val(0)">

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
	            	<div><label id="imageNameLook">图片名字</label></div>
	            	<div><a href="javascript:void(0)" id="upImage" onclick="" disabled="true">上一幅</label>
	            		 <a href="javascript:void(0)" id="downImage" onclick="">下一幅</label></div>
	            </div>
	        </div>
	    </div>
    	<!--图片预览结束-->
    	
    	<!--视频预览开始-->
        <div class="window_box hide fix zx2  " id="videoLook">
	        <div class="window_video bgw b1 br3 rel bs10 ">
	            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20"  onclick="closeVideoLook()">x</span>
	            <div class="add_tit p20 bb f16">视频预览</div>
	            <div class="add_con p20 tc" id="mediaDisplay" >
	            	<label id="videoNameLook">视频名字</label>
	            </div>
	        </div>
	    </div>
    	<!--视频预览结束-->
<div id="addTr" class="easyui-window" title="试题详情" modal="true" dosize="true" maximizable="true" collapsible="true" minimizable="false" closed="true" iconcls="icon-add"  style="width:500px; height:400px;">
		<form action="">
			<table id="my_show">
				<tr>
					<td align="right">试题大项</td>
					<td>
						<input type="text" id="addTrSectionName" placeholder="请输入大项名称"/>
					</td>
				</tr>
				<tr>
					<td align="right">试题类型</td>
					<td>
						<select id="addTritemTypeTest" mutiple>
							<option value="4">单选题</option>
							<option value="1">多选题</option>
							<option value="2">判断题</option>
							<option value="8">填空</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">所属题库</td>
					<td>
						<!-- 题库 -->
						<select id="addTrquestionIdTest">
							<c:forEach items="${tAssignmentQuestionpools}" var="current">
								<option value="${current.questionpoolId }">${current.title }(${fn:length(current.TAssignmentItems)})</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">试题数量</td>
					<td>
						<input type="text" id="addTritemQuantityTest" placeholder="请输入数字" oninput="changeNumber(this)"/>
					</td>
				</tr>
				<tr>
					<td align="right">试题分值(仅用于该次考试)</td>
					<td>
						<input type="text" id="addTritemScoreTest" placeholder="请输入数字" oninput="changeNumber(this)"/>
					</td>
				</tr>
				<tr>
					<td align="right"></td>
					<td>
						<input type="button" value="确定" onclick="addTrRecord()"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
    <!--添加考勤开始-->
	<div id="newAttendenceFolder" class="window_box hide fix zx2  "  >
		<div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
             <div class="add_tit p10 bb f16">新增考勤</div>
            <div class="add_con p10">
		<form:form action="${pageContext.request.contextPath}/tcoursesite/saveAttendence?
		tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}" method="POST" 
		modelAttribute="tAssignment" enctype="multipart/form-data" onsubmit="return submitAttendence()">
    		<div class="w98p cf">	
    				<label >考勤名称<font color="red">*</font>：</label>
			    	<form:input path="title" id="tAttendenceTitle" required="true" type="text" class=" w100p b1 br3 h30 lh30 mt5 plr5" />			
			    	<form:hidden path="id" id="tAttendenceId" />
			    	<form:hidden path="TAssignmentAnswerAssign.id" id="tAttendenceAnswerAssignId" />
			    	<form:hidden path="TAssignmentControl.id" id="tAttendenceControlId" />				                   
				<div class="cf">	
				<div class=" w45p f14 l mt10" id="belongToChapterTAttendence">
                            章节
                   <form:select path="wkFolder.wkChapter.id" id="tAttendenceWkChapterId" 
                   onchange="changeAssignmentLessonsByChapterId(this.value)" class="w100p lh30 br3 b1 mt5 select_chosen" >
                   			<form:option value="-1">请选择</form:option>
                       <c:forEach items="${tCourseSite.wkChapters}" var="chap" varStatus="i">
                       		<c:if test="${chap.type == moduleType}">
                       			<form:option value="${chap.id}">${chap.name}</form:option>
                       		</c:if>
                       </c:forEach>
                   </form:select>

               </div>
               <div class=" w45p f14 r mt10 " id="belongToLessonTAttendence">
                   课时
                   <form:select path="wkFolder.wkLesson.id" id="tAttendenceWkLessonId" class="w100p lh30 br3 b1 mt5 select_chosen">
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
			 	<form:hidden path="type"  value="attendence"/>
			 	<form:hidden path="siteId" id="tAttendenceSiteId" value="${tCourseSite.id}"/>
			 	<form:hidden path="wkFolder.id" id="tAttendenceWkFolderId" />
	 	        <form:hidden path="user.username" id="tAttendenceuserusername" />
	 	        <form:hidden path="TAssignmentAnswerAssign.user.username" id="tAttendenceUsername" value="${user.username}" />
				 	
			</div>
			<div class="w100p f14 l mt10">
				   	<div>
				   		<!--默认作业配置中的最高分为100分  -->
		     			<label>考勤分值<font color="red">*</font>：</label>
		     			<form:input path="TAssignmentAnswerAssign.score" id="tAttendenceScore" type="text" placeholder="请输入分值" class=" w100p b1 br3 h30 lh30 mt5 plr5" required="true"/>
				 	</div>
			 	</div>
			<div class="w100p f14 l mt10 hid">
			    	<div>
		     			<label >评分设置：</label><br>
		     			<form:radiobutton path="TAssignmentControl.toGradebook" name="tAttendenceControlToGradebook" value="yes"  id="tAttendenceControlToGradebookYes" checked="checked"/><label for="tAttendenceControlToGradebookYes">将作业添加到成绩簿</label> 
		     			<form:radiobutton path="TAssignmentControl.toGradebook" name="tAttendenceControlToGradebook" value="no"  id="tAttendenceControlToGradebookNo"/><label for="tAttendenceControlToGradebookNo">不要将作业加入到成绩簿中 </label><br>
		     			<form:radiobutton path="TAssignmentControl.gradeToStudent" name="tAttendenceControlGradeToStudent" value="yes" id="tAttendenceControlGradeToStudentYes" checked="checked"/><label for="tAttendenceControlGradeToStudentYes">将成绩公布给学生 </label>
		     			<form:radiobutton path="TAssignmentControl.gradeToStudent" name="tAttendenceControlGradeToStudent" value="no" id="tAttendenceControlGradeToStudentNo" /><label for="tAttendenceControlGradeToStudentNo">不将成绩公布给学生</label><br>
		     			<form:radiobutton path="TAssignmentControl.gradeToTotalGrade" name="tAttendenceControlGradeToTotalGrade" value="yes" id="tAttendenceControlGradeToTotalGradeYes" checked="checked"/><label for="tAttendenceControlGradeToTotalGradeYes">将成绩计入总成绩</label>
		     			<form:radiobutton path="TAssignmentControl.gradeToTotalGrade" name="tAttendenceControlGradeToTotalGrade" value="no" id="tAttendenceControlGradeToTotalGradeNo" /><label for="tAttendenceControlGradeToTotalGradeNo">不将成绩计入总成绩</label>
			 		</div>
		 		</div>
			<div class="moudle_footer cf tc">
		        <div class="submit_link">		      
		        	<input class="btn  bgc l ml30 mt10 poi plr20 br3" id="save" type="submit" value="保存">
					<div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
                       	 取消
                    </div>
		        </div>
		    </div>
    	</form:form>
    	</div>
    	</div>
	</div>
	<!--添加考勤结束-->
	
	
	
	    <script type="text/javascript">
	    $(".newScroll").click(
	            function(){
	                var sel =this.offsetTop;
	                //console.log(sel);
	                var s="kongsd";         
	               
	                $.cookie("mysel",sel,{"path":"/", expires:30});
	                
	                var cookie_sel = $.cookie("mysel");
	                console.log(cookie_sel);
	                console.log(document.cookie);
	            }
	        )
	        $(function(){
	        var dt = new Date(); 
	        dt.setSeconds(dt.getSeconds() + 60); 
	        document.cookie = "cookietest=1; expires=" + dt.toGMTString(); 
	        var cookiesEnabled = document.cookie.indexOf("cookietest=") != -1;      
	            var cookie_sel = $.cookie("mysel"); 
	            
	            if(cookie_sel!=null){ 
	               console.log(cookie_sel);
	                $("html,body").scrollTop(cookie_sel);
	            }
	                
	            })
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
        //关闭视频
        function closeVideoLook(){
     		 $("#con").remove();
        	}

        
      //弹出的方法
        function videoLook(id,moduleType) {
        	var con = '<iframe allowFullScreen="true" id="con" scrolling="yes" id="message" frameborder="0" src="${pageContext.request.contextPath}/tcoursesite/videoLook?folderId=' + id + '&moduleType= ' + moduleType + '" style="width:100%;height:560px;"></iframe>'
            $("#mediaDisplay").html(con); 
            $("#videoLook").fadeIn(100);
            $("body").css("overflow-y","hidden");
        }
      
        $("input[type=reset]").trigger("click");
        
        
        $(".checkall").click(
        	    function (event) {
        	        if (this.checked) {
        	            $(this).parent().parent().find("input[name='checknames']").each(function () {
        	                this.checked = true;
        	            });
        	        } else {
        	            $(this).parent().parent().find("input[name='checknames']").each(function () {
        	                this.checked = false;
        	            });
        	        }
        	    }
        	);

        	$(".check_box").click(
        		    function (event) {
        		        if (this.checked) {
        		        } else {
        		        	$(this).parent().parent().parent().find("input[name='All']").each(function () {
        		                this.checked = false;
        		            });
        		        }
        		    }
        		);
        //资源批量删除
        function deleteWkFolders(){
        	if(confirm("是否确认删除？"))
        	   {
        		window.location="${pageContext.request.contextPath}/tcoursesite/deleteFolders";
        	   }
        }
      function addTr() {
	    //获取当前屏幕的绝对位置
	    var topPos = window.pageYOffset;
	    //使得弹出框在屏幕顶端可见
	    $('#addTr').window({left:"100px", top:(topPos+20)+"px"}); 
	    $("#addTr").window('open');
	}
		function deleteThisTr(obj,questionIdTest){
		for(var i=0;i<23;i++){
		if(array[i]==questionIdTest){array[i]=0;}
		}
		$(obj).parent().parent().remove();
	}
	var array = new Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
	var count = 0;
	function addTrRecord(){
	
		var sectionName = $("#addTrSectionName").val().trim();
		var questionIdTest = $("#addTrquestionIdTest").val();
		var questionIdTestHtml = $("#addTrquestionIdTest option:selected").html();
		var itemTypeTest = $("#addTritemTypeTest").val();
		var itemTypeTestHtml = $("#addTritemTypeTest option:selected").html();
		if(sectionName==""){
			alert("请填写大项名称");
			return false;
		}
		if(itemTypeTest==""){
			alert("请选择试题类型");
			return false;
		}
		var itemQuantityTest = $("#addTritemQuantityTest").val().trim();
		if(itemQuantityTest==""){
			return false;
		}else {
		alert(questionIdTestHtml);
			var itemCount = questionIdTestHtml.substring(questionIdTestHtml.indexOf("(")+1,questionIdTestHtml.indexOf(")"));
			if(itemQuantityTest>Number(itemCount)){
				alert("题库数量不足，请重新设定！");
				return false;
			}
		}
		var itemScoreTest = $("#addTritemScoreTest").val().trim();
		if(itemScoreTest==""){
			alert("请填写试题分值");
			return false;
		}
		if (array.indexOf(questionIdTest)!=-1) {
			alert("该题库已添加，可直接修改数量");
			return false;
		}
		array[count] = questionIdTest;
		count++;
		
		var trString = "<tr>"+
						"<td><input type='text' style='width:80px;' name='sectionName' value='"+sectionName+"' />"+"</td>"+
						"<td><input type='hidden' name='itemTypeTest' value='"+itemTypeTest+"'/>"+itemTypeTestHtml+"</td>"+
						"<td><input type='hidden' name='questionIdTest' value='"+questionIdTest+"'/>"+questionIdTestHtml+"</td>"+
						"<td><input type='text' style='width:50px;' name='itemQuantityTest' value='"+itemQuantityTest+"'  oninput='changeNumber(this)' /></td>"+
						"<td><input type='text' style='width:50px;' name='itemScoreTest' value='"+itemScoreTest+"' oninput='changeNumber(this)' /></td>"+
						"<td><button type='button' class='btn' onclick='deleteThisTr(this,"+questionIdTest+")'>删除</button></td>"+
						"</tr>";
		$("#itemBody").children().last().before(trString);
		$("#addTrSectionName").val("");
		$("#addTritemTypeTest").val("");
		$("#addTritemQuantityTest").val("");
		$("#addTritemScoreTest").val("");
		$("#addTr").window('close');
	}
	    
    //输入内容是数字
    function changeNumber(obj){
		var price=$(obj).val();
		price = price.replace(/[^\d.]/g,"");
	    //必须保证第一个为数字而不是.
	    price = price.replace(/^\./g,"");
	    //保证只有出现一个.而没有多个.
	    price = price.replace(/\.{2,}/g,".");
	    //保证.只出现一次，而不能出现两次以上
	    price = price.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
	    $(obj).val(price);
	}
  //新增或编辑考勤
    function newAttendenceFolder(chapterType,chapterId,lessonId,folderId,status){
    	$('.searchable-select').remove();
    	if(status==1){
    		alert("此考勤已经保存成绩，确定修改?");
    		//return false;
    	}

    	if(folderId != -1){
    		$.ajax({
    			async:false, 
        		type: "POST",
        		url: $("#contextPath").val()+"/tcoursesite/editAttendence",
        		data: {'folderId':folderId},
        		dataType:'json',
        		success:function(data){
        			$.each(data,function(key,values){  
        				if(key=="tAttendenceControlToGradebook"||key=="tAttendenceControlGradeToStudent"||key=="tAttendenceControlGradeToTotalGrade"){
        					if(values=="yes"){
        						document.getElementById(key+"Yes").checked="checked";
        					}else{
        						document.getElementById(key+"No").checked="checked";
        					}
        				}else{
        					$("#"+key).val(""+values);
        				}
        			 }); 
        		},
        		error:function(){
        			alert("信息错误！");
        			}
        	});
    	}else{    		    		   		
    		$("#tAssignmentTitle").val("");
    		$("#tAttendenceId").val(null);
    		$("#tAttendenceAnswerAssignId").val(null);
    		$("#tAttendenceControlId").val(null);
    		$("#tAttendenceWkChapterId").val(null);
    		$("#tAttendenceWkLessonId").val(0);
    		$("#tAttendenceWkFolderId").val(null);
    	}
    	
    	$("#newAttendenceFolder").fadeIn(100);
    	
    	changeAssignmentLessonsByChapterId(chapterId);
    	$("#tAttendenceChapterType").val(chapterType);
    	$("#tAttendenceWkChapterId").val(chapterId);
    	$("#tAttendenceWkLessonId").val(lessonId);
    	$(".select_chosen").trigger("liszt:updated");
    	$('.select_chosen').chosen();
    	$('.select_search').searchableSelect();
    }
  //提交考勤时做判断
    function submitAttendence(){
    	var chapterId = $("#tAttendenceWkChapterId").val();
    	if(chapterId==-1){
    		alert("请选择章节");
    		return false;
    	}
    }
    </script>

 <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.date_input.pack.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/tAssinment.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/tDocument.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/tExam.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/tImage.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/tTest.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/tVideo.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/tcoursesite.js"></script>
</body>


</html>
