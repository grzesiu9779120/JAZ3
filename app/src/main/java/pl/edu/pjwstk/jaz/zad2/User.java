package pl.edu.pjwstk.jaz.zad2;

import org.springframework.stereotype.Component;

@Component
public class User {
    private String username;
    private String password;
    private String role;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

}
