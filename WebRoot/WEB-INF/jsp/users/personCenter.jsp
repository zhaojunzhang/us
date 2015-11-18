<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人中心</title>
</head>
<body>
<h3 style="color: red">${msg}</h3>
用户名：${usersCustom.nickname }
用户头像：<img src="${pageContext.request.contextPath }${usersCustom.avator }"  width="50" height="50"/>
上一次登陆时间 :<%=request.getSession().getAttribute("dateStr") %>
<a href="${pageContext.request.contextPath }/login.jsp">登陆</a>
<a href="${pageContext.request.contextPath }/regist.jsp">注册</a>
<a href="${pageContext.request.contextPath }/users/schoolLife.action">校园生活</a>
<a href="${pageContext.request.contextPath }/findProductsType.action?type=闲互送"">校园易购</a>
<a href="${pageContext.request.contextPath }/users/schoolSide.action">校园周边</a>
<a href="${pageContext.request.contextPath }/findAllNotesWeb.action" >留言墙</a>
<a href="${pageContext.request.contextPath }/users/personCenter.action">个人中心</a>
<a href="${pageContext.request.contextPath }/invalidate.jsp">注销</a>
<p>
<c:if test="${empty usersCustom }">
<%
response.setHeader("refresh", "3;URL=/us1/login.jsp");//这里的3,是你要确定的时间秒URL是要跳转的地址
%>
<font color="red" size="5"> 您还未登录,请您先登录<br> <br>
三秒后将跳转到登录页面 <br> <br> 如果没有跳转,请按 <a href="/us1/login.jsp">这里</a>!!!
<br> </font>

</c:if>
<c:if test="${not empty usersCustom }">
<a href="${pageContext.request.contextPath }/users/findUserById.action">补全个人信息</a>
<a href="${pageContext.request.contextPath }/users/findUserPassword.action">密保管理和权限验证</a>
<a href="${pageContext.request.contextPath }/guahuo.jsp">我要挂货</a>
<a href="${pageContext.request.contextPath }/findPersonOrderitems.action">个人记录</a>
<a href="${pageContext.request.contextPath }/users/findMailboxByUid.action">个人信箱</a>
</c:if>

</body>
</html>