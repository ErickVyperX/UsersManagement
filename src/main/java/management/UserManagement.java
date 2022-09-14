package management;

import data.UserDAO;
import domain.User;
import exceptions.*;
import java.sql.SQLException;
import java.util.List;

public class UserManagement {

    public void listUsers() throws DataReadingException {
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.select().forEach(user -> System.out.println(user.toString()));
        } catch (SQLException e) {
            throw new DataReadingException("There was a problem to access the database" + e.getMessage());
        }
    }

    public void addUser(String username, String password) throws DataWritingException {
        User user = new User(username, password);
        UserDAO userDAO = new UserDAO();
        try {
            if (userDAO.insert(user) == 1) {
                System.out.println("User registered successfully!");
            }
        } catch (SQLException e) {
            throw new DataWritingException("There was a problem trying to add a user!" + e.getMessage());
        }
    }

    public void setPassword(String newPassword, int idUser) throws DataWritingException {
        User user = new User(idUser);
        UserDAO userDAO = new UserDAO();
        try {
            if (foundUser(user)) {
                if (userDAO.update(newPassword, user) == 1) {
                    System.out.println("Password updated successfully!");
                }
            } else {
                System.out.println("Password not changed. Because ID user is incorrect!");
            }
        } catch (SQLException e) {
            throw new DataWritingException("There was a problem trying to modify a user!" + e.getMessage());
        }
    }

    public boolean foundUser(User user) throws DataReadingException {
        UserDAO userDAO = new UserDAO();
        try {
            List<User> users = userDAO.select();
            for (User user1 : users) {
                if (user.getIdUser() == user1.getIdUser()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new DataReadingException("There was a problem to find the user!" + e.getMessage());
        }
        return false;
    }

    public void deleteUser(int idUser) throws DataWritingException {
        UserDAO userDAO = new UserDAO();
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
            throw new DataWritingException("There was a problem trying to delete a user!" + e.getMessage());
        }
    }
}
