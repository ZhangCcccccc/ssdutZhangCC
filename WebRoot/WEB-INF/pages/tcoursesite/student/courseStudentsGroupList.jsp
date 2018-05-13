<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html>

<head>
    <title>XDU实验实训教学平台</title>
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

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>
<script type="text/javascript">
	//首页
	function first(url){
		window.location = url;
	}
	//末页
	function last(url){
		window.location = url;
	}
	//上一页
	function previous(url){
		var page=${page};
		if(page==1){
			page=1;
		}else{
			page=page-1;
		}
		window.location = url+page;
	}
	//下一页
	function next(url){
		var totalPage=${pageModel.totalPage};
		var page=${page};
		if(page>=totalPage){
			page=totalPage;
		}else{
			page=page+1;
		}
		window.location = url+page;
	}
	

	//弹出选择学生窗口
	function deleteTCourseSiteUser(id) {
		if(confirm("是否确认删除？"))
		   {
			window.location = "${pageContext.request.contextPath}/tcoursesite/student/deleteTCourseSiteUser?tCourseSiteId="+${tCourseSite.id}+"&id="+id;
		   }
	}
	
	function newTCourseSiteGroup(id,tCourseSiteId){
		if(id!="-1"){//修改则查询原信息
			$("#tCourseSiteGroupId").val(id);
			$.ajax({
				url:"${pageContext.request.contextPath}/tcoursesite/student/findTCourseSiteGroupById?tCourseSiteId="+tCourseSiteId+"&id="+id,
		       	type:"POST",
		       	async:false,
		       	success:function(data){
					$("#groupTitle").val(data[0]);
					$("#description").val(data[1]);
		       	}
			})
		}else{//新增
			$("#groupTitle").val("");
			$("#description").val("");
		}
		
	    $("#newTCourseSiteGroup").fadeIn(100);
	}
	//删除
	function deletetCourseSiteGroup(id) {
		if(confirm("是否确认删除？"))
		   {
			window.location = "${pageContext.request.contextPath}/tcoursesite/student/deletetCourseSiteGroup?tCourseSiteId="+${tCourseSite.id}+"&id="+id;
		   }
	}
	//全部删除
	function deletetCourseSiteGroups(){
		var studentSize = 0;
		$("#studentsList").find("input[type='checkbox']").each(function () {
		  if(this.checked){
		   studentSize = studentSize + 1;
		  }
		    });
		 if(studentSize==0){
		  alert("请选择课程分组！");
		  return false;
		 }
		if(confirm("是否确认删除？"))
		   {
			$("#form").submit();
		   }
	}
</script>

</head>

<body>
    <div class="course_con ma">
        <div class="course_cont r">
            <ul class="tool_box cf rel zx2 pt5  pb10">
                <li class="rel l pb5">
                	<c:if test="${flag==1}">
                    <div class="wire_btn Lele l ml10 mt10 poi" onclick="newTCourseSiteGroup('-1',${tCourseSite.id})">
                        <i class="fa fa-plus mr5"></i>学生分组
                    </div>
                    </c:if>
                </li>
                <li class="rel l pb5">
                    <div class="wire_btn Lele l ml10 mt10 poi" onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=2&selectId=-1'">
                        <i class="fa fa-plus mr5"></i>创造体验
                    </div>
                </li>
            </ul>
            <div class="course_mod f14 mb2">
                <div class=" lh40 bgg  pl30 f18 ">
                    <span class="bc">学生分组</span>
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14">
                        	<form:form id="form" action="${pageContext.request.contextPath}/tcoursesite/student/deletetCourseSiteGroups?tCourseSiteId=${tCourseSite.id }" method="POST" enctype="multipart/form-data" >
                            <table class="w100p" id="studentsList">
                            	<c:if test="${flag==1}">
                            	<tr>
                                	<th class="w5p tl">
                                		<input class="checkall " id="checkall"  type="checkbox" name="All" >
                                		<label class="mt10" for="checkall">全选</label>
		     		 				</th>
                                    <%--<th class="w10p tl "></th>
                                    --%><th class="w15p tl"></th>
                                    <th class="tl w10p" >
                                    	<a href="javascript:void(0);" onclick="deletetCourseSiteGroups()">全部删除</a>
                                    </th>                                    
                                </tr>
                              </c:if>
                                <tr>
                                	<th class="w10p tl">
		     		 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程分组名称</th>
                                    <th class="w10p tl ">课程分组描述</th>
                                    <th class="tl w10p">操作</th>  
                                </tr>
                                <c:forEach items="${tCourseSiteGroups}" var="tCourseSiteGroup" varStatus="i">
                                <tr>
                                	<td>
                                		<c:choose>  
    										<c:when test="${flag==1}">    <!--如果 --> 
    											<input class="l check_box" type='checkbox' id='${tCourseSiteGroup.id}' name='checkname' value='${tCourseSiteGroup.id}' />
		                    					<label class="l mt10" for="${tCourseSiteGroup.id}">${tCourseSiteGroup.groupTitle}</label>
   	 										</c:when>
   	 										<c:otherwise>  <!--否则 -->  
   	 											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${tCourseSiteGroup.groupTitle}
    										</c:otherwise>
                                		</c:choose>
		                    		</td>
                                    <td>${tCourseSiteGroup.description}</td>
                                    <td>
                                    	<c:if test="${flag==1}">
                                        	<a href="javascript:void(0);" class="g9 hbc" onclick="deletetCourseSiteGroup(${tCourseSiteGroup.id})">
                                        	<i class="fa fa-trash-o f18 mr10  lh30 poi" ></i></a>
                                        	<a href="javascript:void(0)" class="g9 hbc" onclick="newTCourseSiteGroup(${tCourseSiteGroup.id},${tCourseSiteGroup.TCourseSite.id})">
		                                    <i class="fa fa fa-edit f18 mr10  lh30 poi" ></i></a>
		                                    <a href="${pageContext.request.contextPath}/tcoursesite/student/tCourseSiteGroupDetail?tCourseSiteId=${tCourseSiteGroup.TCourseSite.id}&tCourseSiteGroupId=${tCourseSiteGroup.id}&currpage=1">
		                                    	详情</a>
                                        </c:if>
									</td>								
                                </tr>
                                </c:forEach>

                            </table>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
          <div class="window_box hide fix zx2  " id="newTCourseSiteGroup">
        <div class="window_con bgw b1 br3 rel bs10 ">
            <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
            <div class="add_tit p20 bb f16">新建学生分组</div>
            <div class="add_con p20">
            <form:form action="${pageContext.request.contextPath }/tcoursesite/student/savetCourseSiteGroup?tCourseSiteId=${tCourseSite.id }" method="POST" modelAttribute="tCourseSiteGroup">
                <div class="add_module cf f14">
                	<form:hidden path="id" id="tCourseSiteGroupId"/>
                	<form:hidden path="TCourseSite.id" id="tCourseSiteId" value="${tCourseSite.id }"/>
                    <div class="cf w100p  mt10 mb20">
                        <div class="l w15p lh25">课程分组名称</div>
                        <div class="l w45p">
                            <form:input path="groupTitle" id="groupTitle" required="true" class="w100p lh25 br3 b1" type="text" />
                        </div>

                    </div>
                    
                    <div class="cf w100p  mt10 mb20">
                        <div class="l w15p lh25">课程分组描述</div>
                        <div class="l w45p">
                            <form:input path="description" id="description"  class="w100p lh25 br3 b1" type="text" />
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
        
        <div class="page course_cont r" >
	        ${totalRecords}条记录,共${pageModel.totalPage}页
	    	<a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/tcoursesite/student/courseStudentsGroupList?tCourseSiteId=${tCourseSite.id}&currpage=1')" target="_self">首页</a>			    
			<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/tcoursesite/student/courseStudentsGroupList?tCourseSiteId=${tCourseSite.id}&currpage=')" target="_self">上一页</a>
			第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
				<option value="${pageContext.request.contextPath}/tcoursesite/student/courseStudentsGroupList?tCourseSiteId=${tCourseSite.id}&currpage=${page}">${page}</option>
					<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
				    	<c:if test="${j.index!=page}">
				    		<option value="${pageContext.request.contextPath}/tcoursesite/student/courseStudentsGroupList?tCourseSiteId=${tCourseSite.id}&currpage=${j.index}">${j.index}</option>
				    	</c:if>
		    		</c:forEach>
	    	</select>页
			<a href="javascript:void(0)"  onclick="next('${pageContext.request.contextPath}/tcoursesite/student/courseStudentsGroupList?tCourseSiteId=${tCourseSite.id}&currpage=')" target="_self">下一页</a>
	 		<a href="javascript:void(0)"  onclick="last('${pageContext.request.contextPath}/tcoursesite/student/courseStudentsGroupList?tCourseSiteId=${tCourseSite.id}&currpage=${pageModel.totalPage}')" target="_self">末页</a>
	    </div>
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



</script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>

</body>

</html>