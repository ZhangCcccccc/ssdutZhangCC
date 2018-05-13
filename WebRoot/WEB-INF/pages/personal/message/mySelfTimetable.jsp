<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.equipmentlend-resources" />

<!doctype html>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/SpryTabbedPanels.js">
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/table-head.js"></script>

    <meta name="decorator" content="iframe" />
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
    
    <!-- 表头冻结[start] -->
    <script type="text/javascript">
    	$(document).ready(function(e) {
    	   var tablefix = new iStyle_TableFix({
    	   	操作对象 : '.content_box',
    	   	隐藏原有元素 : true,
    	   	左侧悬浮 : false,	
    	   	行合并模式 : true, //在表格有跨行合并的单元格时启用，此模式IE下运行速度较慢
    	   	左侧悬浮列数 : 2,
    	   	左悬浮id : 'fixedleft', 
    	   	位移修正 : -4,
    	   	顶部悬浮 : true,
    	   	顶部悬浮行数 : 1,
    	   	顶部悬浮id : 'fixedtop',
    	   	顶部位置修正 : -5,
    	   	顶部宽度自适应 : true
    	   	});
    	   });
    </script>
    <style>
    	.tb th{
    	background:#EBE5FF;  /*表头背景色*/
    	}
    	.content_box td{border-left:1px dotted #ccc}
    	#fixedtop th{height:36px;
    		lien-height:36px;
    		border-left:1px dotted #ccc
    		}
    </style>
    <!-- 表头冻结[end] -->
</head>

<body>
<div class="right-content">
<div class="content-box">
<div>
<div class="content-double">
<div class="title">我的课表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;${cname}--${term}</div>
<div class="content_box">
    <table  width="100%" class="tb">
    <thead>
    <tr>
        <th class="tbh" width="10%">节次</th>
        <th id="weekdayName1" class="tbh" width="12%">星期一</th>
        <th id="weekdayName2" class="tbh" width="12%">星期二</th>
        <th id="weekdayName3" class="tbh" width="12%">星期三</th>
        <th id="weekdayName4" class="tbh" width="12%">星期四</th>
        <th id="weekdayName5" class="tbh" width="12%">星期五</th>
        <th id="weekdayName6" class="tbh" width="12%">星期六</th>
        <th id="weekdayName0" class="tbh" width="12%">星期日</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="academyCourseCode" value=""></c:set>
    <c:set var="commonLab" value=""></c:set>
    <c:set var="selfCourseCode" value=""></c:set> 
    <c:forEach var="class" varStatus="cStatus" begin="1" end="12">
        <tr>
            <c:if test="${cStatus.index==1}">
               <td class="tbl tbct"  style="width:60px"><span id="className12-13">1</span></td>  
            </c:if>
            <c:if test="${cStatus.index==2}">
               <td class="tbl tbct"  style="width:60px"><span id="className12-13">2</span></td>  
            </c:if>
            <c:if test="${cStatus.index==3}">
               <td class="tbl tbct"  style="width:60px"><span id="className12-13">3</span></td>  
            </c:if>
            <c:if test="${cStatus.index==4}">
               <td class="tbl tbct"  style="width:60px"><span id="className12-13">4</span></td>  
            </c:if>
            <c:if test="${cStatus.index==5}">
               <td class="tbl tbct"  style="width:60px"><span id="className12-13">5</span></td>  
            </c:if>
            <c:if test="${cStatus.index==6}">
               <td class="tbl tbct"  style="width:60px"><span id="className12-13">6</span></td>  
            </c:if>
            <c:if test="${cStatus.index==7}">
               <td class="tbl tbct"  style="width:60px"><span id="className12-13">7</span></td>  
            </c:if>
            <c:if test="${cStatus.index==8}">
               <td class="tbl tbct"  style="width:60px"><span id="className12-13">8</span></td>  
            </c:if>
            <c:if test="${cStatus.index==9}">
               <td class="tbl tbct"  style="width:60px"><span id="className12-13">9</span></td>  
            </c:if>
            <c:if test="${cStatus.index==10}">
               <td class="tbl tbct"  style="width:60px"><span id="className12-13">10</span></td>  
            </c:if>
            <c:if test="${cStatus.index==11}">
               <td class="tbl tbct"  style="width:60px"><span id="className12-13">11</span></td>  
            </c:if>
            <c:if test="${cStatus.index==12}">
               <td class="tbl tbct"  style="width:60px"><span id="className12-13">12</span></td>  
            </c:if>
            <c:forEach begin="1" end="7"  varStatus="iWeek">
            <td class="tb" ondblclick="showTimetable('4','${iWeek.count}','${cStatus.index}')" valign="top">
            <div style="font-size:10px;line-height:15px;">
    			 <c:set var="academyCourseCode" value=""></c:set>
    			 <c:set var="commonTeacher" value=""></c:set>
    			 <c:set var="commonLab" value=""></c:set>
    			 <c:set var="selfCourseCode" value=""></c:set>
            <!--教务选课组  -->
            <c:set var="arrayValue" value="" />
            <c:forEach var="ta" items="${timetableAppointment}" varStatus="lStatus">
                 
                 <c:if test="${ta.status==1}">
                        <c:if test="${ta.weekday==iWeek.count}">
                            <!--周次的逗号分隔串  -->
                            <c:if test="${ta.startClass<=cStatus.index&&ta.endClass>=cStatus.index}">
                                <c:if test="${academyCourseCode!=ta.schoolCourseDetail.schoolCourse.courseCode}">
                                <c:if test="${!empty academyCourseCode}">
                                     <!-- 冒泡算法排序 -->
    						         <!-- 定义数组  -->
    						         <c:set var="arrayValue" value="${arrayValue},"/>
    						         <c:set var="array" value="${fn:split(arrayValue, ',')}"/>
    						         <!-- 数组长度 -->
    						         <c:set var="length1" value="${fn:length(array)}"/>
    						         <c:forEach var="i" begin="0" end="${length1}"> 
    						           <c:forEach var="j"  begin="0" end="${length1-i}">
    						                 <c:if test="${fn:split(array[j], '-')[0]-fn:split(array[j+1], '-')[0]>0&&j<length1-1}">

    						                 
    						                 <!-- 中间变量：一次循环的最小值和最大值 -->
    						                 <c:set var="max" value=",${array[j]}," />
    						                 <c:set var="min" value=",${array[j+1]}," />
    						                 <!-- 字符串替换生成新数组（jstl无数组赋值方法） -->
    						                 <c:set var="arrayValue" value="${fn:replace(arrayValue,min,',-1,')}"/>
    						                 <c:set var="arrayValue" value="${fn:replace(arrayValue,max,min )}"/>
    						                 <c:set var="arrayValue" value="${fn:replace(arrayValue,',-1,', max)}"/>
    						                 <c:set var="array" value="${fn:split(arrayValue, ',')}"/>
    						                </c:if>
    						           </c:forEach>
    						         </c:forEach> 
    						         <!-- 显示排序好的周次 --> 
    						         <c:forEach var="week" items="${array}"> 
    						          ${week}
    						         </c:forEach>
    						         <c:set var="arrayValue" value=""/>
                                <br>
                                </c:if>
                                ${ta.schoolCourseDetail.schoolCourse.courseName}&nbsp;
                               <%--  <c:forEach var="aa" items="${ta.timetableTeacherRelateds}" varStatus="tStatus">
                                <label title="${aa.user.cname}">${aa.user.cname}</label>
                                </c:forEach> --%> 
                               <!--排课记录是连续的，子表无记录  -->
                              <c:if test="${ta.timetableAppointmentSameNumbers.size()==0}">
                                    <c:if test="${ta.startWeek==ta.endWeek}">
                                                                                                                      周次：<%-- ${ta.startWeek } --%>
                                          <c:set var="arrayValue" value="${arrayValue},${ta.startWeek}" />
                                    </c:if>
                                    <c:if test="${ta.startWeek!=ta.endWeek}">
                                                                                                                       周次：<%-- ${ta.startWeek }-${ta.endWeek } --%>
                                          <c:set var="arrayValue" value="${arrayValue},${ta.startWeek}-${ta.endWeek }" />
                                    </c:if>
                              </c:if>
                              <!--排课记录是不连续，子表有记录  -->
                              <c:if test="${ta.timetableAppointmentSameNumbers.size()>0}"> 
                              <c:set var="sameStart" value="-"></c:set>
                              <c:forEach var="entry" items="${ta.timetableAppointmentSameNumbers}"   varStatus="p"> 
                              
                              <!-- 显示周次节次 -->
                                <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
                                <c:if test="${fn:indexOf(sameStart,v_param)<0}">
                                
                                <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
                                                                                                      周次：<c:forEach var="entry1" items="${ta.timetableAppointmentSameNumbers}"  varStatus="q">  
                                    <c:if test="${entry.startClass==entry1.startClass}">
                                    <%-- <td>
                                    [${entry1.startClass }-${entry1.endClass }]
                                    </td> --%>
                                    <c:if test="${entry1.startWeek==entry1.endWeek}">
                                   <%--  ${entry1.startWeek } --%>
                                    <c:set var="arrayValue" value="${arrayValue},${entry1.startWeek}" />
                                    </c:if>
                                    <c:if test="${entry1.startWeek!=entry1.endWeek}">
                                       <%--  ${entry1.startWeek }-${entry1.endWeek } --%>
                                        <c:set var="arrayValue" value="${arrayValue},${entry1.startWeek }-${entry1.endWeek }" />
                                    </c:if>
                                    </c:if>
                                </c:forEach>                              
                                </c:if>
                                </c:forEach>
                              </c:if>
                              </c:if>
                              <!--如果是相同的选课组，则合并周次显示  -->
                              
                             <!--实验室变量  -->
                             <c:set var="tempLab" value="" />
			                 <c:forEach var="lab" items="${ta.timetableLabRelateds}">
			                      <c:set var="tempLab" value="${tempLab},${lab.labRoom.id}," />
			                 </c:forEach>
			                 
			                 <!--教师变量  -->
                             <c:set var="tempTeacher" value="" />
			                 <c:forEach var="teacher" items="${ta.timetableTeacherRelateds}">
			                      <c:set var="tempTeacher" value="${tempTeacher},${lab.user.username}," />
			                 </c:forEach>
                 
                              <c:if test="${academyCourseCode==ta.schoolCourseDetail.schoolCourse.courseCode&&tempLab !=commonLab}">
                                <%-- <c:set var="arrayValue" value="" /> --%>
                                <%-- <br>temp:${tempLab }common: ${commonLab } --%>
                                <c:if test="${ta.timetableAppointmentSameNumbers.size()==0}">
                                    <c:if test="${ta.startWeek==ta.endWeek}">
                                         <%--  ,${ta.startWeek } --%>
                                    <c:set var="arrayValue" value="${arrayValue},${ta.startWeek }" />
                                    
                                    </c:if>
                                    <c:if test="${ta.startWeek!=ta.endWeek}">
                                         <%--  ,${ta.startWeek }-${ta.endWeek } --%>
                                    <c:set var="arrayValue" value="${arrayValue},${ta.startWeek }-${ta.endWeek }" />
                                    </c:if>
                              </c:if>
                              <c:if test="${ta.timetableAppointmentSameNumbers.size()>0}"> 
                              <c:set var="sameStart" value="-"></c:set>
                              <c:forEach var="entry" items="${ta.timetableAppointmentSameNumbers}"   varStatus="p"> 
                              
                              <!-- 显示周次节次 -->
                                <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
                                <c:if test="${fn:indexOf(sameStart,v_param)<0}">
                                <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
                                    <c:forEach var="entry1" items="${ta.timetableAppointmentSameNumbers}"  varStatus="q">  
                                    <c:if test="${entry.startClass==entry1.startClass}">
                                    <%-- <td>
                                    [${entry1.startClass }-${entry1.endClass }]
                                    </td> --%>
                                    <c:if test="${entry1.startWeek==entry1.endWeek}">
                                    <%-- ${entry1.startWeek } --%>
                                    <c:set var="arrayValue" value="${arrayValue},${entry1.startWeek }" />
                                    
                                    </c:if>
                                    <c:if test="${entry1.startWeek!=entry1.endWeek}">
                                        <%-- ,${entry1.startWeek }-${entry1.endWeek } --%>
                                        <c:set var="arrayValue" value="${arrayValue},${entry1.startWeek }-${entry1.endWeek }" />
                                    </c:if>
                                    </c:if>
                                </c:forEach>                                                     
                                </c:if>
                                </c:forEach>
                              </c:if>
                              
                              </c:if>
                              <c:set var="academyCourseCode" value="${ta.schoolCourseDetail.schoolCourse.courseCode}"></c:set>
                              <!--实验室变量  -->
                              <c:set var="commonLab" value="" />
			                 <c:forEach var="lab" items="${ta.timetableLabRelateds}">
			                      <c:set var="commonLab" value="${commonLab}${lab.labRoom.id}," />
			                 </c:forEach>
			                 
			                  <!--教师变量  -->
                              <c:set var="commonTeacher" value="" />
			                 <c:forEach var="teacher" items="${ta.timetableTeacherRelateds}">
			                      <c:set var="commonTeacher" value="${commonTeacher}${lab.user.username}," />
			                 </c:forEach>
                 
                            </c:if>
                        </c:if>
                     </c:if>
                 <%-- </c:if> --%>
             </c:forEach>
             <!-- 冒泡算法排序 -->
             <!-- 定义数组  -->
             <c:set var="arrayValue" value="${arrayValue},"/>
             <c:set var="array" value="${fn:split(arrayValue, ',')}"/>
             <!-- 数组长度 -->
             <c:set var="length1" value="${fn:length(array)}"/>
             <c:forEach var="i" begin="0" end="${length1}"> 
               <c:forEach var="j"  begin="0" end="${length1-i}">
                     <c:if test="${fn:split(array[j], '-')[0]-fn:split(array[j+1], '-')[0]>0&&j<length1-1}">
                     
                     <!-- 中间变量：一次循环的最小值和最大值 -->
                     <c:set var="max" value=",${array[j]}," />
                     <c:set var="min" value=",${array[j+1]}," />
                     <!-- 字符串替换生成新数组（jstl无数组赋值方法） -->
                     <c:set var="arrayValue" value="${fn:replace(arrayValue,min,',-1,')}"/>
                     <c:set var="arrayValue" value="${fn:replace(arrayValue,max,min )}"/>
                     <c:set var="arrayValue" value="${fn:replace(arrayValue,',-1,', max)}"/>
                     <c:set var="array" value="${fn:split(arrayValue, ',')}"/>
                    </c:if>
               </c:forEach>
             </c:forEach> 
             <!-- 显示排序好的周次 --> 
             <c:forEach var="week" items="${array}"> 
              ${week}
             </c:forEach>   
             
             <c:set var="arrayValue" value="" />
             <c:set var="academyCourseCode" value=""></c:set>
             <c:set var="commonLab" value=""></c:set>
             <c:set var="commonTeacher" value=""></c:set>
             <c:set var="selfCourseCode" value=""></c:set>   
    
             <!--自建选课组排课  -->
             <c:forEach var="ta" items="${selfTimetableAppointment}" varStatus="lStatus">
                 <c:if test="${ta.status==1}">
                      <c:if test="${ta.weekday==iWeek.count}">
                          <!--分组排课  -->
                          <c:if test="${ta.timetableStyle==6||ta.timetableStyle==5}">
                            <c:if test="${ta.startClass<=cStatus.index&&ta.endClass>=cStatus.index}">
                               <c:if test="${selfCourseCode!=ta.timetableSelfCourse.courseCode}">
                               <c:if test="${!empty selfCourseCode}"> 
                                 <!-- 冒泡算法排序 -->
    					         <!-- 定义数组  -->
    					         <c:set var="arrayValue" value="${arrayValue},"/>
    					         <c:set var="array" value="${fn:split(arrayValue, ',')}"/>
    					         <!-- 数组长度 -->
    					         <c:set var="length1" value="${fn:length(array)}"/>
    					         <c:forEach var="i" begin="0" end="${length1}"> 
    					           <c:forEach var="j"  begin="0" end="${length1-i}">
    					                 
    					                 <c:if test="${fn:split(array[j], '-')[0]-fn:split(array[j+1], '-')[0]>0&&j<length1-1}">
    					                 <!-- 中间变量：一次循环的最小值和最大值 -->
    					                 <c:set var="max" value=",${array[j]}," />
    					                 <c:set var="min" value=",${array[j+1]}," />
    					                 <!-- 字符串替换生成新数组（jstl无数组赋值方法） -->
    					                 <c:set var="arrayValue" value="${fn:replace(arrayValue,min,',-1,')}"/>
    					                 <c:set var="arrayValue" value="${fn:replace(arrayValue,max,min )}"/>
    					                 <c:set var="arrayValue" value="${fn:replace(arrayValue,',-1,', max)}"/>
    					                 <c:set var="array" value="${fn:split(arrayValue, ',')}"/>
    					                </c:if>
    					           </c:forEach>
    					         </c:forEach> 
    					         <!-- 显示排序好的周次 --> 
    					         <c:forEach var="week" items="${array}"> 
    					          ${week}
    					         </c:forEach>
    					         <c:set var="arrayValue" value=""/>  
                                <br>
                               </c:if>
                                ${ta.timetableSelfCourse.schoolCourseInfo.courseName}&nbsp;   
                                <%-- <c:forEach var="aa" items="${ta.timetableTeacherRelateds}" varStatus="tStatus">
                                <label title="${aa.user.cname}">${aa.user.cname}</label>
                                </c:forEach>  --%>   
                               <c:if test="${ta.timetableAppointmentSameNumbers.size()==0}">
                                    
                                    <c:if test="${ta.startWeek==ta.endWeek}">
                                                                                                                    周次：<%-- ${ta.startWeek }  --%>
                                          <c:set var="arrayValue" value="${arrayValue},${ta.startWeek }" />
                                    </c:if>
                                    <c:if test="${ta.startWeek!=ta.endWeek}">
                                                                                                                     周次：<%-- ${ta.startWeek }-${ta.endWeek } --%> 
                                          <c:set var="arrayValue" value="${arrayValue},${ta.startWeek }-${ta.endWeek }" />
                                    </c:if>
                              </c:if>
                              <c:if test="${ta.timetableAppointmentSameNumbers.size()>0}"> 
                              <c:set var="sameStart" value="-"></c:set>
                              <c:forEach var="entry" items="${ta.timetableAppointmentSameNumbers}"   varStatus="p"> 
                              
                              <!-- 显示周次节次 -->
                                <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
                                <c:if test="${fn:indexOf(sameStart,v_param)<0}">
                                <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
                                                                                                      周次：<c:forEach var="entry1" items="${ta.timetableAppointmentSameNumbers}"  varStatus="q">  
                                    <c:if test="${entry.startClass==entry1.startClass}">
                                    <%-- <td>
                                    [${entry1.startClass }-${entry1.endClass }]
                                    </td> --%>
                                    <c:if test="${entry1.startWeek==entry1.endWeek}">
                                   <%--  ${entry1.startWeek }  --%>
                                    <c:set var="arrayValue" value="${arrayValue},${entry1.startWeek }" />
                                    </c:if>
                                    <c:if test="${entry1.startWeek!=entry1.endWeek}">
                                       <%--  ${entry1.startWeek }-${entry1.endWeek } --%> 
                                        <c:set var="arrayValue" value="${arrayValue},${entry1.startWeek }-${entry1.endWeek }" />
                                    </c:if>
                                    </c:if>
                                </c:forEach>
                                </c:if>
                                </c:forEach>
                              </c:if>
                             </c:if> 
                             <%-- <br>${commonLab} --%>
                             <c:set var="commonLab" value="" />
                             <c:set var="commonTeacher" value=""></c:set>
                             <c:set var="tempLab" value=""></c:set>
                             <c:forEach var="lab" items="${ta.timetableLabRelateds}">
			                      <c:set var="tempLab" value="${tempLab},${lab.labRoom.id}," />
			                 </c:forEach>
			        
			                 <c:set var="tempTeacher" value=""></c:set>
                             <c:forEach var="teacher" items="${ta.timetableTeacherRelateds}">
			                      <c:set var="tempTeacher" value="${tempTeacher},${teacher.user.username}," />
			                 </c:forEach>
			                   
                             <c:if test="${selfCourseCode eq ta.timetableSelfCourse.courseCode&&tempLab !=commonLab}">
                          
                                <c:if test="${ta.timetableAppointmentSameNumbers.size()==0}">
                                    
                                    <c:if test="${ta.startWeek==ta.endWeek}">
                                           <%-- ,${ta.startWeek } --%>
                                           <c:set var="arrayValue" value="${arrayValue},${ta.startWeek }" />
                                    </c:if>
                                    <c:if test="${ta.startWeek!=ta.endWeek}">
                                           <%-- ,${ta.startWeek }-${ta.endWeek } --%>
                                           <c:set var="arrayValue" value="${arrayValue},${ta.startWeek }-${ta.endWeek }" />
                                    </c:if>
                                    <%--<c:forEach var="aa" items="${ta.timetableTeacherRelateds}" varStatus="tStatus">
                                <label title="${aa.user.cname}">${aa.user.cname}</label>
                                </c:forEach>--%>  
                              </c:if>
                              <c:if test="${ta.timetableAppointmentSameNumbers.size()>0}"> 
                              <c:set var="sameStart" value="-"></c:set>
                              <c:forEach var="entry" items="${ta.timetableAppointmentSameNumbers}"   varStatus="p"> 
                              
                              <!-- 显示周次节次 -->
                                <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
                                <c:if test="${fn:indexOf(sameStart,v_param)<0}">
                                <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
                                    <c:forEach var="entry1" items="${ta.timetableAppointmentSameNumbers}"  varStatus="q">  
                                    <c:if test="${entry.startClass==entry1.startClass}">
                                    <%-- <td>
                                    [${entry1.startClass }-${entry1.endClass }]
                                    </td> --%>
                                    <c:if test="${entry1.startWeek==entry1.endWeek}">
                                   <%--  ${entry1.startWeek } --%>
                                    <c:set var="arrayValue" value="${arrayValue},${entry1.startWeek }" />
                                    </c:if>
                                    <c:if test="${entry1.startWeek!=entry1.endWeek}">
                                        <%-- ${entry1.startWeek }-${entry1.endWeek } --%>
                                        <c:set var="arrayValue" value="${arrayValue},${entry1.startWeek }-${entry1.endWeek }" />
                                    </c:if>
                                    </c:if>
                                </c:forEach>
                                </c:if>
                                </c:forEach>
                              </c:if>
                             </c:if>
                             
                             <c:set var="selfCourseCode" value="${ta.timetableSelfCourse.courseCode}"></c:set>
                             
                             <!--实验室变量  -->
                             <c:set var="commonLab" value="" />
			                 <c:forEach var="lab" items="${ta.timetableLabRelateds}">
			                      <c:set var="commonLab" value="${commonLab},${lab.labRoom.id}," />
			                 </c:forEach>
			                 
			                 <c:set var="commonTeacher" value="" />
			                 <c:forEach var="teacher" items="${ta.timetableTeacherRelateds}">
			                      <c:set var="commonTeacher" value="${commonTeacher},${teacher.user.username}," />
			                 </c:forEach>
                 
                             </c:if>
                             </c:if>
                       <%-- </c:if> --%>
                       </c:if>
                 </c:if>
        
             </c:forEach>
             
             <!-- 冒泡算法排序 -->
             <!-- 定义数组  -->
             <c:set var="arrayValue" value="${arrayValue},"/>
             <c:set var="array" value="${fn:split(arrayValue, ',')}"/>
             <!-- 数组长度 -->
             <c:set var="length1" value="${fn:length(array)}"/>
             <c:forEach var="i" begin="0" end="${length1}"> 
               <c:forEach var="j"  begin="0" end="${length1-i}">
                     <c:if test="${fn:split(array[j], '-')[0]-fn:split(array[j+1], '-')[0]>0&&j<length1-1}"> 
                     <!-- 中间变量：一次循环的最小值和最大值 -->
                     <c:set var="max" value=",${array[j]}," />
                     <c:set var="min" value=",${array[j+1]}," />
                     <!-- 字符串替换生成新数组（jstl无数组赋值方法） -->
                     <c:set var="arrayValue" value="${fn:replace(arrayValue,min,',-1,')}"/>
                     <c:set var="arrayValue" value="${fn:replace(arrayValue,max,min )}"/>
                     <c:set var="arrayValue" value="${fn:replace(arrayValue,',-1,', max)}"/>
                     <c:set var="array" value="${fn:split(arrayValue, ',')}"/>
                    </c:if>
               </c:forEach>
             </c:forEach> 
             <!-- 显示排序好的周次 --> 
             <c:forEach var="week" items="${array}"> 
              ${week}
             </c:forEach>
            </div>
            </td >
            </c:forEach>
        </tr> 
    </c:forEach>
    </tbody>
    </table>
</div>

<div class="" >
<div class="title">我的课程</div>
					
    <table>
    <thead>
    <tr>
    <!--    <th></th>  -->
       <th>序号</th>
       <th>课程编号</th>
       <th>课程名称</th>
       <th>实验项目名称</th>
       <th width="30px;">星期</th>
       <th width="100px;" colspan=2>
           <table>
           <tr>
              <td width="45px;">节次</td>
              <td width="45px;">周次</td>
           </tr>
           </table>
       </th>
       <th>上课教师</th>
       <th>指导教师</th>
       <th>实验室</th>
       <!--    <th>学生名单</th> -->
       <!--    <th>排课方式</th>
       <th colspan=2>操作</th>
       <th></th> -->
    </tr>
    </thead>
    <tbody>
    <c:set var="ifRowspan" value="0"></c:set>
    <c:set var="count" value="1"></c:set>
    <c:forEach items="${timetableAppointment}" var="current"  varStatus="i">	
    <c:set var="rowspan" value="0"></c:set>
    <tr>
        <c:forEach items="${timetableAppointment}" var="current1"  varStatus="j">	
             <c:if test="${current1.schoolCourseDetail.schoolCourse.courseCode==current.schoolCourseDetail.schoolCourse.courseCode }">
                <c:set value="${rowspan + 1}" var="rowspan" />
             </c:if>
         </c:forEach>
    <%--      <c:if test="${rowspan>1&&ifRowspan!=current.schoolCourseDetail.schoolCourse.courseCode }">
              <td  rowspan="${rowspan }"><input type='checkbox' name='VoteOption1' value=1></td>  
         </c:if> --%>
    <%--      <c:if test="${rowspan==1 }">
              <td><!-- <input type='checkbox' name='VoteOption1' value=1> --></td>
         </c:if> --%>
         <c:if test="${rowspan>1&&ifRowspan!=current.schoolCourseDetail.schoolCourse.courseCode }">
              <td rowspan="${rowspan }">${count}</td>
              <c:set var="count" value="${count+1}"></c:set>
         </c:if>
         <c:if test="${rowspan==1 }">
             <td >${count}</td>
             <c:set var="count" value="${count+1}"></c:set>
         </c:if>
         <c:if test="${rowspan>1&&ifRowspan!=current.schoolCourseDetail.schoolCourse.courseCode }">
             <td rowspan="${rowspan }">${current.schoolCourseDetail.schoolCourse.schoolCourseInfo.courseNumber}</td>
             <td rowspan="${rowspan }">${current.schoolCourseDetail.schoolCourse.courseName}</td>
         </c:if>
        
         <c:if test="${rowspan==1 }">
             <td >${current.schoolCourseDetail.schoolCourse.schoolCourseInfo.courseNumber}</td>
             <td >${current.schoolCourseDetail.schoolCourse.courseName}</td>
         </c:if>
         <td >
            <c:forEach var="entry" items="${current.timetableItemRelateds}">  
            <c:if test="${empty entry.operationItem||entry.operationItem.id==0}">
             ${current.schoolCourseDetail.schoolCourse.courseName}(课程名称)
            
             </c:if>
             <c:if test="${not empty entry.operationItem&&entry.operationItem.id!=0 }">
                ${entry.operationItem.lpName}&nbsp;&nbsp;&nbsp;
             </c:if>
            </c:forEach>
            <c:if test="${current.timetableItemRelateds.size()==0}">
             ${current.schoolCourseDetail.schoolCourse.courseName}(课程名称)
            </c:if>
         </td>
         
          <!--对应星期  -->
         <td>${current.weekday}</td>
         <!--对应节次  -->
         <td colspan=2 width="90px;" >
         <table>
         <c:if test="${current.timetableAppointmentSameNumbers.size()==0}">
         <tr>
                 <td width="45px;">
                 <c:if test="${current.startClass==current.endClass}">
                 ${current.startClass }
                 </c:if>
                 <c:if test="${current.startClass!=current.endClass}">
                 ${current.startClass }-${current.endClass }
                 </c:if>
                 </td>
                 <td width="45px;">
                 <c:if test="${current.startWeek==current.endWeek}">
                 ${current.startWeek }
                 </c:if>
                 <c:if test="${current.startWeek!=current.endWeek}">
                 ${current.startWeek }-${current.endWeek }
                 </c:if>
                 </td>
          </tr>
          </c:if>
         <c:if test="${current.timetableAppointmentSameNumbers.size()>0}">
             
             <c:set var="sameStart" value="-"></c:set>
             <c:forEach var="entry" items="${current.timetableAppointmentSameNumbers}"   varStatus="p"> 
             <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
             <c:if test="${fn:indexOf(sameStart,v_param)<0}">
             <tr>
             <td width="45px;">
                 <c:if test="${entry.startClass==entry.endClass}">
                 ${entry.startClass }
                 </c:if>
                 <c:if test="${entry.startClass!=entry.endClass}">
                 ${entry.startClass }-${entry.endClass }
                 </c:if>
             </td>
             <td width="45px;">
             <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
             <c:forEach var="entry1" items="${current.timetableAppointmentSameNumbers}"  varStatus="q">  
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
             </c:forEach>
             </td>
             </tr>
             </c:if>
             </c:forEach>
              
         </c:if>
         </table>
         </td>
         <td>
         <!--上课教师  -->
         <c:forEach var="entry" items="${current.timetableTeacherRelateds}">  
         <c:out value="${entry.user.cname}" />  
         </c:forEach> 
         </td>
         <td>
         <!--指导教师  -->
         <c:forEach var="entry" items="${current.timetableTutorRelateds}">  
         <c:out value="${entry.user.cname}" />  
         </c:forEach>
         </td>
         <td>
         <!--相关实验分室  -->
         <c:forEach var="entry" items="${current.timetableLabRelateds}">  
         <c:out value="${entry.labRoom.labRoomName}" /><br>  
         </c:forEach>
                            <c:set var="ifRowspan" value="${current.schoolCourseDetail.schoolCourse.courseCode }"></c:set>
         </td>
    </tr>
    </c:forEach>
    <c:forEach items="${selfTimetableAppointment}" var="current"  varStatus="i">	
    <c:set var="rowspan" value="0"></c:set>
    <tr>
        <c:forEach items="${selfTimetableAppointment}" var="current1"  varStatus="j">	
             <c:if test="${current1.timetableSelfCourse.courseCode==current.timetableSelfCourse.courseCode }">
                <c:set value="${rowspan + 1}" var="rowspan" />
             </c:if>
         </c:forEach>
    <%--      <c:if test="${rowspan>1&&ifRowspan!=current.schoolCourseDetail.schoolCourse.courseCode }">
              <td  rowspan="${rowspan }"><input type='checkbox' name='VoteOption1' value=1></td>  
         </c:if> --%>
    <%--      <c:if test="${rowspan==1 }">
              <td><!-- <input type='checkbox' name='VoteOption1' value=1> --></td>
         </c:if> --%>
         <c:if test="${rowspan>1&&ifRowspan!=current.timetableSelfCourse.courseCode }">
              <td rowspan="${rowspan }">${count}</td>
              <c:set var="count" value="${count+1}"></c:set>
         </c:if>
         <c:if test="${rowspan==1 }">
             <td >${count}</td>
             <c:set var="count" value="${count+1}"></c:set>
         </c:if>
         <c:if test="${rowspan>1&&ifRowspan!=current.timetableSelfCourse.courseCode }">
             <td rowspan="${rowspan }">${current.timetableSelfCourse.schoolCourseInfo.courseNumber}</td>
             <td rowspan="${rowspan }">${current.timetableSelfCourse.schoolCourseInfo.courseName}</td>
         </c:if>
        
         <c:if test="${rowspan==1 }">
             <td >${current.timetableSelfCourse.schoolCourseInfo.courseNumber}</td>
             <td >${current.timetableSelfCourse.schoolCourseInfo.courseName}</td>
         </c:if>
         <td >
            <c:forEach var="entry" items="${current.timetableItemRelateds}">  
             <c:if test="${empty entry.operationItem||entry.operationItem.id==0 }">
                ${current.timetableSelfCourse.schoolCourseInfo.courseName}(课程名称)
             </c:if>
              <c:if test="${not empty entry.operationItem&&entry.operationItem.id!=0 }">
             ${entry.operationItem.lpName}&nbsp;&nbsp;&nbsp;
             </c:if>
            </c:forEach>
            <c:if test="${current.timetableItemRelateds.size()==0}">
             ${current.timetableSelfCourse.schoolCourseInfo.courseName}(课程名称)
            </c:if>
         </td>
         
          <!--对应星期  -->
         <td>${current.weekday}</td>
         <!--对应节次  -->
         <td colspan=2 width="90px;" >
         <table>
         <c:if test="${current.timetableAppointmentSameNumbers.size()==0}">
         <tr>
                 <td width="45px;">
                 <c:if test="${current.startClass==current.endClass}">
                 ${current.startClass }
                 </c:if>
                 <c:if test="${current.startClass!=current.endClass}">
                 ${current.startClass }-${current.endClass }
                 </c:if>
                 </td>
                 <td width="45px;">
                 <c:if test="${current.startWeek==current.endWeek}">
                 ${current.startWeek }
                 </c:if>
                 <c:if test="${current.startWeek!=current.endWeek}">
                 ${current.startWeek }-${current.endWeek }
                 </c:if>
                 </td>
          </tr>
          </c:if>
         <c:if test="${current.timetableAppointmentSameNumbers.size()>0}">
             
             <c:set var="sameStart" value="-"></c:set>
             <c:forEach var="entry" items="${current.timetableAppointmentSameNumbers}"   varStatus="p"> 
             <c:set var="v_param" value="-${entry.startClass}-" ></c:set>   
             <c:if test="${fn:indexOf(sameStart,v_param)<0}">
             <tr>
             <td width="45px;">
                 <c:if test="${entry.startClass==entry.endClass}">
                 ${entry.startClass }
                 </c:if>
                 <c:if test="${entry.startClass!=entry.endClass}">
                 ${entry.startClass }-${entry.endClass }
                 </c:if>
             </td>
             <td width="45px;">
             <c:set var="sameStart" value="${sameStart}-${entry.startClass }-"></c:set>
             <c:forEach var="entry1" items="${current.timetableAppointmentSameNumbers}"  varStatus="q">  
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
             </c:forEach>
             </td>
             </tr>
             </c:if>
             </c:forEach>
              
         </c:if>
         </table>
         </td>
         <td>
         <!--上课教师  -->
         <c:forEach var="entry" items="${current.timetableTeacherRelateds}">  
         <c:out value="${entry.user.cname}" />  
         </c:forEach> 
         </td>
         <td>
         <!--指导教师  -->
         <c:forEach var="entry" items="${current.timetableTutorRelateds}">  
         <c:out value="${entry.user.cname}" />  
         </c:forEach>
         </td>
         <td>
         <!--相关实验分室  -->
         <c:forEach var="entry" items="${current.timetableLabRelateds}">  
         <c:out value="${entry.labRoom.labRoomName}" /><br>  
         </c:forEach>
                            <c:set var="ifRowspan" value="${current.timetableSelfCourse.courseCode }"></c:set>
         </td>
    </tr>
    </c:forEach>  
    </tbody>
    </table>
<div  class=" more"></div>
</div>
</div>
</div>
</body>
</html>

