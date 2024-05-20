package com.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.health.model.Reviews;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, String> {
}

