<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html>

<head>
    <title>GVSUN实验实训综合教学平台台</title>
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
	      $("#myPrint1").click(function(){	    	  
	      $("#my_show1").jqprint();	    
	      });
	});
	$(document).ready(function(){	 
	      $("#myPrint2").click(function(){	    	  
	      $("#my_show2").jqprint();	    
	      });
	});
	$(document).ready(function(){	 
	      $("#myPrint3").click(function(){	    	  
	      $("#my_show3").jqprint();	    
	      });
	});
	$(document).ready(function(){	 
	      $("#myPrint4").click(function(){	    	  
	      $("#my_show4").jqprint();	    
	      });
	});
	$(document).ready(function(){	 
	      $("#myPrint5").click(function(){	    	  
	      $("#my_show5").jqprint();	    
	      });
	});
	$(document).ready(function(){	 
	      $("#myPrint").click(function(){	    	  
	      $(".my_show").jqprint();	    
	      });
	});
</script>
            
</head>

<body>
    <div class="course_con ma back_gray">
        <div class="course_cont r back_gray">
        	<div class="notice_cont ">
            <div class="w100p cf">
                
            </div>
            </div>    
        
        	<ul class="tool_box tab cf rel zx2 pt5  pb10">
                <li class="rel l pb5">
                    <div class="wire_btn l ml30 mt10 poi bgc">
                        <i class="fa fa-bar-chart mr5"></i>实验成绩
                    </div>
                </li>
                <c:if test="${flag >0}">
                <li class="rel l pb5">
                    <div class="wire_btn l ml10 mt10 poi <c:if test="${type eq 'weight'}">bgc</c:if>">
                        <i class="fa fa-pie-chart mr5"></i>成绩权重
                    </div>
                </li>
                </c:if>
            </ul>
        
             <div class="tab_list f14 mb2 <c:if test="${type eq 'weight'}">hide</c:if>">
                <div class="lh40 bgg  pl30 f18 ">
                    <span class="bc">实验成绩</span>
                    <%-- <div class="h20 lh20 br3 plr10 r f12 bgb wh ml30 mt10 mr5 poi">
                        
                         <li><input type="button" value="打印" id="myPrint1"></li>                      
                    </div> --%>
                    
                </div>
                <div class="module_con  mtb20">                   
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14"><%--
                            <table class="w100p">
                                --%><table class="w100p"  id="my_show1">
                                <tr>
                                    <th class="w10p tl">姓名</th>
                                    <th class="tl">学号</th>
                                    <c:forEach items="${workAndTestAndReport}" var="current"  varStatus="i">
                                    <c:if test="${current.type=='exptest' }">
                                     <th class="tl">预习测试<br>${fn:substring(100*current.weight,0,fn:indexOf(100*current.weight, ".")) }%</th>
                                    </c:if>
                                    <c:if test="${current.type=='expreport' }">
                                     <th class="tl">实验报告<br>${fn:substring(100*current.weight,0,fn:indexOf(100*current.weight, ".")) }%</th>
                                    </c:if>
                                     <c:if test="${current.type=='expwork' }">
                                     <th class="tl">实验作业<br>${fn:substring(100*current.weight,0,fn:indexOf(100*current.weight, ".")) }%</th>
                                    </c:if>
                                    </c:forEach>
                                    <th class="tl">总成绩</th>
<!--                                    <th class="tl">最终成绩</th> -->
                                </tr>                             
                                <c:forEach items="${gradeLists}" var="current"  varStatus="i">
                                 <c:if test="${flag>0||(flag==0&&current[1] eq user.username)}">
                                <tr>                                 
                                	<c:forEach items="${current }" var="current1" varStatus="j">
                                    <td>${current1 }</td>
                                    </c:forEach>  
<!--                                     <td>${finalGradeLists[current[1]]}</td>                                 -->
                                </tr> 
                                 </c:if>
                                 </c:forEach>                                                   
                            </table>
                        </div>
                    </div>
                </div>
              <div class="page" >
    	        ${pageModel.totalRecords}条记录,共${pageModel.totalPage}页
              <a href="${pageContext.request.contextPath}/tcoursesite/skill/gradeExperimentSkill?currpage=1&tCourseSiteId=${tCourseSite.id }&skillId=${skillId }">首页</a>			    
	          <a href="${pageContext.request.contextPath}/tcoursesite/skill/gradeExperimentSkill?currpage=${pageModel.previousPage}&tCourseSiteId=${tCourseSite.id }&skillId=${skillId }">上一页</a>
	                                 第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;">
	          <option value="${pageContext.request.contextPath}/tcoursesite/skill/gradeExperimentSkill?currpage=${pageModel.currpage}&tCourseSiteId=${tCourseSite.id }&skillId=${skillId }">${pageModel.currpage}</option>
	          <c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
              <c:if test="${j.index!=pageModel.currpage}">
              <option value="${pageContext.request.contextPath}/tcoursesite/skill/gradeExperimentSkill?currpage=${j.index}&tCourseSiteId=${tCourseSite.id }&skillId=${skillId }">${j.index}</option>
              </c:if>
              </c:forEach></select>页
	          <a href="${pageContext.request.contextPath}/tcoursesite/skill/gradeExperimentSkill?currpage=${pageModel.nextPage}&tCourseSiteId=${tCourseSite.id }&skillId=${skillId }">下一页</a>
 	          <a href="${pageContext.request.contextPath}/tcoursesite/skill/gradeExperimentSkill?currpage=${pageModel.lastPage}&tCourseSiteId=${tCourseSite.id }&skillId=${skillId }">末页</a>
              </div>
            </div>
            
            <div class="tab_list f14 mb2 <c:if test="${type ne 'weight'}">hide</c:if>">
                <div class=" lh40 bgg  pl30 f18 ">
                    <span class="bc">成绩权重</span>
                   <%--  <div class="h20 lh20 br3 plr10 r f12 bgb wh ml30 mt10 mr5 poi">
                        <i class="fa fa-print mr5"></i>打印
                   <li><input type="button" value="打印" id="myPrint"></li> 
                    </div> --%>
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14">
						<%--实验作业成绩权重没有对应表可存（新建作业的时候没有生成对应数据），暂且引掉，目前实验作业总成绩为各作业总分的平均值 	
						    <form action="${pageContext.request.contextPath }/tcoursesite/?tCourseSiteId=${tCourseSite.id}" method="POST"  onsubmit="return checkForm(this)">
								<div class="content-box" style="width: 23%;float: left;text-align:center;">
									  <table  class="my_show2" style="width:100%">
											<thead>
											        <th style="display: block;border:none;color:#333;">实验作业成绩权重</th>
											    <tr>                   
											        <th>实验作业标题</th>
											        <th class="w36p ">权重</th>
											    </tr>
											</thead>
											<tbody>
												<c:forEach items="${workList }" var="current1" varStatus="i">
													<tr>
														<td>${current1.title }</td>
														<td><input name="weight2" type="text" style="width: 38px;" value="" class="easyui-numberbox b1 br3 h20 lh20 mt5 plr5" required="required" />%</td>
													</tr>
											    </c:forEach>
											</tbody>
										</table>
										<button style="border:none;" class="bbtn bgb f14 r mt10 poi tc br3 wh w80" type="submit">确定</button>
									<br>
								</div>
							</form> --%>
							<form action="${pageContext.request.contextPath }/tcoursesite/skill/saveWorkAndReportAndTestWeight?tCourseSiteId=${tCourseSite.id}&skillId=${skillId }" method="POST"  onsubmit="return checkForm(this)">
								<div class="content-box" style="width: 23%;float: left;text-align:center;">
										
										<%--<table  id="my_show" class="w100p ">
										--%><table  class="my_show" style="width:100%">
												
											<thead>
											        <th style="display: block;border:none;color:#333;">实验项目成绩权重</th>
											    <tr>                   
											        <th>标题</th>
											        <th class="w36p ">权重</th>
											    </tr>
											</thead>
											<tbody>
												<c:forEach items="${workAndTestAndReport}" var="current1" varStatus="i">
												<input type='hidden' value='${current1.id }' name='wid'/>
													<tr>
													<c:if test="${current1.type=='expreport' }">
													<td>实验报告</td>
													<td><input name="weight" type="text" style="width: 38px;" value="${current1.weight*100 }" class="easyui-numberbox b1 br3 h20 lh20 mt5 plr5" required="required" />%</td>
													</c:if>
													<c:if test="${current1.type=='exptest' }">
													<td>预习测试</td>
													<td><input name="weight" type="text" style="width: 38px;" value="${current1.weight*100 }" class="easyui-numberbox b1 br3 h20 lh20 mt5 plr5" required="required" />%</td>
													</c:if>
													<c:if test="${current1.type=='expwork' }">
													<td>实验作业</td>
													<td><input name="weight" type="text" style="width: 38px;" value="${current1.weight*100 }" class="easyui-numberbox b1 br3 h20 lh20 mt5 plr5" required="required" />%</td>
													</c:if>
													</tr>
											</c:forEach>
										   </tbody>
										 </table>
									  <button style="border:none;" class="bbtn bgb f14 r mt10 poi tc br3 wh w80" type="submit">确定</button>
									<br>
								</div>
							</form>
							
                        </div>
                    </div>
                </div>
            </div>
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
    </script>


</body>

</html>