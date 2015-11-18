<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/tags" prefix="date"%>
<jsp:include page="../Header.jsp">
	<jsp:param name="page_tag" value="schoolSide" />
</jsp:include>

<!DOCTYPE html>
<html lang="en">
<head>
<title>留言墙</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/us.css" />
<link rel="stylesheet" href="css/us-ui.css" />
<link rel="stylesheet" href="css/us-private.css" />
<link rel="stylesheet" href="font-awesome/css/font-awesome.css" />

</head>

<body>

	<!-- content start-->
	<div id="content" class="container">
		<div
			class="container col-xs-12 col-sm-12 col-md-8 col-lg-8 g-content-left">
			<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 note-left">
				<ul class="users">
					<c:forEach items="${notesExtend1}" var="item">
						<li><a href="#"> <img
								src="${pageContext.request.contextPath}${item.avator }"
								width="40" height="40" /></a></li>

					</c:forEach>
				</ul>
			</div>
			<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10 note-right">
				<ul class="notes">
					<c:forEach items="${ notesExtend1}" var="item">
						<li><span class="point"></span>
							<div class="msg">
								<span class="triangle"></span>
								<div>
									<p class="content">${item.content}</p>
									<p class="timer">
										<strong><date:date value="${item.ncreated }" /></strong>
									</p>
								</div>
							</div></li>

					</c:forEach>

				</ul>
			</div>

		
			<form action="${pageContext.request.contextPath }/insertNotes.action" method="post">
				<div class="row note-write-wrap">
					<div class="note-title">
						<h5>我来留言</h5>
					</div>
					<div class="note-write clearfix">
						<textarea name="content" id="input" class="form-control" rows="3"
							required="required"></textarea>
				<button type="submit" class="btn btn-default us-btn pull-right">提交留言</button>
					</div>
				</div>
			</form>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
			<ul class="share clearfix">
				<li><a href="#"></a></li>
				<li style="background-position:-73px;"><a href="#"></a></li>
				<li style="background-position:-144px;"><a href="#"></a></li>
				<li style="background-position:-210px;"><a href="#"></a></li>
			</ul>

		</div>
	</div>

	<!-- content end -->

	<!--Footer-part-->
	<jsp:include page="../Foot.jsp"></jsp:include>
</body>

</html>
