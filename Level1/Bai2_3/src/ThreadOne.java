import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayDeque;
import java.util.Queue;

public class ThreadOne extends Thread {
    private Queue<Student> studentQueue = new ArrayDeque<>();

    private final Student student;

    public ThreadOne(Student student) {
        this.student = student;
    }

    @Override
    public void run() {
        addStudentToQueue(student);
    }

    public boolean addStudentToQueue(Student stu) {
        if (studentQueue.isEmpty()) {
            readFileToQueue();
        }
        return studentQueue.add(stu);
    }


    public void readFileToQueue() {
        FileInputStream fis;
        ObjectInputStream inStream;
        try {
            fis = new FileInputStream("Students.txt");
            inStream = new ObjectInputStream(fis);
            Object s;
            while (true) {
                try {
                    s = inStream.readObject();
                } catch (Exception e) {
                    break;
                }
                this.studentQueue.add((Student) s);
            }
            fis.close();
            inStream.close();
        } catch (IOException e) {
            System.err.println("Đọc file vào queue lỗi: " + e);
        }
    }
}
