<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>密码管理</title>
<script type="text/javascript">
//验证注册表单
function checkFrom(){

//为其它字段添加非空校验
var password = document.getElementById("newpassword").value;
var repassword = document.getElementById("renewpassword").value;
if(password!=repassword){
alert("两次密码必须一致");
  return false;
}
}
</script>
</head>
<body>
<form action="${pageContext.request.contextPath }/users/updateUserPasswordById.action" method="post" onsubmit="return checkForm();">
  <table>
      <tr style="display: none;">
		    <td>用户uid</td>
		    <td><input type="text" name="uid" value="${usersCustom.uid}"/></td>
	  </tr>
      <tr>
         <td>原密码</td>
         <td><input type="password" name="password"></td>
         <td><h6 style="color: red">${msg}</h6></td>
      </tr>
      <tr>
         <td>新密码</td>
         <td><input type="password" name="newpassword" id="newpassword"></td>
      </tr>
      <tr>
         <td>重复新密码</td>
         <td><input type="password" name="renewpassword" id="renewpassword"></td>
      </tr>
      <tr>
      <td>手机号</td>
      <td><input type="text" name="phone" value="${usersCustom.phone}"></td>
      <td>倒计时发送验证码</td>
      </tr>
      <tr>
        <td>手机验证码</td>
        <td><input type="text" name="updateCheckcode"></td>
      </tr>
       <tr>
             <td><input type="submit" value="修改密码"/></td>
             <td>
             <input type="button" value="返回" onclick="history.go(-1);"/>
             </td>
             <td><a href="${pageContext.request.contextPath }/users/findPhoneById.action">更改绑定的手机</a></td>
             <td><a href="${pageContext.request.contextPath }/purviewVerification.jsp">进行权限验证</a></td>
        </tr>
        
  </table>
</form>
</body>
</html>