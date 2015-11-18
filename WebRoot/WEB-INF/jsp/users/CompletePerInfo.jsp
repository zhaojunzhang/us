<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>补全个人信息页面</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/users/updateUserInfo.action" enctype="multipart/form-data" method="post" > 
		<table>
		<tr style="display: none;">
		    <td>用户id</td>
		    <td><input type="text" name="uid" value="${usersCustom.uid}"/></td>
		</tr>
		<tr style="display: none;">
		    <td>用户名</td>
		    <td><input type="text" name="username" value="${usersCustom.username}"/></td>
		</tr>
			<tr>
			    <td>用户昵称</td>
			    <td><input type="text" name="nickname"
					value="${usersCustom.nickname}" /></td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td><input type="text" name="email"
					value="${usersCustom.email }" /></td>
			</tr>
			<tr>
				<td>个人头像</td>
				
         <td><c:if test="${usersCustom.avator !=null}">
			          <img src="${pageContext.request.contextPath }${usersCustom.avator }" width=100 height=100/>
			       <br/>	</c:if>
		         <input type="file"  name="avator_img" /></td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
		            <select name="male">
		            <option selected="selected" value="male">男</option>
		            <option value="female">女</option>
		            </select>		
				</td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="补全个人信息" /></td>
			</tr>


		</table>
	</form>
</body>
</html>