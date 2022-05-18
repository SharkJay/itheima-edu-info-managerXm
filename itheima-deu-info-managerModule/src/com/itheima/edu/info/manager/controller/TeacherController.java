package com.itheima.edu.info.manager.controller;

import com.itheima.edu.info.manager.domain.Teacher;
import com.itheima.edu.info.manager.service.TeacherService;

import java.util.Scanner;

public class TeacherController {

    private Scanner sc = new Scanner(System.in);
    private TeacherService teacherService = new TeacherService();

    public void start() {

        teacherLoop:
        while (true) {

            System.out.println("--------欢迎来到 <老师> 管理系统--------");
            System.out.println("请输入您的选择: 1.添加老师  2.删除老师  3.修改老师  4.查看老师  5.退出");
            String choice = sc.next();

            switch (choice) {
                case "1":
                    //System.out.println("添加老师");
                    addTeacher();
                    break;
                case "2":
                    //System.out.println("删除老师");
                    deleteTeacherById();
                    break;
                case "3":
                    //System.out.println("修改老师");
                    updateTeacher();
                    break;
                case "4":
                    //System.out.println("查看老师");
                    findAllTeacher();
                    break;
                case "5":
                    System.out.println("已退出，欢迎您的下次使用！");
                    //不指定就只能结束当前switch
                    break teacherLoop;
                default:
                    System.out.println("输入有误，请重新输入！！！");
                    break;
            }
        }
    }

    //修改老师方法
    public void updateTeacher() {
        //调用封装好的录入老师方法
        String id = inputTeacherId();

        //调用录入老师信息方法
        Teacher newTeacher = inputTeacherInfo(id);

        //调用业务员中的修改老师方法，把id和新老师对象传入
        teacherService.updateTeacher(id, newTeacher);

        //提示信息
        System.out.println("修改成功！！！");
    }

    //根据老师id删除对象
    public void deleteTeacherById() {
        //调用封装好的录入老师id方法
        String id = inputTeacherId();

        //调用业务员中的删除方法，根据id删除老师
        teacherService.deleteTeacherById(id);

        System.out.println("删除成功！！！");
    }

    //查看老师方法
    public void findAllTeacher() {
        //1 从业务员中获取数组对象
        Teacher[] teachers = teacherService.findAllTeacher();

        //2 判断数组中是否有元素
        if (teachers == null) {
            //判断数组地址是不是null，是null说明数组没有元素
            System.out.println("查无信息，请添加后重试！！！");
            //直接结束当前findAllTeacher方法
            return;
        }

        //3 遍历数组取出所有元素
        System.out.println("编号\t\t姓名\t年龄\t生日");
        for (int i = 0; i < teachers.length; i++) {
            Teacher teacher = teachers[i];
            if (teacher != null) {
                System.out.println(teacher.getId() + "\t" + teacher.getName() + "\t" + teacher.getAge() + "\t\t" + teacher.getBirthday());
            }
        }
    }

    //添加老师方法
    public void addTeacher() {

        String id;

        //输入可能是多次，所以用while无限循环
        while (true) {
            //1 接收一个不存在的老师id
            System.out.println("请输入老师id：");
            id = sc.next();

            //2 判断id是否存在
            boolean exists = teacherService.isExists(id);
            if (exists) {
                //id存在说明已经被占用
                System.out.println("id已被占用，请重新输入！！！");
            } else {
                //不存在，结束循环，可以使用id
                break;
            }
        }

        //调用录入老师信息方法
        Teacher teacher = inputTeacherInfo(id);

        //将封装好的老师对象传递到TeacherService继续完成添加
        boolean result = teacherService.addTeacher(teacher);
        if (result) {
            System.out.println("添加成功！！！");
        } else {
            System.out.println("添加失败！！！");
        }
    }

    //录入老师id，判断id是否存在
    public String inputTeacherId() {

        String id;

        //键盘录入无限次
        while (true) {
            //键盘接收老师id
            System.out.println("请输入老师id：");
            id = sc.next();

            //判断id是否存在
            boolean exists = teacherService.isExists(id);
            if (!exists) {
                System.out.println("您输入的id不存在，请重新输入！！！");
            } else {
                break;
            }
        }

        return id;

        /*String id;

        //输入可能是多次，所以用while无限循环
        while (true) {
            //1 接收一个不存在的老师id
            System.out.println("请输入老师id：");
            id = sc.next();

            //2 判断id是否存在
            boolean exists = teacherService.isExists(id);
            if (exists) {
                //id存在说明已经被占用
                System.out.println("id已被占用，请重新输入！！！");
            } else {
                //不存在，结束循环，可以使用id
                break;
            }
        }*/
    }

    //录入老师信息封装成方法
    public Teacher inputTeacherInfo(String id) {
        //3 输入老师信息
        System.out.println("请输入老师姓名：");
        String name = sc.next();
        System.out.println("请输入老师年龄：");
        String age = sc.next();
        System.out.println("请输入老师生日：");
        String birthday = sc.next();

        //带参构造
        Teacher teacher = new Teacher(id, name, age, birthday);

        return teacher;
    }
}
