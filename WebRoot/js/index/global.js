 $(".menu_container li").click(
         function () {
             $(this).addClass("select");
             $(this).siblings().removeClass("select")
         })
     /*幻灯片*/
 var glide = $('.slider').glide().data('api_glide');

 $(window).on('keyup', function (key) {
     if (key.keyCode === 13) {
         glide.jump(3, console.log('Wooo!'));
     }
 });

 $('.slider__arrows-item').on('click', function () {
     console.log(glide.current());
 });
 /*幻灯片结束*/

 /* 更多课程动画*/
 $(".more_courses").css("opacity", "0.6")
 $(".more_courses").hover(
     function () {
         $(this).animate({
             opacity: '1',
             filter: 'alpha(opacity=60)',
         }, 200);
     },
     function () {
         $(this).animate({
             opacity: '0.6',
             filter: 'alpha(opacity=60)',
         }, 200);
     }
 );
 /* 更多课程动画结束*/

 /*首页课程列表动画*/
 $(".courses_box").hover(
         function () {
             $(this).children(".courses_intro").stop().slideDown({
                 duration: 500,
                 easing: 'easeOutCubic'
             });
             $(this).children(".courses_intro_tit").stop().slideDown({
                 duration: 500,
                 easing: 'easeOutCubic'
             });
         },
         function () {
             $(this).children(".courses_intro").stop().slideUp({
                 duration: 500,
                 easing: 'easeOutCirc'
             });
             $(this).children(".courses_intro_tit").stop().slideUp();
         }
     )
     /*首页课程列表动画结束*/
     /*ad 动画*/
 $(".ad_pic_box").hover(
         function () {
             $(this).stop().animate({
                 bottom: '+5px'
             }, 180, 'easeInOutSine')
         },
         function () {
             $(this).stop().animate({
                 bottom: '-5px'
             }, 180, 'easeInOutSine')
         }
     )
     /*ad 动画结束*/

 /*特色介绍*/
 var Height = $(window).height();
 $(window).scroll(
     function () {
         var offset1 = $(".intro1").offset();
         var wintop = $(document).scrollTop();
         var H1 = offset1.top - wintop
         if (H1 < Height - 300) {
             $(".intro1 ").addClass("sd");
             $(".intro1 .intro1_text").animate({
                 opacity: '1',
                 filter: 'alpha(opacity=100)',
             }, 1800);
             $(".intro1 .computer_dis").animate({
                 bottom: '-3px',
                 opacity: '1',
                 filter: 'alpha(opacity=100)',
             }, 500);
         }
         var offset2 = $('.intro2').offset();
         var H2 = offset2.top - wintop
         if (H2 < Height - 300) {
             $(".intro2 .intro2_text").animate({
                 opacity: '1',
                 filter: 'alpha(opacity=100)',
             }, 1800);
             $(".intro2 .computer_test").animate({
                 bottom: '-3px',
                 opacity: '1',
                 filter: 'alpha(opacity=100)',
             }, 500);
             setTimeout("remainTime_boy()", 500);
             setTimeout("remainTime1_sun()", 800);
             setTimeout("remainTime1_shadow()", 900);
         }

         var offset3 = $('.intro3').offset();
         var H3 = offset3.top - wintop
         if (H3 < Height - 300) {
             $(".intro3 .intro3_text").animate({
                 opacity: '1',
                 filter: 'alpha(opacity=100)',
             }, 1800);
             $(".intro3 .computer_step").animate({
                 bottom: '-3px',
                 opacity: '1',
                 filter: 'alpha(opacity=100)',
             }, 500);
             setTimeout("remainTime1_trophy()", 500);
             setTimeout("remainTime1_star1()", 800);
             setTimeout("remainTime1_star2()", 1000);
             setTimeout("remainTime1_star3()", 900);
             setTimeout("remainTime1_star4()", 1300);

         }
     }
 );

 function remainTime_boy() {
     $(".intro2 .boy_test").animate({
         bottom: '-0px',
         opacity: '1',
         filter: 'alpha(opacity=100)',
     }, 500);
 }

 function remainTime1_sun() {
     $(".intro2 .sun_test").animate({
         top: '0px',
         opacity: '1',
         filter: 'alpha(opacity=100)',
     }, 500);
 }

 function remainTime1_shadow() {
     $(".intro2 .shadow_test").animate({
         top: '0px',
         opacity: '0.5',
         filter: 'alpha(opacity=100)',
     }, 500);
 }

 function remainTime1_trophy() {
     $(".intro3 .trophy_step").animate({
         bottom: '-8px',
         opacity: '1',
         filter: 'alpha(opacity=100)',
     }, 500);
 }

 function remainTime1_star1() {
     $(".star_stype .star1").animate({
         opacity: '1',
         filter: 'alpha(opacity=100)',
     }, 500);
 }

 function remainTime1_star2() {
     $(".star_stype .star2").animate({
         opacity: '1',
         filter: 'alpha(opacity=100)',
     }, 500);
 }

 function remainTime1_star3() {
     $(".star_stype .star3").animate({
         opacity: '1',
         filter: 'alpha(opacity=100)',
     }, 500);
 }

 function remainTime1_star4() {
     $(".star_stype .star4").animate({
         opacity: '1',
         filter: 'alpha(opacity=100)',
     }, 500);
 }

 /*特色介绍结束*/
 /*返回顶部*/

 $(window).scroll(
     function () {
         var hh = $(window).height();
         var hi = $(document).scrollTop();
         if (hi > hh) {
             $('.top').slideDown("100")
         } else {
             $('.top').slideUp("100")
         }
     }
 );
 $('.top').click(function () {
     $('html, body').animate({
         scrollTop: 0
     }, 'slow');
 });
 /*返回顶部*/
 /*登录效果*/
 window.onload = function () {
     var h = $(window).height()
     $(".log-in").height(h) + "px"
 }
 $(".login_btn").click(
     function () {
         $(".log-in").show();
     });
 $(".close").click(
         function () {
             $(".log-in").hide();
         }
     )
     /*登录效果*/