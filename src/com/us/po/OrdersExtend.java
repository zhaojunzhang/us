package com.us.po;

import java.util.List;



//������չ��
public class OrdersExtend extends Orders {
	//�û���
	private String uname;
	private  List<OrderitemsExtend> orderitemsExtend;
	private String ip;
	private String agent;
	private String phone;
	//��Ʒ��
	public String getUname() {
		return uname;
	}
	public List<OrderitemsExtend> getOrderitemsExtend() {
		return orderitemsExtend;
	}
	public void setOrderitemsExtend(List<OrderitemsExtend> orderitemsExtend) {
		this.orderitemsExtend = orderitemsExtend;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

}
