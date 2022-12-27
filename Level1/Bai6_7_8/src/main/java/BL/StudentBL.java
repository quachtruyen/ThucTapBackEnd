package BL;

import DAL.StudentDAL;
import Persistence.Student;

import java.io.IOException;
import java.sql.SQLException;

public class StudentBL {
    StudentDAL stuDAL = new StudentDAL();

    public void insertStudent(Student stu) throws SQLException, IOException, ClassNotFoundException {
        stuDAL.insertStudent(stu);
    }
}
