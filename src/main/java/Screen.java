package main.java;

public class Screen {

    public static void main(String[] args) {

    }
    // 清除屏幕内容
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // 暂停程序执行，等待用户输入
    public static void pause() {
        System.out.println("按回车键继续...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 主选择操作界面
    public  static void mainOperateScreen() {
        Screen.clearScreen();
        System.out.println("--------------------------------------------------------------");
        System.out.println("*************************机房预约系统*************************");
        System.out.println("--------------------------------------------------------------");
        System.out.println("请选择您的身份\n");

        System.out.println("\t*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("\t*\t\t\t\t\t\t*");
        System.out.println("\t*\t\t 1.学生         \t*");
        System.out.println("\t*\t\t\t\t\t\t*");
        System.out.println("\t*\t\t 2.老师         \t*");
        System.out.println("\t*\t\t\t\t\t\t*");
        System.out.println("\t*\t\t 0.退出系统     \t*");
        System.out.println("\t*\t\t\t\t\t\t*");
        System.out.println("\t*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("请输入您的选择:");
    }


    // 学生登录界面
    public static void studentLoginScreen() {
        clearScreen();
        System.out.println("--------------------------------------------------------------");
        System.out.println("*********************欢迎进入学生登录界面*********************");
        System.out.println("--------------------------------------------------------------");
        System.out.println("若想返回上一级请按 *  || 直接退出系统请按 0 || 继续请按其他键 \n");
    }
    // 教师登录界面
    public static void teacherLoginScreen() {
        clearScreen();
        System.out.println("--------------------------------------------------------------");
        System.out.println("*********************欢迎进入教师登录界面*********************");
        System.out.println("--------------------------------------------------------------");
        System.out.println("若想返回上一级请按 *  || 直接退出系统请按 0 || 继续请按其他键 \n");
    }

    // 管理员登录界面
    public static void managerLoginScreen() {
        clearScreen();
        System.out.println("--------------------------------------------------------------");
        System.out.println("********************欢迎进入管理员登录界面********************");
        System.out.println("--------------------------------------------------------------");
        System.out.println("若想返回上一级请按 *  || 直接退出系统请按 0 || 继续请按其他键 \n");
    }


    // 管理员选择操作界面
    public static void managerOperateScreen(String username) {
        clearScreen();
        System.out.println("--------------------------------------------------------------");
        System.out.println("*************管理员-" + username + "-登录此系统*************");
        System.out.println("--------------------------------------------------------------");
        System.out.println("请选择您要进行的操作\n");

        // 省略其他界面构建代码...
    }

    // 学生选择操作界面
    public static void studentOperateScreen(String user) {
        clearScreen();
        System.out.println("--------------------------------------------------------------");
        System.out.println("*************同学--" + user + "-登录此系统**************");
        System.out.println("--------------------------------------------------------------");
        System.out.println("请选择您要进行的操作\n");
        System.out.println("\t*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("\t*\t\t\t\t\t*");
        System.out.println("\t*\t\t 1.申请预约         \t*");
        System.out.println("\t*\t\t\t\t\t*");
        System.out.println("\t*\t\t 2.查看我的预约     \t*");
        System.out.println("\t*\t\t\t\t\t*");
        System.out.println("\t*\t\t 3.查看所有预约     \t*");
        System.out.println("\t*\t\t\t\t\t*");
        System.out.println("\t*\t\t 4.取消预约         \t*");
        System.out.println("\t*\t\t\t\t\t*");
        System.out.println("\t*\t\t *.注销并返回上一级 \t*");
        System.out.println("\t*\t\t\t\t\t*");
        System.out.println("\t*\t\t 0.退出系统         \t*");
        System.out.println("\t*\t\t\t\t\t*");
        System.out.println("\t*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("请输入您的选择:");
    }

    // 教师选择操作界面
    public static void teacherOperateScreen(String username) {
        clearScreen();
        System.out.println("--------------------------------------------------------------");
        System.out.println("**************教师--" + username + "-登录此系统*************");
        System.out.println("--------------------------------------------------------------");
        System.out.println("请选择您要进行的操作\n");//构建一开始的主界面
        System.out.println("\t*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("\t*\t\t\t\t\t*");
        System.out.println("\t*\t\t 1.查看所有预约     \t*");
        System.out.println("\t*\t\t\t\t\t*");
        System.out.println("\t*\t\t 2.审核预约         \t*");
        System.out.println("\t*\t\t\t\t\t*");
        System.out.println("\t*\t\t *.注销并返回上一级 \t*");
        System.out.println("\t*\t\t\t\t\t*");
        System.out.println("\t*\t\t 0.直接退出系统     \t*");
        System.out.println("\t*\t\t\t\t\t*");
        System.out.println("\t*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("请输入您的选择:");

    }





    // 登录成功的反馈提示
    public static void loginSuccessPrompt(char type) {
        if (type == '1') {
            System.out.println("\n------------------------学生登录成功！------------------------\n");
            pause();
        }

        if (type == '2') {
            System.out.println("\n------------------------教师登录成功！------------------------\n");
            pause();
        }
        if (type == '3') {
            System.out.println("\n-----------------------管理员登录成功！-----------------------\n");
            pause();
        }
    }

    // 登录失败的提示
    public static void loginFailPrompt() {
        System.out.println("\n--------------------------登录失败！--------------------------\n");
        pause();
    }

    // 注销成功的反馈提示
    public static void logoutPrompt() {
        System.out.println("\n--------------------------注销成功！--------------------------\n");
        pause();
    }

    // 预约清空成功的反馈提示
    public static void clearSuccess() {
        System.out.println("\n------------------------预约清空成功！------------------------\n");
        pause();
    }

    // 直接退出系统的提示
    public static void directExitPrompt() {
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("******************系统退出成功，欢迎下次使用******************");
        System.out.println("--------------------------------------------------------------\n");
        System.exit(0);
    }

    // 显示账号添加状态的提示
    public static void isAddAccountPrompt(char fir, char sec) {
        if (fir == '1') {
            if (sec == '1') {
                System.out.println("\n-----------------------学生账号添加成功-----------------------\n");
                pause();
            }
            if (sec == '2') {
                System.out.println("\n----------------------学生账号添加失败！----------------------");
                System.out.println("------------------输入的学号与已有账号的重复------------------");
                System.out.println("--------------------请重新输入学生账号信息--------------------\n");
                pause();
            }

        }
        if (fir == '2') {
            if (sec == '1') {
                System.out.println("\n-----------------------教师账号添加成功-----------------------\n");
                pause();
            }
            if (sec == '2') {
                System.out.println("\n----------------------教师账号添加失败！----------------------");
                System.out.println("-----------------输入的职工号与已有账号的重复-----------------\n");
                System.out.println("--------------------请重新输入教师账号信息--------------------\n");
                pause();
            }
        }
    }

    // 显示是否有账号信息的提示
    public static void isHaveAccountPrompt(char type) {
        if (type == '1') {
            System.out.println("------------------------无学生账号信息------------------------\n");
            pause();
        }
        if (type == '2') {
            System.out.println("------------------------无教师账号信息------------------------\n");
            pause();
        }
    }

    // 显示所在操作层的提示
    public static void operateLevelShowing(char userType, int operatType) {
        if (userType == 'M') {
            if (operatType == 1)
                System.out.println("**********************进行添加账号的操作**********************\n");

            if (operatType == 2)
                System.out.println("**********************进行查看账号的操作**********************\n");

            if (operatType == 3)
                System.out.println("********************进行查看机房信息的操作********************\n");

            if (operatType == 4)
                System.out.println("********************进行显示所有预约的操作********************\n");

            if (operatType == 5)
                System.out.println("**********************进行清空预约的操作**********************\n");
        }

        if (userType == 'S') {
            if (operatType == 1)
                System.out.println("**********************进行申请预约的操作**********************\n");

            if (operatType == 2)
                System.out.println("********************进行显示我的预约的操作********************\n");

            if (operatType == 3)
                System.out.println("******************进行显示所有人的预约的操作******************\n");

            if (operatType == 4)
                System.out.println("*******************进行取消自己的预约的操作*******************\n");
        }

        if (userType == 'T') {
            if (operatType == 1)
                System.out.println("******************进行显示所有人的预约的操作******************\n");

            if (operatType == 2)
                System.out.println("******************进行审核申请人的预约的操作******************\n");

            if (operatType == 3)
                System.out.println("********************进行查看学生账号的操作********************\n");
        }
    }

    // 进行申请预约的操作提示和机房情况说明界面
    public static void applyOrderTips() {
        System.out.println("-------机房基本全年开放，如有特殊情况将会在界面进行提示------------");
        System.out.println("---------------机房每天的开放时间为8:00至21:00----------------");
        System.out.println("|请根据提示依次输入 机房编号 使用时间 来进行填写                  |");
        System.out.println("|使用时间请按照：年-月-日 时:分:秒  进行填写  例如2022-1-6 16:30 |");
        System.out.println("----------------------------------------------------------\n");
    }

    // 申请预约成功的提示
    public static void applySuccessPrompt() {
        System.out.println("\n------------------------预约添加成功！------------------------\n");
        pause();
    }

    // 申请预约失败的提示
    public static void applyFailPrompt() {
        System.out.println("\n------------------------预约添加失败！------------------------");
        System.out.println("----您的预约与其他预约相冲突，请重新挑选机房或时间进行预约----\n");
        pause();
    }

    // 取消预约成功的提示
    public static void cancelSuccessPrompt() {
        System.out.println("\n------------------------取消预约成功！------------------------\n");
        pause();
    }

    // 取消操作成功的提示
    public static void cancelOperatePrompt() {
        System.out.println("\n------------------------取消操作成功！------------------------\n");
        pause();
    }

    // 审核预约成功的提示
    public static void reviewSuccessPrompt() {
        System.out.println("\n------------------------审核预约成功！------------------------\n");
        pause();
    }

    // 审核预约失败的提示
    public static void reviewFailPrompt() {
        System.out.println("\n------------------------审核预约失败！------------------------\n");
        pause();
    }
}

