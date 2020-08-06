package com.neusoft.stucou.dao;

import java.util.List;

import com.neusoft.stucou.po.Student;
import com.neusoft.stucou.po.Teacher;;
public interface TeacherDAO {

	List<Teacher> showTeacher(String teachername);

	int saveTeacher(String teachername, String technology);

	Teacher getTeacherid(int num);

	int updateTeacher(Teacher t);

	int removeTeacher(int teacherId);

	List<Student> showstu(int teacherid);

}
