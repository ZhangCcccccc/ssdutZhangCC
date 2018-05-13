<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
<meta content="none" name="decorator">
<!--控制默认缩放大小、最小缩放大小、最大缩放大小。通过这个可以设置用户的最大和最小缩放程度。-->
<meta name="viewport" content="width=device-width,user-scalable=no"/>
<!--//表示手机模式设置为启用。-->
<meta name="format-detection" content="telephone=no"/>
<title>微课手机首页</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery-1.7.1.min.js" ></script>

</head>
<body>
	<img style="width:100%;height: 100%;" src="${pageContext.request.contextPath}/images/mobile/starry.jpg">
</body>
</html>
<SCRIPT>
	var userHeight=screen.height;
	var userWidth=screen.width;
	//根据用户屏幕的比例，选择不同的启动画面
	
	//alert(userHeight+"--"+userWidth);//显示器分辨率，只能用JavaScript代码获取
	$(document).ready(function() {  
            function jump(count) {  
                window.setTimeout(function(){  
                    count--;  
                    if(count > 0) {  
                        $('#num').attr('innerHTML', count);  
                        jump(count);  
                    } else {  
                        location.href="course";  
                    }  
                }, 1000);  
            }  
            jump(3);  
        });  
</SCRIPT>
