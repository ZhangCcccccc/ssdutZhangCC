<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page language="java" isELIgnored="false"	contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
  <head>
  <meta name="decorator" content="iframe" />
  <style>
  .tool-box input[type=text]{
	width:100px!important;}
  </style>
<script type="text/javascript">

	//点击查看，后台获取消息内容
	function setMsgState(id){
	//	$("#state"+id).text("已处理");
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/setMsgStateNew?id="+id,
			dataType:'json',
			success:function(data){
				if(data[2] == ''){
					$("#magContent").html("<font size='2px;'>"+data[1]+"</font>");
					$("#msg").window('open');
					
					if(data[0]==0){
						$(".red_numb",parent.document).each(function(){
				    		$(this).text(Number($(this).text())-1);
				    	})	
					}
				}
				else{
					if(data[2] =='timetableStudent' ){
						$("#magContent").html("<font size='2px;'> <a href='"+"${pageContext.request.contextPath}"+data[1]+"' target='_parent'>查看</a></font>");
						$("#msg").window('open');
						
						if(data[0]==0){
							$(".red_numb",parent.document).each(function(){
					    		$(this).text(Number($(this).text())-1);
					    	})	;
						}
					}
					if(data[2] =='timetable' ){
						$("#magContent").html("<font size='2px;'> <a onclick='setTurnType("+"\"timetable\""+",\"${pageContext.request.contextPath}"+data[1]+"\")'>查看</a></font>");
						$("#msg").window('open');
						
						if(data[0]==0){
							$(".red_numb",parent.document).each(function(){
					    		$(this).text(Number($(this).text())-1);
					    	})	;
						}
					}
					if(data[2] =='labReservation' ){
						$("#magContent").html("<font size='2px;'> <a onclick='setTurnType("+"\"labReservation\""+",\"${pageContext.request.contextPath}"+data[1]+"\")'>查看</a></font>");
						$("#msg").window('open');
						
						if(data[0]==0){
							$(".red_numb",parent.document).each(function(){
					    		$(this).text(Number($(this).text())-1);
					    	})	;
						}
					}
				}
			},
			error:function(){
				alert("数据加载失败，请检查网络");
			}
		});
	}
	 
	 
	 	function setTurnType(type,url){
		$.ajax({
			url:"${pageContext.request.contextPath}/self/setTurnTypeSession",
			data:{sessionType:type},
			type:"POST",
			success:function(s){
				window.location.href=url;
			}
		});
	}
</script>
<script langauge="javascript">
//如果为查询则提交查询页面，如果为电子表格导出，则导出excel
function subform(gourl){ 
 var gourl;
 form.action=gourl;
 form.submit();
}
</script> 
  </head>
  
  <body>
  <!--消息内容弹出框-->
<div id="msg" class="easyui-window" title="消息" closed="true" iconCls="icon-add" style="width:450px;">
	<table class="tb" style="margin:10px"> 
	<tr id="magContent">
	
	</tr>	
	</table>
	<br>
	<button class="btn" onClick="location.reload();" style="margin:10px;">关闭</button>
</div>
  
  
  
   <div class="right-content">
   <div class="title">
	<div id="title">所有消息列表</div>
</div>
<div id="TabbedPanels1" class="TabbedPanels">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		<div class="content-box">	
			<div class="tool-box">
				<form:form id="form_act"  name="form" action="${pageContext.request.contextPath}/personal/messageList?currpage=1 " method="post" modelAttribute="message">
					<table style="width:90%;">
						<tbody>
							<tr>
								<td class="label" valign="middle">
								<span style="float:left;line-height:30px;" >消息人：</span>
								<form:input  path="sendUser" style="width:90px;float:left;"/>
								</td>
								<td class="label" valign="middle" >
								<span style="float:left;line-height:30px;" >消息标题：</span>
                                <form:input path="title" style="width:90px;float:left;"/></td>
								<td class="label" valign="middle">申请日期:</td>
								<td>
								<input  class="easyui-datebox"  id="starttime" name="starttime" value="${starttime}"  type="text"  onclick="new Calendar().show(this);" style="width:100px;" />
								</td>
								<td style="width:20px;"><span style="margin-left:-35px;">到</span></td>
								<td>
								<span style="margin-left:-50px;">
								<input class="easyui-datebox"  id="endtime" name="endtime"  value="${endtime}" type="text"  onclick="new Calendar().show(this);" style="width:100px;" />
								</span>
								</td>
								<td>
							    	<input class="btn btn-new-new"  type="button"  onclick="subform('${pageContext.request.contextPath}/personal/messageList?currpage=${page}');" value="查询" />
								</td>
								<td>
									<a class="btn btn-new" href="${pageContext.request.contextPath}/personal/messageList?currpage=1" ><span>取消查询</span> </a>
								</td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
<div class="content-box">				
<div id="contentarea">
<div id="content">
<div class="content-box">
	<table >
			<thead>
				<tr>
					<th>序号</th>
					<%--<th>❉</th>
					--%><th>消息人</th>
					<th>消息标题</th>
					<th>消息人单位</th>
					
					<th>发送时间</th>
					<th>消息状态</th>
					<th>操作</th>
				</tr>
			</thead>
					<tbody>
		<c:forEach items="${messages}" var="current"  varStatus="i">	
        	<tr>
            	<td>${i.count}</td>
            	<%--<td>
            	<c:if test="${current.cond==0}">
				紧急
				</c:if>
				<c:if test="${current.cond==1}">
				一般
				</c:if>
				</td>
            	--%><td>${current.sendUser}</td>
            	
            	<td>${current.title }</td>
            	<td>${current.sendCparty }</td>
            	<td><fmt:formatDate value="${current.createTime.time}" pattern="yyyy-MM-dd"/></td>
            	<td>
            	<c:if test="${current.messageState==0}">
            	未读
            	</c:if>
            	<c:if test="${current.messageState==1}">
            	已读
            	</c:if> 
            	</td>
            	
            	
            	<td>
            		<a onclick="viewMessage(${current.id})" href="javascript:void(0);">查看</a>
					<a onclick='deleteMessage(${current.id})'>删除</a> 
            	</td>	           	     
         	</tr>
        </c:forEach>
      	</tbody>
	</table>
<div class="gvsun-page">
	<div class="gvsun-page1">
		共:${totalRecords}&nbsp;条记录 总页数:${pageModel.totalPage}&nbsp; <input type="hidden" value="${pageModel.totalPage}" id="totalpage" />
	</div>
	<div class="gvsun-page2">
		当前页为 :${page}&nbsp; 转到第 ：<input type="text" class="gvsun-input" name="currpage" id="page" />&nbsp;页
		<a onclick="$('#form_act').attr('action','messageList?currpage='+turnPage()+'').submit();"><img src="${pageContext.request.contextPath}/images/icons/go.gif"  /></a> &nbsp; 
		<a onclick="$('#form_act').attr('action','messageList?currpage=1').submit();">首页</a>				    
		<a onclick="$('#form_act').attr('action','messageList?currpage=${pageModel.previousPage}').submit();">上一页</a>
		<a onclick="$('#form_act').attr('action','messageList?currpage=${pageModel.nextPage}').submit();" >下一页</a>
		<a onclick="$('#form_act').attr('action','messageList?currpage=${pageModel.lastPage}').submit();">末页</a>
		</div>
		<div  align="right">   
	</div>
</div>
</div>
</div>
</div>

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
      '.chzn-select'           : {search_contains:true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    function deleteMessage(id){  
    	if(confirm("删除?"))
    	{
    		$.ajax({
    	        url:"${pageContext.request.contextPath}/deleteMessage?idKey="+id,
    	        type: 'POST',   
    	        async: false,  
    	        cache: false,  
    	        contentType: false,  
    	        processData: false, 
    	        
    	        error: function(request) {
    	            alert("请求错误");
    	        },
    	        success: function(data) {
    	        	parent.location.reload(); 
    	        }
    	    });
    		
    	}
    }
    
    function viewMessage(id){
				$.ajax({
					type: "POST",
					url: "${pageContext.request.contextPath}/setMsgStateNew?id="+id,
					dataType:'json',
					success:function(data){
						if(data[2] == 'timetableStudent'){
							window.location.href="${pageContext.request.contextPath}"+data[1];
						}
						if(data[2] == 'timetable'){
							$.ajax({
				           url:"${pageContext.request.contextPath}/self/setTurnTypeSession",
				           data:{sessionType:'timetable'},
				           type:"POST",
				           success:function(s){
				           	window.location.href="${pageContext.request.contextPath}"+data[1];
				           }
				           });
						}
						if(data[2] =='labReservation'){
							$.ajax({
				           url:"${pageContext.request.contextPath}/self/setTurnTypeSession",
				           data:{sessionType:'labReservation'},
				           type:"POST",
				           success:function(s){
				           	window.location.href="${pageContext.request.contextPath}"+data[1];
				           }
				           });
						}
					},
					error:function(){
						alert("数据加载失败，请检查网络");
					}
				});
			}
</script>
<!-- 下拉框的js -->
</body>
</html>