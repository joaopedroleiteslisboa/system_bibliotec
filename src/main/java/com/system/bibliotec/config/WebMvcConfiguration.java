package com.system.bibliotec.config;

import com.system.bibliotec.service.CustomAuditEventRepositoryService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(customAuditEventRepositoryService());
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }


    @Bean
    public CustomAuditEventRepositoryService customAuditEventRepositoryService() {
        return new CustomAuditEventRepositoryService();
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver s = new SessionLocaleResolver();
        s.setDefaultLocale(new Locale("pt", "BR"));
        return s;
    }

    @Override
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean l = new LocalValidatorFactoryBean();
        l.setValidationMessageSource(messageSource());
        return l;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages", "classpath:messages_pt_BR", "classpath:ValidationMessages");
        //SET ResourceLoader - DEFAULT...
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }


}
