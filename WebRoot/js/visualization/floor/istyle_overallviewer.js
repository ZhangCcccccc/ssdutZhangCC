//istyle 全景可视化实验室框架
//版本号 0.1

function iStyle_Overallviewer(jsonscript){
	this.images=new Array();
	this.script=jsonscript;
	this.target=$(".istyle_overallviewer_conteiner[name='"+this.script.楼层名+"']");
	this.index=undefined;   //页面指向指针
	this.rooms=new Array();
	this.loadthread=undefined;
	this.movethread=undefined;
	this.mouseholdthread=undefined;
	this.mouseposthread=undefined;
	this.initialise();
	this.mx=null;
	this.my=null;
	this.mousex=null;
	this.mousey=null;
	}
	iStyle_Overallviewer.prototype={
		initialise:function(){
            $(this.target).append('<div class="istyle_overallviewer_shade"><div class="istyle_overallviewer_respondbox"></div></div><div class="istyle_overallviewer_respondbox"></div><div class="istyle_overallviewer_canvas"></div><div class="istyle_overallviewer_dropbox"></div><img src="'+this.script.加载动画+'" class="istyle_loadingimg">');
			$(".istyle_overallviewer_dropbox").animate({opacity:0},0);
			$(this.target).find($(".istyle_overallviewer_rooms")).hide(0);
			this.userinterface();
			this.setwindow();
			/*$(this.target).mouseleave($.proxy(function(){
				this.clearuserinterval();
				},this));*/
			if(this.script.模式==undefined)
				this.script.模式="默认";
			switch(this.script.模式){
				case "自动":
					var request = undefined;
					request=this.getrequest();
					name=request["name"];
					this.getindex(name);
					break;
				case "默认":
					this.index=0;
					break;	
				default:
					//根据名称确定默认房间
					this.getindex(this.script.模式);
					break;
				}
				//$(".istyle_overallviewer_conteiner:['name'='"+this.script.楼层名+"']")
			for(var i=0;i<this.script.房间.length;i++){
				if(this.script.标签!=undefined){
					this.rooms[i]=new iStyle_Rooms(this,this.script.房间[i],this.script.房间[i].名称,this.target,this.script.标签);
					}
				else{
					this.rooms[i]=new iStyle_Rooms(this,this.script.房间[i],this.script.房间[i].名称,this.target);
					}
				}
			//显示初始图片	
			this.setrooms();
			$(window).resize($.proxy(function(){
				this.resize();
				},this));
			$(this.target).attr('unselectable','on') 
				.css({'-moz-user-select':'-moz-none', 
				'-moz-user-select':'none', 
				'-o-user-select':'none', 
				'-khtml-user-select':'none', /* you could also put this in a class */ 
				'-webkit-user-select':'none',/* and add the CSS class here instead */ 
				'-ms-user-select':'none', 
				'user-select':'none' 
				}).bind('selectstart', function(){ return false; }); 
			//setInterval($.proxy(function(){this.resize();},this),10);
			},
		
		//设置窗口
		setwindow:function(){
			var win=$("<div class='istyle_overallviewer_window'><div class='wintitle'><span></span><div class='closebtn'>关闭</div></div><div class='wininner'></div></div>");
			$(this.target).append(win);
			win.slideUp(0);
			win.find($(".closebtn")).mousedown($.proxy(function(){
				var target=$(this.target).find($('.istyle_overallviewer_window'));
				target.find($('.wininner')).text(' ');
				target.slideUp(500);
				},this));
			},
		
		//开启小窗口
		openwindow:function(title,inner,src){
			var target=$(this.target).find($('.istyle_overallviewer_window'));
			target.find($('.wintitle span')).text(title);
			target.slideDown(500);
			if(src!=null&&src!=undefined){
				target.find($('.wininner')).append('<iframe class="winiframe" src="'+src+'"></iframe>');
				}
			else{
				var conteiner=$('<div class="winconteiner"></div>');
				conteiner.append(inner);
				target.find($('.wininner')).append(conteiner);
				}
			},
			
		userinterface:function(){
			$(this.target).append('<div class="istyle_overallviewer_ctrlbuttons"><div class="istyle_overallviewer_leftbutton">向左</div><div class="istyle_overallviewer_rightbutton">向右</div></div>');
			var switcher=$('<div class="istyle_overallviewer_switchbuttons"></div>');
			$(this.target).append(switcher);
			for(i=0;i<this.script.房间.length;i++){
				switcher.append($('<div index="'+i+'">'+this.script.房间[i].名称+'</div>'));
				}
			this.buttonbind();
			this.touchbind();
			},
		
		//交互按钮事件绑定
		buttonbind:function(){
			var object=$(this.target).find($(".istyle_overallviewer_ctrlbuttons"));
			object.find($(".istyle_overallviewer_rightbutton")).mousedown($.proxy(function(event){
				this.clearuserinterval();
				$(event.target).addClass('istyle_overallviewer_ctrlbuttons_press');
				this.mouseholdthread=setInterval($.proxy(function(){
					this.moveroom({x:-700,y:0});
					},this),10);
				},this));
				
			object.find($(".istyle_overallviewer_rightbutton")).mouseup($.proxy(function(event){
				$(event.target).removeClass('istyle_overallviewer_ctrlbuttons_press');
				this.clearuserinterval();
				},this));
			object.find($(".istyle_overallviewer_leftbutton")).mousedown($.proxy(function(event){
				this.clearuserinterval();
				this.resize();
				$(event.target).addClass('istyle_overallviewer_ctrlbuttons_press');
				this.mouseholdthread=setInterval($.proxy(function(){
					this.moveroom({x:700,y:0});
					},this),10);
				},this));
			object.find($(".istyle_overallviewer_leftbutton")).mouseup($.proxy(function(event){
				this.clearuserinterval();
				$(event.target).removeClass('istyle_overallviewer_ctrlbuttons_press');
				},this));
			var switcher=$(this.target).find($(".istyle_overallviewer_switchbuttons div"));
			for(var i=0;i<switcher.length;i++){
				switcher.eq(i).mousedown($.proxy(function(event){
					this.index=$(event.target).attr('index');
					$(event.target).addClass("istyle_overallviewer_switchbuttons_press");
					this.setrooms();
					},this));
				switcher.eq(i).mouseup(function(){
					$(this).removeClass("istyle_overallviewer_switchbuttons_press");
					});
				switcher.eq(i).mouseleave(function(){
					$(this).removeClass("istyle_overallviewer_switchbuttons_press");
					});	
				};
				
			},
		
		
		//附加鼠标拖拽算法
		touchbind:function(){
			var object=$(this.target).find($(".istyle_overallviewer_shade"));
			object.mousemove($.proxy(function(e){
					var e = e || window.event;
					this.mousex=e.screenX;
					this.mousey=e.screenY;
					},this));
			object.mousedown($.proxy(function(e){
				this.clearuserinterval();
				this.moveroom({x:0,y:0});
				this.mouseholdthread=setInterval($.proxy(function(){
					var mx=this.mousex;
					var my=this.mousey;						
					this.mouseposthread=setTimeout($.proxy(function(){
						$(this.target).find($(".istyle_overallviewer_canvas,.istyle_overallviewer_respondbox")).css({top:"+="+this.mousey-my,left:"+="+(this.mousex-mx)+"px"});
						this.judgeborder();
						},this),10);
					},this),11);
				},this));
			object.mouseleave(
				$.proxy(function(e){
					//object.unbind("mousemove");
					this.clearuserinterval();
					},this));	
			object.mouseup(
				$.proxy(function(e){
					//object.unbind("mousemove");
					this.clearuserinterval();
					},this));
			},
				
		//清除用户按键响应线程
		clearuserinterval:function(){
			if(this.mouseholdthread!=undefined){
				clearInterval(this.mouseholdthread);
				this.mouseholdthread=undefined;
				}
			if(this.mouseposthread!=undefined){
				clearTimeout(this.mouseposthread);
				this.mouseposthread=undefined;
				}
			}, 
		
		//初始化房间显示
		setrooms:function(){
			$(this.target).find($(".istyle_overallviewer_switchbuttons div")).removeClass("istyle_overallviewer_switchbuttons_active");
			$(this.target).find($(".istyle_overallviewer_switchbuttons div:eq("+this.index+")")).addClass("istyle_overallviewer_switchbuttons_active");
			this.rooms[this.index].showroom();
			},
			
		//重设定镜头尺寸（宽度）
		resize:function(){
			var canvas=$(this.target).find($(".istyle_overallviewer_canvas,.istyle_overallviewer_respondbox"));
			var zoomlv=$(this.target).find($(".istyle_overallviewer_canvas")).find($(".istyle_overallviewer_img")).height()/this.rooms[this.index].bgheight;
			var width=this.rooms[this.index].bgwidth*zoomlv;
			canvas.width(width*3+'px');
			$(".istyle_overallviewer_img_clone").width(width+'px');
			$(".istyle_overallviewer_respondclone").width(width+'px');
			this.judgeborder();
			this.zoomsegment(zoomlv);
			},		
		//缩放交互图块
		zoomsegment:function(zoomlv){
			var objects=$(this.target).find($(".istyle_overallviewer_segment"));
			for(var i=0;i<objects.length;i++){
				objects.eq(i).css({'height':objects.eq(i).attr('height')*zoomlv+'px','width':objects.eq(i).attr('width')*zoomlv+'px','left':objects.eq(i).attr('x')*zoomlv+'px','top':objects.eq(i).attr('y')*zoomlv+'px'});
				var mark=$(this.target).find($(".istyle_overallviewer_marks[name='"+objects.eq(i).attr('name')+"']"));
				var mwidth=mark.find('img').outerWidth(true)+mark.find('div').outerWidth(true);
				mark.css({'width':mwidth+'px','left':objects.eq(i).attr('x')*zoomlv+objects.eq(i).width()/2-mark.width()/2+'px','top':objects.eq(i).attr('y')*zoomlv-mark.height()-3+'px'})
				}
			},
	
		//设置鼠标按下按钮时镜头速度
		//script.x 横轴位移  负值向左卷动，正值向右卷动
		//script.y 纵轴位移  负值向上卷动，正值向右卷动
		setmove:function(script){
			mx=script;
			},
		
		//移动房间镜头	
		moveroom:function(script){
			this.mx=script.x; //镜头的横向移轴
			this.my= script.y; //镜头的纵向移轴
			//镜头的移轴线程
			if(this.movethread==undefined)
				this.movethread=setInterval($.proxy(function(){
					//当没有移动镜头的时候消除镜头线程
					if(this.mx==0&&this.my==0){
						clearInterval(this.movethread);
						this.movethread=undefined;
						}
					var speedx=this.mx/50;
					if(this.mx>0&&speedx>10)
						speedx==10;
					if(this.mx<0&&speedx>-0.01)
						speedx=-0.01;
					if(speedx)
					var speedy=this.my/50;
					if(speedx>10)
						speedy==10;
					//$(this.target).find($(".istyle_overallviewer_canvas,.istyle_overallviewer_respondbox")).stop();
					//$(this.target).find($(".istyle_overallviewer_canvas,.istyle_overallviewer_respondbox")).animate({opacity:1},0);
					$(this.target).find($(".istyle_overallviewer_canvas,.istyle_overallviewer_respondbox")).css({top:"+="+speedy+"%",left:"+="+speedx+"%"});
					this.mx=this.mx-speedx;
					this.my=this.my-speedy;
					if(this.mx>-0.01&&this.mx<0.01)
						this.mx=0;
					this.judgeborder();
					},this),10);
					
			//$(this.target).find(".istyle_overallviewer_img").animate({top:"+=100px"},1000);
			},
		
				
		//边界判定,重置镜头位置
		judgeborder:function(){
			var judgeobj=$(this.target).find($(".istyle_overallviewer_canvas,.istyle_overallviewer_respondbox"));
			var imgwidth=$(this.target).find($(".istyle_overallviewer_img_clone")).width();
			if(judgeobj.offset().left<0){
				if(judgeobj.offset().left-$(this.target).width()<-judgeobj.width()){
					judgeobj.css("left",judgeobj.offset().left+imgwidth+'px');
					}
				}
			else if(judgeobj.offset().left!=0){
				judgeobj.css("left",judgeobj.offset().left-imgwidth+'px');
				}
			},
		
		//根据房间名获取焦点
		getindex:function(roomname){
			for(i=0;i<this.script.房间.length;i++){
				if(roomname==this.script.房间[i].名称){
					this.index=i;
					break;
					}
				}
			},
		
		getrequest:function(){
			var url = location.search; //获取url中"?"符后的字串
            var theRequest = new Object();
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                strs = str.split("&");
                for ( var i = 0; i < strs.length; i++) {
                    theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
                }
            }
            return theRequest;
			}	
		};


function iStyle_Rooms(obj,script,name,object,marksrc){
	this.core=obj;				//父级类
	this.script=script;			//图块脚本
	this.marksrc=marksrc;		//图标路径
	this.src=script.图像;		//图像url
	this.blocksnum=0;			//总块数
	this.loadedblocknum=0; 		//加载完成的块数
	this.bgloaded=0;			//背景大图加载状态
	this.loaded=0;				//总体加载进程
	this.defaultheight=null;   //加载的图像的默认高度坐标
	this.testthread=undefined; //测试加载是否完成的线程
	this.target=object;  //要添加到的DOM对象
	this.image=document.createElement("img");
	this.segments=new Array();
	this.name=name;
	this.initialise();
	}
	iStyle_Rooms.prototype={
		initialise:function(){
			this.image.src=this.src;
			$(this.image).load($.proxy(function(){this.onloaded();},this));
			//$(this.image).bind("load",this.onloaded(),false);
			$(this.image).attr({"id":this.name,"class":"istyle_overallviewer_img"});
			$(this.target).find($(".istyle_overallviewer_dropbox")).append(this.image);
			var script=this.script.交互;
			this.blocksnum=script.length;
			for(var i=0;i<script.length;i++){
				this.segments[i]=document.createElement("img");
				this.segments[i].src=script[i].src;
				$(this.target).find($(".istyle_overallviewer_dropbox")).append(this.segments[i]);
				$(this.segments[i]).load($.proxy(function(event){this.addsegments(event);},this));
				}
				//$(this.segments).load($.proxy(function(event){this.addsegments(event);},this));
			},
		showroom:function(){
			$(this.target).find(".istyle_overallviewer_img_clone,.istyle_overallviewer_respondclone").remove();
			$(this.target).find(".istyle_overallviewer_respondbox,.istyle_overallviewer_canvas").stop();
			$(this.target).find(".istyle_overallviewer_respondbox,.istyle_overallviewer_canvas").animate({opacity:0},0);
			$(this.target).find($(".istyle_loadingimg")).stop();
			$(this.target).find($(".istyle_loadingimg")).show(0);
			$(this.target).find($(".istyle_loadingimg")).animate({opacity:1},"fast");
			if(this.loaded!=1){
				this.testthread=setInterval($.proxy(function(){
					if(this.loaded==1){
						clearInterval(this.testthread);
						this.showimage();
						}
					},this),10);
				}
			else
				this.showimage();
			},	
			
		showimage:function(speed){
			if(speed==null)
				speed=1500;
			var clone=$(this.image).clone();
			clone.addClass("istyle_overallviewer_img_clone");
			var canvas=$(this.target).find(".istyle_overallviewer_canvas");
			canvas.append(clone);
			canvas.append(clone.clone(true));
			canvas.append(clone.clone(true));
			var wid=$(this.target).find(".istyle_overallviewer_img_clone").width();	
			canvas.css("left",-wid+'px');
			$(this.target).find($(".istyle_loadingimg")).stop();
			$(this.target).find($(".istyle_loadingimg")).animate({opacity:0},"fast");
			$(this.target).find($(".istyle_loadingimg")).hide(0);
			$("#"+this.name).animate({opacity:1},speed);
			canvas.width(wid+'px');
			$(this.target).find(".istyle_overallviewer_canvas").stop();
			$(this.target).find(".istyle_overallviewer_canvas").animate({opacity:1},speed);
			this.addrespondbox(clone.width(),speed);
			},
		
		addrespondbox:function(width,speed){
			object=$(this.target).find($(".istyle_overallviewer_respondbox"));
			var clone=$('<div class="istyle_overallviewer_respondclone"></div>');
			var script=this.script.交互;
			this.blocksnum=script.length;
			for(i=0;i<script.length;i++){
				var img=$(this.segments[i]).clone(true);
				img.unbind();
				var mark=$("<div class='istyle_overallviewer_marks' name='"+script[i].名称+"'></div>");
				mark.mousedown($.proxy(function(event){
					var name=$(event.target).parent().attr("name");
					var target=$(this.target).find($(".istyle_overallviewer_rooms[name='"+this.name+"']")).find($(".istyle_overallviewer_items[name='"+name+"']"));
					if(target.attr('target')!=null&&target.attr('target')!=undefined){
						this.core.index=$(this.target).find($(".istyle_overallviewer_rooms")).index($(this.target).find($(".istyle_overallviewer_rooms[name='"+target.attr('target')+"']")));
						this.core.setrooms();
						}
					else
						if(target.attr('url')!=null&&target.attr('url')!=undefined){
							window.open(target.attr('url'));
							}
						else
							if(target.attr('src')!=null&&target.attr('src')!=undefined){
								//alert(target.attr('src'));
								this.core.openwindow(target.attr('name'),null,target.attr('src'));
								}	
							else{
								this.core.openwindow(target.attr('name'),target.find('.istyle_overallviewer_inners').clone(true));
								}
						
					
					},this));
				var div=$("<div>"+script[i].名称+"</div>");
				mark.append(div);
				
				if(this.marksrc!=undefined){
					var mimg=$("<img class='istyle_overallviewer_markicons' src='"+this.marksrc+"'>");
					mark.append(mimg);
					mimg.prev().animate({opacity:0},0);
					mimg.mouseenter(function(){
						var target=$(this).parent().parent().parent().parent().next().find($(".istyle_overallviewer_segment[name='"+$(this).parent().attr("name")+"']"));
						$(this).prev().stop();
						$(this).prev().animate({opacity:1},500);
						target.stop();
						target.animate({opacity:1},500);
						});
					mimg.mouseleave(function(){
						var target=$(this).parent().parent().parent().parent().next().find($(".istyle_overallviewer_segment[name='"+$(this).parent().attr("name")+"']"));
						$(this).prev().stop();
						$(this).prev().animate({opacity:0},500);
						target.stop();
						target.animate({opacity:0},500);
						});
					}
				else{
					div.mouseenter(function(){
						var target=$(this).parent().parent().parent().parent().next().find($(".istyle_overallviewer_segment[name='"+$(this).parent().attr("name")+"']"));
						$(this).prev().stop();
						$(this).prev().animate({opacity:1},500);
						target.stop();
						target.animate({opacity:1},500);
						});
					div.mouseleave(function(){
						var target=$(this).parent().parent().parent().parent().next().find($(".istyle_overallviewer_segment[name='"+$(this).parent().attr("name")+"']"));
						target.stop();
						target.animate({opacity:0},500);
						});
					}
					
				$(img).mouseenter(function(){
					$(this).animate({opacity:1},1000);
					});
				$(img).addClass("istyle_overallviewer_segment");
				$(img).attr({'x':script[i].x,'y':script[i].y,'name':script[i].名称});
				$(img).css({"left":script[i].x+"px","top":script[i].y+"px"});
				$(img).animate({opacity:0},0);
				clone.append($(img));
				clone.append(mark);
				clone.width(width+'px');
				}
			object.append(clone);
			object.append(clone.clone(true));
			object.append(clone.clone(true));
			object.eq(0).find($(".istyle_overallviewer_segment")).remove();
			object.eq(1).find($(".istyle_overallviewer_marks")).remove();
			object.css("left",-width+"px");
			object.width(width*3+'px');
			$(this.target).find(".istyle_overallviewer_respondbox").stop();
			$(this.target).find(".istyle_overallviewer_respondbox").animate({opacity:1},speed);
			this.core.resize();
			},
		
		//房间大图加载完成后执行
		onloaded:function(){
			this.bgloaded=1;
			this.bgheight=$("#"+this.name).height();
			this.bgwidth=$("#"+this.name).width();
			if(this.loadedblocknum==this.blocksnum){
				this.finishload();
				}	
			},
			
		//单个交互瓷砖图片加载完成后执行
		addsegments:function(event){
			$(event.target).unbind();
			//alert($(event.target).attr('src'))
			this.loadedblocknum++;
			if(this.loadedblocknum==this.blocksnum){
				if(this.bgloaded==1){
					this.finishload();
					}
				}
			},
			
		//全部图像资源加载完成后执行
		finishload:function(){
			
			for(i=0;i<this.segments.length;i++){
				$(this.segments[i]).attr({"width":$(this.segments[i]).width(),"height":$(this.segments[i]).height()});
				}
			this.loaded=1;
			}
		};