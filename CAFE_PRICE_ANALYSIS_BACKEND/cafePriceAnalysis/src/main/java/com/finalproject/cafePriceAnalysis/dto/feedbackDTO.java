package com.finalproject.cafePriceAnalysis.dto;

public class feedbackDTO {
	private int userid;
	private int dashboard_rating;
	private int analysis_rating;
	private String comments;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getDashboard_rating() {
		return dashboard_rating;
	}
	public void setDashboard_rating(int dashboard_rating) {
		this.dashboard_rating = dashboard_rating;
	}
	public int getAnalysis_rating() {
		return analysis_rating;
	}
	public void setAnalysis_rating(int analysis_rating) {
		this.analysis_rating = analysis_rating;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
