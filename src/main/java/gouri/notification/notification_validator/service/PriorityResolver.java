package gouri.notification.notification_validator.service;

import gouri.notification.notification_validator.enums.MessageType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PriorityResolver {

    public String resolver(MessageType messageType) {
       return switch(messageType){
        case OTP -> "notification_priority_high";
        case TRANSACTIONAL -> "notification_priority_medium";
        case PROMOTIONAL -> "notification_priority_low";
       };
    }
}
