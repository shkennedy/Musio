package com.sbu.webspotify.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sbu.webspotify.dto.identifier.SongIdentifier;
import com.sbu.webspotify.model.User;
import java.util.Set;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findById(int id); 
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String encryptedPassword);
	boolean existsByUsername(String username);

	@Modifying
	@Query(value = "INSERT INTO user_listening_history (user_id, song_id) values (:userId, :songId)", nativeQuery = true)
	@Transactional
	void addSongToUserHistory(@Param("userId") int userId, @Param("songId") int songId);
	
}
