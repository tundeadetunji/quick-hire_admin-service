package io.github.tundeadetunji.admin_service.messaging;

import io.github.tundeadetunji.admin_service.shared.NotificationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationListener {

    //this is only for local dev/testing

    /*@RabbitListener(queues = "#{adminQueue.name}")
    public void receive(String notificationMessage) {
        //in production, send to admin/stakeholders' email

        try {
            NotificationMessage message = NotificationMessage.fromJson(notificationMessage);
            log.info("Notification received in admin-service");
            log.info("Subject: {}", message.getSubject());
            log.info("Body: {}", message.getBody());
        } catch (Exception e) {
            log.error("Failed to parse notification message: {}", e.getMessage());
        }
    }*/
}
