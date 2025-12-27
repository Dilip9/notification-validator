package gouri.notification.notification_validator.consumer;

import gouri.notification.notification_validator.dto.NotificationRequest;
import gouri.notification.notification_validator.service.PriorityResolver;
import gouri.notification.notification_validator.service.ValidationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ValidationConsumer {

    private final ValidationService validationService;
    private final PriorityResolver priorityResolver;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ValidationConsumer(ValidationService validationService, PriorityResolver priorityResolver, KafkaTemplate<String, Object> kafkaTemplate) {
        this.validationService = validationService;
        this.priorityResolver = priorityResolver;
        this.kafkaTemplate = kafkaTemplate;
    }


    @KafkaListener(topics = "notification_raw_topic")
    public void consume(NotificationRequest request){
        validationService.validateNotificationData(request);
        String priorityTopic = priorityResolver.resolver(request.getMessageType());
        kafkaTemplate.send(priorityTopic, request);
    }

}
