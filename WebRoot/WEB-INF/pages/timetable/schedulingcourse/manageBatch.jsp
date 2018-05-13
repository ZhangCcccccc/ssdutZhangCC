<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script  type="text/javascript">
//更新分组名单
function updateTimetableStudentsGroup(groupId){
    $.ajax({
        url:"${pageContext.request.contextPath}/timetable/updateTimetableStudentsGroup?groupId=" + groupId ,
        type:"post",
        success:function(data){
            if(data=="success"){
                alert("学生名单更新完成！")
                window.location.reload();
            }else{
                alert("未能更新！")
            }
        }
    });
}
var batchId=0;
function moveId(id){
	batchId = id;
}

//更新选课时间
function info(){
	var begintime=$("#begintime").val();
	var endtime=$("#endtime").val();
	if(begintime==null||begintime==''){
		alert("请选择开始时间");
	}else if(endtime==null||endtime==''){
		alert("请选择结束时间");
	}else{
		$.ajax({
		    url:"${pageContext.request.contextPath}/timetable/altBatchTime?batchId="+batchId,
		    type:"POST",
		    data: {'begintime':begintime,'endtime':endtime},
		       success:function(data){//AJAX查询成功
		            if(data == 'success'){
		                $(".edit-edit").slideUp();
		                $(".btn-edit").slideDown();//修改信息显示
		                window.location.reload();//刷新页面
		            }else{
		                alert("未能修改成功，请稍后重试");
		            }
		       }
		});
	}
}

//分组名单
function listTimetableStudentsGroup(id){
	var sessionId=$("#sessionId").val();
	var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/openSearchStudentGroup?id='+id+'" style="width:100%;height:100%;"></iframe>'
	$('#doSearchStudents').html(con);  
	//获取当前屏幕的绝对位置
	var topPos = window.parent.pageYOffset;
	//使得弹出框在屏幕顶端可见
	$('#doSearchStudents').window({left:"0px", top:topPos+"px"});
	$('#doSearchStudents').window('open');
}

// 保存分组人数
function saveNumbers(id){
	var groupId = id;
	var number = $("#number").val();
	$.ajax({
	    url:"${pageContext.request.contextPath}/timetable/saveNumbers?groupId="+groupId+"&number="+number,
	    type:"POST",
       success:function(data){//AJAX查询成功
            if(data == 'success'){
                alert("修改成功");
            }else{
            }
       }
	});
}

</script>

<!-- 下拉的样式结束 -->	
</head>
<body>
<div class="iStyle_RightInner">

<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">排课管理</a></li>
	<li class="end"><a href="${pageContext.request.contextPath}/timetable/listTimetableTerm?id=-1">查看分组情况</a></li>
</ul>
</div>
</div>

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<div class="title">学生选课列表</div>
<table> 
<thead>
<tr>
   <th>所属批次</th>
   <th>组名</th>
   <th>选课形式</th>
   <th>选课时间</th>
   <th>修改时间</th>
   <th>每组人数</th>
   <th>已选人数</th>
   <th>学生名单操作</th>
   
</tr>
</thead>
<tbody>
<!-- 判断相同批次的当前批次id -->
<c:set var="ifRowspanBatch" value="0"></c:set>

<c:forEach items="${groups}" var="current"  varStatus="i">	
<!--合并相同批次的变量  -->
<c:set var="rowspanBatch" value="0"></c:set>  
   
<!--合并相同批次的变量计数  -->
     <c:forEach items="${groups}" var="current1"  varStatus="j">
         <c:if test="${current1.timetableBatch.id==current.timetableBatch.id }">
            <c:set value="${rowspanBatch + 1}" var="rowspanBatch" />
         </c:if>
     </c:forEach>
<tr>
	<c:if test="${ifRowspanBatch!=current.timetableBatch.id }">
     <td rowspan="${rowspanBatch }">${current.timetableBatch.batchName}</td>
	</c:if>
     <td>${current.groupName}</td>
     <td>
     	<c:if test="${current.timetableBatch.ifselect eq 0}">自动选课</c:if>
     	<c:if test="${current.timetableBatch.ifselect eq 1}">学生选课</c:if>
     </td>
     <td>从<fmt:formatDate value="${current.timetableBatch.startDate.time }" pattern="yyyy-MM-dd" />到
     <fmt:formatDate value="${current.timetableBatch.endDate.time }" pattern="yyyy-MM-dd" />
     </td>
     <c:if test="${ifRowspanBatch!=current.timetableBatch.id }">
   		<td rowspan="${rowspanBatch }">
	    	<a class="changeTime" onclick="moveId(${current.timetableBatch.id })"><font color="blue">修改时间</font></a>
	    </td>
     </c:if>
     <td>
     	<input id="number" type="text"  value="${current.numbers}" onchange="saveNumbers(${current.id})" />
     </td>
     <td><a  href='#' onclick='listTimetableStudentsGroup(${current.id})'>${current.timetableGroupStudentses.size()}</a></td>
     <c:if test="${ifRowspanBatch!=current.timetableBatch.id }">
	     <td rowspan="${rowspanBatch }">
		     <c:if test="${current.timetableBatch.ifselect eq 0}">
			     <a  href='#' onclick='updateTimetableStudentsGroup(${current.id})'>更新</a>
		     </c:if>
		     <c:if test="${current.timetableBatch.ifselect eq 1}">暂无操作</c:if>
	     </td>
     </c:if>
     <c:set var="ifRowspanBatch" value="${current.timetableBatch.id }"></c:set>
</tr>
</c:forEach> 
</tbody>
</table>
</div>
<form:form name="auditform" action="" method="post">
    <div class="edit-edit">
        <table>
            <tr>
                <td>选课开始日期</td>
                <td> <input id="begintime" class="Wdate" type="text" name="begintime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:200px;" value="<fmt:formatDate value="${reservation.begintime.time}" pattern="yyyy-MM-dd HH:mm:ss" />" readonly /> </td>
            </tr>
            <tr>    
                <td>选课结束日期</td>
                <td> <input id="endtime" class="Wdate" type="text" name="endtime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:200px;" value="<fmt:formatDate value="${reservation.endtime.time}" pattern="yyyy-MM-dd HH:mm:ss" />" readonly /> </td>
            </tr>
        </table>
		<input class="btn" id="save" type="button" onclick="info()" value="保存" />
		<input class="btn btn-return" id="save" type="button" value="返回" /> 
    </div>
</form:form>
</div>
</div>
</div>
</div>
</div>

<!-- 查看学生名单 -->
<div id="doSearchStudents" class="easyui-window" title="查看学生名单" closed="true"  modal="true" iconCls="icon-add" style="width:800px;height:500px">
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
    
</script>
<!-- 下拉框的js -->


<script type="text/javascript">
					$(".changeTime").click(function(){
						//$(".btn-edit").slideUp(); //原信息隐藏
						$(this).hide();//修改按钮隐藏
						$(".edit-edit").slideDown();//修改信息显示
					});
					$(".btn-return").click(function(){
						$(".edit-edit").slideUp();
						$(".changeTime").slideDown();//修改信息显示
						//window.location.reload();//刷新页面
					})
</script>
</body>
</html>