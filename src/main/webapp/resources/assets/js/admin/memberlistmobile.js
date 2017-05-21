function memberModifyForm(member_id){
	
	$("#membermodifyarea").load("/dokky/AdminModifyForm.do", {member_id:member_id});
}

function deleteMember(member_id){
	
	if(confirm("해당 회원을 탈퇴 처리하시겠습니까?") == false){ return false; }
	
	$.ajax({
		url:"/dokky/MemberDelete.do",
		type: "post",
		data: { member_id: member_id, ap:"AjaxMemberListMobile"},
		success: function(data){
			$("#area").html(data);
		}
		
	});
}

function paging(path, i, search, n) {
	
	stoploop();
	
	$('#area').load(path, {
		currentPage : i,
		search : search,
		n : n,
		ap : 'AjaxMemberListMobile'
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
	
	$("#area").load(path,{
		currentPage: i, 
		search: search, 
		ch: ch,
		n: n, 
		ap: 'AjaxMemberListMobile'
		});
	setTimeout(loop, 1000);
}, 1000);

function stoploop(){
	clearTimeout(loop);
}