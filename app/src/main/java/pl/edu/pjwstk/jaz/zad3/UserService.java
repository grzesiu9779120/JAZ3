package pl.edu.pjwstk.jaz.zad3;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.util.Optional;

@Transactional
@Service
public class UserService {
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveUser(User user) {
        if (findByUsername(user.getUsername()).isPresent()) {
            throw new EntityExistsException("A user with this name already exists.");
        }
        var userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setIdRole(Integer.parseInt(user.getIdRole()));

        entityManager.persist(userEntity);

        if(findByUsername("admin").isEmpty()){
            userEntity.setIdRole(1);
            userEntity.setUsername("admin");
            userEntity.setPassword("psadmin");
            entityManager.persist(userEntity);
        }
    }

    public Optional<UserEntity> findByUsername(String username) {
        try {
            return Optional.ofNullable(entityManager.createQuery("select ue from UserEntity ue where ue.username=:username", UserEntity.class)
                    .setParameter("username", username)
                    .getSingleResult());
        } catch (NoResultException ms) {
            return Optional.empty();
        }
    }
}
