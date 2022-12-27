package DAL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;

public class dbHelper {
    private static Connection conn = null;

    private static final Logger loggerDB = LogManager.getLogger(dbHelper.class);

    //    tạo kết nối
    public static Connection getConnection(){
        if(conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String connectionString = "jdbc:mysql://127.0.0.1:3306/thuctap";
                conn = DriverManager.getConnection(connectionString, "root", "13112001");
            } catch (SQLException | ClassNotFoundException s) {
                s.printStackTrace();
            }
        }

        return conn;
    }

    //    Dong ket noi
    public static void closeConnection() throws SQLException {
        if(conn != null) {
            conn.close();
            conn = null;
        }
    }

    //    thuc hien truy van
    public static ResultSet execQuerry(String querry) throws SQLException {
        ResultSet result;
        try {
            PreparedStatement connPreparedState = conn.prepareStatement(querry);
            result = connPreparedState.executeQuery();
        } catch (SQLException throwables) {
            result = null;
        }

        return result;
    }

    public static void execUpdate(String query) throws SQLException {
        PreparedStatement connPreparedState = conn.prepareStatement(query);
        try {
            connPreparedState.executeUpdate();
            loggerDB.info("0");
        } catch (SQLException throwables) {
            loggerDB.error("1");
        }
    }
}
