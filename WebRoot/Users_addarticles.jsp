<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加文章</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/users/addarticles.action" enctype="multipart/form-data" method="post">  
		<table>
			<tr>
				<td>标题</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>作者</td>
				<td><input type="text" name="author" value="${usersCustom.nickname }" readOnly="true"/></td>
			</tr>
		<tr>
				<td>标签类型</td>
				<td><input type="text" name="tags"></td>
			</tr>
			<tr>
				<td>文章分类</td>
            <td>
                <select name="category">
                <option value="校园风云">校园风云</option>
                <option value="校园资讯">校园资讯</option>
                <option value="校园活动">校园活动</option>
                <option value="校园八卦">校园八卦</option>
                <option value="口碑商">口碑商</option>
                <option value="便生活">便生活</option>
                <option value="有活动">有活动</option>
                </select>
                
            </td>
			</tr>
			<tr>
			    <td>文章摘要</td>
			    <td><input type="text" name="slug" maxlength="20"></td>
			</tr>
			<tr>
			 <script id="container" name="content" type="text/plain">
                                     这里写你的初始化内容
           </script>
           </tr>
           <tr>
           <td>  <input type="submit" value="提交"></td>
           </tr>
		</table>		           
	</form>
	 <!-- 配置文件 -->
    <script type="text/javascript" src="ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="ueditor/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var editor = UE.getEditor('container');      
    </script>
</body>
</html>