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

                // 根据身份进行逻辑处理
                if (identity.equals("学生")) {
                    if (name.equals("") && id.equals("") && password.equals("")) {
                        JOptionPane.showMessageDialog(null, "登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        showStudentPage();
                    } else {
                        JOptionPane.showMessageDialog(null, "登录失败", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (identity.equals("教师")) {
                    if (name.equals("") && id.equals("") && password.equals("")) {
                        JOptionPane.showMessageDialog(null, "登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        showTeacherPage();
                    } else {
                        JOptionPane.showMessageDialog(null, "登录失败", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (identity.equals("管理员")) {
                    if (name.equals("") && id.equals("") && password.equals("")) {
                        JOptionPane.showMessageDialog(null, "登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        showAdminPage();
                    } else {
                        JOptionPane.showMessageDialog(null, "登录失败", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        return panel;
    }

    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("学生操作界面");
        panel.add(titleLabel);

        // 添加预约按钮
        JButton addReservationButton = new JButton("添加预约");
        panel.add(addReservationButton);

        // 查看我的预约按钮
        JButton myReservationButton = new JButton("查看我的预约");
        panel.add(myReservationButton);

        // 查看所有预约按钮
        JButton allReservationsButton = new JButton("查看所有预约");
        panel.add(allReservationsButton);

        // 取消预约按钮
        JButton cancelReservationButton = new JButton("取消预约");
        panel.add(cancelReservationButton);

        // 添加返回按钮
        JButton backButton = createBackButton();
        panel.add(backButton);

        // 设置添加预约按钮的事件监听器
        addReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddReservationPage();
            }
        });

        // 设置查看我的预约按钮的事件监听器
        myReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMyReservationPage();
            }
        });

        // 设置查看所有预约按钮的事件监听器
        allReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllReservationsPage();
            }
        });

        // 设置取消预约按钮的事件监听器
        cancelReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCancelReservationPage();
            }
        });

        return panel;
    }

    private JPanel createAddReservationPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("添加预约页面");
        panel.add(titleLabel);

        // 添加预约表单组件
        // ...

        // 提交预约按钮
        JButton submitButton = new JButton("提交预约");
        panel.add(submitButton);

        // 添加返回按钮
        JButton backButton = createBackButton();
        panel.add(backButton);

        // 设置提交预约按钮的事件监听器
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理提交预约逻辑
                JOptionPane.showMessageDialog(null, "预约提交成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createMyReservationPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("查看我的预约页面");
        panel.add(titleLabel);

        // 显示我的预约信息
        // ...

        // 添加返回按钮
        JButton backButton = createBackButton();
        panel.add(backButton);

        return panel;
    }

    private JPanel createAllReservationsPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("查看所有预约页面");
        panel.add(titleLabel);

        // 显示所有预约信息
        // ...

        // 添加返回按钮
        JButton backButton = createBackButton();
        panel.add(backButton);

        return panel;
    }

    private JPanel createCancelReservationPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("取消预约页面");
        panel.add(titleLabel);

        // 显示可取消的预约信息
        // ...

        // 取消预约按钮
        JButton cancelButton = new JButton("取消预约");
        panel.add(cancelButton);

        // 添加返回按钮
        JButton backButton = createBackButton();
        panel.add(backButton);

        // 设置取消预约按钮的事件监听器
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理取消预约逻辑
                JOptionPane.showMessageDialog(null, "预约取消成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createTeacherPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("教师操作界面");
        panel.add(titleLabel);

        // 查看所有预约按钮
        JButton allReservationsButton = new JButton("查看所有预约");
        panel.add(allReservationsButton);

        // 取消预约按钮
        JButton cancelReservationButton = new JButton("取消预约");
        panel.add(cancelReservationButton);

        // 添加返回按钮
        JButton backButton = createBackButton();
        panel.add(backButton);

        // 设置查看所有预约按钮的事件监听器
        allReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllReservationsPage();
            }
        });

        // 设置取消预约按钮的事件监听器
        cancelReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCancelReservationPage();
            }
        });

        return panel;
    }

    private JPanel createAdminPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("管理员界面");
        panel.add(titleLabel);

        // 查看所有预约按钮
        JButton allReservationsButton = new JButton("查看所有预约");
        panel.add(allReservationsButton);

        // 取消预约按钮
        JButton cancelReservationButton = new JButton("取消预约");
        panel.add(cancelReservationButton);

        // 添加返回按钮
        JButton backButton = createBackButton();
        panel.add(backButton);

        // 设置查看所有预约按钮的事件监听器
        allReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllReservationsPage();
            }
        });

        // 设置取消预约按钮的事件监听器
        cancelReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCancelReservationPage();
            }
        });

        return panel;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("返回");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginPage();
            }
        });
        return backButton;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RoomReservationSystemGUI().setVisible(true);
            }
        });
    }
}
