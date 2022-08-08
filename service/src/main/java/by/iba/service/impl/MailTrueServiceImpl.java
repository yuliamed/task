package by.iba.service.impl;

import by.iba.exception.ServiceException;
import by.iba.service.MailSender;
import by.iba.service.MailTrueService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MailTrueServiceImpl implements MailTrueService {
    private final MailSender emailSenderService;
    private final String sender = "test_my_sender@mail.ru";

    @Override
    public void sendSimpleMailMessage(SimpleMailMessage message) {
        try {
            this.emailSenderService.send(message);
        } catch (ServiceException e) {
            throw new ServiceException(HttpStatus.CONFLICT.value() + " exception.by.iba.email.sending_exception");
        }
    }

    @Override
    public SimpleMailMessage getSimpleMailMessage(String emailTo, String subject, String message) {
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setText(message);
        return simpleMailMessage;
    }
}
