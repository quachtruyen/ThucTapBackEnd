import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ListStudent {
    private ArrayList<Student> listStudent;

    private static Scanner input = new Scanner(System.in);

    public ListStudent() {
        this.listStudent = new ArrayList<>();
    }

    public void addStudent() {
        Validate validate = new Validate();
        System.out.print("số học viên cần nhập: ");
        int amountStudent = 0;

        while (true) {
            try {
                amountStudent = Integer.parseInt(input.next());
                if (amountStudent < 0) {
                    System.out.print("nhập sai vui lòng nhập lại số lớn hơn 0: ");
                    continue;
                }
                break;
            } catch (Exception ignored) {
                System.out.print("nhập sai vui lòng nhập lại (nhập số):");
            }
        }
        for (int i = 0; i < amountStudent; i++) {
            System.out.println("nhập học viên thứ " + (i + 1));
            System.out.print("nhập tên: ");
            String name;
            while (true) {
                name = input.next();
                if (!validate.validateName(name)) {
                    System.out.println("Tên không hợp lệ!");
                    System.out.print("Nhập lại tên: ");
                    continue;
                }
                break;

            }
            System.out.print("nhập điểm: ");
            float mark = 0;
            while (true) {
                try {
                    mark = Integer.parseInt(input.next());
                    if (mark < 0 || mark > 10) {
                        System.out.print("nhập sai vui lòng nhập lại số từ 0 - 10: ");
                        continue;
                    }
                    break;
                } catch (Exception ignored) {
                    System.out.print("nhập sai vui lòng nhập lại (nhập số):");
                }
            }
            System.out.print("nhập email: ");
            String email;
            while (true) {
                email = input.next();
                if (!validate.validateEmail(email)) {
                    System.out.println("Email không hợp lệ!");
                    System.out.print("Nhập lại Email: ");
                    continue;
                }
                break;
            }
            Student stu = new Student(name, mark, email);

            listStudent.add(stu);

        }
    }

    public void showListStudents() {
        System.out.println("Tên\t\t\t" + "Điểm\t" + "Email\t\t\t\t" + "Học lực");
        for (Student student : listStudent) {
            System.out.println(student.getName() + "\t\t\t" + student.getMark() + " \t" + student.getEmail() + " \t\t\t\t\t" + student.getRank());
        }
    }

    public void searchByMark() {
        System.out.println("Điểm nhỏ nhất: ");
        float min = input.nextFloat();
        System.out.println("Điểm lớn nhất: ");
        float max = input.nextFloat();

        for (Student student : listStudent) {
            if (student.getMark() >= min && student.getMark() <= max) {
                System.out.println(student.toString());
            }
        }
    }

    public void searchByRank() {
        System.out.println("Học lực: " +
                "\n1. Yếu " +
                "\n2. Trung Bình" +
                "\n3. Khá" +
                "\n4. Giỏi" +
                "\n5. Xuất sắc");
        System.out.print("lựa chọn: ");
        int hocLuc = 0;
        while (true) {
            try {
                hocLuc = Integer.parseInt(input.next());
                if (hocLuc < 0 || hocLuc > 5) {
                    System.out.print("nhập sai, vui lòng nhập lại số trong khoảng 0 - 10: ");
                    continue;
                }
                break;
            } catch (Exception ignored) {
                System.out.print("Lựa chọn lại (Nhập số): ");
            }
        }

        switch (hocLuc) {
            case 1:
                for (Student student : listStudent) {
                    if (student.getRank().equals(Constant.rank.Y.getLabel())) {
                        System.out.println(student);
                    }
                }
                break;
            case 2:
                for (Student student : listStudent) {
                    if (student.getRank().equals(Constant.rank.TB.getLabel())) {
                        System.out.println(student);
                    }
                }
                break;
            case 3:
                for (Student student : listStudent) {
                    if (student.getRank().equals(Constant.rank.K.getLabel())) {
                        System.out.println(student);
                    }
                }
                break;
            case 4:
                for (Student student : listStudent) {
                    if (student.getRank().equals(Constant.rank.G.getLabel())) {
                        System.out.println(student);
                    }
                }
                break;
            case 5:
                for (Student student : listStudent) {
                    if (student.getRank().equals(Constant.rank.XS.getLabel())) {
                        System.out.println(student);
                    }
                }
                break;
        }
    }

    public void searchByCode() {
        Validate validate = new Validate();
        System.out.println("nhập mã: ");
        int maSo = input.nextInt();

        System.out.println("học viên có mã " + maSo);
        System.out.println(listStudent.get(maSo).toString());

        System.out.println("Bạn có muốn cập nhật thông tin học viên không?Y/N: ");
        String rep = input.next();
        if (rep.equals("Y")) {
            System.out.print("Sửa tên: ");
            String name;
            while (true) {
                name = input.next();
                if (!validate.validateName(name)) {
                    System.out.println("Tên không hợp lệ!");
                    System.out.print("Nhập lại tên: ");
                    continue;
                }
                break;

            }
            listStudent.get(maSo).setName(name);
            System.out.print("Sửa điểm: ");
            float mark = 0;
            while (true) {
                try {
                    mark = Integer.parseInt(input.next());
                    if (mark < 0 || mark > 10) {
                        System.out.print("nhập sai vui lòng nhập lại số từ 0 - 10: ");
                        continue;
                    }
                    break;
                } catch (Exception ignored) {
                    System.out.print("nhập sai vui lòng nhập lại (nhập số):");
                }
            }
            listStudent.get(maSo).setMark(mark);
            System.out.print("Sửa email: ");
            String email;
            while (true) {
                email = input.next();
                if (!validate.validateName(email)) {
                    System.out.println("Email không hợp lệ!");
                    System.out.print("Nhập lại Email: ");
                    continue;
                }
                break;

            }
            listStudent.get(maSo).setEmail(email);
            System.out.println(listStudent.get(maSo).toString());
        }
    }

    public void sortByMark() {
        Collections.sort(listStudent, new SortStudentByGPA());
        showListStudents();
    }

    public void showTopFiveMark() {
        Collections.sort(listStudent, new SortStudentByGPA());
        int length = listStudent.size();
        int finalIndex = 0;
        if (length > 5) {
            finalIndex = length - 5;
        }

        for (int firstIndex = length - 1; firstIndex >= finalIndex; firstIndex--) {
            System.out.println(listStudent.get(firstIndex).toString());
        }
    }

    public float avgMark() {
        if (listStudent.size() > 0) {
            float sum = 0;
            for (Student student : listStudent) {
                sum += student.getMark();
            }
            return sum / listStudent.size();
        }

        return 0;
    }

    public void showGreaterThanAvg() {
        float avg = avgMark();
        for (Student student : listStudent) {
            if (student.getMark() > avg) {
                System.out.println(student);
            }
        }
    }

    public void countStudentsByRank() {
        System.out.println("Học lực: " +
                "\n1. Yếu " +
                "\n2. Trung Bình" +
                "\n3. Khá" +
                "\n4. Giỏi" +
                "\n5. Xuất sắc" +
                "\n0. Thoát");
        System.out.print("lựa chọn: ");
        int choose = 0;
        while (true) {
            try {
                choose = Integer.parseInt(input.next());
                if (choose < 0 || choose > 5) {
                    System.out.print("nhập sai vui lòng nhập lại số từ 0 - 5: ");
                    continue;
                }
                break;
            } catch (Exception ignored) {
                System.out.print("nhập sai vui lòng nhập lại (nhập số):");
            }
        }

        if (choose == 0) {
            return;
        }

        String hocluc = null;
        switch (choose) {
            case 1:
                hocluc = Constant.rank.Y.getLabel();
                break;
            case 2:
                hocluc = Constant.rank.TB.getLabel();
                break;
            case 3:
                hocluc = Constant.rank.K.getLabel();
                break;
            case 4:
                hocluc = Constant.rank.G.getLabel();
                break;
            case 5:
                hocluc = Constant.rank.XS.getLabel();
                break;
        }

        int count = 0;

        for (Student student : listStudent) {
            if (student.getRank().equals(hocluc)) {
                System.out.println(student);
                count++;
            }
        }
        System.out.println("Số học viên: " + count);
    }
}
