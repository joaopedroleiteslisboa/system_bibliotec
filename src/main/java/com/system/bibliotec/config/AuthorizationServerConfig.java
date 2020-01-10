package com.system.bibliotec.config;

import java.security.KeyPair;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.ApiPropertyConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;


@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(ApiPropertyConfig.class)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	 @Autowired
	 private DataSource dataSource;
	 
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 private AuthenticationManager authenticationManager;
	 
	 @Autowired
	 private ApiPropertyConfig ApiPropertyConfig;
	 
	 @Autowired
	 private UserDetailsService userDetailsService;
	 
	 @Autowired
	 private JwtAccessTokenConverter jwtAccessTokenConverter;
	 
	 @Autowired
	 private TokenStore tokenStore;
	
	  @Bean
	    public TokenStore tokenStore() {
	        if (tokenStore == null) {
	            tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
	        }
	        return tokenStore;
	    }
	
	  @Bean
	    public DefaultTokenServices tokenServices(final TokenStore tokenStore,
	                                              final ClientDetailsService clientDetailsService) {
	        DefaultTokenServices tokenServices = new DefaultTokenServices();
	        tokenServices.setSupportRefreshToken(true);
	        tokenServices.setTokenStore(tokenStore);
	        tokenServices.setClientDetailsService(clientDetailsService);
	        tokenServices.setAuthenticationManager(this.authenticationManager);
	        return tokenServices;
	    }
	  
	  @Bean
	    public JwtAccessTokenConverter jwtAccessTokenConverter() {
	        if (jwtAccessTokenConverter != null) {
	            return jwtAccessTokenConverter;
	        }

	        ApiPropertyConfig.JwtProperties jwtProperties = ApiPropertyConfig.getJwt();
	        KeyPair keyPair = keyPair(jwtProperties, keyStoreKeyFactory(jwtProperties));

	        jwtAccessTokenConverter = new JwtAccessTokenConverter();
	        jwtAccessTokenConverter.setKeyPair(keyPair);
	        return jwtAccessTokenConverter;
	    }

	    @Override
	    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
	        clients.jdbc(this.dataSource);
	    }

	    @Override
	    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
	        endpoints.authenticationManager(this.authenticationManager)
	                .accessTokenConverter(jwtAccessTokenConverter())
	                .userDetailsService(this.userDetailsService)
	                .tokenStore(tokenStore());
	    }

	    @Override
	    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
	        oauthServer.passwordEncoder(this.passwordEncoder).tokenKeyAccess("permitAll()")
	                .checkTokenAccess("isAuthenticated()");
	    }

	    private KeyPair keyPair(ApiPropertyConfig.JwtProperties jwtProperties, KeyStoreKeyFactory keyStoreKeyFactory) {
	        return keyStoreKeyFactory.getKeyPair(jwtProperties.getKeyPairAlias(), jwtProperties.getKeyPairPassword().toCharArray());
	    }

	    private KeyStoreKeyFactory keyStoreKeyFactory(ApiPropertyConfig.JwtProperties jwtProperties) {
	        return new KeyStoreKeyFactory(jwtProperties.getKeyStore(), jwtProperties.getKeyStorePassword().toCharArray());
	    }

}
