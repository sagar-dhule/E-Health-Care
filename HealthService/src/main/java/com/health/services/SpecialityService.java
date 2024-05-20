package com.health.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.model.Speciality;
import com.health.repository.SpecialityRepository;

@Service
public class SpecialityService {

	@Autowired
	SpecialityRepository SpecialityRepository;

	public String saveSpeciality(Speciality Speciality) {
		SpecialityRepository.save(Speciality);
		return Speciality.getTitle();
	}

	public Speciality getSpecialityByid(String id) {
		return SpecialityRepository.findById(id).get();
	}

	public List<Speciality> getAllSpeciality() {
		return SpecialityRepository.findAll();

	}

}
