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

    @GetMapping("/getUsers")
    public ResponseEntity<String> findByUsername() {
        var user = userRepository.findAll();

        return new ResponseEntity<>(user.toString(), HttpStatus.OK);
    }

}
