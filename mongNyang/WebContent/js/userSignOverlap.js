
$('#overlap').click(function() {
	let writeId = $('#id').val();
	
	if(!writeId){
		alert("아이디를 입력해주세요");
		return false;
	}else{
		location.href="useroverlap.do?writeId="+writeId;
	}
	
});