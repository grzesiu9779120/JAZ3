package pl.edu.pjwstk.jaz.zad3;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestController
public class RegisterControllerWithJPA {
    private final UserService userService;

    public RegisterControllerWithJPA(UserService userService) {
        this.userService = userService;
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
        if (user.getIdRole()==null){
            user.setIdRole("2");
        }
        try {
            userService.saveUser(user);
        } catch (EntityExistsException | EntityNotFoundException message) {
            return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Registered.", HttpStatus.CREATED);
    }
}