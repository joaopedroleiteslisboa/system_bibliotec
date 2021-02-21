package com.system.bibliotec.token;

import com.system.bibliotec.security.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {

    @Autowired
    private AppUserDetailsService userDetailsService;

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication authentication = super.extractAuthentication(map);
        Authentication userAuthentication = authentication.getUserAuthentication();

        if (userAuthentication != null) {
            String email = (String) authentication.getPrincipal();
            if (email != null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername( email);

                Collection<? extends GrantedAuthority> authorities = userAuthentication.getAuthorities();

                userAuthentication = new UsernamePasswordAuthenticationToken(userDetails,
                        userAuthentication.getCredentials(), authorities);
            }
        }
        return new OAuth2Authentication(authentication.getOAuth2Request(), userAuthentication);
    }
}
