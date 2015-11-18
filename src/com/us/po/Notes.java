package com.us.po;

public class Notes {

	private int noteid;
	private int uid;
	private String content;//ÁôÑÔÄÚÈİ
	private int status;//ÉóºË
	private String comment;
	private long ncreated;
	public long getNcreated() {
		return ncreated;
	}
	public void setNcreated(long ncreated) {
		this.ncreated = ncreated;
	}
	public int getNoteid() {
		return noteid;
	}
	public void setNoteid(int noteid) {
		this.noteid = noteid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
}
