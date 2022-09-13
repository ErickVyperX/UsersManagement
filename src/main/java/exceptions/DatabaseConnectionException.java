package exceptions;

import java.sql.SQLException;

public class DatabaseConnectionException extends SQLException {
    public DatabaseConnectionException(String message) {
        super(message);
    }
}
