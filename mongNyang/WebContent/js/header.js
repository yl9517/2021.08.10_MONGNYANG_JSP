$('.boards>a').click(function() {
	let th = $(this);
	console.log(th+'click');
	$(this).addClass('clickmenu');
});