package presentation;

import Persistence.Student;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ThreadOne extends Thread {
    private static final Logger logger = LogManager.getLogger(ThreadOne.class);
    Student student;

    public ThreadOne(Student student) {
        this.student = student;
    }

    @Override
    public void run() {
        if(Constant.studentQueue.add(student)) {
            logger.info("Thành công");
        }
        else {
            logger.warn("Thất bại");
        }
    }
}
