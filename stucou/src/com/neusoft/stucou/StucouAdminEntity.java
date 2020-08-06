package com.neusoft.stucou;

import java.util.Scanner;

import com.neusoft.stucou.po.Course;
import com.neusoft.stucou.po.Teacher;
import com.neusoft.stucou.view.CourseView;
import com.neusoft.stucou.view.TeacherView;
import com.neusoft.stucou.view.impl.CourseViewimpl;
import com.neusoft.stucou.view.impl.TeacherViewimpl;

public class StucouAdminEntity {
	private Scanner input =new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StucouAdminEntity().work();
	}
	public void work() {
		System.out.println("-----------------------------------------");
		System.out.println("                     管理员入口 ");
		System.out.println("-----------------------------------------");

		String strin="";
		String strin1="";
		TeacherView bview =new TeacherViewimpl();
		System.out.println("管理员账号： ");
		strin=input.next();
		System.out.println("管理员密码： ");
		strin1=input.next();
		if (login(strin,strin1)) {
			int num=0;
			while (num!=3)
			{
			System.out.println("--------一级菜单1.课程管理模块2.教师管理模块3.退出------------");
			num=input.nextInt();

				switch (num) {
				case 1 :{
					courseManage();
					break;
				}
				case 2 :{
					teacherManage();
					break;
				}
				case 3 :{
					System.out.println("告辞");
					break;
				}
				default:
                    System.out.println("没有这个选项！\n");
                    break;
			}
			}
		}else{
			System.out.printf(strin,strin1);
		}
	}
	private void courseManage() {

        CourseView cv = new CourseViewimpl();
        int num = 0;
        while(num!=6) {
            //输出二级菜单
            System.out.println("\n--------------二级菜单（课程管理）1.查看课程列表=2.新增课程=3.修改课程=4.删除课程=5.查看选课人数=6.返回一级菜单---------\n");
            System.out.println("请输入你的选择：");
            num = input.nextInt();

            switch(num) {
                case 1:
                    cv.showCourseList();
                    break;
                case 2:
                    cv.saveCourse();
                    break;
                case 3:
                    cv.updateCourse();
                    break;
                case 4:
                    cv.removeCourse();
                    break;
                case 5:
                	cv.showStu();
                    break;    
                case 6:
                    break;  
                default:
                    System.out.println("没有这个选项！\n");
                    break;
            }
        }
    
	}
	private void teacherManage() {

        TeacherView tv = new TeacherViewimpl();

        int num = 0;
        while(num!=6) {
            //输出二级菜单
            System.out.println("\n--------------二级菜单（教师管理）1.查看教师列表="
            		+ "2.新增教师=3.修改教师=4.删除教师=5.查看教师人数=6.返回一级菜单---------\n");
            System.out.println("请输入你的选择：");
            num = input.nextInt();

            switch(num) {
                case 1:
                    tv.showTeacherList();
                    break;
                case 2:
                    tv.saveTeacher();
                    break;
                case 3:
                    tv.updateTeacher();
                    break;
                case 4:
                    tv.removeTeacher();
                    break;
                case 5:
                	tv.showTeacher();
                    break;    
                case 6:
                    break;   
                default:
                    System.out.println("没有这个选项！\n");
                    break;
            }
        }
    
	}
	public boolean login(String name,String password) {
		if (name.equals("admin") && password.equals("123"))
			return true;
		else
			return false;
	}

}
