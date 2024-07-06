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
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final Configuration freemarkerConfig;

    private void sendEmailMessage(String to, String subject, String body) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(mimeMessage);
            log.info("%s email sent to %s".formatted(subject, to));
        } catch (MessagingException e) {
            log.error("Failed to send email to %s".formatted(to), e);
            throw new MailSendException("Failed to send email", e);
        }
    }

    private String processTemplate(String templateName, Map<String, Object> model) {
        try {
            Template template = freemarkerConfig.getTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (IOException | TemplateException e) {
            log.error("Failed to process email template %s".formatted(templateName), e);
            throw new MailSendException("Failed to process email template", e);
        }
    }

    @Override
    public void sendEmail(String to, String subject, String templateName, Map<String, Object> model) {
        String emailContent = processTemplate(templateName, model);
        sendEmailMessage(to, subject, emailContent);
    }
}