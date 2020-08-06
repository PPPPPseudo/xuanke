package com.neusoft.stucou.view.impl;


import java.util.List;
import java.util.Scanner;
import com.neusoft.stucou.dao.StudentDAO;
import com.neusoft.stucou.dao.impl.StudentDAOimpl;
import com.neusoft.stucou.po.Course;
import com.neusoft.stucou.po.Student;
import com.neusoft.stucou.view.StudentView;

public class StudentViewimpl implements StudentView{
	
	private Scanner input =new Scanner(System.in);
	public void saveStudent(){
		String studentname = "";
		String studentclass = "";
		System.out.println("输入学生姓名");
		studentname = input.next();
		System.out.println("输入学生专业");
		studentclass = input.next();
		StudentDAO dao = new StudentDAOimpl();
		int a = dao.saveStudent(studentname,studentclass);
		System.out.println("新增学生编号为:"+a);
	}
	@Override
	public void seCourse() {
		// TODO Auto-generated method stub
		String coursename = "";
        int teacherid = 0;
        String inputStr = "";
        System.out.println("是否需要输入教师编号关键词(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入教师编号关键词：");
            teacherid = input.nextInt();
        }
        System.out.println("是否需要输入课程名称关键词(y/n)：");
        inputStr = input.next();
        if(inputStr.equalsIgnoreCase("y")) {
            System.out.println("请输入课程名称关键词：");
            coursename = input.next();
        }
        StudentDAO dao = new StudentDAOimpl();
        List<Course> list = dao.seCourse(coursename, teacherid);
        System.out.println("课程编号\t课程名称\t教师编号\t课程课时");
        for(Course c : list) {
            System.out.println(c.getCourseid()+"\t"+c.getCoursename()+"\t"+c.getTeacherid()+"\t"+c.getCoursetime());
        }
	}
	@Override
	public void showlist() {
		// TODO Auto-generated method stub
        int studentid = 0;
        System.out.println("请输入学生学号：");
        studentid = input.nextInt();
        StudentDAO dao = new StudentDAOimpl();
        List<Course> list = dao.showlist(studentid);
        System.out.println("课程编号\t课程名称\t教师编号\t课程课时");
        for(Course c : list) {
            System.out.println(c.getCourseid()+"\t"+c.getCoursename()+"\t"+c.getTeacherid()+"\t"+c.getCoursetime());
        }
        
	}
	@Override
	public void saveCourse(int studentid) {
		// TODO Auto-generated method stub
		int courseid =0;
		System.out.println("输入课程编号：");
        courseid = input.nextInt();
		StudentDAO dao = new StudentDAOimpl();
		int a = dao.saveCourse(studentid,courseid);
		if (a!=0){
			System.out.println("选课成功");
		}else
			System.out.println("选课失败");
	}

}
