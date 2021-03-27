package com.ssb.onlinelibrary.config;

import com.ssb.onlinelibrary.common.Constants;
import com.ssb.onlinelibrary.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
@EnableWebSecurity
@ComponentScan("com.ssb.onlinelibrary")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Order(0)
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("........10...HttpSecurity....");
        http.authorizeRequests()
                .antMatchers( "/api/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/app/**").permitAll()
                .and().headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable();
    }
}

