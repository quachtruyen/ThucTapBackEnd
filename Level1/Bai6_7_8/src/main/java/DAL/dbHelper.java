package DAL;

import java.sql.*;

public class dbHelper {
    private static Connection conn = null;

    //    tạo kết nối
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String connectionString = "jdbc:mysql://127.0.0.1:3306/thuctap";
                conn = DriverManager.getConnection(connectionString, "root", "13112001");
            } catch (SQLException | ClassNotFoundException s) {
                System.out.println("Kết nối jdbc thất bại" + s);
            }
        }

        return conn;
    }

    //    Dong ket noi
    public static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }

    //    thuc hien truy van
    public static ResultSet execQuerry(String querry) throws SQLException {
        ResultSet result;

        PreparedStatement connPreparedState = conn.prepareStatement(querry);
        result = connPreparedState.executeQuery();

        return result;
    }

    public static void execUpdate(String query) throws SQLException {
        PreparedStatement connPreparedState = conn.prepareStatement(query);
        connPreparedState.executeUpdate();
    }
}
