package com.us.po;

public class Admin_groups {

	private int agid;
	private String groupname;  //管理员分组名
	private String authority;  //权限
	private String comment;    //描述
	public int getAgid() {
		return agid;
	}
	public void setAgid(int agid) {
		this.agid = agid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
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
}
