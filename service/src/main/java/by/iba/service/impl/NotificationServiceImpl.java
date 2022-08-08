package by.iba.service.impl;

import by.iba.service.MailTrueService;
import by.iba.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final MailTrueService mailTrueService;


    @Override
    public void sendNotificationMessage(String emailTo, String subject, String message) {
        final SimpleMailMessage simpleMailMessage = mailTrueService.getSimpleMailMessage(emailTo, subject, message);
        mailTrueService.sendSimpleMailMessage(simpleMailMessage);
    }
}
