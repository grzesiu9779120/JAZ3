package pl.edu.pjwstk.jaz.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.repository.UserRepository;

@Service
public class UserDetailsServiceIml implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceIml(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // todo throw if not exception
        return userRepository.findByUsername(username).get();
    }
}
