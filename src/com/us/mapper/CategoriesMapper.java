package com.us.mapper;

import java.util.List;

import com.us.po.Categories;

public interface CategoriesMapper {

	//添加数据
	public int insertCategories(Categories categories)throws Exception;
	//根据分类名查询分类表中的数据
	public Categories findCategoriesByName(String name)throws Exception;
	//根据cid来查询文章分类
	public Categories findCategoriesById(int cid)throws Exception;
	
	
}
