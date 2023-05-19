package main.java.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomReservationSystemGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public RoomReservationSystemGUI() {
        setTitle("机房预约系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // 创建主面板，使用CardLayout来管理不同页面的显示
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 添加登录页面
        JPanel loginPanel = createLoginPanel();
        cardPanel.add(loginPanel, "Login");

        // 添加学生操作界面
        JPanel studentPanel = createStudentPanel();
        cardPanel.add(studentPanel, "Student");

        // 添加添加预约界面
        JPanel addReservationPanel = createAddReservationPanel();
        cardPanel.add(addReservationPanel, "AddReservation");

        // 添加查看我的预约界面
        JPanel myReservationPanel = createMyReservationPanel();
        cardPanel.add(myReservationPanel, "MyReservation");

        // 添加查看所有预约界面
        JPanel allReservationsPanel = createAllReservationsPanel();
        cardPanel.add(allReservationsPanel, "AllReservations");

        // 添加取消预约界面
        JPanel cancelReservationPanel = createCancelReservationPanel();
        cardPanel.add(cancelReservationPanel, "CancelReservation");

        // 添加教师操作界面
        JPanel teacherPanel = createTeacherPanel();
        cardPanel.add(teacherPanel, "Teacher");

        // 添加管理员界面
        JPanel adminPanel = createAdminPanel();
        cardPanel.add(adminPanel, "Admin");

        add(cardPanel);

        // 显示登录页面
        showLoginPage();
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));

        JLabel titleLabel = new JLabel("登录页面");
        panel.add(titleLabel);

        // 下拉选择菜单选择身份
        String[] identities = {"教师", "学生", "管理员"};
        JComboBox<String> identityComboBox = new JComboBox<>(identities);
        panel.add(identityComboBox);

        // 输入姓名
        JLabel nameLabel = new JLabel("姓名:");
        JTextField nameTextField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameTextField);

        // 输入学号/工号
        JLabel idLabel = new JLabel("学号/工号:");
        JTextField idTextField = new JTextField();
        panel.add(idLabel);
        panel.add(idTextField);

        // 输入密码
        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordLabel);
        panel.add(passwordField);

        // 登录按钮
        JButton loginButton = new JButton("登录");
        panel.add(loginButton);

        // 设置登录按钮的事件监听器
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identity = (String) identityComboBox.getSelectedItem();
                String name = nameTextField.getText();
                String id = idTextField.getText();
                String password = new String(passwordField.getPassword());

                // 根据身份和登录信息进行登录验证
                boolean loginSuccess = performLogin(identity, name, id, password);

                if (loginSuccess) {
                    if (identity.equals("学生")) {
                        showStudentPage();
                    } else if (identity.equals("教师")) {
                        showTeacherPage();
                    } else if (identity.equals("管理员")) {
                        showAdminPage();
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "登录失败，请检查您的登录信息。", "登录失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));

        JLabel titleLabel = new JLabel("学生操作页面");
        panel.add(titleLabel);

        // 添加预约按钮
        JButton addReservationButton = new JButton("添加预约");
        panel.add(addReservationButton);

        // 添加查看我的预约按钮
        JButton myReservationButton = new JButton("查看我的预约");
        panel.add(myReservationButton);

        // 添加取消预约按钮
        JButton cancelReservationButton = new JButton("取消预约");
        panel.add(cancelReservationButton);

        // 返回按钮
        JButton backButton = new JButton("返回");
        panel.add(backButton);

        // 设置按钮的事件监听器
        addReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddReservationPage();
            }
        });

        myReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMyReservationPage();
            }
        });

        cancelReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCancelReservationPage();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginPage();
            }
        });

        return panel;
    }

    private JPanel createAddReservationPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 1));


        // 输入预约机房编号
        JLabel computerroom = new JLabel("机房编号:");
        JTextField computerroomTextField = new JTextField();
        panel.add(computerroom);
        panel.add(computerroomTextField);

        // 输入预约的开始时间
        JLabel startdate = new JLabel("预约的开始时间:");
        JTextField startdateTextField = new JTextField();
        panel.add(startdate);
        panel.add(startdateTextField);

        // 输入预约的结束时间
        JLabel endDate = new JLabel("预约的结束时间:");
        JTextField endDateTextField = new JTextField();
        panel.add(endDate);

        panel.add(endDateTextField);

        // 提交按钮
        JButton submitButton = new JButton("提交");
        panel.add(submitButton);

        // 返回按钮
        JButton backButton = new JButton("返回");
        panel.add(backButton);

        // 设置按钮的事件监听器
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String computerRoom = computerroomTextField.getText();
                String StartDate = startdateTextField.getText();
                String EndDate = endDateTextField.getText();

                // 执行添加预约的操作
                boolean reservationAdded = addReservation(computerRoom, StartDate, EndDate);

                if (reservationAdded) {
                    JOptionPane.showMessageDialog(panel, "预约添加成功。", "添加预约", JOptionPane.INFORMATION_MESSAGE);
                    showStudentPage();
                } else {
                    JOptionPane.showMessageDialog(panel, "预约添加失败，请检查输入信息。", "添加预约失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStudentPage();
            }
        });

        return panel;
    }

    private JPanel createMyReservationPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("我的预约");
        panel.add(titleLabel, BorderLayout.NORTH);

        // 创建用于显示数据的文本区域
        JTextArea reservationTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(reservationTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 返回按钮
        JButton backButton = new JButton("返回");
        panel.add(backButton, BorderLayout.SOUTH);

        // 设置按钮的事件监听器
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStudentPage();
            }
        });

        return panel;
    }

    private JPanel createAllReservationsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("所有预约记录");
        panel.add(titleLabel, BorderLayout.NORTH);

        // 创建用于显示数据的文本区域
        JTextArea reservationTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(reservationTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 返回按钮
        JButton backButton = new JButton("返回");
        panel.add(backButton, BorderLayout.SOUTH);

        // 设置按钮的事件监听器
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTeacherPage();
            }
        });

        return panel;
    }

    private JPanel createCancelReservationPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("取消预约页面");
        panel.add(titleLabel);

        // 输入要取消的预约编号
        JLabel reservationNumberLabel = new JLabel("预约编号:");
        JTextField reservationNumberTextField = new JTextField();
        panel.add(reservationNumberLabel);
        panel.add(reservationNumberTextField);

        // 提交按钮
        JButton submitButton = new JButton("提交");
        panel.add(submitButton);

        // 返回按钮
        JButton backButton = new JButton("返回");
        panel.add(backButton);

        // 设置按钮的事件监听器
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reservationNumber = reservationNumberTextField.getText();

                // 执行取消预约的操作
                boolean reservationCanceled = cancelReservation(reservationNumber);

                if (reservationCanceled) {
                    JOptionPane.showMessageDialog(panel, "预约取消成功。", "取消预约", JOptionPane.INFORMATION_MESSAGE);
                    showStudentPage();
                } else {
                    JOptionPane.showMessageDialog(panel, "预约取消失败，请检查输入信息。", "取消预约失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStudentPage();
            }
        });

        return panel;
    }

    private JPanel createTeacherPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("教师操作页面");
        panel.add(titleLabel);

        // 添加查看审批记录按钮
        JButton viewApprovalRecordsButton = new JButton("审批记录");
        panel.add(viewApprovalRecordsButton);

        // 添加查看所有预约记录按钮
        JButton viewAllRecordsButton = new JButton("查看所有记录");
        panel.add(viewAllRecordsButton);

        // 返回按钮
        JButton backButton = new JButton("返回");
        panel.add(backButton);

        // 设置按钮的事件监听器
        viewApprovalRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showApprovalRecordsPage();
            }
        });

        viewAllRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllReservationsPage();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginPage();
            }
        });

        return panel;
    }

    private JPanel createAdminPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("管理员页面");
        panel.add(titleLabel);

        // 添加查看所有预约记录按钮
        JButton viewAllRecordsButton = new JButton("查看所有记录");
        panel.add(viewAllRecordsButton);

        // 添加取消预约按钮
        JButton cancelReservationButton = new JButton("取消预约");
        panel.add(cancelReservationButton);

        // 返回按钮
        JButton backButton = new JButton("返回");
        panel.add(backButton);

        // 设置按钮的事件监听器
        viewAllRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllReservationsPage();
            }
        });

        cancelReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCancelReservationPage();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginPage();
            }
        });

        return panel;
    }

    private boolean performLogin(String identity, String name, String id, String password) {
        // 执行登录验证的逻辑
        // 返回登录验证结果，登录成功返回true，登录失败返回false
        return true;//test
//        return false;
    }

    private boolean addReservation(String date, String timeSlot, String purpose) {
        // 执行添加预约的逻辑
        // 返回添加预约的结果，添加成功返回true，添加失败返回false
        return false;
    }

    private boolean cancelReservation(String reservationNumber) {
        // 执行取消预约的逻辑
        // 返回取消预约的结果，取消成功返回true，取消失败返回false
        return false;
    }

    private void showLoginPage() {
        cardLayout.show(cardPanel, "Login");
    }

    private void showStudentPage() {
        cardLayout.show(cardPanel, "Student");
    }

    private void showAddReservationPage() {
        cardLayout.show(cardPanel, "AddReservation");
    }

    private void showMyReservationPage() {
        cardLayout.show(cardPanel, "MyReservation");
    }

    private void showAllReservationsPage() {
        cardLayout.show(cardPanel, "AllReservations");
    }

    private void showCancelReservationPage() {
        cardLayout.show(cardPanel, "CancelReservation");
    }

    private void showTeacherPage() {
        cardLayout.show(cardPanel, "Teacher");
    }

    private void showAdminPage() {
        cardLayout.show(cardPanel, "Admin");
    }

    private void showApprovalRecordsPage() {
        // 显示审批记录页面的逻辑
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RoomReservationSystemGUI().setVisible(true);
            }
        });
    }
}
