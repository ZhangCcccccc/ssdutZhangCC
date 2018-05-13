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
<script type="text/javascript">
function  A(){
	var array=new Array();
	var parent = document.getElementById("tags"); 
	alert(parent.childNodes.length);
	for(var i = 0;i<parent.childNodes.length;i++){
		var child = parent.childNodes[i];
		if(child.nodeName=="INPUT"){
			if(child.checked){
			array.push(child.value); //将选中的值 添加到 array中 			
			}
		}
	}
	alert(array);
	window.location.href="middleTable?array="+array; 
}

function  checked  ()
{
   var name = document.getElementById("medium-input")
   if (name.value=="")
   {alert("栏目不得为空！");
      return false;  
      }
      return true;
      
}
</script>
</script>

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
        <h3>编辑栏目</h3>
        <ul class="content-box-tabs">
          <li><a  class="default-tab">Forms</a></li>
        </ul>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
     
        <div class="tab-content default-tab"  id="tab1">
          <form:form  action="${pageContext.request.contextPath}/admin/saveChannel"    method="POST"  modelAttribute="channel"  onsubmit ="return  checked()" >
            <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <p>
              <label>栏目名称</label>
              <form:input class="text-input medium-input datepicker" type="text" id="medium-input" name="medium-input"  path="title"/>
            </p>           
            <p>
              <label>栏目超链接</label>
              <form:input class="text-input small-input" type="text" id="small-input" name="small-input" path="hyperlink"/>
              <br />
            </p>
            <p>
              <label>栏目概要</label>
              <form:input class="text-input large-input" type="text" id="large-input" name="large-input"  path="profile"/>
            </p>    
            <table style="margin: 20px auto;">
            <tr>
            <td>
              <label>栏目图片</label>
              <form:select name="dropdown" class="small-input" path="cmsResource.id">
					<form:options items="${resources}" itemLabel="name" itemValue="id"/>
           	</form:select>    
            </td>
            <td>
              <label>父栏目</label>
              <form:select name="dropdown" class="small-input" path="cmsChannel.id">
              		<form:option  value="0">请选择</form:option>
            	    <c:forEach  items="${channels}"  var="channel">
		            		<form:option  cssStyle="color:red;"    value="${channel.id}">${channel.title}</form:option>
		            		<c:forEach  items="${channel.cmsChannels}"  var="childChannel">
		            			<form:option value="${childChannel.id}">---${childChannel.title}</form:option>
		            		</c:forEach>
		            </c:forEach>
           	  </form:select>             
            </td>
            <td style="display: none;">
              <label>栏目创建人</label>
              <form:select name="dropdown" class="small-input" path="user.username">
					<form:options items="${users}" itemLabel="cname" itemValue="username"/>
           	</form:select>            
            </td>
            </tr>
            <tr>
            <td>
              <label>栏目State</label>
              <form:select name="dropdown" class="small-input" path="state">
                <form:option value="1">正常活动</form:option>
                <form:option value="0">关闭</form:option>
           	</form:select>               
            </td>
            <td  style="display:none;">
              <label>所属网站</label>
              <form:select name="dropdown" class="small-input" path="cmsSite.siteurl">
					<form:options items="${sites}" itemLabel="name" itemValue="siteurl"/>
           	</form:select>             
            </td> 
            <td  style="display: none;">
              <label>Id</label>
              <form:input class="text-input medium-input datepicker" type="text" id="medium-input" name="medium-input"  path="id"/>             
            </td>                    
            </tr>
            </table>
            <div  id="lili"  style="display: none;">
            <c:forEach items="${channel.cmsTags }"  var="tagB">
            <span>${tagB.id }</span>
            </c:forEach>
            </div>
            <label>选择标签</label>
            <p id="lucy">
            <c:forEach  items="${tags }"   var="tag"  >
            <input type="checkbox" name="tagsss" value="${tag.id }"/>${tag.name }
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
</body>
<!-- Download From www.exet.tk-->
</html>
