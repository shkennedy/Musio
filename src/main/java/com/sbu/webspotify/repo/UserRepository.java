package com.sbu.webspotify.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbu.webspotify.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findById(int id); 
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String encryptedPassword);
	boolean existsByUsername(String username);
}
