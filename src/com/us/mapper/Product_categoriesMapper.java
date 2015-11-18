package com.us.mapper;


import com.us.po.Product_categories;

public interface Product_categoriesMapper {

	// 添加商品的分类
	public void insertPcategories(Product_categories pcategories)throws Exception;
	//根据分类名查询分类表中的数据
	public Product_categories findProduct_categoriesByName(String name)throws Exception;
	//根据pcid来查询pcname
	public String findPcnameByPcid(int pcid)throws Exception;
}
