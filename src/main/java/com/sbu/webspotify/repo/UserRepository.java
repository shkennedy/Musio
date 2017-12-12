package com.sbu.webspotify.repo;

import javax.transaction.Transactional;
import com.sbu.webspotify.dto.identifier.UserIdentifier;
import com.sbu.webspotify.model.User;
import java.util.Set;
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

	@Query(value = "SELECT distinct u2.id as id, u2.username as username from user_following_user ufu, user u1, user u2 WHERE u1.id = ufu.follower and u2.id=ufu.user_being_followed and u1.id = :userId",
	nativeQuery = true)
	Set<UserIdentifier> getFollowedUsers(@Param("userId") int userId);

}
