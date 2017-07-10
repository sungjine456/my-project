$(".answer-writer input[type=submit]").click(addAnswer);

function addAnswer(e){
	e.preventDefault();
	console.log("click");
	
	var queryString = $(".answer-writer").serialize();
	console.log(queryString);
	
	var url =$(".answer-writer").attr("action");
	console.log(url);
	
	$.ajax({
		type : 'post',
		url : url,
		data : queryString,
		dataType : 'json',
		error : onError,
		success : onSuccess
	});
}

function onSuccess(data, stauts) {
	console.log(data);
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(data.writer.userId, data.formattedCreateDate, data.contents, data.id, data.id);
	$(".qna-comment-slipp-articles").prepend(template);
	console.log($(".answer-wirter input[name=contents]").val());
	$(".answer-writer textarea[name=contents]").val("");
}

String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};

function onError(data, status){
	console.log("error");
	console.log(data);
	console.log(status);
}