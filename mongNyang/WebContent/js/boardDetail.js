let boardType = $('#boardType').val();
let boardNum = $('#boardNum').val();

$('#add').click(function(){
    location.href="boardinsert.do?boardType="+boardType;		
});

$('#modify').click(function(){
	location.href="boardmodify.do?boardType="+boardType+"&boardNum="+boardNum;
});

$('#delete').click(function(){

   	 if(confirm("글을 삭제 하시겠습니까?")){		  		  
     		 location.href="boarddel.do?boardType="+boardType+"&boardNum="+boardNum;
	}else{
		return;	
	}
    
});

$('#list').click(function(){
	location.href="boardlist.do?boardType="+boardType;
});