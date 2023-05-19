package main.java;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
//            Screen.clearScreen();
            Screen.mainOperateScreen();
            char select = scanner.next().charAt(0);
            scanner.nextLine(); // 清空输入缓冲区
            switch (select) {
                case '1': // 用户身份为学生
                    while (true) {
                        Screen.studentLoginScreen();
                        char flag = scanner.next().charAt(0);
                        scanner.nextLine(); // 清空输入缓冲区

                        if (flag == '*') {
                            break;
                        }

                        if (flag == '0') {
                            Screen.directExitPrompt();
                        }

                        Identity user = Operate.loginAndCreate( '1');
                        if (user != null) {
                            Operate.studentMainMenuOperate(user);
                        }
                    }
                    break;

                case '2': // 用户身份为老师
                    while (true) {
//                        Screen.clearScreen();
                        Screen.teacherLoginScreen();
                        char flag = scanner.next().charAt(0);
                        scanner.nextLine(); // 清空输入缓冲区
                        if (flag == '*') {
                            break;
                        }

                        if (flag == '0') {
                            Screen.directExitPrompt();
                        }

                        Identity user = Operate.loginAndCreate( '2');
                        if (user != null) {
                            Operate.teacherMainMenuOperate(user);
                        }
                    }
                    break;

                case '3': // 用户身份为管理员
                    while (true) {
//                        Screen.clearScreen();
                        Screen.managerLoginScreen();
                        char flag = scanner.next().charAt(0);
                        scanner.nextLine(); // 清空输入缓冲区
                        if (flag == '*') {
                            break;
                        }

                        if (flag == '0') {
                            Screen.directExitPrompt();
                        }

                        Identity user = Operate.loginAndCreate( '3');
                        if (user != null) {
                            Operate.managerMainMenuOperate(user);
                        }
                    }
                    break;

                case '0': // 退出系统
                    System.out.println("已退出本系统");
                    scanner.close();
                    return;

                default: // 输入的选择不正确
                    System.out.println("请您输入正确的选择(按任意键继续)");
                    scanner.nextLine(); // 清空输入缓冲区
                    break;
            }
        }
    }
}