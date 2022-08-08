package by.iba.service;

import org.springframework.mail.SimpleMailMessage;

public interface MailTrueService {
    public void sendSimpleMailMessage(SimpleMailMessage message) ;

    SimpleMailMessage getSimpleMailMessage(String emailTo, String subject, String message);
}
