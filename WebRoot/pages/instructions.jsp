<%@ page language="java" pageEncoding="utf-8"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>操作手册</title>
    <style type="text/css" >
	    body{
			text-align: center;
		}
		#sessionOut {
            margin-top: 50px;
            margin-left: 25%;
			padding: 15px 50px;
			width: 600px;
			border: 2px solid lightgrey;
			background-color: #eee;
			text-align: center;
		}
		a{
			font-weight:bold;
			font-family:"宋体";
			font-size:18px;
		}
		.download{position:absolute;
			right:0px;
			}
		.download a{font-size:12px;
			color:#555;
			}
			.download a:hover{color:#000;}
			ul{margin:0px;
				padding:0px;}
		#sessionOut ul li{position:relative;
			line-height:30px;
			list-style:none;
			text-align:left;
			font-size:12px;
			}
    </style>
  </head>
<body>


	<div id ="sessionOut">
		<h4>操作手册</h4>
		<ul>
			<li>西安电子科技大学实验室智能管理系统用户操作手册-管理员
				<span class="download">
				<a href="${pageContext.request.contextPath}/pages/instructions/manage.pdf" target="_blank">查看<font color="bule"></font></a>
				<a href="${pageContext.request.contextPath}/system/downloadFile?fileName=manage.pdf">下载</a>
				</span>
			</li>
			<li>西安电子科技大学实验室智能管理系统用户操作手册-教师
				<span class="download">
				<a href="${pageContext.request.contextPath}/pages/instructions/teacher.pdf" target="_blank">查看<font color="bule"></font></a>
				<a href="${pageContext.request.contextPath}/system/downloadFile?fileName=teacher.pdf">下载</a>
				</span>
			</li>
			<li>西安电子科技大学实验室智能管理系统用户操作手册-学生
			<span class="download">
			<a href="${pageContext.request.contextPath}/pages/instructions/student.pdf" target="_blank">查看<font color="bule"></font></a>
	   		<a href="${pageContext.request.contextPath}/system/downloadFile?fileName=student.pdf">下载</a>
	   		</span>
	   	</li>
		</ul>
		<br>
		
		<br>
		
		<br>
		
	</div>
</body>

<script type="text/javascript">
      //location.reload();
</script>
</html>