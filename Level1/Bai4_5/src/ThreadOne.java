public class ThreadOne extends Thread {
    private Student stu;

    public ThreadOne(Student stu) {
        this.stu = stu;
    }

    @Override
    public void run() {
        Constant.studentQueue.add(stu);
    }
}
