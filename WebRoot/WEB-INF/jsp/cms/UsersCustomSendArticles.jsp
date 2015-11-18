<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tags" prefix="date"%>
<%@page import="com.us.po.PageBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已发过的文章</title>
<script type="text/javascript">
	function jump() {
		//获得用户输入页码
		var pNum = document.getElementById("pNum").value;
		location.href = "${pageContext.request.contextPath }/users/findArticlesByUid.action?pNum="
				+ pNum;

	}
</script>

</head>
<body>
<a href="${pageContext.request.contextPath }/users/schoolSide.action">首页</a>
<a href="${pageContext.request.contextPath }/users/findReputationBusinessman.action">口碑商</a>
<a href="${pageContext.request.contextPath }/users/findThenlife.action">便生活</a>
<a href="${pageContext.request.contextPath }/users/findActivity.action">有活动</a>
<p/>
	&nbsp;
	<!-- 显示list数据 -->
	<c:if test="${empty pageBean.articlesCustoms}">
		<h1>没有信息</h1>
	</c:if>
	<c:if test="${not empty pageBean.articlesCustoms}">
		<table width="70%">
		
			<c:forEach items="${pageBean.articlesCustoms}" var="articlesCustom">
				<tr>
				   <td><a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">标题 &nbsp;&nbsp;&nbsp;
				    ${articlesCustom.title } </a></td>
				</tr>
				<tr>
				<td>${articlesCustom.slug }</td>
				</tr>
				<tr>
				 <td>作者
				  &nbsp;${articlesCustom.author }
				 <date:date value ="${articlesCustom.created}"/>&nbsp;
				     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/usersupdatefindArticlesCustomByAid.action?aid=${articlesCustom.aid}">修改</a>&nbsp;&nbsp;&nbsp;
				   </td>
				   
			</c:forEach>
			<tr>
				<td colspan="9" align="center">
					<!-- 显示首页 --> <c:if test="${pageBean.pNum==1}">
                                           首页      上一页
               </c:if> <c:if test="${pageBean.pNum!=1}">
						<a
							href="${pageContext.request.contextPath }/users/findArticlesByUid.action?pNum=1">首页</a>
						<a
							href="${pageContext.request.contextPath }/users/findArticlesByUid.action?pNum=${pageBean.pNum-1 }">上一页</a>
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
								href="${pageContext.request.contextPath }/users/findArticlesByUid.action?pNum=${i} ">${i}
							</a>
						</c:if>

					</c:forEach> <!-- 显示尾页 --> <c:if test="${pageBean.pNum==pageBean.totalPageNum}">
                                                 下一页   尾页
               </c:if> <c:if test="${pageBean.pNum!=pageBean.totalPageNum}">

						<a
							href="${pageContext.request.contextPath }/users/findArticlesByUid.action?pNum=${pageBean.pNum+1}">下一页</a>
						<a
							href="${pageContext.request.contextPath }/users/findArticlesByUid.action?pNum=${ pageBean.totalPageNum}">尾页</a>
					</c:if> <input type="text" id="pNum" size="2" /><input type="button"
					value="go" onclick="jump();" />
				</td>

			</tr>


		</table>
	</c:if>

</body>
</html>