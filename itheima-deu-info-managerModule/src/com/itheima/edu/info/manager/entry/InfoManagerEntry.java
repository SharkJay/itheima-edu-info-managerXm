package com.itheima.edu.info.manager.entry;

import com.itheima.edu.info.manager.controller.StudentController;

import java.util.Scanner;

//程序的入口类，提供一个main方法
public class InfoManagerEntry {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //多次输入，使用无限循环
        while (true) {
            //设置菜单
            System.out.println("--------欢迎来到信息管理系统--------");
            System.out.println("请输入您的选择: 1.学生管理  2.老师管理  3.退出");
            //采用字符串形式接收数据
            String choice = sc.next();

            switch (choice) {
                case "1":
                    //System.out.println("学生管理");
                    /*一切和用户打交道的内容应该交给客服Controller类处理，在StudentController类中创建start方法
                     * 目的是开启学生管理系统*/
                    StudentController studentController = new StudentController();
                    studentController.start();
                    break;
                case "2":
                    System.out.println("老师管理");
                    break;
                case "3":
                    System.out.println("已退出，欢迎您的使用！");
                    //退出当前正在运行的虚拟机
                    System.exit(0);
                    break;
                default:
                    System.out.println("您输入的有误，请重新输入！");
                    break;
            }
        }

    }
}
