package by.iba.service.impl;

import by.iba.exception.ServiceException;
import by.iba.service.MailSender;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class MailSenderImpl implements MailSender {
    private final JavaMailSender javaMailSender;


    @Override
    @Async
    public void send(final SimpleMailMessage message) {
        if (Objects.nonNull(message)) {
            try {
                this.javaMailSender.send(message);
            } catch (Exception e) {
                throw new ServiceException(HttpStatus.CONFLICT.value() + " exception.by.iba.email.sending_exception");
            }
        }
    }
}
