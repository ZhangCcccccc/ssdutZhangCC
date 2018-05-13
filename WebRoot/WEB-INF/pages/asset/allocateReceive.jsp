<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>

	<!-- 表头排序 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/zhbitlims/common/sortTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/zhbitlims/common/jquery.tablesorter.js"></script>
	
	<link href="${pageContext.request.contextPath}/css/cmsSystem/plugins/sweetalert/sweetalert.css" rel="stylesheet">
	
    <script src="${pageContext.request.contextPath}/js/cmsSystem/plugins/iCheck/icheck.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/cmsSystem/plugins/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/cmsSystem/plugins/sweetalert/sweetalert.min.js"></script>
   <!-- 下拉框的样式 -->
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  <link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/chosen/chosen.css" />
  <!-- 下拉的样式结束 --> 
  
  <!-- 文件上传的样式和js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/PopupDiv_v1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/js/jquery.jsonSuggest-2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/swfupload/uploadify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/jquery.uploadify.min.js"></script>  
  
  <style>
    select{
        width:162px;
        margin-left:10px;
    }
    .chzn-container{
    width:162px !important;
    margin-left:10px
    }
    .edit-content-box {
    border: 1px solid #9BA0AF;
    border-radius: 5px;
    overflow: visible;
    margin-top: 15px;
}
  </style>
  <script type="text/javascript">
  $(document).ready(
		  //设置
	    	function (){                                       
	    		          $('[data-id]').each(function(i,e){
	    				         $(e).on('click',function(){
	    				         layer.open({
	    		                    type: 2,
	    		                    title: '设置',
	    		                    maxmin: true,
	    		                    shadeClose: true, 
	    		                    area : ['700px' , '350px'],
	    		                    content: '${pageContext.request.contextPath }/asset/setAllocation?id='+$(e).attr('data-id')
	    		                    })  
	    		   		      });                                              
	    		   			});
	    		          $('[data-view]').each(function(i,e){
	    				         $(e).on('click',function(){
	    				         layer.open({
	    		                    type: 2,
	    		                    title: '查看',
	    		                    maxmin: true,
	    		                    shadeClose: true, 
	    		                    area : ['700px' , '350px'],
	    		                    content: '${pageContext.request.contextPath }/asset/viewAllocation?id='+$(e).attr('data-view')
	    		                    })  
	    		   		      });                                              
	    		   			});
	    		          $('[data-showview]').each(function(i,e){
	    				         $(e).on('click',function(){
	    				         layer.open({
	    		                    type: 2,
	    		                    title: '查看',
	    		                    maxmin: true,
	    		                    shadeClose: true, 
	    		                    area : ['700px' , '350px'],
	    		                    content: '${pageContext.request.contextPath }/asset/showViewAllocation?id='+$(e).attr('data-showview')
	    		                    })  
	    		   		      });                                              
	    		   			});
	    }); 
	    
  </script>
</head>

<body>
<div class="main_container cf rel w95p ma">
 
  <div class="right-content">
  <div id="TabbedPanels1" class="TabbedPanels">
  <div class="TabbedPanelsContentGroup">
  <div class="TabbedPanelsContent">
  <div>
    <fieldset class="introduce-box">
    <legend>申领单基本信息</legend>
        <table id="listTable" width="50%" cellpadding="0">
            <tr>
                <th>申领人：</th><td>${assetReceive.user.cname}</td>
                <th>实验大纲：</th><td>
	    	<c:if test = "${assetReceive.type == 0}"><span class="title_focus">公用</span></c:if>
	    	<c:if test = "${assetReceive.type != 0}"><span class="title_focus">${assetReceive.operationItem.operationOutline.labOutlineName}</span></c:if>
	    </td>
				<th>实验项目：</th><td>
	    	<c:if test = "${assetReceive.type == 0}"><span class="title_focus">--</span></c:if>
	    	<c:if test = "${assetReceive.type != 0}"><span class="title_focus">${assetReceive.operationItem.lpName}</span></c:if>
	    </td>
            </tr>
            <tr>
                <th>实验内容：</th><td>${assetReceive.projectContent}</td>
            </tr>
        </table>
    </fieldset>
  </div>
  </div>
  </div>
  </div>
  
  <div class="right-content">
      <div id="TabbedPanels1" class="TabbedPanels">
          <div class="TabbedPanelsContentGroup">
              <div class="TabbedPanelsContent">
                  <div class="content-box">
                    <div class="title">
                         <a class="btn btn-new" href="${pageContext.request.contextPath}/asset/listAssetReceives?currpage=1&status=9">返回列表</a>
                    </div>
                        <table> 
                            <thead>
                            <tr>
                            	<th>序号</th>
                                <th>药品名称</th>
                                <th>申领单位</th>
                                <th>申领数量</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            
                            <tbody>
                                <c:forEach items="${listAssetReceiveRecords }" var="curr" varStatus="i">
                                    <tr>
                                        <td>${i.count+pageSize*(currpage-1)}</td>
                                        <td>${curr.asset.chName}[${curr.asset.specifications}]</td>
                                        <td>${units[i.count-1]}</td>
                                        <td>${curr.quantity}</td>
                                        <td>
                                        	<c:if test="${curr.result eq 0 }">审核拒绝</c:if>
                                        	<c:if test="${curr.result eq 1 && curr.allocationStatus eq 1}">已设置</c:if>
                                        	<c:if test="${curr.result eq 1 && curr.allocationStatus eq 0}">未设置</c:if>
                                        </td>
                                        <td> 
                                        	<c:if test="${curr.result eq 1 && curr.allocationStatus eq 1}"><a data-showview='${curr.id}'>查看</a> </c:if>
                                        	<%--<c:if test="${curr.result eq 1 && nums[i.count-1] eq 0}">无物资</c:if>--%>
                                        	<c:if test="${curr.result eq 1  && curr.allocationStatus eq 0 && nums[i.count-1] ne 0 && nums[i.count-1] ne 1}"><a data-id='${curr.id}'>设置</a> </c:if>
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
        <input type="hidden" id="pageContext" value="${pageContext.request.contextPath }"/>
        <input type="hidden" id="itemId" value="${operationItem.id }"/>
   <script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
   <script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
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
    </script>
    <!-- 下拉框的js -->  
     <script type="text/javascript">
     var expendableId;
     function moveId(id){
         expendableId = id;
     }
        $(".changeAmount").click(function(){
            $(this).hide();//修改按钮隐藏
            $(this).parent().find(".edit-edit").slideDown();//修改信息显示
        });
        $(".edit-edit").blur(function(){
            $(this).hide();//修改按钮隐藏
            $(this).parent().find(".changeAmount").slideDown();//修改信息显示
            var amount = $(this).val();
            if(amount==''){
                amount=0;
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/operation/saveItemExpendableAmount?expendableId="+expendableId+"&amount="+amount,
                type:"POST",
                success:function(data){//AJAX查询成功
                        if(data=="success"){
                            window.history.go(0);
                        }   
                }
            });
        });
    </script>
</body>
</html>
