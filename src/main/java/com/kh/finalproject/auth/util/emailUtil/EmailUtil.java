package com.kh.finalproject.auth.util.emailUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private  JavaMailSender mailSender;

	    public void sendMail(SimpleMailMessage message) {
	        
	        mailSender.send(message);
	    }
        
        public void sendMailPw(SimpleMailMessage messagePw) {
            mailSender.send(messagePw);
        }

        public void sendMailId(SimpleMailMessage messageId) {
        	mailSender.send(messageId);
        }

}

