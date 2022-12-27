import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choose = 0;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("\n1. Thêm sinh viên" +
                    "\n2. Hiển thị sinh viên" +
                    "\n3. Hiển thị sinh viên sau mỗi 5s" +
                    "\n4. Dừng chương trình");
            System.out.print("lựa chon: ");
            choose = input.nextInt();
            switch (choose) {
                case 1:
                    Xfile.writeFile();
                    break;
                case 2:
                    List<Student> studentList = Xfile.readFile();
                    int i = 0;
                    for (Student student : studentList) {
                        System.out.println(++i + ": " + student.toString());
                    }
                    break;
                case 3:
                    ThreadTwo show = new ThreadTwo();
                    Thread thr = new Thread(show);
                    thr.start();
                    break;

            }
        } while (choose != 4);
    }
}
