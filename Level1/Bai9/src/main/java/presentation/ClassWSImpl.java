package presentation;

import BL.ClassBL;
import Persistence.Class;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.jws.WebService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@WebService(targetNamespace="http://mycompany.net/dev/ph")
public class ClassWSImpl implements ClassWS {
    private static final Logger logger = LogManager.getLogger(ClassWSImpl.class);

    @Override
    public boolean checkLogin(String username, String password) {
        Properties properties = readFile("app.conf");
        if (username.equals(properties.getProperty("username")) && password.equals(properties.getProperty("password"))) {
            logger.info("Thành công");
            return true;
        }
        logger.warn("Username/password không đúng");
        return false;
    }

    @Override
    public boolean addClass(String username, String password, String name, int code) {
        Class aClass = new Class(name, code);
        try {
            if (checkLogin(username, password)) {
                ClassBL classBL = new ClassBL();
                classBL.insertClass(aClass);
                logger.info("Thành công");
                return true;
            }
            logger.error("Tạo class thất bại!");
            return false;
        } catch (SQLException | IOException | ClassNotFoundException e) {
            logger.error("Tạo class thất bại: " + e);
            return false;
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
