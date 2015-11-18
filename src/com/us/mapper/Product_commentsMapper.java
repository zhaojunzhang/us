package com.us.mapper;

import java.util.List;

import com.us.po.PageBean;
import com.us.po.Product_commentsExtend;

public interface Product_commentsMapper {
	
	//按pid查询商品评论的总数
	public int countProduct_ommentsByPid(int pid)throws Exception;
	//分页查找所有的评论
	public List<Product_commentsExtend> findCommentByPage(PageBean pageBean) throws Exception;
	//插入评论
	public int  insertProduct_comments(Product_commentsExtend product_commentsExtend) throws Exception;
	
}
