package test;

import main.java.Screen;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScreenTest {
    public static void main(String[] args) {
        Screen.applyOrderTips();
    }


    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.testng.annotations.Test
    void clearScreen() {
    }

    @org.junit.jupiter.api.Test
    void pause() {
    }

    @org.junit.jupiter.api.Test
    void mainOperateScreen() {
        Screen.mainOperateScreen();
    }

    @org.junit.jupiter.api.Test
    void studentLoginScreen() {
        Screen.studentLoginScreen();
    }

    @org.junit.jupiter.api.Test
    void teacherLoginScreen() {
        Screen.teacherLoginScreen();
    }

    @org.junit.jupiter.api.Test
    void managerLoginScreen() {
        Screen.managerLoginScreen();
    }

    @org.junit.jupiter.api.Test
    void managerOperateScreen() {
        Screen.managerOperateScreen("12121");
    }

    @Test
    public void studentOperateScreen() {
        Screen.studentOperateScreen("45454");
    }

    @org.junit.jupiter.api.Test
    void teacherOperateScreen() {
        Screen.teacherOperateScreen("45454");
    }

    @org.junit.jupiter.api.Test
    void loginSuccessPrompt() {
    }

    @org.junit.jupiter.api.Test
    void loginFailPrompt() {
    }

    @org.junit.jupiter.api.Test
    void logoutPrompt() {
    }

    @org.junit.jupiter.api.Test
    void clearSuccess() {
    }

    @org.junit.jupiter.api.Test
    void directExitPrompt() {
    }

    @org.junit.jupiter.api.Test
    void isAddAccountPrompt() {
    }

    @org.junit.jupiter.api.Test
    void isHaveAccountPrompt() {
    }

    @org.junit.jupiter.api.Test
    void operateLevelShowing() {
        Screen.operateLevelShowing('T','2');
    }

    @org.junit.jupiter.api.Test
    void applyOrderTips() {
    }

    @org.junit.jupiter.api.Test
    void applySuccessPrompt() {
    }

    @Test
    void applyFailPrompt() {

    }

    @org.junit.jupiter.api.Test
    void cancelSuccessPrompt() {
    }

    @org.junit.jupiter.api.Test
    void cancelOperatePrompt() {
    }

    @org.junit.jupiter.api.Test
    void reviewSuccessPrompt() {
    }

    @org.junit.jupiter.api.Test
    void reviewFailPrompt() {
    }
}