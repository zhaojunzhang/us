<%@page import="com.us.po.ProductsExtend"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/tags" prefix="date"%>
<jsp:include page="../Header.jsp">

<jsp:param name="page_tag" value="schoolSide"/>
</jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>校园易购</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/us.css" />
<link rel="stylesheet" href="css/us-ui.css" />
<link rel="stylesheet" href="css/us-private.css" />
<link rel="stylesheet" href="font-awesome/css/font-awesome.css" />
</head>
<body>
	
	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="container collapse navbar-collapse navbar-ex3-collapse">
		<div class="container g-nav">
			<ul class="nav navbar-nav">
				<li class="bg1"><a
					href="${pageContext.request.contextPath }/findAllProducts.action?status=1">首页</a></li>
				<li><a
					href="${pageContext.request.contextPath }/findShangchengByType.action?type=闲互送&pNum=1&status=1">闲互送</a></li>
				<li><a
					href="${pageContext.request.contextPath }/findShangchengByType.action?type=闲互换&pNum=1&status=1">闲互换</a></li>
				<li><a
					href="${pageContext.request.contextPath }/findShangchengByType.action?type=聚便宜&pNum=1&status=1">聚便宜</a></li>
			</ul>
		</div>
	</div>
	<!-- content start-->
	<div id="content" class="container">
		<div
			class="container col-xs-12 col-sm-12 col-md-8 col-lg-8 g-content-left">
			<div class="section-title">
				<h3>闲互送商品</h3>
			</div>
			<c:forEach items="${pageBean.productsExtend}" var="item" >
			<div class="option clearfix">
						<div class="row goods-img">
							<div class="col-xs-6 col-md-4">
				<c:forEach items="${item.imagesExtend}" var="imagesExtend">
		
				<a href="" >
					<img src="${pageContext.request.contextPath}${imagesExtend.url}" width="100" height="100">
							</a>
				</c:forEach>
				</div>
				</div>
			     <p>摘要：${item.summary }</p>
					<p>描述：${item.pdescription}</p>
					<span><img src="${pageContext.request.contextPath}${item.avator}" alt=""width="30" height="30">${item.username}</span>
					<p class="grab-time">
						上线时间：<strong><date:date value="${item.pcreated }" /></strong>
					</p>
					<a
						href="${pageContext.request.contextPath }/insertOrderitemsSuccess.action?pid=${item.pid}"
						class="pull-right btn btn-success grab">抢</a>
				</div>		
			</c:forEach>

			<!-- 显示首页 -->
			<c:if test="${pageBean.pNum == 1}">
					首页  上一页
				</c:if>
			<c:if test="${pageBean.pNum != 1}">
				<a
					href="${pageContext.request.contextPath }/findShangchengByType.action?type=${pageBean.type}&status=${pageBean.status}&pNum=1">首页</a>
				<a
					href="${pageContext.request.contextPath }/findShangchengByType.action?type=${pageBean.type}&status=${pageBean.status}&pNum=${pageBean.pNum-1 }">上一页</a>
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
						${i}
					</c:if>
				<c:if test="${pageBean.pNum!=i}">
					<a
						href="${pageContext.request.contextPath }/findShangchengByType.action?type=${pageBean.type}&status=${pageBean.status}&pNum=${i }">${i
						} </a>
				</c:if>

			</c:forEach>
			<c:if test="${pageBean.pNum==pageBean.totalPageNum}">
					下一页 尾页
				</c:if>
			<c:if test="${pageBean.pNum!=pageBean.totalPageNum}">
				<a
					href="${pageContext.request.contextPath }/findShangchengByType.action?type=${pageBean.type}&status=${pageBean.status}&pNum=${pageBean.pNum + 1 }">下一页</a>
				<a
					href="${pageContext.request.contextPath }/findShangchengByType.action?type=${pageBean.type}&status=${pageBean.status}&pNum=${pageBean.totalPageNum}">尾页</a>
			</c:if>
			<input type="text" id="pNum" size="2" /><input type="button"
				value="go" onclick="jump();" />
			
		</div>

	<!-- content end -->
	<div class="container col-xs-12 col-sm-12 col-md-4 col-lg-4">
			<ul class="share clearfix">
				<li><a href="#"></a></li>
				<li style="background-position:-73px;"><a href="#"></a></li>
				<li style="background-position:-144px;"><a href="#"></a></li>
				<li style="background-position:-210px;"><a href="#"></a></li>
			</ul>
			<div class="option clearfix">
				<h3>聚便宜福利热抢区</h3>
				   <c:forEach items="${productsCheap}" var="list">

				<c:if test="${list.type=='聚便宜' }">
				<div class="row goods-img">
					<div class="col-xs-6 col-md-6">
							<c:forEach items="${list.imagesExtend}" var="imagesExtend">
							    <a href="" >
								<img src="${pageContext.request.contextPath}${imagesExtend.url}" width="100" height="100">
							   </a>
							</c:forEach>
					</div>
					
					</div>
				
					<p>摘要：${list.summary }</p>
						<span><img src="${pageContext.request.contextPath}${list.avator}" alt=""width="30" height="30">${list.username}</span>
						  	<button type="button" class="btn btn-default pick">捡福利</button>
						<p class="grab-time">
							上线时间：<strong><date:date value="${list.pcreated }" /></strong>
						</p>
				         
                           </c:if>
			       </c:forEach>
			       </div>
			</div>
		</div>

	<!--Footer-part-->
	<jsp:include page="../Foot.jsp"></jsp:include>

</body>

</html>
