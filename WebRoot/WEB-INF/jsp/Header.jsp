<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css1/us.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css1/us-ui.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css1/us-private.css" />
  <link rel="stylesheet" href="font-awesome/css/font-awesome.css"/>
</head>
<body>
  <!-- head.html start-->
  <!-- header start -->
  <div id="header">
    <nav class="navbar navbar-default" role="navigation">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
      </div>
      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav navbar-left">
          <li><a href="javascript:;">欢迎用户</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
         
		<c:if test="${empty usersCustom}">
		  <li><a href="${pageContext.request.contextPath }/regist.jsp"><span class="col1">免费注册</span></a></li>
          <li><a href="${pageContext.request.contextPath }/login.jsp">登录</a></li>
          </c:if>
           <c:if test="${not empty usersCustom}">
		    <li><a href="">用户名：${usersCustom.nickname }</a></li>
		    <li><a href="${pageContext.request.contextPath }/invalidate.jsp">注销</a></li>
	       </c:if>
          
          <li class="dropdown">
            <a href="${pageContext.request.contextPath }/users/personCenter.action" class="dropdown-toggle" data-toggle="dropdown">个人中心<b class="caret"></b></a>
            <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath }/users/findUserById.action">补全个人中心</a></li>
              <li><a href="${pageContext.request.contextPath }/users/findUserPassword.action">密码管理和权限验证</a></li>
              <li><a href="${pageContext.request.contextPath }/guahuo.jsp">我要挂货</a></li>
              <li><a href="${pageContext.request.contextPath }/findPersonOrderitemsByPage.action?pNum=1">个人记录</a></li>
               <li><a href="${pageContext.request.contextPath }/findOrderitemsExtendByPageWeb.action?pNum=1">抢购记录</a></li>
              <li><a href="${pageContext.request.contextPath }/users/findMailboxByUid.action">个人信箱</a></li>
            </ul>
          </li>
          <li><a href="#">手机版</a></li>
        </ul>
      </div>
      <!-- /.navbar-collapse -->
    </nav>
  </div>
  <!-- header end -->

  <!-- top start -->
  <div id="top" class="clearfix">
    <div class="container">
      <div class="row">
        <form id="search" action="" method="POST" role="form" class="form col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-8">
          <div class="form-group">
            <input type="text" class="form-control" id="" placeholder="点击查询">
            <button type="submit" class="btn btn-primary"></button>
          </div>
        </form>
      </div>
    </div>
  </div>
  <%
    String str = request.getParameter("page_tag");
   %>
 <!-- navbar start -->
  <nav class="navbar navbar-default g-navbar" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex2-collapse">
        <span class="sr-only">US校园</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
  
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse navbar-ex2-collapse">
      <div class="container g-nav">
        <ul class="nav navbar-nav">
          <li <%if(str.equals("schoolLife")) out.print("class=\"active\"");%>><a href="${pageContext.request.contextPath }/users/schoolLife.action">校园生活</a></li>
          <li <%if(str.equals("TescoCampus")) out.print("class=\"active\"");%>><a href="${pageContext.request.contextPath }/findAllProducts.action?status=1">校园易购</a></li>
          <li <%if(str.equals("schoolSide")) out.print("class=\"active\"");%>><a href="${pageContext.request.contextPath }/users/schoolSide.action">校园周边</a></li>
          <li <%if(str.equals("NotesWeb")) out.print("class=\"active\"");%>><a href="${pageContext.request.contextPath }/findAllNotesWeb.action" >留言墙</a></li>
        </ul>
      </div>
    </div><!-- /.navbar-collapse -->
  </nav>
  
  <!-- navbar end -->
  <!-- /.navbar-collapse --> </nav>
	<nav class="navbar navbar-default g-navbar-s" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex3-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
	</div>

	