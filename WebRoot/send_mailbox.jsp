<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>写信</title>
</head>
<body>
<h3 style="color: red">${msg2}</h3>
<h3 style="color: red">${msg1}</h3>
<h3 style="color: red">${msg}</h3>
<form action="${pageContext.request.contextPath }/users/sendUsersMailbox.action" method="post">
    <table>
       <tr>
          <td>收件人用户名</td>
          <td><input type="text" name="username"></td>
       </tr>
       <tr>
          <td>标题</td>
          <td><input type="text" name="title"></td>
       </tr>
       <tr>
           <td>内容</td>
            <td>
              <textarea rows="10" cols="80" name="content"></textarea>
           </td>
       </tr>
        <tr>
           <td colspan="2">
           <input type="submit" value="发送"/>
           
           </td>
      </tr>
    </table>
</form>
</body>
</html>