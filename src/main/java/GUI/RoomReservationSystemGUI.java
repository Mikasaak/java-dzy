package main.java.GUI;

import main.java.Manager;
import main.java.Operate;
import main.java.Student;
import main.java.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomReservationSystemGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private Student student = new Student("1", "1", "1");
    private Teacher teacher;
    private Manager manager;

    DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            // 调用父类方法获取默认的渲染组件
            Component component = super.getListCellRendererComponent(list, value, index, isSelected,
                    cellHasFocus);
            // 设置水平对齐方式为居中对齐
            setHorizontalAlignment(CENTER);
            return component;
        }
    };
    Font fontBig = new Font("", Font.BOLD, 18);

    private void SetTextFormatJLabel(JLabel jLabel) {
        jLabel.setFont(fontBig);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    private void SetTextFormatJTextField(JTextField jTextField) {
        jTextField.setFont(fontBig);
        jTextField.setHorizontalAlignment(JTextField.CENTER);
    }
    private void SetTextFormatJButton(JButton jButton) {
        jButton.setFont(fontBig);
        jButton.setHorizontalAlignment(JButton.CENTER);
    }
    private void SetTextFormatJComboBox(JComboBox jComboBox) {
        jComboBox.setFont(fontBig);
        jComboBox.setRenderer(renderer);
    }



    public RoomReservationSystemGUI() {
        setTitle("机房预约系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 400);
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

        // 添加教师操作界面
        JPanel teacherPanel = createTeacherPanel();
        cardPanel.add(teacherPanel, "Teacher");

        // 添加管理员界面
        JPanel adminPanel = createAdminPanel();
        cardPanel.add(adminPanel, "Admin");

        // 学生操作子界面
        // 添加添加预约界面
        JPanel addReservationPanel = createAddReservationPanel();
        cardPanel.add(addReservationPanel, "AddReservation");

        // 添加查看我的预约界面
        JPanel myReservationPanel = createMyReservationPanel(student);
        cardPanel.add(myReservationPanel, "MyReservation");

        // 添加查看所有预约界面
        JPanel allReservationsPanel = createAllReservationsPanel();
        cardPanel.add(allReservationsPanel, "AllReservations");

        // 添加取消预约界面
        JPanel cancelReservationPanel = createCancelReservationPanel();
        cardPanel.add(cancelReservationPanel, "CancelReservation");

        //教师操作子界面
        //添加审核预约界面
        JPanel auditOrderPanel = createAuditOrderPanel();
        cardPanel.add(auditOrderPanel, "AuditOrder");

        //添加查看所有预约界面
        JPanel checkAllOrderPanel = createCheckAllOrderPanel();
        cardPanel.add(checkAllOrderPanel, "CheckAllOrder");

        //添加查看学生账号界面
        JPanel checkStudentAccountPanel = createCheckStudentAccountPanel();
        cardPanel.add(checkStudentAccountPanel, "CheckStudentAccount");

        //管理员操作子界面
        //添加查看账号界面
        JPanel checkAccountPanel = createCheckAccountPanel();
        cardPanel.add(checkAccountPanel, "CheckAccount");

        //添加添加账号界面
        JPanel addAccountPanel = createAddAccountPanel();
        cardPanel.add(addAccountPanel, "AddAccount");

        //添加删除账号界面
        JPanel deleteAccountPanel = createDeleteAccountPanel();
        cardPanel.add(deleteAccountPanel, "DeleteAccount");

        //添加清空预约界面
        JPanel clearReservationPanel = createClearReservationPanel();
        cardPanel.add(clearReservationPanel, "ClearReservation");

        add(cardPanel);

        // 显示登录页面
        showLoginPage();
    }






    //登录界面
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));

        JLabel titleLabel = new JLabel("登录页面");
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel);

        // 下拉选择菜单选择身份
        String[] identities = {"教师", "学生", "管理员"};
        JComboBox<String> identityComboBox = new JComboBox<>(identities);
        identityComboBox.setRenderer(renderer);
        SetTextFormatJComboBox(identityComboBox);
        panel.add(identityComboBox);

        // 输入姓名
        JLabel nameLabel = new JLabel("姓名:");
        JTextField nameTextField = new JTextField();
        // 设置输入框的字体
        SetTextFormatJLabel(nameLabel);
        SetTextFormatJTextField(nameTextField);
        panel.add(nameLabel);
        panel.add(nameTextField);

        // 输入学号/工号
        JLabel idLabel = new JLabel("账号(学号/工号):");
        JTextField idTextField = new JTextField();
        // 设置输入框的字体
        SetTextFormatJLabel(idLabel);
        SetTextFormatJTextField(idTextField);
        panel.add(idLabel);
        panel.add(idTextField);

        // 输入密码
        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordField = new JPasswordField();

        // 设置输入框的字体
        SetTextFormatJLabel(passwordLabel);
        SetTextFormatJTextField(passwordField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        // 登录按钮
        JButton loginButton = new JButton("登录");
        SetTextFormatJButton(loginButton);
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
                        if(Operate.loginPart(name,id,password,'1')) {
                            JOptionPane.showMessageDialog(panel, "登录成功！", "登录成功", JOptionPane.INFORMATION_MESSAGE);
                            showStudentPage();
                        }
                        else {
                            JOptionPane.showMessageDialog(panel, "登录失败，请检查您的登录信息。", "登录失败", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (identity.equals("教师")) {
                        if(Operate.loginPart(name,id,password,'2')) {
                            JOptionPane.showMessageDialog(panel, "登录成功！", "登录成功", JOptionPane.INFORMATION_MESSAGE);
                            showTeacherPage();
                        }
                        else {
                            JOptionPane.showMessageDialog(panel, "登录失败，请检查您的登录信息。", "登录失败", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (identity.equals("管理员")) {
                        if(Operate.loginPart(name,id,password,'3')) {
                            JOptionPane.showMessageDialog(panel, "登录成功！", "登录成功", JOptionPane.INFORMATION_MESSAGE);
                            showAdminPage();
                        }
                        else {
                            JOptionPane.showMessageDialog(panel, "登录失败，请检查您的登录信息。", "登录失败", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "登录失败，请检查您的登录信息。", "登录失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

    //学生操作界面
    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 1));

        JLabel titleLabel = new JLabel("学生操作页面");
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel);

        // 添加预约按钮
        JButton addReservationButton = new JButton("添加预约");
        SetTextFormatJButton(addReservationButton);
        panel.add(addReservationButton);

        // 添加查看我的预约按钮
        JButton myReservationButton = new JButton("查看我的预约");
        SetTextFormatJButton(myReservationButton);
        panel.add(myReservationButton);

        JButton AllReservationButton = new JButton("查看所有预约");
        SetTextFormatJButton(AllReservationButton);
        panel.add(AllReservationButton);
        // 添加取消预约按钮
        JButton cancelReservationButton = new JButton("取消预约");
        SetTextFormatJButton(cancelReservationButton);
        panel.add(cancelReservationButton);

        // 返回按钮
        JButton backButton = new JButton("返回");
        SetTextFormatJButton(backButton);
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

    //添加预约界面
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
                boolean reservationAdded = student.addReservation(computerRoom, StartDate, EndDate);

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

    //查看我的预约界面
    private JPanel createMyReservationPanel(Student student) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("我的预约");
        panel.add(titleLabel, BorderLayout.NORTH);

        // 创建用于显示数据的文本区域
        JTextArea reservationTextArea = null;
//        JScrollPane scrollPane = new JScrollPane(reservationTextArea);
        reservationTextArea = new JTextArea();
        reservationTextArea.setEditable(false); // 设置为只读
        reservationTextArea.setLineWrap(true); // 自动换行

        // 创建JScrollPane，并将JTextArea添加到其中
        JScrollPane scrollPane = new JScrollPane(reservationTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // 显示垂直滚动条

        // 将JScrollPane添加到面板的中间区域
        panel.add(scrollPane, BorderLayout.CENTER);

        // 获取预约信息
        String reservationInfo = student.getReservationInfo();
        // 添加内容到JTextArea
        reservationTextArea.append(reservationInfo);
//        panel.add(scrollPane, BorderLayout.CENTER);

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

    //查看所有预约界面
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

    //取消预约界面
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


    //教师操作界面
    private JPanel createTeacherPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1));
        JLabel titleLabel = new JLabel("教师操作页面");
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel);

        // 添加审批预约按钮
        JButton auditOrderButton = new JButton("审批记录");
        SetTextFormatJButton(auditOrderButton);
        panel.add(auditOrderButton);

        // 添加查看所有预约记录按钮
        JButton viewAllRecordsButton = new JButton("查看所有记录");
        SetTextFormatJButton(viewAllRecordsButton);
        panel.add(viewAllRecordsButton);

        // 添加查看学生账号按钮
        JButton viewStudentAccountButton = new JButton("查看学生账号");
        SetTextFormatJButton(viewStudentAccountButton);
        panel.add(viewStudentAccountButton);

        // 返回按钮
        JButton backButton = new JButton("返回");
        SetTextFormatJButton(backButton);
        panel.add(backButton);

        // 设置按钮的事件监听器
        auditOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAuditOrderPage();
            }
        });

        viewAllRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCheckAllOrderPage();
            }
        });

        viewStudentAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCheckStudentAccountPage();
            }
        });

        // 设置按钮的事件监听器
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginPage();
            }
        });

        return panel;
    }

    //审核预约界面
    private JPanel createAuditOrderPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JLabel titleLabel = new JLabel("审核预约");
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel);


        JButton backButton = new JButton("返回");
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTeacherPage();
            }
        });

        return panel;
    }

    //显示所有预约界面
    private JPanel createCheckAllOrderPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JLabel titleLabel = new JLabel("查看所有预约记录");
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel);


        JButton backButton = new JButton("返回");
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTeacherPage();
            }
        });

        return panel;
    }
    //查看学生账号界面
    private JPanel createCheckStudentAccountPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JLabel titleLabel = new JLabel("查看学生账号");
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel);


        JButton backButton = new JButton("返回");
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTeacherPage();
            }
        });

        return panel;
    }


    //管理员操作界面
    private JPanel createAdminPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("管理员页面");
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel);
//        CheckAccountPanel
//                AddAccountPanel
//        DeleteAccountPanel
//                ClearReservationPanel

        // 添加查看账号按钮
        JButton checkAccountButton = new JButton("查看账号");
        SetTextFormatJButton(checkAccountButton);
        panel.add(checkAccountButton);

        // 添加添加账号按钮
        JButton addAccountButton = new JButton("添加账号");
        SetTextFormatJButton(addAccountButton);
        panel.add(addAccountButton);

        // 添加删除账号按钮
        JButton deleteAccountButton = new JButton("删除账号");
        SetTextFormatJButton(deleteAccountButton);
        panel.add(deleteAccountButton);

        // 返回按钮
        JButton backButton = new JButton("返回");
        SetTextFormatJButton(backButton);
        panel.add(backButton);

        // 设置按钮的事件监听器
        checkAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCheckAccountPage();
            }
        });

        addAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddAccountPage();
            }
        });

        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeleteAccountPage();
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

    //查看账号界面
    private JPanel createCheckAccountPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JLabel titleLabel = new JLabel("查看账号");
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel);


        JButton backButton = new JButton("返回");
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminPage();
            }
        });

        return panel;
    }

    //添加账号界面
    private JPanel createAddAccountPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JLabel titleLabel = new JLabel("添加账号");
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel);


        JButton backButton = new JButton("返回");
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminPage();
            }
        });

        return panel;
    }

    //删除账号界面
    private JPanel createDeleteAccountPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JLabel titleLabel = new JLabel("删除账号");
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel);


        JButton backButton = new JButton("返回");
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminPage();
            }
        });

        return panel;

    }



    //清空预约界面
    private JPanel createClearReservationPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JLabel titleLabel = new JLabel("清空预约记录");
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel);


        JButton backButton = new JButton("返回");
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminPage();
            }
        });

        return panel;
    }



    // 登录验证
    private boolean performLogin(String identity, String name, String id, String password) {
        // 执行登录验证的逻辑
        // 返回登录验证结果，登录成功返回true，登录失败返回false
        return true;//test
//        return false;
    }

    // 添加预约
    private boolean addReservation(String date, String timeSlot, String purpose) {
        // 执行添加预约的逻辑
        // 返回添加预约的结果，添加成功返回true，添加失败返回false
        return false;
    }


    // 取消预约
    private boolean cancelReservation(String reservationNumber) {
        // 执行取消预约的逻辑
        // 返回取消预约的结果，取消成功返回true，取消失败返回false
        return false;
    }


    // 查看所有预约记录
    private void showLoginPage() {
        cardLayout.show(cardPanel, "Login");
    }

    // 显示学生页面
    private void showStudentPage() {
        cardLayout.show(cardPanel, "Student");
    }

    // 显示添加预约页面
    private void showAddReservationPage() {
        cardLayout.show(cardPanel, "AddReservation");
    }

    // 显示我的预约页面
    private void showMyReservationPage() {
        cardLayout.show(cardPanel, "MyReservation");
    }

    // 显示所有预约记录页面
    private void showAllReservationsPage() {
        cardLayout.show(cardPanel, "AllReservations");
    }

    // 显示取消预约页面
    private void showCancelReservationPage() {
        cardLayout.show(cardPanel, "CancelReservation");
    }

    // 显示教师页面
    private void showTeacherPage() {
        cardLayout.show(cardPanel, "Teacher");
    }
    // 显示审批预约页面
    private void showAuditOrderPage() {
        cardLayout.show(cardPanel, "AuditOrder");
    }
    // 显示查看所有预约记录页面
    private void showCheckAllOrderPage() {
        cardLayout.show(cardPanel, "CheckAllOrder");
    }
    // 显示查看学生账号页面
    private void showCheckStudentAccountPage() {
        cardLayout.show(cardPanel, "CheckStudentAccount");
    }

    // 显示管理员页面
    private void showAdminPage() {
        cardLayout.show(cardPanel, "Admin");
    }
//    CheckAccountPanel
//            AddAccountPanel
//    DeleteAccountPanel
//            ClearReservationPanel
    private void showCheckAccountPage() {
        cardLayout.show(cardPanel, "CheckAccount");
    }
    private void showAddAccountPage() {
        cardLayout.show(cardPanel, "AddAccount");
    }
    private void showDeleteAccountPage() {
        cardLayout.show(cardPanel, "DeleteAccount");
    }
    private void showClearReservationPage() {
        cardLayout.show(cardPanel, "ClearReservation");
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
