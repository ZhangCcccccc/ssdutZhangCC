<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.lab-resources"/>
<head>
 <script type="text/javascript">
 $(function(){ 
	var termId=${course.schoolTerm.id}
			$('#test').datagrid({
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'${pageContext.request.contextPath}/getStartTimetableInformation?termId='+termId,
				sortName: 'courseNumber',
				sortOrder: 'desc',
				fit:'true',
				fitColumns:'true',
				idField:'courseNumber',
				remoteSort: false,
				pagination:true,
				rownumbers:true,
					columns:[[
                    {field:'ck',checkbox:true},
                    {title:'课程序号',field:'courseNumber',width:'50',sortable:true},
                    {title:'课程名称',field:'courseName',width:'100',sortable:true},
                    {title:'课程安排',field:'courseArrange',width:'150',sortable:true},
                    {title:'教学班',field:'classesName',width:'100',sortable:true},
                    {title:'教师',field:'teacher',width:'100',sortable:true},
	                {title:'人数',field:'peopleNumber',width:'100',sortable:true},
	                {title:'学分',field:'credit',width:'50',sortable:true},
	                {title:'周时',field:'weekTime',width:'50',sortable:true},
                    {title:'周数',field:'weeks',width:'100',sortable:true},
                    {title:'操作',field:'do',width:'20',sortable:true}
				]],
//-----------------------------------选中进行删除和修改---------------------------------
				toolbar:[//工具栏上定义按钮				           
				           {text:'批量排课',
					        	iconCls:'icon-ok',
					        	handler:function(){
					        	$('#test').datagrid({url:'${pageContext.request.contextPath}/getStartTimetableInformation?termId='+termId});
					        	  }}]
			});
	   //-------------------------------------分页定义------------------------------------
			var p = $('#test').datagrid('getPager');
			$(p).pagination({
				 pageSize: 20,//每页显示的记录条数 
			        pageList: [20,30,40],//可以设置每页记录条数的列表 
			        beforePageText: '第',//页数文本框前显示的汉字 
			        afterPageText: '页    共 {pages} 页', 
			        displayMsg: '当前显示{from}条到{to}条记录   共{total}条记录' 
			});
			//----------------------------查看view（）----------------------------------
			var id;
			function view(){
			   var select = $('#test').datagrid('getSelected');
			   if(select){
				   id =select.ID;
				   window.location='../jsp/viewAppList.jsp?ID='+id;
			   }else{
			    $.messager.alert('warning','请选择一行数据','warning');
			   }
			  }
		});		
 </script>
</head>
<div>
<table id="test"></table></div>