package com.us.po;



public class UsersCustom extends Users{
    private String newpassword;//������
	private String name;//������
	private String authority;//Ȩ��
	private String newphone;//�°󶨵��ֻ���
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
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getNewphone() {
		return newphone;
	}
	public void setNewphone(String newphone) {
		this.newphone = newphone;
	}
	
	
}
