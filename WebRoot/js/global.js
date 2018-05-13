  $(".Lele").click(
            function () {
                $(".window_box").fadeIn(100)
            }
        );
$(".close_icon").click(
            function () {
                $(".window_box").fadeOut(100)
            }
        );

$('.drop_list li').click(
            function(){
                var T=$(this).html();
                //alert(T)
                $(this).parent().siblings(".drop_box").find(".drop_btn").html(T)
            }
        )

$(function () {
            $('textarea').autosize();
            //$('.animated').autosize();
        });

 $(".c_category").hover(
            function () {
                $(this).find(".hide").show()
            },
            function () {
                $(this).find(".hide").hide()
            }
        )
 
 $(".module_tit").click(
            function () {
                $(this).siblings(".module_con").slideToggle(150)
            }
        )
 
 $("table tr").hover(
            function () {
                $(this).addClass("hg8")
            },
            function () {
                $(this).removeClass("hg8")
            }
        );

$(".file_op").click(
            function () {
                $(this).parent().siblings(".accessory").slideToggle(130)
                var con = $(this).html();
                $(this).html(con == '收起' ? '查看文件' : '收起')
            }
        );

 $('.dropdown').hover(
            function () {
                $(this).find(".dropdown_list").fadeIn(100)
            },
            function () {
                $(this).find(".dropdown_list").fadeOut(100)
            }
        );


$(".cm_list").not(".select").hover(
            function(){
                $(this).find("a").css("color","#78B0FF")
            },
            function(){
                $(this).find("a").css("color","#333")
            }
        )

