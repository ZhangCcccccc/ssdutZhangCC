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
function uploadImageForLabRoom(){
	
	 //获取当前屏幕的绝对位置
	    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#searchFile').window({
    	top:topPos+"px",
	}); 
    $("#searchFile").window('open');
			 
			 $('#file_upload').uploadify({
	            'swf': '${pageContext.request.contextPath}/swfupload/swfupload.swf',  
	            'uploader':'${pageContext.request.contextPath}/xidapp/imageUploadTest;jsessionid=<%=session.getId()%>',		//提交的controller和要在火狐下使用必须要加的id
	            buttonText:'选择文件',
	             onQueueComplete : function(data) {//当队列中的所有文件全部完成上传时触发	
	      			    //当上传玩所有文件的时候关闭对话框并且转到显示界面
	            	 $('#searchFile').window('close');	  
	            	 window.location.href="${pageContext.request.contextPath}/tcoursesite/disk/xidapp/imageTest";          	
		}
	        });
			
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
				
		<button onclick="uploadImageForLabRoom()">上传图片</button>
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