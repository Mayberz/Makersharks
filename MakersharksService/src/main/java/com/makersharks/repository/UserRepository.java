package com.makersharks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makersharks.Entity.UserData;


public interface UserRepository extends JpaRepository<UserData, Integer> {

	public UserData findByUsername(String username);
}
