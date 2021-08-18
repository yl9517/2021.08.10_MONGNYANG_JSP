let check = $("input[type='checkbox']");
let num = 0;
check.click(function(){
	$(".view").toggle();
	num++;
	console.log(num);
	
	if(num%2==1) {//홀수 = 해결 될 때마다
		//frontcontroller 이동
	}
});



