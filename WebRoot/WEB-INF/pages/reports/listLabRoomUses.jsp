<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipment-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式 -->	
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link href="${pageContext.request.contextPath}/css/iconFont.css" rel="stylesheet">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<!-- 打印开始 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<!-- 打印结束 -->

<!-- 打印、导出组件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/LodopFuncs.js"></script>
<script type="text/javascript">
	// 跳转
	function targetUrl(url)
	{
	  document.queryForm.action=url;
	  document.queryForm.submit();
	}
	// 取消
	function cancelQuery(){
		window.location.href="${pageContext.request.contextPath}/report/listLabRoomUsage?page=1";
	}	

/*
*查看学生名单
*/
function listTimetableStudents(id, type){
var sessionId=$("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/report/openSearchStudent?id=' + id +'&type='+type+ '" style="width:100%;height:100%;"></iframe>'
$('#doSearchStudents').html(con);  
$('#doSearchStudents').window({left:"0px", top:"0px"});
$('#doSearchStudents').window('open');
}
</script>

</head>

<body>
<div class="navigation">
				<div id="navigation">
					<ul>
						<li><a href="javascript:void(0)">系统报表</a></li>
						<li class="end"><a href="javascript:void(0)">实验室日志报表</a></li>
					</ul>
				</div>
			</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
	<div class="TabbedPanelsTabGroup-box">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
		     <div class="tool-box">
		     <form:form name="queryForm" action="${pageContext.request.contextPath}/report/listLabRoomUsage?page=1" method="post" >
				<table class="list_form">
					<tr>	
 						<td>
							学期：
							<select name="termId" class="chzn-select">
							<option value="">请选择</option>
							<c:forEach var="curr" items="${terms}"> 
								<c:if test="${curr.id == termId }">
									<option value="${curr.id}" selected>${curr.termName }</option>
								</c:if>
								<c:if test="${curr.id != termId }">
									<option value="${curr.id}">${curr.termName }</option>
								</c:if>
							</c:forEach>
							</select>
							
						</td>
						<td>
							实验中心名称：
							<select name="centerId" class="chzn-select">
							<option value="">请选择</option>
							<c:forEach var="curr" items="${centers}"> 
							<c:if test="${curr.id == centerId }">
								<option value="${curr.id}" selected>${curr.centerName}</option>
							</c:if>
							<c:if test="${curr.id != centerId }">
								<option value="${curr.id}">${curr.centerName}</option>
							</c:if>
							</c:forEach>
							</select>
							
						</td>
						<td>
							实验室名称：
							<select name="labId" class="chzn-select">
							<option value="">请选择</option>
							<c:forEach var="curr" items="${rooms}"> 
							<c:if test="${curr.id == labId }">
								<option value="${curr.id}" selected>${curr.labRoomName }</option>
							</c:if>
							<c:if test="${curr.id != labId }">
								<option value="${curr.id}">${curr.labRoomName }</option>
							</c:if>
							</c:forEach>
							</select>
							
						</td>
						<td>
							使用类型：
							<select name="useType" class="chzn-select">
							<option value="">请选择</option>
							<c:if test="${useType == 1}">
								<option value="1" selected>排课</option>
								<option value="2">预约</option>
							</c:if>
							<c:if test="${useType == 2}">
								<option value="1">排课</option>
								<option value="2"  selected>预约</option>
							</c:if>
							<c:if test="${useType != 2 && useType != 1}">
								<option value="1">排课</option>
								<option value="2">预约</option>
							</c:if>
							</select>
							
						</td>
						<td>
							<input type="button" value="取消" onclick="cancelQuery();">
							<input  type="submit" value="查询">
						</td>
					</tr >
				</table>
			</form:form>
		       
		    </div>
    	<div class="content-box">
    		<div class="title">
    			<div id="title">实验室日志列表</div>
    			<a class="btn btn-new" onclick="exportLabRoomUses()">导出</a>
    		</div>
            <table class="tb" id="my_show"> 
                <thead>
                    <tr>
                    	<th style="width:3%">序号</th>
                        <th style="width:8%">实验室名称</th>
                        <th style="width:8%">实验室地点</th>
                        <th style="width:5%">时间</th>
                        <th style="width:10%">使用类型</th>
                        <th style="width:5%">使用详情</th>
                        <th style="width:5%">任课或值班教师</th>
                        <th style="width:10%">使用情况</th>
                        <th style="width:10%">学生名单</th>
                    </tr>
                </thead>
                
                <tbody>
                		<c:forEach items="${listLabRoomUses}" var="curr" varStatus="i">
                		<tr>
                        <td>${i.count+(page-1)*pageSize}</td>
                        <td>${curr[1]}</td>
                        <td>${curr[2]}</td>
                        <td>${curr[19]} ${curr[21] }-${curr[22] }</td>
                        <td>
                        	<c:if test="${curr[10] eq 1}">
                        		计划内
                        	</c:if>
                        	<c:if test="${curr[10] eq 2}">
                        		计划外
                        	</c:if>
						</td>
                        <td>
                        	<c:if test="${curr[10] eq 1}">
                        		<c:if test="${curr[25] ne 23}">
                        		${curr[11]}${curr[12]}<br>${curr[23]}-${curr[24]}
                        		</c:if>
                        		<c:if test="${curr[25] eq 23}">
                        		${curr[11]}${curr[12]}<br>${curr[23]}
                        		</c:if>
                        	</c:if>
                        	<c:if test="${curr[10] eq 2}">
                        		${curr[11]}${curr[12]}&nbsp;
                        	</c:if>
                        </td>
                        <td>${curr[13]}</td>
                        <td>
                        	<sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_LABCENTERMANAGER">
                        		${curr[28]}
                        	</sec:authorize>
                        	<sec:authorize ifNotGranted="ROLE_SUPERADMIN,ROLE_LABCENTERMANAGER">
                        		<c:if test="${curr[10] eq 1}">
	                        		<textarea placeholder="请输入内容" id="useCondition${i.count}" onchange="setUseCondition(1,${curr[26]},${i.count})">${curr[28]}</textarea>
	                        	</c:if>
	                        	<c:if test="${curr[10] eq 2}">
	                        		<textarea placeholder="请输入内容" id="useCondition${i.count}" onchange="setUseCondition(2,${curr[27]},${i.count})">${curr[28]}</textarea>
	                        	</c:if>
                        	</sec:authorize>
                        	
                        </td>
                        <td>
                        	<c:if test="${curr[10] eq 1}">
                        		<a class="btn btn-common" href='javascript:void(0)' onclick='listTimetableStudents(${curr[15]},1)'>${curr[14]} </a>
                        	</c:if>
                        	<c:if test="${curr[10] eq 2}">
                        		<a class="btn btn-common" href='javascript:void(0)' onclick='listTimetableStudents(${curr[16]},2)'>${curr[14]} </a>
                        	</c:if>
                        </td>
                        
                        </tr>
                		</c:forEach>
                       
                </tbody>
            </table>
            
	<!-- 分页模块 -->
		<div class="page" >
	        ${totalRecords}条记录,共${pageModel.totalPage}页
	    <a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/report/listLabRoomUsage?page=1')" target="_self">首页</a>			    
		<a href="javascript:void(0)" onclick="targetUrl('${pageContext.request.contextPath}/report/listLabRoomUsage?page=${pageModel.previousPage}')" target="_self">上一页</a>
		第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
		<option selected="selected" value="${pageContext.request.contextPath}/report/listLabRoomUsage?page=${page}">${page}</option>
		<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
	    <c:if test="${j.index!=page}">
	    <option value="${pageContext.request.contextPath}/report/listLabRoomUsage?page=${j.index}">${j.index}</option>
	    </c:if>
	    </c:forEach></select>页
		<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/report/listLabRoomUsage?page=${pageModel.nextPage}')" target="_self">下一页</a>
	 	<a href="javascript:void(0)"  onclick="targetUrl('${pageContext.request.contextPath}/report/listLabRoomUsage?page=${pageModel.lastPage}')" target="_self">末页</a>
	    </div>
	<!-- 分页模块 -->
</div>
<!-- 查看学生名单 -->
<div id="doSearchStudents" class="easyui-window" title="查看学生名单" closed="true" iconCls="icon-add" style="width:1000px;height:500px">
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
    function exportLabRoomUses(){
    	document.queryForm.action = "${pageContext.request.contextPath}/report/exportLabRoomUses";
		document.queryForm.submit();
    }
    
    function setUseCondition(type, id, conditionId){
    	var useCondition = $("#useCondition"+conditionId).val();
		$.ajax({
			         url:"${pageContext.request.contextPath}/setUseCondition",
			         dataType:"json",
			         type:'GET',
			         data:{type:type,id:id, useCondition:useCondition},
			         complete:function(result)
			         {
			         	 
			          }
			});
    }
</script>
<!-- 下拉框的js -->

</body>
</html>


