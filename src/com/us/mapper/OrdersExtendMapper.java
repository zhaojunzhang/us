package com.us.mapper;

import java.util.List;

import com.us.po.OrdersExtend;
import com.us.po.PageBean;

public  interface OrdersExtendMapper {
		//�����û�UId�����û��Ķ�����
		public Integer  findOrdersExtendCountByUid(PageBean pageBean)throws Exception;
	    public List<OrdersExtend> findAllOrdersExtend(PageBean pageBean)throws Exception;
	   //ͨ��orderid��ʱ��
		public OrdersExtend findCreated( int orderid)throws Exception;		
		//ͨ��orderid�����û�uid
		public int findUIdByOrderid(int orderid)throws Exception;
		//ɾ������
		public void deleteOrdersByOrderid(int orderid)throws Exception;
		//ͨ��uid���Ҷ���
		public List<OrdersExtend> findOrdersExtendByUid(int uid)throws Exception;
		//���ɶ���
		public int insertOrders(OrdersExtend ordersExtend)throws Exception;
		//����uid��������ҳ
		public List<OrdersExtend> findUidByOrderidPage(PageBean pageBean)throws Exception;
		public OrdersExtend findOrdersByOrderid(int orderid)throws Exception;
}


