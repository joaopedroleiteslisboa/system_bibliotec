package com.system.bibliotec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.system.bibliotec.config.SpringSecurityAuditorAware;


import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;


@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class SystemBibliotecApplication {


    public static void main(String[] args) {
        SpringApplication.run(SystemBibliotecApplication.class, args);
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditorAware();
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }


}
