	
	//首页
	function firstPage(type, page){
		if(type == 1){//图片类型
			$.ajax({
		    	  url:$("#contextPath").val()+"/tcoursesite/findUploadsByUser?uploadType=" + type + "&currpage="+page,
			      type:"POST",
			    	  success:function(data){
			    		  $("#picResourceList").html(data);
			    	  }
		      });
		}
		
		if(type == 2){//文件类型
			$.ajax({
		    	  url:$("#contextPath").val()+"/tcoursesite/findUploadsByUser?uploadType=" + type + "&currpage="+page,
			      type:"POST",
			    	  success:function(data){
			    		  $("#fileResourceList").html(data);
			    		  $("#attachementResourceList").html(data);
			    	  }
		      });
		}
		
	}
	//上一页
	function previousPage(type, page){
		if(page == 1){
			page = 1;
		}else{
			page = page-1;
		}
		if(type == 1){//图片类型
			$.ajax({
		    	  url:$("#contextPath").val()+"/tcoursesite/findUploadsByUser?uploadType=" + type + "&currpage="+page,
			      type:"POST",
			    	  success:function(data){
			    		  $("#picResourceList").html(data);
			    	  }
		      });
		}
		if(type == 2){//文件类型
			$.ajax({
		    	  url:$("#contextPath").val()+"/tcoursesite/findUploadsByUser?uploadType=" + type + "&currpage="+page,
			      type:"POST",
			    	  success:function(data){
			    		  $("#fileResourceList").html(data);
			    		  $("#attachementResourceList").html(data);
			    	  }
		      });
		}
		
	}
	
	//下一页
	function nextPage(type, page, totalPage){
			if(page >= totalPage){
				page = totalPage
			}else{
				page = page + 1;
			}
		if(type == 1){//图片类型
			$.ajax({
		    	  url:$("#contextPath").val()+"/tcoursesite/findUploadsByUser?uploadType=" + type + "&currpage="+page,
			      type:"POST",
			    	  success:function(data){
			    		  $("#picResourceList").html(data);
			    	  }
		      });
		}
		if(type == 2){
			$.ajax({
		    	  url:$("#contextPath").val()+"/tcoursesite/findUploadsByUser?uploadType=" + type + "&currpage="+page,
			      type:"POST",
			    	  success:function(data){
			    		  $("#fileResourceList").html(data);
			    		  $("#attachementResourceList").html(data);
			    	  }
		      });
		}
		
	}
	
	//末页
	function lastPage(type, page){
		if(type == 1){//图片类型
			$.ajax({
		    	  url:$("#contextPath").val()+"/tcoursesite/findUploadsByUser?uploadType=" + type + "&currpage="+page,
			      type:"POST",
			    	  success:function(data){
			    		  $("#picResourceList").html(data);
			    	  }
		      });
		}
		if(type == 2){//文件类型
			$.ajax({
		    	  url:$("#contextPath").val()+"/tcoursesite/findUploadsByUser?uploadType=" + type + "&currpage="+page,
			      type:"POST",
			    	  success:function(data){
			    		  $("#fileResourceList").html(data);
			    		  $("#attachementResourceList").html(data);
			    	  }
		      });
		}
		
	}
	

