
//查询
function search(s){
	document.form.action=s;
	document.form.submit();
}
//导出所有excel
function exportAll(s){
	var num=$("#currpage").val();
	document.form.action=s;
	document.form.submit();
}


//导出选中excel
function expers(s){
	document.form.action=s;
	document.form.submit();
}

//首页
function first(){
	var page=$("#tage").val();
	document.form.action="/xidlims/operation/complexLmsExperiment?currpage=1&tage="+page;
    document.form.submit();
}

function previous(){
	var tage=$("#tage").val();
	var page=$("#currpage").val();
	if(page==1){
		page=1;
	}else{
		page=page-1;
	}
    document.form.action="/xidlims/operation/complexLmsExperiment?currpage=1&tage="+tage;
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
    document.form.action="/xidlims/operation/complexLmsExperiment?currpage=1&tage="+tage;
    document.form.submit();
}

function last(){
	var page=$("#totalpage").val();
	var tage=parseInt($("#tage").val());
    document.form.action="/xidlims/operation/complexLmsExperiment?currpage=1&tage="+tage;
    document.form.submit();
}
function zln(s){

    if(confirm( '你真的要删除吗？ ')==false){
       return   false;
    }else{
     window.location.href="/operation/delectitem?idkey="+s;
    }

}

