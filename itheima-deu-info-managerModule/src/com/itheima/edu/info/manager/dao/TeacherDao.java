package com.itheima.edu.info.manager.dao;

import com.itheima.edu.info.manager.domain.Teacher;

public class TeacherDao {

    //创建数组
    private static Teacher[] teachers = new Teacher[5];

    public boolean addTeacher(Teacher teacher) {
        //接收到的teacher放到哪个位置？先找出数组中第一个null元素的位置

        int index = -1;
        //遍历数组
        for (int i = 0; i < teachers.length; i++) {
            //获取到每一个数组元素
            Teacher t = teachers[i];
            if (t == null) {
                //发现数组中null元素，同时用index记录null元素的索引
                index = i;
                break;
            }
        }

        if (index == -1) {
            //说明数组中没有null元素，数组满了，添加失败
            return false;
        } else {
            //把此刻的数组索引位置对应的元素用传入的老师赋值
            teachers[index] = teacher;
            return true;
        }
    }

    public Teacher[] findAllTeacher() {
        //返回数组
        return teachers;
    }

    public void deleteTeacherById(String id) {
        //1 根据id找索引
        int index = getIndex(id);
        //2 将该索引位置的元素使用null替换
        teachers[index] = null;
    }

    //根据id找索引的方法
    public int getIndex(String id) {
        //假设id不存在,上来就是-1
        int index = -1;
        for (int i = 0; i < teachers.length; i++) {
            Teacher teacher = teachers[i];
            if (teacher != null && teacher.getId().equals(id)) {
                index = i;
                break;
            }
        }

        return index;
    }

    public void updateTeacher(String id, Teacher newTeacher) {
        //确认id在数组中的索引位置
        int index = getIndex(id);
        //将该索引对应的旧老师元素使用传入的新老师对象替换
        teachers[index] = newTeacher;
    }
}
