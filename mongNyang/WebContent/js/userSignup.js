
$('#overlap').click(function() {
	let userid = $('#id').val();
	if(!userid){
		alert("아이디를 입력해주세요");
		return false;
	}else{
		location.href="selectuser.do?userId="+userid;
	}
	
});