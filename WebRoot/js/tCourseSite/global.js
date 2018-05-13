  $(".Lele").click(
            function () {
               // $(".window_box").fadeIn(100);
                $("body").css("overflow-y","hidden")
            }
        );
$(".close_icon").click(
            function () {
                $(".window_box").fadeOut(100)
                $("body").css("overflow-y","auto")
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
                $(this).find(".fa-eye").css("color","#000");
            },
            function () {
                $(this).removeClass("hg8")
                $(this).find(".fa-eye").css("color","#999");
            }
        );

$(".file_op").click(
            function () {
                $(this).parent().next(".accessory").slideToggle(130)
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

 $(".tab li").click(
            function(){
                $(this).find(".wire_btn").addClass("bgc");
                 $(this).siblings().find(".wire_btn").removeClass("bgc")
                var i =$(this).index()
                //alert(i)
                $(this).parent().parent(".tab_box").find(".tab_list").eq(i).slideDown(150);
                 $(this).parent().parent(".tab_box").find(".tab_list").eq(i).siblings(".tab_list").slideUp(150);
                /*$(".tab_list").eq(i).slideDown(150)
                $(".tab_list").eq(i).siblings(".tab_list").slideUp(150)*/
            }
        )
//因为信息，通知，课程信息阴影移动问题注释
//$(function () {
//            $('.select_search').searchableSelect();
//        });
/*$("#checkall").click(
        function (event) {
            event.stopPropagation();
            if (this.checked) {
                $(this).parent().parent().find("input[name='checkname']").each(function () {
                	$("input[name='checkname'],input[name='All']").attr("checked",true); 
                });
            } else {
                $(this).parent().parent().find("input[name='checkname']").each(function () {
                	
                	$("input[name='checkname'],input[name='All']").attr("checked",false); 
                });
            }
        }
    );*/
$("#checkallDocument,.checkallDocument,#checkallImage,.checkallImage").click(
        function (event) {
            event.stopPropagation();
            if (this.checked) {
                $(this).parent().parent().find("input[name='checkname']").each(function () {
                    this.checked = true;
                });
            } else {
                $(this).parent().parent().find("input[name='checkname']").each(function () {
                    this.checked = false;
                });
            }
        }
    );
