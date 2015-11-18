<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有的操作</title>
</head>
<body>
	<h1>商品、留言墙数据管理中心</h1>

	 <form action="">
	 
	 	<table>
	 		<tr> <td><a href="${pageContext.request.contextPath }/findProductsByPage.action?pNum=1&status=1" >通过审查的商品</a></td> </tr>
	 		<tr> <td><a href="${pageContext.request.contextPath }/findProductsByPage.action?pNum=1&status=0" >没有审查的商品</a></td> </tr>
	 		<tr> <td><a href="${pageContext.request.contextPath }/findProductsByPage.action?pNum=1&status=2" >没有通过审查的商品</a></td> </tr>
	     	<tr> <td><a href="${pageContext.request.contextPath }/findByPageNotes.action?pNum=1" >留言墙</a></td> </tr>
	 		<tr><td> <a href="${pageContext.request.contextPath }/adminguahuo.jsp">管理员挂货</a>  </td></tr>
	 		<tr><td> <a href="${pageContext.request.contextPath }/findOrderitemsExtendByPage.action?pNum=1">抢购记录查询</a></td></tr>
	 	</table>
	 </form>
	


</body>
</html>