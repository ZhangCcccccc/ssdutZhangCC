<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cloud CMS</title>
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
		        function sss(){
		        var array=new Array();
		        var flag=false; //判断是否一个未选   
		        $("input[name='RK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
		                    if ($(this).is(':checked')) { //判断是否选中    
		                        flag = true; //只要有一个被选择 设置为 true  
		                    }  
		                })  
		        if (flag) {  
		           $("input[name='RK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
		                        if ($(this).is(':checked')) { //判断是否选中
		                            array.push($(this).val()); //将选中的值 添加到 array中 
		                        }  
		                    })  
		      	        if (!confirm("您确认要删除吗？删除后不可恢复！")) {
                            window.event.returnValue = false;
         }              
		           //将要集体删除的数据 传递给action处理   
				   window.location.href="deleteMultipleResource?array="+array+"&type="+${type}; 
		        } else {   
		        	alert("请至少选择一条记录");  
		        	}  
		    	}
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
     
      <li><a class="shortcut-button" href="${pageContext.request.contextPath}/admin/newDocument"><span> <img src="${pageContext.request.contextPath}/images/systemBack/icons/image_add_48.png" alt="icon" /><br />
        上传附件 </span></a></li>
      
    </ul>
    <!-- End .shortcut-buttons-set -->
    <div class="clear"></div>

   
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>Content box</h3>
        <ul class="content-box-tabs">
          <li><a  class="default-tab">Table</a></li>
        </ul>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">
          <table>
            <thead>
              <tr>
                <%--<th>
                  <input class="check-all" type="checkbox" />
                </th>
                --%><th>名称</th>
                <th>资源名称</th>
                <%--<th>所属栏目</th>
                --%><th>录入时间</th>
                <th>操作</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="6">
                  <div class="bulk-actions align-left">
                    <%--<a class="button"   href="javascript:sss();">删除所有选中</a> --%></div>
                  <!-- End .pagination -->
                  <div class="clear"></div>
                </td>
              </tr>
            </tfoot>
            <tbody>
            <c:forEach  items="${documents}"  var="document">
              <tr><%--
                <td>
                  <input type="checkbox"   name="RK"   value="${document.id }"/>
                </td>
                --%><td><a   title="title">${document.name }</a></td>
                <td><a >${document.profile }</a></td>
                <%--<td >
                <c:if test="${document.tag==0 }">合作信息</c:if>
                <c:if test="${document.tag==1 }">活动公告</c:if>
                </td>
                --%><td><fmt:formatDate type="date"  pattern="yyyy-MM-dd"   value="${document.createTime.time }" /></td>
                <td>
                	<a onclick="return confirm('确定删除此附件吗？');"  href="${pageContext.request.contextPath}/admin/deleteDocument?Id=${document.id}" title="Delete"><img src="${pageContext.request.contextPath}/images/systemBack/icons/cross.png" alt="Delete" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                	<a href="${pageContext.request.contextPath}/admin/editDocument?documentIdKey=${document.id}" title="Edit Meta"><img src="${pageContext.request.contextPath}/images/systemBack/icons/hammer_screwdriver.png" alt="Edit Meta" /></a> 
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
        <section  id=page >
       <span> 总共${totalRecords}记录 &nbsp共${pageModel.totalPage}页</span>
       <span><a href="${pageContext.request.contextPath}/admin/documentsList?page=${pageModel.firstPage}">首页</a></span>
        <span><a href="${pageContext.request.contextPath}/admin/documentsList?page=${pageModel.previousPage}">上一页</a></span>
        <span><a href="${pageContext.request.contextPath}/admin/documentsList?page=${pageModel.nextPage}">下一页</a></span> 
        <span><a href="${pageContext.request.contextPath}/admin/documentsList?page=${pageModel.lastPage}">尾页</a></span>
        </section>
      </div>
      
    </div>
    
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
