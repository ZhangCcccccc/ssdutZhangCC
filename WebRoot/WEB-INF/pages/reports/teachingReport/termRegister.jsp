<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学期登记</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/timetable/lmsReg.css">

<!-- 打印开始 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<!-- 打印结束 -->

<!-- 打印、导出组件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/LodopFuncs.js"></script>

<style type="text/css">
	table{width:100%;}
	.array{width:100%;
		word-break:break-all;}
</style>
<script type="text/javascript">
$(document).ready(function(){
      $('#fullview').click(function(){
           $('.sit_sider_bar').animate( { width:'0'}, 500 );
           $('.sit_maincontent').animate( { width:'100%'}, 500 );
           $('.toggle,.toggleLink,#fullview,.sit_footer,.sit_sider_bar > h3').css("display","none");
           $('#fullview1').css("display","inline");
      });
  
      $('#fullview1').click(function(){
           $('.sit_sider_bar').animate( { width:'23%'}, 500 );
           $('.sit_maincontent').animate( { width:'75%'}, 500 );
           $('#fullview1').css("display","none");
           $('.toggle,#fullview,.toggleLink,.sit_footer,.sit_sider_bar > h3 ').css("display","inline");
      });
      
});
                              
$(function(){
      var height = $(document).height();
      $(".sit_sider_bar").css('height',height);
      $(".sit_maincontent").css('height',height);
}) ;

</script>
<script type="text/javascript">
$(function(){
    $("#showTimetable").window({
        top: ($(window).width() - 800) * 0.5 ,
        left: ($(window).width() - 1000) * 0.5   
    })
    $(".sit_maincontent").css('height',600);
})
</script>
<script type="text/javascript">
				//如果为查询则提交查询页面，如果为电子表格导出，则导出excel
					function subform(gourl){ 
					 var gourl;
					 form.action=gourl;
					 form.submit();
					}
					
	//导出excel
	function exportExcel()
	{
		document.form.action = "${pageContext.request.contextPath}/report/teachingReport/exportTermRegister";
		document.form.submit();
	}
</script>
</head>

<body>

<!--userPermission -->
<div class="sit_module width_quarter" style="position: absolute;height: 500px;margin-top: 0px;display: none">
    <div class="sit_title">
        <h3 class="tabs_involved">权限列表</h3>  
    </div>
    <div class="message_list" style="height: 450px">
        <div style="border: 0px;margin:0px auto;width:98%;height:90%;">
        </div>
    </div>
</div>
<!-- right maincontent -->
<div class="sit_maincontent" style="width: 99%; height: 800px;">

<table border="0" cellpadding="5" cellspacing="0" bgcolor="#F3EFEE" height="30" width="100%">
<tbody>
<tr>
    <td>
    <form name="form" action="" method="post" modelAttribute="schoolTerm">
    <form:select id="term" path="schoolTerm.id">
      <c:forEach items="${schoolTerms}" var="current">
        <form:option value="${current.id}">${current.termName}</form:option>
      </c:forEach>
    </form:select>
    
    <form:select path="schoolTerm.termCode">
    	<form:option value="">请选择</form:option>
    	<form:options items="${weeksMap}"/>
    </form:select>
    
    <input value="查询" onclick="subform('${pageContext.request.contextPath}/report/teachingReport/termRegister');" type="button">&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/report/teachingReport/termRegister"><input type="button" value="取消查询"></a>
    <a href=#><input type="button" onclick="exportExcel();" value="导出"></a>
    </form>
    </td>    
</tr>
</tbody>
</table>

<script type="text/javascript">
function WordWrap(textlength, id){
var obj=document.getElementById(id);
var strText=obj.innerHTML;
var tem="";
while(strText.length>textlength){
tem+=strText.substr(0,textlength)+"<br/>";
strText=strText.substr(textlength,strText.length);
}
tem+= strText;
obj.innerHTML=tem;
}
</script>
<div id="myShow">
<table valign="center" cellpadding="5" cellspacing="0" align="center" width="100%" style="word-wrap:break-all">
<tbody>
<tr>
    <th class="tbh" width="10%">节次</th>
    <th id="weekdayName1" class="tbh" width="6%">实验室</th>
    <th id="weekdayName1" class="tbh" width="11%">星期一</th>
    <th id="weekdayName2" class="tbh" width="11%">星期二</th>
    <th id="weekdayName3" class="tbh" width="11%">星期三</th>
    <th id="weekdayName4" class="tbh" width="11%">星期四</th>
    <th id="weekdayName5" class="tbh" width="11%">星期五</th>
    <th id="weekdayName6" class="tbh" width="11%">星期六</th>
    <th id="weekdayName0" class="tbh" width="11%">星期日</th>
</tr>

<c:set var="row_span" value="${labRoomMap.size()}"></c:set>

<c:forEach var="class" varStatus="cStatus" begin="1" end="5">  <%-- 循环9此代表左侧节次大单元格总格有9个 --%>
    <c:forEach var="labroom" items="${labRoomMap}" varStatus="rStatus">
    <tr>
        <c:if test="${cStatus.index==1}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan="${row_span}" style="width:60px"><span id="className12-13">第一节<br>~<br>第二节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==2}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan="${row_span}" style="width:60px"><span id="className12-13">第三节<br>~<br>第四节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==3}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan="${row_span}" style="width:60px"><span id="className12-13">第五节<br>~<br>第六节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==4}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan="${row_span}" style="width:60px"><span id="className12-13">第七节<br>~<br>第八节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==5}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan="${row_span}" style="width:60px"><span id="className12-13">晚</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==6}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan="${row_span}" style="width:60px"><span id="className12-13">第十节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==7}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan="${row_span}" style="width:60px"><span id="className12-13">第十一节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==8}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan="${row_span}" style="width:60px"><span id="className12-13">第十二节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==9}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan="${row_span}" style="width:60px"><span id="className12-13">第十三节</span></td>  
            </c:if>
        </c:if>
        
        <td class="tb" valign="top"> <%-- 实验室显示 --%>
		  <b>
		  <c:set var="labroomCount" value="1"></c:set>
		  <c:forTokens items="${labroom.value}" delims="-" var="tech">
		     <c:if test="${labroomCount==1}">  <%-- 去掉if条件可以显示实验室名称 --%>
		     ${tech}
		     <c:set var="labroomCount" value="2"></c:set>
		     </c:if>
		  </c:forTokens>
		  </b>
		</td>
        
        <c:forEach begin="1" end="7" varStatus="iWeek">
        <td id="myId" class="tb" valign="top" style="word-wrap:break-all">
        	<!-- 二次排课显示 -->
        	<c:forEach var="ltimetable" items="${labTimetable}" varStatus="lStatus">
                 <c:if test="${labroom.key==ltimetable.labRoom.id}">
                    <c:if test="${ltimetable.timetableAppointment.weekday==iWeek.count}">
                    <c:if test="${cStatus.index<=4}">
                        <c:if test="${ltimetable.timetableAppointment.startClass<=cStatus.index*2-1&&ltimetable.timetableAppointment.endClass>=cStatus.index*2-1||ltimetable.timetableAppointment.startClass>=cStatus.index*2&&ltimetable.timetableAppointment.endClass<=cStatus.index*2}">
                            <!--timetableStyle排课方式  -->
                            <!-- 不分批处理 -->
                            ${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}
                            <c:forEach var="tTimetable" items="${ltimetable.timetableAppointment.timetableTeacherRelateds}">
                            <br>${tTimetable.user.cname}
                            </c:forEach> &nbsp;
                             <!-- 显示周次节次 -->
                                <c:if test="${ltimetable.timetableAppointment.startWeek==ltimetable.timetableAppointment.endWeek}">
                                 &nbsp;周次：${ltimetable.timetableAppointment.startWeek }
                                </c:if>
                                <c:if test="${ltimetable.timetableAppointment.startWeek!=ltimetable.timetableAppointment.endWeek}">
                                &nbsp;周次：${ltimetable.timetableAppointment.startWeek }-${ltimetable.timetableAppointment.endWeek }
                                </c:if>
                                <br>
                            <br>
                        </c:if>
                    </c:if>
                    <c:if test="${cStatus.index>4}">
                        <c:if test="${ltimetable.timetableAppointment.startClass<=9&&ltimetable.timetableAppointment.endClass>=9}">
                            
                            ${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}
                            <c:forEach var="tTimetable" items="${ltimetable.timetableAppointment.timetableTeacherRelateds}">
                            <br>${tTimetable.user.cname}
                            </c:forEach> &nbsp;
                             <!-- 显示周次节次 -->
                                <c:if test="${ltimetable.timetableAppointment.startWeek==ltimetable.timetableAppointment.endWeek}">
                                 &nbsp;周次：${ltimetable.timetableAppointment.startWeek }
                                </c:if>
                                <c:if test="${ltimetable.timetableAppointment.startWeek!=ltimetable.timetableAppointment.endWeek}">
                                &nbsp;周次：${ltimetable.timetableAppointment.startWeek }-${ltimetable.timetableAppointment.endWeek }
                                </c:if>
                                <br>
                             <br>
                        </c:if>
                    </c:if>
                    </c:if>
                 
                    </c:if>
            </c:forEach>
            
            <!-- 自主排课显示 -->
            <c:forEach var="lSelftimetable" items="${labSelfTimetable}" varStatus="lStatus">
                 <c:if test="${labroom.key==lSelftimetable.labRoom.id}">
                    <c:if test="${lSelftimetable.timetableAppointment.weekday==iWeek.count}">
                    <c:if test="${cStatus.index<=4}">
                        <c:if test="${lSelftimetable.timetableAppointment.startClass<=cStatus.index*2-1&&lSelftimetable.timetableAppointment.endClass>=cStatus.index*2-1||lSelftimetable.timetableAppointment.startClass>=cStatus.index*2&&lSelftimetable.timetableAppointment.endClass<=cStatus.index*2}">
                            <!-- 不分批处理 -->
                            ${lSelftimetable.timetableAppointment.timetableSelfCourse.schoolCourseInfo.courseName}
                            <c:forEach var="tTimetable" items="${lSelftimetable.timetableAppointment.timetableTeacherRelateds}" varStatus="tStatus">
                            <br>${tTimetable.user.cname}
                            </c:forEach> &nbsp;
                             <!-- 显示周次节次 -->
                                <c:if test="${lSelftimetable.timetableAppointment.startWeek==lSelftimetable.timetableAppointment.endWeek}">
                                 &nbsp;周次：${lSelftimetable.timetableAppointment.startWeek }
                                </c:if>
                                <c:if test="${lSelftimetable.timetableAppointment.startWeek!=lSelftimetable.timetableAppointment.endWeek}">
                                &nbsp;周次：${lSelftimetable.timetableAppointment.startWeek }-${lSelftimetable.timetableAppointment.endWeek }
                                </c:if>
                                <br>
                            <br>
                        </c:if>
                    </c:if>
                    <c:if test="${cStatus.index>4}">
                        <c:if test="${lSelftimetable.timetableAppointment.startClass<=9&&lSelftimetable.timetableAppointment.endClass>=9}">
                            ${lSelftimetable.timetableAppointment.timetableSelfCourse.schoolCourseInfo.courseName}
                            <c:forEach var="tTimetable" items="${lSelftimetable.timetableAppointment.timetableTeacherRelateds}" varStatus="tStatus">
                            <br>${tTimetable.user.cname}
                            </c:forEach> &nbsp;
                             <!-- 显示周次节次 -->
                                <c:if test="${lSelftimetable.timetableAppointment.startWeek==lSelftimetable.timetableAppointment.endWeek}">
                                 &nbsp;周次：${lSelftimetable.timetableAppointment.startWeek }
                                </c:if>
                                <c:if test="${lSelftimetable.timetableAppointment.startWeek!=lSelftimetable.timetableAppointment.endWeek}">
                                &nbsp;周次：${lSelftimetable.timetableAppointment.startWeek }-${lSelftimetable.timetableAppointment.endWeek }
                                </c:if>
                                <br>
                            <br>
                        </c:if>
                    </c:if>
                    </c:if>
                    </c:if>
            </c:forEach>
        </td>
        </c:forEach>
        
    </tr> 
    </c:forEach>
</c:forEach>
</tbody>
</table>
</div>
<!--//right maincontent -->
</div>
<script type="text/javascript">
	var LODOP; //声明为全局变量 
	//导出excel文件  
	function SaveAsFile(){ 
		LODOP=getLodop();   
		LODOP.PRINT_INIT(""); 
		LODOP.ADD_PRINT_TABLE(0,0,"100%","100%",$("#myShow").html()); 
		LODOP.SET_SAVE_MODE("Orientation",2); //Excel文件的页面设置：横向打印   1-纵向,2-横向;
		LODOP.SET_SAVE_MODE("PaperSize",9);  //Excel文件的页面设置：纸张大小   9-对应A4
		LODOP.SET_SAVE_MODE("Zoom",90);       //Excel文件的页面设置：缩放比例
		LODOP.SET_SAVE_MODE("CenterHorizontally",true);//Excel文件的页面设置：页面水平居中
		LODOP.SET_SAVE_MODE("CenterVertically",true); //Excel文件的页面设置：页面垂直居中
//		LODOP.SET_SAVE_MODE("QUICK_SAVE",true);//快速生成（无表格样式,数据量较大时或许用到） 
		LODOP.SET_SHOW_MODE("NP_NO_RESULT",true);  //解决chrome弹出框问题
		LODOP.SAVE_TO_FILE("学期报表.xls"); 
	};
</script>
</body>
</html>