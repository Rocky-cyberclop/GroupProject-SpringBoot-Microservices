package com.twoteethreeeight.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twoteethreeeight.userservice.Dto.MailStructure;
import com.twoteethreeeight.userservice.services.MailService;


@RestController
@RequestMapping("/api")
public class MailController {

	@Autowired
	private MailService mailService;
	
	@PostMapping("/send/{mail}")
	public String send(@PathVariable String mail,@RequestBody MailStructure mailStructure) {
		mailService.sendMail(mail,mailStructure);
		return "Send mail successfully";
	}
}
