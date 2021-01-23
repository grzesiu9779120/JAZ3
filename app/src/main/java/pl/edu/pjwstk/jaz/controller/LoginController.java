package pl.edu.pjwstk.jaz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.pjwstk.jaz.model.User;
import pl.edu.pjwstk.jaz.repository.UserRepository;
import pl.edu.pjwstk.jaz.security.UserSession;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    private UserRepository userRepository;
    final UserSession userSession;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginController(UserRepository userRepository, UserSession userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpServletRequest request) {
        if (user.isEmpty()) {
            return new ResponseEntity<>("You must provide a user name and password.", HttpStatus.BAD_REQUEST);
        } else if (user.usernameIsEmpty()) {
            return new ResponseEntity<>("You must provide a user name.", HttpStatus.BAD_REQUEST);
        } else if (user.passwordIsEmpty()) {
            return new ResponseEntity<>("You must provide a password.", HttpStatus.BAD_REQUEST);
        }
        var findUser = userRepository.findByUsername(user.getUsername());
        if (findUser.isPresent()) {
            if (passwordEncoder.matches(user.getPassword(), findUser.get().getPassword())) {
                userSession.setLoggedIn(true);
            } else {
                return new ResponseEntity<>("Wrong password.", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("The user does not exist.", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Login.", HttpStatus.ACCEPTED);
    }
}
