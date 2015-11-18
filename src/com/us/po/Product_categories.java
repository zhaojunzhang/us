package com.us.po;

public class Product_categories {

	private int pcid;
	private String pcname;
	private int order;
	private String description;
	private int parent;
	public int getPcid() {
		return pcid;
	}
	public void setPic(int pcid) {
		this.pcid = pcid;
	}
	
	
	public String getPcname() {
		return pcname;
	}
	public void setPcname(String pcname) {
		this.pcname = pcname;
	}
	public void setPcid(int pcid) {
		this.pcid = pcid;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	
}
