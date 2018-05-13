<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上海中医药大学解剖实验课</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/songProject/style.css" />
	
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery-1.7.1.min.js" ></script>
<script type="text/javascript">
	//首页
	function first(url){
		//document.queryForm.action=url;
		//document.queryForm.submit();
		window.location = url;
	}
	//末页
	function last(url){
		//document.queryForm.action=url;
		//document.queryForm.submit();
		window.location = url;
	}
	//上一页
	function previous(url){
		var page=${page};
		if(page==1){
			page=1;
		}else{
			page=page-1;
		}
		//alert("上一页的路径："+url+page);
		//document.queryForm.action=url+page;
		//document.queryForm.submit();
		window.location = url+page;
	}
	//下一页
	function next(url){
		var totalPage=${pageModel.totalPage};
		var page=${page};
		if(page>=totalPage){
			page=totalPage;
		}else{
			page=page+1;
		}
		//alert("下一页的路径："+page);
		//document.queryForm.action=url+page;
		//document.queryForm.submit();
		window.location = url+page;
	}
	
	function showContent(obj){
		var name = $(obj).html();
		$(obj).parent().parent().parent().attr("class","content_right");
		$(obj).parent().parent().parent().html($(obj).next().html());
		$(".list_box2").html(name);
		$(".nav").append("&nbsp;>&nbsp;<a href='javascript:void(0);'>"+name+"</a>")
	}
</script> 


</head>
<body>


<div id="content_list">

<!-- 当前位置栏开始  -->
    <div class="nav">
			&nbsp;当前位置： 
			<a href="${pageContext.request.contextPath}/cms/courseSiteNew">首页</a>&nbsp;>&nbsp;
			<a href="${pageContext.request.contextPath}/cms/channel/findChannelList?tagId=${tCourseSiteChannel.TCourseSiteTag.id }">${tCourseSiteChannel.channelName}</a>&nbsp;>&nbsp;
    		<c:if test="${tCourseSiteChannels[0].TCourseSiteChannelsForChannelId.size()==0 }">
				<a href="${pageContext.request.contextPath}/cms/channel/findArticalsByChannelId?channelId=${tCourseSiteChannels[0].id }&currpage=1">${tCourseSiteChannels[0].channelName}</a>
			</c:if>
			<c:if test="${tCourseSiteChannels[0].TCourseSiteChannelsForChannelId.size()>0 }">
				<a href="${pageContext.request.contextPath}/cms/channel/findSecondChannelsByChannelId?channelId=${tCourseSiteChannels[0].id }">${tCourseSiteChannels[0].channelName}</a>
			</c:if>
    </div>
<!-- 当前位置栏结束  -->
    
<!-- 左侧栏开始  -->
    <div class="content_list_left">
	    <div class="list_left_title">${tCourseSiteChannel.channelName}</div>
		<div class="list_left_list">
		     <ul>
					<c:forEach items="${tCourseSiteChannels}" var="tCourseSiteChannel" varStatus="i">
						<li>
							<!-- 不包含三级栏目则查询文章列表，否则查询三级栏目列表 -->
							<c:if test="${tCourseSiteChannel.TCourseSiteChannelsForChannelId.size()==0 }">
								<a href="${pageContext.request.contextPath}/cms/channel/findArticalsByChannelId?channelId=${tCourseSiteChannel.id }&currpage=1" <c:if test="${tCourseSiteChannel.id==tCourseSiteChannels[0].id }">style="color:#0146a8; font-weight:bold"</c:if>>${tCourseSiteChannel.channelName}</a>&nbsp;&nbsp;
							</c:if>
							<c:if test="${tCourseSiteChannel.TCourseSiteChannelsForChannelId.size()>0 }">
								<a href="${pageContext.request.contextPath}/cms/channel/findSecondChannelsByChannelId?channelId=${tCourseSiteChannel.id }" <c:if test="${tCourseSiteChannel.id==tCourseSiteChannels[0].id }">style="color:#0146a8; font-weight:bold"</c:if>>${tCourseSiteChannel.channelName}</a>&nbsp;&nbsp;
							</c:if>
						</li>
				</c:forEach>
			 </ul>
		</div>
	</div>
<!-- 左侧栏结束  -->	




<!-- 内容栏开始  -->		
	<div class="content_list_right">
	   	<div class="list_box">
	   
	      	<div class="list_box1"></div>

		  	<div class="list_box2">${tCourseSiteChannels[0].channelName}</div>
	       
		  	<div class="list_box3"></div>
		  
		  	<div class="list_box4">
               	<div class="list_right">
			       	<ul>
						<c:forEach items="${tCourseSiteArticals}" var="tCourseSiteArtical" varStatus="i">
							<li>
								<a href="javascript:void(0);" onclick="showContent(this)">${tCourseSiteArtical.name}</a>
								<span style="display: none">${tCourseSiteArtical.content}</span>
							</li>
		 				</c:forEach>
				    </ul>
			   	</div>
			   	<c:if test="${totalRecords>10 }">
			   		<div class="page" >
						${totalRecords}条记录,共${pageModel.totalPage}页 
						<a href="${pageContext.request.contextPath}/cms/channel/findArticalsByChannelId?channelId=${tCourseSiteArticals[0].TCourseSiteChannel.id }&currpage=1" target="_self">首页</a> 
						<a href="${pageContext.request.contextPath}/cms/channel/findArticalsByChannelId?channelId=${tCourseSiteArticals[0].TCourseSiteChannel.id }&currpage=${pageModel.previousPage}" target="_self">上一页</a> 
						第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
							<option value="${pageContext.request.contextPath}/cms/channel/findArticalsByChannelId?channelId=${tCourseSiteArticals[0].TCourseSiteChannel.id }&currpage=${page}">${page}</option>
							<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">
								<c:if test="${j.index!=page}">
									<option value="${pageContext.request.contextPath}/cms/channel/findArticalsByChannelId?channelId=${tCourseSiteArticals[0].TCourseSiteChannel.id }&currpage=${j.index}">${j.index}</option>
								</c:if>
							</c:forEach>
						</select>页 
						<a href="${pageContext.request.contextPath}/cms/channel/findArticalsByChannelId?channelId=${tCourseSiteArticals[0].TCourseSiteChannel.id }&currpage=${pageModel.nextPage }" target="_self">下一页</a> 
						<a href="${pageContext.request.contextPath}/cms/channel/findArticalsByChannelId?channelId=${tCourseSiteArticals[0].TCourseSiteChannel.id }&currpage=${pageModel.totalPage}" target="_self">末页</a>
					</div>
			   	</c:if>
				
		  	</div>
		  	
	       
			<c:if test="${totalRecords==0}">
	       		<div class="list_box2">该栏目下没有创建文章</div>
				<div class="list_box3"></div>
				<div class="list_box4">
				<div class="content_right">赶紧去创建一篇文章吧！</div>
				</div>
	       
	       	</c:if>
		  
			<div class="list_box5"></div>
			<div class="list_box6"></div>
			<div class="list_box7"></div>
		</div>		
	</div>
	
<!-- 内容栏结束  -->	

<script type="text/javascript">
	/* 如果仅有一篇文章，默认触发点击事件 */
    var count = $(".list_box4 ul li").size();
    if (count == 1) {
		$(".list_box4 ul li a").click();
	}

</script>	
</div> 


</body>
</html>