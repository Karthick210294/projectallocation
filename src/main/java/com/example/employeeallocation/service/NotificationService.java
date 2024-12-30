package com.example.employeeallocation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Slf4j
public class NotificationService {
    private static Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final KafkaTemplate<Object, String> kafkaTemplate;
    private final String TOPIC = "employee-notifications";

    @Autowired
    public NotificationService(KafkaTemplate<Object, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProjectAllocationNotification(String employeeId, String projectName) {
//        NotificationEvent event = NotificationEvent.builder()
//                .employeeId(employeeId)
//                .type("PROJECT_ALLOCATION")
//                .timestamp(LocalDateTime.now())
//                .additionalDetails(Map.of(
//                        "projectName", projectName,
//                        "allocationTime", LocalDateTime.now().toString()
//                ))
//                .build();
//
//        CompletableFuture<SendResult<String, NotificationEvent>> future =
//                kafkaTemplate.send(TOPIC, employeeId, event);
//
//        future.whenComplete((result, ex) -> {
//            if (ex == null) {
//                logger.info("Notification sent successfully for employee: {}", employeeId);
//            } else {
//                logger.error("Failed to send notification for employee: {}", employeeId, ex);
//            }
//        });
        try {
            String message = String.format("Employee %s allocated to project %s", employeeId, projectName);
            kafkaTemplate.send(TOPIC, message);
            logger.info("Notification sent successfully for employee: {}", employeeId);
        } catch (Exception e) {
            logger.error("Failed to send notification for employee: {}", employeeId, e);
        }
    }
}