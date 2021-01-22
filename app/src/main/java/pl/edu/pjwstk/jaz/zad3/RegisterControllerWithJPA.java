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

    @PostMapping("third/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        /*
        var user2 = userService.findByUsername("admin");
        if (user2.isEmpty()) {
            User user3 = new User();
            user3.setUsername("admin");
            user3.setPassword("pwadmin");
            user3.setIdRole("1");
            userService.saveUser(user3);
        }
        */
        /*
        var user2 = userService.findByUsername("admin");
        if (!user2.isPresent()) {
            User user3 = new User();
            user3.setUsername("admin");
            user3.setPassword("pwadmin");
            user3.setIdRole("1");
            userService.saveUser(user3);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        */



        if (user.isEmpty()) {
            return new ResponseEntity<>("You must provide a user name and password.", HttpStatus.BAD_REQUEST);
        } else if (user.usernameIsEmpty()) {
            return new ResponseEntity<>("You must provide a user name.", HttpStatus.BAD_REQUEST);
        } else if (user.passwordIsEmpty()) {
            return new ResponseEntity<>("You must provide a password.", HttpStatus.BAD_REQUEST);
        } else if (user.idRoleIsEmpty()) {
          //  user.setIdRole("2");
            return new ResponseEntity<>("You must provide a role.", HttpStatus.BAD_REQUEST);
        }
        try {
            userService.saveUser(user);
        } catch (EntityExistsException | EntityNotFoundException message) {
            return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Registered.", HttpStatus.CREATED);
    }
}