package com.system.bibliotec.service;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.system.bibliotec.model.Usuario;

/**
 * BibliotecMailSendImpl
 */
@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    private static final String USER = "usuario";

    private static final String BASE_URL = "baseUrl";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private Environment env;

    @Autowired
    private ServicoDoSistema servicoSistema;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml, Usuario u) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}", isMultipart,
                isHtml, to, subject, content);


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(env.getProperty("spring.mail.username"));
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Email encaminhado para usuario '{}'", to);
        } catch (MailException | MessagingException e) {
            log.warn("Não foi possivel encaminhar o email para usuario{}'", to, e);

            servicoSistema.saveUserContextErrorLoginAndId(
                    e.getCause(), Thread.currentThread().getStackTrace()[1].getMethodName(), getCurrentAuditorUser(), u.getId().toString());

        }
    }

    @Async
    public void sendEmailFromTemplate(Usuario usuario, String templateName, String titleKey) {
        if (usuario.getEmail() != null) {

            Locale locale = Locale.forLanguageTag(usuario.getLangKey());
            Context context = new Context(locale);
            context.setVariable(USER, usuario);
            context.setVariable(BASE_URL, env.getProperty("spring.mail.username"));
            String content = templateEngine.process(templateName, context);
            String subject = messageSource.getMessage(titleKey, null, locale);
            sendEmail(usuario.getEmail(), subject, content, false, true, usuario);

        } else {
            log.debug("Email doesn't exist for usuario '{}'", usuario.getEmail());
        }


    }

    @Async
    public void sendEmailFromTemplate(Usuario usuario, String templateName, String titleKey, String urlEmailAcao) {
        if (usuario.getEmail() != null) {

            Locale locale = Locale.forLanguageTag(usuario.getLangKey());
            Context context = new Context(locale);
            context.setVariable(USER, usuario);
            context.setVariable(BASE_URL, urlEmailAcao);
            String content = templateEngine.process(templateName, context);
            String subject = messageSource.getMessage(titleKey, null, locale);
            sendEmail(usuario.getEmail(), subject, content, false, true, usuario);

        } else {
            log.debug("Email do usuario não foi informado para disparo de mensagens: ");
        }


    }

    @Async
    public void sendActivationEmail(Usuario usuario, String endPointBase) {
        log.debug("Encaminhando Email de Ativação de Conta do Usuario: ", usuario.getEmail());
        sendEmailFromTemplate(usuario, "mail/activationEmail", "email.activation.title", endPointBase);
    }

    @Async
    public void sendCreationEmail(Usuario usuario) {
        log.debug("Encaminhando Email de criação de Conta: ", usuario.getEmail());
        sendEmailFromTemplate(usuario, "mail/creationEmail", "email.activation.title");
    }

    @Async
    public void sendPasswordResetMail(Usuario usuario) {
        log.debug("Encaminhando Email de recuperação de senha: ", usuario.getEmail());
        sendEmailFromTemplate(usuario, "mail/passwordResetEmail", "email.reset.title");
    }

    @Async
    public void sendPenultimoDiaOperacao(Usuario usuario) {
        log.debug("Encaminhando Email de recuperação de senha: ", usuario.getEmail());
        sendEmailFromTemplate(usuario, "mail/penultimoDiaOperacao", "email.reset.title");
    }

    @Async
    public void sendUltimoDiaOperacao(Usuario usuario) {
        log.debug("Encaminhando Email de recuperação de senha: ", usuario.getEmail());
        sendEmailFromTemplate(usuario, "mail/ultimoDiaOperacao", "email.reset.title");
    }

    @Async
    public void sendDataUltrapassadaOperacao(Usuario usuario) {
        log.debug("Encaminhando Email de recuperação de senha: ", usuario.getEmail());
        sendEmailFromTemplate(usuario, "mail/dataUltrapassadaOperacao", "email.reset.title");
    }


    private String getCurrentAuditorUser() {
        return Optional.of(SecurityUtils.getCurrentUserNameId().orElse(ConstantsUtils.ANONYMOUS_USER)).get();
    }


}