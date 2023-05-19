package test;

import main.java.Student;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private InputStream originalSystemIn;

    @Before
    public void setUp() {
        originalSystemIn = System.in; // 保存原始的System.in
    }
    @Test
    void menu() {
    }

    @Test
    public void testApplyOrder() {
        String input = "1\n2022-13-31 12:00\n2023-01-01 12:00:00\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream); // 将System.in设置为模拟的输入流

        new Student("1", "1", "1").applyOrder();
//        assertEquals(true, result); // 验证预期结果

        System.setIn(originalSystemIn); // 恢复原始的System.in
    }

    @Test
    void showMyOrder() {
    }

    @Test
    void showAllOrder() {
    }

    @Test
    void cancelMyOrder() {
    }
}