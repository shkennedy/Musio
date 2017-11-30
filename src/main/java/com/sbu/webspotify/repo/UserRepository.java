package com.sbu.webspotify.repo;

import javax.transaction.Transactional;

import com.sbu.webspotify.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findById(int id); 
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String encryptedPassword);
	boolean existsByUsername(String username);
	boolean existsById(int id);

	@Modifying
	@Query(value = "INSERT INTO user_listening_history (user_id, song_id) values (:userId, :songId)", nativeQuery = true)
	@Transactional
	void addSongToUserHistory(@Param("userId") int userId, @Param("songId") int songId);
	
}
