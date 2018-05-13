// JavaScript Document
/*
	框架核心
	vesion:sfff0.5
	//update:
	//0.1-->0.11 解决了IE低版本下无法正确的激活左侧栏的问题
	//0.11-->0.2 解决了url的错误问题
	//0.2-->0.3 现在框架能支持自动包装内容页面了
	//0.3-->0.4 提供了cookies 记录左侧栏状态并且在页面打开时加载左侧栏记忆状态
	//0.4-->0.5 减少了树状态记录器的写入次数，减少了数据遍历量大幅度提高了框架运行效率
*/
function iStyle_Feelings_Core(script) {
	this.script = script;
	this.target = script.settings.target;
	this.menu = null;
	this.tree = null;
	this.ui = null;
	this.win = null;
	this.mainurl = null;
	this.listbox = null;
	this.cmode = false;  	//是否关闭地址栏键入模式	
	this.srcmode = false;   //iframe是否已src的形式跳转
	this.hashthread = undefined; //IE7下检测
	this.hashm = undefined;	 //用于记录url
	this.die7 = false; 
	this.initialize();
}
iStyle_Feelings_Core.prototype = {
	initialize: function () {
		this.mainurl = location.href;
		if (this.mainurl.indexOf("#") != -1) {
			this.mainurl = this.mainurl.split("#");
			this.mainurl = this.mainurl[0];
		}
        this.tempurl = this.mainurl.split("/");
		this.mainurl = this.mainurl.replace(this.tempurl[this.tempurl.length-1],"");
		//将页面DOM设置为无法选中状态以提高交互体验
		if (this.script.settings.unselectable)
			$(this.target).attr('unselectable', 'on')
			.css({
				'-moz-user-select': '-moz-none none',
				'-o-user-select': 'none',
				'-khtml-user-select': 'none',
				'-webkit-user-select': 'none',
				'-ms-user-select': 'none',
				'user-select': 'none'
			}).bind('selectstart', function () {
				return false;
			});

		//绑定主内容自适应大小
		this.ui = new iStyle_Feelings_UserInteraction(this);
		this.ui.selfadaption(true);
		$(window).resize($.proxy(function () {
			this.ui.selfadaption(true);
		}, this));

		//初始化侧边栏或剔除侧边栏DOM
		if (this.script.tree.switchon) {
			this.tree = new iStyle_Feelings_Tree(this);
		} else
			$(this.target).find(".iStyle_Feelings_Tree").remove();

		//初始化主菜单或剔除主菜单DOM
		if (this.script.menu.switchon) {
			this.menu = new iStyle_Feelings_Menu(this);
		} else
			$(this.target).find(".iStyle_Feelings_MainMenu").remove();
		//设定url
		this.setiframeurl();
		//this.test();
	},
	test: function() {
		//console.log("ok!");
		//console.log($(this.target).find(".iStyle_Feelings_MainMenu").css());
		},
	//框架hash 模拟url欺骗浏览器跳转iframe内容页面
	setiframeurl: function () {
		var surl = location.hash.replace("#", ""); //获取初始的url
		if (surl != '' && surl != 'about:blank') {
			if (this.script.tree.switchon)
				this.tree.setactived(surl);
			else
				$(this.target).find(".iStyle_Feelings_Iframe").attr("src", surl);
		} else if (this.script.tree.switchon) {
			var url = $(this.target).find(".iStyle_Feelings_Tree_ActiveLeaf[href!=null]").attr("href").replace(this.mainurl + "#", "");
			location.hash = url;
			$(this.target).find(".iStyle_Feelings_Iframe").get(0).contentWindow.location.replace(url);
			//$(this.target).find(".iStyle_Feelings_Iframe").attr("src", url);
		}
		$(this.target).find(".iStyle_Feelings_Iframe").load($.proxy(function () {
			$(this.target).find(".iStyle_Feelings_Iframe").unbind("load");
			if (this.script.settings.autourl)
				$(this.target).find(".iStyle_Feelings_Iframe").load($.proxy(function () {
					var url = $(this.target).find(".iStyle_Feelings_Iframe").get(0).contentWindow.location.href.replace(this.mainurl, "");
					var thurl = location.hash.replace("#", "");
					if(url !== thurl){
						this.cmode=true;
						location.hash = '#' + url;
					}
				}, this));
			}, this));
		
		
		if (this.script.settings.ie7compat && this.testie7()) {
			this.srcmode=true;
			if (this.hashthread === undefined){
				this.hashthread = window.setInterval($.proxy(function(e){
					if (this.hashm != location.hash.replace("#","")) {
						this.hashm = location.hash.replace("#","");
						//console.log("die7"+this.die7);
						if ( !this.die7 ) {
							this.cmode=false;
							this.hashtest();
						}
						this.die7 = false;
					}
				},this),100);
			}
		} else
			window.onhashchange = $.proxy(function () {
				this.hashtest();
			}, this);
	},
	hashtest:function () {
		var jurl = location.hash.replace("#", "");
		if(!this.cmode){
			if (jurl != '' && jurl != 'about:blank')
				$(this.target).find(".iStyle_Feelings_Iframe").get(0).contentWindow.location.replace(location.hash.replace("#", ""));
			if (this.script.tree.switchon)
				this.tree.setactived(jurl);
		}
		this.cmode=false;
	},
	//监测浏览器是否为ie7
	testie7: function () {
		var browser = navigator.appName; //获取浏览器名称
		var b_version = navigator.appVersion; //获取浏览器版本
		var version = b_version.split(";");
		if (version.length > 1)
			var trim_Version = version[1].replace(/[ ]/g, "");
		if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE7.0") {
			return true;
		}
		return false;
	}
}

/*
	主菜单
	vesion:sfff0.1
*/

function iStyle_Feelings_Menu(core) {
	this.core = core;
	this.target = undefined;
	this.items = undefined;
	this.tree = undefined;
	this.trucks = undefined;
	this.script = this.core.script.menu;
	this.initialize();
}
iStyle_Feelings_Menu.prototype = {
	initialize: function () {
		this.target = $(this.core.target).find(".iStyle_Feelings_MainMenu");
		this.tree = $(this.core.target).find(".iStyle_Feelings_Tree");
		this.items = this.target.find(".iStyle_Feelings_MainMenu_Items");
		this.trucks = this.tree.find(".iStyle_Feelings_Tree_Trunks");
		//替换掉A标签自身的click事件
		this.items.click($.proxy(function (e) {
			if ($(e.target).attr("href") == null || $(e.target).attr("href") == undefined) {
				this.switchtree($(e.target).attr("target"));
				return false;
			}
		}, this));
		//为菜单栏添加鼠标移入时的动画特效
		if (this.script.hover_animate) {
			//将所有菜单项设为指定透明度
			this.items.animate({
				opacity: this.script.hover_animate
			}, 0);
			//鼠标移入时将感应项透明度调为1
			this.items.mouseenter($.proxy(function (e) {
				var tar = $(e.target);
				tar.stop();
				tar.animate({
					opacity: 1
				}, 250);
			}, this));
			//鼠标离开时菜单项透明度恢复默认
			this.items.mouseleave($.proxy(function (e) {
				var tar = $(e.target);
				tar.stop();
				tar.animate({
					opacity: this.script.hover_animate
				}, 250);
			}, this));
		}
	},
	//切换树状菜单内容
	switchtree: function (index) {
		this.trucks.attr("actived", "false");
		this.trucks.eq(index).attr("actived", "true");
		this.items.removeClass("iStyle_Feelings_MainMenu_Items_Actived");
		this.items.eq(index).addClass("iStyle_Feelings_MainMenu_Items_Actived");
		this.core.tree.hidetrunks();
	},
	//菜单项宽度自适应
	resize: function () {
		if (this.items.css("margin-right") != "auto")
			var itemsmargin = parseInt(this.items.css("margin-right"));
		else
			var itemsmargin = 0;
		var width = this.target.width() / this.items.length - itemsmargin * (this.items.length - 1) / this.items.length - 0.05;
		this.items.css({
			width: width + 'px'
		});
		this.items.eq(this.items.length - 1).css({
			"margin-right": '0px'
		});
	}
}

/*
	树状菜单
	vesion:sfff0.12
	//update:
	//0.1-->0.11
*/
function iStyle_Feelings_Tree(core) {
	this.core = core;
	this.target = $(this.core.target).find(".iStyle_Feelings_Tree"); //将目标对齐到侧边栏
	this.script = this.core.script.tree; //加载树脚本
	this.cookiesthread = undefined; //cookies记忆线程
	this.cookiestimer = null; //用于定时用户每次操作后多长时间后记录一次左侧栏状态
	this.scrollthread = undefined; //滚动条卷动线程
	this.scrolltimer = null; //滚动条计时器
	this.wheelthread = undefined; //鼠标滚轮线程
	this.attribute = null; //树的属性
	this.slidethread = undefined; //树的收缩展开线程	
	this.mouseoutthread = undefined; //树的离开判定与自动收缩线程
	this.mouseouttimer = 0; //输的的自动收缩计时器
	this.initialize();
}

/*
	tree: 侧边栏设置
			以下属性为true时
			switchon: 启动侧边栏
			cookies: 启用cookies
			scrolling: 启动滚动条
			mousewheel: 启动鼠标滚轮控制
			scrollbutton: 卷动菜单按钮
			dragwidth: 启用用户拖拽宽度
			
			带有配置的属性
			longitudinaldom: 控件上方和下方的DOM结构，class或id，用于自适应
				例如 'longitudinaldom':'.iStyle_Feelings_Header,.iStyle_Feelings_Footer'
			latitudinaldom: 控件左方和右方的dom结构，class或id，用于自适应
				例如 'latitudinaldom':'.iStyle_Feelings   v_Maininner'
			width: 用于指定侧边栏的默认宽度
				px值，指定固定像素值
			hover_animate: 鼠标移入菜单项变化特效
				false 关闭鼠标移入时动画
				0-1的值 为鼠标离开时的透明度 移入时透明度为1	
	*/
iStyle_Feelings_Tree.prototype = {
	initialize: function () {
		if (this.script.cookies)
			this.attribute = this.getcookies();
		if (this.attribute === null){
			//如果不取得cookies 或 cookies 中没有记录，则左侧栏初始化settings 变量
			this.attribute = {
				'treewidth': null,
				'slided': false,
				'titlestate': new Array(),
				'leafstate': new Array()
			};
			this.target.find(".iStyle_Feelings_Tree_TrunkTitle").each($.proxy(function (index, element) {
				if ($(element).hasClass("iStyle_Feelings_Tree_Opened")) {
					this.attribute.titlestate[index] = true;
					
				} else {
					this.attribute.titlestate[index] = false;
				}
			}, this));
		}
		this.settree();
		//初始化收缩状态
		this.initializeslide();
		//编辑a标签URL以支持在新窗口打开链接 v2 修改，通过子页面Js调用框架实现了该功能。屏蔽之。
		//this.editurl();
		//绑定按键事件
		this.bindclick();
		//为菜单栏添加滚动条
		if (this.script.scrolling && this.core.testie7()) {
			this.bindscroll();
		}
		if (this.script.mousewheel) {
			this.bindmousescroll();
		}
		if (this.script.dragwidth) {
			this.bindwidthdrag();
		}
		//隐藏未激活的枝干
		this.hidetrunks();
		//为菜单栏添加鼠标移入时的动画特效
		if (this.script.hover_animate) {
			//将所有菜单项设为指定透明度
			this.target.find(".iStyle_Feelings_Tree_Trunk a").animate({
				opacity: this.script.hover_animate
			}, 0);
			//鼠标移入时将感应项透明度调为1
			this.target.find(".iStyle_Feelings_Tree_Trunk a").mouseenter($.proxy(function (e) {
				var tar = this.cleanicon($(e.target));
				tar.stop();
				tar.animate({
					opacity: 1
				}, 250);
			}, this));
			//鼠标离开时菜单项透明度恢复默认
			this.target.find(".iStyle_Feelings_Tree_Trunk a").mouseleave($.proxy(function (e) {
				var tar = this.cleanicon($(e.target));
				tar.stop();
				tar.animate({
					opacity: this.script.hover_animate
				}, 250);
			}, this));
		}
		//调节一次左侧栏高度
		this.selfadaption();
		//添加自适应高度
		$(window).resize($.proxy(function () {
			this.selfadaption();
		}, this));
	},
	//记录左侧栏记忆状态
	setcookies: function () {
		this.cookiestimer = 3;
		//console.log(this.cookiestimer);
		if ( this.cookiesthread === undefined ){
			this.cookiesthread = setInterval($.proxy( function() {
				this.cookiestimer = this.cookiestimer-1;
				//console.log(this.cookiestimer);
				if ( this.cookiestimer == 0 ){
					$.cookies.set("iStyle_Tree_attr",this.attribute);
					clearInterval(this.cookiesthread);
					this.cookiesthread = undefined;
				}
			},this),500);
		}
	},
	//取得左侧栏记忆状态
	getcookies: function () {
		var attr = $.cookies.get("iStyle_Tree_attr");
		return attr;
	},
	//激活指定的A标签
	setactived: function (url) {
		var a = this.target.find("a");
		for (var i = 0; i < a.length; i++) {
			if (a.eq(i).attr("href") != undefined && a.eq(i).attr("href") != null && a.eq(i).attr("href") != "") {
				if (a.eq(i).attr("href").replace(this.core.mainurl + "#", "") == url) {
					this.activeleaf(a.eq(i));
					return true;
				}
			}
		}
		this.target.find(".iStyle_Feelings_Tree_Trunks[actived='true'] a").removeClass("iStyle_Feelings_Tree_ActiveLeaf");
		$(this.core.target).find(".iStyle_Feelings_Iframe").get(0).contentWindow.location.replace(url.replace("#", ""));
	},
	//修改A标签的URL
	editurl: function () {
		var a = this.target.find("a[href!=null]");
		for (var i = 0; i < a.length; i++) {
			if (a.eq(i).attr("href") != null && a.eq(i).attr("href") != undefined && a.eq(i).attr("href") != "") {
				var leafhref = a.eq(i).attr("href").split("#");
				if (leafhref.length > 1)
					var ahref = leafhref[leafhref.length - 1].replace("#", "");
				else
					var ahref = a.eq(i).attr("href");
				a.eq(i).attr("href", this.core.mainurl + "#" + ahref);
			}
		}
	},
	//初始化收缩状态
	initializeslide: function () {
		this.target.find(".iStyle_Feelings_Tree_Leaves").slideUp(0);
		this.target.find(".iStyle_Feelings_Tree_Opened").next().slideDown(0);
	},
	//隐藏没有激活的侧边栏内容
	hidetrunks: function () {
		this.target.find(".iStyle_Feelings_Tree_Trunks[actived!='true']").hide("slow");
		this.target.find(".iStyle_Feelings_Tree_Trunks[actived='true']").show("slow");
	},
	//自适应
	selfadaption: function () {
		//找到侧边栏上下方的元素
		var longitudinaldom = $(this.core.target).find(this.script.longitudinaldom);
		//取得侧边栏上下方元素的高度
		var lonheight = 0;
		for (var i = 0; i < longitudinaldom.length; i++) {
			lonheight = lonheight + longitudinaldom.eq(i).outerHeight(true);
		}
		//获取要操作的对象
		var obj = $(this.core.target).find(".iStyle_Feelings_Tree");
		obj.height($(this.core.target).outerHeight(true) - lonheight + 'px');
		if (this.script.scrolling)
			this.judgescroll();
		this.judgeborder();
	},
	
	//绑定宽度拖拽
	bindwidthdrag: function () {
		var dragger = $('<div class="iStyle_Feelings_Tree_WidthDragger"></div>');
		this.target.after(dragger);
		var dwidth = dragger.outerWidth(true) / 2;
		var dleft = this.target.offset().left + this.target.outerWidth(true) - dwidth;
		dragger.css({
			left: dleft + "px"
		});
		this.core.ui.binddrag(dragger, dragger, this.core.target);
		var dragthread = undefined;
		dragger.mousedown($.proxy(function (e) {
			if (dragthread == undefined)
				dragthread = window.setInterval($.proxy(function (e) {
					this.core.ui.selfadaption(true);
					var width = dragger.offset().left + dwidth - this.target.offset().left;
					this.target.css({
						width: width + 'px'
					});
					if (this.target.outerWidth(true) < 90) {
						this.slidetree(this.target.find(".iStyle_Feelings_Tree_SlideButton"));
						clearInterval(dragthread);
						dragthread = undefined;
					}
				}, this), 10);
		}, this));
		dragger.mouseup($.proxy(function (e) {
			this.core.ui.selfadaption(false);
			clearInterval(dragthread);
			dragthread = undefined;
		}, this));
	},
	//绑定单击事件
	bindclick: function () {
		//绑定收缩按钮单击事件
		if (this.target.attr("slided") != 'true')
			this.target.find(".iStyle_Feelings_Tree_SlideButton").mousedown($.proxy(function (e) {
				this.slidetree($(e.target));
			}, this));
		else {
			this.target.find(".iStyle_Feelings_Tree_SlideButton").mousedown($.proxy(function (e) {
				this.opentree();
			}, this));
		}

		//绑定二级菜单单击事件
		this.target.find(".iStyle_Feelings_Tree_TrunkTitle").mousedown($.proxy(function (e) {
			//检测发生事件DOM
			var tar = this.cleanicon($(e.target));
			//如果是树收缩状态时
			if (this.target.attr("slided") == 'true') {
				//如果是超链接三级菜单，则直接激活
				if (tar.attr("href") != null && tar.attr("href") != "" && tar.attr("href") != undefined) {
					this.activeleaf(tar);
					return false;
				} else {
					this.target.attr("autoslided", "true");
					this.slidetitle(this.target.find(".iStyle_Feelings_Tree_TrunkTitle"), tar);
				}
			} else {
				//如果标题标题展开则移除展开样式并收缩子项
				if (tar.hasClass("iStyle_Feelings_Tree_Opened")) {
					this.slidetitle(tar);
				}
				//如果二级菜单为链接则直接激活
				else if (tar.attr("href") != null && tar.attr("href") != "" && tar.attr("href") != undefined) {
					this.activeleaf(tar);
				} else {
					this.opentitle(tar);
				}
			}
		}, this));
		//绑定三级菜单单击事件
		this.target.find(".iStyle_Feelings_Tree_Leaf").click($.proxy(function (e) {
			this.activeleaf($(e.target));
			return false;
		}, this));
	},
	//防止icon 对象污染交互
	cleanicon: function (target) {
		if (target.hasClass("iStyle_Feelings_Tree_Icon"))
			var tar = target.parent()
		else
			var tar = target;
		return tar;
	},
	//收缩树
	slidetree: function (tar) {
		this.writetree();
		this.core.ui.selfadaption(true);
		this.target.attr("slided", "true");
		this.target.attr("autoslided", "false");
		this.target.find(".iStyle_Feelings_Tree_SlideButton").addClass("iStyle_Feelings_Tree_SlideButton_Folded");
		if (this.script.dragwidth) {
			$(this.core.target).find(".iStyle_Feelings_Tree_WidthDragger").attr("lockdrag", "true");
			$(this.core.target).find(".iStyle_Feelings_Tree_WidthDragger").hide(0);
		}		
		this.target.stop();
		this.target.find(".iStyle_Feelings_Tree_SlideButton").unbind();
		this.target.find(".iStyle_Feelings_Tree_TrunkTitle").css({
			"text-overflow": "clip"
		});
		
		if (!this.script.allwayshowmark)
			this.target.find(".iStyle_Feelings_Tree_TrunkTitle").css({
				"width": "150%"
			});
		this.target.animate({
			width: 1.5 + 'em'
		}, $.proxy(function () {
			this.target.find(".iStyle_Feelings_Tree_SlideButton").mousedown($.proxy(function (e) {
				this.opentree();
			}, this));
			if (this.script.scrolling)
				this.judgescroll();
			this.judgeborder();
			//this.core.ui.selfadaption(true);
			//this.core.ui.selfadaption(false);
		}, this));
		this.slidetitle(this.target.find(".iStyle_Feelings_Tree_TrunkTitle"));
		//alert(this.attribute.titlestate[0]);
		$(document).unbind(".is_mouseleave");
	},
	//打开树
	opentree: function (tar) {
		this.target.find(".iStyle_Feelings_Tree_SlideButton").removeClass("iStyle_Feelings_Tree_SlideButton_Folded");
		this.target.stop();
		this.target.find(".iStyle_Feelings_Tree_SlideButton").unbind();
		this.target.find(".iStyle_Feelings_Tree_TrunkTitle").css({
			"text-overflow": "ellipsis"
		});
		this.core.ui.selfadaption(true);
		if (!this.script.allwayshowmark)
			this.target.find(".iStyle_Feelings_Tree_TrunkTitle").css({
				"width": "100%"
			});
		if (this.attribute.width < 180) {
			$(this.core.target).find(".iStyle_Feelings_Tree_WidthDragger").css("left", 180 - $(this.core.target).find(".iStyle_Feelings_Tree_WidthDragger").outerWidth(true) / 2 + 'px');
			this.attribute.width = 180;
		}
		this.target.animate({
			width: this.attribute.width + 'px'
		}, $.proxy(function () {
			if (this.script.dragwidth)
				$(this.core.target).find(".iStyle_Feelings_Tree_WidthDragger").show(0);
			$(this.core.target).find(".iStyle_Feelings_Tree_WidthDragger").attr("lockdrag", "false");
			this.target.find(".iStyle_Feelings_Tree_SlideButton").mousedown($.proxy(function (e) {
				this.slidetree(tar);
			}, this));
			//this.core.ui.selfadaption(true);
			//this.core.ui.selfadaption(false);
		}, this));
		if (tar != null && tar) {
			this.opentitle(tar);
			this.target.attr("autoslided", "true");
			this.autoslide();
		} else {
			this.settree();
			this.target.attr("slided", "false");
		}
	},
	//读取树状态
	writetree: function () {
		var tar = $(this.core.target).find(".iStyle_Feelings_Tree");
		this.attribute.width = tar.width();
		this.attribute.slided = tar.attr("slided");
		/*if (this.target.attr("autoslided") != "true")
			if (tar.attr("slided") != "true")
				tar.find(".iStyle_Feelings_Tree_TrunkTitle").each($.proxy(function (index, element) {
					if ($(element).hasClass("iStyle_Feelings_Tree_Opened")) {
						this.attribute.titlestate[index] = true;
					} else {
						this.attribute.titlestate[index] = false;
					}
				}, this));*/
	},
	//自动收缩树
	autoslide: function () {
		this.mouseouttimer = 2;
		var intree = true;
		if (this.mouseoutthread == undefined) {
			this.mouseoutthread = window.setInterval($.proxy(function (e) {
				$(document).bind("mousemove.is_mouseleave", $.proxy(function (e) {
					var left = this.target.offset().left;
					var top = this.target.offset().top;
					var width = this.target.outerWidth(true);
					var height = this.target.outerHeight(true);
					var mx = e.clientX;
					var my = e.clientY;
					if (mx < left || my < top || mx > (left + width) || my > (top + height)) {
						intree = false;
					} else {
						intree = true;
					}
				}, this));
				if (!intree) {
					this.mouseouttimer--;
					if (this.mouseouttimer < 0) {
						clearInterval(this.mouseoutthread);
						this.mouseoutthread = null;
						this.mouseoutthread = undefined;
						this.slidetree();
					}
				} else {
					this.mouseouttimer = 2;
				}

			}, this), 500);
		}
	},
	//根据属性设定树状态
	settree: function () {
		for (var i = 0; i < this.attribute.titlestate.length; i++) {
			//console.log(this.attribute.titlestate[i]+":i"+i);
			if (this.attribute.titlestate[i]) {
				this.opentitle($(this.core.target).find(".iStyle_Feelings_Tree_TrunkTitle:eq(" + i + ")"));
			}
		}
	},
	//折叠二级菜单
	slidetitle: function (target, callbacktar) {
		if (this.slidethread == undefined)
			this.slidethread = window.setInterval($.proxy(function () {
				if (this.script.scrolling)
					this.judgescroll();
				this.judgeborder();
			}, this), 10);
		target.removeClass("iStyle_Feelings_Tree_Opened");
		target.removeClass("iStyle_Feelings_Tree_UniqueActiveLeaf");
		target.parent().find(".iStyle_Feelings_Tree_Leaves").stop();
		target.parent().find(".iStyle_Feelings_Tree_Leaves").slideUp("slow", $.proxy(function () {
			for (var i = 0; i < target.length; i++) {
				if (target.eq(i).parent().find(".iStyle_Feelings_Tree_ActiveLeaf").length > 0)
					target.eq(i).addClass("iStyle_Feelings_Tree_ActiveLeaf");
			}
			clearInterval(this.slidethread);
			this.slidethread = undefined;
		}, this));
		if (this.target.attr("autoslided") == "true")
			this.opentree(callbacktar);
		else{
			if(	this.target.attr("slided") != "true" )
				this.attribute.titlestate[this.target.find(".iStyle_Feelings_Tree_TrunkTitle").index(target)] = false;
		}
		if (this.script.cookies)
			this.setcookies();
		//this.writetree();
	},
	//打开二级菜单
	opentitle: function (target) {
		target.addClass("iStyle_Feelings_Tree_Opened");
		if (this.slidethread == undefined)
			this.slidethread = window.setInterval($.proxy(function () {
				if (this.script.scrolling)
					this.judgescroll();
				this.judgeborder();
			}, this), 10);
		target.removeClass("iStyle_Feelings_Tree_ActiveLeaf");
		if (this.target.attr("autoslided") == "true")
			target.addClass("iStyle_Feelings_Tree_UniqueActiveLeaf");
		target.parent().find(".iStyle_Feelings_Tree_Leaves").stop();
		target.parent().find(".iStyle_Feelings_Tree_Leaves").slideDown("slow", $.proxy(function () {
			clearInterval(this.slidethread);
			this.slidethread = undefined;
			this.writetree();
		}, this));
		if (this.target.attr("autoslided") != "true" && this.target.attr("slided") != "true"){
			this.attribute.titlestate[this.target.find(".iStyle_Feelings_Tree_TrunkTitle").index(target)] = true;
			//alert(this.target.find(".iStyle_Feelings_Tree_TrunkTitle").index(target));
		}
		if (this.script.cookies)
			this.setcookies();
	},
	//绑定鼠标滚轮滚动
	bindmousescroll: function () {
		var speed = this.target.find(".iStyle_Feelings_Tree_TrunkTitle").outerHeight(true); //卷动速度，根据菜单项的高度决定
		var target = this.target.find(".iStyle_Feelings_Tree_Trunks[actived='true']");		//设定卷动目标，目标为当前激活的树枝
		var wheelspeed = 0;																	//卷动计数器
		var mov = 0;																		//卷动像素值
		this.target.mousewheel($.proxy(function (e, delta) {
			if (this.target.attr("slided") == "true" && this.target.attr("autoslided") != "true")
				return false;
			if (wheelspeed < 0 && delta > 0 || wheelspeed > 0 && delta < 0) {
				wheelspeed = 0;
				return false;
			}
			wheelspeed = wheelspeed + delta * speed;
			if (this.wheelthread == undefined) {
				this.wheelthread = window.setInterval($.proxy(function (e) {
					if (wheelspeed == 0) {
						clearInterval(this.wheelthread);
						this.wheelthread = undefined;
					}
					mov = Math.sqrt(Math.abs(wheelspeed / 5) / 2);
					if (mov > 6)
						mov = 6;
					if (wheelspeed > 0) {
						wheelspeed = wheelspeed - mov;
						if (wheelspeed < 0)
							wheelspeed = 0;
						target.css({
							"top": "+=" + mov + 'px'
						});
					} else {
						wheelspeed = wheelspeed + mov;
						if (wheelspeed > 0)
							wheelspeed = 0;
						target.css({
							"top": "-=" + mov + 'px'
						});
					}
					if (this.script.scrolling)
						this.showscroll();
					this.judgeborder();
				}, this), 10);
			}
		}, this));
	},
	//激活指定的标签
	activeleaf: function (target) {
		//去除其余激活的标签
		this.target.find(".iStyle_Feelings_Tree_TrunkTitle,.iStyle_Feelings_Tree_Leaf ").removeClass("iStyle_Feelings_Tree_ActiveLeaf");
		target.addClass("iStyle_Feelings_Tree_ActiveLeaf");
		if (target.attr("target") == "_blank"){
			window.open(target.attr("href"));
			return false;
		}
		if (target.attr("href") != null && target.attr("href") != undefined && target.attr("href") != "") {
			var url = target.attr("href").replace(this.core.mainurl + "#", "");
			if(this.core.srcmode){
				this.core.die7 = true;
				$(this.core.target).find(".iStyle_Feelings_Iframe").attr("src", url);
			}
			else	
				$(this.core.target).find(".iStyle_Feelings_Iframe").get(0).contentWindow.location.replace(url);
		}
	},
	//判定卷动边界
	judgeborder: function () {
		var trunk = this.target.find(".iStyle_Feelings_Tree_Trunks[actived='true']"); //取得激活中的树
		var viewheight = this.target.outerHeight(true) - this.target.find(".iStyle_Feelings_Tree_SlideButton").outerHeight(true);
		if (trunk.outerHeight(true) > viewheight) {
			if (trunk.position().top + trunk.outerHeight(true) < viewheight) {
				trunk.css({
					top: viewheight - trunk.outerHeight(true) + 'px'
				});
			}
			if (trunk.position().top > 0)
				trunk.css({
					top: '0px'
				});
			//如果启用了滚动条则使滚动条跟随卷动
			if (this.script.scrolling) {
				var lenp = trunk.position().top / (viewheight - trunk.outerHeight(true));
				var viewheight = this.target.find(".iStyle_Feelings_Tree_Scroller").outerHeight(true) - this.target.find(".iStyle_Feelings_Tree_Dragger").outerHeight(true);
				this.target.find(".iStyle_Feelings_Tree_Dragger").css("top", viewheight * lenp + 'px');
			}
		} else {
			trunk.css({
				top: '0px'
			});
		}

	},
	//添加滚动条
	bindscroll: function () {
		//创建并添加滚动条对象
		//iStyle_Feelings_Tree_ScrollBox: 整体容器，整体作为鼠标移入的相应区域
		//iStyle_Feelings_Tree_Scroller: 滚筒条容器
		//iStyle_Feelings_Tree_Dragger: 滚动条滑块
		var scrolldom = $('<div class="iStyle_Feelings_Tree_ScrollBox"><div class="iStyle_Feelings_Tree_Scroller"><div class="iStyle_Feelings_Tree_Dragger"></div></div></div>');
		this.target.append(scrolldom);
		var scrollbox = this.target.find(".iStyle_Feelings_Tree_ScrollBox"); //滚动条相应区域
		var scroller = this.target.find(".iStyle_Feelings_Tree_Scroller"); //滚动条容器
		var dragger = $(this.core.target).find(".iStyle_Feelings_Tree_Dragger"); //滚动条拖拽滑块
		//设置滚动条未激活时的透明度
		scroller.animate({
			opacity: 0.2
		}, 0);
		//鼠标移入响应区域时显示滚动条
		scrollbox.mouseenter($.proxy(function (e) {
			this.showscroll();
		}, this));
		//鼠标在响应区域内移动时显示滚动条
		scrollbox.mousemove($.proxy(function (e) {
			this.showscroll();
		}, this));
		scrollbox.mouseleave($.proxy(function (e) {
			//鼠标离开响应区域时收缩滚动条并关闭滚动响应线程
			this.hidescroll();
		}, this));
		//鼠标拖拽滑块时
		dragger.mousedown($.proxy(function (e) {
			var targettrunk = this.target.find(".iStyle_Feelings_Tree_Trunks[actived='true']");
			var viewheight = this.target.outerHeight(true) - this.target.find(".iStyle_Feelings_Tree_SlideButton").outerHeight(true);
			var scrollheight = scroller.outerHeight() - dragger.outerHeight(true);
			dragger.mousemove($.proxy(function (e) {
				//取得滚动条的高度百分比
				var scale = dragger.position().top / scrollheight;
				targettrunk.animate({
					top: -(targettrunk.outerHeight(true) - viewheight) * scale + 'px'
				}, 0);
			}, this));
		}, this));
		dragger.mouseup($.proxy(function (e) {
			scrollbox.mouseleave($.proxy(function (e) {
				this.hidescroll();
			}, this));
		}, this));
		//空白区域点击事件
		scroller.mousedown($.proxy(function (e) {
			if ($(e.target).attr("class") == "iStyle_Feelings_Tree_Dragger")
				return false;
			var tartop = this.getoffset(e).offsetY - dragger.outerHeight(true) / 2;
			var scrolllength = scroller.outerHeight(true) - dragger.outerHeight(true)
			if (tartop > scrolllength)
				tartop = scrolllength;
			if (tartop < 0)
				tartop = 0;
			dragger.stop();
			dragger.animate({
				top: tartop + 'px'
			});
			var lenp = tartop / scrolllength;
			var vlength = this.target.outerHeight(true) - this.target.find(".iStyle_Feelings_Tree_SlideButton").outerHeight(true) - this.target.find(".iStyle_Feelings_Tree_Trunks[actived='true']").outerHeight(true);
			this.target.find(".iStyle_Feelings_Tree_Trunks[actived='true']").stop();
			this.target.find(".iStyle_Feelings_Tree_Trunks[actived='true']").animate({
				top: vlength * lenp + 'px'
			});
		}, this));
		//鼠标在滚动条区域滚动条透明度降低
		scroller.mouseenter($.proxy(function (e) {
			scroller.stop();
			scroller.animate({
				width: '1.2em'
			}, 0);
			scroller.animate({
				opacity: 0.65
			}, 250);
		}, this));
		//鼠标离开滚动条区域滚动条透明度提高
		scroller.mouseleave($.proxy(function (e) {
			scroller.stop();
			scroller.animate({
				opacity: 0.2
			}, 250);
		}, this));
		this.core.ui.binddrag(dragger, dragger, dragger.parent());
	},
	//取得offset 数值，用以支持在FF浏览器中获取 offset值
	getoffset: function (event) {
		var target = event.currentTarget // 当前触发的目标对象
		if (event.offsetX) {
			return {
				offsetX: event.offsetX,
				offsetY: event.offsetY
			}
		} else {
			var scrollTop = document.documentElement.scrollTop;
			var scrollLeft = document.documentElement.scrollLeft
			var pageX = event.clientX + scrollLeft;
			var pageY = event.clientY + scrollTop;
			return {
				offsetX: (target.offsetX || pageX - target.offsetLeft),
				offsetY: (target.offsetY || pageY - target.offsetTop)
			}
		}
	},
	//判定隐藏显示滚动条
	judgescroll: function () {
		var trunksheight = $(this.core.target).find(".iStyle_Feelings_Tree_Trunks[actived='true']").outerHeight(true);
		var displayheight = $(this.core.target).find(".iStyle_Feelings_Tree").height() - $(this.core.target).find(".iStyle_Feelings_Tree_SlideButton").outerHeight(true);
		if (trunksheight <= displayheight) {
			$(this.core.target).find(".iStyle_Feelings_Tree_ScrollBox").hide(0);
			this.hidescroll();
		} else {
			$(this.core.target).find(".iStyle_Feelings_Tree_ScrollBox").show(0);
			this.showscroll();
		}

	},
	//显示滚动条
	showscroll: function () {
		if (this.scrollthread == undefined) {
			this.target.find(".iStyle_Feelings_Tree_Scroller").stop();
			this.target.find(".iStyle_Feelings_Tree_Scroller").animate({
				width: '1.2em'
			});
			this.scrolltimer = 300;
			this.scrollthread = window.setInterval($.proxy(function () {
				trunksheight = this.target.find(".iStyle_Feelings_Tree_Trunks[actived='true']").outerHeight(true);
				displayheight = this.target.height() - this.target.find(".iStyle_Feelings_Tree_SlideButton").outerHeight(true);
				var lenp = displayheight / trunksheight;
				this.target.find(".iStyle_Feelings_Tree_Dragger").height(this.target.height() * lenp / (1 + lenp) + 'px');
				this.scrolltimer--;
				if (this.scrolltimer < 0)
					this.hidescroll();
			}, this), 10);

		} else {
			this.scrolltimer = 300;
		}
	},
	//隐藏滚动条
	hidescroll: function () {
		var scroller = this.target.find(".iStyle_Feelings_Tree_Scroller");
		scroller.stop();
		scroller.animate({
			width: '0em'
		});
		scroller.animate({
			opacity: 0.2
		}, 0);
		clearInterval(this.scrollthread);
		this.scrollthread = undefined;
		this.scrolltimer = 0;
	}
}

/*
	用户交互响应
	vesion:sfff0.1
*/
function iStyle_Feelings_UserInteraction(core) {
	this.core = core;
	this.script = this.core.script.userinteraction;
	this.swidth = 0;
	this.sheight = 0;
	this.selfadaptthread = undefined;
	this.selfadapttimer = 0;
	this.longitudinaldom = undefined;
	this.latitudinaldom = undefined;
	this.setabsolute = false;
	this.initialize();
}
iStyle_Feelings_UserInteraction.prototype = {
	initialize: function () {
		this.selfadptar = $(this.script.self_adaption.target);
		this.swidth = this.selfadptar.outerWidth(true) - this.selfadptar.width();
		this.sheight = $(this.script.self_adaption.target).outerHeight(true) - $(this.script.self_adaption.target).height();
		this.longitudinaldom = $(this.script.self_adaption.longitudinaldom);
		this.latitudinaldom = $(this.script.self_adaption.latitudinaldom);
	},

	//绑定拖拽事件
	binddrag: function (object, movetarget, borderobj) {
		new iStyle_Plugins_Dragger(object, movetarget, borderobj);
	},

	//自适应
	selfadaption: function (switchon) {
		this.selfadapttimer = 450;
		if (switchon) {
			if (this.selfadaptthread == undefined) {
				this.selfadaptthread = window.setInterval($.proxy(function (e) {
					var adwidth = 0;
					var adheight = 0;
					for (var i = 0; i < this.longitudinaldom.length; i++) {
						adheight = adheight + this.longitudinaldom.eq(i).outerHeight(true);
					}
					for (var i = 0; i < this.latitudinaldom.length; i++) {
						adwidth = adwidth + this.latitudinaldom.eq(i).outerWidth(true);
					}
					this.selfadptar.css({
						width: $(this.core.target).outerWidth(true) - adwidth - this.swidth + 'px',
						height: $(this.core.target).height() - adheight - this.sheight
					});
					
					if (!this.setabsolute) {
						setTimeout($.proxy(function(){
							var right = $(this.core.target).outerWidth(true) - this.selfadptar.offset().left + parseInt(this.selfadptar.css("margin-left")) - this.selfadptar.outerWidth(true);
							var top = this.selfadptar.offset().top - parseInt(this.selfadptar.css("margin-top"));
							this.selfadptar.css({
								position: "absolute",
								right: right + 'px',
								top: top + 'px'
							});
							if (this.core.script.menu.switchon && this.core.script.menu.algintoiframe) {
								$(this.core.target).find(".iStyle_Feelings_MainMenu").css({
									position: "absolute",
									right: right + parseInt(this.selfadptar.css("margin-right")) + 'px'
								});
							}
						},this),100);
						this.setabsolute = true;
					}
					this.selfadapttimer--;
					if (this.selfadapttimer < 0) {
						clearInterval(this.selfadaptthread);
						this.selfadaptthread = undefined;
					}
					if (this.core.script.menu.switchon && this.core.script.menu.algintoiframe) {
						$(this.core.target).find(".iStyle_Feelings_MainMenu").css({
							width: $(this.core.target).outerWidth(true) - adwidth - this.swidth + 'px'
						});
						if (this.core.script.menu.autoresize)
							this.core.menu.resize();
					}
				}, this), 10);
			}
		} else {
			this.selfadapttimer = 450;
			clearInterval(this.selfadaptthread);
			this.selfadaptthread = undefined;
		}
	}
}



/*
	系统内窗口
	vesion:sfff0.1
*/
function iStyle_Feelings_Window(core) {
	this.initialize();
}
iStyle_Feelings_Window.prototype = {
	initialize: function () {}
}

/*
	气泡小窗口
	vesion:sfff0.1
*/
function iStyle_Feelings_Listbox(core) {
	this.initialize();
}
iStyle_Feelings_Listbox.prototype = {
	initialize: function () {}
}

/*
	拖拽事件处理插件
	vesion:sfff0.1
*/
function iStyle_Plugins_Dragger(object, mtarget, borderobj) {
	this.target = $(object); //接收拖拽事件的对象
	this.mtarget = $(mtarget); //拖拽事件要移动的对象
	this.border = $(borderobj); //对象移动边缘对象
	this.initialize();
}
iStyle_Plugins_Dragger.prototype = {
	initialize: function () {
		this.bind();
	},

	//绑定拖拽事件
	bind: function () {
		this.target.mousedown($.proxy(function (e) {
			e = e || window.event;
			var mousedownX = e.clientX; //获取鼠标按下时的位置 
			var mousedownY = e.clientY;
			var mwidth = this.mtarget.outerWidth(true); //获取要移动目标的外围宽度与高度
			var mheight = this.mtarget.outerHeight(true);
			var bstartX = this.border.offset().left; //获取作为边界的对象的位置与高宽
			var bstartY = this.border.offset().top;
			var bwidth = this.border.outerWidth(true);
			var bheight = this.border.outerHeight(true);
			var fstartX = this.mtarget.offset().left;
			var fstartY = this.mtarget.offset().top;
			var fixX = fstartX - this.mtarget.position().left;
			var fixY = fstartY - this.mtarget.position().top;
			//绑定鼠标移动事件
			$(document).bind("mousemove.is_drag", $.proxy(function (e) {
				var mx = e.clientX;
				var my = e.clientY;
				var left = mx - mousedownX;
				var top = my - mousedownY;
				if (this.mtarget.attr("lockdrag") != "true") {
					this.mtarget.css({
						"top": (fstartY + top - fixY) + 'px',
						"left": (fstartX + left - fixY) + 'px'
					});
				} else {
					$(document).unbind(".is_drag");
				}
				var startX = this.mtarget.offset().left;
				var startY = this.mtarget.offset().top;
				if (startX - bstartX < 0)
					this.mtarget.css("left", '0px');
				if (startX + mwidth > bstartX + bwidth)
					this.mtarget.css("left", bwidth - mwidth + 'px');
				if (startY - bstartY < 0)
					this.mtarget.css("top", '0px');
				if (startY + mheight > bstartY + bheight)
					this.mtarget.css("top", bheight - mheight + 'px');
			}, this));
		}, this));
		//当鼠标按键抬起和鼠标离开响应区域事接触鼠标移动绑定释放资源
		this.target.mouseup($.proxy(function (e) {
			$(document).unbind(".is_drag");
		}, this));
		$(window).mouseup($.proxy(function (e) {
			$(document).unbind(".is_drag");
		}, this));
	},

	//取得offset 数值，用以支持在FF浏览器中获取 offset值
	getoffset: function (event) {
		var target = event.currentTarget // 当前触发的目标对象
		if (event.offsetX) {
			return {
				offsetX: event.offsetX,
				offsetY: event.offsetY
			}
		} else {
			var scrollTop = document.documentElement.scrollTop;
			var scrollLeft = document.documentElement.scrollLeft
			var pageX = event.clientX + scrollLeft;
			var pageY = event.clientY + scrollTop;
			return {
				offsetX: (target.offsetX || pageX - target.offsetLeft),
				offsetY: (target.offsetY || pageY - target.offsetTop)
			}
		}
	}
}