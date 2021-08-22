//url에서 파라미터 가져오기
let sear = window.location.search;

const params = new URLSearchParams(sear);
let bType = params.get('boardType');
if(bType != "" && bType!=null){
	document.getElementById(bType).classList.add('thisclick');
}
