package presentation;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface StudentWS {
    @WebMethod
    boolean checkLogin(String username, String password);

    @WebMethod
    void addStudentReal(String username, String password, String name, int age, int code, String className, String address, int mark);
}
