// JavaScript Document
window.onresize = function (){
		resize();
		}
	resize();
	function resize(){
		var width=document.documentElement.clientWidth
		document.getElementById("conteiner").style.width=width+'px';
		//document.getElementById("conteiner").style.height=960*width/640+'px';
		document.getElementsByTagName("html").item(0).style.fontSize=120*width/640+'%';
	}