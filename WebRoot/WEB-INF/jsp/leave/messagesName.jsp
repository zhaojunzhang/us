<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/tags" prefix="date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<title>留言板</title>
<script type="text/javascript">
	function jump() {
		// 获得用户输入页码
		var pNum = document.getElementById("pNum").value;
		location.href = "${pageContext.request.contextPath }/findByPageNotesName.action?pNum="
				+ pNum;
	}
	// 按钮 全部选中，全部取消
	function changeSelectState() {
		// 判断全选按钮 是选中 还是 取消
		var selectAll = document.getElementById("selectAll");
		if (selectAll.checked) {
			// 选中
			var nodelist = document.getElementsByName("ids");
			for ( var i = 0; i < nodelist.length; i++) {
				nodelist[i].checked = true;
			}
		} else {
			// 取消
			var nodelist = document.getElementsByName("ids");
			for ( var i = 0; i < nodelist.length; i++) {
				nodelist[i].checked = false;
			}
		}
	}
</script>
</head>
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
			<li class="submenu"><a href="#"><i
					class="icon icon-shopping-cart"></i> <span>商城</span> <span
					class="label label-important">4</span></a>
				<ul>
					<li><a href="goods-add.html">添加商品</a></li>
					<li><a href="goods-approved.html">通过审核的商品</a></li>
					<li><a href="goods-notpass.html">未通过审核的商品</a></li>
					<li><a href="goods-unapproved.html">未审核的商品</a></li>
				</ul></li>
			<li class="submenu active"><a href="see-notes.html"><i
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
					class="icon-home"></i> Home</a>
			</div>
			<h1>查看留言板</h1>
		</div>
		<!--End-breadcrumbs-->
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>查看留言板</h5>
						</div>
						<form
							action="${pageContext.request.contextPath }/findByPageNotesName.action?pNum=1"
							role="form" method="post">
							<div class="form-group">
								<h5>输入用户的名字</h5>
								<input type="text" class="form-control" name="username" id=""
									placeholder="输入查询内容"> <input type="submit"
									class="btn btn-primary" value="查询">
							</div>
						</form>

						<c:if test="${empty pageBean.notesExtend}">
							<h1>该页无任何数据！</h1>
						</c:if>
						<c:if test="${not empty pageBean.notesExtend}">
							<form
								action="${pageContext.request.contextPath }/delBatchNotes.action"
								method="post">

								<div class="widget-content nopadding">
									<table class="table table-bordered data-table">
										<thead>
											<tr>
												<th>全选 <input type="checkbox" id="selectAll"
													onclick="changeSelectState();" /></th>
												<th>用户名</th>
												<th>用户ip</th>
												<th>留言内容</th>
												<th>时间</th>
												<th>删除操作</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach items="${pageBean.notesExtend}" var="item">
												<tr class="gradeX">
													<td><input type="checkbox" name="ids"
														value="${item.noteid }" /></td>
													<td>${item.username }</td>
													<td>${item.ip}</td>
													<td>${item.content}</td>
													<td><date:date value="${item.ncreated }" /></td>
													<td><a
														href="${pageContext.request.contextPath }/deleteNotes.action?noteid=${item.noteid}">删除</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="pagination alternate text-center">
										<ul>
				<!-- 显示首页 -->
				<c:if test="${pageBean.pNum == 1}">
					首页  上一页
				</c:if>
				
				<c:if test="${pageBean.pNum != 1}">
				<li>	<a href="${pageContext.request.contextPath }/findByPageNotesName.action?username=${pageBean.name}&pNum=1">首页</a></li>
				<li>	<a href="${pageContext.request.contextPath }/findByPageNotesName.action?username=${pageBean.name}&pNum=${pageBean.pNum-1 }">
				上一页</a></li>
				</c:if>
				
				<!-- 当前页为中心前后各显示10页 -->
				<c:set var="begin" value="1" scope="page" />
				<c:set var="end" value="${pageBean.totalPageNum}" scope="page" />
				
				<!-- 判断前面有没有10页 -->
				<c:if test="${pageBean.pNum-10>0}">
					<c:set var="begin" value="${pageBean.pNum-10}" scope="page" />
				</c:if>
				
				<!-- 判断后面有没有10页 -->
				<c:if test="${pageBean.pNum+10 < pageBean.totalPageNum}">
					<c:set var="end" value="${pageBean.pNum + 10}" scope="page" />
				</c:if>
				
				<!-- 当前页不显示链接 -->
				<c:forEach begin="${begin}" end="${end}" var="i">
					<c:if test="${pageBean.pNum==i}">
					<li>	${i }</li>
					</c:if>
					<c:if test="${pageBean.pNum!=i}">
					<li>	<a href="${pageContext.request.contextPath }/findByPageNotesName.action?username=${pageBean.name}&pNum=${i }">${i } </a></li>
					</c:if>	
				</c:forEach>
				
				<!-- 显示尾页 -->
				<c:if test="${pageBean.pNum == pageBean.totalPageNum}">
					下一页 尾页
				</c:if>
				<c:if test="${pageBean.pNum != pageBean.totalPageNum}">
				<li>	<a href="${pageContext.request.contextPath }/findByPageNotesName.action?username=${pageBean.name}&pNum=${pageBean.pNum + 1 }">下一页</a></li>
				<li>	<a href="${pageContext.request.contextPath }/findByPageNotesName.action?username=${pageBean.name}&pNum=${pageBean.totalPageNum}">尾页</a></li>
				</c:if>
			
											<li><input type="text" id="pNum" size="2" /><input
												type="button" value="go" onclick="jump();" /></li>

										</ul>
									</div>
								</div>
							</form>
						</c:if>
						<table>
							<tr>
								<td colspan="7"><input type="submit" value="删除选中信息" /></td>
							</tr>
						</table>
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
				