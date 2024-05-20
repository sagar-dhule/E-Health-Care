package com.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health.model.User;
import com.health.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

	public UserProfile findByUser(User user);

}
