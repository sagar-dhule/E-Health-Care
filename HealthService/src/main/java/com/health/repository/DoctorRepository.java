package com.health.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.health.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {

    @Query("SELECT p FROM Doctor p WHERE p.name LIKE %?1%")
    Page<Doctor> searchByName(String keyword, org.springframework.data.domain.Pageable pageable);

    @Query("SELECT d FROM Doctor d INNER JOIN d.speciality s WHERE s.title LIKE %?1% AND d.name LIKE %?2% ")
    Page<Doctor> findAllBySpeciality(String name, String keyword, org.springframework.data.domain.Pageable pageable);

   
    @Query("SELECT d FROM Doctor d JOIN d.speciality s WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', ?1, '%')) AND LOWER(s.title) LIKE LOWER(CONCAT('%', ?2, '%'))")
    Page<Doctor> findAllDoctorsByKeyword(String keyword,Pageable pageable);


	@Query("SELECT d FROM Doctor d JOIN d.speciality s WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(s.title) LIKE LOWER(CONCAT('%', ?1, '%'))")	 
	Page<Doctor> findAllBySpecialityId(String speciality_id, String title, String keyword, Pageable paging);

	List<Doctor> findAllBySpecialityId(String specialityId);
}
