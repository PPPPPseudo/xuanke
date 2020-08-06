package com.neusoft.stucou.po;

public class Student {
	private int studentid ;
	private String studentname ;
	private String studentclass ;
	
	
	@Override
	public String toString() {
		return "Student [studentid=" + 
	studentid + ", studentname=" + studentname + 
	", studentclass=" + studentclass+ "]";
	}
	
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getStudentclass() {
		return studentclass;
	}
	public void setStudentclass(String studentclass) {
		this.studentclass = studentclass;
	}
}
