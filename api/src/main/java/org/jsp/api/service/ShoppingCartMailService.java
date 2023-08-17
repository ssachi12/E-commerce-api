package org.jsp.api.service;

import org.jsp.api.dto.EmailConfigaration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class ShoppingCartMailService {
@Autowired
private JavaMailSender mailSender;
public String sendWelcomeMail(EmailConfigaration config) {
	MimeMessage message= mailSender.createMimeMessage();
	MimeMessageHelper helper=new MimeMessageHelper(message);
	try {
		helper.setTo(config.getUser().get("email"));
		helper.setSubject(config.getSubject());
		helper.setText(config.getText());
		mailSender.send(message);
		return "mail sent succesfully";
	} catch (Exception e) {
		e.printStackTrace();
		return "unable to send message";
	}
}
}
