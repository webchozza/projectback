function scrap(){
	
	var member_id = ${member_id};
	
	$.ajax(){
		url: "/dokky/ScrapList.do",
		type: "json",
		data: {member_id: member_id},
		success: function(data){
			
		}
		
	}
}

