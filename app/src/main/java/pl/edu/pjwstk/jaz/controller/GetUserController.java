package pl.edu.pjwstk.jaz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.repository.UserRepository;


@RestController
public class GetUserController {
    private UserRepository userRepository;

    public GetUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("third/getUser/{username}")
    public ResponseEntity<String> findByUsername(@PathVariable("username") String username) {
        var user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return new ResponseEntity<>("The user was not found in the database.",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user.get().toString(), HttpStatus.OK);
    }

}
