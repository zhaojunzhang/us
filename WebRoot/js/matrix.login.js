
$(document).ready(function(){

	var login = $('#loginform');
	var recover = $('#recoverform');
	var speed = 400;

	$('#to-recover').click(function(){
		
		$("#loginform").slideUp();
		$("#recoverform").fadeIn();
	});
	$('#to-login').click(function(){
		
		$("#recoverform").hide();
		$("#loginform").fadeIn();
	});
	
	(function(){
		var validate = $("#loginform").validate({
        debug: true, //调试模式取消submit的默认提交功能   
        //errorClass: "label.error", //默认为错误的样式类为：error   
        focusInvalid: false, //当为false时，验证无效时，没有焦点响应  
        onkeyup: false,   
        submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form   
            alert("提交表单");   
            form.submit();   //提交表单   
        },   
        rules:{
            username:{
                required:true,
                rangelength:[0,11]
            },
            password:{
                required:true,
                digits:true,
                rangelength:[5,12]
            }             
        },
        messages:{
            username:{
                required:"必填",
                rangelength:"用户名必须是{0}到11个数字"
            },
            password:{
                required: "不能为空",
                digits:"密码必须是数字",
                rangelength: "确认密码的必须为{0}到{1}个字符"
            }                                
        }
                  
    }); 
	})()
    
  if($.browser.msie == true && $.browser.version.slice(0,3) < 10) {
      $('input[placeholder]').each(function(){ 
     
        var input = $(this);       
       
        $(input).val(input.attr('placeholder'));
               
        $(input).focus(function(){
             if (input.val() == input.attr('placeholder')) {
                 input.val('');
             }
        });
       
        $(input).blur(function(){
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.val(input.attr('placeholder'));
            }
        });

 		 });        
  }



});