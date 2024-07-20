package com.akerke.ecommerceapi.service.impl;

import com.akerke.ecommerceapi.common.dto.CreateShopRequest;
import com.akerke.ecommerceapi.common.dto.ShopRequestDto;
import com.akerke.ecommerceapi.common.enums.RequestStatus;
import com.akerke.ecommerceapi.core.mapper.ShopMapper;
import com.akerke.ecommerceapi.repository.ShopRequestRepository;
import com.akerke.ecommerceapi.security.EcommerceUserDetails;
import com.akerke.ecommerceapi.service.EmailService;
import com.akerke.ecommerceapi.service.ShopRequestService;
import com.akerke.ecommerceapi.service.ShopService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopRequestServiceImpl implements ShopRequestService {

    private final ShopRequestRepository shopRequestRepository;
    private final ShopMapper shopMapper;
    private final ShopService shopService;
    private final EmailService emailService;

    @Override
    public void save(CreateShopRequest createShopRequest, Authentication authentication) {
        var shopRequest = shopMapper.toShopRequest(createShopRequest);
        var user = (EcommerceUserDetails) authentication.getPrincipal();
        shopRequest.setUser(user.user());

        shopRequestRepository.save(shopRequest);

        log.info("Saving shop request: {}", createShopRequest);
    }

    @Override
    public List<ShopRequestDto> getAll(Pageable pageable) {
        return shopRequestRepository.findAll(pageable).stream()
                .map(shopMapper::toShopRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public void handlePendingShopRequest(Long id,
                                         RequestStatus status,
                                         @Nullable String reason,
                                         Authentication authentication) {
        var shopRequest = shopRequestRepository.findByID(id);

        var reviewer = (EcommerceUserDetails) authentication.getPrincipal();
        shopRequest.setReviewedBy(reviewer.user());
        shopRequest.setReviewedAt(LocalDateTime.now());
        shopRequest.setRequestStatus(status);
        shopRequest.setRejectedReason(reason);

        shopRequestRepository.save(shopRequest);

        switch (status) {
            case APPROVED -> {
                shopService.save(shopRequest);
                emailService.sendEmail(shopRequest.getUser().getEmail(),
                        "Shop request approved",
                        "shop-request-approved.ftl",
                        Map.of("shopRequest", shopRequest));
                log.info("Shop request approved: {}", shopRequest);
            }
            case DENIED -> {
                if (reason == null || reason.isBlank()) {
                    throw new IllegalArgumentException("Reason is required for denied shop request");
                }
                emailService.sendEmail(shopRequest.getUser().getEmail(),
                        "Shop request rejected",
                        "shop-request-rejected.ftl",
                        Map.of("shopRequest", shopRequest));
                log.info("Shop request rejected: {}", shopRequest);
            }
            default -> throw new IllegalArgumentException("Unknown request status: " + status);
        }
    }

}
