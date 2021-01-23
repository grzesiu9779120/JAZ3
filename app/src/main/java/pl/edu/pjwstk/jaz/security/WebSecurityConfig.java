package pl.edu.pjwstk.jaz.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private UserDetailsServiceIml userDetailsServiceIml;

    public WebSecurityConfig(UserDetailsServiceIml userDetailsServiceIml) {
        this.userDetailsServiceIml = userDetailsServiceIml;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceIml);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().disable();
        http.csrf().disable();
        http.authorizeRequests()

                    .antMatchers("/login").permitAll()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/addSection").authenticated()
                    .antMatchers("/getUsers").hasRole("ADMIN")


                    .antMatchers("/hello").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().defaultSuccessUrl("/hello");

                  //  .httpBasic();
    }
}


