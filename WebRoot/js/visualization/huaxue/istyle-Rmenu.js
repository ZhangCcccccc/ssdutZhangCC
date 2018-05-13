// JavaScript Document
window.onload=function(){
		loadmenu();
	};
	function loadmenu(){
		var Rmenuspan=$(".istyle-Rmenu span");
		var Rwidth=0;
		$("#menubox").css("z-index",50);
		$(".Rmenu-list>div").hide();
		for(i=0;i<Rmenuspan.length;i++){
			var twidth=$(".Rmenu-list").eq(i).find("span").eq(0).get(0).offsetWidth;
			//var twidth=document.getElementsByClassName("Rmenu-list").item(i).getElementsByTagName("span").item(0).offsetWidth;
			if(twidth>Rwidth)
				Rwidth=twidth;
		}
		Rmenuspan.width(Rwidth+'px');
		Rmenuspan.css("display","block");
		Rmenuspan.attr("expand","false");
		Rmenuspan.next().css("left",Rwidth+11+"px");
		Rmenuspan.next().css("top","0px");
		Rmenuspan.click(function(){
			Rmenuspan.next().hide("slow");
			if($(this).attr("expand")=="false"){
				$(this).next().show("slow");
				Rmenuspan.attr("expand","false");
				Rmenuspan.attr("class","unlist");
				$(this).attr("class","actlist");
				$(this).attr("expand","true");
			}
			else{
				$(this).next().hide("slow");
				$(this).attr("class","unlist");
				$(this).attr("expand","false");
			}
		});
	}