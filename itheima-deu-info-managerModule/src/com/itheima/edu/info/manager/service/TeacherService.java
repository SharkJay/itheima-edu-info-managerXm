package com.itheima.edu.info.manager.service;

import com.itheima.edu.info.manager.dao.TeacherDao;
import com.itheima.edu.info.manager.domain.Teacher;

public class TeacherService {
    //创建库管对象
    private TeacherDao teacherDao = new TeacherDao();

    public boolean addTeacher(Teacher teacher) {

        //调用库管中的添加方法，把接收到的老师对象传入，返回一个布尔结果
        return teacherDao.addTeacher(teacher);
    }

    //判断接收到的id在数组中是否存在
    public boolean isExists(String id) {
        //1 获取库管对象中的数组
        Teacher[] teachers = teacherDao.findAllTeacher();

        boolean exists = false;

        //对数组遍历
        for (int i = 0; i < teachers.length; i++) {
            Teacher teacher = teachers[i];
            //拿到的数组很可能没装满，因此先判断元素是否有null且id和输入的保持一致
            if (teacher != null && teacher.getId().equals(id)) {
                exists = true;
                break;
            }
        }

        return exists;
    }

    public Teacher[] findAllTeacher() {
        Teacher[] allTeacher = teacherDao.findAllTeacher();

        boolean flag = false;
        for (int i = 0; i < allTeacher.length; i++) {
            Teacher teacher = allTeacher[i];
            //如果数组中有一个元素不为null就说明数组不为空
            if (teacher != null) {
                flag = true;
                break;
            }
        }

        if (flag) {
            //返回对象地址
            return allTeacher;
        } else {
            //代表数组中什么都没有
            return null;
        }
    }

    public void deleteTeacherById(String id) {
        teacherDao.deleteTeacherById(id);

    }

    public void updateTeacher(String id, Teacher newTeacher) {
        //继续调用库管中的修改方法
        teacherDao.updateTeacher(id,newTeacher);
    }
}
