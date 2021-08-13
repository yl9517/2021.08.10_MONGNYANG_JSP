const toogleBtn = document.querySelector('.toogleBtn');
const boards = document.querySelector('.boards');
const user = document.querySelector('.user');

let num =1;

toogleBtn.addEventListener('click',() => {
	
	if(num%2==1){
		boards.style.display="block";
		user.style.display = "block";
		num++;
	}else{
		boards.style.display="none";
		user.style.display = "none";
		num++;
	}
});
