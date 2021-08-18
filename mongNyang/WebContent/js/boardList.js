/**
 * 
 */

let boardType = $('#btype').val();

console.log(boardType);

$('#add').click(function(){
      location.href="boardinsert.do?boardType="+boardType;		
});
	