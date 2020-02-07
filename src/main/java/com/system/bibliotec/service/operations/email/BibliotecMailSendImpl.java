package com.system.bibliotec.service.operations.email;

import javax.mail.internet.MimeMessage;

import com.system.bibliotec.service.operations.IBiblotecMailSend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * BibliotecMailSendImpl
 */
@Component
public class BibliotecMailSendImpl implements IBiblotecMailSend{


    private final Logger log = LoggerFactory.getLogger(BibliotecMailSendImpl.class);
	
    private static final String USER = "user";

       private static final String BASE_URL = "baseUrl";

       
       @Autowired
       private JavaMailSender javaMailSender;
       
       @Autowired
       private MessageSource messageSource;

       private final SpringTemplateEngine templateEngine;


       @Async
       @Override
       public boolean sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        boolean enviado = false;   
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
               isMultipart, isHtml, to, subject, content);

           // Prepare message using a Spring helper
           MimeMessage mimeMessage = javaMailSender.createMimeMessage();
           try {
               MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
               message.setTo(to);
               message.setFrom(ApiPropertyConfig.getMail().getFrom());
               message.setSubject(subject);
               message.setText(content, isHtml);
               javaMailSender.send(mimeMessage);
               enviado = true;
               log.debug("Sent email to Usuario '{}'", to);
               
           }  catch (MailException | MessagingException e) {
               log.warn("Email could not be sent to user '{}'", to, e);
               enviado = false;
           }

           return enviado;
       }

       @Async
       public void sendEmailFromTemplate(Usuario user, String templateName, String titleKey) {
           if (user.getEmail() == null) {
               log.debug("Email doesn't exist for user '{}'", user.getLogin());
               throw new EmailInvalidoExcepti
               return;
           }
           Locale locale = Locale.forLanguageTag(user.getLangKey());
           Context context = new Context(locale);
           context.setVariable(USER, user);
           context.setVariable(BASE_URL, ApiPropertyConfig.getMail().getBaseUrl());
           String content = templateEngine.process(templateName, context);
           String subject = messageSource.getMessage(titleKey, null, locale);
           sendEmail(user.getEmail(), subject, content, false, true);
       }

       @Async
       public void sendActivationEmail(Usuario user) {
           log.debug("Sending activation email to '{}'", user.getEmail());
           sendEmailFromTemplate(user, "mail/activationEmail", "email.activation.title");
       }

       @Async
       public void sendCreationEmail(Usuario user) {
           log.debug("Sending creation email to '{}'", user.getEmail());
           sendEmailFromTemplate(user, "mail/creationEmail", "email.activation.title");
       }

       @Async
       public void sendPasswordResetMail(Usuario user) {
           log.debug("Sending password reset email to '{}'", user.getEmail());
           sendEmailFromTemplate(user, "mail/passwordResetEmail", "email.reset.title");
       }



    

    
}