package com.us.mapper;

import java.util.List;

import com.us.po.PageBean;
import com.us.po.ProductsExtend;
import com.us.po.ProductsQuery;

public interface ProductsExtendMapper {


	//更新商品的浏览次数
	public void productsExtendView(int pid)throws Exception;
	//1通过商品id号查找商品分类pcid
	public int findPcateByPid(int pid)throws Exception;
	//2根据商品的名进行模糊查询找到商品id
	public List<ProductsExtend> findPidByName(String name)throws Exception;
	//3限量抢购商品 
	public void updateQuantity(ProductsExtend productsExtend)throws Exception;	
	//根据商品名称查询商品
	public List<ProductsExtend> findProductsByPcname(String name)throws Exception;
	
	public Integer findProductsCountByPcid(PageBean pageBean) throws Exception;
	public Integer findProductsCountByType(PageBean pageBean) throws Exception;
	//4 通过商品的名称查找商品的总数
	public Integer findProductsCountByName(PageBean pageBean) throws Exception;
	//5 根据商品的名称进行模糊查询
	public List<ProductsExtend> findByConditionByName(PageBean pageBean)throws Exception;
	//5 根据商品的分类进行模糊查询
    public List<ProductsExtend> findByConditionByPcid(PageBean pageBean)throws Exception;
    //5 根据商品类型进行模糊查询
	public List<ProductsExtend> findByConditionByType(PageBean pageBean)throws Exception;
	
	
	//6根据商品名称查询
	public List<ProductsExtend> findTypePcnameByName(ProductsExtend productsExtend)throws Exception;
	//7查询所有status=1，quantity=0的商品
	public List<ProductsExtend> findProductsRecord(ProductsExtend productsExtend)throws Exception;	
	//8查询商品分类
	public List<ProductsExtend> findPcategories(ProductsQuery productsQuery)throws Exception;
	//9查询商品type
	public List<ProductsExtend> findProductsType(ProductsExtend productsExtend)throws Exception;
	// 10根据商品id删除商品
	public void deleteProductsById(int id) throws Exception;
	// 11根据id修改商品
	public void updateProductsById(ProductsExtend productsExtend)throws Exception;
	// 12查询商品的总数
	public Integer findProductsCount(int status) throws Exception;
	//根据商品的分类查询商品的总数
	public Integer findShangeChengCountByType(PageBean pageBean )throws Exception;
	// 13分页查询商品
	public List<ProductsExtend> findProductsByPage(PageBean pageBean) throws Exception;
	//根据商品的type查找商品
	public List<ProductsExtend> findProductsByType (PageBean pageBean) throws Exception;
	
	// 14根据pid查询通过审核的商品
	public ProductsExtend findProductsById(int id) throws Exception;
	// 查询所有status=1，quantity！=0的商品
	public List<ProductsExtend>findAllProducts(ProductsExtend productsExtend)throws Exception;
	// 15插入商品
	public int insertProducts(ProductsExtend productsExtend) throws Exception;
}
