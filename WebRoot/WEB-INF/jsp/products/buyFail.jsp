<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<%
		response.setHeader("refresh", "2;URL=/us1/shangcheng.jsp");//这里的3,是你要确定的时间秒URL是要跳转的地址
	%>

	<h2>抢购失败，每个用户一次只能抢购一个商品</h2>
	<br>2秒后将跳转到首页
	<br>
	<br> 如果没有跳转,请按
	<a href="/us1/shangcheng.jsp">这里</a>!!!
	<br>
	<table>
		<tr>
			<td><input type="button" value="返回" onclick="history.go(-1);">
			</td>
		</tr>
	</table>

</body>
</html>