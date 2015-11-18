<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tags" prefix="date"%>
<%@page import="com.us.po.SchoolLife"%>
<jsp:include page="WEB-INF/jsp/Header.jsp">
<jsp:param name="page_tag" value="schoolLife"/>
</jsp:include>
  <title>校园生活</title>
 
  
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
          <li class="bg1"><a href="${pageContext.request.contextPath }/users/schoolLife.action">首页</a></li>
          <li><a href="${pageContext.request.contextPath }/users/findSchoolSituation.action">校园风云</a></li>
          <li><a href="${pageContext.request.contextPath }/users/findSchoolGossip.action">校园八卦</a></li>
          <li><a href="${pageContext.request.contextPath }/users/findCampusConsultation.action">校园资讯</a></li>
          <li><a href="${pageContext.request.contextPath }/users/findSchoolActivities.action">校园活动</a></li>
        </ul>
      </div>  
    </div><!-- /.navbar-collapse -->
  </nav>
  <!-- navbar end -->

  <!-- content start-->
  <div id="content" class="container">
    <!-- head.html end -->
    <div class="row">
      <div class="container col-xs-12 col-sm-12 col-md-8 col-lg-8 g-content-left">
        <ol class="breadcrumb">
          <li>
            <a href="#">首页</a>
          </li>
          <li>校园生活</li>
        </ol>
        <div class="section-md">
          <div class="us-title">
            <h4 class="widget-title">校园风云</h4>
            <a class="more">more<span class="icon icon-circle-arrow-right"></span></a>
          </div>
          <c:if test="${empty schoolLife.schoolSituations}">
		   <h1>校园风云中没有信息</h1>
	       </c:if>
	       <c:if test="${not empty schoolLife.schoolSituations}">
	       <c:forEach items="${schoolLife.schoolSituations}" var="articlesCustom">
          <div class="section">
            <h5><a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">
				    ${articlesCustom.title } </a></h5>
            <p class="ifo"><span><date:date value ="${articlesCustom.created}"/></span>/<strong>${articlesCustom.views}</strong>人气/<em>9</em>
            <a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">评论</a>
            
            </p>
            <p>${articlesCustom.slug }</p>
          </div>
          </c:forEach>
           </c:if>
        </div>
        
       
        <div class="section-md">
          <div class="us-title">
            <h4 class="widget-title">校园八卦</h4>
            <a class="more">more<span class="icon icon-circle-arrow-right"></span></a>
          </div>
          <c:if test="${empty schoolLife.schoolGossips}">
		  <h1>校园八卦中没有信息</h1>
	      </c:if>
	
	      <c:if test="${not empty schoolLife.schoolGossips}">
	      <c:forEach items="${schoolLife.schoolGossips}" var="articlesCustom">
          <div class="section">
            <h5><a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">
				    ${articlesCustom.title } </a></h5>
            <p class="ifo"><span> <date:date value ="${articlesCustom.created}"/></span>/<strong>${articlesCustom.views}</strong>人气/
            <a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">评论</a>
            <p>${articlesCustom.slug }</p>
          </div>
          </c:forEach>
           </c:if>
        </div>
        <div class="section-md">
          <div class="us-title">
            <h4 class="widget-title">校园资讯</h4>
            <a class="more">more<span class="icon icon-circle-arrow-right"></span></a>
          </div>
          <c:if test="${empty schoolLife.campusConsultations}">
		<h1>校园资讯中没有信息</h1>
	    </c:if>
	    <c:if test="${not empty schoolLife.campusConsultations}">
	    <c:forEach items="${schoolLife.campusConsultations}" var="articlesCustom">
         <div class="section">
            <h5><a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">
				    ${articlesCustom.title } </a></h5>
            <p class="ifo"><span> <date:date value ="${articlesCustom.created}"/></span>/<strong>${articlesCustom.views}</strong>人气/
            <a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">评论</a>
            <p>${articlesCustom.slug }</p>
          </div>
          </c:forEach>
          </c:if>
        </div>
        <div class="section-md">
          <div class="us-title">
            <h4 class="widget-title">校园活动</h4>
            <a class="more">more<span class="icon icon-circle-arrow-right"></span></a>
          </div>
          <c:if test="${empty schoolLife.schoolActivities}">
		<h1>校园活动中没有信息</h1>
	    </c:if>	
	    <c:if test="${not empty schoolLife.schoolActivities}">
	    <c:forEach items="${schoolLife.schoolActivities}" var="articlesCustom">
          <div class="section">
            <h5><a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">
				    ${articlesCustom.title } </a></h5>
            <p class="ifo"><span> <date:date value ="${articlesCustom.created}"/></span>/<strong>${articlesCustom.views}</strong>人气/
            <a href="${pageContext.request.contextPath }/usersfindArticlesCustomByAid.action?aid=${articlesCustom.aid}">评论</a>
            <p>${articlesCustom.slug }</p>
          </div>
          </c:forEach>
          </c:if>
        </div>
      </div>
      <div class="container col-xs-12 col-sm-12 col-md-4 col-lg-4">
        <ul class="share clearfix">
          <li><a href="#"></a></li>
          <li style="background-position:-73px;"><a href="#"></a></li>
          <li style="background-position:-144px;"><a href="#"></a></li>
          <li style="background-position:-210px;"><a href="#"></a></li>
        </ul>
        <div class="option-lg">
          <h4 class="widget-title">本周热门</h4>
          <div class="media option">
            <div class="media-left">
              <a href="#">
                <img width="64" height="64" alt="..." src="img/slide-1.png" class="media-object">
              </a>
            </div>
            <div class="media-body">
              <h5 class="media-heading">Media heading</h5>
              <p>人气<span>209</span></p>
            </div>
          </div>
          <div class="media option">
            <div class="media-left">
              <a href="#">
                <img width="64" height="64" alt="..." src="img/slide-1.png" class="media-object">
              </a>
            </div>
            <div class="media-body">
              <h5 class="media-heading">Media heading</h5>
              <p>人气<span>209</span></p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- foot.html start -->
  </div>
  <!-- content end -->

  <jsp:include page="WEB-INF/jsp/Foot.jsp"></jsp:include>
</body>

</html>
<!-- foot.html end -->
