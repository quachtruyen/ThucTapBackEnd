import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface StudentService {
    @WebMethod
    boolean checkLogin(String username, String password);

    @WebMethod
    void addNewStudent(String username, String password, String name, int age, int code,
                              String className, String address, float mark);
}
