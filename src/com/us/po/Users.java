package com.us.po;

public class Users {

	private int uid;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String male;
	private int type;//用户类型，可用于扩展
	private String avator;//用户头像
	private long created;
	private long loged;
	private String ip;
	private int gid;//用户分组
	private String agent;
	private String nickname;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMale() {
		return male;
	}
	public void setMale(String male) {
		this.male = male;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAvator() {
		return avator;
	}
	public void setAvator(String avator) {
		this.avator = avator;
	}
	
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	public long getLoged() {
		return loged;
	}
	public void setLoged(long loged) {
		this.loged = loged;
	}
	public String getIp() {
		return ip;
	} 
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "Users [uid=" + uid + ", username=" + username + ", password="
				+ password + ", email=" + email + ", phone=" + phone
				+ ", male=" + male + ", type=" + type + ", avator=" + avator
				+ ", created=" + created + ", loged=" + loged + ", ip=" + ip
				+ ", gid=" + gid + ", agent=" + agent + ", nickname="
				+ nickname + "]";
	}
	
	
	
}
