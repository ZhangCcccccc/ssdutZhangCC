
//查询
function search(){
	document.form.action="equipmentListDetail?currpage=1&flag=0";
	document.form.submit();
}
//导出所有excel
function exportAll(s){
	var num=$("#currpage").val();
	document.form.action=s;
	document.form.submit();
}
//导出选中excel
function exportSelect(){
	var num=$("#excelTables").find("option:selected").text();
	document.form.action="exportExcelSelectAllActivity?num="+num;
	document.form.submit();
}

//首页
function first(){
	document.form.action="experimentalMyAudit?currpage=1";
    document.form.submit();
}

function previous(){
	var page=$("#currpage").val();
	if(page==1){
		page=1;
	}else{
		page=page-1;
	}
    document.form.action="experimentalMyAudit?currpage="+page;
    document.form.submit();
}

function next(){
	var totalpage=$("#totalpage").val();
	var page=parseInt($("#currpage").val());
	if(page==totalpage){
		page=totalpage;
	}else{
		page=page+1;
	}
    document.form.action="experimentalMyAudit?currpage="+page;
    document.form.submit();
}

function last(){
	var page=$("#totalpage").val();
	var tage=parseInt($("#tage").val());
    document.form.action="experimentalMyAudit?currpage="+page;
    document.form.submit();
}


