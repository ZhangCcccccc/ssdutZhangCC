<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
            <form:form id="form_act"  name="form" >
              <div id="title">文章列表</div>
              <a  href="${pageContext.request.contextPath}/admin/newArticle"   class="btn btn-new">新建</a>
              <a  href="javascript:sss();"  class="btn btn-new" >删除所选</a>
               <a class="label" valign="middle" >文章标题：</a>
	            <a><input  name="articleName" style=" width:90px;display:" value="${articleName }"/> </a>
	             <a  href="${pageContext.request.contextPath}/admin/article?page=1" class="btn btn-new" >取消查询</a>
	             <a onclick="subform('${pageContext.request.contextPath}/admin/article?page=1 ');" class="btn btn-new" >查询</a>
	           
            </div>
            
            <table class="news-list">
              <tr>
                  <th width="3%"><input type="checkbox"  id="check-all"  onclick="ccc();"/></th>
                  <th width="40%">文章名称</th>                                    
                  <th width="12%">所属栏目</th>                                    
                  <th width="12%">所属网站</th>                                    
                  <th width="11%">附属资源</th>       
                  <th width="11%">附件</th>                             
                  <th width="7%">更新时间</th>                                    
                  <th width="5%">操作</th>                                    
              </tr>
         
              <c:forEach  items="${articles}"   var="article">
              <tr>
                <td><input type="checkbox"  name="AK"  value="${article.id}"/></td>
                <td><a href="${pageContext.request.contextPath}/admin/editArticle?id=${article.id }">${article.title }</a></td>
                <td>${article.cmsChannel.title }</td>
                <td>${article.cmsChannel.cmsSite.name }</td>
                <td>${article.cmsResource.name }</td>
                <td>${article.cmsDocument.profile }</td>
                <td><fmt:formatDate type="date"  pattern="yy-MM-dd" value="${article.createTime.time }"/></td>
                <td><a  onclick="return confirm('确定删除此文章吗？');"   href="${pageContext.request.contextPath}/admin/deleteArticle?idKey=${article.id}"><img src="${pageContext.request.contextPath}/images/remove.png" title="删除"></td></a>
              </tr>
              </c:forEach>
       
            </table>
            </form:form>
         <div class="gvsun-page">
	<div class="gvsun-page1" style="width:40%;">
		共:${totalRecords}&nbsp;条记录 总页数:${pageModel.totalPage}&nbsp; <input type="hidden" value="${pageModel.totalPage}" id="totalpage" />
	</div>
	<div class="gvsun-page2" style="width:50%;">
		当前页为 :${page}&nbsp; 转到第 ：<input type="text" class="gvsun-input" name="page" id="page" />&nbsp;页
		<a onclick="$('#form_act').attr('action','article?page='+turnPage()+'').submit();"><img src="${pageContext.request.contextPath}/images/icons/go.gif"  /></a> &nbsp; 
		<a onclick="$('#form_act').attr('action','article?page=1').submit();">首页</a>				    
		<a onclick="$('#form_act').attr('action','article?page=${pageModel.previousPage}').submit();">上一页</a>
		<a onclick="$('#form_act').attr('action','article?page=${pageModel.nextPage}').submit();" >下一页</a>
		<a onclick="$('#form_act').attr('action','article?page=${pageModel.lastPage}').submit();">末页</a>
		</div>
		<div  align="right">   
	</div>
</div>               
                </div>
    <!--分页的js脚本  -->
<script src="${pageContext.request.contextPath}/js/common/pagemodel.js"></script>
  <script langauge="javascript">
//如果为查询则提交查询页面，如果为电子表格导出，则导出excel
function subform(gourl){ 
 var gourl;
 form.action=gourl;
 form.submit();
}
</script> 
  <script type="text/javascript">
	
	//返回用户输入的页码
	function turnPage(){
		var page=$("#page").val();//获取页码
		var totalPage=${pageModel.totalPage};//
		
		
		
		
		
		if(page.length==0){
		    alert("请输入！");
		    return 1;
		}else{
		    reg=/^[-+]?\d*$/;
		    if(!reg.test(page)){    
		        alert("对不起，您输入的整数类型格式不正确!");//请将“整数类型”要换成你要验证的那个属性名称！   
		        return 1;
		    }else{
		        if(page<=totalPage){
		        	//$('#form_act').attr('action','AllocationApp2?currpage=1&flag=${flag}').submit();
		        	return page;
		        }else{
		          	  alert("对不起，您输入的整数不正确!");
		          	return 1;
		        }
		    }    
		}
	}
</script>
</body>
</html>