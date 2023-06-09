package main.java;

import lombok.Getter;
import lombok.ToString;
import main.java.GUI.Pair;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Getter
@ToString
public class Student extends Identity {


    private String jdbcUrl = "jdbc:mysql://localhost:3306/dzy-java?useSSL=false&serverTimezone=UTC";
    private String sqlusername = "root";
    private String sqlpassword = "root";
    private String StudentID;// 学号

    public Student(String StudentID, String password, String name) {
        this.StudentID = StudentID;
        super.password = password;
        super.name = name;
    }

    public String getName() {
        return super.name;
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
            if (roomID.equals("*")) {
                return;
            }
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM computerroom WHERE ComputerRoomID = '" + roomID + "'";
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
            } catch (ParseException e) {// 如果输入的日期格式不正确则重新输入
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
            } else {

//                检测时间是否冲突
                try {
                    Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
                    Statement statement = connection.createStatement();
                    String sql = "SELECT * FROM `order` WHERE ComputerRoomID = '" + roomID + "'";
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
                            "('" + roomID + "','" + date + "','" + date2 + "','" + super.name + "','" + this.StudentID + "','审核中')";
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
//            Statement statement = connection.createStatement();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM `order` WHERE StudentID = '" + this.StudentID + "'" + " AND StudentName = '" + super.name + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("您暂无预约");
                return;
            }
            resultSet.previous();
            System.out.println("您的预约为:");
            while (resultSet.next()) {
                System.out.println("预约编号: " + resultSet.getString("id") + " 机房编号: " + resultSet.getString("ComputerRoomID") + " 开始时间: " + resultSet.getString("StartDateTime") + " 结束时间: " + resultSet.getString("EndDateTime") + " 状态: " + resultSet.getString("Status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMyReservationInfo() {
        StringBuilder info = new StringBuilder();
        info.append("您的预约为:\n");
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
//            Statement statement = connection.createStatement();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM `order` WHERE StudentID = '" + this.StudentID+"'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                info = new StringBuilder("您暂无预约");
                return info.toString();
            }
            resultSet.beforeFirst();
            while (resultSet.next()) {
                info.append("预约编号: ").append(resultSet.getString("id"))
                        .append(" 机房编号: ").append(resultSet.getString("ComputerRoomID"))
                        .append(" 开始时间: ").append(resultSet.getString("StartDateTime"))
                        .append(" 结束时间: ").append(resultSet.getString("EndDateTime"))
                        .append(" 状态: ").append(resultSet.getString("Status")).append("\n\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return info.toString();
    }

    //展示所有预约
    public void showAllOrder() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
//            Statement statement = connection.createStatement();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM `order` WHERE Status='审核中' OR Status='审核通过'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("暂无审核中和审核通过的预约");
                return;
            }
            resultSet.beforeFirst();
            System.out.println("所有审核中和审核通过的预约为:");
            while (resultSet.next()) {
                System.out.println("预约编号: " + resultSet.getString("id") + " 机房编号: " + resultSet.getString("ComputerRoomID") + " 开始时间: " + resultSet.getString("StartDateTime") + " 结束时间: " + resultSet.getString("EndDateTime") + " 姓名: " + resultSet.getString("StudentName") + " 学号: " + resultSet.getString("StudentID") + " 状态: " + resultSet.getString("Status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                        .append(" 状态: ").append(resultSet.getString("Status")).append("\n\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return info.toString();
    }

    //取消预约
    public void cancelMyOrder() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            this.showMyOrder();
            System.out.println("请输入您要取消的预约编号|返回上一级请按“*”");
            String orderID = scanner.nextLine().trim();
            if (orderID.equals("*")) {
                break;
            }
            if (orderID.equals("")) {
                System.out.println("输入不能为空");
                continue;
            }
            if (!orderID.matches("[0-9]+")) {
                System.out.println("输入格式错误");
                continue;
            }
            int orderIDInt = Integer.parseInt(orderID);
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM `order` WHERE StudentID = '" + this.StudentID + "'" + " AND StudentName = '" + super.name + "'" + " AND id =" + orderIDInt;
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



    public Pair<Boolean, String> cancelMyOrderPart(String orderID) {
        String info = "";
        if (orderID.equals("")) {
//                System.out.println("输入不能为空!   ");
            info += "输入不能为空!  ";
            return new Pair<>(false, info);
        }
        if (!orderID.matches("[0-9]+")) {
//                System.out.println("输入格式错误");
            info += "输入格式错误!  ";
            return new Pair<>(false, info);
        }
        int orderIDInt = Integer.parseInt(orderID);
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM `order` WHERE StudentID = '" + this.StudentID + "'" + " AND StudentName = '" + super.name + "'" + " AND id =" + orderIDInt;
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String sqlDelete = "DELETE FROM `order` WHERE id = " + orderID;
                statement.executeUpdate(sqlDelete);
//              System.out.println("取消成功");
                info += "取消成功!  ";
                return new Pair<>(true, info);
            } else {
//              System.out.println("您没有该预约");
                info += "您没有该预约!  ";
                return new Pair<>(false, info);
            }
        } catch (SQLException e) {
            info += "数据库错误!  ";
            return new Pair<>(false, info);
        } catch (NumberFormatException e) {
//            System.out.println("输入格式错误");
            info += "输入格式错误!  ";
            return new Pair<>(false, info);
        }
    }

    public String getComputerRoomInfo() {
        StringBuilder info = new StringBuilder("机房信息如下:\n");
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM computerroom";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String computerRoomID = resultSet.getString("ComputerRoomID");
                int size = resultSet.getInt("Size");
                // 其他列的获取和输出
                info.append("机房编号: ").append(computerRoomID).append(" 容量: ").append(size).append("\n");
                // 其他列的输出
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;// 如果出现SQL// 异常则直接返回
        }
        return info.toString();

    }

    public Pair<Boolean, String> applyOrderPart(String computerRoomID, String startDateTime, String endDateTime) {
        boolean flag = false;
        String info = "";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM computerroom WHERE ComputerRoomID = '" + computerRoomID + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                resultSet.close();
                statement.close();
                connection.close();
                System.out.println("您输入的机房编号不存在, 请重新输入\n");
                flag = false;
                info += "机房编号不存在!    ";
                return new Pair<>(flag, info);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("您输入的机房编号不存在, 请重新输入\n");
            flag = false;
            info += "机房编号不存在!    ";
            return new Pair<>(flag, info);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date applystartdate = null;
        try {
            applystartdate = format.parse(startDateTime);
        } catch (ParseException e) {// 如果输入的日期格式不正确则重新输入
            flag = false;
            info += "输入的开始日期格式有误!    ";
            System.out.println("您输入的开始日期格式有误, 请重新输入\n");
            return new Pair<>(flag, info);
        }
        String date2 = endDateTime.trim(); // 获取输入的日期字符串并去除首尾空格
//            System.out.println(date2);
        Date applyenddate = null;
        try {
            applyenddate = format.parse(date2);
        } catch (ParseException e) {
            flag = false;
            info += "输入的结束日期格式有误!    ";
            System.out.println("您输入的结束日期格式有误, 请重新输入\n");
            return new Pair<>(flag, info);
        }
        boolean fal = true;
        if (applystartdate.after(applyenddate)) {
            flag = false;
            info += "结束时间不能早于开始时间!    ";
            System.out.println("结束时间不能早于开始时间");
            return new Pair<>(flag, info);
        }
        if (applystartdate.equals(applyenddate)) {
            flag = false;
            info += "开始时间不能等于结束时间!    ";
            System.out.println("开始时间不能等于结束时间");
            return new Pair<>(flag, info);
        }
        if (applystartdate.before(new Date())) {
            flag = false;
            info += "开始时间不能早于当前时间!    ";
            System.out.println("开始时间不能早于当前时间");
            return new Pair<>(flag, info);
        }
        if (applystartdate.getHours() < 8 || applystartdate.getHours() > 22) {
            flag = false;
            info += "开始时间必须在8点到22点之间!    ";
            System.out.println("开始时间必须在8点到22点之间");
            return new Pair<>(flag, info);
        }
        if (applyenddate.getHours() < 8 || applyenddate.getHours() > 22) {
            flag = false;
            info += "结束时间必须在8点到22点之间!    ";
            System.out.println("结束时间必须在8点到22点之间");
            return new Pair<>(flag, info);
        } else {
//                检测时间是否冲突
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM `order` WHERE ComputerRoomID = '" + computerRoomID + "'";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Date startdate = format.parse(resultSet.getString("StartDateTime"));
//                        System.out.println("startdate " + startdate.toString());
                    Date enddate = format.parse(resultSet.getString("EndDateTime"));
//                        System.out.println("enddate " + enddate.toString());
                    // 如果申请的开始时间在已有的开始时间和结束时间之间
                    if (applystartdate.after(startdate) && applystartdate.before(enddate)) {
                        fal = false;
                        flag = false;
                        info += "与已有时间冲突, 请重新预约!    ";
                        System.out.println("与已有时间冲突, 请重新预约\n");
                        return new Pair<>(flag, info);
                    }
                    // 如果申请的结束时间在已有的开始时间和结束时间之间
                    if (applyenddate.after(startdate) && applyenddate.before(enddate)) {
                        fal = false;
                        flag = false;
                        info += "与已有时间冲突, 请重新预约!    ";
                        System.out.println("与已有时间冲突, 请重新预约\n");
                    }
                    // 如果申请的开始时间和结束时间都在已有的开始时间和结束时间之间
                    if (applystartdate.before(startdate) && applyenddate.after(enddate)) {
                        fal = false;
                        flag = false;
                        info += "与已有时间冲突, 请重新预约!    ";
                        System.out.println("与已有时间冲突, 请重新预约\n");
                        return new Pair<>(flag, info);
                    }
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                fal = false;
                info += "与已有时间冲突, 请重新预约!    ";
                System.out.println("与已有时间冲突, 请重新预约\n");
                return new Pair<>(flag, info);
            }
            if (!fal) {
                flag = false;
                info += "与已有时间冲突, 请重新预约!    ";
                System.out.println("与已有时间冲突, 请重新预约\n");
                return new Pair<>(flag, info);
            }
            // 预约成功，添加到数据库
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, sqlpassword, sqlpassword);
                Statement statement = connection.createStatement();
                String sqlInsert = "INSERT INTO `order`\n" +
                        "(`order`.ComputerRoomID,`order`.StartDateTime,`order`.EndDateTime,`order`.StudentName,`order`.StudentID,`order`.`Status`)\n" +
                        "VALUES\n" +
                        "('" + computerRoomID + "','" + startDateTime + "','" + date2 + "','" + super.name + "','" +this.StudentID + "','审核中')";
                statement.executeUpdate(sqlInsert);
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
                info += "预约失败, 请重新预约!    ";
                System.out.println("预约失败, 请重新预约");
                return new Pair<>(flag, info);
            }
            flag = true;
            info += "预约成功!    ";
            System.out.println("预约成功");
            return new Pair<>(flag, info);
        }
    }
}
