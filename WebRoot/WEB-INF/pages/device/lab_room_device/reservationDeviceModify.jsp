<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<head>
<meta name="decorator" content="ijquery"/>
<script src="${pageContext.request.contextPath}/js/dhtmlx/dhtmlxscheduler.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/js/dhtmlx/ext/dhtmlxscheduler_dhx_terrace.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/dhtmlx/ext/dhtmlxscheduler_year_view.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/dhtmlx/ext/dhtmlxscheduler_minical.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/dhtmlx/ext/dhtmlxscheduler_editors.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/dhtmlx/dhtmlxCombo/dhtmlxcombo.css">
<script src="${pageContext.request.contextPath}/js/dhtmlx/dhtmlxCombo/dhtmlxcombo.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/dhtmlx/dhtmlxscheduler.css" type="text/css" title="no title" charset="utf-8">
<style type="text/css" media="screen"> 
   .panel,.window,.messager-window{
   	position:absolute;
   	background:#fff;
   	border-radius:3px;
   	overflow:hidden;
   	    box-shadow: 0px 0px 5px 0px #CECECE;
   }
   .messager-body,.panel-body,.panel-body-noborder,.window-body{
   	padding:8px 5px;
   	
   	line-height:20px;
   	
   }
   .messager-body span,.panel-body span,.panel-body-noborder span,.window-body span{font-size:12px !important;}
   .panel-title{
   	background:#eee;
   	line-height:30px;
   	padding:0 5px;
   	font-weight:bold;
   }
   .l-btn{width:100px;
   text-align:center;
   display:block;
   margin:10px auto 0px auto !important;
   height: 25px;
       line-height: 25px;
    background: #77bace;
    border-radius: 3px;
    color: #FFF;
    text-decoration: none;
    outline: none;
    font-family: arial;
   }
   
</style>

<script type="text/javascript">

	$(document).ready(function init() {
	    scheduler.ignore_week = function(weekday){// 隐藏掉周末--按周显示时
	         if (weekday.getDay() == 6 || weekday.getDay() == 0)
	             return true;
	     };
	    scheduler.ignore_month = function(weekday){// 隐藏掉周末--按月显示时
	         if (weekday.getDay() == 6 || weekday.getDay() == 0)
	             return true;
	     };
	});

	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}

	var equinemtid=${id};

	// 判断是否是纺织学院
	var isFZ = ${isFZ};

	var year=${year};//年
	var month=${month};//月
	var day=${day};//日
	var hour=${hour};//小时
	var minute=${minute};//分钟
	var second=${second};//秒
	var reservalId = ${reservalId}

	var t=${teachers};//导师
	//var propertys=${propertys};//申请性质
	var a=1028;
	
	$(function(){
		if(isFZ){
			first_hour=8;//插件开始时间
			last_hour=17;//插件开始时间
			$("#name_tip").html("此设备的预约时间范围段是8:15-16:30")
		}else{
			first_hour=8;//插件开始时间
			last_hour=20;//插件开始时间
			// $("#name_tip").html("此设备的预约时间范围段是8:30-20:15")
		}
		
		$.messager.alert("提示","<span style='font-size: 14px; color: red'>在你需要预约的日期上拖动一个时间块区,就是您所要预约的时间!<br>添加之后不能进行修改，请谨慎操作！</span>" );
		scheduler.locale.labels.section_type = "项目名称";
		scheduler.config.lightbox.sections = [	
			{name:"time",  height:72, type:"time",   map_to:"auto"}	
		];
		scheduler.config.prevent_cache = false;
		scheduler.config.first_hour=${first_hour};//插件开始时间
		scheduler.config.last_hour=${last_hour};//插件开始时间
		scheduler.config.time_step=5;//间隔时间（单位：分）
		scheduler.config.api_date="%Y-%m-%d %H:%i";
		scheduler.config.xml_date="%Y-%m-%d %H:%i";
		scheduler.config.hour_date="%H:%i";
		scheduler.config.default_date="%Y年%m月%d日";
		scheduler.config.month_date="%Y年 %m月";
		scheduler.config.day_date="%D %m月%d日";
		scheduler.config.start_on_monday=true;//星期一开始
		scheduler.config.multi_day = false;
		scheduler.config.separate_short_events = true;
		scheduler.config.positive_closing = false;
		scheduler.config.drag_resize = false;
		scheduler.config.drag_move = false;
		scheduler.config.details_on_create = true;
		scheduler.config.positive_closing = true;
		scheduler.config.details_on_dblclick = false; 
		scheduler.config.show_loading = true;
		scheduler.init('scheduler_here',new Date(year,month,day),"week");
		var convert = scheduler.date.date_to_str("%Y-%m-%d %H:%i:%s");
		var dateConvert = scheduler.date.date_to_str('%Y-%m-%d');
		//载入文件

		scheduler.load("${pageContext.request.contextPath}/device/getLabReservation?id="+equinemtid+"&reservalId=${reservalId}","json");  
		scheduler.load("${pageContext.request.contextPath}/device/getLimitLabReservation?id="+equinemtid,"json");  

		scheduler.attachEvent("onEventCancel", function(event_id, is_new_event){
		    scheduler.deleteEvent(event_id);
		});
		scheduler.attachEvent("onDblClick", function (event_id, native_event_object){
		    return false;
		});
		scheduler.attachEvent("onClick", function (event_id, native_event_object){
		    return false;
		});
		scheduler.attachEvent("onEventCreated", function(id,e){
			var sevent = scheduler.getEvent(id)
			
		});
		scheduler.attachEvent("onLightbox", function (id){
			$(".dhx_cal_lsection").each(function(i){
		    	if(i<=2){
		    		$(this).css("color","red")
				}
			})
		});

		//以下代码为绑定事件：增加
		scheduler.attachEvent("onEventAdded", function(event_id,event_object){
			
		    var testss = event_object.text;
		   	// var type=event_object.type;
		   	//获取的是event_object的map_to
		    var typea=event_object.typea;
		    var typef=event_object.typef;
		    
		    //申请性质
		    var property=event_object.property;
		    
		    //开始时间
		     var startdate = convert(event_object.start_date);
		  	//结束时间
		    var enddate = convert(event_object.end_date);
		    
		    var date = dateConvert(event_object.end_date);
		    var sdate = dateConvert(event_object.start_date);
		   
		    var nowdate=dateConvert(new Date(year,month,day,hour,minute,second));    
		    	    
			if(event_object.start_date.Format("yyyy-MM-dd")<new Date(year,month,day,hour,minute,second).Format("yyyy-MM-dd")){
			  	$.messager.alert("提示","<span style='font-size: 14px; color: red'>此时间已过时！</span>" );
			    scheduler.deleteEvent(event_id);
			}else{
				$.post('${pageContext.request.contextPath}/device/saveModifyDeviceReservation',{equinemtid:equinemtid,startDate:startdate,endDate:enddate,property:property,reservalId:reservalId},function(data){
			        if(data == 'success') {
			        	/* if (${device.CActiveByTeacherAudit.id==1&&device.isAuditTimeLimit!=null&&device.isAuditTimeLimit==1&&device.auditTimeLimit!=null */
			        	if (${device.CDictionaryByTeacherAudit.CCategory=='c_active' && device.CDictionaryByTeacherAudit.CCnumber=='1'&&device.isAuditTimeLimit!=null&&device.isAuditTimeLimit==1&&device.auditTimeLimit!=null
			        		&&device.auditTimeLimit!=""}) {
			        		var alertString = ${device.auditTimeLimit}+"";
							alert("预约已提交，需导师在预约后"+alertString+"小时之内审核，若超过时间未审核，则预约失效，需要重新预约。");
						}
			            $.messager.show({
			                title: '消息',
			                msg: "<span style='font-size: 14px'>设备预约成功!<br>页面正在跳转请稍后....</span>",
			                showType: 'fade',
			                timeout: 2000
			            });
			           // window.setTimeout('window.location.reload()',1000);
			            
			        }
			        if(data == 'LIMIT'){
			            $.messager.alert('错误','你预约的时间在实验室禁用期间!','error');
			            scheduler.deleteEvent(event_id);
			            $.post('delReg',{regstamp: regstamp});
			        }
			        if(data == 'error'){
			            $.messager.alert('错误','你预约的时间和其他预约存在时间冲突或者您的预约时间小于当前时间!','error');
			            scheduler.deleteEvent(event_id);
			            $.post('delReg',{regstamp: regstamp});
			        }
			        switch(${flag})
			        {
			        case 1:
				        window.location.href="${pageContext.request.contextPath}/device/teacherReservationAudit?id=${reservalId}";
			          	break;
			        case 2:
			        	window.location.href="${pageContext.request.contextPath}/device/labManagerReservationAudit?id=${reservalId}";
			          	break;
			        case 3:
			        	window.location.href="${pageContext.request.contextPath}/device/managerReservationAudit?id=${reservalId}";
			        	break;
			        case 4:	
			        	window.location.href="${pageContext.request.contextPath}/device/alterTime?id=${reservalId}";
			        	break;
			        default:
			        	window.location.href="${pageContext.request.contextPath}/device/alterAudit?id=${reservalId}&tag=1";
			        }
			    });
			}
		});
	    scheduler.attachEvent('onEventDeleted',function(event_id,event_object){
	        $.post('delReg',{regstamp: event_id}); 
	    });
	 	//如果日期是周六、周日则格子成黄色
		scheduler.templates.scalex_class = function(date){
			if (date.getDay()==0 || date.getDay()==6)  return "yellow_cell";
			return "";
		} 
	  
		scheduler.templates.month_date_class = function(date, today){  
		    var time = new Date(date).getTime();  
		    var from = time;  
		    var to = time + 86400000;  
		    //插件中此方法不兼容ie 要修复一下 修复代码如下  
		     
		    scheduler.getEvents = function(from, to) { 
		        var result = []; 
		        for (var a in this._events) { 
		            try{ 
		                var ev = this._events[a]; 
		                var start = new Date(ev.start_date).getTime(); 
		                var end = new Date(ev.end_date).getTime(); 
		                if((start < to) && (end > from)){ 
		                    result.push(ev); 
		                } 
		            }catch(e){} 
		            //原来代码 
		            //if (ev && ( (!from && !to) || (ev.start_date < to && ev.end_date > from) )) 
		                //result.push(ev); 
		        } 
		        return result; 
		    }; 
		}
	});
       
        
    
</script>
</head>
