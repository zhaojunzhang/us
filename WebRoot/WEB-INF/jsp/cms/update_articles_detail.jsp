<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tags" prefix="date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>详细信息</title>
</head>
<body>
<c:if test="${empty articlesCustom}">
<h2>您查询文章不存在</h2>
</c:if>
<c:if test="${not empty articlesCustom}">
<form action="${pageContext.request.contextPath }/users/UpdateUserArticlesCustom.action" method="post">
<table>
     <tr style="display: none;">
		    <td>文章Aid</td>
		    <td><input type="text" name="aid" value="${articlesCustom.aid}"/></td>
	  </tr>
     <tr>
         <td>标题</td>
         <td><input type="text" value="${articlesCustom.title}" name="title"/></td>
     </tr>
     <tr>
         <td>作者</td>
         <td>${articlesCustom.author}</td>
     </tr>
     <tr>
         <td>发布时间</td>
         <td><date:date value ="${articlesCustom.created}"/></td>
     </tr>
     
     <tr>
         <td>浏览次数</td>
         <td>${articlesCustom.views}</td>
     </tr>
       <tr>
       <td>文章类别</td>
       <td>${articlesCustom.category }</td>
       <td>
           <select name="category" >
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
         <td>摘要</td>
         <td><input type="text" value="${articlesCustom.slug}" name="slug"/></td>
     </tr>
       <tr>
			 <script id="container" name="content"  type="text/plain">
                                   ${articlesCustom.content}
           </script>
           </tr>
     <tr>
         <td>标签</td>
         <td><input type="text" value="${articlesCustom.tags}" name="tags"/></td>
     </tr>
     <tr>
      <td>
      <input type="submit" value="修改文章"></td>
      </tr>  
</table> 

	 <!-- 配置文件 -->
          <script type="text/javascript" src="ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
              <script type="text/javascript" src="ueditor/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
             <script type="text/javascript">
        var editor = UE.getEditor('container');
        </script>
</form>   
</c:if>
</body>
</html>