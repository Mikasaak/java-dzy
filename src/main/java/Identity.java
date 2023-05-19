package main.java;

public abstract class Identity {
    protected String name; // 姓名
    protected String password; // 密码

    public abstract void menu(); // 抽象方法，用于派生类实现各个身份的操作菜单
}

