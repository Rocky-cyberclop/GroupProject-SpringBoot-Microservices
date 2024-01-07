package com.twoteethreeeight.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.twoteethreeeight.userservice.Dto.MailStructure;


@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("$(spring.mail.username)")
	private String mailFrom;
	
	@Autowired
	private CodeTmpService codeTmpService;

	public void sendMail(String mail, MailStructure mailStructure) {
		// TODO Auto-generated method stub
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(mailFrom);
		simpleMailMessage.setSubject("Your Valid Code");
		simpleMailMessage.setText(codeTmpService.genarateCodeTmp(mail));
		simpleMailMessage.setTo(mail);
		
		mailSender.send(simpleMailMessage);
	}

}
