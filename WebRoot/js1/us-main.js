/*
	us-main.js	
	版本号：
			1.1.1 	created by yancey qq:1352667433	

*/

$(function(){

			/*评论框宽度*/
			var $offsetW = $('.critic-content .user').outerWidth() + 38;
			var $criticW = $('.critic-content .option').innerWidth();
			$('.critic .msg').outerWidth($criticW-$offsetW);
			console.log($offsetW);

			/*底部分页居中*/
			var $pW = $('.pagination').width();
			$('.pagination').css('left',-$pW/2);
	
});