package com.system.bibliotec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

import com.system.bibliotec.security.AppUserDetailsService;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalSecurityConfig extends GlobalAuthenticationConfigurerAdapter{

	@Autowired
	AppUserDetailsService appUserDetailsService;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		
		auth.userDetailsService(this.appUserDetailsService);
	}
	
	
}
