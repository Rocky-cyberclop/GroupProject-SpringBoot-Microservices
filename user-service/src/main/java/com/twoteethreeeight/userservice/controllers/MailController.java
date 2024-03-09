package com.twoteethreeeight.userservice.controllers;

import com.twoteethreeeight.userservice.models.User;
import com.twoteethreeeight.userservice.repositories.UserRepository;
import com.twoteethreeeight.userservice.services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.twoteethreeeight.userservice.Dto.MailStructure;
import com.twoteethreeeight.userservice.services.MailService;

import java.util.Map;


@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/user/send")
public class MailController {

	@Autowired
	private MailService mailService;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProducerService producerService;

	@PostMapping("/register")
	public boolean sendRegister(@RequestBody Map<String,Object> result) {
		User user = userRepository.findByEmail(result.get("email").toString());
		if (user!= null){
			return false;
		}
		producerService.SendMessage(result.get("email").toString());
//		mailService.sendMail(mail);
		return true;
	}

	@PostMapping("/login/{mail}")
	public String sendLogin(@PathVariable String mail) {
		User user = userRepository.findByEmail(mail);
		producerService.SendMessage(mail);
		return "Send mail successfully";
	}
}
