package com.twoteethreeeight.userservice.controllers;

import com.twoteethreeeight.userservice.models.User;
import com.twoteethreeeight.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twoteethreeeight.userservice.Dto.MailStructure;
import com.twoteethreeeight.userservice.services.MailService;


@RestController
@RequestMapping("/send")
public class MailController {

	@Autowired
	private MailService mailService;
	@Autowired
	private UserRepository userRepository;
	@PostMapping("/register/{mail}")
	public String sendRegister(@PathVariable String mail) {
		User user = userRepository.findByEmail(mail);
		if (user!= null && user.getIsRegister() == false){
			return "Email already exists ";
		}
		mailService.sendMail(mail);
		return "Send mail successfully";
	}

	@PostMapping("/login/{mail}")
	public String sendLogin(@PathVariable String mail) {
		mailService.sendMail(mail);
		return "Send mail successfully";
	}
}
