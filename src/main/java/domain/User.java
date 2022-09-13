package domain;

import java.util.StringJoiner;

public class User {
    private int idUser;
    private String username;
    private String password;

    public User() {
    }

    public User(int idUser) {
        this.idUser = idUser;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("idUser=" + idUser)
                .add("user='" + username + "'")
                .add("password='" + password + "'")
                .toString();
    }
}
