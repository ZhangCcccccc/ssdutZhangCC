// JavaScript Document
function iStyle_TableFix(script){
	this.script = script;
	this.target = $(this.script.操作对象);	
	this.browser = navigator.appName;
	if(window.navigator.userAgent.indexOf("Chrome") >= 0)
		this.chrome = 1;
	else 
	 	this.chrome = 0;
	if (navigator.userAgent.indexOf('Firefox') >= 0)
		this.ff = 1;
	else 
		this.ff = 0;
	this.bind();
}
iStyle_TableFix.prototype = {
	bind : function(){
		if(this.script.左侧悬浮){
			this.fixleft(this.script.左侧悬浮列数,this.script.位移修正,this.script.左悬浮id,this.script.行合并模式);
		}
		if(this.script.顶部悬浮){
			this.fixtop(this.script.顶部悬浮行数,this.script.顶部悬浮id,this.script.顶部宽度自适应,this.script.顶部位置修正);
		}
	},
	fixleft : function(tar,position,id,rowspan){
		var tbodytr = this.target.clone(true);
		tbodytr.find("thead").remove();
		tbodytr.find("tbody").attr("id",id);
		var fix = 0;
		if(!this.chrome){
			fix = 1;
		}
		var top = this.target.find("tbody").offset().top + position + 2 - this.ff;
		var left = this.target.find("tbody").offset().left - fix;
		
		tbodytr.css({
			position : "absolute",
			top : top + 'px',
			left : left + 'px',
			zIndex : 1000
		});	
		var num =  tar;
		var browser = navigator.appName;
		if(browser == "Microsoft Internet Explorer" || browser == "Netscape")
			num = num - 1;
		if(rowspan){
			tbodytr.find("tr").attr("rownum",0);
			var rowspantd = tbodytr.find("td[rowspan]");
			for(var i = 0 ; i < rowspantd.length; i++){
				var tdindex = rowspantd.eq(i).parent().find("td").index(rowspantd.eq(i));
				if(tdindex < tar){
					var index = tbodytr.find("tr").index(rowspantd.eq(i).parent());				
					var rownum = parseInt(rowspantd.eq(i).attr("rowSpan"));
					for(var k = 1 ; k < rownum ; k++){
						tbodytr.find("tr").eq(index+k).attr("rownum",parseInt(tbodytr.find("tr").eq(index+k).attr("rownum"))+1);
					}
				}
			}	
			for(var i = 0 ; i < tbodytr.find("tr").length ; i++){
				var gt = num;
				var tr = tbodytr.find("tr").eq(i);
				gt = gt - parseInt(tr.attr("rownum"));
				tr.find("td:gt("+ gt +")").remove();
				tr.height(this.target.find("tbody tr:eq(" + i + ")").innerHeight());
				if(gt<0){
					tr.animate({opacity:0},0);
					tr.find("td").remove();
				}
			}
		}
		else{
			tbodytr.find("tr").each($.proxy(function(index, element) {
				tbodytr.find("tr:eq(" + index + ")").find("td:gt(" + num + ")").remove();
			},this));
		}
		this.target.find("table").append(tbodytr);
		$(window).bind("scroll.fixlefttd",$.proxy(function(){			
			if($(window).scrollLeft() > left){
				tbodytr.show(0);
				tbodytr.css("left",$(window).scrollLeft()+"px");
			}
			else
				tbodytr.hide(0);
			
		},this));
	},
	fixtop : function(tar,id,widfix,pos){
		var tbodytr = this.target.clone(true);
		var gt = tar - 1;
		tbodytr.find("tbody").remove();
		tbodytr.find("thead").attr("id",id);
		tbodytr.find("table").css("table-layout","fixed");
		tbodytr.find("tr:gt(" + gt + ")").remove();
		//tbodytr.find("td,th").append($('<div style = "width:300px;height:0px;"></div>'));
		var fix = 0;
		if(!this.chrome){
			fix = 1;
		}
		var browser = navigator.appName;
		var b_version = navigator.appVersion; //获取浏览器版本
		var version = b_version.split(";");
		if (version.length > 1)
			var trim_Version = version[1].replace(/[ ]/g, "");
		if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE7.0") {
			fix = 0;
		}
		
		var top = this.target.find("thead").offset().top - fix;
		var left = this.target.find("thead").offset().left - fix;
		tbodytr.css({
			position : "absolute",
			top : top + 'px',
			left : left + 'px',
			zIndex : 1000
		});		
		$("body").append(tbodytr);
		this.topwidth(tbodytr);
		$(window).bind("scroll.fixtopthtd",$.proxy(function(){
			if($(window).scrollTop() > top + tbodytr.height()){
				tbodytr.show(0);
				tbodytr.css("top",$(window).scrollTop() + pos +"px");
			}
			else
				tbodytr.hide(0);
		},this));
		if(widfix){
			$(window).resize($.proxy(function(){
				this.topwidth(tbodytr);
			},this));
		}
	},
	topwidth : function(tbodytr){	
		$(tbodytr).find("table").width(this.target.find("tr").width() + this.chrome);
		var td = $(tbodytr).find("th,td")//.find("div");
		var len = td.length;
		var tartd = this.target.find("thead").find("td:lt(" + len + "),th:lt(" + len + ")");		
		for(var i = 0 ; i < len ; i++){
			var col = 0;
			if(td.eq(i).attr("colSpan") !== undefined)
				col = parseInt(td.eq(i).attr("colSpan"))-1;
			var ss = 1;
			if(this.chrome)
				ss = 0;	
			td.eq(i).width(tartd.eq(i).outerWidth() - 4 - col*ss + this.chrome);
		}
	}
}