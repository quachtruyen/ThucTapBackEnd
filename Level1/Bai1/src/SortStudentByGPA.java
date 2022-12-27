import java.util.Comparator;

public class SortStudentByGPA implements Comparator<Student> {
    @Override
//    nếu 2 student bằng nhau thì sẽ không thay đổi thứ tự và duy trì thứ tự được thêm vào
    public int compare(Student student1, Student student2) {
        if (student1.getMark() >= student2.getMark()) {
            return 1;
        }
        return -1;
    }
}
