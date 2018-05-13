//遮罩层
$(function(){
	var mo=-35;
	//为百分比
	var zo=1.6;
	$('.con22').hover(function(){
		//通过百分比设置鼠标移上去的宽和高
		width=$(".con22").width()*zo;
		height=$(".con22").height()*zo;
		$(this).find('img').stop(false,true).animate({'width':width,'height':height,'top':mo,'left':mo},{duration:100});
		$(this).find('div.con222').stop(false,true).fadeIn(200);
	},function(){
		$(this).find('img').stop(false,true).animate({'width':$(".con22").width(),'height':$(".con22").height(),'top':'0','left':'0'},{duretion:100});
		$(this).find("div.con222").stop(false,true).fadeOut(200);
		
	})
	
})

//table选项卡
$(function(){	
	 $(".btn20").show().siblings().hide();
//	$("li"):eq(0).addClass("on");
	$("li").click(function(){
       var index=$("li").index(this);
       $(".btn2").children().eq(index).show().siblings().hide();
		
		$(this).addClass("on").siblings().removeClass("on");
	});

})




//置顶
$(function(){
	$('button').click(function(){
		$("body").stop().animate({scrollTop:0,},200);
	})
})
