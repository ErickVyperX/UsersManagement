package data.dbconnections;

import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.sql.*;

public class ConnectionMySQL {
    private static Connection connection;
    public static final String URL_JDBC = "jdbc:mysql://@localhost:3306/user_lab?user=root&password=Erick123!";

    public static DataSource getDataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl(URL_JDBC);
        bds.setInitialSize(5);
        return bds;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = getDataSource().getConnection();
        }
        return connection;
    }

    public static void close(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
        connection = null;
    }

    public static void close(Connection conn) throws SQLException {
        conn.close();
        connection = null;
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
