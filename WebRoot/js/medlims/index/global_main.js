$(function () {
    $('#dowebok').fullpage({
        sectionsColor: ['#2c2c2c', '#2c2c2c', '#2c2c2c', '#2c2c2c', '#2c2c2c', '#2c2c2c', '#2c2c2c', '#2c2c2c', '#2c2c2c'],
        anchors: ['page1', 'page2', 'page3', 'page4','page5','page6','page7','page8','page9'],
        menu: '#menu',
        'scrollingSpeed': '600',
        'easing': 'easeInOutBack',
        afterLoad: function (anchorLink, index) {
        	var count=$('.section').length;
            if (index == 1) {
            	$(".sites_name_container").fadeIn();             	
            }
            
            if (index == 2) {
            	for (i = 0; i < 3; i++){
            		$(".charts_box").eq(i).stop().delay(150 * (i + 1)).animate({
            			top:0,
            			opacity: '1',
                        filter: 'alpha(opacity=100)'
            		},900, 'easeOutQuart')
            	}
            	
            }
            
            if (index > 2 && index < count) {
            	var quantity = $(".section"+index+" .charts_box").length;
            	for (i = 0; i < quantity; i++){
            		$(".section"+index+" .charts_box").eq(i).stop().delay(150 * (i + 1)).animate({
            			top:0,
            			opacity: '1',
                        filter: 'alpha(opacity=100)'
            		},900, 'easeOutQuart')
            	}
            	
            }
            /*if (index == 3) {
            	for (i = 0; i < 2; i++){
            		$(".news_box").eq(i).stop().delay(150 * (i + 1)).animate({
            			top:0,
            			opacity: '1',
                        filter: 'alpha(opacity=100)'
            		},600, 'easeOutQuart')
            	}
               
            }
            
            if (index == 4) {
            	for (i = 0; i < 3; i++){
            		$(".aboutUs_box").eq(i).stop().delay(150 * (i + 1)).animate({
            			top:0,
            			opacity: '1',
                        filter: 'alpha(opacity=100)'
            		},900, 'easeOutQuart')
            	}
                
            }*/
            
            if (index == count) {
            	for (i = 0; i < 5; i++){
            		$(".link_box").eq(i).stop().delay(150 * (i + 1)).animate({
            			top:0,
            			opacity: '1',
                        filter: 'alpha(opacity=100)'
            		},1200, 'easeOutQuart')
            	}
                
            }
        },
        
        onLeave: function (index, direction) {
        	var count=$('.section').length;
        	if (index == 1) {
        		$(".sites_name_container").fadeOut();
            }
            if (index == 2) {
               for (i = 3; i >= 0; i--) {
                    $('.charts_box').eq(i).stop().delay(100 * (3 - i)).animate({
                        top: '500',
                        opacity: '0',
                        filter: 'alpha(opacity=0)'
                    }, 500, 'easeOutQuart');
                }
            }
            /*if (index == 3) {
            	for (i = 2; i >= 0; i--) {
                    $('.news_box').eq(i).stop().delay(100 * (2 - i)).animate({
                        top: '500',
                        opacity: '0',
                        filter: 'alpha(opacity=0)'
                    }, 500, 'easeOutQuart');
                }
            }
            if (index == 4) {
            	for (i = 3; i >= 0; i--) {
                    $('.aboutUs_box').eq(i).stop().delay(100 * (3 - i)).animate({
                        top: '500',
                        opacity: '0',
                        filter: 'alpha(opacity=0)'
                    }, 500, 'easeOutQuart');
                }
            }*/
            if (index > 2 && index < count) {
            	var quantity = $(".section"+index+" .charts_box").length;
            	for (i = quantity; i >=0; i--){
            		$(".section"+index+" .charts_box").eq(i).stop().delay(100 * (quantity - i)).animate({
            			top:0,
            			opacity: '1',
                        filter: 'alpha(opacity=100)'
            		},500, 'easeOutQuart')
            	}
            	
            }
            if (index == 5) {
            	for (i = 5; i >= 0; i--) {
                    $('.link_box').eq(i).stop().delay(100 * (5 - i)).animate({
                        top: '500',
                        opacity: '0',
                        filter: 'alpha(opacity=0)'
                    }, 500, 'easeOutQuart');
                }
            }
        }
    });
    setInterval(function () {
        $.fn.fullpage.moveSlideRight();
    }, 10000); 
    
});




