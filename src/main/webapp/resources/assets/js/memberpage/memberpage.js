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

function followclick(addordelete){
	
	var session_id = $("#session_id").val();//팔로우 거는 사람
	var member_id = $("#member_id").val();//팔로우 당하는 사람
	
	if(addordelete == "add"){
	$.ajax({
		url:"/dokky/AddFollow.do",
		type:"get",
		dataType:"json",
		data: {follow_member_id : member_id, member_id : session_id},
		success: function(data){
			followcheck(data);
		}
	});}
	else if (addordelete == "delete"){
	$.ajax({
		url:"/dokky/DeleteFollow.do",
		type:"get",
		dataType:"json",
		data: {follow_member_id : member_id, member_id : session_id},
		success: function(data){
			followcheck(data);
		}
	});}
}

function followcheck(checkValue){

	if(checkValue == "me"){
		$("#me").html("");
	} else if(checkValue == null || checkValue == "0"){//멤버페이지를 불러올 때 팔로우여부 체크값을 보내서 조건을 준다음 plus의 값을 설정함
	$("#plusFollow").html("");
	$("#plusFollow").html('<a href="#" onclick=\'followclick("add")\'><h4 style="color: #f56a6a;">+팔로우</h4></a>');
	} else if(checkValue != null || checkValue == "1"){
	$("#plusFollow").html("");
	$("#plusFollow").html('<a href="#" onclick=\'followclick("delete")\'><h4 style="color: #f56a6a;">팔로우 해제</h4></a>');
	}
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