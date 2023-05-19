package main.java;

import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Operate {

    private static Scanner scanner = new Scanner(System.in);

    // 用于不同的身份进行登录并创建相应对象，返回值为Identity对象
    public static Identity loginAndCreate(char select) {
        Identity person = null; // 父类对象用于指向子类对象

        try {
            // 连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dzy-java", "root", "root");
            Statement statement = connection.createStatement();// 创建Statement对象
            String ID = null;//学号
            String username; // 用户名（姓名）
            String password;// 密码
            if (select != '3') {
                System.out.print("请输入您的学号\nID:");
                ID = scanner.next();
            }
            System.out.print("请输入您的用户名(姓名)\nUsername:");
            username = scanner.next();
            System.out.print("请输入您的密码\nPassword:");

            password = scanner.next();
            if (select == '1') { // 对学生身份的人进行身份验证
                String query = "SELECT StudentID, Username, Password FROM studentaccount";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String checkID = resultSet.getString("StudentID"); // 数据库中读出的用于验证登录的ID,username,password
                    String checkUsername = resultSet.getString("Username");
                    String checkPassword = resultSet.getString("Password");

                    if (checkID.equals(ID) && checkUsername.equals(username) && checkPassword.equals(password)) {
                        Screen.loginSuccessPrompt(select); // 登录成功的提示
                        person = new Student(ID , username, password); // 创建学生对象，并赋值给父类对象
                        return person;
                    }
                }

            } else if (select == '2') { // 对教师身份的人进行身份验证
                String query = "SELECT TeacherID, Username, Password FROM teacheraccount";
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    String checkID = resultSet.getString("TeacherID"); // 数据库中读出的用于验证登录的ID,username,password
                    String checkUsername = resultSet.getString("Username");
                    String checkPassword = resultSet.getString("Password");

                    if (checkID.equals(ID) && checkUsername.equals(username) && checkPassword.equals(password)) {
                        Screen.loginSuccessPrompt(select); // 登录成功的提示
                        person = new Teacher(ID, username, password); // 创建教师对象，并赋值给父类对象
                        return person;
                    }
                }

            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Screen.loginFailPrompt(); // 登录失败的提示
        return person;
    }

    public static void studentMainMenuOperate (Identity user) {
        while (true) {
            user.menu();// 调用父类的menu方法
            Student student = (Student) user;
            char select = scanner.next().charAt(0);// 接收用户输入的选项
            scanner.nextLine();// 清空输入缓冲区
            if (select == '1')//进行申请预约的操作
            {
                Screen.operateLevelShowing('S',1);//显示操作层（申请预约）
                ((Student) user).applyOrder();
                Screen.pause();
            }

            if (select == '2')//进行显示用户自己预约的操作
            {
                Screen.operateLevelShowing('S',2);//显示操作层（显示我的预约）
                ((Student) user).showMyOrder();
                Screen.pause();
            }

            if (select == '3') //进行显示所有人的预约的操作
            {
                Screen.operateLevelShowing('S',3);//显示操作层（显示所有人的预约的操作）
                ((Student) user).showAllOrder();
                Screen.pause();
            }

            if (select == '4')//进行取消自己的预约的操作
            {
                Screen.operateLevelShowing('S',4);//显示操作层（取消自己的预约的操作）
                ((Student) user).cancelMyOrder();
                Screen.pause();
            }

            if (select == '*')//进行返回注销并上一级的操作
            {
                student = null;
                Screen.logoutPrompt();
                Screen.pause();
                return;
            }

            if (select == '0'){//进行返回直接退出系统操作
                Screen.directExitPrompt();
            }

        }

    }
    public static void teacherMainMenuOperate (Identity user) {
        while (true) {
            user.menu();// 调用父类的menu方法
            Teacher teacher = (Teacher) user;
            char choice = scanner.next().charAt(0);// 接收用户输入的选项
            scanner.nextLine();// 清空输入缓冲区

        }

    }

}
