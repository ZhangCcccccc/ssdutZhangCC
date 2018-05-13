// JavaScript Document
var inlist=false;
$(document).ready(function(){
	$(".is_SearcherDownList").mouseenter(is_ShowDownlist);
	$(".is_SearcherDownList").mouseleave(is_HideDownlist);
	$(".is_SearcherDownlistInner").mouseenter(is_EnterList);
	$(".is_SearcherDownlistInner").mouseleave(is_LeaveList);
	$(".is_SearcherDownlistInner a").mousedown(is_SearchInner);
	$(".is_SearcherDownList .is_Icons").mousedown(is_SearchRecover);
	$(".is_SearcherButtonBox .is_Icons[value='search']").mousedown(is_searchButton_Search_Base);
	$(".is_SearcherButtonBox .is_Icons[value='clear']").mousedown(is_searchButton_Clear_Base);
	$(".is_SearcherButtonBox .is_Icons[value='showall']").mousedown(is_searchButton_Showall_Base);
	$(".is_SearcherButtonBox .is_Icons[value='add']").mousedown(is_searchButton_Add_Base);
	$(".is_SearcherButtonBox .is_Icons[value='add']").hide();
	});
	
function is_ShowDownlist(){
	$(".is_SearcherDownlistInner").stop();
	$(".is_SearcherDownlistInner").show("fast");
	}
	
function is_HideDownlist(){
	setTimeout(function(){
		if(!inlist){
			$(".is_SearcherDownlistInner").stop();
			$(".is_SearcherDownlistInner").hide("fast");
			}
		},80);	
	}

function is_EnterList(){
	inlist=true;
	}

function is_LeaveList(){
	inlist=false;
	$(".is_SearcherDownlistInner").hide("fast");
	}

function is_SearchInner(){
	inlist=false;
	$(".is_SearcherDownlistInner").hide("fast");
	$(".is_SearcherDownList .is_Icons").show("fast");
	$(".is_SearcherDownList").css("background-color","#34a3c7");
	$(".is_SearcherButtonBox .is_Icons[value='add']").show("fast");
	$(".is_SearcherDownList span").text($(this).text());
	}

function is_SearchRecover(){
	$(this).hide("fast");
	$(".is_SearcherDownList").css("background-color","#949494");
	$(".is_SearcherButtonBox .is_Icons[value='add']").hide("fast");
	$(".is_SearcherDownList span").text("全部");
	}

function is_searchButton_Search_Base(){
	var searchvalue=$(".is_SearcherBox input").val();
	searchvalue=searchvalue.replace(/\s+/g,"");
	if($(".is_SearcherDownList span").text()!="全部"){
		//alert('yeah!!');
		$(".iStyle_Marksfeild .iStyle_Mark").remove();
		$(".iStyle_Marksfeild").append($('<div class="iStyle_Mark iStyle_ActiveMark"><span>'+$(".is_SearcherDownList span").text()+'</span></div>'));
		}
	var $tags=$(".iStyle_Tags");
	var dosearch=true;
	for(i=0;i<$tags.length;i++){
		if(dosearch&&$(".iStyle_Tags span:eq("+i+")").text()==searchvalue)
			dosearch=false;
		}
	if(searchvalue!=""&&searchvalue!=null&&searchvalue!=$(".iStyle_Tags").text()&&dosearch){
		$(".iStyle_Tagsfeild").append($('<div class="iStyle_Tags"><span>'+searchvalue+'</span><div class="is_Icons" type="97" size="auto"><img src="images/is_Icon.png"/></div></div>'));
		is_IconLoad();
		$(".iStyle_Tags .is_Icons").mousedown(iStyle_SystemUI_DelTags);
		iStyle_SystemUI_Resize();
		iStyle_SystemUI_searchButton_Search();
		}
	}

function is_searchButton_Clear_Base(){
	is_CleanPlane();
	iStyle_SystemUI_searchButton_Clear();
	}

function is_searchButton_Showall_Base(){
	is_CleanPlane();
	iStyle_SystemUI_searchButton_Showall();
	}

function is_searchButton_Add_Base(){
	is_CleanPlane();
	iStyle_SystemUI_searchButton_Add();
	}

function is_CleanPlane(){
	$(".iStyle_Mark").remove();
	$(".iStyle_Tags").remove();
	iStyle_SystemUI_Resize();
	}