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
		<h3>
			<a href="index.html">US后台管理系统</a>
		</h3>
	</div>
	
	<jsp:include page="../HeaderBack.jsp"></jsp:include>
  <!--main-container-part-->
  <div id="content">
    <div id="content-header">
      <div id="breadcrumb"> <a href="index.html" title="返回主页" class="tip-bottom"><i class="icon-home"></i>主页</a> <a href="college-joke.html" class="current">查询用户订单</a> </div>
      <h1>查询用户订单</h1>
    </div>

    <div class="container-fluid">
      <hr>
      <div class="row-fluid">
        <div class="span12">
          <div class="widget-box">
            <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
              <h5>查询用户订单</h5>
            </div>

	   <form action ="${pageContext.request.contextPath }/findOrdersByCondition.action?pNum=1" method="post" role="form">
      <div class="form-group">
      
      <!-- 选择条件查询字段 -->
      <select name = "conditionName"  id="input" class="form-control" required="required">
      <option value="username">用户名</option>
      <option value="orderid">订单号</option>
      <option value="pcname">商品名称</option>
      </select> 
      <input type="text" name="conditionValue" class="form-control" id="" placeholder="输入查询内容"/>
      <input type = "submit" class="btn btn-primary"value = "查询"/>   
      </div>
    </form>
	<!-- 显示list数据 -->
	<c:if test="${empty pageBean.ordersExtends}">
		<h1>该页无任何数据！</h1>
	</c:if>
	<c:if test="${not empty pageBean.ordersExtends}">
		 <div class="widget-content nopadding">
              <table class="table table-bordered data-table">
                <thead>
                  <tr>
                   <th>订单id</th>
                    <th>用户名</th>
                    <th>手机号</th>
                    <th>订单抢购时间</th>
                    <th>商品名称</th>
                    <th>数量</th>
                    <th>售价</th>
                    <th>商品类型</th>
                    <th>操作</th>
                  </tr>
                </thead>
                <tbody>

			<c:forEach items="${pageBean.ordersExtends }" var="ordersExtends">
				 <tr class="gradeX">

				    <td>${ordersExtends.orderid }</td>
					<td>${ordersExtends.uname }</td>	
					<td>${ordersExtends.phone }</td>									
					<td><date:date value="${ordersExtends.created }"/></td>
					<c:forEach items="${ordersExtends.orderitemsExtend }" var="orderitemsExtend">
			        <td>${orderitemsExtend.productsExtend.name }</td>
			        <td>${orderitemsExtend.oquantity }</td>
			        <td>${orderitemsExtend.oprice }</td>			
			        <td>${orderitemsExtend.productsExtend.pdescription }</td>
			   	<td><a href="javascript:void(0);" onclick="confirmDel('${ordersExtends.orderid}');">删除</a></td>
			        </c:forEach>
			        </tr>
			    </c:forEach>
			
			    </tbody>
              </table>
			  <div class="pagination alternate text-center">
                <ul>

					<!-- 显示首页 --> <c:if test="${pageBean.pNum == 1}">
					<li> <a href="javascript:;"> 首页 </a></li>  <li> <a href="javascript:;">上一页 </a> </li>

				</c:if> <c:if test="${pageBean.pNum != 1}">
						<a
							href="${pageContext.request.contextPath }/findOrderitemsExtendByPage.action?pNum=1">首页</a>
						<a
							href="${pageContext.request.contextPath }/findOrderitemsExtendByPage.action?pNum=${pageBean.pNum-1 }">上一页</a>
					</c:if> <!-- 当前页为中心前后各显示10页 --> <c:set var="begin" value="1" scope="page" />
					<c:set var="end" value="${pageBean.totalPageNum}" scope="page" />

					<!-- 判断前面有没有10页 --> <c:if test="${pageBean.pNum-10>0}">
						<c:set var="begin" value="${pageBean.pNum-10}" scope="page" />
					</c:if> <!-- 判断后面有没有10页 --> <c:if
						test="${pageBean.pNum+10 < pageBean.totalPageNum}">
						<c:set var="end" value="${pageBean.pNum + 10}" scope="page" />
					</c:if> <!-- 当前页不显示链接 --> <c:forEach begin="${begin}" end="${end}" var="i">
						<c:if test="${pageBean.pNum==i}">
						<li>  <a href="javascript:;"> ${i} </a>  </li>
					</c:if>
						<c:if test="${pageBean.pNum!=i}">
							<a
								href="${pageContext.request.contextPath }/findOrderitemsExtendByPage.action?pNum=${i }">${i
								} </a>
						</c:if>
					</c:forEach> <!-- 显示尾页 --> <c:if test="${pageBean.pNum==pageBean.totalPageNum}">
						<li> <a href="javascript:;"> 下一页   </a>   </li>
						<li><a href="javascript:;"> 尾页   </a>    </li>
				</c:if> <c:if test="${pageBean.pNum!=pageBean.totalPageNum}">
						<a
							href="${pageContext.request.contextPath }/findOrderitemsExtendByPage.action?pNum=${pageBean.pNum + 1 }">下一页</a>
						<a
							href="${pageContext.request.contextPath }/findOrderitemsExtendByPage.action?pNum=${pageBean.totalPageNum}">尾页</a>
					</c:if> 
			
			</ul>
					<input type="text" id="pNum" size="2" /><input type="button"
					value="go" onclick="jump();" />
			
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