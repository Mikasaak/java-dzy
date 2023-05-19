package main.java.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationSystemGUI {
    private JFrame frame;
    private JPanel panel;
    private CardLayout cardLayout;
    private String previousPage;

    public ReservationSystemGUI() {
        frame = new JFrame("机房预约系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        cardLayout = new CardLayout();
        panel.setLayout(cardLayout);

        // 添加登录页面
        panel.add(createLoginPage(), "Login");
        // 添加学生操作页面
        panel.add(createStudentPage(), "Student");
        // 添加教师操作页面
        panel.add(createTeacherPage(), "Teacher");
        // 添加管理员操作页面
        panel.add(createAdminPage(), "Admin");

        cardLayout.show(panel, "Login"); // 初始显示登录页面

        frame.add(panel);
        frame.setVisible(true);
    }

    private JPanel createLoginPage() {
        JPanel loginPanel = new JPanel(new BorderLayout());

        // 身份选择下拉菜单
        String[] identities = {"教师", "学生", "管理员"};
        JComboBox<String> identityComboBox = new JComboBox<>(identities);
        loginPanel.add(identityComboBox, BorderLayout.NORTH);

        // 输入框
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("姓名:"));
        inputPanel.add(new JTextField());
        inputPanel.add(new JLabel("学号/职工号:"));
        inputPanel.add(new JTextField());
        inputPanel.add(new JLabel("密码:"));
        inputPanel.add(new JPasswordField());
        loginPanel.add(inputPanel, BorderLayout.CENTER);

        // 登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identity = (String) identityComboBox.getSelectedItem();
                if (identity.equals("教师")) {
                    previousPage = "Login"; // 记录上一级菜单
                    cardLayout.show(panel, "Teacher");
                } else if (identity.equals("学生")) {
                    previousPage = "Login"; // 记录上一级菜单
                    cardLayout.show(panel, "Student");
                } else if (identity.equals("管理员")) {
                    previousPage = "Login"; // 记录上一级菜单
                    cardLayout.show(panel, "Admin");
                }
            }
        });
        loginPanel.add(loginButton, BorderLayout.SOUTH);

        return loginPanel;
    }

    private JPanel createStudentPage() {
        JPanel studentPanel = new JPanel(new BorderLayout());

        // 返回上一级菜单按钮
        JButton backButton = new JButton("返回");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, previousPage);
            }
        });
        studentPanel.add(backButton, BorderLayout.NORTH);

        // 添加预约按钮
        JButton reserveButton = new JButton("添加预约");
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousPage = "Student"; // 记录上一级菜单
                cardLayout.show(panel, "AddReservation");
            }
        });
        studentPanel.add(reserveButton, BorderLayout.CENTER);

        // 查看我的预约按钮
        JButton myReservationButton = new JButton("查看我的预约");
        myReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousPage = "Student"; // 记录上一级菜单
                cardLayout.show(panel, "MyReservation");
            }
        });
        studentPanel.add(myReservationButton, BorderLayout.SOUTH);

        return studentPanel;
    }

    private JPanel createTeacherPage() {
        JPanel teacherPanel = new JPanel(new BorderLayout());

        // 返回上一级菜单按钮
        JButton backButton = new JButton("返回");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, previousPage);
            }
        });
        teacherPanel.add(backButton, BorderLayout.NORTH);

        // 审批学生预约按钮
        JButton approveButton = new JButton("审批学生预约");
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousPage = "Teacher"; // 记录上一级菜单
                cardLayout.show(panel, "ApproveReservation");
            }
        });
        teacherPanel.add(approveButton, BorderLayout.CENTER);

        // 显示所有预约按钮
        JButton allReservationButton = new JButton("显示所有预约");
        allReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousPage = "Teacher"; // 记录上一级菜单
                cardLayout.show(panel, "AllReservation");
            }
        });
        teacherPanel.add(allReservationButton, BorderLayout.SOUTH);

        return teacherPanel;
    }

    private JPanel createAdminPage() {
        JPanel adminPanel = new JPanel(new BorderLayout());

        // 添加账号按钮
        JButton addButton = new JButton("添加账号");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousPage = "Admin"; // 记录上一级菜单
                cardLayout.show(panel, "AddAccount");
            }
        });
        adminPanel.add(addButton, BorderLayout.NORTH);

        // 删除账号按钮
        JButton deleteButton = new JButton("删除账号");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousPage = "Admin"; // 记录上一级菜单
                cardLayout.show(panel, "DeleteAccount");
            }
        });
        adminPanel.add(deleteButton, BorderLayout.CENTER);

        // 清空记录按钮
        JButton clearButton = new JButton("清空记录");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousPage = "Admin"; // 记录上一级菜单
                cardLayout.show(panel, "ClearRecords");
            }
        });
        adminPanel.add(clearButton, BorderLayout.SOUTH);

        return adminPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReservationSystemGUI();
            }
        });
    }
}
