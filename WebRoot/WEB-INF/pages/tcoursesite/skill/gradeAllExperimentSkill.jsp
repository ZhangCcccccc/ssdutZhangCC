<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html>

<head>
    <title>上海交通大学电工电子实验教学中心 云实验教学基础平台台</title>
    <meta name="decorator" content="course">
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
    <link href="${pageContext.request.contextPath}/css/tCourseSite/skill/experiment.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/skill/picChange.js"></script>
	

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
            
</head>

<body>
    <div class="course_con ma back_gray">
        <div class="course_cont r back_gray">
        	<div class="course_infobox">
                <h4 class="course_infobox_title">
                    <span>${tCourseSite.title}</span>
                    <input onclick="addExperimentSkillProfile('${tCourseSite.experimentSkillProfile}')" type="button" class="r ml10" value="编辑概要"/>
                    <%-- <input onclick="window.location.href='${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSite.id}&moduleType=2&selectId=-1'" type="button" class="r" value="返回技能"/> --%>
                </h4>
                
                <div class="course_infobox_profile">
                	<p>${tCourseSite.experimentSkillProfile}</p>
                </div>
                <div class="course_schedule">
               		<c:forEach begin="1" end="${skillDoList[0]}" step="1" varStatus="j" var="current">	
               			<div class="bggreen"></div>
                   	</c:forEach>
                   	<c:forEach begin="1" end="${skillDoList[1]}" step="1" varStatus="j" var="current">	
               			<div class="bgb"></div>
                   	</c:forEach>
                   	<c:forEach begin="1" end="${skillDoList[2]}" step="1" varStatus="j" var="current">	
               			<div class="bgg"></div>
                   	</c:forEach>
                    <p class="f16">( ${skillDoList[0]}/${fn:length(tExperimentSkills)})</p>
                </div>
            </div>
            <div class="course_nav">
                <div class="r lab_item_ctrl">
                    <a class="btn_back" href="${pageContext.request.contextPath}/tcoursesite/skill/experimentSkillsList?tCourseSiteId=${tCourseSite.id}">返回实验项目列表</a>
                </div>
            </div>
        	<div class="notice_cont ">
            <div class="w100p cf">
                
            </div>
            </div>    
        
        
        
            <div class="tab_list f14 mb2 <c:if test="${type eq 'weight'}">hide</c:if>">
                <div class="lh40 bgg  pl30 f18 ">
                    <span class="bc">实验成绩</span>
                    <div class="h20 lh20 br3 plr10 r f12 bgb wh ml30 mt10 mr5 poi">
                         <li><input type="button" value="打印" id="myPrint"></li>                  
                    </div>
                    
                </div>
                <div class="module_con  mtb20">                   
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14"><%--
                            <table class="w100p">
                                --%><table class="w100p"  id="my_show">
                                <tr>
                                    <th class="w10p tl">姓名</th>
                                    <th class="tl">学号</th>
                                    <c:forEach items="${tExperimentSkills}" var="current"  varStatus="i">
                                    <th class="tl">${current.experimentName}<br>${fn:substring(100*current.weight,0,fn:indexOf(100*current.weight, ".")) }%</th>
                                    </c:forEach>
                                    <th class="tl">总成绩</th>
                                </tr>                             
                                <c:forEach items="${sumLists}" var="current"  varStatus="i">
                                 <c:if test="${flag>0||(flag==0&&current[1] eq user.username)}">
                                <tr>                                 
                                	<c:forEach items="${current }" var="current1" varStatus="j">
                                    <td>${current1 }</td>
                                    </c:forEach>  
                                </tr> 
                                 </c:if>
                                 </c:forEach>                                                   
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            
              <div class="page" >
   	          ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
              <a href="${pageContext.request.contextPath}/tcoursesite/skill/gradeAllExperimentSkill?currpage=1&tCourseSiteId=${tCourseSite.id }">首页</a>			    
	          <a href="${pageContext.request.contextPath}/tcoursesite/skill/gradeAllExperimentSkill?currpage=${pageModel.previousPage}&tCourseSiteId=${tCourseSite.id }">上一页</a>
	                                 第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	          <option value="${pageContext.request.contextPath}/tcoursesite/skill/gradeAllExperimentSkill?currpage=${pageModel.currpage}&tCourseSiteId=${tCourseSite.id }">${pageModel.currpage}</option>
	          <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
              <c:if test="${j.index!=pageModel.currpage}">
              <option value="${pageContext.request.contextPath}/tcoursesite/skill/gradeAllExperimentSkill?currpage=${j.index}&tCourseSiteId=${tCourseSite.id }">${j.index}</option>
              </c:if>
              </c:forEach></select>页
	          <a href="${pageContext.request.contextPath}/tcoursesite/skill/gradeAllExperimentSkill?currpage=${pageModel.nextPage}&tCourseSiteId=${tCourseSite.id }">下一页</a>
 	          <a href="${pageContext.request.contextPath}/tcoursesite/skill/gradeAllExperimentSkill?currpage=${pageModel.lastPage}&tCourseSiteId=${tCourseSite.id }">末页</a>
              </div>
          
        </div>
    </div>
    
    <%-- 创建文件夹窗口--%>
<div class="window_box hide fix zx2 " id="addExperimentSkillProfile">
    <div class="window_con bgw b1 br3 rel bs10 ">
        <span class="close_icon f16 abs b1 br3 bs5 poi h20 w20 lh20">x</span>
        <div class="add_tit p20 bb f16" id="folderDivName">编辑实验技能概要</div>
        <form:form action="${pageContext.request.contextPath}/tcoursesite/skill/saveExperimentSkillProfile?tCourseSiteId=${tCourseSite.id}&moduleType=${moduleType }&selectId=${selectId}" method="POST">
	        <div class="add_con p20">
	            <div class="add_module cf f14">
	                <div class="cf w100p  mt10 mb20">
	                    <div class="lh25">概要</div>
	                    <div class="w100p">
	                        <input class="w98p lh25 br3 b1 plr5" type="text"  name="experimentSkillProfile" id="experimentSkillProfile"/>
	                    </div>
	                </div>
	            </div>
	            <div class="cf tc">
	                <input  type="submit" class="btn bgb l mt10 poi  plr20 br3 wh" value="保存"/>
	                <div class="btn close_icon bgc l ml30 mt10 poi plr20 br3">取消</div>
	            </div>
	        </div>
        </form:form>
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
        function saveFinalGrade(id){
        	//window.location="${pageContext.request.contextPath}/tcoursesite/saveFinalGrade?tCourseSiteId="+id;
        	frm.action = "${pageContext.request.contextPath}/tcoursesite/saveFinalGrade?tCourseSiteId="+id;
        }
        function importFinalGrade(id){
        	frm.action = "${pageContext.request.contextPath}/tcoursesite/exportFinalGrade?tCourseSiteId="+id;
        }
        function checkForm(obj){
    	var total = 0;
    	$(obj).find("input[type='text']").each(function(){
			total += Number($(this).val());    	
    	});
    	if (total!=100) {
			if (confirm("当前权重之和不为1，是否确认？")) {
				return true;
			}else {
				return false;
			}
		}
		return true;
    }
        
      //添加实验技能描述
        function addExperimentSkillProfile(profile){
        	$("#experimentSkillProfile").val(profile);
        	$("#addExperimentSkillProfile").fadeIn(100);//新增文件夹框弹出
        }
    </script>


</body>

</html>