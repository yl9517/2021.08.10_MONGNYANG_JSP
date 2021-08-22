//url에서 파라미터 가져오기
let search = window.location.search;

const params = new URLSearchParams(search)
let boardType = params.get('boardType');
console.log('타입'+boardType);


document.getElementsByClassName(boardType).onclick=function(){
	document.getElementsByClassName(boardType).classList.toggle('thisclick');

}
