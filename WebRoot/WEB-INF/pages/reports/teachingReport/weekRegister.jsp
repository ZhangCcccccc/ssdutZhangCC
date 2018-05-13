<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html >
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
      
      $('#myPrint').click(function(){
           $('#myShow').jqprint();
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
		document.form.action = "${pageContext.request.contextPath}/report/teachingReport/exportWeekRegister";
		document.form.submit();
	}
</script>
<style >
 br
 {mso-data-placement:same-cell;}
</style>
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
    <input value="查询" onclick="subform('${pageContext.request.contextPath}/report/teachingReport/weekRegister');" type="button" >&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/report/teachingReport/weekRegister"><input type="button" value="取消查询"></a>
     <input type="button" value="导出" onclick="exportExcel();">
    </form>
    </td>    
</tr>
</tbody>
</table>
<script>
if(!$('#term').val()){
    $.messager.alert(
        "提示",
        "<span style='font-size: 14px'>请先选择<font color=red>学期</font>,>学期为必选项.其它为可选项.</span>"            //showType: 'slide',
        //timeout: '4000'
    )
}
</script>

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
<c:forEach var="class" varStatus="cStatus" begin="1" end="9">  <%-- 循环9此代表左侧节次大单元格总格有9个 --%>
    <c:forEach var="labroom" items="${labRoomMap}" varStatus="rStatus">
    <tr>
        <c:if test="${cStatus.index==1}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px"><span id="className12-13">第一节<br>~<br>第二节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==2}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px"><span id="className12-13">第三节<br>~<br>第四节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==3}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px"><span id="className12-13">第五节<br>~<br>第六节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==4}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px"><span id="className12-13">第七节<br>~<br>第八节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==5}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px"><span id="className12-13">第九节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==6}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px"><span id="className12-13">第十节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==7}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px"><span id="className12-13">第十一节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==8}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px"><span id="className12-13">第十二节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==9}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px"><span id="className12-13">第十三节</span></td>  
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
                    <c:if test="${ltimetable.timetableAppointment.weekday==iWeek.count&&ltimetable.timetableAppointment.timetableAppointmentSameNumbers.size()==0}">
                    <c:if test="${cStatus.index<=4}">
                        <c:if test="${ltimetable.timetableAppointment.startClass<=cStatus.index*2-1&&ltimetable.timetableAppointment.endClass>=cStatus.index*2-1||ltimetable.timetableAppointment.startClass>=cStatus.index*2&&ltimetable.timetableAppointment.endClass<=cStatus.index*2}">
                            
                            <c:if test="${ltimetable.timetableAppointment.timetableStyle!=5 && ltimetable.timetableAppointment.timetableStyle!=6 }">
                            <!-- 不分批处理 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()==0 }">
                            <c:if test="${ltimetable.timetableAppointment.schoolCourse.state != 0}">
                            ${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}
                            </c:if>
                            <c:if test="${ltimetable.timetableAppointment.schoolCourse.state == 0}">
                            ${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}
                            </c:if>
                            </c:if>
                            </c:if>
                            <c:forEach var="tTimetable" items="${ltimetable.timetableAppointment.timetableTeacherRelateds}" varStatus="tStatus">
                            <br>${tTimetable.user.cname}
                            </c:forEach> &nbsp;
                             <!-- 显示周次节次 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableAppointmentSameNumbers.size()==0}">
                                <c:if test="${ltimetable.timetableAppointment.startClass==ltimetable.timetableAppointment.endClass}">
                                                                                                     节次：${ltimetable.timetableAppointment.startClass }
                                </c:if>
                                <c:if test="${ltimetable.timetableAppointment.startClass!=ltimetable.timetableAppointment.endClass}">
                                                                                                     节次：${ltimetable.timetableAppointment.startClass }-${ltimetable.timetableAppointment.endClass }
                                </c:if>
                                <c:if test="${ltimetable.timetableAppointment.startWeek==ltimetable.timetableAppointment.endWeek}">
                                 &nbsp;周次：${ltimetable.timetableAppointment.startWeek }
                                </c:if>
                                <c:if test="${ltimetable.timetableAppointment.startWeek!=ltimetable.timetableAppointment.endWeek}">
                                &nbsp;周次：${ltimetable.timetableAppointment.startWeek }-${ltimetable.timetableAppointment.endWeek }
                                </c:if>
                                <br>
                             </c:if>
                            <br>
                        </c:if>
                    </c:if>
                    <c:if test="${cStatus.index>4}">
                        <c:if test="${ltimetable.timetableAppointment.startClass<=cStatus.index+4&&ltimetable.timetableAppointment.endClass>=cStatus.index+4}">
                            
                            <c:if test="${ltimetable.timetableAppointment.timetableStyle!=5 &&ltimetable.timetableAppointment.timetableStyle!=6 }">
                            
                            <!-- 分批处理 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()>0 }">
                            ${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}
                            </c:if>
                             <!-- 不分批处理 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()==0 }">
                            ${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}
                            </c:if>
                            </c:if>
                            <c:forEach var="tTimetable" items="${ltimetable.timetableAppointment.timetableTeacherRelateds}" varStatus="tStatus">
                            <br>${tTimetable.user.cname}
                            </c:forEach> &nbsp;
                             <!-- 显示周次节次 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableAppointmentSameNumbers.size()==0}">
                                <c:if test="${ltimetable.timetableAppointment.startClass==ltimetable.timetableAppointment.endClass}">
                                                         节次：${ltimetable.timetableAppointment.startClass }
                                </c:if>
                                <c:if test="${ltimetable.timetableAppointment.startClass!=ltimetable.timetableAppointment.endClass}">
                                                         节次：${ltimetable.timetableAppointment.startClass }-${ltimetable.timetableAppointment.endClass }
                                </c:if>
                                <c:if test="${ltimetable.timetableAppointment.startWeek==ltimetable.timetableAppointment.endWeek}">
                                 &nbsp;周次：${ltimetable.timetableAppointment.startWeek }
                                </c:if>
                                <c:if test="${ltimetable.timetableAppointment.startWeek!=ltimetable.timetableAppointment.endWeek}">
                                &nbsp;周次：${ltimetable.timetableAppointment.startWeek }-${ltimetable.timetableAppointment.endWeek }
                                </c:if>
                                <br>
                             </c:if>
                             <br>
                        </c:if>
                    </c:if>
                    </c:if>
                 
                    <c:if test="${ltimetable.timetableAppointment.weekday==iWeek.count&&ltimetable.timetableAppointment.timetableAppointmentSameNumbers.size()>0}">
                    <c:if test="${cStatus.index<=4}">
                            <c:set var="sameStart" value="-"></c:set>
                            <c:forEach var="entry" items="${ltimetable.timetableAppointment.timetableAppointmentSameNumbers}"   varStatus="p"> 
                            <c:if test="${entry.startClass<=cStatus.index*2-1&&entry.endClass>=cStatus.index*2-1||entry.startClass>=cStatus.index*2&&entry.endClass<=cStatus.index*2}">
                        
                            <c:if test="${ltimetable.timetableAppointment.timetableStyle!=5 &&ltimetable.timetableAppointment.timetableStyle!=6 }">
                            
                            <!-- 显示周次节次 -->
                            <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
                            <c:if test="${fn:indexOf(sameStart,v_param)<0}">
                            <!-- 分批处理 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()>0 }">
                            ${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}
                            </c:if>
                             <!-- 不分批处理 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()==0 }">
                            ${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}
                            </c:if>
                               <c:forEach var="tTimetable" items="${ltimetable.timetableAppointment.timetableTeacherRelateds}" varStatus="tStatus">
                               <br>${tTimetable.user.cname}
                                </c:forEach> &nbsp;                                                                 
                                <c:if test="${entry.startClass==entry.endClass}">
                                                                                                         节次：${entry.startClass }
                                </c:if>
                                <c:if test="${entry.startClass!=entry.endClass}">
                                                                                                                          节次： ${entry.startClass }-${entry.endClass }
                                </c:if>
                            <br>
                            <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
                                                                                                  周次：<c:forEach var="entry1" items="${ltimetable.timetableAppointment.timetableAppointmentSameNumbers}"  varStatus="q">  
                                <c:if test="${entry.startClass==entry1.startClass}">
                                <%-- <td>
                                [${entry1.startClass }-${entry1.endClass }]
                                </td> --%>
                                <c:if test="${entry1.startWeek==entry1.endWeek}">
                                ${entry1.startWeek }
                                </c:if>
                                <c:if test="${entry1.startWeek!=entry1.endWeek}">
                                    ${entry1.startWeek }-${entry1.endWeek }
                                </c:if>
                                </c:if>
                            </c:forEach><br>
                            <br>
                            </c:if>
                        
                            </c:if>
                            </c:if>
                            </c:forEach>
                            </c:if>
                    <c:if test="${cStatus.index>4}">
                            <c:set var="sameStart" value="-"></c:set>
                            <c:forEach var="entry" items="${ltimetable.timetableAppointment.timetableAppointmentSameNumbers}"   varStatus="p"> 
                            <c:if test="${entry.startClass<=cStatus.index+4&&entry.endClass>=cStatus.index+4}">
                            
                            <c:if test="${ltimetable.timetableAppointment.timetableStyle!=5&&ltimetable.timetableAppointment.timetableStyle!=6 }">
                            
                            <!-- 显示周次节次 -->
                            <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
                            <c:if test="${fn:indexOf(sameStart,v_param)<0}">
                            <!-- 分批处理 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()>0 }">
                            ${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}
                            </c:if>
                             <!-- 不分批处理 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()==0 }">
                            ${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}
                            </c:if>
                               <c:forEach var="tTimetable" items="${ltimetable.timetableAppointment.timetableTeacherRelateds}" varStatus="tStatus">
                               <br>${tTimetable.user.cname}
                                </c:forEach> &nbsp;                                                                 
                                <c:if test="${entry.startClass==entry.endClass}">
                                                                                                         节次：${entry.startClass }
                                </c:if>
                                <c:if test="${entry.startClass!=entry.endClass}">
                                                                                                                          节次： ${entry.startClass }-${entry.endClass }
                                </c:if>
                            <br>
                            <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
                                                                                                  周次：<c:forEach var="entry1" items="${ltimetable.timetableAppointment.timetableAppointmentSameNumbers}"  varStatus="q">  
                                <c:if test="${entry.startClass==entry1.startClass}">
                                <%-- <td>
                                [${entry1.startClass }-${entry1.endClass }]
                                </td> --%>
                                <c:if test="${entry1.startWeek==entry1.endWeek}">
                                ${entry1.startWeek }
                                </c:if>
                                <c:if test="${entry1.startWeek!=entry1.endWeek}">
                                    ${entry1.startWeek }-${entry1.endWeek }
                                </c:if>
                                </c:if>
                            </c:forEach><br>
                            
                           <br>
                            </c:if>
                        
                            </c:if>
                            </c:if>
                            </c:forEach>
                    </c:if>
                    </c:if>
                    </c:if>
            </c:forEach>
            
            <!-- 自主排课显示 -->
            <c:forEach var="lSelftimetable" items="${labSelfTimetable}" varStatus="lStatus">
                 <c:if test="${labroom.key==lSelftimetable.labRoom.id}">
                    <c:if test="${lSelftimetable.timetableAppointment.weekday==iWeek.count&&lSelftimetable.timetableAppointment.timetableAppointmentSameNumbers.size()==0}">
                    <c:if test="${cStatus.index<=4}">
                        <c:if test="${lSelftimetable.timetableAppointment.startClass<=cStatus.index*2-1&&lSelftimetable.timetableAppointment.endClass>=cStatus.index*2-1||lSelftimetable.timetableAppointment.startClass>=cStatus.index*2&&lSelftimetable.timetableAppointment.endClass<=cStatus.index*2}">
                            
                            <c:if test="${lSelftimetable.timetableAppointment.timetableStyle==5||lSelftimetable.timetableAppointment.timetableStyle==6 }">
                            <!-- 不分批处理 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()==0 }">
                            ${lSelftimetable.timetableAppointment.timetableSelfCourse.schoolCourseInfo.courseName}
                            </c:if>
                            </c:if>
                            <c:forEach var="tTimetable" items="${lSelftimetable.timetableAppointment.timetableTeacherRelateds}" varStatus="tStatus">
                            <br>${tTimetable.user.cname}
                            </c:forEach> &nbsp;
                             <!-- 显示周次节次 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableAppointmentSameNumbers.size()==0}">
                                <c:if test="${lSelftimetable.timetableAppointment.startClass==lSelftimetable.timetableAppointment.endClass}">
                                                                                                     节次：${lSelftimetable.timetableAppointment.startClass }
                                </c:if>
                                <c:if test="${lSelftimetable.timetableAppointment.startClass!=lSelftimetable.timetableAppointment.endClass}">
                                                                                                     节次：${lSelftimetable.timetableAppointment.startClass }-${lSelftimetable.timetableAppointment.endClass }
                                </c:if>
                                <c:if test="${lSelftimetable.timetableAppointment.startWeek==lSelftimetable.timetableAppointment.endWeek}">
                                 &nbsp;周次：${lSelftimetable.timetableAppointment.startWeek }
                                </c:if>
                                <c:if test="${lSelftimetable.timetableAppointment.startWeek!=lSelftimetable.timetableAppointment.endWeek}">
                                &nbsp;周次：${lSelftimetable.timetableAppointment.startWeek }-${lSelftimetable.timetableAppointment.endWeek }
                                </c:if>
                                <br>
                             </c:if>
                            
                            <br>
                        </c:if>
                    </c:if>
                    <c:if test="${cStatus.index>4}">
                        <c:if test="${lSelftimetable.timetableAppointment.startClass<=cStatus.index+4&&lSelftimetable.timetableAppointment.endClass>=cStatus.index+4}">
                            
                            <c:if test="${lSelftimetable.timetableAppointment.timetableStyle==5 ||lSelftimetable.timetableAppointment.timetableStyle==6 }">
                            <!-- 分批处理 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()>0 }">
                            ${lSelftimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}
                            </c:if>
                             <!-- 不分批处理 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()==0 }">
                            ${lSelftimetable.timetableAppointment.timetableSelfCourse.schoolCourseInfo.courseName}
                            </c:if>
                            </c:if>
                            
                            <c:forEach var="tTimetable" items="${lSelftimetable.timetableAppointment.timetableTeacherRelateds}" varStatus="tStatus">
                            <br>${tTimetable.user.cname}
                            </c:forEach> &nbsp;
                             <!-- 显示周次节次 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableAppointmentSameNumbers.size()==0}">
                                <c:if test="${lSelftimetable.timetableAppointment.startClass==lSelftimetable.timetableAppointment.endClass}">
                                                         节次：${lSelftimetable.timetableAppointment.startClass }
                                </c:if>
                                <c:if test="${lSelftimetable.timetableAppointment.startClass!=lSelftimetable.timetableAppointment.endClass}">
                                                         节次：${lSelftimetable.timetableAppointment.startClass }-${lSelftimetable.timetableAppointment.endClass }
                                </c:if>
                                <c:if test="${lSelftimetable.timetableAppointment.startWeek==lSelftimetable.timetableAppointment.endWeek}">
                                 &nbsp;周次：${lSelftimetable.timetableAppointment.startWeek }
                                </c:if>
                                <c:if test="${lSelftimetable.timetableAppointment.startWeek!=lSelftimetable.timetableAppointment.endWeek}">
                                &nbsp;周次：${lSelftimetable.timetableAppointment.startWeek }-${lSelftimetable.timetableAppointment.endWeek }
                                </c:if>
                                <br>
                             </c:if>

                            <br>
                        </c:if>
                    </c:if>
                    </c:if>
                 
                    <c:if test="${lSelftimetable.timetableAppointment.weekday==iWeek.count&&lSelftimetable.timetableAppointment.timetableAppointmentSameNumbers.size()>0}">
                    <c:if test="${cStatus.index<=4}">
                            <c:set var="sameStart" value="-"></c:set>
                            <c:forEach var="entry" items="${lSelftimetable.timetableAppointment.timetableAppointmentSameNumbers}"   varStatus="p"> 
                            <c:if test="${entry.startClass<=cStatus.index*2-1&&entry.endClass>=cStatus.index*2-1||entry.startClass>=cStatus.index*2&&entry.endClass<=cStatus.index*2}">
                        
                            <%-- <c:if test="${lSelftimetable.timetableAppointment.timetableStyle==5 }">
                            <font color=red><b>[${lSelftimetable.labRoom.labRoomNumber}&nbsp;${lSelftimetable.labRoom.labRoomName}]</b></font><br>
                            </c:if> --%>
                            <c:if test="${lSelftimetable.timetableAppointment.timetableStyle==5 ||lSelftimetable.timetableAppointment.timetableStyle==6 }">
                            <%-- <font color=green><b>[${lSelftimetable.labRoom.labRoomNumber}&nbsp;${lSelftimetable.labRoom.labRoomName}]</b></font><br>
                             --%>
                            <!-- 显示周次节次 -->
                            <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
                            <c:if test="${fn:indexOf(sameStart,v_param)<0}">
                            <!-- 分批处理 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()>0 }">
                            ${lSelftimetable.timetableAppointment.schoolCourseInfo.courseName}
                            </c:if>
                             <!-- 不分批处理 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()==0 }">
                            ${lSelftimetable.timetableAppointment.timetableSelfCourse.schoolCourseInfo.courseName}
                            </c:if>
                               <c:forEach var="tTimetable" items="${lSelftimetable.timetableAppointment.timetableTeacherRelateds}" varStatus="tStatus">
                               <br>${tTimetable.user.cname}
                                </c:forEach> &nbsp;                                                                 
                                <c:if test="${entry.startClass==entry.endClass}">
                                                                                                         节次：${entry.startClass }
                                </c:if>
                                <c:if test="${entry.startClass!=entry.endClass}">
                                                                                                                          节次： ${entry.startClass }-${entry.endClass }
                                </c:if>
                            <br>
                            <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
                                                                                                  周次：<c:forEach var="entry1" items="${lSelftimetable.timetableAppointment.timetableAppointmentSameNumbers}"  varStatus="q">  
                                <c:if test="${entry.startClass==entry1.startClass}">
                                <%-- <td>
                                [${entry1.startClass }-${entry1.endClass }]
                                </td> --%>
                                <c:if test="${entry1.startWeek==entry1.endWeek}">
                                ${entry1.startWeek }
                                </c:if>
                                <c:if test="${entry1.startWeek!=entry1.endWeek}">
                                    ${entry1.startWeek }-${entry1.endWeek }
                                </c:if>
                                </c:if>
                            </c:forEach><br>
                             
                            <br>
                            </c:if>
                        
                            </c:if>
                            </c:if>
                            </c:forEach>
                            </c:if>
                    <c:if test="${cStatus.index>4}">
                            <c:set var="sameStart" value="-"></c:set>
                            <c:forEach var="entry" items="${lSelftimetable.timetableAppointment.timetableAppointmentSameNumbers}"   varStatus="p"> 
                            <c:if test="${entry.startClass<=cStatus.index+4&&entry.endClass>=cStatus.index+4}">
                            
                            <c:if test="${lSelftimetable.timetableAppointment.timetableStyle==5||lSelftimetable.timetableAppointment.timetableStyle==6 }">
                            
                            <!-- 显示周次节次 -->
                            <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
                            <c:if test="${fn:indexOf(sameStart,v_param)<0}">
                            <!-- 分批处理 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()>0 }">
                            ${lSelftimetable.timetableAppointment.schoolCourseInfo.courseName}
                            </c:if>
                             <!-- 不分批处理 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()==0 }">
                            ${lSelftimetable.timetableAppointment.timetableSelfCourse.schoolCourseInfo.courseName}
                            </c:if>
                               <c:forEach var="tTimetable" items="${lSelftimetable.timetableAppointment.timetableTeacherRelateds}" varStatus="tStatus">
                               <br>${tTimetable.user.cname}
                                </c:forEach> &nbsp;                                                                 
                                <c:if test="${entry.startClass==entry.endClass}">
                                                                                                         节次：${entry.startClass }
                                </c:if>
                                <c:if test="${entry.startClass!=entry.endClass}">
                                                                                                                          节次： ${entry.startClass }-${entry.endClass }
                                </c:if>
                            <br>
                            <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
                                                                                                  周次：<c:forEach var="entry1" items="${lSelftimetable.timetableAppointment.timetableAppointmentSameNumbers}"  varStatus="q">  
                                <c:if test="${entry.startClass==entry1.startClass}">
                                <%-- <td>
                                [${entry1.startClass }-${entry1.endClass }]
                                </td> --%>
                                <c:if test="${entry1.startWeek==entry1.endWeek}">
                                ${entry1.startWeek }
                                </c:if>
                                <c:if test="${entry1.startWeek!=entry1.endWeek}">
                                    ${entry1.startWeek }-${entry1.endWeek }
                                </c:if>
                                </c:if>
                            </c:forEach><br>
                            
                            <br>
                            </c:if>
                        
                            </c:if>
                            </c:if>
                            </c:forEach>
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

</body>
</html>