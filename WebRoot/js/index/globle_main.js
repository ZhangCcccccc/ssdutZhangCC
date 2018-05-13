$(function () {
    $('#dowebok').fullpage({
        anchors: ['page1', 'page2', 'page3', 'page4'],
		menu: '#menu',
        slidesNavigation: 'ture',
        /*'navigation': true,*/
        'scrollingSpeed': '600',
        'easing': 'easeInOutBack',

        afterLoad: function (anchorLink, index) {
            if (index == 1) {

                $(".menu_container,.menu_box").stop().animate({
                    backgroundColor: '#fff'
                }, 100)
            }
            if (index == 2) {
                for (i = 0; i <= 2; i++) {
                    $('.news_list').eq(i).stop().delay(60 * (1 + i)).animate({
                        left: '0',
                        opacity: '1',
                        filter: 'alpha(opacity=1)'
                    }, 500, 'easeOutExpo');
                }
                $('.title_container,.more').stop().animate({
                    left: '0px',
                    opacity: '1',
                    filter: 'alpha(opacity=1)'
                }, 300)

                $(".menu_container,.menu_box").stop().animate({
                    backgroundColor: '#ebedf0'
                }, 80)
            }
            if (index == 3) {
                for (i = 0; i <= 3; i++) {
                    $(".link_container").eq(i).stop().delay(60 * (1 + i)).animate({
                        top: '0',
                        opacity: '1',
                        filter: 'alpha(opacity=1)'
                    }, 500, 'easeOutExpo');

                }

                $(".menu_container,.menu_box").stop().animate({
                    backgroundColor: '#fff'
                }, 80)
            }

        },
        onLeave: function (index, direction) {
            if (index == '2') {
                for (i = 0; i <= 2; i++) {
                    $('.news_list').eq(i).stop().delay(60 * (1 + i)).animate({
                        left: '1000',
                        opacity: '0',
                        filter: 'alpha(opacity=0)'
                    }, 500, 'easeOutExpo');
                }
                $('.title_container,.more').stop().animate({
                    left: '-500px',
                    opacity: '0',
                    filter: 'alpha(opacity=0)'
                }, 300)
            }
            if (index == '3') {
                for (i = 0; i <= 3; i++) {
                    $(".link_container").eq(i).stop().delay(60 * (1 + i)).animate({
                        top: '500px',
                        opacity: '0',
                        filter: 'alpha(opacity=0)'
                    }, 500, 'easeOutExpo');

                }
            }

        }
    });
      setInterval(function () {
        $.fn.fullpage.moveSlideRight();
    }, 5000);
});