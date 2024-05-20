package com.health.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.health.model.User;
import com.health.model.UserProfile;
import com.health.repository.UserProfileRepository;
import com.health.repository.UserRepository;

@Service
public class UserProfileService {

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	UserRepository userRepository;

	public UserProfile setUserProfile(UserProfile userProfile) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User u = userRepository.findByEmail(userDetails.getUsername());

		UserProfile up;

		if (u.getUserprofile() == null)
			up = new UserProfile();

		else {
			up = u.getUserprofile();
		}
		up.setFirstname(u.getFirstname());
		up.setLastname(u.getLastname());
		up.setAddress(userProfile.getAddress());
		up.setGender(userProfile.getGender());
		up.setMobile(userProfile.getMobile());
		u.setUserprofile(up);
		up.setUser(u);

		userRepository.save(u);
		return up;
	}

	public UserProfile getUserProfile() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		User u = userRepository.findByEmail(email);

		return u.getUserprofile();

	}

}
