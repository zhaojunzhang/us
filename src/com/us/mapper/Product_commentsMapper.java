package com.us.mapper;

import java.util.List;

import com.us.po.PageBean;
import com.us.po.Product_commentsExtend;

public interface Product_commentsMapper {
	
	//��pid��ѯ��Ʒ���۵�����
	public int countProduct_ommentsByPid(int pid)throws Exception;
	//��ҳ�������е�����
	public List<Product_commentsExtend> findCommentByPage(PageBean pageBean) throws Exception;
	//��������
	public int  insertProduct_comments(Product_commentsExtend product_commentsExtend) throws Exception;
	
}
