<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tags" prefix="date"%>
<%@page import="com.us.po.SchoolLife"%>
<jsp:include page="WEB-INF/jsp/Header.jsp">
<jsp:param name="page_tag" value="schoolSide"/>
</jsp:include>
  <!-- header end -->
<title>校园周边</title>
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
          <li class=bg1><a href="${pageContext.request.contextPath }/users/schoolSide.action">首页</a></li>
          <li><a href="${pageContext.request.contextPath }/users/findReputationBusinessman.action">口碑商</a></li>
          <li><a href="${pageContext.request.contextPath }/users/findThenlife.action">便生活</a></li>
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
          <li>首页</li>
        </ol>
        <div class="section-md">
          <div class="us-title">
            <h4 class="widget-title">便生活</h4>
			<c:if test="${empty schoolLife.thenlifes}">
		     <h1>便生活中没有信息</h1>
	        </c:if>
			<c:if test="${not empty schoolLife.thenlifes}">
            <a class="more">more<span class="icon icon-circle-arrow-right"></span></a>
          </div>
		  <c:forEach items="${schoolLife.thenlifes}" var="articlesCustom">
          <div class="section">
            <h5><a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">
				    ${articlesCustom.title } </a></h5>
            <p class="ifo"><span> <date:date value ="${articlesCustom.created}"/></span>/<strong>${articlesCustom.views}</strong>人气/<em>9</em><a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">评论</a></p>
            <p>${articlesCustom.slug}</p>
          </div>
		  </c:forEach>
        </div>
		 </c:if>
        <div class="section-md">
          <div class="us-title">
            <h4 class="widget-title">有活动</h4>
			<c:if test="${empty schoolLife.activities}">
		    <h1>有活动中没有信息</h1>
	        </c:if> 
         	<c:if test="${not empty schoolLife.activities}">
            <a class="more">more<span class="icon icon-circle-arrow-right"></span></a>
          </div>
		  <c:forEach items="${schoolLife.activities}" var="articlesCustom">
          <div class="section">
            <h5><a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">
				    ${articlesCustom.title } </a></h5>
            <p class="ifo"><span><date:date value ="${articlesCustom.created}"/></span>/<strong>${articlesCustom.views}</strong>人气/<a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">评论</a></p>
            <p>${articlesCustom.slug}</p>
          </div>
		  </c:forEach>
        </div>
		</c:if> 
      </div>
      <div class="container col-xs-12 col-sm-12 col-md-4 col-lg-4">
        <ul class="share clearfix">
          <li><a href="#"></a></li>
          <li style="background-position:-73px;"><a href="#"></a></li>
          <li style="background-position:-144px;"><a href="#"></a></li>
          <li style="background-position:-210px;"><a href="#"></a></li>
        </ul>
        <div class="option-lg">
          <h4 class="widget-title">口碑商</h4>
		   <c:if test="${empty schoolLife.reputationBusinessman}">
		   <h1>口碑商中没有信息</h1>
	       </c:if>
	       <c:if test="${not empty schoolLife.reputationBusinessman}">
		   <c:forEach items="${schoolLife.reputationBusinessman}" var="articlesCustom">
          <div class="option clearfix">

            <h5><a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">
				    ${articlesCustom.title } </a></h5>
					 <p class="ifo"><span><date:date value ="${articlesCustom.created}"/></span>/<strong>${articlesCustom.views}</strong>人气/<a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">评论</a></p>
            <p>${articlesCustom.slug }</p>
          </div>
          </c:forEach>
        </div>
		</c:if>
      </div>
    </div>
  <!-- foot.html start -->
  </div>
  <!-- content end -->

  <!--Footer-part-->
	<jsp:include page="WEB-INF/jsp/Foot.jsp"></jsp:include>
</body>

</html>
<!-- foot.html end -->