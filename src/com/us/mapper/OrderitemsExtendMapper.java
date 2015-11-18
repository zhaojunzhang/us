package com.us.mapper;

import java.util.List;
import com.us.po.OrderitemsExtend;
import com.us.po.PageBean;


public interface OrderitemsExtendMapper {
	//1.根据pid找到orderID。
	public List<OrderitemsExtend> findOrderidByPid(int pid)throws Exception;
	//2根据商品id统计订单明细
	public int countOrdritemsByPid(PageBean pageBean )throws Exception;
	//3通过orderitemsid查找pid
	public int  findPidByOrderitemsid(int orderitemsid)throws Exception;
	
	//4统计订单明细表的总数findPidByOrderitemsid
	public Integer orderitemsCount()throws Exception;
	
	//5查询所有订单明细表
	public List<OrderitemsExtend> findOrderitemsExtendByPage(PageBean pageBean) throws Exception;
	
	//6分页查询订单明细表
	public List<OrderitemsExtend> findOrderitemsExtendByPid(PageBean pageBean) throws Exception;
	

	//8通过orderid查找订单明细表
	public List<OrderitemsExtend> findOrderitemsByOrderid(int orderid)throws Exception;	
	//9生成订单明细
	public  int  insertOrderitems(OrderitemsExtend orderitemsExtend)throws Exception;
	//通过商品pid来查询订单
	public List<OrderitemsExtend> findOrdersExtendByPid(int pid)throws Exception;

}
