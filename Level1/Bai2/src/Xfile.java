import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Xfile {
    static List<Student> studentList = new ArrayList<>();

    public static void newStudent() {
        studentList = readFile();
        Scanner input = new Scanner(System.in);
        System.out.print("số sinh viên cần nhập: ");
        int amountStudent = input.nextInt();
        for (int i = 0; i < amountStudent; i++) {
            System.out.println("Nhập sinh viên thứ " + i);
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

            Student student = new Student(name, age, code, className, address);
            studentList.add(student);
        }

    }

    public static void writeFile() {
        newStudent();
        try {
            FileOutputStream file = new FileOutputStream("Students.txt");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(studentList);

            file.close();
            output.close();
        } catch (IOException e) {
            System.err.println("thất bại");
        } finally {
            System.out.println("Thành công");
        }
    }

    public static List<Student> readFile() {
        try {
            FileInputStream fileStream = new FileInputStream("Students.txt");
            if (fileStream.available() > 0) {
                ObjectInputStream input = new ObjectInputStream(fileStream);

                studentList = (List<Student>) input.readObject();
                input.close();
            }
            fileStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("Không tìm thấy file! " + e);
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy Class! " + e);
        } catch (IOException e) {
            System.err.println(e);
        }

        return studentList;
    }
}
