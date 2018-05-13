//基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
var days = [];
$.ajax({
	async:false,
	type: "POST",
	url: $("#contextPath").val()+"/tcoursesite/nearSevenDayOrMoon",
	data: {'dayOrMoon':day,'daySize':7},
	dataType:'json',
	success:function(data){
		$.each(data,function(key,values){  
			days.push(values)
		 }); 
	},
	error:function(){
		alert("信息错误！");
		}
});

alert(days);
// 指定图表的配置项和数据
var option = {
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    legend: {
	        data:['访问量','访问的用户']
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : days
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'访问量',
	            type:'bar',
	            data:[86, 101, 96, 102, 167, 160, 157, 101, 96, 102, 167, 160, 157, 101, 96, 102, 167, 160, 157, 101, 96, 102, 167, 160, 101, 96, 102, 167, 160],
	            
	        },
	        
	        {
	            name:'访问的用户',
	            type:'bar',
	            data:[32, 33, 30, 33, 39, 33, 32, 101, 96, 102, 167, 160, 157, 101, 96, 102, 167, 160, 157, 101, 96, 102, 167, 160, 157, 101, 96, 102, 167, 160],
	            
	        },
	        
	    ]
	};
// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);