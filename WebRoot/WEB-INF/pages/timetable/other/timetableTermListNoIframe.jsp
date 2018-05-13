<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>
<head>
<meta name="decorator" content="timetable"/>
 <script type="text/javascript">
 /**
 * EasyUI DataGrid根据字段动态合并单元格
 * @param tableID 要合并table的id
 * @param colList 要合并的列,用逗号分隔(例如："name,department,office");
 */
   function fixWidth(percent)  
{  
    return document.body.clientWidth*percent ; //这里你可以自己做调整  
}  
   var termId=${termId};
   var nowWeek=${week};
  /*    var weekday=1; */
 $(function(){
  $(window).resize(function(){  
$('#test').datagrid('resize'); 
});   
$("#ongo").attr("src","${pageContext.request.contextPath}/labClassWeekdays?termId="+termId+"&week="+nowWeek);   
 $('#test').datagrid({
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'${pageContext.request.contextPath}/timetable/searchStartTimetableInformation',
				queryParams:{termId:termId,courseName:''},
				sortName: 'courseNumber',
				sortOrder: 'desc', 
				title:'排课列表',
				idField:'courseNumber',
				fitColumns:'true',
				selectOnCheck: true,
				checkOnSelect: true,
				remoteSort: false,
				pagination:true,
				rownumbers:true,
					columns:[[
                     {field:'ck',width:fixWidth(0.01),checkbox:true },
                    {field:'id',width:fixWidth(0.01),hidden:true},
                    {title:'课程序号',field:'courseNumber',width:fixWidth(0.05),sortable:true},
                    {title:'课程名称',field:'courseName',width:fixWidth(0.1),sortable:true},
                    {title:'学期',field:'termName',width:fixWidth(0.1),sortable:true},
                    {title:'课程安排',field:'courseArrange',width:fixWidth(0.2),sortable:true},
                    {title:'教学班',field:'classesName',width:fixWidth(0.1),sortable:true},
                    {title:'教师',field:'teacher',width:fixWidth(0.05),sortable:true},
	                {title:'人数',field:'peopleNumber',width:fixWidth(0.05),sortable:true},
	                {title:'学分',field:'credit',width:fixWidth(0.05),sortable:true},
	                {title:'周时',field:'weekTime',width:fixWidth(0.05),sortable:true},
                    {title:'周数',field:'weeks',width:fixWidth(0.05),sortable:true},
                    {title:'操作',field:'do',width:fixWidth(0.05),sortable:true}
				]],
//-----------------------------------选中进行删除和修改---------------------------------
				toolbar:[//工具栏上定义按钮				           
				           {text:'批量排课',
					        	iconCls:'icon-ok',
					        	handler:function(){
					         var arr = new Array();
			             var rows = $('#test').datagrid('getSelections');			             
			            for(var i=0; i<rows.length; i++){
				         arr[i]=rows[i].id;
			           }
			           var idList=arr.join("-");
			            $.ajax({
	                 type: "POST",
	                 url: "${pageContext.request.contextPath}/batchSaveTimetable",
	                 data: { //发送给数据库的数据
	        	       idStr:idList
	        	       },
	                 success: function(data) {
	        		   if(data=="hahaha"){
	        		      alert("此次批量排课已结束");
	        		      $("#test").datagrid('reload');
	        		        $("#ongo").attr("src","${pageContext.request.contextPath}/labClassWeekdays?termId="+termId+"&week="+nowWeek);    
	        		   }
	        	   }
	        	   });
					 }}]
			});	
			//-------------------------------------分页定义------------------------------------
			var p = $('#test').datagrid('getPager');
			$(p).pagination({
				 pageSize: 10,//每页显示的记录条数 
			      beforePageText: '第',//页数文本框前显示的汉字 
			      afterPageText: '页    共 {pages} 页', 
			      displayMsg: '当前显示{from}条到{to}条记录   共{total}条记录' 
			});
	//获取生成的搜索框
			var a=$("#testa");
			//将生成好的搜索框放入工具栏
			$(".datagrid-toolbar").append(a);
		 });
/*   $('td[id^="appoint_"]').each(function(){
      var appointName=$(this).attr('id');
    var arr=appointName.split("_");
    $.post("getTimetableImformation",{termId:termId,week:nowWeek,weekday1:arr[1],labId:arr[2],classId:arr[3]},function(data){
       setTimeout(function () { 
      $(this).append(data); 
    }, 8000);
     });
        });

 function doSearch(){ 
			var termId=$("#course_term").val();
			var courseName=$("#course_name").val();
			 var queryParams = $('#test').datagrid('options').queryParams;  
			   queryParams.termId = $("#course_term").val();
	           queryParams.courseName = $("#course_name").val();
		 $('#test').datagrid('options').queryParams=queryParams;     
    $("#test").datagrid('reload');
		}
//开始排课
function startTimetable(index){
 window.location.href="${pageContext.request.contextPath}/timetable/startTimetable?id="+index;
}
//全选
function test() {  
   if($("#clickAll").prop("checked"))
   {
     $('input[id^="weeksss_"]').each(function() {
         $(this).prop("checked", true);
     });
   }
   else
   {
     $('input[id^="weeksss_"]').each(function() {
         $(this).prop("checked", false);
     });           
   }
	 /* $('input[id^="weeksss_"]').each(function(){
	  $(this).attr("checked",true);
	 });  */
	 function doSearch(){ 
			var termId1=$("#course_term").val();
			var courseName=$("#course_name").val();
			 var queryParams = $('#test').datagrid('options').queryParams;  
			   queryParams.termId = $("#course_term").val();
	           queryParams.courseName = $("#course_name").val();
		 $('#test').datagrid('options').queryParams=queryParams;     
    $("#test").datagrid('reload');
    $("#ongo").attr("src","${pageContext.request.contextPath}/labClassWeekdays?termId="+termId1+"&week="+nowWeek);   
		}
		//开始排课
function startTimetable(index){
 window.location.href="${pageContext.request.contextPath}/timetable/startTimetable?id="+index;
}
//取消排课
function cancelAllCourseAppointment(index){
 window.location.href="${pageContext.request.contextPath}/cancelAllCourseAppointment?id="+index;
}
//全选
function test() {  
   if($("#clickAll").prop("checked"))
   {
     $('input[id^="weeksss_"]').each(function() {
         $(this).prop("checked", true);
     });
   }
   else
   {
     $('input[id^="weeksss_"]').each(function() {
         $(this).prop("checked", false);
     });           
   }
   }
//点击确定事件
function sureOnclick(){
  //获取页面所选的学期
  var termId2=$("#course_term").val();
  //获取被选中的周次
   var arr = new Array();
  var weekList="";
     var i=0;
  $("input[name='week']:checked").each(function(){ 
    arr[i]=$(this).val();
    /* weekList+=$(this).val()+"\r\n";  */
    i=i+1;
  });
  if(arr.length==0){
     alert("请选择至少一个周次");
  }else{
   var weekList=arr.join(",");
/*    var queryParams = $('#spdata').datagrid('options').queryParams;  
			   queryParams.termId =termId2;
	           queryParams.week =weekList;
	          /*  queryParams.weekday1=weekday; */
		/*  $('#spdata').datagrid('options').queryParams=queryParams;     
    $("#spdata").datagrid('reload');  */
     $("#ongo").attr("src","${pageContext.request.contextPath}/labClassWeekdays?termId="+termId2+"&week="+weekList);   
 }
}
/* function weekdayFormatter(value){
 var button1='<button onclick="move(value,true)">上</button></br>';
			var button2='</br><button  onclick="move(value,false)">下</button>';
			var val=button1+value+button2;
			 return val;
} */
/* function move(value,isUp) {
  if(value=="星期一"){
    weekday=1;
  }
  if(value=="星期二"){
   weekday=2;
  }
  if(value=="星期三"){
    weekday=3;
  }
  if(value=="星期四"){
   weekday=4;
  }
  if(value=="星期五"){
   weekday=5;
  }
  if(value=="星期六"){
   weekday=6;
  }
  if(value=="星期天"){
   weekday=7;
  }
 /*  alert(isUp); */
 /*    if (isUp) {
     if(weekday==1){
       weekday=7;
      }
      else{
       weekday=weekday-1;
      }
    } else {
    if(weekday==7){
       weekday=1;
      }
      else{
       weekday=weekday+1;
      }
    }
//获取页面所选的学期
  var termId2=$("#course_term").val();
  //获取被选中的周次
   var arr = new Array();
  var weekList="";
     var i=0;
  $("input[name='week']:checked").each(function(){ 
    arr[i]=$(this).val();
    weekList+=$(this).val()+"\r\n"; 
    i=i+1;
  });
  if(arr.length==0){
    weekList=nowWeek;
  }else{
   var weekList=arr.join("-");
 }
  var queryParams = $('#spdata').datagrid('options').queryParams;  
			   queryParams.termId =termId2;
	           queryParams.week =weekList;
	           queryParams.weekday1=weekday;
		 $('#spdata').datagrid('options').queryParams=queryParams;     
    $("#spdata").datagrid('reload');
} */ 
//--------------------------取消课程的------------------------
function cancelCourseAppointment(idKey){
 var r = confirm('确定要删除吗？');
    if(r==true){
     $.ajax({
      type:"POST",
      url: "${pageContext.request.contextPath}/cancelCourseAppointment",
           data:  {id:idKey},
           success:function(data){
            refresh();
           }
    });
    }
}
 function refresh(){
            window.location.reload();
        }
</script>
</head>
<div>

<table id="test" toolbar="#testa"></table>
<div id="testa" style="display:inline;padding-top:3px;">
<form:form modelAttribute="course">
<form:select path="schoolTerm.id" id="course_term">
<c:forEach items="${terms}" var="term" varStatus="k">
<form:option value="${term.id}" label="${term.termName}"/></c:forEach>
</form:select>
<form:input id="course_name" path="courseName"/>
<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查询</a>
</form:form>
</div>
<c:forEach items="${weeks}" var="curr" varStatus="h"><input type="checkbox" name="week" id="weeksss_${curr.id}" value="${curr.week}">${curr.week}周</c:forEach><input type="checkbox" name="clickAll" id="clickAll" onclick="test();"/>全选 
<table></table>
<input type="submit" value="确定" onclick="sureOnclick();"/>（<font color=red>注意：默认显示的是当前周日历。如需查询其他周次，请选择您要查询的周次，确定后在日历中显示</font>）
<table></table>
<br><hr>
<iframe id="ongo" name="ongo" src="about:blank"
            width="100%" onload="this.height=ongo.document.body.scrollHeight"  frameborder="0" border="0" scrolling="no"></iframe>
           <script type="text/javascript">
  /** 
 * JQuery扩展方法，用户对JQuery EasyUI的DataGrid控件进行操作。 
 */  
$.fn.extend({  
    /** 
     * 修改DataGrid对象的默认大小，以适应页面宽度。 
     *  
     * @param heightMargin 
     *            高度对页内边距的距离。 
     * @param widthMargin 
     *            宽度对页内边距的距离。 
     * @param minHeight 
     *            最小高度。 
     * @param minWidth 
     *            最小宽度。 
     *  
     */  
    resizeDataGrid : function( widthMargin, minWidth) {  
       /*  var height = $(document.body).height() - heightMargin;   */
        var width = $(document.body).width() - widthMargin;  
  
 /*        height = height < minHeight ? minHeight : height;   */
        width = width < minWidth ? minWidth : width;  
  
        $(this).datagrid('resize', {  
            width : width  
        });  
    }  
});  

$(function() { 
    //datagrid数据表格ID 
    var datagridId = 'test';  
    // 第一次加载时自动变化大小  
    $('#' + datagridId).resizeDataGrid(20, 800);  
    // 当窗口大小发生变化时，调整DataGrid的大小  
    $(window).resize(function() {  
        $('#' + datagridId).resizeDataGrid(20, 800);  
    });  
});
 </script>
</div>
