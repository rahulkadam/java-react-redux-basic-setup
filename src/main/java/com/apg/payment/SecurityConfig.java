package com.apg.payment;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Hitesh This class is created for adding custom security configuration
 * for APG Payment application.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * spring authorization
     * https://www.youtube.com/watch?v=payxWrmF_0k
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/view").hasRole("USER")
                .antMatchers("/view1").authenticated()
                .and()
                .oauth2Login();
    }
}
