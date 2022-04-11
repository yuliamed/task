package by.iba.service.impl;

import by.iba.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {
    private final MailSender javaMailSender;
    //@Value("${spring.mail.username}")
    private final String sender = "test_my_sender@mail.ru";

    @Override
    public void sendEmail(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(sender);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText("Hello, this message can help you to confirm your account "+message);

        this.javaMailSender.send(mailMessage);
    }

}
