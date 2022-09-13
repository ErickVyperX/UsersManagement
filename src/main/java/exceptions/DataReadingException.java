package exceptions;

public class DataReadingException extends DatabaseConnectionException{
    public DataReadingException(String message) {
        super(message);
    }
}
