package presentation;

import Persistence.Student;

import java.util.ArrayDeque;
import java.util.Queue;

public class Constant {
    public static final Queue<Student> studentQueue = new ArrayDeque<>();
    public static final String URL = "http://localhost:8080/StudentWSImpl?wsdl";
}
