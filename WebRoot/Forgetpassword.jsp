<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>找回密码</title>
</head>
<body>
<h3 style="color: red">${msg}</h3>
<form action="${pageContext.request.contextPath }/users/findPasswordByEmailNotice.action" method="post">
<table>
       <tr>
           <td>用户名</td>
           <td><input type="text" name=username></td>
       </tr>
       <tr>
           <td>邮箱</td>
           <td><input type="text" name="email"/></td>
       </tr>
       <tr>
       	<td><input type="submit" value="找回密码"></td>
       </tr>
</table>
</form>
</body>
</html>