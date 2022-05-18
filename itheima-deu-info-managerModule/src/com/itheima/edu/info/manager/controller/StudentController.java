package com.itheima.edu.info.manager.controller;

import com.itheima.edu.info.manager.domain.Student;
import com.itheima.edu.info.manager.service.StudentService;

import java.util.Scanner;

//和用户打交道（客服接待）
public class StudentController {

    //首先创建业务员对象（作用域为整个类），并且用private修饰，禁止外类访问
    private StudentService studentService = new StudentService();

    //键盘录入数据
    private Scanner sc = new Scanner(System.in);

    //开启学生管理系统，并且展示学生管理系统菜单
    public void start() {

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
                    //System.out.println("删除学生");
                    //创建删除学生方法，通过id
                    deleteStudentById();
                    break;
                case "3":
                    //System.out.println("修改学生");
                    //创建修改学生方法
                    updateStudent();
                    break;
                case "4":
                    //System.out.println("查看学生");
                    //创建查看学生方法
                    findAllStudent();
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

    //修改学生方法
    public void updateStudent() {

        //调用输入学生id方法，接收一个返回值
        String updateId = inputStudentId();

        //调用键盘录入学生信息方法
        Student newstudent = inputStudentInfo(updateId);

        //调用业务员service中的修改方法，传入要修改的学生id和新的学生对象
        studentService.updateStudent(updateId,newstudent);

        //提示修改成功
        System.out.println("修改成功！！！");

    }

    //删除学生方法
    public void deleteStudentById() {

        //调用输入学生id方法，减少冗余
        String delId = inputStudentId();
        //3 调用业务员service中的deleteStudentById方法，根据Id删除学生
        studentService.deleteStudentById(delId);
        //4 提示删除成功
        System.out.println("删除成功！");
    }

    //查看学生方法
    public void findAllStudent() {

        //1调用业务员中的获取方法，得到学生的对象数组
        Student[] stus = studentService.findAllStudent();
        //2 判断数组的内存地址是否为null
        if (stus == null) {
            System.out.println("查无信息，请添加后重试！");
            //retun空，结束这个findAllStudent方法，当然if语句也结束了
            return;
        }
        //3 不是null，遍历数组，获取学生信息并且打印在控制台
        System.out.println("学号\t\t姓名\t年龄\t生日");
        for (int i = 0; i < stus.length; i++) {
            //得到具体的学生对象
            Student stu = stus[i];
            //如果stus数组没装满stu学生对象，可能会存在null，打印之前先做一个判断
            if (stu != null) {
                System.out.println(stu.getId() + "\t" + stu.getName() + "\t" + stu.getAge() + "\t\t" + stu.getBirthday());
            }
        }

    }

    //添加学生方法
    public void addStudent() {

        //1键盘接受学生信息
        //Scanner sc = new Scanner(System.in);//作用域已提升到整个类

        //对学生学号判断是否已被占用，提示用户
        String id;//放在循环中定义只能在循环中使用，可以放到外面定义，提升作用域，使得下方也可正常使用
        while (true) {
            System.out.println("请输入学生学号：");
            id = sc.next();
            //调用StudentService业务员类中的idExists方法，把id传入该方法，接收一个布尔返回值判断是否添加学号成功
            boolean flag = studentService.isExists(id);
            if (flag) {
                //true表示输入的学号已被占用
                System.out.println("此学号已被占用，请重新输入！");
            } else {
                //没有被占用就结束循环
                break;
            }
        }

        //调用键盘录入学生信息方法,减少代码冗余
        Student stu = inputStudentInfo(id);

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

    //优化delete和update中相同的代码段，封装成方法，减轻冗余现象
    //该方法的作用就是录入学生的id，方法的返回值是String
    public String inputStudentId(){
        String id;

        while (true) {
            //1 键盘录入要删除的学生id
            System.out.println("请输入您要删除的对象id：");
            //在循环结束后，这个id所记录的值就是我们要删除的那个学生id了，提升delId的作用域，以便再次使用
            id = sc.next();
            //2 判断id在容器中是否存在，如果不存在，则需要一直录入
            boolean exists = studentService.isExists(id);
            if (!exists) {
                //如果id 不存在，提示重新输入
                System.out.println("您输入的id不存在，请重新输入！");
            } else {
                //存在，就结束当前while死循环
                break;
            }
        }

        return id;
    }

    //键盘录入学生信息
    public Student inputStudentInfo(String id){

        //确定好学生id后，修改新的学生信息
        System.out.println("请输入新的学生姓名：");
        String name = sc.next();
        System.out.println("请输入新的学生年龄：");
        String age = sc.next();
        System.out.println("请输入新的学生生日：");
        String birthday = sc.next();

        //把信息封装到学生对象，带参数构造方法
        Student stu = new Student(id, name, age, birthday);

        //返回学生对象
        return stu;
    }
}
