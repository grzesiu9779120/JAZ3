package pl.edu.pjwstk.jaz.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.model.User;
import pl.edu.pjwstk.jaz.repository.UserRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, EntityManager entityManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new EntityExistsException("A user with this name already exists.");
        }
        var userEntity = new User();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setRole(user.getRole());

        entityManager.persist(userEntity);

        if(userRepository.findByUsername("admin").isEmpty()){
            userEntity.setRole("ROLE_ADMIN");
            userEntity.setUsername("admin");
            userEntity.setPassword("pwadmin");
            entityManager.persist(userEntity);
        }
    }

}
