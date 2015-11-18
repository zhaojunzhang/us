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
	
	// ��ѯ����status=1��quantity��=0����Ʒ
	public List<ProductsExtend>findAllProducts(ProductsExtend productsExtend)throws Exception;
	//ͨ��orderid�����û�uid
	public int findUIdByOrderid(int orderid)throws Exception;
	//1.����pid�ҵ�orderID��
	public List<OrderitemsExtend> findOrderidByPid(int pid)throws Exception;
	//��pid��ѯ��Ʒ���۵�����
	public int countProduct_ommentsByPid(int pid)throws Exception;
	//�鿴��Ʒ���������
	public int productsExtendViews(int pid)throws Exception;
	//��������
	public int  insertProduct_comments(Product_commentsExtend product_commentsExtend) throws Exception;
	//ͨ��orderitemsid����pid
	public int  findPidByOrderitemsid(int orderitemsid)throws Exception;
	//ͨ����Ʒid�Ų�����Ʒ����
	public int findPcateByPid(int pid)throws Exception;
	//������Ʒ�����ҵ���Ʒid
	public List<ProductsExtend> findPidByName(String name)throws Exception;
	//����������Ʒ 
	public void  updateQuantity(ProductsExtend productsExtend)throws Exception;
	//��ҳ��ѯ��Ʒ
	public PageBean findOrderitemsExtendByPage(PageBean pageBean)throws Exception;
	//ͨ���û�����ҳ��ѯ������ϸ��
	 public PageBean findOrderitemsByOrderid(PageBean pageBean) throws Exception;
	//ͨ����Ʒ����ҳ��ѯ������ϸ��
	public PageBean findOrderitemsByPid(PageBean pageBean) throws Exception;
	//ɾ������
	public void deleteOrdersByOrderid(int orderid)throws Exception;
	//ͨ��orderid������ƷPID

	//ͨ��orderid���Ҷ�����ϸ��
	public List<OrderitemsExtend> findOrderitemsByOrderid(int orderid)throws Exception;
	//ͨ��uid���Ҷ���
	public List<OrdersExtend> findOrdersExtendByUid(int uid)throws Exception;
	//���ɶ���
	public int insertOrders(OrdersExtend ordersExtend)throws Exception;
	//���ɶ�����ϸ
	public  int insertOrderitems(int uid,int pid)throws Exception;
	//ͨ����Ʒ�����Ʋ�����Ʒ������
	public Integer findProductsCountByName(PageBean pageBean)throws Exception;
	
	//������Ʒ�����ƽ��з�ҳ
	public PageBean findByPageByName(PageBean pageBean)throws Exception;
	//������Ʒ���Ʋ�ѯ
	public List<ProductsExtend>findTypePcnameByName(ProductsExtend productsExtend)throws Exception;
	//��ѯ����status=1��quantity=0����Ʒ
	public List<ProductsExtend> findProductsRecord(ProductsExtend productsExtend)throws Exception;	
	//��ѯ��Ʒ����
	public List<ProductsExtend> findPcategories(ProductsQuery productsQuer)throws Exception;
	//��ѯ��Ʒtype
	public List<ProductsExtend> findProductsType(ProductsExtend productsExtend)throws Exception;
	//������Ʒidɾ����Ʒ
	public void deleteProductsById(int id)throws Exception;
	//�޸���Ʒ
	public ProductsExtend updateProductsById(ProductsExtend productsExtend)throws Exception;
	//��ѯ��Ʒ������
	public Integer findProductsCount(int status)throws Exception;
	//��ҳ��ѯ��Ʒ����
	public PageBean findCommentByPage(PageBean pageBean)throws Exception;
	//��ҳ��ѯ��Ʒ
	public PageBean findProductsByPage(PageBean pageBean)throws Exception;
	//������Ʒ��type������Ʒ
	public  PageBean  findProductsByType (PageBean pageBean) throws Exception;
	//��ҳ��ѯ���˹����¼
	public PageBean findPersonOrderitemsByPage(PageBean pageBean) throws Exception;
	//����pid��ѯ��Ʒ
	public ProductsExtend findProductsById(int id)throws Exception;
	//������Ʒ
	public int insertProducts(ProductsExtend productsExtend,List<String> images)throws Exception;
    //������������ѯ����
	public PageBean findOrdersByCondition(PageBean pageBean)throws Exception;

}
