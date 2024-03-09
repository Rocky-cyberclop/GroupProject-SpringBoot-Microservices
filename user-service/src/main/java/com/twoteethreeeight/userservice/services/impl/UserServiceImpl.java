package com.twoteethreeeight.userservice.services.impl;

import com.twoteethreeeight.userservice.Dto.ProfileDto;
import com.twoteethreeeight.userservice.config.JWTTokenUtil;
import com.twoteethreeeight.userservice.models.User;
import com.twoteethreeeight.userservice.repositories.UserRepository;
import com.twoteethreeeight.userservice.services.CodeTmpService;
import com.twoteethreeeight.userservice.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CodeTmpService codeTmpService;

	@Autowired
	private UserDetailsService userDetailsService;

    /*This function is just for create test User and not need to reuse*/
    @Override
    public List<User> createTestUser() {
		List<User> users = new ArrayList<>();
		User user1 = new User();
		user1.setFullName("Tran Anh Hao");
		user1.setEmail("rockyoperation@gmail.com");
		user1.setPhone("0355669359");
		user1.setRole("User");
		users.add(user1);
		User user2 = new User();
		user2.setFullName("Nguyen Tri Thuc");
		user2.setEmail("trithuc0416@gmail.com");
		user2.setPhone("0355611359");
		user2.setRole("User");
		users.add(user2);
		User user3 = new User();
		user3.setFullName("Thuc Tri Nguyen");
		user3.setEmail("thucb2005736@student.ctu.edu.vn");
		user3.setPhone("0355611322");
		user3.setRole("User");
		users.add(user3);

		userRepository.saveAll(users);
		return users;
	}

	@Override
	public ResponseEntity<String> editUserInfo(String email, ProfileDto infoEdit) {
//		System.out.println(infoEdit.toString());
		User user = userRepository.findByEmail(email);
		user.setPhone(infoEdit.getPhone());
		user.setFullName(infoEdit.getFullName());
		userRepository.save(user);

		return new ResponseEntity<>("Edit user info successfully!", HttpStatus.OK);
	}

	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	@Override
	public Optional<User> getUserById(ObjectId objectId) {
		return userRepository.findById(objectId);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}



	@Override
	public String loginUser(String email, String responseCode) {
		User user = userRepository.findByEmail(email);

		if (user != null && codeTmpService.validateCode(email, responseCode)) {
			return jwtTokenUtil.generateToken(email);
		}
		return null;
	}

	@Override
	public boolean registerUser(String email, String responseCode) {
		if (codeTmpService.validateCode(email, responseCode)) {
			return true;
		}
		return false;
	}

	@Override
	public User Authenticate(String token) {

		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		String email = jwtTokenUtil.getUsernameFromToken(token);
		User user = userRepository.findByEmail(email);
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public String updateUserByEmail(String responeCode, ProfileDto infoEdit) {
		String email = infoEdit.getEmail();
		User user = userRepository.findByEmail(email);

		if (user != null) {
			if (!codeTmpService.validateCode(email, responeCode)) {
				return "The authentication code is incorrect or expired";
			}
			// khong cho phep thay doi email
			if (!user.getEmail().equals(infoEdit.getEmail())) {
				return "Changing email is not allowed";
			}
			user.setFullName(infoEdit.getFullName());
			user.setPhone(infoEdit.getPhone());
			userRepository.save(user);
			return "Edit user successfully";

		} else {
			return "User not found";
		}
	}

}
