package com.dnsouzadev.picpay_desafio.notification;

import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.dnsouzadev.picpay_desafio.transaction.Transaction;

@Service
public class NotificationConsumer {
    private RestClient restClient;

    public NotificationConsumer(RestClient.Builder builder) {
        this.restClient = builder
        .baseUrl("https://util.devi.tools/api/v1/notify")
        .build();
    }

    @KafkaListener(topics = "transaction-notification", groupId = "picpay_desafio")
    public void receiveNotification(Transaction transaction) {
        var response = restClient.post()
            .body(transaction)
            .retrieve()
            .toEntity(Notification.class);

            if(response.getStatusCode() != HttpStatus.NO_CONTENT) {
                throw new NotificationException("Notification not sent");
            }

    }
}
