package com.flightreservation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightreservation.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByusername(String username);
}
