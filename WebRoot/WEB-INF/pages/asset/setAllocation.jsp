<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe" />
  
  <!-- 下拉框的样式 -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
	<!-- 下拉的样式结束 -->
	
	<link href="${pageContext.request.contextPath}/css/cmsSystem/plugins/sweetalert/sweetalert.css" rel="stylesheet">
	
  <script type="text/javascript">

  </script>
 <style>
  
fieldset label {
    display: block;
    float: left;
    font-weight: bold;
    height: 25px;
    line-height: 25px;
    margin: -5px 0 5px;
    padding-left: 10px;
    text-shadow: 0 1px 0 #fff;
    text-transform: uppercase;
    width: 90%;
}  
</style>
</head>
  
<body>
<div class="main_container cf rel w95p ma">
  <!-- 内容栏开始 -->
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div class="content-box">
  <!-- 标题 -->
  <div class="right-content">
	  <div id="TabbedPanels1" class="TabbedPanels">
		  <div class="TabbedPanelsContentGroup">
			  <div class="TabbedPanelsContent">
				  <div class="content-box">
				  	<div class="title">
				  	</div>
					  	<table> 
							<thead>
							<tr>
							    <th>序号</th>
								<th>药品名称</th>
								<th>cas号</th>
								<th>货号</th>
								<th>实验室</th>
								<th>药品柜名称</th>
								<th>药品柜编号</th>
								<th>现有数量</th>
							</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${records}" var="curr" varStatus="i">
									<tr>
									   <%--<td><input type="checkbox" id="flag1" name="flag1" value="${curr.id }" checked="checked"></td>--%>
										<td>${i.count+pageSize*(currpage-1)}</td>
										<td>${curr.asset.chName}[${curr.asset.specifications}]</td>
										<td>${curr.asset.cas}</td>
										<td>${curr.assetCabinetWarehouseAccess.assetAppRecord.styleNumber}</td>
										<td>
											<c:if test="${curr.assetCabinetWarehouse eq null}">${curr.position}</c:if>
											<c:if test="${curr.assetCabinetWarehouse ne null}">${curr.assetCabinetWarehouse.assetCabinet.labRoom.labRoomName}</c:if>
										</td>
										<td>
											<c:if test="${curr.assetCabinetWarehouse eq null}">-</c:if>
											<c:if test="${curr.assetCabinetWarehouse ne null}">${curr.assetCabinetWarehouse.assetCabinet.cabinetName}</c:if>
										</td>
										<td>
											<c:if test="${curr.assetCabinetWarehouse eq null}">-</c:if>
											<c:if test="${curr.assetCabinetWarehouse ne null}">${curr.assetCabinetWarehouse.warehouseName}</c:if>
										</td>
										<td>
										<c:if test="${curr.asset.specifications eq null}">${spec[i.count-1]*curr.cabinetQuantity}${unit[i.count-1] }</c:if>
										<c:if test="${curr.asset.specifications ne null}">${intPart[i.count-1]}${curr.asset.unit}${decimalPart[i.count-1]*spec[i.count-1]}${unit[i.count-1] }</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				  </div>
			  </div>
		  </div>
	  </div>
  </div>
 
	<div class="new-classroom"> 
	 
	<c:forEach items="${records}" var="curr" varStatus="i">
	<fieldset>
	 	<input type="checkbox" class="flag" id="flag" name="flag" value="${curr.id }" >
	    <label>
	    <c:if test="${curr.assetCabinetWarehouse eq null}">${curr.position}</p></c:if>
		<c:if test="${curr.assetCabinetWarehouse ne null}">${curr.assetCabinetWarehouse.assetCabinet.labRoom.labRoomName}
		-${curr.assetCabinetWarehouse.assetCabinet.cabinetName}
		-${curr.assetCabinetWarehouse.warehouseName}</c:if>
		</label>
		<input class="quantity"name="quantity" id="quantity"/>
	  </fieldset>
	 </c:forEach>
	     
	</div>
	<div class="moudle_footer">
        <div class="submit_link">
          <input class="btn" id="save" type="button" value="确定" onclick="setAllocation();" >
		  <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
        </div>
	</div> 
    
    <!-- 下拉框的js -->
	<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
	
	<script src="${pageContext.request.contextPath}/js/cmsSystem/plugins/iCheck/icheck.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/cmsSystem/plugins/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/cmsSystem/plugins/sweetalert/sweetalert.min.js"></script>
	
	<script type="text/javascript">
	    var config = {
	      '.chzn-select': {search_contains : true},
	      '.chzn-select-deselect'  : {allow_single_deselect:true},
	      '.chzn-select-no-single' : {disable_search_threshold:10},
	      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
	      '.chzn-select-width'     : {width:"95%"}
	    }
	    for (var selector in config) {
	      $(selector).chosen(config[selector]);
	    }
	function setAllocation(){
		var obj1 = document.getElementsByName("flag");
		var obj2 = document.getElementsByName("quantity");
		var flag = new Array();
		var quantity = new Array();
		var count = 0;
		
		for(i=0;i<obj1.length;i++){
			if(obj1[i].checked){
				flag[count] = obj1[i].value;
				quantity[count]=obj2[i].value;
				count++;
			}	  
		} 
		var id = ${id}; 
		$.ajax({
            url:'${pageContext.request.contextPath}/asset/saveAllocation?id='+${id},
            type:'POST',
           data:{"quantity":quantity,"flag":flag}, 
           error:function (request){
             alert('请求错误!');
           },
           success:function(data){
        	   if(data == 'everyError'){
        		   alert('大于柜门现有数量，请重新选择!');
        	   }
        	   else if(data == 'totalError'){
        		   alert('与申领总量不符，请重新选择!');
        	   }
        	   else{ 
                   parent.location.href="${pageContext.request.contextPath}/asset/allocateReceive?id="+data;
                     var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                     parent.layer.closeAll('iframe'); 
        	   }
           }         
     }); 
	}
	</script>
	<!-- 下拉框的js -->
  </div>
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>
