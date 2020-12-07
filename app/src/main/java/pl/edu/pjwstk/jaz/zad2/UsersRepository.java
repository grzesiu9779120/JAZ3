package pl.edu.pjwstk.jaz.zad2;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Scope("singleton")
public class UsersRepository {
    Map <String, User> users = new HashMap<>();

    public void addUser(User user){
        if(users.containsKey(user.getUsername())){
            throw new UserExistException("There is already a user with this username, please select a different name");
        }

        if ((user.getUsername() == null ) || (user.getPassword() == null )) {
            throw new BadCredentialsException("Incorrect data. Password or login is empty.");
        }
        users.put(user.getUsername(), user);
        if (user.getRole() == null) {
            user.setRole("user");
        }
    }

    public User getUser(String username) {
        if (users.containsKey(username)) {
            return users.get(username);
        }
        throw new UserNotFoundException("The user with the given name could not be found: " + username);
    }

}
