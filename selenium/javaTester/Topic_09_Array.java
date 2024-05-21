package javaTester;

public class Topic_09_Array {

    int[] studentAge = {15, 10, 20, 22};
    // Vừa khai báo vừa gán biến luôn
    static String[] studentName = {"Đinh Nữ Giáng Tiên", "Trần Sĩ Nghĩa"};

    // Khai báo trước rồi gán giá trị sau
    public static void main(String[] args) {

        String[] studentAddress = new String[5];
        studentAddress[0] = "Nguyên Văn A";
        studentAddress[1] = "Nguyên Văn B";
        studentAddress[2] = "Nguyên Văn C";
        studentAddress[3] = "Nguyên Văn D";
        studentAddress[4] = "Nguyên Văn E";

        System.out.println(studentName[0]);
        System.out.println(studentName[1]);

        for (int i = 0; i < studentAddress.length; i++) {
            System.out.println(studentAddress[i]);
        }

    }

}
