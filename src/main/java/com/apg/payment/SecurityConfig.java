package com.apg.payment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Hitesh This class is created for adding custom security configuration
 * for APG Payment application.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomOauthUserService customOauthUserService;

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
                .oauth2Login()
                .successHandler(myAuthenticationSuccessHandler())
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint()
                .oidcUserService(customOauthUserService);
//                .and()
  //              .authorizationEndpoint()
    //            .baseUri("/oauth2/authorize")
      //          .authorizationRequestRepository(customAuthorizationRequestRepository());

        http.addFilterBefore(authenticationTokenFilterBean(), OAuth2AuthorizationRequestRedirectFilter.class);
        http.sessionManagement(cust -> cust.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    }

    @Bean
    public AuthorizationRequestRepository customAuthorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public CustomJwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new CustomJwtAuthenticationFilter();
    }
}
