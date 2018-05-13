<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.coursearrange-resources"/>
<head>
<meta name="decorator" content="iframe"/>
  <link href="${pageContext.request.contextPath}/css/room/muchPress.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript">
  function _w_table_lefttitle_rowspan(_w_table_id,_w_table_mincolnum,_w_table_maxcolnum){   
 if(_w_table_mincolnum == void 0){_w_table_mincolnum=1;}
 if(_w_table_maxcolnum == void 0){_w_table_maxcolnum=_w_table_mincolnum;}
 if(_w_table_mincolnum>_w_table_maxcolnum){
  return "";
 }else{
  var _w_table_splitrow=new Array();
  for(iLoop=_w_table_mincolnum;iLoop<=_w_table_maxcolnum;iLoop++){
   _w_table_onerowspan(iLoop);
  }
 }
 
 function _w_table_onerowspan(_w_table_colnum){
  _w_table_firstrow = 0;//前一列合并区块第一行
  _w_table_SpanNum = 0;//合并单元格时的，单元格Span个数
  _w_table_splitNum = 0;//数组的_w_table_splitrow的当前元素下标
  _w_table_Obj = $(_w_table_id + " tr td:nth-child(" + _w_table_colnum + ")");
  _w_table_curcol_rownum = _w_table_Obj.length-1;//此列最后一行行数
  if(_w_table_splitrow.length==0){_w_table_splitrow[0] = _w_table_curcol_rownum;}
  _w_table_lastrow = _w_table_splitrow[0];//前一列合并区块最后一行
  var _w_table_firsttd;
  var _w_table_currenttd;
  var _w_table_curcol_splitrow=new Array();
  _w_table_Obj.each(function(i){
   if(i==_w_table_firstrow){
    _w_table_firsttd = $(this);
    _w_table_SpanNum = 1;
   }else{
    _w_table_currenttd = $(this);
    if(_w_table_firsttd.html()==_w_table_currenttd.html()){
     _w_table_SpanNum++;
     _w_table_currenttd.hide(); //remove();
     _w_table_firsttd.attr("rowSpan",_w_table_SpanNum); 
    }else{
     _w_table_firsttd = $(this);
     _w_table_SpanNum = 1;
     setTableRow(i-1);
    }
    if(i==_w_table_lastrow){setTableRow(i);}
   }
   function setTableRow(_splitrownum){
    if(_w_table_lastrow<=_splitrownum&&_w_table_splitNum++<_w_table_splitrow.length){
     //_w_table_firstrow=_w_table_lastrow+1;
     _w_table_lastrow=_w_table_splitrow[_w_table_splitNum];
    }
    _w_table_curcol_splitrow[_w_table_curcol_splitrow.length]=_splitrownum;
    if(i<_w_table_curcol_rownum){_w_table_firstrow=_splitrownum+1;}
   }
  });
  _w_table_splitrow=_w_table_curcol_splitrow;
 }
} 
  var status=${status};
    $(function(){
     $("#nopublished").click(function(){
      window.location.href="${pageContext.request.contextPath}/releaseTimetableList?currpage=1";
     });
      $("#published").click(function(){
      window.location.href="${pageContext.request.contextPath}/havedReleasedCourseList?currpage=1";
     });
      $("#print").click(function(){
	$("#my_show").jqprint();
	});
	 if (status==2) {
            $("#c2").attr('class', 'btnpress');
        } else {
            $("#c" + status).attr('class', 'btnpress');
        }
        
        $("#tabC1 tr:nth-child(even)").addClass("c2");
	_w_table_lefttitle_rowspan(".tb",10);
   });  
  	 //导出查询之后的数据
  function exportSelect(){
	 document.form.action="${pageContext.request.contextPath}/exportSearchHavedReleasedCourse";
	 document.form.submit();
}
//查询
function submitSelect(){
 document.form.action="${pageContext.request.contextPath}/searchHavedReleasedCourseList";
	 document.form.submit();
}
  </script>
  </head>
<div>
     <form:form name="form" method="Post" modelAttribute="course">
 <table class="list_form">
    <tr>
        <td>
        <form:select id="term" path="schoolTerm.id">
              <c:forEach items="${terms}" var="term" varStatus="k">
                 <form:option value="${term.id}" label="${term.termName}"/></c:forEach>
              </form:select>
               <form:input id="course_name" path="courseName" onkeyup="value=value.replace(/[^\u4E00-\u9FA5\w]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5\w]/g,''))"/>
               <input type="button" value="查询" id="save" onclick="submitSelect();"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <a onclick="window.history.go(-1)">返回</a></td>   
    </tr>
</table>
</form:form>
</div>
<div>
    <ul id="c">
        <li id="c2" class="sortbtn" style="border-left:1px solid #ccc"><a href="javascript:void(0)" id="nopublished"><fmt:message key="nopublished"/></a></li>
         <li id="c1" class="sortbtn"><a href="javascript:void(0)" id="published"><fmt:message key="published"/></a></li>
    </ul>
</div>
<div  class="l_right">
    <div class="list_top">
        <ul class="top_tittle"><fmt:message key="my.teacherneed"/></ul>
        <ul  class="new_bulid"> <li class="new_bulid_1"><c:choose><c:when test="${newFlag}"><a href="${pageContext.request.contextPath}/exportHavedReleasedCourse"><fmt:message key="navigation.export"/></a></c:when><c:otherwise><a href="javascript:void(0)" onclick="exportSelect();"><fmt:message key="navigation.export"/></a></c:otherwise></c:choose></li></ul>  
        <ul  class="new_bulid"><li class="new_bulid_1"><a onclick="window.history.go(-1)"><fmt:message key="navigation.back"/></a></li></ul>       
    <ul class="new_bulid">
                <li class="new_bulid_1"><a href="javascript:void(0)" id="print">打印</a></li>
            </ul>
    </div>
         <table class="tb" id="my_show"> 
               <thead>
                    <tr>
                       <th><fmt:message key="course.coursenumber.title"/></th>
                       <th><fmt:message key="course.coursename.title"/></th>
                       <th><fmt:message key="course.teacher.title"/></th>
                       <th><fmt:message key="course.academyname.title"/></th>
                        <th><fmt:message key="course.classesname.title"/></th>
                       <th><fmt:message key="course.arrange.title"/></th>
                        <th><fmt:message key="coursedetail.coursetypename.title"/></th>
                       <th><fmt:message key="course.environmentalrequirements.title"/></th>
                       <th><fmt:message key="course.labarrange.title"/></th>
                       <th><fmt:message key="course.release.title"/></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${courseDetails}" var="current"  varStatus="i">	
                        <tr>
                            <td>${current.teacherNeed.course.courseNumber}</td>
                            <td>${current.teacherNeed.course.courseName}</td>
                            <td>${current.teacherNeed.course.userByTeacher.cname}</td>
                            <td>${current.teacherNeed.course.schoolAcademy.academyName}</td>
                            <td>${current.teacherNeed.course.classesName}</td>
                           <td><c:if test="${current.weekday==1}">星期一</c:if>
                           <c:if test="${current.weekday==2}">星期二</c:if>
                           <c:if test="${current.weekday==3}">星期三</c:if>
                           <c:if test="${current.weekday==4}">星期四</c:if>
                           <c:if test="${current.weekday==5}">星期五</c:if>
                           <c:if test="${current.weekday==6}">星期六</c:if>
                           <c:if test="${current.weekday==7}">星期天</c:if>[${current.startWeek}-${current.totalWeeks}] ${current.classroomType}[${current.startClass}-${current.endClass}]</td>
                           <td>${current.courseTypeName}</td>
                           <td>${current.environmentalRequirements}</td>
                           <td><table><tr><th><fmt:message key="course.arrangeweek.title"/></th>
                        <th><fmt:message key="course.allclass.title"/></th>
                        <th><fmt:message key="coursearrange.lab.title"/></th></tr><c:forEach items="${labDetails}" var="curr"  varStatus="j"><c:if test="${curr.courseDetailNo==current.courseDetailNo}"><tr><td>${curr.courseTypeName}</td><td>${curr.evaluationModeName}</td><td>${curr.courseNumber}</td></tr></c:if></c:forEach></table></td>
                      <td>${current.teacherNeed.course.CCourseStatus.name}</td>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
     <c:choose><c:when test='${newFlag}'>
        <div class="pagination">
   <a href="${pageContext.request.contextPath}/havedReleasedCourseList?currpage=${pageModel.firstPage}" target="_self"><fmt:message key="firstpage.title"/></a>				    
	<a href="${pageContext.request.contextPath}/havedReleasedCourseList?currpage=${pageModel.previousPage}" target="_self"><fmt:message key="previouspage.title"/></a>
		第<select onchange="javascript:window.location.href = this.options[this.selectedIndex].value;"><option value="${pageContext.request.contextPath}/havedReleasedCourseList?currpage=${page}">${page}</option><c:forEach begin="${pageModel.firstPage}" end="${pageModel.lastPage}" step="1" varStatus="j" var="current">	
    <c:if test="${j.index!=page}"><option value="${pageContext.request.contextPath}/havedReleasedCourseList?currpage=${j.index}">${j.index}</option></c:if></c:forEach></select>页
	<a href="${pageContext.request.contextPath}/havedReleasedCourseList?currpage=${pageModel.nextPage}" target="_self"><fmt:message key="nextpage.title"/></a>
	<a href="${pageContext.request.contextPath}/havedReleasedCourseList?currpage=${pageModel.lastPage}" target="_self"><fmt:message key="lastpage.title"/></a>
    </div>
    <div class="pagination_desc" style="float: left">
       <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
    <strong><fmt:message key="totalpage.title"/>:${pageModel.totalPage}&nbsp;</strong>
</div>
</c:when><c:otherwise>
<div class="pagination_desc" style="float: left">
       <fmt:message key="total"/><strong>${totalRecords}</strong> <fmt:message key="record"/> 
</div>
</c:otherwise></c:choose>		
</div>


