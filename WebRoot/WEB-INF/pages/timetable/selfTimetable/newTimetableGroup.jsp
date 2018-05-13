<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="now" class="java.util.Date" />   
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
<meta name="decorator" content="iframe"/>
<title>我要排课</title>
<link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />

<!-- 下拉的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->	

</head>
<body>

<div class="iStyle_RightInner">
<br>
<hr>
<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<table  > 
<thead>
<tr>
  <!--  <th>选择</th> -->
  <th >选课组编号</th>
     <td >
         ${courseCode}
     </td>
     <th>选课人数     </th>
     <td>
       ${courseCodes.timetableCourseStudents.size()}
     </td>
     <th >课程名称 </th>
     <td >
         ${courseCodes.schoolCourseInfo.courseName}
     </td>
</tr>
<tr>
    <%--  <th >项目编号 </th>
     <td >
         ${item.itemNumber}
     </td>
     <th >项目名称 </th>
     <td >
         ${item.itemName}
     </td> --%>
     <th >授课教师</th>
     <td >
         ${courseCodes.user.cname}
     </td>
     <th>所属学院     </th>
     <td>
       ${courseCodes.schoolAcademy.academyName}
     </td>
     <th>所属学期</th>
   <td>${courseCodes.schoolTerm.termName} </td>
</tr>
</thead>
</table>
</div>
</div>
</div>
</div>
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/selfTimetable/saveTimetableGroup" target=_self>

<%-- <div class="right-content">
<form name="form" method="Post" action="${pageContext.request.contextPath}/timetable/selfTimetable/saveTimetableGroup" target=_self>
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<table  > 
<thead>
<tr>
  <!--  <th>选择</th> -->
  <th colspan="2">选择相关的实验项目(<font color="red">*</font>为必填)</th>
</tr>
</thead>
<tbody>
<tr>
   <td>实验项目选择(<font color="red">*</font>)</td>
   <td>
      <select class="chzn-select" data-placeholder="选择实验项目" multiple="multiple" name="item" id="item" style="width:550px"  required="true">
	   <option value ="-1" selected> ${courseCodes.schoolCourseInfo.courseName}</option>
	   
	   <c:forEach items="${courseCodeItemList}" var="current"  varStatus="i">	
	       <option value ="${current.id}"> ${current.itemNumber},${current.itemName}</option>
	   </c:forEach>
	  </select>
   </td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
</div> --%>
<input type="hidden" name="item" value="-1">

<div class="right-content">
<div id="TabbedPanels1" class="TabbedPanels">
<div class="TabbedPanelsContentGroup">
<div class="TabbedPanelsContent">
<div class="content-box">
<!--分组的计数  -->
<input type="hidden" name="countGroup" value="${countGroup }">
<!--批次名称  -->
<input type="hidden" name="batchName" value="${batchName}">
<!--选课形式  -->
<input type="hidden" name="ifselect" value="${ifselect}">
<!--开始选课时间 -->
<input type="hidden" name="startDate" value="${startDate}">
<!--结束选课时间  -->
<input type="hidden" name="endDate" value="${endDate}">

<input type="hidden" name="term" value="${courseCodes.schoolTerm.id}">
<input type="hidden" name="labroom" value="${labroom }">
<input type="hidden" name="item" value="${item.id }">
<!--选课组编号  -->
<input type="hidden" name="courseCode" value="${courseCode}">
<!--分组计数  -->
<input type="hidden" name="countGroup" value="${countGroup}">
<!--选课形式  -->
<input type="hidden" name="ifselect" value="${ifselect}">
<table > 
<thead>
<tr>
  <!--  <th>选择</th> -->
   <th colspan=3>对选定的选课组进行分组处理</th>
    <th></th>  
</tr>
</thead>
<tbody>

</tbody>
</table>

<table> 
<thead>

<tr>
  <!--  <th>选择</th> -->
   <th>序号</th>
   <th>组名</th>
   <th>人数</th>
   <c:if test="${ifselect==2 }">
  <th> 勾选学生</th>
   </c:if>
   <th>选课开始日期</th>
   <th>选课结束日期</th>
   <th>选课形式</th>
</tr>
</thead>
<tbody>

<c:forEach var="current" begin="1" end="${countGroup }" step="1" >
<tr>

<td>${current}</td>
<td>
<input type="text" name="groupName" value="第${current }组"></td>
<td>
<c:if test="${current==countGroup }">
<input type="text" name="numbers" value='<fmt:parseNumber value="${courseCodes.timetableCourseStudents.size()-courseCodes.timetableCourseStudents.size()/countGroup*(countGroup-1)}" integerOnly="true"/>'>
</c:if>
<c:if test="${current!=countGroup }">
<input type="text" name="numbers" value='<fmt:parseNumber value="${courseCodes.timetableCourseStudents.size()/countGroup}" integerOnly="true"/>'>
</c:if>
</td>
<c:if test="${ifselect==2 }">
<td><select id="student_${current}" name="student_${current}" class="chzn-select" multiple="multiple"  onChange="listStudents(this.id)">
		<c:forEach items="${students}" var="curr">
			<option value="${curr.id}">${curr.user.cname}</option>
		</c:forEach>
	</select>
</td>
</c:if>
<td>${startDate }</td>
<td>${endDate}</td>
<c:if test="${current==1 }">
   <td rowspan="${countGroup}" >
      <c:if test="${ifselect==0 }">
                自动选课 
      </c:if>
      <c:if test="${ifselect==1 }">
               学生选课      
      </c:if>
      <c:if test="${ifselect==2 }">
                录入
      </c:if>
   </td>
</c:if>
</tr>
</c:forEach>

</tbody>
</table>
<br>
<hr>
<a href="javascript:void(0)"><input type="button" value="确定" onclick="checkNum()"></a>&nbsp;
<a href="${pageContext.request.contextPath}/timetable/selfTimetable/doIframeGroupSelfTimetable?term=${courseCodes.schoolTerm.id}&courseCode=${courseCodeId}"><input type="button" value="返回" ></a>
</div>
</div>
</div>
</div>
</div>
</form>
</div>
</div>
</div>
<hr>

<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select'           : {search_contains:true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
    function isVailid() { 
	    if(parseInt($("#endDate").val().replace(/\-/g,""))<parseInt($("#startDate").val().replace(/\-/g,""))){
	        alert($("#endDate").val().replace(/\-/g,"")+"输入的起始日期大于结束日期！"+$("#startDate").val().replace(/\-/g,""));
	        return false;
	    }
	    else{
	    	document.form.action="${pageContext.request.contextPath}/timetable/selfTimetable/saveTimetableGroup";
	        document.form.submit();
	    }
    }
    function checkNum(){
    	var ifselect = ${ifselect};
    	if(ifselect == 2){
    		var flag = 1;
        	var number = document.getElementsByName("numbers");
        	for(var i=0; i<number.length; i++){
    				var a = new Array();
    				var count=0;
    				var k = i+1;
    				var selected = document.getElementById("student_"+k);
    				for(var j=0;j<selected.length;j++){  
    		    		if(selected.options[j].selected == true){
    		    			count++;
    		    		}  
    		    	}
    				if(count != number[i].value){
    					flag = 0;
    				}
        	}
        	if(flag == 0){
        		alert("选择人数与分组人数不匹配，请重新调整！")
        	}
        	else{

        		document.form.action="${pageContext.request.contextPath}/timetable/selfTimetable/saveTimetableGroup";
                document.form.submit();
        	}
    	}
    	else{
    		document.form.action="${pageContext.request.contextPath}/timetable/selfTimetable/saveTimetableGroup";
            document.form.submit();
    	}
    }
    function listStudents(x){
    	var selectedComs = document.getElementById(x);
    	var count = 0;
    	//当前选中的select
    	var students = new Array();
    	for(var i=0;i<selectedComs.length;i++){  
    		if(selectedComs.options[i].selected == true){
    			students[count]=selectedComs.options[i].value;
    			//alert(students[count]);
    			count++;
    			
    		}  
    	}  
		var countGroup = ${countGroup};
		var id = ${courseCodeId}; 
		var s = {"students":students};
		var currSelect;
		//其余全部select,被选中的值
		for(var i=1;i<=countGroup;i++){
				var a = new Array();
				count=0;
				var selected = document.getElementById("student_"+i);
				for(var j=0;j<selected.length;j++){  
		    		if(selected.options[j].selected == true){
		    			a[count]=selected.options[j].value;
		    			//alert(students[count]);
		    			count++;
		    		}  
		    	}
				s["student_"+i]=a;
		}
		$.ajax({
            url:'${pageContext.request.contextPath}/timetable/selfTimetable/removeDuplicateStudents?id='+id+'&countGroup='+countGroup,
            type:'POST',
           data:s, 
           error:function (request){
             alert('请求错误!');
           },
           success:function(data){
        	   for(var i=1; i <= countGroup; i++){
    				   //alert(1);
    				   $("#student_"+i).html(data["student_"+i]);
   		            	$("#student_"+i).trigger("liszt:updated");
    		   }
           }         
     });
		/*$.ajax({
            url:'${pageContext.request.contextPath}/timetable/selfTimetable/removeDuplicateStudents?id='+${id},
            type:'POST',
           data:{"students":students}, 
           error:function (request){
             alert('请求错误!');
           },
           success:function(data){
        		   for(var i=1; i < countGroup; i++){
        			   if("student_"+i != x){
        				   $("#student_"+i).html(data.operationItems);
       		            	$("#student_"+i).trigger("liszt:updated");
        			   }
        		   }
           }         
     }); */
     
	}
</script>
<!-- 下拉框的js -->
</body>
</html>