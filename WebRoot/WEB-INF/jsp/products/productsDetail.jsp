<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/tags" prefix="date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品详情</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/uniform.css" />
<link rel="stylesheet" href="css/select2.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link rel="stylesheet" href="font-awesome/css/font-awesome.css" />

</head>

<style type="text/css">
hr {
	border-color: FF7F00;
}

div {
	float: left;
	margin: 10px;
}

div dd {
	margin: 0px;
	font-size: 10pt;
}

div dd.dd_name {
	color: blue;
}

div dd.dd_city {
	color: #000;
}
</style>
<script type="text/javascript">
	function jump() {
		// 获得用户输入页码
		var pNum = document.getElementById("pNum").value;
		location.href = "${pageContext.request.contextPath }/findCommentByPage.action?pNum="
				+ pNum;
	}
</script>
<body>
	<!--Header-part-->
	<div id="header">
		<h3>
			<a href="index.html">US后台管理系统</a>
		</h3>
	</div>
	<!--close-Header-part-->
	<!--top-Header-menu-->
	<div id="user-nav" class="navbar navbar-inverse">
		<ul class="nav">
			<li class="dropdown" id="profile-messages"><a title="" href="#"
				data-toggle="dropdown" data-target="#profile-messages"
				class="dropdown-toggle"><i class="icon icon-user"></i> <span
					class="text">欢迎用户</span><b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="#"><i class="icon-user"></i>个人中心</a></li>
					<li class="divider"></li>
					<li><a href="#"><i class="icon-check"></i>我的任务</a></li>
					<li class="divider"></li>
					<li><a href="login.html"><i class="icon-key"></i>退出登录</a></li>
				</ul></li>
			<li class="dropdown" id="menu-messages"><a href="#"
				data-toggle="dropdown" data-target="#menu-messages"
				class="dropdown-toggle"> <i class="icon icon-envelope"></i> <span
					class="text">信息</span> <span class="label label-important">5</span>
					<b class="caret"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a class="sAdd" title="" href="#"><i class="icon-plus"></i>新消息</a></li>
					<li class="divider"></li>
					<li><a class="sInbox" title="" href="#"><i
							class="icon-envelope"></i>收件箱</a></li>
					<li class="divider"></li>
					<li><a class="sOutbox" title="" href="#"><i
							class="icon-arrow-up"></i>发件箱</a></li>
					<li class="divider"></li>
					<li><a class="sTrash" title="" href="#"><i
							class="icon-trash"></i>垃圾箱</a></li>
				</ul></li>
			<li class=""><a title="" href="#"><i class="icon icon-cog"></i>
					<span class="text">Settings</span></a></li>
			<li class=""><a title="" href="login.html"><i
					class="icon icon-share-alt"></i> <span class="text">退出登录</span></a></li>
		</ul>
	</div>
	<!--close-top-Header-menu-->
	<!--start-top-serch-->
	<div id="search">
		<input type="text" placeholder="Search here..." />
		<button type="submit" class="tip-bottom" title="Search">
			<i class="icon-search icon-white"></i>
		</button>
	</div>
	<!--close-top-serch-->
	<!--sidebar-menu-->
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
			<li class="submenu"><a href="#"><i class="icon icon-user-md"></i>
					<span>用户</span> <span class="label label-important">3</span></a>
				<ul>
					<li><a href="user-all.html">查询所有用户</a></li>
					<li><a href="user-order.html">查询用户订单</a></li>
					<li><a href="user-message.html">添加消息给用户</a></li>
				</ul></li>
			<li class="submenu active"><a href="#"><i
					class="icon icon-shopping-cart"></i> <span>商城</span> <span
					class="label label-important">4</span></a>
				<ul>
					<li><a href="goods-add.html">添加商品</a></li>
					<li><a href="goods-approved.html">通过审核的商品</a></li>
					<li><a href="goods-notpass.html">未通过审核的商品</a></li>
					<li><a href="goods-unapproved.html">未审核的商品</a></li>
				</ul></li>
			<li class="submenu"><a href="see-notes.html"><i
					class="icon icon-pencil"></i> <span>留言板</span> <span
					class="label label-important">1</span></a>
				<ul>
					<li><a href="see-notes.html">查看留言板</a></li>
				</ul></li>
			<li class="submenu"><a href="add-article.html"><i
					class="icon icon-file"></i> <span>文章</span> <span
					class="label label-important">1</span></a>
				<ul>
					<li><a href="add-article.html">添加文章</a></li>
				</ul></li>
		</ul>
	</div>
	<!--sidebar-menu-->
	<!--main-container-part-->
	<div id="content">
		<!--breadcrumbs面包屑导航-->
		<div id="content-header">
			<div id="breadcrumb">
				<a href="index.html" title="返回主页" class="tip-bottom"><i
					class="icon-home"></i> Home</a><a href="goods-detail.html">商品详情</a>
			</div>
			<h1>商品详情</h1>
		</div>
		<!--End-breadcrumbs-->
		<div class="container-fluid">
			<div class="widget-box">
				<div class="widget-title">
					<span class="icon"> <i class="icon-briefcase"></i>
					</span>
					<h5>商品详情</h5>
				</div>
				<div class="widget-content">
					<div class="row-fluid">
						<div class="span6">
							<table class="">
								<tbody>
									<tr>
										<td><h4>商品内容</h4></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="span6">
							<table class="table table-bordered table-invoice">
								<tbody>
								<%-- 	<tr>
										<td class="width30">名称</td>
										<td class="width70"><strong>${productsExtend.name}</strong>
									</tr> --%>
										<tr>
										<td class="width30">摘要</td>
										<td class="width70"><strong>${productsExtend.summary}</strong>
									</tr>
									<%-- <tr>
										<td>商品分类</td>
										<td><strong>${productsExtend.pcname }</strong></td>
									</tr> --%>
									<tr>
										<td>状态码</td>
										<td><strong>${productsExtend.status}</strong></td>
									</tr>
									<tr>
										<td>售价</td>
										<td><strong>${productsExtend.soldprice }</strong></td>
									</tr>
									<tr>
										<td>数量</td>
										<td><strong>${productsExtend.quantity }</strong></td>
									</tr>
									<tr>
										<td>描述</td>
										<td class="width70">${productsExtend.pdescription }</td>
									</tr>
								
									<%-- <tr>
										<td><a
											href="${pageContext.request.contextPath }/insertOrderitemsSuccess.action?pid=${productsExtend.pid}">抢购</a>
										</td>
									</tr> --%>

								</tbody>
							</table>


							<!-- 图片 -->
							<form action="">
								<table width="750" height="60" cellpadding="0" cellspacing="0"
									border="0">
									<c:forEach items="${productsExtend.imagesExtend}"
										var="imagesExtend">
										<div>
											<dl>

												<dt>
													<img
														src="${pageContext.request.contextPath }${imagesExtend.url}"
														width="200" height="150" />
												</dt>
												<dd class="dd_name">${item.name }</dd>
											</dl>
										</div>
									</c:forEach>

								</table>
							</form>
							<%-- <!-- 评论 -->
							<form method="post"
								action="${pageContext.request.contextPath }/insertComments.action?pid=${productsExtend.pid}">
								<table width="100%">
									<tr>
										<td><a
											href="${pageContext.request.contextPath }/findCommentByPage.action?pNum=1&pid=${productsExtend.pid}">查看评论</a>
										</td>
									</tr>
									<tr>
										<td>我要评论<textarea rows="3" cols="30" name="comment"></textarea>
											<input type="submit" value="提交" />
										</td>
									</tr>

								</table>
							</form>
 --%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!--end-main-container-part-->
	<!--Footer-part-->
	<div class="row-fluid">
		<div id="footer" class="span12">
			2015 &copy; US后台管理系统. 欢迎致电 <a href="#">00000000000</a>
		</div>
	</div>
	<!--end-Footer-part-->
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.ui.custom.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.uniform.js"></script>
	<script src="js/select2.min.js"></script>
	<script src="js/matrix.js"></script>
</body>

</html>
