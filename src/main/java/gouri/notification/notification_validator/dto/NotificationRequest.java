package gouri.notification.notification_validator.dto;


import gouri.notification.notification_validator.enums.ChannelType;
import gouri.notification.notification_validator.enums.MessageType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationRequest {
    private String clientId;
    private String userId;
    private ChannelType channelType;
    private String recipient;

    @NotBlank
    private String content;
    private MessageType messageType;
}
