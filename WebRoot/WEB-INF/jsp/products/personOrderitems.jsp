<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.us.po.PageBean"%>
	<%@taglib uri="/tags" prefix="date"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="css/bootstrap.min.css" />
  <link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
  <link rel="stylesheet" href="css/uniform.css" />
  <link rel="stylesheet" href="css/select2.css" />
  <link rel="stylesheet" href="css/matrix-style.css" />
  <link rel="stylesheet" href="css/matrix-media.css" />
  <link rel="stylesheet" href="font-awesome/css/font-awesome.css" />
<script type="text/javascript">
	function jump() {
		// 获得用户输入页码
		var pNum = document.getElementById("pNum").value;
		location.href = "${pageContext.request.contextPath }/findOrderitemsExtendByPage.action?pNum="
				+ pNum;
	}
	function confirmDel(id){
		var isConfirm = window.confirm("确认删除吗？想好了吗？");
		if(isConfirm){
			// 提交删除链接
			location.href="${pageContext.request.contextPath }/deleteOrdersByOrderid.action?orderid="+id;
		}	
	}	
</script>
</head>
<body>
  <!--Header-part-->
  <div id="header">
    <h3><a href="index.html">US后台管理系统</a></h3>
  </div>

  <!--main-container-part-->
  <div id="content">
    <div id="content-header">
      <div id="breadcrumb"> <a href="index.html" title="回到主页" class="tip-bottom"><i class="icon-home"></i>主页</a> <a href="college-joke.html" class="current">查询用户订单</a> </div>
      <h1>查询用户订单</h1>
    </div>

    <div class="container-fluid">
      <hr>
      <div class="row-fluid">
        <div class="span12">
          <div class="widget-box">
            <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
              <h5>被抢走的商品</h5>
            </div>

	   <form action ="${pageContext.request.contextPath }/findOrdersByCondition.action?pNum=1" method="post" role="form">
      <div class="form-group">
      
      <!-- 选择条件查询字段 -->
      <select name = "conditionName"  id="input" class="form-control" required="required">
      <option value="pcname">商品名称</option>
      </select> 
      <input type="text" name="conditionValue" class="form-control" id="" placeholder="输入查询内容"/>
      <input type = "submit" class="btn btn-primary"value = "查询"/>   
      </div>
    </form>
	<!-- 显示list数据 -->
	<c:if test="${empty pageBean.ordersExtend}">
		<h1>该页无任何数据！q</h1>
	</c:if>
	<c:if test="${not empty pageBean.ordersExtend}">
		 <div class="widget-content nopadding">
              <table class="table table-bordered data-table">
                <thead>
                  <tr>
                 <td>订单号</td>
				<td>用户名</td>
				<td>抢购时间</td>
				
				<td>商品名</td>
				<td>数量</td>
				<td>价格</td>
				<td>商品描述</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${ pageBean.ordersExtend }" var="ordersExtend">
			   <tr>
			   <td>${ordersExtend.orderid }</td>
			    <td>${ordersExtend.uname }</td>
			    <td><date:date value="${ordersExtend.created }"/></td>
			    
			    <c:forEach items="${ordersExtend.orderitemsExtend }" var="orderitemsExtend">
			        <td>${orderitemsExtend.productsExtend.name }</td>
			     <td>${orderitemsExtend.oquantity }</td>
			     <td>${orderitemsExtend.oprice }</td>
			
			   <td>${orderitemsExtend.productsExtend.pdescription }</td>
			   	<td><a href="${pageContext.request.contextPath}/deleteOrdersByOrderid.action?orderid=${ordersExtend.orderid }">删除</a></td>
			    </c:forEach>
			        </tr>
			    </c:forEach>
			
			    </tbody>
              </table>
			  <div class="pagination alternate text-center">
                <ul>

					<!-- 显示首页 --> <c:if test="${pageBean.pNum == 1}">
					首页  上一页
				</c:if> <c:if test="${pageBean.pNum != 1}">
						<a
							href="${pageContext.request.contextPath }/findPersonOrderitemsByPage.action?pNum=1">首页</a>
						<a
							href="${pageContext.request.contextPath }/findPersonOrderitemsByPage.action?pNum=${pageBean.pNum-1 }">上一页</a>
					</c:if> <!-- 当前页为中心前后各显示10页 --> <c:set var="begin" value="1" scope="page" />
					<c:set var="end" value="${pageBean.totalPageNum}" scope="page" />

					<!-- 判断前面有没有10页 --> <c:if test="${pageBean.pNum-10>0}">
						<c:set var="begin" value="${pageBean.pNum-10}" scope="page" />
					</c:if> <!-- 判断后面有没有10页 --> <c:if
						test="${pageBean.pNum+10 < pageBean.totalPageNum}">
						<c:set var="end" value="${pageBean.pNum + 10}" scope="page" />\
					</c:if> <!-- 当前页不显示链接 --> <c:forEach begin="${begin}" end="${end}" var="i">
						<c:if test="${pageBean.pNum==i}">
						${i}
					</c:if>
						<c:if test="${pageBean.pNum!=i}">
							<a
								href="${pageContext.request.contextPath }/findOrderitemsExtendByPage.action?pNum=${i }">${i
								} </a>
						</c:if>
					</c:forEach> <!-- 显示尾页 --> <c:if test="${pageBean.pNum==pageBean.totalPageNum}">
					下一页 尾页
				</c:if> <c:if test="${pageBean.pNum!=pageBean.totalPageNum}">
						<a
							href="${pageContext.request.contextPath }/findPersonOrderitemsByPage.action?pNum=${pageBean.pNum + 1 }">下一页</a>
						<a
							href="${pageContext.request.contextPath }/findPersonOrderitemsByPage.action?pNum=${pageBean.totalPageNum}">尾页</a>
					</c:if> 
					<input type="text" id="pNum" size="2" /><input type="button"
					value="go" onclick="jump();" />
			
			</ul>
			</div>
			</div>
	</c:if> 
	</div>
	</div>
	</div>
	</div>
	</div>
	
</body>
</html>