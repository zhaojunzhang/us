package com.us.po;

public class Groups {

	private int gid;
	private String name;
	private String authority;
	private String comment;
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Groups [gid=" + gid + ", name=" + name + ", authority="
				+ authority + ", comment=" + comment + "]";
	}
	
}
