function MemberCheck(i){

	stoploop();
	
	if(i == 'all'){
		$('#area').load('/dokky/MemberList.do', {ap:'AjaxArrange'});
	}
	if(i == 'on'){
		$('#area').load('/dokky/MemberList.do', {ap:'AjaxArrange', ch:1});
	}
	if(i == 'out'){
		$('#area').load('/dokky/MemberList.do', {ap:'AjaxArrange', ch:0});
	}
}

//멤버리스트 화면 로딩시 한번만 동작함(이후엔 #area영역에 호출된 MemberListPage의 setTimeout함수가 동작함)
$(document).ready(function(){

	var form = document.getElementById('searchform');
	var path = $("#path").val();
	var i = form.i.value;
	var search = form.search.value;
	var n = $("#demo-category").val();
	
	$("#area").load(path,{
		currentPage: i, 
		search: search, 
		n: n, 
		ap: 'AjaxMemberCheck'
		});
});
