package presentation;

import BL.StudentBL;
import Persistence.Student;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.jws.WebService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@WebService(targetNamespace = "http://mycompany.net/dev/ph")
public class StudentWSImpl implements StudentWS {
    private static final Logger logger = LogManager.getLogger(StudentWSImpl.class);

    @Override
    public boolean checkLogin(String username, String password) {
        Properties properties = readFile("app.conf");
        if (username.equals(properties.getProperty("username")) && password.equals(properties.getProperty("password"))) {
            logger.info("Thành công");
            return true;
        }
        logger.error("Username/password không đúng");
        return false;
    }

    @Override
    public void addStudentFake(String username, String password, String name, int age, int code, String className, String address, int mark) {
        Student student = new Student(name, age, code, className, address, mark);
        try {
            if (checkLogin(username, password)) {
                StudentBL stuBL = new StudentBL();
                ThreadOne thr1 = new ThreadOne(student);
                thr1.start();
                stuBL.insertStudent(student);
            }
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            System.err.println("Thêm học viên thất bại!" + throwables);
        }
    }

    public static Properties readFile(String file) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(file));

        } catch (FileNotFoundException e) {
            System.err.println("Không tìm thấy file " + e);
        } catch (IOException e) {
            System.err.println("Đọc file lỗi " + e);
        }
        return prop;
    }
}
