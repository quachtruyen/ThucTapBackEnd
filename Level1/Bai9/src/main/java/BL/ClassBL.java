package BL;

import DAL.ClassDAL;
import Persistence.Class;

import java.io.IOException;
import java.sql.SQLException;

public class ClassBL {
    ClassDAL classDAL = new ClassDAL();

    public void insertClass(Class aClass) throws SQLException, IOException, ClassNotFoundException {
        classDAL.insertClass(aClass);
    }

    public boolean checkForExistence(String nameClass) {
        return classDAL.checkForExistence(nameClass);
    }
}
