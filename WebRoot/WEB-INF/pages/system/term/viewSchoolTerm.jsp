<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<head>
<meta name="decorator" content="iframe"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/raphael-min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript"> 
$(function(){
$(".datebox :text").attr("readonly","readonly"); 
  $('#term_name').blur(function(){
            var name = $('#term_name').val();
            $.trim(name);
            $('#yearcode').val(name.substr(0,9));
            if(name.indexOf('一') >= 0){
                $('#termcode').val(1);
            } else {
                if(name.indexOf('二') >= 0) {
                    $('#termcode').val(2);
                } else {
                     $('#term_name').val("");
                      $('#yearcode').val("");
                       $('#termcode').val("");
                    $.messager.alert('错误','输入有误!<br>请严格按照格式输入！');
                    
                }
            }
        });
});

function checkTerm(){
	var inputValue = document.getElementById('term_name');
 		 $.ajax({
 	           type:"POST",
 	           url:"${pageContext.request.contextPath}/system/checkTerm",
 	           data:{termName:inputValue.value},
 	           success:function(data){
 	        	  if(data=="ok"){        	  
 	        	 alert(inputValue.value +"已存在!");
 	        	 return false;
 	        	  }
 	           }       
 		 });
	}
	
function checkTerm0(){
	var inputValue = document.getElementById('term_name');
	 $.ajax({
           type:"POST",
           url:"${pageContext.request.contextPath}/system/checkTerm",
           data:{termName:inputValue.value},
           success:function(data){
        	  if(data=="ok"){        	  
        	 alert(inputValue.value +"已存在!");
        	 $("#term_name").val("");
        	 return false;
        	  }
           }       
	 });
}
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
</head>
<!-- 导航栏 -->
<div class="navigation">
<div id="navigation">
<ul>
<li><a href="">系统管理</a></li>
<li><a href="${pageContext.request.contextPath}/system/listTerm?currpage=1">学期管理</a></li>
<li class="end"><a href="${pageContext.request.contextPath}/system/newTerm">查看学期日历</a></li>
</ul>
</div>
</div>
<!-- 导航栏 -->
<div id="TabbedPanels1" class="TabbedPanels">
	<div class="TabbedPanelsContentGroup">
		<div class="TabbedPanelsContent">
			<div class="content-box">
				<div class="title">查看学期日历
 	<div id="lineDiv" style='position:absolute;z-index:9999;'></div>
<table cellspacing="0" cellpadding="0" style="border-collapse:collapse;">
 	<tr>
 	<!-- <td colspan="2" rowspan="3"></td> -->
 	 <td colspan="2" rowspan="3" id="lineTd" style="border:#000000 solid 1px;width:219px;height:76px;vertical-align:top;" points="[110,79,222,42,222,79]">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;周<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课次&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期<br>星期</td>
 	<c:set var="tempWeek" value="0"/>
 	<c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
 			<c:set var="countWeek" value="0"/>
 			
 		<c:forEach items="${schoolTermWeeks }" var="currWeek2" varStatus="i">
 			<c:if test="${currWeek1[6] == currWeek2[6] }">
 				<c:set var="countWeek" value="${countWeek+1 }"/>
 			</c:if>
 		</c:forEach>
 		<c:if test="${currWeek1 [6] != tempWeek && countWeek>1}">
 			<td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" colspan="${countWeek }">${currWeek1 [6]}月</td>
 		</c:if>
 		<c:if test="${countWeek==1}">
 			<td style="text-align:center;border:#000000 solid 1px;vertical-align:top;">${currWeek1 [6]}月</td>
 		</c:if>
 		<c:set var="tempWeek" value="${currWeek1[6] }"/>
 	</c:forEach>
 	</tr>
 	<tr>
 	<c:set var="tempWeek" value="0"/>
 	<c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
 		<td style="text-align:center;border:#000000 solid 1px;vertical-align:top;">${currWeek1[2] }</td>
 	</c:forEach>
 	</tr>
 	<tr>
 	<c:set var="tempWeek" value="0"/>
 	<c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
 		<td style="text-align:center;border:#000000 solid 1px;vertical-align:top;">
 			<c:if test="${currWeek1[6]==currWeek1[7]}">${currWeek1[8]}- ${currWeek1[9]}</c:if>
 			<c:if test="${currWeek1[6]!=currWeek1[7]}">${currWeek1[6]}/${currWeek1[8]}- ${currWeek1[7]}/${currWeek1[9]}</c:if>
		</td>
 	</c:forEach>
 	</tr>
 	<c:forEach var="class1"  varStatus="cStatus1" begin="${schoolTermWeeks[0][10]}" end="7">
       	<c:forEach var="class2" varStatus="cStatus2" begin="1" end="5">
       		<tr>
       			<c:if test="${cStatus2.index==1}">
       				<c:if test="${cStatus1.index==1}">
			               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;"rowspan="5" class="tbl tbct"  style="width:60px"><span id="className12-13">一</span></td>  
			            </c:if>
			            <c:if test="${cStatus1.index==2}">
			               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" rowspan="5" class="tbl tbct"  style="width:60px"><span id="className12-13">二</span></td>  
			            </c:if>
			            <c:if test="${cStatus1.index==3}">
			               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" rowspan="5" class="tbl tbct"  style="width:60px"><span id="className12-13">三</span></td>  
			            </c:if>
			            <c:if test="${cStatus1.index==4}">
			               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" rowspan="5" class="tbl tbct"  style="width:60px"><span id="className12-13">四</span></td>  
			            </c:if>
			            <c:if test="${cStatus1.index==5}">
			               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" rowspan="5" class="tbl tbct"  style="width:60px"><span id="className12-13">五</span></td>  
			            </c:if>
			            <c:if test="${cStatus1.index==6}">
			               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;"rowspan="5" class="tbl tbct"  style="width:60px"><span id="className12-13">六</span></td>  
			            </c:if>
			            <c:if test="${cStatus1.index==7}">
			               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" rowspan="5" class="tbl tbct"  style="width:60px"><span id="className12-13">日</span></td>  
			            </c:if> 
	               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" class="tbl tbct"  style="width:60px"><span id="className12-13">1-2</span></td>  
	            </c:if>
	            <c:if test="${cStatus2.index==2}">
	               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" class="tbl tbct"  style="width:60px"><span id="className12-13">3-4</span></td>  
	            </c:if>
	            <c:if test="${cStatus2.index==3}">
	               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" class="tbl tbct"  style="width:60px"><span id="className12-13">5-6</span></td>  
	            </c:if>
	            <c:if test="${cStatus2.index==4}">
	               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" class="tbl tbct"  style="width:60px"><span id="className12-13">7-8</span></td>  
	            </c:if>
	            <c:if test="${cStatus2.index==5}">
			            <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" class="tbl tbct"  style="width:60px"><span id="className12-13">晚</span></td> 
	            </c:if>
	            <c:set var="countWeek" value="0"/>
	            <c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
	            	<c:set var="countWeek" value="${countWeek+1 }"/>
	            	<c:if test="${not empty specialSchoolWeeks}">
	            	<c:set var="flag" value="0"/>
	            	<c:set var="dayName" value="0"/>
	            	<c:forEach items="${specialSchoolWeeks }" var="specialWeek1" varStatus="i">
	            		<c:if test="${currWeek1[2] == specialWeek1.week && specialWeek1.weekday == cStatus1.index && cStatus2.index ==1}">
	            			<c:set var="flag" value="1"/>
	            			<c:set var="dayName" value="${specialWeek1.CDictionary.CName}"/>
	            		</c:if>
	            		<c:if test="${currWeek1[2] == specialWeek1.week && specialWeek1.weekday == cStatus1.index && cStatus2.index !=1}">
	            			<c:set var="flag" value="2"/>
	            			<c:set var="dayName" value="${specialWeek1.CDictionary.CName}"/>
	            		</c:if>
			 		</c:forEach>
			 		<c:if test="${flag == 1}">
	            		<td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" rowspan="5">${dayName }</td>
	            	</c:if>
	            	<c:if test="${flag != 1 && flag != 2}">
	            		<td style="text-align:center;border:#000000 solid 1px;vertical-align:top;"></td>
	            	</c:if>
			 		</c:if>
			 		<c:if test="${empty specialSchoolWeeks}">
			 			<td style="text-align:center;border:#000000 solid 1px;vertical-align:top;"></td>
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
			               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;"rowspan="5" class="tbl tbct"  style="width:60px"><span id="className12-13">一</span></td>  
			            </c:if>
			            <c:if test="${cStatus1.index==2}">
			               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" rowspan="5" class="tbl tbct"  style="width:60px"><span id="className12-13">二</span></td>  
			            </c:if>
			            <c:if test="${cStatus1.index==3}">
			               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" rowspan="5" class="tbl tbct"  style="width:60px"><span id="className12-13">三</span></td>  
			            </c:if>
			            <c:if test="${cStatus1.index==4}">
			               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" rowspan="5" class="tbl tbct"  style="width:60px"><span id="className12-13">四</span></td>  
			            </c:if>
			            <c:if test="${cStatus1.index==5}">
			               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" rowspan="5" class="tbl tbct"  style="width:60px"><span id="className12-13">五</span></td>  
			            </c:if>
			            <c:if test="${cStatus1.index==6}">
			               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;"rowspan="5" class="tbl tbct"  style="width:60px"><span id="className12-13">六</span></td>  
			            </c:if>
			            <c:if test="${cStatus1.index==7}">
			               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" rowspan="5" class="tbl tbct"  style="width:60px"><span id="className12-13">日</span></td>  
			            </c:if> 
	               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" class="tbl tbct"  style="width:60px"><span id="className12-13">1-2</span></td>  
	            </c:if>
	            <c:if test="${cStatus2.index==2}">
	               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" class="tbl tbct"  style="width:60px"><span id="className12-13">3-4</span></td>  
	            </c:if>
	            <c:if test="${cStatus2.index==3}">
	               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" class="tbl tbct"  style="width:60px"><span id="className12-13">5-6</span></td>  
	            </c:if>
	            <c:if test="${cStatus2.index==4}">
	               <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" class="tbl tbct"  style="width:60px"><span id="className12-13">7-8</span></td>  
	            </c:if>
	            <c:if test="${cStatus2.index==5}">
			            <td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" class="tbl tbct"  style="width:60px"><span id="className12-13">晚</span></td> 
	            </c:if>
	            <c:set var="countWeek" value="0"/>
	            <c:forEach items="${schoolTermWeeks }" var="currWeek1" varStatus="i">
	            	<c:set var="countWeek" value="${countWeek+1 }"/>
	            	<c:if test="${not empty specialSchoolWeeks}">
	            	<c:set var="flag" value="0"/>
	            	<c:set var="dayName" value="0"/>
	            	<c:forEach items="${specialSchoolWeeks }" var="specialWeek1" varStatus="i">
	            		<c:if test="${currWeek1[2] == specialWeek1.week && specialWeek1.weekday == cStatus1.index && cStatus2.index ==1}">
	            			<c:set var="flag" value="1"/>
	            			<c:set var="dayName" value="${specialWeek1.CDictionary.CName}"/>
	            		</c:if>
	            		<c:if test="${currWeek1[2] == specialWeek1.week && specialWeek1.weekday == cStatus1.index && cStatus2.index !=1}">
	            			<c:set var="flag" value="2"/>
	            			<c:set var="dayName" value="${specialWeek1.CDictionary.CName}"/>
	            		</c:if>
			 		</c:forEach>
			 		<c:if test="${flag == 1}">
	            		<td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" rowspan="5">${dayName }</td>
	            	</c:if>
	            	<c:if test="${flag != 1 && flag != 2}">
	            		<td style="text-align:center;border:#000000 solid 1px;vertical-align:top;"></td>
	            	</c:if>
			 		</c:if>
			 		<c:if test="${empty specialSchoolWeeks}">
			 			<td style="text-align:center;border:#000000 solid 1px;vertical-align:top;"></td>
			 		</c:if>
			 	</c:forEach>
       		</tr>
       	</c:forEach>
     </c:forEach>
     </c:if>
     <tr>
     	<td style="text-align:center;border:#000000 solid 1px;vertical-align:top;">备注</td>
     	<td style="text-align:center;border:#000000 solid 1px;vertical-align:top;" colspan="${countWeek+1}">${schoolTerm.mem }</td>
     </tr>
 </table>
</div>
</div>
</div>
</div>
 <script>
       $(function() {
          var paper = new Raphael("lineDiv");
          paper.path("M0,0L55,192");//坐标(0,0)(110,85)
          paper.path("M0,0L288,193");//坐标(0,0)(110,85)
          paper.path("M0,0L290,130");//坐标(0,0)(220,42)
          paper.path("M0,0L285,65");//坐标(0,0)(220,79)
          var offset = $("#lineTd").offset();//td的位置
           //将画线的DIV移动到td的位置
          $("#lineDiv").offset({top: offset.top, left: offset.left});
       })
    </script>