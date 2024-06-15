package com.fyodork.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource);


        /* UserBuilder userBuilder=User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("ivan")
                        .password("1")
                        .roles("Employee"))
                .withUser(userBuilder.username("elena")
                        .password("2")
                        .roles("HR"))
                .withUser(userBuilder.username("kfy")
                        .password("3")
                        .roles("Manager","HR"));*/




    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("Employee","HR","Manager")
                .antMatchers("/hr_info").hasRole("HR")
                .antMatchers("/manager_info").hasRole("Manager")
                .and().formLogin().permitAll();
    }
}
/*
@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("ivan")
                .password(passwordEncoder.encode("1"))
                .roles("EMPLOYEE")
                .build());
        manager.createUser(User.withUsername("elena")
                .password(passwordEncoder.encode("2"))
                .roles("HR")
                .build());
        manager.createUser(User.withUsername("best")
                .password(passwordEncoder.encode("3"))
                .roles("MANAGER", "HR")
                .build());
        return manager;
    }}*/
