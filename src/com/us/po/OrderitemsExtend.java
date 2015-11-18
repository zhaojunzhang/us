package com.us.po;

//订单明细表的扩展
public class OrderitemsExtend extends Orderitems {

	private int quantity;
	private String username;
	private ProductsExtend productsExtend;
	private String price;
	private long created;
	private double soldprice;
	private String name;
	private String type;
	private String ip;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public ProductsExtend getProductsExtend() {
		return productsExtend;
	}
	public void setProductsExtend(ProductsExtend productsExtend) {
		this.productsExtend = productsExtend;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public double getSoldprice() {
		return soldprice;
	}
	public void setSoldprice(double soldprice) {
		this.soldprice = soldprice;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

}
