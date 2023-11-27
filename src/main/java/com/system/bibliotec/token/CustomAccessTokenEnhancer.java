package com.system.bibliotec.token;

import com.system.bibliotec.security.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomAccessTokenEnhancer implements TokenEnhancer {

    @Autowired
    private AppUserDetailsService userDetailsService;


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Authentication userAuthentication = authentication.getUserAuthentication();
        if (userAuthentication != null) {
            Object principal = authentication.getUserAuthentication().getPrincipal();

            if (principal instanceof String) {

                Map<String, Object> additionalInfo = new HashMap<>();

                additionalInfo.put("email", principal);

                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            }
        }

        return accessToken;
    }
}