package com.us.po;

public class Admins {

	private int id;
	private String username;
	private String password;
	private int agid; //����Ա����id
	private long created;//����ʱ��
	private long loged; //�ϴε�¼ʱ��
	private String ip; //�û�ip
	private String comment;//����
	private String agent;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getAgid() {
		return agid;
	}
	public void setAgid(int agid) {
		this.agid = agid;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	
	
}
