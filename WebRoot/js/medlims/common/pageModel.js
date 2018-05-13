//首页
function first(longurl){
    document.form.action=longurl;
    document.form.submit();
}

function previous(url){
	var page=$("#currpage").val();
	if(page==1){
		page=1;
	}else{
		page=page-1;
	}
    document.form.action= url + "&currpage=" + page ;
    document.form.submit();
}

function next(url){
	var totalpage=$("#totalpage").val();
	var page=parseInt($("#currpage").val());
	if(page==totalpage){
		page=totalpage;
	}else{
		page=page+1;
	}
    document.form.action= url + "&currpage="+page ;
    document.form.submit();
}

function last(url){
	var page=$("#totalpage").val();
    document.form.action= url + "&currpage="+page ;
    document.form.submit();
}


$(document).ready(function() { 
    $("table").tablesorter({ 
    headers: { 
       7: { 
           sorter: false 
       } 
    } 
}); 