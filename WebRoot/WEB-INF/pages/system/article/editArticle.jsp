<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://"  
            + request.getServerName() + ":" + request.getServerPort()  
            + path + "/";  
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--                       CSS                       -->
<!-- 下拉框的样式 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/systemBack/reset.css" type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/systemBack/style.css" type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/systemBack/invalid.css" type="text/css" media="screen" />
<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/jquery-1.3.2.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/facebox.js"></script>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/jquery.wysiwyg.js"></script>
<!-- jQuery Datepicker Plugin -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/jquery.datePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/systemBack/jquery.date.js"></script>
<!-- ueditor编辑器 -->

<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all_table.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script> 
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/tCourseSite/Calendar.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css"/>
</head>
<body>
<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->

  <div id="main-content">
    <!-- Main Content Section with everything -->
    <noscript>
    <!-- Show a notification if the user has disabled javascript -->
    <div class="notification error png_bg">
      <div> Javascript is disabled or is not supported by your browser. Please <a href="http://browsehappy.com/" title="Upgrade to a better browser">upgrade</a> your browser or <a href="http://www.google.com/support/bin/answer.py?answer=23852" title="Enable Javascript in your browser">enable</a> Javascript to navigate the interface properly.
        Download From <a href="http://www.exet.tk">exet.tk</a></div>
    </div>
    </noscript>
    <!-- Page Head -->
    <h2>Welcome</h2>
    <p id="page-intro">What would you like to do?</p>
    <ul class="shortcut-buttons-set">
      <li><a class="shortcut-button" href="${pageContext.request.contextPath}/admin/newArticle"><span> <img src="${pageContext.request.contextPath}/images/systemBack/icons/paper_content_pencil_48.png" alt="icon" /><br />
        创建文章 </span></a></li>
      <li><a class="shortcut-button" href="${pageContext.request.contextPath}/admin/newChannel"><span> <img src="${pageContext.request.contextPath}/images/systemBack/icons/pencil_48.png" alt="icon" /><br />
        创建栏目</span></a></li>
      <li><a class="shortcut-button" href="${pageContext.request.contextPath}/admin/addNewImage"><span> <img src="${pageContext.request.contextPath}/images/systemBack/icons/image_add_48.png" alt="icon" /><br />
        上传图片 </span></a></li>
      <li><a class="shortcut-button" href="${pageContext.request.contextPath}/admin/newSite"><span> <img src="${pageContext.request.contextPath}/images/systemBack/icons/site.png" alt="icon"  style="width:48px;" /><br />
        增加网站 </span></a></li>
      <li><a class="shortcut-button" href="${pageContext.request.contextPath}/admin/linkNew" ><span> <img src="${pageContext.request.contextPath}/images/systemBack/icons/link.png" alt="icon" style="width:48px;"/><br />
       创建链接 </span></a></li>
    </ul>
    <!-- End .shortcut-buttons-set -->
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>编辑文章</h3>
        <ul class="content-box-tabs">
          <li><a  class="default-tab">Forms</a></li>
        </ul>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
     
        <div class="tab-content default-tab"  id="tab1">
          <form:form action="${pageContext.request.contextPath}/admin/saveArticle" method="POST" modelAttribute="article">
            <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <p>
              <label>文章名称</label>
              <form:input class="text-input medium-input datepicker" type="text" id="medium-input" name="medium-input" path="title"/>
            </p>
            <p>
              <label>内容概要</label>
              <form:input class="text-input large-input" type="text" id="large-input" name="large-input" path="profile"/>
            </p>
            <p>
              <label>选择附件</label>
              <form:select name="dropdown" class="small-input" path="cmsDocument.id">
              		<form:option value="0">请选择</form:option>
              		<c:forEach  items="${documents }"   var="document">
              		<form:option value="${document.id }">${document.name }</form:option>
              		</c:forEach>
           	</form:select>
              <br />
            </p>
            <p>
              <label>文章排序（数字越小越靠前）</label>
              <form:input class="text-input small-input" type="text" id="small-input" name="small-input" path="sort"/>
              <br />
            </p>                       	
            <p>
              <label>页面访问次数</label>
              <form:input class="text-input small-input" type="text" id="small-input" name="small-input" path="readNum"   disabled="true"/>
              <br />
            </p>
            <table style="margin: 20px auto;">
            <tr>
            <td>
              <label>文章附图</label>
              <%--<form:select id="picture" style="width: 280px" class="chzn-select" path="resource.id">
              <form:option value="0" label=""/>
              <form:options items="${resources}"/>
           	</form:select>--%>    
           	
           	<!-- 原来的下来选择框 -->
              <form:select name="dropdown" class="small-input" path="cmsResource.id">
              		<form:option value="0">请选择</form:option>
              		<c:forEach  items="${resources }"   var="image">
              		<form:option value="${image.id }">${image.name }</form:option>
              		</c:forEach>
           	</form:select>    
            </td>
            <td>
              <label>栏目</label>
              <form:select name="dropdown" class="small-input" path="cmsChannel.id">
           	    <c:forEach  items="${channels}"  var="channel">
	            		<form:option  cssStyle="color:red;"    value="${channel.id}">${channel.title}</form:option>
	            		<c:forEach  items="${channel.cmsChannels}"  var="childChannel">
	            			<form:option value="${childChannel.id}">---${childChannel.title}</form:option>
	            		</c:forEach>
	            </c:forEach>
           	</form:select>             
            </td>
            <td  style="display: none;">
              <label>文章创建人</label>
              <form:select name="dropdown" class="small-input" path="user.username">
					<form:options items="${users}" itemLabel="cname" itemValue="username"/>
           	</form:select>            
            </td>
            </tr>
            <tr>
            <td>
              <label>文章State</label>
              <form:select name="dropdown" class="small-input" path="state">
                <form:option value="1">正常活动</form:option>
                <form:option value="0">关闭</form:option>
           	</form:select>               
            </td>  
            <td>
            <div>
              <label>时间</label>
            	<form:input path="createTime" value="${date}" onclick="new Calendar().show(this);" />
			</div>
		   </td>       
            <td  style="display: none;">
              <label>Id</label>
              <form:input class="text-input medium-input datepicker" type="text" id="medium-input" name="medium-input"  path="id"/>             
            </td>                    
            </tr>
            </table>
            <div  id="lili"  style="display: none;">
            <c:forEach items="${article.cmsTags }"  var="tagB">
            <span>${tagB.id }</span>
            </c:forEach>
            </div>
            <label>选择标签</label>
            <p id="lucy">
            <c:forEach  items="${tags }"   var="tag"  >
            <input type="checkbox" name="tagsss"    value="${tag.id }"/>${tag.name }
            </c:forEach>
            </P>
            <script type="text/javascript">
            var parent = document.getElementById("lucy");
			var parentB = document.getElementById("lili");
			for(var i = 0;i<parent.childNodes.length;i++){
				var child = parent.childNodes[i];
				if(child.nodeName=="INPUT"){
					for(var j = 0;j<parentB.childNodes.length;j++){
						var childB = parentB.childNodes[j];
						if(childB.nodeName=="SPAN"){
							if(child.value==childB.innerHTML){
								child.checked="checked";  					
							}
						}				
					}
				}
			}
            </script>            
            <p>
              <label>内容</label>
               <form:textarea id="site_bottomContent"   path="news"></form:textarea>
            </p>
            <p>
              <input id="aaa" class="button" type="submit" value="Submit" />
            </p>
            

            
            </fieldset>
            <div class="clear"></div>
            
            <!-- End .clear -->
          </form:form>
        </div>
        <!-- End #tab2 -->
      </div>
      <!-- End .content-box-content -->
    </div>
    <!-- End .content-box -->
    <div class="clear"></div>
    <div id="footer"> <small>
      <!-- Remove this notice or replace it with whatever you want -->
      &#169; Copyright 2015 Gvsun | Powered by <a href="http://www.gvsun.com/">Gvsun</a> | <a href="javascript:void(0);">Top</a> </small> <a href="http://www.gvsun.com/"  target="_blank">http://www.gvsun.com/</a></div>
    <!-- End #footer -->
  </div>
  <!-- End #main-content -->
</div>
<script type="text/javascript">
	$(function() {
		$("p>.button").click(function() {
			var length = $('#large-input').val().length;
			if(length>300){
				alert("字数过长,请在300字以内。");
				return false;
			};
		});
	});
	
</script>
<script type="text/javascript">				
	//生成html编辑栏，设置宽度为1000px
	/* var editor = new UE.ui.Editor({initialFrameWidth:1000});
	editor.render("site_bottomContent"); */
	var editor =new UE.getEditor('site_bottomContent');     
</script> 

<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js"  type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select'           : {search_contains:true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'选项，未找到!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
</script>
</body>
<!-- Download From www.exet.tk-->
</html>
