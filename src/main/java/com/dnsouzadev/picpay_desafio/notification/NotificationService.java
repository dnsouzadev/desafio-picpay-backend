package com.dnsouzadev.picpay_desafio.notification;

import org.springframework.stereotype.Service;

import com.dnsouzadev.picpay_desafio.transaction.Transaction;

@Service
public class NotificationService {
    private final NotificationProducer notificationProducer;

    public NotificationService(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    public void notify(Transaction transaction) {
        notificationProducer.sendNotification(transaction);

    }
}
