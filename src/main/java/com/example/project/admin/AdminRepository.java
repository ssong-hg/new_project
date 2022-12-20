package com.example.project.admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String>{

	
		Optional<Admin> findById(String id);
		Optional<Admin> findByIdAndPw(String id, String pw);
}
