package data.dbconnections;

import java.sql.*;

public class ConnectionMySQL {
    private static Connection connection;
    public static final String URL_JDBC = "jdbc:mysql://@localhost:3306/user_lab?user=root&password=Erick123!";

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL_JDBC);
        }
        return connection;
    }

    public static void close(PreparedStatement preparedStatement, Connection conn) throws SQLException {
        preparedStatement.close();
        conn.close();
        connection = null;
    }

    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection conn) throws SQLException {
        resultSet.close();
        preparedStatement.close();
        conn.close();
        connection = null;
    }
}
