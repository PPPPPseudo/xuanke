package com.neusoft.stucou.po;

public class Teacher {
	private int teacherid;
	private String teachername;
	private String technology;
	
	@Override
	public String toString() {
		return "Teacher [teacherid=" + teacherid + 
				", teachername=" + teachername +
				", technology=" + technology + "]";
	}
	
	public int getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
}
