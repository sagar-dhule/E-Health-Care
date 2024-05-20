package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.model.User;
import com.health.model.UserProfile;
import com.health.payload.response.MessageResponse;
import com.health.repository.UserRepository;
import com.health.services.UserProfileService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserProfileController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileService userProfileService;

	@PreAuthorize("hasRole('user') or hasRole('admin')")
	@PostMapping("/setprofile")
	public ResponseEntity<?> setUserProfile(@RequestBody UserProfile userProfile) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		if (email.equals("anonymousUser")) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: User is not logged in!"));
		}

		userProfileService.setUserProfile(userProfile);

		return ResponseEntity.ok(new MessageResponse("User profile updated"));

	}

	@GetMapping("/profileDetails/{email}")
	public ResponseEntity<User> getProfileDetails(@PathVariable String email) throws Exception
	{
	User user = userRepository.findByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
