<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>更改手机绑定</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/users/updateUserPhoneById.action" method="post" >
  <table>
      <tr style="display: none;">
		    <td>用户id</td>
		    <td><input type="text" name="uid" value="${usersCustom.uid}"/></td>
	  </tr>
      <tr>
      <td>已绑定手机号</td>
      <td><input name="phone" value="${usersCustom.phone}"></td>
      </tr>
      <tr>
       <td>上次登录时间</td>
       <td><%=request.getSession().getAttribute("dateStr") %></td>
      </tr>
      <tr>
         <td>新手机号</td>
         <td><input type="text" name="newphone" /></td>
         <td>倒计时发送验证码</td>
      </tr>
      <tr>
        <td>手机验证码</td>
        <td><input type="text" name="updateCheckcode"></td>
      </tr>
       <tr>
             <td><input type="submit" value="修改绑定手机"/></td>
             <td>
             <input type="button" value="返回" onclick="history.go(-1);"/>
             </td>
        </tr>
  </table>
</form>
</body>
</html>