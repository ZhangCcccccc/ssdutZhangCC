<%@ page language="java" pageEncoding="utf-8"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>用户登录已超时</title>
    <style type="text/css" >
	    body{
			text-align: center;
		}
		#sessionOut {
            margin-top: 50px;
            margin-left: 25%;
			padding: 15px 50px;
			width: 600px;
			border: 2px solid green;
			background-color: yellow;
			text-align: center;
		}
		a{
			font-weight:bold;
			font-family:"宋体";
			font-size:18px;
		}
    </style>
  </head>
<body>
<% 
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
/* String  url  =  "http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);  
   
if(request.getQueryString()!=null) 
{   
    url+="?"+request.getQueryString();           
}  */
 request.setAttribute("basePath", basePath);
/* System.out.println("path："+path); 
System.out.println("basePath："+basePath);    
System.out.println("URL："+url);    
System.out.println("URL参数："+request.getQueryString());  */ 
%>

	<div id ="sessionOut">
		您长时间未操作系统，为确保您的资料及数据信息安全，系统自动超时退出！
		<br>请重新<a href="<%=request.getAttribute("basePath")%>" ><font color="red">登录</font></a>系统！
	    <%
	    response.sendRedirect(basePath);
	     %>
	</div>
</body>

<script type="text/javascript">
      //location.reload();
</script>
</html>