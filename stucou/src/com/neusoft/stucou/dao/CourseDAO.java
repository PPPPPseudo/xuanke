package com.neusoft.stucou.dao;

import java.util.List;

import com.neusoft.stucou.po.Course;

public interface CourseDAO {

	List<Course> showCourse(String coursename);

	int saveCourse(int coursetime, int teacherid, String courseName);

	Course getCourseid(int courseid);

	int updateCourse(Course c);

	int removeCourse(int courseId);

}
