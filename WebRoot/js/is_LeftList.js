// JavaScript Document
//页面加载完成后的动作
$(document).ready(function(){	
	is_TreeResize();
	is_OnLoad();		
	});

//初始化控件事件和样式重构
function is_OnLoad(){
	iStyle_SystemUI_CheckCookie(true);
	is_RefreshTree();
	$(".is_TreeTitle").mousedown(is_SlideTreeTitles);
	$(".is_Leaves").mousedown(is_ActiveLeaves);
	$(".is_SlideButton").mousedown(is_PressSlideButtons);	
	}

function is_RefreshTree(){
	$(".is_Leaves").css("display","none");
	$(".opened_Tree~.is_Leaves").css("display","block");
	if($(".is_TreeButtons").hasClass("is_Slided")){
		$(".is_TreeButtons").removeClass("is_Slided");
		is_PressSlideButtons(0);
		iStyle_SystemUI_ResizeLeftlist(0);
		}
	}

//点击分类标题后的动作
function is_SlideTreeTitles(){
	if(!$(".is_TreeButtons").hasClass("is_Slided")){
		if($(this).hasClass("opened_Tree")){
			$(this).removeClass("opened_Tree");
			$(this).parent().find(".is_Leaves").slideUp();
			if($(this).parent().find(".is_Leaves").hasClass("active_Leaf")){
				$(this).addClass("active_Leaf");
				}
			}
		else{
			$(this).removeClass("active_Leaf");
			$(this).addClass("opened_Tree");
			$(".opened_Tree~.is_Leaves").slideDown();
			}
		iStyle_SystemUI_CheckCookie(false);
		}
	}
	
//点击内容标签后的动作
function is_ActiveLeaves(){
	$(this).parent().siblings().find(".is_TreeTitle").removeClass("active_Leaf");
	$(".is_Leaves").attr("class","is_Leaves");
	$(this).attr("class","is_Leaves active_Leaf");
	iStyle_SystemUI_CheckCookie(false);
	}

//点击收缩按钮后的动作
function is_PressSlideButtons(speed){
	//判断左侧栏展开状态，如果展开，则收起，清除分类标题单击事件，赋予鼠标移上后显示内容事件
	if(speed!=0){
		speed="slow";
		}
	if(!$(".is_TreeButtons").hasClass("is_Slided")){
		$(".is_TreeButtons").addClass("is_Slided");
		$(".is_TreeButtons").animate({width:$(".is_TreeTitle").height()+"px"},speed);
		$(".is_LeftList").animate({width:$(".is_TreeTitle").height()+"px"},speed);
		$(".is_Shadow").attr("class","is_Shadow is_Shadow_R");
		$(".opened_Tree~.is_Leaves").slideUp(speed);
		$(".active_Leaf").parent().find(".is_TreeTitle").addClass("active_Leaf");
		$(".is_TreeTitle").unbind();
		$(".is_TreeTitle").mousedown(is_ShowLeaves);
		$(".is_TreeTitle").mouseleave();
		}
	else{
		$(".is_TreeButtons").removeClass("is_Slided");
		is_RemoveLeaves();
		$(".is_TreeButtons").animate({width:$(".is_TreeButtons").attr("t_width")+"px"},speed);
		$(".is_LeftList").animate({width:$(".is_TreeButtons").attr("t_width")+"px"},speed);
		$(".is_Shadow").attr("class","is_Shadow");
		if($(".is_Tree>.active_Leaf:first").hasClass("opened_Tree")){
			$(".is_Tree>.active_Leaf:first").removeClass("active_Leaf");
			}
		$(".opened_Tree~.is_Leaves").slideDown(speed);
		$(".is_TreeTitle").unbind();
		$(".is_TreeTitle").mousedown(is_SlideTreeTitles);
		}
	iStyle_SystemUI_CheckCookie(false);
	}
	
function is_ShowLeaves(){
	if(!$(this).hasClass("active_Title")){
		is_RemoveLeaves();
		$(".is_TreeTitle").removeClass("active_Title");
		$(this).addClass("active_Title");
		$is_leavescontainer=$("<div class='is_LeavesContainer'></div>");
		$is_leavescontainer.css({"width":$(".is_TreeButtons").attr("t_width")+"px","left":$(".is_TreeButtons").width()+"px","top":$(this).position().top+"px"});
		$is_cloneleaves=$(this).parent().find(".is_Leaves").clone(true);
		$is_cloneleaves.mousedown(is_ActiveLeavesClone);
		$is_leavescontainer.attr("index",$(".is_Tree").index($(this).parent()));
		$is_clonetitle=$(this).clone(true);
		$is_clonetitle.unbind();
		$is_clonetitle.find(".is_Icons").attr("type","97");
		$is_clonetitle.find(".is_Icons").mousedown(is_RemoveLeaves);
		$is_clonetitle.attr("class","slided_Tree");
		$is_cloneleaves.css("display","block");
		$is_leavescontainer.append($is_clonetitle);
		$is_leavescontainer.append($is_cloneleaves);
		$is_leavescontainer.css("display","none");
		$(".is_LeftList").append($is_leavescontainer)
		$(".is_LeavesContainer").show("fast");
		$deler=$("<div id='is_TreeDeleter' style='position:absolute; left:0; top:0; z-index:0; width:100%; height:100%;'></div>");
		$deler.mousedown(is_RemoveLeaves);
		$("body").append($deler);
		$(".is_SlideButton").hide();
		is_IconLoad();
		}
	else{
		is_RemoveLeaves();
		}
	}

function is_ActiveLeavesClone(){
	var tree_index=$(".is_LeavesContainer").attr("index");
	var leaf_index=$(this).index()-1;
	$(".is_Tree .is_Leaves").removeClass("active_Leaf");
	$(".is_Tree:eq("+tree_index+")").find(".is_Leaves:eq("+leaf_index+")").addClass("active_Leaf");
	//alert("tree_index:"+tree_index+";leaf_index:"+leaf_index);
	//is_RemoveLeaves();
	}
	
function is_RemoveLeaves(){
	$(".is_TreeTitle").removeClass("active_Title");
	$("#is_TreeDeleter").remove();
	$(".active_Leaf").parent().find(".is_TreeTitle").addClass("active_Leaf");
	$(".is_LeavesContainer").hide("slow",function(){$(this).remove();});
	$(".is_SlideButton").show();
	iStyle_SystemUI_CheckCookie(false);
	}

//根据内容长度重新设置左侧栏宽度
function is_TreeResize(){
	var $tree_div=$(".is_TreeButtons div");
	var div_maxwidth=0;
	for(i=0;i<$tree_div.length;i++){
		if($tree_div.width()>div_maxwidth)
			div_maxwidth=$tree_div.width();
		}
	div_maxwidth=div_maxwidth+35;
	$(".is_LeftList").css("width",div_maxwidth+"px");
	$(".is_TreeTitle").css("width",div_maxwidth+"px");
	$(".is_Leaves").css("width",div_maxwidth+"px");
	$(".is_TreeButtons").attr("t_width",div_maxwidth);
	}


//	
function iStyle_SystemUI_GetCookie(c_name){
	if (document.cookie.length>0){
  		c_start=document.cookie.indexOf(c_name + "=")
  		if (c_start!=-1){ 
    		c_start=c_start + c_name.length+1 
    		c_end=document.cookie.indexOf(";",c_start)
    		if (c_end==-1) c_end=document.cookie.length
    			return unescape(document.cookie.substring(c_start,c_end))
    		} 
  		}
	return ""
	}

function iStyle_SystemUI_SetCookie(c_name,value,expiredays){
	var exdate=new Date()
	exdate.setDate(exdate.getDate()+expiredays)
	document.cookie=c_name+ "=" +escape(value)+
	((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
	}

function iStyle_SystemUI_CheckCookie(onload){
	/**/
}