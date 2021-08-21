let boardType = $('#btype').val(); //보드타입


//지역메뉴 누르면
let addreach = document.getElementsByClassName('addrBtn'); //addrBtn 배열에 담기

	for(let i=0; i<addreach.length; i++){
	//	addreach[i].classList.remove('thisclick');
		
		addreach[i].onclick=function(){		
			this.classList.add('thisclick'); //css 추가
			
			let thisaddr = 'images/map_'+this.id+'.png'; //아이디 가져오기 (동서남북 소문자)
			console.log("아이디:"+this.id.toUpperCase());
			console.log("이미지:"+thisaddr);
			
			let map = document.getElementById('map'); //이미지 가져오기		
			map.setAttribute('src',thisaddr);	//이미지 변경
			
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