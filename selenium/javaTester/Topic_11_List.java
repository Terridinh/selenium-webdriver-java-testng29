package javaTester;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Topic_11_List {
    @Test
    public void testList() {
        List<String> studentName = new ArrayList<String>();
        studentName.add("Nguyễn Văn A");
        studentName.add("Nguyễn Văn B");
        studentName.add("Nguyễn Văn C");

        // 3 element trong list
        System.out.println(studentName.size());

        System.out.println(studentName.get(0));
        System.out.println(studentName.get(1));
        System.out.println(studentName.get(2));

        // Nếu muốn lấy ra element cuối cùng của list -> Lấy tổng số lượng - 1
        System.out.println(studentName.get(studentName.size() - 1));

    }
}