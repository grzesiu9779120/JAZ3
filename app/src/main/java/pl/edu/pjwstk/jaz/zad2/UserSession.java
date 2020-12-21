package pl.edu.pjwstk.jaz.zad2;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class UserSession {
    private boolean isLogged = false;

    public boolean isLoggedIn() {

        return isLogged;
    }

    public void setLoggedIn(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public void logIn() {
        isLogged = true;
    }
}
