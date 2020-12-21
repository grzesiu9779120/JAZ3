package pl.edu.pjwstk.jaz.zad2;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndPointAuthorities {

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/onlyAdmin")
    public void access(){
        System.out.println("Hello admin.");
    }

    @PreAuthorize("hasAnyAuthority('user','admin')")
    @GetMapping("/read")
    public void read(){
        System.out.println("logged in");
    }

    @GetMapping("/auth0/filterUse")
    public void filter(){
        System.out.println("filter is working properly");
    }
}
