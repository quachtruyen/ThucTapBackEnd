import java.util.List;

public class ThreadTwo implements Runnable {

    @Override
    public void run() {
        int whileProviso = 0;
        do {
            try {
                List<Student> studentList = Xfile.readFile();
                int serial = 0;
                System.out.println("\n");
                for (Student student : studentList) {
                    System.out.println(++serial + ": " + student.toString());
                }

                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            whileProviso++;
        } while (whileProviso != 10);
    }
}
