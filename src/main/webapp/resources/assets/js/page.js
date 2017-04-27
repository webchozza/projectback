/*
<script src="/dokky/resources/assets/js/page.js"></script>
 */

//Ajax를 이용한 페이징
function ch(path, i, search, n) {
	$('#area').load(path, {
		currentPage : i,
		search : search,
		n : n,
		ap : 'AjaxPaging'
	});
}

function deleteMember() {
	return confirm("해당 회원을 탈퇴처리 하시겠습니까?");
}

//Ajax를 이용한 검색
function sch() {
	
	var form = document.getElementById('searchform');
	var path = form.path.value;
	var i = form.i.value;
	var search = form.search.value;
	var n = form.n.value;
	
	$('#area').load(path, {
		currentPage : i,
		search : search,
		n : n,
		ap : 'AjaxSearch'
	});
}