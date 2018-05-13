<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript"> 
$(function(){
$(".datebox :text").attr("readonly","readonly"); 
  $('#term_name').blur(function(){
            var name = $('#term_name').val();
            $.trim(name);
            $('#yearcode').val(name.substr(0,9));
            if(name.indexOf('一') >= 0){
                $('#termcode').val(1);
            } else {
                if(name.indexOf('二') >= 0) {
                    $('#termcode').val(2);
                } else {
                     $('#term_name').val("");
                      $('#yearcode').val("");
                       $('#termcode').val("");
                    $.messager.alert('错误','输入有误!<br>请严格按照格式输入！');
                    
                }
            }
        });
});

function checkTerm(){
	var inputValue = document.getElementById('term_name');
 		 $.ajax({
 	           type:"POST",
 	           url:"${pageContext.request.contextPath}/tcoursesite/checkTerm",
 	           data:{termName:inputValue.value},
 	           success:function(data){
 	        	  if(data=="ok"){        	  
 	        	 alert(inputValue.value +"已存在!");
 	        	 return false;
 	        	  }
 	           }       
 		 });
	}
	
</script>
</head>
<!-- 导航栏 -->
    <div class="lest_content " >
        <div class="left_nav">
        	<div class="left_user_box">
                <img src="${pageContext.request.contextPath}/images/index/user.png"/>
            </div>
            <div class="left_nav_box">
                <ul class="left_nav_list">
	                <sec:authorize ifAnyGranted="ROLE_TEACHER">
	                    <li <c:if test="${select eq 'tcourse' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listSelectCourse?currpage=1">选课组管理</a>
	                    </li>
	                </sec:authorize>
	                <sec:authorize ifAnyGranted="ROLE_SUPERADMIN">
	                    <li <c:if test="${select eq 'authority' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listUserAuthority">权限管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'user' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listUser?currpage=1">用户管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'term' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/listTerm?currpage=1">学期管理</a>
	                    </li>
	                    <li <c:if test="${select eq 'visualization' }">class="selected"</c:if>><a href="${pageContext.request.contextPath}/tcoursesite/visualization/roomList?page=1">可视化管理</a>
	                    </li>
		            </sec:authorize>
                </ul>
            </div>  
            <div class="power2" style="display: block;">
                Power by <a href="http://www.gvsun.com" target="_blank">Gvsun</a>
            </div>
        </div>
<!-- 导航栏 -->
<div id="TabbedPanels1" class="TabbedPanels" style="float:right;width:80%">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">修改学期
				<%--<input class="btn btn-edit" type="submit" value="提交" onclick="checkTerm0();"/>
            	<a class="btn btn-edit" type="submit" value="返回" onclick="window.history.go(-1)">返回</a>
			--%><%--<a class="btn btn-edit" type="submit" value="返回" onclick="window.history.go(-1)">返回</a>
            <a class="btn btn-edit" type="button" value="提交" onclick="subform('${pageContext.request.contextPath}/saveNewTerm');" >保存</a>
            <input type="button"  onclick="subform('${pageContext.request.contextPath}/saveNewTerm');" value="查询" />
    --%></div>  
 <form:form id="myForm" action="${pageContext.request.contextPath}/tcoursesite/saveNewTerm"  name="myForm" method="POST" modelAttribute="schoolTerm">
 <%--<input class="btn btn-edit"  type="submit" value="提交">--%>
 <table>
 	<form:hidden path="id" value="${idKey }"/>
   <tr> 
     <th>学期名称<font color=red>*</font></td>
			<td><form:input class="easyui-validatebox" id="term_name" path="termName" required="true"  validType="length[0,25]" invalidMessage="不能超过25个字符！" />
			例:2010-2011学年第二学期</td>
   </tr><tr> 
		<th>年份<font color=red>*</font></th>
            <td><form:input id="yearcode" path="yearCode" required="true" readonly="true"/></br>2010-2011学年,学年代码是2010-2011</td>
   </tr>
   <tr><th>
		学期开始时间<font color=red>*</font></th>
			<td><input id="term_start" name="termStart" value="${termStart}" onclick="new Calendar().show(this);"  readonly="readonly"  required="true"/>
			<%--<c:choose>
			<c:when test='${newFlag}' >
			<form:input id="term_start" path="termStart" class="easyui-datebox" value="${date}" required="true"/>
			</c:when><c:otherwise>
             <input class="easyui-datebox" id="term_start"value="<fmt:formatDate value="${schoolTerm.termStart.time}" pattern="yyyy-MM-dd"/>" name="termStart" required="required"/>
             </c:otherwise>
             </c:choose>--%></td>
           </tr><tr>
           <th>学期结束时间<font color=red>*</font></th>
            <td><input id="term_end" name="termEnd" value="${termEnd}" onclick="new Calendar().show(this);" readonly="readonly"  required="true"/>
            <%--<c:choose>
			<c:when test='${newFlag}' >
			<form:input id="term_end" path="termEnd" class="easyui-datebox" value="${date}" required="true"/>
			</c:when><c:otherwise>
            <input class="easyui-datebox" id="term_end" name="termEnd" value="<fmt:formatDate value="${schoolTerm.termEnd.time}" pattern="yyyy-MM-dd"/>" required="required"/>
           </c:otherwise>
           </c:choose>--%></td>
           </tr>
  <tr>
            <th>学期代码<font color=red>*</font></th>
           <td> <form:input   id="termcode" path="termCode" required="true" readonly="true"/></br>2010-2011学年第二学期,学期代码是2</td>
  </tr>
 </table>
    <div class="moudle_footer">
        <div class="submit_link">
            <input type="submit" value="提交" ">
            <input type="button" value="返回" onclick="window.history.go(-1)">
        </div>
    </div>
   </form:form>
</div>
</div>
</div>
</div>
</div>