<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
<meta name="decorator" content="iframe"/>
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
		function showAddItem(){
			$("#addItem").show();
		}

function checkTerm(){
	var inputValue = document.getElementById('term_name');
 		 $.ajax({
 	           type:"POST",
 	           url:"${pageContext.request.contextPath}/system/checkTerm",
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
<div class="navigation">
<div id="navigation">
<ul>
<li><a href="">系统管理</a></li>
<li><a href="${pageContext.request.contextPath}/system/listTerm?currpage=1">学期管理</a></li>
<li class="end"><a href="${pageContext.request.contextPath}/system/newTerm">修改学期</a></li>
</ul>
</div>
</div>
<!-- 导航栏 -->
<div id="TabbedPanels1" class="TabbedPanels">
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
 <form:form id="myForm" action="${pageContext.request.contextPath}/system/saveeditor"  name="myForm" method="POST" modelAttribute="schoolTerm">
 <%--<input class="btn btn-edit"  type="submit" value="提交">--%>
					<table>
						<form:hidden path="id" value="${idKey}" />
						<tr>
							<th>学期名称<font color=red>*</font>
							</td>
							<td><form:input class="easyui-validatebox" id="term_name"
									path="termName" required="true" validType="length[0,25]"
									invalidMessage="不能超过25个字符！" /> 例:2010-2011学年第二学期</td>
						</tr>
						<tr>
							<th>年份<font color=red>*</font></th>
							<td><form:input id="yearcode" path="yearCode"
									required="true" readonly="true" /></br>2010-2011学年第二学期,学年代码是2010-2011</td>
						</tr>
						<tr>
							<th>学期开始时间<font color=red>*</font></th>
							<td><input id="term_start" name="termStart"
								value="${termStart}" onclick="new Calendar().show(this);"
								readonly="readonly" required="true" /> <%--<c:choose>
			<c:when test='${newFlag}' >
			<form:input id="term_start" path="termStart" class="easyui-datebox" value="${date}" required="true"/>
			</c:when><c:otherwise>
             <input class="easyui-datebox" id="term_start"value="<fmt:formatDate value="${schoolTerm.termStart.time}" pattern="yyyy-MM-dd"/>" name="termStart" required="required"/>
             </c:otherwise>
             </c:choose>--%></td>
						</tr>
						<tr>
							<th>学期结束时间<font color=red>*</font>
							</th>
							<td><input id="term_end" name="termEnd" value="${termEnd}"
								onclick="new Calendar().show(this);" readonly="readonly"
								required="true" /> <%--<c:choose>
			<c:when test='${newFlag}' >
			<form:input id="term_end" path="termEnd" class="easyui-datebox" value="${date}" required="true"/>
			</c:when><c:otherwise>
            <input class="easyui-datebox" id="term_end" name="termEnd" value="<fmt:formatDate value="${schoolTerm.termEnd.time}" pattern="yyyy-MM-dd"/>" required="required"/>
           </c:otherwise>
           </c:choose>--%>
			</td>
		</tr>
		<tr>
			<th>学期代码<font color=red>*</font>
			</th>
			<td><form:input id="termcode" path="termCode"
					required="true" readonly="true" /></br>2010-2011学年第二学期,学期代码是2</td>
		</tr>
		<c:set var="count" value="0" />
		<c:forEach items="${cDictionarys}" var="curr" varStatus="i">
		   <c:forEach items="${weeks}" var="currWeek" varStatus="j">
		  
  		 	<c:if test="${curr.cTeachingDateType eq currWeek[2] }">
  		 	 <input type="hidden" id="type" name="type" value="${currWeek[2]}"/>
  		 	<c:set var="count" value="${count+1 }"/>
  		 		<c:set var="flag" value="${1}"/><%--
  		 		--%><tr><th>
		${curr.CName }开始时间</th>
			<td><input id="startDate${count }" name="startDate${count }"  value="${currWeek[0] }" onclick="new Calendar().show(this);"  readonly="readonly" /></td>
           </tr><tr>
           <th>${curr.CName }结束时间</th>
            <td><input id="endDate${count }" name="endDate${count }"   value="${currWeek[1] }" onclick="new Calendar().show(this);" readonly="readonly" /></td>
           </tr>
  		 	</c:if>
          </c:forEach>
          </c:forEach>
		  <%--<c:forEach items="${ }" var="curr" varStatus="i">
			<th>活动名称<font color=red>*</font>
			</th>
			<td><form:input id="termcode" path="termCode"
					required="true" readonly="true" />
			</td>
		<tr>
		<th>活动开始时间<font color=red>*</font></th>
		<td><input id="term_start" name="termStart"
			value="${termStart}" onclick="new Calendar().show(this);"
			readonly="readonly" required="true" />
		</tr>
		<tr>
		<th>活动结束时间<font color=red>*</font></th>
		<td><input id="term_start" name="termStart"
			value="${termStart}" onclick="new Calendar().show(this);"
			readonly="readonly" required="true" />
		</tr>
  		 </c:forEach>--%>
		<tr id="addItem" style="display:none">
	<c:set var="count" value="0" />
	<c:forEach items="${cDictionarys}" var="curr" varStatus="i">
  	<c:if test="${curr.CName ne '节日' }">
  	<c:set var="count" value="${count+1 }"/>
  		<input type="hidden" id="type" name="type" value="${curr.cTeachingDateType}"/>
  			<c:set var="flag" value="${0 }"/>
  		 <c:forEach items="${weeks}" var="currWeek" varStatus="j">
  		 	<c:if test="${curr.cTeachingDateType eq currWeek[2] }">
  		 		<c:set var="flag" value="${1}"/>
  		 		<tr><th>
		${curr.CName }开始时间</th>
			<td><input id="startDate${count }" name="startDate${count }"  value="${currWeek[0] }" onclick="new Calendar().show(this);"  readonly="readonly" /></td>
			<td><input id="startDate${count }" name="startDate${count }"  value="${currWeek[0] }" onclick="new Calendar().show(this);"  readonly="readonly" /></td>
           </tr><tr>
           <th>${curr.CName }结束时间</th>
            <td><input id="endDate${count }" name="endDate${count }"   value="${currWeek[1] }" onclick="new Calendar().show(this);" readonly="readonly" /></td>
           </tr>
  		 	</c:if>
          </c:forEach>
          <c:if test="${flag eq 0 }">
          	<tr><th>
		${curr.CName }开始时间</th>
			<td><input id="startDate${count }" name="startDate${count }"  onclick="new Calendar().show(this);"  readonly="readonly" /></td>
           </tr><tr>
           <th>${curr.CName }结束时间</th>
            <td><input id="endDate${count }" name="endDate${count }"   onclick="new Calendar().show(this);" readonly="readonly" /></td>
           </tr>
          </c:if>
  	</c:if>
  </c:forEach>

  </tr>
<tr>
	<th>备注</th>
	<td><form:input id="mem" path="mem" />
	</td>
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