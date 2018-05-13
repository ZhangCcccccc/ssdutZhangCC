
//导出选中excel
function expers(s){

	document.form.action=s;
	document.form.submit();
}

//首页
function first(){
	var tage=$("#tage").val();
	document.form.action="/xidlims/Labreservation/Labreservationmanage?tage="+tage+"&currpage=1";
    document.form.submit();
}

function previous(){
	var page=$("#currpage").val();
	var tage=$("#tage").val();
	if(page==1){
		page=1;
	}else{
		page=page-1;
	}
    document.form.action="/xidlims/Labreservation/Labreservationmanage?tage="+tage+"&currpage="+page;
    document.form.submit();
}

function next(){
	var tage=$("#tage").val();
	var totalpage=$("#totalpage").val();
	var page=parseInt($("#currpage").val());
	if(page==totalpage){
		page=totalpage;
	}else{
		page=page+1;
	}
    document.form.action="/xidlims/Labreservation/Labreservationmanage?tage="+tage+"&currpage="+page;
    document.form.submit();
}

function last(){
	var page=$("#totalpage").val();
	var tage=$("#tage").val();
    document.form.action="/xidlims/Labreservation/Labreservationmanage?tage="+tage+"&currpage="+page;
    document.form.submit();
}


