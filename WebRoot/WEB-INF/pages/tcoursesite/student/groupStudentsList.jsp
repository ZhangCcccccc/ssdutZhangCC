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
		//document.queryForm.action=url;
		//document.queryForm.submit();
		window.location = url;
	}
	//末页
	function last(url){
		//document.queryForm.action=url;
		//document.queryForm.submit();
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
		//alert("上一页的路径："+url+page);
		//document.queryForm.action=url+page;
		//document.queryForm.submit();
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
		//alert("下一页的路径："+page);
		//document.queryForm.action=url+page;
		//document.queryForm.submit();
		window.location = url+page;
	}
	//返回
	function back() {
		window.location = "${pageContext.request.contextPath}/tcoursesite/student/tCourseSiteGroupDetail?tCourseSiteId="+${tCourseSite.id}+"&tCourseSiteGroupId="+${tCourseSiteGroupId}+"&currpage=1";
	}
</script>

</head>

<body>
    <div class="course_con ma">
        <div class="course_cont r">
        <ul class="tool_box cf rel zx2 pt5  pb10">
                <li class="rel l pb5">
                    <div class="wire_btn Lele l ml10 mt10 poi" onclick="back();">
                        	返回
                    </div>
                </li>
            </ul>
            <div class="course_mod f14 mb2">
                <div class=" lh40 bgg  pl30 f18 ">
                    <span class="bc">学生</span>
                </div>
             	<div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14">
                        	<form:form id="form" action="${pageContext.request.contextPath}/tcoursesite/student/saveTCourseSiteGroupUsers?tCourseSiteId=${tCourseSite.id}&tCourseSiteGroupId=${tCourseSiteGroupId}" 
                        	method="POST" modelAttribute="tAssignment" enctype="multipart/form-data" >
                            <table class="w100p" id="studentsList">
                                <tr>
                                	<th class="w10p tl">
                                		<input class="checkall " id="checkall"  type="checkbox" name="All" >
		     		 					<label class="mt10" for="checkall">姓名</label></th>
                                    <th class="w10p tl ">学号</th>
                                    <%--<th class="w15p tl">身份</th>                                   
                                --%></tr>
                                <c:forEach items="${tCourseSiteUsers}" var="tCourseSiteUser" varStatus="i">
                                <tr>
                                	<td>
                                		<input class="l check_box" type='checkbox' id='${tCourseSiteUser.id}' name='checkname' value='${tCourseSiteUser.id}' />
		                    			<label class="l mt10" for="${tCourseSiteUser.id}">${tCourseSiteUser.user.cname}</label>
		                    			</td>
                                    <td>${tCourseSiteUser.user.username}</td>
                                </tr>
                                </c:forEach>

                            </table>
                             <div class="bbtn bgb f14 le100 mt10 poi tc br3 wh w80" onclick="submit()">
		                       	添加
		                    </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="page course_cont r" >
	        ${totalRecords}条记录,共${pageModel.totalPage}页
	    	<a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/tcoursesite/student/groupStudentsList?tCourseSiteId=${tCourseSite.id}&tCourseSiteGroupId=${tCourseSiteGroupId}&currpage=1')" target="_self">首页</a>			    
			<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/tcoursesite/student/groupStudentsList?tCourseSiteId=${tCourseSite.id}&tCourseSiteGroupId=${tCourseSiteGroupId}&currpage=')" target="_self">上一页</a>
			第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
				<option value="${pageContext.request.contextPath}/tcoursesite/student/groupStudentsList?tCourseSiteId=${tCourseSite.id}&tCourseSiteGroupId=${tCourseSiteGroupId}&currpage=${page}">${page}</option>
					<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
				    	<c:if test="${j.index!=page}">
				    		<option value="${pageContext.request.contextPath}/tcoursesite/student/groupStudentsList?tCourseSiteId=${tCourseSite.id}&tCourseSiteGroupId=${tCourseSiteGroupId}&currpage=${j.index}">${j.index}</option>
				    	</c:if>
		    		</c:forEach>
	    	</select>页
			<a href="javascript:void(0)"  onclick="next('${pageContext.request.contextPath}/tcoursesite/student/groupStudentsList?tCourseSiteId=${tCourseSite.id}&tCourseSiteGroupId=${tCourseSiteGroupId}&currpage=')" target="_self">下一页</a>
	 		<a href="javascript:void(0)"  onclick="last('${pageContext.request.contextPath}/tcoursesite/student/groupStudentsList?tCourseSiteId=${tCourseSite.id}&tCourseSiteGroupId=${tCourseSiteGroupId}&currpage=${pageModel.totalPage}')" target="_self">末页</a>
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

function submit(){		
	 var studentSize = 0;
	$("#studentsList").find("input[type='checkbox']").each(function () {
	  if(this.checked){
	   studentSize = studentSize + 1;
	  }
	    });
	 if(studentSize==0){
	  alert("请选择学生！");
	  return false;
	 }
	 $("#form").submit();
}
</script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>

</body>

</html>