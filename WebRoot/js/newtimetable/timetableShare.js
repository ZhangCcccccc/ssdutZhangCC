/****************************************************************************
 * @功能：该js为应对西电排课中的公共部分
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/

// 此处需要按照项目修改
var contextPath = "/xidlims";

/****************************************************************************
 * @功能：返回到排课页面
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/
function turnPage(){
	document.form.action = contextPath+"/newtimetable/listMySchedule?currpage=1";
}

/****************************************************************************
 * @功能：保存本次排课的开始上课时间
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/
function saveActualStartDate(groupId,appointmentId){
	$.ajax({
         url:contextPath+"/newtimetable/saveActualStartDate?actualStartDate="+
         $("#actualStartDate"+appointmentId).val()+"&groupId="+groupId+"&appointmentId="+appointmentId,
         dataType:"json",
         type:'GET',
         complete:function(result)
         {
          }
	});
}

/****************************************************************************
 * @功能：保存本次排课的结束上课时间
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/
function saveActualEndDate(groupId,appointmentId){
	$.ajax({
         url:contextPath+"/newtimetable/saveActualEndDate?actualEndDate="+$("#actualEndDate"+appointmentId).val()+
         "&groupId="+groupId+"&appointmentId="+appointmentId,
         dataType:"json",
         type:'GET',
         complete:function(result)
         {
          }
	});
}

/****************************************************************************
 * @功能：完成某一批次（batch）的排课
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/
function completeBatchTimetable(batchId){
	$.ajax({
         url:contextPath+"/newtimetable/completeBatchTimetable?batchId="+batchId,
         dataType:"json",
         type:'GET',
         complete:function(result)
         {
         	window.location.reload();
         }
	});
}

/****************************************************************************
 * @功能：完成某一课程的排课（不合班）
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/
function completeAll(courseDetailNo){
	$.ajax({
         url:contextPath+"/newtimetable/completeDetailTimetable?courseDetailNo="+courseDetailNo,
         dataType:"json",
         type:'GET',
         complete:function(result)
         {
         	if(result.responseText == "hourFail"){
         		alert("实验学时未排够!");
         	}
         	else{
         		window.location.reload();
          	}
          }
	});
}

/****************************************************************************
 * @功能：修改某一批次（batch）的排课
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/
function modifyBatchTimetable(batchId){
	$.ajax({
         url:contextPath+"/newtimetable/modifyBatchTimetable?batchId="+batchId,
         dataType:"json",
         type:'GET',
         complete:function(result)
         {
         	window.location.reload();
         }
	});
}

/****************************************************************************
 * @功能：修改全部批次（batch）的排课--（此方法需要修改${courseDetailNo}的写法不对）
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/
function modifyAll(){
	$.ajax({
         url:contextPath+"/newtimetable/modifyDetailTimetable?courseDetailNo=${courseDetailNo}",
         dataType:"json",
         type:'GET',
         complete:function(result)
         {
         	window.location.reload();
          }
	});
}

/****************************************************************************
 * @功能：按照分组来发送消息
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/
function sendDetailMessageByGroup(courseDetailNo){
	var obj = document.getElementsByName("checkGroupMessage");
	  var check_val = [];
	  var flag = 0;
	  for(k in obj){
	      if(obj[k].checked)
	      {
	      	flag++;
	       	check_val.push(obj[k].value);
	      }
	  }
	  if(flag == 0){
	  	alert("请至少选择一条记录!");
	  }else{
	  	$.ajax({
		         url:contextPath+"/newtimetable/sendDetailMessageByGroupNotSelfSelect?courseDetailNo="+courseDetailNo,
		         dataType:"json",
		         type:'GET',
		         data:{groups:check_val.join(",")},
		         complete:function(result)
		         {
		         	alert("发送消息完成！");
		         }
		});
	  }
}
/****************************************************************************
 * @功能：按照分组来发送消息(合班)
 * @作者：戴昊宇
 * @Date：2017-10-18
 ****************************************************************************/
function sendDetailMessageByGroupMerge(mergeId){
	var obj = document.getElementsByName("checkGroupMessage");
	  var check_val = [];
	  var flag = 0;
	  for(k in obj){
	      if(obj[k].checked)
	      {
	      	flag++;
	       	check_val.push(obj[k].value);
	      }
	  }
	  if(flag == 0){
	  	alert("请至少选择一条记录!");
	  }else{
	  	$.ajax({
		         url:contextPath+"/newtimetable/sendMergeMessageByGroupNotSelfSelect?mergeId="+mergeId,
		         dataType:"json",
		         type:'GET',
		         data:{groups:check_val.join(",")},
		         complete:function(result)
		         {
		         	alert("发送消息完成！");
		          }
		});
	  }
}

/****************************************************************************
 * @功能：按照分批来发送消息
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/
function sendMessageByBacth(courseDetailNo, batchId){
	$.ajax({
         url:contextPath+"/newtimetable/sendMessageByBacthNotSelfSelect?courseDetailNo="+courseDetailNo+"&batchId="+batchId,
         dataType:"json",
         type:'GET',
         complete:function(result)
         {
         	alert("发送消息完成！");
         }
	});
}

/****************************************************************************
 * @功能：按照课程来发送消息（不合班）
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/
function sendMessageAll(courseDetailNo){
	$.ajax({
         url:contextPath+"/newtimetable/sendMessageAllNotSelfSelect?courseDetailNo="+courseDetailNo,
         dataType:"json",
         type:'GET',
         complete:function(result)
         {
         	alert("发送消息完成！");
         }
	});
}
/****************************************************************************
 * @功能：按照课程来发送消息（合班）
 * @作者：戴昊宇
 * @Date：2017-10-18
 ****************************************************************************/
function sendMessageAllMerge(mergeId){
	$.ajax({
         url:contextPath+"/newtimetable/sendMergeMessageAllNotSelfSelect?mergeId="+mergeId,
         dataType:"json",
         type:'GET',
         complete:function(result)
         {
         	alert("发送消息完成！");
          }
	});
}
/****************************************************************************
 * @功能：删除某一次排课记录
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/
function deleteSpecializedBasicCourseAppointment(appointmentId){
	$.messager.confirm('提示','确定删除？',function(r){
		if(r) {
  			$.ajax({
		         url:contextPath+"/newtimetable/deleteSpecializedBasicCourseAppointment?appointmentId="+appointmentId,
		         dataType:"json",
		         type:'GET',
		         complete:function(result)
		         {
		           window.location.reload();
		         }
			});
		}else {
		}
	});
}

/****************************************************************************
 * @功能：ajax保存教师
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/
function saveTeacher(appointmentId){
    var teachersArray=[];
    $($("#teacher"+appointmentId+" option:selected")).each(function(){
	teachersArray.push(this.value);
	});
	console.log(teachersArray.join(","));
	//教师先不管
	$.ajax({
			url:contextPath+"/newtimetable/saveTimetableAppointmentTeacher",
			type:'POST',
			data:{appointmentId:appointmentId,teacher:teachersArray.join(",")},
			error:function (request){
 			alert('请求错误!');
		 },
		 success:function(result)
			{
			}
	});
}

/****************************************************************************
 * @功能：删除分组下的学生
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/
function deleteThisStudent(studentId){
	$.ajax({
           url:contextPath+"/newtimetable/deleteThisStudent?studentId="+studentId,
           
           type:"POST",
           success:function(data){//AJAX查询成功
                viewStudents(data);  
             var num= $("#groupnum"+data).val()-1;
             $("#groupnum"+data).val(num);
           }
	});
}


/****************************************************************************
 * @功能：删除所有分组（非合班）
 * @作者：贺子龙
 * @Date：2017-10-14
 ****************************************************************************/
function delGroup(courseDetailNo,type, item){	
	$.ajax({
         url:contextPath+"/newtimetable/delSpecializedBasicCourseTimetableSelfGroup?courseDetailNo="+courseDetailNo+"&mergeId=0&type="+type+"&item="+item,
         dataType:"json",
         type:'GET',
         complete:function(result)
         {
        	 location.reload();
          }
	});
	
}

/****************************************************************************
 * @功能：删除所有分组（合班）
 * @作者：戴昊宇
 * @Date：2017-10-18
 ****************************************************************************/
function delGroupMerge(mergeId,type, item){	
	$.ajax({
		url:contextPath+"/newtimetable/delSpecializedBasicCourseTimetableSelfGroup?courseDetailNo=0&mergeId="+mergeId+"&type="+type+"&item="+item,
		dataType:"json",
		type:'GET',
		complete:function(result)
		{
			location.reload();
		}
	});
}



