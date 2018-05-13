<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html >
<head>
<meta name="decorator" content="iframe"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/lib.css" />
		<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/silver.css" media="screen">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.silver_track.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.silver_track_recipes.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.silver_track.navigator.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.silver_track.bullet_navigator.js" charset="utf-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
		<!-- 下拉框的样式 -->
 		 <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
  		<link type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/chosen/chosen.css" />
  		<!-- 下拉的样式结束 --> 
  		
  			<style type="text/css">
  			.cbm_box .chzn-container,.course_basic_message .cbm_box{
  				
  				height:auto !important;
  			}
  			
  			.course_basic_message .cbm_box,.course_select div,.course_select{
  				overflow:visible !important;
  			}
  			.search-choice span{
  				width:auto !important;
  				background:none !important;
  				color:#333 !important;
  			}
  			#teacher-chzn{
  				height:auto !important;
  			}
  			.chzn-container-multi .chzn-results{
  				height:auto;
  			}
  		</style>
</head>

<body>
<div class="main_container cf rel w95p ma mb60">
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
<form action="${pageContext.request.contextPath}lab/labReservationCalendar" method="post">
<table border="0" cellpadding="5" cellspacing="0" bgcolor="#F3EFEE" height="30" width="100%">
<tbody>
<tr>
    <td>
    <select id="term" name="term">
       <c:forEach items="${schoolTerm}" var="current">
    	    <c:if test="${current.id == term}">
    	       <option value ="${current.id}" selected>${current.termName}---${term } </option>
    	    </c:if>
    	    <c:if test="${current.id != term}">
    	       <option value ="${current.id}" >${current.termName}---${term } </option>
    	    </c:if>		
        </c:forEach>
    </select>
    <input value="查询" type="submit">&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/lab/labReservationCalendar?roomId=${roomId}"><input type="button" value="取消查询"></a>
    <input type="hidden" value=-1 id="courseCode" name="courseCode">

    </td>    
</tr>
</tbody>
</table>
<input type="hidden" name="term" value="${term }" >
<input type="hidden" name="roomId" value="${labroom}" >
<input type="hidden" name="week" value="${week }" > 
<input type="hidden" name="courseCode" value="${courseCode }" >
<input type="hidden" name="teacherId" value="${teacherId }" >

</form>
<div class="edit-content">
</div>
	<div class="bgwo w100p ptb10">
		<table class="timetable_tab" cellspacing="0" style="table-layout:fixed;">
			<tr>
				<th colspan="2" rowspan="3" class="timetable_th" style="width:;">
					<span class="tt_pa1">月</span>
					<span class="tt_pa2">周</span>
					<span class="tt_pa3">日期</span>
					<span class="tt_pa4">课次</span>
					<span class="tt_pa5">星期</span>
				</th>
				<c:set var="tempWeek" value="0"/>
			 	<c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
			 			<c:set var="countWeek" value="0"/>
			 			
			 		<c:forEach items="${schoolTermWeeks }" var="currWeek2" varStatus="i">
			 			<c:if test="${currWeek1[6] == currWeek2[6] }">
			 				<c:set var="countWeek" value="${countWeek+1 }"/>
			 			</c:if>
			 		</c:forEach>
			 		<c:if test="${currWeek1 [6] != tempWeek && countWeek>1}">
			 			<th colspan="${countWeek }">${currWeek1 [6]}月</th>
			 		</c:if>
			 		<c:if test="${countWeek==1}">
			 			<th>${currWeek1 [6]}月</th>
			 		</c:if>
			 		<c:set var="tempWeek" value="${currWeek1[6] }"/>
			 	</c:forEach>
			</tr>
			<tr>
			<c:set var="tempWeek" value="0"/>
		 	<c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
		 		<td>${currWeek1[2] }</td>
		 	</c:forEach>
			</tr>
			<tr>
			<c:set var="tempWeek" value="0"/>
		 	<c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
		 		<th>
		 			<c:if test="${currWeek1[6]==currWeek1[7]}">${currWeek1[8]}<br>|<br>${currWeek1[9]}</c:if>
		 			<c:if test="${currWeek1[6]!=currWeek1[7]}">${currWeek1[6]}/${currWeek1[8]}<br>|<br>${currWeek1[7]}/${currWeek1[9]}</c:if>
				</th>
		 	</c:forEach>
		</tr>
			<c:forEach var="class1"  varStatus="cStatus1" begin="${schoolTermWeeks[0][10]}" end="7">
		       	<c:forEach var="class2" varStatus="cStatus2" begin="1" end="5">
		       		<tr>
		       			<c:if test="${cStatus2.index==1}">
		       				<c:if test="${cStatus1.index==1}">
					               <td rowspan="5">一</td>  
					            </c:if>
					            <c:if test="${cStatus1.index==2}">
					               <td rowspan="5">二</td>  
					            </c:if>
					            <c:if test="${cStatus1.index==3}">
					               <td rowspan="5">三</td>
					            </c:if>
					            <c:if test="${cStatus1.index==4}">
					               <td rowspan="5">四</td>  
					            </c:if>
					            <c:if test="${cStatus1.index==5}">
					               <td rowspan="5">五</td>  
					            </c:if>
					            <c:if test="${cStatus1.index==6}">
					               <td rowspan="5">六</td>
					            </c:if>
					            <c:if test="${cStatus1.index==7}">
					               <td rowspan="5">日</td>
					            </c:if> 
			               <th>1-2</th>
			            </c:if>
			            <c:if test="${cStatus2.index==2}">
			               <th>3-4</th>  
			            </c:if>
			            <c:if test="${cStatus2.index==3}">
			               <th>5-6</th>
			            </c:if>
			            <c:if test="${cStatus2.index==4}">
			               <th>7-8</th>
			            </c:if>
			            <c:if test="${cStatus2.index==5}">
					            <th>晚</th>
			            </c:if>
			            <c:set var="countWeek" value="0"/>
			              <c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
			            	<c:set var="countWeek" value="${countWeek+1 }"/>
			            	<c:set var="flag" value="0"/>
			            	<c:set var="dayName" value="0"/>
			            	<c:forEach items="${centerCalendar }" var="currTimetable">
			            	    <c:set var="selectweek" value=",${currTimetable[3]},"/>
			            	     <c:set var="calendarweek" value=",${currWeek1[2]},"/>
			            			<c:if test="${fn:contains(selectweek,calendarweek)
				            		&& currTimetable[4] == cStatus1.index 
				            		&& cStatus2.index > currTimetable[5]/2 && cStatus2.index <= currTimetable[6]/2}">
		            				<c:if test="${currTimetable[0] ne  roomId&&currTimetable[0] ne 0}">
		            					<c:set var="flag" value="6"/>
		            				</c:if>
		            				<c:if test="${currTimetable[0] eq  roomId}">
		            					<c:set var="flag" value="3"/>
		            				</c:if>
		            				<c:if test="${currTimetable[0]==0}">
		            					<c:set var="flag" value="4"/>
		            				</c:if>
					            	</c:if>
			            	</c:forEach>
			            	<c:if test="${not empty specialSchoolWeeks}">
				            	<c:forEach items="${specialSchoolWeeks }" var="specialWeek1" varStatus="i">
				            		<c:if test="${currWeek1[2] == specialWeek1.week && specialWeek1.weekday == cStatus1.index && cStatus2.index ==1}">
				            			<c:set var="flag" value="1"/>
				            			<c:set var="dayName" value="${specialWeek1.CDictionary.CName}"/>
				            		</c:if>
				            		<c:if test="${currWeek1[2] == specialWeek1.week && specialWeek1.weekday == cStatus1.index && cStatus2.index !=1 && flag != 3}">
				            			<c:set var="flag" value="2"/>
				            			<c:set var="dayName" value="${specialWeek1.CDictionary.CName}"/>
				            		</c:if>
						 		</c:forEach>
					 		</c:if>
					 		<c:if test="${flag == 6}">
			            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"  class="chose_td" bgcolor="#68EE68"></td>
			            	</c:if>
					 		<c:if test="${flag == 3}">
			            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"  class="chose_td" bgcolor="red"></td>
			            	</c:if>
			            	<c:if test="${flag == 4}">
			            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"  class="chose_td" bgcolor="green"></td>
			            	</c:if>
					 		<c:if test="${flag == 1}">
			            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"  class="chose_td" rowspan="5">${dayName }</td>
			            	</c:if>
			            	<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4}">
			            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this"></td>
			            	</c:if>
					 	</c:forEach>
		       		</tr>
		       	</c:forEach>
		     </c:forEach>
		     <c:if test="${schoolTermWeeks[0][11] != 7 && schoolTermWeeks[0][10] != 1}">
								<c:forEach var="class1"  varStatus="cStatus1" begin="1" end="${schoolTermWeeks[0][11]}">
		       	<c:forEach var="class2" varStatus="cStatus2" begin="1" end="5">
		       		<tr>
		       			<c:if test="${cStatus2.index==1}">
		       				<c:if test="${cStatus1.index==1}">
					               <td rowspan="5">一</td>  
					            </c:if>
					            <c:if test="${cStatus1.index==2}">
					               <td rowspan="5">二</td>  
					            </c:if>
					            <c:if test="${cStatus1.index==3}">
					               <td rowspan="5">三</td>
					            </c:if>
					            <c:if test="${cStatus1.index==4}">
					               <td rowspan="5">四</td>  
					            </c:if>
					            <c:if test="${cStatus1.index==5}">
					               <td rowspan="5">五</td>  
					            </c:if>
					            <c:if test="${cStatus1.index==6}">
					               <td rowspan="5">六</td>
					            </c:if>
					            <c:if test="${cStatus1.index==7}">
					               <td rowspan="5">日</td>
					            </c:if> 
			               <th>1-2</th>
			            </c:if>
			            <c:if test="${cStatus2.index==2}">
			               <th>3-4</th>  
			            </c:if>
			            <c:if test="${cStatus2.index==3}">
			               <th>5-6</th>
			            </c:if>
			            <c:if test="${cStatus2.index==4}">
			               <th>7-8</th>
			            </c:if>
			            <c:if test="${cStatus2.index==5}">
					            <th>晚</th>
			            </c:if>
			            <c:set var="countWeek" value="0"/>
			              <c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
			            	<c:set var="countWeek" value="${countWeek+1 }"/>
			            	<c:set var="flag" value="0"/>
			            	<c:set var="dayName" value="0"/>
			            	<c:forEach items="${listLabTimetableAppointments }" var="currTimetable">
			            		<c:set var="selectweek" value=",${currTimetable[3]},"/>
			            	     <c:set var="calendarweek" value=",${currWeek1[2]},"/>
			            			<c:if test="${fn:contains(selectweek,calendarweek)
			            		&& currTimetable[4] == cStatus1.index 
			            		&& cStatus2.index > currTimetable[5]/2 && cStatus2.index <= currTimetable[6]/2}">
			            				<c:if test="${currTimetable[0] ne  roomId&&currTimetable[0] ne 0}">
		            					<c:set var="flag" value="6"/>
		            				</c:if>
		            				<c:if test="${currTimetable[0] eq  roomId}">
		            					<c:set var="flag" value="3"/>
		            				</c:if>
		            				<c:if test="${currTimetable[0]==0}">
		            					<c:set var="flag" value="4"/>
		            				</c:if>
				            	</c:if>
			            	</c:forEach>
			            	<c:if test="${not empty specialSchoolWeeks}">
				            	<c:forEach items="${specialSchoolWeeks }" var="specialWeek1" varStatus="i">
				            		<c:if test="${currWeek1[2] == specialWeek1.week && specialWeek1.weekday == cStatus1.index && cStatus2.index ==1}">
				            			<c:set var="flag" value="1"/>
				            			<c:set var="dayName" value="${specialWeek1.CDictionary.CName}"/>
				            		</c:if>
				            		<c:if test="${currWeek1[2] == specialWeek1.week && specialWeek1.weekday == cStatus1.index && cStatus2.index !=1 && flag != 3}">
				            			<c:set var="flag" value="2"/>
				            			<c:set var="dayName" value="${specialWeek1.CDictionary.CName}"/>
				            		</c:if>
						 		</c:forEach>
					 		</c:if>
					 		<c:if test="${flag == 6}">
			            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"  class="chose_td" bgcolor="#68EE68"></td>
			            	</c:if>
					 		<c:if test="${flag == 3}">
			            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"  class="chose_td" bgcolor="red"></td>
			            	</c:if>
			            	<c:if test="${flag == 4}">
			            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"  class="chose_td" bgcolor="green"></td>
			            	</c:if>
					 		<c:if test="${flag == 1}">
			            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}"  class="chose_td" rowspan="5">${dayName }</td>
			            	</c:if>
			            	<c:if test="${flag != 1 && flag != 2 && flag != 3 && flag != 4}">
			            		<td id="${currWeek1[2]}-${cStatus1.index}-${cStatus2.index}" class="chose_td choose_this"></td>
			            	</c:if>
					 	</c:forEach>
		       		</tr>
		       	</c:forEach>
		     </c:forEach>
		     </c:if>
		</table>
	</div>
</div>
</div>
<script type="text/javascript">
/* $(document).ready(function(){
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

$(function(){
    $("#showTimetable").window({
        top: ($(window).width() - 800) * 0.5 ,
        left: ($(window).width() - 1000) * 0.5   
    })
    $(".sit_maincontent").css('height',600);
})
 */

	$(".week_box").click(
		function() {
			$(this).addClass("week_box_select").siblings().removeClass("week_box_select");
		}
	);
</script>
</body>
</html>