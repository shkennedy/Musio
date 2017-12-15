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

	@Modifying
	@Query(value = "DELETE ufs1 FROM user_favorite_song ufs1 WHERE EXISTS ( SELECT * FROM (SELECT * FROM user_favorite_song) AS ufs2 WHERE ufs2.song_id = ufs1.song_id AND ufs2.user_id = ufs1.user_id AND ufs2.timestamp < ufs1.timestamp)"
			, nativeQuery = true)
	@Transactional
	void cleanFavoriteSongs();

	@Modifying
	@Query(value = "DELETE from user_favorite_song where user_id=:userId and song_id=:songId", nativeQuery = true)
	@Transactional
	void deleteFavoriteSongRecord(@Param("userId") int userId, @Param("songId") int songId);

	@Modifying
	@Query(value = "DELETE ufa1 FROM user_favorite_album ufa1 WHERE EXISTS ( SELECT * FROM (SELECT * FROM user_favorite_album) AS ufa2 WHERE ufa2.album_id = ufa1.album_id AND ufa2.user_id = ufa1.user_id AND ufa2.timestamp < ufa1.timestamp)"
			, nativeQuery = true)
	@Transactional
	void cleanFavoriteAlbums();

	@Modifying
	@Query(value = "DELETE from user_favorite_album where user_id=:userId and album_id=:albumId", nativeQuery = true)
	@Transactional
	void deleteFavoriteAlbumRecord(@Param("userId") int userId, @Param("albumId") int albumId);

	@Modifying
	@Query(value = "DELETE ufa1 FROM user_favorite_artist ufa1 WHERE EXISTS ( SELECT * FROM (SELECT * FROM user_favorite_artist) AS ufa2 WHERE ufa2.artist_id = ufa1.artist_id AND ufa2.user_id = ufa1.user_id AND ufa2.timestamp < ufa1.timestamp)"
			, nativeQuery = true)
	@Transactional
	void cleanFavoriteArtists();

	@Modifying
	@Query(value = "DELETE from user_favorite_artist where user_id=:userId and artist_id=:artistId", nativeQuery = true)
	@Transactional
	void deleteFavoriteArtistRecord(@Param("userId") int userId, @Param("artistId") int artistId);

	@Modifying
	@Query(value = "DELETE ufg1 FROM user_favorite_genre ufg1 WHERE EXISTS ( SELECT * FROM (SELECT * FROM user_favorite_genre) AS ufg2 WHERE ufg2.genre_id = ufg1.genre_id AND ufg2.user_id = ufg1.user_id AND ufg2.timestamp < ufg1.timestamp)"
			, nativeQuery = true)
	@Transactional
	void cleanFavoriteGenres();

	@Modifying
	@Query(value = "DELETE from user_favorite_genre where user_id=:userId and genre_id=:genreId", nativeQuery = true)
	@Transactional
	void deleteFavoriteGenreRecord(@Param("userId") int userId, @Param("genreId") int genreId);

	@Modifying
	@Query(value = "DELETE ufp1 FROM user_favorite_playlist ufp1 WHERE EXISTS ( SELECT * FROM (SELECT * FROM user_favorite_playlist) AS ufp2 WHERE ufp2.playlist_id = ufp1.playlist_id AND ufp2.user_id = ufp1.user_id AND ufp2.timestamp < ufp1.timestamp)"
			, nativeQuery = true)
	@Transactional
	void cleanFavoritePlaylists();

	@Modifying
	@Query(value = "DELETE from user_favorite_playlist where user_id=:userId and playlist_id=:playlistId", nativeQuery = true)
	@Transactional
	void deleteFavoritePlaylistRecord(@Param("userId") int userId, @Param("playlistId") int playlistId);

	@Modifying
	@Query(value = "DELETE ufu1 FROM user_following_user ufu1 WHERE EXISTS ( SELECT * FROM (SELECT * FROM user_following_user) AS ufu2 WHERE ufu2.follower = ufu1.follower AND ufu2.user_being_followed = ufu1.user_being_followed AND ufu2.timestamp < ufu1.timestamp)"
			, nativeQuery = true)
	@Transactional
	void cleanFollowingUsers();

	@Modifying
	@Query(value = "DELETE from user_following_user where follower=:user1Id and user_being_followed=:user2Id", nativeQuery = true)
	@Transactional
    void deleteFollowingUserRecord(@Param("user1Id") int follower, @Param("user2Id") int user_being_followed);
    
    @Query(value = "SELECT u.id as id, u.username as username FROM User u WHERE u.username LIKE CONCAT('%' :queryString, '%')", nativeQuery = true)
    Set<UserIdentifier> findByUsernameContaining(@Param("queryString") String queryString);
}
