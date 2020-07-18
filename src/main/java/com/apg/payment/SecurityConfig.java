package com.apg.payment;

import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hitesh This class is created for adding custom security configuration
 * for APG Payment application.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String CLIENT_PROPERTY_KEY
            = "spring.security.oauth2.client.registration.";

    /*
    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {

        return new InMemoryOAuth2AuthorizedClientService(
                clientRegistrationRepository());
    }*/

    public class RefererRedirectionAuthenticationSuccessHandler
            extends SimpleUrlAuthenticationSuccessHandler
            implements AuthenticationSuccessHandler {

        public RefererRedirectionAuthenticationSuccessHandler() {
            super();
            setUseReferer(true);
        }

    }
    /**
     * spring authorization
     * https://www.youtube.com/watch?v=payxWrmF_0k
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/view").hasRole("USER")
                .antMatchers("/view1").authenticated()
                .and()
                .oauth2Login();
                //.successHandler(new RefererRedirectionAuthenticationSuccessHandler());
                //.clientRegistrationRepository(clientRegistrationRepository())
                //.authorizedClientService(authorizedClientService());;
        /**     http.authorizeRequests()
                .antMatchers("static/css","static/js").permitAll()
                .antMatchers("/view").hasRole("USER")
        //      .antMatchers("/view1").permitAll()
                .antMatchers("/").permitAll()
                .and().oauth2Login(); ** /
        //http.headers().frameOptions().sameOrigin();
        //http.headers().httpStrictTransportSecurity();
        http.csrf().disable();
        //http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin();
        // http.authorizeRequests().antMatchers("/**").permitAll();
        //http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin()
        //      .and().antMatcher("/");
        // http.oauth2Login().authorizationEndpoint().authorizationRequestResolver()
    }

    /**
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
    {
        authenticationManagerBuilder.inMemoryAuthentication().withUser(
                User
                        .withUsername("rahul")
                        .password("rahul")
                        .roles("USER"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    */
    }

    /*
    private static List<String> clients = Arrays.asList("google", "facebook");

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = clients.stream()
                .map(c -> getRegistration(c))
                .filter(registration -> registration != null)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }



    private ClientRegistration getRegistration(String client) {
        String clientId = "673024212943-87tnnljf5t0dg04ab6kugi84efbmukpa.apps.googleusercontent.com";

        if (clientId == null) {
            return null;
        }

        String clientSecret = "DEnfn75v8SRtvyyczapEB1cf";

        if (client.equals("google")) {
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(clientId).clientSecret(clientSecret).build();
        }
        if (client.equals("facebook")) {
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(clientId).clientSecret(clientSecret).build();
        }
        return null;
    }
    */
}
