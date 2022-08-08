package by.iba.service;

public interface NotificationService {
    void sendNotificationMessage(String emailTo, String subject, String message);
}
