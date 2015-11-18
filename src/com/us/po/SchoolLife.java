package com.us.po;

import java.util.List;

public class SchoolLife {

	private int cid;
	private int start=0;
	private int numperpage=3;
	private List<ArticlesCustom> schoolGossips;
	private List<ArticlesCustom> schoolSituations;
	private List<ArticlesCustom> schoolActivities;
	private List<ArticlesCustom> campusConsultations;
	private List<ArticlesCustom> reputationBusinessman;
	private List<ArticlesCustom> activities;
	private List<ArticlesCustom> thenlifes;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getNumperpage() {
		return numperpage;
	}
	public void setNumperpage(int numperpage) {
		this.numperpage = numperpage;
	}
	public List<ArticlesCustom> getSchoolGossips() {
		return schoolGossips;
	}
	public void setSchoolGossips(List<ArticlesCustom> schoolGossips) {
		this.schoolGossips = schoolGossips;
	}
	public List<ArticlesCustom> getSchoolSituations() {
		return schoolSituations;
	}
	public void setSchoolSituations(List<ArticlesCustom> schoolSituations) {
		this.schoolSituations = schoolSituations;
	}
	public List<ArticlesCustom> getSchoolActivities() {
		return schoolActivities;
	}
	public void setSchoolActivities(List<ArticlesCustom> schoolActivities) {
		this.schoolActivities = schoolActivities;
	}
	public List<ArticlesCustom> getCampusConsultations() {
		return campusConsultations;
	}
	public void setCampusConsultations(List<ArticlesCustom> campusConsultations) {
		this.campusConsultations = campusConsultations;
	}
	public List<ArticlesCustom> getReputationBusinessman() {
		return reputationBusinessman;
	}
	public void setReputationBusinessman(List<ArticlesCustom> reputationBusinessman) {
		this.reputationBusinessman = reputationBusinessman;
	}
	public List<ArticlesCustom> getActivities() {
		return activities;
	}
	public void setActivities(List<ArticlesCustom> activities) {
		this.activities = activities;
	}
	public List<ArticlesCustom> getThenlifes() {
		return thenlifes;
	}
	public void setThenlifes(List<ArticlesCustom> thenlifes) {
		this.thenlifes = thenlifes;
	}
	
	
}
