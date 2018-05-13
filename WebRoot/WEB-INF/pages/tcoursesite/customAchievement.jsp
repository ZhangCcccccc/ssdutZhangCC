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
	<link href="${pageContext.request.contextPath}/css/tCourseSite/message/notice.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>
    <!-- 打印开始 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
	<!-- 打印结束 -->
<script type="text/javascript">
	//打印
	$(document).ready(function(){	 
	      $("#myPrint").click(function(){	    	  
	      $("#my_show").jqprint();	    
	      });
	});
</script>
<style>
.tool_box li div{
	padding:3px 6px !important;
}
</style>       
</head>

<body>
    <div class="course_con ma back_gray">
        <div class="course_cont r back_gray">
        	<div class="notice_cont ">
            <div class="w100p cf">
                
                <ul class="tool_box tab cf rel zx2 pt5 ">
                
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/student/courseStudentsList?tCourseSiteId=${tCourseSite.id}&currpage=1" class="g3">
                        <i class="fa fa-edit mr5"></i>班级成员
                        </a>
                    </div>
                </li><%--
                
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/attendence?tCourseSiteId=${tCourseSite.id}&attendenceId=-1" class="g3">
                        <i class="fa fa-edit mr5"></i>考勤
                        </a>
                    </div>
                </li>
                
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi ">
                        <a href="#" class="g9">
                        <i class="fa fa-file-text-o mr5"></i>作业
                        </a>
                    </div>
                </li>
                
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/gradeBook?tCourseSiteId=${tCourseSite.id}&type=assignment" class="g3">
                        <i class="fa fa-comments-o mr5"></i>成绩
                        </a>
                    </div>
                </li>--%>
                <li class="rel l pb5">
                    <div class="wire_btn1 bgb l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/assignmentAchievement?tCourseSiteId=${tCourseSite.id}&type=assignment" class="g3">
                        <i class="fa fa-comments-o mr5"></i>成绩
                        </a>
                    </div>
                </li><%--
                
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi">
                        <a href="#" class="g9">
                        <i class="fa fa-comments-o mr5"></i>学习行为
                        </a>
                    </div>
                </li>
                --%></ul>
            </div>
            </div>    
        
            <ul class="tool_box tab cf rel zx2 pt5  pb10">
                 <li class="rel l pb5">
                    <div class="wire_btn l ml10 mt10 poi">
                      <a href="${pageContext.request.contextPath}/tcoursesite/assignmentAchievement?tCourseSiteId=${tCourseSite.id}&type=assignment" class="g3">
                        <i class="fa fa-bar-chart mr5"></i>作业成绩
                      </a>
                    </div>
                </li>
                <li class="rel l pb5">
                    <div class="wire_btn l ml10 mt10 poi">
                      <a href="${pageContext.request.contextPath}/tcoursesite/examAchievement?tCourseSiteId=${tCourseSite.id}&type=exam" class="g3">
                        <i class="fa fa-bar-chart mr5"></i>测试成绩
                      </a>
                    </div>
                </li>
                <li class="rel l pb5">
                    <div class="wire_btn l ml10 mt10 poi">
                       <a href="${pageContext.request.contextPath}/tcoursesite/testAchievement?tCourseSiteId=${tCourseSite.id}&type=test" class="g3">
                          <i class="fa fa-bar-chart mr5"></i>考试成绩
                       </a>
                    </div>
                </li>
                 <li class="rel l pb5">
                    <div class="wire_btn l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/attendenceAchievement?tCourseSiteId=${tCourseSite.id}&type=attendence" class="g3">
                          <i class="fa fa-bar-chart mr5"></i>考勤成绩
                       </a>
                    </div>
                </li>
                 <li class="rel l pb5">
                    <div class="wire_btn l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/experimentAchievement?tCourseSiteId=${tCourseSite.id}&type=experiment" class="g3">
                          <i class="fa fa-bar-chart mr5"></i>实验成绩
                       </a>
                    </div>
                </li>
                <c:if test="${customFlag }">
                	<li class="rel l pb5">
                    <div class="wire_btn l ml10 mt10 poi bgc">
                        <a href="${pageContext.request.contextPath}/tcoursesite/customAchievement?tCourseSiteId=${tCourseSite.id}&type=custom" class="g3">
                          <i class="fa fa-bar-chart mr5"></i>自定义成绩
                       </a>
                    </div>
                    </li>
                </c:if>
                <li class="rel l pb5">
                    <div class="wire_btn l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/sumAchievement?tCourseSiteId=${tCourseSite.id}&type=sum" class="g3">
                          <i class="fa fa-bar-chart mr5"></i>总成绩
                       </a>
                    </div>
                </li>
                <c:if test="${flag==1}">
                <li class="rel l pb5">
                    <div class="wire_btn l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/weightAchievement?tCourseSiteId=${tCourseSite.id}&type=weight" class="g3">
                          <i class="fa fa-bar-chart mr5"></i>成绩权重
                       </a>
                    </div>
                </li>
                </c:if>
                <c:if test="${flag==1}">
                <li class="rel l pb5">
                    <div class="wire_btn l ml10 mt10 poi">
                         <a href="${pageContext.request.contextPath}/tcoursesite/customGrade?tCourseSiteId=${tCourseSite.id}&type=customGrade" class="g3">
                          <i class="fa fa-bar-chart mr5"></i>自定义
                        </a>
                    </div>
                </li>
                </c:if>
            </ul>
            <c:if test="${customFlag }">
            <div class="tab_list f14 mb2">
              <form action="" method="POST" name="customForm" enctype="multipart/form-data">
                <div class=" lh40 bgg  pl30 f18 ">
                    <span class="bc">自定义成绩</span>
                    <div class="h20 lh20 br3 plr10 r f12 bgb wh ml30 mt10 mr5 poi">
                    	<c:if test="${flag>0}">
                    		<a href="javascript:void(0);" onclick="saveEditCustomScore(${tCourseSite.id})">保存</a>
                   		 </c:if>
                    	<input type="button" value="打印" id="myPrint">
                    </div>
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14">
                            <table class="w100p"  id="my_show">
                                <tr>
                                    <th class="w10p tl">姓名</th>
                                    <th class="tl">学号</th>
                                    <c:forEach items="${customTGradeObjects }" var="current"  varStatus="i">
                                    	<th class="tl">
                                    		${current.title }&nbsp;
                                    		<c:if test="${flag==1}">
                                    			<a href="javascript:void(0);" class="g9 hbc" onclick="deleteCustomScore(${current.id})">
                                    			<i class="fa fa-trash-o f18 mr10  lh30 poi" ></i></a>
                                    		</c:if>
                                    	</th>
                                    </c:forEach>
                                </tr>
                                
                                <c:forEach items="${customLists }" var="current"  varStatus="i">
                                 <c:if test="${flag==1||(flag==0&&current[1] eq user.username)}">
                                <tr>
                                	<c:forEach items="${current }" var="current1" varStatus="j">
                                		<c:if test="${j.count==1 || j.count==2 }">
                                			<td>
                                				${current1 }
                                	    	</td>
                                	    </c:if>
                                	    <c:if test="${j.count!=1 && j.count!=2 }">
                                    		<td>
                                    			<input  type='number' id='${current[1]}_${j.count }' name='${current[1]}_${j.count.toString() }' value='${current1 }'/>
                                    		</td>
                                    	</c:if>
                                    </c:forEach>
                                </tr>
                                </c:if>
                                </c:forEach>
                                
                            </table>
                        </div>
                    </div>
                </div>
               </form>
              <div class="page" >
   	          ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
              <a href="${pageContext.request.contextPath}/tcoursesite/customAchievement?currpage=1&tCourseSiteId=${tCourseSite.id }&type=${type }">首页</a>			    
	          <a href="${pageContext.request.contextPath}/tcoursesite/customAchievement?currpage=${pageModel.previousPage}&tCourseSiteId=${tCourseSite.id }&type=${type }">上一页</a>
	                                 第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	          <option value="${pageContext.request.contextPath}/tcoursesite/customAchievement?currpage=${pageModel.currpage}&tCourseSiteId=${tCourseSite.id }&type=${type }">${pageModel.currpage}</option>
	          <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
              <c:if test="${j.index!=pageModel.currpage}">
              <option value="${pageContext.request.contextPath}/tcoursesite/customAchievement?currpage=${j.index}&tCourseSiteId=${tCourseSite.id }&type=${type }">${j.index}</option>
              </c:if>
              </c:forEach></select>页
	          <a href="${pageContext.request.contextPath}/tcoursesite/customAchievement?currpage=${pageModel.nextPage}&tCourseSiteId=${tCourseSite.id }&type=${type }">下一页</a>
 	          <a href="${pageContext.request.contextPath}/tcoursesite/customAchievement?currpage=${pageModel.lastPage}&tCourseSiteId=${tCourseSite.id }&type=${type }">末页</a>
              </div>
            </div>
            </c:if>
        </div>
    </div>
<script type="text/javascript">

    	$(".Lele").click(
            function () {
                $(".window_box").fadeIn(100)
            }
        );
        $(".close_icon").click(
            function () {
                $(".window_box").fadeOut(100)
            }
        );
        $(".module_tit").click(
            function () {
                $(this).siblings(".module_con").slideToggle(150)
            }
        )
        $(function () {
            $('textarea').autosize();
            //$('.animated').autosize();
        });
        $(".cm_list").not(".select").hover(
            function () {
                $(this).find("a").css("color", "#78B0FF")
            },
            function () {
                $(this).find("a").css("color", "#333")
            }
        )
        $(".tab li").click(
            function(){
                $(this).find(".wire_btn").addClass("bgc");
                 $(this).siblings().find(".wire_btn").removeClass("bgc")
                var i =$(this).index()
                //alert(i)
                $(".tab_list").eq(i).slideDown(150)
                $(".tab_list").eq(i).siblings(".tab_list").slideUp(150)
            }
        )
        
      //保存改动后的自定义成绩
        function saveEditCustomScore(id){
        	//判断不为空
        	for(var i=0;i<document.customForm.elements.length-1;i++){
                if(document.customForm.elements[i].value=="") {
                      alert("成绩不能为空");
                      document.customForm.elements[i].focus();
                      return false;
                   } 
              }
    	  
        	if ($('#userForm').form('validate')){
        		customForm.action = "${pageContext.request.contextPath}/tcoursesite/saveEditCustomScore?tCourseSiteId="+id+"&currpage="+${pageModel.currpage };
        		customForm.submit();  
            }
        }
      //弹出是否删除自定义成绩的窗口
    	function deleteCustomScore(customScoreId) {
    		if(confirm("是否确认删除？"))
    		   {
    			window.location = "${pageContext.request.contextPath}/tcoursesite/deleteCustomScore?tCourseSiteId="+ ${tCourseSite.id} +"&customScoreId=" +customScoreId+"&currpage="+${pageModel.currpage };
    		   }
    	}
</script>

</body>
</html>