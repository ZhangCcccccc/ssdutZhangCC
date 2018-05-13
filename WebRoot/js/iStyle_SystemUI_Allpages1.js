// JavaScript Document
$(document).ready(function(){
	iStyle_SystemUI_Resize();
	$(".is_Leaves").mousedown(iStyle_SystemUI_LinksClick);
	$(".is_SlideButton").mousedown(iStyle_SystemUI_ResizeLeftlist);
	$(".iStyle_Tags .is_Icons").mousedown(iStyle_SystemUI_DelTags);
	$(".iStyle_Mark").mousedown(iStyle_SystemUI_ActiveMark);
	$(".iStyle_Mark").mousedown(iStyle_SystemUI_MarkLinksClick);
	$(".iStyle_Iframe iframe").load(iStyle_IframeJSReligious);
	});
$(window).resize(iStyle_SystemUI_Resize);
function iStyle_SystemUI_Resize(){
	$(".iStyle_RightInner").width($(document).width()-$(".is_LeftList").width()-$(".is_SlideButton").width()+"px");
	$(".iStyle_Iframe").height($(".iStyle_RightInner").height()-$(".iStyle_Header").height()-$(".iStyle_Searchfeild").height()-$(".iStyle_Message").height()-$(".Header").height()-$(".navigation").height()-1+"px");
	$(".iStyle_Header").width($(".iStyle_RightInner").width()+$(".is_SlideButton").width()+"px");
	$(".iStyle_Iframe").width($(".iStyle_RightInner").width()-$(".is_SlideButton").width()+"px");
	}
function iStyle_SystemUI_ResizeLeftlist(speed){
//判断左侧栏展开状态，如果展开，则收起，清除分类标题单击事件，赋予鼠标移上后显示内容事件
	if(speed!=0){
		speed="slow";
		}
	var l_width=$(".is_TreeButtons").attr("t_width");
	if(!$(".is_TreeButtons").hasClass("is_Slided")){
		$(".iStyle_RightInner").animate({width:$(document).width()-l_width-$(".is_SlideButton").width()+"px"},speed);
		$(".iStyle_Iframe").animate({width:$(document).width()-l_width-$(".is_SlideButton").width()*2+"px"},speed);
		}
	else{
		$(".iStyle_RightInner").animate({width:$(document).width()-$(".is_TreeTitle").height()-$(".is_SlideButton").width()+"px"},speed);
		$(".iStyle_Header").animate({width:$(document).width()-$(".is_TreeTitle").height()+"px"},0);
		$(".iStyle_Iframe").animate({width:$(document).width()-$(".is_TreeTitle").height()-$(".is_SlideButton").width()*2+"px"},speed);
		}
	}

function iStyle_IframeJSReligious(){
	clearInterval();
	$(window.frames[0].document.body).find(".panel").css('width',"100%");
	$(window.frames[0].document.body).find("#tt").css('overflow-x',"hidden");
	//$(window.frames[0].document.body).find("iframe").css('height','10px');
	/*setInterval(function(){
		if($(window.frames[0].frames[0].document)!=null||$(window.frames[0].frames[0].document)!=''){
			clearInterval();
			var fkinhight=$(window.frames[0].frames[0].document.body).find("#wrapper").height();
			$(window.frames[0].frames[0].document.body).find(".hoverbox").css('z-index','10');
			$(window.frames[0].document.body).find("iframe").css('height',fkinhight+'px');
			$(window.frames[0].document.body).find("iframe").css('overflow','hidden');
			$(window.frames[0].document.body).find("iframe").attr('scrolling','no');
			$(window.frames[0].document.body).find("#tt").css('height',fkinhight+8+'px');
			}
		},50);
	$(".iStyle_Iframe iframe").scroll(function(){alert("aaa!!")});*/
	/*setTimeout(function(){
		var fkinhight=100;//$(window.frames[0].frames[0].document.body).find("#wrapper").height();
		$(window.frames[0].document.body).find("iframe").css('height',fkinhight+'px');
		$(window.frames[0].document.body).find("iframe").css('overflow','hidden');
		$(window.frames[0].document.body).find("iframe").attr('scrolling','no');
		$(window.frames[0].document.body).find("#tt").css('height',fkinhight+20+'px');
		},50);*/
	/*setInterval(function(){
		alert($(window.frames[0].document.body).find("#wrapper").height());
		$(window.frames[0].document.body).find("iframe").css('height',$(window.frames[0].document.body).find("#wrapper").height()+'px');
		},1500);*/

	}
	
function iStyle_SystemUI_DelTags(){
	$(this).parent().animate({width:"0px"},"fast",function(){
		$(this).remove();
		iStyle_SystemUI_Resize();
		if($(".iStyle_Tags").length>0)
			iStyle_SystemUI_searchButton_Search();
		else
			is_searchButton_Clear_Base();
		});	
	}

function iStyle_SystemUI_ActiveMark(){
	if(!$(this).hasClass("iStyle_ActiveMark")){
		$(".iStyle_Mark").removeClass("iStyle_ActiveMark");
		$(this).addClass("iStyle_ActiveMark");
		}
	}

function iStyle_SystemUI_MarkLinksClick(){
	iStyle_SystemUI_IframeLoad_Base($(this).attr("src"));
	}


function iStyle_SystemUI_LinksClick(){
	iStyle_SystemUI_IframeLoad_Base($(this).attr("src"));
	}
function iStyle_SystemUI_IframeLoad_Base(is_src){
	$(".iStyle_Iframe iframe").attr("src",is_src);
	$(".iStyle_Iframe iframe").unbind();
	$(".iStyle_Iframe iframe").load(function() {
		setTimeout(iStyle_IframeJSReligious,500);
    });//(iStyle_IframeJSReligious,1000);
	}

