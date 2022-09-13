package exceptions;

public class DataWritingException extends DatabaseConnectionException{
    public DataWritingException(String message) {
        super(message);
    }
}
