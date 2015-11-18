<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>邮件的详细信息</title>
</head>
<body>
<form action="">
 <table>
        <tr>
              <td>发件人用户名</td>
              <td>${mailboxCustom.username }</td>
        </tr>
        <tr>
              <td>发件人昵称</td>
              <td>${mailboxCustom.nickname }</td>
        </tr>
        <tr>
              <td>标题</td>
              <td>${mailboxCustom.title }</td>
        </tr>
        <tr>
              <td>内容</td>
              <td>${mailboxCustom.content }</td>
        </tr>
        <tr>
              <td>发送时间</td>
              <td>${mailboxCustom.sendtime }</td>
        </tr>
        <tr>
              <td><a href="${pageContext.request.contextPath }/users/replyMailbox.action?sendid=${mailboxCustom.sendid}&username=${mailboxCustom.username}&nickname=${mailboxCustom.nickname}">回复</a></td>
              <td><a href="${pageContext.request.contextPath }/users/delMailboxById.action?mailid=${mailboxCustom.mailid}">删除</a></td>
        </tr>
 </table>
</form>
</body>
</html>