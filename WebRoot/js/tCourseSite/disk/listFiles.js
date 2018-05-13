
var root = "";
var paths = new Array();
var sessionId = $("#sessionId").val();

//下载文件
function download_(fid){
	if(fid != 0){//下载一个文件
		//去掉上次下载生成的form
		$("#hform").remove();
		//定义一个form表单
		var form = $("<form id='hform'>");  
        form.attr('style', 'display:none');   
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', $("#contextPath").val()+"/tcoursesite/disk/download?tCourseSiteId="+$("#tCourseSiteId").val());
        //定义要下载的资源的主键
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fid');
        input1.attr('value', fid);
        $('body').append(form); 
        form.append(input1); 
        //提交表单
        form.submit();
        $("#hform").remove();
	}else{//下载多个文件
	}
}


//删除文件
function removeFiles(fid){
	//定义要删除的多个文件和文件夹的主键集
	var fids = "";
	if(fid!=0){//删除单个文件
		fids += fid + ",";
	}else{//删除多个文件
		$("tbody").find("input[type='checkbox']").each(function () {//遍历页面所有checkbox
		    if(this.checked){//如果多选框被选中
			  fids += this.value + ",";
		    }
	    });
	}
	if(fids == ""){//未选择文件或文件夹提示
		alert("请选择要删除的文件！");
		return false;
	}
	
	if(confirm("是否确认删除？")){//删除确认
		//定义方法的参数
		var params = {};
		params['fids'] = encodeURI(fids);//要删除的多个文件和文件夹的主键集
		params['tCourseSiteId'] = $("#tCourseSiteId").val();//当前所在的课程站点
		//ajax删除文件
		$.ajax({
			url:$("#contextPath").val()+'/tcoursesite/disk/removeFile',			
			data : params,
			dataType:'text',
			success:function(data){//返回已删除的文件夹的id集
				var removeFileClass = data.split(",");//string转数组
				//遍历已删除的文件或文件夹
				for(var i=0;i<removeFileClass.length;i++){
					if(removeFileClass[i]!=""){
						//删除对应文件夹或文件在页面上的tr
						$("."+removeFileClass[i]).remove();
						$(".file"+removeFileClass[i]).remove();
					}
				}
			}
		})
	}
} 



//文件上传
function uploadFile(path,fileParentFolderId){//父文件夹的id
	//当前课程的id
	var tCourseSiteId = $("#tCourseSiteId").val();
	//已打开过的文件夹的id集
	var openFolderIds = $("#openFolderIds").val();
	//路径转化为数组
	var paths = path.split("/");
	//计算left样式值
	var paddingLeft = "";
	if(path!=''){
		paddingLeft = "style='padding-left: "+20*(paths.length-1)+"px;'";
	}
	//设置生成的tr显示还是隐藏
	var fileStyle = "";
	if(fileParentFolderId!=-1){//如果有父文件夹，即不在根目录
		if(!$("#"+fileParentFolderId).find('i').hasClass('fa-folder-open-o')){//如果父文件夹图标为隐藏
			fileStyle = "style='display: none;'";//生成的tr设置为隐藏
		}
	}else{
		paddingLeft = "";//反之设置为显示
	}
	$("#myModal").fadeIn(100);//显示上传弹出框
	//文件上传
	$("#file_upload").uploadify({
		auto:true,
		method:'post',
		upImgUrl:true,
		uploadLimit: 8,
		buttonText: '选择文件',
		requeueErrors: false,
		fileObjName:"attach",
		removeTimeout:1,
		buttonClass: "btn btnUp btn-info",
		swf:$("#contextPath").val()+'/swfupload/swfupload.swf',
		uploader:$("#contextPath").val()+'/tcoursesite/disk/file/upload;jsessionid='+$("#sessionId").val(),
		onUploadSuccess:function(file,data,response){//返回已上传文件的json
			var data = $.parseJSON(data);
			//设置上传成功后页面动态生成的文件tr
			var ht = getTrForFile(data,fileStyle,paddingLeft);
			//$("#upedFiles").append(ht)
			//判断此文件夹是否打开过
	       	if(openFolderIds.indexOf(fileParentFolderId+",") > -1 ){
	       		//在兄弟文件tr的最后添加生成的文件tr
	       		$("."+data.parentClass+":last").after(ht);
	       		//鼠标经过tr变色事件重写
				$("table tr").hover(
		            function () {
		                $(this).addClass("hg8")
		                $(this).find(".fa-eye").css("color","#000");
		            },
		            function () {
		                $(this).removeClass("hg8")
		                $(this).find(".fa-eye").css("color","#999");
		            }
		        );
	       	}
	       	var parentSize = $("#size"+fileParentFolderId).text();
	       	var size = parentSize.substring(0,parentSize.length-1);
	       	size = parseInt(size);
	       	size = size + 1;
	       	parentSize = size + "项";
	       	$("#size"+fileParentFolderId).html(parentSize);
	       	
		}, 
		'onUploadStart' : function(file) {//上传开始时传参
            var currentPath = "" ;//设置当前路径
            if((path != "-1" )){//如果不是根目录
            	currentPath = path;
			}
            $("#file_upload").uploadify("settings", "formData", {'tCourseSiteId':$("#tCourseSiteId").val(),'currentPath':currentPath});
         },
         onQueueComplete : function(event, data) {//当队列中的所有文件全部完成上传时触发　 
        	 $("#myModal").fadeOut(100)//自动关闭上传框
 		}
	});
}
//压缩包上传
function uploadZip(path,folderParentFolderId){//父文件夹的id
	//当前课程的id
	var tCourseSiteId = $("#tCourseSiteId").val();
	//已打开过的文件夹的id集
	var openFolderIds = $("#openFolderIds").val();
	//路径转化为数组
	var paths = path.split("/");
	//计算left样式值
	var paddingLeft = "";
	if(path!=''){
		paddingLeft = "style='padding-left: "+20*(paths.length-1)+"px;'";
	}
	//父文件夹的第一个文件tr的class
	var firstFileClass = "file";
	//设置生成的tr显示还是隐藏
	var folderStyle = "";
	if(folderParentFolderId!=-1){//如果有父文件夹，即不在根目录
		if(!$("#"+folderParentFolderId).find('i').hasClass('fa-folder-open-o')){//如果父文件夹图标为隐藏
			folderStyle = "style='display: none;'";//生成的tr设置为隐藏
		}
		firstFileClass += folderParentFolderId;//所在父文件夹的第一个文件tr
	}
	$("#myModal").fadeIn(100);//显示上传弹出框
	//文件上传
	$("#file_upload").uploadify({
		auto:true,
		method:'post',
		upImgUrl:true,
		uploadLimit: 8,
		buttonText: '选择文件',
		requeueErrors: false,
		fileObjName:"attach",
		removeTimeout:1,
		buttonClass: "btn btnUp btn-info",
		swf:$("#contextPath").val()+'/swfupload/swfupload.swf',
		uploader:$("#contextPath").val()+'/tcoursesite/disk/uploadZip;jsessionid='+$("#sessionId").val(),
		onUploadSuccess:function(file,data,response){//返回已上传文件的json
			var data = $.parseJSON(data);
			//alert(data.result);
			var result = data.result;
			if(result == "false"){
				alert("要创建的文件夹已存在");
			}else if(result == "notZip"){
				alert("请选择zip格式压缩包！");
			}else{
				//拼接文件夹tr
				var ht = getTrForFolder(data,folderStyle,paddingLeft);
				//$("#upedFiles").append(ht)
				//判断此文件夹是否打开过
		       	if(openFolderIds.indexOf(folderParentFolderId+",") > -1 ){
		       		//在所在父文件夹的第一个文件tr之前添加新文件夹的tr
		       		$("#"+firstFileClass).before(ht);
		       		//鼠标经过tr变色事件重写
					$("table tr").hover(
			            function () {
			                $(this).addClass("hg8")
			                $(this).find(".fa-eye").css("color","#000");
			            },
			            function () {
			                $(this).removeClass("hg8")
			                $(this).find(".fa-eye").css("color","#999");
			            }
			        );
		       	}
		    	var parentSize = $("#size"+folderParentFolderId).text();
		       	var size = parentSize.substring(0,parentSize.length-1);
		       	size = parseInt(size);
		       	size = size + 1;
		       	parentSize = size + "项";
		       	$("#size"+folderParentFolderId).html(parentSize);
	       	}
		}, 
		'onUploadStart' : function(file) {//上传开始时传参
            var currentPath = "" ;//设置当前路径
            if((path != "-1" )){//如果不是根目录
            	currentPath = path;
			}
            $("#file_upload").uploadify("settings", "formData", {'tCourseSiteId':$("#tCourseSiteId").val(),'currentPath':currentPath});
         },
         onQueueComplete : function(event, data) {//当队列中的所有文件全部完成上传时触发　 
        	 $("#myModal").fadeOut(100)//自动关闭上传框
 		}
	});
}
//添加文件夹
function addFolder(path,name,folderParentFolderId){
	$("#originalFolderName").val(name);//添加的文件夹名
	$("#folderPath").val(path);//添加的文件夹路径
	$("#folderParentFolderId").val(folderParentFolderId);//父文件夹id
	if(name=='-1'){//新增
		$("#folderName").val('');
	}else{//编辑（原则上不能编辑）
		$("#folderName").val(name);
	}
	$("#addFolder").fadeIn(100);//新增文件夹框弹出
}
//保存文件夹
function saveFolder(){
	//上传路径
	var path = $("#folderPath").val();
	//文件夹名字
	var name = $("#folderName").val();
	if (name.indexOf(" ") >=0){//禁止文件夹名字中含有空格
		alert("文件夹名字不能含有空格！");
		return false;
		name = name.replace(/\s/g, ""); // 这句话可以强制删除所有空格（暂未用到）
	}
	  
	//父文件夹的id
	var folderParentFolderId = $("#folderParentFolderId").val();
	//父文件夹的第一个文件tr的class
	var firstFileClass = "file";
	//当前课程的id
	var tCourseSiteId = $("#tCourseSiteId").val();
	//已打开过的文件夹的id集
	var openFolderIds = $("#openFolderIds").val();
	//判断文件夹在第几层
	var paths = path.split("/");    
	var paddingLeft = "";
	if(path!=''){//设置文件夹tr靠左侧的距离，根据所在层
		paddingLeft = "style='padding-left: "+20*(paths.length-1)+"px;'";
	}
	var folderStyle = "";
	if(folderParentFolderId!=-1){//如果有父文件夹，即不在根目录
		if(!$("#"+folderParentFolderId).find('i').hasClass('fa-folder-open-o')){//如果父文件夹图标为隐藏
			folderStyle = "style='display: none;'";//生成的tr设置为隐藏
		}
		firstFileClass += folderParentFolderId;//所在父文件夹的第一个文件tr
	}
	//保存文件夹
	$.ajax({
		type: "POST",
		url: $("#contextPath").val()+"/tcoursesite/disk/creatFolder",
		data: {'tCourseSiteId':tCourseSiteId,'path':path,'name':name},
		dataType:'json',
		success:function(data){//返回文件夹信息
			if(!data){
				alert("要创建的文件夹已存在");
			}else{
				//拼接文件夹tr
				var ht = getTrForFolder(data,folderStyle,paddingLeft);
				//判断此文件夹是否打开过
		       	if(openFolderIds.indexOf(folderParentFolderId+",") > -1 ){
		       		//在所在父文件夹的第一个文件tr之前添加新文件夹的tr
		       		$("#"+firstFileClass).before(ht);
		       		//鼠标经过tr变色事件重写
					$("table tr").hover(
			            function () {
			                $(this).addClass("hg8")
			                $(this).find(".fa-eye").css("color","#000");
			            },
			            function () {
			                $(this).removeClass("hg8")
			                $(this).find(".fa-eye").css("color","#999");
			            }
			        );
		       	}
		       	$("#addFolder").fadeOut(100);//关闭添加文件夹弹出框
			}
			var parentSize = $("#size"+folderParentFolderId).text();
	       	var size = parentSize.substring(0,parentSize.length-1);
	       	size = parseInt(size);
	       	size = size + 1;
	       	parentSize = size + "项";
	       	$("#size"+folderParentFolderId).html(parentSize);
		},
		error:function(){
			alert("信息错误！");
			}
	});
}
//第一次打开文件夹时，显示下一层的文件夹和文件
function findFilesJson(path,folderId){
	$.ajax({
		type: "POST",
		url: $("#contextPath").val()+"/tcoursesite/disk/findFilesJson",
		data: {'tCourseSiteId':$("#tCourseSiteId").val(),'path':path},
		dataType:'json',
		success:function(data){//返回文件夹下所有文件夹及文件的tr
			$.each(data,function(key,values){//生成的页面元素添加到此文件夹下面
				$("."+folderId+":last").after(values);
			 }); 
			//鼠标经过tr变色事件重写
			$("table tr").hover(
	            function () {
	                $(this).addClass("hg8")
	                $(this).find(".fa-eye").css("color","#000");
	            },
	            function () {
	                $(this).removeClass("hg8")
	                $(this).find(".fa-eye").css("color","#999");
	            }
	        );
			
		},
		error:function(){
			alert("信息错误！");
			}
	});
}
//打开或关闭文件夹
function lookFolders(obj){
	var folderClasses = $(obj).parent().attr("class").split(" ");//当前文件夹tr的class
	var openFolderIds = $("#openFolderIds").val();//曾经打开过的文件夹
    var folderId = $(obj).parent().attr("id");//当前文件夹id
   	var path = $(obj).parent().find("input:first").val();//当前文件夹的相对路径
   	//判断此文件夹是否打开过
   	if(openFolderIds.indexOf(folderId+",") > -1 ){//如果当前文件夹打开过
   		$(obj).parent().siblings('.'+folderId).each(function(){//遍历此文件夹下所有的文件夹tr
   			var classes = $(this).attr("class").split(" ");//遍历的tr的class
    		if(classes.length==folderClasses.length){
    			$(this).toggle();//当前文件夹的子文件夹打开或隐藏
    		} 
        })
        $(obj).parent().siblings('.file'+folderId).each(function(){//遍历此文件夹下所有的文件tr
   			var classes = $(this).attr("class").split(" ");//遍历的tr的class
    		if(classes.length==folderClasses.length){
    			$(this).toggle();//当前文件夹的子文件夹打开或隐藏
    		} 
        })
   	}else{//如果当前文件夹未打开过
   		findFilesJson(path,folderId);//ajxa生成子文件夹和子文件元素
   		//把此文件标记为打开过
   		openFolderIds = openFolderIds +folderId+",";
   		$("#openFolderIds").val(openFolderIds);
   	}
   	
    if($(obj).parent().siblings('.'+folderId).css("display")=="none"){//如果当前文件夹关闭
        $(obj).parent().siblings('.'+folderId).each(function(){//遍历所有子文件夹
        	//var sss = $(this).attr("class");
        	var sonClass = $(this).attr("id");//获取子文件夹的id
        	$(this).siblings('.'+sonClass).css("display","none");//隐藏子文件夹的下一层子文件夹
        	$(this).siblings('.file'+sonClass).css("display","none");//隐藏子文件夹的下一层子文件
        })
    } 
    //如果关闭文件夹时关闭所有子文件夹
    if($(obj).parent().siblings('.'+folderId).find('i').hasClass('fa-folder-open-o')){
        $(obj).parent().siblings('.'+folderId).find('i').removeClass('fa-folder-open-o')     
    }
    $(obj).find('i').toggleClass('fa-folder-open-o')//切换打开或关闭文件夹图标
}
		
//checkbox全选效果
$(function checkallChange(){
	
	$(".checkall").click(
	    function (event) {
	        if (this.checked) {
	            $(this).parent().parent().parent().parent().find("input[name='checkname']").each(function () {
	                this.checked = true;
	            });
	        } else {
	            $(this).parent().parent().parent().parent().find("input[name='checkname']").each(function () {
	                this.checked = false;
	            });
	        }
	    }
	);
	
	$(".check_box").click(
		    function (event) {
		        if (this.checked) {
		        } else {
		        	$(this).parent().parent().parent().parent().find("input[name='All']").each(function () {
		                this.checked = false;
		            });
		        }
		    }
		);
});
//加载页面时页面所有checkbox全部设置为未选择
$(function (){
	$("body").find("input:checkbox").each(function () {
        this.checked = false;
    });
});

//拼接文件夹tr
function getTrForFolder(data,folderStyle,paddingLeft){
	//拼接文件夹tr
	var ht =  "<tr id='" + data.id + "' class='"+data.folderClass+""+data.id+"' "+folderStyle+">";
	ht += "<input type='hidden'  id='path" + data.id + "' value='" + data.folderPath + data.name + "/'/>";
	ht += "<td class='parent' id='row_8279' "+paddingLeft+" onclick='lookFolders(this)'>";
	ht += "<input class='l check_box' type='checkbox' id='checkbox" + data.id + "' name='checkname' value='" + data.id + "' />";
	ht += "<label class='l mt10' for='checkbox" + data.id + "'></label>";
	ht += "<div class='resource'><span><i class='fa fa-folder-o bc mlr5 f18'></i>";
	ht += "<span> "+data.name+"</span>";
	ht += "</span></div></td>";
	ht += "<td>整个站点</td>";
	ht += "<td>admin</td>";//创建者
	ht += "<td>"+data.upTime+"</td>";//时间
	ht += "<td id='size" + data.id + "'>0项</td>";//大小
	ht += '<td >';
	//上传压缩包按钮
	ht += '<i class="fa fa-list mr5 poi"  title="上传压缩文件" onclick="uploadZip(';
	ht += "'"+data.folderPath+data.name+"/',"+data.id;
	ht += ')"></i>&nbsp';
	//上传文件按钮
	ht += '<i class="fa fa-upload mr5 poi" title="上传文件" onclick="uploadFile(';
	ht += "'"+data.folderPath+data.name+"/',"+data.id;
	ht += ')"></i>&nbsp';
	//添加文件夹按钮
	ht += '<i class="fa fa-folder-o mr5 poi" title="添加文件夹" onclick="addFolder(';
	ht += "'"+data.folderPath+data.name+"/'";
	ht += ',';
	ht += "'',"+data.id;
	ht += ')">';
	ht += "</i>&nbsp";
	ht += "<i class=\"fa fa-trash-o mr5 poi\" title=\"删除\"  onclick='removeFiles(" + data.id + ");return false;'></i>";
	ht += "</td>";
	ht += "</tr>";
	//放置一个隐藏的tr，用来给此文件夹下的文件定位
	ht += "<tr id='file"+data.id+"' class='file"+data.id+"' type='hidden'></tr>";
	return ht;
}

//拼接文件tr
function getTrForFile(data,fileStyle,paddingLeft){
	var ht = "";//设置上传成功后页面动态生成的文件tr
	ht += "<tr id='file" + data.id + "' class='" + data.fileClass + "' " + fileStyle + ">";
	ht += "<td class='parent' "+paddingLeft+" >";
	ht += "<input class='l check_box' type='checkbox' id='checkbox" + data.id + "' name='checkname' value='" + data.id + "' />";
	ht += "<label class='l mt10' for='checkbox" + data.id + "'></label>";
	ht += "<div class=''><span><i class='fa fa-file-o bc mlr5 f18'></i>";
	ht += "<span> "+data.name+"</span>";
	ht += "</span></div></td>";
	ht += "<td>整个站点</td>";
	ht += "<td>admin</td>";
	ht += "<td>"+data.upTime+"</td>";
	ht += "<td>"+data.size+"</td>";
	ht += "<td><i class=\"fa fa-download mr5 poi\" title=\"下载\"  onclick='download_(" + data.id + ");return false;'>";
	ht += "</i>&nbsp";
	ht += "<i class=\"fa fa-trash-o mr5 poi\" title=\"删除\"  onclick='removeFiles(" + data.id + ");return false;'></i>";
	ht += "</td>";
	ht += "</tr>";
	return ht;
}
//搜索功能
function searchAll(){
	var uploadName = document.getElementById("uploadName").value;
	document.getElementById("searchPath").innerHTML="";
	$.ajax({
		data: {'uploadName':uploadName,'tCourseSiteId':$("#tCourseSiteId").val()},
        url:$("#contextPath").val()+"/tcoursesite/disk/searchFiles",
        type:"POST",
        success:function(data){//AJAX查询成功
        	document.getElementById("tbody").innerHTML=data;
        }
	});
}
//搜索时打开或关闭文件夹
function openFolders(id){
	$.ajax({
		data: {'id':id,'tCourseSiteId':$("#tCourseSiteId").val()},
        url:$("#contextPath").val()+"/tcoursesite/disk/openFolder",
        type:"POST",
        success:function(data){//AJAX查询成功
        	$.each(data,function(key,values){//生成的页面元素添加到此文件夹下面
        		document.getElementById(key).innerHTML=values;
			 }); 
        }
	});
}
