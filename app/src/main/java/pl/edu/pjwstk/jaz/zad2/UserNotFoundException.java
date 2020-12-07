package pl.edu.pjwstk.jaz.zad2;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
