package DAL;

import Persistence.Student;

import java.sql.SQLException;

public class StudentDAL {
    private String query;

    public void insertStudent(Student stu) throws SQLException {
        query = "INSERT INTO Student" +
                "(name, code, age, className, address, mark) " +
                "VALUES (" + stu.getName() + "," + stu.getCode() + "," + stu.getAge()
                + "," + stu.getClassName() + "," + stu.getAddress() + "," + stu.getMark() + ");";
        dbHelper.getConnection();
        dbHelper.execUpdate(query);

        dbHelper.closeConnection();
    }
}
