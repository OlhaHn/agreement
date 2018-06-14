package com.olhahn.agreementApp.config;

import com.olhahn.agreementApp.model.UserEntity;
import com.olhahn.agreementApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.util.List;

/**
 * Project: agreement.
 *
 * @author Olha Hnatiuk on 6/10/18
 **/
@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        final InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryAuthentication = auth.inMemoryAuthentication();
        List<UserEntity> users = userService.getUsers();;
        for(UserEntity u: users){
            inMemoryAuthentication.withUser(u.getName())
                    .password(u.getPassword())
                    .authorities("ROLE_USER");
        }

        inMemoryAuthentication.withUser("a")
                .password("a")
                .authorities("ROLE_USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/*").access("hasRole('ROLE_USER')")
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login?logout");

    }

}