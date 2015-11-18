<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
//验证注册表单
function checkFrom(){

//为其它字段添加非空校验
var password = document.getElementById("password").value;
var repassword = document.getElementById("repassword").value;
if(password!=repassword){
alert("两次密码必须一致");
  return false;
     }
  }
  // 切换验证码
	function change(){
		document.getElementById("myimg").src = "${pageContext.request.contextPath }/CheckCodeController/service.action?"+new Date().getTime();
	}
</script>
</head>
<body>
<h3 style="color: red">${msg}</h3>
<h1>US商场用户注册</h1>
<form action="${pageContext.request.contextPath }/users/insertUsers.action" method="post" onsubmit="return checkForm();">
    <table>
        <tr>
            <td>手机号</td>
            <td><input type="text" name="phone" id="phone"></td>
            <td></td>
        </tr>
        <tr>
        <td>手机验证码</td>
        <td><input type="text" name="ativecode" id="ativecode"/></td>
        
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
            <td>确认密码</td>
            <td><input type="password" name="repassword" id="repassword"></td>
        </tr>
     
        <tr>
             <td><input type="submit" value="注册"/></td>
        </tr>
    </table>
</form>
</body>
</html>