
$('#overlap').click(function() {
	//아이디 한글 못받게
	var reg = /^[a-zA-Z0-9]*$/;
	
	if(!reg.test(join.id.value)){
		alert("영문/숫자 조합만 가능합니다.");
		return false;
	}
	
	
	let writeId = $('#id').val();
	
	if(!writeId){
		alert("아이디를 입력해주세요");
		return false;
	}else{
		location.href="useroverlap.do?writeId="+writeId;
	}
	
});