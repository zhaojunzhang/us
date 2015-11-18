<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.us.po.PageBean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function jump() {
		// 获得用户输入页码
		var pNum = document.getElementById("pNum").value;
		location.href = "${pageContext.request.contextPath }/findByPage.action?pNum="
				+ pNum;
	}
</script>
</head>
<body>
	<form action=" ${pageContext.request.contextPath }/findProductsByName.action"
		method="post">
		<table width="" border=1>
			<tr>
				<td><h3>商品1</h3></td>
				<td>输入名称<input type="text" name="name">
				</td>
				<td colspan="9"><input type="submit" value="查询" /></td>
			</tr>
		</table>
	</form>
	<!-- 显示list数据 -->
	<c:if test="${empty pageBean.productsExtend}">
		<h1>该页无任何数据！</h1>
	</c:if>
	<c:if test="${not empty pageBean.productsExtend}">
		<h1>分页数据！</h1>
		<table border="1" width="100%">
			<tr>
				<td>名称</td>
				<td>售价</td>
				<td>数量</td>
				<td>描述</td>
				<td>浏览次数</td>
				<td>评论次数</td>
				<td>状态码</td>
				<td>图片</td>
				<td colspan="3">查看详情</td>

			</tr>
			<c:forEach items="${pageBean.productsExtend }" var="item">
				<tr>
					<td>${item.name }</td>
					<td>${item.soldprice }</td>
					<td>${item.quantity }</td>
					<td>${item.pdescription }</td>
					<td>${item.views }</td>
					<td>${item.commentcount }</td>
					<td>${item.status }</td>

					<td><a
						href="${pageContext.request.contextPath }/findImagesExtendByPid.action?pid=${item.pid}">查看</a></td>
					<td><a
						href="${pageContext.request.contextPath }/findProductsById.action?pid=${item.pid}">修改</a>
					</td>
					<td><a
						href="${pageContext.request.contextPath }/deleteProductsById.action?pid=${item.pid}">删除</a>
					</td>
				</tr>

			</c:forEach>
			<tr>
				<td><input type="button" value="返回" onclick="history.go(-1);">
				</td>

				<td colspan="9" align="center">
					<!-- 显示首页 --> <c:if test="${pageBean.pNum == 1}">
					首页  上一页
				</c:if> <c:if test="${pageBean.pNum != 1}">
						<a
							href="${pageContext.request.contextPath }/findByPage.action?pNum=1">首页</a>
						<a
							href="${pageContext.request.contextPath }/findByPage.action?pNum=${pageBean.pNum-1 }">上一页</a>
					</c:if> <!-- 当前页为中心前后各显示10页 --> <c:set var="begin" value="1" scope="page" />
					<c:set var="end" value="${pageBean.totalPageNum}" scope="page" />

					<!-- 判断前面有没有10页 --> <c:if test="${pageBean.pNum-10>0}">
						<c:set var="begin" value="${pageBean.pNum-10}" scope="page" />
					</c:if> <!-- 判断后面有没有10页 --> <c:if
						test="${pageBean.pNum+10 < pageBean.totalPageNum}">
						<c:set var="end" value="${pageBean.pNum + 10}" scope="page" />
					</c:if> <!-- 当前页不显示链接 --> <c:forEach begin="${begin}" end="${end}" var="i">
						<c:if test="${pageBean.pNum==i}">
						${i}
					</c:if>
						<c:if test="${pageBean.pNum!=i}">
							<a
								href="${pageContext.request.contextPath }/findByPage.action?pNum=${i }">${i
								} </a>
						</c:if>
					</c:forEach> <!-- 显示尾页 --> <c:if test="${pageBean.pNum==pageBean.totalPageNum}">
					下一页 尾页
				</c:if> <c:if test="${pageBean.pNum!=pageBean.totalPageNum}">
						<a
							href="${pageContext.request.contextPath }/findByPage.action?pNum=${pageBean.pNum + 1 }">下一页</a>
						<a
							href="${pageContext.request.contextPath }/findByPage.action?pNum=${pageBean.totalPageNum}">尾页</a>
					</c:if> <input type="text" id="pNum" size="2" /><input type="button"
					value="go" onclick="jump();" />
				</td>
			</tr>
		</table>
	</c:if>
</body>
</html>