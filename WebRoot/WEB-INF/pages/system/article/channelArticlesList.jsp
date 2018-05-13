<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
         <script type="text/javascript">
		        function sss(){
		        var array=new Array();
		        var flag=false; //判断是否一个未选   
		        $("input[name='AK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
		                    if ($(this).is(':checked')) { //判断是否选中    
		                        flag = true; //只要有一个被选择 设置为 true  
		                    }  
		                })  
		        if (flag) {  
		           $("input[name='AK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
		                        if ($(this).is(':checked')) { //判断是否选中
		                            array.push($(this).val()); //将选中的值 添加到 array中 
		                        }  
		                    })  
		             if (!confirm("您确认要删除吗？删除后不可恢复！")) {
                            window.event.returnValue = false;
         }          
		            
		           //将要集体删除的数据 传递给action处理   
				   window.location.href="deleteMultipleArticles?array="+array; 
		        } else {   
		        	alert("请至少选择一条记录");  
		        	}  
		    	}
		    	
		      function ccc(){
         		  	var checkBoxAll = document.getElementById("check-all"); 	
					$("input[name='AK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                      $(this).attr('checked', checkBoxAll.checked);
                    })    
				//	checkBoxAll.parent().parent().parent().find("input[type='checkbox']").attr('checked', $(this).is(':checked'));
		    	}
        </script>      
</head>

<body>
<div class="table">
            <div class="title">
              <div id="title">文章列表</div>
              <a  href="${pageContext.request.contextPath}/admin/newArticle"   class="btn btn-new">新建</a>
              <a  href="javascript:sss();"  class="btn btn-new"  >删除所选</a>
            </div>
            <table class="news-list">
              <tr>
                  <th width="3%"><input type="checkbox"  id="check-all"  onclick="ccc();" /></th>
                  <th width="40%">文章名称</th>                                    
                  <th width="12%">所属栏目</th>                                    
                  <th width="12%">所属网站</th>                                    
                  <th width="11%">附属资源</th>                                    
                  <th width="7%">更新时间</th>                                    
                  <th width="5%">删除</th>                                    
              </tr>
         
              <c:forEach  items="${articles}"   var="article">
              <tr>
                <td><input type="checkbox"  name="AK"  value="${article.id}"/></td>
                <td><a href="${pageContext.request.contextPath}/admin/editArticle?id=${article.id }">${article.title }</a></td>
                <td>${article.cmsChannel.title }</td>
                <td>${article.cmsChannel.cmsSite.name }</td>
                <td>${article.cmsResource.name }</td>
                <td><fmt:formatDate type="date"  pattern="yy-MM-dd" value="${article.createTime.time }"/></td>
                <td><img src="${pageContext.request.contextPath}/images/remove.png"></td>
              </tr>
              </c:forEach>
       
            </table>
            <div class="page" >
                ${count }条记录,共${maxpage }页
                <a href="${pageContext.request.contextPath}/admin/channelListArticles?idKey=${idKey }&page=1"  target="_self">首页</a>			    
                <a href="${pageContext.request.contextPath}/admin/channelListArticles?idKey=${idKey }&page=${page-1}" >上一页</a>
                <a href="${pageContext.request.contextPath}/admin/channelListArticles?idKey=${idKey }&page=${page+1}" >下一页</a>
                <a href="${pageContext.request.contextPath}/admin/channelListArticles?idKey=${idKey }&page=${maxpage}">末页</a>
            </div>                   
                </div>
    
  
</body>
</html>