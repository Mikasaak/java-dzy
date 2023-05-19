package main.java;

public class Manager extends Identity {
    private String ManagerID;//管理员ID
    public Manager(String ManagerID, String password, String name ) {
        this.ManagerID = ManagerID;
        super.password = password;//密码
        super.name = name;

    }

    @Override
    public void menu() {
        Screen.managerOperateScreen(super.name);
    }

    public void auditOrder() {

    }

    public void showAllOrder() {

    }
}
