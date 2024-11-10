$("#reply-delete").on("click", (e)=>{
	e.preventDafault();

	const replyId = e.target.dataset.id
	
$.ajax({
	type : "DELETE",
	url : "/reply/" + replyId
}).done((response)=>{
	alert(response.data)
	location.reload(); /*새로고침*/
}).fail((error)=>{
	console.log(error);
})

})
