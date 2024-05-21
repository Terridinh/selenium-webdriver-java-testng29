package javaTester;

public class Topic_03_System_Info {
    public static void main(String args[]) {
        // Lấy ra đường dẫn tương đối tại thư mục hiện tại
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        String osName = System.getProperty("os.name");
        System.out.println(osName);
    }
}
