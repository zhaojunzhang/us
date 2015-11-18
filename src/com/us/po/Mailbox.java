package com.us.po;

public class Mailbox {

	private int mailid;
	private int uid;
	private String title;
	private String content;
	private String ip;
	private String agent;
	private int sendid;
	private long sendtime;
	private long receivetime;
	public int getMailid() {
		return mailid;
	}
	public void setMailid(int mailid) {
		this.mailid = mailid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public int getSendid() {
		return sendid;
	}
	public void setSendid(int sendid) {
		this.sendid = sendid;
	}
	public long getSendtime() {
		return sendtime;
	}
	public void setSendtime(long sendtime) {
		this.sendtime = sendtime;
	}
	public long getReceivetime() {
		return receivetime;
	}
	public void setReceivetime(long receivetime) {
		this.receivetime = receivetime;
	}
	@Override
	public String toString() {
		return "Mailbox [mailid=" + mailid + ", uid=" + uid + ", title="
				+ title + ", content=" + content + ", ip=" + ip + ", agent="
				+ agent + ", sendid=" + sendid + ", sendtime=" + sendtime
				+ ", receivetime=" + receivetime + "]";
	}
    
}
