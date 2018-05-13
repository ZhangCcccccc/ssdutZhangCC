<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
         <script type="text/javascript">
		        function sss(){
		        var array=new Array();
		        var flag=false; //判断是否一个未选   
		        $("input[name='CK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
		                    if ($(this).is(':checked')) { //判断是否选中    
		                        flag = true; //只要有一个被选择 设置为 true  
		                    }  
		                })  
		        if (flag) {  
		           $("input[name='CK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
		                        if ($(this).is(':checked')) { //判断是否选中
		                            array.push($(this).val()); //将选中的值 添加到 array中 
		                        }  
		                    })  
		                 if (!confirm("您确认要删除吗？删除后不可恢复！")) {
                            window.event.returnValue = false;
         }          
		           
		           //将要集体删除的数据 传递给action处理   
				   window.location.href="deleteMultipleChannels?array="+array; 
		        } else {   
		        	alert("请至少选择一条记录");  
		        	}  
		    	}
		    	
		    	function ccc(){
         		  	var checkBoxAll = document.getElementById("check-all"); 	
					$("input[name='CK']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                      $(this).attr('checked', checkBoxAll.checked);
                    })    
				//	checkBoxAll.parent().parent().parent().find("input[type='checkbox']").attr('checked', $(this).is(':checked'));
		    	}
        </script>     
</head>

<body>
<div class="table">
            <div class="title">
              <div id="title">栏目列表</div>
              <a  href="${pageContext.request.contextPath}/admin/newChannel"  class="btn btn-new">新建</a>
              <a  href="javascript:sss();"   class="btn btn-new">删除所选</a>
            </div>
            <table class="news-list">
              <tr>
                  <th width="3%"><input type="checkbox"  onclick="ccc();"  id="check-all"/></th>
                  <th width="7%">栏目名称</th>                                    
                  <th width="20%">栏目超链接</th>                                    
                  <th width="10%">栏目父栏目</th>                                    
                  <th width="15%">栏目所属网站</th>                                    
                  <th width="15%">栏目标签</th>                                    
                  <th width="8%">栏目</th>                                    
                  <th width="7%">更新时间</th>                                    
                  <th width="5%">删除</th>                                    
                  <th width="5%">Articles</th>                                    
                  <th width="5%">子栏目</th>                                    
              </tr>
              <c:forEach  items="${channels }"  var="channel">
              <tr>
                <td><input type="checkbox"  name="CK"  value="${channel.id }"/></td>
                <td><a  href="${pageContext.request.contextPath}/admin/editChannel?id=${channel.id}"  style="color: #8c4356;">${channel.title }</a></td>
                <td>${channel.hyperlink }</td>
                <td>${channel.cmsChannel.title }</td>
                <td>${channel.cmsSite.name }</td>
                <td>
                <c:forEach  items="${channel.cmsTags }"  var="tag">
                ${tag.name }
                </c:forEach>
                </td>
                <td>图片栏目</td>
                <td><fmt:formatDate type="date"  pattern="yy-MM-dd" value="${channel.createTime.time }"/></td>
                <td><a  onclick="return confirm('确定删除此栏目吗？');"   href="${pageContext.request.contextPath}/admin/deleteChannel?idKey=${channel.id}"><img src="${pageContext.request.contextPath}/images/remove.png"></a></td>
                <td><a   href="${pageContext.request.contextPath}/admin/channelListArticles?idKey=${channel.id}&page=1"><img src="${pageContext.request.contextPath}/images/systemBack/articles.jpg"  style="width:30px;"></a></td>
                <td><a  href="${pageContext.request.contextPath}/admin/getChildChannelsByChannelId?id=${channel.id}" ><img src="${pageContext.request.contextPath}/images/nav.png"  style="width:30px;" ></a></td>
              </tr>
              </c:forEach> 
            </table>             
                </div>
    
  
</body>
</html>