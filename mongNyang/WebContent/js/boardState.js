let bnum = $('#bnum').val();
let bstate = $('#bstate').val();

/*console.log(bstate);
console.log(typeof bstate)*/

if(bstate=='true'){ //1이면 (bstate가 1이면 해결상태)
	$(".view").toggle();
	$('input:checkbox').prop('checked',true);
		//	console.log('해결상태');
}



let check = $("input[type='checkbox']");

check.click(function(){
	$(".view").toggle();

	if(bstate=='true'){
		bstate = 'false';
	}
	else if(bstate=='false'){
		bstate = 'true';
	}

	location.href="boardstatemodify.do?boardNum="+bnum+"&boardState="+bstate;
	
});
