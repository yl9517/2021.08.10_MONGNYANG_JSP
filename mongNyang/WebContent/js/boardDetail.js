let boardType = $('#boardType').val();
let boardNum = $('#boardNum').val();

$('#add').click(function(){
    location.href="boardinsert.do?boardType="+boardType;		
});

$('#modify').click(function(){
	location.href="boardmodify.do?boardType="+boardType+"&boardNum="+boardNum;
});

$('#delete').click(function(){
	location.href="boarddel.do?boardType="+boardType+"&boardNum="+boardNum;
});

$('#list').click(function(){
	location.href="boardlist.do?boardType="+boardType;
});