package com.akerke.ecommerceapi.core.schedule;

import com.akerke.ecommerceapi.repository.ConfirmationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
@Slf4j
public class TokenPurgeTask {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Scheduled(cron = "${spring.scheduler.token-purge.cron}")
    public void purgeExpired() {
        log.info("TokenPurgeTask scheduled.");
        LocalDateTime now = LocalDateTime.from(Instant.now());
        confirmationTokenRepository.deleteAllByExpirationDateSince(now);
    }

}
