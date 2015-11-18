<%@page import="com.us.po.ProductsExtend"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/tags" prefix="date"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../Header.jsp">
<jsp:param name="page_tag" value="schoolSide"/>
</jsp:include>


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
	<h3 style="color: red">${msg}</h3>
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
				
				<h3>冻结商品期待榜</h3>
			</div>
			<c:forEach items="${productsList}" var="list">

			<!-- 闲互送的商品 -->
				<c:if test="${list.type=='闲互送' }">
					<!-- 取出闲互送的商品 -->
					<div class="option clearfix">
						<div class="row goods-img">
							<div class="col-xs-6 col-md-4">
							<!-- 遍历图片 -->
							<c:forEach items="${list.imagesExtend}" var="imagesExtend">
							<a href="" >
								<img src="${pageContext.request.contextPath}${imagesExtend.url}" width="100" height="100">
							</a>
							</c:forEach>
							</div>
						</div>

						<p>摘要：${list.summary }</p>
						<p> ${list.type } </p>
						<span><img src="${pageContext.request.contextPath}${list.avator}" alt=""width="30" height="30">${list.username}</span>
						<p class="grab-time">
							上线时间：<strong><date:date value="${list.pcreated }" /></strong>
						</p>
			
					</div>
				</c:if>


			</c:forEach>
			<hr>
			 <h4>精品置换</h4>
			<c:forEach items="${productsExchange}" var="list">

			<!-- 闲互换的商品 -->
				<c:if test="${list.type=='闲互换' }">
					<!-- 取出闲互送的商品 -->
					<div class="section clearfix">
					
						<div class="row goods-img">
							<div class="col-xs-6 col-md-4">
								<!-- 遍历图片 -->
							<c:forEach items="${list.imagesExtend}" var="imagesExtend">
							<a href="" >
								<img src="${pageContext.request.contextPath}${imagesExtend.url}" width="100" height="100">
							</a>
							</c:forEach>
							</div>
						</div>

						<p>摘要：${list.summary }</p>
						<p> ${item.type } </p>
						<span><img src="${pageContext.request.contextPath}${list.avator}" alt=""width="30" height="30">${list.username}</span>
						<p class="grab-time">
							上线时间：<strong><date:date value="${list.pcreated }" /></strong>
						</p>
						   <a href="#" class="pull-right btn btn-success grab">抢</a>
						<p>${list.type}</p>
					</div>
				</c:if>


			</c:forEach>
	</div>
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

	<!-- content end -->

	<!--Footer-part-->
	<jsp:include page="../Foot.jsp"></jsp:include>

</body>

</html>
		