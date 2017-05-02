function scrap(){

	var form = document.getElementById('searchform');
	var i = form.i.value;
	var search = form.search.value;
	var member_id = ${member_id};
	var forEach = '<';
	forEach += 'c:forEach var="board" items="${board}">';
	alert(forEach);
	alert(member_id);

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
	var member_id = ${member_id};
	var forEach = '<';
	forEach += 'c:forEach var="board" items="${list}">';
	
	$('#movearea').load("/dokky/MemberPage.do", {
		member_id : member_id,
		currentPage : i,
		search : search,
		ap : 'AjaxMemberPage'
	});
}