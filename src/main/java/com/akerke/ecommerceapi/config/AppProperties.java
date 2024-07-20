package com.akerke.ecommerceapi.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String domain;

    @Valid
    private MailProperties mailProperties;

    @Data
    public static class MailProperties {
        @NotBlank
        private String emailConfirmationLinkPattern;
        @NotBlank
        private String resetPasswordLinkPattern;
    }

}
