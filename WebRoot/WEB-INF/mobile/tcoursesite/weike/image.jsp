<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="none" name="decorator">
    <meta charset="utf-8">
<!--控制默认缩放大小、最小缩放大小、最大缩放大小。通过这个可以设置用户的最大和最小缩放程度。-->
<meta name="viewport" content="width=device-width,user-scalable=no"/>
<!--//表示手机模式设置为启用。-->
<meta name="format-detection" content="telephone=no"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mobile/lessons.css"/>
<title>课程内容</title>
<style>
#buttonbox a{
		width:30% !important
	}

.pic{    text-align: center;}
.pic img{
 width:94% !important;
 margin:auto;
}
</style>
</head>
  <body>
	<div id="conteiner">
       	<%-- <div id="picbox">
       	
       	<c:forEach items="${pics}" var="pic" varStatus="i">
				<div class="pic"><c:if test="${i.count==1}">
			<div class="cr-content-container" id="content-${i.count }" style="display:block;">
			<img src="${pageContext.request.contextPath}${pic.url}"></img>
			<!-- </div> -->
		</c:if>
		<c:if test="${i.count>1}">
			<div class="cr-content-container" id="content-${i.count }" >
			<img src="${pageContext.request.contextPath}${pic.url}"></img>
		<!-- </div> -->
		</c:if></div>
		</c:forEach>
        </div>
        <div id="picindex">
        </div> --%>
        
        
        <div id="conteiner">
        	<div id="buttonbox">
	        	<a href="${pageContext.request.contextPath}/tcoursesite/weike/video?tCourseSiteId=${tCourseSite.id}&folderId=${wkFolder.id}" >视频</a>
				<a href="${pageContext.request.contextPath}/tcoursesite/weike/image?tCourseSiteId=${tCourseSite.id}&folderId=${wkFolder.id}" >图片</a>
				<%--<a href="${pageContext.request.contextPath}/lesson/${lesson.id}/deep" > 扩展阅读</a>
				<a href="${pageContext.request.contextPath}/lesson/${lesson.id}/testStart">做题</a>
	        	--%>
	        	<a href="${pageContext.request.contextPath}/tcoursesite/weike/site?tCourseSiteId=${tCourseSite.id}">返回</a>
	        </div>
       	<div id="picbox">
			<c:forEach items="${images}" var="pic" varStatus="i">
				<div class="pic">
            		<img src="${pageContext.request.contextPath}${pic.url}"></img>
            	</div>
			</c:forEach>
        </div>
         <div stime="300" showen="true" id="picmess" class="message">
        	触摸图片查看大图
        </div>
        <div stime="300" showen="true" id="picindex" class="message">
       	</div>
	</div>
	 <!-- 分享开始 -->
	<div class="jiathis_style_32x32" style="display:block;bottom:0px;right:1px!important;right:18px;line-height:30px;position:fixed;text-align:center;color:#fff; padding-right:10px;">
		<a class="jiathis_button_qzone"></a>
		<a class="jiathis_button_tsina"></a>
		<a class="jiathis_button_tqq"></a>
		<a class="jiathis_button_weixin"></a>
		<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
		<a class="jiathis_counter_style"></a>
	</div>
	<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1400041722306662" charset="utf-8"></script>
	<!-- 分享结束 -->
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile/jquery-1.11.0.min.js"></script>
<script>
	var startX;
	var startY;
	var x=0;
	var y=0;
	var moved=false;
	var picindex=0;
	var pnum=document.getElementsByClassName("pic").length;
	document.getElementById("picindex").innerHTML="第"+(picindex+1)+"张/共"+pnum+"张";
	document.getElementsByClassName("pic").item(0).style.display="block";
	$("#bigmes").animate({opacity:"0"},5);	
	var actionwidth=640/12;
	var clientwidth=document.body.clientWidth;
	var changed=false;
	//document.getElementsByClassName("message").item(0).setAttribute(
	var opp=setInterval(function(){
		var mess=document.getElementsByClassName("message");
		for(i=0;i<mess.length;i++){
			if(parseInt(mess.item(i).getAttribute("stime"))>0){
				mess.item(i).setAttribute("stime",parseInt(mess.item(i).getAttribute("stime"))-1);
				if(mess.item(i).getAttribute("showen")=="false"){
					$(mess.item(i)).stop();
					$(mess.item(i)).animate({opacity:"1"},5);	
					mess.item(i).setAttribute("showen","true");
				}
			}
			else{
				if(mess.item(i).getAttribute("showen")=="true"){
					$(mess.item(i)).stop();
					$(mess.item(i)).animate({opacity:"0"},500);	
					mess.item(i).setAttribute("showen","false");
				}
			}
		}
	},3);
	function touchStart(event){  
		event.preventDefault(); //阻止触摸时浏览器的缩放、滚动条滚动等  
		var touch = event.targetTouches[0]; //获取第一个触点
		var x = Number(touch.pageX); //页面触点X坐标  
		var y = Number(touch.pageY); //页面触点Y坐标  
//记录触点初始位置  
		startX = x;  
		startY = y; 
	}
	window.onresize=function(){
		clientwidth=document.documentElement.clientWidth;
		actionwidth=clientwidth/6;
		resize();
		//pospic();
	}
	function touchMove(event){
		//阻止触摸时浏览器的缩放、滚动条滚动等
		moved=true;
		var ispbc=false;
		if(this.getAttributeNode("id").value=="picbox")
			ispbc=true;	
		var touch = event.targetTouches[0]; //获取第一个触点
		var lenth= event.targetTouches.length;
		x = Number(touch.pageX); //页面触点X坐标 
		y = Number(touch.pageY); //页面触点Y坐标
		showmes("picmess",300);
		showmes("picindex",300);
		if(ispbc){
			event.preventDefault(); 
			if(((startX-x)>actionwidth)&&!changed){
				if(picindex<pnum-1){
					$(document.getElementsByClassName("pic").item(picindex)).hide(500);
					picindex++;
					//picindex=pnum-1;
					document.getElementById("picindex").innerHTML="第"+(picindex+1)+"张/共"+pnum+"张";
					$(document.getElementsByClassName("pic").item(picindex)).show(500);
					
					changed=true;
					pospic();
				}
				//picalign(picindex);
			}
			if(((x-startX)>actionwidth)&&!changed){
				if(picindex>0){
				$(document.getElementsByClassName("pic").item(picindex)).hide(500);
				picindex=picindex-1;
				document.getElementById("picindex").innerHTML="第"+(picindex+1)+"张/共"+pnum+"张";
				$(document.getElementsByClassName("pic").item(picindex)).show(500);
				changed=true;
				pospic();
				}
				//picalign(picindex);
			}
			/*if(y>startY&&scrollY>-2)
					scrollY=scrollY-1;
			if(startY>y&&scrollY<100)
					scrollY=scrollY+1;
			window.scrollTo(1,scrollY);*/
		}
		else{
			var my=Math.round((y-startY)/actionwidth*2.5);
			if(my>3)
				my=3;
			if(my<-3)
				my=-3;
			var mx=Math.round((x-startX)/actionwidth*2.5);
			if(mx>3)
				mx=3;
			if(mx<-3)
				mx=-3;
			if(y>startY){
				document.getElementById("picc").style.top=(document.getElementById("picc").offsetTop+my)+'px';}
			if(y<startY)
				document.getElementById("picc").style.top=(document.getElementById("picc").offsetTop+my)+'px';
			if(x>startX){
				//alert("abb");
				document.getElementById("picc").style.left=(document.getElementById("picc").offsetLeft+mx)+'px';
			}
			if(x<startX)
				document.getElementById("picc").style.left=(document.getElementById("picc").offsetLeft+mx)+'px';		
		}
	}
	function touchEnd(event){
		//event.preventDefault(); //阻止触摸时浏览器的缩放、滚动条滚动等
		var ispbc=false;
		var isclick=false;
		if((startX-x)>-5&&(startX-x)<5&&(startY-y)>-5&&(startY-y)<5)
			isclick=true;
		if(this.getAttributeNode("id").value=="picbox")
			ispbc=true;
		if(ispbc){
			if(isclick||!moved){
				var picb=document.createElement("div");
				picb.style.width="100%";
				picb.style.height="100%";
				picb.style.position="absolute";
				picb.style.left="0px";
				picb.style.top="0px";
				picb.id="picb";
				picb.style.overflow="hidden";
				picb.style.zIndex="0";
				picb.style.backgroundColor="black";
				var picc=document.createElement("img");
				picc.src=document.getElementsByClassName("pic").item(picindex).getElementsByTagName("img").item(0).src;
				picc.style.position="relative";
				picc.id="picc";
				picb.addEventListener("touchstart",touchStart,false);
				picb.addEventListener("touchmove",touchMove,false);
				picb.addEventListener("touchend",touchEnd,false);
				picb.appendChild(picc);
				var picmes=document.createElement("div");
				picmes.setAttribute("stime","0");
				picmes.setAttribute("showen","false");
				picmes.setAttribute("id","bigmes");
				picmes.setAttribute("class","message");
				picmes.innerHTML="单击返回/按住拖动";
				picb.appendChild(picmes);
				document.body.appendChild(picb);
				showmes("bigmes",400);
			}
		}
		else{
			if(isclick||!moved)
				document.body.removeChild(this);
				showmes("picmess",300);
				showmes("picindex",300);
		}
		moved=false;	//alert(document.getElementsByClassName("pic").item(picindex).getElementsByTagName("img").item(0).src);
		changed=false;
	}
	document.getElementById("picbox").addEventListener("touchstart",touchStart,false);
	document.getElementById("picbox").addEventListener("touchmove",touchMove,false);
	document.getElementById("picbox").addEventListener("touchend",touchEnd,false);
	
	//alert(document.getElementById("buttonbox").offsetHeight);
	
	function pospic(){		
		var wid=document.documentElement.clientWidth;
		var hei=document.documentElement.clientHeight-document.getElementsByTagName("a").item(0).offsetHeight;
		var ph;
		var pw;
		document.getElementById("picmess").style.top=document.getElementsByTagName("a").item(0).offsetHeight+'px';
		var thepic=document.getElementsByClassName("pic").item(picindex).getElementsByTagName("img").item(0);
		var bpic=thepic.offsetHeight/thepic.offsetWidth;
		var bclient=hei/wid;
		//alert("bpic"+bpic+"bclient"+bclient);
		if(bpic<bclient){
			thepic.style.cssText="width:"+wid+'px';
			ph=(hei-thepic.offsetHeight)/2;
			thepic.style.cssText="position:relative;top:"+ph+"px;width:"+wid+"px";
			}
			else{
				thepic.style.cssText="height:"+hei+'px';
				pw=(wid-thepic.offsetWidth)/2;
				thepic.style.cssText="position:relative;left:"+pw+"px;height:"+hei+"px";
			}
	}
	function resize(){
		var width=document.documentElement.clientWidth;
		document.getElementById("conteiner").style.width='100%';
		document.getElementById("conteiner").style.height='100%';
		document.getElementsByTagName("html").item(0).style.fontSize=120*width/640+'%';
		pospic();
	}
	function showmes(id,time){
		document.getElementById(id).setAttribute("stime",time);
	}
	window.onload=function(){resize()};
</script>
