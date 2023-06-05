package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.User;

public interface UserLoginRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);

}
