function paging(path, i, search, n) {
	$('#area').load(path, {
		currentPage : i,
		AllSearch : search,
		n : n,
		ap : 'AjaxPaging'
	});
}

function sch() {
	var form = document.getElementById("searchform");
	var path = $("#path").val();
	var i = form.i.value;
	var AllSearch = form.AllSearch.value;
	
	$('#area').load(path, {
		currentPage : i,
		AllSearch : AllSearch,
		ap : 'AjaxSearch'
	});
}

function category(n){
	var form = document.getElementById("searchform");
	var path = $("#path").val();
	var i = form.i.value;
	if(n=="all"){
		var AllSearch = "";
		n = 0;
	}else{
		var AllSearch = form.AllSearch.value;
	}
	
	$("#area").load(path, {
		currentPage : i,
		AllSearch : AllSearch,
		n : n,
		ap : "AjaxCategory"
	});
}