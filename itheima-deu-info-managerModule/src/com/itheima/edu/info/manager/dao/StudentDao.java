package com.itheima.edu.info.manager.dao;

import com.itheima.edu.info.manager.domain.Student;

//访问存储数据的数组，进行赠删改查（库管）
public class StudentDao {

    //1创建学生对象数组，类型为Student，大小为5
    /*私有化 ，不让外类访问这里的stus数组，static修饰后可以被StudentDao类的所有对象共享
     * 避免出现创建多个类时创建多个数组*/
    private static Student[] stus = new Student[5];

    public boolean addStudent(Student stu) {

        //2 添加学生到数组
        //2.1 首先假设数组已经存满，没有null的元素，定义一个变量index为-1
        int index = -1;

        //2.2 遍历数组取出每一个元素判断是否是null
        for (int i = 0; i < stus.length; i++) {
            Student student = stus[i];
            //如果索引对应为null，index就记录索引值
            if (student == null) {
                index = i;
                //并且此时要退出循环，如果不退出，循环就会一直往后索引之后的null
                break;
            }
        }

        //3 判断是否添加成功
        if (index == -1) {
            //如果index为-1，说明数组中没有null值，也就说明此时数组已经装满，也就是此时添加失败
            return false;
        } else {
            //否则说明未装满，则将接收到的学生对象stu添加进数组
            stus[index] = stu;
            //抛出添加成功返回值
            return true;
        }
    }

    public Student[] findAllStudent() {
        //返回学生数组
        return stus;
    }
}
