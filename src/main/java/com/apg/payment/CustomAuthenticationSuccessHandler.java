package com.apg.payment;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private String homeUrl = "http://localhost:8080/view";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        System.out.println("onAuthenticationSuccess : ");

        if (response.isCommitted()) {
            return;
        }

        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();

        System.out.println("OIDC user from success : "+ oidcUser);
        String redirectionUrl = UriComponentsBuilder.fromUriString(homeUrl)
                .queryParam("auth_token", oidcUser.getIdToken())
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, redirectionUrl);
    }
}
