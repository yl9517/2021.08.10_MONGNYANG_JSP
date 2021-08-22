//url에서 파라미터 가져오기
let sear = window.location.search;

const params = new URLSearchParams(sear);
let bType = params.get('boardType');
console.log('타입'+bType);


document.getElementsByClassName(bType).onclick=function(){
	document.getElementsByClassName(bType).classList.toggle('thisclick');

}
