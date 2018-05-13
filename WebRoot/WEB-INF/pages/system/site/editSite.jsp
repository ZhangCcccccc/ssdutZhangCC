<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--                       CSS                       -->
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
<!-- 富文本的css -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
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
        <h3>编辑网站</h3>
        <ul class="content-box-tabs">
          <li><a  class="default-tab">Forms</a></li>
        </ul>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
     
        <div class="tab-content default-tab"  id="tab1">
          <form:form  action="${pageContext.request.contextPath}/admin/saveSite"  method="POST"  modelAttribute="site" >
            <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <p>
              <label>网站名称</label>
              <form:input class="text-input medium-input datepicker" type="text" id="medium-input" name="medium-input"  path="name"/>
            </p>           
            <p>
              <label>网站英文唯一标识符</label>
              <c:if test="${empty site.siteurl }">
              <form:input class="text-input small-input" type="text" id="small-input" name="small-input" path="siteurl"/>
              </c:if>
              <c:if test="${not empty site.siteurl }">
              <form:input class="text-input small-input" type="text" id="small-input" name="small-input" path="siteurl"  readonly="true"/>
              </c:if>
              <br />
            </p>
<%-- 
            <p>
              <label>网站底部文字</label>
              <form:input class="text-input large-input" type="text" id="large-input" name="large-input" path=""/>
            </p>
           --%>  
            <table style="margin: 20px auto;">
            <tr>
            <td style="display:none;">
              <label>网站Logo</label>
              <form:select name="dropdown" class="small-input" path="imageResource">
              		<c:forEach items="${resources }"  var="video">
              		<c:if test="${video.imageVideo==0 }">
              		<form:option value="${video.id }">${video.name }</form:option>
              		</c:if>
              		</c:forEach>
           	</form:select>    
            </td>
            <td style="display:none;">
              <label>网站视频资料</label>
              <form:select name="dropdown" class="small-input" path="videoResource">
              		<c:forEach items="${resources }"  var="video">
              		<c:if test="${video.imageVideo==1 }">
              		<form:option value="${video.id }">${video.name }</form:option>
              		</c:if>
              		</c:forEach>
           	</form:select>             
            </td>
            <td style="display:none;">
              <label>网站创建人</label>
              <form:select name="dropdown" class="small-input" path="user.username">
					<form:options items="${users}" itemLabel="cname" itemValue="username"/>
           	</form:select>            
            </td>
            </tr>
            <tr>
            <td>
              <label>选择模板</label>
              <form:select name="dropdown" class="small-input" path="cmsTemplate.id">
					<form:options items="${templates}" itemLabel="name" itemValue="id"/>
           	</form:select>              
            </td>
            <td >
              <label>网站State</label>
              <form:select name="dropdown" class="small-input" path="state">
                <form:option value="1">正常活动</form:option>
                <form:option value="0">关闭</form:option>
           	</form:select>               
            </td>
            <td  style="display:none;">
            <form:input type="text"  path="current"  />
            </td>
            <td style="display:none;">
              <label>附属照片</label>
              <form:select name="dropdown" class="small-input" path="cmsResource.id">
              		<c:forEach items="${resources }"  var="video">
              		<c:if test="${video.imageVideo==0 }">
              		<form:option value="${video.id }">${video.name }</form:option>
              		</c:if>
              		</c:forEach>
           	</form:select>              
            </td>
            </tr>
            </table>
            <p>
              <label>网站底部文字</label>
               <form:textarea id="site_bottomContent" name="textfield"  path="bottomContent"></form:textarea>
            </p>
            <p>
              <input class="button" type="submit" value="Submit" />
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
    	
    <div id="footer"> <small>
      <!-- Remove this notice or replace it with whatever you want -->
      &#169; Copyright 2015 Gvsun | Powered by <a href="http://www.gvsun.com/">Gvsun</a> | <a href="javascript:void(0);">Top</a> </small> <a href="http://www.gvsun.com/"  target="_blank">http://www.gvsun.com/</a></div>
    <!-- End #footer -->
  </div>
  <!-- End #main-content -->
</div>
<script type="text/javascript">				
	//生成html编辑栏，设置宽度为888px
	//var editor = new UE.ui.Editor({initialFrameWidth:1000});
	//editor.render("site_bottomContent");	  
	var editor = UE.getEditor('site_bottomContent')
</script> 
</body>
<!-- Download From www.exet.tk-->
</html>
