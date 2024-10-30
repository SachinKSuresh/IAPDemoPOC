package com.IAPDemoPOC.Subscription.Webhook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IAPDemoPOC.Subscription.Webhook.handler.WebhookHandlerFactory;
import com.IAPDemoPOC.Subscription.Webhook.handler.WebhookNotificationHandler;

@RestController
@RequestMapping("/webhook")
public class WebhookController {
private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);
    
    private final WebhookHandlerFactory handlerFactory;

    @Autowired
    public WebhookController(WebhookHandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    @PostMapping("/notification")
    public ResponseEntity<String> handleWebhook(@RequestBody WebhookNotification notification) {
        try {
            logger.info("Received webhook notification: {}", notification.getId());
            
            
            if (notification.getType() == null) {
                return ResponseEntity.badRequest().body("Notification type is required");
            }

            
            WebhookNotificationHandler handler = handlerFactory.getHandler(notification.getType());
            handler.handle(notification);

            return ResponseEntity.ok("Notification processed successfully");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid notification type", e);
            return ResponseEntity.badRequest().body("Invalid notification type");
        } catch (Exception e) {
            logger.error("Error processing webhook notification", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error processing notification");
        }
    }

}
