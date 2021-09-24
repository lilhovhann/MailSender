/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.project.app.mailsender.services;

import io.project.app.mailsender.domain.EmailData;
import io.project.app.mailsender.repositories.EmailDataRepository;

import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Locale;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

import io.project.app.mailsender.dto.EmailDataDTO;

/**
 *
 * @author root
 */
@Service
@Slf4j
public class MailSenderService {

    private static TemplateEngine templateEngine;
    private static Context context;

    private JavaMailSender emailSender;

    @Autowired
    private EmailDataRepository emailDataRepository;

    private EmailData emailData;

    @Autowired
    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public Optional<EmailData> contactHicare(EmailDataDTO emailDataDTO) {
        emailData = new EmailData();
        try {
            String htmlTemplate = "templates/contactEmailTemplate";

            this.initializeTemplateEngine();
            context.setVariable("sender", "noreply@lilith.com");
            context.setVariable("content", "My name is " + emailDataDTO.getName());
            context.setVariable("mailTo", "lilith.hovhann@gmail.com");
            String mailBody = templateEngine.process(htmlTemplate, context);

            emailData.setEmail(emailDataDTO.getEmail());
            emailData.setName(emailDataDTO.getName());
            //write info@hic.am bellow
            sendEmailtoPatient("lilith.hovhann@gmail.com", "Question from HiCare user", mailBody);
            emailDataRepository.save(emailData);
            return Optional.of(emailData);
        } catch (MessagingException ex) {
            log.error("ERROR " + ex.getMessage());
            return Optional.empty();
        }
    }

    private void sendEmailtoPatient(String mailTo, String subject, String mailBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        helper.setTo(mailTo);
        helper.setSubject(subject);
        helper.setText(mailBody, true);
        emailSender.send(message);
        log.info("Email sent to " + mailTo);
    }

    private void initializeTemplateEngine() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setTemplateMode("HTML");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        context = new Context(Locale.US);
    }

}
