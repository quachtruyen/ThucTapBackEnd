import javax.jws.WebService;
import java.util.Properties;

@WebService(targetNamespace = "http://mycompany.net/dev/ph")
public class StudentServiceImpl implements StudentService {
    @Override
    public boolean checkLogin(String username, String password) {
        Properties properties = Xfile.readFile("app.conf");
        if (username.equals(properties.getProperty("username")) && password.equals(properties.getProperty("password"))) {
            System.out.println("Thành công");
            return true;
        }
        System.out.println("Username/password không đúng");
        return false;
    }

    @Override
    public void addNewStudent(String username, String password, String name, int age, int code, String className, String address, float mark) {
        if (checkLogin(username, password)) {
            Student student = new Student(username, password, name, age, code, className, address, mark);
            Xfile.writeFile(student);
        }
    }
}
