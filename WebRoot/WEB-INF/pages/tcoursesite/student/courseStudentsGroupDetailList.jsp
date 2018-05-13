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
	function importStudents() {
		$("#importStudents").fadeIn(100);
	    var sessionId=$("#sessionId").val();
	    //获取当前屏幕的绝对位置
	    var topPos = window.pageYOffset;
	    //使得弹出框在屏幕顶端可见
	    $('#importStudents').window({left:"0px", top:(topPos+20)+"px"}); 
	    $("#importStudents").window('open');
	}
	
	//跳转选择学生页面
	function usersList() {
		window.location = "${pageContext.request.contextPath}/tcoursesite/student/groupStudentsList?tCourseSiteId="+${tCourseSite.id}+"&tCourseSiteGroupId="+${tCourseSiteGroupId}+"&currpage=1";
	}
	//返回
	function back() {
		window.location = "${pageContext.request.contextPath}/tcoursesite/student/courseStudentsGroupList?tCourseSiteId="+${tCourseSite.id}+"&currpage=1";
	}
	//弹出选择学生窗口
	function deleteTCourseSiteUser(id) {
		if(confirm("是否确认删除？"))
		   {
			window.location = "${pageContext.request.contextPath}/tcoursesite/student/deleteTCourseSiteGroupUser?tCourseSiteId="+${tCourseSite.id}+"&id="+id;
		   }
	}
	//弹出选择学生窗口
	function importStudentsBySchoolCourse(tCourseSiteId,courseNo) {
		if(courseNo != null && courseNo !=""){
			if(confirm("是否确认导入学生？"))
			   {
				window.location = "${pageContext.request.contextPath}/tcoursesite/student/saveTCourseSiteUsersByCourseNo?tCourseSiteId="+${tCourseSite.id};
			   }
		   }else{
			   alert("此课程无教务课程");
		   }
		
	}
</script>

</head>

<body>
    <div class="course_con ma">
        <div class="course_cont r">
            <ul class="tool_box cf rel zx2 pt5  pb10">
                <li class="rel l pb5">
                    <div class="wire_btn Lele l ml10 mt10 poi" onclick="usersList();">
                        <i class="fa fa-plus mr5"></i>学生
                    </div>
                    <div class="wire_btn Lele l ml10 mt10 poi" onclick="back();">
                        	返回
                    </div>
                </li>
            </ul>
            <div class="course_mod f14 mb2">
                <div class=" lh40 bgg  pl30 f18 ">
                    <span class="bc">分组成员</span>
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14">
                        	<form:form id="form" action="${pageContext.request.contextPath}/tcoursesite/student/deleteTCourseSiteGroupUsers?tCourseSiteId=${tCourseSite.id }&tCourseSiteGroupId=${tCourseSiteGroupId}" method="POST" enctype="multipart/form-data" >
                            <table class="w100p" id="studentsList">
                            	<tr>
                                	<th class="w5p tl">
                                		<input class="checkall " id="checkall"  type="checkbox" name="All" >
                                		<label class="mt10" for="checkall">全选</label>
		     		 				</th>
                                    <th class="w10p tl "></th>
                                    <%--<th class="w15p tl"></th>
                                    --%><th class="tl w10p" >
                                    	<a href="javascript:void(0);" onclick="deleteTCourseSiteUsers()">全部删除</a>
                                    </th>                                    
                                </tr>
                                <tr>
                                	<th class="w10p tl">
		     		 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名</th>
                                    <th class="w10p tl ">学号</th>
                                    <%--<th class="w15p tl">身份</th>
                                    --%><th class="tl w10p">操作</th>  
                                </tr>
                                <c:forEach items="${tCourseSiteUsers}" var="tCourseSiteUser" varStatus="i">
                                <tr>
                                	<td>           		
                                		<input class="l check_box" type='checkbox' id='${tCourseSiteUser.id}' name='checkname' value='${tCourseSiteUser.id}' />
		                    			<label class="l mt10" for="${tCourseSiteUser.id}">${tCourseSiteUser.user.cname}</label>		                    			
		                    		</td>
                                    <td>${tCourseSiteUser.user.username}</td>
                                    <td>
                                        	<a href="javascript:void(0);" class="g9 hbc" onclick="deleteTCourseSiteUser(${tCourseSiteUser.id})">
                                        	<i class="fa fa-trash-o f18 mr10  lh30 poi" ></i></a>
                                        	<a href="${pageContext.request.contextPath}/tcoursesite/student/saveTCourseSiteGroupUsersForRole?id=${tCourseSiteUser.id}">
                                        		<c:if test="${tCourseSiteUser.role==0}">设置为组长</c:if>
                                        		<c:if test="${tCourseSiteUser.role==2}">设置为学生</c:if>
                                        	</a>
                                        <%--<a href="javascript:void(0);" class="g9 hbc"><i class="fa fa fa-edit f18 mr10  lh30 poi" ></i></a>
                                    --%></td>
                                </tr>
                                </c:forEach>

                            </table>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="page course_cont r" >
	        ${totalRecords}条记录,共${pageModel.totalPage}页
	    	<a href="javascript:void(0)"    onclick="first('${pageContext.request.contextPath}/tcoursesite/student/tCourseSiteGroupDetail?tCourseSiteId=${tCourseSite.id}&tCourseSiteGroupId=${tCourseSiteGroupId}&currpage=1')" target="_self">首页</a>			    
			<a href="javascript:void(0)" onclick="previous('${pageContext.request.contextPath}/tcoursesite/student/tCourseSiteGroupDetail?tCourseSiteId=${tCourseSite.id}&tCourseSiteGroupId=${tCourseSiteGroupId}&currpage=')" target="_self">上一页</a>
			第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
				<option value="${pageContext.request.contextPath}/tcoursesite/student/tCourseSiteGroupDetail?tCourseSiteId=${tCourseSite.id}&tCourseSiteGroupId=${tCourseSiteGroupId}&currpage=${page}">${page}</option>
					<c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
				    	<c:if test="${j.index!=page}">
				    		<option value="${pageContext.request.contextPath}/tcoursesite/student/tCourseSiteGroupDetail?tCourseSiteId=${tCourseSite.id}&tCourseSiteGroupId=${tCourseSiteGroupId}&currpage=${j.index}">${j.index}</option>
				    	</c:if>
		    		</c:forEach>
	    	</select>页
			<a href="javascript:void(0)"  onclick="next('${pageContext.request.contextPath}/tcoursesite/student/tCourseSiteGroupDetail?tCourseSiteId=${tCourseSite.id}&tCourseSiteGroupId=${tCourseSiteGroupId}&currpage=')" target="_self">下一页</a>
	 		<a href="javascript:void(0)"  onclick="last('${pageContext.request.contextPath}/tcoursesite/student/tCourseSiteGroupDetail?tCourseSiteId=${tCourseSite.id}&tCourseSiteGroupId=${tCourseSiteGroupId}&currpage=${pageModel.totalPage}')" target="_self">末页</a>
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

function deleteTCourseSiteUsers(){
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
	if(confirm("是否确认删除？"))
	   {
		$("#form").submit();
	   }
	//$("#form").submit();
}
//缺少 checkFileType();
function checkFileType(){
		var string = $("#file").val();
		if(string==""){
			alert("请参照样本上传excel文件！");
			return false;
		}else{
			var ss = string.split(".");
			var s = (ss[ss.length-1]).toLowerCase();			
			if(s=="xls"||s=="xlsx"){
				$("#fileName").val(string);
				return true;
			}else{
				alert("请参照样本上传excel文件！");
				return false;
			}
		}
		
	}
</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>

</body>

</html>