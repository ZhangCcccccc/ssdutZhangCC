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
     	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tCourseSite/date.css" />
 		<link href="${pageContext.request.contextPath}/css/tCourseSite/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
 	    <link href="${pageContext.request.contextPath}/css/tCourseSite/message/notice.css" rel="stylesheet" type="text/css">
 		<link href="${pageContext.request.contextPath}/css/tCourseSite/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tCourseSite/message/demo.css" type="text/css">
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tCourseSite/message/zTreeStyle/zTreeStyle.css" type="text/css">
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/message/jquery.ztree.core-3.5.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/message/jquery.ztree.excheck.js"></script>
   		
   	   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/tcoursesiteBefore.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>
        
    <!-- 上传插件的css和js -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>
   	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
   	
   	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/easyui.css">

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js" ></script>
    
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>

 
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
    function checkAll(obj){
    $(" input[type='checkbox']").prop('checked', $(obj).prop('checked'));
    }
    function change(e){
        var src=e.target || window.event.srcElement; //获取事件源，兼容chrome/IE
   
        var file=src.value;
        var fileName = $('#fileName');
        fileName.val(file.substring( file.lastIndexOf('\\')+1 ));
    }
      var editor = UE.getEditor('info_content',{  
                //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个  
                toolbars:[['FullScreen', 'Source','Bold','Italic', 'Undo', 'Redo','formatmatch','pasteplain','forecolor','backcolor','insertorderedlist','fontfamily','insertunorderedlist ','link','imagenone','imageright','imageleft','imagecenter','simpleupload','insertimage','attachment','fontsize','underline','test']],  
                //focus时自动清空初始化时的内容  
                autoClearinitialContent:true,  
                //关闭字数统计  
                 
                //关闭elementPath  
                  
                //默认的编辑区域高度  
                initialFrameWidth:605  
                //更多其他参数，请参考ueditor.config.js中的配置项  
            }); 
   

    
</script>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css"/> 
   
</head>
<body>
<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
<input type="hidden"  id="sessionId" value="<%=session.getId()%>"/>
 <div class="course_con ma back_gray" >
        <div class="course_cont r back_gray">
        	<div class="notice_cont ">
            <div class="w100p cf">
                
                <ul class="tool_box tab cf rel zx2 pt5 ">
                
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml30 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/message/listMessages?tCourseSiteId=${tCourseSite.id}&currpage=1&queryType=1&titleQuery=" class="g3">
                        <i class="fa fa-edit mr5"></i>通知列表
                        </a>
                    </div>
                </li>
                
                <li class="rel l pb5">
                    <div class="wire_btn1 bgb l ml10 mt10 poi">
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
            <div class="lh40 bgg mb15 pl30 f18 ">
                    <span class="bc">信息发送</span>  
                </div>
            <form:form action="" method="POST" modelAttribute="tMessage" onsubmit="return submitAssignment()" name="frm">
            <form:hidden path="id" id="id"/>
            <div class="cf mtb20">
			               <span class="f16 mb5 ml110 block l mt15">消息标题：</span>
			               <form:input path="title" id="title" type="text" style="width:200px;" class=" mt10 ml20  lh20 filename" />
			 </div>	
            <div class="mb20 cf">
                                   									
                    <p>
                      <label class="f16 mb5 ml80">信息内容编辑：</label>
                       <form:textarea class="r mr140" id="info_content"   path="content"></form:textarea>
                    </p>               
            </div>
            <div class="cf mtb20">
                <span class="f16 mb5 ml110 block l mt15">添加附件：</span>
                <input  type="button" class="btn_h25 bgb  mt15 poi  plr20 br3 ml20 wh nobd"  onclick="addDocumentFolder();" value="选择文件"/>
                
                <input name="documentsList" id="documentsList" type="text" class="hide"/><br>                    
                     <div class="cf mtb20">
                    <ul id="documentsNameList" class="">                       
                    </ul>
                	</div> 
            </div> 
            <%--<div  class="cf mtb20">
                <span class="f16 mb5 ml96 block l mt15">选择联系组：</span>
                <select name="tCourseSiteGroupId" id="tCourseSiteGroupId"  class=" mt15 select_css lh20">
                    <c:forEach items="${tCourseSiteGroups}" var="tCourseSiteGroup" varStatus="i">                                	
                       <option value="${tCourseSiteGroup.id}">${tCourseSiteGroup.groupTitle}</option>                                	
                    </c:forEach>
                </select>
            </div>
             --%><%--<div  class="cf mtb20">
                <div id="menuContent2" class="menuContent" style="display:none; position: absolute; left:477px;top:487px;z-index:999;">
                        <ul id="treeDemo" class="ztree" style="margin-top:0; width:150px;"></ul>
                </div> 
                <span class="f16 mb5 ml96 block l mt15">选择联系组：</span>
                <input id="citySel2" type="text" class="mt10 clear-input" readonly onclick="showContact()"  />
            <input id="citySel3" type="hidden"  path="tCourseSiteGroupId" value="${x }" name="partyId1"/>
				<input id="citySel2" type="text" readonly class="contact" name="citySel2" value="${citySel2 }"  onclick="CPartyMenu(); return false;" name="partyName1" />
            </div>         
            --%>
           <div  class="cf mtb20"  style="display: none;">
                <div id="menuContent" class="menuContent" style="display:none; position: absolute;left:476.5px;top:596px; ">
                        <ul id="treeDemo" class="ztree" style="margin-top:0; width:150px;"></ul>
                </div> 
                <span class="f16 mb5 ml96 block l mt15">选择联系组：</span>
                <input id="citySel" type="text" class="mt10 contact" readonly onclick="showContact()" />
            </div>
            <div class="info_recive cf mtb20"  style="display: none;">
                <span class="f16 mb5 ml96 block l mt15">信息接收端：</span>
                <div class="l">
                    <input class="checkall " type="checkbox" id="checkAll" name="All" onclick="checkAll(this)" style="margin-top:18px;margin-left:15px;padding-left:15px" /><label for="checkAll"></label><span class="r block mt20 ml5">全选</span>
                </div>
                <div class="l">
                    <input class="check_box" type="checkbox" id="check_email" value="email" name="checkname" style="margin-top:18px;margin-left:15px;padding-left:15px" /><label for="check_email"></label><span class="r block mt20 ml5">邮箱邮件</span>
                </div>
                <div class="l">
                    <input class="check_box" type="checkbox" id="check_info" value="info" checked="checked" name="checkname" style="margin-top:18px;margin-left:15px;padding-left:15px" /><label for="check_info"></label><span class="r block mt20 ml5">信息公告(默认勾选)</span>
                </div>
                
            </div>
            <div class="cf mtb20">
                <span class="f16 mb5 ml80 block l mt15">信息定时发送：</span>
                <input type="datetime" name="releaseTime" id="releaseTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly" required="required" class="Wdate w30p b1 ml20 br3 h30 lh30 mt5 plr5 " /> 
            </div>
            <div class="cf mtb20">
                
                      <input class="btn_h25 bgb l mt10 poi  plr20 br3 wh nobd ml350" type="submit" value="立即发送" onclick="return nowSubmit()" />
                   	<input class="btn_h25 bgb l mt10 poi  plr20 br3 wh nobd ml30" type="submit" value="确定计划" onclick="return savePlan()" />
                    
             </div>
            </form:form>
        </div>
    </div>
    </div>
    <div class="window_box hide fix zx5  " id="addDocumentFolder">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
             <div class="add_tit p10 bb f16">添加附件</div>
            <div class="add_con p10">
            <form:form action="${pageContext.request.contextPath}/tcoursesite/saveDocumentFolder?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType}&selectId=${selectId}" 
            method="POST" onsubmit="return submitDocumentFolder()" modelAttribute="documentFolder">
                <div class="add_module cf">                                      
                   <div class="tab_box">                 
                    <div class="tab_list f14 mb2">
                    <input type="file" name="file" id="DocumentUploadifyPic" />
                                  
                    </div>
                    </div>
                </div>
                </form:form>
            </div>
        </div>
    </div>
    <script type="text/javascript" ></script>
        <script type="text/javascript">
                          $(function () {
                              $("#datetimepicker1").datetimepicker({
                                  widgetPositioning:{
                                      horizontal: 'left',
                                      vertical: 'bottom'
                                  }    
                              });
                          });
                function savePlan(){
                	if(!document.getElementById("check_email").checked && !document.getElementById("check_info").checked ){
                		alert("请确认接收端");
                		return false;
                	}
                	var date1=$("#releaseTime").val();
                	var date2='${nowDate}';
                	var a=Date.parse(date1) ;
                	var b=Date.parse(date2);
                	if(date1==""||a-b<0){
                		alert("请检查发送时间！");
                		return false;
                	}
                	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            	    checkCount = zTree.getCheckedNodes(true);
            	 
            	    var classPurview = "";
            	    for(var i=0;i<checkCount.length;i++) {
            	   classPurview += checkCount[i].id + ",";
            	    }
            	    var s = $("#documentsList").val();
                     frm.action = "${pageContext.request.contextPath}/tcoursesite/message/saveTMessageUser?tCourseSiteId="+${tCourseSite.id}+"&documentsList="+s+"&classPurview="+classPurview;
                      }
                
                function nowSubmit(){
                	//if(!document.getElementById("check_email").checked){
                		//alert("请确认接收端");
                		//return false;
                	//}
                	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            	    checkCount = zTree.getCheckedNodes(true);
            	 
            	    var classPurviewNow = "";
            	    for(var i=0;i<checkCount.length;i++) {
            	    	classPurviewNow += checkCount[i].id + ",";
            	    }
            	    var s = $("#documentsList").val();
                     frm.action = "${pageContext.request.contextPath}/tcoursesite/message/saveTMessageUserNow?tCourseSiteId="+${tCourseSite.id}+"&documentsList="+s+"&classPurviewNow="+classPurviewNow;
                     }
                       
              //文件上传插件
                function addDocumentFolder(){
                	//document.getElementById("documentsList").innerHTML="";
            		//sdocument.getElementById("documentsNameList").innerHTML="";
            		$("#documentFolderId").val("");
            		$("#documentFolderName").val("")
                	$("#addDocumentFolder").fadeIn(100);
                	//文件上传插件
                	$("#DocumentUploadifyPic").uploadify({
                		'auto' : true, 		
                		'formData':{type:2},    //传值
                    	'swf': $("#contextPath").val()+'/swfupload/swfupload.swf',  
                    	'uploader':$("#contextPath").val()+'/tcoursesite/message/fileUpload;jsessionid='+$("#sessionId").val(),		
                    	'fileSizeLimit' : 0, //以字节为单位，0为不限制。1MB:1*1024*1024
                        'buttonText':'选择文件',
                        'cancelImage':'/swfupload/uploadify-cancel.png',
                        'simUploadLimit' : 88, // 一次同步上传的文件数目
                    	'onUploadError':function(file, errorCode, errorMsg, errorString) {
                        	//alert("上传失败，请检查文件大小和格式！");
                    	} ,
                    	'onUploadSuccess' : function(file, data, response) {
                    		//alert(data);
                    		//alert("文件："+file.name+"  上传成功");
                    		$("#documentsNameList").append("<li class='pic_list hg9 lh35 ptb5 rel ovh'>" +
                					        				"<div class='cf rel zx1 z c_category'>" +
                					        				"<div id='"+data+"'class=''l mlr15 cc1 c_tool poi'>" +
                					        				file.name +"<i class='fa fa-trash-o g9 f18 mr5 poi' onclick='deleteAttach("+data+")'></i>"+
                					        				"</div></div></li>");
                    		var cur=$("#documentsList").val();
                    		$("#documentsList").val(cur+data+",");
                    		
                		},
                    	onQueueComplete : function(event, data) {//当队列中的所有文件全部完成上传时触发　 
                    		$("#addDocumentFolder").fadeOut(100);
                		}
                	});
                } 	
              //删除插件
              	function deleteAttach(data){
              		var ids=$("#documentsList").val();
              		ids = ids.replace(data+",","");
              		$("#documentsList").val(ids);
              		//getElementById(data).innerHTML=""; 
              		var my = document.getElementById(data);
              	    if (my != null){ 
              	    	my.parentNode.removeChild(my);
              	    }
              	       
              }
              $(".checkall").click(
                	    function (event) {
                	        if (this.checked) {
                	            $(this).parent().parent().find("input[name='checkname']").each(function () {
                	                this.checked = true;
                	            });
                	        } else {
                	            $(this).parent().parent().find("input[name='checkname']").each(function () {
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
                	   function showContact(){
                	          
                	          if($(".menuContent").css('display') != "block")
                	          {
                	        	  var cityObj = $("#citySel");
                	  			var cityOffset = $("#citySel").offset();
                	  			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
                	              $(".menuContent").css("display","block");
                	          }
                	          else
                	          {
                	              $(".menuContent").css("display","none");
                	          }
                	      }
                	   

                	  var code;
                	      
                	      
                	      var setting = {
                	              check: {
                	                      enable: true
                	                  },
                	                  data: {
                	                      simpleData: {
                	                          enable: true
                	                      }
                	                  }
                	              };
                	function showCode(str) {
                	          if (!code) code = $("#code");
                	          code.empty();
                	          code.append("<li>"+str+"</li>");
                	      }
                	$(document).ready(function(){
                		 $.ajax({
                	   	type: 'POST',
                	   	url: "${pageContext.request.contextPath}/tcoursesite/message/getcpartyMenu?tCourseSiteId=${tCourseSite.id}",
                	   	success:function(json){
                	   		
                	   		$.fn.zTree.init($("#treeDemo"), setting, json);
                	   	}
                		 });
                	 // $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                	 
                		
                		$("body").bind("mousedown", onBodyDown);
                	});
                	function hideMenu() {
                		$("#menuContent").fadeOut("fast");
                		$("body").unbind("mousedown", onBodyDown);
                	}
                	function onBodyDown(event) {
                		if (!(event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
                			hideMenu();
                		}
                	}
           
          </script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/message/moment-with-locales.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/message/locales.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/message/bootstrap.min.js"></script>  
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/message/bootstrap-datetimepicker.js"></script>
    
</body>
</html>