package com.itheima.edu.info.manager.service;

import com.itheima.edu.info.manager.dao.StudentDao;
import com.itheima.edu.info.manager.domain.Student;

//业务的逻辑处理（业务员）
public class StudentService {

    //创建StudentDao库管对象（作用域为整个类）
    private StudentDao studentDao = new StudentDao();

    //业务员接收到上一级客服类StudentService中传入的封装好学生信息的学生对象
    public boolean addStudent(Student stu) {

        /*将业务员StudentService接收到的学生对象传入到库管中的addStudent方法
        * 得到一个布尔返回值，将此返回值返回*/
        return studentDao.addStudent(stu);
    }

    public boolean isExists(String id) {

        //调用库管StudentDao中的findAllStudent方法，该方法返回一个学生对象 数组
        Student[] stus = studentDao.findAllStudent();

        //假设id在数组中不存在
        boolean exists = false;
        //遍历数组获取每一个对象
        for (int i = 0; i < stus.length; i++) {
            Student student = stus[i];
            if(student != null && student.getId().equals(id)){
                //说明id存在
                exists = true;
                break;
            }
        }

        return  exists;
    }
}
