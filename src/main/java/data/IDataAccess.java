package data;

import domain.User;
import java.sql.SQLException;
import java.util.List;

public interface IDataAccess {

    List<User> select() throws SQLException;

    int insert(User user) throws SQLException;

    int update(String newPassword, User user) throws SQLException;

    int delete(User user) throws SQLException;
}