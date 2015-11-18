<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tags" prefix="date"%>
<%@page import="com.us.po.PageBean"%>
<jsp:include page="../Header.jsp">
	<jsp:param name="page_tag" value="null" />
</jsp:include>
<title>文章详情</title>

<c:if test="${empty articlesCustom}">
<h2>您查询文章不存在</h2>
</c:if>
<c:if test="${not empty articlesCustom}">
<!-- content start-->
<div id="content" class="container">
	<!-- head.html end -->
	<div class="row">
		<div
			class="container col-xs-12 col-sm-12 col-md-8 col-lg-8 g-content-left">
			<ol class="breadcrumb">
				<li><a href="#">首页</a></li>

				<li>文章详情</li>
			</ol>
			<div class="section-lg">
				<div class="aiticle-title clearfix">

					<img
						src="${pageContext.request.contextPath }${usersCustom.avator }"
						height="50" width="50" class="img-responsive g-avatar pull-left"
						alt="Image"> <span class="user pull-left">${articlesCustom.author}</span>
				</div>
				<div class="section article-detail">

					<h4>${articlesCustom.title}</h4>
					<p class="ifo">
						<span><date:date value="${articlesCustom.created}" /></span>/<strong>${articlesCustom.views}</strong>人气/<em>${pageBean.totalRecordNum
							}</em><a class="aiticle-critic" href="">评论</a>
					</p>
					<p style="margin-top: 10px;">>${articlesCustom.content}</p>
				</div>
			</div>
			<div class="row critic">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="critic-title">
						<h5>发表我的评论</h5>
					</div>
					<div class="critic-write clearfix">
						<form
							action="${pageContext.request.contextPath }/users/addCommentsByAid.action"
							method="post">
							<table>
								<tr style="display: none;">
									<td>文章aid</td>
									<td><input type="text" name="aid"
										value="${articlesCustom.aid}" /></td>
								</tr>
								<tr style="display: none;">
									<td>作者</td>
									<td><input type="text" name="author"
										value="${usersCustom.nickname }" readonly="true" /></td>
								</tr>
								<tr>
									<textarea name="text" id="input" class="form-control" rows="3" required="required"></textarea>
									
								</tr>
								<tr>
									<td><input type="submit" value="添加评论" class="btn btn-default us-btn pull-right"></td>
								</tr>
							</table>
						</form>
					</div>
					<p class="critic-num">
						<span>${pageBean.totalRecordNum }</span>个小伙伴在吐槽
					</p>
					<hr>
					<div class="critic-content">
					 <c:forEach items="${pageBean.commentsCustoms}" var="commentsCustom">
						<div class="option clearfix">
							<div class="user">
								<img src="${pageContext.request.contextPath }${commentsCustom.avator }" height="50" width="50">
							</div>
							<div class="msg">
								<div class="dialogue"></div>
								<p>${commentsCustom.text}</p>
								<p>
									<span>${commentsCustom.author }</span><strong><date:date value ="${commentsCustom.created}"/></strong>
								</p>
							</div>
						</div>
						</c:forEach>
						 <tr>
				<td colspan="9" align="center">
					<!-- 显示首页 --> <c:if test="${pageBean.pNum==1}">
                                           首页      上一页
               </c:if> <c:if test="${pageBean.pNum!=1}">
						<a
							href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?pNum=1&aid=${articlesCustom.aid}">首页</a>
						<a
							href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?pNum=${pageBean.pNum-1 }&aid=${articlesCustom.aid}">上一页</a>
					</c:if> <!-- 当前页为中心前后各显示10页 --> <c:set var="begin" value="1" scope="page" />
					<c:set var="end" value="${pageBean.totalPageNum}" scope="page" /> <!-- 判断前面有没有10页 -->
					<c:if test="${pageBean.pNum-10>0}">
						<c:set var="begin" value="${pageBean.pNum-10}" scope="page" />
					</c:if> <!-- 判断后面有没有10页 --> <c:if
						test="${pageBean.pNum+10<pageBean.totalPageNum}">
						<c:set var="end" value="${pageBean.pNum+10}" scope="page" />
					</c:if> <!-- 当前页不显示链接 --> <c:forEach begin="${begin}" end="${end}" var="i">
						<c:if test="${pageBean.pNum==i}">
               ${i}
               </c:if>
						<c:if test="${pageBean.pNum!=i}">
							<a
								href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?pNum=${i}&aid=${articlesCustom.aid} ">${i}
							</a>
						</c:if>

					</c:forEach> <!-- 显示尾页 --> <c:if test="${pageBean.pNum==pageBean.totalPageNum}">
                                                 下一页   尾页
               </c:if> <c:if test="${pageBean.pNum!=pageBean.totalPageNum}">

						<a
							href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?pNum=${pageBean.pNum+1}&aid=${articlesCustom.aid}">下一页</a>
						<a
							href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?pNum=${pageBean.totalPageNum}&aid=${articlesCustom.aid}">尾页</a>
					</c:if> <input type="text" id="pNum" size="2" /><input type="button"
					value="go" onclick="jump();" />
				</td>

			</tr>
					</div>
				</div>
			</div>
		</div>
		<div class="container col-xs-12 col-sm-12 col-md-4 col-lg-4">
			<ul class="share clearfix">
				<li><a href="#"></a></li>
				<li style="background-position:-73px;"><a href="#"></a></li>
				<li style="background-position:-144px;"><a href="#"></a></li>
				<li style="background-position:-210px;"><a href="#"></a></li>
			</ul>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<h5 class="widget-title">主题</h5>
				</div>
			</div>
		</div>
	</div>
	<!-- foot.html start-->
</div>
<!-- content end -->

<jsp:include page="../Foot.jsp"></jsp:include>
</c:if>
</body>
</html>
<!--foot.html end-->