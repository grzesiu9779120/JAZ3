package pl.edu.pjwstk.jaz.zad2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    final UsersRepository usersRepository;

@Autowired
    public RegisterController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping ("/register")
    public ResponseEntity<String> register (@RequestBody User user) {
        try {
            usersRepository.addUser(user);
            return new ResponseEntity<>("Registration successful", HttpStatus.OK);
        }
        catch (UserExistException | BadCredentialsException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }
}
