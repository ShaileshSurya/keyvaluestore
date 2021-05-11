package keyvaluestore.exception;

public class InvalidCommandException extends RuntimeException {
    public static final String message = "Invalid Command";

    public InvalidCommandException(){
        super(message);
    }
}
