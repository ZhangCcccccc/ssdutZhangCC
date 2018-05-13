
//导出选中excel
function expers(s){
	alert(s);
	document.form.action=s;
	document.form.submit();
}

//首页
function first(){
	document.form.action="/xidlims/Lab/labAnnexList?currpage=1";
    document.form.submit();
}

function previous(){
	var page=$("#currpage").val();
	if(page==1){
		page=1;
	}else{
		page=page-1;
	}
    document.form.action="/xidlims/Lab/labAnnexList?currpage="+page;
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
    document.form.action="/xidlims/Lab/labAnnexList?currpage="+page;
    document.form.submit();
}

function last(){
	var page=$("#totalpage").val();
	var tage=parseInt($("#tage").val());
    document.form.action="/xidlims/Lab/labAnnexList?currpage="+page;
    document.form.submit();
}
function zln(s){

    if(confirm( '你真的要删除吗？ ')==false){
       return   false;
    }else{
     window.location.href="${pageContext.request.contextPath}/operation/delectitem?idkey="+s;
    }

}

