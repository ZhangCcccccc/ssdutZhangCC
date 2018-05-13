<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
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
//拼凑HTML，添加特殊日期
var count =0;
function add() { 	
	count=count+1;
    var trObj = document.createElement("tr");  
    trObj.id="1236";  
    trObj.innerHTML = "<td><select id='type' name='type'>"+
	"<option value='723'>节日</option>"+
	"<option value='724'>运动会</option>"+
	"<option value='725'>期中考试</option>"+
	"<option value='726'>期末考试</option>"+
	"<option value='727'>四六级考试</option>"+
	"</select></td><td>开始时间:<input id='startDate' name='startDate"+count+"'"+
	"onclick='new Calendar().show(this);' readonly='readonly'/>"+
	"活动结束:<input id='endDate' name='endDate"+count +"'"+
	"onclick='new Calendar().show(this);' readonly='readonly' /></td>"; 
	document.getElementById("two_file").appendChild(trObj); 
}  
//删除特殊日期
function del() { 
	if(count>=1){
    $("table").find("tr:last").remove();
    count=count-1;
	}
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
	
function checkTerm0(){
	var inputValue = document.getElementById('term_name');
	 $.ajax({
           type:"POST",
           url:"${pageContext.request.contextPath}/system/checkTerm",
           data:{termName:inputValue.value},
           success:function(data){
        	  if(data=="ok"){        	  
        	 alert(inputValue.value +"已存在!");
        	 $("#term_name").val("");
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
	<div id="navigation">
		<ul>
			<li><a href="">系统管理</a>
			</li>
			<li><a
				href="${pageContext.request.contextPath}/system/listTerm?currpage=1">学期管理</a>
			</li>
			<li class="end"><a
				href="${pageContext.request.contextPath}/system/newTerm">新增学期</a>
			</li>
		</ul>
	</div>
</div>
<!-- 导航栏 -->
<div id="TabbedPanels1" class="TabbedPanels">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">
					新增学期
					<%--<input class="btn btn-edit" type="submit" value="提交" onclick="checkTerm0();"/>
            	<a class="btn btn-edit" type="submit" value="返回" onclick="window.history.go(-1)">返回</a>
			--%>
					<%--<a class="btn btn-edit" type="submit" value="返回" onclick="window.history.go(-1)">返回</a>
            <a class="btn btn-edit" type="button" value="提交" onclick="subform('${pageContext.request.contextPath}/saveNewTerm');" >保存</a>
            <input type="button"  onclick="subform('${pageContext.request.contextPath}/saveNewTerm');" value="查询" />
    --%>
				</div>
				<form:form id="myForm"
					action="${pageContext.request.contextPath}/system/saveNewTerm"
					name="myForm" method="POST" modelAttribute="schoolTerm">
					<%--<input class="btn btn-edit"  type="submit" value="提交">--%>
					<table id="two_file">
						<form:hidden path="id" />
						<tr>
							<th>学期名称<font color=red>*</font>
							</td>
							<td><form:input class="easyui-validatebox" id="term_name"
									path="termName" required="true" onblur="checkTerm0();"
									validType="length[0,25]" invalidMessage="不能超过25个字符！" />
								例:2010-2011学年第二学期</td>
						</tr>
						<tr>
							<th>年份<font color=red>*</font>
							</th>
							<td><form:input id="yearcode" path="yearCode"
									required="true" readonly="true" /></br>2010-2011学年第二学期,学年代码是2010-2011</td>
						</tr>
						<tr >
							<th>学期开始时间<font color=red>*</font>
							</th>
							<td><input id="term_start" name="termStart" value="${date}"
								onclick="new Calendar().show(this);" readonly="readonly"
								required="true" /> <%--<c:choose>
			<c:when test='${newFlag}' >
			<form:input id="term_start" path="termStart" class="easyui-datebox" value="${date}" required="true"/>
			</c:when><c:otherwise>
             <input class="easyui-datebox" id="term_start"value="<fmt:formatDate value="${schoolTerm.termStart.time}" pattern="yyyy-MM-dd"/>" name="termStart" required="required"/>
             </c:otherwise>
             </c:choose>--%>
							</td>
						</tr>
						<tr>
							<th>学期结束时间<font color=red>*</font>
							</th>
							<td ><input id="term_end" name="termEnd" value="${date}" onclick="new Calendar().show(this);" readonly="readonly"required="true"/> 
			<%--<c:choose>
			<c:when test='${newFlag}' >
			<form:input id="term_end" path="termEnd" class="easyui-datebox" value="${date}" required="true"/>
			</c:when><c:otherwise>
            <input class="easyui-datebox" id="term_end" name="termEnd" value="<fmt:formatDate value="${schoolTerm.termEnd.time}" pattern="yyyy-MM-dd"/>" required="required"/>
           </c:otherwise>
           </c:choose>--%>
							</td>
						</tr>
						<tr id=clones>
							<th>学期代码<font color=red>*</font>
							</th>
							<td><form:input id="termcode" path="termCode"
									required="true" readonly="true" /></br>2010-2011学年第二学期,学期代码是2</td>
						</tr>
						
						<%--
						<tr>
						
						<th><input type="button" value="添加" id="btn" /></th>
						<td>点击添加特殊活动</td>
						</tr>
                --%>
                <tr id="1st"> 
                <c:set var="count" value="0"/>
  				<c:set var="count" value="${count+1}"/>
                	<th>
                	添加特殊活动
                	</th> 
                    <td><input type="button" value="添加" onclick="add()">   
                    <input type='button' value='删除' onclick="del()"></td>  
                </tr>
                <c:forEach items="${cDictionarys }" var="curr" varStatus="i">
                <c:set var="count" value="0"/>
  				<c:set var="count" value="${count+1}"/>
				</c:forEach>
						<%--<c:forEach items="${cDictionarys }" var="curr" varStatus="i">
<c:set var="count" value="0"/>
  	<c:if test="${curr.CName ne '节日' }">
  	<c:set var="count" value="${count+1 }"/>
  		<input type="hidden" id="type" name="type" value="${curr.cTeachingDateType}"/>
  		<tr><th>
		${curr.CName }开始时间</th>
			<td><input id="startDate${count }" name="startDate${count }"  onclick="new Calendar().show(this);"  readonly="readonly" /></td>
           </tr><tr>
           <th>${curr.CName }结束时间</th>
            <td><input id="endDate${count }" name="endDate${count }"   onclick="new Calendar().show(this);" readonly="readonly" /></td>
           </tr>
  	</c:if>
  </c:forEach>--%>
						<%--<c:forEach items="${ }" var="curr" varStatus="i">
			<th>活动名称<font color=red>*</font>
			</th>
			<td><form:input id="termcode" path="termCode"
					required="true" />
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
						<tr>
							<th>备注</th>
							<td><form:input id="mem" path="mem" />
							</td>
						</tr>
					</table>				
					<div class="moudle_footer">
						<div class="submit_link">
							<input type="submit" value="提交"> <input type="button"
								value="返回" onclick="window.history.go(-1)">
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>