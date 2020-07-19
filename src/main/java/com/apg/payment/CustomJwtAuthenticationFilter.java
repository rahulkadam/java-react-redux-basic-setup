package com.apg.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    CustomOauthUserService customOauthUserService;

    @Autowired
    InMemoryOAuth2AuthorizedClientService inMemoryOAuth2AuthorizedClientService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        String queryString = req.getQueryString();

        String tokenParam = req.getParameter("auth_token");

        System.out.println("Query String " + tokenParam);

        if (!StringUtils.isEmpty(tokenParam)) {
            OAuth2AuthorizedClient authentication1 = inMemoryOAuth2AuthorizedClientService.loadAuthorizedClient("google", tokenParam);
            System.out.println("FOund authentication : " + authentication1.getPrincipalName());

            Map<String, Object> additionalParameters = new HashMap<>();
            //OidcUserRequest oidOAuth2UserRequest = new OidcUserRequest(
              //      authentication1.getClientRegistration(), authentication1.getAccessToken(),null, additionalParameters);
            //customOauthUserService.loadUser(oidOAuth2UserRequest);
        }

        OAuth2User user = customOauthUserService.findUserByToken(tokenParam);
        if (user != null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
                    null, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        System.out.println("Custom CustomJwtAuthenticationFilter");
        chain.doFilter(req, res);
    }
}
