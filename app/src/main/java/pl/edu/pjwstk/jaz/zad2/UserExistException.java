package pl.edu.pjwstk.jaz.zad2;

public class UserExistException extends RuntimeException {
    public UserExistException(String message) {
        super(message);
    }
}
