package com.system.bibliotec.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.system.bibliotec.security.SecurityUtils;


@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getCurrentUserNameId()
                .orElse(ConstantsUtils.SYSTEM_ACCOUNT));
    }
}