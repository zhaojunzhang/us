<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>回复信箱</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/users/insertReplyMailbox.action" method="post">
      <table>
            <tr style="display: none;">
		    <td>接收人id</td>
		    <td><input type="text" name="uid" value="${mailboxCustom.sendid}"/></td>
		    </tr>
            <tr>
               <td>接收人的用户名</td>
               <td>${mailboxCustom.username}</td>
            </tr>
            <tr>
               <td>接收人的昵称</td>
               <td>${mailboxCustom.nickname }</td>
            </tr>
            <tr>
                <td>标题</td>
                <td><input type="text" name="title"/></td>
            </tr>
            <tr>
                <td>内容</td>
                <td><textarea rows="6" cols="80" name="content"></textarea></td>
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