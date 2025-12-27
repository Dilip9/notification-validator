package gouri.notification.notification_validator.service;

import gouri.notification.notification_validator.dto.NotificationRequest;
import gouri.notification.notification_validator.enums.ChannelType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidationService {

    public void validateNotificationData(NotificationRequest request) {
        if (!StringUtils.hasText(request.getContent())) {
            throw new IllegalArgumentException("Content cannot be empty");
        }

        if (request.getChannelType() != null && request.getRecipient() == null) {
            throw new IllegalArgumentException("Recipient is required when channel is specified");
        }

        if (request.getChannelType() == ChannelType.EMAIL &&
                !request.getRecipient().contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }
}
