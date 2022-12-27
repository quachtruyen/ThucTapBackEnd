import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n = 0;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("1. Thêm sinh viên" +
                    "\n2. Hiển thị sinh viên" +
                    "\n3. Dừng chương trình");
            System.out.print("lựa chon: ");
            n = input.nextInt();
            switch (n) {
                case 1:
                    Xfile.writeFile();
                    break;
                case 2:
                    List<Student> stu = Xfile.readFile();
                    for (Student student : stu) {
                        System.out.println(student.toString());
                    }
                    break;
            }
        } while (n != 3);
    }
}
