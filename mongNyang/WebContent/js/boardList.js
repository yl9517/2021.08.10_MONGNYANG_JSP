/**
 * 
 */

let boardType = $('#btype').val();

$('#add').click(function(){
      location.href="boardinsert.do?boardType="+boardType;		
});

$('#addrAll').click(function(){
	location.href="boardlist.do?boardType="+boardType;
});

$('#addrEast').click(function(){
	location.href="boardlist.do?boardType="+boardType+"&petAddr=EAST";
});

$('#addrWest').click(function(){
	location.href="boardlist.do?boardType="+boardType+"&petAddr=WEST";
});

$('#addrSouth').click(function(){
	location.href="boardlist.do?boardType="+boardType+"&petAddr=SOUTH";
});

$('#addrNorth').click(function(){
	location.href="boardlist.do?boardType="+boardType+"&petAddr=NORTH";
});