function paging(path, i, search, n) {
	
	var member_id = $("#member_id").val();
	
	$('#movearea').load(path, {
		member_id : member_id,
		currentPage : i,
		n : n,
		search : search,
		ap : 'AjaxPaging'
	});
}

function sch() {
	var form = document.getElementById('searchform');
	var path = '"'+$("#path").val()+'"';
	var member_id = $("#member_id").val();
	var i = form.i.value;
	var search = form.search.value;

	$('#movearea').load(path, {
		member_id: member_id,
		currentPage : i,
		search : search,
		ap : 'AjaxSearch'
	});
}

function follow(){
	
	var form = document.getElementById('searchform');
	var i = form.i.value;
	var search = form.search.value;
	var member_id = $("#member_id").val();
	
	$("#movearea").load("/dokky/ListFollow.do", {
		member_id : member_id,
		currentPage : i,
		search : search,
		ap : 'AjaxFollow'
	});
}

function scrap(){

	var form = document.getElementById('searchform');
	var i = form.i.value;
	var search = form.search.value;
	var member_id = $("#member_id").val();

	$('#movearea').load("/dokky/ScrapList.do", {
		member_id : member_id,
		currentPage : i,
		search : search,
		ap : 'AjaxScrap'
	});
}

function memberpage(){
	
	var form = document.getElementById('searchform');
	var i = form.i.value;
	var search = form.search.value;
	var member_id = $("#member_id").val();
	
	$('#movearea').load("/dokky/MemberPage.do", {
		member_id : member_id,
		currentPage : i,
		search : search,
		ap : 'AjaxMemberPage'
	});
}

function deleteMyBoard(board_id){
	var form = document.getElementById('searchform');
	var i = form.i.value;
	var search = form.search.value;
	var session_id = $("#session_id").val();
	
	if(!confirm("해당 글을 지우시겠습니까?")){ return false; }
	
	$.ajax({
		url:"/dokky/deleteMyBoard.do",
		type: "post",
		data: {board_id: board_id, member_id: session_id, currentPage : i, search : search, ap : "AjaxMyBoardDelete"},
		success: function(data){
			$("#movearea").html(data);
		}
	});
}