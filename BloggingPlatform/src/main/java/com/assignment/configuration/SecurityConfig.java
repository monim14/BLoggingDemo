package com.assignment.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //Disabled for now ,by default spring web security enables csrf protection
    //For demo purpose we can disable for now

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }

    // Hardcoding for now
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("abc")
                .password("aaa")
                .roles("admin_role");
    }
}
