<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>US首页</title>
</head>
<body>
<h3 style="color: red">${msg}</h3>
用户名：${usersCustom.nickname }
用户头像：<img src="${pageContext.request.contextPath }${usersCustom.avator }"  width="100" height="100"/>
上一次登陆时间 :<%=request.getSession().getAttribute("dateStr") %>
<a href="${pageContext.request.contextPath }/login.jsp">登陆</a>
<a href="${pageContext.request.contextPath }/regist.jsp">注册</a>
<a href="${pageContext.request.contextPath }/users/schoolLife.action">校园生活</a>
<a href="${pageContext.request.contextPath }/findProductsType.action?type=闲互送">校园易购</a>
<a href="${pageContext.request.contextPath }/users/schoolSide.action">校园周边</a>
<a href="${pageContext.request.contextPath }/findAllNotesWeb.action" >留言墙</a>
<a href="${pageContext.request.contextPath }/users/personCenter.action">个人中心</a>
<a href="${pageContext.request.contextPath }/invalidate.jsp">注销</a>
</body>
</html>