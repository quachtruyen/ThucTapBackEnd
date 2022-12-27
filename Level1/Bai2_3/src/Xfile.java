import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Xfile {

    static Student newStudent() {
        Scanner input = new Scanner(System.in);

        System.out.print("Name: ");
        String name = input.next();
        System.out.print("Age: ");
        int age = input.nextInt();
        System.out.print("Code: ");
        int code = input.nextInt();
        System.out.print("Class name: ");
        String className = input.next();
        System.out.print("Address: ");
        String address = input.next();

        return new Student(name, age, code, className, address);
    }

    public static void writeFile() {
        FileOutputStream fout;
        ObjectOutputStream oos;
        try {
            if (!hasObject("Students.txt")) {
                fout = new FileOutputStream("Students.txt");
                oos = new ObjectOutputStream(fout);
            } else {
                fout = new FileOutputStream("Students.txt", true);
                oos = new ObjectOutputStream(fout) {
                    @Override
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            }

            Student stu = newStudent();

            ThreadOne threadOne = new ThreadOne(stu);
            threadOne.start();

            oos.writeObject(stu);

            fout.close();
            oos.close();

            System.out.println("Thành công.");
        } catch (Exception e) {
            System.err.println("Thất bại!");
        }
    }

    public static LinkedList<Student> readFile() {
        LinkedList<Student> listStudent = new LinkedList<>();
        FileInputStream fis;
        ObjectInputStream inStream;
        try {
            fis = new FileInputStream("Students.txt");
            inStream = new ObjectInputStream(fis);
            Object s = null;

            while (true) {
                try {
                    s = inStream.readObject();
                } catch (Exception e) {
                    break;
                }

                listStudent.add((Student) s);
            }
            fis.close();
            inStream.close();
        } catch (IOException e) {
            System.err.println("Thất bại!" + e);
        }

        return listStudent;
    }

    public static boolean hasObject(String filePath) {
        FileInputStream fi;
        boolean check = true;
        try {
            fi = new FileInputStream(filePath);
            ObjectInputStream inStream = new ObjectInputStream(fi);
            if (inStream.readObject() == null) {
                check = false;
            }
            inStream.close();
        } catch (Exception e) {
            check = false;
            System.err.println(e);
        }
        return check;
    }
}
