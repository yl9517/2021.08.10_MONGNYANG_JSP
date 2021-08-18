/**
 * 
 */

let boardType = $('#btype').val();

$('#add').click(function(){
      location.href="boardinsert.do?boardType="+boardType;		
});

