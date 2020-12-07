package pl.edu.pjwstk.jaz.zad2;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(String message) {
        super(message);
    }
}