package com.us.mapper;

import java.util.List;

import com.us.po.OrdersExtend;
import com.us.po.PageBean;

public  interface OrdersExtendMapper {
		//根据用户UId查找用户的订单数
		public Integer  findOrdersExtendCountByUid(PageBean pageBean)throws Exception;
	    public List<OrdersExtend> findAllOrdersExtend(PageBean pageBean)throws Exception;
	   //通过orderid找时间
		public OrdersExtend findCreated( int orderid)throws Exception;		
		//通过orderid查找用户uid
		public int findUIdByOrderid(int orderid)throws Exception;
		//删除订单
		public void deleteOrdersByOrderid(int orderid)throws Exception;
		//通过uid查找订单
		public List<OrdersExtend> findOrdersExtendByUid(int uid)throws Exception;
		//生成订单
		public int insertOrders(OrdersExtend ordersExtend)throws Exception;
		//根据uid来订单分页
		public List<OrdersExtend> findUidByOrderidPage(PageBean pageBean)throws Exception;
		public OrdersExtend findOrdersByOrderid(int orderid)throws Exception;
}


