package com.us.mapper;


import com.us.po.Product_categories;

public interface Product_categoriesMapper {

	// �����Ʒ�ķ���
	public void insertPcategories(Product_categories pcategories)throws Exception;
	//���ݷ�������ѯ������е�����
	public Product_categories findProduct_categoriesByName(String name)throws Exception;
	//����pcid����ѯpcname
	public String findPcnameByPcid(int pcid)throws Exception;
}
