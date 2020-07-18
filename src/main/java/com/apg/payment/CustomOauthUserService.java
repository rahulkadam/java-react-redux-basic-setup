package com.apg.payment;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOauthUserService extends OidcUserService {

    @Override
    public OidcUser loadUser(OidcUserRequest oidcUserRequest) {
        OidcUser oidcUser = super.loadUser(oidcUserRequest);
        System.out.println(oidcUser);
        System.out.println(" Oidc User : "+ oidcUser.getEmail());
        return oidcUser;
    }
}
