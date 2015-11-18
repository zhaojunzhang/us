package com.us.mapper;

import java.util.List;
import com.us.po.OrderitemsExtend;
import com.us.po.PageBean;


public interface OrderitemsExtendMapper {
	//1.����pid�ҵ�orderID��
	public List<OrderitemsExtend> findOrderidByPid(int pid)throws Exception;
	//2������Ʒidͳ�ƶ�����ϸ
	public int countOrdritemsByPid(PageBean pageBean )throws Exception;
	//3ͨ��orderitemsid����pid
	public int  findPidByOrderitemsid(int orderitemsid)throws Exception;
	
	//4ͳ�ƶ�����ϸ�������findPidByOrderitemsid
	public Integer orderitemsCount()throws Exception;
	
	//5��ѯ���ж�����ϸ��
	public List<OrderitemsExtend> findOrderitemsExtendByPage(PageBean pageBean) throws Exception;
	
	//6��ҳ��ѯ������ϸ��
	public List<OrderitemsExtend> findOrderitemsExtendByPid(PageBean pageBean) throws Exception;
	

	//8ͨ��orderid���Ҷ�����ϸ��
	public List<OrderitemsExtend> findOrderitemsByOrderid(int orderid)throws Exception;	
	//9���ɶ�����ϸ
	public  int  insertOrderitems(OrderitemsExtend orderitemsExtend)throws Exception;
	//ͨ����Ʒpid����ѯ����
	public List<OrderitemsExtend> findOrdersExtendByPid(int pid)throws Exception;

}
