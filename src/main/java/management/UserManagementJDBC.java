package management;

import data.UserDAO;
import data.UserDaoJDBC;
import domain.User;
import exceptions.*;
import java.sql.SQLException;
import static data.UserDaoJDBC.*;

public class UserManagementJDBC {

    public void listUsers() throws DataReadingException {
        UserDAO userDAO = new UserDaoJDBC();
        try {
            userDAO.select().forEach(user -> System.out.println(user.toString()));
        } catch (SQLException e) {
            throw new DataReadingException("There was a problem to access the database " + e.getMessage());
        }
    }

    public void addUser(String username, String password) throws DataWritingException {
        User user = new User(username, password);
        UserDAO userDAO = new UserDaoJDBC();
        try {
            User userFound = userDAO.selectByUsername(user);
            if (userFound != null) {
                userDAO = new UserDaoJDBC(false);
                userDAO.delete(userFound);
                userDAO.insert(user);
                commit();
                closeTransactionConnection();
                System.out.println("Transaction completed. User registered successfully!");
            } else if (userDAO.insert(user) == 1) {
                System.out.println("User registered successfully!");
            }
        } catch (SQLException e) {
            try {
                rollback();
                System.out.println("Transaction not completed!");
            } catch (SQLException ex) {
                throw new DataWritingException(ex.getMessage());
            }
            throw new DataWritingException("There was a problem trying to add a user! " + e.getMessage());
        }
    }

    public void setPassword(String newPassword, int idUser) throws DataWritingException {
        User user = new User(idUser);
        UserDAO userDAO = new UserDaoJDBC();
        try {
            if (foundUser(user)) {
                if (userDAO.update(newPassword, user) == 1) {
                    System.out.println("Password updated successfully!");
                }
            } else {
                System.out.println("Password not changed. Because ID user is incorrect!");
            }
        } catch (SQLException e) {
            throw new DataWritingException("There was a problem trying to modify a user! " + e.getMessage());
        }
    }

    public boolean foundUser(User user) throws DataReadingException {
        UserDAO userDAO = new UserDaoJDBC();
        try {
            if (userDAO.selectById(user) != null) {
                return true;
            }
        } catch (SQLException e) {
            throw new DataReadingException("There was a problem to find the user! " + e.getMessage());
        }
        return false;
    }

    public void deleteUser(int idUser) throws DataWritingException {
        UserDAO userDAO = new UserDaoJDBC();
        User user = new User(idUser);
        try {
            if (foundUser(user)) {
                if (userDAO.delete(user) == 1) {
                    System.out.println("User deleted successfully!");
                }
            } else {
                System.out.println("User not deleted. Because it doesn't exists!");
            }
        } catch (SQLException e) {
            throw new DataWritingException("There was a problem trying to delete a user! " + e.getMessage());
        }
    }
}
