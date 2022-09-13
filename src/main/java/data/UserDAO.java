package data;

import static data.dbconnections.ConnectionMySQL.*;
import domain.User;
import java.sql.*;
import java.util.*;

public class UserDAO implements IDataAccess{
    public static final String SELECT_SQL_QUERY = "SELECT id_user, username, password FROM user";
    public static final String INSERT_SQL_QUERY = "INSERT INTO user (username, password) VALUES (?, ?)";
    public static final String UPDATE_SQL_QUERY = "UPDATE user SET password = ? WHERE id_user = ?";
    public static final String DELETE_SQL_QUERY = "DELETE FROM user WHERE id_user = ?";

    @Override
    public List<User> select() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(SELECT_SQL_QUERY);
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
    public int insert(User user) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(INSERT_SQL_QUERY);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        int rowsAffected = ps.executeUpdate();
        close(ps, conn);
        return rowsAffected;
    }

    @Override
    public int update(String newPassword, User user) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_SQL_QUERY);
        ps.setString(1, newPassword);
        ps.setInt(2, user.getIdUser());
        int rowsAffected = ps.executeUpdate();
        close(ps, conn);
        return rowsAffected;
    }

    @Override
    public int delete(User user) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(DELETE_SQL_QUERY);
        ps.setInt(1, user.getIdUser());
        int rowsAffected = ps.executeUpdate();
        close(ps, conn);
        return rowsAffected;
    }
}
