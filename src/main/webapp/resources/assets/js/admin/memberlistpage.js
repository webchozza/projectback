function paging(path, i, search, n) {
	
	stoploop();
	
	$('#area').load(path, {
		currentPage : i,
		search : search,
		n : n,
		ap : 'AjaxPaging'
	});
}

var loop = setTimeout(function(){
	var form = document.getElementById('valueform');
	var path = $("#path").val();
	var i = form.i.value;
	var search = $("#searchvalue").val();
	var n = $("#demo-category").val();
	var ch = form.ch.value;
	
	if(ch != "0" && ch != "1"){ ch = ""; }
	
	console.log(ch);
	$("#area").load(path,{
		currentPage: i, 
		search: search, 
		ch: ch,
		n: n, 
		ap: 'AjaxMemberCheck'
		});
	setTimeout(loop, 300);
}, 300);

function stoploop(){
	console.log("루프 멈춘다아아");
	clearTimeout(loop);
}