/*
This software is allowed to use under GPL or you need to obtain Commercial or Enterise License
to use it in non-GPL project. Please contact sales@dhtmlx.com for details
*/
scheduler.toPDF=function(A,k,t,u){function g(d){return d.replace(newline_regexp,"\n").replace(html_regexp,"")}function m(d){d=parseFloat(d);return isNaN(d)?"auto":100*d/(q+1)}function x(d){d=parseFloat(d);return isNaN(d)?"auto":100*d/n}function v(d){var a="";if(scheduler.matrix&&scheduler.matrix[scheduler._mode]){if(scheduler.matrix[scheduler._mode].second_scale)var b=d[1].childNodes;d=d[0].childNodes}for(var c=0;c<d.length;c++)a+="\n<column><![CDATA["+g(d[c].innerHTML)+"]]\></column>";q=d[0].offsetWidth;
if(b)for(var f=0,e=d[0].offsetWidth,h=1,c=0;c<b.length;c++)a+="\n<column second_scale='"+h+"'><![CDATA["+g(b[c].innerHTML)+"]]\></column>",f+=b[c].offsetWidth,f>=e&&(e+=d[h]?d[h].offsetWidth:0,h++),q=b[0].offsetWidth;return a}function B(d,a){for(var b=parseInt(d.style.left,10),c=0;c<scheduler._cols.length;c++)if(b-=scheduler._cols[c],b<0)return c;return a}function C(d,a){for(var b=parseInt(d.style.top,10),c=0;c<scheduler._colsS.heights.length;c++)if(scheduler._colsS.heights[c]>b)return c;return a}
function z(d){for(var a="",b=d.firstChild.rows,c=0;c<b.length;c++){for(var f=[],e=0;e<b[c].cells.length;e++)f.push(b[c].cells[e].firstChild.innerHTML);a+="\n<row height='"+d.firstChild.rows[c].cells[0].offsetHeight+"'><![CDATA["+g(f.join("|"))+"]]\></row>";n=d.firstChild.rows[0].cells[0].offsetHeight}return a}function D(d){var a="<data profile='"+d+"'";t&&(a+=" header='"+t+"'");u&&(a+=" footer='"+u+"'");a+=">";var b=scheduler._mode;scheduler.matrix&&scheduler.matrix[scheduler._mode]&&(b=scheduler.matrix[scheduler._mode].render==
"cell"?"matrix":"timeline");a+="<scale mode='"+b+"' today='"+scheduler._els.dhx_cal_date[0].innerHTML+"'>";if(scheduler._mode=="week_agenda")for(var c=scheduler._els.dhx_cal_data[0].getElementsByTagName("DIV"),f=0;f<c.length;f++)c[f].className=="dhx_wa_scale_bar"&&(a+="<column>"+g(c[f].innerHTML)+"</column>");else if(scheduler._mode=="agenda"||scheduler._mode=="map")c=scheduler._els.dhx_cal_header[0].childNodes[0].childNodes,a+="<column>"+g(c[0].innerHTML)+"</column><column>"+g(c[1].innerHTML)+"</column>";
else if(scheduler._mode=="year"){c=scheduler._els.dhx_cal_data[0].childNodes;for(f=0;f<c.length;f++)a+="<month label='"+g(c[f].childNodes[0].innerHTML)+"'>",a+=v(c[f].childNodes[1].childNodes),a+=z(c[f].childNodes[2]),a+="</month>"}else{a+="<x>";c=scheduler._els.dhx_cal_header[0].childNodes;a+=v(c);a+="</x>";var e=scheduler._els.dhx_cal_data[0];if(scheduler.matrix&&scheduler.matrix[scheduler._mode]){a+="<y>";for(f=0;f<e.firstChild.rows.length;f++){var h=e.firstChild.rows[f];a+="<row><![CDATA["+g(h.cells[0].innerHTML)+
"]]\></row>"}a+="</y>";n=e.firstChild.rows[0].cells[0].offsetHeight}else if(e.firstChild.tagName=="TABLE")a+=z(e);else{for(e=e.childNodes[e.childNodes.length-1];e.className.indexOf("dhx_scale_holder")==-1;)e=e.previousSibling;e=e.childNodes;a+="<y>";for(f=0;f<e.length;f++)a+="\n<row><![CDATA["+g(e[f].innerHTML)+"]]\></row>";a+="</y>";n=e[0].offsetHeight}}a+="</scale>";return a}function l(d,a){return(window.getComputedStyle?window.getComputedStyle(d,null)[a]:d.currentStyle?d.currentStyle[a]:null)||
""}function E(){var d="",a=scheduler._rendered;if(scheduler._mode=="agenda"||scheduler._mode=="map")for(var b=0;b<a.length;b++)d+="<event><head>"+g(a[b].childNodes[0].innerHTML)+"</head><body>"+g(a[b].childNodes[2].innerHTML)+"</body></event>";else if(scheduler._mode=="week_agenda")for(b=0;b<a.length;b++)d+="<event day='"+a[b].parentNode.getAttribute("day")+"'><body>"+g(a[b].innerHTML)+"</body></event>";else if(scheduler._mode=="year"){a=scheduler.get_visible_events();for(b=0;b<a.length;b++){var c=
a[b].start_date;if(c.valueOf()<scheduler._min_date.valueOf())c=scheduler._min_date;for(;c<a[b].end_date;){var f=c.getMonth()+12*(c.getFullYear()-scheduler._min_date.getFullYear())-scheduler.week_starts._month,e=scheduler.week_starts[f]+c.getDate()-1,h=j?l(scheduler._get_year_cell(c),"color"):"",o=j?l(scheduler._get_year_cell(c),"backgroundColor"):"";d+="<event day='"+e%7+"' week='"+Math.floor(e/7)+"' month='"+f+"' backgroundColor='"+o+"' color='"+h+"'></event>";c=scheduler.date.add(c,1,"day");if(c.valueOf()>=
scheduler._max_date.valueOf())break}}}else{var k=scheduler.matrix&&scheduler.matrix[scheduler._mode];if(k&&k.render=="cell"){a=scheduler._els.dhx_cal_data[0].getElementsByTagName("TD");for(b=0;b<a.length;b++)h=j?l(a[b],"color"):"",o=j?l(a[b],"backgroundColor"):"",d+="\n<event><body backgroundColor='"+o+"' color='"+h+"'><![CDATA["+g(a[b].innerHTML)+"]]\></body></event>"}else for(b=0;b<a.length;b++){var i=m(a[b].style.left),r=m(a[b].style.width);if(!isNaN(r*1)){var s=x(a[b].style.top),q=x(a[b].style.height),
p=a[b].className.split(" ")[0].replace("dhx_cal_","");if(p!=="dhx_tooltip_line"){var y=scheduler.getEvent(a[b].getAttribute("event_id")),e=y._sday,w=y._sweek,t=y._length||0;if(scheduler._mode!="month"){if(scheduler.matrix&&scheduler.matrix[scheduler._mode]){var e=0,u=a[b].parentNode.parentNode.parentNode,w=u.rowIndex;r+=m(10)}else{scheduler.xy.menu_width&&(r+=m(r*20/100),i-=m(15-i*20/100));if(a[b].parentNode==scheduler._els.dhx_cal_data[0])continue;i+=m(a[b].parentNode.style.left);i-=m(51)}if(scheduler.matrix&&
scheduler.matrix[scheduler._mode]){var v=n;n=a[b].parentNode.offsetHeight;s=x(a[b].style.top);s-=s*0.2;n=v}}else q=parseInt(a[b].offsetHeight,10),s=parseInt(a[b].style.top,10)-22,e=B(a[b],e),w=C(a[b],w);d+="\n<event week='"+w+"' day='"+e+"' type='"+p+"' x='"+i+"' y='"+s+"' width='"+r+"' height='"+q+"' len='"+t+"'>";p=="event"?(d+="<header><![CDATA["+g(a[b].childNodes[1].innerHTML)+"]]\></header>",h=j?l(a[b].childNodes[2],"color"):"",o=j?l(a[b].childNodes[2],"backgroundColor"):"",d+="<body backgroundColor='"+
o+"' color='"+h+"'><![CDATA["+g(a[b].childNodes[2].innerHTML)+"]]\></body>"):(h=j?l(a[b],"color"):"",o=j?l(a[b],"backgroundColor"):"",d+="<body backgroundColor='"+o+"' color='"+h+"'><![CDATA["+g(a[b].innerHTML)+"]]\></body>");d+="</event>"}}}}return d}function F(){var d="</data>";return d}var q=0,n=0,j=!1;k=="fullcolor"&&(j=!0,k="color");k=k||"color";html_regexp=RegExp("<[^>]*>","g");newline_regexp=RegExp("<br[^>]*>","g");var p=(new Date).valueOf(),i=document.createElement("div");i.style.display=
"none";document.body.appendChild(i);i.innerHTML='<form id="'+p+'" method="post" target="_blank" action="'+A+'" accept-charset="utf-8" enctype="application/x-www-form-urlencoded"><input type="hidden" name="mycoolxmlbody"/> </form>';document.getElementById(p).firstChild.value=encodeURIComponent(D(k).replace("\u2013","-")+E()+F());document.getElementById(p).submit();i.parentNode.removeChild(i);grid=null};