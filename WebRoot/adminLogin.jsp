<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<script type="text/javascript">
	// 切换验证码
	function change(){
		document.getElementById("myimg").src = "${pageContext.request.contextPath }/CheckCodeController/service.action?"+new Date().getTime();
	}
</script>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap-responsive.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/us-bms-login.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/font-awesome/css/font-awesome.css" />
</head>

<body>
  <div id="loginbox">
    <form id="loginform" class="form-vertical clearfix" action="${pageContext.request.contextPath }/admins/loginAdmins.action" method="post">
      <div class="control-group normal_text">
        <h3><img src="${pageContext.request.contextPath }/img/logo.png" alt="Logo" />US后台管理系统</h3></div>
      <div class="control-group">
        <div class="controls">
          <div class="main_input_box">
            <span class="add-on bg_lg"><i class="icon-user"></i></span>
            <input name="username" class="username" type="text" placeholder="用户名" />
          </div>
        </div>
      </div>
      <div class="control-group">
        <div class="controls">
          <div class="main_input_box">
            <span class="add-on bg_ly"><i class="icon-lock"></i></span>
            <input name="password" class="password" type="password" placeholder="密码" />
          </div>
        </div>
      </div>
      <div class="control-group">
        <div class="controls">
          <div class="main_input_box">
            <span class="add-on bg_ly"><i class="icon-lock"></i></span>
            <input type="text" placeholder="验证码" name="checkcode" />
            <span class="main-safe-code">
            <img id="myimg"
					src="${pageContext.request.contextPath }/CheckCodeController/service.action"
					style="cursor:pointer;" onclick="change()" />
            </span>
          </div>
        </div>
      </div>
      <input type="submit"  class="btn btn-success pull-right btn-login" value="登录"/>
    </form>
  </div>
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
  <script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath }/js/jquery.validate.js"></script>
  <script src="${pageContext.request.contextPath }/js/matrix.login.js"></script>
</body>

</html>
