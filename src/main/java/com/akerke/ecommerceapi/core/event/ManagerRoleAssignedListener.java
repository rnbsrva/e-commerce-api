package com.akerke.ecommerceapi.core.event;

import com.akerke.ecommerceapi.model.User;
import com.akerke.ecommerceapi.service.EmailService;
import com.akerke.ecommerceapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class ManagerRoleAssignedListener {

    private final EmailService emailService;
    private final UserService userService;

    @EventListener
    public void onSellerRoleAssigned(ManagerRoleAssignedEvent event) {
        User user = userService.findById(event.getUserId());
        Map<String, Object> model = Map.of("name", user.getName());
        emailService.sendEmail(user.getEmail(), "Manager role assigned", "manager-role-assigned.ftl", model);
    }
}
