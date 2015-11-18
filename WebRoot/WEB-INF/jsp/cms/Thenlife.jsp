<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tags" prefix="date"%>
<%@page import="com.us.po.PageBean"%>
<jsp:include page="../Header.jsp">
<jsp:param name="page_tag" value="schoolSide"/>
<jsp:param name="page_tag2" value="Thenlife"/>
</jsp:include>

<title>便生活</title>
<script type="text/javascript">
	function jump() {
		//获得用户输入页码
		var pNum = document.getElementById("pNum").value;
		location.href = "${pageContext.request.contextPath }/users/findThenlife.action?pNum="
				+ pNum;

	}
</script>

<nav class="navbar navbar-default g-navbar-s" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex3-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
  
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="container collapse navbar-collapse navbar-ex3-collapse">
      <div class="container g-nav">
        <ul class="nav navbar-nav">
          <li><a href="${pageContext.request.contextPath }/users/schoolSide.action">首页</a></li>
          <li><a href="${pageContext.request.contextPath }/users/findReputationBusinessman.action">口碑商</a></li>
          <li class=bg1><a href="${pageContext.request.contextPath }/users/findThenlife.action">便生活</a></li>
          <li><a href="${pageContext.request.contextPath }/users/findActivity.action">有活动</a></li>
		  <li><a href="${pageContext.request.contextPath }/users/findArticlesByUid.action">我发的文章</a></li>
        </ul>
      </div>  
    </div><!-- /.navbar-collapse -->
  </nav>
<!-- content start-->
  <div id="content" class="container">
    <!-- head.html end -->
    <div class="row">
      <div class="container col-xs-12 col-sm-12 col-md-8 col-lg-8 g-content-left">
        <ol class="breadcrumb">
          <li>
            <a href="#">主页</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath }/users/schoolSide.action">校园周边</a>
          </li>
          <li>便生活</li>
        </ol>
        <div class="section-lg">
        <button type="button" class="btn btn-default us-btn g-post"><a href="${pageContext.request.contextPath }/usersAddArticles.action">我也要发</a></button>
          <div class="us-title">
            <h4 class="widget-title">便生活</h4>
          </div>
          <c:if test="${empty pageBean.articlesCustoms}">
		  <h1>便生活中没有信息</h1>
	      </c:if>
	      <c:if test="${not empty pageBean.articlesCustoms}">
	  <form action ="${pageContext.request.contextPath }/users/findArticlesByCondition.action?category=便生活" method="post">
      <!-- 选择条件查询字段 -->
      <select name = "conditionName">
      <option value="author">作者</option>
      <option value="title">标题</option>
      </select> 
      <input type="text" name="conditionValue" />
      <input type = "submit" value = "查询"/>   
	      <c:forEach items="${pageBean.articlesCustoms}" var="articlesCustom">
          <div class="section">
            <h5><a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">
				    ${articlesCustom.title } </a></h5>
            <p class="ifo"><span><date:date value ="${articlesCustom.created}"/></span>/<strong>${articlesCustom.views}</strong>人气/<a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">评论</a>
            <a  href="${pageContext.request.contextPath }/users/addPraise.action?aid=${articlesCustom.aid}&count=${articlesCustom.count}&category=便生活">点赞${articlesCustom.count }</a>
            </p>
            <p>${articlesCustom.slug}</p>
            
            <span class="user">${articlesCustom.author }</span>
          </div> 
          </c:forEach>
          
          <div class="us-pagination clearfix">
            <ul class="pagination">
              <tr>
				<td colspan="9" align="center">
					<!-- 显示首页 --> <c:if test="${pageBean.pNum==1}">
                                           首页      上一页
               </c:if> <c:if test="${pageBean.pNum!=1}">
						<a
							href="${pageContext.request.contextPath }/users/findReputationBusinessman.action?pNum=1">首页</a>
						<a
							href="${pageContext.request.contextPath }/users/findReputationBusinessman.action?pNum=${pageBean.pNum-1 }">上一页</a>
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
								href="${pageContext.request.contextPath }/users/findReputationBusinessman.action?pNum=${i} ">${i}
							</a>
						</c:if>

					</c:forEach> <!-- 显示尾页 --> <c:if test="${pageBean.pNum==pageBean.totalPageNum}">
                                                 下一页   尾页
               </c:if> <c:if test="${pageBean.pNum!=pageBean.totalPageNum}">

						<a
							href="${pageContext.request.contextPath }/users/findReputationBusinessman.action?pNum=${pageBean.pNum+1}">下一页</a>
						<a
							href="${pageContext.request.contextPath }/users/findReputationBusinessman.action?pNum=${ pageBean.totalPageNum}">尾页</a>
					</c:if> <input type="text" id="pNum" size="2" /><input type="button"
					value="go" onclick="jump();" />
				</td>

			</tr>
            </ul>
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
        <div class="board">
          <h3>排行榜</h3>
          <ul class="list-group board-list">
            <li class="list-group-item"><span class="list-num">1</span><img src="img/great.png" height="18" width="19"><strong>12</strong>Item 1</li>
            <li class="list-group-item"><span class="list-num">2</span><img src="img/great.png" height="18" width="19"><strong>12</strong>Item 2</li>
            <li class="list-group-item"><span class="list-num">3</span><img src="img/great.png" height="18" width="19"><strong>12</strong>Item 3</li>
            <li class="list-group-item"><span class="list-num active">4</span><img src="img/great.png" height="18" width="19"><strong>12</strong>Item 4</li>
          </ul>
        </div>
      </div>
    </div>
    <!-- foot.html start -->
  </div>
  <!-- content end -->
  <jsp:include page="../Foot.jsp"></jsp:include>
   </c:if>
</body>
</html>