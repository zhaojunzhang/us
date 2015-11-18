package com.us.service;

import java.util.List;

import com.us.po.ImagesExtend;
import com.us.po.OrderitemsExtend;
import com.us.po.OrdersExtend;
import com.us.po.PageBean;
import com.us.po.Product_categories;
import com.us.po.Product_commentsExtend;
import com.us.po.ProductsExtend;
import com.us.po.ProductsQuery;
import com.us.po.UsersCustom;

public interface ProductsExtendService {
	
	// 查询所有status=1，quantity！=0的商品
	public List<ProductsExtend>findAllProducts(ProductsExtend productsExtend)throws Exception;
	//通过orderid查找用户uid
	public int findUIdByOrderid(int orderid)throws Exception;
	//1.根据pid找到orderID。
	public List<OrderitemsExtend> findOrderidByPid(int pid)throws Exception;
	//按pid查询商品评论的总数
	public int countProduct_ommentsByPid(int pid)throws Exception;
	//查看商品的浏览次数
	public int productsExtendViews(int pid)throws Exception;
	//插入评论
	public int  insertProduct_comments(Product_commentsExtend product_commentsExtend) throws Exception;
	//通过orderitemsid查找pid
	public int  findPidByOrderitemsid(int orderitemsid)throws Exception;
	//通过商品id号查找商品分类
	public int findPcateByPid(int pid)throws Exception;
	//根据商品的名找到商品id
	public List<ProductsExtend> findPidByName(String name)throws Exception;
	//限量抢购商品 
	public void  updateQuantity(ProductsExtend productsExtend)throws Exception;
	//分页查询商品
	public PageBean findOrderitemsExtendByPage(PageBean pageBean)throws Exception;
	//通过用户名分页查询订单明细表
	 public PageBean findOrderitemsByOrderid(PageBean pageBean) throws Exception;
	//通过商品名分页查询订单明细表
	public PageBean findOrderitemsByPid(PageBean pageBean) throws Exception;
	//删除订单
	public void deleteOrdersByOrderid(int orderid)throws Exception;
	//通过orderid查找商品PID

	//通过orderid查找订单明细表
	public List<OrderitemsExtend> findOrderitemsByOrderid(int orderid)throws Exception;
	//通过uid查找订单
	public List<OrdersExtend> findOrdersExtendByUid(int uid)throws Exception;
	//生成订单
	public int insertOrders(OrdersExtend ordersExtend)throws Exception;
	//生成订单明细
	public  int insertOrderitems(int uid,int pid)throws Exception;
	//通过商品的名称查找商品的总数
	public Integer findProductsCountByName(PageBean pageBean)throws Exception;
	
	//根据商品的名称进行分页
	public PageBean findByPageByName(PageBean pageBean)throws Exception;
	//根据商品名称查询
	public List<ProductsExtend>findTypePcnameByName(ProductsExtend productsExtend)throws Exception;
	//查询所有status=1，quantity=0的商品
	public List<ProductsExtend> findProductsRecord(ProductsExtend productsExtend)throws Exception;	
	//查询商品分类
	public List<ProductsExtend> findPcategories(ProductsQuery productsQuer)throws Exception;
	//查询商品type
	public List<ProductsExtend> findProductsType(ProductsExtend productsExtend)throws Exception;
	//根据商品id删除商品
	public void deleteProductsById(int id)throws Exception;
	//修改商品
	public ProductsExtend updateProductsById(ProductsExtend productsExtend)throws Exception;
	//查询商品的总数
	public Integer findProductsCount(int status)throws Exception;
	//分页查询商品评论
	public PageBean findCommentByPage(PageBean pageBean)throws Exception;
	//分页查询商品
	public PageBean findProductsByPage(PageBean pageBean)throws Exception;
	//根据商品的type查找商品
	public  PageBean  findProductsByType (PageBean pageBean) throws Exception;
	//分页查询个人购买记录
	public PageBean findPersonOrderitemsByPage(PageBean pageBean) throws Exception;
	//根据pid查询商品
	public ProductsExtend findProductsById(int id)throws Exception;
	//插入商品
	public int insertProducts(ProductsExtend productsExtend,List<String> images)throws Exception;
    //根据条件来查询订单
	public PageBean findOrdersByCondition(PageBean pageBean)throws Exception;

}
