function memberModifyForm(member_id){
	
	$("#membermodifyarea").load("/dokky/AdminModifyForm.do", {member_id:member_id});
}

function deleteMember(member_id){
	
	if(confirm("해당 회원을 탈퇴 처리하시겠습니까?") == false){ return false; }
	
	$.ajax({
		url:"/dokky/MemberDelete.do",
		type: "post",
		data: { member_id: member_id, ap:"AjaxMemberWeb"},
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
	
	$("#area").load(path,{
		currentPage: i, 
		search: search, 
		ch: ch,
		n: n, 
		ap: 'AjaxMemberWeb'
		});
	setTimeout(loop, 1000);
}, 1000);

function stoploop(){
	clearTimeout(loop);
}

function admin(member_admin, member_id){
	
	if(member_admin == 1){
	if(confirm("관리자로 하시겠습니까?") == false){
		return false;
	}}else if(confirm("회원으로 하시겠습니까?") == false){
		return false;
	}
	
	$.ajax({
		url:"/dokky/AdminUpdate.do",
		type: "post",
		dataType: "json",
		data: {member_admin: member_admin, member_id: member_id},
		success: function(data){
			if(member_admin==0){
				$("#member_admin").html("회원");
			}else if(member_admin==1){
				$("#member_admin").html("관리자");
			}
		}
		
	});
	
}