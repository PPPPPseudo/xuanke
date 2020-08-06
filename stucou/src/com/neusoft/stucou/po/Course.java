package com.neusoft.stucou.po;

public class Course {

	private int courseid;
	private int teacherid;
	private int coursetime;
	private String coursename;
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public int getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}
	public int getCoursetime() {
		return coursetime;
	}
	public void setCoursetime(int coursetime) {
		this.coursetime = coursetime;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	@Override
	public String toString() {
		return "Course [courseid=" + courseid + 
				", teacherid=" + teacherid + ", coursetime=" 
				+ coursetime+ ", coursename=" + coursename + "]";
	}
	
}
