<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
</head>
<body>
	<h1>商品上传成功</h1>
	<form action="" method="post">

		<table>
			<tr>
				<td><input type="button" value="返回" onclick="history.go(-1);">
				</td>
			</tr>
			<c:forEach items="${imagesExtend}" var="item">

				<tr>
					<td>${item.imgid}</td>
					<td><img src="${pageContext.request.contextPath }${item.url}"
						alt=""></td>
				</tr>
			</c:forEach>
		</table>

	</form>
</body>
</html>