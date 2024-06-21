package javaTester;

import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;

import java.io.File;

public class Topic_03_System_Info {
    public static void main(String args[]) {
        String osName = System.getProperty("os.name");
        Keys keys;

        if (osName.startsWith("Windows")) {
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }

        Keys cmdCtrl = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.CONTROL : Keys.COMMAND;

        // Lấy ra đường dẫn tương đối tại thư mục hiện tại
        String projectPath = System.getProperty("user.dir");
        // Cách 1 là dùng hàm if else ( nếu hệ điều hành là Windows thì dùng "\\", nếu hệ điều hành là IOS thì dùng "/"
        //String character = Platform.getCurrent().is(Platform.WINDOWS) ? "\\" : "/";

        // Cách thứ 2: Dùng separator -> nó sẽ tự động detect ra hệ điều hành và điều chỉnh được
        String character = File.separator;

        String batmanName = "batman.png";
        String workingName = "workingarea.jpg";
        String weddingName = "wedding.jpg";

        String batmanFilePath = projectPath + character + "uploadFiles" + character + batmanName;
        String workingFilePath = projectPath + character + "uploadFiles" + character + workingName;
        String weddingFilePath = projectPath + character + "uploadFiles" + character + weddingName;

        System.out.println(batmanFilePath);
        System.out.println(workingFilePath);
        System.out.println(weddingFilePath);



    }
}
