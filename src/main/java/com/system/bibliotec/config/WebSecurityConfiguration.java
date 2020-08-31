package com.system.bibliotec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import com.system.bibliotec.security.AppUserDetailsService;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceBean())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = DefaultPasswordEncoderFactories.createDelegatingPasswordEncoder();
        }
        return passwordEncoder;

    }
    //   @Bean
    //  public UserDetailsService userDetailsService() {
    //     if (userDetailsService == null) {
    //         userDetailsService = new JdbcDaoImpl();
    ////        ((JdbcDaoImpl) userDetailsService).setDataSource(dataSource);
    //   }
    //    return userDetailsService;
    //  }


    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new AppUserDetailsService();
    }



}
