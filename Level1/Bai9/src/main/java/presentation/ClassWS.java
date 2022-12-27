package presentation;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ClassWS {
    @WebMethod
    boolean checkLogin(String username, String password);

    @WebMethod
    boolean addClass(String username, String password, String name, int code);
}
