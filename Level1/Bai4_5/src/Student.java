import java.io.Serializable;

public class Student implements Serializable {
    private String username;
    private String password;
    private String name;
    private int age;
    private int code;
    private String className;
    private String address;
    private float mark;

    public Student(String username, String password, String name, int age, int code, String className, String address, float mark) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.code = code;
        this.className = className;
        this.address = address;
        this.mark = mark;
    }

    public Student(String name, int age, int code, String className, String address) {
        this.name = name;
        this.age = age;
        this.code = code;
        this.className = className;
        this.address = address;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", code=" + code +
                ", className='" + className + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
