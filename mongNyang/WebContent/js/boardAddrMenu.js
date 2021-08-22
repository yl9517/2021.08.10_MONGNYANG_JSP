//보드 리스트랑 연결

let boardType = $('#btype').val(); //보드타입
let petAddr = $('#paddr').val(); //펫타입

let petAddrlower = petAddr.toLowerCase(); //소문자로
let thisaddr = 'images/map_'+petAddrlower+'.png'; //이미지 이름 설정

let map = document.getElementById('map'); //이미지구역 가져오기		
map.setAttribute('src',thisaddr);	//이미지 변경

document.getElementById(petAddrlower).classList.add('thisclick'); //선택지역 class 추가

//지역메뉴 누르면
let addreach = document.getElementsByClassName('addrBtn'); //addrBtn 배열에 담기

	for(let i=0; i<addreach.length; i++){
		
		addreach[i].onclick=function(){		
			if(this.id != 'all'){ //동서남북이면
				location.href="boardlist.do?boardType="+boardType+"&petAddr="+this.id.toUpperCase();
			}else{ //전체보기면
				location.href="boardlist.do?boardType="+boardType;		
			}
		}
	}

//글쓰기버튼 누르면
$('#add').click(function(){
    location.href="boardinsert.do?boardType="+boardType;		
});