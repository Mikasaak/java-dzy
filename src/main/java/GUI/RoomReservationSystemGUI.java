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

    private JLabel CreateEmptyJLabel( int width, int height) {
        JLabel jLabel = new JLabel("");
        jLabel.setPreferredSize(new Dimension(width, height));
        SetTextFormatJLabel(jLabel);
        return jLabel;
    }


    private JLabel CreateJLabel( JPanel jPanel,String text, int width, int height, int left, int right) {
        JLabel jLabel = new JLabel(text);
        JLabel leftEmpty = CreateEmptyJLabel(left, height);
        JLabel rightEmpty = CreateEmptyJLabel(right, height);
        jLabel.setPreferredSize(new Dimension(width, height));
        SetTextFormatJLabel(jLabel);
        jPanel.add(leftEmpty);
        jPanel.add(jLabel);
        jPanel.add(rightEmpty);
        return jLabel;
    }
    private JTextField CreateJTextField( JPanel jPanel ,int width, int height,int left, int right) {
        JTextField jTextField = new JTextField();
        JLabel leftEmpty = CreateEmptyJLabel(left, height);
        JLabel rightEmpty = CreateEmptyJLabel(right, height);
        jTextField.setPreferredSize(new Dimension(width, height));
        SetTextFormatJTextField(jTextField);
        jPanel.add(leftEmpty);
        jPanel.add(jTextField);
        jPanel.add(rightEmpty);
        return jTextField;
    }
    private JPasswordField CreateJPasswordField(  JPanel jPanel,int width, int height,int left, int right) {
        JPasswordField jPasswordField = new JPasswordField();
        JLabel leftEmpty = CreateEmptyJLabel(left, height);
        JLabel rightEmpty = CreateEmptyJLabel(right, height);
        jPasswordField.setPreferredSize(new Dimension(width, height));
        SetTextFormatJTextField(jPasswordField);
        jPanel.add(leftEmpty);
        jPanel.add(jPasswordField);
        jPanel.add(rightEmpty);
        return jPasswordField;
    }
    private JButton CreateJButton( JPanel jPanel,String text, int width, int height,int left, int right) {
        JButton jButton = new JButton(text);
        JLabel leftEmpty = CreateEmptyJLabel(left, height);
        JLabel rightEmpty = CreateEmptyJLabel(right, height);
        jButton.setPreferredSize(new Dimension(width, height));
        SetTextFormatJButton(jButton);
        jPanel.add(leftEmpty);
        jPanel.add(jButton);
        jPanel.add(rightEmpty);
        return jButton;
    }
    private JComboBox<?> CreateJComboBox(JPanel jPanel , String[] items, int width, int height,int left, int right) {
        JComboBox<?> jComboBox = new JComboBox<>(items);
        JLabel leftEmpty = CreateEmptyJLabel(left, height);
        JLabel rightEmpty = CreateEmptyJLabel(right, height);
        jComboBox.setPreferredSize(new Dimension(width, height));
        // Apply text format settings
        SetTextFormatJComboBox(jComboBox);
        jComboBox.setSelectedIndex(0);
        jComboBox.setRenderer(renderer);
        jPanel.add(leftEmpty);
        jPanel.add(jComboBox);
        jPanel.add(rightEmpty);
        return jComboBox;
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


    public Student student=null;
    public Teacher teacher=null;
    private void addLoginPage(JPanel cardPanel) {
        // 添加登录页面
        JPanel loginPanel = createLoginPanel();
        cardPanel.add(loginPanel, "Login");
    }
    private void addStudentPage(JPanel cardPanel,Student student) {
        // 添加学生操作界面
        JPanel studentPanel = createStudentPanel(student);
        cardPanel.add(studentPanel, "Student");

    }
    private void addTeacherPage(JPanel cardPanel,Teacher teacher) {
        // 添加教师操作界面
        JPanel teacherPanel = createTeacherPanel(teacher);
        cardPanel.add(teacherPanel, "Teacher");
    }
    private void addAdminPage(JPanel cardPanel,Manager manager) {
        // 添加管理员界面
        JPanel adminPanel = createAdminPanel(manager);
        cardPanel.add(adminPanel, "Admin");
    }
    // 学生操作子界面
    //加入添加预约界面
    private void addAddReservationPage(JPanel cardPanel,Student student) {
        JPanel addReservationPanel = createAddReservationPanel(student);
        cardPanel.add(addReservationPanel, "AddReservation");
    }
    //加入查看预约界面
    private void addMyReservationPage(JPanel cardPanel,Student student) {
        JPanel myReservationPanel = createMyReservationPanel(student);
        cardPanel.add(myReservationPanel, "MyReservation");
    }
    private void addAllReservationPage(JPanel cardPanel,Student student) {
        JPanel allReservationPage = createAllReservationsPanel(student);
        cardPanel.add(allReservationPage, "AllReservation");
    }

    //加入取消预约界面
    private void addCancelReservationPage(JPanel cardPanel,Student student) {
        JPanel cancelReservationPanel = createCancelReservationPanel(student);
        cardPanel.add(cancelReservationPanel, "CancelReservation");
    }


    //教师操作子界面
    //        //教师操作子界面
//        //添加审核预约界面
//        JPanel auditOrderPanel = createAuditOrderPanel(teacher);
//        cardPanel.add(auditOrderPanel, "AuditOrder");

    private void addAuditOrderPage(JPanel cardPanel,Teacher teacher) {
        JPanel auditOrderPanel = createAuditOrderPanel(teacher);
        cardPanel.add(auditOrderPanel, "AuditOrder");
    }
//
//        //添加查看所有预约界面
//        JPanel checkAllOrderPanel = createCheckAllOrderPanel(teacher);
//        cardPanel.add(checkAllOrderPanel, "CheckAllOrder");
    private void addCheckAllOrderPage(JPanel cardPanel,Teacher teacher) {
        JPanel checkAllOrderPanel = createCheckAllOrderPanel(teacher);
        cardPanel.add(checkAllOrderPanel, "CheckAllOrder");
    }
//
//        //添加查看学生账号界面
//        JPanel checkStudentAccountPanel = createCheckStudentAccountPanel(teacher);
//        cardPanel.add(checkStudentAccountPanel, "CheckStudentAccount");

    private void addCheckStudentAccountPage(JPanel cardPanel,Teacher teacher) {
        JPanel checkStudentAccountPanel = createCheckStudentAccountPanel(teacher);
        cardPanel.add(checkStudentAccountPanel, "CheckStudentAccount");
    }
//
//        //管理员操作子界面
//        //添加查看账号界面
//        JPanel checkAccountPanel = createCheckAccountPanel(manager);
//        cardPanel.add(checkAccountPanel, "CheckAccount");
    private void addCheckAccountPage(JPanel cardPanel,Manager manager) {
        JPanel checkAccountPanel = createCheckAccountPanel(manager);
        cardPanel.add(checkAccountPanel, "CheckAccount");
    }
//
//        //添加添加账号界面
//        JPanel addAccountPanel = createAddAccountPanel(manager);
//        cardPanel.add(addAccountPanel, "AddAccount");
//
    private void addAddAccountPage(JPanel cardPanel,Manager manager) {
        JPanel addAccountPanel = createAddAccountPanel(manager);
        cardPanel.add(addAccountPanel, "AddAccount");
    }
//        //添加删除账号界面
//        JPanel deleteAccountPanel = createDeleteAccountPanel(manager);
//        cardPanel.add(deleteAccountPanel, "DeleteAccount");
    private void addDeleteAccountPage(JPanel cardPanel,Manager manager) {
        JPanel deleteAccountPanel = createDeleteAccountPanel(manager);
        cardPanel.add(deleteAccountPanel, "DeleteAccount");
    }
//
//        //添加清空预约界面
//        JPanel clearReservationPanel = createClearReservationPanel(manager);
//        cardPanel.add(clearReservationPanel, "ClearReservation");
    private void addClearReservationPage(JPanel cardPanel,Manager manager) {
        JPanel clearReservationPanel = createClearReservationPanel(manager);
        cardPanel.add(clearReservationPanel, "ClearReservation");
    }







    public RoomReservationSystemGUI() {
        setTitle("机房预约系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 420);
        setResizable(false); // 禁用鼠标缩放功能
        setLocationRelativeTo(null);
        // 创建主面板，使用CardLayout来管理不同页面的显示
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        // 添加登录页面
        addLoginPage(cardPanel);
        add(cardPanel);
        // 显示登录页面
        showLoginPage();
    }

    //登录界面
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel titleLabel = CreateJLabel(panel,"登录界面",700,  50,0 ,0);//标题

        JLabel identity =  CreateJLabel(panel,"身份:",300,  50,0 ,0);//身份标签
        // 下拉选择菜单选择身份
        String[] identities = {"教师", "学生", "管理员"};
        JComboBox<String> identityComboBox =  (JComboBox<String>) CreateJComboBox(panel,identities, 400, 50,0 ,0);
        // 输入姓名
        JLabel nameLabel = CreateJLabel(panel,"姓名:",300,  50,0 ,0);//姓名标签
        JTextField nameTextField = CreateJTextField(panel,400, 50,0 ,0);//姓名输入框

        // 输入学号/工号
        JLabel idLabel = CreateJLabel(panel,"学号/工号:",300,  50,0 ,0);//学号/工号标签
        JTextField idTextField =  CreateJTextField(panel,400, 50,0 ,0);//学号/工号输入框
        // 输入密码
        JLabel passwordLabel =  CreateJLabel(panel,"密码:",300,  50,0 ,0);//密码标签
        JPasswordField passwordField = CreateJPasswordField(panel,400, 50,0 ,0);//密码输入框

        // 登录按钮
        JButton loginButton=CreateJButton(panel,"登录",300,  50,200 ,200);

        // 设置登录按钮的事件监听器
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identity = (String) identityComboBox.getSelectedItem();
                String name = nameTextField.getText();
                String id = idTextField.getText();
                String password = new String(passwordField.getPassword());
                passwordField.setText("");

                // 根据身份和登录信息进行登录验证
                boolean loginSuccess = performLogin(identity, name, id, password);

                if (loginSuccess) {
                    if (identity.equals("学生")) {
                        if(Operate.loginPart(id,password,'1')) {
                            JOptionPane.showMessageDialog(panel, "登录成功！", "登录成功", JOptionPane.INFORMATION_MESSAGE);
                            student = new Student("1", "1", "1");
                            addStudentPage(cardPanel,student);
                            showStudentPage();
                        }
                        else {
                            JOptionPane.showMessageDialog(panel, "登录失败，请检查您的登录信息。", "登录失败", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (identity.equals("教师")) {
                        if(Operate.loginPart(id,password,'2')) {
                            JOptionPane.showMessageDialog(panel, "登录成功！", "登录成功", JOptionPane.INFORMATION_MESSAGE);
                            teacher = new Teacher("1", "1", "1");
                            addTeacherPage(cardPanel,teacher);
                            showTeacherPage();
                        }
                        else {
                            JOptionPane.showMessageDialog(panel, "登录失败，请检查您的登录信息。", "登录失败", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (identity.equals("管理员")) {
                        if(Operate.loginPart(id,password,'3')) {
                            JOptionPane.showMessageDialog(panel, "登录成功！", "登录成功", JOptionPane.INFORMATION_MESSAGE);
                            Manager manager = new Manager("1", "1", "1");
                            addAdminPage(cardPanel,manager);
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

//    学生操作界面
    private JPanel createStudentPanel(Student student) {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // 添加标题
        JLabel titleLabel = CreateJLabel(panel,"学生操作界面"+student.getStudentID(),700,  60,0 ,0);

        // 添加预约按钮
        JButton addReservationButton = CreateJButton(panel,"预约",300,  80,50 ,0);

        // 添加查看我的预约按钮
        JButton myReservationButton = CreateJButton(panel,"查看我的预约",300,  80,20 ,0);


        // 添加查看所有预约按钮
        JButton AllReservationButton = CreateJButton(panel,"查看所有预约",300,  80,50 ,0);
        // 添加取消预约按钮
        JButton cancelReservationButton = CreateJButton(panel,"取消预约",300,  80,20 ,0);

        // 返回按钮
        JLabel emptyLabel = CreateJLabel(panel,"",700,  30,0 ,0);
        JButton backButton = CreateJButton(panel,"退出登录",300,  80,200 ,0);

        // 设置按钮的事件监听器

        final Student student1 = this.student;
        //预约按钮
        addReservationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addAddReservationPage(cardPanel,student1);
                showAddReservationPage();
            }
        });

        //查看我的预约按钮
        myReservationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addMyReservationPage(cardPanel,student1);
                showMyReservationPage();
            }
        });
        //查看所有预约按钮
        AllReservationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addAllReservationPage(cardPanel,student1);
                showAllReservationsPage();
            }
        });

        //取消预约按钮
        cancelReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCancelReservationPage(cardPanel,student1);
                showCancelReservationPage();
            }
        });

        //返回按钮


        student = this.student;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel, "退出登录成功！", "退出登录成功", JOptionPane.INFORMATION_MESSAGE);
                showLoginPage();
            }
        });

        return panel;
    }

    //添加预约界面
    private JPanel createAddReservationPanel(Student student) {
        JPanel panel = new JPanel(new GridLayout(6, 2));

        JLabel titleLabel = new JLabel("添加预约");
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel);
        JLabel emptyLabel = new JLabel("输入预约信息");
        SetTextFormatJLabel(emptyLabel);
        panel.add(emptyLabel);

        // 输入预约机房编号
        JLabel computerroom = new JLabel("机房编号:");
        JTextField computerroomTextField = new JTextField();
        SetTextFormatJLabel(computerroom);
        SetTextFormatJTextField(computerroomTextField);
        panel.add(computerroom);
        panel.add(computerroomTextField);

        // 输入预约的开始时间
        JLabel startdate = new JLabel("预约的开始时间:");
        JTextField startdateTextField = new JTextField();
        SetTextFormatJLabel(startdate);
        SetTextFormatJTextField(startdateTextField);
        panel.add(startdate);
        panel.add(startdateTextField);

        // 输入预约的结束时间
        JLabel endDate = new JLabel("预约的结束时间:");
        JTextField endDateTextField = new JTextField();
        SetTextFormatJLabel(endDate);
        SetTextFormatJTextField(endDateTextField);

        panel.add(endDate);
        panel.add(endDateTextField);

        // 返回按钮
        JButton backButton = new JButton("返回");
        SetTextFormatJButton(backButton);
        panel.add(backButton);
        // 提交按钮
        JButton submitButton = new JButton("提交");
        SetTextFormatJButton(submitButton);
        panel.add(submitButton);



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
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel, BorderLayout.NORTH);

        // 创建用于显示数据的文本区域
        JTextArea reservationTextArea = null;
//        JScrollPane scrollPane = new JScrollPane(reservationTextArea);
        reservationTextArea = new JTextArea();
        reservationTextArea.setEditable(false); // 设置为只读
        reservationTextArea.setLineWrap(true); // 自动换行
        reservationTextArea.setWrapStyleWord(true); // 断行不断字
        reservationTextArea.setFont(new Font("宋体", Font.PLAIN, 15));

        // 创建JScrollPane，并将JTextArea添加到其中
        JScrollPane scrollPane = new JScrollPane(reservationTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // 显示垂直滚动条

        // 将JScrollPane添加到面板的中间区域
        panel.add(scrollPane, BorderLayout.CENTER);

        // 获取预约信息
        String reservationInfo = student.getMyReservationInfo();
        // 添加内容到JTextArea
        reservationTextArea.append(reservationInfo);
//        panel.add(scrollPane, BorderLayout.CENTER);

        // 返回按钮
        JButton backButton = new JButton("返回");
        SetTextFormatJButton(backButton);
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
    private JPanel createAllReservationsPanel( Student student) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("所有预约" +student.getStudentID());
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel, BorderLayout.NORTH);

        // 创建用于显示数据的文本区域
        JTextArea reservationTextArea = null;
//        JScrollPane scrollPane = new JScrollPane(reservationTextArea);
        reservationTextArea = new JTextArea();
        reservationTextArea.setEditable(false); // 设置为只读
        reservationTextArea.setLineWrap(true); // 自动换行
        reservationTextArea.setWrapStyleWord(true); // 断行不断字
        reservationTextArea.setFont(new Font("宋体", Font.PLAIN, 15));

        // 创建JScrollPane，并将JTextArea添加到其中
        JScrollPane scrollPane = new JScrollPane(reservationTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // 显示垂直滚动条

        // 将JScrollPane添加到面板的中间区域
        panel.add(scrollPane, BorderLayout.CENTER);

        // 获取预约信息
        String reservationInfo = student.getAllReservationInfo();
        // 添加内容到JTextArea
        reservationTextArea.append(reservationInfo);
//        panel.add(scrollPane, BorderLayout.CENTER);

        // 返回按钮
        JButton backButton = new JButton("返回");
        SetTextFormatJButton(backButton);
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

    //取消预约界面
    private JPanel createCancelReservationPanel( Student student) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("取消预约页面");
        SetTextFormatJLabel(titleLabel);
        titleLabel.setPreferredSize(new Dimension(700, 50));
        panel.add(titleLabel);
        // 创建用于显示数据的文本区域
        JTextArea reservationTextArea = null;
//        JScrollPane scrollPane = new JScrollPane(reservationTextArea);
        reservationTextArea = new JTextArea();
        reservationTextArea.setEditable(false); // 设置为只读
        reservationTextArea.setLineWrap(true); // 自动换行
        reservationTextArea.setWrapStyleWord(true); // 断行不断字
        reservationTextArea.setFont(new Font("宋体", Font.PLAIN, 15));
        // 创建JScrollPane，并将JTextArea添加到其中
        JScrollPane scrollPane = new JScrollPane(reservationTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // 显示垂直滚动条
        // 将JScrollPane添加到面板的中间区域
        scrollPane.setPreferredSize(new Dimension(700, 200));

        panel.add(scrollPane, BorderLayout.CENTER);

        // 获取预约信息
        String reservationInfo ="54646456554654646646545645466546546546546546556456465465456sdj" +
                "nkajkl" +
                "dddddddd\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\\nn\n\nddddddddddddddddddddddddddddd\nddddddddddddddddddddddddddddddddddddddd\ndddddd" +
                "dddddd\ndddddddddddddddddddddddddddddddd\ndddddddddddddddddddddddddddddddddddddddddd\nd" +
                "d" +
                "nddddddddddddddddddddddddddd" +
                "ndddddddddddd\ndddddddddddddddddddddddddddddddddddddd\ndd" +
                "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd\ndddd" +
                "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd\n"
                ;
        // 添加内容到JTextArea
        reservationTextArea.append(reservationInfo);
//        panel.add(scrollPane, BorderLayout.CENTER);



        // 输入要取消的预约编号
        JLabel reservationNumberLabel = new JLabel("输入要取消的预约编号:");
        JTextField reservationNumberTextField = new JTextField();
        SetTextFormatJLabel(reservationNumberLabel);
        SetTextFormatJTextField(reservationNumberTextField);
        reservationNumberLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        reservationNumberLabel.setPreferredSize(new Dimension(200, 50));
        reservationNumberTextField.setPreferredSize(new Dimension(100, 50));
        panel.add(reservationNumberLabel);
        panel.add(reservationNumberTextField);

        // 提交按钮
        JButton submitButton = new JButton("提交");
        SetTextFormatJButton(submitButton);
        setPreferredSize(new Dimension(100, 50));
        panel.add(submitButton);

        JLabel spaceItem = new JLabel(" ");
        spaceItem.setPreferredSize(new Dimension(200, 50));
        panel.add(spaceItem);


        // 返回按钮
        JButton backButton = new JButton("返回");
        SetTextFormatJButton(backButton);
        setPreferredSize(new Dimension(100, 50));
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
    private JPanel createTeacherPanel(Teacher teacher) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = CreateJLabel( panel ,"教师操作页面", 700, 70,0,0);

        // 添加审批预约按钮
        JButton auditOrderButton = CreateJButton(panel, "审批预约", 220, 100, 25, 0);

        // 添加查看所有预约记录按钮
        JButton viewAllRecordsButton = CreateJButton(panel, "查看所有预约记录", 220, 100, 0, 0);

        // 添加查看学生账号按钮
        JButton viewStudentAccountButton = CreateJButton(panel, "查看学生账号", 220, 100, 0, 0);

        // 返回按钮
        JLabel emptyLabel = CreateJLabel(panel, " ", 700, 20, 0, 0);
        JButton backButton = CreateJButton(panel, "退出登录", 200, 100, 270, 0);

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
                JOptionPane.showMessageDialog(panel, "退出登录成功。", "退出登录", JOptionPane.INFORMATION_MESSAGE);
                showLoginPage();
            }
        });

        return panel;
    }

    //审核预约界面
    private JPanel createAuditOrderPanel( Teacher teacher) {
        JPanel panel = new JPanel(new BorderLayout());
        // 创建用于显示数据的文本区域
        JTextArea reservationTextArea = null;
        JLabel titleLabel = new JLabel("审批预约");
        titleLabel.setFont(new Font("", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // 创建文字区域
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);// 激活自动换行功能
        textArea.setWrapStyleWord(true);// 激活断行不断字功能
        JScrollPane scrollPane = new JScrollPane(textArea);
        String reservationInfo = student.getAllReservationInfo();
        // 添加内容到JTextArea
        textArea.append(reservationInfo);



        panel.add(scrollPane, BorderLayout.CENTER);

        // 创建两行的输入区域面板
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setPreferredSize(new Dimension(750, 100));

        // 第一行的说明标签和输入框
        JLabel orderID = CreateJLabel(inputPanel, "输入要审批的预约编号:", 200, 50, 0, 0);
        JTextField orderIDTextField1 = CreateJTextField(inputPanel, 100, 50, 0, 0);


        // 第二行的说明标签和输入框
        JLabel statusLabel = CreateJLabel(inputPanel, "选择审批结果:", 150, 50, 0, 0);
        String[] identities = {"审核中", "审核通过", "审核不通过"};
        JComboBox<String> statusComboBox =  (JComboBox<String>) CreateJComboBox(inputPanel,identities, 200, 50,0 ,0);
        // 输入姓名

        JButton backButton = CreateJButton(inputPanel, "返回", 100, 40, 200, 0);
        JButton confirmButton = CreateJButton(inputPanel, "确定", 100, 40, 200, 0);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderIDTextField1.setText("");
                showTeacherPage();
                // 返回按钮的事件处理逻辑
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String t1 = orderIDTextField1.getText();
                String t2 = (String) statusComboBox.getSelectedItem();
                JOptionPane.showMessageDialog(panel, " t1: "+ t1 + " t2: " + t2, "title", JOptionPane.INFORMATION_MESSAGE);
                orderIDTextField1.setText("");
                // 确定按钮的事件处理逻辑
            }
        });
        inputPanel.add(backButton);
        inputPanel.add(confirmButton);
        panel.add(inputPanel, BorderLayout.SOUTH);


        // 确定按钮




        panel.setVisible(true);
        return panel;
    }

    //显示所有预约界面
    private JPanel createCheckAllOrderPanel(Teacher teacher) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("所有预约");
        SetTextFormatJLabel(titleLabel);
        panel.add(titleLabel, BorderLayout.NORTH);

        // 创建用于显示数据的文本区域
        JTextArea reservationTextArea = null;
//        JScrollPane scrollPane = new JScrollPane(reservationTextArea);
        reservationTextArea = new JTextArea();
        reservationTextArea.setEditable(false); // 设置为只读
        reservationTextArea.setLineWrap(true); // 自动换行
        reservationTextArea.setWrapStyleWord(true); // 断行不断字
        reservationTextArea.setFont(new Font("宋体", Font.PLAIN, 15));

        // 创建JScrollPane，并将JTextArea添加到其中
        JScrollPane scrollPane = new JScrollPane(reservationTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // 显示垂直滚动条

        // 将JScrollPane添加到面板的中间区域
        panel.add(scrollPane, BorderLayout.CENTER);

        // 获取预约信息
        String reservationInfo = teacher.getAllReservationInfo();
        // 添加内容到JTextArea
        reservationTextArea.append(reservationInfo);
//        panel.add(scrollPane, BorderLayout.CENTER);

        // 返回按钮
        JButton backButton = new JButton("返回");
        SetTextFormatJButton(backButton);
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
    //查看学生账号界面
    private JPanel createCheckStudentAccountPanel(Teacher teacher) {
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
    private JPanel createAdminPanel(Manager manager) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel titleLabel = CreateJLabel( panel ,"管理员操作页面", 700, 70,0,0);

        // 添加查看账号按钮
        JButton checkAccountButton = CreateJButton(panel, "查看账号", 220, 100, 25, 0);

        // 添加添加账号按钮
        JButton addAccountButton = CreateJButton(panel, "添加账号", 220, 100, 0, 0);

        // 添加删除账号按钮
        JButton deleteAccountButton = CreateJButton(panel, "删除账号", 220, 100, 0, 0);

        // 返回按钮
        JLabel emptyLabel = CreateJLabel(panel, " ", 700, 20, 0, 0);
        JButton backButton = CreateJButton(panel, "退出登录", 200, 100, 270, 0);

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
                JOptionPane.showMessageDialog(panel, "退出登录成功。", "退出登录", JOptionPane.INFORMATION_MESSAGE);
                showLoginPage();
            }
        });

        return panel;
    }

    //查看账号界面
    private JPanel createCheckAccountPanel(Manager manager) {
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
    private JPanel createAddAccountPanel(Manager manager) {
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
    private JPanel createDeleteAccountPanel( Manager manager) {
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
    private JPanel createClearReservationPanel(Manager manager) {
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
        cardLayout.show(cardPanel, "AllReservation");
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
