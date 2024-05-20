package com.health.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

	Payment findByDoctorid(String doctorId);

}
