function MemberCheck(i,mobile){
console.log(mobile);
	stoploop();
	
	if(mobile == "web"){ var ap = "AjaxMemberWeb"; }
	else if(mobile == "mobile"){ var ap = "AjaxMemberListMobile"; }
	
	if(i == 'all'){
		$("#ch").val("");
		$('#area').load('/dokky/MemberList.do', {ap:ap});
	}
	if(i == 'on'){
		$("#ch").val("1");
		$('#area').load('/dokky/MemberList.do', {ap:ap, ch:1});
	}
	if(i == 'out'){
		$("#ch").val("0");
		$('#area').load('/dokky/MemberList.do', {ap:ap, ch:0});
	}
}

$(document).ready(function(){

	if(window.innerWidth > 500){
		pageload(1);
	}else if(window.innerWidth <= 500){
		pageload(0);
	}
});


function pageload(pagewidth){
	var form = document.getElementById('searchform');
	var path = $("#path").val();
	var i = form.i.value;
	var search = form.search.value;
	var n = $("#demo-category").val();
	
	if(pagewidth == 0){
		$("#area").load(path,{
			currentPage: i, 
			search: search, 
			n: n, 
			ap: 'AjaxMemberListMobile'
			});
	}else if(pagewidth == 1){
		$("#area").load(path,{
			currentPage: i, 
			search: search, 
			n: n, 
			ap: 'AjaxMemberListWeb'
			});
	}
}