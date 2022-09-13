package test;

import exceptions.DatabaseConnectionException;
import management.UserManagement;
import java.util.Scanner;

public class TestUserManagement {
    public static void main(String[] args) throws DatabaseConnectionException {
        Scanner scanner = new Scanner(System.in);
        int option, idUser;
        String username, newPassword, lineOption;
        UserManagement userManagement = new UserManagement();
        do {
            System.out.println("""
                    Welcome to your Password Manager. Please, enter a number option:
                    1- Show your users
                    2- Add a new user
                    3- Set a password
                    4- Delete a user
                    5- Exit""");
            lineOption = scanner.nextLine();
            option = lineOption.matches("[0-9]*")
                    ? Integer.parseInt(lineOption)
                    : -1;
            switch (option) {
                case 1 -> userManagement.listUsers();
                case 2 -> {
                    System.out.println("Enter a username: ");
                    username = scanner.nextLine();
                    System.out.println("Enter a password: ");
                    newPassword = scanner.nextLine();
                    userManagement.addUser(username, newPassword);
                }
                case 3 -> {
                    System.out.println("Enter the User ID: ");
                    idUser = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the new password: ");
                    newPassword = scanner.nextLine();
                    userManagement.setPassword(newPassword, idUser);
                }
                case 4 -> {
                    System.out.println("Enter the User ID: ");
                    idUser = Integer.parseInt(scanner.nextLine());
                    userManagement.deleteUser(idUser);
                }
                case 5 -> System.out.println("Bye Bye!");
                default -> System.out.println("Invalid Option!");
            }
        } while (option != 5);
    }
}
