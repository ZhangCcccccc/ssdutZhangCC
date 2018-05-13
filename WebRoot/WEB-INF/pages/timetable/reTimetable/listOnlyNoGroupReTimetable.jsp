<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我要排课</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/timetable/lmsReg.css">

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

//弹出排课界面的方法
function showTimetable(term,weekday,classids,labroom) {
    var sessionId=$("#sessionId").val();
    var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/doOnlyNoGroupSelectCourseCode?term=' + term + '&weekday='+weekday+'&classids='+classids+'&labroom=' +labroom + '" style="width:100%;height:100%;"></iframe>'
    $("#showTimetable").html(con);
    //获取当前屏幕的绝对位置
    var topPos = window.pageYOffset;
    //使得弹出框在屏幕顶端可见
    $('#showTimetable').window({left:"0px", top:topPos+"px"});  
    $("#showTimetable").window('open');
}
</script>
<script type="text/javascript">
$(function(){
    $("#showTimetable").window({
       
        top: 0,
        left:0, 
        onBeforeClose:function (){ //当面板关闭之前触发的事件
              if (confirm('窗口正在关闭，请确认您当前的操作已保存。\n 是否继续关闭窗口？')) {
                    window.location.reload();
                    $('#showTimetable').window('close', true); 
                    //这里调用close 方法，true 表示面板被关闭的时候忽略onBeforeClose 回调函数。
              }else{
                    return false;
              } 
              
         }   
    })
    $(".sit_maincontent").css('height',600);
})

//分组排课修改查看
function listTimetableGroup(term,weekday,classids,courseCode,labroom){
var sessionId=$("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/doIframeGroupReTimetable?term=' + term +'&weekday='+weekday+'&classids='+classids+'&courseCode='+courseCode+'&labroom='+labroom+'" style="width:100%;height:100%;"></iframe>'
$('#showTimetable').html(con);  
//获取当前屏幕的绝对位置
var topPos = window.pageYOffset;
//使得弹出框在屏幕顶端可见
$('#showTimetable').window({left:"0px", top:topPos+"px"});
$('#showTimetable').window('open');
}

//不分组排课修改查看
function listTimetableNoGroup(term,weekday,classids,courseCode,labroom){
var sessionId=$("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/timetable/doOnlyNoGroupReTimetable?term=' + term +'&weekday='+weekday+'&classids='+classids+'&flag=0&courseCode='+courseCode+'&labroom='+labroom+'" style="width:100%;height:100%;"></iframe>'
$('#showTimetable').html(con);  
//获取当前屏幕的绝对位置
var topPos = window.pageYOffset;
//使得弹出框在屏幕顶端可见
$('#showTimetable').window({left:"0px", top:topPos+"px"});
$('#showTimetable').window('open');
}
</script>
</head>

<body>
<div class="navigation">
<div id="navigation">
<ul>
	<li><a href="javascript:void(0)">排课管理</a></li>
	<li><a href="javascript:void(0)">二次排课（不含分批)</a></li>
	<li class="end"><a href="javascript:void(0)">查看选课日历</a></li>
</ul>
</div>
</div>
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
    <form action="${pageContext.request.contextPath}/timetable/listOnlyNoGroupReTimetable" method="get">
    <select id="term" name="term" >
       <c:forEach items="${schoolTerms}" var="current">
    	    <c:if test="${current.id == schoolTerm.id}">
    	       	<option value ="${current.id}" selected>${current.termName} </option>
    	    </c:if>
    	    <c:if test="${current.id != term}">
    	    	<option value ="${current.id}" >${current.termName} </option>
    	    </c:if>		
        </c:forEach>
    </select>
    <select id="labroom" size="1" name="labroom"  >
         <option value ="-1" selected>所有实验室</option>
    	<c:forEach items="${labRoomMap}" var="current"  varStatus="i">
    	    <c:if test="${current.key == labroom}">
    	       <option value ="${current.key}" selected>${current.value} </option>
    	    </c:if>
    	    <c:if test="${current.key != labroom}">
    	       <option value ="${current.key}" >${current.value} </option>
    	    </c:if>		
            
        </c:forEach>
    </select>
   <%--  <select id="courseNo" size="1" name="courseNo" >
    	<c:forEach items="${schoolCourses}" var="current"  varStatus="i">
    	     <option value ="${current.courseNo}" >${curr.courseNumber}:${curr.courseName} </option>
        </c:forEach>
    </select> --%>
    <input value="查询" type="submit">&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/timetable/listOnlyNoGroupReTimetable"><input type="button" value="取消查询"></a>
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

<table valign="center" cellpadding="5" cellspacing="0" align="center" width="100%" style="word-wrap:break-all">
<tbody>
<tr>
    <th class="tbh" >节次</th>
    <th id="weekdayName1" class="tbh" width="6%">实验室</th>
    <th id="weekdayName1" class="tbh" width="11%">星期一</th>
    <th id="weekdayName2" class="tbh" width="11%">星期二</th>
    <th id="weekdayName3" class="tbh" width="11%">星期三</th>
    <th id="weekdayName4" class="tbh" width="11%">星期四</th>
    <th id="weekdayName5" class="tbh" width="11%">星期五</th>
    <th id="weekdayName6" class="tbh" width="11%">星期六</th>
    <th id="weekdayName0" class="tbh" width="11%">星期日</th>
</tr>
<c:forEach var="class" varStatus="cStatus" begin="1" end="9">
    <c:forEach var="labroom" items="${labRoomMap}" varStatus="rStatus">
    <!-- 判断奇数偶数，以确定日历背景样式 -->
    <c:if test="${cStatus.count%2==0}">
    <tr>
    </c:if>
    <c:if test="${cStatus.count%2!=0}">
    <tr style="background:#FEFFDA"  >
    </c:if>
    
        <c:if test="${cStatus.index==1}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px;background:#FEFFDA"><span id="className12-13">第一节<br>~<br>第二节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==2}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px"><span id="className12-13">第三节<br>~<br>第四节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==3}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px;background:#FEFFDA"><span id="className12-13">第五节<br>~<br>第六节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==4}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px"><span id="className12-13">第七节<br>~<br>第八节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==5}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px;background:#FEFFDA"><span id="className12-13">第九节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==6}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px"><span id="className12-13">第十节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==7}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px;background:#FEFFDA"><span id="className12-13">第十一节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==8}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px"><span id="className12-13">第十二节</span></td>  
            </c:if>
        </c:if>
        <c:if test="${cStatus.index==9}">
            <c:if test="${rStatus.count==1}">
                <td class="tbl tbct" rowspan=${labRoomMap.size()} style="width:60px;background:#FEFFDA"><span id="className12-13">第十三节</span></td>  
            </c:if>
        </c:if>
        
        <td class="tb" valign="top"> 
          <b>
          <c:set var="labroomCount" value="1"></c:set>
          <c:forTokens items="${labroom.value}" delims="-" var="tech">
             <c:if test="${labroomCount==1}">
             ${tech}
             <c:set var="labroomCount" value="2"></c:set>
             </c:if>
          </c:forTokens>
          </b>
        </td>
      
        <c:forEach begin="1" end="7"  varStatus="iWeek">
        <td id="myId" class="tb" ondblclick="showTimetable('${schoolTerm.id }','${iWeek.count}','${cStatus.index}','${labroom.key}')" valign="top" style="word-wrap:break-all">
            <!-- 二次排课显示 -->
            <c:forEach var="ltimetable" items="${labTimetable}" varStatus="lStatus">
                 <c:if test="${labroom.key==ltimetable.labRoom.id}">
                    <c:if test="${ltimetable.timetableAppointment.weekday==iWeek.count&&ltimetable.timetableAppointment.timetableAppointmentSameNumbers.size()==0}">
                    <c:if test="${cStatus.index<=4}">
                        <c:if test="${ltimetable.timetableAppointment.startClass<=cStatus.index*2-1&&ltimetable.timetableAppointment.endClass>=cStatus.index*2-1||ltimetable.timetableAppointment.startClass>=cStatus.index*2&&ltimetable.timetableAppointment.endClass<=cStatus.index*2}">
                            <%-- <c:if test="${ltimetable.timetableAppointment.timetableStyle==5 }">
                            <font color=red><b>[${ltimetable.labRoom.labRoomNumber}&nbsp;${ltimetable.labRoom.labRoomName}]</b></font><br>
                            </c:if> --%>
                            <c:if test="${ltimetable.timetableAppointment.timetableStyle!=5&&ltimetable.timetableAppointment.timetableStyle!=6 }">
                            <!-- 不分批处理 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()==0 }">
                            <a href='javascript:void(0)' onclick='listTimetableNoGroup("${ltimetable.timetableAppointment.schoolCourse.schoolTerm.id}","${ltimetable.timetableAppointment.weekday}","1","${ltimetable.timetableAppointment.schoolCourse.courseCode}","-1")'>
                            <c:if test="${ltimetable.timetableAppointment.schoolCourse.state!=0 }">
                            <input type="button" value="${ltimetable.timetableAppointment.schoolCourse.courseCode}-${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}">
                            </c:if>
                            <c:if test="${ltimetable.timetableAppointment.schoolCourse.state==0 }">
                            <input type="button" style="background-color:red;" value="${ltimetable.timetableAppointment.schoolCourse.courseCode}-${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}">
                            </c:if>
                            </a>
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
                            <!--分批排课状况-->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()>0 }">
                             <c:if test="${ltimetable.timetableAppointment.status==1 }">
                             <b>分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==2 }">
                             <b>分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>分批排课未完成</b></font>
                             </c:if>
                            </c:if>
                            <!--不分批排课状况-->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()==0 }">
                             <!--教务排课  -->
                             <c:if test="${ltimetable.timetableAppointment.timetableStyle==1||ltimetable.timetableAppointment.timetableStyle==2 }">
                             <c:if test="${ltimetable.timetableAppointment.status==1 }">
                             <b>教务排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==2 }">
                             <b>教务排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>教务排课未完成</b></font>
                             </c:if>
                             </c:if>
                             <!--二次排课  -->
                             <c:if test="${ltimetable.timetableAppointment.timetableStyle==3||ltimetable.timetableAppointment.timetableStyle==4 }">
                             <c:if test="${ltimetable.timetableAppointment.status==1 }">
                             <b>不分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==2 }">
                             <b>不分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>不分批排课未完成</b></font>
                             </c:if>
                             </c:if>
                            </c:if><br>
                        </c:if>
                    </c:if>
                    <c:if test="${cStatus.index>4}">
                        <c:if test="${ltimetable.timetableAppointment.startClass<=cStatus.index+4&&ltimetable.timetableAppointment.endClass>=cStatus.index+4}">
                            <%-- <c:if test="${ltimetable.timetableAppointment.timetableStyle==5 }">
                            <font color=red><b>[${ltimetable.labRoom.labRoomNumber}&nbsp;${ltimetable.labRoom.labRoomName}]</b></font><br>
                            </c:if> --%>
                            <c:if test="${ltimetable.timetableAppointment.timetableStyle!=5 &&ltimetable.timetableAppointment.timetableStyle!=6 }">
                            
                            <!-- 分批处理 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()>0 }">
                            <input type="button" onclick="alert('请到二次分批排课模块处理二次分批排课业务！')" style="color:#00A600" value="${ltimetable.timetableAppointment.schoolCourse.courseCode}-${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}">
                            </c:if>
                             <!-- 不分批处理 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()==0 }">
                            <a href='javascript:void(0)' onclick='listTimetableNoGroup("${ltimetable.timetableAppointment.schoolCourse.schoolTerm.id}","${ltimetable.timetableAppointment.weekday}","1","${ltimetable.timetableAppointment.schoolCourse.courseCode}","-1")'>
                            ${ltimetable.timetableAppointment.schoolCourse.courseCode}<input type="button" value="${ltimetable.timetableAppointment.schoolCourse.courseCode}-${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}">
                            </a>
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

                             <!--分批排课状况-->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()>0 }">
                             <c:if test="${ltimetable.timetableAppointment.status==1 }">
                             <b>分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==2 }">
                             <b>分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>分批排课未完成</b></font>
                             </c:if>
                            </c:if>
                            <!--不分批排课状况-->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()==0 }">
                             <!--教务排课  -->
                             <c:if test="${ltimetable.timetableAppointment.timetableStyle==1||ltimetable.timetableAppointment.timetableStyle==2 }">
                             <c:if test="${ltimetable.timetableAppointment.status==1 }">
                             <b>教务排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==2 }">
                             <b>教务排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>教务排课未完成</b></font>
                             </c:if>
                             </c:if>
                             <!--二次排课  -->
                             <c:if test="${ltimetable.timetableAppointment.timetableStyle==3||ltimetable.timetableAppointment.timetableStyle==4 }">
                             <c:if test="${ltimetable.timetableAppointment.status==1 }">
                             <b>不分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==2 }">
                             <b>不分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>不分批排课未完成</b></font>
                             </c:if>
                             </c:if>
                            </c:if><br>
                        </c:if>
                    </c:if>
                    </c:if>
                 
                    <c:if test="${ltimetable.timetableAppointment.weekday==iWeek.count&&ltimetable.timetableAppointment.timetableAppointmentSameNumbers.size()>0}">
                    <c:if test="${cStatus.index<=4}">
                            <c:set var="sameStart" value="-"></c:set>
                            <c:forEach var="entry" items="${ltimetable.timetableAppointment.timetableAppointmentSameNumbers}"   varStatus="p"> 
                            <c:if test="${entry.startClass<=cStatus.index*2-1&&entry.endClass>=cStatus.index*2-1||entry.startClass>=cStatus.index*2&&entry.endClass<=cStatus.index*2}">
                        
                            <%-- <c:if test="${ltimetable.timetableAppointment.timetableStyle==5 }">
                            <font color=red><b>[${ltimetable.labRoom.labRoomNumber}&nbsp;${ltimetable.labRoom.labRoomName}]</b></font><br>
                            </c:if> --%>
                            <c:if test="${ltimetable.timetableAppointment.timetableStyle!=5 &&ltimetable.timetableAppointment.timetableStyle!=6 }">
                            <%-- <font color=green><b>[${ltimetable.labRoom.labRoomNumber}&nbsp;${ltimetable.labRoom.labRoomName}]</b></font><br>
                             --%>
                            <!-- 显示周次节次 -->
                            <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
                            <c:if test="${fn:indexOf(sameStart,v_param)<0}">
                            <!-- 分批处理 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()>0 }">
                            <input type="button" onclick="alert('请到二次分批排课模块处理二次分批排课业务！')" style="color:#00A600" value="${ltimetable.timetableAppointment.schoolCourse.courseCode}-${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}">
                            </c:if>
                             <!-- 不分批处理 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()==0 }">
                            <a href='javascript:void(0)' onclick='listTimetableNoGroup("${ltimetable.timetableAppointment.schoolCourse.schoolTerm.id}","${ltimetable.timetableAppointment.weekday}","1","${ltimetable.timetableAppointment.schoolCourse.courseCode}","-1")'>
                            <input type="button" value="${ltimetable.timetableAppointment.schoolCourse.courseCode}-${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}">
                            </a>
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
                             <!--分批排课状况-->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()>0 }">
                             <c:if test="${ltimetable.timetableAppointment.status==1 }">
                             <b>分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==2 }">
                             <b>分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>分批排课未完成</b></font>
                             </c:if>
                            </c:if>
                            <!--不分批排课状况-->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()==0 }">
                             <!--教务排课  -->
                             <c:if test="${ltimetable.timetableAppointment.timetableStyle==1||ltimetable.timetableAppointment.timetableStyle==2 }">
                             <c:if test="${ltimetable.timetableAppointment.status==1 }">
                             <b>教务排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==2 }">
                             <b>教务排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>教务排课未完成</b></font>
                             </c:if>
                             </c:if>
                             <!--二次排课  -->
                             <c:if test="${ltimetable.timetableAppointment.timetableStyle==3||ltimetable.timetableAppointment.timetableStyle==4 }">
                             <c:if test="${ltimetable.timetableAppointment.status==1 }">
                             <b>不分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==2 }">
                             <b>不分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>不分批排课未完成</b></font>
                             </c:if>
                             </c:if>
                            </c:if><br>
                            </c:if>
                        
                            </c:if>
                            </c:if>
                            </c:forEach>
                            </c:if>
                    <c:if test="${cStatus.index>4}">
                            <c:set var="sameStart" value="-"></c:set>
                            <c:forEach var="entry" items="${ltimetable.timetableAppointment.timetableAppointmentSameNumbers}"   varStatus="p"> 
                            <c:if test="${entry.startClass<=cStatus.index+4&&entry.endClass>=cStatus.index+4}">
                            <%-- <c:if test="${ltimetable.timetableAppointment.timetableStyle==5 }">
                            <font color=red><b>[${ltimetable.labRoom.labRoomNumber}&nbsp;${ltimetable.labRoom.labRoomName}]</b></font><br>
                            </c:if> --%>
                            <c:if test="${ltimetable.timetableAppointment.timetableStyle!=5&&ltimetable.timetableAppointment.timetableStyle!=6 }">
                            <%-- <font color=green><b>[${ltimetable.labRoom.labRoomNumber}&nbsp;${ltimetable.labRoom.labRoomName}]</b></font><br>
                             --%>
                            <!-- 显示周次节次 -->
                            <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
                            <c:if test="${fn:indexOf(sameStart,v_param)<0}">
                            <!-- 分批处理 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()>0 }">
                            <input type="button" onclick="alert('请到二次分批排课模块处理二次分批排课业务！')" style="color:#00A600" value="${ltimetable.timetableAppointment.schoolCourse.courseCode}-${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}">
                            </c:if>
                             <!-- 不分批处理 -->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()==0 }">
                            <a href='javascript:void(0)' onclick='listTimetableNoGroup("${ltimetable.timetableAppointment.schoolCourse.schoolTerm.id}","${ltimetable.timetableAppointment.weekday}","1","${ltimetable.timetableAppointment.schoolCourse.courseCode}","-1")'>
                            <input type="button" value="${ltimetable.timetableAppointment.schoolCourse.courseCode}-${ltimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}">
                            </a>
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
                            <!--分批排课状况-->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()>0 }">
                             <c:if test="${ltimetable.timetableAppointment.status==1 }">
                             <b>分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==2 }">
                             <b>分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>分批排课未完成</b></font>
                             </c:if>
                            </c:if>
                            <!--不分批排课状况-->
                            <c:if test="${ltimetable.timetableAppointment.timetableGroups.size()==0 }">
                             <!--教务排课  -->
                             <c:if test="${ltimetable.timetableAppointment.timetableStyle==1||ltimetable.timetableAppointment.timetableStyle==2 }">
                             <c:if test="${ltimetable.timetableAppointment.status==1 }">
                             <b>教务排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==2 }">
                             <b>教务排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>教务排课未完成</b></font>
                             </c:if>
                             </c:if>
                             <!--二次排课  -->
                             <c:if test="${ltimetable.timetableAppointment.timetableStyle==3||ltimetable.timetableAppointment.timetableStyle==4 }">
                             <c:if test="${ltimetable.timetableAppointment.status==1 }">
                             <b>不分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==2 }">
                             <b>不分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${ltimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>不分批排课未完成</b></font>
                             </c:if>
                             </c:if>
                            </c:if><br>
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
                            <%-- <c:if test="${lSelftimetable.timetableAppointment.timetableStyle==5 }">
                            <font color=red><b>[${lSelftimetable.labRoom.labRoomNumber}&nbsp;${lSelftimetable.labRoom.labRoomName}]</b></font><br>
                            </c:if> --%>
                            <c:if test="${lSelftimetable.timetableAppointment.timetableStyle==5||lSelftimetable.timetableAppointment.timetableStyle==6 }">
                            <!-- 不分批处理 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()==0 }">
                            <input type="button" onclick="alert('请到自主排课模块处理自主排课业务！')" style="color:#CC3333" value="${lSelftimetable.timetableAppointment.timetableSelfCourse.courseCode}${lSelftimetable.timetableAppointment.schoolCourseInfo.courseName}">
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
                            <!--分批排课状况-->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()>0 }">
                             <c:if test="${lSelftimetable.timetableAppointment.status==1 }">
                             <b>分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==2 }">
                             <b>分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>分批排课未完成</b></font>
                             </c:if>
                            </c:if>
                            <!--不分批排课状况-->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()==0 }">
                            
                             <!--二次排课  -->
                             <c:if test="${lSelftimetable.timetableAppointment.status==1 }">
                             <b>不分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==2 }">
                             <b>不分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>不分批排课未完成</b></font>
                             </c:if>

                            </c:if><br>
                        </c:if>
                    </c:if>
                    <c:if test="${cStatus.index>4}">
                        <c:if test="${lSelftimetable.timetableAppointment.startClass<=cStatus.index+4&&lSelftimetable.timetableAppointment.endClass>=cStatus.index+4}">
                            <%-- <c:if test="${lSelftimetable.timetableAppointment.timetableStyle==5 }">
                            <font color=red><b>[${lSelftimetable.labRoom.labRoomNumber}&nbsp;${lSelftimetable.labRoom.labRoomName}]</b></font><br>
                            </c:if> --%>
                            <c:if test="${lSelftimetable.timetableAppointment.timetableStyle==5 ||lSelftimetable.timetableAppointment.timetableStyle==6 }">
                            
                            <!-- 分批处理 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()>0 }">
                            <input type="button" onclick="alert('请到二次分批排课模块处理二次分批排课业务！')" style="color:#00A600" value="${lSelftimetable.timetableAppointment.schoolCourse.courseCode}-${lSelftimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}">
                            </c:if>
                             <!-- 不分批处理 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()==0 }">
                            <input type="button" onclick="alert('请到自主排课模块处理自主排课业务！')" style="color:#CC3333"  value="${lSelftimetable.timetableAppointment.schoolCourse.courseCode}-${lSelftimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}">
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

                             <!--分批排课状况-->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()>0 }">
                             <c:if test="${lSelftimetable.timetableAppointment.status==1 }">
                             <b>分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==2 }">
                             <b>分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>分批排课未完成</b></font>
                             </c:if>
                            </c:if>
                            <!--不分批排课状况-->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()==0 }">
                             <!--二次排课  -->
                             <c:if test="${lSelftimetable.timetableAppointment.timetableStyle==5||lSelftimetable.timetableAppointment.timetableStyle==6 }">
                             <c:if test="${lSelftimetable.timetableAppointment.status==1 }">
                             <b>不分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==2 }">
                             <b>不分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>不分批排课未完成</b></font>
                             </c:if>
                             </c:if>
                            </c:if><br>
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
                            <input type="button" onclick="alert('请到二次分批排课模块处理二次分批排课业务！')" style="color:#00A600" value="${lSelftimetable.timetableAppointment.timetableSelfCourse.courseCode}-${lSelftimetable.timetableAppointment.schoolCourseInfo.courseName}">
                            </c:if>
                             <!-- 不分批处理 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()==0 }">
                            <input type="button" onclick="alert('请到自主排课模块处理自主排课业务！')" style="color:#CC3333" value="${lSelftimetable.timetableAppointment.timetableSelfCourse.courseCode}-${lSelftimetable.timetableAppointment.schoolCourseInfo.courseName}">
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
                             <!--分批排课状况-->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()>0 }">
                             <c:if test="${lSelftimetable.timetableAppointment.status==1 }">
                             <b>分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==2 }">
                             <b>分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>分批排课未完成</b></font>
                             </c:if>
                            </c:if>
                            <!--不分批排课状况-->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()==0 }">
                             <!--二次排课  -->
                             <c:if test="${lSelftimetable.timetableAppointment.status==1 }">
                             <b>不分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==2 }">
                             <b>不分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>不分批排课未完成</b></font>
                             </c:if>
                            </c:if><br>
                            </c:if>
                        
                            </c:if>
                            </c:if>
                            </c:forEach>
                            </c:if>
                    <c:if test="${cStatus.index>4}">
                            <c:set var="sameStart" value="-"></c:set>
                            <c:forEach var="entry" items="${lSelftimetable.timetableAppointment.timetableAppointmentSameNumbers}"   varStatus="p"> 
                            <c:if test="${entry.startClass<=cStatus.index+4&&entry.endClass>=cStatus.index+4}">
                            <%-- <c:if test="${lSelftimetable.timetableAppointment.timetableStyle==5 }">
                            <font color=red><b>[${lSelftimetable.labRoom.labRoomNumber}&nbsp;${lSelftimetable.labRoom.labRoomName}]</b></font><br>
                            </c:if> --%>
                            <c:if test="${lSelftimetable.timetableAppointment.timetableStyle==5||lSelftimetable.timetableAppointment.timetableStyle==6 }">
                            <%-- <font color=green><b>[${lSelftimetable.labRoom.labRoomNumber}&nbsp;${lSelftimetable.labRoom.labRoomName}]</b></font><br>
                             --%>
                            <!-- 显示周次节次 -->
                            <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
                            <c:if test="${fn:indexOf(sameStart,v_param)<0}">
                            <!-- 分批处理 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()>0 }">
                            <input type="button" onclick="alert('请到二次分批排课模块处理二次分批排课业务！')" style="color:#00A600" value="${lSelftimetable.timetableAppointment.timetableSelfCourse.courseCode}-${lSelftimetable.timetableAppointment.schoolCourseInfo.courseName}">
                            </c:if>
                             <!-- 不分批处理 -->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()==0 }">
                            <input type="button" onclick="alert('请到自主排课模块处理自主排课业务！')" style="color:#CC3333" value="${lSelftimetable.timetableAppointment.schoolCourse.courseCode}-${lSelftimetable.timetableAppointment.schoolCourse.schoolCourseInfo.courseName}">
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
                            <!--分批排课状况-->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()>0 }">
                             <c:if test="${lSelftimetable.timetableAppointment.status==1 }">
                             <b>分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==2 }">
                             <b>分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>分批排课未完成</b></font>
                             </c:if>
                            </c:if>
                            <!--不分批排课状况-->
                            <c:if test="${lSelftimetable.timetableAppointment.timetableGroups.size()==0 }">
                             <!--二次排课  -->
                             <c:if test="${lSelftimetable.timetableAppointment.status==1 }">
                             <b>不分批排课已完成-已发布</b>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==2 }">
                             <b>不分批排课已完成-</b> <font color="red"><b>未发布</b></font>
                             </c:if>
                             <c:if test="${lSelftimetable.timetableAppointment.status==10 }">
                             <font color="red"><b>不分批排课未完成</b></font>
                             </c:if>
                            </c:if><br>
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
<!--//right maincontent -->
</div>

<sec:authorize ifAnyGranted="ROLE_TEACHER,ROLE_SUPERADMIN,ROLE_EXCENTERDIRECTOR">
<!--二次排课弹出窗口  -->
<div id="showTimetable" class="easyui-window panel-body panel-body-noborder window-body" title="二次排课（仅含不分批内容）" modal="true" dosize="true" maximizable="false" collapsible="true" minimizable="false" closed="true" iconcls="icon-add" style="width: 1000px; height: 650px;">
</div>
</sec:authorize>

</body>
</html>