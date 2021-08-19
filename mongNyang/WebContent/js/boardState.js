let boardType = $('#btype').val();
let boardNum = $('#bnum').val();


let check = $("input[type='checkbox']");
let state = 0; //미해결
check.click(function(){
	$(".view").toggle();
	state++;
	console.log(state);

	location.href="boardstateupdate.do?boardType="+boardType+"&boardNum="+boardNum+"&boardState="+state;
	
});
