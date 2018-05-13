function changeOrderExerciseType(tCourseSiteId,questionId,type){
	//alert($("#contextPath").val());
	window.location.href=$("#contextPath").val()+"/tcoursesite/exercise/findOrderItemListBySiteIdAndQuestionId?tCourseSiteId="+tCourseSiteId+"&currpage=1&questionId="+questionId+"&itemType="+type;
}

function changeStochasticExerciseType(tCourseSiteId,questionId,type){
	//alert($("#contextPath").val());
	window.location.href=$("#contextPath").val()+"/tcoursesite/exercise/findStochasticItemListBySiteIdAndQuestionId?tCourseSiteId="+tCourseSiteId+"&currpage=1&questionId="+questionId+"&itemType="+type;
}

function changeMistakeExerciseType(tCourseSiteId,questionId,type){
	//alert($("#contextPath").val());
	window.location.href=$("#contextPath").val()+"/tcoursesite/exercise/findMistakeItemListBySiteIdAndQuestionId?tCourseSiteId="+tCourseSiteId+"&currpage=1&questionId="+questionId+"&itemType="+type;
}

