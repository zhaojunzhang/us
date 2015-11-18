package com.us.po;

public class Praise {

	private int aid;
	private int uid;
	private int status;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Praise [aid=" + aid + ", uid=" + uid + ", status=" + status
				+ "]";
	}
	
}
