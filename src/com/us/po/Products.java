package com.us.po;

public class Products {

	private int pid;
	private int pcid;
	private String name;
	private double soldprice;//售价
	private double oldprice;
	private int quantity;//数量
	private String type;  //类型
	private long pcreated;
	private String model; //型号
	private int views;  //浏览次数
	private int commentcount;  //评论次数
	private String address;
	private int status;
	private String comment;
	private int limit;
	private String mainimages;

	
	public long getPcreated() {
		return pcreated;
	}
	public void setPcreated(long pcreated) {
		this.pcreated = pcreated;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	private String summary;
	private String pdescription;

	public String getPdescription() {
		return pdescription;
	}
	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSoldprice() {
		return soldprice;
	}
	public void setSoldprice(double soldprice) {
		this.soldprice = soldprice;
	}
	public double getOldprice() {
		return oldprice;
	}
	public void setOldprice(double oldprice) {
		this.oldprice = oldprice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPcid() {
		return pcid;
	}
	public void setPcid(int pcid) {
		this.pcid = pcid;
	}

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getCommentcount() {
		return commentcount;
	}
	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getMainimages() {
		return mainimages;
	}
	public void setMainimages(String mainimages) {
		this.mainimages = mainimages;
	}
	
}
