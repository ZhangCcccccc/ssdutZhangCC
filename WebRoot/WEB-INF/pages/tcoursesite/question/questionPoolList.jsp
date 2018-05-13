<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<%--<%@ include file="/WEB-INF/sitemesh-decorators/course.jsp" %>
--%><html>

<head>
    <title></title>
    <!--[if lt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <![endif]-->
    <!--[if gte IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>  
    <![endif]-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/reset.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/lib.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/global.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/jquery.searchableSelect.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>

<script type="text/javascript">
	$(function(){
	   	$("#print").click(function(){
			$("#my_show").jqprint();
		});
	});
	
	function newQuestionPool(id,tCourseSiteId){
		if(id!=""){//修改则查询原信息
			$("#questionpoolId").val(id);
			$.ajax({
				url:"${pageContext.request.contextPath}/tcoursesite/question/findQusetionStringById?tCourseSiteId="+tCourseSiteId+"&id="+id,
		       	type:"POST",
		       	async:false,
		       	success:function(data){
					$("#questionTitle").val(data[0]);
					$("#questionType").val(data[1]);
					findQusetionListByTypeAndId(data[1],id,tCourseSiteId);
					$("#description").val(data[2]);
					$("#questionpoolParentId").val(data[3]);
					$("#sort").val(data[4]);
		       	}
			})
		}else{//新增
			$("#questionpoolId").val("");
			findQusetionListByTypeAndId($("#questionType").val(),id,tCourseSiteId);		
			$("#description").val("");
			$("#questionTitle").val("");
			$("#questionPoolParentId").val("");
			$("#sort").val("");
		}
		
		//绑定change事件
		$("#questionType").change(function(){
			findQusetionListByTypeAndId($("#questionType").val(),id,tCourseSiteId);
		})
	    $("#newQuestionPool").fadeIn(100);
	}
	
	function findQusetionListByTypeAndId(type,id,tCourseSiteId){
		$.ajax({
			url:"${pageContext.request.contextPath}/tcoursesite/question/findQusetionListByTypeAndId?type="+type+"&id="+id+"&tCourseSiteId="+tCourseSiteId,
	       	type:"POST",
	       	success:function(data){
	       		$("#questionpoolParentId").html(data);
	       	}
		})
	}
 	function copyQuestionPool(username,tCourseSiteId){
		$.ajax({
			async:false,
			url:"${pageContext.request.contextPath}/tcoursesite/question/findQusetionStringByUsername?username="+username+"&tCourseSiteId="+tCourseSiteId,
	       	type:"POST",
	       	success:function(data){ 
	       		//alert(data);
	       		var _tr = "";
	       		$.each(data,function(key,values){
	       			_tr +=  "<tr>"+"<td>";
	       			_tr +="<input class='l check_box' type='checkbox' id='"+key+"' name='checkname' value= '"+key+"'/>";
	       			_tr +="<label class='l mt10' for='"+key+"' >"+values+"</label>";
	       			//_tr +="<input class='l check_box' type='checkbox' id=poolid poolid='"+key+"' name='checkname'/>";
	       			//_tr +="<label class='l mt10' for='poolid' >"+values+"</label>";
	       			_tr +="</td>"+"</tr>";
	       		})
	       		
	       	 	$("#copyQuestionPoolByUserListDetail").html(_tr);
	       	}
		})
		$("#copyQuestionPoolByUser").fadeIn(100);
	}
 	function exportQuestionPoolById(questionpoolId){
		$.ajax({
			async:false,
			url:"${pageContext.request.contextPath}/tcoursesite/question/exportQuestionPoolById?questionId="+questionpoolId,
	       	type:"POST",
	       	success:function(data){ 
	       		alert("导出成功,D盘根目录下！");
	       	}
		})
	}
 	function exportExcelQuestionPoolById(questionId){
		$.ajax({
			async:false,
			url:"${pageContext.request.contextPath}/tcoursesite/question/exportExcelQuestionPoolById?questionId="+questionId,
	       	type:"POST",
	       	success:function(data){ 
	       		alert("导出成功,D盘根目录下！");
	       	}
		})
	}
</script>
</head>

<body>
    <div class="course_con ma">
        <div class="course_cont r">
            <ul class="tool_box cf rel zx2 pt5  pb10">
                <li class="rel l pb5">
                	<c:if test="${flag==1}">
                    <div class="wire_btn Lele l ml30 mt10 poi" onclick="newQuestionPool('',${tCourseSite.id})">
                        <i class="fa fa-plus mr5"></i>题库
                    </div>
                    <%--<div class="wire_btn Lele l ml30 mt10 poi" onclick="copyQuestionPool('${user.username}','${tCourseSite.id}')">
                        <i class="fa fa-plus mr5"></i>题库复制
                    </div>
                    --%></c:if>
                    <div class="wire_btn Lele l ml30 mt10 poi" onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=1&selectId=-1'">
                        <i class="fa mr5"></i>课程知识
                    </div>
                </li>
            </ul>
            <div class="course_mod f14 mb2">
                <div class=" lh40 bgg  pl30 f18 ">
                    <span class="bc">课程题库</span>
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14">
                            <table class="w100p">
                                <tr>
                                    <th class="w30p tl">名称</th>
                                    <th class="w10p tl ">创建人</th>
                                    <th class="w15p tl">创建时间</th>
                                    <th class="w20p tl">分类信息</th>
                                    <th class="tl w10p">操作</th>                                    
                                </tr>
                               <c:forEach items="${tAssignmentQuestionpools }" var="questionPool" varStatus="i">
	                               <c:if test="${questionPool.type != 1 && questionPool.TAssignmentQuestionpool == null}">
	                                <tr>
	                                    <td><a href="${pageContext.request.contextPath }/tcoursesite/question/findTAssignmentItemsByQuestionId?tCourseSiteId=${tCourseSite.id }&currpage=1&id=${questionPool.questionpoolId}" class="g3 hbc">${questionPool.title }</a></td>
	                                    <td>${questionPool.user.cname }</td>
	                                    <td><fmt:formatDate value="${questionPool.createdTime.time }" pattern="yyyy-MM-dd"/></td>
	                                    <td>${questionPool.description }</td>
	                                    <td style="width:20%">
	                                    	<c:if test="${flag==1}">
	                                        <a href="${pageContext.request.contextPath }/tcoursesite/question/deleteQuestionPoolById?tCourseSiteId=${tCourseSite.id}&questionId=${questionPool.questionpoolId}" onclick="return confirm('删除该题库会同时删除下面的试题,确认删除该题库？')" class="g9 hbc">
	                                        	<i class="fa fa-trash-o f18 mr10  lh30 poi" ></i></a>
	                                        <a href="javascript:void(0)" onclick="newQuestionPool(${questionPool.questionpoolId},${tCourseSite.id})" class="g9 hbc">
	                                        	<i class="fa fa fa-edit f18 mr10  lh30 poi" ></i></a>
	                                        <a href="javascript:void(0)" onclick="exportQuestionPoolById(${questionPool.questionpoolId})" class="g9 hbc">
	                                        	Txt导出 </a>
	                                        <a href="javascript:void(0)" onclick="exportExcelQuestionPoolById(${questionPool.questionpoolId})" class="g9 hbc">
	                                        	Excel导出 </a>             
	                                        </c:if>
	                                    </td>
	                                </tr>
	                                <c:forEach items="${questionPool.TAssignmentQuestionpools }" var="questionPool1" varStatus="j">
	                                	<tr>
		                                    <td><a href="${pageContext.request.contextPath }/tcoursesite/question/findTAssignmentItemsByQuestionId?tCourseSiteId=${tCourseSite.id }&currpage=1&id=${questionPool1.questionpoolId}" class="g3 hbc">${questionPool1.title }</a></td>
		                                    <td>${questionPool1.user.cname }</td>
		                                    <td><fmt:formatDate value="${questionPool1.createdTime.time }" pattern="yyyy-MM-dd"/></td>
		                                    <td>${questionPool1.description }</td>
		                                    <td>
		                                    	<c:if test="${flag==1}">
		                                        <a href="${pageContext.request.contextPath }/tcoursesite/question/deleteQuestionPoolById?tCourseSiteId=${tCourseSite.id }&questionId=${questionPool1.questionpoolId}" onclick="return confirm('删除该题库会同时删除下面的试题,确认删除该题库？')" class="g9 hbc">
		                                        	<i class="fa fa-trash-o f18 mr10  lh30 poi" ></i></a>
		                                        <a href="javascript:void(0)" onclick="newQuestionPool(${questionPool1.questionpoolId},${tCourseSite.id })" class="g9 hbc">
		                                        	<i class="fa fa fa-edit f18 mr10  lh30 poi" ></i></a>
		                                        </c:if>
		                                    </td>
		                                </tr>
		                                <c:forEach items="${questionPool1.TAssignmentQuestionpools }" var="questionPool2" varStatus="k">
		                                	<tr>
			                                    <td><a href="${pageContext.request.contextPath }/tcoursesite/question/findTAssignmentItemsByQuestionId?tCourseSiteId=${tCourseSite.id }&currpage=1&id=${questionPool2.questionpoolId}" class="g3 hbc">${questionPool2.title }</a></td>
			                                    <td>${questionPool2.user.cname }</td>
			                                    <td><fmt:formatDate value="${questionPool1.createdTime.time }" pattern="yyyy-MM-dd"/></td>
			                                    <td>${questionPool2.description }</td>
			                                    <td>
			                                    	<c:if test="${flag==1}">
			                                        <a href="${pageContext.request.contextPath }/tcoursesite/question/deleteQuestionPoolById?tCourseSiteId=${tCourseSite.id }&questionId=${questionPool2.questionpoolId}" onclick="return confirm('删除该题库会同时删除下面的试题,确认删除该题库？')" class="g9 hbc">
			                                        	<i class="fa fa-trash-o f18 mr10  lh30 poi" ></i></a>
			                                        <a href="javascript:void(0)" onclick="newQuestionPool(${questionPool2.questionpoolId},${tCourseSite.id })" class="g9 hbc">
			                                        	<i class="fa fa fa-edit f18 mr10  lh30 poi" ></i></a>
			                                        </c:if>
			                                    </td>
			                                </tr>
		                                </c:forEach>
	                                </c:forEach>
	                               </c:if>
                               </c:forEach>

                            </table>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="course_mod f14 mb2">
                <div class=" lh40 bgg  pl30 f18 ">
                    <span class="bc">公共题库</span>
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14">
                            <table class="w100p">
                                <tr>
                                    <th class="w30p tl">名称</th>
                                    <th class="w10p tl ">创建人</th>
                                    <th class="w15p tl">创建时间</th>
                                    <th class="w20p tl">分类信息</th>
                                    <th class="tl w10p">操作</th>                                    
                                </tr>
                               <c:forEach items="${tAssignmentQuestionpools }" var="questionPool" varStatus="i">
	                               <c:if test="${questionPool.type == 1 && questionPool.TAssignmentQuestionpool == null}">
	                                <tr>
	                                    <td><a href="${pageContext.request.contextPath }/tcoursesite/question/findTAssignmentItemsByQuestionId?tCourseSiteId=${tCourseSite.id }&currpage=1&id=${questionPool.questionpoolId}" class="g3 hbc">${questionPool.title }</a></td>
	                                    <td>${questionPool.user.cname }</td>
	                                    <td><fmt:formatDate value="${questionPool.createdTime.time }" pattern="yyyy-MM-dd"/></td>
	                                    <td>${questionPool.description }</td>
	                                    <td>
	                                    	<c:if test="${flag==1}">
	                                        <a href="${pageContext.request.contextPath }/tcoursesite/question/deleteQuestionPoolById?tCourseSiteId=${tCourseSite.id }&questionId=${questionPool.questionpoolId}" onclick="return confirm('删除该题库会同时删除下面的试题,确认删除该题库？')" class="g9 hbc">
	                                        	<i class="fa fa-trash-o f18 mr10  lh30 poi" ></i></a>
	                                        <a href="javascript:void(0)" onclick="newQuestionPool(${questionPool.questionpoolId},${tCourseSite.id })" class="g9 hbc">
	                                        	<i class="fa fa fa-edit f18 mr10  lh30 poi" ></i></a>
	                                        </c:if>
	                                    </td>
	                                </tr>
	                                <c:forEach items="${questionPool.TAssignmentQuestionpools }" var="questionPool1" varStatus="j">
	                                	<tr>
		                                    <td><a href="${pageContext.request.contextPath }/tcoursesite/question/findTAssignmentItemsByQuestionId?tCourseSiteId=${tCourseSite.id }&currpage=1&id=${questionPool1.questionpoolId}" class="g3 hbc">${questionPool1.title }</a></td>
		                                    <td>${questionPool1.user.cname }</td>
		                                    <td><fmt:formatDate value="${questionPool1.createdTime.time }" pattern="yyyy-MM-dd"/></td>
		                                    <td>${questionPool1.description }</td>
		                                    <td>
		                                    	<c:if test="${flag==1}">
		                                        <a href="${pageContext.request.contextPath }/tcoursesite/question/deleteQuestionPoolById?tCourseSiteId=${tCourseSite.id }&questionId=${questionPool1.questionpoolId}" onclick="return confirm('删除该题库会同时删除下面的试题,确认删除该题库？')" class="g9 hbc">
		                                        	<i class="fa fa-trash-o f18 mr10  lh30 poi" ></i></a>
		                                        <a href="javascript:void(0)" onclick="newQuestionPool(${questionPool1.questionpoolId},${tCourseSite.id })" class="g9 hbc">
		                                        	<i class="fa fa fa-edit f18 mr10  lh30 poi" ></i></a>
		                                        </c:if>
		                                    </td>
		                                </tr>
		                                <c:forEach items="${questionPool1.TAssignmentQuestionpools }" var="questionPool2" varStatus="k">
		                                	<tr>
			                                    <td><a href="${pageContext.request.contextPath }/tcoursesite/question/findTAssignmentItemsByQuestionId?tCourseSiteId=${tCourseSite.id }&currpage=1&id=${questionPool2.questionpoolId}" class="g3 hbc">${questionPool2.title }</a></td>
			                                    <td>${questionPool2.user.cname }</td>
			                                    <td><fmt:formatDate value="${questionPool1.createdTime.time }" pattern="yyyy-MM-dd"/></td>
			                                    <td>${questionPool2.description }</td>
			                                    <td>
			                                    	<c:if test="${flag==1}">
			                                        <a href="${pageContext.request.contextPath }/tcoursesite/question/deleteQuestionPoolById?tCourseSiteId=${tCourseSite.id }&questionId=${questionPool2.questionpoolId}" onclick="return confirm('删除该题库会同时删除下面的试题,确认删除该题库？')" class="g9 hbc">
			                                        	<i class="fa fa-trash-o f18 mr10  lh30 poi" ></i></a>
			                                        <a href="javascript:void(0)" onclick="newQuestionPool(${questionPool2.questionpoolId},${tCourseSite.id })" class="g9 hbc">
			                                        	<i class="fa fa fa-edit f18 mr10  lh30 poi" ></i></a>
			                                        </c:if>
			                                    </td>
			                                </tr>
		                                </c:forEach>
	                                </c:forEach>
	                               </c:if>
                               </c:forEach>

                            </table>
                        </div>
                    </div>
                </div>
            </div>
            
            
        </div>
    </div>
    <div class="window_box hide fix zx2  " id="newQuestionPool">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p20 bb f16">练习</div>
            <div class="add_con p20">
            <form:form action="${pageContext.request.contextPath }/tcoursesite/question/saveQuestionPool?tCourseSiteId=${tCourseSite.id }" method="POST" modelAttribute="tAssignmentQuestionpool">
                <div class="add_module cf f14">
                	<form:hidden path="questionpoolId" id="questionpoolId"/>
                    <div class="cf w100p  mt10 mb20">
                        <div class="l w15p lh25">名称</div>
                        <div class="l w45p">
                            <form:input path="title" id="questionTitle" required="true" class="w100p lh25 br3 b1" type="text" />
                        </div>

                    </div>
                    
                    <div class="cf w100p  mt10 mb20">
                        <div class="l w15p lh25">类别信息</div>
                        <div class="l w45p">
                            <form:input path="description" id="description"  class="w100p lh25 br3 b1" type="text" />
                        </div>

                    </div>
                    
                    <div class="cf w100p  mt10 mb20">
                        <div class="l w15p lh25">题库类型</div>
                        <div class="l w45p">
							<form:select  path="type" id="questionType" class="w100p lh25 br3 b1">
								<form:option value="2">课程题库</form:option>
								<form:option value="1">公共题库</form:option>
							</form:select>
                        </div>

                    </div>
                    
                    
                    <div class="cf w100p  mt10 mb20">
                        <div class="l w15p">父类别</div>
                        <div class="l w45p">
                            <form:select path="TAssignmentQuestionpool.questionpoolId" id="questionpoolParentId" class="w100p lh25 br3 b1">
								<form:option value="">请选择</form:option>
							</form:select>
                        </div>
                    </div>
                </div>
                <div class="cf tc">
                    <input type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" />
                    <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
                       	 取消
                    </div>
                </div>
            </form:form>
            </div>
        </div>
    </div>
    <div class="window_box hide fix zx2  " id="copyQuestionPoolByUser" style="z-index:300;">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>                        
            	<form:form id="form" action="${pageContext.request.contextPath }/tcoursesite/question/saveCopyQuestionPool?tCourseSiteId=${tCourseSite.id}" method="POST" modelAttribute="tAssignmentQuestionpool" enctype="multipart/form-data" >
                            <table class="w100p" id="copyQuestionPoolByUserList">                            	
                            	<tr>
                                	<th class="w5p tl">
                                		<input class="checkall " id="checkall"  type="checkbox" name="All" >
                                		<label class="mt10" for="checkall">全选</label>
		     		 				</th>
                                    <th class="w10p tl "></th>
                                    <th class="w15p tl"></th>                                                                      
                                </tr>                              
                                <tr>
                                	<th class="w10p tl">
		     		 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题库名</th>
                                </tr>
                               
                                <tr id="copyQuestionPoolByUserListDetail">
                                	<%--<td>                               		
                                		<input class="l check_box" type='checkbox' id='${tCourseSiteUser.id}' name='checkname' value='${tCourseSiteUser.id}' />
		                    			<label class="l mt10" for="${tCourseSiteUser.id}">${tCourseSiteUser.user.cname}</label>
		                    		</td>                                                                                                      
                                --%></tr>
                               
                            </table>
                            
                            <div class="cf tc">
                    	<%--<div type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" value="导入"/></div>
                    	--%><div class="btn bgb l mt10 poi  plr20 br3 wh">
                    	<a href="javascript:void(0);" onclick="saveCopyQuestionPool()">导入</a>
                    	</div>
                    	<div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">
                       	 取消
                    </div>
                </div>
                            </form:form></div>
                           
                            
    </div>
         <script type="text/javascript">
         $(".checkall").click(
        		    function (event) {
        		        if (this.checked) {
        		            $(this).parent().parent().parent().find("input[name='checkname']").each(function () {
        		                this.checked = true;
        		            });
        		        } else {
        		            $(this).parent().parent().parent().find("input[name='checkname']").each(function () {
        		                this.checked = false;
        		            });
        		        }
        		    }
        		);

        		$(".check_box").click(
        			    function (event) {
        			        if (this.checked) {
        			        } else {
        			        	$(this).parent().parent().parent().find("input[name='All']").each(function () {
        			                this.checked = false;
        			            });
        			        }
        			    }
        			);
        		function saveCopyQuestionPool(){
        			var Size = 0;
        			$("#copyQuestionPoolByUserList").find("input[type='checkbox']").each(function () {
        			  if(this.checked){
        			   Size = Size + 1;
        			  }
        			    });
        			 if(Size==0){
        			  alert("请选择！");
        			  return false;
        			 }
        			if(confirm("是否确认导入？"))
        			   {
        				$("#form").submit();
        			   }
        			//$("#form").submit();
        		}

    </script>
  
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>
    

</body>

</html>