package com.neusoft.stucou.view.impl;

import java.util.List;
import java.util.Scanner;


import com.neusoft.stucou.dao.CourseDAO;
import com.neusoft.stucou.dao.StudentDAO;
import com.neusoft.stucou.dao.impl.CourseDAOimpl;
import com.neusoft.stucou.dao.impl.StudentDAOimpl;
import com.neusoft.stucou.po.Course;
import com.neusoft.stucou.po.Student;
import com.neusoft.stucou.view.CourseView;

public class CourseViewimpl implements CourseView{

	private Scanner input=new Scanner(System.in);
	@Override
	public void showCourseList() {
		// TODO Auto-generated method stub
		String coursename="";
        System.out.println("是否需要输入课程名称关键词(y/n)：");
        String inputStr = input.next();
        if(inputStr.equalsIgnoreCase("y")) {
            System.out.println("请输入课程名称关键词：");
            coursename = input.next();
        }
        CourseDAO dao = new CourseDAOimpl();
        List<Course> list = dao.showCourse(coursename);
        System.out.println("课程编号\t课程名称\t教师编号\t课程课时");
        for(Course c : list) {
            System.out.println(c.getCourseid()+"\t"+c.getCoursename()+"\t"+c.getTeacherid()+"\t"+c.getCoursetime());
        }
	}
	@Override
	public void saveCourse() {
		// TODO Auto-generated method stub
		int courseid = 0;
        int coursetime =0;
        int teacherid=0;
        String CourseName="";

        System.out.println("输入课程课时：");
        coursetime = input.nextInt();
        System.out.println("输入教师编号：");
        teacherid = input.nextInt();
        System.out.println("输入课程名字：");
        CourseName = input.next();
        CourseDAO dao = new CourseDAOimpl();
		int a = dao.saveCourse(coursetime,teacherid,CourseName);
        System.out.println("新增食品编号"+a);
	}

	@Override
	public void updateCourse() {
		// TODO Auto-generated method stub
		CourseDAO dao = new CourseDAOimpl();
		System.out.println("输入课程编号");
		int Courseid = input.nextInt();
        Course c = dao.getCourseid(Courseid);
        System.out.println(c);
        String inputStr = "";
        
        System.out.println("是否修改课程名称(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的课程名称：");
            c.setCoursename(input.next());
        }

        System.out.println("是否修改课程课时(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的课程课时：");
            c.setCoursetime(input.nextInt());
        }

        System.out.println("是否修改教师编号(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的教师编号：");
            c.setTeacherid(input.nextInt());
        }
        int num =dao.updateCourse(c);
		if (num>0)
			System.out.println("修改成功");
		else
			System.out.println("修改失败");
        
	}

	@Override
	public void removeCourse() {
		// TODO Auto-generated method stub
        System.out.println("输入课程编号：");
        int courseId = input.nextInt();
        System.out.println("确认删除？："+courseId+"(y/n)");
        String str = input.next();
		if (str.equals("y")){
			CourseDAO dao = new CourseDAOimpl();
			int num=dao.removeCourse(courseId);
			if (num>0)
			{
				System.out.println("删除成功");
			}else
				System.out.println("删除失败");
		}
        
	}
	@Override
	public void showStu() {
		// TODO Auto-generated method stub
		System.out.println("输入课程编号：");
        int courseId = input.nextInt();
		StudentDAO dao = new StudentDAOimpl();
		List<Student> slist=dao.showstu(courseId);
		for(Student s:slist){
			System.out.println(s.toString());
		}
	}
	

}
