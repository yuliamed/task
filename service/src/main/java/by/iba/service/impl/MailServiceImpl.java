package by.iba.service.impl;

import by.iba.exception.ServiceException;
import by.iba.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {
    private final MailSender mailSender;
    //@Value("${spring.mail.username}")
    private final String sender = "test_my_sender@mail.ru";

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String emailTo, String subject, String message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        try {
//            mailMessage.setFrom(sender);
//            mailMessage.setTo(emailTo);
//            mailMessage.setSubject(subject);
//            mailMessage.setText("Hello, this message can help you to confirm your account " + message);
//            this.javaMailSender.send(mailMessage);
//        } catch (MailException ex) {
//            throw new ServiceException("The mail box with name = " + emailTo + " does not exist! Check your email");
//        }
        mailMessage.setFrom(sender);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText("Hello, this message can help you to confirm your account " + message);
        this.mailSender.send(mailMessage);

    }


}

