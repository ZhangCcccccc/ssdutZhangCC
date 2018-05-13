// JavaScript Document
// 为无框架页面添加系统框架

function iStyle_Feelings_AppendShell (script) {
	this.script = script;
	this.initialize();
	}
iStyle_Feelings_AppendShell.prototype = {
	initialize : function () {
		if (window.parent.length < 1)
			this.seturl();
		},
	seturl : function () {
		var urls = location.href.split("/");
		var hash = urls[urls.length-1];
		var url = location.href.replace(hash,"");
		location.href = url + this.script.host + "#" + hash;
		}
	}