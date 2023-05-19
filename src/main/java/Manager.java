package main.java;

import java.sql.*;
import java.util.Scanner;

public class Manager extends Identity {
    private String ManagerID;//管理员ID

    private String jdbcUrl = "jdbc:mysql://localhost:3306/dzy-java?useSSL=false&serverTimezone=UTC";
    private String sqlusername = "root";
    private String sqlpassword = "root";
    public Manager(String ManagerID, String password, String name ) {
        this.ManagerID = ManagerID;
        super.password = password;//密码
        super.name = name;

    }

    @Override
    public void menu() {
        Screen.managerOperateScreen(super.name);
    }



    //显示账号
    public void showAccount() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1.显示所有学生账号");
            System.out.println("2.显示所有教师账号");
            System.out.println("3.显示所有管理员账号");
            System.out.println("*.返回上一级");
            System.out.println("0.退出系统");
            char select = scanner.next().charAt(0);// 接收用户输入的选项
            scanner.nextLine();// 清空输入缓冲区
            if (select == '1')//进行显示所有学生账号的操作
            {
                ((Manager) this).showStudentsAccount();
                Screen.pause();
            }
            else if (select == '2')//进行显示所有教师账号的操作
            {
                ((Manager) this).showTeachersAccount();
                Screen.pause();
            }
            else if (select == '3')//进行显示所有管理员账号的操作
            {
                ((Manager) this).showManagersAccount();
                Screen.pause();
            }
            else if (select == '*')//返回上一级
            {
                break;
            }
            else if (select == '0')//退出系统
            {
                Screen.directExitPrompt();
                System.exit(0);
            }
            else//输入错误
            {
                System.out.println("输入错误，请重新输入");
                Screen.pause();
            }
        }
    }


    //添加学生账号
    public void addAccount() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1.添加学生账号");
            System.out.println("2.添加教师账号");
            System.out.println("3.添加管理员账号");
            System.out.println("*.返回上一级");
            System.out.println("0.退出系统");
            char select = scanner.next().charAt(0);// 接收用户输入的选项
            scanner.nextLine();// 清空输入缓冲区
            if (select == '1')//进行添加学生账号的操作
            {
                ((Manager) this).addStudentAccount();
                Screen.pause();
            }
            else if (select == '2')//进行添加教师账号的操作
            {
                ((Manager) this).addTeacherAccount();
                Screen.pause();
            }
            else if (select == '3')//进行添加管理员账号的操作
            {
                ((Manager) this).addManagerAccount();
                Screen.pause();
            }
            else if (select == '*')//返回上一级
            {
                break;
            }
            else if (select == '0')//退出系统
            {
                Screen.directExitPrompt();
                System.exit(0);
            }
            else//输入错误
            {
                System.out.println("输入错误，请重新输入");
                Screen.pause();
            }
        }
    }


    //删除帐号
    public void deleteAccount() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1.删除学生账号");
            System.out.println("2.删除教师账号");
            System.out.println("3.删除管理员账号");
            System.out.println("*.返回上一级");
            System.out.println("0.退出系统");
            char select = scanner.next().charAt(0);// 接收用户输入的选项
            scanner.nextLine();// 清空输入缓冲区
            if (select == '1')//进行删除学生账号的操作
            {
                ((Manager) this).deleteStudentAccount();
                Screen.pause();
            }
            else if (select == '2')//进行删除教师账号的操作
            {
                ((Manager) this).deleteTeacherAccount();
                Screen.pause();
            }
            else if (select == '3')//进行删除管理员账号的操作
            {
                ((Manager) this).deleteManagerAccount();
                Screen.pause();
            }
            else if (select == '*')//返回上一级
            {
                break;
            }
            else if (select == '0')//退出系统
            {
                Screen.directExitPrompt();
                System.exit(0);
            }
            else//输入错误
            {
                System.out.println("输入错误，请重新输入");
                Screen.pause();
            }
        }
    }



    public void showStudentsAccount() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM studentaccount";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("所有学生账号为:");
            while (resultSet.next()) {
                String StudentID = resultSet.getString("StudentID");
                String Password = resultSet.getString("Password");
                String Name = resultSet.getString("Name");
                System.out.println("学号:" + StudentID + " 密码:" + Password + " 姓名:" + Name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void showTeachersAccount() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM teacheraccount";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("所有教师账号为:");
            while (resultSet.next()) {
                String StudentID = resultSet.getString("TeacherID");
                String Password = resultSet.getString("Password");
                String Name = resultSet.getString("Name");
                System.out.println("学号:" + StudentID + " 密码:" + Password + " 姓名:" + Name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void showManagersAccount() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM manageraccount";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("所有管理员账号为:");
            while (resultSet.next()) {
                String StudentID = resultSet.getString("ManagerID");
                String Password = resultSet.getString("Password");
                String Name = resultSet.getString("Name");
                System.out.println("学号:" + StudentID + " 密码:" + Password + " 姓名:" + Name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    private void addStudentAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入学生账号: ");
        String StudentID = scanner.nextLine();
        System.out.print("请输入学生密码: ");
        String Password = scanner.nextLine();
        System.out.print("请输入学生姓名: ");
        String Name = scanner.nextLine();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM studentaccount WHERE StudentID = '" + StudentID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                System.out.println("该学生账号已存在");
                return;
            }
            sql = "INSERT INTO studentaccount VALUES('" + StudentID + "','" + Password + "','" + Name + "')";
            statement.executeUpdate(sql);
            System.out.println("添加成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    private void addTeacherAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入教师账号: ");
        String TeacherID = scanner.nextLine();
        System.out.print("请输入教师密码: ");
        String Password = scanner.nextLine();
        System.out.print("请输入教师姓名: ");
        String Name = scanner.nextLine();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM teacheraccount WHERE TeacherID = '" + TeacherID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                System.out.println("该教师账号已存在");
                return;
            }
            sql = "INSERT INTO teacheraccount VALUES('" + TeacherID + "','" + Password + "','" + Name + "')";
            statement.executeUpdate(sql);
            System.out.println("添加成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void addManagerAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入管理员账号: ");
        String ManagerID = scanner.nextLine();
        System.out.print("请输入管理员密码: ");
        String Password = scanner.nextLine();
        System.out.print("请输入管理员姓名: ");
        String Name = scanner.nextLine();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM manageraccount WHERE ManagerID = '" + ManagerID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                System.out.println("该管理员账号已存在");
                return;
            }
            sql = "INSERT INTO manageraccount VALUES('" + ManagerID + "','" + Password + "','" + Name + "')";
            statement.executeUpdate(sql);
            System.out.println("添加成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



    private void deleteTeacherAccount() {
        showTeachersAccount();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除的教师账号: ");
        String TeacherID = scanner.nextLine();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM teacheraccount WHERE TeacherID = '" + TeacherID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("该教师账号不存在");
                return;
            }
            sql = "DELETE FROM teacheraccount WHERE TeacherID = '" + TeacherID + "'";
            statement.executeUpdate(sql);
            System.out.println("删除成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteStudentAccount() {
        showStudentsAccount();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除的学生账号: ");
        String StudentID = scanner.nextLine();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM studentaccount WHERE StudentID = '" + StudentID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("该学生账号不存在");
                return;
            }
            sql = "DELETE FROM studentaccount WHERE StudentID = '" + StudentID + "'";
            statement.executeUpdate(sql);
            System.out.println("删除成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void deleteManagerAccount() {
        showManagersAccount();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要删除的管理员账号: ");
        String ManagerID = scanner.nextLine();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM manageraccount WHERE ManagerID = '" + ManagerID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("该管理员账号不存在");
                return;
            }
            sql = "DELETE FROM manageraccount WHERE ManagerID = '" + ManagerID + "'";
            statement.executeUpdate(sql);
            System.out.println("删除成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void clearRecord() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM `order`";
            statement.executeUpdate(sql);
            System.out.println("清除成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
