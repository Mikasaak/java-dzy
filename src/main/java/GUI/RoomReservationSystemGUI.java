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
    Font fontSmall = new Font("", Font.BOLD, 13);
    Font fontMiddle = new Font("", Font.BOLD, 15);
    private Font FountSet(int fountWidth ,int fountSize ) {
        return new Font("", fountWidth, fountSize);
    }
    private Font FountSet(String fountFamily,int fountWidth ,int fountSize) {
        return new Font(fountFamily, fountWidth, fountSize);
    }
    private  void SetTextFormatJLabel(JLabel jLabel) {
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

    private void SetTextFormat(JLabel jLabel , Font font, int alignment) {
        jLabel.setFont(font);
        jLabel.setHorizontalAlignment(alignment);
    }
    private void SetTextFormat(JTextField jTextField , Font font, int alignment) {
        jTextField.setFont(font);
        jTextField.setHorizontalAlignment(alignment);
    }
    private void SetTextFormat(JButton jButton , Font font, int alignment) {
        jButton.setFont(font);
        jButton.setHorizontalAlignment(alignment);
    }
    private void SetTextFormat(JComboBox<?> jComboBox , Font font,DefaultListCellRenderer renderer) {
        jComboBox.setFont(font);
        jComboBox.setRenderer(renderer);
    }
    private void SetTextFormat(JPasswordField jPasswordField , Font font, int alignment) {
        jPasswordField.setFont(font);
        jPasswordField.setHorizontalAlignment(alignment);
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
    private JLabel CreateJLabel( JPanel jPanel,String text, int width, int height, int left, int right, Font font ,int alignment) {
        JLabel jLabel = new JLabel(text);
        JLabel leftEmpty = CreateEmptyJLabel(left, height);
        JLabel rightEmpty = CreateEmptyJLabel(right, height);
        jLabel.setPreferredSize(new Dimension(width, height));
        SetTextFormat(jLabel,font,alignment);
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
    private JTextField CreateJTextField( JPanel jPanel ,int width, int height,int left, int right, Font font ,int alignment) {
        JTextField jTextField = new JTextField();
        JLabel leftEmpty = CreateEmptyJLabel(left, height);
        JLabel rightEmpty = CreateEmptyJLabel(right, height);
        jTextField.setPreferredSize(new Dimension(width, height));
        SetTextFormat(jTextField,font,alignment);
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

    private JPasswordField CreateJPasswordField(  JPanel jPanel,int width, int height,int left, int right, Font font ,int alignment) {
        JPasswordField jPasswordField = new JPasswordField();
        JLabel leftEmpty = CreateEmptyJLabel(left, height);
        JLabel rightEmpty = CreateEmptyJLabel(right, height);
        jPasswordField.setPreferredSize(new Dimension(width, height));
        SetTextFormat(jPasswordField,font,alignment);
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

    private JButton CreateJButton( JPanel jPanel,String text, int width, int height,int left, int right, Font font ,int alignment) {
        JButton jButton = new JButton(text);
        JLabel leftEmpty = CreateEmptyJLabel(left, height);
        JLabel rightEmpty = CreateEmptyJLabel(right, height);
        jButton.setPreferredSize(new Dimension(width, height));
        SetTextFormat(jButton,font,alignment);
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
    private JComboBox<?> CreateJComboBox(JPanel jPanel , String[] items, int width, int height,int left, int right, Font font ,int alignment) {
        JComboBox<?> jComboBox = new JComboBox<>(items);
        JLabel leftEmpty = CreateEmptyJLabel(left, height);
        JLabel rightEmpty = CreateEmptyJLabel(right, height);
        jComboBox.setPreferredSize(new Dimension(width, height));
        // Apply text format settings
        SetTextFormat(jComboBox,font,renderer);
        jComboBox.setSelectedIndex(0);
        jPanel.add(leftEmpty);
        jPanel.add(jComboBox);
        jPanel.add(rightEmpty);
        return jComboBox;
    }




    public Student student=null;
    public Teacher teacher=null;
    private Manager manager=null;
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
    private void addAddStudentAccountPage(JPanel cardPanel, Manager manager) {
        JPanel addStudentAccountPanel = createAddStudentAccountPanel(manager);
        cardPanel.add(addStudentAccountPanel, "AddStudentAccount");
    }
    private void addAddTeacherAccountPage(JPanel cardPanel, Manager manager) {
        JPanel addTeacherAccountPanel = createAddTeacherAccountPanel(manager);
        cardPanel.add(addTeacherAccountPanel, "AddTeacherAccount");
    }
//        //添加删除账号界面
//        JPanel deleteAccountPanel = createDeleteAccountPanel(manager);
//        cardPanel.add(deleteAccountPanel, "DeleteAccount");
    private void addDeleteStudentAccountPage(JPanel cardPanel, Manager manager) {
        JPanel deleteAccountPanel = createDeleteStudentAccountPanel(manager);
        cardPanel.add(deleteAccountPanel, "DeleteStudentAccount");
    }
    private void addDeleteTeacherAccountPage(JPanel cardPanel, Manager manager) {
        JPanel deleteAccountPanel = createDeleteTeacherAccountPanel(manager);
        cardPanel.add(deleteAccountPanel, "DeleteTeacherAccount");
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

        JLabel titleLabel = CreateJLabel(panel,"登录界面",700,  60,0 ,0);//标题

        JLabel identity =  CreateJLabel(panel,"身份:",300,  50,0 ,0);//身份标签
        // 下拉选择菜单选择身份
        String[] identities = {"教师", "学生", "管理员"};
        JComboBox<String> identityComboBox =  (JComboBox<String>) CreateJComboBox(panel,identities, 400, 50,0 ,0);
        // 输入姓名
//        JLabel nameLabel = CreateJLabel(panel,"姓名:",300,  50,0 ,0);//姓名标签
//        JTextField nameTextField = CreateJTextField(panel,400, 50,0 ,0);//姓名输入框

        // 输入学号/工号
        JLabel idLabel = CreateJLabel(panel,"学号/工号:",300,  50,0 ,0);//学号/工号标签
        JTextField idTextField =  CreateJTextField(panel,400, 50,0 ,0);//学号/工号输入框
        // 输入密码
        JLabel passwordLabel =  CreateJLabel(panel,"密码:",300,  50,0 ,0);//密码标签
        JPasswordField passwordField = CreateJPasswordField(panel,400, 50,0 ,0);//密码输入框

       JLabel emptyLabel1 = CreateJLabel(panel,"",700,  20,0 ,0);//空标签
        // 登录按钮
        JButton loginButton=CreateJButton(panel,"登录",300,  70,200 ,200);

        // 设置登录按钮的事件监听器
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identity = (String) identityComboBox.getSelectedItem();
//                String name = nameTextField.getText();
                String id = idTextField.getText();
                String password = new String(passwordField.getPassword());
                passwordField.setText("");
                // 根据身份和登录信息进行登录验证
                if (identity.equals("学生")) {
                    Pair<Boolean, String> pair = Operate.loginPart(id, password, '1');
                    if(pair.getKey()){
                        JOptionPane.showMessageDialog(panel, "登录成功！", "登录成功", JOptionPane.INFORMATION_MESSAGE);
                        student = new Student(id, password, pair.getValue());
                        System.out.println(student.getName());
                        addStudentPage(cardPanel,student);
                        showStudentPage();
                    }
                    else {
                        JOptionPane.showMessageDialog(panel, "登录失败，请检查您的登录信息。", "登录失败", JOptionPane.ERROR_MESSAGE);
                    }
                }

                else if (identity.equals("教师")) {
                    Pair<Boolean, String> pair = Operate.loginPart(id, password, '2');
                    if(pair.getKey()) {
                        JOptionPane.showMessageDialog(panel, "登录成功！", "登录成功", JOptionPane.INFORMATION_MESSAGE);
                        teacher = new Teacher(id, password, pair.getValue());
                        addTeacherPage(cardPanel,teacher);
                        showTeacherPage();
                    }
                    else {
                        JOptionPane.showMessageDialog(panel, "登录失败，请检查您的登录信息。", "登录失败", JOptionPane.ERROR_MESSAGE);
                    }
                }

                else if (identity.equals("管理员")) {
                    Pair<Boolean, String> pair = Operate.loginPart(id, password, '3');
                    if(pair.getKey()) {
                        JOptionPane.showMessageDialog(panel, "登录成功！", "登录成功", JOptionPane.INFORMATION_MESSAGE);
                        manager = new Manager(id, password, pair.getValue());
                        addAdminPage(cardPanel,manager);
                        showAdminPage();
                    }
                    else {
                        JOptionPane.showMessageDialog(panel, "登录失败，请检查您的登录信息。", "登录失败", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(panel, "登录失败，请检查您的登录信息。", "登录失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

//    学生操作界面
    private JPanel createStudentPanel(Student student) {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel titleLabel = CreateJLabel(panel,"同学 "+student.getName()+" 请选择你的操作",700,  60, 0,0);

        // 添加预约按钮
        JButton addReservationButton = CreateJButton(panel,"预约机房",300,  80,50 ,0);

        // 添加查看我的预约按钮
        JButton myReservationButton = CreateJButton(panel,"查看我的预约",300,  80,20 ,0);

        JLabel emptyLabel1 = CreateJLabel(panel,"",700,  10,0 ,0);

        // 添加查看所有预约按钮
        JButton AllReservationButton = CreateJButton(panel,"查看所有预约",300,  80,50 ,0);
        // 添加取消预约按钮
        JButton cancelReservationButton = CreateJButton(panel,"取消预约",300,  80,20 ,0);

        // 返回按钮
        JLabel emptyLabel2 = CreateJLabel(panel,"",700,  10,0 ,0);
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
        JPanel panel = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel titleLabel = CreateJLabel(titlePanel,"同学 "+ student.getName() +" 请输入要添加的预约",700,  60, 0,0);
        panel.add(titlePanel, BorderLayout.NORTH);
        // 创建用于显示数据的文本区域
        JTextArea reservationTextArea = null;
//        JScrollPane scrollPane = new JScrollPane(reservationTextArea);
        reservationTextArea = new JTextArea();
        reservationTextArea.setEditable(false); // 设置为只读
        reservationTextArea.setLineWrap(true); // 自动换行
        reservationTextArea.setWrapStyleWord(true); // 断行不断字
        reservationTextArea.setFont(new Font("宋体", Font.PLAIN, 13));
        // 创建JScrollPane，并将JTextArea添加到其中
        JScrollPane scrollPane = new JScrollPane(reservationTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // 显示垂直滚动条
        // 将JScrollPane添加到面板的中间区域
        scrollPane.setPreferredSize(new Dimension(300, 200));
        panel.add(scrollPane, BorderLayout.WEST);

        // 获取预约信息
        String reservationInfo = student.getComputerRoomInfo();
        // 添加内容到JTextArea
        reservationTextArea.append(reservationInfo);
//        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setPreferredSize(new Dimension(400, 500));
        // 输入要预约的机房编号
        JLabel computerRoomNumberLabel = CreateJLabel(inputPanel,"请输入要预约的机房编号",195,  50,0 ,0,
              FountSet(Font.PLAIN,14),JLabel.LEFT);
        JTextField computerRoomNumberTextField =CreateJTextField(inputPanel,150,  50,0 ,0,
                FountSet(Font.PLAIN,14),JTextField.LEFT);
//        computerRoomNumberLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        // 输入预约的开始时间
        JLabel startTimeLabel = CreateJLabel(inputPanel,"请输入预约的开始时间",200,  50,0 ,0,
                FountSet(Font.PLAIN,15),JLabel.LEFT);
        JTextField startTimeTextField =CreateJTextField(inputPanel,150,  50,0 ,0,
                FountSet(Font.PLAIN,15),JTextField.LEFT);
        // 输入预约的结束时间
        JLabel endTimeLabel = CreateJLabel(inputPanel,"请输入预约的结束时间",200,  50,0 ,0,
                FountSet(Font.PLAIN,15),JLabel.LEFT);
        JTextField endTimeTextField =CreateJTextField(inputPanel,150,  50,0 ,0,
                FountSet(Font.PLAIN,15),JTextField.LEFT);
        panel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setPreferredSize(new Dimension(700, 100));
        // 提交按钮
        JButton submitButton = CreateJButton(buttonPanel,"提交",100,  50,400 ,0);
        // 返回按钮
        JButton backButton = CreateJButton(buttonPanel,"返回",100,  50,0 ,0);

        // 设置按钮的事件监听器
        JTextArea finalReservationTextArea = reservationTextArea;
        submitButton.addActionListener(new ActionListener() {
            // 点击提交按钮后执行的操作
            //获取输入的信息
            @Override
            public void actionPerformed(ActionEvent e) {
                String computerRoomNumber = computerRoomNumberTextField.getText();
                String startTime = startTimeTextField.getText();
                String endTime = endTimeTextField.getText();
                // 调用Student类的ApplyOrderPart方法
                Pair<Boolean, String> info = student.applyOrderPart(computerRoomNumber, startTime, endTime);
                if (info.getKey()) {
                    JOptionPane.showMessageDialog(panel, info.getValue(), "提示", JOptionPane.INFORMATION_MESSAGE);
                    computerRoomNumberTextField.setText("");
                    startTimeTextField.setText("");
                    endTimeTextField.setText("");
                    showAddReservationPage();
                } else {
                    JOptionPane.showMessageDialog(panel, info.getValue(), "提示", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStudentPage();
            }
        });


        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    //查看我的预约界面
    private JPanel createMyReservationPanel(Student student) {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = CreateJLabel(titlePanel,"同学 "+ student.getName() +" 这是你的所有预约",700,  60, 0,0);
        panel.add(titlePanel, BorderLayout.NORTH);

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

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = CreateJLabel(titlePanel,"同学 "+ student.getName() +" 这是你查阅的所有审核中和审核通过的预约",700,  60, 0,0);
        panel.add(titlePanel, BorderLayout.NORTH);

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
        JLabel titleLabel = new JLabel("同学 "+ student.getName() +" 选择你要取消的预约");
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
        String reservationInfo = student.getMyReservationInfo();

        // 添加内容到JTextArea
        reservationTextArea.append(reservationInfo);
//        panel.add(scrollPane, BorderLayout.CENTER);



        // 输入要取消的预约编号
        JLabel cancelReservationNumberLabel = CreateJLabel(panel ,"请输入要取消的预约编号：",200, 50,0,0
                ,FountSet(Font.BOLD,15),JLabel.LEFT);
        JTextField cancelReservationNumberTextField = CreateJTextField(panel, 100, 50,0,0);

        // 提交按钮
        JButton submitButton = CreateJButton(panel, "提交", 100, 50,100,0);

        // 返回按钮
        JButton backButton = CreateJButton(panel, "返回", 100, 50,0,0);

        // 设置按钮的事件监听器
        JTextArea finalReservationTextArea = reservationTextArea;
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cancelReservationNumber = cancelReservationNumberTextField.getText();
                Pair<Boolean,String> result = student. cancelMyOrderPart(cancelReservationNumber);
                if (result.getKey()) {
                    JOptionPane.showMessageDialog(panel, result.getValue(), "提示", JOptionPane.INFORMATION_MESSAGE);
                    cancelReservationNumberTextField.setText("");//清空输入框
                    String newReservationInfo = student.getMyReservationInfo();
                    finalReservationTextArea.setText(newReservationInfo);

                    showCancelReservationPage();
                } else {
                    JOptionPane.showMessageDialog(panel, result.getValue(), "提示", JOptionPane.ERROR_MESSAGE);
                    cancelReservationNumberTextField.setText("");//清空输入框
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
        JLabel titleLabel = CreateJLabel( panel ,"教师 "+ teacher.getName() + "请选择您的操作", 700, 70,0,0);

        // 添加审批预约按钮
        JButton auditOrderButton = CreateJButton(panel, "审批预约", 220, 100, 25, 0);

        // 添加查看所有预约记录按钮
        JButton viewAllRecordsButton = CreateJButton(panel, "查看所有预约记录", 220, 100, 0, 0);

        // 添加查看学生账号按钮
        JButton viewStudentAccountButton = CreateJButton(panel, "查看学生账号", 220, 100, 0, 0);

        // 返回按钮
        JLabel emptyLabel = CreateJLabel(panel, " ", 700, 20, 0, 0);
        JButton backButton = CreateJButton(panel, "退出登录", 200, 100, 270, 0);

        final Teacher teacher1 = this.teacher;
        // 设置按钮的事件监听器

        auditOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAuditOrderPage(cardPanel, teacher1);
                showAuditOrderPage();
            }
        });

        viewAllRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCheckAllOrderPage(cardPanel, teacher1);
                showCheckAllOrderPage();
            }
        });


        viewStudentAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCheckStudentAccountPage(cardPanel, teacher1);
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

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = CreateJLabel(titlePanel,"教师 "+ teacher.getName() +" 请选择要审核的预约记录",700,  60, 0,0);
        panel.add(titlePanel, BorderLayout.NORTH);
        JTextArea reservationTextArea = null;
        // 创建文字区域
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);// 激活自动换行功能
        textArea.setWrapStyleWord(true);// 激活断行不断字功能
        JScrollPane scrollPane = new JScrollPane(textArea);
        String reservationInfo = teacher.getAuditingReservationInfo();
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
                String orderID = orderIDTextField1.getText();
                String status = (String) statusComboBox.getSelectedItem();
                Pair<Boolean, String> result = teacher.auditOrderPart(orderID, status);
                if (result.getKey()) {
                    JOptionPane.showMessageDialog(panel, result.getValue(), "提示", JOptionPane.INFORMATION_MESSAGE);
                    orderIDTextField1.setText("");
                    String newReservationInfo = teacher.getAuditingReservationInfo();
                    textArea.setText(newReservationInfo);
                    showAuditOrderPage();
                } else {
                    JOptionPane.showMessageDialog(panel, result.getValue(), "提示", JOptionPane.ERROR_MESSAGE);
                    orderIDTextField1.setText("");
                }
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

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = CreateJLabel(titlePanel,"教师 "+ teacher.getName() +" 这是您查阅的所有的预约记录",700,  60, 0,0);
        panel.add(titlePanel, BorderLayout.NORTH);

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
        String reservationInfo = teacher.getEveryReservationInfo();
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
        JPanel panel = new JPanel(new BorderLayout());

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = CreateJLabel(titlePanel,"教师 "+ teacher.getName() +" 这是您要查阅的所有学生账号信息",700,  60, 0,0);
        panel.add(titlePanel, BorderLayout.NORTH);

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
        String studentAccountInfo = teacher.getStudentAccount();
        // 添加内容到JTextArea
        reservationTextArea.append(studentAccountInfo);
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


    //管理员操作界面
    private JPanel createAdminPanel(Manager manager) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel titleLabel = CreateJLabel( panel ,"管理员 "+manager.getName() +"请选择您的操作", 700, 70,0,0);

        // 添加查看账号按钮
        JButton checkAccountButton = CreateJButton(panel, "查看账号", 220, 100, 25, 0);

        // 添加student账号按钮
        JButton addStudentAccountButton = CreateJButton(panel, "添加学生账号", 220, 100, 0, 0);
        // 添加teacher账号按钮
        JButton addTeacherAccountButton = CreateJButton(panel, "添加教师账号", 220, 100, 0, 0);
        JLabel emptyLabel = CreateJLabel( panel ,"", 700, 30,0,0);
        // 添加删除Students账号按钮
        JButton deleteStudentAccountButton = CreateJButton(panel, "删除学生账号", 220, 100, 25, 0);
        // 添加删除teacher账号按钮
        JButton deleteTeacherAccountButton = CreateJButton(panel, "删除教师账号", 220, 100, 0, 0);
        //
        // 退出登录按钮
        JButton backButton = CreateJButton(panel, "退出登录", 220, 100, 0, 0);

        final Manager  manager1 = this.manager;
        // 设置按钮的事件监听器
        checkAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCheckAccountPage(cardPanel, manager1);
                showCheckAccountPage();
            }
        });

        addStudentAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAddStudentAccountPage(cardPanel, manager1);
                showAddStudentAccountPage();
            }
        });
        addTeacherAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAddTeacherAccountPage(cardPanel, manager1);
                showAddTeacherAccountPage();
            }
        });

        deleteStudentAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDeleteStudentAccountPage(cardPanel, manager1);
                showDeleteStudentAccountPage();
            }
        });
        deleteTeacherAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDeleteTeacherAccountPage(cardPanel, manager1);
                showDeleteTeacherAccountPage();
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
        JPanel panel = new JPanel(new BorderLayout());

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = CreateJLabel(titlePanel,"管理员 "+ manager.getName() +" 请选择要查阅的账号",700,  60, 0,0);
        panel.add(titlePanel, BorderLayout.NORTH);

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
        String studentAccountInfo = "请选择要查看的账号类型\n";
        // 添加内容到JTextArea
        reservationTextArea.append(studentAccountInfo);
//        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel searchLabel = CreateJLabel(buttonPanel, "请选择要查看的账号：", 200, 100, 0, 0);
        String[] identities = {"教师", "学生", "管理员"};
        JComboBox<String> identityComboBox =  (JComboBox<String>) CreateJComboBox(buttonPanel,identities, 200, 50,0 ,0);

        JButton searchButton = CreateJButton(buttonPanel, "查询", 100, 50, 50, 0);
        // 返回按钮
        JButton backButton = CreateJButton(buttonPanel, "返回", 100, 50, 0, 0);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        // 设置按钮的事件监听器
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminPage();
            }
        });
        JTextArea finalReservationTextArea = reservationTextArea;
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identity = (String) identityComboBox.getSelectedItem();
                if (identity.equals("教师")) {
                    String teacherAccountInfo = manager.getTeacherAccountInfo();

                    finalReservationTextArea.setText(teacherAccountInfo);
                } else if (identity.equals("学生")) {
                    String studentAccountInfo = manager.getStudentAccountInfo();
                    finalReservationTextArea.setText(studentAccountInfo);
                } else if (identity.equals("管理员")) {
                    String managerAccountInfo = manager.getManagerAccountInfo();
                    finalReservationTextArea.setText(managerAccountInfo);
                }
            }
        });
        return panel;
    }

    //添加学生账号界面
    private JPanel createAddStudentAccountPanel(Manager manager) {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = CreateJLabel(titlePanel,"管理员 "+ manager.getName() +" 请输入要添加的学生账号",700,  60, 0,0);
        panel.add(titlePanel, BorderLayout.NORTH);
        // 创建用于显示数据的文本区域
        JTextArea studentAccountInfoTextArea = null;
//        JScrollPane scrollPane = new JScrollPane(studentAccountInfoTextArea);
        studentAccountInfoTextArea = new JTextArea();
        studentAccountInfoTextArea.setEditable(false); // 设置为只读
        studentAccountInfoTextArea.setLineWrap(true); // 自动换行
        studentAccountInfoTextArea.setWrapStyleWord(true); // 断行不断字
        studentAccountInfoTextArea.setFont(new Font("宋体", Font.PLAIN, 15));
        // 创建JScrollPane，并将JTextArea添加到其中
        JScrollPane scrollPane = new JScrollPane(studentAccountInfoTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // 显示垂直滚动条
        // 将JScrollPane添加到面板的中间区域
        scrollPane.setPreferredSize(new Dimension(300, 200));
        panel.add(scrollPane, BorderLayout.WEST);

        // 获取学生账号信息
        String studentAccountInfo = manager.getStudentAccountInfo();
        // 添加内容到JTextArea
        studentAccountInfoTextArea.append(studentAccountInfo);
//        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setPreferredSize(new Dimension(400, 500));

        JLabel studentNameLabel = CreateJLabel(inputPanel,"请输入要添加的学生姓名",195,  50,0 ,0,
                FountSet(Font.PLAIN,15),JLabel.LEFT);
        JTextField studentNameTextField =CreateJTextField(inputPanel,150,  50,0 ,0,
                FountSet(Font.PLAIN,15),JTextField.LEFT);
//        studentNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        // 输入预约的开始时间
        JLabel studentIDLabel = CreateJLabel(inputPanel,"请输入添加的学生学号",200,  50,0 ,0,
                FountSet(Font.PLAIN,15),JLabel.LEFT);
        JTextField studentIDTextField =CreateJTextField(inputPanel,150,  50,0 ,0,
                FountSet(Font.PLAIN,15),JTextField.LEFT);
        // 输入预约的结束时间
        JLabel studentPasswordLabel = CreateJLabel(inputPanel,"请输入添加的学生密码",200,  50,0 ,0,
                FountSet(Font.PLAIN,15),JLabel.LEFT);
        JTextField studentPasswordTextField =CreateJTextField(inputPanel,150,  50,0 ,0,
                FountSet(Font.PLAIN,15),JTextField.LEFT);
        panel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setPreferredSize(new Dimension(700, 100));
        // 提交按钮
        JButton submitButton = CreateJButton(buttonPanel,"确定",100,  50,400 ,0);
        // 返回按钮
        JButton backButton = CreateJButton(buttonPanel,"返回",100,  50,0 ,0);

        // 设置按钮的事件监听器
        JTextArea finalStudentAccountInfoTextArea = studentAccountInfoTextArea;
        submitButton.addActionListener(new ActionListener() {
            // 点击提交按钮后执行的操作
            //获取输入的信息
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = studentIDTextField.getText();
                String studentPassword = studentPasswordTextField.getText();
                String studentName = studentNameTextField.getText();
                Pair<Boolean, String> result = manager.addStudentAccountPart(studentID, studentPassword,studentName);

                if (result.getKey()) {
                    JOptionPane.showMessageDialog(panel, result.getValue(), "提示", JOptionPane.INFORMATION_MESSAGE);
                    // 重新获取StudentAccountInfo
                    String studentAccountInfo = manager.getStudentAccountInfo();
                    // 添加内容到JTextArea
                    finalStudentAccountInfoTextArea.setText(studentAccountInfo);
                    // 清空输入框
                    studentIDTextField.setText("");
                    studentPasswordTextField.setText("");
                    studentNameTextField.setText("");


                } else {
                    JOptionPane.showMessageDialog(panel, result.getValue(), "提示", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminPage();
            }
        });


        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }
    //添加教师账号界面
    private JPanel createAddTeacherAccountPanel(Manager manager) {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = CreateJLabel(titlePanel,"管理员 "+ manager.getName() +" 请输入要添加的教师账号",700,  60, 0,0);
        panel.add(titlePanel, BorderLayout.NORTH);
        // 创建用于显示数据的文本区域
        JTextArea teacherAccountInfoTextArea = null;
//        JScrollPane scrollPane = new JScrollPane(teacherAccountInfoTextArea);
        teacherAccountInfoTextArea = new JTextArea();
        teacherAccountInfoTextArea.setEditable(false); // 设置为只读
        teacherAccountInfoTextArea.setLineWrap(true); // 自动换行
        teacherAccountInfoTextArea.setWrapStyleWord(true); // 断行不断字
        teacherAccountInfoTextArea.setFont(new Font("宋体", Font.PLAIN, 15));
        // 创建JScrollPane，并将JTextArea添加到其中
        JScrollPane scrollPane = new JScrollPane(teacherAccountInfoTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // 显示垂直滚动条
        // 将JScrollPane添加到面板的中间区域
        scrollPane.setPreferredSize(new Dimension(300, 200));
        panel.add(scrollPane, BorderLayout.WEST);
        // 获取教师账号信息
        String teacherAccountInfo = manager.getTeacherAccountInfo();
        // 添加内容到JTextArea
        teacherAccountInfoTextArea.append(teacherAccountInfo);
//        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setPreferredSize(new Dimension(400, 500));

        JLabel teacherNameLabel = CreateJLabel(inputPanel,"请输入要添加的教师姓名",195,  50,0 ,0,
                FountSet(Font.PLAIN,15),JLabel.LEFT);
        JTextField teacherNameTextField =CreateJTextField(inputPanel,150,  50,0 ,0,
                FountSet(Font.PLAIN,15),JTextField.LEFT);
//        teacherNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel teacherIDLabel = CreateJLabel(inputPanel,"请输入添加的教师职工号",200,  50,0 ,0,
                FountSet(Font.PLAIN,15),JLabel.LEFT);
        JTextField teacherIDTextField =CreateJTextField(inputPanel,150,  50,0 ,0,
                FountSet(Font.PLAIN,15),JTextField.LEFT);

        JLabel teacherPasswordLabel = CreateJLabel(inputPanel,"请输入添加的教师密码",200,  50,0 ,0,
                FountSet(Font.PLAIN,15),JLabel.LEFT);
        JTextField teacherPasswordTextField =CreateJTextField(inputPanel,150,  50,0 ,0,
                FountSet(Font.PLAIN,15),JTextField.LEFT);
        panel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setPreferredSize(new Dimension(700, 100));
        // 提交按钮
        JButton submitButton = CreateJButton(buttonPanel,"确定",100,  50,400 ,0);
        // 返回按钮
        JButton backButton = CreateJButton(buttonPanel,"返回",100,  50,0 ,0);

        // 设置按钮的事件监听器
        JTextArea finalTeacherAccountInfoTextArea = teacherAccountInfoTextArea;
        submitButton.addActionListener(new ActionListener() {
            // 点击提交按钮后执行的操作
            //获取输入的信息


            @Override
            public void actionPerformed(ActionEvent e) {
                String teacherID = teacherIDTextField.getText();
                String teacherPassword = teacherPasswordTextField.getText();
                String teacherName = teacherNameTextField.getText();

                Pair<Boolean, String> result = manager.addTeacherAccountPart(teacherID, teacherPassword,teacherName);

                if (result.getKey()) {
                    JOptionPane.showMessageDialog(panel, result.getValue(), "提示", JOptionPane.INFORMATION_MESSAGE);
                    // 重新获取TeacherAccountInfo
                    String teacherAccountInfo = manager.getTeacherAccountInfo();
                    // 添加内容到JTextArea
                    finalTeacherAccountInfoTextArea.setText(teacherAccountInfo);
                    // 清空输入框
                    teacherIDTextField.setText("");
                    teacherPasswordTextField.setText("");
                    teacherNameTextField.setText("");

                } else {
                    JOptionPane.showMessageDialog(panel, result.getValue(), "提示", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminPage();
            }
        });


        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    //删除学生账号界面
    private JPanel createDeleteStudentAccountPanel(Manager manager) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = CreateJLabel(panel, "管理员 "+ manager.getName() +" 请输入要删除的学生账号", 700, 50,0,0);
        // 创建用于显示数据的文本区域
        JTextArea studentAccountInfoTextArea = null;
//        JScrollPane scrollPane = new JScrollPane(studentAccountInfoTextArea);
        studentAccountInfoTextArea = new JTextArea();
        studentAccountInfoTextArea.setEditable(false); // 设置为只读
        studentAccountInfoTextArea.setLineWrap(true); // 自动换行
        studentAccountInfoTextArea.setWrapStyleWord(true); // 断行不断字
        studentAccountInfoTextArea.setFont(new Font("宋体", Font.PLAIN, 15));
        // 创建JScrollPane，并将JTextArea添加到其中
        JScrollPane scrollPane = new JScrollPane(studentAccountInfoTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // 显示垂直滚动条
        // 将JScrollPane添加到面板的中间区域
        scrollPane.setPreferredSize(new Dimension(700, 200));

        panel.add(scrollPane, BorderLayout.CENTER);

        // 获取预约信息
        String studentAccountInfo = manager.getStudentAccountInfo();
        // 添加内容到JTextArea
        studentAccountInfoTextArea.append(studentAccountInfo);
//        panel.add(scrollPane, BorderLayout.CENTER);



        // 输入要取消的预约编号
        JLabel studentAccountIDLabel = CreateJLabel(panel ,"请输入要删除的学生账号的学号：",200, 50,0,0
                ,FountSet(Font.BOLD,12),JLabel.LEFT);
        JTextField studentAccountIDTextField = CreateJTextField(panel, 100, 50,0,0);

        // 提交按钮
        JButton submitButton = CreateJButton(panel, "提交", 100, 50,100,0);

        // 返回按钮
        JButton backButton = CreateJButton(panel, "返回", 100, 50,0,0);

        // 设置按钮的事件监听器
        JTextArea finalStudentAccountInfoTextArea = studentAccountInfoTextArea;
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentAccountID = studentAccountIDTextField.getText();
                Pair<Boolean, String> result = manager.deleteStudentAccountPart(studentAccountID);

                if (result.getKey()) {
                    JOptionPane.showMessageDialog(panel, result.getValue(), "提示", JOptionPane.INFORMATION_MESSAGE);
                    // 重新获取StudentAccountInfo
                    String studentAccountInfo = manager.getStudentAccountInfo();
                    // 添加内容到JTextArea
                    finalStudentAccountInfoTextArea.setText(studentAccountInfo);
                    // 清空输入框
                    studentAccountIDTextField.setText("");
                } else {
                    JOptionPane.showMessageDialog(panel, result.getValue(), "提示", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminPage();
            }
        });

        return panel;

    }
    //删除教师账号界面
    private JPanel createDeleteTeacherAccountPanel(Manager manager) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = CreateJLabel(panel, "管理员 "+ manager.getName() +" 请输入要删除的教师账号", 700, 50,0,0);
        // 创建用于显示数据的文本区域
        JTextArea teacherAccountInfoTextArea = null;
//        JScrollPane scrollPane = new JScrollPane(teacherAccountInfoTextArea);
        teacherAccountInfoTextArea = new JTextArea();
        teacherAccountInfoTextArea.setEditable(false); // 设置为只读
        teacherAccountInfoTextArea.setLineWrap(true); // 自动换行
        teacherAccountInfoTextArea.setWrapStyleWord(true); // 断行不断字
        teacherAccountInfoTextArea.setFont(new Font("宋体", Font.PLAIN, 15));
        // 创建JScrollPane，并将JTextArea添加到其中
        JScrollPane scrollPane = new JScrollPane(teacherAccountInfoTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // 显示垂直滚动条
        // 将JScrollPane添加到面板的中间区域
        scrollPane.setPreferredSize(new Dimension(700, 200));

        panel.add(scrollPane, BorderLayout.CENTER);
        
        String teacherAccountInfo = manager.getTeacherAccountInfo();

        // 添加内容到JTextArea
        teacherAccountInfoTextArea.append(teacherAccountInfo);
//        panel.add(scrollPane, BorderLayout.CENTER);



        // 输入要取消的预约编号
        JLabel teacherAccountIDLabel = CreateJLabel(panel ,"请输入要删除的教师账号的职工号：",200, 50,0,0
                ,FountSet(Font.BOLD,12),JLabel.LEFT);
        JTextField teacherAccountIDTextField = CreateJTextField(panel, 100, 50,0,0);

        // 提交按钮
        JButton submitButton = CreateJButton(panel, "提交", 100, 50,100,0);

        // 返回按钮
        JButton backButton = CreateJButton(panel, "返回", 100, 50,0,0);

        // 设置按钮的事件监听器
        JTextArea finalTeacherAccountInfoTextArea = teacherAccountInfoTextArea;
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teacherAccountID = teacherAccountIDTextField.getText();

                Pair<Boolean, String> result = manager.deleteTeacherAccountPart(teacherAccountID);

                if (result.getKey()) {
                    JOptionPane.showMessageDialog(panel, result.getValue(), "提示", JOptionPane.INFORMATION_MESSAGE);
                    // 重新获取TeacherAccountInfo
                    String teacherAccountInfo = manager.getTeacherAccountInfo();
                    // 添加内容到JTextArea
                    finalTeacherAccountInfoTextArea.setText(teacherAccountInfo);
                    // 清空输入框
                    teacherAccountIDTextField.setText("");
                } else {
                    JOptionPane.showMessageDialog(panel, result.getValue(), "提示", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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


    // 添加预约
    private boolean addReservation(String date, String timeSlot, String purpose) {
        // 执行添加预约的逻辑
        // 返回添加预约的结果，添加成功返回true，添加失败返回false
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
    private void showAddStudentAccountPage() {
        cardLayout.show(cardPanel, "AddStudentAccount");
    }
    private void showAddTeacherAccountPage() {
        cardLayout.show(cardPanel, "AddTeacherAccount");
    }
    private void showDeleteStudentAccountPage() {
        cardLayout.show(cardPanel, "DeleteStudentAccount");
    }
    private void showDeleteTeacherAccountPage() {
        cardLayout.show(cardPanel, "DeleteTeacherAccount");
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
