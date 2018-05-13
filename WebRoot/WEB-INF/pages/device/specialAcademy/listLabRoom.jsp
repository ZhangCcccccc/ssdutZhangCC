<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.equipment-resources"/>
<html>

<head>
    <title></title>
    <!--[if lt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <![endif]-->
    <!--[if gte IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>  
    <![endif]-->
    <meta name="decorator" content="iframe"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="" rel="stylesheet" type="text/css">
    <script type="text/javascript" src=""></script>
<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	
 <script type="text/javascript">
	//首页
	function first(url){
		document.queryForm.action=url;
		document.queryForm.submit();
	}
	//末页
	function last(url){
		document.queryForm.action=url;
		document.queryForm.submit();
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
		document.queryForm.action=url+page;
		document.queryForm.submit();
	}
	//下一页
	function next(url){
		var totalPage=${pageModel.totalPage};
		var page=${page};
		if(page>=totalPage){
			page=totalPage;
		}else{
			page=page+1
		}
		//alert("下一页的路径："+page);
		document.queryForm.action=url+page;
		document.queryForm.submit();
	}
	//取消查询
	function cancelQuery(){
		window.location.href="${pageContext.request.contextPath}/device/listLabRoomDevice?page=1";
	} 
</script>   
    <style type="text/css">
        .layout_classroom_intro {
            height: 50px;
            font-size: 14px;
            font-weight: bold;
            
            line-height: 50px;
            background: #eee;
            padding: 0 10px;
        }
        .layout_classroom_list{
        	overflow:hidden;
        }
        .layout_classroom_list li {
            width: 25%;
            float: left;
            margin-bottom: 25px;
        }
        
        .layout_classroom {
            width: 90%;
            margin: auto;
            overflow: hidden;
        }
        
        .layout_classroom_pic {
            width: 100%;
            border: 0px;
        }
        
        li {
            list-style: none;
        }
        a{
            text-decoration: none;
        }
        .layout_classroom a{
            color:#6ba6b4
        }
        .layout_classroom a:hover{
            color:#555
        }
    </style>
</head>

<body>
<div class="navigation">
				<div id="navigation">
					<ul>
						<li><a href="javascript:void(0)">实验设备管理</a></li>
						<li class="end"><a href="javascript:void(0)">设备管理</a></li>
					</ul>
				</div>
</div>

  <div class="tool-box">
            <form:form name="queryForm" action="${pageContext.request.contextPath}/device/listLabRoomDeviceForLabroom?page=1" method="post" modelAttribute="labRoomDevice">
					<table class="list_form">
						<tr>
							<td>实验室：</td>
							<td style="width:160px;">
								<form:select class="chzn-select"  path="labRoom.id" id="labRoom_id">
								<form:option value="">请选择</form:option>
								<form:options items="${rooms}" itemLabel="labRoomName" itemValue="id"/>
								</form:select>		    				    				            
							</td> 
							 
							 
							 <td>设备编号：</td>
							<td style="width:110px;">
								<form:select class="chzn-select"  path="schoolDevice.deviceNumber" id="schoolDevice_deviceNumber">
								<form:option value="">请选择</form:option>
								<form:options items="${listLabRoomDeviceAll}" itemLabel="schoolDevice.deviceNumber" itemValue="schoolDevice.deviceNumber"/>
								</form:select>
							</td> 
				     
							<td>设备名称：</td>
							<td style="width:170px;">
								<form:select class="chzn-select"  path="schoolDevice.deviceName" id="schoolDevice_deviceName">
								<form:option value="">请选择</form:option>
								<c:forEach items="${listLabRoomDeviceAll}" var="curr">
								<form:option value="${curr.schoolDevice.deviceName }">${curr.schoolDevice.deviceName}[${curr.schoolDevice.deviceNumber }]</form:option>
								</c:forEach>
								</form:select>
							</td> 
							<td>设备开放：</td>
							<td>
								<form:select class="chzn-select"  path="CDictionaryByAllowAppointment.CNumber" id="schoolDevice_allowAppointment">
								<form:option value="">请选择</form:option>
								<form:option value="1">是</form:option>
								<form:option value="2">否</form:option>
								</form:select>
							</td>  
							<td>设备管理员：</td>
							<td>
								<form:select class="chzn-select"  path="user.username" id="username">
								<form:option value="">请选择</form:option>
								<form:options items="${userMap}"/>
								</form:select>
							</td> 
							<td>
								<input type="button" value="取消" onclick="cancelQuery()"/>
								<input type="submit" value="查询">
							</td>
						</tr >
				</table>
				</form:form>	
</div>
<div class="right-content">	
	<div id="TabbedPanels1" class="TabbedPanels">
	
	<ul class="layout_classroom_list">
	<c:forEach items="${roomsList}" var="room" varStatus="i">
        <li>
            <div class="layout_classroom">
                <a href="${pageContext.request.contextPath}/device/listLabRoomDeviceNew?roomId=${room.id}&page=1">
	                <c:set var="flag" value="0"></c:set>
                	<c:forEach items="${room.commonDocuments}" var="d" varStatus="i">
	                	<c:if test="${d.type==4}">
	                	<c:set var="flag" value="1"></c:set>
	                    	<div style="height:220px;overflow:hidden;"><img src="${pageContext.request.contextPath}/${d.documentUrl}" class="layout_classroom_pic"></div>
	                    </c:if>	
					</c:forEach>
					<c:if test="${flag == 0}">
	                    	<div style="height:220px;overflow:hidden;"><img src="${pageContext.request.contextPath}/images/no-img.jpg" class="layout_classroom_pic"></div>
	                    </c:if>	
                    <div class="layout_classroom_intro">
                        	实验室名称：${room.labRoomName }
                    </div>
                </a>
            </div>
        </li>
	</c:forEach>
    </ul>
	
		<div class="TabbedPanelsContentGroup">
			<div class="TabbedPanelsContent">
			<!-- 分页模块 -->
			<div class="page" >
			        ${totalRecordsLabroom}条记录,共${pageModelLabroom.totalPage}页
			    <a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/device/listLabRoomDevice?page=1')" target="_self">首页</a>			    
				<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/device/listLabRoomDevice?page=')" target="_self">上一页</a>
				第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
				<option value="${pageContext.request.contextPath}/device/listLabRoomDevice?page=${page}">${page}</option>
				<c:forEach begin="${pageModelLabroom.firstPage}" end="${pageModelLabroom.lastPage}" step="1" varStatus="j" var="current">	
			    <c:if test="${j.index!=page}">
			    <option value="${pageContext.request.contextPath}/device/listLabRoomDevice?page=${j.index}">${j.index}</option>
			    </c:if>
			    </c:forEach></select>页
				<a href="javascript:void(0)"     onclick="next('${pageContext.request.contextPath}/device/listLabRoomDevice?page=')" target="_self">下一页</a>
			 	<a href="javascript:void(0)"    onclick="last('${pageContext.request.contextPath}/device/listLabRoomDevice?page=${pageModelLabroom.totalPage}')" target="_self">末页</a>
			    </div>
			<!-- 分页模块 -->
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
      '.chzn-select-width'     : {width:"100%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
</script>
<!-- 下拉框的js -->

</body>

</html>