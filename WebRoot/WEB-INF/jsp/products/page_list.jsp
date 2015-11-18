<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.us.po.PageBean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link rel="stylesheet" href="css/select2.css" />
<link rel="stylesheet" href="font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<script type="text/javascript">
	function jump() {
		// 获得用户输入页码fanxmistic
		var pNum = document.getElementById("pNum").value;
		location.href = "${pageContext.request.contextPath }/findByPage.action?pNum="
				+ pNum;
	}
	function confirmDel(id) {
		var isConfirm = window.confirm("确认删除吗？想好了吗？");
		if (isConfirm) {
			// 提交删除链接
			//location.href="/delCustomer?id="+id;
			location.href = "${pageContext.request.contextPath }/deleteProductsById.action?pid="
					+ id;
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
	
	<jsp:include page="../HeaderBack.jsp"></jsp:include>
	<!--main-container-part-->
	<div id="content">
		<!--breadcrumbs面包屑导航-->
		<div id="content-header">
			<div id="breadcrumb">
				<a href="index.html" title="返回主页" class="tip-bottom"><i
					class="icon-home"></i> Home</a><a class="current"
					href="goods-approved.html">商品</a>
			</div>

		</div>
		
		<!--End-breadcrumbs-->
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
													
							
								<c:if test="${status==1}">		
												
									<jsp:include page="../productPass.jsp"></jsp:include>
								</c:if>
								<c:if test="${status==0}">								
									<jsp:include page="../productUnCheck.jsp"></jsp:include>
								</c:if>
								<c:if test="${status==2}">	
									<jsp:include page="../productUnpass.jsp"></jsp:include>
								</c:if>
						
							
						</div>
						<form
							action="${pageContext.request.contextPath }/findByPageByName.action?pNum=1&status=${pageBean.status}"
							method="post" role="form">
							<div class="form-group">
								<!-- 选择条件查询字段 -->
								<select name="conditionName" id="input" class="form-control"
									required="required">
									<option value="请选择">--请选择--</option>
									<option value="name">商品名称</option>
									<option value="type">挂货类型</option>
								</select> <input type="text" class="form-control" id=""
									name="conditionValue" placeholder="输入查询内容"> <input
									type="submit" class="btn btn-primary" value="查询" />
							</div>
						</form>


						<!-- 显示list数据 -->
						<c:if test="${empty pageBean.productsExtend}">
							<h1>无无审查的商品！</h1>
						</c:if>
						<c:if test="${not empty pageBean.productsExtend}">
							<div class="widget-content nopadding">
								<table class="table table-bordered data-table">
									<thead>
										<tr>
											<th>名称</th>
											<th>挂货类型</th>
											<th>售价</th>
											<th>数量</th>
											<th>摘要</th>
											<th>描述</th>
											<th>状态码</th>
											<th colspan="3">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pageBean.productsExtend }" var="item">
											<tr class="gradeX">
												<td>${item.name }</td>
												<td>${item.type }</td>
												<td>${item.soldprice }</td>
												<td>${item.quantity }</td>
												<td>${item.summary}</td>
												<td>${item.pdescription }</td>
												<td>${item.status }</td>

												<td><a
													href="${pageContext.request.contextPath }/productsDetail.action?pid=${item.pid}">查看</a></td>
												<td><a
													href="${pageContext.request.contextPath }/findProductsById.action?pid=${item.pid}">修改</a>
												</td>
												<td><a href="javascript:void(0);"
													onclick="confirmDel('${item.pid}');">删除</a></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="pagination alternate text-center">
									<ul>
										<!-- 显示首页 -->
										<c:if test="${pageBean.pNum == 1}">
											<li><a href="javascript:;"> 首页 </a></li>
											<li><a href="javascript:;">上一页 </a></li>
										</c:if>
										<c:if test="${pageBean.pNum != 1}">
											<li><a
												href="${pageContext.request.contextPath }/findProductsByPage.action?status=${pageBean.status}&pNum=1">首页</a></li>
											<li><a
												href="${pageContext.request.contextPath }/findProductsByPage.action?status=${pageBean.status}&pNum=${pageBean.pNum-1 }">上一页</a>
											</li>
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
												<li><a href="javascript:;"> ${i} </a></li>
											</c:if>
											<c:if test="${pageBean.pNum!=i}">
												<li><a
													href="${pageContext.request.contextPath }/findProductsByPage.action?status=${pageBean.status}&pNum=${i }">${i
														} </a></li>
											</c:if>
										</c:forEach>
										<!-- 显示尾页 -->
										<c:if test="${pageBean.pNum==pageBean.totalPageNum}">
											<li><a href="javascript:;"> 下一页 </a></li>
											<li><a href="javascript:;"> 尾页 </a></li>
										</c:if>
										<c:if test="${pageBean.pNum!=pageBean.totalPageNum}">
											<li><a
												href="${pageContext.request.contextPath }/findProductsByPage.action?status=${pageBean.status}&pNum=${pageBean.pNum + 1 }">下一页</a>
											</li>
											<li><a
												href="${pageContext.request.contextPath }/findProductsByPage.action?status=${pageBean.status}&pNum=${pageBean.totalPageNum}">尾页</a>
											</li>
										</c:if>

									</ul>
									<input type="text" id="pNum" size="2" /><input type="button"
										value="go" onclick="jump();" />

								</div>
							</div>
						</c:if>
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

