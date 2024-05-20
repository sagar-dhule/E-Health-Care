package com.health.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.model.Speciality;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, String> {
	List<Speciality> findByTitle(String title);

}
