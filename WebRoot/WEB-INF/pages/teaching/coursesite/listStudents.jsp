<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>学生名单</title>
    <meta name="decorator" content="none"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!-- import css-->
	<link href="${pageContext.request.contextPath}/css/weike/video.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/weike/discuss.css" rel="stylesheet" type="text/css">
	<style type="text/css" media="screen">
		@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/icon.css");
		@import url("${pageContext.request.contextPath}/js/jquery-easyui/themes/gray/easyui.css");
		@import url("${pageContext.request.contextPath}/css/weike/style.css");
		
	</style> 
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/browse.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mousewheel.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/iStyle_Feelings_Core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/locale/easyui-lang-zh_CN.js"></script>

	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.gif" /> 
	
	<link href="${pageContext.request.contextPath}/css/weike/Untitled-1.css" rel="stylesheet" type="text/css">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.gif" />
	
<script type="text/javascript">
	if (document.getElementById){
		document.write('<style type="text/css">\n')
		document.write('.submenu{display: none;}\n')
		document.write('</style>\n')
	}

	function SwitchMenu(obj){
		if(document.getElementById){
			var el = document.getElementById(obj);
			var ar = document.getElementById("masterdiv").getElementsByTagName("ul"); 
			if(el.style.display != "block"){
				for (var i=0; i<ar.length; i++){
				if (ar[i].className=="submenu") //DynamicDrive.com change
				ar[i].style.display = "none";
				}
				el.style.display = "block";
				}else{
		el.style.display = "none";
			}
		}
	}

	
	$(function() {
		var chapterId=${lesson.wkChapter.id};
  		var lessonId=${lesson.id};
  		//alert("sub"+chapterId)
  		SwitchMenu("sub"+chapterId);
  		$("#lesson"+lessonId).css({ 
		"background-color": "#f3f3f3",
		"border-left": "3px solid #428bca" });
		
		//绑定点击赞的事件
		$("#btn").click(function() {
			$.tipsBox({
				obj: $(this),
				str: "赞+1",
                callback: function() {
                    //alert(5);
                }
			});
			//后台保存赞
			$.ajax({
				type:'get',
				url:'${pageContext.request.contextPath}/savePraise?lessonId=${lesson.id}&category=1',
				dataType:'json',
				success:function(data){
					if(data=='OK'){
						var praise= Number($("#praiseNum").text());
						praise+=1;
						$("#praiseNum").text(praise);
					}		
				}
			});
			
		});
	});
	
	
	;(function($) {
        $.extend({
            tipsBox: function(options) {
                options = $.extend({
                    obj: null,  //jq对象，要在那个html标签上显示
                    str: "+1",  //字符串，要显示的内容;也可以传一段html，如: "<b style='font-family:Microsoft YaHei;'>+1</b>"
                    startSize: "12px",  //动画开始的文字大小
                    endSize: "30px",    //动画结束的文字大小
                    interval: 600,  //动画时间间隔
                    color: "red",    //文字颜色
                    callback: function() {}    //回调函数
                }, options);
                $("body").append("<span class='num'>"+ options.str +"</span>");
                var box = $(".num");
                var left = options.obj.offset().left + options.obj.width() / 2;
                var top = options.obj.offset().top - options.obj.height();
                box.css({
                    "position": "absolute",
                    "left": left + "px",
                    "top": top + "px",
                    "z-index": 9999,
                    "font-size": options.startSize,
                    "line-height": options.endSize,
                    "color": options.color
                });
                box.animate({
                    "font-size": options.endSize,
                    "opacity": "0",
                    "top": top - parseInt(options.endSize) + "px"
                }, options.interval , function() {
                    box.remove();
                    options.callback();
                });
            }
        });
    })(jQuery);
    
    function newChildDiscuss(topDiscuss){
    	$("#newChildDiscuss").window('open');
    	var videoHeight=$("#newChildDiscuss").height();//这两行是处理弹出框的位置，使其居中
		$("#newChildDiscuss").panel("move",{top:$(document).scrollTop() + ($(window).height()-videoHeight) * 0.1});
		$("#topDiscuss").val(topDiscuss);
    }
</script>

</head>
  

<div class="right_content_box" id="divTassignments">
	            <div class="right_content_tit">
               		学生名单
	            </div>
	           <a href="javascript:history.go(-1)"><input type="button" value="返回" class="btn btn-common" ></a>
<table  class="tb" cellspacing="0" id="my_show"> 
<thead>
						    <tr>    
						        <th>学号</th> 
						        <th>姓名</th>
						        <th>班级</th>
						    </tr>
						</thead>
<body>				
		<c:forEach items="${liststudents }" var="current"  varStatus="i">
		<tr>
		<td>${current.user.username}</td>
		<td>${current.user.cname}</td>
		<td>${current.user.schoolClasses.className}</td>
		</tr>
		</c:forEach>					
</body>
</table>
</div>

</html>
