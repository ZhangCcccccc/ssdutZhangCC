<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html>
<head>
	<meta name="decorator" content="course"/>
	<title>文件管理</title>
	
    <link href="${pageContext.request.contextPath}/css/tCourseSite/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/reset.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/lib.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/global.css" rel="stylesheet" type="text/css">

    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>
    <!-- 文件上传的样式和js开始 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>  
	<!-- 文件上传的样式和js结束 -->
	
                                <script type="text/javascript">
                                </script>
</head>
<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
	<input type="hidden"  id="tCourseSiteId" value="${tCourseSite.id }"/>
	<input type="hidden"  id="openFolderIds" value="-1,"/>
	<input type="hidden"  id="useClass" value="parent"/>

    <div class="course_con ma">
        <div class="course_cont r">
            <div class="tab_list f14 mb2">
                <div class="lh40 bgg  pl30 f18 ">
                    <span class="bc">资源</span>
                    <c:if test="${flag==1}">
	                     <i class="fa fa-list mr5 poi" title="上传压缩文件" onclick="uploadZip('-1',-1)"></i>
	                     <i class="fa fa-upload mr5 poi" title="上传文件" onclick="uploadFile('-1',-1)"></i>
	                     <i class="fa fa-folder-o mr5 poi" title="添加文件夹" onclick="addFolder('','',-1)"></i>
	                </c:if>
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14">
                        <div id="searchPath">
                        </div>
                        	<input id="uploadName" style="border:1px solid  #000 "/>
                        	<button onclick="searchAll()">搜索</button>
                            <table class="w100p">
                            <thead >
                            	<tr >
                                    <th class="tl">
                                    	<c:if test="${flag==1}">
	            							<input class="checkall " id="checkall"  type="checkbox" name="All" >
	                                		<label class="mt10" for="checkall">全选</label>  &nbsp;&nbsp;    
	                                		<a href="javascript:void(0)" onclick="removeFiles(0);return false;" class="g0">
	                                		<i class="fa fa-trash-o mr-5 poi" title="批量删除" ></i>
	                                		<i class="fa fa-trash-o mr-5 poi" title="批量删除" ></i>
	                                        </a>      
	                                    </c:if>                
                                    </th>
                                    <th class="tl w60"></th>
                                    <th class="tl w60"></th>
                                    <th class="tl w140"></th>
                                    <th class="tl w100"></th>
                                    <th class="tl w100"></th>
                                </tr>
                                <tr >
                                    
                                    <th class="tl">标题</th>
                                    <th class="tl">访问范围</th>
                                    <th class="tl">创建者</th>
                                    <th class="tl">最后修改时间</th>
                                    <th class="tl">大小</th>
                                    <th class="tl">操作</th>
                                </tr>
                            </thead>
                            <tbody id = "tbody">
                            	
                                <c:forEach items="${lists}" var="list" varStatus="i">
                                <c:if test="${i.index==0}">
                                	<tr class="folder" type="hidden"></tr>
                                		<c:forEach items="${list}" var="file" varStatus="j">
	                                	<tr class="folder ${file.id}" id="${file.id}">
	                                		<input type="hidden"  id="path${file.id}" value="${file.name}/"/>
		                                    <td class="parent" id="row_${file.id}" onclick="lookFolders(this)">
		                                    	<input class="l check_box" type='checkbox' id='checkbox${file.id}' name='checkname' value='${file.id}' />
		                    					<label class="l mt10" for="checkbox${file.id}"></label>
		                                        <div class="resource">
		                                            <span>
		                                                <i class="fa fa-folder-o bc mlr5 f18"></i>
		                                                <span>${file.name}</span>
		                                            </span>
		                                        </div>
		                                    </td>
		                                    <td>整个站点</td>
		                                    <td>admin</td>
		                                    <td>${file.date}</td>
		                                    <td id="size${file.id}">${file.size}</td>  
		                                    <td>
		                                    	<c:if test="${flag==1}">
				                                    <i class="fa fa-list mr5 poi"  title="上传压缩文件" onclick="uploadZip('${file.name}/',${file.id})"></i>
				                                    <i class="fa fa-upload mr5 poi" title="上传文件" onclick="uploadFile('${file.name}/',${file.id})"></i>
				                                    <i class="fa fa-folder-o mr5 poi" title="添加文件夹" onclick="addFolder('${file.name}/','',${file.id})"></i>
				                                    <i class="fa fa-trash-o mr5 poi" title="删除" onclick="removeFiles(${file.id});return false;"></i>
				                                </c:if>
		                                    </td> 
		                                </tr> 
		                                <tr id="file${file.id}" class="file${file.id}" ></tr>
		                                </c:forEach>
	                            </c:if>
	                            <c:if test="${i.index==1}">
	                            	
                            		<tr id="file" class="file" type="hidden"></tr>
                            	
                                		<c:forEach items="${list}" var="file" varStatus="j">
		                                <tr class="file file${file.id}" id="file${file.id}">
                                            <td class="parent">
                                            	<input class="l check_box" type='checkbox' id='checkbox${file.id}' name='checkname' value='${file.id}' />
		                    					<label class="l mt10" for="checkbox${file.id}"></label>
                                                <div class="">
                                                    <span>
                                                        <i class="fa fa-file-o bc mlr5 f18"></i>
                                                        <span>${file.name}</span>
                                                    </span>
                                                </div>
                                            </td>
                                            <td>整个站点</td>
                                            <td>admin</td>
                                            <td>${file.date}</td>
                                            <td>${file.size}</td>
                                            <td>
	                                            <i class="fa fa-download mr5 poi" title="下载"  onclick="download_(${file.id});return false;"></i>
	                                            <c:if test="${flag==1}">
	                                            	<i class="fa fa-trash-o mr5 poi" title="删除"  onclick="removeFiles(${file.id});return false;"></i>
	                                            </c:if>
                                            </td>
	                                	</tr>
	                                	</c:forEach>
                                </c:if>
                                <c:if test="${i.index==2}">
                                	<tr class="url" type="hidden"></tr>
                                		<c:forEach items="${list}" var="file" varStatus="j">
		                                <tr class="url" id="folder${file.id}">
                                            <td class="parent">
                                            	<input class="l check_box" type='checkbox' id='checkbox${file.id}' name='checkname' value='${file.id}' />
		                    					<label class="l mt10" for="checkbox${file.id}"></label>
                                                <div class="">
                                                    <span>
                                                        <i class="fa fa-file-o bc mlr5 f18"></i>
                                                        <span>${file.name}</span>
                                                    </span>
                                                </div>
                                            </td>
                                            <td>整个站点</td>
                                            <td>admin</td>
                                            <td>2016-08-30</td>
                                            <td>${file.size}</td>
                                            <td>打开</td>
	                                	</tr>
	                                	</c:forEach>
                                </c:if>
                                </c:forEach>
                                </tbody>
                                
                                

                            </table>
                            

                        </div>
                    </div>
                </div>
            </div>
     </div>       
     </div>  
     
        <!-- Modal -->
     <div class="modal window_box hide fix zx2 " id="myModal">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p20 bb f16">上传文件</div>
            <div class="modal-content">
			<div class="modal-body" style="height: 336px; overflow: scroll;overflow:auto; overflow-x:hidden;">
  					<input id="file_upload" name="file_upload" type="file" multiple="true">
   				<div id="upedFiles" style="padding: 6px;font-weight: bold;font-size: 13px;">
   				</div>
			</div>
		</div>
        </div>
    </div>

<%-- 创建文件夹窗口--%>
<div class="window_box hide fix zx2 " id="addFolder">
    <div class="window_con bgw b1 br3 rel bs10 ">
        <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
        <div class="add_tit p20 bb f16" id="folderDivName">添加文件夹</div>
        <div class="add_con p20">
            <div class="add_module cf f14">
            	<input  id="originalFolderName" class="hide"/>
            	<input  id="originalFolderPath" class="hide"/>
            	<input  id="folderPath" class="hide"/>
            	<input  id="folderParentFolderId" class="hide"/>
                <div class="cf w100p  mt10 mb20">
                    <div class="lh25">标题</div>
                    <div class="w100p">
                        <input class="w98p lh25 br3 b1 plr5" type="text"  id="folderName"/>
                    </div>
                </div>
            </div>
            <div class="cf tc">
                <input onclick="saveFolder()" type="button" class="btn bgb l mt10 poi  plr20 br3 wh" value="保存"/>
                <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
                    取消
                </div>
            </div>
        </div>
    </div>
</div>
     
     	<script type="text/javascript">
     	$(function(){
     		$("#openFolderIds").val('-1,');
     	});
	</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/disk/listFiles.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>

</body>
</html>