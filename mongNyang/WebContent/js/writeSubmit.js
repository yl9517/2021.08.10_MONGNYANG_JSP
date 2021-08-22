function writeSubmit() {
	let boardTitle = $('#boardTitle').val();
	let boardContent = $('#boardContent').val();
	
	if(boardTitle=="" || boardTitle==null){ //제목 입력 안했으면
		alert('제목을 입력해주세요');
		return false;
	}
	if(boardContent=="" || boardContent==null){ //제목 입력 안했으면
		alert('내용을 입력해주세요');
		return false;
	}
	
}