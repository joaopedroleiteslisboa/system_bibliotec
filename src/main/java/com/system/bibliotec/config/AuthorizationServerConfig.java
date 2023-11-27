//package com.system.bibliotec.config;
//
//import java.security.KeyPair;
//import java.util.Arrays;
//
//import javax.sql.DataSource;
//
//import com.system.bibliotec.token.CustomAccessTokenEnhancer;
//import com.system.bibliotec.token.CustomJwtAccessTokenConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
//
//import com.system.bibliotec.security.AppUserDetailsService;
//
//
//@Configuration
//@EnableAuthorizationServer
//@EnableConfigurationProperties(ApiSecurityAuthorizationServerProperties.class)
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    private final DataSource dataSource;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final ApiSecurityAuthorizationServerProperties securityProperties;
//    private final AppUserDetailsService userDetailsService;
//
//    private final CustomJwtAccessTokenConverter jwtAccessTokenConverter;
//
//    private final TokenStore tokenStore;
//
//    @Autowired
//    public AuthorizationServerConfig(DataSource dataSource, PasswordEncoder passwordEncoder,
//                                     AuthenticationManager authenticationManager, ApiSecurityAuthorizationServerProperties securityProperties,
//                                     AppUserDetailsService userDetailsService,
//                                     CustomJwtAccessTokenConverter jwtAccessTokenConverter,
//                                     TokenStore tokenStore) {
//        this.dataSource = dataSource;
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//        this.securityProperties = securityProperties;
//        this.userDetailsService = userDetailsService;
//        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
//        this.tokenStore = tokenStore;
//    }
//
//    @Bean
//    public TokenEnhancer tokenEnhancer() {
//        return new CustomAccessTokenEnhancer();
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//    @Bean
//    public DefaultTokenServices tokenServices(TokenStore tokenStore,
//                                              ClientDetailsService clientDetailsService) {
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setTokenStore(tokenStore);
//        tokenServices.setClientDetailsService(clientDetailsService);
//        tokenServices.setAuthenticationManager(this.authenticationManager);
//        return tokenServices;
//    }
//
////	@Bean
////	public JwtAccessTokenConverter jwtAccessTokenConverter() {
////		if (jwtAccessTokenConverter != null) {
////			return jwtAccessTokenConverter;
////		}
////
////		ApiSecurityAuthorizationServerProperties.JwtProperties jwtProperties = securityProperties.getJwt();
////		KeyPair keyPair = keyPair(jwtProperties, keyStoreKeyFactory(jwtProperties));
//
////		jwtAccessTokenConverter = new JwtAccessTokenConverter();
////		jwtAccessTokenConverter.setKeyPair(keyPair);
////		return jwtAccessTokenConverter;
////	}
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//
//        if (jwtAccessTokenConverter != null) {
//            return jwtAccessTokenConverter;
//        }
//
//        ApiSecurityAuthorizationServerProperties.JwtProperties jwtProperties = securityProperties.getJwt();
//        KeyPair keyPair = keyPair(jwtProperties, keyStoreKeyFactory(jwtProperties));
//        jwtAccessTokenConverter.setKeyPair(keyPair);
//        return jwtAccessTokenConverter;
//    }
//
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.jdbc(this.dataSource);
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));
//
//        endpoints.authenticationManager(this.authenticationManager)
//                .userDetailsService(this.userDetailsService)
//                //.accessTokenConverter(jwtAccessTokenConverter())
//                .tokenEnhancer(tokenEnhancerChain)
//                .tokenStore(tokenStore());
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
//        oauthServer.passwordEncoder(this.passwordEncoder).tokenKeyAccess("permitAll()")
//                .allowFormAuthenticationForClients()
//                .checkTokenAccess("isAuthenticated()");
//    }
//
//    private KeyPair keyPair(ApiSecurityAuthorizationServerProperties.JwtProperties jwtProperties, KeyStoreKeyFactory keyStoreKeyFactory) {
//        return keyStoreKeyFactory.getKeyPair(jwtProperties.getKeyPairAlias(), jwtProperties.getKeyPairPassword().toCharArray());
//    }
//
//    private KeyStoreKeyFactory keyStoreKeyFactory(ApiSecurityAuthorizationServerProperties.JwtProperties jwtProperties) {
//        return new KeyStoreKeyFactory(jwtProperties.getKeyStore(), jwtProperties.getKeyStorePassword().toCharArray());
//    }
//
//}