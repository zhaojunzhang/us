<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>挂货区</title>
<script type="text/javascript">
	 j = 1;  
    $(document).ready(function(){  
          
        $("#btn_add2").click(function(){  
            document.getElementById("newUpload2").innerHTML+='<div id="div_'+j+'"><input  name="file_'+j+'" type="file"  /><input type="button" value="删除"  onclick="del_2('+j+')"/></div>';  
              j = j + 1;  
        });  
    });  
      
    function del_2(o){  
         document.getElementById("newUpload2").removeChild(document.getElementById("div_"+o));  
    }  
	function SumbitBatch(form, fun) {
		if (document.form.a.value < 100) {
			alert("请填写大于100的数字");
			return false;
		}
	}

  
</script>
 <title>添加商品</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="css/bootstrap.min.css" />
  <link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
  <link rel="stylesheet" href="css/matrix-style.css" />
  <link rel="stylesheet" href="css/matrix-media.css" />
  <link rel="stylesheet" href="css/uniform.css" />
  <link rel="stylesheet" href="css/jquery.gritter.css" />
  <link rel="stylesheet" href="css/bootstrap-wysihtml5.css" />
  <link rel="stylesheet" href="font-awesome/css/font-awesome.css" />
  <style type="text/css">
  @media (min-width: 1200px){
    .row-fluid{
        padding-left: 20px;
    } 
  }
  </style>
</head>
<body>

  <div id="content">
    <!--breadcrumbs面包屑导航-->
    <div id="content-header">
      <div id="breadcrumb"> 
        <a href="index.html" title="回到主页" class="tip-bottom"><i class="icon-home"></i>主页</a> 
        <a href="add-article.html" title="添加商品" class="tip-bottom" class="current">添加商品</a> 
      </div>
      <h1>添加商品</h1>
    </div>
    <!--End-breadcrumbs-->
    <div class="row-fluid">
      <div class="span6">
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
            <h5>添加商品详情</h5>
          </div>
       
          <div class="widget-content nopadding">
    
<h3 style="color: red">${msg}</h3>
	<form action="${pageContext.request.contextPath }/insertProducts.action?status=0"
		enctype="multipart/form-data" method="post"  class="form-horizontal">
		
              <div class="control-group">
                <label class="control-label">挂货分类</label>
                <div class="controls">

				<select name="type">
				        <option>--请选择--</option>
						<option value="闲互送">闲互送</option>
						<option value="闲互换">闲互换</option>
					
				</select>
				    </div>
              </div>
              <div class="control-group">
			 <label class="control-label">商品名称:</label>
                <div class="controls">
			   <input type="text" class="span11" name="name" />
                </div>
              </div>
              <div class="control-group">
                <label class="control-label">商品数量:</label>
                <div class="controls">
                  <input type="text" class="span11" name="quantity" />
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" >每人限购的数量:</label>
			   <div class="controls">
                  <input type="text" class="span11" name="limit"/>
                </div>
                </div>
			 <div class="control-group">
                <label class="control-label">商品摘要</label>
                <div class="controls">
                  <input class="span11"  name="summary"/>
                </div>
              </div>
               <div class="control-group">
                <label class="control-label">商品描述</label>
                <div class="controls">
                  <textarea class="span11" rows="3" cols="30" name="pdescription">
                  </textarea>
                </div>
              </div>
                    <div class="control-group" id="newUpload2"> 
                      <label class="control-label">上传图片</label> 
                       <div class="controls f-files">
                  			 <input type="file" name="file">  
                   </div>  
                   	</div>
                    <div class="controls">
                   <input type="button" id="btn_add2"  class="btn btn-success" value="增加一行" >  
                   <input type="submit" value="上传" >  
				</div>
			
		<div class="form-actions text-right">
                <input type="submit" class="btn btn-success" value="添加">
              </div>		
            
	</form>
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
  <script src="js/jquery.min.js"></script>
  <script src="js/jquery.ui.custom.js"></script> 
  <script src="js/bootstrap.min.js"></script> 
  <script src="js/jquery.uniform.js"></script> 
  <script src="js/select2.min.js"></script> 
  <script src="js/matrix.js"></script> 
  <script src="js/jquery.peity.min.js"></script> 
</body>

</html>
