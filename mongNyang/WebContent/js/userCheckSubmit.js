function checkSubmit(writeId,useId) {
    let id = document.getElementById('id').value;
    
	if(writeId!=id || useId!=0){
		alert('아이디 중복확인을 해주세요.');
		return false;
	}

	
    
    let pwd = document.getElementById('pwd').value;
    let pwdCheck = document.getElementById('pwdCheck').value;
	
	if(pwd!=pwdCheck){
		alert('패스워드를 다시 확인해주세요.');
		return false;
	}
	
}