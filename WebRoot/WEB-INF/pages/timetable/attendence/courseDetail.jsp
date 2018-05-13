<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.ccoursetype-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<script type="text/javascript">
//打开快速考勤的弹出框
var courseId=${courseId};
var courseArrangeId=${courseArrangeId};
var id=${id};
function newAttendance()
{   
    //获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#rapid').window({left:"100px", top:topPos+"px"});
	$('#rapid').window('open');
	var name="";
	$('#tt-user').datagrid({
		iconCls:'icon-add',
		url: encodeURI('${pageContext.request.contextPath}/searchStudent?name='+name+'&courseId='+'${courseId}'),
		width: 670,
		height: 'auto',
		fitColumns: true,
		rownumbers: true,
		//singleSelect: true,
		frozenColumns:[[
		                {field:'ck',checkbox:true}
		]],
		columns:[[
					{field: 'usernumber', title: '卡号', width: 150},
					{field: 'cname', title: '学生', width: 150,}
		          ]],
		 rownumbers:true,
		 toolbar:[{
			 	id:'btnadd',
				text:'考勤',
				iconCls:'icon-add',
				handler:function(){
					var cards=[];
					var rows=$('#tt-user').datagrid('getSelections');
					if(rows.length<1)
						{
							alert('请至少勾选一项!');
							return false;
						}
					for(var i=0;i<rows.length;i++)
						{
							cards.push(rows[i].usernumber);
						}
					//alert(cards.join(','));
					$.ajax({
						type: "POST",
						url: "${pageContext.request.contextPath}/saveUser",
						data: {userName:cards.join(','),courseId:courseId},
						success:function(data)
						{
							if(data=='ok')
								{
									alert("ok");
									window.location.href="${pageContext.request.contextPath}/courseDetail?courseId="+courseId+"&courseArrangeId="+courseArrangeId+"&id="+id;
									//window.location.href="${pageContext.request.contextPath}/courseDetail?courseId='+${courseId}+'$courseArrangeId='+'${courseArrangeId}";
								}
						}
					});
				}
		 }]
	});
}
	function update(num)
	{
		var id=num;
		$('#p').panel('open');
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/findUser",
			data: {userId:id},
			success:function(data)
			{
				alert('${user.id}');
			}
		});
	}
	
	//保存考勤的信息
	/*function addUserSubmit(id,courseId)
	{
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/saveUser",
			data: {userId:id,courseId:courseId},
			success:function(data)
			{
				if(data=='ok')
					{
						alert("ok");
						'${pageContext.request.contextPath}/searchUser?name='+name+'&iskey='+'${iskey}'
							"${pageContext.request.contextPath}/courseDetail?courseId='+courseId+'$courseArrangeId='+'${courseArrangeId}"	
						window.location.href="${pageContext.request.contextPath}/courseSelect?idkey="+courseId;
					}
			}
		});
	}*/
</script>
</head>

<body>
<div style="float:left;width: 100%">
    <div class="sit_module width_3_quarter"style="float:left;width: 30%">
        <div class="sit_title">
            <h3 style="width: 40%"><fmt:message key="course.info"/></h3>
            <%--<ul class="tabs1">
                <li><a onclick="window.history.go(-1)">返回课程列表</a></li>
            </ul>
        --%></div>
   <div id="editLabInfo" class="module_content">
    <table border="0" style="width:100%;" cellpadding="10"  >
                        <tr>  
                            <th align="left"style="width:10%;"><fmt:message key="course.number"/></th>
                            <td style="width:23%;">${courseArrange.courseDetail.courseNumber}</td>
                         </tr>
                         <tr>
                             <th align="left"style="width:10%;"><fmt:message key="course"/></th>
                            <td style="width:23%;">${course.courseName}</td>
                        </tr>
                        <tr>
                        <th align="left"style="width:10%;"><fmt:message key="teacher"/></th>
                            <td style="width:23%;">${course.userByTeacher.cname}</td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;"><fmt:message key="class"/></th>
                            <td style="width:23%;">第${courseArrange.arrangeClass}节</td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;"><fmt:message key="date"/></th>
                            <td style="width:23%;">
                            <fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${courseArrange.createdDate.time}"/>
                            </td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;"><fmt:message key="begin.time"/></th>
                            <td style="width:23%;">
                            <%--<c:forEach items="${courseArrange..schoolClassesForUpdatedBy}" var="current">
                            	<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" type="both" value="${current.startDate.time}"/>
                            </c:forEach>--%>
                            ${startTime}
                            </td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;"><fmt:message key="end.time"/></th>
                            <td style="width:23%;">
                            <%--<c:forEach items="${courseArrange.courseDetail.startClass}" var="current">
                            	<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" type="both" value="${current.endDate.time}"/>
                            </c:forEach>--%>
                            ${endTime}
                            </td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;"><fmt:message key="lab.name"/></th>
                            <td style="width:23%;">
                            <c:forEach items="${courseDetail.courseAppointments}" var="current">
                            	${current.labAnnex.labName}
                            </c:forEach>
							</td>
                        </tr>
                        <%--<tr>
                            <th align="left"style="width:10%;">实验室</th>
                            <td style="width:23%;"></td>
                        </tr>
                        --%><tr>
                            <th align="left"style="width:10%;"><fmt:message key="team"/></th>
                            <td style="width:23%;">${courseArrange.courseDetail.schoolTerm.termName}</td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;"><fmt:message key="join.number"/></th>
                            <td style="width:23%;"></td>
                        </tr>
                        <tr>
                            <th align="left"style="width:10%;"><fmt:message key="total.number"/></th>
                            <td style="width:23%;">${number}</td>
                        </tr>
                    </table>
    </div>
    </div>
    <div style="float:right;width: 65%">
    <!--实验室软件主界面开始-->
    <div class="sit_module width_quarter"style="float:right;width: 100%">
        <div class="sit_title">
            <h3 class="tabs_involved"><fmt:message key="student.attendance.list"/></h3>
            <ul class="new_bulid" style="float:rigth;width:60px">
                <li><a href="javascript:void(0)" onclick="newAttendance();"><fmt:message key="fast.attendance"/></a></li>
            </ul>
           <ul class="new_bulid" style="float:rigth;width:100px">
            	<li><a href="javascript:void(0)" onclick="window.history.go(-1)"><fmt:message key="back"/></a></li>
            </ul>
        </div> 
        <!-- 实验室软件信息开始 -->
        <div class="tab_container">
            <div id="labSoftwareInfo" class="module_content">
            <table class="tablesorter" cellspacing="0">
             <thead>
                   <tr>
                         <th><fmt:message key="student"/></th>
                         <th><fmt:message key="test"/></th>
                         <th><fmt:message key="attendance.time"/></th>
                         <th><fmt:message key="true.attendance"/></th>
                         <th><fmt:message key="memo"/></th>
                         <th><fmt:message key="operation"/></th>
                   </tr>
                    </thead>
                    <tbody>
                <c:forEach items="${students}" var="current"  varStatus="i">
                    <tr>
                       <td>${current.userByStudentNumber.cname}&nbsp;(${current.userByStudentNumber.username})</td>
                       <td> <c:forEach items="${current.userByStudentNumber.timeAttendancesForUserNumber}" var="curr">
                       		<c:choose>
                       			<c:when test="${curr.attendanceMachine==1}">
                       			已考勤
                       			</c:when>
                       			<c:otherwise>
                       			未考勤
                       			</c:otherwise>
                       		</c:choose>
                       </c:forEach></td>
                       <td> 
                        <c:forEach items="${current.userByStudentNumber.timeAttendancesForUserNumber}" var="curr" varStatus="i">
                       		<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" type="both" value="${curr.attendDate.time}"/>
                       </c:forEach> </td>
                       <td><c:forEach items="${current.userByStudentNumber.timeAttendancesForUserNumber}" var="curr" varStatus="i">
                       		<c:choose>
                       			<c:when test="${curr.actualAttendance==1}">
                       			已考勤
                       			</c:when>
                       			<c:when test="${curr.actualAttendance==0}">
                       			未考勤
                       			</c:when>
                       			<c:otherwise>
                       				${curr.attendanceMachine}
                       			</c:otherwise>
                       		</c:choose>
                       </c:forEach></td>
                       <td><c:forEach items="${current.userByStudentNumber.timeAttendancesForUserNumber}" var="curr" varStatus="i">
                       		${curr.memo}
                       </c:forEach></td>
                       <td>
                       <c:forEach items="${current.userByStudentNumber.timeAttendancesForUserNumber}" var="curr" varStatus="i">
                       <a href="${pageContext.request.contextPath}/findUser?userId=${current.userByStudentNumber.id}&idkeyTime=${curr.id}&courseArrangeId=${courseArrangeId}&courseId=${curr.courseArrange.courseDetail.id}&id=${id}">修改</a>
                       </c:forEach>
                       </td>
                    </tr>
                    </c:forEach>
                  </tbody>
                 </table>
                 <c:choose><c:when test='${newFlag}'>
        <div class="pagination">
   <a href="${pageContext.request.contextPath}/courseDetail?currpage=${pageModel.firstPage}&courseArrangeId=${courseArrangeId}&courseId=${courseId}&id=${id}&" target="_self"><fmt:message key="firstpage.title"/></a>
	<a href="${pageContext.request.contextPath}/courseDetail?currpage=${pageModel.previousPage}&courseArrangeId=${courseArrangeId}&courseId=${courseId}&id=${id}&" target="_self"><fmt:message key="previouspage.title"/></a>	
	 <%--<c:forEach var="currentpage" begin="1" end="${pageModel.totalPage}"><a href="${pageContext.request.contextPath}/courseDetail?currpage=<c:out value="${currentpage}"/>" target="_self"><c:out value="${currentpage}"/></a></c:forEach>
	--%><select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;"><option value="${pageContext.request.contextPath}/courseDetail?currpage=${page}&courseArrangeId=${courseArrangeId}&courseId=${courseId}&id=${id}&">${page}</option><c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}"><option value="${pageContext.request.contextPath}/courseDetail?currpage=${j.index}&courseArrangeId=${courseArrangeId}&courseId=${courseId}&id=${id}&">${j.index}</option></c:if></c:forEach></select>页
	<a href="${pageContext.request.contextPath}/courseDetail?currpage=${pageModel.nextPage}&courseArrangeId=${courseArrangeId}&courseId=${courseId}&id=${id}&" target="_self"><fmt:message key="nextpage.title"/></a>
	<a href="${pageContext.request.contextPath}/courseDetail?currpage=${pageModel.lastPage}&courseArrangeId=${courseArrangeId}&courseId=${courseId}&id=${id}&" target="_self"><fmt:message key="lastpage.title"/></a>
    </div>
    <div class="pagination_desc" style="float: left">
       <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
    <strong><fmt:message key="totalpage.title"/>:${pageModel.totalPage}&nbsp;</strong>
</div>
</c:when><c:otherwise>
<div class="pagination_desc" style="float: left">
       <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
</div>
</c:otherwise></c:choose> 
            </div>
            </div>
            <!-- 快速考勤 -->
  <div id="rapid" class="easyui-window" title="<fmt:message key='student'/>" closed="true" iconCls="icon-add" style="width:710px;height:400px">
    <table width="100%">
    </table>
    <table id="tt-user" align="center"></table>
</div>
<!-- 快速考勤结束 -->
<!-- 修改 -->
<%--<div id="p" class="easyui-panel" title="考勤修改" closed="true" style="width:300px;height:200px" align="right">
<form:form id="" action="${pageContext.request.contextPath}/" method="post" modelAttribute="">
    <table width="100%">
        <tr>
               <td width="14%">学生:</td>
               <td width="16%"><input id="user_name" type="text" name="cname" value="${user.cname}" style="width: 100px">
        </tr>
        <tr>
               <td width="14%">学号:</td>
               <td width="16%"><input id="user_name" type="text" name="username" value="${user.username}" style="width: 100px">
        </tr>
        <tr>
               <td width="14%">考勤:</td>
               <td width="16%"><input  type="radio" name="1">是<input  type="radio" name="1">否
        </tr>
        <tr>
               <td width="14%">备注:</td>
               <td width="16%"><select name="memo" value="${memo}" style="width: 100px">
        </tr>
        <tr>
        	<td><input type="submit" value="提交"></td>
        	<td><input type="button" value="取消"></td>
        </tr>
    </table>
</form:form>
    <table id="tt-user" align="center"></table>
</div>
            --%></div>
</div>
    
    </div>
</body>
</html>

