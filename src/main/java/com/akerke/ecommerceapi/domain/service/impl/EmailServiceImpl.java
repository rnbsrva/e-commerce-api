package com.akerke.ecommerceapi.domain.service.impl;

import com.akerke.ecommerceapi.domain.service.EmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final Configuration freemarkerConfig;

    private void sendEmail(String to, String subject, String body) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MailSendException("Failed to send email", e);
        }
    }

    @Override
    public void sendConfirmationEmail(String to, String confirmationLink, String name) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", name);
        model.put("confirmationLink", confirmationLink);

        String emailContent;
        try {
            Template template = freemarkerConfig.getTemplate("confirmation-mail.ftl");
            emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (IOException | TemplateException e) {
            throw new MailSendException("Failed to process email template", e);
        }

        sendEmail(to, "Email Confirmation", emailContent);
        log.info("Confirmation email sent.");
    }
}