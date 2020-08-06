package com.neusoft.stucou;

import java.util.Scanner;

import com.neusoft.stucou.view.StudentView;
import com.neusoft.stucou.view.impl.StudentViewimpl;

public class StucouStudentEntity {
	private Scanner input =new Scanner(System.in);
	public static void main(String[] args) {
		new StucouStudentEntity().work();
	}
	public void work() {
		int num=0;
		StudentView sv= new StudentViewimpl();
		System.out.println("输入学号");
		int xuehao=input.nextInt();
		while (num!=5)
		{
		System.out.println("--------1.注册2.查询可选课程3.查询已选课程4.选课5.退出------------");
		num=input.nextInt();

			switch (num) {
			case 1 :{
				sv.saveStudent();
				break;
			}
			case 2 :{
				sv.seCourse();
				break;
			}
			case 3 :{
				sv.showlist();
				break;
			}
			case 4 :{
				sv.saveCourse(xuehao);
				break;
			}
			case 5 :{
				System.out.println("告辞");
				break;
			}
			default:
                System.out.println("没有\n");
                break;
		}
		}
	}
}
