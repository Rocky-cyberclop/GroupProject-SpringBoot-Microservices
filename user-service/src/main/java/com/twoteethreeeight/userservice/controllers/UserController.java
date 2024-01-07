package com.twoteethreeeight.userservice.controllers;

import com.twoteethreeeight.userservice.Dto.ProfileDto;
import com.twoteethreeeight.userservice.Provider.AuthenticationResponse;
import com.twoteethreeeight.userservice.Provider.EntityResponse;
import com.twoteethreeeight.userservice.config.JWTTokenUtil;
import com.twoteethreeeight.userservice.models.User;
import com.twoteethreeeight.userservice.repositories.UserRepository;
import com.twoteethreeeight.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	@GetMapping("all")
	public ResponseEntity<List<User>> getAllUser() {
		return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
	}

	/* This function is just for create test User and not need to reuse */
	@GetMapping("createTestUser")
	public ResponseEntity<List<User>> createTestUser() {
		return new ResponseEntity<List<User>>(userService.createTestUser(), HttpStatus.OK);
	}

	@PostMapping("login")
	public ResponseEntity<Object> login(@RequestBody Map<String, String> loginData) {
		String email = loginData.get("Email");
		String responseCode = loginData.get("responseCode");
		User user = userRepository.findByEmail(email);
		String role = user.getRole();
		if (responseCode != null) {
			String token = userService.loginUser(email, responseCode);
			if (token != null) {
				return EntityResponse.genarateResponse("Authentication", HttpStatus.OK,
						new AuthenticationResponse(token, role));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid CodeTmp or Code Expirated");
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid CodeTmp");
	}

	@PostMapping("register")
	public ResponseEntity<String> registerUser(@RequestBody User user, @RequestParam String responseCode) {
		String result = userService.registerUser(user, responseCode);

		if (result.equals("Register successfully")) {
			return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
		} else if (result.equals("Email already exists")) {
			return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("The authentication code is incorrect or expired", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("info")
	public ResponseEntity<?> getUserInfo(@RequestHeader(name = "Authorization") String token) {
		User user = userService.Authenticate(token);
		if (user != null) {
			ProfileDto profileDto = new ProfileDto(user.getFullName(), user.getEmail(), user.getPhone());
			return ResponseEntity.ok(profileDto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
	}

	@PostMapping("edit")
	public ResponseEntity<String> updateUserByEmail(@RequestParam String responseCode, @RequestParam String email,
			@RequestBody User infoEdit) {

		String result = userService.updateUserByEmail(responseCode, email, infoEdit);

		switch (result) {
		case "Edit user successfully":
			return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
		case "The authentication code is incorrect or expired":
			return new ResponseEntity<>("The authentication code is incorrect or expired", HttpStatus.BAD_REQUEST);
		case "Changing email is not allowed":
			return new ResponseEntity<>("Changing email is not allowed", HttpStatus.BAD_REQUEST);
		case "User not found":
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		default:
			return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
