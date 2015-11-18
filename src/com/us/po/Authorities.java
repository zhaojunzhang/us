package com.us.po;

public class Authorities {
	private String leave="0";
	private String product="0";
	private String gossip="0";
	private String situation="0";
	private String sactivity="0";
	private String information="0";
	private String reputation="0";
	private String activity="0";
	private String thenlife="0";
	private int uid;
	public String getLeave() {
		return leave;
	}
	public void setLeave(String leave) {
		this.leave = leave;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getGossip() {
		return gossip;
	}
	public void setGossip(String gossip) {
		this.gossip = gossip;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getSactivity() {
		return sactivity;
	}
	public void setSactivity(String sactivity) {
		this.sactivity = sactivity;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getReputation() {
		return reputation;
	}
	public void setReputation(String reputation) {
		this.reputation = reputation;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getThenlife() {
		return thenlife;
	}
	public void setThenlife(String thenlife) {
		this.thenlife = thenlife;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Authorities [leave=" + leave + ", product=" + product
				+ ", gossip=" + gossip + ", situation=" + situation
				+ ", sactivity=" + sactivity + ", information=" + information
				+ ", reputation=" + reputation + ", activity=" + activity
				+ ", thenlife=" + thenlife + ", uid=" + uid + "]";
	}
	
	

}
