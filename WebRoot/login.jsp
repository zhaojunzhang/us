<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<script type="text/javascript">
	// 切换验证码
	function change(){
		document.getElementById("myimg").src = "${pageContext.request.contextPath }/CheckCodeController/service.action?"+new Date().getTime();
	}
</script>
</head>
<body>
<h3 style="color: red">${msg}</h3>
	<h1>US登陆页面</h1>

	<form
		action="${pageContext.request.contextPath }/users/loginUsers.action"
		method="post">
		<table >
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username" ></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="password" ></td>
				<td><a href="${pageContext.request.contextPath }/Forgetpassword.jsp">忘记密码</a></td>
			</tr>
			
			<tr>
				<td>验证码</td>
				<td><input type="text" name="checkcode" /></td>
			</tr>
			
                        
            
			<tr>
				<img id="myimg"
					src="${pageContext.request.contextPath }/CheckCodeController/service.action"
					style="cursor:pointer;" onclick="change()" />
				
			</tr>
			<!-- 记住用户名密码、自动登录Filter -->
			<tr>
				<td><input type="submit" value="登录"></td>
				<td><a href="${pageContext.request.contextPath }/regist.jsp">还没有注册吧，快去注册</a></td>
			</tr>
		</table>
	</form>
</body>
</html>