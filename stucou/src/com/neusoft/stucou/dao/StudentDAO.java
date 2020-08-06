package com.neusoft.stucou.dao;

import java.util.List;

import com.neusoft.stucou.po.Course;
import com.neusoft.stucou.po.Student;

public interface StudentDAO {

	int saveStudent(String studentname, String studentclass);

	List<Course> seCourse(String coursename, int teacherid);

	List<Course> showlist(int studentid);

	int saveCourse(int studentid, int courseid);

	List<Student> showstu(int courseId);

}
