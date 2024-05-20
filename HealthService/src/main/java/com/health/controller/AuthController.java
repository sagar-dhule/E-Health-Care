package com.health.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.health.model.User;
import com.health.payload.request.LoginRequest;
import com.health.payload.request.SignupRequest;
import com.health.payload.response.JwtResponse;
import com.health.payload.response.MessageResponse;
import com.health.repository.UserRepository;
import com.health.security.jwt.JwtUtils;
import com.health.security.services.UserDetailsImpl;
import com.health.services.DoctorService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	DoctorService doctorService;

	@Autowired
	JwtUtils jwtUtils;
	
	

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(userDetails.getId(), userDetails.getEmail(),
				userDetails.getFirstname(), userDetails.getLastname(), userDetails.getAuthorities().toString(), jwt));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		// Create new user's account
		User user = new User(signUpRequest.getFirstname(), signUpRequest.getLastname(), 
				signUpRequest.getGender(), signUpRequest.getAge(), signUpRequest.getMobile(),
				signUpRequest.getAddress(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()),signUpRequest.getSpecialization(),
						signUpRequest.getExperience());

		String strRoles = signUpRequest.getType();

		if (strRoles == null) {
			System.err.println("Role is null");

		}
		if (strRoles.equals("patient") || strRoles.equals("doctor") || strRoles.equals("admin"))
			user.setType("ROLE_" + strRoles);
		else {
			throw new RuntimeException("Error: Role is not found.");
		}
		
		User docuser=userRepository.save(user);
		
		
		if (strRoles.equals("doctor")) {
			
			doctorService.saveDoctorUser(docuser);
		}
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public ResponseEntity<?> logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		System.out.println("logged out sucessfully");
		return ResponseEntity.ok(new MessageResponse("Logged out successfully!"));
	}
}
