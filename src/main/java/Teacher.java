package main.java;

import lombok.Builder;
import lombok.ToString;

public class Teacher extends Identity{
    private String teacherName; // 教师姓名
    public Teacher(String teacherName,String username, String password) {
        super.userName = username;
        super.password = password;
        this.teacherName = teacherName;
    }

    @Override
    public void menu() {
        Screen.teacherOperateScreen(this.userName);
    }
}
