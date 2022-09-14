package data;

import static data.dbconnections.ConnectionMySQL.*;
import domain.User;
import java.sql.*;
import java.util.*;

public class UserDAO implements IDataAccess{
    private static Connection transactionConnection;

    public static final String SELECT_SQL = "SELECT id_user, username, password FROM user";
    public static final String SELECT_BY_ID_SQL = "SELECT id_user, username, password FROM user WHERE id_user = ?";
    public static final String SELECT_BY_USERNAME_SQL = "SELECT id_user FROM user WHERE username = ?";
    public static final String INSERT_SQL = "INSERT INTO user (username, password) VALUES (?, ?)";
    public static final String UPDATE_SQL = "UPDATE user SET password = ? WHERE id_user = ?";
    public static final String DELETE_SQL = "DELETE FROM user WHERE id_user = ?";

    public UserDAO() {
    }

    public UserDAO(boolean autoCommit) throws SQLException {
        transactionConnection = getConnection();
        transactionConnection.setAutoCommit(autoCommit);
    }

    public static void commit() throws SQLException {
        transactionConnection.commit();
    }

    public static void rollback() throws SQLException {
        transactionConnection.rollback();
    }

    public static void closeTransactionConnection() throws SQLException {
        close(transactionConnection);
    }

    @Override
    public List<User> select() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(SELECT_SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setIdUser(rs.getInt(1));
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            users.add(user);
        }
        close(rs, ps, conn);
        return users;
    }

    @Override
    public User selectById(User user) throws SQLException {
        User user1 = null;
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL);
        ps.setInt(1, user.getIdUser());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user1 = new User();
            user1.setIdUser(rs.getInt(1));
            user1.setUsername(rs.getString(2));
            user1.setPassword(rs.getString(3));
        }
        close(rs, ps, conn);
        return user1;
    }

    @Override
    public User selectByUsername(User user) throws SQLException {
        User user1 = null;
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(SELECT_BY_USERNAME_SQL);
        ps.setString(1, user.getUsername());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user1 = new User();
            user1.setIdUser(rs.getInt(1));
        }
        close(rs, ps, conn);
        return user1;
    }

    @Override
    public int insert(User user) throws SQLException {
        Connection conn = transactionConnection != null ? transactionConnection : getConnection();
        PreparedStatement ps = conn.prepareStatement(INSERT_SQL);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        int rowsAffected = ps.executeUpdate();
        close(ps);
        if (transactionConnection == null) {
            close(conn);
        }
        return rowsAffected;
    }

    @Override
    public int update(String newPassword, User user) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_SQL);
        ps.setString(1, newPassword);
        ps.setInt(2, user.getIdUser());
        int rowsAffected = ps.executeUpdate();
        close(ps, conn);
        return rowsAffected;
    }

    @Override
    public int delete(User user) throws SQLException {
        Connection conn = transactionConnection != null ? transactionConnection : getConnection();
        PreparedStatement ps = conn.prepareStatement(DELETE_SQL);
        ps.setInt(1, user.getIdUser());
        int rowsAffected = ps.executeUpdate();
        close(ps);
        if (transactionConnection == null) {
            close(conn);
        }
        return rowsAffected;
    }
}
