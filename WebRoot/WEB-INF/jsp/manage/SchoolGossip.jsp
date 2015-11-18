<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tags" prefix="date"%>
<%@page import="com.us.po.PageBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>校园八卦</title>
<script type="text/javascript">
	function jump() {
		//获得用户输入页码
		var pNum = document.getElementById("pNum").value;
		location.href = "${pageContext.request.contextPath }/admins/findSchoolGossip.action?pNum="
				+ pNum;

	}
</script>

  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap-responsive.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/uniform.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/select2.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/matrix-style.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/matrix-media.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/font-awesome/css/font-awesome.css" />
</head>

<body>
  <!--Header-part-->
  <div id="header">
    <h3><a href="index.html">US后台管理系统</a></h3>
  </div>
  <!--close-Header-part-->
 <!--top-Header-menu-->
  <div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav">
      <li id="profile-messages"><a title="" href="#"><i class="icon icon-user"></i> <span class="text">欢迎用户:${adminsCustom.username}</span></a>
      </li>
      <li class=""><a title="" href="${pageContext.request.contextPath }/admininvalidate.jsp"><i class="icon icon-share-alt"></i> <span class="text">退出登录</span></a></li>
    </ul>
  </div>
  <!--close-top-Header-menu-->
  <!--sidebar-menu-->
  <div id="sidebar">
    <a href="#" class="visible-phone"><i class="icon icon-home"></i>menu</a>
    <ul>
      <li class="submenu"> <a href="javascript:;"><i class="icon icon-th-list"></i> <span>校园生活</span> <span class="label label-important">4</span></a>
        <ul>
          <li><a href="${pageContext.request.contextPath }/admins/findSchoolGossip.action">校园八卦</a></li>
          <li><a href="${pageContext.request.contextPath }/admins/findSchoolSituation.action">校园风云</a></li>
          <li><a href="${pageContext.request.contextPath }/admins/findCampusConsultation.action">校园资讯</a></li>
          <li><a href="${pageContext.request.contextPath }/admins/findSchoolActivities.action">校园活动</a></li>
        </ul>
      </li>
      <li class="submenu"> <a href="${pageContext.request.contextPath }/admins/findReputationBusinessman.action"><i class="icon icon-plane"></i> <span>校园周边</span> <span class="label label-important">3</span></a>
        <ul>
          <li><a href="${pageContext.request.contextPath }/admins/findReputationBusinessman.action">口碑商</a></li>
          <li><a href="${pageContext.request.contextPath }/admins/findThenlife.action">便生活</a></li>
          <li><a href="${pageContext.request.contextPath }/admins/findActivity.action">有活动</a></li>
        </ul>
      </li>
      <li class="submenu"> <a href="${pageContext.request.contextPath }/admins/findAllUsersCustom.action"><i class="icon icon-user-md"></i> <span>用户</span> <span class="label label-important">3</span></a>
        <ul>
          <li><a href="${pageContext.request.contextPath }/admins/findAllUsersCustom.action">查询所有用户</a></li>
          <li><a href="${pageContext.request.contextPath }/findOrderitemsExtendByPage.action?pNum=1">查询用户订单</a></li>
        </ul>
      </li>
      <li class="submenu"> <a href="${pageContext.request.contextPath }/findProductsByPage.action?pNum=1&status=1"><i class="icon icon-shopping-cart"></i> <span>商城</span> <span class="label label-important">4</span></a>
        <ul>
		  <li><a href="${pageContext.request.contextPath }/adminguahuo.jsp">添加商品</a></li>
          <li><a href="${pageContext.request.contextPath }/findProductsByPage.action?pNum=1&status=1">通过审核的商品</a></li>
          <li><a href="${pageContext.request.contextPath }/findProductsByPage.action?pNum=1&status=2">未通过审核的商品</a></li>
          <li><a href="${pageContext.request.contextPath }/findProductsByPage.action?pNum=1&status=0">未审核的商品</a></li>
        </ul>
      </li>
      <li class="submenu"> <a href="${pageContext.request.contextPath }/findByPageNotes.action?pNum=1"><i class="icon icon-pencil"></i> <span>留言板</span> <span class="label label-important">1</span></a>
        <ul>
          <li><a href="${pageContext.request.contextPath }/findByPageNotes.action?pNum=1">查看留言板</a></li>
        </ul>
      </li>
      <li class="submenu"> <a href="add-article.html"><i class="icon icon-file"></i> <span>文章</span> <span class="label label-important">1</span></a>
        <ul>
          <li><a href="${pageContext.request.contextPath }/addarticles.jsp">添加文章</a></li>
        </ul>
      </li>
    </ul>
  </div>
  <!--sidebar-menu-->
  <!--main-container-part-->
  <div id="content">
    <div id="content-header">
      <div id="breadcrumb"> <a href="index.html" title="回到主页" class="tip-bottom"><i class="icon-home"></i>主页</a> <a href="college-joke.html" class="current">校园八卦</a> </div>
      <h1>校园八卦</h1>
    </div>

    <div class="container-fluid">
      <hr>
      <div class="row-fluid">
        <div class="span12">
          <div class="widget-box">
            <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
              <h5>八卦小文</h5>
            </div>
			<c:if test="${empty pageBean.articlesCustoms}">
		      <h1>校园八卦中没有信息</h1>
	         </c:if>
	         <c:if test="${not empty pageBean.articlesCustoms}">
            <form action ="${pageContext.request.contextPath }/admins/findArticlesByCondition.action?category=校园八卦" method="post" role="form">
              <div class="form-group">
                <select name="conditionName" id="input" class="form-control" required="required">              
                  <option value="author">作者</option>
                  <option value="title">标题</option>
                </select>
                <input type="text" name="conditionValue" class="form-control" id="" placeholder="输入查询内容">
                <input type="submit" class="btn btn-primary" value="查询">
              </div>
            </form>
            <div class="widget-content nopadding">
              <table class="table table-bordered data-table">
                <thead>
                  <tr>
                    <th>作者</th>
                    <th>标题</th>
                    <th>内容</th>
                    <th>浏览次数</th>
                    <th>发表时间</th>
                    <th>标签</th>
                    <th>操作</th>
                  </tr>
                </thead>
                <tbody>
             
				  <c:forEach items="${pageBean.articlesCustoms}" var="articlesCustom">
				 <tr class="gradeX">
					<td>${articlesCustom.author }</td>
					<td>${articlesCustom.title }</td>
					<td>${articlesCustom.slug }</td>
					<td class="center">${articlesCustom.views }</td>
				    <td>
				    <date:date value ="${articlesCustom.created}"/>
				    </td>
				    <td>${articlesCustom.tags}</td>
					<td><a href="${pageContext.request.contextPath }/findArticlesCustomByAid.action?aid=${articlesCustom.aid}">详情</a>&nbsp;
					&nbsp;<a href="${pageContext.request.contextPath }/admins/delArticlesCustomByAid.action?aid=${articlesCustom.aid}&category=校园八卦">删除</a>
					<a href="${pageContext.request.contextPath }/adminFindUpdateArticles.action?aid=${articlesCustom.aid}">修改文章</a>
					</td>						                           
				</tr>
			</c:forEach>
                </tbody>
                </table>
            
                 </c:if>
              <div class="pagination alternate text-center">
                <ul>
            
                <table>
                 <tr>
				<td colspan="9" align="center">
					<!-- 显示首页 --> <c:if test="${pageBean.pNum==1}">
                                           首页      上一页
               </c:if> <c:if test="${pageBean.pNum!=1}">
						<a
							href="${pageContext.request.contextPath }/admins/findSchoolGossip.action?pNum=1">首页</a>
						<a
							href="${pageContext.request.contextPath }/admins/findSchoolGossip.action?pNum=${pageBean.pNum-1 }">上一页</a>
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
								href="${pageContext.request.contextPath }/admins/findSchoolGossip.action?pNum=${i} ">${i}
							</a>
						</c:if>

					</c:forEach> <!-- 显示尾页 --> <c:if test="${pageBean.pNum==pageBean.totalPageNum}">
                                                 下一页   尾页
               </c:if> <c:if test="${pageBean.pNum!=pageBean.totalPageNum}">

						<a
							href="${pageContext.request.contextPath }/admins/findSchoolGossip.action?pNum=${pageBean.pNum+1}">下一页</a>
						<a
							href="${pageContext.request.contextPath }/admins/findSchoolGossip.action?pNum=${ pageBean.totalPageNum}">尾页</a>
					</c:if> <input type="text" id="pNum" size="2" /><input type="button"
					value="go" onclick="jump();" />
				</td>

			</tr>
			</table>

                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  <!--end-main-container-part-->
  <!--Footer-part-->
  <div class="row-fluid">
    <div id="footer" class="span12"> 2015 &copy; US后台管理系统. 欢迎致电 <a href="#">00000000000</a> </div>
  </div>
  <!--end-Footer-part-->
  <script src="${pageContext.request.contextPath }/js/jquery.min.js"></script> 
  <script src="${pageContext.request.contextPath }/js/jquery.ui.custom.js"></script> 
  <script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script> 
  <script src="${pageContext.request.contextPath }/js/jquery.uniform.js"></script> 
  <script src="${pageContext.request.contextPath }/js/select2.min.js"></script> 
  <script src="${pageContext.request.contextPath }/js/matrix.js"></script> 
   
</body>

</html>
