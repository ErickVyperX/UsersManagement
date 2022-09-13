package data.dbconnections;

import java.sql.*;

public class ConnectionMySQL {
    public static final String URL_JDBC = "jdbc:mysql://@localhost:3306/user_lab?user=root&password=Erick123!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_JDBC);
    }

    public static void close(PreparedStatement preparedStatement, Connection connection) throws SQLException {
        preparedStatement.close();
        connection.close();
    }

    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) throws SQLException {
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
