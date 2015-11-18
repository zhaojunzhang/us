<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加文章</title>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap-responsive.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/matrix-style.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/matrix-media.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/uniform.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/jquery.gritter.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap-wysihtml5.css" />
  <link href="${pageContext.request.contextPath }/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <style type="text/css">
  @media (min-width: 1200px){
    .row-fluid{
        padding-left: 20px;
    } 
  }
  </style>
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
    <!--breadcrumbs面包屑导航-->
    <div id="content-header">
      <div id="breadcrumb"> 
        <a href="index.html" title="回到主页" class="tip-bottom"><i class="icon-home"></i>主页</a> 
        <a href="add-article.html" title="添加文章" class="tip-bottom" class="current">添加文章</a> 
      </div>
      <h1>添加文章</h1>
    </div>
    <!--End-breadcrumbs-->
    <div class="row-fluid">
      <div>
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
            <h5>添加文章详情</h5>
          </div>
          <div class="widget-content nopadding">
            <form action="${pageContext.request.contextPath }/admins/addarticles.action" enctype="multipart/form-data" method="post" class="form-horizontal">
              <div class="control-group">
                <label class="control-label">标题 :</label>
                <div class="controls">
                  <input type="text" class="span11" name="title" placeholder="标题" />
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">作者 :</label>
                <div class="controls">
                  <input type="text" class="span11" name="author" placeholder="作者" />
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">标签类型</label>
                <div class="controls">
                  <input type="text"  name="tags" class="span11" placeholder=""  />
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">文章分类</label>
                <div class="controls">
                  <select name="category">
                <option value="校园风云">校园风云</option>
                <option value="校园资讯">校园资讯</option>
                <option value="校园活动">校园活动</option>
                <option value="校园八卦">校园八卦</option>
                <option value="口碑商">口碑商</option>
                <option value="便生活">便生活</option>
                <option value="有活动">有活动</option>
                </select>
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">文章摘要:</label>
                <div class="controls">
                  <input type="text"  name="slug" class="span11" />
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">文章内容</label>
               <tr>
			 <script id="container" name="content" type="text/plain">
                                     这里写你的初始化内容
           </script>
           </tr>
              </div>
              <div class="form-actions text-right">
                <input type="submit" class="btn btn-success" value="提交">
              </div>
            </form>
			 <!-- 配置文件 -->
          <script type="text/javascript" src="ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
              <script type="text/javascript" src="ueditor/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
             <script type="text/javascript">
        var editor = UE.getEditor('container');
       
               </script>
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
  <script src="${pageContext.request.contextPath }/js/jquery.peity.min.js"></script> 
</body>

</html>
