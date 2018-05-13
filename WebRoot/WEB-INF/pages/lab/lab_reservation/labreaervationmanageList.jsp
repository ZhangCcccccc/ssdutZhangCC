<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.labappointment-resources"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
<meta name="decorator" content="iframe"/>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/labreservation/labreservationmanagerList.js"></script>
<!-- 打印插件的引用 -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
  <script type="text/javascript">
  
  	
	function zln(s){
     if(confirm( '你真的要删除吗？ ')==false){
       return   false;
    }else{
     window.location.href="${pageContext.request.contextPath}/labreservation/labreationdelectitem?idkey="+s;
    }

}
  
	$(document).ready(function(){
	$("#print").click(function(){
	$("#list_top").jqprint();
	});

	
var s=${tage};
 if(1==s){
 $("#s1").addClass("TabbedPanelsTab selected");
 $("#s0").addClass("TabbedPanelsTab");
  $("#s2").addClass("TabbedPanelsTab");
    $("#s3").addClass("TabbedPanelsTab");
    $("#s4").addClass("TabbedPanelsTab");
 }
  if(3==s){
 $("#s3").addClass("TabbedPanelsTab selected");
 $("#s0").addClass("TabbedPanelsTab");
 $("#s1").addClass("TabbedPanelsTab ");
  $("#s2").addClass("TabbedPanelsTab");
    $("#s5").addClass("TabbedPanelsTab");
    $("#s4").addClass("TabbedPanelsTab");
 }
  if(4==s){
 $("#s4").addClass("TabbedPanelsTab selected");
 $("#s0").addClass("TabbedPanelsTab");
 $("#s1").addClass("TabbedPanelsTab ");
  $("#s2").addClass("TabbedPanelsTab");
    $("#s3").addClass("TabbedPanelsTab");
   
 }
  if(2==s){
 $("#s2").addClass("TabbedPanelsTab selected");
 $("#s0").addClass("TabbedPanelsTab");
 $("#s1").addClass("TabbedPanelsTab ");
    $("#s3").addClass("TabbedPanelsTab");
    $("#s4").addClass("TabbedPanelsTab");
 }
   if(0==s){
 $("#s0").addClass("TabbedPanelsTab selected");
 $("#s2").addClass("TabbedPanelsTab");
 $("#s1").addClass("TabbedPanelsTab ");
    $("#s3").addClass("TabbedPanelsTab");
    $("#s4").addClass("TabbedPanelsTab");
 }

	});
	

	
	

//分页

//首页
	function first(url){
		document.form.action=url;
		document.form.submit();
	}
	//末页
	function last(url){
		document.form.action=url;
		document.form.submit();
	}
	//上一页
	function previous(url){
		var currpage=${currpage};
		
		if(currpage==1){
			currpage=1;
		}else{
			currpage=currpage-1;
		}
		//alert("上一页的路径："+url+currpage);
		document.form.action=url+currpage;
		document.form.submit();
	}
	//下一页
	function next(url){
		var totalPage=${pageModel.totalPage};
		var currpage=${currpage};
		if(currpage>=totalPage){
			currpage=totalPage;
		}else{
			currpage=currpage+1
		}
		//alert("下一页的路径："+page);
		document.form.action=url+currpage;
		document.form.submit();
	}
</script>

 <!-- 打印插件的引用 -->
 <link href="${pageContext.request.contextPath}/css/iconFont.css"
	rel="stylesheet">
</head>
<body>
	<div class="navigation">
		<div id="navigation">
			<ul>
				<li><a href="javascript:void(0)">实验室预约管理</a></li>
				<li class="end"><a href="javascript:void(0)">实验室预约统计</a></li>
			</ul>
		</div>
	</div>
<div id="TabbedPanels1" class="TabbedPanels">
<ul class="TabbedPanelsTabGroup">
				<li class="TabbedPanelsTab" id="s0"><a href="${pageContext.request.contextPath}/labreservation/Labreservationmanage?tage=0&currpage=1">全部</a>
				</li>
				<li class="TabbedPanelsTab" id="s2"><a href="${pageContext.request.contextPath}/labreservation/Labreservationmanage?tage=2&currpage=1">审核中</a></li>
				<li class="TabbedPanelsTab" id="s1"><a href="${pageContext.request.contextPath}/labreservation/Labreservationmanage?tage=1&currpage=1">审核通过</a></li>
				<li class="TabbedPanelsTab" id="s4"><a href="${pageContext.request.contextPath}/labreservation/Labreservationmanage?tage=4&currpage=1">审核拒绝</a></li>
				<li class="TabbedPanelsTab" id="s3"><a href="${pageContext.request.contextPath}/labreservation/Labreservationmanage?tage=3&currpage=1">未审核</a>
				</li>
			</ul>
	<div class="TabbedPanelsContentGroup">
	<div class="TabbedPanelsContent">
 <div class="tool-box">
                <form:form name="form" action="${pageContext.request.contextPath}/labreservation/Labreservationmanage?currpage=1&tage=${tage}" method="post" modelAttribute="labReservation">
				<ul style="position: absolute;">
					<li>学期：<form:select path="remarks">
			        <option value="">请选择</option>
			        <c:if test="${shoolTerm!=null}">
			        <option value="${shoolTerm.id}" selected="selected">${shoolTerm.termName}</option>
			        </c:if>
					<c:forEach items="${timemap}" var="s">
					<option value="${s.id}">${s.termName}</option>
					</c:forEach>
					</form:select> </li>
					<li>实验室：<form:input path="eventName"/> </li>
					<li><input type="button" onclick="expers('${pageContext.request.contextPath}/labreservation/Labreservationmanage?currpage=1&tage=${tage}')"  value="查询"></li>
					<li><input type="button" value="打印" id="print"></li>
					
				</ul>
				</form:form>
			</div>
<div class="content-box">
<table >
<thead>
   <th>序号</th>
   <th>活动名称</th>
   <th>周次</th>
   <th>星期</th>
   <th>节次</th>
   <th>预约实验室</th>
   <th style="width:25%">内容</th>
    <th >学生名单</th>
   <th >审批人</th>
   <th >审批状态</th>
   <sec:authorize ifAnyGranted="ROLE_SUPERADMIN,ROLE_LABMANAGER">
   <th colspan="2">操作</th>  
   </sec:authorize>
</tr>
</thead>

<tbody >
<c:forEach items="${labReservations}" var="s" varStatus="i">
<tr>
     <td >${i.count+(currpage-1)*pageSize}</td>
     <td >${s.name}</td>
     <td>第[<c:forEach items="${s.week }"  var="a" ><c:if test="${a!=null}">${a }&nbsp;</c:if></c:forEach>]周</td>
     <td>星期[<c:forEach items="${s.day}"  var="d" ><c:if test="${d!=null}">${d }&nbsp;</c:if></c:forEach>]</td> 
     <td> 第[<c:forEach items="${s.time}"  var="f" ><c:if test="${f!=null}">${f }&nbsp;</c:if></c:forEach>]节</td>
     <td>${s.lab}</td>
     <td><p>${s.start}</p></td>
     <td><a href="${pageContext.request.contextPath}/lab/findstudentforlabreation?idkey=${s.id}&currpage=1"title="查看学生名单">查看学生名单</a></td>
     <td>${s.auditUser }</td>
     <td><c:if test="${s.cont==1}">审核通过</c:if>
         <c:if test="${s.cont==2}">审核中</c:if>
         <c:if test="${s.cont==4}">审核拒绝</c:if>
         <c:if test="${s.cont==3}">未审核</c:if> </td>
    <c:if test="${fn:contains(s.labRoom.labRoomAdmins,user.username)||user.username==s.user.username}">
     <td>
     <!--审核通过  -->
     <c:if test="${s.cont==1}">
         <c:if test="${s.fabu==null}"> 
              <a href="${pageContext.request.contextPath}/lab/publishedcourses?idkey=${s.id}&tage=${tage}" title="发布"><i class="icon-busy"></i></a>
         </c:if>
         <a  href="${pageContext.request.contextPath}/lab/checkButton?idkey=${s.id}&tage=${tage}">查看</a>
     </c:if>
     <!--审核中  -->
     <c:if test="${s.cont==2}"><a href="${pageContext.request.contextPath}/lab/checkButton?idkey=${s.id}&tage=${tage}" > 查看</a></c:if>
     <!--未审核  -->
     <c:if test="${s.cont==3}">
         <c:if test="${user.username==s.user.username}">  <!-- 申请者-->
            <a  href="${pageContext.request.contextPath}/lab/checkButton?idkey=${s.id}&tage=${tage}">查看</a>
         </c:if>
         <c:if test="${user.username!=s.user.username}">   <!-- 实验室管理员 -->
            <a  href="${pageContext.request.contextPath}/lab/checkButton?idkey=${s.id}&tage=${tage}">审核</a>
         </c:if>
     </c:if>
     <!--审核拒绝  -->
     <c:if test="${s.cont==4}">
          <c:if test="${user.username==s.user.username}">
               <a  href="${pageContext.request.contextPath}/lab/checkButton?idkey=${s.id}&tage=${tage}">查看</a>
          </c:if>
          <c:if test="${user.username!=s.user.username}">
               <a  href="${pageContext.request.contextPath}/lab/checkButton?idkey=${s.id}&tage=${tage}">审核</a>
          </c:if>
     </c:if> 
     </td>
     <td><c:if test="${s.fabu!=1&&user.username!=s.user.username}"><a href="javascript:void(0)" onclick="zln(${s.id});"  >删除</a></c:if>
     </td>
     </c:if>
</tr>
</c:forEach>

<tr> 
                    <td colspan="8" align="left" >
					  共 ${totalRecords}条记录, 总页数:${pageModel.totalPage}页 <input type="hidden" value="${pageModel.lastPage}" id="totalpage" />&nbsp;
                  <input type="hidden" value="${currpage}" id="currpage" /><input type="hidden" value="${tage}" id="tage" />
                      <a href="javascript:void(0)" onclick="first('${pageContext.request.contextPath}/labreservation/Labreservationmanage?tage=${tage}&currpage=1')" target="_self">首页</a>				    
                      <a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/labreservation/Labreservationmanage?tage=${tage}'+'&currpage=')" target="_self">上一页</a>
                      <a href="javascript:void(0)" onclick="next('${pageContext.request.contextPath}/labreservation/Labreservationmanage?tage=${tage}'+'&currpage=')" target="_self">下一页</a>
                      <a href="javascript:void(0)" onclick="last('${pageContext.request.contextPath}/labreservation/Labreservationmanage?tage=${tage}&currpage=${pageModel.totalPage}')" target="_self">末页</a>				   
                  </td>
                </tr>	

</table>
</div></div></div>
</body>
</html -->