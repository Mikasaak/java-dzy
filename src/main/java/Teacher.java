package main.java;

import main.java.GUI.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Teacher extends Identity {

    private String jdbcUrl = "jdbc:mysql://localhost:3306/dzy-java?useSSL=false&serverTimezone=UTC";
    private String sqlusername = "root";
    private String sqlpassword = "root";
    private String TeacherID; //教师职工号

    public Teacher(String TeacherID, String password, String name) {
        this.TeacherID = TeacherID;///教师职工号
        super.password = password;//密码
        super.name = name;//（用户名）姓名

    }

    @Override
    public void menu() {
        Screen.teacherOperateScreen(this.name);
    }

    //显示所有学生账号
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

    public String getStudentAccount() {
        StringBuilder info = new StringBuilder();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM studentaccount";
            ResultSet resultSet = statement.executeQuery(sql);
            info.append("所有学生账号为:\n");
            while (resultSet.next()) {
                String StudentID = resultSet.getString("StudentID");
                String Password = resultSet.getString("Password");
                String Name = resultSet.getString("Name");
                info.append(" 姓名:").append(Name).append("学号:").append(StudentID).append(" 密码:").append(Password).append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return info.toString();
    }

    public String getAllReservationInfo() {
        StringBuilder info = new StringBuilder();
        info.append("所有审核中和审核通过的预约为:\n");
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
//            Statement statement = connection.createStatement();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM `order` WHERE Status='审核中' OR Status='审核通过'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                info = new StringBuilder("暂无审核中和审核通过的预约");
                return info.toString();
            }
            resultSet.beforeFirst();
            while (resultSet.next()) {
                info.append("预约编号: ").append(resultSet.getString("id")).append(" 机房编号: ").append(resultSet.getString("ComputerRoomID"))
                        .append(" 开始时间: ").append(resultSet.getString("StartDateTime"))
                        .append(" 结束时间: ").append(resultSet.getString("EndDateTime"))
                        .append(" 姓名: ").append(resultSet.getString("StudentName"))
                        .append(" 学号: ").append(resultSet.getString("StudentID"))
                        .append(" 状态: ").append(resultSet.getString("Status")).append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return info.toString();
    }


    /**
     * @return boolean审核是否通过
     */
    //审核预约
    public Boolean auditOrder() {

        Scanner scanner = new Scanner(System.in);
        try {
            ArrayList<String> ordersID = new ArrayList<String>();
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM `order` WHERE Status='审核中'";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("所有预约信息为:");
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String startDate = resultSet.getString("StartDateTime");
                String endDate = resultSet.getString("EndDateTime");
                String studentID = resultSet.getString("StudentID");
                String studentName = resultSet.getString("StudentName");
                String status = resultSet.getString("Status");
                ordersID.add(id);
                System.out.println("预约编号:" + id + " 开始时间:" + startDate + " 结束时间:" + endDate +
                        " 学号:" + studentID + " 姓名:" + studentName + " 状态:" + status);
            }

            System.out.println("------------------------------------------------");
            System.out.println("请选择要审核的预约编号:");
            String id = scanner.next();
            for (String orderID : ordersID) {
                if (orderID.equals(id)) {
                    break;
                } else {
                    System.out.println("输入错误!");
                    return false;
                }
            }
            System.out.println("请选择审核结果(1.通过 2.不通过):");
            String result = scanner.next();
            if (result.equals("1")) {
                sql = "UPDATE `order` SET Status='审核通过' WHERE id=" + id;
                statement.executeUpdate(sql);
                System.out.println("审核成功!");
            } else if (result.equals("2")) {
                sql = "UPDATE `order` SET Status='审核不通过' WHERE id=" + id;
                statement.executeUpdate(sql);
                System.out.println("审核成功!");
                return true;
            } else {
                System.out.println("输入错误!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public Pair<Boolean, String> auditOrderPart(String auditID, String result) {
        String info = "";
        Boolean flag = false;
        try {
            ArrayList<Integer> auditingOrdersID = new ArrayList<Integer>();//存储所有预约编号
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();//创建statement对象
            String sql = "SELECT * FROM `order` WHERE Status='审核中'";//查询所有审核中的预约
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = parseInt(resultSet.getString("id"));
                auditingOrdersID.add(id);
            }
            if (auditingOrdersID.size() == 0) {
                info += "暂无审核中的预约!";
                return new Pair<Boolean, String>(flag, info);
            }
            int auditIDInt = parseInt(auditID.trim());
            System.out.println("auditIDInt:" + auditIDInt);
            for (int orderID : auditingOrdersID) {
                if (orderID == parseInt(auditID.trim())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                info += "输入错误!";
                return new Pair<Boolean, String>(flag, info);
            }
            sql = "UPDATE `order` SET Status='"+ result + "'" + " WHERE id=" + auditIDInt;
            statement.executeUpdate(sql);
            info += "审核成功!";
            return new Pair<Boolean, String>(flag, info);
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            info += "查询错误!    ";
            return new Pair<Boolean, String>(flag, info);
        }
    }

    public String getAuditingReservationInfo() {
        StringBuilder info = new StringBuilder();
        info.append("所有审核中的预约为:\n");
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM `order` WHERE Status='审核中'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                info = new StringBuilder("暂无审核中的预约");
                return info.toString();
            }
            resultSet.beforeFirst();
            while (resultSet.next()) {
                info.append("预约编号: ").append(resultSet.getString("id")).append(" 机房编号: ").append(resultSet.getString("ComputerRoomID"))
                        .append(" 开始时间: ").append(resultSet.getString("StartDateTime"))
                        .append(" 结束时间: ").append(resultSet.getString("EndDateTime"))
                        .append(" 姓名: ").append(resultSet.getString("StudentName"))
                        .append(" 学号: ").append(resultSet.getString("StudentID"))
                        .append(" 状态: ").append(resultSet.getString("Status")).append("\n\n");
            }
        } catch (SQLException e) {
            info = new StringBuilder("查询失败");
            throw new RuntimeException(e);
//            return info.toString();
        }
        return info.toString();
    }


}
