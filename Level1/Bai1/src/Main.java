import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListStudent listStudent = new ListStudent();
        Scanner input = new Scanner(System.in);

        int choose = 0;
        do {
            System.out.println("\n\n1. Nhập học viên. " +
                    "\n2. hiển thị học viên. " +
                    "\n3. Tìm kiếm học viên theo khoảng điểm." +
                    "\n4. Tìm kiếm học viên theo học lực." +
                    "\n5. Tìm kiếm học viên theo mã số và cập nhật thông tin." +
                    "\n6. Sắp xếp học viên theo điểm" +
                    "\n7. hiển thị 5 học viên có điểm cao nhất." +
                    "\n8. Hiển thị danh sánh học viên có điểm trên trung bình của lớp" +
                    "\n9. Tính điểm trung bình của lớp." +
                    "\n10. Tổng hợp số học viên theo học lực.");
            System.out.println("0. Thoát. ");
            System.out.print("lựa chọn: ");
            while (true) {
                try {
                    choose = Integer.parseInt(input.nextLine());
                    if (choose < 0 || choose > 10) {
                        System.out.print("nhập sai, vui lòng nhập lại số trong khoảng 0 - 10: ");
                        continue;
                    }
                    break;
                } catch (Exception ignored) {
                    System.out.print("Lựa chọn lại (Nhập số): ");
                }
            }

            switch (choose) {
                case 1:
                    listStudent.addStudent();
                    break;
                case 2:
                    listStudent.showListStudents();
                    break;
                case 3:
                    listStudent.searchByMark();
                    break;
                case 4:
                    listStudent.searchByRank();
                    break;
                case 5:
                    listStudent.searchByCode();
                    break;
                case 6:
                    listStudent.sortByMark();
                    break;
                case 7:
                    listStudent.showTopFiveMark();
                    break;
                case 8:
                    listStudent.showGreaterThanAvg();
                    break;
                case 9:
                    System.out.println("Điểm trung bình: " + listStudent.avgMark());
                    break;
                case 10:
                    listStudent.countStudentsByRank();
                    break;
                default:
                    break;
            }
        }
        while (choose != 0);
    }
}
