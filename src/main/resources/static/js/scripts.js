$(".answer-writer input[type=submit]").claick(addAnswer);

function AddAnswer(e){
	console.log("click me");
	e.preventDefault();
	
	var queryString = $(".answer-writer").serialize();
	console.log(queryString);
	
	var url =$(".answer-wirter").attr("action");
	console.log(url);
	
	$.ajax({
		type : 'post',
		url : url,
		data : queryString,
		dataType : 'json',
		error : onError,
		success : onSuccess,
	});
}

function onSuccess(data, stauts) {
	
}