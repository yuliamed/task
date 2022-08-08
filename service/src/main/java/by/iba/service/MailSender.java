package by.iba.service;

import by.iba.exception.ServiceException;
import org.springframework.mail.SimpleMailMessage;

public interface MailSender {
    void send(SimpleMailMessage message) throws ServiceException;
}
