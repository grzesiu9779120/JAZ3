package pl.edu.pjwstk.jaz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.model.User;
import pl.edu.pjwstk.jaz.repository.UserRepository;
import pl.edu.pjwstk.jaz.service.UserService;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestController
public class RegisterController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        if (user.isEmpty()) {
            return new ResponseEntity<>("You must provide a user name and password.", HttpStatus.BAD_REQUEST);
        } else if (user.usernameIsEmpty()) {
            return new ResponseEntity<>("You must provide a user name.", HttpStatus.BAD_REQUEST);
        } else if (user.passwordIsEmpty()) {
            return new ResponseEntity<>("You must provide a password.", HttpStatus.BAD_REQUEST);
        }

        user.setRole("ROLE_USER");

        try {
            userService.saveUser(user);
        } catch (EntityExistsException | EntityNotFoundException message) {
            return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Registered.", HttpStatus.CREATED);
    }
}