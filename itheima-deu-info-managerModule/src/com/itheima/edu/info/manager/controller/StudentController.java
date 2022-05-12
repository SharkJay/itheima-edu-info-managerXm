package com.itheima.edu.info.manager.controller;

import com.itheima.edu.info.manager.domain.Student;
import com.itheima.edu.info.manager.service.StudentService;

import java.util.Scanner;

//和用户打交道（客服接待）
public class StudentController {

    //开启学生管理系统，并且展示学生管理系统菜单
    public void start() {

        //键盘录入数据
        Scanner sc = new Scanner(System.in);

        //创建while无限循环，给while循环起名，方便使用break在特定位置退出
        studentLoop:
        while (true) {

            System.out.println("--------欢迎来到 <学生> 管理系统--------");
            System.out.println("请输入您的选择: 1.添加学生  2.删除学生  3.修改学生  4.查看学生  5.退出");

            //采用字符串形式接收数据
            String choice = sc.next();

            switch (choice) {
                case "1":
                    //System.out.println("添加学生");
                    //创建一个添加学生的方法
                    addStudent();
                    break;
                case "2":
                    System.out.println("删除学生");
                    break;
                case "3":
                    System.out.println("修改学生");
                    break;
                case "4":
                    System.out.println("查看学生");
                    break;
                case "5":
                    System.out.println("已退出，欢迎您下次使用学生管理系统！");
                    /*这里只需退出学生管理系统，不退出整个信息管理系统，因此不能使用System.exit(0)退出整个虚拟机
                     * 采用指向性退出当前while循环*/
                    break studentLoop;
                default:
                    System.out.println("您输入的有误，请重新输入！");
                    break;
            }
        }

    }

    public void addStudent() {

        //首先创建业务员对象
        StudentService studentService = new StudentService();

        //1键盘接受学生信息
        Scanner sc = new Scanner(System.in);

        //对学生学号判断是否已被占用，提示用户
        String id;//放在循环中定义只能在循环中使用，可以放到外面定义，提升作用域，使得下方也可正常使用
        while(true){
            System.out.println("请输入学生学号：");
            id = sc.next();
            //调用StudentService业务员类中的idExists方法，把id传入该方法，接收一个布尔返回值判断是否添加学号成功
            boolean flag = studentService.isExists(id);
            if(flag){
                //true表示输入的学号已被占用
                System.out.println("此学号已被占用，请重新输入！");
            }else{
                //没有被占用就结束循环
                break;
            }
        }

        System.out.println("请输入学生姓名：");
        String name = sc.next();
        System.out.println("请输入学生年龄：");
        String age = sc.next();
        System.out.println("请输入学生生日：");
        String birthday = sc.next();

        //2将学生信息封装为学生对象
        Student stu = new Student();
        stu.setId(id);
        stu.setName(name);
        stu.setAge(age);
        stu.setBirthday(birthday);

        //3将学生对象传递给StudentService业务员中的addStudent()方法
        //将封装好学生信息的学生对象传入到业务员中的addStudent()方法中，并且得到一个返回值，用以判断添加成功或失败
        boolean result = studentService.addStudent(stu);

        //4根据返回值判断添加成功或失败------if条件判断语句中为true时才会执行大括号中的“添加成功”
        if (result) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败！");
        }
    }
}
