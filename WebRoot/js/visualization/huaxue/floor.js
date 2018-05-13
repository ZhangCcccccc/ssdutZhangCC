	// JavaScript Document
	var shownnum=0;
	var aniop=0;
	showroom(5);
	shown(0);
	document.getElementById("useper").style.display="none";
	function showroom(speed){
		$(".roomdiv").stop();
		$(".roomdiv").animate({opacity:"1"},speed);
		var roomdiv=$(".roomdiv");
		//var roomdiv=document.getElementsByClassName("roomdiv");
		for(i=0;i<roomdiv.length;i++){
			var op=roomdiv.eq(i).attr("u"+shownnum);
			//var op=roomdiv.item(i).getAttribute("u"+shownnum);
			roomdiv.eq(i).find(".roomimg").animate({opacity:op},5);
			//$(roomdiv.item(i).getElementsByClassName("roomimg")).animate({opacity:op},5);
		}
	}
	
	function shown(num){
		var wi=window.screen.width/2;
		var he=window.screen.height/2;
		shownnum=num;
		$("#buttonbox .actlist").eq(0).attr("class","unlist");
		//document.getElementById("buttonbox").getElementsByClassName("actlist").item(0).setAttribute("class","unlist");
		$("#buttonbox .unlist").eq(shownnum).attr("class","actlist");
		//document.getElementById("buttonbox").getElementsByClassName("unlist").item(shownnum).setAttribute("class","actlist");
		var roomdiv=$(".roomdiv");
		//var roomdiv=document.getElementsByClassName("roomdiv");
		$(".roomtext").css("z-index","0");
		for(i=0;i<roomdiv.length;i++){
			roomdiv.eq(i).find(".roomtitle").eq(0).css("top",parseInt(roomdiv.eq(i).find(".roomimgy").eq(0).get(0).style.top)-38+'px');
			//roomdiv.item(i).getElementsByClassName("roomtitle").item(0).style.top=parseInt(roomdiv.item(i).getElementsByClassName("roomimgy").item(0).style.top)-25+'px';
			roomdiv.eq(i).find(".roomtitle").eq(0).css("left",parseInt(roomdiv.eq(i).find(".roomimgy").eq(0).get(0).style.left)+45+'px');
			//roomdiv.item(i).getElementsByClassName("roomtitle").item(0).style.left=parseInt(roomdiv.item(i).getElementsByClassName("roomimgy").item(0).style.left)+50+'px';
			var tileft=parseInt(roomdiv.eq(i).find(".roomimgy").eq(0).get(0).style.left);
			//var tileft=parseInt(roomdiv.item(i).getElementsByClassName("roomimgy").item(0).style.left);
			var titop=parseInt(roomdiv.eq(i).find(".roomimgy").eq(0).get(0).style.top);
			//var titop=parseInt(roomdiv.item(i).getElementsByClassName("roomimgy").item(0).style.top);
			var piche=roomdiv.eq(i).find(".roomimgy").eq(0).get(0).offsetHeight;
			//var piche=roomdiv.item(i).getElementsByClassName("roomimgy").item(0).offsetHeight;
			var picwi=roomdiv.eq(i).find(".roomimgy").eq(0).get(0).offsetWidth;
			//var picwi=roomdiv.item(i).getElementsByClassName("roomimgy").item(0).offsetWidth;
			var texthe=roomdiv.eq(i).find(".roomtext").eq(0).get(0).offsetHeight;
			//var texthe=roomdiv.item(i).getElementsByClassName("roomtext").item(0).offsetHeight;
			var textwi=roomdiv.eq(i).find(".roomtext").eq(0).get(0).offsetWidth;
			//var textwi=roomdiv.item(i).getElementsByClassName("roomtext").item(0).offsetWidth;
			if(tileft<wi){
				roomdiv.eq(i).find(".roomtext").eq(0).get(0).style.left=tileft+picwi+'px';
				//roomdiv.item(i).getElementsByClassName("roomtext").item(0).style.left=tileft+picwi+'px';
			}
			else{
				roomdiv.eq(i).find(".roomtext").eq(0).get(0).style.left=tileft-textwi+'px';
				//roomdiv.item(i).getElementsByClassName("roomtext").item(0).style.left=tileft-textwi+'px';
			}
			if(titop<he){
				roomdiv.eq(i).find(".roomtext").eq(0).get(0).style.top=titop+'px';
				//roomdiv.item(i).getElementsByClassName("roomtext").item(0).style.top=titop+'px';
			}
			else{
				roomdiv.eq(i).find(".roomtext").eq(0).get(0).style.top=titop-(texthe-piche)+'px';
				//roomdiv.item(i).getElementsByClassName("roomtext").item(0).style.top=titop-(texthe-piche)+'px';
			}
		}
		showroom(900);
		if(shownnum!=0){
			$(".roomimgy").animate({opacity:"1"},5);
			$("#useper").show(500);
		}
		else{
			$(".roomimgy").animate({opacity:"0"},5);
			$("#useper").hide(500);

		}
		var Rmenuspan=$(".istyle-Rmenu span");
		Rmenuspan.attr("expand","false");
		Rmenuspan.attr("class","unlist");
		Rmenuspan.next().hide("slow");
	}
	$(".roomtext").animate({opacity:"0"},500);
	$(".roomtitle").mouseover(function(){
			$(this).stop();
			$(this).animate({opacity:"0.5"},500);
			var tthis=$(this).next().next();
			tthis.stop();
			tthis.next().stop();
			$(tthis.parent().find(".roomimgy")).stop();
			tthis.parent().siblings(".roomimg").animate({opacity:"1"},500);
			tthis.animate({opacity:"0"},500);	
			$(tthis.parent().find(".roomimgy")).animate({opacity:"1"},600);
			//var divtext=document.getElementsByClassName("roomtext");
			tthis.next().css("z-index","9");
			tthis.next().animate({opacity:"1"},500);
	});
	$(".roomtitle").mouseleave(function(){
			$(this).stop();
			$(".roomtext").stop();
			var tthis=$(this).next().next();
			$(this).animate({opacity:"1"},500);
			tthis.stop();
			tthis.next().stop();
			$(".roomtext").css("z-index","0");
			$(tthis.parent().find(".roomimgy")).stop();
			var roomdiv=tthis.parent().get(0);
			var op=roomdiv.getAttribute("u"+shownnum);
			if(shownnum==0)
			$(tthis.parent().find(".roomimgy")).animate({opacity:"0"},500);	
			tthis.animate({opacity:op},500);
			
			$(".roomtext").animate({opacity:"0"},500);
	});
	