package main.java;

import lombok.ToString;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


@ToString
public class Student extends Identity{


    private String jdbcUrl = "jdbc:mysql://localhost:3306/dzy-java?useSSL=false&serverTimezone=UTC";
    private String sqlusername = "root";
    private String sqlpassword = "root";
    private String StudentID;// 学号
    public Student(String StudentID, String password, String name) {
        this.StudentID = StudentID;
        super.password=password;
        super.name =name;
    }


    @Override
    //显示操作菜单
    public void menu() {
        Screen.studentOperateScreen(this.name);
    }

    /**
     * @return 是否添加成功
     */
    //申请预约
    public void applyOrder() {
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM computerroom";
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("机房信息如下");
            while (resultSet.next()) {
                String computerRoomID = resultSet.getString("ComputerRoomID");
                int size = resultSet.getInt("Size");
                // 其他列的获取和输出

                System.out.println("机房编号: " + computerRoomID + ", 容纳人数: " + size);
                // 其他列的输出

            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;// 如果出现SQL// 异常则直接返回
        }

        while (true) {
            System.out.println("请输入您要预约的机房编号/若退出该层输入 ”*” ");
            String roomID = scanner.next();
            if(roomID.equals("*")){
                return;
            }
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM computerroom WHERE ComputerRoomID = " + roomID;
                ResultSet resultSet = statement.executeQuery(sql);
                if (!resultSet.next()) {
                    resultSet.close();
                    statement.close();
                    connection.close();
                    System.out.println("您输入的机房编号不存在, 请重新输入\n");
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("您输入的机房编号不存在, 请重新输入\n");
                continue;
            }
            System.out.println("请输入您要预约的开始时间/格式为年-月-日 时:分 例:2020-01-01 12:00");
            // 清除缓冲区中的剩余内容
            scanner.nextLine();
            String date = scanner.nextLine();
//            System.out.println(date);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date applystartdate = null;
            try {
                applystartdate = format.parse(date);
            }
            catch (ParseException e) {// 如果输入的日期格式不正确则重新输入
                System.out.println("您输入的日期格式有误, 请重新输入\n");
                continue;
            }
//            System.out.println(applystartdate.toString());

            System.out.println("请输入您要预约的结束时间/格式为年-月-日 时:分 例:2020-01-01 12:00");

            // 清除缓冲区中的剩余内容
            String date2 = scanner.nextLine().trim(); // 获取输入的日期字符串并去除首尾空格
//            System.out.println(date2);
            Date applyenddate = null;
            try {
                applyenddate = format.parse(date2);
            } catch (ParseException e) {
                System.out.println("您输入的日期格式有误, 请重新输入\n");
                continue;
            }
            System.out.println(applyenddate.toString());
            boolean fal = true;
            if (applystartdate.after(applyenddate)) {
                System.out.println("结束时间不能早于开始时间");
                continue;
            }
            if (applystartdate.equals(applyenddate)) {
                System.out.println("开始时间不能等于结束时间");
                continue;
            }
            if (applystartdate.before(new Date())) {
                System.out.println("开始时间不能早于当前时间");
                continue;
            }
            if (applystartdate.getHours() < 8 || applystartdate.getHours() > 22) {
                System.out.println("开始时间必须在8点到22点之间");
                continue;
            }
            if (applyenddate.getHours() < 8 || applyenddate.getHours() > 22) {
                System.out.println("结束时间必须在8点到22点之间");
                continue;
            }
            else {

//                检测时间是否冲突
                try {
                    Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
                    Statement statement = connection.createStatement();
                    String sql = "SELECT * FROM `order` WHERE ComputerRoomID = " + roomID;
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        Date startdate = format.parse(resultSet.getString("StartDateTime"));
//                        System.out.println("startdate " + startdate.toString());
                        Date enddate = format.parse(resultSet.getString("EndDateTime"));
//                        System.out.println("enddate " + enddate.toString());
                        // 如果申请的开始时间在已有的开始时间和结束时间之间
                        if (applystartdate.after(startdate) && applystartdate.before(enddate)) {
                            fal = false;
                            break;
                        }
                    // 如果申请的结束时间在已有的开始时间和结束时间之间
                        if (applyenddate.after(startdate) && applyenddate.before(enddate)) {
                            fal = false;
                            break;
                        }
                    // 如果申请的开始时间和结束时间都在已有的开始时间和结束时间之间
                        if (applystartdate.before(startdate) && applyenddate.after(enddate)) {
                            fal = false;
                            break;
                        }
                    }
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("与已有时间冲突, 请重新预约\n");
                    continue;
                }
                if (!fal) {
                    System.out.println("与已有时间冲突, 请重新预约\n");
                    continue;
                }
                // 预约成功，添加到数据库
                try {
                    Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
                    Statement statement = connection.createStatement();
                    String sqlInsert = "INSERT INTO `order`\n" +
                            "(`order`.ComputerRoomID,`order`.StartDateTime,`order`.EndDateTime,`order`.StudentName,`order`.StudentID,`order`.`Status`)\n" +
                            "VALUES\n" +
                            "('"+ roomID +"','"+ date +"','"+date2+"','"+ this.StudentID +"','"+ super.name +"','审核中')";
                    statement.executeUpdate(sqlInsert);
                    statement.close();
                    connection.close();

                } catch (Exception e) {
                    System.out.println("预约失败, 请重新预约");
                    e.printStackTrace();
                    continue;
                }
                System.out.println("预约成功");
                break;
            }

        }
    }


    //显示自己的预约
    public void showMyOrder() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM `order` WHERE StudentID = " + this.StudentID + " AND StudentName = " + super.name;
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("您的预约为:");
            while (resultSet.next()) {
                System.out.println("预约编号: "+ resultSet.getString("id")+" 机房编号: " + resultSet.getString("ComputerRoomID") + " 开始时间: " + resultSet.getString("StartDateTime") + " 结束时间: " + resultSet.getString("EndDateTime") + " 状态: " + resultSet.getString("Status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //展示所有预约
    public void showAllOrder() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM `order` WHERE Status='审核中' OR Status='已通过'";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("所有预约为:");
            while (resultSet.next()) {
                System.out.println("预约编号: "+ resultSet.getString("id") + " 机房编号: " + resultSet.getString("ComputerRoomID") + " 开始时间: " + resultSet.getString("StartDateTime") + " 结束时间: " + resultSet.getString("EndDateTime") + " 状态: " + resultSet.getString("Status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //取消预约
    public void cancelMyOrder() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            this.showMyOrder();
            System.out.println("请输入您要取消的预约编号");
            String orderID = scanner.nextLine().trim();
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM `order` WHERE StudentID = " + this.StudentID + " AND StudentName = " + super.name + " AND id = " + orderID;
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    String sqlDelete = "DELETE FROM `order` WHERE id = " + orderID;
                    statement.executeUpdate(sqlDelete);
                    System.out.println("取消成功");
                    break;
                } else {
                    System.out.println("您没有该预约");
                    continue;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
