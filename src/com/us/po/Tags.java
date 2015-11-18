package com.us.po;

public class Tags {

	private int tid;
	private String name; //±êÇ©Ãû³Æ
	private String comment;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Tags [tid=" + tid + ", name=" + name + ", comment=" + comment
				+ "]";
	}
	
}
