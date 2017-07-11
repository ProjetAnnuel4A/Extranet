package com.esgi.extranet.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author timotheearnauld
 */
@Service
public class MailClient {
    private JavaMailSender mailSender;

    @Autowired
    public MailClient(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @ResponseBody
    public Boolean prepareAndSend(String recipient, String subject, String message){
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(message, true);
        };
        try{
            mailSender.send(messagePreparator);
        }catch(MailException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
