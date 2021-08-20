function modifyAlert(rNum){
	console.log(rNum);
	location.href="useralertupdate.do?replyNum="+rNum+"&changeAlert=1";
	return true;
}