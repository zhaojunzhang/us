<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
response.setHeader("refresh", "3;URL=/us1/findAllProducts.action?status=1");//这里的3,是你要确定的时间秒URL是要跳转的地址
%>
<br> 
用户名：${usersCustom.nickname }
用户分组：${usersCustom.name }
</body>
</html>