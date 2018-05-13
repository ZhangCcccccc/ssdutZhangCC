<%@page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<head>
<meta name="decorator" content="iframe"/>
<script type="text/javascript">
	function checkTime(){
		var inputValue = document.getElementById('sectionName').value;
		var campusNumber=document.getElementById('campusNumber').value;
	 		 $.ajax({
	 	           type:"POST",
	 	           url:"${pageContext.request.contextPath}/system/checkTime?timeName="+inputValue+"&campusNumber="+campusNumber,
	 	           success:function(data){
	 	        	  if(data=="ok"){        	  
		 	        	 alert(inputValue +"已存在!");
		 	        	 return false;
	 	        	  }
	 	           }       
	 		 });
		}
	
	function checkDate(){
		var startDate = document.getElementById('startDate');
		var endDate = document.getElementById('endDate');	
	 		 $.ajax({
	 	           type:"POST",
	 	           url:"${pageContext.request.contextPath}/system/checkDate",
	 	           data:{startDate:startDate.value,endDate:endDate.value},
	 	           success:function(data){
	 	        	  if(data=="ok"){        	  
	 	        	 alert("节次的结束时间不能小于开始时间!");
	 	        	 return false;
	 	        	  }
	 	           }       
	 		 });
		}
		
	function saveTime(){
		 $("#form1").attr("action","${pageContext.request.contextPath}/system/saveNewTime"); 
	}
</script>
</head>
<!-- 导航栏 -->
<%-- <div class="navigation">
<div id="navigation">
<ul>
<li><a href="">系统管理</a></li>
<li><a href="${pageContext.request.contextPath}/system/listTime?currpage=1">节次管理</a></li>
<li class="end"><a href="">新增节次</a></li>
</ul>
</div>
</div> --%>
<!-- 导航栏 -->
<div id="TabbedPanels1" class="TabbedPanels">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">新增节次
				<a class="btn btn-edit" type="submit" value="返回" onclick="window.history.go(-1)">返回</a>
				</div>
 <form:form id="form1" method="POST" modelAttribute="time">
   <table>
    <tr>
     <th>节次名字<font color=red>*</font></th>
			<td><form:input class="easyui-validatebox" id="sectionName" path="sectionName" maxlength="50" required="true" validType="length[0,50]" invalidMessage="不能超过50个字符！" onkeyup="value=value.replace(/[^\u4E00-\u9FA5\w]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5\w]/g,''))"  onblur="checkTime();"/>
			<form:hidden path="id"/>
			<input type="hidden" id="campusNumber" value="${campusNumber}">
			</td>
			
	 <th>开始时间<font color=red>*</font></th>
			<td><c:choose>
			<c:when test='${newFlag}' >
			<form:input id="startDate" path="startDate" class="easyui-timespinner" value="${date}" required="true" onblur="checkDate();"/>
			</c:when><c:otherwise>
             <input class="easyui-timespinner" id="startDate" name="startDate" value="<fmt:formatDate value="${time.startDate.time}" pattern="HH:mm:ss"/>"  required="required" onblur="checkDate();"/>
             </c:otherwise>
             </c:choose></td>
    </tr>
    <tr>
   <th>结束时间<font color=red>*</font></th>
            <td><c:choose>
			<c:when test='${newFlag}' >
			<form:input id="endDate" path="endDate" class="easyui-timespinner" value="${date}" required="true" onblur="checkDate();"/>
			</c:when><c:otherwise>
            <input class="easyui-timespinner" id="endDate" name="endDate" value="<fmt:formatDate value="${time.endDate.time}" pattern="HH:mm:ss"/>" required="required" onblur="checkDate();"/>
           </c:otherwise>
           </c:choose>
     </td>
     <th>校区</th>
    <td>
    <form:select path="systemCampus.campusNumber">
    <form:options items="${campus}" itemLabel="campusName" itemValue="campusNumber"/>
    </form:select>
    </td>
     
    </tr>
    <tr>
    <th>排序</th><td><form:input class="easyui-validatebox" id="sequence" path="sequence" maxlength="10" validType="length[0,10]" invalidMessage="不能超过10个字符！" onkeyup="value=value.replace(/[^\u4E00-\u9FA5\w]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5\w]/g,''))" /></td>
    <th>组合:</th><td><form:input class="easyui-validatebox" id="combine" path="combine" maxlength="5" validType="length[0,5]" invalidMessage="不能超过5个字符！" onkeyup="value=value.replace(/[^\u4E00-\u9FA5\w]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5\w]/g,''))" /></td></tr>
   </table>
    <div class="moudle_footer">
        <div class="submit_link">
            <input type="submit" value="提交" onclick="saveTime();" class="alt_btn" >
            <%--<input type="button" value="返回" onclick="window.history.go(-1)">
        --%></div>
    </div>
   </form:form>
</div>
