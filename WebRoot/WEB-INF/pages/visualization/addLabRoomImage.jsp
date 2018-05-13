 <%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
 <jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
 
 <html >  
<head>
<meta name="decorator" content="iframe"/>
<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">

<!-- 文件上传的样式和js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>  

<script type="text/javascript">
function uploadImageForLabRoom(type){
	
	 //获取当前屏幕的绝对位置
	    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#searchFile').window({
    	top:topPos+"px",
	}); 
    $("#searchFile").window('open');
			 
			 $('#file_upload').uploadify({
				'formData':{id:'${labRoom.id}',type:type},    //传值
	            'swf': '${pageContext.request.contextPath}/swfupload/swfupload.swf',  
	            'uploader':'${pageContext.request.contextPath}/visualization/uploadImageForLabRoom;jsessionid=<%=session.getId()%>',		//提交的controller和要在火狐下使用必须要加的id
	            buttonText:'选择文件',
	             onQueueComplete : function(data) {//当队列中的所有文件全部完成上传时触发	
	      			    //当上传玩所有文件的时候关闭对话框并且转到显示界面
	            	 $('#searchFile').window('close');	  
	            	 window.location.href="${pageContext.request.contextPath}/visualization/addLabRoomImage?id="+${labRoom.id};          	
		}
	        });
			
		}
	function uploadVideoForLabRoom(){
			 
		     $('#searchFile1').window({top: 1000});
			 $('#searchFile1').window('open');
			 $('#video_upload').uploadify({
				'formData':{id:'${labRoom.id}'},    //传值
	            'swf': '${pageContext.request.contextPath}/swfupload/swfupload.swf',  
	            'uploader':'${pageContext.request.contextPath}/visualization/uploadVideoForLabRoom;jsessionid=<%=session.getId()%>',		//提交的controller和要在火狐下使用必须要加的id
	            buttonText:'选择文件',
	             onQueueComplete : function(data) {//当队列中的所有文件全部完成上传时触发	
	      			    //当上传完所有文件的时候关闭对话框并且转到显示界面
	            	 $('#searchFile1').window('close');	
	            	 window.location.href="${pageContext.request.contextPath}/visualization/addLabRoomImage?id="+${labRoom.id};           	
		}
	        });
			
		}

/**
*校区
*/
function changeCampus(){
	var s=document.getElementById("campus").value;
	//alert("s的值为："+s);
			//Ajax方法 根据网站id查询出所有的栏目
			$.post('${pageContext.request.contextPath}/visualization/findBuildByCampusId?id='+s+'',function(data){  //serialize()序列化
				$("#build").html(data);
				$("#build").trigger("liszt:updated");
			 });				
}
/**
*楼栋
*/
function changeBuilds(){
	var s=document.getElementById("build").value;
	//var s=$("#campus").val;
	//alert("s的值为："+s);
			//Ajax方法 根据网站id查询出所有的栏目
			$.post('${pageContext.request.contextPath}/visualization/findRoomByBuildNumber?buildNumber='+s+'',function(data){  //serialize()序列化
				$("#room").html(data);
				$("#room").trigger("liszt:updated");
				
			 });				
}


function checkRoom(){
	$("#addRoom").show();
    $("#addRoom").window('open');   
    
}
//确定
function complate(){
	var s=$("#room").val();
	document.getElementById("roomNumber").value=s;
	document.getElementById("roomAddress").value=s;
	$("#addRoom").hide();
	$("#addRoom").window('close');  
}		
//取消
function cancle(){
	$("#campus").val("-1");
	$("#campus").trigger("liszt:updated");
	$("#build").html("");
	$("#build").trigger("liszt:updated");
	$("#room").html("");
	$("#room").trigger("liszt:updated");
	$("#addRoom").hide();
	$("#addRoom").window('close');  
}		

function showImage(image){
	//alert(image.src);
	//$("#img").src=image.src;
	document.getElementById("img").src=image.src;
	$('#showImage').window({
		        top: 1000   
		     });
	$('#showImage').window('open');
}

function setRoomCapacity(){
	var area=document.getElementById("labRoomArea").value;
	//实验室容量，向上取整
	var capacity=Math.ceil(area/5);
	document.getElementById("capacity").value=capacity;
}
</script>


<style type="text/css">
.select-box{overlow:hidden;}
.left-box,.right-box{float:left;
margin-right:15px;}
.right-box a{color:#333;
	font-size:12px;
	}
.right-box{width:250px;
	border:1px solid #333;}
.right-box select{width:250px;
	overflow:scroll;}
.select-action a{color:#333;
	text-decoration:none;}
.chzn-container{width: 200px;}
.chzn-container ,#build_chzn{width:100%;}
.chzn-container{width:100% !important;}

</style>
</head>
<body>
<div class="right-content">
	<div id="TabbedPanels1" class="TabbedPanels">
	  <div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">
				<div id="title">实验室资源</div>
				
				</div> 
<form:form action="${pageContext.request.contextPath}/visualization/saveLabRoom" method="POST" modelAttribute="labRoom">
		<div class="new-classroom">
					<form:hidden path="id"></form:hidden>
					<fieldset>
						    <label>实验室名称：</label>${labRoom.labRoomName}
					</fieldset>		

			  		<fieldset>
						     <label>实验室编号：</label>${labRoom.labRoomNumber}
					 </fieldset>
					 <fieldset>
					 		<label>实验室英文名称：</label>${labRoom.labRoomEnName}
					 </fieldset>
					  <fieldset>
					  		<label>实验室简称：</label>${labRoom.labRoonAbbreviation}
					 </fieldset>
					  <%--<fieldset>
					  		<label>实验室类别：</label>${labRoom.CLabRoomType.name}
					 </fieldset>
					  --%><fieldset>
						    <label>实验室地点：</label><input type="button" onclick="checkRoom();" value="选择" />
						    	<form:input path="labRoomAddress" id="roomAddress" disabled="true"/>
						    	<form:hidden path="systemRoom.roomNumber" id="roomNumber" name="roomNumber"/>
					</fieldset>
					  
					<fieldset class="introduce-box">
						    <label> 附件 ：<input type="button" onclick="uploadImageForLabRoom(2)" value="上传附件"/></label>
							    <ul class="img-box">
						     	<c:forEach items="${labRoom.commonDocuments}" var="d">
						     	<c:if test="${d.type==2}">
						    	<li>
						    	${d.documentName}
						    	<a class="btn btn-common" href="${pageContext.request.contextPath}/visualization/downloadImageForLabRoom?id=${d.id}">下载</a>
						    	<a class="btn btn-common" href="${pageContext.request.contextPath}/visualization/deleteImageForLabRoom?id=${labRoom.id}&type=2">删除</a>
						    	</li>
						    	</c:if>
						     </c:forEach>
						     </ul>
							 
					</fieldset>
					<fieldset class="introduce-box">
						     <label>楼层平面图: <input type="button" onclick="uploadImageForLabRoom(1)" value="上传图片"/></label>
						     <ul class="img-box">
						     <c:forEach items="${labRoom.commonDocuments}" var="d">
						     	<c:if test="${d.type==1}">
						    	<li>
						    	<img alt="${d.documentName}" src="${pageContext.request.contextPath}/${d.documentUrl}" border="0"  width="200px" height="150px" onclick="showImage(this);"> 
						    	<a class="btn btn-common" href="${pageContext.request.contextPath}/visualization/deleteImageForLabRoom?id=${labRoom.id}&type=1">删除</a>
						    	</li>
						    	</c:if>
						     </c:forEach>
						     </ul>
							 
					 </fieldset>
					 <fieldset class="introduce-box">
						     <label>全景图片: <input type="button" onclick="uploadImageForLabRoom(3)" value="上传全景图片"/></label>
						     <ul class="img-box">
						     <c:forEach items="${labRoom.commonDocuments}" var="d">
						     	<c:if test="${d.type==3}">
						    	<li>
						    	<img alt="${d.documentName}" src="${pageContext.request.contextPath}/${d.documentUrl}" border="0"  width="200px" height="150px" onclick="showImage(this);"> 
						    	<a class="btn btn-common" href="${pageContext.request.contextPath}/visualization/deleteImageForLabRoom?id=${labRoom.id}&type=3">删除</a>
						    	</li>
						    	</c:if>
						     </c:forEach>
						     </ul>
							 
					 </fieldset>
					<fieldset class="introduce-box">
						     <label>展示图片: <input type="button" onclick="uploadImageForLabRoom(4)" value="上传展示图片"/></label>
						     <ul class="img-box">
						     <c:forEach items="${labRoom.commonDocuments}" var="d">
						     	<c:if test="${d.type==4}">
						    	<li>
						    	<img alt="${d.documentName}" src="${pageContext.request.contextPath}/${d.documentUrl}" border="0"  width="200px" height="150px" onclick="showImage(this);"> 
						    	<a class="btn btn-common" href="${pageContext.request.contextPath}/visualization/deleteImageForLabRoom?id=${labRoom.id}&type=4">删除</a>
						    	</li>
						    	</c:if>
						     </c:forEach>
						     </ul>
					 </fieldset>
		</div>	 
			<div class="moudle_footer">
			        <div class="submit_link">
			          <input class="btn" id="save" type="submit" value="确定">
					<input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
			        </div>
			    </div>
		</table>
		
		
		
	</form:form>
<div id="showImage" class="easyui-window" title="查看图片" closed="true" iconCls="icon-add" style="width:620px;height:340px">
		<center>
	   <img id="img" alt="" src="" width="600" height="300" border="0">
	   </center>
     </div>		
<div id="searchFile" class="easyui-window" title="上传图片" closed="true" iconCls="icon-add" style="width:400px;height:200px">
	    <form id="form_file" method="post" enctype="multipart/form-data">
           <table  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
            <td>
          	<div id="queue"></div>
		    <input id="file_upload" name="file_upload" type="file" multiple="true">
            </tr>   
            </table>
         </form>
     </div>		
  <div id="searchFile1" class="easyui-window" title="上传视频" closed="true" iconCls="icon-add" style="width:400px;height:200px">
	    <form id="form_ video" method="post" enctype="multipart/form-data">
           <table  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
            <td>
          	<div id="queue">（文件格式：MP4）</div>
		    <input id="video_upload" name="file_upload" type="file" multiple="true">
            </tr>   
            </table>
         </form>
     </div> 
   <!-- 地址 -->
<div id="addRoom" class="easyui-window " title="选择地点" align="left" title="" modal="true" maximizable="false" collapsible="false" closed="true" minimizable="false" style="width: 600px; height: 300px;">
			
				<table>
					<tr>
						<td>校区：</td>
						<td>
							<select id="campus" class="chzn-select" onchange="changeCampus()" style="width: 150">
							<option value="-1">请选择校区</option>
							<c:forEach items="${campusList}" var="c">
							<option value="${c.campusNumber}">${c.campusName}</option>
							</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>楼栋：</td>
						<td>
							<select id="build" class="chzn-select" onchange="changeBuilds()" style="width: 150">
							</select>
						</td>
					</tr>
					<tr>
						<td>房间：</td>
						<td>
							<select id="room" class="chzn-select" style="width: 150">
							</select>
						</td>
					</tr>
					
					<tr>
						<td><input type="button" value="确定" onclick="complate();">
						</td>
						<td><input type="button" value="取消" onclick="cancle();">
						</td>
					</tr>
					
				</table>
				
			
		</div>  
     </div>
		</div>
		</div>
	</div>
</div>  

<!-- 下拉框的js -->
					<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
					<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
					<script type="text/javascript">
						    var config = {
						      '.chzn-select': {search_contains : true},
						      '.chzn-select-deselect'  : {allow_single_deselect:true},
						      '.chzn-select-no-single' : {disable_search_threshold:10},
						      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
						      '.chzn-select-width'     : {width:"95%"}
						    }
						    for (var selector in config) {
						      $(selector).chosen(config[selector]);
						    }
						</script>
					<!-- 下拉框的js -->	
</body>


</html>