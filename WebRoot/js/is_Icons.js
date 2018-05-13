// JavaScript Document
$(document).ready(function(){
	is_IconLoad();
	});

function is_IconLoad(){
	$(".is_Icons").css({"position":"relative","overflow":"hidden"});
	$(".is_Icons img").css({"position":"absolute"});
	var num=$(".is_Icons").length;
	for(i=0;i<num;i++){
		var tsize=is_IconResize(i);
		is_IconSettype(i,tsize);
		}
	}
	
function is_IconResize(num){
	var tsize=$(".is_Icons:eq("+num+")").attr("size");
	switch(tsize){
		case "big":
			tsize=69;
			break;
		case "min":
			tsize=46;
			break;
		case "small":
			tsize=29;
			break;
		case "auto":
			tsize=$(".is_Icons:eq("+num+")").parent().height();
			break;
		default:
			parseInt(tsize);
		}
	$(".is_Icons:eq("+num+") img").css("width",tsize/46*940+"px");
	$(".is_Icons:eq("+num+")").css({"width":tsize+"px","height":tsize+"px"});
	return tsize;
	}
		
//根据图表序号载入图标，详表见文档JPG文件
function is_IconSettype(num,tsize){
	var type=$(".is_Icons:eq("+num+")").attr("type")-1;
	var ip_y=Math.floor(type/20);
	var ip_x=type-ip_y*20;
	var replace_num=47*tsize/46;
	$(".is_Icons:eq("+num+") img").css({"top":-replace_num*ip_y+"px","left":-replace_num*ip_x+"px"});
	}