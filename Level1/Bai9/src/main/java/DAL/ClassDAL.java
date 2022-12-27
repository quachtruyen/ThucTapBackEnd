package DAL;

import Persistence.Class;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassDAL {
    private String query;
    private ResultSet rs;

    public void insertClass(Class stu) throws SQLException {
        query = "INSERT INTO Class " +
                "(name, code) " +
                "VALUES (" + stu.getName() + "," + stu.getCode() + ");";
        dbHelper.getConnection();
        dbHelper.execUpdate(query);

        dbHelper.closeConnection();
    }

    public boolean checkForExistence(String nameClass) {
        query = "SELECT * " +
                "FROM thuctap.cLass " +
                "WHERE name = " + nameClass + ";";

        try {
            dbHelper.getConnection();
            rs = dbHelper.execQuerry(query);
            if(!rs.first()) {
                return false;
            }
            dbHelper.closeConnection();
        } catch (SQLException throwables) {
            return false;
        }

        return true;
    }
}
