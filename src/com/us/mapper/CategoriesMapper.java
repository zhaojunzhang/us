package com.us.mapper;

import java.util.List;

import com.us.po.Categories;

public interface CategoriesMapper {

	//�������
	public int insertCategories(Categories categories)throws Exception;
	//���ݷ�������ѯ������е�����
	public Categories findCategoriesByName(String name)throws Exception;
	//����cid����ѯ���·���
	public Categories findCategoriesById(int cid)throws Exception;
	
	
}
