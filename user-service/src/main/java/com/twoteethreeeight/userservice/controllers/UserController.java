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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
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
	public ResponseEntity<ProfileDto> getUserInfo(@RequestHeader(name = "Authorization") String token) {
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		String email = jwtTokenUtil.getUsernameFromToken(token);
		User user = userRepository.findByEmail(email);
			ProfileDto profileDto = new ProfileDto();
			profileDto.setEmail(user.getEmail());
			profileDto.setFullName(user.getFullName());
			profileDto.setPhone(user.getPhone());
//			return ResponseEntity.ok(user);
			return new ResponseEntity<ProfileDto>(profileDto, HttpStatus.OK);
	}

	@PostMapping("edit/{responseCode}")
	public ResponseEntity<String> updateUserByEmail(@PathVariable String responseCode,
			@RequestBody ProfileDto infoEdit) {

		String result = userService.updateUserByEmail(responseCode, infoEdit);

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

	@PutMapping("/edit")
	public ResponseEntity<String> editUserInfo(@RequestHeader("email") String email, @RequestBody ProfileDto infoEdit) {
			return userService.editUserInfo(email, infoEdit);
	}

}
