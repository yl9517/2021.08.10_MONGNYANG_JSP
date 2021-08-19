function checkSubmit(writeId,useId) {
	console.log('확인'+writeId);
	console.log('중복숫자'+useId);

    let id = document.getElementById('id').value;
    
	if(writeId!=id || useId!=0){
		alert('아이디 중복확인을 해주세요.');
		return false;
	}
	
    
    let pwd = document.getElementById('pwd').value;
    let pwdCheck = document.getElementById('pwdCheck').value;
   
	console.log('비번1'+pwd);
	console.log('비번2'+pwdCheck);
	
	if(pwd!=pwdCheck){
		alert('패스워드를 다시 확인해주세요.');
		return false;
	}
	
}