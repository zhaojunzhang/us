package com.us.mapper;

import java.util.List;

import com.us.po.PageBean;
import com.us.po.ProductsExtend;
import com.us.po.ProductsQuery;

public interface ProductsExtendMapper {


	//������Ʒ���������
	public void productsExtendView(int pid)throws Exception;
	//1ͨ����Ʒid�Ų�����Ʒ����pcid
	public int findPcateByPid(int pid)throws Exception;
	//2������Ʒ��������ģ����ѯ�ҵ���Ʒid
	public List<ProductsExtend> findPidByName(String name)throws Exception;
	//3����������Ʒ 
	public void updateQuantity(ProductsExtend productsExtend)throws Exception;	
	//������Ʒ���Ʋ�ѯ��Ʒ
	public List<ProductsExtend> findProductsByPcname(String name)throws Exception;
	
	public Integer findProductsCountByPcid(PageBean pageBean) throws Exception;
	public Integer findProductsCountByType(PageBean pageBean) throws Exception;
	//4 ͨ����Ʒ�����Ʋ�����Ʒ������
	public Integer findProductsCountByName(PageBean pageBean) throws Exception;
	//5 ������Ʒ�����ƽ���ģ����ѯ
	public List<ProductsExtend> findByConditionByName(PageBean pageBean)throws Exception;
	//5 ������Ʒ�ķ������ģ����ѯ
    public List<ProductsExtend> findByConditionByPcid(PageBean pageBean)throws Exception;
    //5 ������Ʒ���ͽ���ģ����ѯ
	public List<ProductsExtend> findByConditionByType(PageBean pageBean)throws Exception;
	
	
	//6������Ʒ���Ʋ�ѯ
	public List<ProductsExtend> findTypePcnameByName(ProductsExtend productsExtend)throws Exception;
	//7��ѯ����status=1��quantity=0����Ʒ
	public List<ProductsExtend> findProductsRecord(ProductsExtend productsExtend)throws Exception;	
	//8��ѯ��Ʒ����
	public List<ProductsExtend> findPcategories(ProductsQuery productsQuery)throws Exception;
	//9��ѯ��Ʒtype
	public List<ProductsExtend> findProductsType(ProductsExtend productsExtend)throws Exception;
	// 10������Ʒidɾ����Ʒ
	public void deleteProductsById(int id) throws Exception;
	// 11����id�޸���Ʒ
	public void updateProductsById(ProductsExtend productsExtend)throws Exception;
	// 12��ѯ��Ʒ������
	public Integer findProductsCount(int status) throws Exception;
	//������Ʒ�ķ����ѯ��Ʒ������
	public Integer findShangeChengCountByType(PageBean pageBean )throws Exception;
	// 13��ҳ��ѯ��Ʒ
	public List<ProductsExtend> findProductsByPage(PageBean pageBean) throws Exception;
	//������Ʒ��type������Ʒ
	public List<ProductsExtend> findProductsByType (PageBean pageBean) throws Exception;
	
	// 14����pid��ѯͨ����˵���Ʒ
	public ProductsExtend findProductsById(int id) throws Exception;
	// ��ѯ����status=1��quantity��=0����Ʒ
	public List<ProductsExtend>findAllProducts(ProductsExtend productsExtend)throws Exception;
	// 15������Ʒ
	public int insertProducts(ProductsExtend productsExtend) throws Exception;
}
