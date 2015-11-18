<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<div id="sidebar">
		<a href="#" class="visible-phone"><i class="icon icon-home"></i>menu</a>
		<ul>
			<li><a href="index.html"><i class="icon icon-home"></i> <span>首页</span></a>
			</li>
			<li class="submenu"><a href="javascript:;"><i
					class="icon icon-th-list"></i> <span>校园生活</span> <span
					class="label label-important">4</span></a>
				<ul>
					<li><a href="college-joke.html">校园八卦</a></li>
					<li><a href="college-wind.html">校园风云</a></li>
					<li><a href="college-info.html">校园资讯</a></li>
					<li><a href="college-activity.html">校园活动</a></li>
				</ul></li>
			<li class="submenu"><a href="#"><i class="icon icon-plane"></i>
					<span>校园周边</span> <span class="label label-important">3</span></a>
				<ul>
					<li><a href="srd-business.html">口碑商</a></li>
					<li><a href="srd-easy.html">便生活</a></li>
					<li><a href="srd-activity.html">有活动</a></li>
				</ul></li>
			<!-- 用户 -->
			<li class="submenu "><a href="#"><i class="icon icon-user-md"></i>
					<span>用户</span> <span class="label label-important">3</span></a>
				<ul>
					<li>
					<li><a href="user-all.html">查询所有用户</a></li>
					<li><a
						href="${pageContext.request.contextPath }/findOrderitemsExtendByPage.action?pNum=1">查询用户订单</a></li>
					<li><a href="user-message.html">添加消息给用户</a></li>
				</ul></li>
			<!-- 商城 -->
			<li class="submenu active"><a href=""><i
					class="icon icon-shopping-cart"></i> <span>商城</span> <span
					class="label label-important">4</span></a>
				<ul>
					<li><a href="adminguahuo.jsp">添加商品</a></li>
					<li><a
						href="${pageContext.request.contextPath }/findProductsByPage.action?pNum=1&status=1">通过审核的商品</a></li>
					<li><a
						href="${pageContext.request.contextPath }/findProductsByPage.action?pNum=1&status=0">未通过审核的商品</a></li>
					<li><a
						href="${pageContext.request.contextPath }/findProductsByPage.action?pNum=1&status=2">未审核的商品</a></li>
				</ul></li>
			<li class="submenu"><a href=""><i class="icon icon-pencil"></i>
					<span>留言板</span> <span class="label label-important">1</span></a>
				<ul>
					<li><a
						href="${pageContext.request.contextPath }/findByPageNotes.action?pNum=1">查看留言板</a></li>
				</ul></li>
			<li class="submenu"><a href="add-article.html"><i
					class="icon icon-file"></i> <span>文章</span> <span
					class="label label-important">1</span></a>
				<ul>
					<li><a href="add-article.html">添加文章</a></li>
				</ul></li>
		</ul>
	</div>