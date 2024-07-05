package com.akerke.ecommerceapi.domain.model;

import com.akerke.ecommerceapi.common.enums.ConfirmationTokenType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ConfirmationToken extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ConfirmationTokenType confirmationTokenType;

    private String token;

    private LocalDateTime expirationDate;

    @OneToOne
    private User user;
}
