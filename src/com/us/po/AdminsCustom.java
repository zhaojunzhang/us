package com.us.po;

public class AdminsCustom extends Admins{

	private String groupname;//分组名
	private String authority;//权限
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
	
}
