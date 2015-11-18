<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>找回密码</title>
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
<%
   int uid = Integer.parseInt(request.getParameter("uid"));
   
 %>
<body>
<form action="${pageContext.request.contextPath }/users/findPasswordByEmail.action" method="post">
<table>
        <tr style="display: none;">
		    <td>用户uid</td>
		    <td><input type="text" name="uid" value="<%=uid %>"/></td>
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
         <td><input type="submit" value="修改密码"/></td>
      </tr>
</table>
</form>
</body>
</html>