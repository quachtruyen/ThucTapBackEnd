import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args){
        Endpoint.publish(Constant.URL, new StudentServiceImpl());
    }
}
