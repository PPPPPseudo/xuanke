package com.neusoft.stucou.view.impl;

import java.util.List;
import java.util.Scanner;

import com.neusoft.stucou.dao.CourseDAO;
import com.neusoft.stucou.dao.StudentDAO;
import com.neusoft.stucou.dao.TeacherDAO;
import com.neusoft.stucou.dao.impl.CourseDAOimpl;
import com.neusoft.stucou.dao.impl.StudentDAOimpl;
import com.neusoft.stucou.dao.impl.TeacherDAOimpl;
import com.neusoft.stucou.po.Course;
import com.neusoft.stucou.po.Student;
import com.neusoft.stucou.po.Teacher;
import com.neusoft.stucou.view.TeacherView;

public class TeacherViewimpl implements TeacherView{

	private Scanner input =new Scanner(System.in);
	@Override
	public void showTeacherList() {
		// TODO Auto-generated method stub
		String teachername="";
        System.out.println("是否需要输入教师名称关键词(y/n)：");
        String inputStr = input.next();
        if(inputStr.equalsIgnoreCase("y")) {
            System.out.println("请输入教师名称关键词：");
            teachername = input.next();
        }
        TeacherDAO dao = new TeacherDAOimpl();
        List<Teacher> list = dao.showTeacher(teachername);
        System.out.println("教师编号\t教师姓名\t教师专精");
        for(Teacher c : list) {
            System.out.println(c.getTeacherid()+"\t"+c.getTeachername()+"\t"+c.getTechnology()+"\t");
        }
	}
	@Override
	public void saveTeacher() {
		// TODO Auto-generated method stub

        String technology ="";
        String teachername="";

        System.out.println("输入教师名字：");
        teachername = input.next();

        System.out.println("输入教师专精：");
        technology = input.next();
        TeacherDAO dao = new TeacherDAOimpl();
		int a = dao.saveTeacher(teachername,technology);
        System.out.println("新增食品编号"+a);
	}

	@Override
	public void updateTeacher() {
		// TODO Auto-generated method stub
		System.out.println("输入教师编号");
		int num=input.nextInt();
		TeacherDAO dao = new TeacherDAOimpl();
		Teacher t = dao.getTeacherid(num);
		String inputStr = "";
		System.out.println("是否修改教师名称(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的教师名称：");
            t.setTeachername(input.next());
        }
        System.out.println("是否修改教师专精(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的教师专精：");
            t.setTechnology(input.next());
        }
        num =dao.updateTeacher(t);
		if (num>0)
			System.out.println("修改成功");
		else
			System.out.println("修改失败");
	}

	@Override
	public void removeTeacher() {
		// TODO Auto-generated method stub
		System.out.println("输入教师编号：");
        int teacherId = input.nextInt();
        System.out.println("确认删除？："+teacherId+"(y/n)");
        String str = input.next();
		if (str.equals("y")){
			TeacherDAO dao = new TeacherDAOimpl();
			int num=dao.removeTeacher(teacherId);
			if (num>0)
			{
				System.out.println("删除成功");
			}else
				System.out.println("删除失败");
		}
	}

	@Override
	public void showTeacher() {
		// TODO Auto-generated method stub
		System.out.println("输入教师编号：");
        int Teacherid = input.nextInt();
		TeacherDAO dao = new TeacherDAOimpl();
		List<Student> slist=dao.showstu(Teacherid);
		for(Student s:slist){
			System.out.println(s.toString());
		}
	}

}
